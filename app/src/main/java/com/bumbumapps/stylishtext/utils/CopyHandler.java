package com.bumbumapps.stylishtext.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;



import es.dmoral.toasty.Toasty;

import static android.content.Context.CLIPBOARD_SERVICE;

import static com.bumbumapps.stylishtext.utils.AdsUtils.loadGoogleInterstitialAd;
import static com.bumbumapps.stylishtext.utils.AdsUtils.mInterstitialAd;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.InterstitialAd;

public class CopyHandler {
    Context context;

    public CopyHandler(Context context) {
        this.context = context;

    }
    public void copyText(String data){
        if(Globals.TIMER_FINISHED && mInterstitialAd!=null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show((Activity) context);
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    Globals.TIMER_FINISHED = false;
                    Timers.timer().start();
                    mInterstitialAd = null;
                    loadGoogleInterstitialAd(context);

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
                }
            });
        }else {
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
        }




    }
    public void shareText(String data) {
        if(Globals.TIMER_FINISHED && mInterstitialAd!=null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show((Activity) context);
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    Globals.TIMER_FINISHED = false;
                    Timers.timer().start();
                    mInterstitialAd = null;
                    loadGoogleInterstitialAd(context);
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
                }
            });
        }else {

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

        }

    }
    public void copyTextAnother(String data){
        if(mInterstitialAd!=null && mInterstitialAd.isLoaded()){
            mInterstitialAd.show((Activity) context);
            mInterstitialAd.setAdListener(new AdListener(){
                @Override
                public void onAdClosed() {
                    Globals.TIMER_FINISHED=false;
                    Timers.timer().start();
                    mInterstitialAd = null;
                    loadGoogleInterstitialAd(context);

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
                }
            });


        }else {
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
        }

    }


}
