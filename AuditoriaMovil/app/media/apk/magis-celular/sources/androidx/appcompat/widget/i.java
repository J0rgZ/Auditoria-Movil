package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
/* loaded from: classes.dex */
public class i extends CheckedTextView {
    public static final int[] b = {16843016};
    public final p0 a;

    public i(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843720);
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        p0 p0Var = this.a;
        if (p0Var != null) {
            p0Var.b();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return m.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(b.b.d(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.e0.q(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        p0 p0Var = this.a;
        if (p0Var != null) {
            p0Var.p(context, i);
        }
    }

    public i(Context context, AttributeSet attributeSet, int i) {
        super(o2.b(context), attributeSet, i);
        p0 p0Var = new p0(this);
        this.a = p0Var;
        p0Var.m(attributeSet, i);
        p0Var.b();
        r2 u = r2.u(getContext(), attributeSet, b, i, 0);
        setCheckMarkDrawable(u.g(0));
        u.v();
    }
}
