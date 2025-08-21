package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.ActionMenuView;
import java.util.ArrayList;
import v.b;
/* loaded from: classes.dex */
public class d extends androidx.appcompat.view.menu.b implements b.a {
    public c A;
    public b B;
    public final f C;
    public int D;
    public C0009d k;
    public Drawable l;
    public boolean m;
    public boolean n;
    public boolean o;
    public int p;
    public int q;
    public int r;

    /* renamed from: s  reason: collision with root package name */
    public boolean f14s;
    public boolean t;
    public boolean u;
    public boolean v;
    public int w;
    public final SparseBooleanArray x;
    public e y;
    public a z;

    /* loaded from: classes.dex */
    public class a extends androidx.appcompat.view.menu.l {
        public a(Context context, androidx.appcompat.view.menu.r rVar, View view) {
            super(context, rVar, view, false, R$attr.actionOverflowMenuStyle);
            if (!((androidx.appcompat.view.menu.i) rVar.getItem()).l()) {
                View view2 = d.this.k;
                f(view2 == null ? (View) d.this.i : view2);
            }
            j(d.this.C);
        }

        @Override // androidx.appcompat.view.menu.l
        public void e() {
            d dVar = d.this;
            dVar.z = null;
            dVar.D = 0;
            super.e();
        }
    }

    /* loaded from: classes.dex */
    public class b extends ActionMenuItemView.b {
        public b() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.b
        public androidx.appcompat.view.menu.p a() {
            a aVar = d.this.z;
            if (aVar != null) {
                return aVar.c();
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public e a;

        public c(e eVar) {
            this.a = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (d.this.c != null) {
                d.this.c.changeMenuMode();
            }
            View view = (View) d.this.i;
            if (view != null && view.getWindowToken() != null && this.a.m()) {
                d.this.y = this.a;
            }
            d.this.A = null;
        }
    }

    /* renamed from: androidx.appcompat.widget.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0009d extends q implements ActionMenuView.a {
        public final float[] a;

        /* renamed from: androidx.appcompat.widget.d$d$a */
        /* loaded from: classes.dex */
        public class a extends t1 {
            public final /* synthetic */ d j;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(View view, d dVar) {
                super(view);
                this.j = dVar;
            }

            @Override // androidx.appcompat.widget.t1
            public androidx.appcompat.view.menu.p b() {
                e eVar = d.this.y;
                if (eVar == null) {
                    return null;
                }
                return eVar.c();
            }

            @Override // androidx.appcompat.widget.t1
            public boolean c() {
                d.this.B();
                return true;
            }

            @Override // androidx.appcompat.widget.t1
            public boolean d() {
                d dVar = d.this;
                if (dVar.A != null) {
                    return false;
                }
                dVar.s();
                return true;
            }
        }

        public C0009d(Context context) {
            super(context, null, R$attr.actionOverflowButtonStyle);
            this.a = new float[2];
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            u2.a(this, getContentDescription());
            setOnTouchListener(new a(this, d.this));
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean a() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean b() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            d.this.B();
            return true;
        }

        @Override // android.widget.ImageView
        public boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                m.h.l(background, paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
            }
            return frame;
        }
    }

    /* loaded from: classes.dex */
    public class e extends androidx.appcompat.view.menu.l {
        public e(Context context, androidx.appcompat.view.menu.g gVar, View view, boolean z) {
            super(context, gVar, view, z, R$attr.actionOverflowMenuStyle);
            h(8388613);
            j(d.this.C);
        }

        @Override // androidx.appcompat.view.menu.l
        public void e() {
            if (d.this.c != null) {
                d.this.c.close();
            }
            d.this.y = null;
            super.e();
        }
    }

