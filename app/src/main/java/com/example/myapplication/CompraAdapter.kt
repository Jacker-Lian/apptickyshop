package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Sala // Usamos Sala como ejemplo de "compra"

class CompraAdapter : ListAdapter<Sala, CompraAdapter.CompraViewHolder>(CompraDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_historial_compra, parent, false)
        return CompraViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompraViewHolder, position: Int) {
        val sala = getItem(position)
        holder.bind(sala)
    }

    class CompraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNombreCompra: TextView = itemView.findViewById(R.id.tvNombreCompra)
        private val tvDetalleCompra: TextView = itemView.findViewById(R.id.tvDetalleCompra) // Puedes usarlo para la contraseña o fecha

        fun bind(sala: Sala) {
            tvNombreCompra.text = "Sala: ${sala.nombre}"
            tvDetalleCompra.text = "Contraseña: ${sala.password}" // O puedes poner la fecha de creación si la añades a Sala
        }
    }

    class CompraDiffCallback : DiffUtil.ItemCallback<Sala>() {
        override fun areItemsTheSame(oldItem: Sala, newItem: Sala): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Sala, newItem: Sala): Boolean {
            return oldItem == newItem
        }
    }
}