package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.f;
import java.util.List;
/* loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.o implements f.b, RecyclerView.z.b {
    static final boolean DEBUG = false;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    private static final String TAG = "LinearLayoutManager";
    public static final int VERTICAL = 1;
    final a mAnchorInfo;
    private int mInitialPrefetchItemCount;
    private boolean mLastStackFromEnd;
    private final b mLayoutChunkResult;
    private c mLayoutState;
    int mOrientation;
    m mOrientationHelper;
    d mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private int[] mReusableIntPair;
    private boolean mReverseLayout;
    boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    private boolean mStackFromEnd;

    /* loaded from: classes.dex */
    public static class a {
        public m a;
        public int b;
        public int c;
        public boolean d;
        public boolean e;

        public a() {
            e();
        }

        public void a() {
            int m;
            if (this.d) {
                m = this.a.i();
            } else {
                m = this.a.m();
            }
            this.c = m;
        }

        public void b(View view, int i) {
            if (this.d) {
                this.c = this.a.d(view) + this.a.o();
            } else {
                this.c = this.a.g(view);
            }
            this.b = i;
        }

        public void c(View view, int i) {
            int o = this.a.o();
            if (o >= 0) {
                b(view, i);
                return;
            }
            this.b = i;
            if (this.d) {
                int i2 = (this.a.i() - o) - this.a.d(view);
                this.c = this.a.i() - i2;
                if (i2 > 0) {
                    int e = this.c - this.a.e(view);
                    int m = this.a.m();
                    int min = e - (m + Math.min(this.a.g(view) - m, 0));
                    if (min < 0) {
                        this.c += Math.min(i2, -min);
                        return;
                    }
                    return;
                }
                return;
            }
            int g = this.a.g(view);
            int m2 = g - this.a.m();
            this.c = g;
            if (m2 > 0) {
                int i3 = (this.a.i() - Math.min(0, (this.a.i() - o) - this.a.d(view))) - (g + this.a.e(view));
                if (i3 < 0) {
                    this.c -= Math.min(m2, -i3);
                }
            }
        }

        public boolean d(View view, RecyclerView.a0 a0Var) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            if (!pVar.c() && pVar.a() >= 0 && pVar.a() < a0Var.b()) {
                return true;
            }
            return LinearLayoutManager.DEBUG;
        }

        public void e() {
            this.b = -1;
            this.c = Integer.MIN_VALUE;
            this.d = LinearLayoutManager.DEBUG;
            this.e = LinearLayoutManager.DEBUG;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.b + ", mCoordinate=" + this.c + ", mLayoutFromEnd=" + this.d + ", mValid=" + this.e + '}';
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public int a;
        public boolean b;
        public boolean c;
        public boolean d;

        public void a() {
            this.a = 0;
            this.b = LinearLayoutManager.DEBUG;
            this.c = LinearLayoutManager.DEBUG;
            this.d = LinearLayoutManager.DEBUG;
        }
    }

    /* loaded from: classes.dex */
    public static class c {
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int k;
        public boolean m;
        public boolean a = true;
        public int h = 0;
        public int i = 0;
        public boolean j = LinearLayoutManager.DEBUG;
        public List l = null;

        public void a() {
            b(null);
        }

        public void b(View view) {
            View f = f(view);
            if (f == null) {
                this.d = -1;
            } else {
                this.d = ((RecyclerView.p) f.getLayoutParams()).a();
            }
        }

        public boolean c(RecyclerView.a0 a0Var) {
            int i = this.d;
            if (i >= 0 && i < a0Var.b()) {
                return true;
            }
            return LinearLayoutManager.DEBUG;
        }

        public View d(RecyclerView.v vVar) {
            if (this.l != null) {
                return e();
            }
            View o = vVar.o(this.d);
            this.d += this.e;
            return o;
        }

        public final View e() {
            int size = this.l.size();
            for (int i = 0; i < size; i++) {
                View view = ((RecyclerView.d0) this.l.get(i)).itemView;
                RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
                if (!pVar.c() && this.d == pVar.a()) {
                    b(view);
                    return view;
                }
            }
            return null;
        }

        public View f(View view) {
            int a;
            int size = this.l.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = ((RecyclerView.d0) this.l.get(i2)).itemView;
                RecyclerView.p pVar = (RecyclerView.p) view3.getLayoutParams();
                if (view3 != view && !pVar.c() && (a = (pVar.a() - this.d) * this.e) >= 0 && a < i) {
                    view2 = view3;
                    if (a == 0) {
                        break;
                    }
                    i = a;
                }
            }
            return view2;
        }
    }

    /* loaded from: classes.dex */
    public static class d implements Parcelable {
        public static final Parcelable.Creator<d> CREATOR = new a();
        public int a;
        public int b;
        public boolean c;

        /* loaded from: classes.dex */
        public static class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public d createFromParcel(Parcel parcel) {
                return new d(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public d[] newArray(int i) {
                return new d[i];
            }
        }

        public d() {
        }

        public boolean a() {
            if (this.a >= 0) {
                return true;
            }
            return LinearLayoutManager.DEBUG;
        }

        public void b() {
            this.a = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c ? 1 : 0);
        }

        public d(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt() != 1 ? LinearLayoutManager.DEBUG : true;
        }

        public d(d dVar) {
            this.a = dVar.a;
            this.b = dVar.b;
            this.c = dVar.c;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, DEBUG);
    }

    public final void A(RecyclerView.v vVar, int i, int i2) {
        if (i < 0) {
            return;
        }
        int i3 = i - i2;
        int childCount = getChildCount();
        if (this.mShouldReverseLayout) {
            int i4 = childCount - 1;
            for (int i5 = i4; i5 >= 0; i5--) {
                View childAt = getChildAt(i5);
                if (this.mOrientationHelper.d(childAt) > i3 || this.mOrientationHelper.p(childAt) > i3) {
                    y(vVar, i4, i5);
                    return;
                }
            }
            return;
        }
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt2 = getChildAt(i6);
            if (this.mOrientationHelper.d(childAt2) > i3 || this.mOrientationHelper.p(childAt2) > i3) {
                y(vVar, 0, i6);
                return;
            }
        }
    }

    public final void B() {
        if (this.mOrientation != 1 && isLayoutRTL()) {
            this.mShouldReverseLayout = !this.mReverseLayout;
        } else {
            this.mShouldReverseLayout = this.mReverseLayout;
        }
    }

    public final boolean C(RecyclerView.v vVar, RecyclerView.a0 a0Var, a aVar) {
        View q;
        int m;
        int childCount = getChildCount();
        boolean z = DEBUG;
        if (childCount == 0) {
            return DEBUG;
        }
        View focusedChild = getFocusedChild();
        if (focusedChild != null && aVar.d(focusedChild, a0Var)) {
            aVar.c(focusedChild, getPosition(focusedChild));
            return true;
        } else if (this.mLastStackFromEnd != this.mStackFromEnd) {
            return DEBUG;
        } else {
            if (aVar.d) {
                q = p(vVar, a0Var);
            } else {
                q = q(vVar, a0Var);
            }
            if (q == null) {
                return DEBUG;
            }
            aVar.b(q, getPosition(q));
            if (!a0Var.e() && supportsPredictiveItemAnimations()) {
                if ((this.mOrientationHelper.g(q) >= this.mOrientationHelper.i() || this.mOrientationHelper.d(q) < this.mOrientationHelper.m()) ? true : true) {
                    if (aVar.d) {
                        m = this.mOrientationHelper.i();
                    } else {
                        m = this.mOrientationHelper.m();
                    }
                    aVar.c = m;
                }
            }
            return true;
        }
    }

    public final boolean D(RecyclerView.a0 a0Var, a aVar) {
        int i;
        boolean z;
        int g;
        boolean e = a0Var.e();
        boolean z2 = DEBUG;
        if (!e && (i = this.mPendingScrollPosition) != -1) {
            if (i >= 0 && i < a0Var.b()) {
                aVar.b = this.mPendingScrollPosition;
                d dVar = this.mPendingSavedState;
                if (dVar != null && dVar.a()) {
                    boolean z3 = this.mPendingSavedState.c;
                    aVar.d = z3;
                    if (z3) {
                        aVar.c = this.mOrientationHelper.i() - this.mPendingSavedState.b;
                    } else {
                        aVar.c = this.mOrientationHelper.m() + this.mPendingSavedState.b;
                    }
                    return true;
                } else if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                    View findViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                    if (findViewByPosition != null) {
                        if (this.mOrientationHelper.e(findViewByPosition) > this.mOrientationHelper.n()) {
                            aVar.a();
                            return true;
                        } else if (this.mOrientationHelper.g(findViewByPosition) - this.mOrientationHelper.m() < 0) {
                            aVar.c = this.mOrientationHelper.m();
                            aVar.d = DEBUG;
                            return true;
                        } else if (this.mOrientationHelper.i() - this.mOrientationHelper.d(findViewByPosition) < 0) {
                            aVar.c = this.mOrientationHelper.i();
                            aVar.d = true;
                            return true;
                        } else {
                            if (aVar.d) {
                                g = this.mOrientationHelper.d(findViewByPosition) + this.mOrientationHelper.o();
                            } else {
                                g = this.mOrientationHelper.g(findViewByPosition);
                            }
                            aVar.c = g;
                        }
                    } else {
                        if (getChildCount() > 0) {
                            if (this.mPendingScrollPosition < getPosition(getChildAt(0))) {
                                z = true;
                            } else {
                                z = DEBUG;
                            }
                            if (z == this.mShouldReverseLayout) {
                                z2 = true;
                            }
                            aVar.d = z2;
                        }
                        aVar.a();
                    }
                    return true;
                } else {
                    boolean z4 = this.mShouldReverseLayout;
                    aVar.d = z4;
                    if (z4) {
                        aVar.c = this.mOrientationHelper.i() - this.mPendingScrollPositionOffset;
                    } else {
                        aVar.c = this.mOrientationHelper.m() + this.mPendingScrollPositionOffset;
                    }
                    return true;
                }
            }
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        }
        return DEBUG;
    }

    public final void E(RecyclerView.v vVar, RecyclerView.a0 a0Var, a aVar) {
        int i;
        if (D(a0Var, aVar) || C(vVar, a0Var, aVar)) {
            return;
        }
        aVar.a();
        if (this.mStackFromEnd) {
            i = a0Var.b() - 1;
        } else {
            i = 0;
        }
        aVar.b = i;
    }

    public final void F(int i, int i2, boolean z, RecyclerView.a0 a0Var) {
        int i3;
        int m;
        this.mLayoutState.m = resolveIsInfinite();
        this.mLayoutState.f = i;
        int[] iArr = this.mReusableIntPair;
        boolean z2 = DEBUG;
        iArr[0] = 0;
        int i4 = 1;
        iArr[1] = 0;
        calculateExtraLayoutSpace(a0Var, iArr);
        int max = Math.max(0, this.mReusableIntPair[0]);
        int max2 = Math.max(0, this.mReusableIntPair[1]);
        if (i == 1) {
            z2 = true;
        }
        c cVar = this.mLayoutState;
        if (z2) {
            i3 = max2;
        } else {
            i3 = max;
        }
        cVar.h = i3;
        if (!z2) {
            max = max2;
        }
        cVar.i = max;
        if (z2) {
            cVar.h = i3 + this.mOrientationHelper.j();
            View t = t();
            c cVar2 = this.mLayoutState;
            if (this.mShouldReverseLayout) {
                i4 = -1;
            }
            cVar2.e = i4;
            int position = getPosition(t);
            c cVar3 = this.mLayoutState;
            cVar2.d = position + cVar3.e;
            cVar3.b = this.mOrientationHelper.d(t);
            m = this.mOrientationHelper.d(t) - this.mOrientationHelper.i();
        } else {
            View u = u();
            this.mLayoutState.h += this.mOrientationHelper.m();
            c cVar4 = this.mLayoutState;
            if (!this.mShouldReverseLayout) {
                i4 = -1;
            }
            cVar4.e = i4;
            int position2 = getPosition(u);
            c cVar5 = this.mLayoutState;
            cVar4.d = position2 + cVar5.e;
            cVar5.b = this.mOrientationHelper.g(u);
            m = (-this.mOrientationHelper.g(u)) + this.mOrientationHelper.m();
        }
        c cVar6 = this.mLayoutState;
        cVar6.c = i2;
        if (z) {
            cVar6.c = i2 - m;
        }
        cVar6.g = m;
    }

    public final void G(int i, int i2) {
        int i3;
        this.mLayoutState.c = this.mOrientationHelper.i() - i2;
        c cVar = this.mLayoutState;
        if (this.mShouldReverseLayout) {
            i3 = -1;
        } else {
            i3 = 1;
        }
        cVar.e = i3;
        cVar.d = i;
        cVar.f = 1;
        cVar.b = i2;
        cVar.g = Integer.MIN_VALUE;
    }

    public final void H(a aVar) {
        G(aVar.b, aVar.c);
    }

    public final void I(int i, int i2) {
        int i3;
        this.mLayoutState.c = i2 - this.mOrientationHelper.m();
        c cVar = this.mLayoutState;
        cVar.d = i;
        if (this.mShouldReverseLayout) {
            i3 = 1;
        } else {
            i3 = -1;
        }
        cVar.e = i3;
        cVar.f = -1;
        cVar.b = i2;
        cVar.g = Integer.MIN_VALUE;
    }

    public final void J(a aVar) {
        I(aVar.b, aVar.c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    public void calculateExtraLayoutSpace(RecyclerView.a0 a0Var, int[] iArr) {
        int i;
        int extraLayoutSpace = getExtraLayoutSpace(a0Var);
        if (this.mLayoutState.f == -1) {
            i = 0;
        } else {
            i = extraLayoutSpace;
            extraLayoutSpace = 0;
        }
        iArr[0] = extraLayoutSpace;
        iArr[1] = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean canScrollHorizontally() {
        if (this.mOrientation == 0) {
            return true;
        }
        return DEBUG;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean canScrollVertically() {
        if (this.mOrientation == 1) {
            return true;
        }
        return DEBUG;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.a0 a0Var, RecyclerView.o.c cVar) {
        int i3;
        if (this.mOrientation != 0) {
            i = i2;
        }
        if (getChildCount() != 0 && i != 0) {
            ensureLayoutState();
            if (i > 0) {
                i3 = 1;
            } else {
                i3 = -1;
            }
            F(i3, Math.abs(i), true, a0Var);
            collectPrefetchPositionsForLayoutState(a0Var, this.mLayoutState, cVar);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void collectInitialPrefetchPositions(int i, RecyclerView.o.c cVar) {
        boolean z;
        int i2;
        d dVar = this.mPendingSavedState;
        int i3 = -1;
        if (dVar != null && dVar.a()) {
            d dVar2 = this.mPendingSavedState;
            z = dVar2.c;
            i2 = dVar2.a;
        } else {
            B();
            z = this.mShouldReverseLayout;
            i2 = this.mPendingScrollPosition;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        }
        if (!z) {
            i3 = 1;
        }
        for (int i4 = 0; i4 < this.mInitialPrefetchItemCount && i2 >= 0 && i2 < i; i4++) {
            cVar.a(i2, 0);
            i2 += i3;
        }
    }

    public void collectPrefetchPositionsForLayoutState(RecyclerView.a0 a0Var, c cVar, RecyclerView.o.c cVar2) {
        int i = cVar.d;
        if (i >= 0 && i < a0Var.b()) {
            cVar2.a(i, Math.max(0, cVar.g));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollExtent(RecyclerView.a0 a0Var) {
        return g(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollOffset(RecyclerView.a0 a0Var) {
        return h(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollRange(RecyclerView.a0 a0Var) {
        return i(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.z.b
    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        boolean z = DEBUG;
        int i2 = 1;
        if (i < getPosition(getChildAt(0))) {
            z = true;
        }
        if (z != this.mShouldReverseLayout) {
            i2 = -1;
        }
        if (this.mOrientation == 0) {
            return new PointF(i2, 0.0f);
        }
        return new PointF(0.0f, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollExtent(RecyclerView.a0 a0Var) {
        return g(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollOffset(RecyclerView.a0 a0Var) {
        return h(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollRange(RecyclerView.a0 a0Var) {
        return i(a0Var);
    }

    public int convertFocusDirectionToLayoutDirection(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 17) {
                    if (i != 33) {
                        if (i != 66) {
                            if (i == 130 && this.mOrientation == 1) {
                                return 1;
                            }
                            return Integer.MIN_VALUE;
                        } else if (this.mOrientation == 0) {
                            return 1;
                        } else {
                            return Integer.MIN_VALUE;
                        }
                    } else if (this.mOrientation == 1) {
                        return -1;
                    } else {
                        return Integer.MIN_VALUE;
                    }
                } else if (this.mOrientation == 0) {
                    return -1;
                } else {
                    return Integer.MIN_VALUE;
                }
            } else if (this.mOrientation != 1 && isLayoutRTL()) {
                return -1;
            } else {
                return 1;
            }
        } else if (this.mOrientation == 1 || !isLayoutRTL()) {
            return -1;
        } else {
            return 1;
        }
    }

    public c createLayoutState() {
        return new c();
    }

    public void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = createLayoutState();
        }
    }

    public int fill(RecyclerView.v vVar, c cVar, RecyclerView.a0 a0Var, boolean z) {
        int i = cVar.c;
        int i2 = cVar.g;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                cVar.g = i2 + i;
            }
            x(vVar, cVar);
        }
        int i3 = cVar.c + cVar.h;
        b bVar = this.mLayoutChunkResult;
        while (true) {
            if ((!cVar.m && i3 <= 0) || !cVar.c(a0Var)) {
                break;
            }
            bVar.a();
            layoutChunk(vVar, a0Var, cVar, bVar);
            if (!bVar.b) {
                cVar.b += bVar.a * cVar.f;
                if (!bVar.c || cVar.l != null || !a0Var.e()) {
                    int i4 = cVar.c;
                    int i5 = bVar.a;
                    cVar.c = i4 - i5;
                    i3 -= i5;
                }
                int i6 = cVar.g;
                if (i6 != Integer.MIN_VALUE) {
                    int i7 = i6 + bVar.a;
                    cVar.g = i7;
                    int i8 = cVar.c;
                    if (i8 < 0) {
                        cVar.g = i7 + i8;
                    }
                    x(vVar, cVar);
                }
                if (z && bVar.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - cVar.c;
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), true, DEBUG);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public View findFirstVisibleChildClosestToEnd(boolean z, boolean z2) {
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild(0, getChildCount(), z, z2);
        }
        return findOneVisibleChild(getChildCount() - 1, -1, z, z2);
    }

    public View findFirstVisibleChildClosestToStart(boolean z, boolean z2) {
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild(getChildCount() - 1, -1, z, z2);
        }
        return findOneVisibleChild(0, getChildCount(), z, z2);
    }

    public int findFirstVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), DEBUG, true);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, true, DEBUG);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findLastVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, DEBUG, true);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public View findOnePartiallyOrCompletelyInvisibleChild(int i, int i2) {
        char c2;
        int i3;
        int i4;
        ensureLayoutState();
        if (i2 > i) {
            c2 = 1;
        } else if (i2 < i) {
            c2 = 65535;
        } else {
            c2 = 0;
        }
        if (c2 == 0) {
            return getChildAt(i);
        }
        if (this.mOrientationHelper.g(getChildAt(i)) < this.mOrientationHelper.m()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        if (this.mOrientation == 0) {
            return this.mHorizontalBoundCheck.a(i, i2, i3, i4);
        }
        return this.mVerticalBoundCheck.a(i, i2, i3, i4);
    }

    public View findOneVisibleChild(int i, int i2, boolean z, boolean z2) {
        int i3;
        ensureLayoutState();
        int i4 = 320;
        if (z) {
            i3 = 24579;
        } else {
            i3 = 320;
        }
        if (!z2) {
            i4 = 0;
        }
        if (this.mOrientation == 0) {
            return this.mHorizontalBoundCheck.a(i, i2, i3, i4);
        }
        return this.mVerticalBoundCheck.a(i, i2, i3, i4);
    }

    public View findReferenceChild(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i, int i2, int i3) {
        int i4;
        ensureLayoutState();
        int m = this.mOrientationHelper.m();
        int i5 = this.mOrientationHelper.i();
        if (i2 > i) {
            i4 = 1;
        } else {
            i4 = -1;
        }
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (((RecyclerView.p) childAt.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.mOrientationHelper.g(childAt) < i5 && this.mOrientationHelper.d(childAt) >= m) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        if (view == null) {
            return view2;
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View findViewByPosition(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i - getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (getPosition(childAt) == i) {
                return childAt;
            }
        }
        return super.findViewByPosition(i);
    }

    public final int g(RecyclerView.a0 a0Var) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return p.a(a0Var, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p generateDefaultLayoutParams() {
        return new RecyclerView.p(-2, -2);
    }

    @Deprecated
    public int getExtraLayoutSpace(RecyclerView.a0 a0Var) {
        if (a0Var.d()) {
            return this.mOrientationHelper.n();
        }
        return 0;
    }

    public int getInitialPrefetchItemCount() {
        return this.mInitialPrefetchItemCount;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    public boolean getStackFromEnd() {
        return this.mStackFromEnd;
    }

    public final int h(RecyclerView.a0 a0Var) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return p.b(a0Var, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    public final int i(RecyclerView.a0 a0Var) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return p.c(a0Var, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public boolean isLayoutRTL() {
        if (getLayoutDirection() == 1) {
            return true;
        }
        return DEBUG;
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }

    public final View j() {
        return findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount());
    }

    public final View k(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return findReferenceChild(vVar, a0Var, 0, getChildCount(), a0Var.b());
    }

    public final View l() {
        return findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1);
    }

    public void layoutChunk(RecyclerView.v vVar, RecyclerView.a0 a0Var, c cVar, b bVar) {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int f;
        boolean z2;
        View d2 = cVar.d(vVar);
        if (d2 == null) {
            bVar.b = true;
            return;
        }
        RecyclerView.p pVar = (RecyclerView.p) d2.getLayoutParams();
        if (cVar.l == null) {
            boolean z3 = this.mShouldReverseLayout;
            if (cVar.f == -1) {
                z2 = true;
            } else {
                z2 = DEBUG;
            }
            if (z3 == z2) {
                addView(d2);
            } else {
                addView(d2, 0);
            }
        } else {
            boolean z4 = this.mShouldReverseLayout;
            if (cVar.f == -1) {
                z = true;
            } else {
                z = DEBUG;
            }
            if (z4 == z) {
                addDisappearingView(d2);
            } else {
                addDisappearingView(d2, 0);
            }
        }
        measureChildWithMargins(d2, 0, 0);
        bVar.a = this.mOrientationHelper.e(d2);
        if (this.mOrientation == 1) {
            if (isLayoutRTL()) {
                f = getWidth() - getPaddingRight();
                i4 = f - this.mOrientationHelper.f(d2);
            } else {
                i4 = getPaddingLeft();
                f = this.mOrientationHelper.f(d2) + i4;
            }
            if (cVar.f == -1) {
                int i5 = cVar.b;
                i3 = i5;
                i2 = f;
                i = i5 - bVar.a;
            } else {
                int i6 = cVar.b;
                i = i6;
                i2 = f;
                i3 = bVar.a + i6;
            }
        } else {
            int paddingTop = getPaddingTop();
            int f2 = this.mOrientationHelper.f(d2) + paddingTop;
            if (cVar.f == -1) {
                int i7 = cVar.b;
                i2 = i7;
                i = paddingTop;
                i3 = f2;
                i4 = i7 - bVar.a;
            } else {
                int i8 = cVar.b;
                i = paddingTop;
                i2 = bVar.a + i8;
                i3 = f2;
                i4 = i8;
            }
        }
        layoutDecoratedWithMargins(d2, i4, i, i2, i3);
        if (pVar.c() || pVar.b()) {
            bVar.c = true;
        }
        bVar.d = d2.hasFocusable();
    }

    public final View m(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return findReferenceChild(vVar, a0Var, getChildCount() - 1, -1, a0Var.b());
    }

    public final View n() {
        if (this.mShouldReverseLayout) {
            return j();
        }
        return l();
    }

    public final View o() {
        if (this.mShouldReverseLayout) {
            return l();
        }
        return j();
    }

    public void onAnchorReady(RecyclerView.v vVar, RecyclerView.a0 a0Var, a aVar, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.onDetachedFromWindow(recyclerView, vVar);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(vVar);
            vVar.c();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View onFocusSearchFailed(View view, int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        int convertFocusDirectionToLayoutDirection;
        View n;
        View t;
        B();
        if (getChildCount() == 0 || (convertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i)) == Integer.MIN_VALUE) {
            return null;
        }
        ensureLayoutState();
        F(convertFocusDirectionToLayoutDirection, (int) (this.mOrientationHelper.n() * MAX_SCROLL_FACTOR), DEBUG, a0Var);
        c cVar = this.mLayoutState;
        cVar.g = Integer.MIN_VALUE;
        cVar.a = DEBUG;
        fill(vVar, cVar, a0Var, true);
        if (convertFocusDirectionToLayoutDirection == -1) {
            n = o();
        } else {
            n = n();
        }
        if (convertFocusDirectionToLayoutDirection == -1) {
            t = u();
        } else {
            t = t();
        }
        if (t.hasFocusable()) {
            if (n == null) {
                return null;
            }
            return t;
        }
        return n;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
            accessibilityEvent.setToIndex(findLastVisibleItemPosition());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutChildren(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int r;
        int i6;
        View findViewByPosition;
        int g;
        int i7;
        int i8 = -1;
        if ((this.mPendingSavedState != null || this.mPendingScrollPosition != -1) && a0Var.b() == 0) {
            removeAndRecycleAllViews(vVar);
            return;
        }
        d dVar = this.mPendingSavedState;
        if (dVar != null && dVar.a()) {
            this.mPendingScrollPosition = this.mPendingSavedState.a;
        }
        ensureLayoutState();
        this.mLayoutState.a = DEBUG;
        B();
        View focusedChild = getFocusedChild();
        a aVar = this.mAnchorInfo;
        if (aVar.e && this.mPendingScrollPosition == -1 && this.mPendingSavedState == null) {
            if (focusedChild != null && (this.mOrientationHelper.g(focusedChild) >= this.mOrientationHelper.i() || this.mOrientationHelper.d(focusedChild) <= this.mOrientationHelper.m())) {
                this.mAnchorInfo.c(focusedChild, getPosition(focusedChild));
            }
        } else {
            aVar.e();
            a aVar2 = this.mAnchorInfo;
            aVar2.d = this.mShouldReverseLayout ^ this.mStackFromEnd;
            E(vVar, a0Var, aVar2);
            this.mAnchorInfo.e = true;
        }
        c cVar = this.mLayoutState;
        if (cVar.k >= 0) {
            i = 1;
        } else {
            i = -1;
        }
        cVar.f = i;
        int[] iArr = this.mReusableIntPair;
        iArr[0] = 0;
        iArr[1] = 0;
        calculateExtraLayoutSpace(a0Var, iArr);
        int max = Math.max(0, this.mReusableIntPair[0]) + this.mOrientationHelper.m();
        int max2 = Math.max(0, this.mReusableIntPair[1]) + this.mOrientationHelper.j();
        if (a0Var.e() && (i6 = this.mPendingScrollPosition) != -1 && this.mPendingScrollPositionOffset != Integer.MIN_VALUE && (findViewByPosition = findViewByPosition(i6)) != null) {
            if (this.mShouldReverseLayout) {
                i7 = this.mOrientationHelper.i() - this.mOrientationHelper.d(findViewByPosition);
                g = this.mPendingScrollPositionOffset;
            } else {
                g = this.mOrientationHelper.g(findViewByPosition) - this.mOrientationHelper.m();
                i7 = this.mPendingScrollPositionOffset;
            }
            int i9 = i7 - g;
            if (i9 > 0) {
                max += i9;
            } else {
                max2 -= i9;
            }
        }
        a aVar3 = this.mAnchorInfo;
        if (!aVar3.d ? !this.mShouldReverseLayout : this.mShouldReverseLayout) {
            i8 = 1;
        }
        onAnchorReady(vVar, a0Var, aVar3, i8);
        detachAndScrapAttachedViews(vVar);
        this.mLayoutState.m = resolveIsInfinite();
        this.mLayoutState.j = a0Var.e();
        this.mLayoutState.i = 0;
        a aVar4 = this.mAnchorInfo;
        if (aVar4.d) {
            J(aVar4);
            c cVar2 = this.mLayoutState;
            cVar2.h = max;
            fill(vVar, cVar2, a0Var, DEBUG);
            c cVar3 = this.mLayoutState;
            i3 = cVar3.b;
            int i10 = cVar3.d;
            int i11 = cVar3.c;
            if (i11 > 0) {
                max2 += i11;
            }
            H(this.mAnchorInfo);
            c cVar4 = this.mLayoutState;
            cVar4.h = max2;
            cVar4.d += cVar4.e;
            fill(vVar, cVar4, a0Var, DEBUG);
            c cVar5 = this.mLayoutState;
            i2 = cVar5.b;
            int i12 = cVar5.c;
            if (i12 > 0) {
                I(i10, i3);
                c cVar6 = this.mLayoutState;
                cVar6.h = i12;
                fill(vVar, cVar6, a0Var, DEBUG);
                i3 = this.mLayoutState.b;
            }
        } else {
            H(aVar4);
            c cVar7 = this.mLayoutState;
            cVar7.h = max2;
            fill(vVar, cVar7, a0Var, DEBUG);
            c cVar8 = this.mLayoutState;
            i2 = cVar8.b;
            int i13 = cVar8.d;
            int i14 = cVar8.c;
            if (i14 > 0) {
                max += i14;
            }
            J(this.mAnchorInfo);
            c cVar9 = this.mLayoutState;
            cVar9.h = max;
            cVar9.d += cVar9.e;
            fill(vVar, cVar9, a0Var, DEBUG);
            c cVar10 = this.mLayoutState;
            i3 = cVar10.b;
            int i15 = cVar10.c;
            if (i15 > 0) {
                G(i13, i2);
                c cVar11 = this.mLayoutState;
                cVar11.h = i15;
                fill(vVar, cVar11, a0Var, DEBUG);
                i2 = this.mLayoutState.b;
            }
        }
        if (getChildCount() > 0) {
            if (this.mShouldReverseLayout ^ this.mStackFromEnd) {
                int r2 = r(i2, vVar, a0Var, true);
                i4 = i3 + r2;
                i5 = i2 + r2;
                r = s(i4, vVar, a0Var, DEBUG);
            } else {
                int s2 = s(i3, vVar, a0Var, true);
                i4 = i3 + s2;
                i5 = i2 + s2;
                r = r(i5, vVar, a0Var, DEBUG);
            }
            i3 = i4 + r;
            i2 = i5 + r;
        }
        v(vVar, a0Var, i3, i2);
        if (!a0Var.e()) {
            this.mOrientationHelper.s();
        } else {
            this.mAnchorInfo.e();
        }
        this.mLastStackFromEnd = this.mStackFromEnd;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutCompleted(RecyclerView.a0 a0Var) {
        super.onLayoutCompleted(a0Var);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mAnchorInfo.e();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof d) {
            this.mPendingSavedState = (d) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            return new d(this.mPendingSavedState);
        }
        d dVar = new d();
        if (getChildCount() > 0) {
            ensureLayoutState();
            boolean z = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
            dVar.c = z;
            if (z) {
                View t = t();
                dVar.b = this.mOrientationHelper.i() - this.mOrientationHelper.d(t);
                dVar.a = getPosition(t);
            } else {
                View u = u();
                dVar.a = getPosition(u);
                dVar.b = this.mOrientationHelper.g(u) - this.mOrientationHelper.m();
            }
        } else {
            dVar.b();
        }
        return dVar;
    }

    public final View p(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.mShouldReverseLayout) {
            return k(vVar, a0Var);
        }
        return m(vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.f.b
    public void prepareForDrop(View view, View view2, int i, int i2) {
        char c2;
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        ensureLayoutState();
        B();
        int position = getPosition(view);
        int position2 = getPosition(view2);
        if (position < position2) {
            c2 = 1;
        } else {
            c2 = 65535;
        }
        if (this.mShouldReverseLayout) {
            if (c2 == 1) {
                scrollToPositionWithOffset(position2, this.mOrientationHelper.i() - (this.mOrientationHelper.g(view2) + this.mOrientationHelper.e(view)));
            } else {
                scrollToPositionWithOffset(position2, this.mOrientationHelper.i() - this.mOrientationHelper.d(view2));
            }
        } else if (c2 == 65535) {
            scrollToPositionWithOffset(position2, this.mOrientationHelper.g(view2));
        } else {
            scrollToPositionWithOffset(position2, this.mOrientationHelper.d(view2) - this.mOrientationHelper.e(view));
        }
    }

    public final View q(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.mShouldReverseLayout) {
            return m(vVar, a0Var);
        }
        return k(vVar, a0Var);
    }

    public final int r(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int i2;
        int i3 = this.mOrientationHelper.i() - i;
        if (i3 > 0) {
            int i4 = -scrollBy(-i3, vVar, a0Var);
            int i5 = i + i4;
            if (z && (i2 = this.mOrientationHelper.i() - i5) > 0) {
                this.mOrientationHelper.r(i2);
                return i2 + i4;
            }
            return i4;
        }
        return 0;
    }

    public boolean resolveIsInfinite() {
        if (this.mOrientationHelper.k() == 0 && this.mOrientationHelper.h() == 0) {
            return true;
        }
        return DEBUG;
    }

    public final int s(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int m;
        int m2 = i - this.mOrientationHelper.m();
        if (m2 > 0) {
            int i2 = -scrollBy(m2, vVar, a0Var);
            int i3 = i + i2;
            if (z && (m = i3 - this.mOrientationHelper.m()) > 0) {
                this.mOrientationHelper.r(-m);
                return i2 - m;
            }
            return i2;
        }
        return 0;
    }

    public int scrollBy(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        int i2;
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        ensureLayoutState();
        this.mLayoutState.a = true;
        if (i > 0) {
            i2 = 1;
        } else {
            i2 = -1;
        }
        int abs = Math.abs(i);
        F(i2, abs, true, a0Var);
        c cVar = this.mLayoutState;
        int fill = cVar.g + fill(vVar, cVar, a0Var, DEBUG);
        if (fill < 0) {
            return 0;
        }
        if (abs > fill) {
            i = i2 * fill;
        }
        this.mOrientationHelper.r(-i);
        this.mLayoutState.k = i;
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int scrollHorizontallyBy(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return scrollBy(i, vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void scrollToPosition(int i) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        d dVar = this.mPendingSavedState;
        if (dVar != null) {
            dVar.b();
        }
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = i2;
        d dVar = this.mPendingSavedState;
        if (dVar != null) {
            dVar.b();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int scrollVerticallyBy(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return scrollBy(i, vVar, a0Var);
    }

    public void setInitialPrefetchItemCount(int i) {
        this.mInitialPrefetchItemCount = i;
    }

    public void setOrientation(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        assertNotInLayoutOrScroll(null);
        if (i != this.mOrientation || this.mOrientationHelper == null) {
            m b2 = m.b(this, i);
            this.mOrientationHelper = b2;
            this.mAnchorInfo.a = b2;
            this.mOrientation = i;
            requestLayout();
        }
    }

    public void setRecycleChildrenOnDetach(boolean z) {
        this.mRecycleChildrenOnDetach = z;
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (z == this.mReverseLayout) {
            return;
        }
        this.mReverseLayout = z;
        requestLayout();
    }

    public void setSmoothScrollbarEnabled(boolean z) {
        this.mSmoothScrollbarEnabled = z;
    }

    public void setStackFromEnd(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (this.mStackFromEnd == z) {
            return;
        }
        this.mStackFromEnd = z;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean shouldMeasureTwice() {
        if (getHeightMode() != 1073741824 && getWidthMode() != 1073741824 && hasFlexibleChildInBothOrientations()) {
            return true;
        }
        return DEBUG;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.a0 a0Var, int i) {
        j jVar = new j(recyclerView.getContext());
        jVar.setTargetPosition(i);
        startSmoothScroll(jVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean supportsPredictiveItemAnimations() {
        if (this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd) {
            return true;
        }
        return DEBUG;
    }

    public final View t() {
        int childCount;
        if (this.mShouldReverseLayout) {
            childCount = 0;
        } else {
            childCount = getChildCount() - 1;
        }
        return getChildAt(childCount);
    }

    public final View u() {
        int i;
        if (this.mShouldReverseLayout) {
            i = getChildCount() - 1;
        } else {
            i = 0;
        }
        return getChildAt(i);
    }

    public final void v(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i, int i2) {
        boolean z;
        if (a0Var.g() && getChildCount() != 0 && !a0Var.e() && supportsPredictiveItemAnimations()) {
            List k = vVar.k();
            int size = k.size();
            int position = getPosition(getChildAt(0));
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < size; i5++) {
                RecyclerView.d0 d0Var = (RecyclerView.d0) k.get(i5);
                if (!d0Var.isRemoved()) {
                    char c2 = 1;
                    if (d0Var.getLayoutPosition() < position) {
                        z = true;
                    } else {
                        z = DEBUG;
                    }
                    if (z != this.mShouldReverseLayout) {
                        c2 = 65535;
                    }
                    if (c2 == 65535) {
                        i3 += this.mOrientationHelper.e(d0Var.itemView);
                    } else {
                        i4 += this.mOrientationHelper.e(d0Var.itemView);
                    }
                }
            }
            this.mLayoutState.l = k;
            if (i3 > 0) {
                I(getPosition(u()), i);
                c cVar = this.mLayoutState;
                cVar.h = i3;
                cVar.c = 0;
                cVar.a();
                fill(vVar, this.mLayoutState, a0Var, DEBUG);
            }
            if (i4 > 0) {
                G(getPosition(t()), i2);
                c cVar2 = this.mLayoutState;
                cVar2.h = i4;
                cVar2.c = 0;
                cVar2.a();
                fill(vVar, this.mLayoutState, a0Var, DEBUG);
            }
            this.mLayoutState.l = null;
        }
    }

    public void validateChildOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("validating child count ");
        sb.append(getChildCount());
        boolean z = true;
        if (getChildCount() < 1) {
            return;
        }
        int position = getPosition(getChildAt(0));
        int g = this.mOrientationHelper.g(getChildAt(0));
        if (this.mShouldReverseLayout) {
            for (int i = 1; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                int position2 = getPosition(childAt);
                int g2 = this.mOrientationHelper.g(childAt);
                if (position2 < position) {
                    w();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("detected invalid position. loc invalid? ");
                    if (g2 >= g) {
                        z = DEBUG;
                    }
                    sb2.append(z);
                    throw new RuntimeException(sb2.toString());
                } else if (g2 > g) {
                    w();
                    throw new RuntimeException("detected invalid location");
                }
            }
            return;
        }
        for (int i2 = 1; i2 < getChildCount(); i2++) {
            View childAt2 = getChildAt(i2);
            int position3 = getPosition(childAt2);
            int g3 = this.mOrientationHelper.g(childAt2);
            if (position3 < position) {
                w();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("detected invalid position. loc invalid? ");
                if (g3 >= g) {
                    z = DEBUG;
                }
                sb3.append(z);
                throw new RuntimeException(sb3.toString());
            } else if (g3 < g) {
                w();
                throw new RuntimeException("detected invalid location");
            }
        }
    }

    public final void w() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            StringBuilder sb = new StringBuilder();
            sb.append("item ");
            sb.append(getPosition(childAt));
            sb.append(", coord:");
            sb.append(this.mOrientationHelper.g(childAt));
        }
    }

    public final void x(RecyclerView.v vVar, c cVar) {
        if (cVar.a && !cVar.m) {
            int i = cVar.g;
            int i2 = cVar.i;
            if (cVar.f == -1) {
                z(vVar, i, i2);
            } else {
                A(vVar, i, i2);
            }
        }
    }

    public final void y(RecyclerView.v vVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 > i) {
            for (int i3 = i2 - 1; i3 >= i; i3--) {
                removeAndRecycleViewAt(i3, vVar);
            }
            return;
        }
        while (i > i2) {
            removeAndRecycleViewAt(i, vVar);
            i--;
        }
    }

    public final void z(RecyclerView.v vVar, int i, int i2) {
        int childCount = getChildCount();
        if (i < 0) {
            return;
        }
        int h = (this.mOrientationHelper.h() - i) + i2;
        if (this.mShouldReverseLayout) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (this.mOrientationHelper.g(childAt) < h || this.mOrientationHelper.q(childAt) < h) {
                    y(vVar, 0, i3);
                    return;
                }
            }
            return;
        }
        int i4 = childCount - 1;
        for (int i5 = i4; i5 >= 0; i5--) {
            View childAt2 = getChildAt(i5);
            if (this.mOrientationHelper.g(childAt2) < h || this.mOrientationHelper.q(childAt2) < h) {
                y(vVar, i4, i5);
                return;
            }
        }
    }

    public LinearLayoutManager(Context context, int i, boolean z) {
        this.mOrientation = 1;
        this.mReverseLayout = DEBUG;
        this.mShouldReverseLayout = DEBUG;
        this.mStackFromEnd = DEBUG;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new a();
        this.mLayoutChunkResult = new b();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        setOrientation(i);
        setReverseLayout(z);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mOrientation = 1;
        this.mReverseLayout = DEBUG;
        this.mShouldReverseLayout = DEBUG;
        this.mStackFromEnd = DEBUG;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new a();
        this.mLayoutChunkResult = new b();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        RecyclerView.o.d properties = RecyclerView.o.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.a);
        setReverseLayout(properties.c);
        setStackFromEnd(properties.d);
    }
}
