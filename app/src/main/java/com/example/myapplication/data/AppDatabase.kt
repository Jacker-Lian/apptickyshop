package com.example.myapplication.data
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import android.content.Context

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}