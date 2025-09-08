package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "salas_usuarios")
data class SalaUsuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val salaId: Int,
    val usuarioEmail: String
)