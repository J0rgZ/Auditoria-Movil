package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public class i {
    public int b;
    public int c;
    public int d;
    public int e;
    public boolean h;
    public boolean i;
    public boolean a = true;
    public int f = 0;
    public int g = 0;

    public boolean a(RecyclerView.a0 a0Var) {
        int i = this.c;
        if (i >= 0 && i < a0Var.b()) {
            return true;
        }
        return false;
    }

    public View b(RecyclerView.v vVar) {
        View o = vVar.o(this.c);
        this.c += this.d;
        return o;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.b + ", mCurrentPosition=" + this.c + ", mItemDirection=" + this.d + ", mLayoutDirection=" + this.e + ", mStartLine=" + this.f + ", mEndLine=" + this.g + '}';
    }
}
