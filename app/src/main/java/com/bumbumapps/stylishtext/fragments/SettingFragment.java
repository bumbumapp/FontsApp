package com.bumbumapps.stylishtext.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.activities.ThemesActivity;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

public class SettingFragment extends Fragment implements View.OnClickListener {
    ReviewManager reviewManager;
    Activity context;
    LinearLayout theme,mail,rate,policy,about,share;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        theme = view.findViewById(R.id.theme);
        mail = view.findViewById(R.id.mail);
        rate = view.findViewById(R.id.rate);
        policy = view.findViewById(R.id.policy);
        about = view.findViewById(R.id.about);
        share = view.findViewById(R.id.share);
        reviewManager = ReviewManagerFactory.create(requireContext());
        theme.setOnClickListener(this);
        mail.setOnClickListener(this);
        policy.setOnClickListener(this);
        rate.setOnClickListener(this);
        about.setOnClickListener(this);
        share.setOnClickListener(this);

        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.theme) {
            startActivity(new Intent(context, ThemesActivity.class));
        }
        //Share App Link
        if (id == R.id.share){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_msg) + requireActivity().getPackageName());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        if (id == R.id.rate){
            showRateApp();
        }
        if (id == R.id.about) {
                  showAboutDialog();        }
        //Email Open Intent
        if (id == R.id.mail){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.email));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
            startActivity(Intent.createChooser(intent, "Send Email"));
        }
        //Policy Open Intent
        if (id == R.id.policy){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.privacypolicy)));
            startActivity(browserIntent);
        }

    }
     // About App Dialog
    private void showAboutDialog() {
        final Dialog dialog = new Dialog(context, R.style.DialogCustomTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_about);

        Button dialog_btn=dialog.findViewById(R.id.bt_close);
        Button dialog_btnrate=dialog.findViewById(R.id.bt_rate);

        dialog_btnrate.setOnClickListener(v -> rateApp());

        dialog_btn.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
    //Rate Your App
    private void rateApp(){
        final String appName = requireActivity().getPackageName();
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id="
                            + appName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id="
                            + appName)));
        }
    } public void showRateApp() {
        Task<ReviewInfo> request = reviewManager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();

                Task<Void> flow = reviewManager.launchReviewFlow(context, reviewInfo);
                flow.addOnCompleteListener(task1 -> {

                });

            } else {
                rateApp();
            }
        });
    }
}