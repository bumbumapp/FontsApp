package com.bumbumapps.stylishtext.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumbumapps.stylishtext.activities.EmoticonGridActivity;
import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.activities.ShowActivity;
import com.huawei.hms.ads.InterstitialAd;


public class HomeFragment extends Fragment implements View.OnClickListener {

    Activity activity;
    CardView item1,item2,item3,item4,item5,item6,item7,item8,item9,item10,item11;
    Intent intent;
    String KEY = "Home";
    InterstitialAd mInterstitialAd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        item1 = view.findViewById(R.id.item1);
        item2 = view.findViewById(R.id.item2);
        item3 = view.findViewById(R.id.item3);
        item4 = view.findViewById(R.id.item4);
        item5 = view.findViewById(R.id.item5);
        item6 = view.findViewById(R.id.item6);
        item7 = view.findViewById(R.id.item7);
        item8 = view.findViewById(R.id.item8);
        item9 = view.findViewById(R.id.item9);
        item10 = view.findViewById(R.id.item10);
        item11 = view.findViewById(R.id.item11);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);
        item5.setOnClickListener(this);
        item6.setOnClickListener(this);
        item7.setOnClickListener(this);
        item8.setOnClickListener(this);
        item9.setOnClickListener(this);
        item10.setOnClickListener(this);
        item11.setOnClickListener(this);


        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    public void onClick(View v) {
        if (v == item1){
            intent = new Intent(activity, ShowActivity.class);
            intent.putExtra(KEY,1);
            startActivity(intent);

        }
        if (v == item2){
            intent = new Intent(activity,ShowActivity.class);
            intent.putExtra(KEY,2);
            startActivity(intent);

        }
        if (v == item3){
            intent = new Intent(activity, EmoticonGridActivity.class);
            startActivity(intent);

        }
        if (v == item4){
            intent = new Intent(activity,ShowActivity.class);
            intent.putExtra(KEY,4);
            startActivity(intent);

        }
        if (v == item5){
            intent = new Intent(activity,ShowActivity.class);
            intent.putExtra(KEY,5);
            startActivity(intent);
        }
        if (v == item6){
            intent = new Intent(activity,ShowActivity.class);
            intent.putExtra(KEY,6);
            startActivity(intent);
        }
        if (v == item7){
            intent = new Intent(activity,ShowActivity.class);
            intent.putExtra(KEY,7);
            startActivity(intent);

        }
        if (v == item8){
            intent = new Intent(activity,ShowActivity.class);
            intent.putExtra(KEY,8);
            startActivity(intent);
        }
        if (v == item9){
            intent = new Intent(activity,ShowActivity.class);
            intent.putExtra(KEY,9);
            startActivity(intent);
        }
        if (v == item10){
            intent = new Intent(activity,ShowActivity.class);
            intent.putExtra(KEY,10);
            startActivity(intent);

        }
        if (v == item11){
            intent = new Intent(activity,ShowActivity.class);
            intent.putExtra(KEY,11);
            startActivity(intent);

        }
    }




}