package org.cocos2dx.lib;

import android.content.Context;
import android.opengl.ETC1Util;
import android.util.Log;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes.dex */
public class Cocos2dxETCLoader {
    private static final String ASSETS_PATH = "assets/";
    private static Context context;

    public static boolean loadTexture(String str) {
        ETC1Util.ETC1Texture eTC1Texture;
        InputStream open;
        if (ETC1Util.isETC1Supported() && str.length() != 0) {
            try {
                if (str.charAt(0) == '/') {
                    open = new FileInputStream(str);
                } else {
                    if (str.startsWith(ASSETS_PATH)) {
                        str = str.substring(7);
                    }
                    open = context.getAssets().open(str);
                }
                eTC1Texture = ETC1Util.createTexture(open);
                open.close();
            } catch (Exception unused) {
                Log.d("Cocos2dx", "Unable to create texture for " + str);
                eTC1Texture = null;
            }
            if (eTC1Texture != null) {
                try {
                    int width = eTC1Texture.getWidth();
                    int height = eTC1Texture.getHeight();
                    int remaining = eTC1Texture.getData().remaining();
                    byte[] bArr = new byte[remaining];
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.order(ByteOrder.nativeOrder());
                    wrap.put(eTC1Texture.getData());
                    nativeSetTextureInfo(width, height, bArr, remaining);
                    return true;
                } catch (Exception e) {
                    Log.d("invoke native function error", e.toString());
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    private static native void nativeSetTextureInfo(int i, int i2, byte[] bArr, int i3);

    public static void setContext(Context context2) {
        context = context2;
    }
}
