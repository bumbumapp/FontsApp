package com.bumbumapps.stylishtext.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.utils.BottomSheet;
import com.bumbumapps.stylishtext.utils.CopyHandler;
import com.bumbumapps.stylishtext.utils.NumStyle;

import org.jetbrains.annotations.NotNull;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.MyViewHolder> {
    Context context;
    String name;
    String[][] number_style_arr;
    String[] numberArr;
    int type;
    int value;

    public NumberAdapter(Context context, String[] strArr, String[][] strArr2, String str, int i) {
        this.context = context;
        this.numberArr = strArr;
        this.number_style_arr = strArr2;
        this.name = str;
        this.type = i;
    }

    public void setName(String str, int i) {
        this.name = str;
        this.type = i;
        notifyDataSetChanged();
    }

    @NotNull
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.num_item, viewGroup, false));
    }

    public void onBindViewHolder(@NotNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.number.setText(String.valueOf(i + 1));
        value = i;
        final CopyHandler copyHandler = new CopyHandler(context);
        //Check which number is enter
        if (name.equalsIgnoreCase(" ")) {
            myViewHolder.numberStylish.setText(StyleMaker("0123456789".toLowerCase().toCharArray(), NumStyle.numberStyle[i]));
            StringBuilder sb = new StringBuilder();
            sb.append(i + 1);
            sb.append("");
        } else if (!name.isEmpty()) {
            myViewHolder.numberStylish.setText(StyleMaker(this.name.toLowerCase().toCharArray(), NumStyle.numberStyle[i]));
            StringBuilder sb2 = new StringBuilder();
            sb2.append(i + 1);
            sb2.append("");
        }
        final String data =StyleMaker(name.toLowerCase().toCharArray(), NumStyle.numberStyle[i]);
        // Share Button
        myViewHolder.share_number.setOnClickListener(view -> copyHandler.shareText(data));
        // Copy Button
        myViewHolder.copy_number.setOnClickListener(view -> copyHandler.copyText(data));
        // Preview text Bottomsheet
        myViewHolder.layout.setOnClickListener(v -> {
            BottomSheet bottomSheet = new BottomSheet();
            bottomSheet.styleBottom(context,data);
        });
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView copy_number;
        TextView numberStylish,number;
        ImageView share_number;
        LinearLayout layout;

        public MyViewHolder(NumberAdapter numberAdapter, View view) {
            super(view);
            numberStylish = view.findViewById(R.id.textView);
            number = view.findViewById(R.id.txt_number);
            share_number = view.findViewById(R.id.share);
            copy_number = view.findViewById(R.id.Copy_stylish);
            layout = itemView.findViewById(R.id.linearclick);

        }
    }

    public int getItemCount() {
        return this.numberArr.length;
    }

    public long getItemId(int i) {
        return Long.parseLong(this.numberArr[i]);
    }
    // Stylish Number Maker
    private String StyleMaker(char[] cArr, String[] strArr) {
        StringBuilder stringBuffer = new StringBuilder();
        for (char c : cArr) {
            if (c - '0' >= 0 && c - '9' <= 10) {
                stringBuffer.append(strArr[c - '0']);
            }
        }
        return stringBuffer.toString();
    }
}
