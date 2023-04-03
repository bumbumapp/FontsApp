package com.bumbumapps.stylishtext.utils;

import android.text.Editable;
import android.text.TextWatcher;

import com.bumbumapps.stylishtext.fragments.NumberStyleFragment;

public class NumberEditText implements TextWatcher {
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.length() == 0) {
            NumberStyleFragment.name_number = " ";
            NumberStyleFragment.numberAdapter.setName(NumberStyleFragment.name_number, NumberStyleFragment.type);
            NumberStyleFragment.numberAdapter.notifyDataSetChanged();
        } else if (charSequence.length() >= 1) {
            NumberStyleFragment.name_number = charSequence.toString();
            NumberStyleFragment.numberAdapter.setName(NumberStyleFragment.name_number, NumberStyleFragment.type);
        }
    }

    public void afterTextChanged(Editable editable) {
        NumberStyleFragment.name_number = "0123456789";
        NumberStyleFragment.numberAdapter.notifyDataSetChanged();
    }
}
