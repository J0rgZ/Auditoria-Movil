package androidx.work;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.Keep;
import com.google.common.util.concurrent.ListenableFuture;
import f1.d;
import java.util.UUID;
import java.util.concurrent.Executor;
import u0.v;
/* loaded from: classes.dex */
public abstract class ListenableWorker {
    public Context a;
    public WorkerParameters b;
    public volatile boolean c;
    public boolean d;
    public boolean e;

    /* loaded from: classes.dex */
    public static abstract class a {

        /* renamed from: androidx.work.ListenableWorker$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0042a extends a {
            public final androidx.work.b a;

            public C0042a() {
                this(androidx.work.b.c);
            }

            public androidx.work.b e() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj != null && C0042a.class == obj.getClass()) {
                    return this.a.equals(((C0042a) obj).a);
                }
                return false;
            }

            public int hashCode() {
                return (C0042a.class.getName().hashCode() * 31) + this.a.hashCode();
            }

            public String toString() {
                return "Failure {mOutputData=" + this.a + '}';
            }

            public C0042a(androidx.work.b bVar) {
                this.a = bVar;
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends a {
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj != null && b.class == obj.getClass()) {
                    return true;
                }
                return false;
            }

            public int hashCode() {
                return b.class.getName().hashCode();
            }

            public String toString() {
                return "Retry";
            }
        }

        /* loaded from: classes.dex */
        public static final class c extends a {
            public final androidx.work.b a;

            public c() {
                this(androidx.work.b.c);
            }

            public androidx.work.b e() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj != null && c.class == obj.getClass()) {
                    return this.a.equals(((c) obj).a);
                }
                return false;
            }

            public int hashCode() {
                return (c.class.getName().hashCode() * 31) + this.a.hashCode();
            }

            public String toString() {
                return "Success {mOutputData=" + this.a + '}';
            }

            public c(androidx.work.b bVar) {
                this.a = bVar;
            }
        }

        public static a a() {
            return new C0042a();
        }

        public static a b() {
            return new b();
        }

        public static a c() {
            return new c();
        }

        public static a d(androidx.work.b bVar) {
            return new c(bVar);
        }
    }

    @Keep
    @SuppressLint({"BanKeepAnnotation"})
    public ListenableWorker(Context context, WorkerParameters workerParameters) {
        if (context != null) {
            if (workerParameters != null) {
                this.a = context;
                this.b = workerParameters;
                return;
            }
            throw new IllegalArgumentException("WorkerParameters is null");
        }
        throw new IllegalArgumentException("Application Context is null");
    }

    public final Context a() {
        return this.a;
    }

    public Executor c() {
        return this.b.a();
    }

    public ListenableFuture d() {
        d s2 = d.s();
        s2.p(new IllegalStateException("Expedited WorkRequests require a ListenableWorker to provide an implementation for `getForegroundInfoAsync()`"));
        return s2;
    }

    public final UUID f() {
        return this.b.c();
    }

    public final b g() {
        return this.b.d();
    }

    public g1.a h() {
        return this.b.e();
    }

    public v i() {
        return this.b.f();
    }

    public boolean j() {
        return this.e;
    }

    public final boolean k() {
        return this.c;
    }

    public final boolean l() {
        return this.d;
    }

    public void m() {
    }

    public void n(boolean z) {
        this.e = z;
    }

    public final void o() {
        this.d = true;
    }

    public abstract ListenableFuture p();

    public final void q() {
        this.c = true;
        m();
    }
}
