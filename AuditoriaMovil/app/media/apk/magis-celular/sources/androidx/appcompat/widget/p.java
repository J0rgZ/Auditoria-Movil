package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.R$styleable;
/* loaded from: classes.dex */
public class p {
    public final ImageView a;
    public p2 b;
    public p2 c;
    public p2 d;

    public p(ImageView imageView) {
        this.a = imageView;
    }

    public final boolean a(Drawable drawable) {
        if (this.d == null) {
            this.d = new p2();
        }
        p2 p2Var = this.d;
        p2Var.a();
        ColorStateList a = androidx.core.widget.n.a(this.a);
        if (a != null) {
            p2Var.d = true;
            p2Var.a = a;
        }
        PorterDuff.Mode b = androidx.core.widget.n.b(this.a);
        if (b != null) {
            p2Var.c = true;
            p2Var.b = b;
        }
        if (!p2Var.d && !p2Var.c) {
            return false;
        }
        k.i(drawable, p2Var, this.a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable drawable = this.a.getDrawable();
        if (drawable != null) {
            o1.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            p2 p2Var = this.c;
            if (p2Var != null) {
                k.i(drawable, p2Var, this.a.getDrawableState());
                return;
            }
            p2 p2Var2 = this.b;
            if (p2Var2 != null) {
                k.i(drawable, p2Var2, this.a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        p2 p2Var = this.c;
        if (p2Var != null) {
            return p2Var.a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        p2 p2Var = this.c;
        if (p2Var != null) {
            return p2Var.b;
        }
        return null;
    }

    public boolean e() {
        Drawable background = this.a.getBackground();
        if (Build.VERSION.SDK_INT >= 21 && o.a(background)) {
            return false;
        }
        return true;
    }

    public void f(AttributeSet attributeSet, int i) {
        int n;
        r2 u = r2.u(this.a.getContext(), attributeSet, R$styleable.k, i, 0);
        try {
            Drawable drawable = this.a.getDrawable();
            if (drawable == null && (n = u.n(R$styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = b.b.d(this.a.getContext(), n)) != null) {
                this.a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                o1.b(drawable);
            }
            int i2 = R$styleable.AppCompatImageView_tint;
            if (u.r(i2)) {
                androidx.core.widget.n.c(this.a, u.c(i2));
            }
            int i3 = R$styleable.AppCompatImageView_tintMode;
            if (u.r(i3)) {
                androidx.core.widget.n.d(this.a, o1.e(u.k(i3, -1), null));
            }
        } finally {
            u.v();
        }
    }

    public void g(int i) {
        if (i != 0) {
            Drawable d = b.b.d(this.a.getContext(), i);
            if (d != null) {
                o1.b(d);
            }
            this.a.setImageDrawable(d);
        } else {
            this.a.setImageDrawable(null);
        }
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (this.c == null) {
            this.c = new p2();
        }
        p2 p2Var = this.c;
        p2Var.a = colorStateList;
        p2Var.d = true;
        b();
    }

    public void i(PorterDuff.Mode mode) {
        if (this.c == null) {
            this.c = new p2();
        }
        p2 p2Var = this.c;
        p2Var.b = mode;
        p2Var.c = true;
        b();
    }

    public final boolean j() {
        int i = Build.VERSION.SDK_INT;
        if (i > 21) {
            if (this.b != null) {
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
