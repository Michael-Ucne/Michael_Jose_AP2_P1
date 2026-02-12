package com.phantomshard.michael_jose_ap2_p1.presentation.cerveza

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.tooling.preview.Preview
import com.phantomshard.michael_jose_ap2_p1.ui.theme.Michael_Jose_AP2_P1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CervezaEditScreen(
    cervezaId: Int?,
    onNavigateBack: () -> Unit,
    viewModel: CervezaViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(cervezaId) {
        if (cervezaId != null && cervezaId != 0) {
            viewModel.onEvent(CervezaUiEvent.SelectedCerveza(cervezaId))
        }
    }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onNavigateBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (cervezaId == null || cervezaId == 0) "Nueva Cerveza" else "Editar Cerveza") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { innerPadding ->
        CervezaEditBody(
            uiState = uiState,
            onEvent = viewModel::onEvent,
            onCancel = onNavigateBack,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun CervezaEditBody(
    uiState: CervezaUiState,
    onEvent: (CervezaUiEvent) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = uiState.nombre,
            onValueChange = { onEvent(CervezaUiEvent.NombreChanged(it)) },
            label = { Text("Nombre") },
            isError = uiState.nombreError != null,
            supportingText = { uiState.nombreError?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.marca,
            onValueChange = { onEvent(CervezaUiEvent.MarcaChanged(it)) },
            label = { Text("Marca") },
            isError = uiState.marcaError != null,
            supportingText = { uiState.marcaError?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.puntuacion,
            onValueChange = { onEvent(CervezaUiEvent.PuntuacionChanged(it)) },
            label = { Text("Puntuación (1-5)") },
            isError = uiState.puntuacionError != null,
            supportingText = { uiState.puntuacionError?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }

            if (uiState.cervezaId != null && uiState.cervezaId != 0) {
                Button(
                    onClick = { onEvent(CervezaUiEvent.Delete) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = null)
                    Spacer(Modifier.width(4.dp))
                    Text("Eliminar")
                }
            }

            Button(
                onClick = { onEvent(CervezaUiEvent.Save) },
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Save, contentDescription = null)
                Spacer(Modifier.width(4.dp))
                Text("Guardar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CervezaEditPreview() {
    Michael_Jose_AP2_P1Theme {
        CervezaEditBody(
            uiState = CervezaUiState(
                nombre = "Presidente",
                marca = "Cervecería Nacional",
                puntuacion = "5"
            ),
            onEvent = {},
            onCancel = {}
        )
    }
}
