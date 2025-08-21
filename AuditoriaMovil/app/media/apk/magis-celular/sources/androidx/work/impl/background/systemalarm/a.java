package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemalarm.d;
import d1.p;
import java.util.HashMap;
import java.util.Map;
import u0.k;
/* loaded from: classes.dex */
public class a implements v0.b {
    public static final String d = k.f("CommandHandler");
    public final Context a;
    public final Map b = new HashMap();
    public final Object c = new Object();

    public a(Context context) {
        this.a = context;
    }

    public static Intent a(Context context) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_CONSTRAINTS_CHANGED");
        return intent;
    }

    public static Intent b(Context context, String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_DELAY_MET");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    public static Intent d(Context context, String str, boolean z) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_EXECUTION_COMPLETED");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        intent.putExtra("KEY_NEEDS_RESCHEDULE", z);
        return intent;
    }

    public static Intent e(Context context) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_RESCHEDULE");
        return intent;
    }

    public static Intent f(Context context, String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_SCHEDULE_WORK");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    public static Intent g(Context context, String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_STOP_WORK");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    public static boolean n(Bundle bundle, String... strArr) {
        if (bundle == null || bundle.isEmpty()) {
            return false;
        }
        for (String str : strArr) {
            if (bundle.get(str) == null) {
                return false;
            }
        }
        return true;
    }

    public void c(String str, boolean z) {
        synchronized (this.c) {
            v0.b bVar = (v0.b) this.b.remove(str);
            if (bVar != null) {
                bVar.c(str, z);
            }
        }
    }

    public final void h(Intent intent, int i, d dVar) {
        k.c().a(d, String.format("Handling constraints changed %s", intent), new Throwable[0]);
        new b(this.a, i, dVar).a();
    }

    public final void i(Intent intent, int i, d dVar) {
        Bundle extras = intent.getExtras();
        synchronized (this.c) {
            String string = extras.getString("KEY_WORKSPEC_ID");
            k c = k.c();
            String str = d;
            c.a(str, String.format("Handing delay met for %s", string), new Throwable[0]);
            if (!this.b.containsKey(string)) {
                c cVar = new c(this.a, i, string, dVar);
                this.b.put(string, cVar);
                cVar.f();
            } else {
                k.c().a(str, String.format("WorkSpec %s is already being handled for ACTION_DELAY_MET", string), new Throwable[0]);
            }
        }
    }

    public final void j(Intent intent, int i) {
        Bundle extras = intent.getExtras();
        String string = extras.getString("KEY_WORKSPEC_ID");
        boolean z = extras.getBoolean("KEY_NEEDS_RESCHEDULE");
        k.c().a(d, String.format("Handling onExecutionCompleted %s, %s", intent, Integer.valueOf(i)), new Throwable[0]);
        c(string, z);
    }

    public final void k(Intent intent, int i, d dVar) {
        k.c().a(d, String.format("Handling reschedule %s, %s", intent, Integer.valueOf(i)), new Throwable[0]);
        dVar.g().r();
    }

    public final void l(Intent intent, int i, d dVar) {
        String string = intent.getExtras().getString("KEY_WORKSPEC_ID");
        k c = k.c();
        String str = d;
        c.a(str, String.format("Handling schedule work for %s", string), new Throwable[0]);
        WorkDatabase n = dVar.g().n();
        n.c();
        try {
            p m = n.B().m(string);
            if (m == null) {
                k c2 = k.c();
                c2.h(str, "Skipping scheduling " + string + " because it's no longer in the DB", new Throwable[0]);
            } else if (m.b.a()) {
                k c3 = k.c();
                c3.h(str, "Skipping scheduling " + string + "because it is finished.", new Throwable[0]);
            } else {
                long a = m.a();
                if (!m.b()) {
                    k.c().a(str, String.format("Setting up Alarms for %s at %s", string, Long.valueOf(a)), new Throwable[0]);
                    x0.a.c(this.a, dVar.g(), string, a);
                } else {
                    k.c().a(str, String.format("Opportunistically setting an alarm for %s at %s", string, Long.valueOf(a)), new Throwable[0]);
                    x0.a.c(this.a, dVar.g(), string, a);
                    dVar.k(new d.b(dVar, a(this.a), i));
                }
                n.r();
            }
        } finally {
            n.g();
        }
    }

    public final void m(Intent intent, d dVar) {
        String string = intent.getExtras().getString("KEY_WORKSPEC_ID");
        k.c().a(d, String.format("Handing stopWork work for %s", string), new Throwable[0]);
        dVar.g().w(string);
        x0.a.a(this.a, dVar.g(), string);
        dVar.c(string, false);
    }

    public boolean o() {
        boolean z;
        synchronized (this.c) {
            if (!this.b.isEmpty()) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public void p(Intent intent, int i, d dVar) {
        String action = intent.getAction();
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action)) {
            h(intent, i, dVar);
        } else if ("ACTION_RESCHEDULE".equals(action)) {
            k(intent, i, dVar);
        } else if (!n(intent.getExtras(), "KEY_WORKSPEC_ID")) {
            k.c().b(d, String.format("Invalid request for %s, requires %s.", action, "KEY_WORKSPEC_ID"), new Throwable[0]);
        } else if ("ACTION_SCHEDULE_WORK".equals(action)) {
            l(intent, i, dVar);
        } else if ("ACTION_DELAY_MET".equals(action)) {
            i(intent, i, dVar);
        } else if ("ACTION_STOP_WORK".equals(action)) {
            m(intent, dVar);
        } else if ("ACTION_EXECUTION_COMPLETED".equals(action)) {
            j(intent, i);
        } else {
            k.c().h(d, String.format("Ignoring intent %s", intent), new Throwable[0]);
        }
    }
}
