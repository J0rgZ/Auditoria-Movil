package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
/* loaded from: classes.dex */
public class j {
    public final CompoundButton a;
    public ColorStateList b = null;
    public PorterDuff.Mode c = null;
    public boolean d = false;
    public boolean e = false;
    public boolean f;

    public j(CompoundButton compoundButton) {
        this.a = compoundButton;
    }

    public void a() {
        Drawable a = androidx.core.widget.g.a(this.a);
        if (a != null) {
            if (this.d || this.e) {
                Drawable mutate = m.h.r(a).mutate();
                if (this.d) {
                    m.h.o(mutate, this.b);
                }
                if (this.e) {
                    m.h.p(mutate, this.c);
                }
                if (mutate.isStateful()) {
                    mutate.setState(this.a.getDrawableState());
                }
                this.a.setButtonDrawable(mutate);
            }
        }
    }

    public int b(int i) {
        return i;
    }

    public ColorStateList c() {
        return this.b;
    }

    public PorterDuff.Mode d() {
        return this.c;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002e A[Catch: all -> 0x0075, TRY_ENTER, TryCatch #1 {all -> 0x0075, blocks: (B:3:0x000d, B:5:0x0015, B:7:0x001b, B:12:0x002e, B:14:0x0036, B:16:0x003c, B:17:0x0049, B:19:0x0051, B:20:0x005a, B:22:0x0062), top: B:30:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0051 A[Catch: all -> 0x0075, TryCatch #1 {all -> 0x0075, blocks: (B:3:0x000d, B:5:0x0015, B:7:0x001b, B:12:0x002e, B:14:0x0036, B:16:0x003c, B:17:0x0049, B:19:0x0051, B:20:0x005a, B:22:0x0062), top: B:30:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062 A[Catch: all -> 0x0075, TRY_LEAVE, TryCatch #1 {all -> 0x0075, blocks: (B:3:0x000d, B:5:0x0015, B:7:0x001b, B:12:0x002e, B:14:0x0036, B:16:0x003c, B:17:0x0049, B:19:0x0051, B:20:0x005a, B:22:0x0062), top: B:30:0x000d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(android.util.AttributeSet r4, int r5) {
        /*
            r3 = this;
            android.widget.CompoundButton r0 = r3.a
            android.content.Context r0 = r0.getContext()
            int[] r1 = androidx.appcompat.R$styleable.r
            r2 = 0
            android.content.res.TypedArray r4 = r0.obtainStyledAttributes(r4, r1, r5, r2)
            int r5 = androidx.appcompat.R$styleable.CompoundButton_buttonCompat     // Catch: java.lang.Throwable -> L75
            boolean r0 = r4.hasValue(r5)     // Catch: java.lang.Throwable -> L75
            if (r0 == 0) goto L2b
            int r5 = r4.getResourceId(r5, r2)     // Catch: java.lang.Throwable -> L75
            if (r5 == 0) goto L2b
            android.widget.CompoundButton r0 = r3.a     // Catch: android.content.res.Resources.NotFoundException -> L2a java.lang.Throwable -> L75
            android.content.Context r1 = r0.getContext()     // Catch: android.content.res.Resources.NotFoundException -> L2a java.lang.Throwable -> L75
            android.graphics.drawable.Drawable r5 = b.b.d(r1, r5)     // Catch: android.content.res.Resources.NotFoundException -> L2a java.lang.Throwable -> L75
            r0.setButtonDrawable(r5)     // Catch: android.content.res.Resources.NotFoundException -> L2a java.lang.Throwable -> L75
            r5 = 1
            goto L2c
        L2a:
        L2b:
            r5 = 0
        L2c:
            if (r5 != 0) goto L49
            int r5 = androidx.appcompat.R$styleable.CompoundButton_android_button     // Catch: java.lang.Throwable -> L75
            boolean r0 = r4.hasValue(r5)     // Catch: java.lang.Throwable -> L75
            if (r0 == 0) goto L49
            int r5 = r4.getResourceId(r5, r2)     // Catch: java.lang.Throwable -> L75
            if (r5 == 0) goto L49
            android.widget.CompoundButton r0 = r3.a     // Catch: java.lang.Throwable -> L75
            android.content.Context r1 = r0.getContext()     // Catch: java.lang.Throwable -> L75
            android.graphics.drawable.Drawable r5 = b.b.d(r1, r5)     // Catch: java.lang.Throwable -> L75
            r0.setButtonDrawable(r5)     // Catch: java.lang.Throwable -> L75
        L49:
            int r5 = androidx.appcompat.R$styleable.CompoundButton_buttonTint     // Catch: java.lang.Throwable -> L75
            boolean r0 = r4.hasValue(r5)     // Catch: java.lang.Throwable -> L75
            if (r0 == 0) goto L5a
            android.widget.CompoundButton r0 = r3.a     // Catch: java.lang.Throwable -> L75
            android.content.res.ColorStateList r5 = r4.getColorStateList(r5)     // Catch: java.lang.Throwable -> L75
            androidx.core.widget.g.c(r0, r5)     // Catch: java.lang.Throwable -> L75
        L5a:
            int r5 = androidx.appcompat.R$styleable.CompoundButton_buttonTintMode     // Catch: java.lang.Throwable -> L75
            boolean r0 = r4.hasValue(r5)     // Catch: java.lang.Throwable -> L75
            if (r0 == 0) goto L71
            android.widget.CompoundButton r0 = r3.a     // Catch: java.lang.Throwable -> L75
            r1 = -1
            int r5 = r4.getInt(r5, r1)     // Catch: java.lang.Throwable -> L75
            r1 = 0
            android.graphics.PorterDuff$Mode r5 = androidx.appcompat.widget.o1.e(r5, r1)     // Catch: java.lang.Throwable -> L75
            androidx.core.widget.g.d(r0, r5)     // Catch: java.lang.Throwable -> L75
        L71:
            r4.recycle()
            return
        L75:
            r5 = move-exception
            r4.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.j.e(android.util.AttributeSet, int):void");
    }

    public void f() {
        if (this.f) {
            this.f = false;
            return;
        }
        this.f = true;
        a();
    }

    public void g(ColorStateList colorStateList) {
        this.b = colorStateList;
        this.d = true;
        a();
    }

    public void h(PorterDuff.Mode mode) {
        this.c = mode;
        this.e = true;
        a();
    }
}
