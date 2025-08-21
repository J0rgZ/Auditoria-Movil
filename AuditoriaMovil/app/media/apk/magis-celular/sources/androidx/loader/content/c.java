package androidx.loader.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class c {
    public static final ThreadFactory f;
    public static final BlockingQueue g;
    public static final Executor h;
    public static f i;
    public static volatile Executor j;
    public final h a;
    public final FutureTask b;
    public volatile g c = g.PENDING;
    public final AtomicBoolean d = new AtomicBoolean();
    public final AtomicBoolean e = new AtomicBoolean();

    /* loaded from: classes.dex */
    public static class a implements ThreadFactory {
        public final AtomicInteger a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "ModernAsyncTask #" + this.a.getAndIncrement());
        }
    }

    /* loaded from: classes.dex */
    public class b extends h {
        public b() {
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            c.this.e.set(true);
            Object obj = null;
            try {
                Process.setThreadPriority(10);
                obj = c.this.b(this.a);
                Binder.flushPendingCommands();
                return obj;
            } finally {
            }
        }
    }

    /* renamed from: androidx.loader.content.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0021c extends FutureTask {
        public C0021c(Callable callable) {
            super(callable);
        }

        @Override // java.util.concurrent.FutureTask
        public void done() {
            try {
                c.this.l(get());
            } catch (InterruptedException unused) {
            } catch (CancellationException unused2) {
                c.this.l(null);
            } catch (ExecutionException e) {
                throw new RuntimeException("An error occurred while executing doInBackground()", e.getCause());
            } catch (Throwable th) {
                throw new RuntimeException("An error occurred while executing doInBackground()", th);
            }
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class d {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[g.values().length];
            a = iArr;
            try {
                iArr[g.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[g.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class e {
        public final c a;
        public final Object[] b;

        public e(c cVar, Object... objArr) {
            this.a = cVar;
            this.b = objArr;
        }
    }

    /* loaded from: classes.dex */
    public static class f extends Handler {
        public f() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            e eVar = (e) message.obj;
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    eVar.a.j(eVar.b);
                    return;
                }
                return;
            }
            eVar.a.d(eVar.b[0]);
        }
    }

    /* loaded from: classes.dex */
    public enum g {
        PENDING,
        RUNNING,
        FINISHED
    }

    /* loaded from: classes.dex */
    public static abstract class h implements Callable {
        public Object[] a;
    }

    static {
        a aVar = new a();
        f = aVar;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(10);
        g = linkedBlockingQueue;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, linkedBlockingQueue, aVar);
        h = threadPoolExecutor;
        j = threadPoolExecutor;
    }

    public c() {
        b bVar = new b();
        this.a = bVar;
        this.b = new C0021c(bVar);
    }

    public static Handler e() {
        f fVar;
        synchronized (c.class) {
            if (i == null) {
                i = new f();
            }
            fVar = i;
        }
        return fVar;
    }

    public final boolean a(boolean z) {
        this.d.set(true);
        return this.b.cancel(z);
    }

    public abstract Object b(Object... objArr);

    public final c c(Executor executor, Object... objArr) {
        if (this.c != g.PENDING) {
            int i2 = d.a[this.c.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    throw new IllegalStateException("We should never reach this state");
                }
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
            throw new IllegalStateException("Cannot execute task: the task is already running.");
        }
        this.c = g.RUNNING;
        i();
        this.a.a = objArr;
        executor.execute(this.b);
        return this;
    }

    public void d(Object obj) {
        if (f()) {
            g(obj);
        } else {
            h(obj);
        }
        this.c = g.FINISHED;
    }

    public final boolean f() {
        return this.d.get();
    }

    public abstract void g(Object obj);

    public abstract void h(Object obj);

    public void i() {
    }

    public void j(Object... objArr) {
    }

    public Object k(Object obj) {
        e().obtainMessage(1, new e(this, obj)).sendToTarget();
        return obj;
    }

    public void l(Object obj) {
        if (!this.e.get()) {
            k(obj);
        }
    }
}
