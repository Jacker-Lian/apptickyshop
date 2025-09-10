package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "salas")
data class Sala(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val password: String,
    val creadorEmail: String // 👈 necesario para saber quién creó la sala
)
