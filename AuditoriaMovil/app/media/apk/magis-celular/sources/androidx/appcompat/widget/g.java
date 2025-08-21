package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.appcompat.R$attr;
/* loaded from: classes.dex */
public class g extends Button implements v.g0, androidx.core.widget.b {
    private final f mBackgroundTintHelper;
    private final p0 mTextHelper;

    public g(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.buttonStyle);
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

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.n(z, i, i2, i3, i4);
        }
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
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.e0.q(this, callback));
    }

    public void setSupportAllCaps(boolean z) {
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.r(z);
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

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.p(context, i);
        }
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

    public g(Context context, AttributeSet attributeSet, int i) {
        super(o2.b(context), attributeSet, i);
        f fVar = new f(this);
        this.mBackgroundTintHelper = fVar;
        fVar.e(attributeSet, i);
        p0 p0Var = new p0(this);
        this.mTextHelper = p0Var;
        p0Var.m(attributeSet, i);
        p0Var.b();
    }
}
