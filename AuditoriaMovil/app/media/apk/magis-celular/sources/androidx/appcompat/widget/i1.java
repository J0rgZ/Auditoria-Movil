package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
/* loaded from: classes.dex */
public interface i1 {
    void a(Menu menu, m.a aVar);

    boolean b();

    void c();

    void collapseActionView();

    boolean d();

    boolean e();

    boolean f();

    boolean g();

    Context getContext();

    CharSequence getTitle();

    void h();

    void i(j2 j2Var);

    boolean j();

    void k(int i);

    void l(CharSequence charSequence);

    Menu m();

    void n(int i);

    int o();

    v.b2 p(int i, long j);

    void q(int i);

    void r(m.a aVar, g.a aVar2);

    void s(int i);

    void setIcon(int i);

    void setIcon(Drawable drawable);

    void setTitle(CharSequence charSequence);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    ViewGroup t();

    void u(boolean z);

    int v();

    void w();

    void x();

    void y(boolean z);
}
