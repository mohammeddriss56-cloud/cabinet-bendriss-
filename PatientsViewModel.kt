package dz.bendriss.cabinet.ui.patients

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dz.bendriss.cabinet.data.local.AppDatabase
import dz.bendriss.cabinet.data.local.Patient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class PatientsViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = AppDatabase.get(app).patientDao()
    val recherche = MutableStateFlow("")
    val patients: StateFlow<List<Patient>> = recherche
        .flatMapLatest { dao.rechercher(it.trim()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun ajouter(nom: String, prenom: String, telephone: String) = viewModelScope.launch {
        val id = dao.inserer(Patient(nom = nom, prenom = prenom, telephone = telephone))
        dao.modifier(dao.let { Patient(id = id, numeroDossier = "D-%05d".format(id),
            nom = nom, prenom = prenom, telephone = telephone) })
    }

    fun supprimer(p: Patient) = viewModelScope.launch { dao.supprimer(p) }
}
