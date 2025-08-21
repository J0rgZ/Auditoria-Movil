package androidx.transition;

import android.view.View;
/* loaded from: classes.dex */
public class f0 extends o0 {
    public static boolean e = true;

    @Override // androidx.transition.o0
    public void a(View view) {
    }

    @Override // androidx.transition.o0
    public float c(View view) {
        float transitionAlpha;
        if (e) {
            try {
                transitionAlpha = view.getTransitionAlpha();
                return transitionAlpha;
            } catch (NoSuchMethodError unused) {
                e = false;
            }
        }
        return view.getAlpha();
    }

    @Override // androidx.transition.o0
    public void d(View view) {
    }

    @Override // androidx.transition.o0
    public void f(View view, float f) {
        if (e) {
            try {
                view.setTransitionAlpha(f);
                return;
            } catch (NoSuchMethodError unused) {
                e = false;
            }
        }
        view.setAlpha(f);
    }
}
