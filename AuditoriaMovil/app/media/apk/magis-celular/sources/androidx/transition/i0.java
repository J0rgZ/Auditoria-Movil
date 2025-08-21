package androidx.transition;

import android.graphics.Matrix;
import android.view.View;
/* loaded from: classes.dex */
public class i0 extends f0 {
    public static boolean f = true;
    public static boolean g = true;

    @Override // androidx.transition.o0
    public void h(View view, Matrix matrix) {
        if (f) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                f = false;
            }
        }
    }

    @Override // androidx.transition.o0
    public void i(View view, Matrix matrix) {
        if (g) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                g = false;
            }
        }
    }
}
