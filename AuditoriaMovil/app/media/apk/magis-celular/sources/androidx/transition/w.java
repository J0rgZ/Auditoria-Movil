package androidx.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
/* loaded from: classes.dex */
public class w implements x {
    public final ViewGroupOverlay a;

    public w(ViewGroup viewGroup) {
        this.a = viewGroup.getOverlay();
    }

    @Override // androidx.transition.b0
    public void a(Drawable drawable) {
        this.a.add(drawable);
    }

    @Override // androidx.transition.b0
    public void b(Drawable drawable) {
        this.a.remove(drawable);
    }

    @Override // androidx.transition.x
    public void c(View view) {
        this.a.add(view);
    }

    @Override // androidx.transition.x
    public void d(View view) {
        this.a.remove(view);
    }
}
