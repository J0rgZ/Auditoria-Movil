package androidx.work;

import android.content.Context;
import androidx.work.ListenableWorker;
import com.google.common.util.concurrent.ListenableFuture;
import f1.d;
import g8.b0;
import g8.b1;
import g8.e0;
import g8.f;
import g8.f0;
import g8.f1;
import g8.g0;
import g8.m0;
import g8.t;
import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import l7.m;
import q7.j;
import w7.p;
import x7.g;
import x7.i;
/* loaded from: classes.dex */
public abstract class CoroutineWorker extends ListenableWorker {
    public final t f;
    public final d g;
    public final b0 h;

    /* loaded from: classes.dex */
    public static final class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (CoroutineWorker.this.v().isCancelled()) {
                b1.a.a(CoroutineWorker.this.w(), (CancellationException) null, 1, (Object) null);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends j implements p {
        public Object b;
        public int c;
        public final /* synthetic */ u0.j d;
        public final /* synthetic */ CoroutineWorker e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(u0.j jVar, CoroutineWorker coroutineWorker, Continuation continuation) {
            super(2, continuation);
            this.d = jVar;
            this.e = coroutineWorker;
        }

        /* renamed from: a */
        public final Object invoke(e0 e0Var, Continuation continuation) {
            return create(e0Var, continuation).invokeSuspend(l7.t.a);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new b(this.d, this.e, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            u0.j jVar;
            Object c = p7.c.c();
            int i = this.c;
            if (i != 0) {
                if (i == 1) {
                    jVar = (u0.j) this.b;
                    m.b(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } else {
                m.b(obj);
                u0.j jVar2 = this.d;
                CoroutineWorker coroutineWorker = this.e;
                this.b = jVar2;
                this.c = 1;
                Object t = coroutineWorker.t(this);
                if (t == c) {
                    return c;
                }
                jVar = jVar2;
                obj = t;
            }
            jVar.b(obj);
            return l7.t.a;
        }
    }

    /* loaded from: classes.dex */
    public static final class c extends j implements p {
        public int b;

        public c(Continuation continuation) {
            super(2, continuation);
        }

        /* renamed from: a */
        public final Object invoke(e0 e0Var, Continuation continuation) {
            return create(e0Var, continuation).invokeSuspend(l7.t.a);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new c(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object c = p7.c.c();
            int i = this.b;
            try {
                if (i != 0) {
                    if (i == 1) {
                        m.b(obj);
                    } else {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                } else {
                    m.b(obj);
                    CoroutineWorker coroutineWorker = CoroutineWorker.this;
                    this.b = 1;
                    obj = coroutineWorker.r(this);
                    if (obj == c) {
                        return c;
                    }
                }
                CoroutineWorker.this.v().o((ListenableWorker.a) obj);
            } catch (Throwable th) {
                CoroutineWorker.this.v().p(th);
            }
            return l7.t.a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        i.g(context, "appContext");
        i.g(workerParameters, "params");
        this.f = f1.b((b1) null, 1, (Object) null);
        d s2 = d.s();
        i.f(s2, "create()");
        this.g = s2;
        s2.addListener(new a(), h().c());
        this.h = m0.a();
    }

    public static /* synthetic */ Object u(CoroutineWorker coroutineWorker, Continuation continuation) {
        throw new IllegalStateException("Not implemented");
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture d() {
        t b2 = f1.b((b1) null, 1, (Object) null);
        e0 a2 = f0.a(s().plus(b2));
        u0.j jVar = new u0.j(b2, (d) null, 2, (g) null);
        f.b(a2, (o7.f) null, (g0) null, new b(jVar, this, null), 3, (Object) null);
        return jVar;
    }

    @Override // androidx.work.ListenableWorker
    public final void m() {
        super.m();
        this.g.cancel(false);
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture p() {
        f.b(f0.a(s().plus(this.f)), (o7.f) null, (g0) null, new c(null), 3, (Object) null);
        return this.g;
    }

    public abstract Object r(Continuation continuation);

    public b0 s() {
        return this.h;
    }

    public Object t(Continuation continuation) {
        return u(this, continuation);
    }

    public final d v() {
        return this.g;
    }

    public final t w() {
        return this.f;
    }
}
