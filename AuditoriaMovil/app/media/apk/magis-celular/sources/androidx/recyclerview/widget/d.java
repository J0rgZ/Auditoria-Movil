package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import v.d1;
/* loaded from: classes.dex */
public class d extends RecyclerView.n implements RecyclerView.s {
    public static final int[] D = {16842919};
    public static final int[] E = new int[0];
    public int A;
    public final Runnable B;
    public final RecyclerView.t C;
    public final int a;
    public final int b;
    public final StateListDrawable c;
    public final Drawable d;
    public final int e;
    public final int f;
    public final StateListDrawable g;
    public final Drawable h;
    public final int i;
    public final int j;
    public int k;
    public int l;
    public float m;
    public int n;
    public int o;
    public float p;

    /* renamed from: s  reason: collision with root package name */
    public RecyclerView f27s;
    public final ValueAnimator z;
    public int q = 0;
    public int r = 0;
    public boolean t = false;
    public boolean u = false;
    public int v = 0;
    public int w = 0;
    public final int[] x = new int[2];
    public final int[] y = new int[2];

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.h(500);
        }
    }

    /* loaded from: classes.dex */
    public class b extends RecyclerView.t {
        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            d.this.s(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    }

    /* loaded from: classes.dex */
    public class c extends AnimatorListenerAdapter {
        public boolean a = false;

        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.a) {
                this.a = false;
            } else if (((Float) d.this.z.getAnimatedValue()).floatValue() == 0.0f) {
                d dVar = d.this;
                dVar.A = 0;
                dVar.p(0);
            } else {
                d dVar2 = d.this;
                dVar2.A = 2;
                dVar2.m();
            }
        }
    }

    /* renamed from: androidx.recyclerview.widget.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0030d implements ValueAnimator.AnimatorUpdateListener {
        public C0030d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            d.this.c.setAlpha(floatValue);
            d.this.d.setAlpha(floatValue);
            d.this.m();
        }
    }

    public d(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.z = ofFloat;
        this.A = 0;
        this.B = new a();
        this.C = new b();
        this.c = stateListDrawable;
        this.d = drawable;
        this.g = stateListDrawable2;
        this.h = drawable2;
        this.e = Math.max(i, stateListDrawable.getIntrinsicWidth());
        this.f = Math.max(i, drawable.getIntrinsicWidth());
        this.i = Math.max(i, stateListDrawable2.getIntrinsicWidth());
        this.j = Math.max(i, drawable2.getIntrinsicWidth());
        this.a = i2;
        this.b = i3;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        ofFloat.addListener(new c());
        ofFloat.addUpdateListener(new C0030d());
        a(recyclerView);
    }

    public void a(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f27s;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            c();
        }
        this.f27s = recyclerView;
        if (recyclerView != null) {
            q();
        }
    }

    public final void b() {
        this.f27s.removeCallbacks(this.B);
    }

    public final void c() {
        this.f27s.removeItemDecoration(this);
        this.f27s.removeOnItemTouchListener(this);
        this.f27s.removeOnScrollListener(this.C);
        b();
    }

    public final void d(Canvas canvas) {
        int i = this.r;
        int i2 = this.i;
        int i3 = i - i2;
        int i4 = this.o;
        int i5 = this.n;
        int i6 = i4 - (i5 / 2);
        this.g.setBounds(0, 0, i5, i2);
        this.h.setBounds(0, 0, this.q, this.j);
        canvas.translate(0.0f, i3);
        this.h.draw(canvas);
        canvas.translate(i6, 0.0f);
        this.g.draw(canvas);
        canvas.translate(-i6, -i3);
    }

    public final void e(Canvas canvas) {
        int i = this.q;
        int i2 = this.e;
        int i3 = i - i2;
        int i4 = this.l;
        int i5 = this.k;
        int i6 = i4 - (i5 / 2);
        this.c.setBounds(0, 0, i2, i5);
        this.d.setBounds(0, 0, this.f, this.r);
        if (j()) {
            this.d.draw(canvas);
            canvas.translate(this.e, i6);
            canvas.scale(-1.0f, 1.0f);
            this.c.draw(canvas);
            canvas.scale(1.0f, 1.0f);
            canvas.translate(-this.e, -i6);
            return;
        }
        canvas.translate(i3, 0.0f);
        this.d.draw(canvas);
        canvas.translate(0.0f, i6);
        this.c.draw(canvas);
        canvas.translate(-i3, -i6);
    }

    public final int[] f() {
        int[] iArr = this.y;
        int i = this.b;
        iArr[0] = i;
        iArr[1] = this.q - i;
        return iArr;
    }

    public final int[] g() {
        int[] iArr = this.x;
        int i = this.b;
        iArr[0] = i;
        iArr[1] = this.r - i;
        return iArr;
    }

    public void h(int i) {
        int i2 = this.A;
        if (i2 != 1) {
            if (i2 != 2) {
                return;
            }
        } else {
            this.z.cancel();
        }
        this.A = 3;
        ValueAnimator valueAnimator = this.z;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        this.z.setDuration(i);
        this.z.start();
    }

    public final void i(float f) {
        int[] f2 = f();
        float max = Math.max(f2[0], Math.min(f2[1], f));
        if (Math.abs(this.o - max) < 2.0f) {
            return;
        }
        int o = o(this.p, max, f2, this.f27s.computeHorizontalScrollRange(), this.f27s.computeHorizontalScrollOffset(), this.q);
        if (o != 0) {
            this.f27s.scrollBy(o, 0);
        }
        this.p = max;
    }

    public final boolean j() {
        if (d1.z(this.f27s) == 1) {
            return true;
        }
        return false;
    }

    public boolean k(float f, float f2) {
        if (f2 >= this.r - this.i) {
            int i = this.o;
            int i2 = this.n;
            if (f >= i - (i2 / 2) && f <= i + (i2 / 2)) {
                return true;
            }
        }
        return false;
    }

    public boolean l(float f, float f2) {
        if (!j() ? f >= this.q - this.e : f <= this.e / 2) {
            int i = this.l;
            int i2 = this.k;
            if (f2 >= i - (i2 / 2) && f2 <= i + (i2 / 2)) {
                return true;
            }
        }
        return false;
    }

    public void m() {
        this.f27s.invalidate();
    }

    public final void n(int i) {
        b();
        this.f27s.postDelayed(this.B, i);
    }

    public final int o(float f, float f2, int[] iArr, int i, int i2, int i3) {
        int i4 = iArr[1] - iArr[0];
        if (i4 == 0) {
            return 0;
        }
        int i5 = i - i3;
        int i6 = (int) (((f2 - f) / i4) * i5);
        int i7 = i2 + i6;
        if (i7 >= i5 || i7 < 0) {
            return 0;
        }
        return i6;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
        if (this.q == this.f27s.getWidth() && this.r == this.f27s.getHeight()) {
            if (this.A != 0) {
                if (this.t) {
                    e(canvas);
                }
                if (this.u) {
                    d(canvas);
                    return;
                }
                return;
            }
            return;
        }
        this.q = this.f27s.getWidth();
        this.r = this.f27s.getHeight();
        p(0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i = this.v;
        if (i == 1) {
            boolean l = l(motionEvent.getX(), motionEvent.getY());
            boolean k = k(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!l && !k) {
                return false;
            }
            if (k) {
                this.w = 1;
                this.p = (int) motionEvent.getX();
            } else if (l) {
                this.w = 2;
                this.m = (int) motionEvent.getY();
            }
            p(2);
        } else if (i != 2) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.v == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean l = l(motionEvent.getX(), motionEvent.getY());
            boolean k = k(motionEvent.getX(), motionEvent.getY());
            if (l || k) {
                if (k) {
                    this.w = 1;
                    this.p = (int) motionEvent.getX();
                } else if (l) {
                    this.w = 2;
                    this.m = (int) motionEvent.getY();
                }
                p(2);
            }
        } else if (motionEvent.getAction() == 1 && this.v == 2) {
            this.m = 0.0f;
            this.p = 0.0f;
            p(1);
            this.w = 0;
        } else if (motionEvent.getAction() == 2 && this.v == 2) {
            r();
            if (this.w == 1) {
                i(motionEvent.getX());
            }
            if (this.w == 2) {
                t(motionEvent.getY());
            }
        }
    }

    public void p(int i) {
        if (i == 2 && this.v != 2) {
            this.c.setState(D);
            b();
        }
        if (i == 0) {
            m();
        } else {
            r();
        }
        if (this.v == 2 && i != 2) {
            this.c.setState(E);
            n(1200);
        } else if (i == 1) {
            n(1500);
        }
        this.v = i;
    }

    public final void q() {
        this.f27s.addItemDecoration(this);
        this.f27s.addOnItemTouchListener(this);
        this.f27s.addOnScrollListener(this.C);
    }

    public void r() {
        int i = this.A;
        if (i != 0) {
            if (i == 3) {
                this.z.cancel();
            } else {
                return;
            }
        }
        this.A = 1;
        ValueAnimator valueAnimator = this.z;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        this.z.setDuration(500L);
        this.z.setStartDelay(0L);
        this.z.start();
    }

    public void s(int i, int i2) {
        boolean z;
        boolean z2;
        int computeVerticalScrollRange = this.f27s.computeVerticalScrollRange();
        int i3 = this.r;
        if (computeVerticalScrollRange - i3 > 0 && i3 >= this.a) {
            z = true;
        } else {
            z = false;
        }
        this.t = z;
        int computeHorizontalScrollRange = this.f27s.computeHorizontalScrollRange();
        int i4 = this.q;
        if (computeHorizontalScrollRange - i4 > 0 && i4 >= this.a) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.u = z2;
        boolean z3 = this.t;
        if (!z3 && !z2) {
            if (this.v != 0) {
                p(0);
                return;
            }
            return;
        }
        if (z3) {
            float f = i3;
            this.l = (int) ((f * (i2 + (f / 2.0f))) / computeVerticalScrollRange);
            this.k = Math.min(i3, (i3 * i3) / computeVerticalScrollRange);
        }
        if (this.u) {
            float f2 = i4;
            this.o = (int) ((f2 * (i + (f2 / 2.0f))) / computeHorizontalScrollRange);
            this.n = Math.min(i4, (i4 * i4) / computeHorizontalScrollRange);
        }
        int i5 = this.v;
        if (i5 == 0 || i5 == 1) {
            p(1);
        }
    }

    public final void t(float f) {
        int[] g = g();
        float max = Math.max(g[0], Math.min(g[1], f));
        if (Math.abs(this.l - max) < 2.0f) {
            return;
        }
        int o = o(this.m, max, g, this.f27s.computeVerticalScrollRange(), this.f27s.computeVerticalScrollOffset(), this.r);
        if (o != 0) {
            this.f27s.scrollBy(0, o);
        }
        this.m = max;
    }
}
