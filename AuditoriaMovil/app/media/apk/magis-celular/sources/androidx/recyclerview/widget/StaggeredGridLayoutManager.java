package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import w.g0;
/* loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.o implements RecyclerView.z.b {
    public f[] b;
    public m c;
    public m d;
    public int e;
    public int f;
    public final i g;
    public BitSet j;
    public boolean o;
    public boolean p;
    public e q;
    public int r;
    public int[] w;
    public int a = -1;
    public boolean h = false;
    public boolean i = false;
    public int k = -1;
    public int l = Integer.MIN_VALUE;
    public d m = new d();
    public int n = 2;

    /* renamed from: s  reason: collision with root package name */
    public final Rect f25s = new Rect();
    public final b t = new b();
    public boolean u = false;
    public boolean v = true;
    public final Runnable x = new a();

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.m();
        }
    }

    /* loaded from: classes.dex */
    public class b {
        public int a;
        public int b;
        public boolean c;
        public boolean d;
        public boolean e;
        public int[] f;

        public b() {
            c();
        }

        public void a() {
            int m;
            if (this.c) {
                m = StaggeredGridLayoutManager.this.c.i();
            } else {
                m = StaggeredGridLayoutManager.this.c.m();
            }
            this.b = m;
        }

        public void b(int i) {
            if (this.c) {
                this.b = StaggeredGridLayoutManager.this.c.i() - i;
            } else {
                this.b = StaggeredGridLayoutManager.this.c.m() + i;
            }
        }

        public void c() {
            this.a = -1;
            this.b = Integer.MIN_VALUE;
            this.c = false;
            this.d = false;
            this.e = false;
            int[] iArr = this.f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        public void d(f[] fVarArr) {
            int length = fVarArr.length;
            int[] iArr = this.f;
            if (iArr == null || iArr.length < length) {
                this.f = new int[StaggeredGridLayoutManager.this.b.length];
            }
            for (int i = 0; i < length; i++) {
                this.f[i] = fVarArr[i].r(Integer.MIN_VALUE);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c extends RecyclerView.p {
        public f e;
        public boolean f;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int e() {
            f fVar = this.e;
            if (fVar == null) {
                return -1;
            }
            return fVar.e;
        }

        public boolean f() {
            return this.f;
        }

        public void g(boolean z) {
            this.f = z;
        }

        public c(int i, int i2) {
            super(i, i2);
        }

        public c(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    /* loaded from: classes.dex */
    public static class e implements Parcelable {
        public static final Parcelable.Creator<e> CREATOR = new a();
        public int a;
        public int b;
        public int c;
        public int[] d;
        public int e;
        public int[] f;
        public List g;
        public boolean h;
        public boolean i;
        public boolean j;

        /* loaded from: classes.dex */
        public static class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public e createFromParcel(Parcel parcel) {
                return new e(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public e[] newArray(int i) {
                return new e[i];
            }
        }

        public e() {
        }

        public void a() {
            this.d = null;
            this.c = 0;
            this.a = -1;
            this.b = -1;
        }

        public void b() {
            this.d = null;
            this.c = 0;
            this.e = 0;
            this.f = null;
            this.g = null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            if (this.c > 0) {
                parcel.writeIntArray(this.d);
            }
            parcel.writeInt(this.e);
            if (this.e > 0) {
                parcel.writeIntArray(this.f);
            }
            parcel.writeInt(this.h ? 1 : 0);
            parcel.writeInt(this.i ? 1 : 0);
            parcel.writeInt(this.j ? 1 : 0);
            parcel.writeList(this.g);
        }

        public e(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            int readInt = parcel.readInt();
            this.c = readInt;
            if (readInt > 0) {
                int[] iArr = new int[readInt];
                this.d = iArr;
                parcel.readIntArray(iArr);
            }
            int readInt2 = parcel.readInt();
            this.e = readInt2;
            if (readInt2 > 0) {
                int[] iArr2 = new int[readInt2];
                this.f = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.h = parcel.readInt() == 1;
            this.i = parcel.readInt() == 1;
            this.j = parcel.readInt() == 1;
            this.g = parcel.readArrayList(d.a.class.getClassLoader());
        }

        public e(e eVar) {
            this.c = eVar.c;
            this.a = eVar.a;
            this.b = eVar.b;
            this.d = eVar.d;
            this.e = eVar.e;
            this.f = eVar.f;
            this.h = eVar.h;
            this.i = eVar.i;
            this.j = eVar.j;
            this.g = eVar.g;
        }
    }

    /* loaded from: classes.dex */
    public class f {
        public ArrayList a = new ArrayList();
        public int b = Integer.MIN_VALUE;
        public int c = Integer.MIN_VALUE;
        public int d = 0;
        public final int e;

        public f(int i) {
            this.e = i;
        }

        public void a(View view) {
            c p = p(view);
            p.e = this;
            this.a.add(view);
            this.c = Integer.MIN_VALUE;
            if (this.a.size() == 1) {
                this.b = Integer.MIN_VALUE;
            }
            if (p.c() || p.b()) {
                this.d += StaggeredGridLayoutManager.this.c.e(view);
            }
        }

        public void b(boolean z, int i) {
            int r;
            if (z) {
                r = n(Integer.MIN_VALUE);
            } else {
                r = r(Integer.MIN_VALUE);
            }
            e();
            if (r == Integer.MIN_VALUE) {
                return;
            }
            if (!z || r >= StaggeredGridLayoutManager.this.c.i()) {
                if (!z && r > StaggeredGridLayoutManager.this.c.m()) {
                    return;
                }
                if (i != Integer.MIN_VALUE) {
                    r += i;
                }
                this.c = r;
                this.b = r;
            }
        }

        public void c() {
            d.a f;
            ArrayList arrayList = this.a;
            View view = (View) arrayList.get(arrayList.size() - 1);
            c p = p(view);
            this.c = StaggeredGridLayoutManager.this.c.d(view);
            if (p.f && (f = StaggeredGridLayoutManager.this.m.f(p.a())) != null && f.b == 1) {
                this.c += f.a(this.e);
            }
        }

        public void d() {
            d.a f;
            View view = (View) this.a.get(0);
            c p = p(view);
            this.b = StaggeredGridLayoutManager.this.c.g(view);
            if (p.f && (f = StaggeredGridLayoutManager.this.m.f(p.a())) != null && f.b == -1) {
                this.b -= f.a(this.e);
            }
        }

        public void e() {
            this.a.clear();
            s();
            this.d = 0;
        }

        public int f() {
            if (StaggeredGridLayoutManager.this.h) {
                return j(this.a.size() - 1, -1, true);
            }
            return j(0, this.a.size(), true);
        }

        public int g() {
            if (StaggeredGridLayoutManager.this.h) {
                return k(0, this.a.size(), true);
            }
            return k(this.a.size() - 1, -1, true);
        }

        public int h() {
            if (StaggeredGridLayoutManager.this.h) {
                return j(0, this.a.size(), true);
            }
            return j(this.a.size() - 1, -1, true);
        }

        public int i(int i, int i2, boolean z, boolean z2, boolean z3) {
            int i3;
            boolean z4;
            int m = StaggeredGridLayoutManager.this.c.m();
            int i4 = StaggeredGridLayoutManager.this.c.i();
            if (i2 > i) {
                i3 = 1;
            } else {
                i3 = -1;
            }
            while (i != i2) {
                View view = (View) this.a.get(i);
                int g = StaggeredGridLayoutManager.this.c.g(view);
                int d = StaggeredGridLayoutManager.this.c.d(view);
                boolean z5 = false;
                if (!z3 ? g < i4 : g <= i4) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (!z3 ? d > m : d >= m) {
                    z5 = true;
                }
                if (z4 && z5) {
                    if (z && z2) {
                        if (g >= m && d <= i4) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                    } else if (z2) {
                        return StaggeredGridLayoutManager.this.getPosition(view);
                    } else {
                        if (g < m || d > i4) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                    }
                }
                i += i3;
            }
            return -1;
        }

        public int j(int i, int i2, boolean z) {
            return i(i, i2, false, false, z);
        }

        public int k(int i, int i2, boolean z) {
            return i(i, i2, z, true, false);
        }

        public int l() {
            return this.d;
        }

        public int m() {
            int i = this.c;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            c();
            return this.c;
        }

        public int n(int i) {
            int i2 = this.c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.a.size() == 0) {
                return i;
            }
            c();
            return this.c;
        }

        public View o(int i, int i2) {
            View view = null;
            if (i2 == -1) {
                int size = this.a.size();
                int i3 = 0;
                while (i3 < size) {
                    View view2 = (View) this.a.get(i3);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.h && staggeredGridLayoutManager.getPosition(view2) <= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.h && staggeredGridLayoutManager2.getPosition(view2) >= i) || !view2.hasFocusable()) {
                        break;
                    }
                    i3++;
                    view = view2;
                }
            } else {
                int size2 = this.a.size() - 1;
                while (size2 >= 0) {
                    View view3 = (View) this.a.get(size2);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.h && staggeredGridLayoutManager3.getPosition(view3) >= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.h && staggeredGridLayoutManager4.getPosition(view3) <= i) || !view3.hasFocusable()) {
                        break;
                    }
                    size2--;
                    view = view3;
                }
            }
            return view;
        }

        public c p(View view) {
            return (c) view.getLayoutParams();
        }

        public int q() {
            int i = this.b;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            d();
            return this.b;
        }

        public int r(int i) {
            int i2 = this.b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.a.size() == 0) {
                return i;
            }
            d();
            return this.b;
        }

        public void s() {
            this.b = Integer.MIN_VALUE;
            this.c = Integer.MIN_VALUE;
        }

        public void t(int i) {
            int i2 = this.b;
            if (i2 != Integer.MIN_VALUE) {
                this.b = i2 + i;
            }
            int i3 = this.c;
            if (i3 != Integer.MIN_VALUE) {
                this.c = i3 + i;
            }
        }

        public void u() {
            int size = this.a.size();
            View view = (View) this.a.remove(size - 1);
            c p = p(view);
            p.e = null;
            if (p.c() || p.b()) {
                this.d -= StaggeredGridLayoutManager.this.c.e(view);
            }
            if (size == 1) {
                this.b = Integer.MIN_VALUE;
            }
            this.c = Integer.MIN_VALUE;
        }

        public void v() {
            View view = (View) this.a.remove(0);
            c p = p(view);
            p.e = null;
            if (this.a.size() == 0) {
                this.c = Integer.MIN_VALUE;
            }
            if (p.c() || p.b()) {
                this.d -= StaggeredGridLayoutManager.this.c.e(view);
            }
            this.b = Integer.MIN_VALUE;
        }

        public void w(View view) {
            c p = p(view);
            p.e = this;
            this.a.add(0, view);
            this.b = Integer.MIN_VALUE;
            if (this.a.size() == 1) {
                this.c = Integer.MIN_VALUE;
            }
            if (p.c() || p.b()) {
                this.d += StaggeredGridLayoutManager.this.c.e(view);
            }
        }

        public void x(int i) {
            this.b = i;
            this.c = i;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        RecyclerView.o.d properties = RecyclerView.o.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.a);
        setSpanCount(properties.b);
        setReverseLayout(properties.c);
        this.g = new i();
        t();
    }

    public final int A(int i) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            int position = getPosition(getChildAt(childCount));
            if (position >= 0 && position < i) {
                return position;
            }
        }
        return 0;
    }

    public final void B(RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int i;
        int F = F(Integer.MIN_VALUE);
        if (F != Integer.MIN_VALUE && (i = this.c.i() - F) > 0) {
            int i2 = i - (-scrollBy(-i, vVar, a0Var));
            if (z && i2 > 0) {
                this.c.r(i2);
            }
        }
    }

    public final void C(RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int m;
        int I = I(Integer.MAX_VALUE);
        if (I != Integer.MAX_VALUE && (m = I - this.c.m()) > 0) {
            int scrollBy = m - scrollBy(m, vVar, a0Var);
            if (z && scrollBy > 0) {
                this.c.r(-scrollBy);
            }
        }
    }

    public int D() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    public int E() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    public final int F(int i) {
        int n = this.b[0].n(i);
        for (int i2 = 1; i2 < this.a; i2++) {
            int n2 = this.b[i2].n(i);
            if (n2 > n) {
                n = n2;
            }
        }
        return n;
    }

    public final int G(int i) {
        int r = this.b[0].r(i);
        for (int i2 = 1; i2 < this.a; i2++) {
            int r2 = this.b[i2].r(i);
            if (r2 > r) {
                r = r2;
            }
        }
        return r;
    }

    public final int H(int i) {
        int n = this.b[0].n(i);
        for (int i2 = 1; i2 < this.a; i2++) {
            int n2 = this.b[i2].n(i);
            if (n2 < n) {
                n = n2;
            }
        }
        return n;
    }

    public final int I(int i) {
        int r = this.b[0].r(i);
        for (int i2 = 1; i2 < this.a; i2++) {
            int r2 = this.b[i2].r(i);
            if (r2 < r) {
                r = r2;
            }
        }
        return r;
    }

    public final f J(i iVar) {
        int i;
        int i2;
        int i3;
        if (Q(iVar.e)) {
            i2 = this.a - 1;
            i = -1;
            i3 = -1;
        } else {
            i = this.a;
            i2 = 0;
            i3 = 1;
        }
        f fVar = null;
        if (iVar.e == 1) {
            int m = this.c.m();
            int i4 = Integer.MAX_VALUE;
            while (i2 != i) {
                f fVar2 = this.b[i2];
                int n = fVar2.n(m);
                if (n < i4) {
                    fVar = fVar2;
                    i4 = n;
                }
                i2 += i3;
            }
            return fVar;
        }
        int i5 = this.c.i();
        int i6 = Integer.MIN_VALUE;
        while (i2 != i) {
            f fVar3 = this.b[i2];
            int r = fVar3.r(i5);
            if (r > i6) {
                fVar = fVar3;
                i6 = r;
            }
            i2 += i3;
        }
        return fVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void K(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.i
            if (r0 == 0) goto L9
            int r0 = r6.E()
            goto Ld
        L9:
            int r0 = r6.D()
        Ld:
            r1 = 8
            if (r9 != r1) goto L1a
            if (r7 >= r8) goto L16
            int r2 = r8 + 1
            goto L1c
        L16:
            int r2 = r7 + 1
            r3 = r8
            goto L1d
        L1a:
            int r2 = r7 + r8
        L1c:
            r3 = r7
        L1d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r4 = r6.m
            r4.h(r3)
            r4 = 1
            if (r9 == r4) goto L3c
            r5 = 2
            if (r9 == r5) goto L36
            if (r9 == r1) goto L2b
            goto L41
        L2b:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r6.m
            r9.k(r7, r4)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r7 = r6.m
            r7.j(r8, r4)
            goto L41
        L36:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r6.m
            r9.k(r7, r8)
            goto L41
        L3c:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r6.m
            r9.j(r7, r8)
        L41:
            if (r2 > r0) goto L44
            return
        L44:
            boolean r7 = r6.i
            if (r7 == 0) goto L4d
            int r7 = r6.D()
            goto L51
        L4d:
            int r7 = r6.E()
        L51:
            if (r3 > r7) goto L56
            r6.requestLayout()
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.K(int, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0074, code lost:
        if (r10 == r11) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0086, code lost:
        if (r10 == r11) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0088, code lost:
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008a, code lost:
        r10 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View L() {
        /*
            r12 = this;
            int r0 = r12.getChildCount()
            r1 = 1
            int r0 = r0 - r1
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r12.a
            r2.<init>(r3)
            int r3 = r12.a
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r12.e
            r5 = -1
            if (r3 != r1) goto L20
            boolean r3 = r12.isLayoutRTL()
            if (r3 == 0) goto L20
            r3 = 1
            goto L21
        L20:
            r3 = -1
        L21:
            boolean r6 = r12.i
            if (r6 == 0) goto L27
            r6 = -1
            goto L2b
        L27:
            int r0 = r0 + 1
            r6 = r0
            r0 = 0
        L2b:
            if (r0 >= r6) goto L2e
            r5 = 1
        L2e:
            if (r0 == r6) goto Lab
            android.view.View r7 = r12.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r8 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.c) r8
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r9 = r8.e
            int r9 = r9.e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r9 = r8.e
            boolean r9 = r12.n(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r9 = r8.e
            int r9 = r9.e
            r2.clear(r9)
        L54:
            boolean r9 = r8.f
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.getChildAt(r9)
            boolean r10 = r12.i
            if (r10 == 0) goto L77
            androidx.recyclerview.widget.m r10 = r12.c
            int r10 = r10.d(r7)
            androidx.recyclerview.widget.m r11 = r12.c
            int r11 = r11.d(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            androidx.recyclerview.widget.m r10 = r12.c
            int r10 = r10.g(r7)
            androidx.recyclerview.widget.m r11 = r12.c
            int r11 = r11.g(r9)
            if (r10 <= r11) goto L86
            return r7
        L86:
            if (r10 != r11) goto L8a
        L88:
            r10 = 1
            goto L8b
        L8a:
            r10 = 0
        L8b:
            if (r10 == 0) goto La9
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.c) r9
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r8 = r8.e
            int r8 = r8.e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r9 = r9.e
            int r9 = r9.e
            int r8 = r8 - r9
            if (r8 >= 0) goto La0
            r8 = 1
            goto La1
        La0:
            r8 = 0
        La1:
            if (r3 >= 0) goto La5
            r9 = 1
            goto La6
        La5:
            r9 = 0
        La6:
            if (r8 == r9) goto La9
            return r7
        La9:
            int r0 = r0 + r5
            goto L2e
        Lab:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.L():android.view.View");
    }

    public void M() {
        this.m.b();
        requestLayout();
    }

    public final void N(View view, int i, int i2, boolean z) {
        boolean shouldMeasureChild;
        calculateItemDecorationsForChild(view, this.f25s);
        c cVar = (c) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.f25s;
        int g0 = g0(i, i3 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i4 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.f25s;
        int g02 = g0(i2, i4 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z) {
            shouldMeasureChild = shouldReMeasureChild(view, g0, g02, cVar);
        } else {
            shouldMeasureChild = shouldMeasureChild(view, g0, g02, cVar);
        }
        if (shouldMeasureChild) {
            view.measure(g0, g02);
        }
    }

    public final void O(View view, c cVar, boolean z) {
        if (cVar.f) {
            if (this.e == 1) {
                N(view, this.r, RecyclerView.o.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
            } else {
                N(view, RecyclerView.o.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) cVar).width, true), this.r, z);
            }
        } else if (this.e == 1) {
            N(view, RecyclerView.o.getChildMeasureSpec(this.f, getWidthMode(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, false), RecyclerView.o.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
        } else {
            N(view, RecyclerView.o.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) cVar).width, true), RecyclerView.o.getChildMeasureSpec(this.f, getHeightMode(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, false), z);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:87:0x0157, code lost:
        if (m() != false) goto L83;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void P(androidx.recyclerview.widget.RecyclerView.v r9, androidx.recyclerview.widget.RecyclerView.a0 r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 379
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.P(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$a0, boolean):void");
    }

    public final boolean Q(int i) {
        boolean z;
        boolean z2;
        boolean z3;
        if (this.e == 0) {
            if (i == -1) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3 != this.i) {
                return true;
            }
            return false;
        }
        if (i == -1) {
            z = true;
        } else {
            z = false;
        }
        if (z == this.i) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 == isLayoutRTL()) {
            return true;
        }
        return false;
    }

    public void R(int i, RecyclerView.a0 a0Var) {
        int D;
        int i2;
        if (i > 0) {
            D = E();
            i2 = 1;
        } else {
            D = D();
            i2 = -1;
        }
        this.g.a = true;
        d0(D, a0Var);
        Y(i2);
        i iVar = this.g;
        iVar.c = D + iVar.d;
        iVar.b = Math.abs(i);
    }

    public final void S(View view) {
        for (int i = this.a - 1; i >= 0; i--) {
            this.b[i].w(view);
        }
    }

    public final void T(RecyclerView.v vVar, i iVar) {
        int min;
        int min2;
        if (iVar.a && !iVar.i) {
            if (iVar.b == 0) {
                if (iVar.e == -1) {
                    U(vVar, iVar.g);
                } else {
                    V(vVar, iVar.f);
                }
            } else if (iVar.e == -1) {
                int i = iVar.f;
                int G = i - G(i);
                if (G < 0) {
                    min2 = iVar.g;
                } else {
                    min2 = iVar.g - Math.min(G, iVar.b);
                }
                U(vVar, min2);
            } else {
                int H = H(iVar.g) - iVar.g;
                if (H < 0) {
                    min = iVar.f;
                } else {
                    min = Math.min(H, iVar.b) + iVar.f;
                }
                V(vVar, min);
            }
        }
    }

    public final void U(RecyclerView.v vVar, int i) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (this.c.g(childAt) >= i && this.c.q(childAt) >= i) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f) {
                    for (int i2 = 0; i2 < this.a; i2++) {
                        if (this.b[i2].a.size() == 1) {
                            return;
                        }
                    }
                    for (int i3 = 0; i3 < this.a; i3++) {
                        this.b[i3].u();
                    }
                } else if (cVar.e.a.size() == 1) {
                    return;
                } else {
                    cVar.e.u();
                }
                removeAndRecycleView(childAt, vVar);
            } else {
                return;
            }
        }
    }

    public final void V(RecyclerView.v vVar, int i) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.c.d(childAt) <= i && this.c.p(childAt) <= i) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f) {
                    for (int i2 = 0; i2 < this.a; i2++) {
                        if (this.b[i2].a.size() == 1) {
                            return;
                        }
                    }
                    for (int i3 = 0; i3 < this.a; i3++) {
                        this.b[i3].v();
                    }
                } else if (cVar.e.a.size() == 1) {
                    return;
                } else {
                    cVar.e.v();
                }
                removeAndRecycleView(childAt, vVar);
            } else {
                return;
            }
        }
    }

    public final void W() {
        if (this.d.k() == 1073741824) {
            return;
        }
        int childCount = getChildCount();
        float f2 = 0.0f;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            float e2 = this.d.e(childAt);
            if (e2 >= f2) {
                if (((c) childAt.getLayoutParams()).f()) {
                    e2 = (e2 * 1.0f) / this.a;
                }
                f2 = Math.max(f2, e2);
            }
        }
        int i2 = this.f;
        int round = Math.round(f2 * this.a);
        if (this.d.k() == Integer.MIN_VALUE) {
            round = Math.min(round, this.d.n());
        }
        e0(round);
        if (this.f == i2) {
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt2 = getChildAt(i3);
            c cVar = (c) childAt2.getLayoutParams();
            if (!cVar.f) {
                if (isLayoutRTL() && this.e == 1) {
                    int i4 = this.a;
                    int i5 = cVar.e.e;
                    childAt2.offsetLeftAndRight(((-((i4 - 1) - i5)) * this.f) - ((-((i4 - 1) - i5)) * i2));
                } else {
                    int i6 = cVar.e.e;
                    int i7 = this.f * i6;
                    int i8 = i6 * i2;
                    if (this.e == 1) {
                        childAt2.offsetLeftAndRight(i7 - i8);
                    } else {
                        childAt2.offsetTopAndBottom(i7 - i8);
                    }
                }
            }
        }
    }

    public final void X() {
        if (this.e != 1 && isLayoutRTL()) {
            this.i = !this.h;
        } else {
            this.i = this.h;
        }
    }

    public final void Y(int i) {
        boolean z;
        i iVar = this.g;
        iVar.e = i;
        boolean z2 = this.i;
        int i2 = 1;
        if (i == -1) {
            z = true;
        } else {
            z = false;
        }
        if (z2 != z) {
            i2 = -1;
        }
        iVar.d = i2;
    }

    public final void Z(int i, int i2) {
        for (int i3 = 0; i3 < this.a; i3++) {
            if (!this.b[i3].a.isEmpty()) {
                f0(this.b[i3], i, i2);
            }
        }
    }

    public final boolean a0(RecyclerView.a0 a0Var, b bVar) {
        int v;
        if (this.o) {
            v = A(a0Var.b());
        } else {
            v = v(a0Var.b());
        }
        bVar.a = v;
        bVar.b = Integer.MIN_VALUE;
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void assertNotInLayoutOrScroll(String str) {
        if (this.q == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    public boolean b0(RecyclerView.a0 a0Var, b bVar) {
        int i;
        int D;
        int m;
        boolean z = false;
        if (!a0Var.e() && (i = this.k) != -1) {
            if (i >= 0 && i < a0Var.b()) {
                e eVar = this.q;
                if (eVar != null && eVar.a != -1 && eVar.c >= 1) {
                    bVar.b = Integer.MIN_VALUE;
                    bVar.a = this.k;
                } else {
                    View findViewByPosition = findViewByPosition(this.k);
                    if (findViewByPosition != null) {
                        if (this.i) {
                            D = E();
                        } else {
                            D = D();
                        }
                        bVar.a = D;
                        if (this.l != Integer.MIN_VALUE) {
                            if (bVar.c) {
                                bVar.b = (this.c.i() - this.l) - this.c.d(findViewByPosition);
                            } else {
                                bVar.b = (this.c.m() + this.l) - this.c.g(findViewByPosition);
                            }
                            return true;
                        } else if (this.c.e(findViewByPosition) > this.c.n()) {
                            if (bVar.c) {
                                m = this.c.i();
                            } else {
                                m = this.c.m();
                            }
                            bVar.b = m;
                            return true;
                        } else {
                            int g = this.c.g(findViewByPosition) - this.c.m();
                            if (g < 0) {
                                bVar.b = -g;
                                return true;
                            }
                            int i2 = this.c.i() - this.c.d(findViewByPosition);
                            if (i2 < 0) {
                                bVar.b = i2;
                                return true;
                            }
                            bVar.b = Integer.MIN_VALUE;
                        }
                    } else {
                        int i3 = this.k;
                        bVar.a = i3;
                        int i4 = this.l;
                        if (i4 == Integer.MIN_VALUE) {
                            if (l(i3) == 1) {
                                z = true;
                            }
                            bVar.c = z;
                            bVar.a();
                        } else {
                            bVar.b(i4);
                        }
                        bVar.d = true;
                    }
                }
                return true;
            }
            this.k = -1;
            this.l = Integer.MIN_VALUE;
        }
        return false;
    }

    public void c0(RecyclerView.a0 a0Var, b bVar) {
        if (b0(a0Var, bVar) || a0(a0Var, bVar)) {
            return;
        }
        bVar.a();
        bVar.a = 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean canScrollHorizontally() {
        if (this.e == 0) {
            return true;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean canScrollVertically() {
        if (this.e == 1) {
            return true;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean checkLayoutParams(RecyclerView.p pVar) {
        return pVar instanceof c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.a0 a0Var, RecyclerView.o.c cVar) {
        int n;
        int i3;
        if (this.e != 0) {
            i = i2;
        }
        if (getChildCount() != 0 && i != 0) {
            R(i, a0Var);
            int[] iArr = this.w;
            if (iArr == null || iArr.length < this.a) {
                this.w = new int[this.a];
            }
            int i4 = 0;
            for (int i5 = 0; i5 < this.a; i5++) {
                i iVar = this.g;
                if (iVar.d == -1) {
                    n = iVar.f;
                    i3 = this.b[i5].r(n);
                } else {
                    n = this.b[i5].n(iVar.g);
                    i3 = this.g.g;
                }
                int i6 = n - i3;
                if (i6 >= 0) {
                    this.w[i4] = i6;
                    i4++;
                }
            }
            Arrays.sort(this.w, 0, i4);
            for (int i7 = 0; i7 < i4 && this.g.a(a0Var); i7++) {
                cVar.a(this.g.c, this.w[i7]);
                i iVar2 = this.g;
                iVar2.c += iVar2.d;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollExtent(RecyclerView.a0 a0Var) {
        return o(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollOffset(RecyclerView.a0 a0Var) {
        return p(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollRange(RecyclerView.a0 a0Var) {
        return q(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.z.b
    public PointF computeScrollVectorForPosition(int i) {
        int l = l(i);
        PointF pointF = new PointF();
        if (l == 0) {
            return null;
        }
        if (this.e == 0) {
            pointF.x = l;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = l;
        }
        return pointF;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollExtent(RecyclerView.a0 a0Var) {
        return o(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollOffset(RecyclerView.a0 a0Var) {
        return p(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollRange(RecyclerView.a0 a0Var) {
        return q(a0Var);
    }

    public final int convertFocusDirectionToLayoutDirection(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 17) {
                    if (i != 33) {
                        if (i != 66) {
                            if (i == 130 && this.e == 1) {
                                return 1;
                            }
                            return Integer.MIN_VALUE;
                        } else if (this.e == 0) {
                            return 1;
                        } else {
                            return Integer.MIN_VALUE;
                        }
                    } else if (this.e == 1) {
                        return -1;
                    } else {
                        return Integer.MIN_VALUE;
                    }
                } else if (this.e == 0) {
                    return -1;
                } else {
                    return Integer.MIN_VALUE;
                }
            } else if (this.e != 1 && isLayoutRTL()) {
                return -1;
            } else {
                return 1;
            }
        } else if (this.e == 1 || !isLayoutRTL()) {
            return -1;
        } else {
            return 1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d0(int r5, androidx.recyclerview.widget.RecyclerView.a0 r6) {
        /*
            r4 = this;
            androidx.recyclerview.widget.i r0 = r4.g
            r1 = 0
            r0.b = r1
            r0.c = r5
            boolean r0 = r4.isSmoothScrolling()
            r2 = 1
            if (r0 == 0) goto L2e
            int r6 = r6.c()
            r0 = -1
            if (r6 == r0) goto L2e
            boolean r0 = r4.i
            if (r6 >= r5) goto L1b
            r5 = 1
            goto L1c
        L1b:
            r5 = 0
        L1c:
            if (r0 != r5) goto L25
            androidx.recyclerview.widget.m r5 = r4.c
            int r5 = r5.n()
            goto L2f
        L25:
            androidx.recyclerview.widget.m r5 = r4.c
            int r5 = r5.n()
            r6 = r5
            r5 = 0
            goto L30
        L2e:
            r5 = 0
        L2f:
            r6 = 0
        L30:
            boolean r0 = r4.getClipToPadding()
            if (r0 == 0) goto L4d
            androidx.recyclerview.widget.i r0 = r4.g
            androidx.recyclerview.widget.m r3 = r4.c
            int r3 = r3.m()
            int r3 = r3 - r6
            r0.f = r3
            androidx.recyclerview.widget.i r6 = r4.g
            androidx.recyclerview.widget.m r0 = r4.c
            int r0 = r0.i()
            int r0 = r0 + r5
            r6.g = r0
            goto L5d
        L4d:
            androidx.recyclerview.widget.i r0 = r4.g
            androidx.recyclerview.widget.m r3 = r4.c
            int r3 = r3.h()
            int r3 = r3 + r5
            r0.g = r3
            androidx.recyclerview.widget.i r5 = r4.g
            int r6 = -r6
            r5.f = r6
        L5d:
            androidx.recyclerview.widget.i r5 = r4.g
            r5.h = r1
            r5.a = r2
            androidx.recyclerview.widget.m r6 = r4.c
            int r6 = r6.k()
            if (r6 != 0) goto L74
            androidx.recyclerview.widget.m r6 = r4.c
            int r6 = r6.h()
            if (r6 != 0) goto L74
            r1 = 1
        L74:
            r5.i = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.d0(int, androidx.recyclerview.widget.RecyclerView$a0):void");
    }

    public void e0(int i) {
        this.f = i / this.a;
        this.r = View.MeasureSpec.makeMeasureSpec(i, this.d.k());
    }

    public final void f0(f fVar, int i, int i2) {
        int l = fVar.l();
        if (i == -1) {
            if (fVar.q() + l <= i2) {
                this.j.set(fVar.e, false);
            }
        } else if (fVar.m() - l >= i2) {
            this.j.set(fVar.e, false);
        }
    }

    public final void g(View view) {
        for (int i = this.a - 1; i >= 0; i--) {
            this.b[i].a(view);
        }
    }

    public final int g0(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        if (mode != Integer.MIN_VALUE && mode != 1073741824) {
            return i;
        }
        return View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p generateDefaultLayoutParams() {
        if (this.e == 0) {
            return new c(-2, -1);
        }
        return new c(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int getColumnCountForAccessibility(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.e == 1) {
            return this.a;
        }
        return super.getColumnCountForAccessibility(vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int getRowCountForAccessibility(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.e == 0) {
            return this.a;
        }
        return super.getRowCountForAccessibility(vVar, a0Var);
    }

    public int getSpanCount() {
        return this.a;
    }

    public final void h(b bVar) {
        int m;
        e eVar = this.q;
        int i = eVar.c;
        if (i > 0) {
            if (i == this.a) {
                for (int i2 = 0; i2 < this.a; i2++) {
                    this.b[i2].e();
                    e eVar2 = this.q;
                    int i3 = eVar2.d[i2];
                    if (i3 != Integer.MIN_VALUE) {
                        if (eVar2.i) {
                            m = this.c.i();
                        } else {
                            m = this.c.m();
                        }
                        i3 += m;
                    }
                    this.b[i2].x(i3);
                }
            } else {
                eVar.b();
                e eVar3 = this.q;
                eVar3.a = eVar3.b;
            }
        }
        e eVar4 = this.q;
        this.p = eVar4.j;
        setReverseLayout(eVar4.h);
        X();
        e eVar5 = this.q;
        int i4 = eVar5.a;
        if (i4 != -1) {
            this.k = i4;
            bVar.c = eVar5.i;
        } else {
            bVar.c = this.i;
        }
        if (eVar5.e > 1) {
            d dVar = this.m;
            dVar.a = eVar5.f;
            dVar.b = eVar5.g;
        }
    }

    public boolean i() {
        int n = this.b[0].n(Integer.MIN_VALUE);
        for (int i = 1; i < this.a; i++) {
            if (this.b[i].n(Integer.MIN_VALUE) != n) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean isAutoMeasureEnabled() {
        if (this.n != 0) {
            return true;
        }
        return false;
    }

    public boolean isLayoutRTL() {
        if (getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    public boolean j() {
        int r = this.b[0].r(Integer.MIN_VALUE);
        for (int i = 1; i < this.a; i++) {
            if (this.b[i].r(Integer.MIN_VALUE) != r) {
                return false;
            }
        }
        return true;
    }

    public final void k(View view, c cVar, i iVar) {
        if (iVar.e == 1) {
            if (cVar.f) {
                g(view);
            } else {
                cVar.e.a(view);
            }
        } else if (cVar.f) {
            S(view);
        } else {
            cVar.e.w(view);
        }
    }

    public final int l(int i) {
        boolean z;
        if (getChildCount() == 0) {
            if (!this.i) {
                return -1;
            }
            return 1;
        }
        if (i < D()) {
            z = true;
        } else {
            z = false;
        }
        if (z != this.i) {
            return -1;
        }
        return 1;
    }

    public boolean m() {
        int D;
        int E;
        int i;
        if (getChildCount() == 0 || this.n == 0 || !isAttachedToWindow()) {
            return false;
        }
        if (this.i) {
            D = E();
            E = D();
        } else {
            D = D();
            E = E();
        }
        if (D == 0 && L() != null) {
            this.m.b();
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        } else if (!this.u) {
            return false;
        } else {
            if (this.i) {
                i = -1;
            } else {
                i = 1;
            }
            int i2 = E + 1;
            d.a e2 = this.m.e(D, i2, i, true);
            if (e2 == null) {
                this.u = false;
                this.m.d(i2);
                return false;
            }
            d.a e3 = this.m.e(D, e2.a, i * (-1), true);
            if (e3 == null) {
                this.m.d(e2.a);
            } else {
                this.m.d(e3.a + 1);
            }
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        }
    }

    public final boolean n(f fVar) {
        if (this.i) {
            if (fVar.m() < this.c.i()) {
                ArrayList arrayList = fVar.a;
                return !fVar.p((View) arrayList.get(arrayList.size() - 1)).f;
            }
        } else if (fVar.q() > this.c.m()) {
            return !fVar.p((View) fVar.a.get(0)).f;
        }
        return false;
    }

    public final int o(RecyclerView.a0 a0Var) {
        if (getChildCount() == 0) {
            return 0;
        }
        return p.a(a0Var, this.c, x(!this.v), w(!this.v), this, this.v);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void offsetChildrenHorizontal(int i) {
        super.offsetChildrenHorizontal(i);
        for (int i2 = 0; i2 < this.a; i2++) {
            this.b[i2].t(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void offsetChildrenVertical(int i) {
        super.offsetChildrenVertical(i);
        for (int i2 = 0; i2 < this.a; i2++) {
            this.b[i2].t(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.onDetachedFromWindow(recyclerView, vVar);
        removeCallbacks(this.x);
        for (int i = 0; i < this.a; i++) {
            this.b[i].e();
        }
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View onFocusSearchFailed(View view, int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        View findContainingItemView;
        int D;
        boolean z;
        boolean z2;
        int h;
        int h2;
        int h3;
        View o;
        if (getChildCount() == 0 || (findContainingItemView = findContainingItemView(view)) == null) {
            return null;
        }
        X();
        int convertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i);
        if (convertFocusDirectionToLayoutDirection == Integer.MIN_VALUE) {
            return null;
        }
        c cVar = (c) findContainingItemView.getLayoutParams();
        boolean z3 = cVar.f;
        f fVar = cVar.e;
        if (convertFocusDirectionToLayoutDirection == 1) {
            D = E();
        } else {
            D = D();
        }
        d0(D, a0Var);
        Y(convertFocusDirectionToLayoutDirection);
        i iVar = this.g;
        iVar.c = iVar.d + D;
        iVar.b = (int) (this.c.n() * 0.33333334f);
        i iVar2 = this.g;
        iVar2.h = true;
        iVar2.a = false;
        u(vVar, iVar2, a0Var);
        this.o = this.i;
        if (!z3 && (o = fVar.o(D, convertFocusDirectionToLayoutDirection)) != null && o != findContainingItemView) {
            return o;
        }
        if (Q(convertFocusDirectionToLayoutDirection)) {
            for (int i2 = this.a - 1; i2 >= 0; i2--) {
                View o2 = this.b[i2].o(D, convertFocusDirectionToLayoutDirection);
                if (o2 != null && o2 != findContainingItemView) {
                    return o2;
                }
            }
        } else {
            for (int i3 = 0; i3 < this.a; i3++) {
                View o3 = this.b[i3].o(D, convertFocusDirectionToLayoutDirection);
                if (o3 != null && o3 != findContainingItemView) {
                    return o3;
                }
            }
        }
        boolean z4 = !this.h;
        if (convertFocusDirectionToLayoutDirection == -1) {
            z = true;
        } else {
            z = false;
        }
        if (z4 == z) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z3) {
            if (z2) {
                h3 = fVar.f();
            } else {
                h3 = fVar.h();
            }
            View findViewByPosition = findViewByPosition(h3);
            if (findViewByPosition != null && findViewByPosition != findContainingItemView) {
                return findViewByPosition;
            }
        }
        if (Q(convertFocusDirectionToLayoutDirection)) {
            for (int i4 = this.a - 1; i4 >= 0; i4--) {
                if (i4 != fVar.e) {
                    if (z2) {
                        h2 = this.b[i4].f();
                    } else {
                        h2 = this.b[i4].h();
                    }
                    View findViewByPosition2 = findViewByPosition(h2);
                    if (findViewByPosition2 != null && findViewByPosition2 != findContainingItemView) {
                        return findViewByPosition2;
                    }
                }
            }
        } else {
            for (int i5 = 0; i5 < this.a; i5++) {
                if (z2) {
                    h = this.b[i5].f();
                } else {
                    h = this.b[i5].h();
                }
                View findViewByPosition3 = findViewByPosition(h);
                if (findViewByPosition3 != null && findViewByPosition3 != findContainingItemView) {
                    return findViewByPosition3;
                }
            }
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View x = x(false);
            View w = w(false);
            if (x != null && w != null) {
                int position = getPosition(x);
                int position2 = getPosition(w);
                if (position < position2) {
                    accessibilityEvent.setFromIndex(position);
                    accessibilityEvent.setToIndex(position2);
                    return;
                }
                accessibilityEvent.setFromIndex(position2);
                accessibilityEvent.setToIndex(position);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.v vVar, RecyclerView.a0 a0Var, View view, g0 g0Var) {
        int i;
        int i2;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof c)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, g0Var);
            return;
        }
        c cVar = (c) layoutParams;
        if (this.e == 0) {
            int e2 = cVar.e();
            if (cVar.f) {
                i2 = this.a;
            } else {
                i2 = 1;
            }
            g0Var.Z(g0.c.a(e2, i2, -1, -1, false, false));
            return;
        }
        int e3 = cVar.e();
        if (cVar.f) {
            i = this.a;
        } else {
            i = 1;
        }
        g0Var.Z(g0.c.a(-1, -1, e3, i, false, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        K(i, i2, 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsChanged(RecyclerView recyclerView) {
        this.m.b();
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        K(i, i2, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        K(i, i2, 2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        K(i, i2, 4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutChildren(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        P(vVar, a0Var, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutCompleted(RecyclerView.a0 a0Var) {
        super.onLayoutCompleted(a0Var);
        this.k = -1;
        this.l = Integer.MIN_VALUE;
        this.q = null;
        this.t.c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof e) {
            this.q = (e) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable onSaveInstanceState() {
        int D;
        int r;
        int m;
        int[] iArr;
        if (this.q != null) {
            return new e(this.q);
        }
        e eVar = new e();
        eVar.h = this.h;
        eVar.i = this.o;
        eVar.j = this.p;
        d dVar = this.m;
        if (dVar != null && (iArr = dVar.a) != null) {
            eVar.f = iArr;
            eVar.e = iArr.length;
            eVar.g = dVar.b;
        } else {
            eVar.e = 0;
        }
        if (getChildCount() > 0) {
            if (this.o) {
                D = E();
            } else {
                D = D();
            }
            eVar.a = D;
            eVar.b = y();
            int i = this.a;
            eVar.c = i;
            eVar.d = new int[i];
            for (int i2 = 0; i2 < this.a; i2++) {
                if (this.o) {
                    r = this.b[i2].n(Integer.MIN_VALUE);
                    if (r != Integer.MIN_VALUE) {
                        m = this.c.i();
                        r -= m;
                        eVar.d[i2] = r;
                    } else {
                        eVar.d[i2] = r;
                    }
                } else {
                    r = this.b[i2].r(Integer.MIN_VALUE);
                    if (r != Integer.MIN_VALUE) {
                        m = this.c.m();
                        r -= m;
                        eVar.d[i2] = r;
                    } else {
                        eVar.d[i2] = r;
                    }
                }
            }
        } else {
            eVar.a = -1;
            eVar.b = -1;
            eVar.c = 0;
        }
        return eVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onScrollStateChanged(int i) {
        if (i == 0) {
            m();
        }
    }

    public final int p(RecyclerView.a0 a0Var) {
        if (getChildCount() == 0) {
            return 0;
        }
        return p.b(a0Var, this.c, x(!this.v), w(!this.v), this, this.v, this.i);
    }

    public final int q(RecyclerView.a0 a0Var) {
        if (getChildCount() == 0) {
            return 0;
        }
        return p.c(a0Var, this.c, x(!this.v), w(!this.v), this, this.v);
    }

    public final d.a r(int i) {
        d.a aVar = new d.a();
        aVar.c = new int[this.a];
        for (int i2 = 0; i2 < this.a; i2++) {
            aVar.c[i2] = i - this.b[i2].n(i);
        }
        return aVar;
    }

    public final d.a s(int i) {
        d.a aVar = new d.a();
        aVar.c = new int[this.a];
        for (int i2 = 0; i2 < this.a; i2++) {
            aVar.c[i2] = this.b[i2].r(i) - i;
        }
        return aVar;
    }

    public int scrollBy(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        R(i, a0Var);
        int u = u(vVar, this.g, a0Var);
        if (this.g.b >= u) {
            if (i < 0) {
                i = -u;
            } else {
                i = u;
            }
        }
        this.c.r(-i);
        this.o = this.i;
        i iVar = this.g;
        iVar.b = 0;
        T(vVar, iVar);
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int scrollHorizontallyBy(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return scrollBy(i, vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void scrollToPosition(int i) {
        e eVar = this.q;
        if (eVar != null && eVar.a != i) {
            eVar.a();
        }
        this.k = i;
        this.l = Integer.MIN_VALUE;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int scrollVerticallyBy(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return scrollBy(i, vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void setMeasuredDimension(Rect rect, int i, int i2) {
        int chooseSize;
        int chooseSize2;
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.e == 1) {
            chooseSize2 = RecyclerView.o.chooseSize(i2, rect.height() + paddingTop, getMinimumHeight());
            chooseSize = RecyclerView.o.chooseSize(i, (this.f * this.a) + paddingLeft, getMinimumWidth());
        } else {
            chooseSize = RecyclerView.o.chooseSize(i, rect.width() + paddingLeft, getMinimumWidth());
            chooseSize2 = RecyclerView.o.chooseSize(i2, (this.f * this.a) + paddingTop, getMinimumHeight());
        }
        setMeasuredDimension(chooseSize, chooseSize2);
    }

    public void setOrientation(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        assertNotInLayoutOrScroll(null);
        if (i == this.e) {
            return;
        }
        this.e = i;
        m mVar = this.c;
        this.c = this.d;
        this.d = mVar;
        requestLayout();
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        e eVar = this.q;
        if (eVar != null && eVar.h != z) {
            eVar.h = z;
        }
        this.h = z;
        requestLayout();
    }

    public void setSpanCount(int i) {
        assertNotInLayoutOrScroll(null);
        if (i != this.a) {
            M();
            this.a = i;
            this.j = new BitSet(this.a);
            this.b = new f[this.a];
            for (int i2 = 0; i2 < this.a; i2++) {
                this.b[i2] = new f(i2);
            }
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.a0 a0Var, int i) {
        j jVar = new j(recyclerView.getContext());
        jVar.setTargetPosition(i);
        startSmoothScroll(jVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean supportsPredictiveItemAnimations() {
        if (this.q == null) {
            return true;
        }
        return false;
    }

    public final void t() {
        this.c = m.b(this, this.e);
        this.d = m.b(this, 1 - this.e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v7 */
    public final int u(RecyclerView.v vVar, i iVar, RecyclerView.a0 a0Var) {
        int i;
        int i2;
        int m;
        int F;
        boolean z;
        f fVar;
        int r;
        int e2;
        int i3;
        int m2;
        int i4;
        int e3;
        int i5;
        boolean j;
        int n;
        ?? r9 = 0;
        this.j.set(0, this.a, true);
        if (this.g.i) {
            if (iVar.e == 1) {
                i2 = Integer.MAX_VALUE;
            } else {
                i2 = Integer.MIN_VALUE;
            }
        } else {
            if (iVar.e == 1) {
                i = iVar.g + iVar.b;
            } else {
                i = iVar.f - iVar.b;
            }
            i2 = i;
        }
        Z(iVar.e, i2);
        if (this.i) {
            m = this.c.i();
        } else {
            m = this.c.m();
        }
        int i6 = m;
        boolean z2 = false;
        while (iVar.a(a0Var) && (this.g.i || !this.j.isEmpty())) {
            View b2 = iVar.b(vVar);
            c cVar = (c) b2.getLayoutParams();
            int a2 = cVar.a();
            int g = this.m.g(a2);
            if (g == -1) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                if (cVar.f) {
                    fVar = this.b[r9];
                } else {
                    fVar = J(iVar);
                }
                this.m.n(a2, fVar);
            } else {
                fVar = this.b[g];
            }
            f fVar2 = fVar;
            cVar.e = fVar2;
            if (iVar.e == 1) {
                addView(b2);
            } else {
                addView(b2, r9);
            }
            O(b2, cVar, r9);
            if (iVar.e == 1) {
                if (cVar.f) {
                    n = F(i6);
                } else {
                    n = fVar2.n(i6);
                }
                int e4 = this.c.e(b2) + n;
                if (z && cVar.f) {
                    d.a r2 = r(n);
                    r2.b = -1;
                    r2.a = a2;
                    this.m.a(r2);
                }
                i3 = e4;
                e2 = n;
            } else {
                if (cVar.f) {
                    r = I(i6);
                } else {
                    r = fVar2.r(i6);
                }
                e2 = r - this.c.e(b2);
                if (z && cVar.f) {
                    d.a s2 = s(r);
                    s2.b = 1;
                    s2.a = a2;
                    this.m.a(s2);
                }
                i3 = r;
            }
            if (cVar.f && iVar.d == -1) {
                if (z) {
                    this.u = true;
                } else {
                    if (iVar.e == 1) {
                        j = i();
                    } else {
                        j = j();
                    }
                    if (!j) {
                        d.a f2 = this.m.f(a2);
                        if (f2 != null) {
                            f2.d = true;
                        }
                        this.u = true;
                    }
                }
            }
            k(b2, cVar, iVar);
            if (isLayoutRTL() && this.e == 1) {
                if (cVar.f) {
                    i5 = this.d.i();
                } else {
                    i5 = this.d.i() - (((this.a - 1) - fVar2.e) * this.f);
                }
                e3 = i5;
                i4 = i5 - this.d.e(b2);
            } else {
                if (cVar.f) {
                    m2 = this.d.m();
                } else {
                    m2 = (fVar2.e * this.f) + this.d.m();
                }
                i4 = m2;
                e3 = this.d.e(b2) + m2;
            }
            if (this.e == 1) {
                layoutDecoratedWithMargins(b2, i4, e2, e3, i3);
            } else {
                layoutDecoratedWithMargins(b2, e2, i4, i3, e3);
            }
            if (cVar.f) {
                Z(this.g.e, i2);
            } else {
                f0(fVar2, this.g.e, i2);
            }
            T(vVar, this.g);
            if (this.g.h && b2.hasFocusable()) {
                if (cVar.f) {
                    this.j.clear();
                } else {
                    this.j.set(fVar2.e, false);
                    z2 = true;
                    r9 = 0;
                }
            }
            z2 = true;
            r9 = 0;
        }
        if (!z2) {
            T(vVar, this.g);
        }
        if (this.g.e == -1) {
            F = this.c.m() - I(this.c.m());
        } else {
            F = F(this.c.i()) - this.c.i();
        }
        if (F > 0) {
            return Math.min(iVar.b, F);
        }
        return 0;
    }

    public final int v(int i) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            int position = getPosition(getChildAt(i2));
            if (position >= 0 && position < i) {
                return position;
            }
        }
        return 0;
    }

    public View w(boolean z) {
        int m = this.c.m();
        int i = this.c.i();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int g = this.c.g(childAt);
            int d2 = this.c.d(childAt);
            if (d2 > m && g < i) {
                if (d2 > i && z) {
                    if (view == null) {
                        view = childAt;
                    }
                } else {
                    return childAt;
                }
            }
        }
        return view;
    }

    public View x(boolean z) {
        int m = this.c.m();
        int i = this.c.i();
        int childCount = getChildCount();
        View view = null;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int g = this.c.g(childAt);
            if (this.c.d(childAt) > m && g < i) {
                if (g < m && z) {
                    if (view == null) {
                        view = childAt;
                    }
                } else {
                    return childAt;
                }
            }
        }
        return view;
    }

    public int y() {
        View x;
        if (this.i) {
            x = w(true);
        } else {
            x = x(true);
        }
        if (x == null) {
            return -1;
        }
        return getPosition(x);
    }

    public int[] z(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.a];
        } else if (iArr.length < this.a) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.a + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.a; i++) {
            iArr[i] = this.b[i].g();
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new c((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new c(layoutParams);
    }

    /* loaded from: classes.dex */
    public static class d {
        public int[] a;
        public List b;

        public void a(a aVar) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                a aVar2 = (a) this.b.get(i);
                if (aVar2.a == aVar.a) {
                    this.b.remove(i);
                }
                if (aVar2.a >= aVar.a) {
                    this.b.add(i, aVar);
                    return;
                }
            }
            this.b.add(aVar);
        }

        public void b() {
            int[] iArr = this.a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.b = null;
        }

        public void c(int i) {
            int[] iArr = this.a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i, 10) + 1];
                this.a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i >= iArr.length) {
                int[] iArr3 = new int[o(i)];
                this.a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        public int d(int i) {
            List list = this.b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (((a) this.b.get(size)).a >= i) {
                        this.b.remove(size);
                    }
                }
            }
            return h(i);
        }

        public a e(int i, int i2, int i3, boolean z) {
            List list = this.b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                a aVar = (a) this.b.get(i4);
                int i5 = aVar.a;
                if (i5 >= i2) {
                    return null;
                }
                if (i5 >= i && (i3 == 0 || aVar.b == i3 || (z && aVar.d))) {
                    return aVar;
                }
            }
            return null;
        }

        public a f(int i) {
            List list = this.b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = (a) this.b.get(size);
                if (aVar.a == i) {
                    return aVar;
                }
            }
            return null;
        }

        public int g(int i) {
            int[] iArr = this.a;
            if (iArr != null && i < iArr.length) {
                return iArr[i];
            }
            return -1;
        }

        public int h(int i) {
            int[] iArr = this.a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            int i2 = i(i);
            if (i2 == -1) {
                int[] iArr2 = this.a;
                Arrays.fill(iArr2, i, iArr2.length, -1);
                return this.a.length;
            }
            int i3 = i2 + 1;
            Arrays.fill(this.a, i, i3, -1);
            return i3;
        }

        public final int i(int i) {
            if (this.b == null) {
                return -1;
            }
            a f = f(i);
            if (f != null) {
                this.b.remove(f);
            }
            int size = this.b.size();
            int i2 = 0;
            while (true) {
                if (i2 < size) {
                    if (((a) this.b.get(i2)).a >= i) {
                        break;
                    }
                    i2++;
                } else {
                    i2 = -1;
                    break;
                }
            }
            if (i2 == -1) {
                return -1;
            }
            this.b.remove(i2);
            return ((a) this.b.get(i2)).a;
        }

        public void j(int i, int i2) {
            int[] iArr = this.a;
            if (iArr != null && i < iArr.length) {
                int i3 = i + i2;
                c(i3);
                int[] iArr2 = this.a;
                System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
                Arrays.fill(this.a, i, i3, -1);
                l(i, i2);
            }
        }

        public void k(int i, int i2) {
            int[] iArr = this.a;
            if (iArr != null && i < iArr.length) {
                int i3 = i + i2;
                c(i3);
                int[] iArr2 = this.a;
                System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
                int[] iArr3 = this.a;
                Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
                m(i, i2);
            }
        }

        public final void l(int i, int i2) {
            List list = this.b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = (a) this.b.get(size);
                int i3 = aVar.a;
                if (i3 >= i) {
                    aVar.a = i3 + i2;
                }
            }
        }

        public final void m(int i, int i2) {
            List list = this.b;
            if (list == null) {
                return;
            }
            int i3 = i + i2;
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = (a) this.b.get(size);
                int i4 = aVar.a;
                if (i4 >= i) {
                    if (i4 < i3) {
                        this.b.remove(size);
                    } else {
                        aVar.a = i4 - i2;
                    }
                }
            }
        }

        public void n(int i, f fVar) {
            c(i);
            this.a[i] = fVar.e;
        }

        public int o(int i) {
            int length = this.a.length;
            while (length <= i) {
                length *= 2;
            }
            return length;
        }

        /* loaded from: classes.dex */
        public static class a implements Parcelable {
            public static final Parcelable.Creator<a> CREATOR = new C0026a();
            public int a;
            public int b;
            public int[] c;
            public boolean d;

            /* renamed from: androidx.recyclerview.widget.StaggeredGridLayoutManager$d$a$a  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public static class C0026a implements Parcelable.Creator {
                @Override // android.os.Parcelable.Creator
                /* renamed from: a */
                public a createFromParcel(Parcel parcel) {
                    return new a(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: b */
                public a[] newArray(int i) {
                    return new a[i];
                }
            }

            public a(Parcel parcel) {
                this.a = parcel.readInt();
                this.b = parcel.readInt();
                this.d = parcel.readInt() == 1;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    int[] iArr = new int[readInt];
                    this.c = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            public int a(int i) {
                int[] iArr = this.c;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.a + ", mGapDir=" + this.b + ", mHasUnwantedGapAfter=" + this.d + ", mGapPerSpan=" + Arrays.toString(this.c) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.a);
                parcel.writeInt(this.b);
                parcel.writeInt(this.d ? 1 : 0);
                int[] iArr = this.c;
                if (iArr != null && iArr.length > 0) {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.c);
                    return;
                }
                parcel.writeInt(0);
            }

            public a() {
            }
        }
    }
}
