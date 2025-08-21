package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class x2 extends Resources {
    public static boolean b;
    public final WeakReference a;

    public x2(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.a = new WeakReference(context);
    }

    public static boolean a() {
        return b;
    }

    public static boolean b() {
        if (a() && Build.VERSION.SDK_INT <= 20) {
            return true;
        }
        return false;
    }

    public final Drawable c(int i) {
        return super.getDrawable(i);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) {
        Context context = (Context) this.a.get();
        if (context != null) {
            return e2.h().t(context, this, i);
        }
        return super.getDrawable(i);
    }
}
