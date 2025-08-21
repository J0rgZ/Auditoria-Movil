package com.customRobTop;
/* loaded from: classes.dex */
public class JniToCpp {
    public static native void didHideInterstitial();

    public static native void didShowInterstitial();

    public static native void everyplayRecordingStopped();

    public static native void googlePlaySignedIn();

    public static native void hideLoadingCircle();

    public static native void itemPurchased(String str);

    public static native void itemRefunded(String str);

    public static native void promoImageDownloaded();

    public static native void resumeSound();

    public static native void rewardedVideoAdFinished(int i);

    public static native void rewardedVideoAdHidden();

    public static native void setupHSSAssets(String str, String str2);

    public static native void userDidAttemptToRateApp();

    public static native void videoAdHidden();

    public static native void videoAdShowed();
}
