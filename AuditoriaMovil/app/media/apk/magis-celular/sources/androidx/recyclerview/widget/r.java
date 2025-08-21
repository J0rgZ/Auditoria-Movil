package androidx.recyclerview.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public abstract class r extends RecyclerView.r {
    public RecyclerView a;
    public Scroller b;
    public final RecyclerView.t c = new a();

    /* loaded from: classes.dex */
    public class a extends RecyclerView.t {
        public boolean a = false;

        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0 && this.a) {
                this.a = false;
                r.this.l();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (i != 0 || i2 != 0) {
                this.a = true;
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends j {
        public b(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.j
        public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }

        @Override // androidx.recyclerview.widget.j, androidx.recyclerview.widget.RecyclerView.z
        public void onTargetFound(View view, RecyclerView.a0 a0Var, RecyclerView.z.a aVar) {
            r rVar = r.this;
            RecyclerView recyclerView = rVar.a;
            if (recyclerView == null) {
                return;
            }
            int[] c = rVar.c(recyclerView.getLayoutManager(), view);
            int i = c[0];
            int i2 = c[1];
            int calculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i), Math.abs(i2)));
            if (calculateTimeForDeceleration > 0) {
                aVar.d(i, i2, calculateTimeForDeceleration, this.mDecelerateInterpolator);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.r
    public boolean a(int i, int i2) {
        RecyclerView.o layoutManager = this.a.getLayoutManager();
        if (layoutManager == null || this.a.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.a.getMinFlingVelocity();
        if ((Math.abs(i2) <= minFlingVelocity && Math.abs(i) <= minFlingVelocity) || !k(layoutManager, i, i2)) {
            return false;
        }
        return true;
    }

    public void b(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            g();
        }
        this.a = recyclerView;
        if (recyclerView != null) {
            j();
            this.b = new Scroller(this.a.getContext(), new DecelerateInterpolator());
            l();
        }
    }

    public abstract int[] c(RecyclerView.o oVar, View view);

    public int[] d(int i, int i2) {
        this.b.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[]{this.b.getFinalX(), this.b.getFinalY()};
    }

    public RecyclerView.z e(RecyclerView.o oVar) {
        return f(oVar);
    }

    public j f(RecyclerView.o oVar) {
        if (!(oVar instanceof RecyclerView.z.b)) {
            return null;
        }
        return new b(this.a.getContext());
    }

    public final void g() {
        this.a.removeOnScrollListener(this.c);
        this.a.setOnFlingListener(null);
    }

    public abstract View h(RecyclerView.o oVar);

    public abstract int i(RecyclerView.o oVar, int i, int i2);

    public final void j() {
        if (this.a.getOnFlingListener() == null) {
            this.a.addOnScrollListener(this.c);
            this.a.setOnFlingListener(this);
            return;
        }
        throw new IllegalStateException("An instance of OnFlingListener already set.");
    }

    public final boolean k(RecyclerView.o oVar, int i, int i2) {
        RecyclerView.z e;
        int i3;
        if (!(oVar instanceof RecyclerView.z.b) || (e = e(oVar)) == null || (i3 = i(oVar, i, i2)) == -1) {
            return false;
        }
        e.setTargetPosition(i3);
        oVar.startSmoothScroll(e);
        return true;
    }

    public void l() {
        RecyclerView.o layoutManager;
        View h;
        RecyclerView recyclerView = this.a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (h = h(layoutManager)) == null) {
            return;
        }
        int[] c = c(layoutManager, h);
        int i = c[0];
        if (i != 0 || c[1] != 0) {
            this.a.smoothScrollBy(i, c[1]);
        }
    }
}
