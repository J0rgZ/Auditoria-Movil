package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import java.lang.ref.WeakReference;
import k.h;
/* loaded from: classes.dex */
public class p0 {
    public final TextView a;
    public p2 b;
    public p2 c;
    public p2 d;
    public p2 e;
    public p2 f;
    public p2 g;
    public p2 h;
    public final e1 i;
    public int j = 0;
    public int k = -1;
    public Typeface l;
    public boolean m;

    /* loaded from: classes.dex */
    public static class a extends h.c {
        public final WeakReference a;
        public final int b;
        public final int c;

        /* renamed from: androidx.appcompat.widget.p0$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0010a implements Runnable {
            public final WeakReference a;
            public final Typeface b;

            public RunnableC0010a(WeakReference weakReference, Typeface typeface) {
                this.a = weakReference;
                this.b = typeface;
            }

            @Override // java.lang.Runnable
            public void run() {
                p0 p0Var = (p0) this.a.get();
                if (p0Var == null) {
                    return;
                }
                p0Var.B(this.b);
            }
        }

        public a(p0 p0Var, int i, int i2) {
            this.a = new WeakReference(p0Var);
            this.b = i;
            this.c = i2;
        }

        public void onFontRetrievalFailed(int i) {
        }

        public void onFontRetrieved(Typeface typeface) {
            int i;
            boolean z;
            p0 p0Var = (p0) this.a.get();
            if (p0Var == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 28 && (i = this.b) != -1) {
                if ((this.c & 2) != 0) {
                    z = true;
                } else {
                    z = false;
                }
                typeface = Typeface.create(typeface, i, z);
            }
            p0Var.q(new RunnableC0010a(this.a, typeface));
        }
    }

    public p0(TextView textView) {
        this.a = textView;
        this.i = new e1(textView);
    }

    public static p2 d(Context context, k kVar, int i) {
        ColorStateList f = kVar.f(context, i);
        if (f != null) {
            p2 p2Var = new p2();
            p2Var.d = true;
            p2Var.a = f;
            return p2Var;
        }
        return null;
    }

    public final void A(int i, float f) {
        this.i.v(i, f);
    }

    public void B(Typeface typeface) {
        if (this.m) {
            this.a.setTypeface(typeface);
            this.l = typeface;
        }
    }

    public final void C(Context context, r2 r2Var) {
        String o;
        Typeface create;
        boolean z;
        boolean z2;
        Typeface create2;
        this.j = r2Var.k(R$styleable.TextAppearance_android_textStyle, this.j);
        int i = Build.VERSION.SDK_INT;
        boolean z3 = false;
        if (i >= 28) {
            int k = r2Var.k(R$styleable.TextAppearance_android_textFontWeight, -1);
            this.k = k;
            if (k != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        int i2 = R$styleable.TextAppearance_android_fontFamily;
        if (!r2Var.r(i2) && !r2Var.r(R$styleable.TextAppearance_fontFamily)) {
            int i3 = R$styleable.TextAppearance_android_typeface;
            if (r2Var.r(i3)) {
                this.m = false;
                int k2 = r2Var.k(i3, 1);
                if (k2 != 1) {
                    if (k2 != 2) {
                        if (k2 == 3) {
                            this.l = Typeface.MONOSPACE;
                            return;
                        }
                        return;
                    }
                    this.l = Typeface.SERIF;
                    return;
                }
                this.l = Typeface.SANS_SERIF;
                return;
            }
            return;
        }
        this.l = null;
        int i4 = R$styleable.TextAppearance_fontFamily;
        if (r2Var.r(i4)) {
            i2 = i4;
        }
        int i5 = this.k;
        int i6 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface j = r2Var.j(i2, this.j, new a(this, i5, i6));
                if (j != null) {
                    if (i >= 28 && this.k != -1) {
                        Typeface create3 = Typeface.create(j, 0);
                        int i7 = this.k;
                        if ((this.j & 2) != 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        create2 = Typeface.create(create3, i7, z2);
                        this.l = create2;
                    } else {
                        this.l = j;
                    }
                }
                if (this.l == null) {
                    z = true;
                } else {
                    z = false;
                }
                this.m = z;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l == null && (o = r2Var.o(i2)) != null) {
            if (Build.VERSION.SDK_INT >= 28 && this.k != -1) {
                Typeface create4 = Typeface.create(o, 0);
                int i8 = this.k;
                if ((this.j & 2) != 0) {
                    z3 = true;
                }
                create = Typeface.create(create4, i8, z3);
                this.l = create;
                return;
            }
            this.l = Typeface.create(o, this.j);
        }
    }

    public final void a(Drawable drawable, p2 p2Var) {
        if (drawable != null && p2Var != null) {
            k.i(drawable, p2Var, this.a.getDrawableState());
        }
    }

    public void b() {
        if (this.b != null || this.c != null || this.d != null || this.e != null) {
            Drawable[] compoundDrawables = this.a.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
        if (this.f != null || this.g != null) {
            Drawable[] compoundDrawablesRelative = this.a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f);
            a(compoundDrawablesRelative[2], this.g);
        }
    }

    public void c() {
        this.i.a();
    }

    public int e() {
        return this.i.h();
    }

    public int f() {
        return this.i.i();
    }

    public int g() {
        return this.i.j();
    }

    public int[] h() {
        return this.i.k();
    }

    public int i() {
        return this.i.l();
    }

    public ColorStateList j() {
        p2 p2Var = this.h;
        if (p2Var != null) {
            return p2Var.a;
        }
        return null;
    }

    public PorterDuff.Mode k() {
        p2 p2Var = this.h;
        if (p2Var != null) {
            return p2Var.b;
        }
        return null;
    }

    public boolean l() {
        return this.i.p();
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:161:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01b1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void m(android.util.AttributeSet r24, int r25) {
        /*
            Method dump skipped, instructions count: 774
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.p0.m(android.util.AttributeSet, int):void");
    }

    public void n(boolean z, int i, int i2, int i3, int i4) {
        if (!androidx.core.widget.b.P) {
            c();
        }
    }

    public void o() {
        b();
    }

    public void p(Context context, int i) {
        String o;
        ColorStateList c;
        r2 s2 = r2.s(context, i, R$styleable.L);
        int i2 = R$styleable.TextAppearance_textAllCaps;
        if (s2.r(i2)) {
            r(s2.a(i2, false));
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 23) {
            int i4 = R$styleable.TextAppearance_android_textColor;
            if (s2.r(i4) && (c = s2.c(i4)) != null) {
                this.a.setTextColor(c);
            }
        }
        int i5 = R$styleable.TextAppearance_android_textSize;
        if (s2.r(i5) && s2.f(i5, -1) == 0) {
            this.a.setTextSize(0, 0.0f);
        }
        C(context, s2);
        if (i3 >= 26) {
            int i6 = R$styleable.TextAppearance_fontVariationSettings;
            if (s2.r(i6) && (o = s2.o(i6)) != null) {
                this.a.setFontVariationSettings(o);
            }
        }
        s2.v();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.a.setTypeface(typeface, this.j);
        }
    }

    public void q(Runnable runnable) {
        this.a.post(runnable);
    }

    public void r(boolean z) {
        this.a.setAllCaps(z);
    }

    public void s(int i, int i2, int i3, int i4) {
        this.i.r(i, i2, i3, i4);
    }

    public void t(int[] iArr, int i) {
        this.i.s(iArr, i);
    }

    public void u(int i) {
        this.i.t(i);
    }

    public void v(ColorStateList colorStateList) {
        boolean z;
        if (this.h == null) {
            this.h = new p2();
        }
        p2 p2Var = this.h;
        p2Var.a = colorStateList;
        if (colorStateList != null) {
            z = true;
        } else {
            z = false;
        }
        p2Var.d = z;
        y();
    }

    public void w(PorterDuff.Mode mode) {
        boolean z;
        if (this.h == null) {
            this.h = new p2();
        }
        p2 p2Var = this.h;
        p2Var.b = mode;
        if (mode != null) {
            z = true;
        } else {
            z = false;
        }
        p2Var.c = z;
        y();
    }

    public final void x(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (drawable5 == null && drawable6 == null) {
            if (drawable != null || drawable2 != null || drawable3 != null || drawable4 != null) {
                Drawable[] compoundDrawablesRelative = this.a.getCompoundDrawablesRelative();
                Drawable drawable7 = compoundDrawablesRelative[0];
                if (drawable7 == null && compoundDrawablesRelative[2] == null) {
                    Drawable[] compoundDrawables = this.a.getCompoundDrawables();
                    TextView textView = this.a;
                    if (drawable == null) {
                        drawable = compoundDrawables[0];
                    }
                    if (drawable2 == null) {
                        drawable2 = compoundDrawables[1];
                    }
                    if (drawable3 == null) {
                        drawable3 = compoundDrawables[2];
                    }
                    if (drawable4 == null) {
                        drawable4 = compoundDrawables[3];
                    }
                    textView.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
                    return;
                }
                TextView textView2 = this.a;
                if (drawable2 == null) {
                    drawable2 = compoundDrawablesRelative[1];
                }
                Drawable drawable8 = compoundDrawablesRelative[2];
                if (drawable4 == null) {
                    drawable4 = compoundDrawablesRelative[3];
                }
                textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, drawable8, drawable4);
                return;
            }
            return;
        }
        Drawable[] compoundDrawablesRelative2 = this.a.getCompoundDrawablesRelative();
        TextView textView3 = this.a;
        if (drawable5 == null) {
            drawable5 = compoundDrawablesRelative2[0];
        }
        if (drawable2 == null) {
            drawable2 = compoundDrawablesRelative2[1];
        }
        if (drawable6 == null) {
            drawable6 = compoundDrawablesRelative2[2];
        }
        if (drawable4 == null) {
            drawable4 = compoundDrawablesRelative2[3];
        }
        textView3.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
    }

    public final void y() {
        p2 p2Var = this.h;
        this.b = p2Var;
        this.c = p2Var;
        this.d = p2Var;
        this.e = p2Var;
        this.f = p2Var;
        this.g = p2Var;
    }

    public void z(int i, float f) {
        if (!androidx.core.widget.b.P && !l()) {
            A(i, f);
        }
    }
}
