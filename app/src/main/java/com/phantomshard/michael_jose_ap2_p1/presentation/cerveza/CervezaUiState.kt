package com.phantomshard.michael_jose_ap2_p1.presentation.cerveza

import com.phantomshard.michael_jose_ap2_p1.domain.model.Cerveza

data class CervezaUiState(
    val isLoading: Boolean = false,
    val cervezaId: Int? = null,
    val nombre: String = "",
    val marca: String = "",
    val puntuacion: String = "",
    val nombreError: String? = null,
    val marcaError: String? = null,
    val puntuacionError: String? = null,
    val error: String? = null,
    val cervezas: List<Cerveza> = emptyList(),
    val conteoTotal: Int = 0,
    val promedioPuntuacion: Double = 0.0,
    val isSuccess: Boolean = false
)

sealed interface CervezaUiEvent {
    data class NombreChanged(val nombre: String) : CervezaUiEvent
    data class MarcaChanged(val marca: String) : CervezaUiEvent
    data class PuntuacionChanged(val puntuacion: String) : CervezaUiEvent
    data object Save : CervezaUiEvent
    data object Delete : CervezaUiEvent
    data class DeleteCerveza(val cerveza: Cerveza) : CervezaUiEvent
    data class SelectedCerveza(val it: Int) : CervezaUiEvent
}
