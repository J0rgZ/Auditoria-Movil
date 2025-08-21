package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.appcompat.widget.s1;
/* loaded from: classes.dex */
public class FitWindowsLinearLayout extends LinearLayout implements s1 {
    public s1.a a;

    public FitWindowsLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect rect) {
        s1.a aVar = this.a;
        if (aVar != null) {
            aVar.a(rect);
        }
        return super.fitSystemWindows(rect);
    }

    @Override // androidx.appcompat.widget.s1
    public void setOnFitSystemWindowsListener(s1.a aVar) {
        this.a = aVar;
    }
}
