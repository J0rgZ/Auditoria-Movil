package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import androidx.appcompat.R$attr;
/* loaded from: classes.dex */
public class u extends RadioButton implements androidx.core.widget.f0, v.g0 {
    private final f mBackgroundTintHelper;
    private final j mCompoundButtonHelper;
    private final p0 mTextHelper;

    public u(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.radioButtonStyle);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
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

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        j jVar = this.mCompoundButtonHelper;
        if (jVar != null) {
            return jVar.b(compoundPaddingLeft);
        }
        return compoundPaddingLeft;
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

    @Override // androidx.core.widget.f0
    public ColorStateList getSupportButtonTintList() {
        j jVar = this.mCompoundButtonHelper;
        if (jVar != null) {
            return jVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        j jVar = this.mCompoundButtonHelper;
        if (jVar != null) {
            return jVar.d();
        }
        return null;
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

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        j jVar = this.mCompoundButtonHelper;
        if (jVar != null) {
            jVar.f();
        }
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

    @Override // androidx.core.widget.f0
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        j jVar = this.mCompoundButtonHelper;
        if (jVar != null) {
            jVar.g(colorStateList);
        }
    }

    @Override // androidx.core.widget.f0
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        j jVar = this.mCompoundButtonHelper;
        if (jVar != null) {
            jVar.h(mode);
        }
    }

    public u(Context context, AttributeSet attributeSet, int i) {
        super(o2.b(context), attributeSet, i);
        j jVar = new j(this);
        this.mCompoundButtonHelper = jVar;
        jVar.e(attributeSet, i);
        f fVar = new f(this);
        this.mBackgroundTintHelper = fVar;
        fVar.e(attributeSet, i);
        p0 p0Var = new p0(this);
        this.mTextHelper = p0Var;
        p0Var.m(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(b.b.d(getContext(), i));
    }
}
