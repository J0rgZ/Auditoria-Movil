package androidx.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
/* loaded from: classes.dex */
public abstract class h extends Service implements f {
    public final p a = new p(this);

    @Override // androidx.lifecycle.f
    public c getLifecycle() {
        return this.a.a();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        this.a.b();
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.a.c();
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.a.d();
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        this.a.e();
        super.onStart(intent, i);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
