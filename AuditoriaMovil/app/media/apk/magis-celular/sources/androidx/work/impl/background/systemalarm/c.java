package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.work.impl.background.systemalarm.d;
import d1.p;
import e1.n;
import e1.r;
import java.util.Collections;
import java.util.List;
import u0.k;
/* loaded from: classes.dex */
public class c implements z0.c, v0.b, r.b {
    public static final String j = k.f("DelayMetCommandHandler");
    public final Context a;
    public final int b;
    public final String c;
    public final d d;
    public final z0.d e;
    public PowerManager.WakeLock h;
    public boolean i = false;
    public int g = 0;
    public final Object f = new Object();

    public c(Context context, int i, String str, d dVar) {
        this.a = context;
        this.b = i;
        this.d = dVar;
        this.c = str;
        this.e = new z0.d(context, dVar.f(), this);
    }

    public void a(String str) {
        k.c().a(j, String.format("Exceeded time limits on execution for %s", str), new Throwable[0]);
        g();
    }

    public void b(List list) {
        g();
    }

    public void c(String str, boolean z) {
        k.c().a(j, String.format("onExecuted %s, %s", str, Boolean.valueOf(z)), new Throwable[0]);
        d();
        if (z) {
            Intent f = a.f(this.a, this.c);
            d dVar = this.d;
            dVar.k(new d.b(dVar, f, this.b));
        }
        if (this.i) {
            Intent a = a.a(this.a);
            d dVar2 = this.d;
            dVar2.k(new d.b(dVar2, a, this.b));
        }
    }

    public final void d() {
        synchronized (this.f) {
            this.e.e();
            this.d.h().c(this.c);
            PowerManager.WakeLock wakeLock = this.h;
            if (wakeLock != null && wakeLock.isHeld()) {
                k.c().a(j, String.format("Releasing wakelock %s for WorkSpec %s", this.h, this.c), new Throwable[0]);
                this.h.release();
            }
        }
    }

    public void e(List list) {
        if (!list.contains(this.c)) {
            return;
        }
        synchronized (this.f) {
            if (this.g == 0) {
                this.g = 1;
                k.c().a(j, String.format("onAllConstraintsMet for %s", this.c), new Throwable[0]);
                if (this.d.e().j(this.c)) {
                    this.d.h().b(this.c, 600000L, this);
                } else {
                    d();
                }
            } else {
                k.c().a(j, String.format("Already started work for %s", this.c), new Throwable[0]);
            }
        }
    }

    public void f() {
        this.h = n.b(this.a, String.format("%s (%s)", this.c, Integer.valueOf(this.b)));
        k c = k.c();
        String str = j;
        c.a(str, String.format("Acquiring wakelock %s for WorkSpec %s", this.h, this.c), new Throwable[0]);
        this.h.acquire();
        p m = this.d.g().n().B().m(this.c);
        if (m == null) {
            g();
            return;
        }
        boolean b = m.b();
        this.i = b;
        if (!b) {
            k.c().a(str, String.format("No constraints for %s", this.c), new Throwable[0]);
            e(Collections.singletonList(this.c));
            return;
        }
        this.e.d(Collections.singletonList(m));
    }

    public final void g() {
        synchronized (this.f) {
            if (this.g < 2) {
                this.g = 2;
                k c = k.c();
                String str = j;
                c.a(str, String.format("Stopping work for WorkSpec %s", this.c), new Throwable[0]);
                Intent g = a.g(this.a, this.c);
                d dVar = this.d;
                dVar.k(new d.b(dVar, g, this.b));
                if (this.d.e().g(this.c)) {
                    k.c().a(str, String.format("WorkSpec %s needs to be rescheduled", this.c), new Throwable[0]);
                    Intent f = a.f(this.a, this.c);
                    d dVar2 = this.d;
                    dVar2.k(new d.b(dVar2, f, this.b));
                } else {
                    k.c().a(str, String.format("Processor does not have WorkSpec %s. No need to reschedule ", this.c), new Throwable[0]);
                }
            } else {
                k.c().a(j, String.format("Already stopped work for %s", this.c), new Throwable[0]);
            }
        }
    }
}
