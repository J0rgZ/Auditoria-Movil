package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.c;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
/* loaded from: classes.dex */
class FragmentStateAdapter$5 implements d {
    public final /* synthetic */ Handler a;
    public final /* synthetic */ Runnable b;

    @Override // androidx.lifecycle.d
    public void a(f fVar, c.a aVar) {
        if (aVar == c.a.ON_DESTROY) {
            this.a.removeCallbacks(this.b);
            fVar.getLifecycle().c(this);
        }
    }
}
