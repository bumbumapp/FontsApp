package com.bumbumapps.stylishtext.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumbumapps.stylishtext.utils.BottomSheet;
import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.utils.FontsGenerator;
import com.bumbumapps.stylishtext.adapters.FontAdapter;
import java.util.ArrayList;

public class FontFragment extends Fragment implements TextWatcher {
    Context context;
    RecyclerView fontsRV;
    EditText editText;
    private static final String KEY = "FontFragment";
    FontAdapter mAdapter;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    ImageView close,symbolButton;
    private FontsGenerator mGenerator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_font, container, false);
        mGenerator = new FontsGenerator(context);

        close = view.findViewById(R.id.closebtn);
        symbolButton = view.findViewById(R.id.symbols);
        //Preview Text Dialog
        symbolButton.setOnClickListener(v -> BottomSheet.showDialogbox(context,editText));
        fontsRV = view.findViewById(R.id.recycle_view_FF);
        fontsRV.setLayoutManager(new LinearLayoutManager(context));
        editText = view.findViewById(R.id.edit_text_FF);
        fontsRV.setHasFixedSize(true);
        mAdapter = new FontAdapter(context);
        fontsRV.setAdapter(mAdapter);
        editText.addTextChangedListener(this);
        restoreText();
        //Text Delete from EditText
        close.setOnClickListener(v -> {
            int length = editText.getText().length();
            if (length > 0) {
                editText.getText().delete(length - 1, length);
            }
        });
        //All Text Delete from EditText
        close.setOnLongClickListener(v -> {
            editText.getText().clear();
            return false;
        });


        return view;
    }
    //EditText Simple Text Convert in Stylish Text
    private void convertText(String inp) {
        if (inp.isEmpty()) inp = "Fancy Text";
        ArrayList<String> translate = mGenerator.generate(inp);
        mAdapter.setData(translate);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        convertText(editText.getText().toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
       //Save Type Text
    private void restoreText() {
        sharedPreferences =context.getSharedPreferences("MyPre", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.apply();
        editText.setText(sharedPreferences.getString(KEY + 1, ""));
    }
    @Override
    public void onDestroyView() {
        sharedPreferences =context.getSharedPreferences("MyPre", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.apply();
        editor.putString(KEY + 1, editText.getText().toString()).apply();
        super.onDestroyView();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

}
