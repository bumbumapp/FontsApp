package com.bumbumapps.stylishtext.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumbumapps.stylishtext.utils.CopyHandler;
import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.model.ListModel;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.MyViewHolder> {
    List<ListModel> list;
    Activity context;

    public TextAdapter(List<ListModel> arrayList, Activity context) {
        this.list = arrayList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TextAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View row = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
            return new MyViewHolder(row);

        }

        @Override
        public void onBindViewHolder(@NonNull final TextAdapter.MyViewHolder holder, final int position) {
            final ListModel listmodel = list.get(position);
            holder.arts.setText(listmodel.get_name());
            // Count List Number
            holder.num.setText(String.valueOf(position + 1));
            final CopyHandler copyHandler = new CopyHandler(context);
            final String text = holder.arts.getText().toString();
            // Copy Button
            holder.copy.setOnClickListener(v -> copyHandler.copyText(text));
            // Share Button
            holder.share.setOnClickListener(v -> copyHandler.shareText(text));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


        static class MyViewHolder extends RecyclerView.ViewHolder {
            TextView arts,num;
            ImageButton copy,share;
            private MyViewHolder(View itemView) {
                super(itemView);
                num = itemView.findViewById(R.id.numk);
                copy = itemView.findViewById(R.id.btn_copy6);
                share = itemView.findViewById(R.id.btn_share6);
                arts = itemView.findViewById(R.id.text_designs);
            }
        }
    }


