package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.os.Build;
import android.view.View;
import androidx.recyclerview.R$id;
import v.d1;
/* loaded from: classes.dex */
public class h implements g {
    public static final g a = new h();

    public static float e(RecyclerView recyclerView, View view) {
        int childCount = recyclerView.getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (childAt != view) {
                float u = d1.u(childAt);
                if (u > f) {
                    f = u;
                }
            }
        }
        return f;
    }

    @Override // androidx.recyclerview.widget.g
    public void a(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            int i = R$id.item_touch_helper_previous_elevation;
            Object tag = view.getTag(i);
            if (tag instanceof Float) {
                d1.s0(view, ((Float) tag).floatValue());
            }
            view.setTag(i, null);
        }
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }

    @Override // androidx.recyclerview.widget.g
    public void b(View view) {
    }

    @Override // androidx.recyclerview.widget.g
    public void c(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
    }

    @Override // androidx.recyclerview.widget.g
    public void d(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
        if (Build.VERSION.SDK_INT >= 21 && z) {
            int i2 = R$id.item_touch_helper_previous_elevation;
            if (view.getTag(i2) == null) {
                Float valueOf = Float.valueOf(d1.u(view));
                d1.s0(view, e(recyclerView, view) + 1.0f);
                view.setTag(i2, valueOf);
            }
        }
        view.setTranslationX(f);
        view.setTranslationY(f2);
    }
}
