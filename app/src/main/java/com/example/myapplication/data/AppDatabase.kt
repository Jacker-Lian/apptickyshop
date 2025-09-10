package com.example.myapplication.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(
    entities = [
        User::class,
        Sala::class,
        ItemCompra::class,
        Tarea::class,
        SalaUsuario::class // 👈 agregado para que se cree la tabla salas_usuarios
    ],
    version = 4, // 👈 subiste a 4 para reflejar los cambios en la DB
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun salaDao(): SalaDao
    abstract fun itemCompraDao(): ItemCompraDao
    abstract fun tareaDao(): TareaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my_database"
                )
                    // ⚠️ Borra y recrea la DB automáticamente si hay cambios en la versión
                    // (útil mientras desarrollas, en producción deberías crear migraciones)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
