package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.d2;
import v.d1;
/* loaded from: classes.dex */
public final class q extends k implements PopupWindow.OnDismissListener, View.OnKeyListener {
    public static final int v = R$layout.abc_popup_menu_item_layout;
    public final Context b;
    public final g c;
    public final f d;
    public final boolean e;
    public final int f;
    public final int g;
    public final int h;
    public final d2 i;
    public PopupWindow.OnDismissListener l;
    public View m;
    public View n;
    public m.a o;
    public ViewTreeObserver p;
    public boolean q;
    public boolean r;

    /* renamed from: s  reason: collision with root package name */
    public int f10s;
    public boolean u;
    public final ViewTreeObserver.OnGlobalLayoutListener j = new a();
    public final View.OnAttachStateChangeListener k = new b();
    public int t = 0;

    /* loaded from: classes.dex */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (q.this.isShowing() && !q.this.i.u()) {
                View view = q.this.n;
                if (view != null && view.isShown()) {
                    q.this.i.show();
                } else {
                    q.this.dismiss();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements View.OnAttachStateChangeListener {
        public b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = q.this.p;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    q.this.p = view.getViewTreeObserver();
                }
                q qVar = q.this;
                qVar.p.removeGlobalOnLayoutListener(qVar.j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public q(Context context, g gVar, View view, int i, int i2, boolean z) {
        this.b = context;
        this.c = gVar;
        this.e = z;
        this.d = new f(gVar, LayoutInflater.from(context), z, v);
        this.g = i;
        this.h = i2;
        Resources resources = context.getResources();
        this.f = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.m = view;
        this.i = new d2(context, null, i, i2);
        gVar.addMenuPresenter(this, context);
    }

    @Override // androidx.appcompat.view.menu.k
    public void a(g gVar) {
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        if (isShowing()) {
            this.i.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void e(View view) {
        this.m = view;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView g() {
        return this.i.g();
    }

    @Override // androidx.appcompat.view.menu.k
    public void h(boolean z) {
        this.d.d(z);
    }

    @Override // androidx.appcompat.view.menu.k
    public void i(int i) {
        this.t = i;
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean isShowing() {
        if (!this.q && this.i.isShowing()) {
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.k
    public void j(int i) {
        this.i.c(i);
    }

    @Override // androidx.appcompat.view.menu.k
    public void k(PopupWindow.OnDismissListener onDismissListener) {
        this.l = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.k
    public void l(boolean z) {
        this.u = z;
    }

    @Override // androidx.appcompat.view.menu.k
    public void m(int i) {
        this.i.i(i);
    }

    @Override // androidx.appcompat.view.menu.m
    public void onCloseMenu(g gVar, boolean z) {
        if (gVar != this.c) {
            return;
        }
        dismiss();
        m.a aVar = this.o;
        if (aVar != null) {
            aVar.onCloseMenu(gVar, z);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.q = true;
        this.c.close();
        ViewTreeObserver viewTreeObserver = this.p;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.p = this.n.getViewTreeObserver();
            }
            this.p.removeGlobalOnLayoutListener(this.j);
            this.p = null;
        }
        this.n.removeOnAttachStateChangeListener(this.k);
        PopupWindow.OnDismissListener onDismissListener = this.l;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 && i == 82) {
            dismiss();
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable onSaveInstanceState() {
        return null;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean onSubMenuSelected(r rVar) {
        if (rVar.hasVisibleItems()) {
            l lVar = new l(this.b, rVar, this.n, this.e, this.g, this.h);
            lVar.j(this.o);
            lVar.g(k.n(rVar));
            lVar.i(this.l);
            this.l = null;
            this.c.close(false);
            int a2 = this.i.a();
            int l = this.i.l();
            if ((Gravity.getAbsoluteGravity(this.t, d1.z(this.m)) & 7) == 5) {
                a2 += this.m.getWidth();
            }
            if (lVar.n(a2, l)) {
                m.a aVar = this.o;
                if (aVar != null) {
                    aVar.a(rVar);
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    public final boolean p() {
        View view;
        boolean z;
        if (isShowing()) {
            return true;
        }
        if (this.q || (view = this.m) == null) {
            return false;
        }
        this.n = view;
        this.i.D(this);
        this.i.E(this);
        this.i.C(true);
        View view2 = this.n;
        if (this.p == null) {
            z = true;
        } else {
            z = false;
        }
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.p = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.j);
        }
        view2.addOnAttachStateChangeListener(this.k);
        this.i.w(view2);
        this.i.z(this.t);
        if (!this.r) {
            this.f10s = k.d(this.d, null, this.b, this.f);
            this.r = true;
        }
        this.i.y(this.f10s);
        this.i.B(2);
        this.i.A(c());
        this.i.show();
        ListView g = this.i.g();
        g.setOnKeyListener(this);
        if (this.u && this.c.getHeaderTitle() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.b).inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) g, false);
            TextView textView = (TextView) frameLayout.findViewById(16908310);
            if (textView != null) {
                textView.setText(this.c.getHeaderTitle());
            }
            frameLayout.setEnabled(false);
            g.addHeaderView(frameLayout, null, false);
        }
        this.i.m(this.d);
        this.i.show();
        return true;
    }

    @Override // androidx.appcompat.view.menu.m
    public void setCallback(m.a aVar) {
        this.o = aVar;
    }

    @Override // androidx.appcompat.view.menu.p
    public void show() {
        if (p()) {
            return;
        }
        throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
    }

    @Override // androidx.appcompat.view.menu.m
    public void updateMenuView(boolean z) {
        this.r = false;
        f fVar = this.d;
        if (fVar != null) {
            fVar.notifyDataSetChanged();
        }
    }
}
