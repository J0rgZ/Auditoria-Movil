package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import e1.n;
import e1.r;
import java.util.ArrayList;
import java.util.List;
import u0.k;
import v0.j;
/* loaded from: classes.dex */
public class d implements v0.b {
    public static final String k = k.f("SystemAlarmDispatcher");
    public final Context a;
    public final g1.a b;
    public final r c;
    public final v0.d d;
    public final j e;
    public final androidx.work.impl.background.systemalarm.a f;
    public final Handler g;
    public final List h;
    public Intent i;
    public c j;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d dVar;
            RunnableC0045d runnableC0045d;
            synchronized (d.this.h) {
                d dVar2 = d.this;
                dVar2.i = (Intent) dVar2.h.get(0);
            }
            Intent intent = d.this.i;
            if (intent != null) {
                String action = intent.getAction();
                int intExtra = d.this.i.getIntExtra("KEY_START_ID", 0);
                k c = k.c();
                String str = d.k;
                c.a(str, String.format("Processing command %s, %s", d.this.i, Integer.valueOf(intExtra)), new Throwable[0]);
                PowerManager.WakeLock b = n.b(d.this.a, String.format("%s (%s)", action, Integer.valueOf(intExtra)));
                try {
                    k.c().a(str, String.format("Acquiring operation wake lock (%s) %s", action, b), new Throwable[0]);
                    b.acquire();
                    d dVar3 = d.this;
                    dVar3.f.p(dVar3.i, intExtra, dVar3);
                    k.c().a(str, String.format("Releasing operation wake lock (%s) %s", action, b), new Throwable[0]);
                    b.release();
                    dVar = d.this;
                    runnableC0045d = new RunnableC0045d(dVar);
                } catch (Throwable th) {
                    try {
                        k c2 = k.c();
                        String str2 = d.k;
                        c2.b(str2, "Unexpected error in onHandleIntent", new Throwable[]{th});
                        k.c().a(str2, String.format("Releasing operation wake lock (%s) %s", action, b), new Throwable[0]);
                        b.release();
                        dVar = d.this;
                        runnableC0045d = new RunnableC0045d(dVar);
                    } catch (Throwable th2) {
                        k.c().a(d.k, String.format("Releasing operation wake lock (%s) %s", action, b), new Throwable[0]);
                        b.release();
                        d dVar4 = d.this;
                        dVar4.k(new RunnableC0045d(dVar4));
                        throw th2;
                    }
                }
                dVar.k(runnableC0045d);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Runnable {
        public final d a;
        public final Intent b;
        public final int c;

        public b(d dVar, Intent intent, int i) {
            this.a = dVar;
            this.b = intent;
            this.c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b, this.c);
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        void a();
    }

    /* renamed from: androidx.work.impl.background.systemalarm.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class RunnableC0045d implements Runnable {
        public final d a;

        public RunnableC0045d(d dVar) {
            this.a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.d();
        }
    }

    public d(Context context) {
        this(context, null, null);
    }

    public boolean a(Intent intent, int i) {
        k c2 = k.c();
        String str = k;
        boolean z = false;
        c2.a(str, String.format("Adding command %s (%s)", intent, Integer.valueOf(i)), new Throwable[0]);
        b();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            k.c().h(str, "Unknown command. Ignoring", new Throwable[0]);
            return false;
        } else if ("ACTION_CONSTRAINTS_CHANGED".equals(action) && i("ACTION_CONSTRAINTS_CHANGED")) {
            return false;
        } else {
            intent.putExtra("KEY_START_ID", i);
            synchronized (this.h) {
                if (!this.h.isEmpty()) {
                    z = true;
                }
                this.h.add(intent);
                if (!z) {
                    l();
                }
            }
            return true;
        }
    }

    public final void b() {
        if (this.g.getLooper().getThread() == Thread.currentThread()) {
            return;
        }
        throw new IllegalStateException("Needs to be invoked on the main thread.");
    }

    public void c(String str, boolean z) {
        k(new b(this, androidx.work.impl.background.systemalarm.a.d(this.a, str, z), 0));
    }

    public void d() {
        k c2 = k.c();
        String str = k;
        c2.a(str, "Checking if commands are complete.", new Throwable[0]);
        b();
        synchronized (this.h) {
            if (this.i != null) {
                k.c().a(str, String.format("Removing command %s", this.i), new Throwable[0]);
                if (((Intent) this.h.remove(0)).equals(this.i)) {
                    this.i = null;
                } else {
                    throw new IllegalStateException("Dequeue-d command is not the first.");
                }
            }
            e1.k c3 = this.b.c();
            if (!this.f.o() && this.h.isEmpty() && !c3.a()) {
                k.c().a(str, "No more commands & intents.", new Throwable[0]);
                c cVar = this.j;
                if (cVar != null) {
                    cVar.a();
                }
            } else if (!this.h.isEmpty()) {
                l();
            }
        }
    }

    public v0.d e() {
        return this.d;
    }

    public g1.a f() {
        return this.b;
    }

    public j g() {
        return this.e;
    }

    public r h() {
        return this.c;
    }

    public final boolean i(String str) {
        b();
        synchronized (this.h) {
            for (Intent intent : this.h) {
                if (str.equals(intent.getAction())) {
                    return true;
                }
            }
            return false;
        }
    }

    public void j() {
        k.c().a(k, "Destroying SystemAlarmDispatcher", new Throwable[0]);
        this.d.i(this);
        this.c.a();
        this.j = null;
    }

    public void k(Runnable runnable) {
        this.g.post(runnable);
    }

    public final void l() {
        b();
        PowerManager.WakeLock b2 = n.b(this.a, "ProcessCommand");
        try {
            b2.acquire();
            this.e.o().b(new a());
        } finally {
            b2.release();
        }
    }

    public void m(c cVar) {
        if (this.j != null) {
            k.c().b(k, "A completion listener for SystemAlarmDispatcher already exists.", new Throwable[0]);
        } else {
            this.j = cVar;
        }
    }

    public d(Context context, v0.d dVar, j jVar) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.f = new androidx.work.impl.background.systemalarm.a(applicationContext);
        this.c = new r();
        jVar = jVar == null ? j.j(context) : jVar;
        this.e = jVar;
        dVar = dVar == null ? jVar.l() : dVar;
        this.d = dVar;
        this.b = jVar.o();
        dVar.d(this);
        this.h = new ArrayList();
        this.i = null;
        this.g = new Handler(Looper.getMainLooper());
    }
}
