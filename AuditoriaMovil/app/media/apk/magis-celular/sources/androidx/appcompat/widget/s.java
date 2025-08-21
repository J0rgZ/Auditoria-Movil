package androidx.appcompat.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.R$styleable;
/* loaded from: classes.dex */
public class s extends PopupWindow {
    public static final boolean b;
    public boolean a;

    static {
        boolean z;
        if (Build.VERSION.SDK_INT < 21) {
            z = true;
        } else {
            z = false;
        }
        b = z;
    }

    public s(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, i2);
    }

    public final void a(Context context, AttributeSet attributeSet, int i, int i2) {
        r2 u = r2.u(context, attributeSet, R$styleable.D, i, i2);
        int i3 = R$styleable.PopupWindow_overlapAnchor;
        if (u.r(i3)) {
            b(u.a(i3, false));
        }
        setBackgroundDrawable(u.g(R$styleable.PopupWindow_android_popupBackground));
        u.v();
    }

    public final void b(boolean z) {
        if (b) {
            this.a = z;
        } else {
            androidx.core.widget.t.a(this, z);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2) {
        if (b && this.a) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i, int i2, int i3, int i4) {
        if (b && this.a) {
            i2 -= view.getHeight();
        }
        super.update(view, i, i2, i3, i4);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (b && this.a) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }
}
