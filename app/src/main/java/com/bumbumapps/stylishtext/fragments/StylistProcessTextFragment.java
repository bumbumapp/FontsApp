package com.bumbumapps.stylishtext.fragments;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumbumapps.stylishtext.utils.FontsGenerator;
import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.adapters.StylishProcessTextAdapter;
import com.bumbumapps.stylishtext.interfaces.OnTextSelectedListener;

import java.util.ArrayList;

public class StylistProcessTextFragment extends Fragment {
    private static final String KEY_INPUT = "KEY_INPUT";
    private StylishProcessTextAdapter mAdapter;

    public static StylistProcessTextFragment newInstance(String input) {
        StylistProcessTextFragment fragment = new StylistProcessTextFragment();
        Bundle args = new Bundle();
        args.putString(KEY_INPUT, input);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stylish_process_text, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String input = null;
        if (getArguments() != null) {
            input = getArguments().getString(KEY_INPUT);
        }

        RecyclerView mListResult = view.findViewById(R.id.recycleggr_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mListResult.setLayoutManager(layoutManager);

        mAdapter = new StylishProcessTextAdapter(getContext());
        if (getActivity() instanceof OnTextSelectedListener) {
            mAdapter.setListener((OnTextSelectedListener) getActivity());
        }
        mListResult.setAdapter(mAdapter);

        assert input != null;
        convert(input);
    }
    //Stylish Text Converter
    public void convert(String inp) {
        if (inp.isEmpty()) inp = "Type something";
        ArrayList<String> translate = new FontsGenerator(getContext()).generate(inp);
        mAdapter.addAll(translate);
    }


}
