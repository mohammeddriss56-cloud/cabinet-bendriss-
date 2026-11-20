package dz.bendriss.cabinet.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {
    @Query("""SELECT * FROM patients
        WHERE nom LIKE '%' || :q || '%' OR prenom LIKE '%' || :q || '%'
        OR telephone LIKE '%' || :q || '%' ORDER BY nom, prenom""")
    fun rechercher(q: String): Flow<List<Patient>>

    @Query("SELECT COUNT(*) FROM patients") fun total(): Flow<Int>
    @Query("SELECT * FROM patients ORDER BY creeLe DESC LIMIT :n") fun derniers(n: Int): Flow<List<Patient>>
    @Insert suspend fun inserer(p: Patient): Long
    @Update suspend fun modifier(p: Patient)
    @Delete suspend fun supprimer(p: Patient)
}

@Dao
interface ConsultationDao {
    @Query("SELECT * FROM consultations WHERE patientId = :id ORDER BY date DESC")
    fun duPatient(id: Long): Flow<List<Consultation>>
    @Query("SELECT COUNT(*) FROM consultations WHERE date BETWEEN :debut AND :fin")
    fun compter(debut: Long, fin: Long): Flow<Int>
    @Insert suspend fun inserer(c: Consultation): Long
    @Update suspend fun modifier(c: Consultation)
    @Delete suspend fun supprimer(c: Consultation)
}

@Dao
interface ServiceDao {
    @Query("SELECT * FROM services ORDER BY libelle") fun tous(): Flow<List<Service>>
    @Insert suspend fun inserer(s: Service): Long
    @Update suspend fun modifier(s: Service)
    @Delete suspend fun supprimer(s: Service)
}

@Dao
interface PaiementDao {
    @Query("SELECT COALESCE(SUM(verseDa),0) FROM paiements WHERE date BETWEEN :debut AND :fin")
    fun recettes(debut: Long, fin: Long): Flow<Double>
    @Query("SELECT * FROM paiements WHERE patientId = :id ORDER BY date DESC")
    fun duPatient(id: Long): Flow<List<Paiement>>
    @Insert suspend fun inserer(p: Paiement): Long
    @Update suspend fun modifier(p: Paiement)
}
