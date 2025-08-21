package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
/* loaded from: classes.dex */
public class v2 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    public static v2 j;
    public static v2 k;
    public final View a;
    public final CharSequence b;
    public final int c;
    public final Runnable d = new a();
    public final Runnable e = new b();
    public int f;
    public int g;
    public w2 h;
    public boolean i;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            v2.this.g(false);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            v2.this.c();
        }
    }

    public v2(View view, CharSequence charSequence) {
        this.a = view;
        this.b = charSequence;
        this.c = v.q1.c(ViewConfiguration.get(view.getContext()));
        b();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void e(v2 v2Var) {
        v2 v2Var2 = j;
        if (v2Var2 != null) {
            v2Var2.a();
        }
        j = v2Var;
        if (v2Var != null) {
            v2Var.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        v2 v2Var = j;
        if (v2Var != null && v2Var.a == view) {
            e(null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            v2 v2Var2 = k;
            if (v2Var2 != null && v2Var2.a == view) {
                v2Var2.c();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        }
        new v2(view, charSequence);
    }

    public final void a() {
        this.a.removeCallbacks(this.d);
    }

    public final void b() {
        this.f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
    }

    public void c() {
        if (k == this) {
            k = null;
            w2 w2Var = this.h;
            if (w2Var != null) {
                w2Var.c();
                this.h = null;
                b();
                this.a.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (j == this) {
            e(null);
        }
        this.a.removeCallbacks(this.e);
    }

    public final void d() {
        this.a.postDelayed(this.d, ViewConfiguration.getLongPressTimeout());
    }

    public void g(boolean z) {
        long longPressTimeout;
        long j2;
        long j3;
        if (!v.d1.P(this.a)) {
            return;
        }
        e(null);
        v2 v2Var = k;
        if (v2Var != null) {
            v2Var.c();
        }
        k = this;
        this.i = z;
        w2 w2Var = new w2(this.a.getContext());
        this.h = w2Var;
        w2Var.e(this.a, this.f, this.g, this.i, this.b);
        this.a.addOnAttachStateChangeListener(this);
        if (this.i) {
            j3 = 2500;
        } else {
            if ((v.d1.J(this.a) & 1) == 1) {
                longPressTimeout = ViewConfiguration.getLongPressTimeout();
                j2 = 3000;
            } else {
                longPressTimeout = ViewConfiguration.getLongPressTimeout();
                j2 = 15000;
            }
            j3 = j2 - longPressTimeout;
        }
        this.a.removeCallbacks(this.e);
        this.a.postDelayed(this.e, j3);
    }

    public final boolean h(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.f) <= this.c && Math.abs(y - this.g) <= this.c) {
            return false;
        }
        this.f = x;
        this.g = y;
        return true;
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.h != null && this.i) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.a.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.a.isEnabled() && this.h == null && h(motionEvent)) {
            e(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.f = view.getWidth() / 2;
        this.g = view.getHeight() / 2;
        g(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        c();
    }
}
