package androidx.customview.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;
import v.d1;
/* loaded from: classes.dex */
public class c {
    public static final Interpolator w = new a();
    public int a;
    public int b;
    public float[] d;
    public float[] e;
    public float[] f;
    public float[] g;
    public int[] h;
    public int[] i;
    public int[] j;
    public int k;
    public VelocityTracker l;
    public float m;
    public float n;
    public int o;
    public int p;
    public OverScroller q;
    public final AbstractC0016c r;

    /* renamed from: s  reason: collision with root package name */
    public View f18s;
    public boolean t;
    public final ViewGroup u;
    public int c = -1;
    public final Runnable v = new b();

    /* loaded from: classes.dex */
    public static class a implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.F(0);
        }
    }

    /* renamed from: androidx.customview.widget.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class AbstractC0016c {
        public abstract int clampViewPositionHorizontal(View view, int i, int i2);

        public abstract int clampViewPositionVertical(View view, int i, int i2);

        public int getOrderedChildIndex(int i) {
            return i;
        }

        public int getViewHorizontalDragRange(View view) {
            return 0;
        }

        public int getViewVerticalDragRange(View view) {
            return 0;
        }

        public void onEdgeDragStarted(int i, int i2) {
        }

        public boolean onEdgeLock(int i) {
            return false;
        }

        public void onEdgeTouched(int i, int i2) {
        }

        public void onViewCaptured(View view, int i) {
        }

        public abstract void onViewDragStateChanged(int i);

        public abstract void onViewPositionChanged(View view, int i, int i2, int i3, int i4);

        public abstract void onViewReleased(View view, float f, float f2);

        public abstract boolean tryCaptureView(View view, int i);
    }

    public c(Context context, ViewGroup viewGroup, AbstractC0016c abstractC0016c) {
        if (viewGroup != null) {
            if (abstractC0016c != null) {
                this.u = viewGroup;
                this.r = abstractC0016c;
                ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
                this.o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
                this.b = viewConfiguration.getScaledTouchSlop();
                this.m = viewConfiguration.getScaledMaximumFlingVelocity();
                this.n = viewConfiguration.getScaledMinimumFlingVelocity();
                this.q = new OverScroller(context, w);
                return;
            }
            throw new IllegalArgumentException("Callback may not be null");
        }
        throw new IllegalArgumentException("Parent view may not be null");
    }

    public static c l(ViewGroup viewGroup, float f, AbstractC0016c abstractC0016c) {
        c m = m(viewGroup, abstractC0016c);
        m.b = (int) (m.b * (1.0f / f));
        return m;
    }

    public static c m(ViewGroup viewGroup, AbstractC0016c abstractC0016c) {
        return new c(viewGroup.getContext(), viewGroup, abstractC0016c);
    }

    public void A(MotionEvent motionEvent) {
        int i;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            a();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        int i2 = 0;
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked != 3) {
                        if (actionMasked != 5) {
                            if (actionMasked == 6) {
                                int pointerId = motionEvent.getPointerId(actionIndex);
                                if (this.a == 1 && pointerId == this.c) {
                                    int pointerCount = motionEvent.getPointerCount();
                                    while (true) {
                                        if (i2 < pointerCount) {
                                            int pointerId2 = motionEvent.getPointerId(i2);
                                            if (pointerId2 != this.c) {
                                                View r = r((int) motionEvent.getX(i2), (int) motionEvent.getY(i2));
                                                View view = this.f18s;
                                                if (r == view && J(view, pointerId2)) {
                                                    i = this.c;
                                                    break;
                                                }
                                            }
                                            i2++;
                                        } else {
                                            i = -1;
                                            break;
                                        }
                                    }
                                    if (i == -1) {
                                        B();
                                    }
                                }
                                h(pointerId);
                                return;
                            }
                            return;
                        }
                        int pointerId3 = motionEvent.getPointerId(actionIndex);
                        float x = motionEvent.getX(actionIndex);
                        float y = motionEvent.getY(actionIndex);
                        D(x, y, pointerId3);
                        if (this.a == 0) {
                            J(r((int) x, (int) y), pointerId3);
                            int i3 = this.h[pointerId3];
                            int i4 = this.p;
                            if ((i3 & i4) != 0) {
                                this.r.onEdgeTouched(i3 & i4, pointerId3);
                                return;
                            }
                            return;
                        } else if (w((int) x, (int) y)) {
                            J(this.f18s, pointerId3);
                            return;
                        } else {
                            return;
                        }
                    }
                    if (this.a == 1) {
                        n(0.0f, 0.0f);
                    }
                    a();
                    return;
                } else if (this.a == 1) {
                    if (y(this.c)) {
                        int findPointerIndex = motionEvent.findPointerIndex(this.c);
                        float x2 = motionEvent.getX(findPointerIndex);
                        float y2 = motionEvent.getY(findPointerIndex);
                        float[] fArr = this.f;
                        int i5 = this.c;
                        int i6 = (int) (x2 - fArr[i5]);
                        int i7 = (int) (y2 - this.g[i5]);
                        p(this.f18s.getLeft() + i6, this.f18s.getTop() + i7, i6, i7);
                        E(motionEvent);
                        return;
                    }
                    return;
                } else {
                    int pointerCount2 = motionEvent.getPointerCount();
                    while (i2 < pointerCount2) {
                        int pointerId4 = motionEvent.getPointerId(i2);
                        if (y(pointerId4)) {
                            float x3 = motionEvent.getX(i2);
                            float y3 = motionEvent.getY(i2);
                            float f = x3 - this.d[pointerId4];
                            float f2 = y3 - this.e[pointerId4];
                            C(f, f2, pointerId4);
                            if (this.a != 1) {
                                View r2 = r((int) x3, (int) y3);
                                if (d(r2, f, f2) && J(r2, pointerId4)) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        i2++;
                    }
                    E(motionEvent);
                    return;
                }
            }
            if (this.a == 1) {
                B();
            }
            a();
            return;
        }
        float x4 = motionEvent.getX();
        float y4 = motionEvent.getY();
        int pointerId5 = motionEvent.getPointerId(0);
        View r3 = r((int) x4, (int) y4);
        D(x4, y4, pointerId5);
        J(r3, pointerId5);
        int i8 = this.h[pointerId5];
        int i9 = this.p;
        if ((i8 & i9) != 0) {
            this.r.onEdgeTouched(i8 & i9, pointerId5);
        }
    }

    public final void B() {
        this.l.computeCurrentVelocity(1000, this.m);
        n(e(this.l.getXVelocity(this.c), this.n, this.m), e(this.l.getYVelocity(this.c), this.n, this.m));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r3v3, types: [androidx.customview.widget.c$c] */
    public final void C(float f, float f2, int i) {
        boolean c = c(f, f2, i, 1);
        boolean z = c;
        if (c(f2, f, i, 4)) {
            z = c | true;
        }
        boolean z2 = z;
        if (c(f, f2, i, 2)) {
            z2 = (z ? 1 : 0) | true;
        }
        ?? r0 = z2;
        if (c(f2, f, i, 8)) {
            r0 = (z2 ? 1 : 0) | true;
        }
        if (r0 != 0) {
            int[] iArr = this.i;
            iArr[i] = iArr[i] | r0;
            this.r.onEdgeDragStarted(r0, i);
        }
    }

    public final void D(float f, float f2, int i) {
        q(i);
        float[] fArr = this.d;
        this.f[i] = f;
        fArr[i] = f;
        float[] fArr2 = this.e;
        this.g[i] = f2;
        fArr2[i] = f2;
        this.h[i] = t((int) f, (int) f2);
        this.k |= 1 << i;
    }

    public final void E(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (y(pointerId)) {
                float x = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.f[pointerId] = x;
                this.g[pointerId] = y;
            }
        }
    }

    public void F(int i) {
        this.u.removeCallbacks(this.v);
        if (this.a != i) {
            this.a = i;
            this.r.onViewDragStateChanged(i);
            if (this.a == 0) {
                this.f18s = null;
            }
        }
    }

    public boolean G(int i, int i2) {
        if (this.t) {
            return s(i, i2, (int) this.l.getXVelocity(this.c), (int) this.l.getYVelocity(this.c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00dd, code lost:
        if (r12 != r11) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean H(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.c.H(android.view.MotionEvent):boolean");
    }

    public boolean I(View view, int i, int i2) {
        this.f18s = view;
        this.c = -1;
        boolean s2 = s(i, i2, 0, 0);
        if (!s2 && this.a == 0 && this.f18s != null) {
            this.f18s = null;
        }
        return s2;
    }

    public boolean J(View view, int i) {
        if (view == this.f18s && this.c == i) {
            return true;
        }
        if (view != null && this.r.tryCaptureView(view, i)) {
            this.c = i;
            b(view, i);
            return true;
        }
        return false;
    }

    public void a() {
        this.c = -1;
        g();
        VelocityTracker velocityTracker = this.l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.l = null;
        }
    }

    public void b(View view, int i) {
        if (view.getParent() == this.u) {
            this.f18s = view;
            this.c = i;
            this.r.onViewCaptured(view, i);
            F(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.u + ")");
    }

    public final boolean c(float f, float f2, int i, int i2) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if ((this.h[i] & i2) != i2 || (this.p & i2) == 0 || (this.j[i] & i2) == i2 || (this.i[i] & i2) == i2) {
            return false;
        }
        int i3 = this.b;
        if (abs <= i3 && abs2 <= i3) {
            return false;
        }
        if (abs < abs2 * 0.5f && this.r.onEdgeLock(i2)) {
            int[] iArr = this.j;
            iArr[i] = iArr[i] | i2;
            return false;
        } else if ((this.i[i] & i2) != 0 || abs <= this.b) {
            return false;
        } else {
            return true;
        }
    }

    public final boolean d(View view, float f, float f2) {
        boolean z;
        boolean z2;
        if (view == null) {
            return false;
        }
        if (this.r.getViewHorizontalDragRange(view) > 0) {
            z = true;
        } else {
            z = false;
        }
        if (this.r.getViewVerticalDragRange(view) > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z && z2) {
            int i = this.b;
            if ((f * f) + (f2 * f2) <= i * i) {
                return false;
            }
            return true;
        } else if (z) {
            if (Math.abs(f) <= this.b) {
                return false;
            }
            return true;
        } else if (!z2 || Math.abs(f2) <= this.b) {
            return false;
        } else {
            return true;
        }
    }

    public final float e(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        if (abs > f3) {
            if (f <= 0.0f) {
                return -f3;
            }
            return f3;
        }
        return f;
    }

    public final int f(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        if (abs > i3) {
            if (i <= 0) {
                return -i3;
            }
            return i3;
        }
        return i;
    }

    public final void g() {
        float[] fArr = this.d;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.e, 0.0f);
        Arrays.fill(this.f, 0.0f);
        Arrays.fill(this.g, 0.0f);
        Arrays.fill(this.h, 0);
        Arrays.fill(this.i, 0);
        Arrays.fill(this.j, 0);
        this.k = 0;
    }

    public final void h(int i) {
        if (this.d != null && x(i)) {
            this.d[i] = 0.0f;
            this.e[i] = 0.0f;
            this.f[i] = 0.0f;
            this.g[i] = 0.0f;
            this.h[i] = 0;
            this.i[i] = 0;
            this.j[i] = 0;
            this.k = ((1 << i) ^ (-1)) & this.k;
        }
    }

    public final int i(int i, int i2, int i3) {
        int abs;
        if (i == 0) {
            return 0;
        }
        int width = this.u.getWidth();
        float f = width / 2;
        float o = f + (o(Math.min(1.0f, Math.abs(i) / width)) * f);
        int abs2 = Math.abs(i2);
        if (abs2 > 0) {
            abs = Math.round(Math.abs(o / abs2) * 1000.0f) * 4;
        } else {
            abs = (int) (((Math.abs(i) / i3) + 1.0f) * 256.0f);
        }
        return Math.min(abs, 600);
    }

    public final int j(View view, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        float f4;
        int f5 = f(i3, (int) this.n, (int) this.m);
        int f6 = f(i4, (int) this.n, (int) this.m);
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        int abs3 = Math.abs(f5);
        int abs4 = Math.abs(f6);
        int i5 = abs3 + abs4;
        int i6 = abs + abs2;
        if (f5 != 0) {
            f = abs3;
            f2 = i5;
        } else {
            f = abs;
            f2 = i6;
        }
        float f7 = f / f2;
        if (f6 != 0) {
            f3 = abs4;
            f4 = i5;
        } else {
            f3 = abs2;
            f4 = i6;
        }
        return (int) ((i(i, f5, this.r.getViewHorizontalDragRange(view)) * f7) + (i(i2, f6, this.r.getViewVerticalDragRange(view)) * (f3 / f4)));
    }

    public boolean k(boolean z) {
        if (this.a == 2) {
            boolean computeScrollOffset = this.q.computeScrollOffset();
            int currX = this.q.getCurrX();
            int currY = this.q.getCurrY();
            int left = currX - this.f18s.getLeft();
            int top = currY - this.f18s.getTop();
            if (left != 0) {
                d1.V(this.f18s, left);
            }
            if (top != 0) {
                d1.W(this.f18s, top);
            }
            if (left != 0 || top != 0) {
                this.r.onViewPositionChanged(this.f18s, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.q.getFinalX() && currY == this.q.getFinalY()) {
                this.q.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                if (z) {
                    this.u.post(this.v);
                } else {
                    F(0);
                }
            }
        }
        if (this.a != 2) {
            return false;
        }
        return true;
    }

    public final void n(float f, float f2) {
        this.t = true;
        this.r.onViewReleased(this.f18s, f, f2);
        this.t = false;
        if (this.a == 1) {
            F(0);
        }
    }

    public final float o(float f) {
        return (float) Math.sin((f - 0.5f) * 0.47123894f);
    }

    public final void p(int i, int i2, int i3, int i4) {
        int left = this.f18s.getLeft();
        int top = this.f18s.getTop();
        if (i3 != 0) {
            i = this.r.clampViewPositionHorizontal(this.f18s, i, i3);
            d1.V(this.f18s, i - left);
        }
        int i5 = i;
        if (i4 != 0) {
            i2 = this.r.clampViewPositionVertical(this.f18s, i2, i4);
            d1.W(this.f18s, i2 - top);
        }
        int i6 = i2;
        if (i3 != 0 || i4 != 0) {
            this.r.onViewPositionChanged(this.f18s, i5, i6, i5 - left, i6 - top);
        }
    }

    public final void q(int i) {
        float[] fArr = this.d;
        if (fArr == null || fArr.length <= i) {
            int i2 = i + 1;
            float[] fArr2 = new float[i2];
            float[] fArr3 = new float[i2];
            float[] fArr4 = new float[i2];
            float[] fArr5 = new float[i2];
            int[] iArr = new int[i2];
            int[] iArr2 = new int[i2];
            int[] iArr3 = new int[i2];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.e;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.g;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.d = fArr2;
            this.e = fArr3;
            this.f = fArr4;
            this.g = fArr5;
            this.h = iArr;
            this.i = iArr2;
            this.j = iArr3;
        }
    }

    public View r(int i, int i2) {
        for (int childCount = this.u.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.u.getChildAt(this.r.getOrderedChildIndex(childCount));
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public final boolean s(int i, int i2, int i3, int i4) {
        int left = this.f18s.getLeft();
        int top = this.f18s.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.q.abortAnimation();
            F(0);
            return false;
        }
        this.q.startScroll(left, top, i5, i6, j(this.f18s, i5, i6, i3, i4));
        F(2);
        return true;
    }

    public final int t(int i, int i2) {
        int i3;
        if (i < this.u.getLeft() + this.o) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        if (i2 < this.u.getTop() + this.o) {
            i3 |= 4;
        }
        if (i > this.u.getRight() - this.o) {
            i3 |= 2;
        }
        if (i2 > this.u.getBottom() - this.o) {
            return i3 | 8;
        }
        return i3;
    }

    public int u() {
        return this.b;
    }

    public int v() {
        return this.a;
    }

    public boolean w(int i, int i2) {
        return z(this.f18s, i, i2);
    }

    public boolean x(int i) {
        if (((1 << i) & this.k) != 0) {
            return true;
        }
        return false;
    }

    public final boolean y(int i) {
        if (!x(i)) {
            Log.e("ViewDragHelper", "Ignoring pointerId=" + i + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
            return false;
        }
        return true;
    }

    public boolean z(View view, int i, int i2) {
        if (view == null || i < view.getLeft() || i >= view.getRight() || i2 < view.getTop() || i2 >= view.getBottom()) {
            return false;
        }
        return true;
    }
}
