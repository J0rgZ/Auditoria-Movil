package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public abstract class m {
    public final RecyclerView.o a;
    public int b;
    public final Rect c;

    /* loaded from: classes.dex */
    public static class a extends m {
        public a(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // androidx.recyclerview.widget.m
        public int d(View view) {
            return this.a.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).rightMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.a.getDecoratedMeasuredWidth(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.a.getDecoratedMeasuredHeight(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int g(View view) {
            return this.a.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).leftMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int h() {
            return this.a.getWidth();
        }

        @Override // androidx.recyclerview.widget.m
        public int i() {
            return this.a.getWidth() - this.a.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.m
        public int j() {
            return this.a.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.m
        public int k() {
            return this.a.getWidthMode();
        }

        @Override // androidx.recyclerview.widget.m
        public int l() {
            return this.a.getHeightMode();
        }

        @Override // androidx.recyclerview.widget.m
        public int m() {
            return this.a.getPaddingLeft();
        }

        @Override // androidx.recyclerview.widget.m
        public int n() {
            return (this.a.getWidth() - this.a.getPaddingLeft()) - this.a.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.m
        public int p(View view) {
            this.a.getTransformedBoundingBox(view, true, this.c);
            return this.c.right;
        }

        @Override // androidx.recyclerview.widget.m
        public int q(View view) {
            this.a.getTransformedBoundingBox(view, true, this.c);
            return this.c.left;
        }

        @Override // androidx.recyclerview.widget.m
        public void r(int i) {
            this.a.offsetChildrenHorizontal(i);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends m {
        public b(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // androidx.recyclerview.widget.m
        public int d(View view) {
            return this.a.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.a.getDecoratedMeasuredHeight(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.a.getDecoratedMeasuredWidth(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int g(View view) {
            return this.a.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).topMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int h() {
            return this.a.getHeight();
        }

        @Override // androidx.recyclerview.widget.m
        public int i() {
            return this.a.getHeight() - this.a.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.m
        public int j() {
            return this.a.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.m
        public int k() {
            return this.a.getHeightMode();
        }

        @Override // androidx.recyclerview.widget.m
        public int l() {
            return this.a.getWidthMode();
        }

        @Override // androidx.recyclerview.widget.m
        public int m() {
            return this.a.getPaddingTop();
        }

        @Override // androidx.recyclerview.widget.m
        public int n() {
            return (this.a.getHeight() - this.a.getPaddingTop()) - this.a.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.m
        public int p(View view) {
            this.a.getTransformedBoundingBox(view, true, this.c);
            return this.c.bottom;
        }

        @Override // androidx.recyclerview.widget.m
        public int q(View view) {
            this.a.getTransformedBoundingBox(view, true, this.c);
            return this.c.top;
        }

        @Override // androidx.recyclerview.widget.m
        public void r(int i) {
            this.a.offsetChildrenVertical(i);
        }
    }

    public /* synthetic */ m(RecyclerView.o oVar, a aVar) {
        this(oVar);
    }

    public static m a(RecyclerView.o oVar) {
        return new a(oVar);
    }

    public static m b(RecyclerView.o oVar, int i) {
        if (i != 0) {
            if (i == 1) {
                return c(oVar);
            }
            throw new IllegalArgumentException("invalid orientation");
        }
        return a(oVar);
    }

    public static m c(RecyclerView.o oVar) {
        return new b(oVar);
    }

    public abstract int d(View view);

    public abstract int e(View view);

    public abstract int f(View view);

    public abstract int g(View view);

    public abstract int h();

    public abstract int i();

    public abstract int j();

    public abstract int k();

    public abstract int l();

    public abstract int m();

    public abstract int n();

    public int o() {
        if (Integer.MIN_VALUE == this.b) {
            return 0;
        }
        return n() - this.b;
    }

    public abstract int p(View view);

    public abstract int q(View view);

    public abstract void r(int i);

    public void s() {
        this.b = n();
    }

    public m(RecyclerView.o oVar) {
        this.b = Integer.MIN_VALUE;
        this.c = new Rect();
        this.a = oVar;
    }
}
