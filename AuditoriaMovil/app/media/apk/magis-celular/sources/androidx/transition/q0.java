package androidx.transition;

import android.view.View;
import android.view.WindowId;
/* loaded from: classes.dex */
public class q0 implements r0 {
    public final WindowId a;

    public q0(View view) {
        this.a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof q0) && ((q0) obj).a.equals(this.a)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
