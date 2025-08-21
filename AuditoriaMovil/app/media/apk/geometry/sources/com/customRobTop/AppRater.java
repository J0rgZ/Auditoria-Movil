package com.customRobTop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
/* loaded from: classes.dex */
public class AppRater {
    private static final int DAYS_UNTIL_PROMPT = 2;
    private static final int LAUNCHES_AT_REMIND = 2;
    private static final int LAUNCHES_UNTIL_PROMPT = 5;

    public static void app_launched(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("apprater", 0);
        if (sharedPreferences.getBoolean("dontshowagain", false)) {
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong("launch_count", sharedPreferences.getLong("launch_count", 0L) + 1);
        long j = sharedPreferences.getLong("date_firstlaunch", 0L);
        Long.valueOf(j).getClass();
        if (j == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            Long.valueOf(currentTimeMillis).getClass();
            edit.putLong("date_firstlaunch", currentTimeMillis);
        }
        edit.commit();
    }

    public static void dontShowAgain(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("apprater", 0).edit();
        edit.putBoolean("dontshowagain", true);
        edit.commit();
    }

    public static void openRate(Context context) {
        JniToCpp.userDidAttemptToRateApp();
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName())));
    }

    public static void showRateDialog(final Context context, final SharedPreferences.Editor editor, String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thanks for playing!");
        AlertDialog.Builder cancelable = builder.setMessage("Please take some time to rate " + str + " on Google Play. We would love to hear what think! :)").setCancelable(false);
        cancelable.setPositiveButton("Rate " + str + "!", new DialogInterface.OnClickListener() { // from class: com.customRobTop.AppRater.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor editor2 = editor;
                if (editor2 != null) {
                    editor2.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                AppRater.openRate(context);
            }
        }).setNegativeButton("No Thanks", new DialogInterface.OnClickListener() { // from class: com.customRobTop.AppRater.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor editor2 = editor;
                if (editor2 != null) {
                    editor2.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                dialogInterface.cancel();
            }
        }).setNeutralButton("Remind me later", new DialogInterface.OnClickListener() { // from class: com.customRobTop.AppRater.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor editor2 = editor;
                if (editor2 != null) {
                    editor2.putLong("launch_count", 2L);
                    editor.commit();
                }
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    public static void tryShowRateDialog(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("apprater", 0);
        if (sharedPreferences.getBoolean("dontshowagain", false)) {
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        long j = sharedPreferences.getLong("launch_count", 0L);
        long j2 = sharedPreferences.getLong("date_firstlaunch", 0L);
        Long valueOf = Long.valueOf(j2);
        if (j >= 5) {
            long currentTimeMillis = System.currentTimeMillis();
            valueOf.getClass();
            if (currentTimeMillis >= j2 + 172800000) {
                showRateDialog(context, edit, str);
            }
        }
    }
}
