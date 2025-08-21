package androidx.transition;

import android.graphics.Matrix;
import android.view.View;
/* loaded from: classes.dex */
public class n0 extends m0 {
    @Override // androidx.transition.f0, androidx.transition.o0
    public float c(View view) {
        float transitionAlpha;
        transitionAlpha = view.getTransitionAlpha();
        return transitionAlpha;
    }

    @Override // androidx.transition.k0, androidx.transition.o0
    public void e(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    @Override // androidx.transition.f0, androidx.transition.o0
    public void f(View view, float f) {
        view.setTransitionAlpha(f);
    }

    @Override // androidx.transition.m0, androidx.transition.o0
    public void g(View view, int i) {
        view.setTransitionVisibility(i);
    }

    @Override // androidx.transition.i0, androidx.transition.o0
    public void h(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // androidx.transition.i0, androidx.transition.o0
    public void i(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
