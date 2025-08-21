package androidx.work.impl.foreground;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import androidx.work.impl.WorkDatabase;
import d1.p;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import u0.e;
import u0.k;
import v0.j;
import z0.c;
import z0.d;
/* loaded from: classes.dex */
public class a implements c, v0.b {
    public static final String k = k.f("SystemFgDispatcher");
    public Context a;
    public j b;
    public final g1.a c;
    public final Object d = new Object();
    public String e;
    public final Map f;
    public final Map g;
    public final Set h;
    public final d i;
    public b j;

    /* renamed from: androidx.work.impl.foreground.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0046a implements Runnable {
        public final /* synthetic */ WorkDatabase a;
        public final /* synthetic */ String b;

        public RunnableC0046a(WorkDatabase workDatabase, String str) {
            this.a = workDatabase;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            p m = this.a.B().m(this.b);
            if (m != null && m.b()) {
                synchronized (a.this.d) {
                    a.this.g.put(this.b, m);
                    a.this.h.add(m);
                    a aVar = a.this;
                    aVar.i.d(aVar.h);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void b(int i, int i2, Notification notification);

        void c(int i, Notification notification);

        void d(int i);

        void stop();
    }

    public a(Context context) {
        this.a = context;
        j j = j.j(context);
        this.b = j;
        g1.a o = j.o();
        this.c = o;
        this.e = null;
        this.f = new LinkedHashMap();
        this.h = new HashSet();
        this.g = new HashMap();
        this.i = new d(this.a, o, this);
        this.b.l().d(this);
    }

    public static Intent a(Context context, String str, e eVar) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_NOTIFY");
        intent.putExtra("KEY_NOTIFICATION_ID", eVar.c());
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", eVar.a());
        intent.putExtra("KEY_NOTIFICATION", eVar.b());
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    public static Intent d(Context context, String str, e eVar) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_START_FOREGROUND");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        intent.putExtra("KEY_NOTIFICATION_ID", eVar.c());
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", eVar.a());
        intent.putExtra("KEY_NOTIFICATION", eVar.b());
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    public static Intent f(Context context) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_STOP_FOREGROUND");
        return intent;
    }

    public void b(List list) {
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                k.c().a(k, String.format("Constraints unmet for WorkSpec %s", str), new Throwable[0]);
                this.b.v(str);
            }
        }
    }

    public void c(String str, boolean z) {
        boolean z2;
        Map.Entry entry;
        synchronized (this.d) {
            p pVar = (p) this.g.remove(str);
            if (pVar != null) {
                z2 = this.h.remove(pVar);
            } else {
                z2 = false;
            }
            if (z2) {
                this.i.d(this.h);
            }
        }
        e eVar = (e) this.f.remove(str);
        if (str.equals(this.e) && this.f.size() > 0) {
            Iterator it = this.f.entrySet().iterator();
            Object next = it.next();
            while (true) {
                entry = (Map.Entry) next;
                if (!it.hasNext()) {
                    break;
                }
                next = it.next();
            }
            this.e = (String) entry.getKey();
            if (this.j != null) {
                e eVar2 = (e) entry.getValue();
                this.j.b(eVar2.c(), eVar2.a(), eVar2.b());
                this.j.d(eVar2.c());
            }
        }
        b bVar = this.j;
        if (eVar != null && bVar != null) {
            k.c().a(k, String.format("Removing Notification (id: %s, workSpecId: %s ,notificationType: %s)", Integer.valueOf(eVar.c()), str, Integer.valueOf(eVar.a())), new Throwable[0]);
            bVar.d(eVar.c());
        }
    }

    public void e(List list) {
    }

    public final void g(Intent intent) {
        k.c().d(k, String.format("Stopping foreground work for %s", intent), new Throwable[0]);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        if (stringExtra != null && !TextUtils.isEmpty(stringExtra)) {
            this.b.e(UUID.fromString(stringExtra));
        }
    }

    public final void h(Intent intent) {
        int i = 0;
        int intExtra = intent.getIntExtra("KEY_NOTIFICATION_ID", 0);
        int intExtra2 = intent.getIntExtra("KEY_FOREGROUND_SERVICE_TYPE", 0);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        Notification notification = (Notification) intent.getParcelableExtra("KEY_NOTIFICATION");
        k.c().a(k, String.format("Notifying with (id: %s, workSpecId: %s, notificationType: %s)", Integer.valueOf(intExtra), stringExtra, Integer.valueOf(intExtra2)), new Throwable[0]);
        if (notification != null && this.j != null) {
            this.f.put(stringExtra, new e(intExtra, notification, intExtra2));
            if (TextUtils.isEmpty(this.e)) {
                this.e = stringExtra;
                this.j.b(intExtra, intExtra2, notification);
                return;
            }
            this.j.c(intExtra, notification);
            if (intExtra2 != 0 && Build.VERSION.SDK_INT >= 29) {
                for (Map.Entry entry : this.f.entrySet()) {
                    i |= ((e) entry.getValue()).a();
                }
                e eVar = (e) this.f.get(this.e);
                if (eVar != null) {
                    this.j.b(eVar.c(), i, eVar.b());
                }
            }
        }
    }

    public final void i(Intent intent) {
        k.c().d(k, String.format("Started foreground service %s", intent), new Throwable[0]);
        this.c.b(new RunnableC0046a(this.b.n(), intent.getStringExtra("KEY_WORKSPEC_ID")));
    }

    public void j(Intent intent) {
        k.c().d(k, "Stopping foreground service", new Throwable[0]);
        b bVar = this.j;
        if (bVar != null) {
            bVar.stop();
        }
    }

    public void k() {
        this.j = null;
        synchronized (this.d) {
            this.i.e();
        }
        this.b.l().i(this);
    }

    public void l(Intent intent) {
        String action = intent.getAction();
        if ("ACTION_START_FOREGROUND".equals(action)) {
            i(intent);
            h(intent);
        } else if ("ACTION_NOTIFY".equals(action)) {
            h(intent);
        } else if ("ACTION_CANCEL_WORK".equals(action)) {
            g(intent);
        } else if ("ACTION_STOP_FOREGROUND".equals(action)) {
            j(intent);
        }
    }

    public void m(b bVar) {
        if (this.j != null) {
            k.c().b(k, "A callback already exists.", new Throwable[0]);
        } else {
            this.j = bVar;
        }
    }
}
