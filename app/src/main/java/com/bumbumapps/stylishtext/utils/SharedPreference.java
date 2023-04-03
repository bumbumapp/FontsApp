package com.bumbumapps.stylishtext.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.bumbumapps.stylishtext.model.EmotiModel;
import com.google.gson.Gson;

public class SharedPreference {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    public SharedPreference() {
        super();
    }

    public void saveFavorites(Context context, List<EmotiModel> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.apply();
    }

    public void addFavorite(Context context, EmotiModel emotiModel) {
        List<EmotiModel> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<>();
        favorites.add(emotiModel);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, EmotiModel emotiModel) {
        ArrayList<EmotiModel> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(emotiModel);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<EmotiModel> getFavorites(Context context) {
        SharedPreferences settings;
        List<EmotiModel> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            EmotiModel[] favoriteItems = gson.fromJson(jsonFavorites,
                    EmotiModel[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<>(favorites);
        } else
            return null;

        return (ArrayList<EmotiModel>) favorites;
    }
}