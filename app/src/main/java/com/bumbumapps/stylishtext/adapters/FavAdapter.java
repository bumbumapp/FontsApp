package com.bumbumapps.stylishtext.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumbumapps.stylishtext.R;

import com.bumbumapps.stylishtext.model.EmotiModel;
import com.bumbumapps.stylishtext.utils.CopyHandler;
import com.bumbumapps.stylishtext.utils.SharedPreference;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    Context mContext;
    List<EmotiModel> favItemList;
    SharedPreference sharedPreference;

    public FavAdapter(Context context, List<EmotiModel> favItemList) {
        this.mContext = context;
        this.favItemList = favItemList;
        sharedPreference = new SharedPreference();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final CopyHandler copyHandler = new CopyHandler(mContext);
        final EmotiModel emotiModel = favItemList.get(position);
        holder.fav_text_view.setText(emotiModel.getName());
        holder.fav_text_view.setSelected(true);
        // Count List Number
        holder.number.setText(String.valueOf(position + 1));

        if (checkFavoriteItem(emotiModel)) {
            holder.fav_btn.setImageResource(R.drawable.favourite_icon_fill);
            holder.fav_btn.setTag("red");
        } else {
            holder.fav_btn.setImageResource(R.drawable.favourite_icon);
            holder.fav_btn.setTag("grey");
        }
        // favourite button
        holder.fav_btn.setOnClickListener(v -> {
            String tag = holder.fav_btn.getTag().toString();
            if (tag.equalsIgnoreCase("grey")) {
                sharedPreference.addFavorite(mContext, favItemList.get(position));
                holder.fav_btn.setTag("red");
                holder.fav_btn.setImageResource(R.drawable.favourite_icon_fill);
            } else {
                sharedPreference.removeFavorite(mContext, favItemList.get(position));
                holder.fav_btn.setTag("grey");
                holder.fav_btn.setImageResource(R.drawable.favourite_icon);

                remove(favItemList.get(position));
            } });
        // Copy Button
        holder.copy.setOnClickListener(v -> copyHandler.copyText(emotiModel.getName()));
        // Share Button
        holder.share.setOnClickListener(v -> copyHandler.shareText(emotiModel.getName()));
    }

    @Override
    public int getItemCount() {
        return favItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView fav_text_view,number;
        ImageButton fav_btn,copy,share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fav_text_view = itemView.findViewById(R.id.emoti);
            fav_btn = itemView.findViewById(R.id.favBtn);
            number = itemView.findViewById(R.id.numtxt);
            copy = itemView.findViewById(R.id.copy);
            share = itemView.findViewById(R.id.share);

        }
    }
    // Favourite list item check
    public boolean checkFavoriteItem(EmotiModel checkEmoti) {
        boolean check = false;
        List<EmotiModel> favorites = sharedPreference.getFavorites(mContext);
        if (favorites != null) {
            for (EmotiModel emotiModel : favorites) {
                if (emotiModel.equals(checkEmoti)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
    // Item Remove in Favourite list
    public void remove(EmotiModel emotiModel) {
        favItemList.remove(emotiModel);
        notifyDataSetChanged();
    }
}
