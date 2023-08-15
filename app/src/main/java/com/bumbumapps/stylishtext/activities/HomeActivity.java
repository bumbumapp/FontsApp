package com.bumbumapps.stylishtext.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.floating.stylish.FloatingStylishOpenShortCutActivity;
import com.bumbumapps.stylishtext.fragments.FavouriteFragment;
import com.bumbumapps.stylishtext.fragments.HomeFragment;
import com.bumbumapps.stylishtext.utils.AdsUtils;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;

import com.huawei.hms.ads.banner.BannerView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.Objects;


public class HomeActivity extends AppCompatActivity implements ChipNavigationBar.OnItemSelectedListener, PopupMenu.OnMenuItemClickListener {

    ChipNavigationBar navBar;
    MaterialToolbar toolbar;

    private AppUpdateManager appUpdateManager;
    private static final int FLEXIBLE_APP_UPDATE_REQ_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Admob Ads Initialize
        loadBannerAds();
        //Firebase Analytics

        //Bottom Navigation
        navBar = findViewById(R.id.chipnav);
        appUpdateManager = AppUpdateManagerFactory.create(this);
        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(result -> {
            if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && result.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)){
                try {
                    appUpdateManager.startUpdateFlowForResult(result, AppUpdateType.FLEXIBLE, HomeActivity.this,FLEXIBLE_APP_UPDATE_REQ_CODE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }

            }

        });
        AdsUtils.loadGoogleInterstitialAd(this);

        appUpdateManager.registerListener(installStateUpdatedListener);
        navBar.setOnItemSelectedListener(this);
        navBar.setItemSelected(R.id.home,true);
        loadFragment(new HomeFragment());
        findViewById(R.id.menu_more).setOnClickListener(view -> {
            inisalizePopupMenu(view);
        });

    }
    InstallStateUpdatedListener installStateUpdatedListener = state -> {
        if (state.installStatus() == InstallStatus.DOWNLOADED){
            showCompletedUpdate();
        }
    };
    private void inisalizePopupMenu(View view){
        PopupMenu popupMenu=new PopupMenu(HomeActivity.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.home_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    private void showCompletedUpdate() {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"New app is ready!",Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Install" , view -> appUpdateManager.completeUpdate());
        snackbar.show();
    }

    @Override
    protected void onStop() {
        if (appUpdateManager != null) appUpdateManager.unregisterListener(installStateUpdatedListener);
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if (requestCode == FLEXIBLE_APP_UPDATE_REQ_CODE && resultCode !=RESULT_OK){
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //Fragment Container
    private void loadFragment(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,fragment)
                    .commit();
        }
    }

    @Override
    public void onItemSelected(int i) {
        Fragment fragment = null;
        if (i == R.id.home) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Home");
            fragment = new HomeFragment();
        } else if (i == R.id.favourite) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Favourite");
            fragment = new FavouriteFragment();
    }

        loadFragment(fragment);
    }







    @Override
    public void onBackPressed() {

          showExit();
    }

    //Exit Dialog
    private void showExit() {
        final Dialog customDialog;
        LayoutInflater inflater =  getLayoutInflater();
        @SuppressLint("InflateParams") View customView = inflater.inflate(R.layout.layout_exit, null);
        customDialog = new Dialog(this, R.style.DialogCustomTheme);
        customDialog.setContentView(customView);
        Button no = customDialog.findViewById(R.id.tv_no);
        Button yes = customDialog.findViewById(R.id.tv_yes);

        no.setOnClickListener(v -> customDialog.dismiss());

        yes.setOnClickListener(v -> finish());

        customDialog.show();
    }
    private void loadBannerAds() {
        BannerView bannerView=findViewById(R.id.hw_banner_view);
        AdsUtils.showGoogleBannerAd(this,bannerView);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.info){
            startActivity(new Intent(this, HelpActivity.class));
        }
            else if (item.getItemId() == R.id.floatmenu) {
            startActivity(new Intent(getApplicationContext(), FloatingStylishOpenShortCutActivity.class));
            }


        return true;
    }
}