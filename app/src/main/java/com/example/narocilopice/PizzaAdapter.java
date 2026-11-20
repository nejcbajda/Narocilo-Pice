package com.example.narocilopice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private final List<String> items;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    public PizzaAdapter(List<String> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        String item = items.get(position);
        holder.txtItem.setText(item);

        // klik: prikaži izbrano pico
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));

        // dolg pritisk: odstrani pico iz košarice
        holder.itemView.setOnLongClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos == RecyclerView.NO_POSITION) {
                return false;
            }
            String removed = items.remove(pos);
            notifyItemRemoved(pos);
            Toast.makeText(v.getContext(),
                    v.getContext().getString(R.string.removed_toast, removed),
                    Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void dodaj(String item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    static class PizzaViewHolder extends RecyclerView.ViewHolder {
        final TextView txtItem;

        PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItem);
        }
    }
}
