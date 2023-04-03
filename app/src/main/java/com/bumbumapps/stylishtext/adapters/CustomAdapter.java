package com.bumbumapps.stylishtext.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumbumapps.stylishtext.R;

public class CustomAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    String[] logo;
    int[] icon;

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public CustomAdapter(Context context, String[] strArr,int[] icon) {
        this.context = context;
        this.logo = strArr;
        this.inflater = LayoutInflater.from(context);
        this.icon = icon;
    }

    public int getCount() {
        return logo.length;
    }

    @SuppressLint({"ViewHolder", "UseCompatLoadingForDrawables"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("InflateParams") View inflate = inflater.inflate(R.layout.gridview,  null);
        ImageView imageView = inflate.findViewById(R.id.image);
        imageView.setImageDrawable(context.getDrawable(icon[i]));
        ((TextView) inflate.findViewById(R.id.name)).setText(logo[i]);
        return inflate;
    }
}
