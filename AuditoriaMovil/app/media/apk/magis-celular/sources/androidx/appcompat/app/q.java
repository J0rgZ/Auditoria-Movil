package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.i1;
import e.b;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import v.b2;
import v.c2;
import v.d1;
import v.d2;
import v.e2;
/* loaded from: classes.dex */
public class q extends androidx.appcompat.app.a implements ActionBarOverlayLayout.d {
    public static final Interpolator E = new AccelerateInterpolator();
    public static final Interpolator F = new DecelerateInterpolator();
    public boolean A;
    public Context a;
    public Context b;
    public Activity c;
    public Dialog d;
    public ActionBarOverlayLayout e;
    public ActionBarContainer f;
    public i1 g;
    public ActionBarContextView h;
    public View i;
    public boolean l;
    public d m;
    public e.b n;
    public b.a o;
    public boolean p;
    public boolean r;
    public boolean u;
    public boolean v;
    public boolean w;
    public e.h y;
    public boolean z;
    public ArrayList j = new ArrayList();
    public int k = -1;
    public ArrayList q = new ArrayList();

    /* renamed from: s  reason: collision with root package name */
    public int f6s = 0;
    public boolean t = true;
    public boolean x = true;
    public final c2 B = new a();
    public final c2 C = new b();
    public final e2 D = new c();

    /* loaded from: classes.dex */
    public class a extends d2 {
        public a() {
        }

