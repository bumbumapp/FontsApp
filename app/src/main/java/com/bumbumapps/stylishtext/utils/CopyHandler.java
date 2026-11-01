package com.bumbumapps.stylishtext.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.bumbumapps.stylishtext.ads.AdsManager;

import es.dmoral.toasty.Toasty;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Copy / share actions. A finished copy or share is the app's natural break point: the Huawei build may
 * show a capped interstitial first (AdsManager), then the action always runs exactly once.
 */
public class CopyHandler {
    Context context;

    public CopyHandler(Context context) {
        this.context = context;
    }

    private void afterBreak(Runnable action) {
        if (context instanceof Activity) {
            AdsManager.get(context).onBreakPoint((Activity) context, action);
        } else {
            // Floating bubble / process-text contexts: never show a full-screen ad there.
            action.run();
        }
    }

    public void copyText(String data) {
        if (data.isEmpty()) {
            Toasty.info(context, "Enter some text", Toast.LENGTH_LONG).show();
            return;
        }
        afterBreak(() -> copy(data, "Copied to clipboard! Your copied text is " + data));
    }

    public void shareText(String data) {
        if (data.isEmpty()) {
            Toasty.info(context, "Enter some text", Toast.LENGTH_LONG).show();
            return;
        }
        afterBreak(() -> {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, data);
                context.startActivity(Intent.createChooser(i, "choose one"));
            } catch (Exception e) {
                //e.toString();
            }
        });
    }

    public void copyTextAnother(String data) {
        if (data.isEmpty()) {
            Toasty.info(context, "Enter some text", Toast.LENGTH_LONG).show();
            return;
        }
        afterBreak(() -> copy(data, "Copied to clipboard!"));
    }

    private void copy(String data, String message) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        Toasty.success(context, message, Toast.LENGTH_SHORT, true).show();
        ClipData clip = ClipData.newPlainText("simple text", data);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(clip);
        }
    }
}
