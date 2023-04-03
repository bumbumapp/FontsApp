package com.bumbumapps.stylishtext.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.utils.CopyHandler;

import es.dmoral.toasty.Toasty;


public class RepeatFragment extends Fragment {

    public RepeatFragment() {
    }

    EditText input, number;
    String text = "";
    Activity activity;
    TextView outputt;
    SwitchCompat switchCompat;
    ImageView close;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repeat, container, false);
        close = view.findViewById(R.id.close22);
        input =  view.findViewById(R.id.editText);
        number =  view.findViewById(R.id.number);
        switchCompat = view.findViewById(R.id.nLine);

        outputt =  view.findViewById(R.id.preview_text);
        ImageButton copy = view.findViewById(R.id.copy);
        ImageButton share = view.findViewById(R.id.share);
        Button button = view.findViewById(R.id.button);
        final CopyHandler copyHandler = new CopyHandler(activity);
        button.setOnClickListener(v -> {
            textRepeat();
            outputt.setText(text);
                });
        //Copy Button
        copy.setOnClickListener(v -> {
            String value = outputt.getText().toString();
         copyHandler.copyTextAnother(value);
        });
        //Share Button
        share.setOnClickListener(v -> {
            String value = outputt.getText().toString();
            copyHandler.shareText(value);
        });

        switchCompat.setOnCheckedChangeListener((compoundButton, b) -> textRepeat());
        //Text Delete From EditText
        close.setOnClickListener(v -> {
            int length = input.getText().length();
            if (length > 0) {
                input.getText().delete(length - 1, length);
            }
        });
        //All Text Delete From EditText
        close.setOnLongClickListener(v -> {
            input.getText().clear();
            return false;
        });


        return view;
    }
      // Text Repeater System
    private void textRepeat() {
        String inputtext = input.getText().toString();
        String tempNumber = number.getText().toString();
        if (input.getText().toString().trim().isEmpty() || inputtext.isEmpty()) {
            Toasty.info(activity, R.string.toast, Toasty.LENGTH_LONG).show();
        } else {

            if (!tempNumber.equals("")) {
                int num = Integer.parseInt(number.getText().toString());
                text = "";
                for (int i = 0; i < num; i++) {
                    if (switchCompat.isChecked()) {
                        text = text + inputtext + "\n";
                    } else {
                        text = text + inputtext;
                    }
                }
            }
            outputt.setText(text);
        }
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }
}
