package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public abstract class q extends RecyclerView.l {
    public boolean g = true;

    public final void A(RecyclerView.d0 d0Var) {
        I(d0Var);
        h(d0Var);
    }

    public final void B(RecyclerView.d0 d0Var) {
        J(d0Var);
    }

    public final void C(RecyclerView.d0 d0Var, boolean z) {
        K(d0Var, z);
        h(d0Var);
    }

    public final void D(RecyclerView.d0 d0Var, boolean z) {
        L(d0Var, z);
    }

    public final void E(RecyclerView.d0 d0Var) {
        M(d0Var);
        h(d0Var);
    }

    public final void F(RecyclerView.d0 d0Var) {
        N(d0Var);
    }

    public final void G(RecyclerView.d0 d0Var) {
        O(d0Var);
        h(d0Var);
    }

    public final void H(RecyclerView.d0 d0Var) {
        P(d0Var);
    }

    public void I(RecyclerView.d0 d0Var) {
    }

    public void J(RecyclerView.d0 d0Var) {
    }

    public void K(RecyclerView.d0 d0Var, boolean z) {
    }

    public void L(RecyclerView.d0 d0Var, boolean z) {
    }

    public void M(RecyclerView.d0 d0Var) {
    }

    public void N(RecyclerView.d0 d0Var) {
    }

    public void O(RecyclerView.d0 d0Var) {
    }

    public void P(RecyclerView.d0 d0Var) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean a(RecyclerView.d0 d0Var, RecyclerView.l.b bVar, RecyclerView.l.b bVar2) {
        int i;
        int i2;
        if (bVar != null && ((i = bVar.a) != (i2 = bVar2.a) || bVar.b != bVar2.b)) {
            return y(d0Var, i, bVar.b, i2, bVar2.b);
        }
        return w(d0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean b(RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2, RecyclerView.l.b bVar, RecyclerView.l.b bVar2) {
        int i;
        int i2;
        int i3 = bVar.a;
        int i4 = bVar.b;
        if (d0Var2.shouldIgnore()) {
            int i5 = bVar.a;
            i2 = bVar.b;
            i = i5;
        } else {
            i = bVar2.a;
            i2 = bVar2.b;
        }
        return x(d0Var, d0Var2, i3, i4, i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean c(RecyclerView.d0 d0Var, RecyclerView.l.b bVar, RecyclerView.l.b bVar2) {
        int i;
        int i2;
        int i3 = bVar.a;
        int i4 = bVar.b;
        View view = d0Var.itemView;
        if (bVar2 == null) {
            i = view.getLeft();
        } else {
            i = bVar2.a;
        }
        int i5 = i;
        if (bVar2 == null) {
            i2 = view.getTop();
        } else {
            i2 = bVar2.b;
        }
        int i6 = i2;
        if (!d0Var.isRemoved() && (i3 != i5 || i4 != i6)) {
            view.layout(i5, i6, view.getWidth() + i5, view.getHeight() + i6);
            return y(d0Var, i3, i4, i5, i6);
        }
        return z(d0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean d(RecyclerView.d0 d0Var, RecyclerView.l.b bVar, RecyclerView.l.b bVar2) {
        int i = bVar.a;
        int i2 = bVar2.a;
        if (i == i2 && bVar.b == bVar2.b) {
            E(d0Var);
            return false;
        }
        return y(d0Var, i, bVar.b, i2, bVar2.b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean f(RecyclerView.d0 d0Var) {
        if (this.g && !d0Var.isInvalid()) {
            return false;
        }
        return true;
    }

    public abstract boolean w(RecyclerView.d0 d0Var);

    public abstract boolean x(RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2, int i, int i2, int i3, int i4);

    public abstract boolean y(RecyclerView.d0 d0Var, int i, int i2, int i3, int i4);

    public abstract boolean z(RecyclerView.d0 d0Var);
}
