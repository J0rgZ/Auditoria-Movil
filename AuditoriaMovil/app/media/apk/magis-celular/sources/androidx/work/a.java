package androidx.work;

import android.os.Build;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import u0.g;
import u0.i;
import u0.q;
import u0.v;
/* loaded from: classes.dex */
public final class a {
    public final Executor a;
    public final Executor b;
    public final v c;
    public final i d;
    public final q e;
    public final String f;
    public final int g;
    public final int h;
    public final int i;
    public final int j;
    public final boolean k;

    /* renamed from: androidx.work.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class ThreadFactoryC0043a implements ThreadFactory {
        public final AtomicInteger a = new AtomicInteger(0);
        public final /* synthetic */ boolean b;

        public ThreadFactoryC0043a(boolean z) {
            this.b = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            String str;
            if (this.b) {
                str = "WM.task-";
            } else {
                str = "androidx.work-";
            }
            return new Thread(runnable, str + this.a.incrementAndGet());
        }
    }

    /* loaded from: classes.dex */
    public static final class b {
        public Executor a;
        public v b;
        public i c;
        public Executor d;
        public q e;
        public String f;
        public int g = 4;
        public int h = 0;
        public int i = Integer.MAX_VALUE;
        public int j = 20;

        public a a() {
            return new a(this);
        }
    }

    public a(b bVar) {
        Executor executor = bVar.a;
        if (executor == null) {
            this.a = a(false);
        } else {
            this.a = executor;
        }
        Executor executor2 = bVar.d;
        if (executor2 == null) {
            this.k = true;
            this.b = a(true);
        } else {
            this.k = false;
            this.b = executor2;
        }
        v vVar = bVar.b;
        if (vVar == null) {
            this.c = v.c();
        } else {
            this.c = vVar;
        }
        i iVar = bVar.c;
        if (iVar == null) {
            this.d = i.c();
        } else {
            this.d = iVar;
        }
        q qVar = bVar.e;
        if (qVar == null) {
            this.e = new v0.a();
        } else {
            this.e = qVar;
        }
        this.g = bVar.g;
        this.h = bVar.h;
        this.i = bVar.i;
        this.j = bVar.j;
        this.f = bVar.f;
    }

    public final Executor a(boolean z) {
        return Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)), b(z));
    }

    public final ThreadFactory b(boolean z) {
        return new ThreadFactoryC0043a(z);
    }

    public String c() {
        return this.f;
    }

    public g d() {
        return null;
    }

    public Executor e() {
        return this.a;
    }

    public i f() {
        return this.d;
    }

    public int g() {
        return this.i;
    }

    public int h() {
        if (Build.VERSION.SDK_INT == 23) {
            return this.j / 2;
        }
        return this.j;
    }

    public int i() {
        return this.h;
    }

    public int j() {
        return this.g;
    }

    public q k() {
        return this.e;
    }

    public Executor l() {
        return this.b;
    }

    public v m() {
        return this.c;
    }
}
