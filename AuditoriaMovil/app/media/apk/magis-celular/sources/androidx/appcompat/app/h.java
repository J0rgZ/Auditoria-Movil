package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$style;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.h1;
import androidx.appcompat.widget.r2;
import androidx.appcompat.widget.s1;
import androidx.appcompat.widget.x2;
import androidx.appcompat.widget.y2;
import androidx.lifecycle.c;
import e.b;
import e.f;
import java.lang.Thread;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import v.b0;
import v.b2;
import v.c2;
import v.d1;
import v.d2;
import v.g2;
import v.k;
/* loaded from: classes.dex */
public class h extends androidx.appcompat.app.f implements g.a, LayoutInflater.Factory2 {
    public static final Map f0 = new androidx.collection.a();
    public static final boolean g0;
    public static final int[] h0;
    public static boolean i0;
    public static final boolean j0;
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public p[] G;
    public p H;
    public boolean I;
    public boolean J;
    public boolean K;
    public boolean L;
    public boolean M;
    public int N;
    public int O;
    public boolean Q;
    public boolean S;
    public m V;
    public m W;
    public boolean X;
    public int Y;
    public final Runnable Z;
    public boolean b0;
    public Rect c0;
    public final Object d;
    public Rect d0;
    public final Context e;
    public AppCompatViewInflater e0;
    public Window f;
    public k g;
    public final androidx.appcompat.app.e h;
    public androidx.appcompat.app.a i;
    public MenuInflater j;
    public CharSequence k;
    public h1 l;
    public i m;
    public q n;
    public e.b o;
    public ActionBarContextView p;
    public PopupWindow q;
    public Runnable r;

    /* renamed from: s  reason: collision with root package name */
    public b2 f4s;
    public boolean t;
    public boolean u;
    public ViewGroup v;
    public TextView w;
    public View x;
    public boolean y;
    public boolean z;

    /* loaded from: classes.dex */
    public static class a implements Thread.UncaughtExceptionHandler {
        public final /* synthetic */ Thread.UncaughtExceptionHandler a;

        public a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.a = uncaughtExceptionHandler;
        }

