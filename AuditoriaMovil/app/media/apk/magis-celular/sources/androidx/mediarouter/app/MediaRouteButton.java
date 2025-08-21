package androidx.mediarouter.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.appcompat.widget.u2;
import androidx.fragment.app.p;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$string;
import androidx.mediarouter.R$styleable;
import h0.o1;
import h0.s0;
import h0.t0;
import java.util.ArrayList;
import java.util.List;
import v.d1;
/* loaded from: classes.dex */
public class MediaRouteButton extends View {
    public static a p;
    public static final SparseArray q = new SparseArray(2);
    public static final int[] r = {16842912};

    /* renamed from: s  reason: collision with root package name */
    public static final int[] f22s = {16842911};
    public final t0 a;
    public final b b;
    public s0 c;
    public e d;
    public boolean e;
    public int f;
    public c g;
    public Drawable h;
    public int i;
    public int j;
    public ColorStateList k;
    public int l;
    public int m;
    public boolean n;
    public boolean o;

    /* loaded from: classes.dex */
    public static final class a extends BroadcastReceiver {
        public final Context a;
        public boolean b = true;
        public List c = new ArrayList();

        public a(Context context) {
            this.a = context;
        }

        public boolean a() {
            return this.b;
        }

        public void b(MediaRouteButton mediaRouteButton) {
            if (this.c.size() == 0) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                this.a.registerReceiver(this, intentFilter);
            }
            this.c.add(mediaRouteButton);
        }