    /* loaded from: classes.dex */
    public class f implements m.a {
        public f() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            if (gVar == null) {
                return false;
            }
            d.this.D = ((androidx.appcompat.view.menu.r) gVar).getItem().getItemId();
            m.a e = d.this.e();
            if (e == null) {
                return false;
            }
            return e.a(gVar);
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
            if (gVar instanceof androidx.appcompat.view.menu.r) {
                gVar.getRootMenu().close(false);
            }
            m.a e = d.this.e();
            if (e != null) {
                e.onCloseMenu(gVar, z);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class g implements Parcelable {
        public static final Parcelable.Creator<g> CREATOR = new a();
        public int a;

        /* loaded from: classes.dex */
        public static class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public g createFromParcel(Parcel parcel) {
                return new g(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public g[] newArray(int i) {
                return new g[i];
            }
        }

        public g() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
        }

        public g(Parcel parcel) {
            this.a = parcel.readInt();
        }
    }

    public d(Context context) {
        super(context, R$layout.abc_action_menu_layout, R$layout.abc_action_menu_item_layout);
        this.x = new SparseBooleanArray();
        this.C = new f();
    }

    public void A(boolean z) {
        this.n = z;
        this.o = true;
    }

    public boolean B() {
        androidx.appcompat.view.menu.g gVar;
        if (this.n && !v() && (gVar = this.c) != null && this.i != null && this.A == null && !gVar.getNonActionItems().isEmpty()) {
            c cVar = new c(new e(this.b, this.c, this.k, true));
            this.A = cVar;
            ((View) this.i).post(cVar);
            super.onSubMenuSelected(null);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.b
    public void b(androidx.appcompat.view.menu.i iVar, n.a aVar) {
        aVar.initialize(iVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.i);
        if (this.B == null) {
            this.B = new b();
        }
        actionMenuItemView.setPopupCallback(this.B);
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean d(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.k) {
            return false;
        }
        return super.d(viewGroup, i);
    }

    @Override // androidx.appcompat.view.menu.b
    public View f(androidx.appcompat.view.menu.i iVar, View view, ViewGroup viewGroup) {
        int i;
        View actionView = iVar.getActionView();
        if (actionView == null || iVar.j()) {
            actionView = super.f(iVar, view, viewGroup);
        }
        if (iVar.isActionViewExpanded()) {
            i = 8;
        } else {
            i = 0;
        }
        actionView.setVisibility(i);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean flagActionItems() {
        ArrayList<androidx.appcompat.view.menu.i> arrayList;
        int i;
        int i2;
        int i3;
        int i4;
        boolean z;
        boolean z2;
        d dVar = this;
        androidx.appcompat.view.menu.g gVar = dVar.c;
        View view = null;
        int i5 = 0;
        if (gVar != null) {
            arrayList = gVar.getVisibleItems();
            i = arrayList.size();
        } else {
            arrayList = null;
            i = 0;
        }
        int i6 = dVar.r;
        int i7 = dVar.q;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) dVar.i;
        boolean z3 = false;
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < i; i10++) {
            androidx.appcompat.view.menu.i iVar = arrayList.get(i10);
            if (iVar.o()) {
                i8++;
            } else if (iVar.n()) {
                i9++;
            } else {
                z3 = true;
            }
            if (dVar.v && iVar.isActionViewExpanded()) {
                i6 = 0;
            }
        }
        if (dVar.n && (z3 || i9 + i8 > i6)) {
            i6--;
        }
        int i11 = i6 - i8;
        SparseBooleanArray sparseBooleanArray = dVar.x;
        sparseBooleanArray.clear();
        if (dVar.t) {
            int i12 = dVar.w;
            i3 = i7 / i12;
            i2 = i12 + ((i7 % i12) / i3);
        } else {
            i2 = 0;
            i3 = 0;
        }
        int i13 = 0;
        int i14 = 0;
        while (i13 < i) {
            androidx.appcompat.view.menu.i iVar2 = arrayList.get(i13);
            if (iVar2.o()) {
                View f2 = dVar.f(iVar2, view, viewGroup);
                if (dVar.t) {
                    i3 -= ActionMenuView.q(f2, i2, i3, makeMeasureSpec, i5);
                } else {
                    f2.measure(makeMeasureSpec, makeMeasureSpec);
                }
                int measuredWidth = f2.getMeasuredWidth();
                i7 -= measuredWidth;
                if (i14 == 0) {
                    i14 = measuredWidth;
                }
                int groupId = iVar2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                iVar2.u(true);
                i4 = i;
            } else if (iVar2.n()) {
                int groupId2 = iVar2.getGroupId();
                boolean z4 = sparseBooleanArray.get(groupId2);
                if ((i11 > 0 || z4) && i7 > 0 && (!dVar.t || i3 > 0)) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z5 = z;
                i4 = i;
                if (z) {
                    View f3 = dVar.f(iVar2, null, viewGroup);
                    if (dVar.t) {
                        int q = ActionMenuView.q(f3, i2, i3, makeMeasureSpec, 0);
                        i3 -= q;
                        if (q == 0) {
                            z5 = false;
                        }
                    } else {
                        f3.measure(makeMeasureSpec, makeMeasureSpec);
                    }
                    boolean z6 = z5;
                    int measuredWidth2 = f3.getMeasuredWidth();
                    i7 -= measuredWidth2;
                    if (i14 == 0) {
                        i14 = measuredWidth2;
                    }
                    if (!dVar.t ? i7 + i14 > 0 : i7 >= 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z = z6 & z2;
                }
                if (z && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z4) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i15 = 0; i15 < i13; i15++) {
                        androidx.appcompat.view.menu.i iVar3 = arrayList.get(i15);
                        if (iVar3.getGroupId() == groupId2) {
                            if (iVar3.l()) {
                                i11++;
                            }
                            iVar3.u(false);
                        }
                    }
                }
                if (z) {
                    i11--;
                }
                iVar2.u(z);
            } else {
                i4 = i;
                iVar2.u(false);
                i13++;
                view = null;
                dVar = this;
                i = i4;
                i5 = 0;
            }
            i13++;
            view = null;
            dVar = this;
            i = i4;
            i5 = 0;
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.b
    public androidx.appcompat.view.menu.n g(ViewGroup viewGroup) {
        androidx.appcompat.view.menu.n nVar = this.i;
        androidx.appcompat.view.menu.n g2 = super.g(viewGroup);
        if (nVar != g2) {
            ((ActionMenuView) g2).setPresenter(this);
        }
        return g2;
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean i(int i, androidx.appcompat.view.menu.i iVar) {
        return iVar.l();
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void initForMenu(Context context, androidx.appcompat.view.menu.g gVar) {
        super.initForMenu(context, gVar);
        Resources resources = context.getResources();
        e.a b2 = e.a.b(context);
        if (!this.o) {
            this.n = b2.f();
        }
        if (!this.u) {
            this.p = b2.c();
        }
        if (!this.f14s) {
            this.r = b2.d();
        }
        int i = this.p;
        if (this.n) {
            if (this.k == null) {
                C0009d c0009d = new C0009d(this.a);
                this.k = c0009d;
                if (this.m) {
                    c0009d.setImageDrawable(this.l);
                    this.l = null;
                    this.m = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.k.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i -= this.k.getMeasuredWidth();
        } else {
            this.k = null;
        }
        this.q = i;
        this.w = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
        p();
        super.onCloseMenu(gVar, z);
    }

    @Override // androidx.appcompat.view.menu.m
    public void onRestoreInstanceState(Parcelable parcelable) {
        int i;
        MenuItem findItem;
        if ((parcelable instanceof g) && (i = ((g) parcelable).a) > 0 && (findItem = this.c.findItem(i)) != null) {
            onSubMenuSelected((androidx.appcompat.view.menu.r) findItem.getSubMenu());
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable onSaveInstanceState() {
        g gVar = new g();
        gVar.a = this.D;
        return gVar;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public boolean onSubMenuSelected(androidx.appcompat.view.menu.r rVar) {
        boolean z = false;
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        androidx.appcompat.view.menu.r rVar2 = rVar;
        while (rVar2.getParentMenu() != this.c) {
            rVar2 = (androidx.appcompat.view.menu.r) rVar2.getParentMenu();
        }
        View q = q(rVar2.getItem());
        if (q == null) {
            return false;
        }
        this.D = rVar.getItem().getItemId();
        int size = rVar.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            MenuItem item = rVar.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i++;
        }
        a aVar = new a(this.b, rVar, q);
        this.z = aVar;
        aVar.g(z);
        this.z.k();
        super.onSubMenuSelected(rVar);
        return true;
    }

    public boolean p() {
        return s() | t();
    }

    public final View q(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.i;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof n.a) && ((n.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public Drawable r() {
        C0009d c0009d = this.k;
        if (c0009d != null) {
            return c0009d.getDrawable();
        }
        if (this.m) {
            return this.l;
        }
        return null;
    }

    public boolean s() {
        androidx.appcompat.view.menu.n nVar;
        c cVar = this.A;
        if (cVar != null && (nVar = this.i) != null) {
            ((View) nVar).removeCallbacks(cVar);
            this.A = null;
            return true;
        }
        e eVar = this.y;
        if (eVar != null) {
            eVar.b();
            return true;
        }
        return false;
    }

    public boolean t() {
        a aVar = this.z;
        if (aVar != null) {
            aVar.b();
            return true;
        }
        return false;
    }

    public boolean u() {
        if (this.A == null && !v()) {
            return false;
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void updateMenuView(boolean z) {
        ArrayList<androidx.appcompat.view.menu.i> arrayList;
        super.updateMenuView(z);
        ((View) this.i).requestLayout();
        androidx.appcompat.view.menu.g gVar = this.c;
        boolean z2 = false;
        if (gVar != null) {
            ArrayList<androidx.appcompat.view.menu.i> actionItems = gVar.getActionItems();
            int size = actionItems.size();
            for (int i = 0; i < size; i++) {
                v.b b2 = actionItems.get(i).b();
                if (b2 != null) {
                    b2.k(this);
                }
            }
        }
        androidx.appcompat.view.menu.g gVar2 = this.c;
        if (gVar2 != null) {
            arrayList = gVar2.getNonActionItems();
        } else {
            arrayList = null;
        }
        if (this.n && arrayList != null) {
            int size2 = arrayList.size();
            if (size2 == 1) {
                z2 = !arrayList.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.k == null) {
                this.k = new C0009d(this.a);
            }
            ViewGroup viewGroup = (ViewGroup) this.k.getParent();
            if (viewGroup != this.i) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.k);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.i;
                actionMenuView.addView(this.k, actionMenuView.k());
            }
        } else {
            C0009d c0009d = this.k;
            if (c0009d != null) {
                ViewParent parent = c0009d.getParent();
                androidx.appcompat.view.menu.n nVar = this.i;
                if (parent == nVar) {
                    ((ViewGroup) nVar).removeView(this.k);
                }
            }
        }
        ((ActionMenuView) this.i).setOverflowReserved(this.n);
    }

    public boolean v() {
        e eVar = this.y;
        if (eVar != null && eVar.d()) {
            return true;
        }
        return false;
    }

    public void w(Configuration configuration) {
        if (!this.f14s) {
            this.r = e.a.b(this.b).d();
        }
        androidx.appcompat.view.menu.g gVar = this.c;
        if (gVar != null) {
            gVar.onItemsChanged(true);
        }
    }

    public void x(boolean z) {
        this.v = z;
    }

    public void y(ActionMenuView actionMenuView) {
        this.i = actionMenuView;
        actionMenuView.initialize(this.c);
    }

    public void z(Drawable drawable) {
        C0009d c0009d = this.k;
        if (c0009d != null) {
            c0009d.setImageDrawable(drawable);
            return;
        }
        this.m = true;
        this.l = drawable;
    }
}
