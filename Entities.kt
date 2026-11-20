package dz.bendriss.cabinet.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "patients", indices = [Index("nom"), Index("telephone")])
data class Patient(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val numeroDossier: String = "",
    val nom: String,
    val prenom: String,
    val dateNaissance: String = "",
    val sexe: String = "",
    val telephone: String = "",
    val adresse: String = "",
    val antecedents: String = "",
    val allergies: String = "",
    val traitementsHabituels: String = "",
    val creeLe: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "consultations",
    foreignKeys = [ForeignKey(Patient::class, ["id"], ["patientId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("patientId")]
)
data class Consultation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val patientId: Long,
    val date: Long = System.currentTimeMillis(),
    val motif: String = "",
    val histoire: String = "",
    val examenClinique: String = "",
    val poidsKg: Double? = null,
    val tailleCm: Double? = null,
    val tension: String = "",
    val frequenceCardiaque: Int? = null,
    val spo2: Int? = null,
    val auscultation: String = "",
    val diagnostic: String = "",
    val conduiteATenir: String = "",
    val traitement: String = "",
    val prochaineConsultation: Long? = null
) {
    val imc: Double?
        get() = if (poidsKg != null && tailleCm != null && tailleCm > 0)
            poidsKg / ((tailleCm / 100) * (tailleCm / 100)) else null
}

@Entity(tableName = "services")
data class Service(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val libelle: String,
    val prixDa: Double
)

@Entity(
    tableName = "paiements",
    foreignKeys = [ForeignKey(Patient::class, ["id"], ["patientId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("patientId")]
)
data class Paiement(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val patientId: Long,
    val libelleService: String,
    val montantDa: Double,
    val verseDa: Double,
    val date: Long = System.currentTimeMillis()
) {
    val impayeDa: Double get() = (montantDa - verseDa).coerceAtLeast(0.0)
}
