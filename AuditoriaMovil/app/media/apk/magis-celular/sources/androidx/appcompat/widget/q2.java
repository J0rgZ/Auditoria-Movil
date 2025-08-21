package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class q2 extends h2 {
    public final WeakReference b;

    public q2(Context context, Resources resources) {
        super(resources);
        this.b = new WeakReference(context);
    }

    @Override // androidx.appcompat.widget.h2, android.content.res.Resources
    public Drawable getDrawable(int i) {
        Drawable drawable = super.getDrawable(i);
        Context context = (Context) this.b.get();
        if (drawable != null && context != null) {
            e2.h().x(context, i, drawable);
        }
        return drawable;
    }
}
