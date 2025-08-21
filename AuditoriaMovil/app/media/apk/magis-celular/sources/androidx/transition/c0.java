package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;
import v.d1;
/* loaded from: classes.dex */
public abstract class c0 {
    public static final o0 a;
    public static final Property b;
    public static final Property c;

    /* loaded from: classes.dex */
    public static class a extends Property {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(View view) {
            return Float.valueOf(c0.c(view));
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(View view, Float f) {
            c0.g(view, f.floatValue());
        }
    }

    /* loaded from: classes.dex */
    public static class b extends Property {
        public b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Rect get(View view) {
            return d1.s(view);
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(View view, Rect rect) {
            d1.r0(view, rect);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            a = new n0();
        } else if (i >= 23) {
            a = new m0();
        } else if (i >= 22) {
            a = new k0();
        } else if (i >= 21) {
            a = new i0();
        } else {
            a = new f0();
        }
        b = new a(Float.class, "translationAlpha");
        c = new b(Rect.class, "clipBounds");
    }

    public static void a(View view) {
        a.a(view);
    }

    public static b0 b(View view) {
        return new a0(view);
    }

    public static float c(View view) {
        return a.c(view);
    }

    public static r0 d(View view) {
        return new q0(view);
    }

    public static void e(View view) {
        a.d(view);
    }

    public static void f(View view, int i, int i2, int i3, int i4) {
        a.e(view, i, i2, i3, i4);
    }

    public static void g(View view, float f) {
        a.f(view, f);
    }

    public static void h(View view, int i) {
        a.g(view, i);
    }

    public static void i(View view, Matrix matrix) {
        a.h(view, matrix);
    }

    public static void j(View view, Matrix matrix) {
        a.i(view, matrix);
    }
}
