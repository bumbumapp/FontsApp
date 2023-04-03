package com.bumbumapps.stylishtext.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.bumbumapps.stylishtext.interfaces.Style;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class FontsGenerator {

    private static final String PREF_NAME = "stylish_position.xml";
    ArrayList<Style> mEncoders;
    @Nullable
    Context mContext;

    public FontsGenerator(@Nullable Context context) {
        mContext = context;
        long time = System.currentTimeMillis();
        mEncoders = FontsBuilder.makeStyle();
        if (context != null) sortEncoders(context);
        System.out.println("time = " + (System.currentTimeMillis() - time));
    }


    private void sortEncoders(Context context) {
        final HashMap<Style, Integer> positionMap = new HashMap<>();
        for (int i = 0; i < mEncoders.size(); i++) {
            positionMap.put(mEncoders.get(i), i);
        }
        //get data from SharedPreferences
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        if (pref.getInt(Integer.toHexString(mEncoders.get(0).hashCode()), -1) == -1) {
            SharedPreferences.Editor editor = pref.edit();
            for (int index = 0; index < mEncoders.size(); index++) {
                Style style = mEncoders.get(index);
                editor.putInt(Integer.toHexString(style.hashCode()), index);
            }
            editor.apply();
        }

        for (int index = 0; index < mEncoders.size(); index++) {
            Style style = mEncoders.get(index);
            String key = Integer.toHexString(style.hashCode());
            int position = pref.getInt(key, index);
            positionMap.put(style, position);
        }
        //sort mEncoders
        Collections.sort(mEncoders, (o1, o2) -> Objects.requireNonNull(positionMap.get(o1)).compareTo(Objects.requireNonNull(positionMap.get(o2))));
    }
       //Stylish Text Generator
    public ArrayList<String> generate(String input) {
        ArrayList<String> result = new ArrayList<>();
        for (Style style : mEncoders) {
            String encode = style.generate(input);
            result.add(encode);
        }
        return result;
    }
}
