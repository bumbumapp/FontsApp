package com.bumbumapps.stylishtext.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.floating.stylish.FloatingStylishOpenShortCutActivity;

public class BubbleFragment extends Fragment {
    Context context;

    public BubbleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bubble, container, false);
        // Floating Stylish Text Dialog Popup
        AppCompatButton appCompatButton = view.findViewById(R.id.floatingbutton);
        appCompatButton.setOnClickListener(v -> startActivity(new Intent(context, FloatingStylishOpenShortCutActivity.class)));
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}