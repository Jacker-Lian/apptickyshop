package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_compra")
data class ItemCompra(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    var comprado: Boolean
)