package androidx.work.impl.background.systemalarm;

import android.content.Intent;
import androidx.lifecycle.h;
import androidx.work.impl.background.systemalarm.d;
import e1.n;
import u0.k;
/* loaded from: classes.dex */
public class SystemAlarmService extends h implements d.c {
    public static final String d = k.f("SystemAlarmService");
    public d b;
    public boolean c;

    @Override // androidx.work.impl.background.systemalarm.d.c
    public void a() {
        this.c = true;
        k.c().a(d, "All commands completed in dispatcher", new Throwable[0]);
        n.a();
        stopSelf();
    }

    public final void e() {
        d dVar = new d(this);
        this.b = dVar;
        dVar.m(this);
    }

    @Override // androidx.lifecycle.h, android.app.Service
    public void onCreate() {
        super.onCreate();
        e();
        this.c = false;
    }

    @Override // androidx.lifecycle.h, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.c = true;
        this.b.j();
    }

    @Override // androidx.lifecycle.h, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (this.c) {
            k.c().d(d, "Re-initializing SystemAlarmDispatcher after a request to shut-down.", new Throwable[0]);
            this.b.j();
            e();
            this.c = false;
        }
        if (intent != null) {
            this.b.a(intent, i2);
            return 3;
        }
        return 3;
    }
}
