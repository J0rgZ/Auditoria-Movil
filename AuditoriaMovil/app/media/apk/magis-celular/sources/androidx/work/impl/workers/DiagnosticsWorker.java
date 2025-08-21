package androidx.work.impl.workers;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.recyclerview.widget.f;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import d1.g;
import d1.h;
import d1.p;
import d1.q;
import d1.t;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import u0.k;
import v0.j;
/* loaded from: classes.dex */
public class DiagnosticsWorker extends Worker {
    public static final String g = k.f("DiagnosticsWrkr");

    public DiagnosticsWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    public static String s(p pVar, String str, Integer num, String str2) {
        return String.format("\n%s\t %s\t %s\t %s\t %s\t %s\t", pVar.a, pVar.c, num, pVar.b.name(), str, str2);
    }

    public static String t(d1.k kVar, t tVar, h hVar, List list) {
        String str;
        Integer num;
        StringBuilder sb = new StringBuilder();
        if (Build.VERSION.SDK_INT >= 23) {
            str = "Job Id";
        } else {
            str = "Alarm Id";
        }
        sb.append(String.format("\n Id \t Class Name\t %s\t State\t Unique Name\t Tags\t", str));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            p pVar = (p) it.next();
            g c = hVar.c(pVar.a);
            if (c != null) {
                num = Integer.valueOf(c.b);
            } else {
                num = null;
            }
            sb.append(s(pVar, TextUtils.join(",", kVar.b(pVar.a)), num, TextUtils.join(",", tVar.a(pVar.a))));
        }
        return sb.toString();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.a r() {
        WorkDatabase n = j.j(a()).n();
        q B = n.B();
        d1.k z = n.z();
        t C = n.C();
        h y = n.y();
        List d = B.d(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1L));
        List i = B.i();
        List s2 = B.s((int) f.a.DEFAULT_DRAG_ANIMATION_DURATION);
        if (d != null && !d.isEmpty()) {
            k c = k.c();
            String str = g;
            c.d(str, "Recently completed work:\n\n", new Throwable[0]);
            k.c().d(str, t(z, C, y, d), new Throwable[0]);
        }
        if (i != null && !i.isEmpty()) {
            k c2 = k.c();
            String str2 = g;
            c2.d(str2, "Running work:\n\n", new Throwable[0]);
            k.c().d(str2, t(z, C, y, i), new Throwable[0]);
        }
        if (s2 != null && !s2.isEmpty()) {
            k c3 = k.c();
            String str3 = g;
            c3.d(str3, "Enqueued work:\n\n", new Throwable[0]);
            k.c().d(str3, t(z, C, y, s2), new Throwable[0]);
        }
        return ListenableWorker.a.c();
    }
}
