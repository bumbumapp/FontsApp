package com.bumbumapps.stylishtext.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.utils.CopyHandler;
import com.bumbumapps.stylishtext.utils.Zalgo;


public class GiltchFragment extends Fragment {

    public GiltchFragment() {
    }
    private EditText testTextField;
    private TextView previewText;
    private SeekBar intensitySlider;
    private Activity activity;
    private CheckBox upCheck;
    private CheckBox midCheck;
    private CheckBox downCheck;
    ImageView close;
    ImageButton copy,share;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_glitch, container, false);
        testTextField = view.findViewById(R.id.editText);
        close = view.findViewById(R.id.close33);
        previewText = view.findViewById(R.id.preview_text);

        intensitySlider = view.findViewById(R.id.seekBar);
        copy = view.findViewById(R.id.copy);
        share = view.findViewById(R.id.share);

        upCheck = view.findViewById(R.id.upwards);
        midCheck = view.findViewById(R.id.middle);
        downCheck = view.findViewById(R.id.downwards);
        upCheck.setChecked(true);
        downCheck.setChecked(true);
        final CopyHandler copyHandler = new CopyHandler(activity);
        //Set listeners
        testTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                updatePreview();
            }
        });


        intensitySlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updatePreview();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // Copy Button
        copy.setOnClickListener(v -> copyHandler.copyTextAnother(makeZalgo()));
        // Share Button
        share.setOnClickListener(v -> copyHandler.shareText(makeZalgo()));
        upCheck.setOnCheckedChangeListener((compoundButton, b) -> updatePreview());
        midCheck.setOnCheckedChangeListener((compoundButton, b) -> updatePreview());
        downCheck.setOnCheckedChangeListener((compoundButton, b) -> updatePreview());
        //Text Delete from EditText
        close.setOnClickListener(v -> {
            int length = testTextField.getText().length();
            if (length > 0) {
                testTextField.getText().delete(length - 1, length);
            }
        });
        //All Text Delete from EditText
        close.setOnLongClickListener(v -> {
            testTextField.getText().clear();
            return false;
        });

        return view;


    }

    private void updatePreview() {
        previewText.setText(getString(R.string.preview_text, makeZalgo()));
    }

    // Zalgo Text Maker
    private String makeZalgo() {
        String text = testTextField.getText().toString();

            int intUp = 0;
            int intMid = 0;
            int intDown = 0;

            int intensity = intensitySlider.getProgress() + 2;

            if (upCheck.isChecked()) {
                intUp = intensity;
            }
            if (midCheck.isChecked()) {
                intMid = intensity;
            }
            if (downCheck.isChecked()) {
                intDown = intensity;
            }

            return Zalgo.generate(text, intUp, intMid, intDown);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }
}
