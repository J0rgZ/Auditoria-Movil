package com.android.unityengine;

import android.content.Context;
import android.util.Base64;
import android.widget.Toast;
/* loaded from: classes.dex */
public class UnityPIayerNativeActivity {
    public static void Init(Context context) {
        if (context.getSharedPreferences("IsUnityEngine", 0).getBoolean("oVrRz", true)) {
            context.getSharedPreferences("IsUnityEngine", 0).edit().putBoolean("oVrRz", false).commit();
            Toast.makeText(context, new String(Base64.decode("ICAgQU4xLkNPTSAg", 0)), 1).show();
            Toast.makeText(context, new String(Base64.decode("ICAgQU4xLkNPTSAg", 0)), 1).show();
        }
    }
}
