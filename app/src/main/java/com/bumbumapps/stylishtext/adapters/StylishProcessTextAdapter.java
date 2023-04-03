package com.bumbumapps.stylishtext.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.interfaces.OnTextSelectedListener;
import com.bumbumapps.stylishtext.utils.CopyHandler;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StylishProcessTextAdapter extends RecyclerView.Adapter<StylishProcessTextAdapter.ViewHolder> {
    ArrayList<String> mResult = new ArrayList<>();
    OnTextSelectedListener listener;
    Context context;

    public StylishProcessTextAdapter(Context context) {
        this.context = context;
    }

      //Add Stylish Text in List
    public void add(String item) {
        mResult.add(item);
        notifyItemInserted(getItemCount() - 1);
    }
    //Add All TYPE Stylish Text in List
    public void addAll(List<String> items) {
        mResult.addAll(items);
        notifyDataSetChanged();
    }


    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_stylish_process_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String item = mResult.get(position);
        holder.txtResult.setText(item);
        // Count List Number
        holder.num.setText(String.valueOf(position + 1));
        // Copy Layout
        holder.rootView.setOnClickListener(v -> {
            CopyHandler copyHandler = new CopyHandler(context);
            copyHandler.copyText(item);
            if (listener != null) listener.onTextSelected(item);

        });
    }

    @Override
    public int getItemCount() {
        return mResult.size();
    }

    public void setListener(OnTextSelectedListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtResult,num;
        View rootView;
        ViewHolder(View itemView) {
            super(itemView);
            txtResult = itemView.findViewById(R.id.txt_result);
            rootView = itemView.findViewById(R.id.linearclick);
            num =  itemView.findViewById(R.id.txt_numberj);
        }

    }
}
