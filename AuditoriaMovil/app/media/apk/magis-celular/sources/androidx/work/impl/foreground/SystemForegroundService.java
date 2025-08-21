package androidx.work.impl.foreground;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.h;
import androidx.work.impl.foreground.a;
import u0.k;
/* loaded from: classes.dex */
public class SystemForegroundService extends h implements a.b {
    public static final String f = k.f("SystemFgService");
    public static SystemForegroundService g = null;
    public Handler b;
    public boolean c;
    public androidx.work.impl.foreground.a d;
    public NotificationManager e;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ int a;
        public final /* synthetic */ Notification b;
        public final /* synthetic */ int c;

        public a(int i, Notification notification, int i2) {
            this.a = i;
            this.b = notification;
            this.c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Build.VERSION.SDK_INT >= 29) {
                c1.b.a(SystemForegroundService.this, this.a, this.b, this.c);
            } else {
                SystemForegroundService.this.startForeground(this.a, this.b);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ int a;
        public final /* synthetic */ Notification b;

        public b(int i, Notification notification) {
            this.a = i;
            this.b = notification;
        }

        @Override // java.lang.Runnable
        public void run() {
            SystemForegroundService.this.e.notify(this.a, this.b);
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public final /* synthetic */ int a;

        public c(int i) {
            this.a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            SystemForegroundService.this.e.cancel(this.a);
        }
    }

    @Override // androidx.work.impl.foreground.a.b
    public void b(int i, int i2, Notification notification) {
        this.b.post(new a(i, notification, i2));
    }

    @Override // androidx.work.impl.foreground.a.b
    public void c(int i, Notification notification) {
        this.b.post(new b(i, notification));
    }

    @Override // androidx.work.impl.foreground.a.b
    public void d(int i) {
        this.b.post(new c(i));
    }

    public final void e() {
        this.b = new Handler(Looper.getMainLooper());
        this.e = (NotificationManager) getApplicationContext().getSystemService("notification");
        androidx.work.impl.foreground.a aVar = new androidx.work.impl.foreground.a(getApplicationContext());
        this.d = aVar;
        aVar.m(this);
    }

    @Override // androidx.lifecycle.h, android.app.Service
    public void onCreate() {
        super.onCreate();
        g = this;
        e();
    }

    @Override // androidx.lifecycle.h, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.d.k();
    }

    @Override // androidx.lifecycle.h, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (this.c) {
            k.c().d(f, "Re-initializing SystemForegroundService after a request to shut-down.", new Throwable[0]);
            this.d.k();
            e();
            this.c = false;
        }
        if (intent != null) {
            this.d.l(intent);
            return 3;
        }
        return 3;
    }

    @Override // androidx.work.impl.foreground.a.b
    public void stop() {
        this.c = true;
        k.c().a(f, "All commands completed.", new Throwable[0]);
        if (Build.VERSION.SDK_INT >= 26) {
            stopForeground(true);
        }
        g = null;
        stopSelf();
    }
}
