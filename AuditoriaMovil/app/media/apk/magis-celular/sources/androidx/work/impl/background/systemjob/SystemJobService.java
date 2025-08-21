package androidx.work.impl.background.systemjob;

import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.PersistableBundle;
import android.text.TextUtils;
import androidx.work.WorkerParameters;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import u0.k;
import v0.b;
import v0.j;
import y0.b0;
import y0.c0;
import y0.g0;
import y0.h0;
import y0.i0;
import y0.j0;
import y0.k0;
import y0.l0;
import y0.m0;
import y0.n0;
/* loaded from: classes.dex */
public class SystemJobService extends JobService implements b {
    public static final String c = k.f("SystemJobService");
    public j a;
    public final Map b = new HashMap();

    public static String a(JobParameters jobParameters) {
        try {
            PersistableBundle a = l0.a(jobParameters);
            if (a != null && b0.a(a, "EXTRA_WORK_SPEC_ID")) {
                return c0.a(a, "EXTRA_WORK_SPEC_ID");
            }
            return null;
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public void c(String str, boolean z) {
        JobParameters a;
        k.c().a(c, String.format("%s executed on JobScheduler", str), new Throwable[0]);
        synchronized (this.b) {
            a = g0.a(this.b.remove(str));
        }
        if (a != null) {
            h0.a(this, a, z);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            j j = j.j(m0.a(this));
            this.a = j;
            j.l().d(this);
        } catch (IllegalStateException unused) {
            if (Application.class.equals(n0.a(this).getClass())) {
                k.c().h(c, "Could not find WorkManager instance; this may be because an auto-backup is in progress. Ignoring JobScheduler commands for now. Please make sure that you are initializing WorkManager if you have manually disabled WorkManagerInitializer.", new Throwable[0]);
                return;
            }
            throw new IllegalStateException("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().");
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        j jVar = this.a;
        if (jVar != null) {
            jVar.l().i(this);
        }
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        WorkerParameters.a aVar;
        if (this.a == null) {
            k.c().a(c, "WorkManager is not initialized; requesting retry.", new Throwable[0]);
            h0.a(this, jobParameters, true);
            return false;
        }
        String a = a(jobParameters);
        if (TextUtils.isEmpty(a)) {
            k.c().b(c, "WorkSpec id not found!", new Throwable[0]);
            return false;
        }
        synchronized (this.b) {
            if (this.b.containsKey(a)) {
                k.c().a(c, String.format("Job is already being executed by SystemJobService: %s", a), new Throwable[0]);
                return false;
            }
            k.c().a(c, String.format("onStartJob for %s", a), new Throwable[0]);
            this.b.put(a, jobParameters);
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                aVar = new WorkerParameters.a();
                if (i0.a(jobParameters) != null) {
                    aVar.b = Arrays.asList(i0.a(jobParameters));
                }
                if (j0.a(jobParameters) != null) {
                    aVar.a = Arrays.asList(j0.a(jobParameters));
                }
                if (i >= 28) {
                    aVar.c = k0.a(jobParameters);
                }
            } else {
                aVar = null;
            }
            this.a.u(a, aVar);
            return true;
        }
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        if (this.a == null) {
            k.c().a(c, "WorkManager is not initialized; requesting retry.", new Throwable[0]);
            return true;
        }
        String a = a(jobParameters);
        if (TextUtils.isEmpty(a)) {
            k.c().b(c, "WorkSpec id not found!", new Throwable[0]);
            return false;
        }
        k.c().a(c, String.format("onStopJob for %s", a), new Throwable[0]);
        synchronized (this.b) {
            this.b.remove(a);
        }
        this.a.w(a);
        return !this.a.l().f(a);
    }
}
