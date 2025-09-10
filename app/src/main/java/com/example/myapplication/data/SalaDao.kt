package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SalaDao {
    // Insertar nueva sala
    @Insert
    suspend fun insert(sala: Sala)

    // Insertar relación sala-usuario (cuando un usuario se une a una sala)
    @Insert
    suspend fun insertSalaUsuario(salaUsuario: SalaUsuario)

    // Buscar sala por nombre y contraseña
    @Query("SELECT * FROM salas WHERE nombre = :nombre AND password = :password LIMIT 1")
    suspend fun getSala(nombre: String, password: String): Sala?

    // Obtener todas las salas (stream con Flow)
    @Query("SELECT * FROM salas")
    fun getAllSalas(): Flow<List<Sala>>

    // Salas creadas por el usuario (usa el campo creadorEmail en Sala.kt)
    @Query("SELECT * FROM salas WHERE creadorEmail = :email")
    fun getSalasCreadasPorUsuario(email: String): List<Sala>

    // Salas a las que el usuario se ha unido (JOIN con salas_usuarios)
    @Query("""
        SELECT s.* FROM salas s
        INNER JOIN salas_usuarios su ON s.id = su.salaId
        WHERE su.usuarioEmail = :email
    """)
    fun getSalasUnidasPorUsuario(email: String): List<Sala>
}
