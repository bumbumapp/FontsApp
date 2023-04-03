package com.bumbumapps.stylishtext.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bumbumapps.stylishtext.adapters.CustomAdapter;
import com.bumbumapps.stylishtext.R;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.appbar.MaterialToolbar;

public class EmoticonGridActivity extends AppCompatActivity {
    String[] logos = {"Love", "Happy", "Music", "Animals", "Angry", "Sad", "Sleeping", "Excited", "Hungry", "Shy", "Other","Kiss","Smile","Laugh"};
    int[] icon = {R.drawable.love,R.drawable.happy,R.drawable.music,R.drawable.animal,R.drawable.angry,R.drawable.sad,
            R.drawable.sleeping,R.drawable.excited,R.drawable.hungry,R.drawable.shy,R.drawable.other,R.drawable.kiss,R.drawable.smile,R.drawable.laugh};
    GridView simpleGrid;
    MaterialToolbar materialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoticon_grid);
        materialToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(materialToolbar);
        if (getSupportActionBar() != null){
           getSupportActionBar().setTitle("Emoticons");
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //Admob Ad Initialize
        MobileAds.initialize(this);
//        loadBannerAds();
        simpleGrid = findViewById(R.id.simpleGridView);
        simpleGrid.setAdapter(new CustomAdapter(this, logos,icon));
        simpleGrid.setOnItemClickListener(new simpleGridListner());

    }
    private class simpleGridListner implements AdapterView.OnItemClickListener {
        private simpleGridListner() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Intent intent = new Intent(getApplicationContext(), EmoticonsActivity.class);
            intent.putExtra("image", logos[i]);
            intent.putExtra("P", i);
            startActivity(intent);
        }
    }
    //Banner Ad
//    private void loadBannerAds() {
//        AdView mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//    }
}