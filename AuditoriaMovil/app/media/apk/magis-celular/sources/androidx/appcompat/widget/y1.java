package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class y1 implements androidx.appcompat.view.menu.p {
    public static Method H;
    public static Method I;
    public static Method J;
    public final c A;
    public Runnable B;
    public final Handler C;
    public final Rect D;
    public Rect E;
    public boolean F;
    public PopupWindow G;
    public Context a;
    public ListAdapter b;
    public r1 c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public int m;
    public boolean n;
    public boolean o;
    public int p;
    public View q;
    public int r;

    /* renamed from: s  reason: collision with root package name */
    public DataSetObserver f16s;
    public View t;
    public Drawable u;
    public AdapterView.OnItemClickListener v;
    public AdapterView.OnItemSelectedListener w;
    public final g x;
    public final f y;
    public final e z;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View q = y1.this.q();
            if (q != null && q.getWindowToken() != null) {
                y1.this.show();
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements AdapterView.OnItemSelectedListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            r1 r1Var;
            if (i != -1 && (r1Var = y1.this.c) != null) {
                r1Var.setListSelectionHidden(false);
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            y1.this.o();
        }
    }

    /* loaded from: classes.dex */
    public class d extends DataSetObserver {
        public d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (y1.this.isShowing()) {
                y1.this.show();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            y1.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public class e implements AbsListView.OnScrollListener {
        public e() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && !y1.this.t() && y1.this.G.getContentView() != null) {
                y1 y1Var = y1.this;
                y1Var.C.removeCallbacks(y1Var.x);
                y1.this.x.run();
            }
        }
    }

    /* loaded from: classes.dex */
    public class f implements View.OnTouchListener {
        public f() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = y1.this.G) != null && popupWindow.isShowing() && x >= 0 && x < y1.this.G.getWidth() && y >= 0 && y < y1.this.G.getHeight()) {
                y1 y1Var = y1.this;
                y1Var.C.postDelayed(y1Var.x, 250L);
                return false;
            } else if (action == 1) {
                y1 y1Var2 = y1.this;
                y1Var2.C.removeCallbacks(y1Var2.x);
                return false;
            } else {
                return false;
            }
        }
    }

    /* loaded from: classes.dex */
    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            r1 r1Var = y1.this.c;
            if (r1Var != null && v.d1.P(r1Var) && y1.this.c.getCount() > y1.this.c.getChildCount()) {
                int childCount = y1.this.c.getChildCount();
                y1 y1Var = y1.this;
                if (childCount <= y1Var.p) {
                    y1Var.G.setInputMethodMode(2);
                    y1.this.show();
                }
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                H = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
            }
            try {
                J = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                I = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
            }
        }
    }

    public y1(Context context) {
        this(context, null, R$attr.listPopupWindowStyle);
    }

    public void A(Rect rect) {
        Rect rect2;
        if (rect != null) {
            rect2 = new Rect(rect);
        } else {
            rect2 = null;
        }
        this.E = rect2;
    }

    public void B(int i) {
        this.G.setInputMethodMode(i);
    }

    public void C(boolean z) {
        this.F = z;
        this.G.setFocusable(z);
    }

    public void D(PopupWindow.OnDismissListener onDismissListener) {
        this.G.setOnDismissListener(onDismissListener);
    }

    public void E(AdapterView.OnItemClickListener onItemClickListener) {
        this.v = onItemClickListener;
    }

    public void F(boolean z) {
        this.l = true;
        this.k = z;
    }

    public final void G(boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            this.G.setIsClippedToScreen(z);
            return;
        }
        Method method = H;
        if (method != null) {
            try {
                method.invoke(this.G, Boolean.valueOf(z));
            } catch (Exception unused) {
            }
        }
    }

    public void H(int i) {
        this.r = i;
    }

    public void I(int i) {
        r1 r1Var = this.c;
        if (isShowing() && r1Var != null) {
            r1Var.setListSelectionHidden(false);
            r1Var.setSelection(i);
            if (r1Var.getChoiceMode() != 0) {
                r1Var.setItemChecked(i, true);
            }
        }
    }

    public void J(int i) {
        this.e = i;
    }

    public int a() {
        return this.f;
    }

    public void c(int i) {
        this.f = i;
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        this.G.dismiss();
        v();
        this.G.setContentView(null);
        this.c = null;
        this.C.removeCallbacks(this.x);
    }

    public Drawable f() {
        return this.G.getBackground();
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView g() {
        return this.c;
    }

    public void i(int i) {
        this.g = i;
        this.i = true;
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean isShowing() {
        return this.G.isShowing();
    }

    public int l() {
        if (!this.i) {
            return 0;
        }
        return this.g;
    }

    public void m(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.f16s;
        if (dataSetObserver == null) {
            this.f16s = new d();
        } else {
            ListAdapter listAdapter2 = this.b;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f16s);
        }
        r1 r1Var = this.c;
        if (r1Var != null) {
            r1Var.setAdapter(this.b);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int n() {
        int i;
        int i2;
        int makeMeasureSpec;
        int i3;
        boolean z = true;
        if (this.c == null) {
            Context context = this.a;
            this.B = new a();
            r1 p = p(context, !this.F);
            this.c = p;
            Drawable drawable = this.u;
            if (drawable != null) {
                p.setSelector(drawable);
            }
            this.c.setAdapter(this.b);
            this.c.setOnItemClickListener(this.v);
            this.c.setFocusable(true);
            this.c.setFocusableInTouchMode(true);
            this.c.setOnItemSelectedListener(new b());
            this.c.setOnScrollListener(this.z);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.w;
            if (onItemSelectedListener != null) {
                this.c.setOnItemSelectedListener(onItemSelectedListener);
            }
            r1 r1Var = this.c;
            View view = this.q;
            if (view != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i4 = this.r;
                if (i4 != 0) {
                    if (i4 != 1) {
                        Log.e("ListPopupWindow", "Invalid hint position " + this.r);
                    } else {
                        linearLayout.addView(r1Var, layoutParams);
                        linearLayout.addView(view);
                    }
                } else {
                    linearLayout.addView(view);
                    linearLayout.addView(r1Var, layoutParams);
                }
                int i5 = this.e;
                if (i5 >= 0) {
                    i3 = Integer.MIN_VALUE;
                } else {
                    i5 = 0;
                    i3 = 0;
                }
                view.measure(View.MeasureSpec.makeMeasureSpec(i5, i3), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view.getLayoutParams();
                i = view.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                r1Var = linearLayout;
            } else {
                i = 0;
            }
            this.G.setContentView(r1Var);
        } else {
            ViewGroup viewGroup = (ViewGroup) this.G.getContentView();
            View view2 = this.q;
            if (view2 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                i = view2.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                i = 0;
            }
        }
        Drawable background = this.G.getBackground();
        if (background != null) {
            background.getPadding(this.D);
            Rect rect = this.D;
            int i6 = rect.top;
            i2 = rect.bottom + i6;
            if (!this.i) {
                this.g = -i6;
            }
        } else {
            this.D.setEmpty();
            i2 = 0;
        }
        if (this.G.getInputMethodMode() != 2) {
            z = false;
        }
        int r = r(q(), this.g, z);
        if (!this.n && this.d != -1) {
            int i7 = this.e;
            if (i7 != -2) {
                if (i7 != -1) {
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7, 1073741824);
                } else {
                    int i8 = this.a.getResources().getDisplayMetrics().widthPixels;
                    Rect rect2 = this.D;
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8 - (rect2.left + rect2.right), 1073741824);
                }
            } else {
                int i9 = this.a.getResources().getDisplayMetrics().widthPixels;
                Rect rect3 = this.D;
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i9 - (rect3.left + rect3.right), Integer.MIN_VALUE);
            }
            int d2 = this.c.d(makeMeasureSpec, 0, -1, r - i, -1);
            if (d2 > 0) {
                i += i2 + this.c.getPaddingTop() + this.c.getPaddingBottom();
            }
            return d2 + i;
        }
        return r + i2;
    }

    public void o() {
        r1 r1Var = this.c;
        if (r1Var != null) {
            r1Var.setListSelectionHidden(true);
            r1Var.requestLayout();
        }
    }

    public r1 p(Context context, boolean z) {
        return new r1(context, z);
    }

    public View q() {
        return this.t;
    }

    public final int r(View view, int i, boolean z) {
        int maxAvailableHeight;
        if (Build.VERSION.SDK_INT > 23) {
            maxAvailableHeight = this.G.getMaxAvailableHeight(view, i, z);
            return maxAvailableHeight;
        }
        Method method = I;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.G, view, Integer.valueOf(i), Boolean.valueOf(z))).intValue();
            } catch (Exception unused) {
            }
        }
        return this.G.getMaxAvailableHeight(view, i);
    }

    public int s() {
        return this.e;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.G.setBackgroundDrawable(drawable);
    }

    @Override // androidx.appcompat.view.menu.p
    public void show() {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int n = n();
        boolean t = t();
        androidx.core.widget.t.b(this.G, this.h);
        boolean z2 = true;
        if (this.G.isShowing()) {
            if (!v.d1.P(q())) {
                return;
            }
            int i5 = this.e;
            if (i5 == -1) {
                i5 = -1;
            } else if (i5 == -2) {
                i5 = q().getWidth();
            }
            int i6 = this.d;
            if (i6 == -1) {
                if (!t) {
                    n = -1;
                }
                if (t) {
                    PopupWindow popupWindow = this.G;
                    if (this.e == -1) {
                        i4 = -1;
                    } else {
                        i4 = 0;
                    }
                    popupWindow.setWidth(i4);
                    this.G.setHeight(0);
                } else {
                    PopupWindow popupWindow2 = this.G;
                    if (this.e == -1) {
                        i3 = -1;
                    } else {
                        i3 = 0;
                    }
                    popupWindow2.setWidth(i3);
                    this.G.setHeight(-1);
                }
            } else if (i6 != -2) {
                n = i6;
            }
            this.G.setOutsideTouchable((this.o || this.n) ? false : false);
            PopupWindow popupWindow3 = this.G;
            View q = q();
            int i7 = this.f;
            int i8 = this.g;
            if (i5 < 0) {
                i = -1;
            } else {
                i = i5;
            }
            if (n < 0) {
                i2 = -1;
            } else {
                i2 = n;
            }
            popupWindow3.update(q, i7, i8, i, i2);
            return;
        }
        int i9 = this.e;
        if (i9 == -1) {
            i9 = -1;
        } else if (i9 == -2) {
            i9 = q().getWidth();
        }
        int i10 = this.d;
        if (i10 == -1) {
            n = -1;
        } else if (i10 != -2) {
            n = i10;
        }
        this.G.setWidth(i9);
        this.G.setHeight(n);
        G(true);
        PopupWindow popupWindow4 = this.G;
        if (!this.o && !this.n) {
            z = true;
        } else {
            z = false;
        }
        popupWindow4.setOutsideTouchable(z);
        this.G.setTouchInterceptor(this.y);
        if (this.l) {
            androidx.core.widget.t.a(this.G, this.k);
        }
        if (Build.VERSION.SDK_INT > 28) {
            this.G.setEpicenterBounds(this.E);
        } else {
            Method method = J;
            if (method != null) {
                try {
                    method.invoke(this.G, this.E);
                } catch (Exception e2) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
        }
        androidx.core.widget.t.c(this.G, q(), this.f, this.g, this.m);
        this.c.setSelection(-1);
        if (!this.F || this.c.isInTouchMode()) {
            o();
        }
        if (!this.F) {
            this.C.post(this.A);
        }
    }

    public boolean t() {
        if (this.G.getInputMethodMode() == 2) {
            return true;
        }
        return false;
    }

    public boolean u() {
        return this.F;
    }

    public final void v() {
        View view = this.q;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.q);
            }
        }
    }

    public void w(View view) {
        this.t = view;
    }

    public void x(int i) {
        this.G.setAnimationStyle(i);
    }

    public void y(int i) {
        Drawable background = this.G.getBackground();
        if (background != null) {
            background.getPadding(this.D);
            Rect rect = this.D;
            this.e = rect.left + rect.right + i;
            return;
        }
        J(i);
    }

    public void z(int i) {
        this.m = i;
    }

    public y1(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public y1(Context context, AttributeSet attributeSet, int i, int i2) {
        this.d = -2;
        this.e = -2;
        this.h = 1002;
        this.j = true;
        this.m = 0;
        this.n = false;
        this.o = false;
        this.p = Integer.MAX_VALUE;
        this.r = 0;
        this.x = new g();
        this.y = new f();
        this.z = new e();
        this.A = new c();
        this.D = new Rect();
        this.a = context;
        this.C = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.z, i, i2);
        this.f = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.g = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.i = true;
        }
        obtainStyledAttributes.recycle();
        s sVar = new s(context, attributeSet, i, i2);
        this.G = sVar;
        sVar.setInputMethodMode(1);
    }
}
