package com.bumbumapps.stylishtext.adapters;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumbumapps.stylishtext.model.EmotiModel;
import com.bumbumapps.stylishtext.utils.BottomSheet;
import com.bumbumapps.stylishtext.utils.CopyHandler;
import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.utils.SharedPreference;

import java.util.List;

public class EmoticonsAdapter extends RecyclerView.Adapter<EmoticonsAdapter.MyViewHolder> {
    Activity context;
    List<EmotiModel> emotiModels;
    SharedPreference sharedPreference;

    public EmoticonsAdapter(List<EmotiModel> emotiModels, Activity context) {
        this.emotiModels = emotiModels;
        this.context = context;
        sharedPreference = new SharedPreference();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(R.layout.item_emoticons, parent, false);
        return new MyViewHolder(row);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        EmotiModel emotiModel = emotiModels.get(position);
        holder.emoticons.setText(emotiModel.getName());
        holder.emoticons.setSelected(true);
        // Count List Number
        holder.number.setText(String.valueOf(position + 1));
        final CopyHandler copyHandler = new CopyHandler(context);
        final String text = holder.emoticons.getText().toString();
        // Copy Button
        holder.copy.setOnClickListener(v -> copyHandler.copyText(text));
        // Share Button
        holder.share.setOnClickListener(v -> copyHandler.shareText(text));
        // Preview text Bottomsheet
        holder.cardView.setOnClickListener(v -> {
            BottomSheet bottomSheet = new BottomSheet();
            bottomSheet.styleBottom(context,text);
        });

        if (checkFavoriteItem(emotiModel)) {
            holder.favBtn.setImageResource(R.drawable.favourite_icon_fill);
            holder.favBtn.setTag("red");
        } else {
            holder.favBtn.setImageResource(R.drawable.favourite_icon);
            holder.favBtn.setTag("grey");
        }
        // favourite button
        holder.favBtn.setOnClickListener(v -> {
            String tag = holder.favBtn.getTag().toString();
            if (tag.equalsIgnoreCase("grey")) {
                sharedPreference.addFavorite(context, emotiModels.get(position));

                holder.favBtn.setTag("red");
                holder.favBtn.setImageResource(R.drawable.favourite_icon_fill);
            } else {
                sharedPreference.removeFavorite(context, emotiModels.get(position));
                holder.favBtn.setTag("grey");
                holder.favBtn.setImageResource(R.drawable.favourite_icon);

            }

        });

    }

    @Override
    public int getItemCount() {
        return emotiModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView emoticons,number;
        ImageButton copy,share,favBtn;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.numtxt);
            favBtn = itemView.findViewById(R.id.favBtn);
            copy = itemView.findViewById(R.id.copy2);
            share = itemView.findViewById(R.id.btn_share2);
            emoticons = itemView.findViewById(R.id.emoti);
            cardView = itemView.findViewById(R.id.root1);

        }
    }
    // Favourite list item check
    public boolean checkFavoriteItem(EmotiModel checkEmoti) {
        boolean check = false;
        List<EmotiModel> favorites = sharedPreference.getFavorites(context);
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

    // Item Add in Favourite list
    public void add(EmotiModel emotiModel) {
        emotiModels.add(emotiModel);
        notifyDataSetChanged();
    }


}
