package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class o2 extends ContextWrapper {
    public static final Object c = new Object();
    public static ArrayList d;
    public final Resources a;
    public final Resources.Theme b;

    public o2(Context context) {
        super(context);
        if (x2.b()) {
            x2 x2Var = new x2(this, context.getResources());
            this.a = x2Var;
            Resources.Theme newTheme = x2Var.newTheme();
            this.b = newTheme;
            newTheme.setTo(context.getTheme());
            return;
        }
        this.a = new q2(this, context.getResources());
        this.b = null;
    }

    public static boolean a(Context context) {
        if ((context instanceof o2) || (context.getResources() instanceof q2) || (context.getResources() instanceof x2)) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 21 && !x2.b()) {
            return false;
        }
        return true;
    }

    public static Context b(Context context) {
        o2 o2Var;
        if (a(context)) {
            synchronized (c) {
                ArrayList arrayList = d;
                if (arrayList == null) {
                    d = new ArrayList();
                } else {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        WeakReference weakReference = (WeakReference) d.get(size);
                        if (weakReference == null || weakReference.get() == null) {
                            d.remove(size);
                        }
                    }
                    for (int size2 = d.size() - 1; size2 >= 0; size2--) {
                        WeakReference weakReference2 = (WeakReference) d.get(size2);
                        if (weakReference2 != null) {
                            o2Var = (o2) weakReference2.get();
                        } else {
                            o2Var = null;
                        }
                        if (o2Var != null && o2Var.getBaseContext() == context) {
                            return o2Var;
                        }
                    }
                }
                o2 o2Var2 = new o2(context);
                d.add(new WeakReference(o2Var2));
                return o2Var2;
            }
        }
        return context;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.a.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.b;
        if (theme == null) {
            return super.getTheme();
        }
        return theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        Resources.Theme theme = this.b;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}
