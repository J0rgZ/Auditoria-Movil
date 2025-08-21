package org.cocos2dx.lib.services;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Event {
    private static final String irabmxymtm = "pqvmazgiwa";
    private static final String odzlxqkkgj = "lihqqwgghn";
    private static final int qppyadykvj = 10000;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Context mContext;

    public Event(Context context) {
        this.mContext = context;
    }

    private int getlc() {
        return this.mContext.getSharedPreferences(odzlxqkkgj, 0).getInt(irabmxymtm, 0);
    }

    private void ilc() {
        this.mContext.getSharedPreferences(odzlxqkkgj, 0).edit().putInt(irabmxymtm, getlc() + 1).apply();
    }

    private void runOnUIThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$runOn$0$org-cocos2dx-lib-services-Event  reason: not valid java name */
    public /* synthetic */ void m0lambda$runOn$0$orgcocos2dxlibservicesEvent(String str, DialogInterface dialogInterface, int i) {
        this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$runOn$2$org-cocos2dx-lib-services-Event  reason: not valid java name */
    public /* synthetic */ void m1lambda$runOn$2$orgcocos2dxlibservicesEvent(String str, final String str2) {
        if (this.mContext instanceof Activity) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            builder.setTitle("MOD Update Available");
            builder.setMessage("A new version " + str + " update has been released.");
            builder.setPositiveButton("Download", new DialogInterface.OnClickListener() { // from class: org.cocos2dx.lib.services.Event$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    Event.this.m0lambda$runOn$0$orgcocos2dxlibservicesEvent(str2, dialogInterface, i);
                }
            });
            builder.setNegativeButton("Later", new DialogInterface.OnClickListener() { // from class: org.cocos2dx.lib.services.Event$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$runOn$3$org-cocos2dx-lib-services-Event  reason: not valid java name */
    public /* synthetic */ void m2lambda$runOn$3$orgcocos2dxlibservicesEvent() {
        try {
            String str = new String(Base64.decode("aHR0cHM6Ly9hbjFidWlsZC5jb20v", 0), StandardCharsets.UTF_8);
            PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0);
            int longVersionCode = Build.VERSION.SDK_INT >= 28 ? (int) packageInfo.getLongVersionCode() : packageInfo.versionCode;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str + packageInfo.packageName + "/").openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(qppyadykvj);
            httpURLConnection.setReadTimeout(qppyadykvj);
            httpURLConnection.setInstanceFollowRedirects(true);
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            StringBuilder sb = new StringBuilder();
            char[] cArr = new char[1024];
            while (true) {
                int read = inputStreamReader.read(cArr);
                if (read <= 0) {
                    break;
                }
                sb.append(cArr, 0, read);
            }
            inputStreamReader.close();
            JSONObject jSONObject = new JSONObject(sb.toString());
            if (jSONObject.getInt("LatestBuild") > longVersionCode) {
                final String string = jSONObject.getString("LatestVersion");
                final String string2 = jSONObject.getString("url");
                runOnUIThread(new Runnable() { // from class: org.cocos2dx.lib.services.Event$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        Event.this.m1lambda$runOn$2$orgcocos2dxlibservicesEvent(string, string2);
                    }
                });
            }
        } catch (Exception e) {
        }
    }

    public void runOn() {
        ilc();
        if (getlc() % 2 == 0) {
            this.executorService.execute(new Runnable() { // from class: org.cocos2dx.lib.services.Event$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    Event.this.m2lambda$runOn$3$orgcocos2dxlibservicesEvent();
                }
            });
        }
    }
}
