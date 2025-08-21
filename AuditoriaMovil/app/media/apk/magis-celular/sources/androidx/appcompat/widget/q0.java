package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import t.o;
/* loaded from: classes.dex */
public class q0 extends TextView implements v.g0, androidx.core.widget.g0, androidx.core.widget.b {
    private final f mBackgroundTintHelper;
    private Future<t.o> mPrecomputedTextFuture;
    private final g0 mTextClassifierHelper;
    private final p0 mTextHelper;

    public q0(Context context) {
        this(context, null);
    }

    public final void c() {
        Future<t.o> future = this.mPrecomputedTextFuture;
        if (future != null) {
            try {
                this.mPrecomputedTextFuture = null;
                androidx.appcompat.app.m.a(future.get());
                androidx.core.widget.e0.n(this, null);
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.b();
        }
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (androidx.core.widget.b.P) {
            return super.getAutoSizeMaxTextSize();
        }
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            return p0Var.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (androidx.core.widget.b.P) {
            return super.getAutoSizeMinTextSize();
        }
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            return p0Var.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (androidx.core.widget.b.P) {
            return super.getAutoSizeStepGranularity();
        }
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            return p0Var.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (androidx.core.widget.b.P) {
            return super.getAutoSizeTextAvailableSizes();
        }
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            return p0Var.h();
        }
        return new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (androidx.core.widget.b.P) {
            if (super.getAutoSizeTextType() != 1) {
                return 0;
            }
            return 1;
        }
        p0 p0Var = this.mTextHelper;
        if (p0Var == null) {
            return 0;
        }
        return p0Var.i();
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return androidx.core.widget.e0.b(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return androidx.core.widget.e0.c(this);
    }

    public ColorStateList getSupportBackgroundTintList() {
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            return fVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            return fVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.mTextHelper.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.mTextHelper.k();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        c();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        g0 g0Var;
        if (Build.VERSION.SDK_INT < 28 && (g0Var = this.mTextClassifierHelper) != null) {
            return g0Var.a();
        }
        return super.getTextClassifier();
    }

    public o.a getTextMetricsParamsCompat() {
        return androidx.core.widget.e0.g(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return m.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.n(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        c();
        super.onMeasure(i, i2);
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        p0 p0Var = this.mTextHelper;
        if (p0Var != null && !androidx.core.widget.b.P && p0Var.l()) {
            this.mTextHelper.c();
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (androidx.core.widget.b.P) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.s(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (androidx.core.widget.b.P) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.t(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (androidx.core.widget.b.P) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.u(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.g(i);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.o();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.o();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.o();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.o();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.e0.q(this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            androidx.core.widget.e0.k(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            androidx.core.widget.e0.l(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i) {
        androidx.core.widget.e0.m(this, i);
    }

    public void setPrecomputedText(t.o oVar) {
        androidx.core.widget.e0.n(this, oVar);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.j(mode);
        }
    }

    @Override // androidx.core.widget.g0
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.mTextHelper.v(colorStateList);
        this.mTextHelper.b();
    }

    @Override // androidx.core.widget.g0
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.mTextHelper.w(mode);
        this.mTextHelper.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.p(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        g0 g0Var;
        if (Build.VERSION.SDK_INT < 28 && (g0Var = this.mTextClassifierHelper) != null) {
            g0Var.b(textClassifier);
        } else {
            super.setTextClassifier(textClassifier);
        }
    }

    public void setTextFuture(Future<t.o> future) {
        this.mPrecomputedTextFuture = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(o.a aVar) {
        androidx.core.widget.e0.p(this, aVar);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        if (androidx.core.widget.b.P) {
            super.setTextSize(i, f);
            return;
        }
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.z(i, f);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i) {
        Typeface typeface2;
        if (typeface != null && i > 0) {
            typeface2 = l.e.a(getContext(), typeface, i);
        } else {
            typeface2 = null;
        }
        if (typeface2 != null) {
            typeface = typeface2;
        }
        super.setTypeface(typeface, i);
    }

    public q0(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public q0(Context context, AttributeSet attributeSet, int i) {
        super(o2.b(context), attributeSet, i);
        f fVar = new f(this);
        this.mBackgroundTintHelper = fVar;
        fVar.e(attributeSet, i);
        p0 p0Var = new p0(this);
        this.mTextHelper = p0Var;
        p0Var.m(attributeSet, i);
        p0Var.b();
        this.mTextClassifierHelper = new g0(this);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i != 0 ? b.b.d(context, i) : null, i2 != 0 ? b.b.d(context, i2) : null, i3 != 0 ? b.b.d(context, i3) : null, i4 != 0 ? b.b.d(context, i4) : null);
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.o();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i != 0 ? b.b.d(context, i) : null, i2 != 0 ? b.b.d(context, i2) : null, i3 != 0 ? b.b.d(context, i3) : null, i4 != 0 ? b.b.d(context, i4) : null);
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.o();
        }
    }
}
