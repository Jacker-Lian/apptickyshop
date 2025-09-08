package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SalaDao {
    @Insert
    suspend fun insert(sala: Sala)

    @Insert
    suspend fun insertSalaUsuario(salaUsuario: SalaUsuario)

    @Query("SELECT * FROM salas WHERE creadorEmail = :email")
    fun getSalasCreadasPorUsuario(email: String): List<Sala>

    @Query("SELECT s.* FROM salas s INNER JOIN salas_usuarios su ON s.id = su.salaId WHERE su.usuarioEmail = :email")
    fun getSalasUnidasPorUsuario(email: String): List<Sala>

    // AGREGA ESTE MÉTODO:
    @Query("SELECT * FROM salas WHERE nombre = :nombre AND password = :password LIMIT 1")
    fun getSala(nombre: String, password: String): Sala?
}