        public void b(View view) {
            View view2;
            q qVar = q.this;
            if (qVar.t && (view2 = qVar.i) != null) {
                view2.setTranslationY(0.0f);
                q.this.f.setTranslationY(0.0f);
            }
            q.this.f.setVisibility(8);
            q.this.f.setTransitioning(false);
            q qVar2 = q.this;
            qVar2.y = null;
            qVar2.B();
            ActionBarOverlayLayout actionBarOverlayLayout = q.this.e;
            if (actionBarOverlayLayout != null) {
                d1.h0(actionBarOverlayLayout);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends d2 {
        public b() {
        }

        public void b(View view) {
            q qVar = q.this;
            qVar.y = null;
            qVar.f.requestLayout();
        }
    }

    /* loaded from: classes.dex */
    public class c implements e2 {
        public c() {
        }

        public void a(View view) {
            ((View) q.this.f.getParent()).invalidate();
        }
    }

    /* loaded from: classes.dex */
    public class d extends e.b implements g.a {
        public final Context c;
        public final androidx.appcompat.view.menu.g d;
        public b.a e;
        public WeakReference f;

        public d(Context context, b.a aVar) {
            this.c = context;
            this.e = aVar;
            androidx.appcompat.view.menu.g defaultShowAsAction = new androidx.appcompat.view.menu.g(context).setDefaultShowAsAction(1);
            this.d = defaultShowAsAction;
            defaultShowAsAction.setCallback(this);
        }

        public void a() {
            q qVar = q.this;
            if (qVar.m != this) {
                return;
            }
            if (!q.A(qVar.u, qVar.v, false)) {
                q qVar2 = q.this;
                qVar2.n = this;
                qVar2.o = this.e;
            } else {
                this.e.a(this);
            }
            this.e = null;
            q.this.z(false);
            q.this.h.g();
            q.this.g.t().sendAccessibilityEvent(32);
            q qVar3 = q.this;
            qVar3.e.setHideOnContentScrollEnabled(qVar3.A);
            q.this.m = null;
        }

        public View b() {
            WeakReference weakReference = this.f;
            if (weakReference != null) {
                return (View) weakReference.get();
            }
            return null;
        }

        public Menu c() {
            return this.d;
        }

        public MenuInflater d() {
            return new e.g(this.c);
        }

        public CharSequence e() {
            return q.this.h.getSubtitle();
        }

        public CharSequence g() {
            return q.this.h.getTitle();
        }

        public void i() {
            if (q.this.m != this) {
                return;
            }
            this.d.stopDispatchingItemsChanged();
            try {
                this.e.c(this, this.d);
            } finally {
                this.d.startDispatchingItemsChanged();
            }
        }

        public boolean j() {
            return q.this.h.j();
        }

        public void k(View view) {
            q.this.h.setCustomView(view);
            this.f = new WeakReference(view);
        }

        public void l(int i) {
            m(q.this.a.getResources().getString(i));
        }

        public void m(CharSequence charSequence) {
            q.this.h.setSubtitle(charSequence);
        }

        public void o(int i) {
            p(q.this.a.getResources().getString(i));
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean onMenuItemSelected(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            b.a aVar = this.e;
            if (aVar != null) {
                return aVar.d(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void onMenuModeChange(androidx.appcompat.view.menu.g gVar) {
            if (this.e == null) {
                return;
            }
            i();
            q.this.h.l();
        }

        public void p(CharSequence charSequence) {
            q.this.h.setTitle(charSequence);
        }

        public void q(boolean z) {
            super.q(z);
            q.this.h.setTitleOptional(z);
        }

        public boolean r() {
            this.d.stopDispatchingItemsChanged();
            try {
                return this.e.b(this, this.d);
            } finally {
                this.d.startDispatchingItemsChanged();
            }
        }
    }

    public q(Activity activity, boolean z) {
        this.c = activity;
        View decorView = activity.getWindow().getDecorView();
        H(decorView);
        if (z) {
            return;
        }
        this.i = decorView.findViewById(16908290);
    }

    public static boolean A(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return (z || z2) ? false : true;
    }

    public void B() {
        b.a aVar = this.o;
        if (aVar != null) {
            aVar.a(this.n);
            this.n = null;
            this.o = null;
        }
    }

    public void C(boolean z) {
        View view;
        int[] iArr;
        e.h hVar = this.y;
        if (hVar != null) {
            hVar.a();
        }
        if (this.f6s == 0 && (this.z || z)) {
            this.f.setAlpha(1.0f);
            this.f.setTransitioning(true);
            e.h hVar2 = new e.h();
            float f = -this.f.getHeight();
            if (z) {
                this.f.getLocationInWindow(new int[]{0, 0});
                f -= iArr[1];
            }
            b2 k = d1.c(this.f).k(f);
            k.i(this.D);
            hVar2.c(k);
            if (this.t && (view = this.i) != null) {
                hVar2.c(d1.c(view).k(f));
            }
            hVar2.f(E);
            hVar2.e(250L);
            hVar2.g(this.B);
            this.y = hVar2;
            hVar2.h();
            return;
        }
        this.B.b((View) null);
    }

    public void D(boolean z) {
        View view;
        View view2;
        int[] iArr;
        e.h hVar = this.y;
        if (hVar != null) {
            hVar.a();
        }
        this.f.setVisibility(0);
        if (this.f6s == 0 && (this.z || z)) {
            this.f.setTranslationY(0.0f);
            float f = -this.f.getHeight();
            if (z) {
                this.f.getLocationInWindow(new int[]{0, 0});
                f -= iArr[1];
            }
            this.f.setTranslationY(f);
            e.h hVar2 = new e.h();
            b2 k = d1.c(this.f).k(0.0f);
            k.i(this.D);
            hVar2.c(k);
            if (this.t && (view2 = this.i) != null) {
                view2.setTranslationY(f);
                hVar2.c(d1.c(this.i).k(0.0f));
            }
            hVar2.f(F);
            hVar2.e(250L);
            hVar2.g(this.C);
            this.y = hVar2;
            hVar2.h();
        } else {
            this.f.setAlpha(1.0f);
            this.f.setTranslationY(0.0f);
            if (this.t && (view = this.i) != null) {
                view.setTranslationY(0.0f);
            }
            this.C.b((View) null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.e;
        if (actionBarOverlayLayout != null) {
            d1.h0(actionBarOverlayLayout);
        }
    }

    public final i1 E(View view) {
        String str;
        if (view instanceof i1) {
            return (i1) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        if (view != null) {
            str = view.getClass().getSimpleName();
        } else {
            str = "null";
        }
        sb.append(str);
        throw new IllegalStateException(sb.toString());
    }

    public int F() {
        return this.g.o();
    }

    public final void G() {
        if (this.w) {
            this.w = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.e;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            P(false);
        }
    }

    public final void H(View view) {
        boolean z;
        boolean z2;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R$id.decor_content_parent);
        this.e = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.g = E(view.findViewById(R$id.action_bar));
        this.h = (ActionBarContextView) view.findViewById(R$id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(R$id.action_bar_container);
        this.f = actionBarContainer;
        i1 i1Var = this.g;
        if (i1Var != null && this.h != null && actionBarContainer != null) {
            this.a = i1Var.getContext();
            if ((this.g.v() & 4) != 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                this.l = true;
            }
            e.a b2 = e.a.b(this.a);
            if (!b2.a() && !z) {
                z2 = false;
            } else {
                z2 = true;
            }
            M(z2);
            K(b2.e());
            TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(null, R$styleable.a, R$attr.actionBarStyle, 0);
            if (obtainStyledAttributes.getBoolean(R$styleable.ActionBar_hideOnContentScroll, false)) {
                L(true);
            }
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionBar_elevation, 0);
            if (dimensionPixelSize != 0) {
                J(dimensionPixelSize);
            }
            obtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
    }

    public void I(int i, int i2) {
        int v = this.g.v();
        if ((i2 & 4) != 0) {
            this.l = true;
        }
        this.g.k((i & i2) | ((i2 ^ (-1)) & v));
    }

    public void J(float f) {
        d1.s0(this.f, f);
    }

    public final void K(boolean z) {
        boolean z2;
        boolean z3;
        this.r = z;
        if (!z) {
            this.g.i(null);
            this.f.setTabContainer(null);
        } else {
            this.f.setTabContainer(null);
            this.g.i(null);
        }
        boolean z4 = true;
        if (F() == 2) {
            z2 = true;
        } else {
            z2 = false;
        }
        i1 i1Var = this.g;
        if (!this.r && z2) {
            z3 = true;
        } else {
            z3 = false;
        }
        i1Var.y(z3);
        this.e.setHasNonEmbeddedTabs((this.r || !z2) ? false : false);
    }

    public void L(boolean z) {
        if (z && !this.e.q()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.A = z;
        this.e.setHideOnContentScrollEnabled(z);
    }

    public void M(boolean z) {
        this.g.u(z);
    }

    public final boolean N() {
        return d1.Q(this.f);
    }

    public final void O() {
        if (!this.w) {
            this.w = true;
            ActionBarOverlayLayout actionBarOverlayLayout = this.e;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(true);
            }
            P(false);
        }
    }

    public final void P(boolean z) {
        if (A(this.u, this.v, this.w)) {
            if (!this.x) {
                this.x = true;
                D(z);
            }
        } else if (this.x) {
            this.x = false;
            C(z);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a() {
        if (this.v) {
            this.v = false;
            P(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void b() {
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void c(boolean z) {
        this.t = z;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d() {
        if (!this.v) {
            this.v = true;
            P(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void e() {
        e.h hVar = this.y;
        if (hVar != null) {
            hVar.a();
            this.y = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void f(int i) {
        this.f6s = i;
    }

    @Override // androidx.appcompat.app.a
    public boolean h() {
        i1 i1Var = this.g;
        if (i1Var != null && i1Var.j()) {
            this.g.collapseActionView();
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.a
    public void i(boolean z) {
        if (z == this.p) {
            return;
        }
        this.p = z;
        if (this.q.size() <= 0) {
            return;
        }
        m.a(this.q.get(0));
        throw null;
    }

    @Override // androidx.appcompat.app.a
    public int j() {
        return this.g.v();
    }

    @Override // androidx.appcompat.app.a
    public Context k() {
        if (this.b == null) {
            TypedValue typedValue = new TypedValue();
            this.a.getTheme().resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.b = new ContextThemeWrapper(this.a, i);
            } else {
                this.b = this.a;
            }
        }
        return this.b;
    }

    @Override // androidx.appcompat.app.a
    public void m(Configuration configuration) {
        K(e.a.b(this.a).e());
    }

    @Override // androidx.appcompat.app.a
    public boolean o(int i, KeyEvent keyEvent) {
        Menu c2;
        int i2;
        d dVar = this.m;
        if (dVar == null || (c2 = dVar.c()) == null) {
            return false;
        }
        if (keyEvent != null) {
            i2 = keyEvent.getDeviceId();
        } else {
            i2 = -1;
        }
        boolean z = true;
        if (KeyCharacterMap.load(i2).getKeyboardType() == 1) {
            z = false;
        }
        c2.setQwertyMode(z);
        return c2.performShortcut(i, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.a
    public void r(boolean z) {
        if (!this.l) {
            s(z);
        }
    }

    @Override // androidx.appcompat.app.a
    public void s(boolean z) {
        int i;
        if (z) {
            i = 4;
        } else {
            i = 0;
        }
        I(i, 4);
    }

    @Override // androidx.appcompat.app.a
    public void t(int i) {
        this.g.q(i);
    }

    @Override // androidx.appcompat.app.a
    public void u(boolean z) {
        e.h hVar;
        this.z = z;
        if (!z && (hVar = this.y) != null) {
            hVar.a();
        }
    }

    @Override // androidx.appcompat.app.a
    public void v(CharSequence charSequence) {
        this.g.l(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void w(CharSequence charSequence) {
        this.g.setTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void x(CharSequence charSequence) {
        this.g.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public e.b y(b.a aVar) {
        d dVar = this.m;
        if (dVar != null) {
            dVar.a();
        }
        this.e.setHideOnContentScrollEnabled(false);
        this.h.k();
        d dVar2 = new d(this.h.getContext(), aVar);
        if (dVar2.r()) {
            this.m = dVar2;
            dVar2.i();
            this.h.h(dVar2);
            z(true);
            this.h.sendAccessibilityEvent(32);
            return dVar2;
        }
        return null;
    }

    public void z(boolean z) {
        b2 p;
        b2 f;
        if (z) {
            O();
        } else {
            G();
        }
        if (N()) {
            if (z) {
                f = this.g.p(4, 100L);
                p = this.h.f(0, 200L);
            } else {
                p = this.g.p(0, 200L);
                f = this.h.f(8, 100L);
            }
            e.h hVar = new e.h();
            hVar.d(f, p);
            hVar.h();
        } else if (z) {
            this.g.s(4);
            this.h.setVisibility(0);
        } else {
            this.g.s(0);
            this.h.setVisibility(8);
        }
    }

    public q(Dialog dialog) {
        this.d = dialog;
        H(dialog.getWindow().getDecorView());
    }
}
