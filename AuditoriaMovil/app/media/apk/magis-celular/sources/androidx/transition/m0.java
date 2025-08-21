package androidx.transition;

import android.os.Build;
import android.view.View;
/* loaded from: classes.dex */
public class m0 extends k0 {
    public static boolean i = true;

    @Override // androidx.transition.o0
    public void g(View view, int i2) {
        if (Build.VERSION.SDK_INT == 28) {
            super.g(view, i2);
        } else if (i) {
            try {
                view.setTransitionVisibility(i2);
            } catch (NoSuchMethodError unused) {
                i = false;
            }
        }
    }
}
