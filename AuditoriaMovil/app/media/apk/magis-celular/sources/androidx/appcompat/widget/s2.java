package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$id;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
/* loaded from: classes.dex */
public class s2 implements i1 {
    public Toolbar a;
    public int b;
    public View c;
    public View d;
    public Drawable e;
    public Drawable f;
    public Drawable g;
    public boolean h;
    public CharSequence i;
    public CharSequence j;
    public CharSequence k;
    public Window.Callback l;
    public boolean m;
    public d n;
    public int o;
    public int p;
    public Drawable q;

    /* loaded from: classes.dex */
    public class a implements View.OnClickListener {
        public final androidx.appcompat.view.menu.a a;

        public a() {
            this.a = new androidx.appcompat.view.menu.a(s2.this.a.getContext(), 0, 16908332, 0, 0, s2.this.i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            s2 s2Var = s2.this;
            Window.Callback callback = s2Var.l;
            if (callback != null && s2Var.m) {
                callback.onMenuItemSelected(0, this.a);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends v.d2 {
        public boolean a = false;
        public final /* synthetic */ int b;

        public b(int i) {
            this.b = i;
        }

        public void a(View view) {
            this.a = true;
        }

        public void b(View view) {
            if (!this.a) {
                s2.this.a.setVisibility(this.b);
            }
        }

        public void c(View view) {
            s2.this.a.setVisibility(0);
        }
    }

    public s2(Toolbar toolbar, boolean z) {
        this(toolbar, z, R$string.abc_action_bar_up_description, R$drawable.abc_ic_ab_back_material);
    }

    public void A(View view) {
        View view2 = this.d;
        if (view2 != null && (this.b & 16) != 0) {
            this.a.removeView(view2);
        }
        this.d = view;
        if (view != null && (this.b & 16) != 0) {
            this.a.addView(view);
        }
    }

    public void B(int i) {
        if (i == this.p) {
            return;
        }
        this.p = i;
        if (TextUtils.isEmpty(this.a.getNavigationContentDescription())) {
            D(this.p);
        }
    }

    public void C(Drawable drawable) {
        this.f = drawable;
        J();
    }

    public void D(int i) {
        String string;
        if (i == 0) {
            string = null;
        } else {
            string = getContext().getString(i);
        }
        E(string);
    }

    public void E(CharSequence charSequence) {
        this.k = charSequence;
        H();
    }

    public void F(Drawable drawable) {
        this.g = drawable;
        I();
    }

    public final void G(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.b & 8) != 0) {
            this.a.setTitle(charSequence);
        }
    }

    public final void H() {
        if ((this.b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.a.setNavigationContentDescription(this.p);
            } else {
                this.a.setNavigationContentDescription(this.k);
            }
        }
    }

    public final void I() {
        if ((this.b & 4) != 0) {
            Toolbar toolbar = this.a;
            Drawable drawable = this.g;
            if (drawable == null) {
                drawable = this.q;
            }
            toolbar.setNavigationIcon(drawable);
            return;
        }
        this.a.setNavigationIcon((Drawable) null);
    }

    public final void J() {
        Drawable drawable;
        int i = this.b;
        if ((i & 2) != 0) {
            if ((i & 1) != 0) {
                drawable = this.f;
                if (drawable == null) {
                    drawable = this.e;
                }
            } else {
                drawable = this.e;
            }
        } else {
            drawable = null;
        }
        this.a.setLogo(drawable);
    }

    @Override // androidx.appcompat.widget.i1
    public void a(Menu menu, m.a aVar) {
        if (this.n == null) {
            d dVar = new d(this.a.getContext());
            this.n = dVar;
            dVar.h(R$id.action_menu_presenter);
        }
        this.n.setCallback(aVar);
        this.a.setMenu((androidx.appcompat.view.menu.g) menu, this.n);
    }

    @Override // androidx.appcompat.widget.i1
    public boolean b() {
        return this.a.isOverflowMenuShowing();
    }

    @Override // androidx.appcompat.widget.i1
    public void c() {
        this.m = true;
    }

    @Override // androidx.appcompat.widget.i1
    public void collapseActionView() {
        this.a.collapseActionView();
    }

    @Override // androidx.appcompat.widget.i1
    public boolean d() {
        return this.a.canShowOverflowMenu();
    }

    @Override // androidx.appcompat.widget.i1
    public boolean e() {
        return this.a.isOverflowMenuShowPending();
    }

    @Override // androidx.appcompat.widget.i1
    public boolean f() {
        return this.a.hideOverflowMenu();
    }

    @Override // androidx.appcompat.widget.i1
    public boolean g() {
        return this.a.showOverflowMenu();
    }

    @Override // androidx.appcompat.widget.i1
    public Context getContext() {
        return this.a.getContext();
    }

    @Override // androidx.appcompat.widget.i1
    public CharSequence getTitle() {
        return this.a.getTitle();
    }

    @Override // androidx.appcompat.widget.i1
    public void h() {
        this.a.dismissPopupMenus();
    }

    @Override // androidx.appcompat.widget.i1
    public void i(j2 j2Var) {
        View view = this.c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.a;
            if (parent == toolbar) {
                toolbar.removeView(this.c);
            }
        }
        this.c = j2Var;
    }

    @Override // androidx.appcompat.widget.i1
    public boolean j() {
        return this.a.hasExpandedActionView();
    }

