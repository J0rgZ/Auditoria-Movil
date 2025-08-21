package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.R$styleable;
/* loaded from: classes.dex */
public class f {
    public final View a;
    public p2 d;
    public p2 e;
    public p2 f;
    public int c = -1;
    public final k b = k.b();

    public f(View view) {
        this.a = view;
    }

    public final boolean a(Drawable drawable) {
        if (this.f == null) {
            this.f = new p2();
        }
        p2 p2Var = this.f;
        p2Var.a();
        ColorStateList q = v.d1.q(this.a);
        if (q != null) {
            p2Var.d = true;
            p2Var.a = q;
        }
        PorterDuff.Mode r = v.d1.r(this.a);
        if (r != null) {
            p2Var.c = true;
            p2Var.b = r;
        }
        if (!p2Var.d && !p2Var.c) {
            return false;
        }
        k.i(drawable, p2Var, this.a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable background = this.a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            p2 p2Var = this.e;
            if (p2Var != null) {
                k.i(background, p2Var, this.a.getDrawableState());
                return;
            }
            p2 p2Var2 = this.d;
            if (p2Var2 != null) {
                k.i(background, p2Var2, this.a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        p2 p2Var = this.e;
        if (p2Var != null) {
            return p2Var.a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        p2 p2Var = this.e;
        if (p2Var != null) {
            return p2Var.b;
        }
        return null;
    }

    public void e(AttributeSet attributeSet, int i) {
        r2 u = r2.u(this.a.getContext(), attributeSet, R$styleable.O, i, 0);
        try {
            int i2 = R$styleable.ViewBackgroundHelper_android_background;
            if (u.r(i2)) {
                this.c = u.n(i2, -1);
                ColorStateList f = this.b.f(this.a.getContext(), this.c);
                if (f != null) {
                    h(f);
                }
            }
            int i3 = R$styleable.ViewBackgroundHelper_backgroundTint;
            if (u.r(i3)) {
                v.d1.p0(this.a, u.c(i3));
            }
            int i4 = R$styleable.ViewBackgroundHelper_backgroundTintMode;
            if (u.r(i4)) {
                v.d1.q0(this.a, o1.e(u.k(i4, -1), null));
            }
        } finally {
            u.v();
        }
    }

    public void f(Drawable drawable) {
        this.c = -1;
        h(null);
        b();
    }

    public void g(int i) {
        ColorStateList colorStateList;
        this.c = i;
        k kVar = this.b;
        if (kVar != null) {
            colorStateList = kVar.f(this.a.getContext(), i);
        } else {
            colorStateList = null;
        }
        h(colorStateList);
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new p2();
            }
            p2 p2Var = this.d;
            p2Var.a = colorStateList;
            p2Var.d = true;
        } else {
            this.d = null;
        }
        b();
    }

    public void i(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new p2();
        }
        p2 p2Var = this.e;
        p2Var.a = colorStateList;
        p2Var.d = true;
        b();
    }

    public void j(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new p2();
        }
        p2 p2Var = this.e;
        p2Var.b = mode;
        p2Var.c = true;
        b();
    }

    public final boolean k() {
        int i = Build.VERSION.SDK_INT;
        if (i > 21) {
            if (this.d != null) {
                return true;
            }
            return false;
        } else if (i == 21) {
            return true;
        } else {
            return false;
        }
    }
}
