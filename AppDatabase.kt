package dz.bendriss.cabinet.data.local

import android.content.Context
import androidx.room.*

@Database(
    entities = [Patient::class, Consultation::class, Service::class, Paiement::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
    abstract fun consultationDao(): ConsultationDao
    abstract fun serviceDao(): ServiceDao
    abstract fun paiementDao(): PaiementDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        fun get(context: Context): AppDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "cabinet.db"
            ).build().also { instance = it }
        }
    }
}
