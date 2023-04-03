package com.bumbumapps.stylishtext.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ImageView;

import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.adapters.StylerAdapter;
import com.bumbumapps.stylishtext.interfaces.RecyclerViewItem;
import com.bumbumapps.stylishtext.utils.CopyHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


import java.util.Objects;

public class EmojiSheetFragment extends Fragment implements RecyclerViewItem   {
  private Context context;
  private TabLayout layout;
  private RecyclerView recyclerView;
  private StylerAdapter sheetAdapter;
  private EditText editText;
  private String[] strings;
  ImageView close;
  FloatingActionButton button;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emoji_sheet, container, false);
        recyclerView = view.findViewById(R.id.rv_styles);
        editText = view.findViewById(R.id.edittx);
        layout = view.findViewById(R.id.tabScrollable);
        close = view.findViewById(R.id.close22);
        button = view.findViewById(R.id.copy);
        strings =getResources().getStringArray(R.array.emoji_activity);
        recyclerView.setLayoutManager(new GridLayoutManager(context,5));
        sheetAdapter = new StylerAdapter(context,1,strings,this);
        recyclerView.setAdapter(sheetAdapter);
        //Tab Change to Emoji Change
        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (Objects.requireNonNull(layout.getTabAt(0)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_activity);
                    sheetAdapter = new StylerAdapter(context, 1, strings, (position, sym) -> editText.getEditableText().append(sym));
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(1)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_animals);
                    sheetAdapter = new StylerAdapter(context, 1, strings, (position, sym) -> editText.getEditableText().append(sym));
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(2)).isSelected()){
                    strings =getResources().getStringArray(R.array.characters);
                    sheetAdapter = new StylerAdapter(context, 1, strings, (position, sym) -> editText.getEditableText().append(sym));
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(3)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_food);
                    sheetAdapter = new StylerAdapter(context, 1, strings, (position, sym) -> editText.getEditableText().append(sym));
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(4)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_object);
                    sheetAdapter = new StylerAdapter(context, 1, strings, (position, sym) -> editText.getEditableText().append(sym));
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(5)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_people);
                    sheetAdapter = new StylerAdapter(context, 1, strings, (position, sym) -> editText.getEditableText().append(sym));
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(6)).isSelected()){
                    strings =getResources().getStringArray(R.array.symbols);
                    sheetAdapter = new StylerAdapter(context, 1, strings, (position, sym) -> editText.getEditableText().append(sym));
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(7)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_travel);
                    sheetAdapter = new StylerAdapter(context, 1, strings, (position, sym) -> editText.getEditableText().append(sym));
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(8)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_flag);
                    sheetAdapter = new StylerAdapter(context, 1, strings, (position, sym) -> editText.getEditableText().append(sym));
                    recyclerView.setAdapter(sheetAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //Fab Copy Button
       button.setOnClickListener(v -> {
           String data = editText.getText().toString();
           CopyHandler copyHandler = new CopyHandler(context);
           copyHandler.copyText(data);
       });

        //Text Delete from edittext
        close.setOnClickListener(v -> {
            int length = editText.getText().length();
            if (length > 0) {
                editText.getText().delete(length - 1, length);
            }
        });
        //All Text Delete from edittext
        close.setOnLongClickListener(v -> {
            editText.getText().clear();
            return false;
        });


        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public void onItemClick(int position, String sym) {
        editText.getEditableText().append(sym);
    }
}
