package androidx.work.impl.workers;

import android.content.Context;
import android.text.TextUtils;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import com.google.common.util.concurrent.ListenableFuture;
import d1.p;
import f1.d;
import java.util.Collections;
import java.util.List;
import u0.k;
import v0.j;
import z0.c;
/* loaded from: classes.dex */
public class ConstraintTrackingWorker extends ListenableWorker implements c {
    public static final String k = k.f("ConstraintTrkngWrkr");
    public WorkerParameters f;
    public final Object g;
    public volatile boolean h;
    public d i;
    public ListenableWorker j;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ConstraintTrackingWorker.this.u();
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ ListenableFuture a;

        public b(ListenableFuture listenableFuture) {
            this.a = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (ConstraintTrackingWorker.this.g) {
                if (ConstraintTrackingWorker.this.h) {
                    ConstraintTrackingWorker.this.t();
                } else {
                    ConstraintTrackingWorker.this.i.q(this.a);
                }
            }
        }
    }

    public ConstraintTrackingWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.f = workerParameters;
        this.g = new Object();
        this.h = false;
        this.i = d.s();
    }

    public void b(List list) {
        k.c().a(k, String.format("Constraints changed for %s", list), new Throwable[0]);
        synchronized (this.g) {
            this.h = true;
        }
    }

    public void e(List list) {
    }

    @Override // androidx.work.ListenableWorker
    public g1.a h() {
        return j.j(a()).o();
    }

    @Override // androidx.work.ListenableWorker
    public boolean j() {
        ListenableWorker listenableWorker = this.j;
        if (listenableWorker != null && listenableWorker.j()) {
            return true;
        }
        return false;
    }

    @Override // androidx.work.ListenableWorker
    public void m() {
        super.m();
        ListenableWorker listenableWorker = this.j;
        if (listenableWorker != null && !listenableWorker.k()) {
            this.j.q();
        }
    }

    @Override // androidx.work.ListenableWorker
    public ListenableFuture p() {
        c().execute(new a());
        return this.i;
    }

    public WorkDatabase r() {
        return j.j(a()).n();
    }

    public void s() {
        this.i.o(ListenableWorker.a.a());
    }

    public void t() {
        this.i.o(ListenableWorker.a.b());
    }

    public void u() {
        String i = g().i("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME");
        if (TextUtils.isEmpty(i)) {
            k.c().b(k, "No worker to delegate to.", new Throwable[0]);
            s();
            return;
        }
        ListenableWorker b2 = i().b(a(), i, this.f);
        this.j = b2;
        if (b2 == null) {
            k.c().a(k, "No worker to delegate to.", new Throwable[0]);
            s();
            return;
        }
        p m = r().B().m(f().toString());
        if (m == null) {
            s();
            return;
        }
        z0.d dVar = new z0.d(a(), h(), this);
        dVar.d(Collections.singletonList(m));
        if (dVar.c(f().toString())) {
            k.c().a(k, String.format("Constraints met for delegate %s", i), new Throwable[0]);
            try {
                ListenableFuture p = this.j.p();
                p.addListener(new b(p), c());
                return;
            } catch (Throwable th) {
                k c = k.c();
                String str = k;
                c.a(str, String.format("Delegated worker %s threw exception in startWork.", i), new Throwable[]{th});
                synchronized (this.g) {
                    if (this.h) {
                        k.c().a(str, "Constraints were unmet, Retrying.", new Throwable[0]);
                        t();
                    } else {
                        s();
                    }
                    return;
                }
            }
        }
        k.c().a(k, String.format("Constraints not met for delegate %s. Requesting retry.", i), new Throwable[0]);
        t();
    }
}
