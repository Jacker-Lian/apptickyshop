package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemCompraDao {

    @Insert
    fun insert(itemCompra: ItemCompra)

    @Update
    fun update(itemCompra: ItemCompra)

    @Query("SELECT * FROM item_compra")
    fun getAllItemsCompra(): LiveData<List<ItemCompra>>
}
