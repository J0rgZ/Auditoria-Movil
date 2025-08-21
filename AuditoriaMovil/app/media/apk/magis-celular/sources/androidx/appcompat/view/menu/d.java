package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.d2;
import androidx.appcompat.widget.z1;
import java.util.ArrayList;
import java.util.List;
import v.d1;
/* loaded from: classes.dex */
public final class d extends k implements View.OnKeyListener, PopupWindow.OnDismissListener {
    public static final int B = R$layout.abc_cascading_menu_item_layout;
    public boolean A;
    public final Context b;
    public final int c;
    public final int d;
    public final int e;
    public final boolean f;
    public final Handler g;
    public View o;
    public View p;
    public boolean r;

    /* renamed from: s  reason: collision with root package name */
    public boolean f8s;
    public int t;
    public int u;
    public boolean w;
    public m.a x;
    public ViewTreeObserver y;
    public PopupWindow.OnDismissListener z;
    public final List h = new ArrayList();
    public final List i = new ArrayList();
    public final ViewTreeObserver.OnGlobalLayoutListener j = new a();
    public final View.OnAttachStateChangeListener k = new b();
    public final z1 l = new c();
    public int m = 0;
    public int n = 0;
    public boolean v = false;
    public int q = t();

    /* loaded from: classes.dex */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (d.this.isShowing() && d.this.i.size() > 0 && !((C0007d) d.this.i.get(0)).a.u()) {
                View view = d.this.p;
                if (view != null && view.isShown()) {
                    for (C0007d c0007d : d.this.i) {
                        c0007d.a.show();
                    }
                    return;
                }
                d.this.dismiss();
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
            ViewTreeObserver viewTreeObserver = d.this.y;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    d.this.y = view.getViewTreeObserver();
                }
                d dVar = d.this;
                dVar.y.removeGlobalOnLayoutListener(dVar.j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* loaded from: classes.dex */
    public class c implements z1 {

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ C0007d a;
            public final /* synthetic */ MenuItem b;
            public final /* synthetic */ g c;

            public a(C0007d c0007d, MenuItem menuItem, g gVar) {
                this.a = c0007d;
                this.b = menuItem;
                this.c = gVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0007d c0007d = this.a;
                if (c0007d != null) {
                    d.this.A = true;
                    c0007d.b.close(false);
                    d.this.A = false;
                }
                if (this.b.isEnabled() && this.b.hasSubMenu()) {
                    this.c.performItemAction(this.b, 4);
                }
            }
        }

        public c() {
        }

        @Override // androidx.appcompat.widget.z1
        public void b(g gVar, MenuItem menuItem) {
            C0007d c0007d = null;
            d.this.g.removeCallbacksAndMessages(null);
            int size = d.this.i.size();
            int i = 0;
            while (true) {
                if (i < size) {
                    if (gVar == ((C0007d) d.this.i.get(i)).b) {
                        break;
                    }
                    i++;
                } else {
                    i = -1;
                    break;
                }
            }
            if (i == -1) {
                return;
            }
            int i2 = i + 1;
            if (i2 < d.this.i.size()) {
                c0007d = (C0007d) d.this.i.get(i2);
            }
            d.this.g.postAtTime(new a(c0007d, menuItem, gVar), gVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.z1
        public void e(g gVar, MenuItem menuItem) {
            d.this.g.removeCallbacksAndMessages(gVar);
        }
    }

    /* renamed from: androidx.appcompat.view.menu.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0007d {
        public final d2 a;
        public final g b;
        public final int c;

        public C0007d(d2 d2Var, g gVar, int i) {
            this.a = d2Var;
            this.b = gVar;
            this.c = i;
        }

        public ListView a() {
            return this.a.g();
        }
    }

    public d(Context context, View view, int i, int i2, boolean z) {
        this.b = context;
        this.o = view;
        this.d = i;
        this.e = i2;
        this.f = z;
        Resources resources = context.getResources();
        this.c = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.g = new Handler();
    }

    @Override // androidx.appcompat.view.menu.k
    public void a(g gVar) {
        gVar.addMenuPresenter(this, this.b);
        if (isShowing()) {
            v(gVar);
        } else {
            this.h.add(gVar);
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public boolean b() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        int size = this.i.size();
        if (size > 0) {
            C0007d[] c0007dArr = (C0007d[]) this.i.toArray(new C0007d[size]);
            for (int i = size - 1; i >= 0; i--) {
                C0007d c0007d = c0007dArr[i];
                if (c0007d.a.isShowing()) {
                    c0007d.a.dismiss();
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void e(View view) {
        if (this.o != view) {
            this.o = view;
            this.n = v.j.b(this.m, d1.z(view));
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView g() {
        if (this.i.isEmpty()) {
            return null;
        }
        List list = this.i;
        return ((C0007d) list.get(list.size() - 1)).a();
    }

    @Override // androidx.appcompat.view.menu.k
    public void h(boolean z) {
        this.v = z;
    }

    @Override // androidx.appcompat.view.menu.k
    public void i(int i) {
        if (this.m != i) {
            this.m = i;
            this.n = v.j.b(i, d1.z(this.o));
        }
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean isShowing() {
        if (this.i.size() <= 0 || !((C0007d) this.i.get(0)).a.isShowing()) {
            return false;
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.k
    public void j(int i) {
        this.r = true;
        this.t = i;
    }

    @Override // androidx.appcompat.view.menu.k
    public void k(PopupWindow.OnDismissListener onDismissListener) {
        this.z = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.k
    public void l(boolean z) {
        this.w = z;
    }

    @Override // androidx.appcompat.view.menu.k
    public void m(int i) {
        this.f8s = true;
        this.u = i;
    }

    @Override // androidx.appcompat.view.menu.m
    public void onCloseMenu(g gVar, boolean z) {
        int q = q(gVar);
        if (q < 0) {
            return;
        }
        int i = q + 1;
        if (i < this.i.size()) {
            ((C0007d) this.i.get(i)).b.close(false);
        }
        C0007d c0007d = (C0007d) this.i.remove(q);
        c0007d.b.removeMenuPresenter(this);
        if (this.A) {
            c0007d.a.L(null);
            c0007d.a.x(0);
        }
        c0007d.a.dismiss();
        int size = this.i.size();
        if (size > 0) {
            this.q = ((C0007d) this.i.get(size - 1)).c;
        } else {
            this.q = t();
        }
        if (size == 0) {
            dismiss();
            m.a aVar = this.x;
            if (aVar != null) {
                aVar.onCloseMenu(gVar, true);
            }
            ViewTreeObserver viewTreeObserver = this.y;
            if (viewTreeObserver != null) {
                if (viewTreeObserver.isAlive()) {
                    this.y.removeGlobalOnLayoutListener(this.j);
                }
                this.y = null;
            }
            this.p.removeOnAttachStateChangeListener(this.k);
            this.z.onDismiss();
        } else if (z) {
            ((C0007d) this.i.get(0)).b.close(false);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C0007d c0007d;
        int size = this.i.size();
        int i = 0;
        while (true) {
            if (i < size) {
                c0007d = (C0007d) this.i.get(i);
                if (!c0007d.a.isShowing()) {
                    break;
                }
                i++;
            } else {
                c0007d = null;
                break;
            }
        }
        if (c0007d != null) {
            c0007d.b.close(false);
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
        for (C0007d c0007d : this.i) {
            if (rVar == c0007d.b) {
                c0007d.a().requestFocus();
                return true;
            }
        }
        if (rVar.hasVisibleItems()) {
            a(rVar);
            m.a aVar = this.x;
            if (aVar != null) {
                aVar.a(rVar);
            }
            return true;
        }
        return false;
    }

    public final d2 p() {
        d2 d2Var = new d2(this.b, null, this.d, this.e);
        d2Var.M(this.l);
        d2Var.E(this);
        d2Var.D(this);
        d2Var.w(this.o);
        d2Var.z(this.n);
        d2Var.C(true);
        d2Var.B(2);
        return d2Var;
    }

    public final int q(g gVar) {
        int size = this.i.size();
        for (int i = 0; i < size; i++) {
            if (gVar == ((C0007d) this.i.get(i)).b) {
                return i;
            }
        }
        return -1;
    }

    public final MenuItem r(g gVar, g gVar2) {
        int size = gVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = gVar.getItem(i);
            if (item.hasSubMenu() && gVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    public final View s(C0007d c0007d, g gVar) {
        f fVar;
        int i;
        int firstVisiblePosition;
        MenuItem r = r(c0007d.b, gVar);
        if (r == null) {
            return null;
        }
        ListView a2 = c0007d.a();
        ListAdapter adapter = a2.getAdapter();
        int i2 = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            i = headerViewListAdapter.getHeadersCount();
            fVar = (f) headerViewListAdapter.getWrappedAdapter();
        } else {
            fVar = (f) adapter;
            i = 0;
        }
        int count = fVar.getCount();
        while (true) {
            if (i2 < count) {
                if (r == fVar.getItem(i2)) {
                    break;
                }
                i2++;
            } else {
                i2 = -1;
                break;
            }
        }
        if (i2 == -1 || (firstVisiblePosition = (i2 + i) - a2.getFirstVisiblePosition()) < 0 || firstVisiblePosition >= a2.getChildCount()) {
            return null;
        }
        return a2.getChildAt(firstVisiblePosition);
    }

    @Override // androidx.appcompat.view.menu.m
    public void setCallback(m.a aVar) {
        this.x = aVar;
    }

    @Override // androidx.appcompat.view.menu.p
    public void show() {
        boolean z;
        if (isShowing()) {
            return;
        }
        for (g gVar : this.h) {
            v(gVar);
        }
        this.h.clear();
        View view = this.o;
        this.p = view;
        if (view != null) {
            if (this.y == null) {
                z = true;
            } else {
                z = false;
            }
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.y = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.j);
            }
            this.p.addOnAttachStateChangeListener(this.k);
        }
    }

    public final int t() {
        if (d1.z(this.o) != 1) {
            return 1;
        }
        return 0;
    }

    public final int u(int i) {
        List list = this.i;
        ListView a2 = ((C0007d) list.get(list.size() - 1)).a();
        int[] iArr = new int[2];
        a2.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.p.getWindowVisibleDisplayFrame(rect);
        if (this.q == 1) {
            if (iArr[0] + a2.getWidth() + i <= rect.right) {
                return 1;
            }
            return 0;
        } else if (iArr[0] - i < 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void updateMenuView(boolean z) {
        for (C0007d c0007d : this.i) {
            k.o(c0007d.a().getAdapter()).notifyDataSetChanged();
        }
    }

    public final void v(g gVar) {
        C0007d c0007d;
        View view;
        boolean z;
        int i;
        int i2;
        int i3;
        LayoutInflater from = LayoutInflater.from(this.b);
        f fVar = new f(gVar, from, this.f, B);
        if (!isShowing() && this.v) {
            fVar.d(true);
        } else if (isShowing()) {
            fVar.d(k.n(gVar));
        }
        int d = k.d(fVar, null, this.b, this.c);
        d2 p = p();
        p.m(fVar);
        p.y(d);
        p.z(this.n);
        if (this.i.size() > 0) {
            List list = this.i;
            c0007d = (C0007d) list.get(list.size() - 1);
            view = s(c0007d, gVar);
        } else {
            c0007d = null;
            view = null;
        }
        if (view != null) {
            p.N(false);
            p.K(null);
            int u = u(d);
            if (u == 1) {
                z = true;
            } else {
                z = false;
            }
            this.q = u;
            if (Build.VERSION.SDK_INT >= 26) {
                p.w(view);
                i2 = 0;
                i = 0;
            } else {
                int[] iArr = new int[2];
                this.o.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                view.getLocationOnScreen(iArr2);
                if ((this.n & 7) == 5) {
                    iArr[0] = iArr[0] + this.o.getWidth();
                    iArr2[0] = iArr2[0] + view.getWidth();
                }
                i = iArr2[0] - iArr[0];
                i2 = iArr2[1] - iArr[1];
            }
            if ((this.n & 5) == 5) {
                if (!z) {
                    d = view.getWidth();
                    i3 = i - d;
                }
                i3 = i + d;
            } else {
                if (z) {
                    d = view.getWidth();
                    i3 = i + d;
                }
                i3 = i - d;
            }
            p.c(i3);
            p.F(true);
            p.i(i2);
        } else {
            if (this.r) {
                p.c(this.t);
            }
            if (this.f8s) {
                p.i(this.u);
            }
            p.A(c());
        }
        this.i.add(new C0007d(p, gVar, this.q));
        p.show();
        ListView g = p.g();
        g.setOnKeyListener(this);
        if (c0007d == null && this.w && gVar.getHeaderTitle() != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) g, false);
            frameLayout.setEnabled(false);
            ((TextView) frameLayout.findViewById(16908310)).setText(gVar.getHeaderTitle());
            g.addHeaderView(frameLayout, null, false);
            p.show();
        }
    }
}
