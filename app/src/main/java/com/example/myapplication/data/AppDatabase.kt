package com.example.myapplication.data

import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import android.content.Context

// 👇 Incluimos las dos entidades y actualizamos la versión
@Database(entities = [User::class, Sala::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun salaDao(): SalaDao  // 👈 nuevo DAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my_database"
                )
                    // Si cambiaste la versión y aún no tienes migraciones,
                    // puedes usar fallbackToDestructiveMigration()
                    // (esto borra y recrea la BD al actualizar versión)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
