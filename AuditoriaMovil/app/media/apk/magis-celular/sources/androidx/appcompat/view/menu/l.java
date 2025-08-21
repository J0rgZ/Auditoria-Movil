package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.R$dimen;
import androidx.appcompat.view.menu.m;
import v.d1;
/* loaded from: classes.dex */
public class l {
    public final Context a;
    public final g b;
    public final boolean c;
    public final int d;
    public final int e;
    public View f;
    public int g;
    public boolean h;
    public m.a i;
    public k j;
    public PopupWindow.OnDismissListener k;
    public final PopupWindow.OnDismissListener l;

    /* loaded from: classes.dex */
    public class a implements PopupWindow.OnDismissListener {
        public a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            l.this.e();
        }
    }

    public l(Context context, g gVar, View view, boolean z, int i) {
        this(context, gVar, view, z, i, 0);
    }

    public final k a() {
        boolean z;
        k qVar;
        Display defaultDisplay = ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        if (Math.min(point.x, point.y) >= this.a.getResources().getDimensionPixelSize(R$dimen.abc_cascading_menus_min_smallest_width)) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            qVar = new d(this.a, this.f, this.d, this.e, this.c);
        } else {
            qVar = new q(this.a, this.b, this.f, this.d, this.e, this.c);
        }
        qVar.a(this.b);
        qVar.k(this.l);
        qVar.e(this.f);
        qVar.setCallback(this.i);
        qVar.h(this.h);
        qVar.i(this.g);
        return qVar;
    }

    public void b() {
        if (d()) {
            this.j.dismiss();
        }
    }

    public k c() {
        if (this.j == null) {
            this.j = a();
        }
        return this.j;
    }

    public boolean d() {
        k kVar = this.j;
        if (kVar != null && kVar.isShowing()) {
            return true;
        }
        return false;
    }

    public void e() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void f(View view) {
        this.f = view;
    }

    public void g(boolean z) {
        this.h = z;
        k kVar = this.j;
        if (kVar != null) {
            kVar.h(z);
        }
    }

    public void h(int i) {
        this.g = i;
    }

    public void i(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public void j(m.a aVar) {
        this.i = aVar;
        k kVar = this.j;
        if (kVar != null) {
            kVar.setCallback(aVar);
        }
    }

    public void k() {
        if (m()) {
            return;
        }
        throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
    }

    public final void l(int i, int i2, boolean z, boolean z2) {
        k c = c();
        c.l(z2);
        if (z) {
            if ((v.j.b(this.g, d1.z(this.f)) & 7) == 5) {
                i -= this.f.getWidth();
            }
            c.j(i);
            c.m(i2);
            int i3 = (int) ((this.a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            c.f(new Rect(i - i3, i2 - i3, i + i3, i2 + i3));
        }
        c.show();
    }

    public boolean m() {
        if (d()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i, int i2) {
        if (d()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        l(i, i2, true, true);
        return true;
    }

    public l(Context context, g gVar, View view, boolean z, int i, int i2) {
        this.g = 8388611;
        this.l = new a();
        this.a = context;
        this.b = gVar;
        this.f = view;
        this.c = z;
        this.d = i;
        this.e = i2;
    }
}
