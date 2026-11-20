package dz.bendriss.cabinet.ui.patients

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dz.bendriss.cabinet.data.local.Patient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientsScreen(vm: PatientsViewModel = viewModel()) {
    val patients by vm.patients.collectAsStateWithLifecycle()
    val q by vm.recherche.collectAsStateWithLifecycle()
    var dialogue by remember { mutableStateOf(false) }
    var aSupprimer by remember { mutableStateOf<Patient?>(null) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Patients") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { dialogue = true }) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter un patient")
            }
        }
    ) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            OutlinedTextField(
                value = q, onValueChange = { vm.recherche.value = it },
                label = { Text("Rechercher (nom, prénom, téléphone)") },
                modifier = Modifier.fillMaxWidth(), singleLine = true
            )
            Spacer(Modifier.height(12.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(patients, key = { it.id }) { p ->
                    Card(Modifier.fillMaxWidth()) {
                        ListItem(
                            headlineContent = { Text("${p.nom} ${p.prenom}") },
                            supportingContent = { Text("${p.numeroDossier}  •  ${p.telephone}") },
                            trailingContent = {
                                IconButton(onClick = { aSupprimer = p }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Supprimer")
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    if (dialogue) {
        var nom by remember { mutableStateOf("") }
        var prenom by remember { mutableStateOf("") }
        var tel by remember { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = { dialogue = false },
            title = { Text("Nouveau patient") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(nom, { nom = it }, label = { Text("Nom") })
                    OutlinedTextField(prenom, { prenom = it }, label = { Text("Prénom") })
                    OutlinedTextField(tel, { tel = it }, label = { Text("Téléphone") })
                }
            },
            confirmButton = {
                TextButton(enabled = nom.isNotBlank() && prenom.isNotBlank(), onClick = {
                    vm.ajouter(nom.trim(), prenom.trim(), tel.trim()); dialogue = false
                }) { Text("Enregistrer") }
            },
            dismissButton = { TextButton(onClick = { dialogue = false }) { Text("Annuler") } }
        )
    }

    aSupprimer?.let { p ->
        AlertDialog(
            onDismissRequest = { aSupprimer = null },
            title = { Text("Supprimer le patient ?") },
            text = { Text("Le dossier de ${p.nom} ${p.prenom} et tout son historique seront supprimés.") },
            confirmButton = {
                TextButton(onClick = { vm.supprimer(p); aSupprimer = null }) { Text("Supprimer") }
            },
            dismissButton = { TextButton(onClick = { aSupprimer = null }) { Text("Annuler") } }
        )
    }
}
