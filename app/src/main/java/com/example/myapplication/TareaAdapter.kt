package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Tarea

class TareaAdapter(private val onItemCheckedChangeListener: (Tarea) -> Unit) :
    ListAdapter<Tarea, TareaAdapter.TareaViewHolder>(TareaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = getItem(position)
        holder.bind(tarea, onItemCheckedChangeListener)
    }

    class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDescripcionTarea: TextView = itemView.findViewById(R.id.tvDescripcionTarea)
        private val cbCompletada: CheckBox = itemView.findViewById(R.id.cbCompletada)

        fun bind(tarea: Tarea, onItemCheckedChangeListener: (Tarea) -> Unit) {
            tvDescripcionTarea.text = tarea.descripcion
            cbCompletada.isChecked = tarea.completada

            // Importante: Remover el listener antes de establecer el estado para evitar bucles
            // y luego añadirlo de nuevo.
            cbCompletada.setOnCheckedChangeListener(null)
            cbCompletada.isChecked = tarea.completada
            cbCompletada.setOnCheckedChangeListener { _, isChecked ->
                tarea.completada = isChecked
                onItemCheckedChangeListener(tarea)
            }
        }
    }

    class TareaDiffCallback : DiffUtil.ItemCallback<Tarea>() {
        override fun areItemsTheSame(oldItem: Tarea, newItem: Tarea): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Tarea, newItem: Tarea): Boolean {
            return oldItem == newItem
        }
    }
}