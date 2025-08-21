package androidx.lifecycle;

import android.os.Handler;
import androidx.lifecycle.c;
/* loaded from: classes.dex */
public class p {
    public final g a;
    public final Handler b = new Handler();
    public a c;

    /* loaded from: classes.dex */
    public static class a implements Runnable {
        public final g a;
        public final c.a b;
        public boolean c = false;

        public a(g gVar, c.a aVar) {
            this.a = gVar;
            this.b = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.c) {
                this.a.i(this.b);
                this.c = true;
            }
        }
    }

    public p(f fVar) {
        this.a = new g(fVar);
    }

    public c a() {
        return this.a;
    }

    public void b() {
        f(c.a.ON_START);
    }

    public void c() {
        f(c.a.ON_CREATE);
    }

    public void d() {
        f(c.a.ON_STOP);
        f(c.a.ON_DESTROY);
    }

    public void e() {
        f(c.a.ON_START);
    }

    public final void f(c.a aVar) {
        a aVar2 = this.c;
        if (aVar2 != null) {
            aVar2.run();
        }
        a aVar3 = new a(this.a, aVar);
        this.c = aVar3;
        this.b.postAtFrontOfQueue(aVar3);
    }
}