        public final boolean a(Throwable th) {
            String message;
            if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                return false;
            }
            if (!message.contains("drawable") && !message.contains("Drawable")) {
                return false;
            }
            return true;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            if (a(th)) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                notFoundException.initCause(th.getCause());
                notFoundException.setStackTrace(th.getStackTrace());
                this.a.uncaughtException(thread, notFoundException);
                return;
            }
            this.a.uncaughtException(thread, th);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            if ((hVar.Y & 1) != 0) {
                hVar.T(0);
            }
            h hVar2 = h.this;
            if ((hVar2.Y & 4096) != 0) {
                hVar2.T(108);
            }
            h hVar3 = h.this;
            hVar3.X = false;
            hVar3.Y = 0;
        }
    }

    /* loaded from: classes.dex */
    public class c implements b0 {
        public c() {
        }

        public g2 onApplyWindowInsets(View view, g2 g2Var) {
            int k = g2Var.k();
            int I0 = h.this.I0(k);
            if (k != I0) {
                g2Var = g2Var.n(g2Var.i(), I0, g2Var.j(), g2Var.h());
            }
            return d1.X(view, g2Var);
        }
    }

    /* loaded from: classes.dex */
    public class d implements s1.a {
        public d() {
        }

        @Override // androidx.appcompat.widget.s1.a
        public void a(Rect rect) {
            rect.top = h.this.I0(rect.top);
        }
    }

    /* loaded from: classes.dex */
    public class e implements ContentFrameLayout.a {
        public e() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void a() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void onDetachedFromWindow() {
            h.this.R();
        }
    }

    /* loaded from: classes.dex */
    public class f implements Runnable {

        /* loaded from: classes.dex */
        public class a extends d2 {
            public a() {
            }

            public void b(View view) {
                h.this.p.setAlpha(1.0f);
                h.this.f4s.f((c2) null);
                h.this.f4s = null;
            }

            public void c(View view) {
                h.this.p.setVisibility(0);
            }
        }

        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            hVar.q.showAtLocation(hVar.p, 55, 0, 0);
            h.this.U();
            if (h.this.B0()) {
                h.this.p.setAlpha(0.0f);
                h hVar2 = h.this;
                hVar2.f4s = d1.c(hVar2.p).a(1.0f);
                h.this.f4s.f(new a());
                return;
            }
            h.this.p.setAlpha(1.0f);
            h.this.p.setVisibility(0);
        }
    }

    /* loaded from: classes.dex */
    public class g extends d2 {
        public g() {
        }

        public void b(View view) {
            h.this.p.setAlpha(1.0f);
            h.this.f4s.f((c2) null);
            h.this.f4s = null;
        }

        public void c(View view) {
            h.this.p.setVisibility(0);
            h.this.p.sendAccessibilityEvent(32);
            if (h.this.p.getParent() instanceof View) {
                d1.h0((View) h.this.p.getParent());
            }
        }
    }

    /* renamed from: androidx.appcompat.app.h$h  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0006h implements androidx.appcompat.app.b {
        public C0006h() {
        }
    }

    /* loaded from: classes.dex */
    public final class i implements m.a {
        public i() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            Window.Callback d0 = h.this.d0();
            if (d0 != null) {
                d0.onMenuOpened(108, gVar);
                return true;
            }
            return true;
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
            h.this.L(gVar);
        }
    }

    /* loaded from: classes.dex */
    public class j implements b.a {
        public b.a a;

        /* loaded from: classes.dex */
        public class a extends d2 {
            public a() {
            }

            public void b(View view) {
                h.this.p.setVisibility(8);
                h hVar = h.this;
                PopupWindow popupWindow = hVar.q;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (hVar.p.getParent() instanceof View) {
                    d1.h0((View) h.this.p.getParent());
                }
                h.this.p.removeAllViews();
                h.this.f4s.f((c2) null);
                h.this.f4s = null;
            }
        }

        public j(b.a aVar) {
            this.a = aVar;
        }

        public void a(e.b bVar) {
            this.a.a(bVar);
            h hVar = h.this;
            if (hVar.q != null) {
                hVar.f.getDecorView().removeCallbacks(h.this.r);
            }
            h hVar2 = h.this;
            if (hVar2.p != null) {
                hVar2.U();
                h hVar3 = h.this;
                hVar3.f4s = d1.c(hVar3.p).a(0.0f);
                h.this.f4s.f(new a());
            }
            h hVar4 = h.this;
            androidx.appcompat.app.e eVar = hVar4.h;
            if (eVar != null) {
                eVar.onSupportActionModeFinished(hVar4.o);
            }
            h.this.o = null;
        }

        public boolean b(e.b bVar, Menu menu) {
            return this.a.b(bVar, menu);
        }

        public boolean c(e.b bVar, Menu menu) {
            return this.a.c(bVar, menu);
        }

        public boolean d(e.b bVar, MenuItem menuItem) {
            return this.a.d(bVar, menuItem);
        }
    }

    /* loaded from: classes.dex */
    public class l extends m {
        public final PowerManager c;

        public l(Context context) {
            super();
            this.c = (PowerManager) context.getSystemService("power");
        }

        @Override // androidx.appcompat.app.h.m
        public IntentFilter b() {
            if (Build.VERSION.SDK_INT >= 21) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
                return intentFilter;
            }
            return null;
        }

        @Override // androidx.appcompat.app.h.m
        public int c() {
            boolean isPowerSaveMode;
            if (Build.VERSION.SDK_INT >= 21) {
                isPowerSaveMode = this.c.isPowerSaveMode();
                if (!isPowerSaveMode) {
                    return 1;
                }
                return 2;
            }
            return 1;
        }

        @Override // androidx.appcompat.app.h.m
        public void d() {
            h.this.F();
        }
    }

    /* loaded from: classes.dex */
    public abstract class m {
        public BroadcastReceiver a;

        /* loaded from: classes.dex */
        public class a extends BroadcastReceiver {
            public a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                m.this.d();
            }
        }

        public m() {
        }

        public void a() {
            BroadcastReceiver broadcastReceiver = this.a;
            if (broadcastReceiver != null) {
                try {
                    h.this.e.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.a = null;
            }
        }

        public abstract IntentFilter b();

        public abstract int c();

        public abstract void d();

        public void e() {
            a();
            IntentFilter b = b();
            if (b != null && b.countActions() != 0) {
                if (this.a == null) {
                    this.a = new a();
                }
                h.this.e.registerReceiver(this.a, b);
            }
        }
    }

    /* loaded from: classes.dex */
    public class n extends m {
        public final androidx.appcompat.app.p c;

        public n(androidx.appcompat.app.p pVar) {
            super();
            this.c = pVar;
        }

        @Override // androidx.appcompat.app.h.m
        public IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.h.m
        public int c() {
            if (this.c.d()) {
                return 2;
            }
            return 1;
        }

        @Override // androidx.appcompat.app.h.m
        public void d() {
            h.this.F();
        }
    }

    /* loaded from: classes.dex */
    public class o extends ContentFrameLayout {
        public o(Context context) {
            super(context);
        }

        public final boolean c(int i, int i2) {
            if (i >= -5 && i2 >= -5 && i <= getWidth() + 5 && i2 <= getHeight() + 5) {
                return false;
            }
            return true;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (!h.this.S(keyEvent) && !super.dispatchKeyEvent(keyEvent)) {
                return false;
            }
            return true;
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0 && c((int) motionEvent.getX(), (int) motionEvent.getY())) {
                h.this.N(0);
                return true;
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public void setBackgroundResource(int i) {
            setBackgroundDrawable(b.b.d(getContext(), i));
        }
    }

    /* loaded from: classes.dex */
    public static final class p {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public ViewGroup g;
        public View h;
        public View i;
        public androidx.appcompat.view.menu.g j;
        public androidx.appcompat.view.menu.e k;
        public Context l;
        public boolean m;
        public boolean n;
        public boolean o;
        public boolean p;
        public boolean q = false;
        public boolean r;

        /* renamed from: s  reason: collision with root package name */
        public Bundle f5s;

        public p(int i) {
            this.a = i;
        }

        public androidx.appcompat.view.menu.n a(m.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                androidx.appcompat.view.menu.e eVar = new androidx.appcompat.view.menu.e(this.l, R$layout.abc_list_menu_item_layout);
                this.k = eVar;
                eVar.setCallback(aVar);
                this.j.addMenuPresenter(this.k);
            }
            return this.k.b(this.g);
        }

        public boolean b() {
            if (this.h == null) {
                return false;
            }
            if (this.i == null && this.k.a().getCount() <= 0) {
                return false;
            }
            return true;
        }

        public void c(androidx.appcompat.view.menu.g gVar) {
            androidx.appcompat.view.menu.e eVar;
            androidx.appcompat.view.menu.g gVar2 = this.j;
            if (gVar == gVar2) {
                return;
            }
            if (gVar2 != null) {
                gVar2.removeMenuPresenter(this.k);
            }
            this.j = gVar;
            if (gVar != null && (eVar = this.k) != null) {
                gVar.addMenuPresenter(eVar);
            }
        }

        public void d(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                newTheme.applyStyle(i, true);
            }
            newTheme.resolveAttribute(R$attr.panelMenuListTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                newTheme.applyStyle(i2, true);
            } else {
                newTheme.applyStyle(R$style.Theme_AppCompat_CompactMenu, true);
            }
            e.d dVar = new e.d(context, 0);
            dVar.getTheme().setTo(newTheme);
            this.l = dVar;
            TypedArray obtainStyledAttributes = dVar.obtainStyledAttributes(R$styleable.o);
            this.b = obtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_panelBackground, 0);
            this.f = obtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }
    }

    /* loaded from: classes.dex */
    public final class q implements m.a {
        public q() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            Window.Callback d0;
            if (gVar == 0) {
                h hVar = h.this;
                if (hVar.A && (d0 = hVar.d0()) != null && !h.this.M) {
                    d0.onMenuOpened(108, gVar);
                    return true;
                }
                return true;
            }
            return true;
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
            boolean z2;
            o.a rootMenu = gVar.getRootMenu();
            if (rootMenu != gVar) {
                z2 = true;
            } else {
                z2 = false;
            }
            h hVar = h.this;
            o.a aVar = gVar;
            if (z2) {
                aVar = rootMenu;
            }
            p X = hVar.X(aVar);
            if (X != null) {
                if (z2) {
                    h.this.K(X.a, X, rootMenu);
                    h.this.O(X, true);
                    return;
                }
                h.this.O(X, z);
            }
        }
    }

    static {
        boolean z;
        int i2 = Build.VERSION.SDK_INT;
        boolean z2 = false;
        if (i2 < 21) {
            z = true;
        } else {
            z = false;
        }
        g0 = z;
        h0 = new int[]{16842836};
        if (i2 >= 21 && i2 <= 25) {
            z2 = true;
        }
        j0 = z2;
        if (z && !i0) {
            Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
            i0 = true;
        }
    }

    public h(Activity activity, androidx.appcompat.app.e eVar) {
        this(activity, null, eVar, activity);
    }

    @Override // androidx.appcompat.app.f
    public void A(View view, ViewGroup.LayoutParams layoutParams) {
        V();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.g.a().onContentChanged();
    }

    public final int A0(int i2) {
        if (i2 == 8) {
            return 108;
        }
        if (i2 == 9) {
            return 109;
        }
        return i2;
    }

    @Override // androidx.appcompat.app.f
    public void B(Toolbar toolbar) {
        if (!(this.d instanceof Activity)) {
            return;
        }
        androidx.appcompat.app.a j2 = j();
        if (!(j2 instanceof androidx.appcompat.app.q)) {
            this.j = null;
            if (j2 != null) {
                j2.n();
            }
            if (toolbar != null) {
                androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(toolbar, c0(), this.g);
                this.i = nVar;
                this.f.setCallback(nVar.A());
            } else {
                this.i = null;
                this.f.setCallback(this.g);
            }
            l();
            return;
        }
        throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
    }

    public final boolean B0() {
        ViewGroup viewGroup;
        if (this.u && (viewGroup = this.v) != null && d1.Q(viewGroup)) {
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.f
    public void C(int i2) {
        this.O = i2;
    }

    public final boolean C0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || d1.P((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    @Override // androidx.appcompat.app.f
    public final void D(CharSequence charSequence) {
        this.k = charSequence;
        h1 h1Var = this.l;
        if (h1Var != null) {
            h1Var.setWindowTitle(charSequence);
        } else if (w0() != null) {
            w0().x(charSequence);
        } else {
            TextView textView = this.w;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public e.b D0(e.b.a r8) {
        /*
            Method dump skipped, instructions count: 369
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.h.D0(e.b$a):e.b");
    }

    @Override // androidx.appcompat.app.f
    public e.b E(b.a aVar) {
        androidx.appcompat.app.e eVar;
        if (aVar != null) {
            e.b bVar = this.o;
            if (bVar != null) {
                bVar.a();
            }
            j jVar = new j(aVar);
            androidx.appcompat.app.a j2 = j();
            if (j2 != null) {
                e.b y = j2.y(jVar);
                this.o = y;
                if (y != null && (eVar = this.h) != null) {
                    eVar.onSupportActionModeStarted(y);
                }
            }
            if (this.o == null) {
                this.o = D0(jVar);
            }
            return this.o;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    public final void E0() {
        if (!this.u) {
            return;
        }
        throw new AndroidRuntimeException("Window feature must be requested before adding content");
    }

    public boolean F() {
        return G(true);
    }

    public final androidx.appcompat.app.d F0() {
        for (Context context = this.e; context != null; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof androidx.appcompat.app.d) {
                return (androidx.appcompat.app.d) context;
            }
            if (!(context instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    public final boolean G(boolean z) {
        if (this.M) {
            return false;
        }
        int J = J();
        boolean G0 = G0(l0(J), z);
        if (J == 0) {
            a0().e();
        } else {
            m mVar = this.V;
            if (mVar != null) {
                mVar.a();
            }
        }
        if (J == 3) {
            Z().e();
        } else {
            m mVar2 = this.W;
            if (mVar2 != null) {
                mVar2.a();
            }
        }
        return G0;
    }

    public final boolean G0(int i2, boolean z) {
        int i3;
        int i4 = this.e.getApplicationContext().getResources().getConfiguration().uiMode & 48;
        boolean z2 = true;
        if (i2 != 1) {
            if (i2 != 2) {
                i3 = i4;
            } else {
                i3 = 32;
            }
        } else {
            i3 = 16;
        }
        boolean j02 = j0();
        boolean z3 = false;
        if ((j0 || i3 != i4) && !j02 && !this.J && (this.d instanceof ContextThemeWrapper)) {
            Configuration configuration = new Configuration();
            configuration.uiMode = (configuration.uiMode & (-49)) | i3;
            try {
                ((ContextThemeWrapper) this.d).applyOverrideConfiguration(configuration);
                z3 = true;
            } catch (IllegalStateException e2) {
                Log.e("AppCompatDelegate", "updateForNightMode. Calling applyOverrideConfiguration() failed with an exception. Will fall back to using Resources.updateConfiguration()", e2);
            }
        }
        int i5 = this.e.getResources().getConfiguration().uiMode & 48;
        if (!z3 && i5 != i3 && z && !j02 && this.J) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                i.h.f((Activity) obj);
                z3 = true;
            }
        }
        if (!z3 && i5 != i3) {
            H0(i3, j02);
        } else {
            z2 = z3;
        }
        if (z2) {
            Object obj2 = this.d;
            if (obj2 instanceof androidx.appcompat.app.d) {
                ((androidx.appcompat.app.d) obj2).onNightModeChanged(i2);
            }
        }
        return z2;
    }

    public final void H() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.v.findViewById(16908290);
        View decorView = this.f.getDecorView();
        contentFrameLayout.b(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.e.obtainStyledAttributes(R$styleable.o);
        obtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        int i2 = R$styleable.AppCompatTheme_windowFixedWidthMajor;
        if (obtainStyledAttributes.hasValue(i2)) {
            obtainStyledAttributes.getValue(i2, contentFrameLayout.getFixedWidthMajor());
        }
        int i3 = R$styleable.AppCompatTheme_windowFixedWidthMinor;
        if (obtainStyledAttributes.hasValue(i3)) {
            obtainStyledAttributes.getValue(i3, contentFrameLayout.getFixedWidthMinor());
        }
        int i4 = R$styleable.AppCompatTheme_windowFixedHeightMajor;
        if (obtainStyledAttributes.hasValue(i4)) {
            obtainStyledAttributes.getValue(i4, contentFrameLayout.getFixedHeightMajor());
        }
        int i5 = R$styleable.AppCompatTheme_windowFixedHeightMinor;
        if (obtainStyledAttributes.hasValue(i5)) {
            obtainStyledAttributes.getValue(i5, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public final void H0(int i2, boolean z) {
        Resources resources = this.e.getResources();
        Configuration configuration = new Configuration(resources.getConfiguration());
        configuration.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration, null);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 26) {
            androidx.appcompat.app.l.a(resources);
        }
        int i4 = this.O;
        if (i4 != 0) {
            this.e.setTheme(i4);
            if (i3 >= 23) {
                this.e.getTheme().applyStyle(this.O, true);
            }
        }
        if (z) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                if (activity instanceof androidx.lifecycle.f) {
                    if (((androidx.lifecycle.f) activity).getLifecycle().b().a(c.b.STARTED)) {
                        activity.onConfigurationChanged(configuration);
                    }
                } else if (this.L) {
                    activity.onConfigurationChanged(configuration);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.appcompat.app.h$k, android.view.Window$Callback] */
    public final void I(Window window) {
        if (this.f == null) {
            Window.Callback callback = window.getCallback();
            if (!(callback instanceof k)) {
                ?? kVar = new k(callback);
                this.g = kVar;
                window.setCallback(kVar);
                r2 t = r2.t(this.e, null, h0);
                Drawable h = t.h(0);
                if (h != null) {
                    window.setBackgroundDrawable(h);
                }
                t.v();
                this.f = window;
                return;
            }
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    public int I0(int i2) {
        boolean z;
        int i3;
        boolean z2;
        ActionBarContextView actionBarContextView = this.p;
        int i4 = 0;
        if (actionBarContextView != null && (actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.p.getLayoutParams();
            boolean z3 = true;
            if (this.p.isShown()) {
                if (this.c0 == null) {
                    this.c0 = new Rect();
                    this.d0 = new Rect();
                }
                Rect rect = this.c0;
                Rect rect2 = this.d0;
                rect.set(0, i2, 0, 0);
                y2.a(this.v, rect, rect2);
                if (rect2.top == 0) {
                    i3 = i2;
                } else {
                    i3 = 0;
                }
                if (marginLayoutParams.topMargin != i3) {
                    marginLayoutParams.topMargin = i2;
                    View view = this.x;
                    if (view == null) {
                        View view2 = new View(this.e);
                        this.x = view2;
                        view2.setBackgroundColor(this.e.getResources().getColor(R$color.abc_input_method_navigation_guard));
                        this.v.addView(this.x, -1, new ViewGroup.LayoutParams(-1, i2));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i2) {
                            layoutParams.height = i2;
                            this.x.setLayoutParams(layoutParams);
                        }
                    }
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (this.x == null) {
                    z3 = false;
                }
                if (!this.C && z3) {
                    i2 = 0;
                }
                boolean z4 = z3;
                z3 = z2;
                z = z4;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z = false;
            } else {
                z = false;
                z3 = false;
            }
            if (z3) {
                this.p.setLayoutParams(marginLayoutParams);
            }
        } else {
            z = false;
        }
        View view3 = this.x;
        if (view3 != null) {
            if (!z) {
                i4 = 8;
            }
            view3.setVisibility(i4);
        }
        return i2;
    }

    public final int J() {
        int i2 = this.N;
        if (i2 == -100) {
            return androidx.appcompat.app.f.f();
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [android.view.Window$Callback] */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.view.Menu] */
    /* JADX WARN: Type inference failed for: r5v1, types: [android.view.Menu] */
    /* JADX WARN: Type inference failed for: r5v2, types: [androidx.appcompat.view.menu.g] */
    public void K(int i2, p pVar, Menu menu) {
        if (menu == 0) {
            if (pVar == null && i2 >= 0) {
                p[] pVarArr = this.G;
                if (i2 < pVarArr.length) {
                    pVar = pVarArr[i2];
                }
            }
            if (pVar != null) {
                menu = pVar.j;
            }
        }
        if ((pVar == null || pVar.o) && !this.M) {
            this.g.a().onPanelClosed(i2, menu);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void L(androidx.appcompat.view.menu.g gVar) {
        if (this.F) {
            return;
        }
        this.F = true;
        this.l.i();
        Window.Callback d0 = d0();
        if (d0 != null && !this.M) {
            d0.onPanelClosed(108, gVar);
        }
        this.F = false;
    }

    public final void M() {
        m mVar = this.V;
        if (mVar != null) {
            mVar.a();
        }
        m mVar2 = this.W;
        if (mVar2 != null) {
            mVar2.a();
        }
    }

    public void N(int i2) {
        O(b0(i2, true), true);
    }

    public void O(p pVar, boolean z) {
        ViewGroup viewGroup;
        h1 h1Var;
        if (z && pVar.a == 0 && (h1Var = this.l) != null && h1Var.b()) {
            L(pVar.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.e.getSystemService("window");
        if (windowManager != null && pVar.o && (viewGroup = pVar.g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                K(pVar.a, pVar, null);
            }
        }
        pVar.m = false;
        pVar.n = false;
        pVar.o = false;
        pVar.h = null;
        pVar.q = true;
        if (this.H == pVar) {
            this.H = null;
        }
    }

    public final ViewGroup P() {
        ViewGroup viewGroup;
        e.d dVar;
        TypedArray obtainStyledAttributes = this.e.obtainStyledAttributes(R$styleable.o);
        int i2 = R$styleable.AppCompatTheme_windowActionBar;
        if (obtainStyledAttributes.hasValue(i2)) {
            if (obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowNoTitle, false)) {
                x(1);
            } else if (obtainStyledAttributes.getBoolean(i2, false)) {
                x(108);
            }
            if (obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBarOverlay, false)) {
                x(109);
            }
            if (obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionModeOverlay, false)) {
                x(10);
            }
            this.D = obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_android_windowIsFloating, false);
            obtainStyledAttributes.recycle();
            W();
            this.f.getDecorView();
            LayoutInflater from = LayoutInflater.from(this.e);
            if (!this.E) {
                if (this.D) {
                    viewGroup = (ViewGroup) from.inflate(R$layout.abc_dialog_title_material, (ViewGroup) null);
                    this.B = false;
                    this.A = false;
                } else if (this.A) {
                    TypedValue typedValue = new TypedValue();
                    this.e.getTheme().resolveAttribute(R$attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        dVar = new e.d(this.e, typedValue.resourceId);
                    } else {
                        dVar = this.e;
                    }
                    viewGroup = (ViewGroup) LayoutInflater.from(dVar).inflate(R$layout.abc_screen_toolbar, (ViewGroup) null);
                    h1 h1Var = (h1) viewGroup.findViewById(R$id.decor_content_parent);
                    this.l = h1Var;
                    h1Var.setWindowCallback(d0());
                    if (this.B) {
                        this.l.h(109);
                    }
                    if (this.y) {
                        this.l.h(2);
                    }
                    if (this.z) {
                        this.l.h(5);
                    }
                } else {
                    viewGroup = null;
                }
            } else {
                if (this.C) {
                    viewGroup = (ViewGroup) from.inflate(R$layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null);
                } else {
                    viewGroup = (ViewGroup) from.inflate(R$layout.abc_screen_simple, (ViewGroup) null);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    d1.y0(viewGroup, new c());
                } else {
                    ((s1) viewGroup).setOnFitSystemWindowsListener(new d());
                }
            }
            if (viewGroup != null) {
                if (this.l == null) {
                    this.w = (TextView) viewGroup.findViewById(R$id.title);
                }
                y2.c(viewGroup);
                ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R$id.action_bar_activity_content);
                ViewGroup viewGroup2 = (ViewGroup) this.f.findViewById(16908290);
                if (viewGroup2 != null) {
                    while (viewGroup2.getChildCount() > 0) {
                        View childAt = viewGroup2.getChildAt(0);
                        viewGroup2.removeViewAt(0);
                        contentFrameLayout.addView(childAt);
                    }
                    viewGroup2.setId(-1);
                    contentFrameLayout.setId(16908290);
                    if (viewGroup2 instanceof FrameLayout) {
                        ((FrameLayout) viewGroup2).setForeground(null);
                    }
                }
                this.f.setContentView(viewGroup);
                contentFrameLayout.setAttachListener(new e());
                return viewGroup;
            }
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.A + ", windowActionBarOverlay: " + this.B + ", android:windowIsFloating: " + this.D + ", windowActionModeOverlay: " + this.C + ", windowNoTitle: " + this.E + " }");
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    public View Q(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z;
        boolean z2 = false;
        if (this.e0 == null) {
            String string = this.e.obtainStyledAttributes(R$styleable.o).getString(R$styleable.AppCompatTheme_viewInflaterClass);
            if (string != null && !AppCompatViewInflater.class.getName().equals(string)) {
                try {
                    this.e0 = (AppCompatViewInflater) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to instantiate custom view inflater ");
                    sb.append(string);
                    sb.append(". Falling back to default.");
                    this.e0 = new AppCompatViewInflater();
                }
            } else {
                this.e0 = new AppCompatViewInflater();
            }
        }
        boolean z3 = g0;
        if (z3) {
            if (attributeSet instanceof XmlPullParser) {
                if (((XmlPullParser) attributeSet).getDepth() > 1) {
                    z2 = true;
                }
            } else {
                z2 = C0((ViewParent) view);
            }
            z = z2;
        } else {
            z = false;
        }
        return this.e0.createView(view, str, context, attributeSet, z, z3, true, x2.b());
    }

    public void R() {
        androidx.appcompat.view.menu.g gVar;
        h1 h1Var = this.l;
        if (h1Var != null) {
            h1Var.i();
        }
        if (this.q != null) {
            this.f.getDecorView().removeCallbacks(this.r);
            if (this.q.isShowing()) {
                try {
                    this.q.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.q = null;
        }
        U();
        p b0 = b0(0, false);
        if (b0 != null && (gVar = b0.j) != null) {
            gVar.close();
        }
    }

    public boolean S(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.d;
        boolean z = true;
        if (((obj instanceof k.a) || (obj instanceof androidx.appcompat.app.j)) && (decorView = this.f.getDecorView()) != null && v.k.d(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.g.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) {
            z = false;
        }
        if (z) {
            return n0(keyCode, keyEvent);
        }
        return q0(keyCode, keyEvent);
    }

    public void T(int i2) {
        p b0;
        p b02 = b0(i2, true);
        if (b02.j != null) {
            Bundle bundle = new Bundle();
            b02.j.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                b02.f5s = bundle;
            }
            b02.j.stopDispatchingItemsChanged();
            b02.j.clear();
        }
        b02.r = true;
        b02.q = true;
        if ((i2 == 108 || i2 == 0) && this.l != null && (b0 = b0(0, false)) != null) {
            b0.m = false;
            y0(b0, null);
        }
    }

    public void U() {
        b2 b2Var = this.f4s;
        if (b2Var != null) {
            b2Var.b();
        }
    }

    public final void V() {
        if (!this.u) {
            this.v = P();
            CharSequence c0 = c0();
            if (!TextUtils.isEmpty(c0)) {
                h1 h1Var = this.l;
                if (h1Var != null) {
                    h1Var.setWindowTitle(c0);
                } else if (w0() != null) {
                    w0().x(c0);
                } else {
                    TextView textView = this.w;
                    if (textView != null) {
                        textView.setText(c0);
                    }
                }
            }
            H();
            u0(this.v);
            this.u = true;
            p b0 = b0(0, false);
            if (!this.M) {
                if (b0 == null || b0.j == null) {
                    i0(108);
                }
            }
        }
    }

    public final void W() {
        if (this.f == null) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                I(((Activity) obj).getWindow());
            }
        }
        if (this.f != null) {
            return;
        }
        throw new IllegalStateException("We have not been given a Window");
    }

    public p X(Menu menu) {
        int i2;
        p[] pVarArr = this.G;
        if (pVarArr != null) {
            i2 = pVarArr.length;
        } else {
            i2 = 0;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            p pVar = pVarArr[i3];
            if (pVar != null && pVar.j == menu) {
                return pVar;
            }
        }
        return null;
    }

    public final Context Y() {
        Context context;
        androidx.appcompat.app.a j2 = j();
        if (j2 != null) {
            context = j2.k();
        } else {
            context = null;
        }
        if (context == null) {
            return this.e;
        }
        return context;
    }

    public final m Z() {
        if (this.W == null) {
            this.W = new l(this.e);
        }
        return this.W;
    }

    @Override // androidx.appcompat.app.f
    public void a(View view, ViewGroup.LayoutParams layoutParams) {
        V();
        ((ViewGroup) this.v.findViewById(16908290)).addView(view, layoutParams);
        this.g.a().onContentChanged();
    }

    public final m a0() {
        if (this.V == null) {
            this.V = new n(androidx.appcompat.app.p.a(this.e));
        }
        return this.V;
    }

    @Override // androidx.appcompat.app.f
    public void b(Context context) {
        G(false);
        this.J = true;
    }

    public p b0(int i2, boolean z) {
        p[] pVarArr = this.G;
        if (pVarArr == null || pVarArr.length <= i2) {
            p[] pVarArr2 = new p[i2 + 1];
            if (pVarArr != null) {
                System.arraycopy(pVarArr, 0, pVarArr2, 0, pVarArr.length);
            }
            this.G = pVarArr2;
            pVarArr = pVarArr2;
        }
        p pVar = pVarArr[i2];
        if (pVar == null) {
            p pVar2 = new p(i2);
            pVarArr[i2] = pVar2;
            return pVar2;
        }
        return pVar;
    }

    public final CharSequence c0() {
        Object obj = this.d;
        if (obj instanceof Activity) {
            return ((Activity) obj).getTitle();
        }
        return this.k;
    }

    public final Window.Callback d0() {
        return this.f.getCallback();
    }

    @Override // androidx.appcompat.app.f
    public View e(int i2) {
        V();
        return this.f.findViewById(i2);
    }

    public final void e0() {
        V();
        if (this.A && this.i == null) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                this.i = new androidx.appcompat.app.q((Activity) this.d, this.B);
            } else if (obj instanceof Dialog) {
                this.i = new androidx.appcompat.app.q((Dialog) this.d);
            }
            androidx.appcompat.app.a aVar = this.i;
            if (aVar != null) {
                aVar.r(this.b0);
            }
        }
    }

    public final boolean f0(p pVar) {
        View view = pVar.i;
        if (view != null) {
            pVar.h = view;
            return true;
        } else if (pVar.j == null) {
            return false;
        } else {
            if (this.n == null) {
                this.n = new q();
            }
            View view2 = (View) pVar.a(this.n);
            pVar.h = view2;
            if (view2 != null) {
                return true;
            }
            return false;
        }
    }

    @Override // androidx.appcompat.app.f
    public final androidx.appcompat.app.b g() {
        return new C0006h();
    }

    public final boolean g0(p pVar) {
        pVar.d(Y());
        pVar.g = new o(pVar.l);
        pVar.c = 81;
        return true;
    }

    @Override // androidx.appcompat.app.f
    public int h() {
        return this.N;
    }

    public final boolean h0(p pVar) {
        Resources.Theme theme;
        Context context = this.e;
        int i2 = pVar.a;
        if ((i2 == 0 || i2 == 108) && this.l != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme2 = context.getTheme();
            theme2.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                theme = context.getResources().newTheme();
                theme.setTo(theme2);
                theme.applyStyle(typedValue.resourceId, true);
                theme.resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme2.resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
                theme = null;
            }
            if (typedValue.resourceId != 0) {
                if (theme == null) {
                    theme = context.getResources().newTheme();
                    theme.setTo(theme2);
                }
                theme.applyStyle(typedValue.resourceId, true);
            }
            if (theme != null) {
                Context dVar = new e.d(context, 0);
                dVar.getTheme().setTo(theme);
                context = dVar;
            }
        }
        androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(context);
        gVar.setCallback(this);
        pVar.c(gVar);
        return true;
    }

    @Override // androidx.appcompat.app.f
    public MenuInflater i() {
        Context context;
        if (this.j == null) {
            e0();
            androidx.appcompat.app.a aVar = this.i;
            if (aVar != null) {
                context = aVar.k();
            } else {
                context = this.e;
            }
            this.j = new e.g(context);
        }
        return this.j;
    }

    public final void i0(int i2) {
        this.Y = (1 << i2) | this.Y;
        if (!this.X) {
            d1.c0(this.f.getDecorView(), this.Z);
            this.X = true;
        }
    }

    @Override // androidx.appcompat.app.f
    public androidx.appcompat.app.a j() {
        e0();
        return this.i;
    }

    public final boolean j0() {
        boolean z;
        if (!this.S && (this.d instanceof Activity)) {
            PackageManager packageManager = this.e.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            try {
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(this.e, this.d.getClass()), 0);
                if (activityInfo != null && (activityInfo.configChanges & 512) != 0) {
                    z = true;
                } else {
                    z = false;
                }
                this.Q = z;
            } catch (PackageManager.NameNotFoundException unused) {
                this.Q = false;
            }
        }
        this.S = true;
        return this.Q;
    }

    @Override // androidx.appcompat.app.f
    public void k() {
        LayoutInflater from = LayoutInflater.from(this.e);
        if (from.getFactory() == null) {
            v.l.b(from, this);
        } else {
            boolean z = from.getFactory2() instanceof h;
        }
    }

    public boolean k0() {
        return this.t;
    }

    @Override // androidx.appcompat.app.f
    public void l() {
        androidx.appcompat.app.a j2 = j();
        if (j2 != null && j2.l()) {
            return;
        }
        i0(0);
    }

    public int l0(int i2) {
        Object systemService;
        if (i2 == -100) {
            return -1;
        }
        if (i2 != -1) {
            if (i2 != 0) {
                if (i2 != 1 && i2 != 2) {
                    if (i2 == 3) {
                        return Z().c();
                    }
                    throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                }
                return i2;
            }
            if (Build.VERSION.SDK_INT >= 23) {
                systemService = this.e.getSystemService(UiModeManager.class);
                if (((UiModeManager) systemService).getNightMode() == 0) {
                    return -1;
                }
            }
            return a0().c();
        }
        return i2;
    }

    public boolean m0() {
        e.b bVar = this.o;
        if (bVar != null) {
            bVar.a();
            return true;
        }
        androidx.appcompat.app.a j2 = j();
        if (j2 != null && j2.h()) {
            return true;
        }
        return false;
    }

    public boolean n0(int i2, KeyEvent keyEvent) {
        boolean z = true;
        if (i2 != 4) {
            if (i2 == 82) {
                o0(0, keyEvent);
                return true;
            }
        } else {
            if ((keyEvent.getFlags() & 128) == 0) {
                z = false;
            }
            this.I = z;
        }
        return false;
    }

    @Override // androidx.appcompat.app.f
    public void o(Configuration configuration) {
        androidx.appcompat.app.a j2;
        if (this.A && this.u && (j2 = j()) != null) {
            j2.m(configuration);
        }
        androidx.appcompat.widget.k.b().g(this.e);
        G(false);
    }

    public final boolean o0(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {
            p b0 = b0(i2, true);
            if (!b0.o) {
                return y0(b0, keyEvent);
            }
            return false;
        }
        return false;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return Q(view, str, context, attributeSet);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean onMenuItemSelected(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        p X;
        Window.Callback d0 = d0();
        if (d0 != null && !this.M && (X = X(gVar.getRootMenu())) != null) {
            return d0.onMenuItemSelected(X.a, menuItem);
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void onMenuModeChange(androidx.appcompat.view.menu.g gVar) {
        z0(gVar, true);
    }

    @Override // androidx.appcompat.app.f
    public void p(Bundle bundle) {
        String str;
        this.J = true;
        G(false);
        W();
        Object obj = this.d;
        if (obj instanceof Activity) {
            try {
                str = i.p.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
                str = null;
            }
            if (str != null) {
                androidx.appcompat.app.a w0 = w0();
                if (w0 == null) {
                    this.b0 = true;
                } else {
                    w0.r(true);
                }
            }
        }
        this.K = true;
    }

    public boolean p0(int i2, KeyEvent keyEvent) {
        androidx.appcompat.app.a j2 = j();
        if (j2 != null && j2.o(i2, keyEvent)) {
            return true;
        }
        p pVar = this.H;
        if (pVar != null && x0(pVar, keyEvent.getKeyCode(), keyEvent, 1)) {
            p pVar2 = this.H;
            if (pVar2 != null) {
                pVar2.n = true;
            }
            return true;
        }
        if (this.H == null) {
            p b0 = b0(0, true);
            y0(b0, keyEvent);
            boolean x0 = x0(b0, keyEvent.getKeyCode(), keyEvent, 1);
            b0.m = false;
            if (x0) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.app.f
    public void q() {
        androidx.appcompat.app.f.n(this);
        if (this.X) {
            this.f.getDecorView().removeCallbacks(this.Z);
        }
        this.L = false;
        this.M = true;
        androidx.appcompat.app.a aVar = this.i;
        if (aVar != null) {
            aVar.n();
        }
        M();
    }

    public boolean q0(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            if (i2 == 82) {
                r0(0, keyEvent);
                return true;
            }
        } else {
            boolean z = this.I;
            this.I = false;
            p b0 = b0(0, false);
            if (b0 != null && b0.o) {
                if (!z) {
                    O(b0, true);
                }
                return true;
            } else if (m0()) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.app.f
    public void r(Bundle bundle) {
        V();
    }

    public final boolean r0(int i2, KeyEvent keyEvent) {
        boolean z;
        AudioManager audioManager;
        h1 h1Var;
        if (this.o != null) {
            return false;
        }
        boolean z2 = true;
        p b0 = b0(i2, true);
        if (i2 == 0 && (h1Var = this.l) != null && h1Var.d() && !ViewConfiguration.get(this.e).hasPermanentMenuKey()) {
            if (!this.l.b()) {
                if (!this.M && y0(b0, keyEvent)) {
                    z2 = this.l.g();
                }
                z2 = false;
            } else {
                z2 = this.l.f();
            }
        } else {
            boolean z3 = b0.o;
            if (!z3 && !b0.n) {
                if (b0.m) {
                    if (b0.r) {
                        b0.m = false;
                        z = y0(b0, keyEvent);
                    } else {
                        z = true;
                    }
                    if (z) {
                        v0(b0, keyEvent);
                    }
                }
                z2 = false;
            } else {
                O(b0, true);
                z2 = z3;
            }
        }
        if (z2 && (audioManager = (AudioManager) this.e.getSystemService("audio")) != null) {
            audioManager.playSoundEffect(0);
        }
        return z2;
    }

    @Override // androidx.appcompat.app.f
    public void s() {
        androidx.appcompat.app.a j2 = j();
        if (j2 != null) {
            j2.u(true);
        }
    }

    public void s0(int i2) {
        androidx.appcompat.app.a j2;
        if (i2 == 108 && (j2 = j()) != null) {
            j2.i(true);
        }
    }

    @Override // androidx.appcompat.app.f
    public void t(Bundle bundle) {
        if (this.N != -100) {
            f0.put(this.d.getClass(), Integer.valueOf(this.N));
        }
    }

    public void t0(int i2) {
        if (i2 == 108) {
            androidx.appcompat.app.a j2 = j();
            if (j2 != null) {
                j2.i(false);
            }
        } else if (i2 == 0) {
            p b0 = b0(i2, true);
            if (b0.o) {
                O(b0, false);
            }
        }
    }

    @Override // androidx.appcompat.app.f
    public void u() {
        this.L = true;
        F();
        androidx.appcompat.app.f.m(this);
    }

    public void u0(ViewGroup viewGroup) {
    }

    @Override // androidx.appcompat.app.f
    public void v() {
        this.L = false;
        androidx.appcompat.app.f.n(this);
        androidx.appcompat.app.a j2 = j();
        if (j2 != null) {
            j2.u(false);
        }
        if (this.d instanceof Dialog) {
            M();
        }
    }

    public final void v0(p pVar, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        boolean z;
        if (!pVar.o && !this.M) {
            if (pVar.a == 0) {
                if ((this.e.getResources().getConfiguration().screenLayout & 15) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    return;
                }
            }
            Window.Callback d0 = d0();
            if (d0 != null && !d0.onMenuOpened(pVar.a, pVar.j)) {
                O(pVar, true);
                return;
            }
            WindowManager windowManager = (WindowManager) this.e.getSystemService("window");
            if (windowManager == null || !y0(pVar, keyEvent)) {
                return;
            }
            ViewGroup viewGroup = pVar.g;
            if (viewGroup != null && !pVar.q) {
                View view = pVar.i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                    pVar.n = false;
                    WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, pVar.d, pVar.e, 1002, 8519680, -3);
                    layoutParams2.gravity = pVar.c;
                    layoutParams2.windowAnimations = pVar.f;
                    windowManager.addView(pVar.g, layoutParams2);
                    pVar.o = true;
                }
            } else {
                if (viewGroup == null) {
                    if (!g0(pVar) || pVar.g == null) {
                        return;
                    }
                } else if (pVar.q && viewGroup.getChildCount() > 0) {
                    pVar.g.removeAllViews();
                }
                if (f0(pVar) && pVar.b()) {
                    ViewGroup.LayoutParams layoutParams3 = pVar.h.getLayoutParams();
                    if (layoutParams3 == null) {
                        layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
                    }
                    pVar.g.setBackgroundResource(pVar.b);
                    ViewParent parent = pVar.h.getParent();
                    if (parent instanceof ViewGroup) {
                        ((ViewGroup) parent).removeView(pVar.h);
                    }
                    pVar.g.addView(pVar.h, layoutParams3);
                    if (!pVar.h.hasFocus()) {
                        pVar.h.requestFocus();
                    }
                } else {
                    return;
                }
            }
            i2 = -2;
            pVar.n = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i2, -2, pVar.d, pVar.e, 1002, 8519680, -3);
            layoutParams22.gravity = pVar.c;
            layoutParams22.windowAnimations = pVar.f;
            windowManager.addView(pVar.g, layoutParams22);
            pVar.o = true;
        }
    }

    public final androidx.appcompat.app.a w0() {
        return this.i;
    }

    @Override // androidx.appcompat.app.f
    public boolean x(int i2) {
        int A0 = A0(i2);
        if (this.E && A0 == 108) {
            return false;
        }
        if (this.A && A0 == 1) {
            this.A = false;
        }
        if (A0 != 1) {
            if (A0 != 2) {
                if (A0 != 5) {
                    if (A0 != 10) {
                        if (A0 != 108) {
                            if (A0 != 109) {
                                return this.f.requestFeature(A0);
                            }
                            E0();
                            this.B = true;
                            return true;
                        }
                        E0();
                        this.A = true;
                        return true;
                    }
                    E0();
                    this.C = true;
                    return true;
                }
                E0();
                this.z = true;
                return true;
            }
            E0();
            this.y = true;
            return true;
        }
        E0();
        this.E = true;
        return true;
    }

    public final boolean x0(p pVar, int i2, KeyEvent keyEvent, int i3) {
        androidx.appcompat.view.menu.g gVar;
        boolean z = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((pVar.m || y0(pVar, keyEvent)) && (gVar = pVar.j) != null) {
            z = gVar.performShortcut(i2, keyEvent, i3);
        }
        if (z && (i3 & 1) == 0 && this.l == null) {
            O(pVar, true);
        }
        return z;
    }

    @Override // androidx.appcompat.app.f
    public void y(int i2) {
        V();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.e).inflate(i2, viewGroup);
        this.g.a().onContentChanged();
    }

    public final boolean y0(p pVar, KeyEvent keyEvent) {
        boolean z;
        h1 h1Var;
        int i2;
        boolean z2;
        h1 h1Var2;
        h1 h1Var3;
        if (this.M) {
            return false;
        }
        if (pVar.m) {
            return true;
        }
        p pVar2 = this.H;
        if (pVar2 != null && pVar2 != pVar) {
            O(pVar2, false);
        }
        Window.Callback d0 = d0();
        if (d0 != null) {
            pVar.i = d0.onCreatePanelView(pVar.a);
        }
        int i3 = pVar.a;
        if (i3 != 0 && i3 != 108) {
            z = false;
        } else {
            z = true;
        }
        if (z && (h1Var3 = this.l) != null) {
            h1Var3.c();
        }
        if (pVar.i == null && (!z || !(w0() instanceof androidx.appcompat.app.n))) {
            androidx.appcompat.view.menu.g gVar = pVar.j;
            if (gVar == null || pVar.r) {
                if (gVar == null && (!h0(pVar) || pVar.j == null)) {
                    return false;
                }
                if (z && this.l != null) {
                    if (this.m == null) {
                        this.m = new i();
                    }
                    this.l.a(pVar.j, this.m);
                }
                pVar.j.stopDispatchingItemsChanged();
                if (!d0.onCreatePanelMenu(pVar.a, pVar.j)) {
                    pVar.c(null);
                    if (z && (h1Var = this.l) != null) {
                        h1Var.a(null, this.m);
                    }
                    return false;
                }
                pVar.r = false;
            }
            pVar.j.stopDispatchingItemsChanged();
            Bundle bundle = pVar.f5s;
            if (bundle != null) {
                pVar.j.restoreActionViewStates(bundle);
                pVar.f5s = null;
            }
            if (!d0.onPreparePanel(0, pVar.i, pVar.j)) {
                if (z && (h1Var2 = this.l) != null) {
                    h1Var2.a(null, this.m);
                }
                pVar.j.startDispatchingItemsChanged();
                return false;
            }
            if (keyEvent != null) {
                i2 = keyEvent.getDeviceId();
            } else {
                i2 = -1;
            }
            if (KeyCharacterMap.load(i2).getKeyboardType() != 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            pVar.p = z2;
            pVar.j.setQwertyMode(z2);
            pVar.j.startDispatchingItemsChanged();
        }
        pVar.m = true;
        pVar.n = false;
        this.H = pVar;
        return true;
    }

    @Override // androidx.appcompat.app.f
    public void z(View view) {
        V();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.g.a().onContentChanged();
    }

    public final void z0(androidx.appcompat.view.menu.g gVar, boolean z) {
        h1 h1Var = this.l;
        if (h1Var != null && h1Var.d() && (!ViewConfiguration.get(this.e).hasPermanentMenuKey() || this.l.e())) {
            Window.Callback d0 = d0();
            if (this.l.b() && z) {
                this.l.f();
                if (!this.M) {
                    d0.onPanelClosed(108, b0(0, true).j);
                    return;
                }
                return;
            } else if (d0 != null && !this.M) {
                if (this.X && (this.Y & 1) != 0) {
                    this.f.getDecorView().removeCallbacks(this.Z);
                    this.Z.run();
                }
                p b0 = b0(0, true);
                o.a aVar = b0.j;
                if (aVar != null && !b0.r && d0.onPreparePanel(0, b0.i, aVar)) {
                    d0.onMenuOpened(108, b0.j);
                    this.l.g();
                    return;
                }
                return;
            } else {
                return;
            }
        }
        p b02 = b0(0, true);
        b02.q = true;
        O(b02, false);
        v0(b02, null);
    }

    public h(Dialog dialog, androidx.appcompat.app.e eVar) {
        this(dialog.getContext(), dialog.getWindow(), eVar, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public h(Context context, Window window, androidx.appcompat.app.e eVar, Object obj) {
        androidx.appcompat.app.d F0;
        this.f4s = null;
        this.t = true;
        this.N = -100;
        this.Z = new b();
        this.e = context;
        this.h = eVar;
        this.d = obj;
        if (this.N == -100 && (obj instanceof Dialog) && (F0 = F0()) != null) {
            this.N = F0.getDelegate().h();
        }
        if (this.N == -100) {
            Map map = f0;
            Integer num = (Integer) map.get(obj.getClass());
            if (num != null) {
                this.N = num.intValue();
                map.remove(obj.getClass());
            }
        }
        if (window != null) {
            I(window);
        }
        androidx.appcompat.widget.k.h();
    }

    /* loaded from: classes.dex */
    public class k extends e.m {
        public k(Window.Callback callback) {
            super(callback);
        }

        public final ActionMode b(ActionMode.Callback callback) {
            b.a aVar = new f.a(h.this.e, callback);
            e.b E = h.this.E(aVar);
            if (E != null) {
                return aVar.e(E);
            }
            return null;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (!h.this.S(keyEvent) && !super.dispatchKeyEvent(keyEvent)) {
                return false;
            }
            return true;
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            if (!super.dispatchKeyShortcutEvent(keyEvent) && !h.this.p0(keyEvent.getKeyCode(), keyEvent)) {
                return false;
            }
            return true;
        }

        public void onContentChanged() {
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i == 0 && !(menu instanceof androidx.appcompat.view.menu.g)) {
                return false;
            }
            return super.onCreatePanelMenu(i, menu);
        }

        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            h.this.s0(i);
            return true;
        }

        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            h.this.t0(i);
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            androidx.appcompat.view.menu.g gVar;
            if (menu instanceof androidx.appcompat.view.menu.g) {
                gVar = (androidx.appcompat.view.menu.g) menu;
            } else {
                gVar = null;
            }
            if (i == 0 && gVar == null) {
                return false;
            }
            if (gVar != null) {
                gVar.setOverrideVisibleItems(true);
            }
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (gVar != null) {
                gVar.setOverrideVisibleItems(false);
            }
            return onPreparePanel;
        }

        public void onProvideKeyboardShortcuts(List list, Menu menu, int i) {
            o.a aVar;
            p b0 = h.this.b0(0, true);
            if (b0 != null && (aVar = b0.j) != null) {
                super.onProvideKeyboardShortcuts(list, aVar, i);
            } else {
                super.onProvideKeyboardShortcuts(list, menu, i);
            }
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            if (h.this.k0()) {
                return b(callback);
            }
            return super.onWindowStartingActionMode(callback);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (h.this.k0() && i == 0) {
                return b(callback);
            }
            return super.onWindowStartingActionMode(callback, i);
        }
    }
}
