package com.bumbumapps.stylishtext.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumbumapps.stylishtext.utils.Arts;
import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.adapters.TextAdapter;
import com.bumbumapps.stylishtext.model.ListModel;

import java.util.ArrayList;

public class TextDesignFragment extends Fragment {
    private Activity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_text_design, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.textdesignre);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        TextAdapter textAdapter = new TextAdapter(getlist(),context);
        recyclerView.setAdapter(textAdapter);
        return view;
    }
    //Text Arts List
    private ArrayList<ListModel> getlist() {
        ArrayList<ListModel> arrayList = new ArrayList<>();
        ArrayList<String> arrayList2 = new Arts().get();
        for (int i = 0; i < arrayList2.size(); i++) {
            arrayList.add(new ListModel(arrayList2.get(i)));
        }
        return arrayList;}
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
    }
}
