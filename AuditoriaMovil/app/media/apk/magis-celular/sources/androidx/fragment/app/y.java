package androidx.fragment.app;

import androidx.lifecycle.c;
/* loaded from: classes.dex */
public class y implements androidx.lifecycle.f {
    public androidx.lifecycle.g a = null;

    public void a(c.a aVar) {
        this.a.i(aVar);
    }

    public void b() {
        if (this.a == null) {
            this.a = new androidx.lifecycle.g(this);
        }
    }

    public boolean c() {
        if (this.a != null) {
            return true;
        }
        return false;
    }

    @Override // androidx.lifecycle.f
    public androidx.lifecycle.c getLifecycle() {
        b();
        return this.a;
    }
}
