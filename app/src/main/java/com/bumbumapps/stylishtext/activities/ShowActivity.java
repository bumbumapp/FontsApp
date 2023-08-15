package com.bumbumapps.stylishtext.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.fragments.DecorationFragment;
import com.bumbumapps.stylishtext.fragments.EmojiFragment;
import com.bumbumapps.stylishtext.fragments.EmojiSheetFragment;
import com.bumbumapps.stylishtext.fragments.FontFragment;
import com.bumbumapps.stylishtext.fragments.GiltchFragment;
import com.bumbumapps.stylishtext.fragments.NumberStyleFragment;
import com.bumbumapps.stylishtext.fragments.ProNameFragment;
import com.bumbumapps.stylishtext.fragments.RepeatFragment;
import com.bumbumapps.stylishtext.fragments.TextDesignFragment;
import com.bumbumapps.stylishtext.fragments.TextPlayFragment;
import com.bumbumapps.stylishtext.utils.AdsUtils;
import com.google.android.material.appbar.MaterialToolbar;
import com.huawei.hms.ads.banner.BannerView;
//import com.google.firebase.analytics.FirebaseAnalytics;

public class ShowActivity extends AppCompatActivity {
    String KEY = "Home";
    MaterialToolbar materialToolbar;
//    FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        materialToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(materialToolbar);
        if (getSupportActionBar() != null){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //Admob Ad Initialize
        loadBannerAds();


        int itemid = getIntent().getIntExtra(KEY,1);

        if (itemid==1){
            getSupportActionBar().setTitle("Stylish Text");
            loadFragment(new FontFragment());
        }
        if (itemid==2){
            getSupportActionBar().setTitle("Decoration Text");
            loadFragment(new DecorationFragment());
        }
        if (itemid==3){
            getSupportActionBar().setTitle("Emoticons");
        }
        if (itemid==4){
            getSupportActionBar().setTitle("Emoji Sheet");
            loadFragment(new EmojiSheetFragment());
        }
        if (itemid==5){
            getSupportActionBar().setTitle("Glitch Text");
            loadFragment(new GiltchFragment());
        }
        if (itemid==6){
            getSupportActionBar().setTitle("Text Repeater");
            loadFragment(new RepeatFragment());
        }
        if (itemid==7){
            getSupportActionBar().setTitle("Text To Emoji");
            loadFragment(new EmojiFragment());
        }
        if (itemid==8){
            getSupportActionBar().setTitle("Text Arts");
            loadFragment(new TextDesignFragment());
        }
        if (itemid==9){
            getSupportActionBar().setTitle("Text To Play");
            loadFragment(new TextPlayFragment());
        }
        if (itemid==10){
            getSupportActionBar().setTitle("Pro Nickname");
            loadFragment(new ProNameFragment());
        }
        if (itemid==11){
            getSupportActionBar().setTitle("Stylish Number");
            loadFragment(new NumberStyleFragment());
        }
    }
    //Fragment Container
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.showFragment,fragment)
                .commit();

    }
    private void loadBannerAds() {
        BannerView bannerView=findViewById(R.id.hw_banner_view);
        AdsUtils.showGoogleBannerAd(this,bannerView);
    }


}