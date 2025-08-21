package androidx.room;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import k0.e;
import n0.f;
/* loaded from: classes.dex */
public class c {
    public static final String[] m = {"UPDATE", "DELETE", "INSERT"};
    public final String[] b;
    public Map c;
    public final e d;
    public volatile f g;
    public b h;
    public final k0.c i;
    public androidx.room.d k;
    public AtomicBoolean e = new AtomicBoolean(false);
    public volatile boolean f = false;
    public final g.b j = new g.b();
    public Runnable l = new a();
    public final HashMap a = new HashMap();

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        public final Set a() {
            HashSet hashSet = new HashSet();
            Cursor p = c.this.d.p(new n0.a("SELECT * FROM room_table_modification_log WHERE invalidated = 1;"));
            while (p.moveToNext()) {
                try {
                    hashSet.add(Integer.valueOf(p.getInt(0)));
                } catch (Throwable th) {
                    p.close();
                    throw th;
                }
            }
            p.close();
            if (!hashSet.isEmpty()) {
                c.this.g.executeUpdateDelete();
            }
            return hashSet;
        }

        @Override // java.lang.Runnable
        public void run() {
            Lock h = c.this.d.h();
            Set set = null;
            try {
                try {
                    h.lock();
                } finally {
                    h.unlock();
                }
            } catch (SQLiteException | IllegalStateException e) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
            }
            if (!c.this.c()) {
                return;
            }
            if (!c.this.e.compareAndSet(true, false)) {
                return;
            }
            if (c.this.d.k()) {
                return;
            }
            e eVar = c.this.d;
            if (eVar.g) {
                n0.b k = eVar.i().k();
                k.beginTransaction();
                try {
                    set = a();
                    k.setTransactionSuccessful();
                    k.endTransaction();
                } catch (Throwable th) {
                    k.endTransaction();
                    throw th;
                }
            } else {
                set = a();
            }
            if (set != null && !set.isEmpty()) {
                synchronized (c.this.j) {
                    Iterator it = c.this.j.iterator();
                    while (it.hasNext()) {
                        ((d) ((Map.Entry) it.next()).getValue()).a(set);
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public final long[] a;
        public final boolean[] b;
        public final int[] c;
        public boolean d;
        public boolean e;

        public b(int i) {
            long[] jArr = new long[i];
            this.a = jArr;
            boolean[] zArr = new boolean[i];
            this.b = zArr;
            this.c = new int[i];
            Arrays.fill(jArr, 0L);
            Arrays.fill(zArr, false);
        }

        public int[] a() {
            boolean z;
            synchronized (this) {
                if (this.d && !this.e) {
                    int length = this.a.length;
                    int i = 0;
                    while (true) {
                        int i2 = 1;
                        if (i < length) {
                            if (this.a[i] > 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            boolean[] zArr = this.b;
                            if (z != zArr[i]) {
                                int[] iArr = this.c;
                                if (!z) {
                                    i2 = 2;
                                }
                                iArr[i] = i2;
                            } else {
                                this.c[i] = 0;
                            }
                            zArr[i] = z;
                            i++;
                        } else {
                            this.e = true;
                            this.d = false;
                            return this.c;
                        }
                    }
                }
                return null;
            }
        }

        public boolean b(int... iArr) {
            boolean z;
            synchronized (this) {
                z = false;
                for (int i : iArr) {
                    long[] jArr = this.a;
                    long j = jArr[i];
                    jArr[i] = 1 + j;
                    if (j == 0) {
                        z = true;
                        this.d = true;
                    }
                }
            }
            return z;
        }

        public boolean c(int... iArr) {
            boolean z;
            synchronized (this) {
                z = false;
                for (int i : iArr) {
                    long[] jArr = this.a;
                    long j = jArr[i];
                    jArr[i] = j - 1;
                    if (j == 1) {
                        z = true;
                        this.d = true;
                    }
                }
            }
            return z;
        }

        public void d() {
            synchronized (this) {
                this.e = false;
            }
        }
    }

    /* renamed from: androidx.room.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class AbstractC0035c {
        public final String[] a;

        public AbstractC0035c(String[] strArr) {
            this.a = (String[]) Arrays.copyOf(strArr, strArr.length);
        }

        public abstract boolean a();

        public abstract void b(Set set);
    }

    /* loaded from: classes.dex */
    public static class d {
        public final int[] a;
        public final String[] b;
        public final AbstractC0035c c;
        public final Set d;

        public d(AbstractC0035c abstractC0035c, int[] iArr, String[] strArr) {
            this.c = abstractC0035c;
            this.a = iArr;
            this.b = strArr;
            if (iArr.length == 1) {
                HashSet hashSet = new HashSet();
                hashSet.add(strArr[0]);
                this.d = Collections.unmodifiableSet(hashSet);
                return;
            }
            this.d = null;
        }

        public void a(Set set) {
            int length = this.a.length;
            Set set2 = null;
            for (int i = 0; i < length; i++) {
                if (set.contains(Integer.valueOf(this.a[i]))) {
                    if (length == 1) {
                        set2 = this.d;
                    } else {
                        if (set2 == null) {
                            set2 = new HashSet(length);
                        }
                        set2.add(this.b[i]);
                    }
                }
            }
            if (set2 != null) {
                this.c.b(set2);
            }
        }

        public void b(String[] strArr) {
            Set set = null;
            if (this.b.length == 1) {
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    } else if (strArr[i].equalsIgnoreCase(this.b[0])) {
                        set = this.d;
                        break;
                    } else {
                        i++;
                    }
                }
            } else {
                HashSet hashSet = new HashSet();
                for (String str : strArr) {
                    String[] strArr2 = this.b;
                    int length2 = strArr2.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 < length2) {
                            String str2 = strArr2[i2];
                            if (str2.equalsIgnoreCase(str)) {
                                hashSet.add(str2);
                                break;
                            }
                            i2++;
                        }
                    }
                }
                if (hashSet.size() > 0) {
                    set = hashSet;
                }
            }
            if (set != null) {
                this.c.b(set);
            }
        }
    }

    public c(e eVar, Map map, Map map2, String... strArr) {
        this.d = eVar;
        this.h = new b(strArr.length);
        this.c = map2;
        this.i = new k0.c(eVar);
        int length = strArr.length;
        this.b = new String[length];
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            Locale locale = Locale.US;
            String lowerCase = str.toLowerCase(locale);
            this.a.put(lowerCase, Integer.valueOf(i));
            String str2 = (String) map.get(strArr[i]);
            if (str2 != null) {
                this.b[i] = str2.toLowerCase(locale);
            } else {
                this.b[i] = lowerCase;
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            Locale locale2 = Locale.US;
            String lowerCase2 = ((String) entry.getValue()).toLowerCase(locale2);
            if (this.a.containsKey(lowerCase2)) {
                String lowerCase3 = ((String) entry.getKey()).toLowerCase(locale2);
                HashMap hashMap = this.a;
                hashMap.put(lowerCase3, hashMap.get(lowerCase2));
            }
        }
    }

    public static void b(StringBuilder sb, String str, String str2) {
        sb.append("`");
        sb.append("room_table_modification_trigger_");
        sb.append(str);
        sb.append("_");
        sb.append(str2);
        sb.append("`");
    }

    public void a(AbstractC0035c abstractC0035c) {
        d dVar;
        String[] h = h(abstractC0035c.a);
        int[] iArr = new int[h.length];
        int length = h.length;
        for (int i = 0; i < length; i++) {
            Integer num = (Integer) this.a.get(h[i].toLowerCase(Locale.US));
            if (num != null) {
                iArr[i] = num.intValue();
            } else {
                throw new IllegalArgumentException("There is no table with name " + h[i]);
            }
        }
        d dVar2 = new d(abstractC0035c, iArr, h);
        synchronized (this.j) {
            dVar = (d) this.j.f(abstractC0035c, dVar2);
        }
        if (dVar == null && this.h.b(iArr)) {
            l();
        }
    }

    public boolean c() {
        if (!this.d.o()) {
            return false;
        }
        if (!this.f) {
            this.d.i().k();
        }
        if (!this.f) {
            Log.e("ROOM", "database is not initialized even though it is open");
            return false;
        }
        return true;
    }

    public void d(n0.b bVar) {
        synchronized (this) {
            if (this.f) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            bVar.execSQL("PRAGMA temp_store = MEMORY;");
            bVar.execSQL("PRAGMA recursive_triggers='ON';");
            bVar.execSQL("CREATE TEMP TABLE room_table_modification_log(table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
            m(bVar);
            this.g = bVar.compileStatement("UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1 ");
            this.f = true;
        }
    }

    public void e(String... strArr) {
        synchronized (this.j) {
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (!((AbstractC0035c) entry.getKey()).a()) {
                    ((d) entry.getValue()).b(strArr);
                }
            }
        }
    }

    public void f() {
        if (this.e.compareAndSet(false, true)) {
            this.d.j().execute(this.l);
        }
    }

    public void g(AbstractC0035c abstractC0035c) {
        d dVar;
        synchronized (this.j) {
            dVar = (d) this.j.g(abstractC0035c);
        }
        if (dVar != null && this.h.c(dVar.a)) {
            l();
        }
    }

    public final String[] h(String[] strArr) {
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            String lowerCase = str.toLowerCase(Locale.US);
            if (this.c.containsKey(lowerCase)) {
                hashSet.addAll((Collection) this.c.get(lowerCase));
            } else {
                hashSet.add(str);
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    public void i(Context context, String str) {
        this.k = new androidx.room.d(context, str, this, this.d.j());
    }

    public final void j(n0.b bVar, int i) {
        String[] strArr;
        bVar.execSQL("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i + ", 0)");
        String str = this.b[i];
        StringBuilder sb = new StringBuilder();
        for (String str2 : m) {
            sb.setLength(0);
            sb.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            b(sb, str, str2);
            sb.append(" AFTER ");
            sb.append(str2);
            sb.append(" ON `");
            sb.append(str);
            sb.append("` BEGIN UPDATE ");
            sb.append("room_table_modification_log");
            sb.append(" SET ");
            sb.append("invalidated");
            sb.append(" = 1");
            sb.append(" WHERE ");
            sb.append("table_id");
            sb.append(" = ");
            sb.append(i);
            sb.append(" AND ");
            sb.append("invalidated");
            sb.append(" = 0");
            sb.append("; END");
            bVar.execSQL(sb.toString());
        }
    }

    public final void k(n0.b bVar, int i) {
        String[] strArr;
        String str = this.b[i];
        StringBuilder sb = new StringBuilder();
        for (String str2 : m) {
            sb.setLength(0);
            sb.append("DROP TRIGGER IF EXISTS ");
            b(sb, str, str2);
            bVar.execSQL(sb.toString());
        }
    }

    public void l() {
        if (!this.d.o()) {
            return;
        }
        m(this.d.i().k());
    }

    public void m(n0.b bVar) {
        if (bVar.q()) {
            return;
        }
        while (true) {
            try {
                Lock h = this.d.h();
                h.lock();
                try {
                    int[] a2 = this.h.a();
                    if (a2 == null) {
                        return;
                    }
                    int length = a2.length;
                    bVar.beginTransaction();
                    for (int i = 0; i < length; i++) {
                        int i2 = a2[i];
                        if (i2 != 1) {
                            if (i2 == 2) {
                                k(bVar, i);
                            }
                        } else {
                            j(bVar, i);
                        }
                    }
                    bVar.setTransactionSuccessful();
                    bVar.endTransaction();
                    this.h.d();
                } finally {
                    h.unlock();
                }
            } catch (SQLiteException | IllegalStateException e) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
                return;
            }
        }
    }
}
