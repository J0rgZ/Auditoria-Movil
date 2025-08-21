package androidx.work.impl;

import android.content.Context;
import androidx.work.impl.a;
import d1.k;
import d1.n;
import d1.q;
import d1.t;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import k0.e;
import n0.c;
import o0.d;
import v0.h;
/* loaded from: classes.dex */
public abstract class WorkDatabase extends e {
    public static final long l = TimeUnit.DAYS.toMillis(1);

    /* loaded from: classes.dex */
    public class a implements c.c {
        public final /* synthetic */ Context a;

        public a(Context context) {
            this.a = context;
        }

        public c a(c.b bVar) {
            c.b.a a = c.b.a(this.a);
            a.c(bVar.b).b(bVar.c).d(true);
            return new d().a(a.a());
        }
    }

    /* loaded from: classes.dex */
    public class b extends e.b {
        public void c(n0.b bVar) {
            super.c(bVar);
            bVar.beginTransaction();
            try {
                bVar.execSQL(WorkDatabase.w());
                bVar.setTransactionSuccessful();
            } finally {
                bVar.endTransaction();
            }
        }
    }

    public static WorkDatabase s(Context context, Executor executor, boolean z) {
        e.a a2;
        if (z) {
            a2 = k0.d.c(context, WorkDatabase.class).c();
        } else {
            a2 = k0.d.a(context, WorkDatabase.class, h.d());
            a2.f(new a(context));
        }
        return (WorkDatabase) a2.g(executor).a(u()).b(new l0.a[]{androidx.work.impl.a.a}).b(new l0.a[]{new a.h(context, 2, 3)}).b(new l0.a[]{androidx.work.impl.a.b}).b(new l0.a[]{androidx.work.impl.a.c}).b(new l0.a[]{new a.h(context, 5, 6)}).b(new l0.a[]{androidx.work.impl.a.d}).b(new l0.a[]{androidx.work.impl.a.e}).b(new l0.a[]{androidx.work.impl.a.f}).b(new l0.a[]{new a.i(context)}).b(new l0.a[]{new a.h(context, 10, 11)}).b(new l0.a[]{androidx.work.impl.a.g}).e().d();
    }

    public static e.b u() {
        return new b();
    }

    public static long v() {
        return System.currentTimeMillis() - l;
    }

    public static String w() {
        return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (period_start_time + minimum_retention_duration) < " + v() + " AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
    }

    public abstract n A();

    public abstract q B();

    public abstract t C();

    public abstract d1.b t();

    public abstract d1.e x();

    public abstract d1.h y();

    public abstract k z();
}
