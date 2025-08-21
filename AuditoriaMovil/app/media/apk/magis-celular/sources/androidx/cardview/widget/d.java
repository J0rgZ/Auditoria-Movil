package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
/* loaded from: classes.dex */
public abstract class d implements f {
    public final RectF a = new RectF();

    @Override // androidx.cardview.widget.f
    public void a(e eVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        i p = p(context, colorStateList, f, f2, f3);
        p.m(eVar.c());
        eVar.b(p);
        i(eVar);
    }

    @Override // androidx.cardview.widget.f
    public void b(e eVar, float f) {
        q(eVar).p(f);
        i(eVar);
    }

    @Override // androidx.cardview.widget.f
    public float c(e eVar) {
        return q(eVar).l();
    }

    @Override // androidx.cardview.widget.f
    public float d(e eVar) {
        return q(eVar).g();
    }

    @Override // androidx.cardview.widget.f
    public void e(e eVar) {
    }

    @Override // androidx.cardview.widget.f
    public void f(e eVar, float f) {
        q(eVar).r(f);
    }

    @Override // androidx.cardview.widget.f
    public float g(e eVar) {
        return q(eVar).i();
    }

    @Override // androidx.cardview.widget.f
    public ColorStateList h(e eVar) {
        return q(eVar).f();
    }

    @Override // androidx.cardview.widget.f
    public void i(e eVar) {
        Rect rect = new Rect();
        q(eVar).h(rect);
        eVar.a((int) Math.ceil(l(eVar)), (int) Math.ceil(k(eVar)));
        eVar.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // androidx.cardview.widget.f
    public float k(e eVar) {
        return q(eVar).j();
    }

    @Override // androidx.cardview.widget.f
    public float l(e eVar) {
        return q(eVar).k();
    }

    @Override // androidx.cardview.widget.f
    public void m(e eVar) {
        q(eVar).m(eVar.c());
        i(eVar);
    }

    @Override // androidx.cardview.widget.f
    public void n(e eVar, ColorStateList colorStateList) {
        q(eVar).o(colorStateList);
    }

    @Override // androidx.cardview.widget.f
    public void o(e eVar, float f) {
        q(eVar).q(f);
        i(eVar);
    }

    public final i p(Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        return new i(context.getResources(), colorStateList, f, f2, f3);
    }

    public final i q(e eVar) {
        return (i) eVar.e();
    }
}
