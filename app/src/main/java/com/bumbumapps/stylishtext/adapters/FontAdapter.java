package com.bumbumapps.stylishtext.adapters;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumbumapps.stylishtext.utils.AdsUtils;
import com.bumbumapps.stylishtext.utils.BottomSheet;
import com.bumbumapps.stylishtext.utils.CopyHandler;
import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.utils.Globals;
import com.bumbumapps.stylishtext.utils.Timers;
import com.huawei.hms.ads.AdListener;

import java.util.ArrayList;


public class FontAdapter extends RecyclerView.Adapter<FontAdapter.MyViewHolder> {
    ArrayList<String> dataList = new ArrayList<>();
    Context activity;

    public FontAdapter(Context activity) {
        this.activity = activity;

    }
    public void setData(ArrayList<String> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
     }

    @Override
    @NonNull
    public FontAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(activity).inflate(R.layout.adapter_font, parent, false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull final FontAdapter.MyViewHolder holder, final int position) {
        holder.description.setText(dataList.get(position));
        holder.description.setSelected(true);
        // Count List Number
        holder.number.setText(String.valueOf(position + 1));
        final CopyHandler copyHandler = new CopyHandler(activity);
        final String text = holder.description.getText().toString();
        // Copy Button
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHandler.copyText(text);
            }
        });
        // Share Button
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHandler.shareText(text);
            }
        });
        // Preview text Bottomsheet
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.styleBottom(activity,text);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder   {
        TextView description,number;
        ImageView copy,share;
        LinearLayout layout;


        private MyViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.descriptionTV);
            number = itemView.findViewById(R.id.txt_number);
            copy = itemView.findViewById(R.id.btn_copy);
            share = itemView.findViewById(R.id.btn_share);
            layout = itemView.findViewById(R.id.linearclick);


        }

    }

}


