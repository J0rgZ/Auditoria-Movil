package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.appcompat.resources.R$drawable;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public final class e2 {
    public static e2 i;
    public WeakHashMap a;
    public androidx.collection.a b;
    public androidx.collection.h c;
    public final WeakHashMap d = new WeakHashMap(0);
    public TypedValue e;
    public boolean f;
    public e g;
    public static final PorterDuff.Mode h = PorterDuff.Mode.SRC_IN;
    public static final c j = new c(6);

    /* loaded from: classes.dex */
    public static class a implements d {
        @Override // androidx.appcompat.widget.e2.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return c.c.l(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b implements d {
        @Override // androidx.appcompat.widget.e2.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return r0.g.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c extends androidx.collection.e {
        public c(int i) {
            super(i);
        }

        public static int b(int i, PorterDuff.Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }

        public PorterDuffColorFilter c(int i, PorterDuff.Mode mode) {
            return (PorterDuffColorFilter) get(Integer.valueOf(b(i, mode)));
        }

        public PorterDuffColorFilter d(int i, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return (PorterDuffColorFilter) put(Integer.valueOf(b(i, mode)), porterDuffColorFilter);
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* loaded from: classes.dex */
    public interface e {
        boolean a(Context context, int i, Drawable drawable);

        PorterDuff.Mode b(int i);

        Drawable c(e2 e2Var, Context context, int i);

        ColorStateList d(Context context, int i);

        boolean e(Context context, int i, Drawable drawable);
    }

    /* loaded from: classes.dex */
    public static class f implements d {
        @Override // androidx.appcompat.widget.e2.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return r0.n.c(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    public static long e(TypedValue typedValue) {
        return (typedValue.assetCookie << 32) | typedValue.data;
    }

    public static PorterDuffColorFilter g(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList != null && mode != null) {
            return l(colorStateList.getColorForState(iArr, 0), mode);
        }
        return null;
    }

    public static synchronized e2 h() {
        e2 e2Var;
        synchronized (e2.class) {
            if (i == null) {
                e2 e2Var2 = new e2();
                i = e2Var2;
                p(e2Var2);
            }
            e2Var = i;
        }
        return e2Var;
    }

    public static synchronized PorterDuffColorFilter l(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter c2;
        synchronized (e2.class) {
            c cVar = j;
            c2 = cVar.c(i2, mode);
            if (c2 == null) {
                c2 = new PorterDuffColorFilter(i2, mode);
                cVar.d(i2, mode, c2);
            }
        }
        return c2;
    }

    public static void p(e2 e2Var) {
        if (Build.VERSION.SDK_INT < 24) {
            e2Var.a("vector", new f());
            e2Var.a("animated-vector", new b());
            e2Var.a("animated-selector", new a());
        }
    }

    public static boolean q(Drawable drawable) {
        if (!(drawable instanceof r0.n) && !"android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
            return false;
        }
        return true;
    }

    public static void w(Drawable drawable, p2 p2Var, int[] iArr) {
        ColorStateList colorStateList;
        PorterDuff.Mode mode;
        if (o1.a(drawable) && drawable.mutate() != drawable) {
            return;
        }
        boolean z = p2Var.d;
        if (!z && !p2Var.c) {
            drawable.clearColorFilter();
        } else {
            if (z) {
                colorStateList = p2Var.a;
            } else {
                colorStateList = null;
            }
            if (p2Var.c) {
                mode = p2Var.b;
            } else {
                mode = h;
            }
            drawable.setColorFilter(g(colorStateList, mode, iArr));
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    public final void a(String str, d dVar) {
        if (this.b == null) {
            this.b = new androidx.collection.a();
        }
        this.b.put(str, dVar);
    }

    public final synchronized boolean b(Context context, long j2, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            androidx.collection.d dVar = (androidx.collection.d) this.d.get(context);
            if (dVar == null) {
                dVar = new androidx.collection.d();
                this.d.put(context, dVar);
            }
            dVar.j(j2, new WeakReference(constantState));
            return true;
        }
        return false;
    }

    public final void c(Context context, int i2, ColorStateList colorStateList) {
        if (this.a == null) {
            this.a = new WeakHashMap();
        }
        androidx.collection.h hVar = (androidx.collection.h) this.a.get(context);
        if (hVar == null) {
            hVar = new androidx.collection.h();
            this.a.put(context, hVar);
        }
        hVar.a(i2, colorStateList);
    }

    public final void d(Context context) {
        if (this.f) {
            return;
        }
        this.f = true;
        Drawable j2 = j(context, R$drawable.abc_vector_test);
        if (j2 != null && q(j2)) {
            return;
        }
        this.f = false;
        throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
    }

    public final Drawable f(Context context, int i2) {
        Drawable c2;
        if (this.e == null) {
            this.e = new TypedValue();
        }
        TypedValue typedValue = this.e;
        context.getResources().getValue(i2, typedValue, true);
        long e2 = e(typedValue);
        Drawable i3 = i(context, e2);
        if (i3 != null) {
            return i3;
        }
        e eVar = this.g;
        if (eVar == null) {
            c2 = null;
        } else {
            c2 = eVar.c(this, context, i2);
        }
        if (c2 != null) {
            c2.setChangingConfigurations(typedValue.changingConfigurations);
            b(context, e2, c2);
        }
        return c2;
    }

    public final synchronized Drawable i(Context context, long j2) {
        androidx.collection.d dVar = (androidx.collection.d) this.d.get(context);
        if (dVar == null) {
            return null;
        }
        WeakReference weakReference = (WeakReference) dVar.f(j2);
        if (weakReference != null) {
            Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            dVar.d(j2);
        }
        return null;
    }

    public synchronized Drawable j(Context context, int i2) {
        return k(context, i2, false);
    }

    public synchronized Drawable k(Context context, int i2, boolean z) {
        Drawable r;
        d(context);
        r = r(context, i2);
        if (r == null) {
            r = f(context, i2);
        }
        if (r == null) {
            r = j.a.getDrawable(context, i2);
        }
        if (r != null) {
            r = v(context, i2, z, r);
        }
        if (r != null) {
            o1.b(r);
        }
        return r;
    }

    public synchronized ColorStateList m(Context context, int i2) {
        ColorStateList n;
        n = n(context, i2);
        if (n == null) {
            e eVar = this.g;
            if (eVar == null) {
                n = null;
            } else {
                n = eVar.d(context, i2);
            }
            if (n != null) {
                c(context, i2, n);
            }
        }
        return n;
    }

    public final ColorStateList n(Context context, int i2) {
        androidx.collection.h hVar;
        WeakHashMap weakHashMap = this.a;
        if (weakHashMap == null || (hVar = (androidx.collection.h) weakHashMap.get(context)) == null) {
            return null;
        }
        return (ColorStateList) hVar.e(i2);
    }

    public PorterDuff.Mode o(int i2) {
        e eVar = this.g;
        if (eVar == null) {
            return null;
        }
        return eVar.b(i2);
    }

    public final Drawable r(Context context, int i2) {
        int next;
        androidx.collection.a aVar = this.b;
        if (aVar == null || aVar.isEmpty()) {
            return null;
        }
        androidx.collection.h hVar = this.c;
        if (hVar != null) {
            String str = (String) hVar.e(i2);
            if ("appcompat_skip_skip".equals(str) || (str != null && this.b.get(str) == null)) {
                return null;
            }
        } else {
            this.c = new androidx.collection.h();
        }
        if (this.e == null) {
            this.e = new TypedValue();
        }
        TypedValue typedValue = this.e;
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        long e2 = e(typedValue);
        Drawable i3 = i(context, e2);
        if (i3 != null) {
            return i3;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i2);
                AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                while (true) {
                    next = xml.next();
                    if (next == 2 || next == 1) {
                        break;
                    }
                }
                if (next == 2) {
                    String name = xml.getName();
                    this.c.a(i2, name);
                    d dVar = (d) this.b.get(name);
                    if (dVar != null) {
                        i3 = dVar.a(context, xml, asAttributeSet, context.getTheme());
                    }
                    if (i3 != null) {
                        i3.setChangingConfigurations(typedValue.changingConfigurations);
                        b(context, e2, i3);
                    }
                } else {
                    throw new XmlPullParserException("No start tag found");
                }
            } catch (Exception e3) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e3);
            }
        }
        if (i3 == null) {
            this.c.a(i2, "appcompat_skip_skip");
        }
        return i3;
    }

    public synchronized void s(Context context) {
        androidx.collection.d dVar = (androidx.collection.d) this.d.get(context);
        if (dVar != null) {
            dVar.b();
        }
    }

    public synchronized Drawable t(Context context, x2 x2Var, int i2) {
        Drawable r = r(context, i2);
        if (r == null) {
            r = x2Var.c(i2);
        }
        if (r != null) {
            return v(context, i2, false, r);
        }
        return null;
    }

    public synchronized void u(e eVar) {
        this.g = eVar;
    }

    public final Drawable v(Context context, int i2, boolean z, Drawable drawable) {
        ColorStateList m = m(context, i2);
        if (m != null) {
            if (o1.a(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable r = m.h.r(drawable);
            m.h.o(r, m);
            PorterDuff.Mode o = o(i2);
            if (o != null) {
                m.h.p(r, o);
                return r;
            }
            return r;
        }
        e eVar = this.g;
        if ((eVar == null || !eVar.e(context, i2, drawable)) && !x(context, i2, drawable) && z) {
            return null;
        }
        return drawable;
    }

    public boolean x(Context context, int i2, Drawable drawable) {
        e eVar = this.g;
        if (eVar != null && eVar.a(context, i2, drawable)) {
            return true;
        }
        return false;
    }
}
