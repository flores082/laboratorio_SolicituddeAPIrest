package com.example.flores.laboratorio_solicituddeapirest.Dao

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flores.laboratorio_solicituddeapirest.Adapter.NombreEntity
/*
@Entity(tableName = "nombres_table")
// DAO (Data Access Object) para realizar operaciones en la base de datos
@Dao
interface NombreDao {
    @Insert
    suspend fun insertNombre(nombre: NombreEntity)

    @Query("SELECT * FROM nombres_table")
    suspend fun getAllNombres(): List<NombreEntity>
}

// Base de datos Room
@Database(entities = [NombreEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nombreDao(): NombreDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}*/