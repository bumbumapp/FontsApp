package com.bumbumapps.stylishtext.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.interfaces.RecyclerViewItem;

public class StylerAdapter extends RecyclerView.Adapter<StylerAdapter.StylersViewHolder> {
    Context context;
    String[] items;
    int type;
    RecyclerViewItem recyclerViewItem;

    public StylerAdapter(Context context, int i,  String[] list,RecyclerViewItem recyclerViewItem) {
        this.context = context;
        this.type = i;
        this.items = list;
        this.recyclerViewItem = recyclerViewItem;
    }

    @NonNull
    public StylersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new StylersViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sheet, viewGroup, false));
    }

    public void onBindViewHolder(final StylersViewHolder stylersViewHolder, final int i) {
        stylersViewHolder.txt_content.setText(items[i]);
        stylersViewHolder.itemView.setOnClickListener(v -> recyclerViewItem.onItemClick(stylersViewHolder.getAdapterPosition(),items[i]));
    }


    public int getItemCount() {
        return items.length;
    }
    public static class StylersViewHolder extends ViewHolder {
        View itemView;
        public TextView txt_content;

        public StylersViewHolder(View view) {
            super(view);
            this.txt_content = view.findViewById(R.id.symboltxt);
            this.itemView = view;
        }
    }

}
