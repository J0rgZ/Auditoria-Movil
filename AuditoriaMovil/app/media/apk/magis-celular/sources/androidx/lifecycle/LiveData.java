package androidx.lifecycle;

import androidx.lifecycle.c;
import g.b;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class LiveData {
    public static final Object j = new Object();
    public final Object a = new Object();
    public g.b b = new g.b();
    public int c = 0;
    public volatile Object d;
    public volatile Object e;
    public int f;
    public boolean g;
    public boolean h;
    public final Runnable i;

    /* loaded from: classes.dex */
    public class LifecycleBoundObserver extends androidx.lifecycle.LiveData.b implements d {
        public final f e;

        public LifecycleBoundObserver(f fVar, l lVar) {
            super(lVar);
            this.e = fVar;
        }

        @Override // androidx.lifecycle.d
        public void a(f fVar, c.a aVar) {
            if (this.e.getLifecycle().b() == c.b.DESTROYED) {
                LiveData.this.k(this.a);
            } else {
                b(e());
            }
        }

        public void c() {
            this.e.getLifecycle().c(this);
        }

        public boolean d(f fVar) {
            if (this.e == fVar) {
                return true;
            }
            return false;
        }

        public boolean e() {
            return this.e.getLifecycle().b().a(c.b.STARTED);
        }
    }

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.a) {
                obj = LiveData.this.e;
                LiveData.this.e = LiveData.j;
            }
            LiveData.this.l(obj);
        }
    }

    /* loaded from: classes.dex */
    public abstract class b {
        public final l a;
        public boolean b;
        public int c = -1;

        public b(l lVar) {
            this.a = lVar;
        }

        public void b(boolean z) {
            boolean z2;
            if (z == this.b) {
                return;
            }
            this.b = z;
            LiveData liveData = LiveData.this;
            int i = liveData.c;
            int i2 = 1;
            if (i == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z) {
                i2 = -1;
            }
            liveData.c = i + i2;
            if (z2 && z) {
                liveData.h();
            }
            LiveData liveData2 = LiveData.this;
            if (liveData2.c == 0 && !this.b) {
                liveData2.i();
            }
            if (this.b) {
                LiveData.this.d(this);
            }
        }

        public abstract void c();

        public abstract boolean d(f fVar);

        public abstract boolean e();
    }

    public LiveData() {
        Object obj = j;
        this.e = obj;
        this.i = new a();
        this.d = obj;
        this.f = -1;
    }

    public static void b(String str) {
        if (f.a.e().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    public final void c(b bVar) {
        if (!bVar.b) {
            return;
        }
        if (!bVar.e()) {
            bVar.b(false);
            return;
        }
        int i = bVar.c;
        int i2 = this.f;
        if (i >= i2) {
            return;
        }
        bVar.c = i2;
        bVar.a.a(this.d);
    }

    public void d(b bVar) {
        if (this.g) {
            this.h = true;
            return;
        }
        this.g = true;
        do {
            this.h = false;
            if (bVar != null) {
                c(bVar);
                bVar = null;
            } else {
                b.d c = this.b.c();
                while (c.hasNext()) {
                    c((b) ((Map.Entry) c.next()).getValue());
                    if (this.h) {
                        break;
                    }
                }
            }
        } while (this.h);
        this.g = false;
    }

    public Object e() {
        Object obj = this.d;
        if (obj != j) {
            return obj;
        }
        return null;
    }

    public boolean f() {
        if (this.c > 0) {
            return true;
        }
        return false;
    }

    public void g(f fVar, l lVar) {
        b("observe");
        if (fVar.getLifecycle().b() == c.b.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(fVar, lVar);
        b bVar = (b) this.b.f(lVar, lifecycleBoundObserver);
        if (bVar != null && !bVar.d(fVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (bVar != null) {
            return;
        }
        fVar.getLifecycle().a(lifecycleBoundObserver);
    }

    public void h() {
    }

    public void i() {
    }

    public void j(Object obj) {
        boolean z;
        synchronized (this.a) {
            if (this.e == j) {
                z = true;
            } else {
                z = false;
            }
            this.e = obj;
        }
        if (!z) {
            return;
        }
        f.a.e().c(this.i);
    }

    public void k(l lVar) {
        b("removeObserver");
        b bVar = (b) this.b.g(lVar);
        if (bVar == null) {
            return;
        }
        bVar.c();
        bVar.b(false);
    }

    public void l(Object obj) {
        b("setValue");
        this.f++;
        this.d = obj;
        d(null);
    }
}
