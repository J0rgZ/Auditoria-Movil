package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.u1;
import androidx.core.widget.NestedScrollView;
import java.lang.ref.WeakReference;
import v.d1;
/* loaded from: classes.dex */
public class AlertController {
    public NestedScrollView A;
    public Drawable C;
    public ImageView D;
    public TextView E;
    public TextView F;
    public View G;
    public ListAdapter H;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public boolean P;
    public Handler R;
    public final Context a;
    public final j b;
    public final Window c;
    public final int d;
    public CharSequence e;
    public CharSequence f;
    public ListView g;
    public View h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public Button o;
    public CharSequence p;
    public Message q;
    public Drawable r;

    /* renamed from: s  reason: collision with root package name */
    public Button f2s;
    public CharSequence t;
    public Message u;
    public Drawable v;
    public Button w;
    public CharSequence x;
    public Message y;
    public Drawable z;
    public boolean n = false;
    public int B = 0;
    public int I = -1;
    public int Q = 0;
    public final View.OnClickListener S = new a();

    /* loaded from: classes.dex */
    public static class RecycleListView extends ListView {
        public final int a;
        public final int b;

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.F);
            this.b = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingBottomNoButtons, -1);
            this.a = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingTopNoTitle, -1);
        }

        public void a(boolean z, boolean z2) {
            int i;
            int i2;
            if (!z2 || !z) {
                int paddingLeft = getPaddingLeft();
                if (z) {
                    i = getPaddingTop();
                } else {
                    i = this.a;
                }
                int paddingRight = getPaddingRight();
                if (z2) {
                    i2 = getPaddingBottom();
                } else {
                    i2 = this.b;
                }
                setPadding(paddingLeft, i, paddingRight, i2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Message message;
            Message message2;
            Message message3;
            Message message4;
            AlertController alertController = AlertController.this;
            if (view == alertController.o && (message4 = alertController.q) != null) {
                message = Message.obtain(message4);
            } else if (view == alertController.f2s && (message3 = alertController.u) != null) {
                message = Message.obtain(message3);
            } else if (view == alertController.w && (message2 = alertController.y) != null) {
                message = Message.obtain(message2);
            } else {
                message = null;
            }
            if (message != null) {
                message.sendToTarget();
            }
            AlertController alertController2 = AlertController.this;
            alertController2.R.obtainMessage(1, alertController2.b).sendToTarget();
        }
    }

    /* loaded from: classes.dex */
    public class b implements NestedScrollView.b {
        public final /* synthetic */ View a;
        public final /* synthetic */ View b;

        public b(View view, View view2) {
            this.a = view;
            this.b = view2;
        }

        @Override // androidx.core.widget.NestedScrollView.b
        public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
            AlertController.g(nestedScrollView, this.a, this.b);
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public final /* synthetic */ View a;
        public final /* synthetic */ View b;

        public c(View view, View view2) {
            this.a = view;
            this.b = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertController.g(AlertController.this.A, this.a, this.b);
        }
    }

    /* loaded from: classes.dex */
    public class d implements AbsListView.OnScrollListener {
        public final /* synthetic */ View a;
        public final /* synthetic */ View b;

        public d(View view, View view2) {
            this.a = view;
            this.b = view2;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            AlertController.g(absListView, this.a, this.b);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }
    }

    /* loaded from: classes.dex */
    public class e implements Runnable {
        public final /* synthetic */ View a;
        public final /* synthetic */ View b;

        public e(View view, View view2) {
            this.a = view;
            this.b = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertController.g(AlertController.this.g, this.a, this.b);
        }
    }

    /* loaded from: classes.dex */
    public static class f {
        public int A;
        public int B;
        public int C;
        public int D;
        public boolean[] F;
        public boolean G;
        public boolean H;
        public DialogInterface.OnMultiChoiceClickListener J;
        public Cursor K;
        public String L;
        public String M;
        public boolean N;
        public AdapterView.OnItemSelectedListener O;
        public final Context a;
        public final LayoutInflater b;
        public Drawable d;
        public CharSequence f;
        public View g;
        public CharSequence h;
        public CharSequence i;
        public Drawable j;
        public DialogInterface.OnClickListener k;
        public CharSequence l;
        public Drawable m;
        public DialogInterface.OnClickListener n;
        public CharSequence o;
        public Drawable p;
        public DialogInterface.OnClickListener q;

        /* renamed from: s  reason: collision with root package name */
        public DialogInterface.OnCancelListener f3s;
        public DialogInterface.OnDismissListener t;
        public DialogInterface.OnKeyListener u;
        public CharSequence[] v;
        public ListAdapter w;
        public DialogInterface.OnClickListener x;
        public int y;
        public View z;
        public int c = 0;
        public int e = 0;
        public boolean E = false;
        public int I = -1;
        public boolean P = true;
        public boolean r = true;

        /* loaded from: classes.dex */
        public class a extends ArrayAdapter {
            public final /* synthetic */ RecycleListView a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Context context, int i, int i2, CharSequence[] charSequenceArr, RecycleListView recycleListView) {
                super(context, i, i2, charSequenceArr);
                this.a = recycleListView;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                boolean[] zArr = f.this.F;
                if (zArr != null && zArr[i]) {
                    this.a.setItemChecked(i, true);
                }
                return view2;
            }
        }

        /* loaded from: classes.dex */
        public class b extends CursorAdapter {
            public final int a;
            public final int b;
            public final /* synthetic */ RecycleListView c;
            public final /* synthetic */ AlertController d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(Context context, Cursor cursor, boolean z, RecycleListView recycleListView, AlertController alertController) {
                super(context, cursor, z);
                this.c = recycleListView;
                this.d = alertController;
                Cursor cursor2 = getCursor();
                this.a = cursor2.getColumnIndexOrThrow(f.this.L);
                this.b = cursor2.getColumnIndexOrThrow(f.this.M);
            }

            @Override // android.widget.CursorAdapter
            public void bindView(View view, Context context, Cursor cursor) {
                ((CheckedTextView) view.findViewById(16908308)).setText(cursor.getString(this.a));
                RecycleListView recycleListView = this.c;
                int position = cursor.getPosition();
                boolean z = true;
                if (cursor.getInt(this.b) != 1) {
                    z = false;
                }
                recycleListView.setItemChecked(position, z);
            }

            @Override // android.widget.CursorAdapter
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return f.this.b.inflate(this.d.M, viewGroup, false);
            }
        }

        /* loaded from: classes.dex */
        public class c implements AdapterView.OnItemClickListener {
            public final /* synthetic */ AlertController a;

            public c(AlertController alertController) {
                this.a = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                f.this.x.onClick(this.a.b, i);
                if (!f.this.H) {
                    this.a.b.dismiss();
                }
            }
        }

        /* loaded from: classes.dex */
        public class d implements AdapterView.OnItemClickListener {
            public final /* synthetic */ RecycleListView a;
            public final /* synthetic */ AlertController b;

            public d(RecycleListView recycleListView, AlertController alertController) {
                this.a = recycleListView;
                this.b = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                boolean[] zArr = f.this.F;
                if (zArr != null) {
                    zArr[i] = this.a.isItemChecked(i);
                }
                f.this.J.onClick(this.b.b, i, this.a.isItemChecked(i));
            }
        }

        public f(Context context) {
            this.a = context;
            this.b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public void a(AlertController alertController) {
            View view = this.g;
            if (view != null) {
                alertController.n(view);
            } else {
                CharSequence charSequence = this.f;
                if (charSequence != null) {
                    alertController.s(charSequence);
                }
                Drawable drawable = this.d;
                if (drawable != null) {
                    alertController.p(drawable);
                }
                int i = this.c;
                if (i != 0) {
                    alertController.o(i);
                }
                int i2 = this.e;
                if (i2 != 0) {
                    alertController.o(alertController.d(i2));
                }
            }
            CharSequence charSequence2 = this.h;
            if (charSequence2 != null) {
                alertController.q(charSequence2);
            }
            CharSequence charSequence3 = this.i;
            if (charSequence3 != null || this.j != null) {
                alertController.l(-1, charSequence3, this.k, null, this.j);
            }
            CharSequence charSequence4 = this.l;
            if (charSequence4 != null || this.m != null) {
                alertController.l(-2, charSequence4, this.n, null, this.m);
            }
            CharSequence charSequence5 = this.o;
            if (charSequence5 != null || this.p != null) {
                alertController.l(-3, charSequence5, this.q, null, this.p);
            }
            if (this.v != null || this.K != null || this.w != null) {
                b(alertController);
            }
            View view2 = this.z;
            if (view2 != null) {
                if (this.E) {
                    alertController.v(view2, this.A, this.B, this.C, this.D);
                    return;
                } else {
                    alertController.u(view2);
                    return;
                }
            }
            int i3 = this.y;
            if (i3 != 0) {
                alertController.t(i3);
            }
        }

        public final void b(AlertController alertController) {
            int i;
            ListAdapter listAdapter;
            RecycleListView recycleListView = (RecycleListView) this.b.inflate(alertController.L, (ViewGroup) null);
            if (this.G) {
                if (this.K == null) {
                    listAdapter = new a(this.a, alertController.M, 16908308, this.v, recycleListView);
                } else {
                    listAdapter = new b(this.a, this.K, false, recycleListView, alertController);
                }
            } else {
                if (this.H) {
                    i = alertController.N;
                } else {
                    i = alertController.O;
                }
                int i2 = i;
                if (this.K != null) {
                    listAdapter = new SimpleCursorAdapter(this.a, i2, this.K, new String[]{this.L}, new int[]{16908308});
                } else {
                    listAdapter = this.w;
                    if (listAdapter == null) {
                        listAdapter = new h(this.a, i2, 16908308, this.v);
                    }
                }
            }
            alertController.H = listAdapter;
            alertController.I = this.I;
            if (this.x != null) {
                recycleListView.setOnItemClickListener(new c(alertController));
            } else if (this.J != null) {
                recycleListView.setOnItemClickListener(new d(recycleListView, alertController));
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.O;
            if (onItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (this.H) {
                recycleListView.setChoiceMode(1);
            } else if (this.G) {
                recycleListView.setChoiceMode(2);
            }
            alertController.g = recycleListView;
        }
    }

    /* loaded from: classes.dex */
    public static final class g extends Handler {
        public WeakReference a;

        public g(DialogInterface dialogInterface) {
            this.a = new WeakReference(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != -3 && i != -2 && i != -1) {
                if (i == 1) {
                    ((DialogInterface) message.obj).dismiss();
                    return;
                }
                return;
            }
            ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.a.get(), message.what);
        }
    }

    /* loaded from: classes.dex */
    public static class h extends ArrayAdapter {
        public h(Context context, int i, int i2, CharSequence[] charSequenceArr) {
            super(context, i, i2, charSequenceArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, j jVar, Window window) {
        this.a = context;
        this.b = jVar;
        this.c = window;
        this.R = new g(jVar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, R$styleable.g, R$attr.alertDialogStyle, 0);
        this.J = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_android_layout, 0);
        this.K = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.L = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listLayout, 0);
        this.M = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.N = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.O = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listItemLayout, 0);
        this.P = obtainStyledAttributes.getBoolean(R$styleable.AlertDialog_showTitle, true);
        this.d = obtainStyledAttributes.getDimensionPixelSize(R$styleable.AlertDialog_buttonIconDimen, 0);
        obtainStyledAttributes.recycle();
        jVar.supportRequestWindowFeature(1);
    }

    public static boolean B(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            return true;
        }
        return false;
    }

    public static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public static void g(View view, View view2, View view3) {
        int i;
        int i2 = 0;
        if (view2 != null) {
            if (view.canScrollVertically(-1)) {
                i = 0;
            } else {
                i = 4;
            }
            view2.setVisibility(i);
        }
        if (view3 != null) {
            if (!view.canScrollVertically(1)) {
                i2 = 4;
            }
            view3.setVisibility(i2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void A() {
        boolean z;
        boolean z2;
        boolean z3;
        View findViewById;
        ListAdapter listAdapter;
        View findViewById2;
        View findViewById3;
        View findViewById4 = this.c.findViewById(R$id.parentPanel);
        int i = R$id.topPanel;
        View findViewById5 = findViewById4.findViewById(i);
        int i2 = R$id.contentPanel;
        View findViewById6 = findViewById4.findViewById(i2);
        int i3 = R$id.buttonPanel;
        View findViewById7 = findViewById4.findViewById(i3);
        ViewGroup viewGroup = (ViewGroup) findViewById4.findViewById(R$id.customPanel);
        y(viewGroup);
        View findViewById8 = viewGroup.findViewById(i);
        View findViewById9 = viewGroup.findViewById(i2);
        View findViewById10 = viewGroup.findViewById(i3);
        ViewGroup j = j(findViewById8, findViewById5);
        ViewGroup j2 = j(findViewById9, findViewById6);
        ViewGroup j3 = j(findViewById10, findViewById7);
        x(j2);
        w(j3);
        z(j);
        int i4 = 0;
        if (viewGroup.getVisibility() != 8) {
            z = true;
        } else {
            z = false;
        }
        if (j != null && j.getVisibility() != 8) {
            z2 = 1;
        } else {
            z2 = 0;
        }
        if (j3 != null && j3.getVisibility() != 8) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z3 && j2 != null && (findViewById3 = j2.findViewById(R$id.textSpacerNoButtons)) != null) {
            findViewById3.setVisibility(0);
        }
        if (z2) {
            NestedScrollView nestedScrollView = this.A;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            if (this.f == null && this.g == null) {
                findViewById2 = null;
            } else {
                findViewById2 = j.findViewById(R$id.titleDividerNoCustom);
            }
            if (findViewById2 != null) {
                findViewById2.setVisibility(0);
            }
        } else if (j2 != null && (findViewById = j2.findViewById(R$id.textSpacerNoTitle)) != null) {
            findViewById.setVisibility(0);
        }
        ListView listView = this.g;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).a(z2, z3);
        }
        if (!z) {
            View view = this.g;
            if (view == null) {
                view = this.A;
            }
            if (view != null) {
                if (z3) {
                    i4 = 2;
                }
                r(j2, view, z2 | i4, 3);
            }
        }
        ListView listView2 = this.g;
        if (listView2 != null && (listAdapter = this.H) != null) {
            listView2.setAdapter(listAdapter);
            int i5 = this.I;
            if (i5 > -1) {
                listView2.setItemChecked(i5, true);
                listView2.setSelection(i5);
            }
        }
    }

    public final void b(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    public Button c(int i) {
        if (i != -3) {
            if (i != -2) {
                if (i != -1) {
                    return null;
                }
                return this.o;
            }
            return this.f2s;
        }
        return this.w;
    }

    public int d(int i) {
        TypedValue typedValue = new TypedValue();
        this.a.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView e() {
        return this.g;
    }

    public void f() {
        this.b.setContentView(k());
        A();
    }

    public boolean h(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        if (nestedScrollView != null && nestedScrollView.executeKeyEvent(keyEvent)) {
            return true;
        }
        return false;
    }

    public boolean i(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        if (nestedScrollView != null && nestedScrollView.executeKeyEvent(keyEvent)) {
            return true;
        }
        return false;
    }

    public final ViewGroup j(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    public final int k() {
        int i = this.K;
        if (i == 0) {
            return this.J;
        }
        if (this.Q == 1) {
            return i;
        }
        return this.J;
    }

    public void l(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.R.obtainMessage(i, onClickListener);
        }
        if (i != -3) {
            if (i != -2) {
                if (i == -1) {
                    this.p = charSequence;
                    this.q = message;
                    this.r = drawable;
                    return;
                }
                throw new IllegalArgumentException("Button does not exist");
            }
            this.t = charSequence;
            this.u = message;
            this.v = drawable;
            return;
        }
        this.x = charSequence;
        this.y = message;
        this.z = drawable;
    }

    public void m(int i) {
        this.Q = i;
    }

    public void n(View view) {
        this.G = view;
    }

    public void o(int i) {
        this.C = null;
        this.B = i;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (i != 0) {
                imageView.setVisibility(0);
                this.D.setImageResource(this.B);
                return;
            }
            imageView.setVisibility(8);
        }
    }

    public void p(Drawable drawable) {
        this.C = drawable;
        this.B = 0;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (drawable != null) {
                imageView.setVisibility(0);
                this.D.setImageDrawable(drawable);
                return;
            }
            imageView.setVisibility(8);
        }
    }

    public void q(CharSequence charSequence) {
        this.f = charSequence;
        TextView textView = this.F;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public final void r(ViewGroup viewGroup, View view, int i, int i2) {
        View findViewById = this.c.findViewById(R$id.scrollIndicatorUp);
        View findViewById2 = this.c.findViewById(R$id.scrollIndicatorDown);
        if (Build.VERSION.SDK_INT >= 23) {
            d1.I0(view, i, i2);
            if (findViewById != null) {
                viewGroup.removeView(findViewById);
            }
            if (findViewById2 != null) {
                viewGroup.removeView(findViewById2);
                return;
            }
            return;
        }
        if (findViewById != null && (i & 1) == 0) {
            viewGroup.removeView(findViewById);
            findViewById = null;
        }
        if (findViewById2 != null && (i & 2) == 0) {
            viewGroup.removeView(findViewById2);
            findViewById2 = null;
        }
        if (findViewById != null || findViewById2 != null) {
            if (this.f != null) {
                this.A.setOnScrollChangeListener(new b(findViewById, findViewById2));
                this.A.post(new c(findViewById, findViewById2));
                return;
            }
            ListView listView = this.g;
            if (listView != null) {
                listView.setOnScrollListener(new d(findViewById, findViewById2));
                this.g.post(new e(findViewById, findViewById2));
                return;
            }
            if (findViewById != null) {
                viewGroup.removeView(findViewById);
            }
            if (findViewById2 != null) {
                viewGroup.removeView(findViewById2);
            }
        }
    }

    public void s(CharSequence charSequence) {
        this.e = charSequence;
        TextView textView = this.E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void t(int i) {
        this.h = null;
        this.i = i;
        this.n = false;
    }

    public void u(View view) {
        this.h = view;
        this.i = 0;
        this.n = false;
    }

    public void v(View view, int i, int i2, int i3, int i4) {
        this.h = view;
        this.i = 0;
        this.n = true;
        this.j = i;
        this.k = i2;
        this.l = i3;
        this.m = i4;
    }

    public final void w(ViewGroup viewGroup) {
        boolean z;
        Button button = (Button) viewGroup.findViewById(16908313);
        this.o = button;
        button.setOnClickListener(this.S);
        boolean z2 = true;
        if (TextUtils.isEmpty(this.p) && this.r == null) {
            this.o.setVisibility(8);
            z = false;
        } else {
            this.o.setText(this.p);
            Drawable drawable = this.r;
            if (drawable != null) {
                int i = this.d;
                drawable.setBounds(0, 0, i, i);
                this.o.setCompoundDrawables(this.r, null, null, null);
            }
            this.o.setVisibility(0);
            z = true;
        }
        Button button2 = (Button) viewGroup.findViewById(16908314);
        this.f2s = button2;
        button2.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.t) && this.v == null) {
            this.f2s.setVisibility(8);
        } else {
            this.f2s.setText(this.t);
            Drawable drawable2 = this.v;
            if (drawable2 != null) {
                int i2 = this.d;
                drawable2.setBounds(0, 0, i2, i2);
                this.f2s.setCompoundDrawables(this.v, null, null, null);
            }
            this.f2s.setVisibility(0);
            z |= true;
        }
        Button button3 = (Button) viewGroup.findViewById(16908315);
        this.w = button3;
        button3.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.x) && this.z == null) {
            this.w.setVisibility(8);
        } else {
            this.w.setText(this.x);
            Drawable drawable3 = this.r;
            if (drawable3 != null) {
                int i3 = this.d;
                drawable3.setBounds(0, 0, i3, i3);
                this.o.setCompoundDrawables(this.r, null, null, null);
            }
            this.w.setVisibility(0);
            z |= true;
        }
        if (B(this.a)) {
            if (z) {
                b(this.o);
            } else if (z) {
                b(this.f2s);
            } else if (z) {
                b(this.w);
            }
        }
        if (!z) {
            z2 = false;
        }
        if (!z2) {
            viewGroup.setVisibility(8);
        }
    }

    public final void x(ViewGroup viewGroup) {
        NestedScrollView nestedScrollView = (NestedScrollView) this.c.findViewById(R$id.scrollView);
        this.A = nestedScrollView;
        nestedScrollView.setFocusable(false);
        this.A.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroup.findViewById(16908299);
        this.F = textView;
        if (textView == null) {
            return;
        }
        CharSequence charSequence = this.f;
        if (charSequence != null) {
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
        this.A.removeView(this.F);
        if (this.g != null) {
            ViewGroup viewGroup2 = (ViewGroup) this.A.getParent();
            int indexOfChild = viewGroup2.indexOfChild(this.A);
            viewGroup2.removeViewAt(indexOfChild);
            viewGroup2.addView(this.g, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
            return;
        }
        viewGroup.setVisibility(8);
    }

    public final void y(ViewGroup viewGroup) {
        View view = this.h;
        boolean z = false;
        if (view == null) {
            if (this.i != 0) {
                view = LayoutInflater.from(this.a).inflate(this.i, viewGroup, false);
            } else {
                view = null;
            }
        }
        if (view != null) {
            z = true;
        }
        if (!z || !a(view)) {
            this.c.setFlags(131072, 131072);
        }
        if (z) {
            FrameLayout frameLayout = (FrameLayout) this.c.findViewById(R$id.custom);
            frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -1));
            if (this.n) {
                frameLayout.setPadding(this.j, this.k, this.l, this.m);
            }
            if (this.g != null) {
                ((u1.a) viewGroup.getLayoutParams()).a = 0.0f;
                return;
            }
            return;
        }
        viewGroup.setVisibility(8);
    }

    public final void z(ViewGroup viewGroup) {
        if (this.G != null) {
            viewGroup.addView(this.G, 0, new ViewGroup.LayoutParams(-1, -2));
            this.c.findViewById(R$id.title_template).setVisibility(8);
            return;
        }
        this.D = (ImageView) this.c.findViewById(16908294);
        if ((!TextUtils.isEmpty(this.e)) && this.P) {
            TextView textView = (TextView) this.c.findViewById(R$id.alertTitle);
            this.E = textView;
            textView.setText(this.e);
            int i = this.B;
            if (i != 0) {
                this.D.setImageResource(i);
                return;
            }
            Drawable drawable = this.C;
            if (drawable != null) {
                this.D.setImageDrawable(drawable);
                return;
            }
            this.E.setPadding(this.D.getPaddingLeft(), this.D.getPaddingTop(), this.D.getPaddingRight(), this.D.getPaddingBottom());
            this.D.setVisibility(8);
            return;
        }
        this.c.findViewById(R$id.title_template).setVisibility(8);
        this.D.setVisibility(8);
        viewGroup.setVisibility(8);
    }
}
