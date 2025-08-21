package androidx.appcompat.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.R$attr;
import androidx.appcompat.app.c;
/* loaded from: classes.dex */
public class x extends Spinner implements v.g0 {
    public static final int[] i = {16843505};
    public final androidx.appcompat.widget.f a;
    public final Context b;
    public t1 c;
    public SpinnerAdapter d;
    public final boolean e;
    public g f;
    public int g;
    public final Rect h;

    /* loaded from: classes.dex */
    public class a extends t1 {
        public final /* synthetic */ e j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(View view, e eVar) {
            super(view);
            this.j = eVar;
        }

        @Override // androidx.appcompat.widget.t1
        public androidx.appcompat.view.menu.p b() {
            return this.j;
        }

        @Override // androidx.appcompat.widget.t1
        public boolean c() {
            if (!x.this.getInternalPopup().isShowing()) {
                x.this.b();
                return true;
            }
            return true;
        }
    }

    /* loaded from: classes.dex */
    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!x.this.getInternalPopup().isShowing()) {
                x.this.b();
            }
            ViewTreeObserver viewTreeObserver = x.this.getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.removeOnGlobalLayoutListener(this);
            }
        }
    }

    /* loaded from: classes.dex */
    public class c implements g, DialogInterface.OnClickListener {
        public androidx.appcompat.app.c a;
        public ListAdapter b;
        public CharSequence c;

        public c() {
        }

        @Override // androidx.appcompat.widget.x.g
        public int a() {
            return 0;
        }

        @Override // androidx.appcompat.widget.x.g
        public void c(int i) {
            Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.x.g
        public CharSequence d() {
            return this.c;
        }

        @Override // androidx.appcompat.widget.x.g
        public void dismiss() {
            androidx.appcompat.app.c cVar = this.a;
            if (cVar != null) {
                cVar.dismiss();
                this.a = null;
            }
        }

        @Override // androidx.appcompat.widget.x.g
        public Drawable f() {
            return null;
        }

        @Override // androidx.appcompat.widget.x.g
        public void h(CharSequence charSequence) {
            this.c = charSequence;
        }

        @Override // androidx.appcompat.widget.x.g
        public void i(int i) {
            Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.x.g
        public boolean isShowing() {
            androidx.appcompat.app.c cVar = this.a;
            if (cVar != null) {
                return cVar.isShowing();
            }
            return false;
        }

        @Override // androidx.appcompat.widget.x.g
        public void j(int i) {
            Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.x.g
        public void k(int i, int i2) {
            if (this.b == null) {
                return;
            }
            c.a aVar = new c.a(x.this.getPopupContext());
            CharSequence charSequence = this.c;
            if (charSequence != null) {
                aVar.setTitle(charSequence);
            }
            androidx.appcompat.app.c create = aVar.setSingleChoiceItems(this.b, x.this.getSelectedItemPosition(), this).create();
            this.a = create;
            ListView listView = create.getListView();
            listView.setTextDirection(i);
            listView.setTextAlignment(i2);
            this.a.show();
        }

        @Override // androidx.appcompat.widget.x.g
        public int l() {
            return 0;
        }

        @Override // androidx.appcompat.widget.x.g
        public void m(ListAdapter listAdapter) {
            this.b = listAdapter;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            x.this.setSelection(i);
            if (x.this.getOnItemClickListener() != null) {
                x.this.performItemClick(null, i, this.b.getItemId(i));
            }
            dismiss();
        }

        @Override // androidx.appcompat.widget.x.g
        public void setBackgroundDrawable(Drawable drawable) {
            Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
        }
    }

    /* loaded from: classes.dex */
    public static class d implements ListAdapter, SpinnerAdapter {
        public SpinnerAdapter a;
        public ListAdapter b;

        public d(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            Resources.Theme dropDownViewTheme;
            this.a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.b = (ListAdapter) spinnerAdapter;
            }
            if (theme != null && Build.VERSION.SDK_INT >= 23 && y.a(spinnerAdapter)) {
                ThemedSpinnerAdapter a = z.a(spinnerAdapter);
                dropDownViewTheme = a.getDropDownViewTheme();
                if (dropDownViewTheme != theme) {
                    a.setDropDownViewTheme(theme);
                }
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.b;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter != null && spinnerAdapter.hasStableIds()) {
                return true;
            }
            return false;
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            if (getCount() == 0) {
                return true;
            }
            return false;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.b;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    /* loaded from: classes.dex */
    public class e extends y1 implements g {
        public CharSequence K;
        public ListAdapter L;
        public final Rect M;
        public int N;

        /* loaded from: classes.dex */
        public class a implements AdapterView.OnItemClickListener {
            public final /* synthetic */ x a;

            public a(x xVar) {
                this.a = xVar;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                x.this.setSelection(i);
                if (x.this.getOnItemClickListener() != null) {
                    e eVar = e.this;
                    x.this.performItemClick(view, i, eVar.L.getItemId(i));
                }
                e.this.dismiss();
            }
        }

        /* loaded from: classes.dex */
        public class b implements ViewTreeObserver.OnGlobalLayoutListener {
            public b() {
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                e eVar = e.this;
                if (!eVar.N(x.this)) {
                    e.this.dismiss();
                    return;
                }
                e.this.L();
                e.super.show();
            }
        }

        /* loaded from: classes.dex */
        public class c implements PopupWindow.OnDismissListener {
            public final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener a;

            public c(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
                this.a = onGlobalLayoutListener;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                ViewTreeObserver viewTreeObserver = x.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this.a);
                }
            }
        }

        public e(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.M = new Rect();
            w(x.this);
            C(true);
            H(0);
            E(new a(x.this));
        }

        public void L() {
            int i;
            int M;
            Drawable f = f();
            if (f != null) {
                f.getPadding(x.this.h);
                if (y2.b(x.this)) {
                    i = x.this.h.right;
                } else {
                    i = -x.this.h.left;
                }
            } else {
                Rect rect = x.this.h;
                rect.right = 0;
                rect.left = 0;
                i = 0;
            }
            int paddingLeft = x.this.getPaddingLeft();
            int paddingRight = x.this.getPaddingRight();
            int width = x.this.getWidth();
            x xVar = x.this;
            int i2 = xVar.g;
            if (i2 == -2) {
                int a2 = xVar.a((SpinnerAdapter) this.L, f());
                int i3 = x.this.getContext().getResources().getDisplayMetrics().widthPixels;
                Rect rect2 = x.this.h;
                int i4 = (i3 - rect2.left) - rect2.right;
                if (a2 > i4) {
                    a2 = i4;
                }
                y(Math.max(a2, (width - paddingLeft) - paddingRight));
            } else if (i2 == -1) {
                y((width - paddingLeft) - paddingRight);
            } else {
                y(i2);
            }
            if (y2.b(x.this)) {
                M = i + (((width - paddingRight) - s()) - M());
            } else {
                M = i + paddingLeft + M();
            }
            c(M);
        }

        public int M() {
            return this.N;
        }

        public boolean N(View view) {
            if (v.d1.P(view) && view.getGlobalVisibleRect(this.M)) {
                return true;
            }
            return false;
        }

        @Override // androidx.appcompat.widget.x.g
        public CharSequence d() {
            return this.K;
        }

        @Override // androidx.appcompat.widget.x.g
        public void h(CharSequence charSequence) {
            this.K = charSequence;
        }

        @Override // androidx.appcompat.widget.x.g
        public void j(int i) {
            this.N = i;
        }

        @Override // androidx.appcompat.widget.x.g
        public void k(int i, int i2) {
            ViewTreeObserver viewTreeObserver;
            boolean isShowing = isShowing();
            L();
            B(2);
            super.show();
            ListView g = g();
            g.setChoiceMode(1);
            g.setTextDirection(i);
            g.setTextAlignment(i2);
            I(x.this.getSelectedItemPosition());
            if (!isShowing && (viewTreeObserver = x.this.getViewTreeObserver()) != null) {
                b bVar = new b();
                viewTreeObserver.addOnGlobalLayoutListener(bVar);
                D(new c(bVar));
            }
        }

        @Override // androidx.appcompat.widget.y1, androidx.appcompat.widget.x.g
        public void m(ListAdapter listAdapter) {
            super.m(listAdapter);
            this.L = listAdapter;
        }
    }

    /* loaded from: classes.dex */
    public static class f extends View.BaseSavedState {
        public static final Parcelable.Creator<f> CREATOR = new a();
        public boolean a;

        /* loaded from: classes.dex */
        public static class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public f createFromParcel(Parcel parcel) {
                return new f(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public f[] newArray(int i) {
                return new f[i];
            }
        }

        public f(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.a ? (byte) 1 : (byte) 0);
        }

        public f(Parcel parcel) {
            super(parcel);
            this.a = parcel.readByte() != 0;
        }
    }

    /* loaded from: classes.dex */
    public interface g {
        int a();

        void c(int i);

        CharSequence d();

        void dismiss();

        Drawable f();

        void h(CharSequence charSequence);

        void i(int i);

        boolean isShowing();

        void j(int i);

        void k(int i, int i2);

        int l();

        void m(ListAdapter listAdapter);

        void setBackgroundDrawable(Drawable drawable);
    }

    public x(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.spinnerStyle);
    }

    public int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i2 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        View view = null;
        int i3 = 0;
        for (int max2 = Math.max(0, max - (15 - (min - max))); max2 < min; max2++) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
            }
            view = spinnerAdapter.getView(max2, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i3 = Math.max(i3, view.getMeasuredWidth());
        }
        if (drawable != null) {
            drawable.getPadding(this.h);
            Rect rect = this.h;
            return i3 + rect.left + rect.right;
        }
        return i3;
    }

    public void b() {
        this.f.k(getTextDirection(), getTextAlignment());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            fVar.b();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        g gVar = this.f;
        if (gVar != null) {
            return gVar.a();
        }
        return super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        g gVar = this.f;
        if (gVar != null) {
            return gVar.l();
        }
        return super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        if (this.f != null) {
            return this.g;
        }
        return super.getDropDownWidth();
    }

    public final g getInternalPopup() {
        return this.f;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        g gVar = this.f;
        if (gVar != null) {
            return gVar.f();
        }
        return super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.b;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        g gVar = this.f;
        if (gVar != null) {
            return gVar.d();
        }
        return super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            return fVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            return fVar.d();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        g gVar = this.f;
        if (gVar != null && gVar.isShowing()) {
            this.f.dismiss();
        }
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f != null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i2)), getMeasuredHeight());
        }
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        f fVar = (f) parcelable;
        super.onRestoreInstanceState(fVar.getSuperState());
        if (fVar.a && (viewTreeObserver = getViewTreeObserver()) != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new b());
        }
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public Parcelable onSaveInstanceState() {
        boolean z;
        f fVar = new f(super.onSaveInstanceState());
        g gVar = this.f;
        if (gVar != null && gVar.isShowing()) {
            z = true;
        } else {
            z = false;
        }
        fVar.a = z;
        return fVar;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        t1 t1Var = this.c;
        if (t1Var != null && t1Var.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        g gVar = this.f;
        if (gVar != null) {
            if (!gVar.isShowing()) {
                b();
                return true;
            }
            return true;
        }
        return super.performClick();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            fVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            fVar.g(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i2) {
        g gVar = this.f;
        if (gVar != null) {
            gVar.j(i2);
            this.f.c(i2);
            return;
        }
        super.setDropDownHorizontalOffset(i2);
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i2) {
        g gVar = this.f;
        if (gVar != null) {
            gVar.i(i2);
        } else {
            super.setDropDownVerticalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i2) {
        if (this.f != null) {
            this.g = i2;
        } else {
            super.setDropDownWidth(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        g gVar = this.f;
        if (gVar != null) {
            gVar.setBackgroundDrawable(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i2) {
        setPopupBackgroundDrawable(b.b.d(getPopupContext(), i2));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        g gVar = this.f;
        if (gVar != null) {
            gVar.h(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            fVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            fVar.j(mode);
        }
    }

    public x(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, -1);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.e) {
            this.d = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.f != null) {
            Context context = this.b;
            if (context == null) {
                context = getContext();
            }
            this.f.m(new d(spinnerAdapter, context.getTheme()));
        }
    }

    public x(Context context, AttributeSet attributeSet, int i2, int i3) {
        this(context, attributeSet, i2, i3, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0057, code lost:
        if (r10 == null) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public x(android.content.Context r6, android.util.AttributeSet r7, int r8, int r9, android.content.res.Resources.Theme r10) {
        /*
            r5 = this;
            r5.<init>(r6, r7, r8)
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r5.h = r0
            int[] r0 = androidx.appcompat.R$styleable.H
            r1 = 0
            androidx.appcompat.widget.r2 r0 = androidx.appcompat.widget.r2.u(r6, r7, r0, r8, r1)
            androidx.appcompat.widget.f r2 = new androidx.appcompat.widget.f
            r2.<init>(r5)
            r5.a = r2
            if (r10 == 0) goto L22
            e.d r2 = new e.d
            r2.<init>(r6, r10)
            r5.b = r2
            goto L34
        L22:
            int r10 = androidx.appcompat.R$styleable.Spinner_popupTheme
            int r10 = r0.n(r10, r1)
            if (r10 == 0) goto L32
            e.d r2 = new e.d
            r2.<init>(r6, r10)
            r5.b = r2
            goto L34
        L32:
            r5.b = r6
        L34:
            r10 = -1
            r2 = 0
            if (r9 != r10) goto L5a
            int[] r10 = androidx.appcompat.widget.x.i     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L56
            android.content.res.TypedArray r10 = r6.obtainStyledAttributes(r7, r10, r8, r1)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L56
            boolean r3 = r10.hasValue(r1)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L57
            if (r3 == 0) goto L48
            int r9 = r10.getInt(r1, r1)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L57
        L48:
            r10.recycle()
            goto L5a
        L4c:
            r6 = move-exception
            r2 = r10
            goto L50
        L4f:
            r6 = move-exception
        L50:
            if (r2 == 0) goto L55
            r2.recycle()
        L55:
            throw r6
        L56:
            r10 = r2
        L57:
            if (r10 == 0) goto L5a
            goto L48
        L5a:
            r10 = 1
            if (r9 == 0) goto L97
            if (r9 == r10) goto L60
            goto La7
        L60:
            androidx.appcompat.widget.x$e r9 = new androidx.appcompat.widget.x$e
            android.content.Context r3 = r5.b
            r9.<init>(r3, r7, r8)
            android.content.Context r3 = r5.b
            int[] r4 = androidx.appcompat.R$styleable.H
            androidx.appcompat.widget.r2 r1 = androidx.appcompat.widget.r2.u(r3, r7, r4, r8, r1)
            int r3 = androidx.appcompat.R$styleable.Spinner_android_dropDownWidth
            r4 = -2
            int r3 = r1.m(r3, r4)
            r5.g = r3
            int r3 = androidx.appcompat.R$styleable.Spinner_android_popupBackground
            android.graphics.drawable.Drawable r3 = r1.g(r3)
            r9.setBackgroundDrawable(r3)
            int r3 = androidx.appcompat.R$styleable.Spinner_android_prompt
            java.lang.String r3 = r0.o(r3)
            r9.h(r3)
            r1.v()
            r5.f = r9
            androidx.appcompat.widget.x$a r1 = new androidx.appcompat.widget.x$a
            r1.<init>(r5, r9)
            r5.c = r1
            goto La7
        L97:
            androidx.appcompat.widget.x$c r9 = new androidx.appcompat.widget.x$c
            r9.<init>()
            r5.f = r9
            int r1 = androidx.appcompat.R$styleable.Spinner_android_prompt
            java.lang.String r1 = r0.o(r1)
            r9.h(r1)
        La7:
            int r9 = androidx.appcompat.R$styleable.Spinner_android_entries
            java.lang.CharSequence[] r9 = r0.q(r9)
            if (r9 == 0) goto Lbf
            android.widget.ArrayAdapter r1 = new android.widget.ArrayAdapter
            r3 = 17367048(0x1090008, float:2.5162948E-38)
            r1.<init>(r6, r3, r9)
            int r6 = androidx.appcompat.R$layout.support_simple_spinner_dropdown_item
            r1.setDropDownViewResource(r6)
            r5.setAdapter(r1)
        Lbf:
            r0.v()
            r5.e = r10
            android.widget.SpinnerAdapter r6 = r5.d
            if (r6 == 0) goto Lcd
            r5.setAdapter(r6)
            r5.d = r2
        Lcd:
            androidx.appcompat.widget.f r6 = r5.a
            r6.e(r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.x.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }
}
