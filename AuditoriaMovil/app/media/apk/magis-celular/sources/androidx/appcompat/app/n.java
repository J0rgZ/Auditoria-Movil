package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.i1;
import androidx.appcompat.widget.s2;
import java.util.ArrayList;
import v.d1;
/* loaded from: classes.dex */
public class n extends androidx.appcompat.app.a {
    public i1 a;
    public boolean b;
    public Window.Callback c;
    public boolean d;
    public boolean e;
    public ArrayList f = new ArrayList();
    public final Runnable g = new a();
    public final Toolbar.f h;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            n.this.B();
        }
    }

    /* loaded from: classes.dex */
    public class b implements Toolbar.f {
        public b() {
        }

        @Override // androidx.appcompat.widget.Toolbar.f
        public boolean onMenuItemClick(MenuItem menuItem) {
            return n.this.c.onMenuItemSelected(0, menuItem);
        }
    }

    /* loaded from: classes.dex */
    public final class c implements m.a {
        public boolean a;

        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callback = n.this.c;
            if (callback != null) {
                callback.onMenuOpened(108, gVar);
                return true;
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.appcompat.view.menu.m.a
        public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
            if (this.a) {
                return;
            }
            this.a = true;
            n.this.a.h();
            Window.Callback callback = n.this.c;
            if (callback != null) {
                callback.onPanelClosed(108, gVar);
            }
            this.a = false;
        }
    }

    /* loaded from: classes.dex */
    public final class d implements g.a {
        public d() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean onMenuItemSelected(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.appcompat.view.menu.g.a
        public void onMenuModeChange(androidx.appcompat.view.menu.g gVar) {
            n nVar = n.this;
            if (nVar.c != null) {
                if (nVar.a.b()) {
                    n.this.c.onPanelClosed(108, gVar);
                } else if (n.this.c.onPreparePanel(0, null, gVar)) {
                    n.this.c.onMenuOpened(108, gVar);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class e extends e.m {
        public e(Window.Callback callback) {
            super(callback);
        }

        public View onCreatePanelView(int i) {
            if (i == 0) {
                return new View(n.this.a.getContext());
            }
            return super.onCreatePanelView(i);
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (onPreparePanel) {
                n nVar = n.this;
                if (!nVar.b) {
                    nVar.a.c();
                    n.this.b = true;
                }
            }
            return onPreparePanel;
        }
    }

    public n(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        b bVar = new b();
        this.h = bVar;
        this.a = new s2(toolbar, false);
        e.m eVar = new e(callback);
        this.c = eVar;
        this.a.setWindowCallback(eVar);
        toolbar.setOnMenuItemClickListener(bVar);
        this.a.setWindowTitle(charSequence);
    }

    public Window.Callback A() {
        return this.c;
    }

    public void B() {
        androidx.appcompat.view.menu.g gVar;
        Menu z = z();
        if (z instanceof androidx.appcompat.view.menu.g) {
            gVar = (androidx.appcompat.view.menu.g) z;
        } else {
            gVar = null;
        }
        if (gVar != null) {
            gVar.stopDispatchingItemsChanged();
        }
        try {
            z.clear();
            if (!this.c.onCreatePanelMenu(0, z) || !this.c.onPreparePanel(0, null, z)) {
                z.clear();
            }
        } finally {
            if (gVar != null) {
                gVar.startDispatchingItemsChanged();
            }
        }
    }

    public void C(int i, int i2) {
        this.a.k((i & i2) | ((i2 ^ (-1)) & this.a.v()));
    }

    @Override // androidx.appcompat.app.a
    public boolean g() {
        return this.a.f();
    }

    @Override // androidx.appcompat.app.a
    public boolean h() {
        if (this.a.j()) {
            this.a.collapseActionView();
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.a
    public void i(boolean z) {
        if (z == this.e) {
            return;
        }
        this.e = z;
        if (this.f.size() <= 0) {
            return;
        }
        m.a(this.f.get(0));
        throw null;
    }

    @Override // androidx.appcompat.app.a
    public int j() {
        return this.a.v();
    }

    @Override // androidx.appcompat.app.a
    public Context k() {
        return this.a.getContext();
    }

    @Override // androidx.appcompat.app.a
    public boolean l() {
        this.a.t().removeCallbacks(this.g);
        d1.c0(this.a.t(), this.g);
        return true;
    }

    @Override // androidx.appcompat.app.a
    public void m(Configuration configuration) {
        super.m(configuration);
    }

    @Override // androidx.appcompat.app.a
    public void n() {
        this.a.t().removeCallbacks(this.g);
    }

    @Override // androidx.appcompat.app.a
    public boolean o(int i, KeyEvent keyEvent) {
        int i2;
        Menu z = z();
        if (z == null) {
            return false;
        }
        if (keyEvent != null) {
            i2 = keyEvent.getDeviceId();
        } else {
            i2 = -1;
        }
        boolean z2 = true;
        if (KeyCharacterMap.load(i2).getKeyboardType() == 1) {
            z2 = false;
        }
        z.setQwertyMode(z2);
        return z.performShortcut(i, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.a
    public boolean p(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            q();
        }
        return true;
    }

    @Override // androidx.appcompat.app.a
    public boolean q() {
        return this.a.g();
    }

    @Override // androidx.appcompat.app.a
    public void r(boolean z) {
    }

    @Override // androidx.appcompat.app.a
    public void s(boolean z) {
        int i;
        if (z) {
            i = 4;
        } else {
            i = 0;
        }
        C(i, 4);
    }

    @Override // androidx.appcompat.app.a
    public void t(int i) {
        this.a.q(i);
    }

    @Override // androidx.appcompat.app.a
    public void u(boolean z) {
    }

    @Override // androidx.appcompat.app.a
    public void v(CharSequence charSequence) {
        this.a.l(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void w(CharSequence charSequence) {
        this.a.setTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void x(CharSequence charSequence) {
        this.a.setWindowTitle(charSequence);
    }

    public final Menu z() {
        if (!this.d) {
            this.a.r(new c(), new d());
            this.d = true;
        }
        return this.a.m();
    }
}
