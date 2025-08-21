package androidx.activity;

import androidx.lifecycle.c;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import java.util.ArrayDeque;
import java.util.Iterator;
/* loaded from: classes.dex */
public final class OnBackPressedDispatcher {
    public final Runnable a;
    public final ArrayDeque b = new ArrayDeque();

    /* loaded from: classes.dex */
    public class LifecycleOnBackPressedCancellable implements d, androidx.activity.a {
        public final androidx.lifecycle.c a;
        public final b b;
        public androidx.activity.a c;

        public LifecycleOnBackPressedCancellable(androidx.lifecycle.c cVar, b bVar) {
            this.a = cVar;
            this.b = bVar;
            cVar.a(this);
        }

        @Override // androidx.lifecycle.d
        public void a(f fVar, c.a aVar) {
            if (aVar == c.a.ON_START) {
                this.c = OnBackPressedDispatcher.this.b(this.b);
            } else if (aVar == c.a.ON_STOP) {
                androidx.activity.a aVar2 = this.c;
                if (aVar2 != null) {
                    aVar2.cancel();
                }
            } else if (aVar == c.a.ON_DESTROY) {
                cancel();
            }
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.a.c(this);
            this.b.e(this);
            androidx.activity.a aVar = this.c;
            if (aVar != null) {
                aVar.cancel();
                this.c = null;
            }
        }
    }

    /* loaded from: classes.dex */
    public class a implements androidx.activity.a {
        public final b a;

        public a(b bVar) {
            this.a = bVar;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.b.remove(this.a);
            this.a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.a = runnable;
    }

    public void a(f fVar, b bVar) {
        androidx.lifecycle.c lifecycle = fVar.getLifecycle();
        if (lifecycle.b() == c.b.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycle, bVar));
    }

    public androidx.activity.a b(b bVar) {
        this.b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator descendingIterator = this.b.descendingIterator();
        while (descendingIterator.hasNext()) {
            b bVar = (b) descendingIterator.next();
            if (bVar.c()) {
                bVar.b();
                return;
            }
        }
        Runnable runnable = this.a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
