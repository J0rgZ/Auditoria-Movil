package androidx.activity;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public abstract class b {
    public boolean a;
    public CopyOnWriteArrayList b = new CopyOnWriteArrayList();

    public b(boolean z) {
        this.a = z;
    }

    public void a(a aVar) {
        this.b.add(aVar);
    }

    public abstract void b();

    public final boolean c() {
        return this.a;
    }

    public final void d() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((a) it.next()).cancel();
        }
    }

    public void e(a aVar) {
        this.b.remove(aVar);
    }

    public final void f(boolean z) {
        this.a = z;
    }
}
