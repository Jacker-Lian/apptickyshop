package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaDao {
    @Insert
    suspend fun insert(tarea: Tarea)

    @Update
    suspend fun update(tarea: Tarea)

    @Query("SELECT * FROM tareas ORDER BY id DESC")
    fun getAllTareas(): Flow<List<Tarea>> // Usamos Flow para observar cambios
}