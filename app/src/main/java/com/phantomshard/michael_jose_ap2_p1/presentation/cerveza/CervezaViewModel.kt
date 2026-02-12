package com.phantomshard.michael_jose_ap2_p1.presentation.cerveza

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phantomshard.michael_jose_ap2_p1.domain.model.Cerveza
import com.phantomshard.michael_jose_ap2_p1.domain.usecase.DeleteCervezaUseCase
import com.phantomshard.michael_jose_ap2_p1.domain.usecase.GetCervezaUseCase
import com.phantomshard.michael_jose_ap2_p1.domain.usecase.GetCervezasUseCase
import com.phantomshard.michael_jose_ap2_p1.domain.usecase.SaveCervezaUseCase
import com.phantomshard.michael_jose_ap2_p1.domain.usecase.CervezaValidations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CervezaViewModel @Inject constructor(
    private val getCervezasUseCase: GetCervezasUseCase,
    private val saveCervezaUseCase: SaveCervezaUseCase,
    private val deleteCervezaUseCase: DeleteCervezaUseCase,
    private val getCervezaUseCase: GetCervezaUseCase,
    private val validations: CervezaValidations
) : ViewModel() {

    private val _uiState = MutableStateFlow(CervezaUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getCervezas()
    }

    fun onEvent(event: CervezaUiEvent) {
        when (event) {
            is CervezaUiEvent.NombreChanged -> {
                _uiState.update { it.copy(nombre = event.nombre, nombreError = null) }
            }
            is CervezaUiEvent.MarcaChanged -> {
                _uiState.update { it.copy(marca = event.marca, marcaError = null) }
            }
            is CervezaUiEvent.PuntuacionChanged -> {
                _uiState.update { it.copy(puntuacion = event.puntuacion, puntuacionError = null) }
            }
            CervezaUiEvent.Save -> {
                if (validate()) {
                    saveCerveza()
                }
            }
            CervezaUiEvent.Delete -> {
                deleteCerveza()
            }
            is CervezaUiEvent.DeleteCerveza -> {
                deleteCerveza(event.cerveza)
            }
            is CervezaUiEvent.SelectedCerveza -> {
                if (event.it != 0) {
                    loadCerveza(event.it)
                }
            }
        }
    }

    private fun loadCerveza(id: Int) {
        viewModelScope.launch {
            val cerveza = getCervezaUseCase(id)
            cerveza?.let { selected ->
                _uiState.update { it.copy(
                    cervezaId = selected.cervezaId,
                    nombre = selected.nombre,
                    marca = selected.marca,
                    puntuacion = selected.puntuacion.toString(),
                    nombreError = null,
                    marcaError = null,
                    puntuacionError = null
                ) }
            }
        }
    }

    private fun getCervezas() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getCervezasUseCase().collect { list ->
                val count = list.size
                val average = if (count > 0) list.map { it.puntuacion }.average() else 0.0

                _uiState.update { it.copy(
                    cervezas = list,
                    conteoTotal = count,
                    promedioPuntuacion = average,
                    isLoading = false
                ) }
            }
        }
    }

    private fun validate(): Boolean {
        val score = _uiState.value.puntuacion.toIntOrNull() ?: 0
        val errors = validations.validate(_uiState.value.nombre, _uiState.value.marca, score)
        
        _uiState.update { it.copy(
            nombreError = errors["nombre"],
            marcaError = errors["marca"],
            puntuacionError = errors["puntuacion"]
        ) }

        return errors.values.all { it == null }
    }

    private fun saveCerveza() {
        viewModelScope.launch {
            saveCervezaUseCase(
                Cerveza(
                    cervezaId = _uiState.value.cervezaId,
                    nombre = _uiState.value.nombre,
                    marca = _uiState.value.marca,
                    puntuacion = _uiState.value.puntuacion.toIntOrNull() ?: 0
                )
            )
            _uiState.update { it.copy(isSuccess = true) }
            clearData()
        }
    }

    private fun deleteCerveza() {
        viewModelScope.launch {
            _uiState.value.cervezaId?.let { id ->
                deleteCervezaUseCase(
                    Cerveza(
                        cervezaId = id,
                        nombre = _uiState.value.nombre,
                        marca = _uiState.value.marca,
                        puntuacion = _uiState.value.puntuacion.toIntOrNull() ?: 0
                    )
                )
                _uiState.update { it.copy(isSuccess = true) }
                clearData()
            }
        }
    }

    private fun deleteCerveza(cerveza: Cerveza) {
        viewModelScope.launch {
            deleteCervezaUseCase(cerveza)
        }
    }

    private fun clearData() {
        _uiState.update { it.copy(
            cervezaId = null,
            nombre = "",
            marca = "",
            puntuacion = "",
            nombreError = null,
            marcaError = null,
            puntuacionError = null
        ) }
    }
}
