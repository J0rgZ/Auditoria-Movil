package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.TypedValue;
/* loaded from: classes.dex */
public abstract class n2 {
    public static final ThreadLocal a = new ThreadLocal();
    public static final int[] b = {-16842910};
    public static final int[] c = {16842908};
    public static final int[] d = {16843518};
    public static final int[] e = {16842919};
    public static final int[] f = {16842912};
    public static final int[] g = {16842913};
    public static final int[] h = {-16842919, -16842908};
    public static final int[] i = new int[0];
    public static final int[] j = new int[1];

    public static int a(Context context, int i2) {
        ColorStateList d2 = d(context, i2);
        if (d2 != null && d2.isStateful()) {
            return d2.getColorForState(b, d2.getDefaultColor());
        }
        TypedValue e2 = e();
        context.getTheme().resolveAttribute(16842803, e2, true);
        return c(context, i2, e2.getFloat());
    }

    public static int b(Context context, int i2) {
        int[] iArr = j;
        iArr[0] = i2;
        r2 t = r2.t(context, null, iArr);
        try {
            return t.b(0, 0);
        } finally {
            t.v();
        }
    }

    public static int c(Context context, int i2, float f2) {
        int b2 = b(context, i2);
        return l.a.m(b2, Math.round(Color.alpha(b2) * f2));
    }

    public static ColorStateList d(Context context, int i2) {
        int[] iArr = j;
        iArr[0] = i2;
        r2 t = r2.t(context, null, iArr);
        try {
            return t.c(0);
        } finally {
            t.v();
        }
    }

    public static TypedValue e() {
        ThreadLocal threadLocal = a;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue == null) {
            TypedValue typedValue2 = new TypedValue();
            threadLocal.set(typedValue2);
            return typedValue2;
        }
        return typedValue;
    }
}
