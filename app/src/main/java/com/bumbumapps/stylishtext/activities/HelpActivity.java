package com.bumbumapps.stylishtext.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.bumbumapps.stylishtext.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Objects;

public class HelpActivity extends AppCompatActivity {
    MaterialToolbar materialToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        materialToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(materialToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Help");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}