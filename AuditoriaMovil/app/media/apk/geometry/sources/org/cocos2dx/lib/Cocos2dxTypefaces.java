package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;
/* loaded from: classes.dex */
public class Cocos2dxTypefaces {
    private static final HashMap<String, Typeface> sTypefaceCache = new HashMap<>();

    public static synchronized Typeface get(Context context, String str) {
        Typeface typeface;
        synchronized (Cocos2dxTypefaces.class) {
            HashMap<String, Typeface> hashMap = sTypefaceCache;
            if (!hashMap.containsKey(str)) {
                hashMap.put(str, str.startsWith("/") ? Typeface.createFromFile(str) : Typeface.createFromAsset(context.getAssets(), str));
            }
            typeface = hashMap.get(str);
        }
        return typeface;
    }
}
