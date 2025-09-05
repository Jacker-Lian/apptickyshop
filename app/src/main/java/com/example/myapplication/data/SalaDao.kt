package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SalaDao {
    @Insert
    suspend fun insert(sala: Sala)

    @Query("SELECT * FROM salas WHERE nombre = :nombre AND password = :password LIMIT 1")
    suspend fun getSala(nombre: String, password: String): Sala?

    @Query("SELECT * FROM salas")
    suspend fun getAllSalas(): List<Sala>
}
