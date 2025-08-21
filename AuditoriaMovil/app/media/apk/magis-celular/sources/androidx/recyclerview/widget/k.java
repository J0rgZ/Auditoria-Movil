package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public class k extends r {
    public m d;
    public m e;

    @Override // androidx.recyclerview.widget.r
    public int[] c(RecyclerView.o oVar, View view) {
        int[] iArr = new int[2];
        if (oVar.canScrollHorizontally()) {
            iArr[0] = n(oVar, view, q(oVar));
        } else {
            iArr[0] = 0;
        }
        if (oVar.canScrollVertically()) {
            iArr[1] = n(oVar, view, r(oVar));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.r
    public View h(RecyclerView.o oVar) {
        if (oVar.canScrollVertically()) {
            return p(oVar, r(oVar));
        }
        if (oVar.canScrollHorizontally()) {
            return p(oVar, q(oVar));
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.r
    public int i(RecyclerView.o oVar, int i, int i2) {
        int itemCount;
        View h;
        int position;
        int i3;
        PointF computeScrollVectorForPosition;
        int i4;
        int i5;
        if (!(oVar instanceof RecyclerView.z.b) || (itemCount = oVar.getItemCount()) == 0 || (h = h(oVar)) == null || (position = oVar.getPosition(h)) == -1 || (computeScrollVectorForPosition = ((RecyclerView.z.b) oVar).computeScrollVectorForPosition(itemCount - 1)) == null) {
            return -1;
        }
        int i6 = 0;
        if (oVar.canScrollHorizontally()) {
            i4 = o(oVar, q(oVar), i, 0);
            if (computeScrollVectorForPosition.x < 0.0f) {
                i4 = -i4;
            }
        } else {
            i4 = 0;
        }
        if (oVar.canScrollVertically()) {
            i5 = o(oVar, r(oVar), 0, i2);
            if (computeScrollVectorForPosition.y < 0.0f) {
                i5 = -i5;
            }
        } else {
            i5 = 0;
        }
        if (oVar.canScrollVertically()) {
            i4 = i5;
        }
        if (i4 == 0) {
            return -1;
        }
        int i7 = position + i4;
        if (i7 >= 0) {
            i6 = i7;
        }
        if (i6 < itemCount) {
            return i6;
        }
        return i3;
    }

    public final float m(RecyclerView.o oVar, m mVar) {
        int childCount = oVar.getChildCount();
        if (childCount == 0) {
            return 1.0f;
        }
        View view = null;
        View view2 = null;
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = oVar.getChildAt(i3);
            int position = oVar.getPosition(childAt);
            if (position != -1) {
                if (position < i) {
                    view = childAt;
                    i = position;
                }
                if (position > i2) {
                    view2 = childAt;
                    i2 = position;
                }
            }
        }
        if (view == null || view2 == null) {
            return 1.0f;
        }
        int max = Math.max(mVar.d(view), mVar.d(view2)) - Math.min(mVar.g(view), mVar.g(view2));
        if (max == 0) {
            return 1.0f;
        }
        return (max * 1.0f) / ((i2 - i) + 1);
    }

    public final int n(RecyclerView.o oVar, View view, m mVar) {
        return (mVar.g(view) + (mVar.e(view) / 2)) - (mVar.m() + (mVar.n() / 2));
    }

    public final int o(RecyclerView.o oVar, m mVar, int i, int i2) {
        int i3;
        int[] d = d(i, i2);
        float m = m(oVar, mVar);
        if (m <= 0.0f) {
            return 0;
        }
        if (Math.abs(d[0]) > Math.abs(d[1])) {
            i3 = d[0];
        } else {
            i3 = d[1];
        }
        return Math.round(i3 / m);
    }

    public final View p(RecyclerView.o oVar, m mVar) {
        int childCount = oVar.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int m = mVar.m() + (mVar.n() / 2);
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = oVar.getChildAt(i2);
            int abs = Math.abs((mVar.g(childAt) + (mVar.e(childAt) / 2)) - m);
            if (abs < i) {
                view = childAt;
                i = abs;
            }
        }
        return view;
    }

    public final m q(RecyclerView.o oVar) {
        m mVar = this.e;
        if (mVar == null || mVar.a != oVar) {
            this.e = m.a(oVar);
        }
        return this.e;
    }

    public final m r(RecyclerView.o oVar) {
        m mVar = this.d;
        if (mVar == null || mVar.a != oVar) {
            this.d = m.c(oVar);
        }
        return this.d;
    }
}
