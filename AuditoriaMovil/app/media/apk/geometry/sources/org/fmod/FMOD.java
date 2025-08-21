package org.fmod;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import java.io.FileNotFoundException;
/* loaded from: classes.dex */
public class FMOD {
    private static Context gContext;
    private static PluginBroadcastReceiver gPluginBroadcastReceiver = new PluginBroadcastReceiver();

    /* loaded from: classes.dex */
    static class PluginBroadcastReceiver extends BroadcastReceiver {
        PluginBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            FMOD.OutputAAudioHeadphonesChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void OutputAAudioHeadphonesChanged();

    public static boolean checkInit() {
        return gContext != null;
    }

    public static void close() {
        Context context = gContext;
        if (context != null) {
            context.unregisterReceiver(gPluginBroadcastReceiver);
        }
        gContext = null;
    }

    public static int fileDescriptorFromUri(String str) {
        if (gContext != null) {
            try {
                return gContext.getContentResolver().openFileDescriptor(Uri.parse(str), "r").detachFd();
            } catch (FileNotFoundException unused) {
            }
        }
        return -1;
    }

    public static AssetManager getAssetManager() {
        Context context = gContext;
        if (context != null) {
            return context.getAssets();
        }
        return null;
    }

    public static int getOutputBlockSize() {
        String property;
        if (gContext == null || (property = ((AudioManager) gContext.getSystemService("audio")).getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER")) == null) {
            return 0;
        }
        return Integer.parseInt(property);
    }

    public static int getOutputSampleRate() {
        String property;
        if (gContext == null || (property = ((AudioManager) gContext.getSystemService("audio")).getProperty("android.media.property.OUTPUT_SAMPLE_RATE")) == null) {
            return 0;
        }
        return Integer.parseInt(property);
    }

    public static void init(Context context) {
        gContext = context;
        if (context != null) {
            gContext.registerReceiver(gPluginBroadcastReceiver, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        }
    }

    public static boolean isBluetoothOn() {
        Context context = gContext;
        if (context != null) {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            return audioManager.isBluetoothA2dpOn() || audioManager.isBluetoothScoOn();
        }
        return false;
    }

    public static boolean lowLatencyFlag() {
        if (gContext != null) {
            return gContext.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
        }
        return false;
    }

    public static boolean proAudioFlag() {
        if (gContext != null) {
            return gContext.getPackageManager().hasSystemFeature("android.hardware.audio.pro");
        }
        return false;
    }

    public static boolean supportsAAudio() {
        return Build.VERSION.SDK_INT >= 27;
    }

    public static boolean supportsLowLatency() {
        int outputBlockSize = getOutputBlockSize();
        boolean lowLatencyFlag = lowLatencyFlag();
        boolean proAudioFlag = proAudioFlag();
        boolean z = outputBlockSize > 0 && outputBlockSize <= 1024;
        boolean isBluetoothOn = isBluetoothOn();
        Log.i("fmod", "FMOD::supportsLowLatency                 : Low latency = " + lowLatencyFlag + ", Pro Audio = " + proAudioFlag + ", Bluetooth On = " + isBluetoothOn + ", Acceptable Block Size = " + z + " (" + outputBlockSize + ")");
        return z && lowLatencyFlag && !isBluetoothOn;
    }
}