        public void c(MediaRouteButton mediaRouteButton) {
            this.c.remove(mediaRouteButton);
            if (this.c.size() == 0) {
                this.a.unregisterReceiver(this);
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z;
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) && this.b != (!intent.getBooleanExtra("noConnectivity", false))) {
                this.b = z;
                for (MediaRouteButton mediaRouteButton : this.c) {
                    mediaRouteButton.c();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public final class b extends t0.b {
        public b() {
        }

        public void onProviderAdded(t0 t0Var, t0.h hVar) {
            MediaRouteButton.this.b();
        }

        public void onProviderChanged(t0 t0Var, t0.h hVar) {
            MediaRouteButton.this.b();
        }

        public void onProviderRemoved(t0 t0Var, t0.h hVar) {
            MediaRouteButton.this.b();
        }

        public void onRouteAdded(t0 t0Var, t0.i iVar) {
            MediaRouteButton.this.b();
        }

        public void onRouteChanged(t0 t0Var, t0.i iVar) {
            MediaRouteButton.this.b();
        }

        public void onRouteRemoved(t0 t0Var, t0.i iVar) {
            MediaRouteButton.this.b();
        }

        public void onRouteSelected(t0 t0Var, t0.i iVar) {
            MediaRouteButton.this.b();
        }

        public void onRouteUnselected(t0 t0Var, t0.i iVar) {
            MediaRouteButton.this.b();
        }
    }

    /* loaded from: classes.dex */
    public final class c extends AsyncTask {
        public final int a;
        public final Context b;

        public c(int i, Context context) {
            this.a = i;
            this.b = context;
        }

        public final void a(Drawable drawable) {
            if (drawable != null) {
                MediaRouteButton.q.put(this.a, drawable.getConstantState());
            }
            MediaRouteButton.this.g = null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b */
        public Drawable doInBackground(Void... voidArr) {
            if (((Drawable.ConstantState) MediaRouteButton.q.get(this.a)) == null) {
                return this.b.getResources().getDrawable(this.a);
            }
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: c */
        public void onCancelled(Drawable drawable) {
            a(drawable);
        }

        @Override // android.os.AsyncTask
        /* renamed from: d */
        public void onPostExecute(Drawable drawable) {
            if (drawable != null) {
                a(drawable);
            } else {
                Drawable.ConstantState constantState = (Drawable.ConstantState) MediaRouteButton.q.get(this.a);
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                MediaRouteButton.this.g = null;
            }
            MediaRouteButton.this.setRemoteIndicatorDrawableInternal(drawable);
        }
    }

    public MediaRouteButton(Context context) {
        this(context, null);
    }

    private Activity getActivity() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    private androidx.fragment.app.i getFragmentManager() {
        Activity activity = getActivity();
        if (activity instanceof androidx.fragment.app.d) {
            return ((androidx.fragment.app.d) activity).getSupportFragmentManager();
        }
        return null;
    }

    public final void a() {
        if (this.i > 0) {
            c cVar = this.g;
            if (cVar != null) {
                cVar.cancel(false);
            }
            c cVar2 = new c(this.i, getContext());
            this.g = cVar2;
            this.i = 0;
            cVar2.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }

    public void b() {
        boolean z;
        int i;
        boolean z2;
        t0.i m = this.a.m();
        boolean z3 = false;
        if (!m.w() && m.E(this.c)) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i = m.c();
        } else {
            i = 0;
        }
        if (this.j != i) {
            this.j = i;
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            g();
            refreshDrawableState();
        }
        if (i == 1) {
            a();
        }
        if (this.e) {
            setEnabled((this.n || this.a.o(this.c, 1)) ? true : true);
        }
        Drawable drawable = this.h;
        if (drawable != null && (drawable.getCurrent() instanceof AnimationDrawable)) {
            AnimationDrawable animationDrawable = (AnimationDrawable) this.h.getCurrent();
            if (this.e) {
                if ((z2 || i == 1) && !animationDrawable.isRunning()) {
                    animationDrawable.start();
                }
            } else if (i == 2) {
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
                animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
            }
        }
    }

    public void c() {
        int i;
        boolean z;
        if (this.f == 0 && !this.n && !p.a()) {
            i = 4;
        } else {
            i = this.f;
        }
        super.setVisibility(i);
        Drawable drawable = this.h;
        if (drawable != null) {
            if (getVisibility() == 0) {
                z = true;
            } else {
                z = false;
            }
            drawable.setVisible(z, false);
        }
    }

    public boolean d() {
        if (!this.e) {
            return false;
        }
        o1 k = this.a.k();
        if (k != null) {
            if (k.c() && t0.n() && f()) {
                return true;
            }
            return e(k.a());
        }
        return e(1);
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.h != null) {
            this.h.setState(getDrawableState());
            invalidate();
        }
    }

    public final boolean e(int i) {
        androidx.fragment.app.i fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            t0.i m = this.a.m();
            if (!m.w() && m.E(this.c)) {
                if (fragmentManager.c("android.support.v7.mediarouter:MediaRouteControllerDialogFragment") != null) {
                    return false;
                }
                d c2 = this.d.c();
                c2.H2(this.c);
                if (i == 2) {
                    c2.I2(true);
                }
                p a2 = fragmentManager.a();
                a2.d(c2, "android.support.v7.mediarouter:MediaRouteControllerDialogFragment");
                a2.h();
            } else if (fragmentManager.c("android.support.v7.mediarouter:MediaRouteChooserDialogFragment") != null) {
                return false;
            } else {
                androidx.mediarouter.app.b b2 = this.d.b();
                b2.I2(this.c);
                if (i == 2) {
                    b2.J2(true);
                }
                p a3 = fragmentManager.a();
                a3.d(b2, "android.support.v7.mediarouter:MediaRouteChooserDialogFragment");
                a3.h();
            }
            return true;
        }
        throw new IllegalStateException("The activity must be a subclass of FragmentActivity");
    }

    public final boolean f() {
        ApplicationInfo applicationInfo;
        Context context = getContext();
        Intent putExtra = new Intent().setAction("com.android.settings.panel.action.MEDIA_OUTPUT").putExtra("com.android.settings.panel.extra.PACKAGE_NAME", context.getPackageName()).putExtra("key_media_session_token", this.a.j());
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(putExtra, 0)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null && (applicationInfo = activityInfo.applicationInfo) != null && (applicationInfo.flags & 129) != 0) {
                context.startActivity(putExtra);
                return true;
            }
        }
        return false;
    }

