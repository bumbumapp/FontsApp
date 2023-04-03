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
import android.widget.EditText;
import android.widget.ImageView;

import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.adapters.NumberAdapter;
import com.bumbumapps.stylishtext.utils.NumStyle;
import com.bumbumapps.stylishtext.utils.NumberEditText;


public class NumberStyleFragment extends Fragment {

    public static String name_number = "0123456789";
    Context context;
    RecyclerView recycler_view;
    EditText inputText;
    ImageView close;
    public static NumberAdapter numberAdapter = null;
    public static int type = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number_style, container, false);
        inputText = view.findViewById(R.id.inputtext_stylish);
        recycler_view = view.findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(context));
        numberAdapter = new NumberAdapter(context, NumStyle.Number, NumStyle.numberStyle, name_number, type);
        recycler_view.setAdapter(numberAdapter);
        inputText.addTextChangedListener(new NumberEditText());
        close = view.findViewById(R.id.closebtn);
        //Text Delete from EditText
        close.setOnClickListener(v -> {
            int length = inputText.getText().length();
            if (length > 0) {
                inputText.getText().delete(length - 1, length);
            }
        });
        //All Text Delete from EditText
        close.setOnLongClickListener(v -> {
            inputText.getText().clear();
            return false;
        });
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}