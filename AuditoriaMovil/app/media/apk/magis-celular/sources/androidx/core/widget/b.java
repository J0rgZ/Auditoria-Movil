package androidx.core.widget;

import android.os.Build;
/* loaded from: classes.dex */
public interface b {
    public static final boolean P;

    static {
        boolean z;
        if (Build.VERSION.SDK_INT >= 27) {
            z = true;
        } else {
            z = false;
        }
        P = z;
    }
}
