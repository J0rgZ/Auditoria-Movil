package androidx.work.impl.utils;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Build;
import android.text.TextUtils;
import androidx.work.impl.WorkDatabase;
import d1.n;
import d1.p;
import d1.q;
import e1.c;
import e1.d;
import e1.e;
import java.util.List;
import java.util.concurrent.TimeUnit;
import r.a;
import u0.k;
import u0.s;
import v0.f;
import v0.h;
import v0.j;
import y0.f0;
/* loaded from: classes.dex */
public class ForceStopRunnable implements Runnable {
    public static final String d = k.f("ForceStopRunnable");
    public static final long e = TimeUnit.DAYS.toMillis(3650);
    public final Context a;
    public final j b;
    public int c = 0;

    /* loaded from: classes.dex */
    public static class BroadcastReceiver extends android.content.BroadcastReceiver {
        public static final String a = k.f("ForceStopRunnable$Rcvr");

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && "ACTION_FORCE_STOP_RESCHEDULE".equals(intent.getAction())) {
                k.c().g(a, "Rescheduling alarm that keeps track of force-stops.", new Throwable[0]);
                ForceStopRunnable.g(context);
            }
        }
    }

    public ForceStopRunnable(Context context, j jVar) {
        this.a = context.getApplicationContext();
        this.b = jVar;
    }

    public static Intent c(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return intent;
    }

    public static PendingIntent d(Context context, int i) {
        return PendingIntent.getBroadcast(context, -1, c(context), i);
    }

    public static void g(Context context) {
        int i;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (a.d()) {
            i = 167772160;
        } else {
            i = 134217728;
        }
        PendingIntent d2 = d(context, i);
        long currentTimeMillis = System.currentTimeMillis() + e;
        if (alarmManager != null) {
            alarmManager.setExact(0, currentTimeMillis, d2);
        }
    }

    public boolean a() {
        boolean z;
        boolean z2;
        if (Build.VERSION.SDK_INT >= 23) {
            z = f0.h(this.a, this.b);
        } else {
            z = false;
        }
        WorkDatabase n = this.b.n();
        q B = n.B();
        n A = n.A();
        n.c();
        try {
            List<p> i = B.i();
            if (i != null && !i.isEmpty()) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                for (p pVar : i) {
                    B.f(s.a, new String[]{pVar.a});
                    B.b(pVar.a, -1L);
                }
            }
            A.b();
            n.r();
            if (!z2 && !z) {
                return false;
            }
            return true;
        } finally {
            n.g();
        }
    }

    public void b() {
        boolean a = a();
        if (h()) {
            k.c().a(d, "Rescheduling Workers.", new Throwable[0]);
            this.b.r();
            this.b.k().c(false);
        } else if (e()) {
            k.c().a(d, "Application was force-stopped, rescheduling.", new Throwable[0]);
            this.b.r();
        } else if (a) {
            k.c().a(d, "Found unfinished work, scheduling it.", new Throwable[0]);
            f.b(this.b.h(), this.b.n(), this.b.m());
        }
    }

    public boolean e() {
        int i;
        try {
            if (a.d()) {
                i = 570425344;
            } else {
                i = 536870912;
            }
            PendingIntent d2 = d(this.a, i);
            if (Build.VERSION.SDK_INT >= 30) {
                if (d2 != null) {
                    d2.cancel();
                }
                List a = c.a((ActivityManager) this.a.getSystemService("activity"), (String) null, 0, 0);
                if (a != null && !a.isEmpty()) {
                    for (int i2 = 0; i2 < a.size(); i2++) {
                        if (e.a(d.a(a.get(i2))) == 10) {
                            return true;
                        }
                    }
                }
            } else if (d2 == null) {
                g(this.a);
                return true;
            }
            return false;
        } catch (IllegalArgumentException | SecurityException e2) {
            k.c().h(d, "Ignoring exception", new Throwable[]{e2});
            return true;
        }
    }

    public boolean f() {
        androidx.work.a h = this.b.h();
        if (TextUtils.isEmpty(h.c())) {
            k.c().a(d, "The default process name was not specified.", new Throwable[0]);
            return true;
        }
        boolean b = e1.j.b(this.a, h);
        k.c().a(d, String.format("Is default app process = %s", Boolean.valueOf(b)), new Throwable[0]);
        return b;
    }

    public boolean h() {
        return this.b.k().a();
    }

    public void i(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        int i;
        try {
            if (!f()) {
                return;
            }
            while (true) {
                h.e(this.a);
                k.c().a(d, "Performing cleanup operations.", new Throwable[0]);
                try {
                    b();
                    return;
                } catch (SQLiteAccessPermException | SQLiteCantOpenDatabaseException | SQLiteConstraintException | SQLiteDatabaseCorruptException | SQLiteDatabaseLockedException | SQLiteTableLockedException e2) {
                    i = this.c + 1;
                    this.c = i;
                    if (i < 3) {
                        k.c().a(d, String.format("Retrying after %s", Long.valueOf(i * 300)), new Throwable[]{e2});
                        i(this.c * 300);
                    } else {
                        k.c().b(d, "The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.", new Throwable[]{e2});
                        IllegalStateException illegalStateException = new IllegalStateException("The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.", e2);
                        this.b.h().d();
                        throw illegalStateException;
                    }
                }
                k.c().a(d, String.format("Retrying after %s", Long.valueOf(i * 300)), new Throwable[]{e2});
                i(this.c * 300);
            }
        } finally {
            this.b.q();
        }
    }
}
