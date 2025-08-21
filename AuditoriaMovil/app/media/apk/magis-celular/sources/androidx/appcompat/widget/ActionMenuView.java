package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.u1;
/* loaded from: classes.dex */
public class ActionMenuView extends u1 implements g.b, androidx.appcompat.view.menu.n {
    public androidx.appcompat.view.menu.g a;
    public Context b;
    public int c;
    public boolean d;
    public androidx.appcompat.widget.d e;
    public m.a f;
    public g.a g;
    public boolean h;
    public int i;
    public int j;
    public int k;
    public e l;

    /* loaded from: classes.dex */
    public interface a {
        boolean a();

        boolean b();
    }

    /* loaded from: classes.dex */
    public static class b implements m.a {
        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
        }
    }

    /* loaded from: classes.dex */
    public static class c extends u1.a {
        public boolean c;
        public int d;
        public int e;
        public boolean f;
        public boolean g;
        public boolean h;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(c cVar) {
            super(cVar);
            this.c = cVar.c;
        }

        public c(int i, int i2) {
            super(i, i2);
            this.c = false;
        }
    }

    /* loaded from: classes.dex */
    public class d implements g.a {
        public d() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean onMenuItemSelected(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            e eVar = ActionMenuView.this.l;
            if (eVar != null && eVar.onMenuItemClick(menuItem)) {
                return true;
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void onMenuModeChange(androidx.appcompat.view.menu.g gVar) {
            g.a aVar = ActionMenuView.this.g;
            if (aVar != null) {
                aVar.onMenuModeChange(gVar);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface e {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public static int q(View view, int i, int i2, int i3, int i4) {
        ActionMenuItemView actionMenuItemView;
        boolean z;
        int i5;
        c cVar = (c) view.getLayoutParams();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3) - i4, View.MeasureSpec.getMode(i3));
        if (view instanceof ActionMenuItemView) {
            actionMenuItemView = (ActionMenuItemView) view;
        } else {
            actionMenuItemView = null;
        }
        boolean z2 = true;
        if (actionMenuItemView != null && actionMenuItemView.d()) {
            z = true;
        } else {
            z = false;
        }
        if (i2 > 0) {
            i5 = 2;
            if (!z || i2 >= 2) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i2 * i, Integer.MIN_VALUE), makeMeasureSpec);
                int measuredWidth = view.getMeasuredWidth();
                int i6 = measuredWidth / i;
                if (measuredWidth % i != 0) {
                    i6++;
                }
                if (!z || i6 >= 2) {
                    i5 = i6;
                }
                cVar.f = (cVar.c && z) ? false : false;
                cVar.d = i5;
                view.measure(View.MeasureSpec.makeMeasureSpec(i * i5, 1073741824), makeMeasureSpec);
                return i5;
            }
        }
        i5 = 0;
        cVar.f = (cVar.c && z) ? false : false;
        cVar.d = i5;
        view.measure(View.MeasureSpec.makeMeasureSpec(i * i5, 1073741824), makeMeasureSpec);
        return i5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.g.b
    public boolean a(androidx.appcompat.view.menu.i iVar) {
        return this.a.performItemAction(iVar, 0);
    }

    @Override // androidx.appcompat.widget.u1, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof c;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public void g() {
        androidx.appcompat.widget.d dVar = this.e;
        if (dVar != null) {
            dVar.p();
        }
    }

    public Menu getMenu() {
        if (this.a == null) {
            Context context = getContext();
            androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(context);
            this.a = gVar;
            gVar.setCallback(new d());
            androidx.appcompat.widget.d dVar = new androidx.appcompat.widget.d(context);
            this.e = dVar;
            dVar.A(true);
            androidx.appcompat.widget.d dVar2 = this.e;
            m.a aVar = this.f;
            if (aVar == null) {
                aVar = new b();
            }
            dVar2.setCallback(aVar);
            this.a.addMenuPresenter(this.e, this.b);
            this.e.y(this);
        }
        return this.a;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.e.r();
    }

    public int getPopupTheme() {
        return this.c;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // androidx.appcompat.widget.u1, android.view.ViewGroup
    /* renamed from: h */
    public c generateDefaultLayoutParams() {
        c cVar = new c(-2, -2);
        cVar.b = 16;
        return cVar;
    }

    @Override // androidx.appcompat.widget.u1, android.view.ViewGroup
    /* renamed from: i */
    public c generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.view.menu.n
    public void initialize(androidx.appcompat.view.menu.g gVar) {
        this.a = gVar;
    }

    @Override // androidx.appcompat.widget.u1, android.view.ViewGroup
    /* renamed from: j */
    public c generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        c cVar;
        if (layoutParams != null) {
            if (layoutParams instanceof c) {
                cVar = new c((c) layoutParams);
            } else {
                cVar = new c(layoutParams);
            }
            if (cVar.b <= 0) {
                cVar.b = 16;
            }
            return cVar;
        }
        return generateDefaultLayoutParams();
    }

    public c k() {
        c generateDefaultLayoutParams = generateDefaultLayoutParams();
        generateDefaultLayoutParams.c = true;
        return generateDefaultLayoutParams;
    }

    public boolean l(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        View childAt = getChildAt(i - 1);
        View childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof a)) {
            z = false | ((a) childAt).a();
        }
        if (i > 0 && (childAt2 instanceof a)) {
            return z | ((a) childAt2).b();
        }
        return z;
    }

    public boolean m() {
        androidx.appcompat.widget.d dVar = this.e;
        if (dVar != null && dVar.s()) {
            return true;
        }
        return false;
    }

    public boolean n() {
        androidx.appcompat.widget.d dVar = this.e;
        if (dVar != null && dVar.u()) {
            return true;
        }
        return false;
    }

    public boolean o() {
        androidx.appcompat.widget.d dVar = this.e;
        if (dVar != null && dVar.v()) {
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        androidx.appcompat.widget.d dVar = this.e;
        if (dVar != null) {
            dVar.updateMenuView(false);
            if (this.e.v()) {
                this.e.s();
                this.e.B();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        g();
    }

    @Override // androidx.appcompat.widget.u1, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int width;
        int i6;
        if (!this.h) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i7 = (i4 - i2) / 2;
        int dividerWidth = getDividerWidth();
        int i8 = i3 - i;
        int paddingRight = (i8 - getPaddingRight()) - getPaddingLeft();
        boolean b2 = y2.b(this);
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.c) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (l(i11)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (b2) {
                        i6 = getPaddingLeft() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
                        width = i6 + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                        i6 = width - measuredWidth;
                    }
                    int i12 = i7 - (measuredHeight / 2);
                    childAt.layout(i6, i12, width, measuredHeight + i12);
                    paddingRight -= measuredWidth;
                    i9 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin) + ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                    l(i11);
                    i10++;
                }
            }
        }
        if (childCount == 1 && i9 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i13 = (i8 / 2) - (measuredWidth2 / 2);
            int i14 = i7 - (measuredHeight2 / 2);
            childAt2.layout(i13, i14, measuredWidth2 + i13, measuredHeight2 + i14);
            return;
        }
        int i15 = i10 - (i9 ^ 1);
        if (i15 > 0) {
            i5 = paddingRight / i15;
        } else {
            i5 = 0;
        }
        int max = Math.max(0, i5);
        if (b2) {
            int width2 = getWidth() - getPaddingRight();
            for (int i16 = 0; i16 < childCount; i16++) {
                View childAt3 = getChildAt(i16);
                c cVar2 = (c) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !cVar2.c) {
                    int i17 = width2 - ((ViewGroup.MarginLayoutParams) cVar2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i18 = i7 - (measuredHeight3 / 2);
                    childAt3.layout(i17 - measuredWidth3, i18, i17, measuredHeight3 + i18);
                    width2 = i17 - ((measuredWidth3 + ((ViewGroup.MarginLayoutParams) cVar2).leftMargin) + max);
                }
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt4 = getChildAt(i19);
            c cVar3 = (c) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !cVar3.c) {
                int i20 = paddingLeft + ((ViewGroup.MarginLayoutParams) cVar3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i21 = i7 - (measuredHeight4 / 2);
                childAt4.layout(i20, i21, i20 + measuredWidth4, measuredHeight4 + i21);
                paddingLeft = i20 + measuredWidth4 + ((ViewGroup.MarginLayoutParams) cVar3).rightMargin + max;
            }
        }
    }

    @Override // androidx.appcompat.widget.u1, android.view.View
    public void onMeasure(int i, int i2) {
        boolean z;
        androidx.appcompat.view.menu.g gVar;
        boolean z2 = this.h;
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            z = true;
        } else {
            z = false;
        }
        this.h = z;
        if (z2 != z) {
            this.i = 0;
        }
        int size = View.MeasureSpec.getSize(i);
        if (this.h && (gVar = this.a) != null && size != this.i) {
            this.i = size;
            gVar.onItemsChanged(true);
        }
        int childCount = getChildCount();
        if (this.h && childCount > 0) {
            r(i, i2);
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            c cVar = (c) getChildAt(i3).getLayoutParams();
            ((ViewGroup.MarginLayoutParams) cVar).rightMargin = 0;
            ((ViewGroup.MarginLayoutParams) cVar).leftMargin = 0;
        }
        super.onMeasure(i, i2);
    }

    public boolean p() {
        return this.d;
    }

    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9, types: [boolean, int] */
    public final void r(int i, int i2) {
        boolean z;
        int i3;
        int i4;
        boolean z2;
        int i5;
        boolean z3;
        boolean z4;
        int i6;
        int i7;
        boolean z5;
        int i8;
        ?? r14;
        boolean z6;
        int i9;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingTop, -2);
        int i10 = size - paddingLeft;
        int i11 = this.j;
        int i12 = i10 / i11;
        int i13 = i10 % i11;
        if (i12 == 0) {
            setMeasuredDimension(i10, 0);
            return;
        }
        int i14 = i11 + (i13 / i12);
        int childCount = getChildCount();
        int i15 = 0;
        int i16 = 0;
        boolean z7 = false;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        long j = 0;
        while (i16 < childCount) {
            View childAt = getChildAt(i16);
            int i20 = size2;
            if (childAt.getVisibility() != 8) {
                boolean z8 = childAt instanceof ActionMenuItemView;
                int i21 = i17 + 1;
                if (z8) {
                    int i22 = this.k;
                    i8 = i21;
                    r14 = 0;
                    childAt.setPadding(i22, 0, i22, 0);
                } else {
                    i8 = i21;
                    r14 = 0;
                }
                c cVar = (c) childAt.getLayoutParams();
                cVar.h = r14;
                cVar.e = r14;
                cVar.d = r14;
                cVar.f = r14;
                ((ViewGroup.MarginLayoutParams) cVar).leftMargin = r14;
                ((ViewGroup.MarginLayoutParams) cVar).rightMargin = r14;
                if (z8 && ((ActionMenuItemView) childAt).d()) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                cVar.g = z6;
                if (cVar.c) {
                    i9 = 1;
                } else {
                    i9 = i12;
                }
                int q = q(childAt, i14, i9, childMeasureSpec, paddingTop);
                i18 = Math.max(i18, q);
                if (cVar.f) {
                    i19++;
                }
                if (cVar.c) {
                    z7 = true;
                }
                i12 -= q;
                i15 = Math.max(i15, childAt.getMeasuredHeight());
                if (q == 1) {
                    j |= 1 << i16;
                    i15 = i15;
                }
                i17 = i8;
            }
            i16++;
            size2 = i20;
        }
        int i23 = size2;
        if (z7 && i17 == 2) {
            z = true;
        } else {
            z = false;
        }
        boolean z9 = false;
        while (i19 > 0 && i12 > 0) {
            int i24 = 0;
            int i25 = 0;
            int i26 = Integer.MAX_VALUE;
            long j2 = 0;
            while (i25 < childCount) {
                boolean z10 = z9;
                c cVar2 = (c) getChildAt(i25).getLayoutParams();
                int i27 = i15;
                if (cVar2.f) {
                    int i28 = cVar2.d;
                    if (i28 < i26) {
                        j2 = 1 << i25;
                        i26 = i28;
                        i24 = 1;
                    } else if (i28 == i26) {
                        i24++;
                        j2 |= 1 << i25;
                    }
                }
                i25++;
                i15 = i27;
                z9 = z10;
            }
            z2 = z9;
            i5 = i15;
            j |= j2;
            if (i24 > i12) {
                i3 = mode;
                i4 = i10;
                break;
            }
            int i29 = i26 + 1;
            int i30 = 0;
            while (i30 < childCount) {
                View childAt2 = getChildAt(i30);
                c cVar3 = (c) childAt2.getLayoutParams();
                int i31 = i10;
                int i32 = mode;
                long j3 = 1 << i30;
                if ((j2 & j3) == 0) {
                    if (cVar3.d == i29) {
                        j |= j3;
                    }
                    z5 = z;
                } else {
                    if (z && cVar3.g && i12 == 1) {
                        int i33 = this.k;
                        z5 = z;
                        childAt2.setPadding(i33 + i14, 0, i33, 0);
                    } else {
                        z5 = z;
                    }
                    cVar3.d++;
                    cVar3.h = true;
                    i12--;
                }
                i30++;
                mode = i32;
                i10 = i31;
                z = z5;
            }
            i15 = i5;
            z9 = true;
        }
        i3 = mode;
        i4 = i10;
        z2 = z9;
        i5 = i15;
        if (!z7 && i17 == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (i12 > 0 && j != 0 && (i12 < i17 - 1 || z3 || i18 > 1)) {
            float bitCount = Long.bitCount(j);
            if (!z3) {
                if ((j & 1) != 0 && !((c) getChildAt(0).getLayoutParams()).g) {
                    bitCount -= 0.5f;
                }
                int i34 = childCount - 1;
                if ((j & (1 << i34)) != 0 && !((c) getChildAt(i34).getLayoutParams()).g) {
                    bitCount -= 0.5f;
                }
            }
            if (bitCount > 0.0f) {
                i7 = (int) ((i12 * i14) / bitCount);
            } else {
                i7 = 0;
            }
            z4 = z2;
            for (int i35 = 0; i35 < childCount; i35++) {
                if ((j & (1 << i35)) != 0) {
                    View childAt3 = getChildAt(i35);
                    c cVar4 = (c) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        cVar4.e = i7;
                        cVar4.h = true;
                        if (i35 == 0 && !cVar4.g) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = (-i7) / 2;
                        }
                    } else if (cVar4.c) {
                        cVar4.e = i7;
                        cVar4.h = true;
                        ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = (-i7) / 2;
                    } else {
                        if (i35 != 0) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = i7 / 2;
                        }
                        if (i35 != childCount - 1) {
                            ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = i7 / 2;
                        }
                    }
                    z4 = true;
                }
            }
        } else {
            z4 = z2;
        }
        if (z4) {
            for (int i36 = 0; i36 < childCount; i36++) {
                View childAt4 = getChildAt(i36);
                c cVar5 = (c) childAt4.getLayoutParams();
                if (cVar5.h) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((cVar5.d * i14) + cVar5.e, 1073741824), childMeasureSpec);
                }
            }
        }
        if (i3 != 1073741824) {
            i6 = i5;
        } else {
            i6 = i23;
        }
        setMeasuredDimension(i4, i6);
    }

    public androidx.appcompat.view.menu.g s() {
        return this.a;
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.e.x(z);
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.l = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.e.z(drawable);
    }

    public void setOverflowReserved(boolean z) {
        this.d = z;
    }

    public void setPopupTheme(int i) {
        if (this.c != i) {
            this.c = i;
            if (i == 0) {
                this.b = getContext();
            } else {
                this.b = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setPresenter(androidx.appcompat.widget.d dVar) {
        this.e = dVar;
        dVar.y(this);
    }

    public void t(m.a aVar, g.a aVar2) {
        this.f = aVar;
        this.g = aVar2;
    }

    public boolean u() {
        androidx.appcompat.widget.d dVar = this.e;
        if (dVar != null && dVar.B()) {
            return true;
        }
        return false;
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.j = (int) (56.0f * f);
        this.k = (int) (f * 4.0f);
        this.b = context;
        this.c = 0;
    }
}
