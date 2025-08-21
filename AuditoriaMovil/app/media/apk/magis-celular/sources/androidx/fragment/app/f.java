package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
/* loaded from: classes.dex */
public class f {
    public final h a;

    public f(h hVar) {
        this.a = hVar;
    }

    public static f b(h hVar) {
        return new f((h) u.i.e(hVar, "callbacks == null"));
    }

    public void a(Fragment fragment) {
        h hVar = this.a;
        hVar.e.r(hVar, hVar, fragment);
    }

    public void c() {
        this.a.e.A();
    }

    public void d(Configuration configuration) {
        this.a.e.B(configuration);
    }

    public boolean e(MenuItem menuItem) {
        return this.a.e.C(menuItem);
    }

    public void f() {
        this.a.e.D();
    }

    public boolean g(Menu menu, MenuInflater menuInflater) {
        return this.a.e.E(menu, menuInflater);
    }

    public void h() {
        this.a.e.F();
    }

    public void i() {
        this.a.e.H();
    }

    public void j(boolean z) {
        this.a.e.I(z);
    }

    public boolean k(MenuItem menuItem) {
        return this.a.e.X(menuItem);
    }

    public void l(Menu menu) {
        this.a.e.Y(menu);
    }

    public void m() {
        this.a.e.a0();
    }

    public void n(boolean z) {
        this.a.e.b0(z);
    }

    public boolean o(Menu menu) {
        return this.a.e.c0(menu);
    }

    public void p() {
        this.a.e.e0();
    }

    public void q() {
        this.a.e.f0();
    }

    public void r() {
        this.a.e.h0();
    }

    public boolean s() {
        return this.a.e.n0();
    }

    public Fragment t(String str) {
        return this.a.e.t0(str);
    }

    public i u() {
        return this.a.e;
    }

    public void v() {
        this.a.e.U0();
    }

    public View w(View view, String str, Context context, AttributeSet attributeSet) {
        return this.a.e.onCreateView(view, str, context, attributeSet);
    }

    public void x(Parcelable parcelable) {
        h hVar = this.a;
        if (hVar instanceof androidx.lifecycle.t) {
            hVar.e.d1(parcelable);
            return;
        }
        throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
    }

    public Parcelable y() {
        return this.a.e.f1();
    }
}
