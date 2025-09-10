package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.ItemCompra;

import java.util.List;

public class ItemCompraAdapter extends RecyclerView.Adapter<ItemCompraAdapter.ItemCompraViewHolder> {

    private List<ItemCompra> itemCompraList;
    private OnItemCheckedChangeListener listener;

    public interface OnItemCheckedChangeListener {
        void onItemCheckedChanged(ItemCompra item);
    }

    public ItemCompraAdapter(List<ItemCompra> itemCompraList, OnItemCheckedChangeListener listener) {
        this.itemCompraList = itemCompraList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemCompraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_compra, parent, false);
        return new ItemCompraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCompraViewHolder holder, int position) {
        ItemCompra item = itemCompraList.get(position);
        holder.tvNombreItem.setText(item.getNombre());
        holder.cbComprado.setChecked(item.getComprado());

        holder.cbComprado.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setComprado(isChecked); // Actualizar el estado del objeto
            if (listener != null) {
                listener.onItemCheckedChanged(item); // Notificar al listener (Activity)
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCompraList.size();
    }

    static class ItemCompraViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreItem;
        CheckBox cbComprado;

        public ItemCompraViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreItem = itemView.findViewById(R.id.tvNombreItem);
            cbComprado = itemView.findViewById(R.id.cbComprado);
        }
    }
}