    @Override // androidx.appcompat.widget.i1
    public void k(int i) {
        View view;
        int i2 = this.b ^ i;
        this.b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    H();
                }
                I();
            }
            if ((i2 & 3) != 0) {
                J();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.a.setTitle(this.i);
                    this.a.setSubtitle(this.j);
                } else {
                    this.a.setTitle((CharSequence) null);
                    this.a.setSubtitle((CharSequence) null);
                }
            }
            if ((i2 & 16) != 0 && (view = this.d) != null) {
                if ((i & 16) != 0) {
                    this.a.addView(view);
                } else {
                    this.a.removeView(view);
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.i1
    public void l(CharSequence charSequence) {
        this.j = charSequence;
        if ((this.b & 8) != 0) {
            this.a.setSubtitle(charSequence);
        }
    }

    @Override // androidx.appcompat.widget.i1
    public Menu m() {
        return this.a.getMenu();
    }

    @Override // androidx.appcompat.widget.i1
    public void n(int i) {
        Drawable drawable;
        if (i != 0) {
            drawable = b.b.d(getContext(), i);
        } else {
            drawable = null;
        }
        C(drawable);
    }

    @Override // androidx.appcompat.widget.i1
    public int o() {
        return this.o;
    }

    @Override // androidx.appcompat.widget.i1
    public v.b2 p(int i, long j) {
        float f;
        v.b2 c = v.d1.c(this.a);
        if (i == 0) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        return c.a(f).d(j).f(new b(i));
    }

    @Override // androidx.appcompat.widget.i1
    public void q(int i) {
        Drawable drawable;
        if (i != 0) {
            drawable = b.b.d(getContext(), i);
        } else {
            drawable = null;
        }
        F(drawable);
    }

    @Override // androidx.appcompat.widget.i1
    public void r(m.a aVar, g.a aVar2) {
        this.a.setMenuCallbacks(aVar, aVar2);
    }

    @Override // androidx.appcompat.widget.i1
    public void s(int i) {
        this.a.setVisibility(i);
    }

    @Override // androidx.appcompat.widget.i1
    public void setIcon(int i) {
        setIcon(i != 0 ? b.b.d(getContext(), i) : null);
    }

    @Override // androidx.appcompat.widget.i1
    public void setTitle(CharSequence charSequence) {
        this.h = true;
        G(charSequence);
    }

    @Override // androidx.appcompat.widget.i1
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // androidx.appcompat.widget.i1
    public void setWindowTitle(CharSequence charSequence) {
        if (!this.h) {
            G(charSequence);
        }
    }

    @Override // androidx.appcompat.widget.i1
    public ViewGroup t() {
        return this.a;
    }

    @Override // androidx.appcompat.widget.i1
    public void u(boolean z) {
    }

    @Override // androidx.appcompat.widget.i1
    public int v() {
        return this.b;
    }

    @Override // androidx.appcompat.widget.i1
    public void w() {
    }

    @Override // androidx.appcompat.widget.i1
    public void x() {
    }

    @Override // androidx.appcompat.widget.i1
    public void y(boolean z) {
        this.a.setCollapsible(z);
    }

    public final int z() {
        if (this.a.getNavigationIcon() != null) {
            this.q = this.a.getNavigationIcon();
            return 15;
        }
        return 11;
    }

    public s2(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        r2 u = r2.u(toolbar.getContext(), null, R$styleable.a, R$attr.actionBarStyle, 0);
        this.q = u.g(R$styleable.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence p = u.p(R$styleable.ActionBar_title);
            if (!TextUtils.isEmpty(p)) {
                setTitle(p);
            }
            CharSequence p2 = u.p(R$styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(p2)) {
                l(p2);
            }
            Drawable g = u.g(R$styleable.ActionBar_logo);
            if (g != null) {
                C(g);
            }
            Drawable g2 = u.g(R$styleable.ActionBar_icon);
            if (g2 != null) {
                setIcon(g2);
            }
            if (this.g == null && (drawable = this.q) != null) {
                F(drawable);
            }
            k(u.k(R$styleable.ActionBar_displayOptions, 0));
            int n = u.n(R$styleable.ActionBar_customNavigationLayout, 0);
            if (n != 0) {
                A(LayoutInflater.from(this.a.getContext()).inflate(n, (ViewGroup) this.a, false));
                k(this.b | 16);
            }
            int m = u.m(R$styleable.ActionBar_height, 0);
            if (m > 0) {
                ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
                layoutParams.height = m;
                this.a.setLayoutParams(layoutParams);
            }
            int e = u.e(R$styleable.ActionBar_contentInsetStart, -1);
            int e2 = u.e(R$styleable.ActionBar_contentInsetEnd, -1);
            if (e >= 0 || e2 >= 0) {
                this.a.setContentInsetsRelative(Math.max(e, 0), Math.max(e2, 0));
            }
            int n2 = u.n(R$styleable.ActionBar_titleTextStyle, 0);
            if (n2 != 0) {
                Toolbar toolbar2 = this.a;
                toolbar2.setTitleTextAppearance(toolbar2.getContext(), n2);
            }
            int n3 = u.n(R$styleable.ActionBar_subtitleTextStyle, 0);
            if (n3 != 0) {
                Toolbar toolbar3 = this.a;
                toolbar3.setSubtitleTextAppearance(toolbar3.getContext(), n3);
            }
            int n4 = u.n(R$styleable.ActionBar_popupTheme, 0);
            if (n4 != 0) {
                this.a.setPopupTheme(n4);
            }
        } else {
            this.b = z();
        }
        u.v();
        B(i);
        this.k = this.a.getNavigationContentDescription();
        this.a.setNavigationOnClickListener(new a());
    }

    @Override // androidx.appcompat.widget.i1
    public void setIcon(Drawable drawable) {
        this.e = drawable;
        J();
    }
}
