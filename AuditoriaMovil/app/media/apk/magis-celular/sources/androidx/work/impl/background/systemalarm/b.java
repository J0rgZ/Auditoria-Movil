package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import androidx.work.impl.background.systemalarm.d;
import d1.p;
import java.util.ArrayList;
import java.util.List;
import u0.k;
/* loaded from: classes.dex */
public class b {
    public static final String e = k.f("ConstraintsCmdHandler");
    public final Context a;
    public final int b;
    public final d c;
    public final z0.d d;

    public b(Context context, int i, d dVar) {
        this.a = context;
        this.b = i;
        this.c = dVar;
        this.d = new z0.d(context, dVar.f(), (z0.c) null);
    }

    public void a() {
        List<p> g = this.c.g().n().B().g();
        ConstraintProxy.a(this.a, g);
        this.d.d(g);
        ArrayList<p> arrayList = new ArrayList(g.size());
        long currentTimeMillis = System.currentTimeMillis();
        for (p pVar : g) {
            String str = pVar.a;
            if (currentTimeMillis >= pVar.a() && (!pVar.b() || this.d.c(str))) {
                arrayList.add(pVar);
            }
        }
        for (p pVar2 : arrayList) {
            String str2 = pVar2.a;
            Intent b = a.b(this.a, str2);
            k.c().a(e, String.format("Creating a delay_met command for workSpec with id (%s)", str2), new Throwable[0]);
            d dVar = this.c;
            dVar.k(new d.b(dVar, b, this.b));
        }
        this.d.e();
    }
}
