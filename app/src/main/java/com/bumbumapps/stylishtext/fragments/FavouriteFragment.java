package com.bumbumapps.stylishtext.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.adapters.FavAdapter;
import com.bumbumapps.stylishtext.model.EmotiModel;
import com.bumbumapps.stylishtext.utils.SharedPreference;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class FavouriteFragment extends Fragment   {
    RecyclerView recyclerView;
    Context context;
    TextView textView;
    SharedPreference sharedPreference;
    FavAdapter favAdapter;
    List<EmotiModel> favorites;
    MaterialToolbar materialToolbar;
    public FavouriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_emoticons, container, false);
        sharedPreference = new SharedPreference();
        textView = view.findViewById(R.id.nofavtext);
        materialToolbar = view.findViewById(R.id.toolbar);
        favorites = sharedPreference.getFavorites(context);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Check Favourite Item and Show in Favourite List
        if (favorites != null) {
            favAdapter = new FavAdapter(context,favorites);
            recyclerView.setAdapter(favAdapter); }
        if (favorites == null){
            textView.setVisibility(View.VISIBLE);
        }else if (favorites.size() == 0){
            textView.setVisibility(View.VISIBLE);

        }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }



}