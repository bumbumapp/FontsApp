package com.bumbumapps.stylishtext.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.interfaces.RecyclerViewItem;
import java.util.ArrayList;

public class SheetAdapter extends RecyclerView.Adapter<SheetAdapter.MyViewHolder> {
    RecyclerViewItem recyclerViewItem;
    Context context;
    ArrayList<String> symbols;
    public SheetAdapter(Context context, ArrayList<String> symbols ,RecyclerViewItem recyclerViewItem) {
        this.context = context;
        this.symbols = symbols;
        this.recyclerViewItem = recyclerViewItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(R.layout.item_sheet, parent, false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.symbol.setText(symbols.get(position));
        holder.itemView.setOnClickListener(v -> recyclerViewItem.onItemClick(holder.getAdapterPosition(),symbols.get(position)));

    }

    @Override
    public int getItemCount() {
        return symbols.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView symbol;
        private MyViewHolder(View itemView) {
            super(itemView);
            symbol = itemView.findViewById(R.id.symboltxt);

        }
    }
}
