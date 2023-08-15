package com.bumbumapps.stylishtext.utils;

import android.app.Activity;
import android.content.Context;

import com.bumbumapps.stylishtext.R;
import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.InterstitialAd;
import com.huawei.hms.ads.banner.BannerView;

public class AdsUtils {

    public static void showGoogleBannerAd(Context context, BannerView bannerView) {
        bannerView.setAdId(context.getString(R.string.banner_id));
        bannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_360_57);
        bannerView.setBannerRefresh(60);
        AdParam adParam = new AdParam.Builder().build();
        bannerView.loadAd(adParam);
    }

     public static InterstitialAd mInterstitialAd;

    public static void loadGoogleInterstitialAd(Context context) {
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdId(context.getString(R.string.interstial_id));
        AdParam adParam = new AdParam.Builder().build();
        mInterstitialAd.loadAd(adParam);
    }


}
