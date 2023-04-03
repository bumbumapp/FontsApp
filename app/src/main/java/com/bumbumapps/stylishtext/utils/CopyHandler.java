package com.bumbumapps.stylishtext.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumbumapps.stylishtext.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import es.dmoral.toasty.Toasty;

import static android.content.Context.CLIPBOARD_SERVICE;

public class CopyHandler {
    Context context;
    InterstitialAd mInterstitialAd;

    public CopyHandler(Context context) {
        this.context = context;
        loadInterAd();

    }
    public void copyText(String data){
        if (data.isEmpty()) {
            Toasty.info(context, "Enter some text", Toast.LENGTH_LONG).show();
            return;
        }
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        Toasty.success(context, "Copied to clipboard! Your copied text is " + data, Toast.LENGTH_SHORT, true).show();
        ClipData clip = ClipData.newPlainText("simple text", data);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(clip);
        }
        showAd();

    }
    public void shareText(String data) {
        if (data.isEmpty()) {
            Toasty.info(context, "Enter some text", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, data);
            context.startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
        showAd();


    }
    public void copyTextAnother(String data){
        if (data.isEmpty()) {
            Toasty.info(context, "Enter some text", Toast.LENGTH_LONG).show();
            return;
        }
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        Toasty.success(context, "Copied to clipboard!", Toast.LENGTH_SHORT, true).show();
        ClipData clip = ClipData.newPlainText("simple text", data);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(clip);
        }
        showAd();

    }

    private void showAd(){
        if (mInterstitialAd != null) {
            mInterstitialAd.show((Activity) context);
        }
    }
    private void loadInterAd(){
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(context, context.getString(R.string.interstitial_ads), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }

}
