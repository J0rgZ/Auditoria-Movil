package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
/* loaded from: classes.dex */
public abstract class a extends ViewGroup {
    public final C0008a a;
    public final Context b;
    public ActionMenuView c;
    public d d;
    public int e;
    public v.b2 f;
    public boolean g;
    public boolean h;

    /* renamed from: androidx.appcompat.widget.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0008a implements v.c2 {
        public boolean a = false;
        public int b;

        public C0008a() {
        }

        public void a(View view) {
            this.a = true;
        }

        public void b(View view) {
            if (this.a) {
                return;
            }
            a aVar = a.this;
            aVar.f = null;
            a.super.setVisibility(this.b);
        }

        public void c(View view) {
            a.super.setVisibility(0);
            this.a = false;
        }

        public C0008a d(v.b2 b2Var, int i) {
            a.this.f = b2Var;
            this.b = i;
            return this;
        }
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new C0008a();
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true) && typedValue.resourceId != 0) {
            this.b = new ContextThemeWrapper(context, typedValue.resourceId);
        } else {
            this.b = context;
        }
    }

    public static int d(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    public int c(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    public int e(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 + ((i3 - measuredHeight) / 2);
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        if (z) {
            return -measuredWidth;
        }
        return measuredWidth;
    }

    public v.b2 f(int i, long j) {
        v.b2 b2Var = this.f;
        if (b2Var != null) {
            b2Var.b();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            v.b2 a = v.d1.c(this).a(1.0f);
            a.d(j);
            a.f(this.a.d(a, i));
            return a;
        }
        v.b2 a2 = v.d1.c(this).a(0.0f);
        a2.d(j);
        a2.f(this.a.d(a2, i));
        return a2;
    }

    public int getAnimatedVisibility() {
        if (this.f != null) {
            return this.a.b;
        }
        return getVisibility();
    }

    public int getContentHeight() {
        return this.e;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, R$styleable.a, R$attr.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(R$styleable.ActionBar_height, 0));
        obtainStyledAttributes.recycle();
        d dVar = this.d;
        if (dVar != null) {
            dVar.w(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.h = false;
        }
        if (!this.h) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.h = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.h = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.g = false;
        }
        if (!this.g) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.g = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.g = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i);

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i != getVisibility()) {
            v.b2 b2Var = this.f;
            if (b2Var != null) {
                b2Var.b();
            }
            super.setVisibility(i);
        }
    }
}
