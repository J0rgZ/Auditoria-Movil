package com.customRobTop;

import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import java.io.File;
import java.util.UUID;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
/* loaded from: classes.dex */
public class BaseRobTopActivity extends DefaultRobTopActivity {
    public static final int SCREEN_ORIENTATION_LANDSCAPE = 0;
    public static final int SCREEN_ORIENTATION_SENSOR_LANDSCAPE = 6;
    public static final int SCREEN_ORIENTATION_USER_LANDSCAPE = 11;
    public static final String TAG = "RobTopBase";
    public static boolean blockBackButton_ = false;
    public static boolean focusState_ = true;
    public static boolean isLoaded_ = false;
    public static boolean isPaused_ = false;
    public static boolean keyboardActive_ = false;
    public static BaseRobTopActivity me = null;
    public static BroadcastReceiver receiver_ = null;
    public static boolean shouldResumeSound_ = true;

    /* loaded from: classes.dex */
    public class receiverScreen extends BroadcastReceiver {
        public receiverScreen() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                Log.d("TAG", "ACTION_SCREEN_ON");
                if (!((KeyguardManager) BaseRobTopActivity.this.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
                    BaseRobTopActivity.shouldResumeSound_ = true;
                }
                if (!BaseRobTopActivity.isPaused_ && BaseRobTopActivity.shouldResumeSound_) {
                    JniToCpp.resumeSound();
                }
            }
            if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                BaseRobTopActivity.shouldResumeSound_ = false;
            }
            if (intent.getAction().equals("android.intent.action.USER_PRESENT")) {
                BaseRobTopActivity.shouldResumeSound_ = true;
                if (BaseRobTopActivity.isPaused_) {
                    return;
                }
                JniToCpp.resumeSound();
            }
        }
    }

    public static String admobKey() {
        return "";
    }

    public static void cacheInterstitial() {
    }

    public static void cacheRewardedVideo() {
    }

    public static String cbAppID() {
        return "";
    }

    public static String cbAppSignature() {
        return "";
    }

    public static void disableBanner() {
        me._disableBanner();
    }

    public static boolean doesFileExist(String str) {
        return new File(str).exists();
    }

    public static void downloadImage(String str, String str2, String str3) {
    }

    public static void enableBanner() {
        me._enableBanner();
    }

    public static void enableBannerNoRefresh() {
        enableBanner();
    }

    public static boolean gameServicesIsSignedIn() {
        return false;
    }

    public static void gameServicesSignIn() {
    }

    public static void gameServicesSignOut() {
    }

    public static float getDeviceRefreshRate() {
        return ((WindowManager) me.getSystemService("window")).getDefaultDisplay().getRefreshRate();
    }

    public static String getUserID() {
        String string = Settings.Secure.getString(me.getContentResolver(), "android_id");
        return !"9774d56d682e549c".equals(string) ? UUID.nameUUIDFromBytes(string.getBytes()).toString() : UUID.randomUUID().toString();
    }

    public static boolean hasCachedInterstitial() {
        return me._hasCachedInterstitial();
    }

    public static boolean hasCachedRewardedVideo() {
        return me._hasCachedRewardedVideo();
    }

    public static boolean isNetworkAvailable() {
        NetworkInfo[] allNetworkInfo;
        boolean z = false;
        boolean z2 = false;
        for (NetworkInfo networkInfo : ((ConnectivityManager) me.getSystemService("connectivity")).getAllNetworkInfo()) {
            if (networkInfo.getTypeName().equalsIgnoreCase("WIFI") && networkInfo.isConnected()) {
                z = true;
            }
            if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE") && networkInfo.isConnected()) {
                z2 = true;
            }
        }
        return z || z2;
    }

    public static void loadingFinished() {
        isLoaded_ = true;
    }

    public static void logEvent(String str) {
    }

    public static void onNativePause() {
    }

    public static void onNativeResume() {
    }

    public static void onToggleKeyboard() {
        me.runOnUiThread(new Runnable() { // from class: com.customRobTop.BaseRobTopActivity.2
            @Override // java.lang.Runnable
            public void run() {
                if (BaseRobTopActivity.keyboardActive_) {
                    Cocos2dxGLSurfaceView.openIMEKeyboard();
                } else {
                    Cocos2dxGLSurfaceView.closeIMEKeyboard();
                }
            }
        });
    }

    public static void openAppPage() {
        me.runOnUiThread(new Runnable() { // from class: com.customRobTop.BaseRobTopActivity.3
            @Override // java.lang.Runnable
            public void run() {
                AppRater.dontShowAgain(BaseRobTopActivity.me);
                AppRater.openRate(BaseRobTopActivity.me);
            }
        });
    }

    public static void openURL(final String str) {
        me.runOnUiThread(new Runnable() { // from class: com.customRobTop.BaseRobTopActivity.5
            @Override // java.lang.Runnable
            public void run() {
                BaseRobTopActivity.me.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            }
        });
    }

    public static void pauseAds() {
        me._pauseAds();
    }

    public static void queueRefreshBanner() {
    }

    public static void resumeAds() {
        me._resumeAds();
    }

    public static void sendMail(final String str, final String str2, final String str3) {
        me.runOnUiThread(new Runnable() { // from class: com.customRobTop.BaseRobTopActivity.6
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("message/rfc822");
                intent.putExtra("android.intent.extra.EMAIL", new String[]{str3});
                intent.putExtra("android.intent.extra.SUBJECT", str);
                intent.putExtra("android.intent.extra.TEXT", str2);
                try {
                    BaseRobTopActivity.me.startActivity(Intent.createChooser(intent, "Send mail..."));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(BaseRobTopActivity.me, "There are no email clients installed.", 0).show();
                }
            }
        });
    }

    public static void setBlockBackButton(boolean z) {
        blockBackButton_ = z;
    }

    public static void setKeyboardState(boolean z) {
        keyboardActive_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View setSystemUiVisilityMode() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(5894);
        return decorView;
    }

    public static void setupEveryplay() {
    }

    private void setupMainWindowDisplayMode() {
        setSystemUiVisilityMode().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.customRobTop.BaseRobTopActivity.1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                if ((i & 4) == 0) {
                    BaseRobTopActivity.this.setSystemUiVisilityMode();
                }
            }
        });
    }

    public static boolean shouldResumeSound() {
        return shouldResumeSound_;
    }

    public static void showAchievements() {
    }

    public static void showAdDebug() {
        me._showAdDebug();
    }

    public static void showInterstitial() {
        me._showInterstitial();
    }

    public static void showLeaderboards() {
    }

    public static void showRewardedVideo() {
        me._showRewardedVideo();
    }

    public static void tryShowRateDialog(final String str) {
        me.runOnUiThread(new Runnable() { // from class: com.customRobTop.BaseRobTopActivity.4
            @Override // java.lang.Runnable
            public void run() {
                AppRater.tryShowRateDialog(BaseRobTopActivity.me, str);
            }
        });
    }

    public static void unlockAchievement(String str) {
    }

    public static void updateTopScoreLeaderboard(int i) {
    }

    public void _disableBanner() {
    }

    public void _enableBanner() {
    }

    public boolean _hasCachedInterstitial() {
        return false;
    }

    public boolean _hasCachedRewardedVideo() {
        return false;
    }

    public void _pauseAds() {
    }

    public void _resumeAds() {
    }

    public void _showAdDebug() {
    }

    public void _showInterstitial() {
    }

    public void _showRewardedVideo() {
    }

    @Override // com.customRobTop.DefaultRobTopActivity
    public String getPublicKey() {
        return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAklRPrtot3mua6MYUJXkbGD65TmPoUgHjLKuh0SZ67brpqDwftbmzkzc/cE7hiLeMSG/lfYYdX2/jhkhv2OULo67NHgzsskpIOOIP3zB11d+4aH8XkDPB1yRjI+rwk1v/MeK/uilnPUbSF8XnmEvTNxq47r5xgeOrJKjdO3TG6S7IneHQo1vZNwv/HqHJ3RhgzmJW79xNf0GWbPmMjusUhdbOtZdt1DbcMJIaORzBgyU9MnSwy37ri7cOhNL+OLdzUp+keuk7Exi6XAUR9CMeQ5JXdrCk37FAog6LA7IpIEm+ZIFRqhhRVaQ6+KTcTqyNpvYIYh5bH772d3Ro3uVG+QIDAQAB";
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.customRobTop.DefaultRobTopActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        ApplicationInfo applicationInfo = getApplication().getApplicationInfo();
        JniToCpp.setupHSSAssets(applicationInfo.sourceDir, Environment.getExternalStorageDirectory().getAbsolutePath());
        super.onCreate(bundle);
        if (me == null) {
            AppRater.app_launched(this);
        }
        setRequestedOrientation(11);
        setupMainWindowDisplayMode();
        me = this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        isPaused_ = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        updateVisibilityMode();
        isPaused_ = false;
    }

    public void onSignInFailed() {
    }

    public void onSignInSucceeded() {
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            updateVisibilityMode();
        }
    }

    void registerReceiver() {
        BroadcastReceiver broadcastReceiver = receiver_;
        if (broadcastReceiver != null) {
            me.unregisterReceiver(broadcastReceiver);
            receiver_ = null;
        }
        try {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            receiverScreen receiverscreen = new receiverScreen();
            receiver_ = receiverscreen;
            registerReceiver(receiverscreen, intentFilter);
        } catch (Exception unused) {
        }
    }

    public void updateVisibilityMode() {
        setSystemUiVisilityMode();
    }
}
