package com.phantomshard.michael_jose_ap2_p1.presentation.cerveza

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.phantomshard.michael_jose_ap2_p1.domain.model.Cerveza
import androidx.compose.ui.tooling.preview.Preview
import com.phantomshard.michael_jose_ap2_p1.ui.theme.Michael_Jose_AP2_P1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CervezaListScreen(
    onAddCerveza: () -> Unit,
    onEditCerveza: (Int) -> Unit,
    viewModel: CervezaViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("BeerTracker - Lista") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddCerveza,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Cerveza")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (uiState.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            // Lista
            CervezaListBody(
                cervezas = uiState.cervezas,
                onEditCerveza = onEditCerveza,
                onDeleteCerveza = { viewModel.onEvent(CervezaUiEvent.DeleteCerveza(it)) },
                modifier = Modifier.weight(1f)
            )

            // Totales (Footer)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Total Cervezas Probadas: ${uiState.conteoTotal}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Promedio Puntuación: ${"%.2f".format(uiState.promedioPuntuacion)}/5",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Composable
fun CervezaListBody(
    cervezas: List<Cerveza>,
    onEditCerveza: (Int) -> Unit,
    onDeleteCerveza: (Cerveza) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cervezas) { cerveza ->
            CervezaItem(
                cerveza = cerveza,
                onEdit = { cerveza.cervezaId?.let { onEditCerveza(it) } },
                onDelete = { onDeleteCerveza(cerveza) }
            )
        }
    }
}

@Composable
fun CervezaItem(
    cerveza: Cerveza,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Nombre: ${cerveza.nombre}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Marca: ${cerveza.marca}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Puntuación: ${cerveza.puntuacion}/5", style = MaterialTheme.typography.bodySmall)
            }
            
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = "Editar", tint = MaterialTheme.colorScheme.primary)
            }
            
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CervezaListPreview() {
    Michael_Jose_AP2_P1Theme {
        CervezaListBody(
            cervezas = listOf(
                Cerveza(1, "Presidente", "Cervecería Nacional", 5),
                Cerveza(2, "Presidente Light", "Cervecería Nacional", 3)
            ),
            onEditCerveza = {},
            onDeleteCerveza = {}
        )
    }
}