    public final void g() {
        int i;
        int i2 = this.j;
        if (i2 != 1) {
            if (i2 != 2) {
                i = R$string.mr_cast_button_disconnected;
            } else {
                i = R$string.mr_cast_button_connected;
            }
        } else {
            i = R$string.mr_cast_button_connecting;
        }
        String string = getContext().getString(i);
        setContentDescription(string);
        u2.a(this, (!this.o || TextUtils.isEmpty(string)) ? null : null);
    }

    public e getDialogFactory() {
        return this.d;
    }

    public s0 getRouteSelector() {
        return this.c;
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.h;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            return;
        }
        this.e = true;
        if (!this.c.f()) {
            this.a.a(this.c, this.b);
        }
        b();
        p.b(this);
    }

    @Override // android.view.View
    public int[] onCreateDrawableState(int i) {
        boolean z;
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        t0 t0Var = this.a;
        if (t0Var == null) {
            return onCreateDrawableState;
        }
        o1 k = t0Var.k();
        if (k != null) {
            z = k.b().getBoolean("androidx.mediarouter.media.MediaRouterParams.FIXED_CAST_ICON");
        } else {
            z = false;
        }
        if (z) {
            return onCreateDrawableState;
        }
        int i2 = this.j;
        if (i2 != 1) {
            if (i2 == 2) {
                View.mergeDrawableStates(onCreateDrawableState, r);
            }
        } else {
            View.mergeDrawableStates(onCreateDrawableState, f22s);
        }
        return onCreateDrawableState;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        if (!isInEditMode()) {
            this.e = false;
            if (!this.c.f()) {
                this.a.q(this.b);
            }
            p.c(this);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.h != null) {
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int paddingTop = getPaddingTop();
            int height = getHeight() - getPaddingBottom();
            int intrinsicWidth = this.h.getIntrinsicWidth();
            int intrinsicHeight = this.h.getIntrinsicHeight();
            int i = paddingLeft + (((width - paddingLeft) - intrinsicWidth) / 2);
            int i2 = paddingTop + (((height - paddingTop) - intrinsicHeight) / 2);
            this.h.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
            this.h.draw(canvas);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i4 = this.l;
        Drawable drawable = this.h;
        int i5 = 0;
        if (drawable != null) {
            i3 = drawable.getIntrinsicWidth() + getPaddingLeft() + getPaddingRight();
        } else {
            i3 = 0;
        }
        int max = Math.max(i4, i3);
        int i6 = this.m;
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            i5 = drawable2.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom();
        }
        int max2 = Math.max(i6, i5);
        if (mode != Integer.MIN_VALUE) {
            if (mode != 1073741824) {
                size = max;
            }
        } else {
            size = Math.min(size, max);
        }
        if (mode2 != Integer.MIN_VALUE) {
            if (mode2 != 1073741824) {
                size2 = max2;
            }
        } else {
            size2 = Math.min(size2, max2);
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    public boolean performClick() {
        boolean performClick = super.performClick();
        if (!performClick) {
            playSoundEffect(0);
        }
        a();
        if (!d() && !performClick) {
            return false;
        }
        return true;
    }

    public void setAlwaysVisible(boolean z) {
        if (z != this.n) {
            this.n = z;
            c();
            b();
        }
    }

    public void setCheatSheetEnabled(boolean z) {
        if (z != this.o) {
            this.o = z;
            g();
        }
    }

    public void setDialogFactory(e eVar) {
        if (eVar != null) {
            this.d = eVar;
            return;
        }
        throw new IllegalArgumentException("factory must not be null");
    }

    public void setRemoteIndicatorDrawable(Drawable drawable) {
        this.i = 0;
        setRemoteIndicatorDrawableInternal(drawable);
    }

    public void setRemoteIndicatorDrawableInternal(Drawable drawable) {
        Drawable drawable2;
        boolean z;
        c cVar = this.g;
        if (cVar != null) {
            cVar.cancel(false);
        }
        Drawable drawable3 = this.h;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.h);
        }
        if (drawable != null) {
            if (this.k != null) {
                drawable = m.h.r(drawable.mutate());
                m.h.o(drawable, this.k);
            }
            drawable.setCallback(this);
            drawable.setState(getDrawableState());
            if (getVisibility() == 0) {
                z = true;
            } else {
                z = false;
            }
            drawable.setVisible(z, false);
        }
        this.h = drawable;
        refreshDrawableState();
        if (this.e && (drawable2 = this.h) != null && (drawable2.getCurrent() instanceof AnimationDrawable)) {
            AnimationDrawable animationDrawable = (AnimationDrawable) this.h.getCurrent();
            int i = this.j;
            if (i == 1) {
                if (!animationDrawable.isRunning()) {
                    animationDrawable.start();
                }
            } else if (i == 2) {
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
                animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
            }
        }
    }

    public void setRouteSelector(s0 s0Var) {
        if (s0Var != null) {
            if (!this.c.equals(s0Var)) {
                if (this.e) {
                    if (!this.c.f()) {
                        this.a.q(this.b);
                    }
                    if (!s0Var.f()) {
                        this.a.a(s0Var, this.b);
                    }
                }
                this.c = s0Var;
                b();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("selector must not be null");
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        this.f = i;
        c();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        if (!super.verifyDrawable(drawable) && drawable != this.h) {
            return false;
        }
        return true;
    }

    public MediaRouteButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.mediaRouteButtonStyle);
    }

    public MediaRouteButton(Context context, AttributeSet attributeSet, int i) {
        super(i.a(context), attributeSet, i);
        Drawable.ConstantState constantState;
        this.c = s0.c;
        this.d = e.a();
        this.f = 0;
        Context context2 = getContext();
        int[] iArr = R$styleable.A;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i, 0);
        d1.i0(this, context2, iArr, attributeSet, obtainStyledAttributes, i, 0);
        if (isInEditMode()) {
            this.a = null;
            this.b = null;
            this.h = getResources().getDrawable(obtainStyledAttributes.getResourceId(R$styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0));
            return;
        }
        this.a = t0.i(context2);
        this.b = new b();
        if (p == null) {
            p = new a(context2.getApplicationContext());
        }
        this.k = obtainStyledAttributes.getColorStateList(R$styleable.MediaRouteButton_mediaRouteButtonTint);
        this.l = obtainStyledAttributes.getDimensionPixelSize(R$styleable.MediaRouteButton_android_minWidth, 0);
        this.m = obtainStyledAttributes.getDimensionPixelSize(R$styleable.MediaRouteButton_android_minHeight, 0);
        int resourceId = obtainStyledAttributes.getResourceId(R$styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0);
        this.i = obtainStyledAttributes.getResourceId(R$styleable.MediaRouteButton_externalRouteEnabledDrawable, 0);
        obtainStyledAttributes.recycle();
        int i2 = this.i;
        if (i2 != 0 && (constantState = (Drawable.ConstantState) q.get(i2)) != null) {
            setRemoteIndicatorDrawable(constantState.newDrawable());
        }
        if (this.h == null) {
            if (resourceId != 0) {
                Drawable.ConstantState constantState2 = (Drawable.ConstantState) q.get(resourceId);
                if (constantState2 != null) {
                    setRemoteIndicatorDrawableInternal(constantState2.newDrawable());
                } else {
                    c cVar = new c(resourceId, getContext());
                    this.g = cVar;
                    cVar.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
                }
            } else {
                a();
            }
        }
        g();
        setClickable(true);
    }
}
