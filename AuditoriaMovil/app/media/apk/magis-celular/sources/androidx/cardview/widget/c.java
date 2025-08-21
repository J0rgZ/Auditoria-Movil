package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
/* loaded from: classes.dex */
public class c implements f {
    @Override // androidx.cardview.widget.f
    public void a(e eVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        eVar.b(new h(colorStateList, f));
        View f4 = eVar.f();
        f4.setClipToOutline(true);
        f4.setElevation(f2);
        o(eVar, f3);
    }

    @Override // androidx.cardview.widget.f
    public void b(e eVar, float f) {
        p(eVar).h(f);
    }

    @Override // androidx.cardview.widget.f
    public float c(e eVar) {
        return eVar.f().getElevation();
    }

    @Override // androidx.cardview.widget.f
    public float d(e eVar) {
        return p(eVar).d();
    }

    @Override // androidx.cardview.widget.f
    public void e(e eVar) {
        o(eVar, g(eVar));
    }

    @Override // androidx.cardview.widget.f
    public void f(e eVar, float f) {
        eVar.f().setElevation(f);
    }

    @Override // androidx.cardview.widget.f
    public float g(e eVar) {
        return p(eVar).c();
    }

    @Override // androidx.cardview.widget.f
    public ColorStateList h(e eVar) {
        return p(eVar).b();
    }

    @Override // androidx.cardview.widget.f
    public void i(e eVar) {
        if (!eVar.d()) {
            eVar.setShadowPadding(0, 0, 0, 0);
            return;
        }
        float g = g(eVar);
        float d = d(eVar);
        int ceil = (int) Math.ceil(i.c(g, d, eVar.c()));
        int ceil2 = (int) Math.ceil(i.d(g, d, eVar.c()));
        eVar.setShadowPadding(ceil, ceil2, ceil, ceil2);
    }

    @Override // androidx.cardview.widget.f
    public void j() {
    }

    @Override // androidx.cardview.widget.f
    public float k(e eVar) {
        return d(eVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.f
    public float l(e eVar) {
        return d(eVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.f
    public void m(e eVar) {
        o(eVar, g(eVar));
    }

    @Override // androidx.cardview.widget.f
    public void n(e eVar, ColorStateList colorStateList) {
        p(eVar).f(colorStateList);
    }

    @Override // androidx.cardview.widget.f
    public void o(e eVar, float f) {
        p(eVar).g(f, eVar.d(), eVar.c());
        i(eVar);
    }

    public final h p(e eVar) {
        return (h) eVar.e();
    }
}
