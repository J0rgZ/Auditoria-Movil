package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import v.d1;
/* loaded from: classes.dex */
public class d extends p0 {

    /* loaded from: classes.dex */
    public class a extends o {
        public final /* synthetic */ View a;

        public a(View view) {
            this.a = view;
        }

        @Override // androidx.transition.n.g
        public void c(n nVar) {
            c0.g(this.a, 1.0f);
            c0.a(this.a);
            nVar.removeListener(this);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends AnimatorListenerAdapter {
        public final View a;
        public boolean b = false;

        public b(View view) {
            this.a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            c0.g(this.a, 1.0f);
            if (this.b) {
                this.a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (d1.M(this.a) && this.a.getLayerType() == 0) {
                this.b = true;
                this.a.setLayerType(2, null);
            }
        }
    }

    public d(int i) {
        w(i);
    }

    public static float y(u uVar, float f) {
        Float f2;
        if (uVar != null && (f2 = (Float) uVar.a.get("android:fade:transitionAlpha")) != null) {
            return f2.floatValue();
        }
        return f;
    }

    @Override // androidx.transition.p0, androidx.transition.n
    public void captureStartValues(u uVar) {
        super.captureStartValues(uVar);
        uVar.a.put("android:fade:transitionAlpha", Float.valueOf(c0.c(uVar.b)));
    }

    @Override // androidx.transition.p0
    public Animator s(ViewGroup viewGroup, View view, u uVar, u uVar2) {
        float f = 0.0f;
        float y = y(uVar, 0.0f);
        if (y != 1.0f) {
            f = y;
        }
        return x(view, f, 1.0f);
    }

    @Override // androidx.transition.p0
    public Animator u(ViewGroup viewGroup, View view, u uVar, u uVar2) {
        c0.e(view);
        return x(view, y(uVar, 1.0f), 0.0f);
    }

    public final Animator x(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        c0.g(view, f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, c0.b, f2);
        ofFloat.addListener(new b(view));
        addListener(new a(view));
        return ofFloat;
    }
}
