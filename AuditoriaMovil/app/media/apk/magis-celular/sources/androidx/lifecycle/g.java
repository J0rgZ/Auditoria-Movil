package androidx.lifecycle;

import androidx.lifecycle.c;
import g.b;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
public class g extends c {
    public final WeakReference d;
    public g.a b = new g.a();
    public int e = 0;
    public boolean f = false;
    public boolean g = false;
    public ArrayList h = new ArrayList();
    public c.b c = c.b.INITIALIZED;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[c.b.values().length];
            b = iArr;
            try {
                iArr[c.b.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[c.b.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[c.b.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[c.b.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[c.b.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[c.a.values().length];
            a = iArr2;
            try {
                iArr2[c.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[c.a.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[c.a.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[c.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[c.a.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[c.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[c.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public c.b a;
        public d b;

        public b(e eVar, c.b bVar) {
            this.b = i.f(eVar);
            this.a = bVar;
        }

        public void a(f fVar, c.a aVar) {
            c.b h = g.h(aVar);
            this.a = g.l(this.a, h);
            this.b.a(fVar, aVar);
            this.a = h;
        }
    }

    public g(f fVar) {
        this.d = new WeakReference(fVar);
    }

    public static c.a f(c.b bVar) {
        int i = a.b[bVar.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            throw new IllegalArgumentException("Unexpected state value " + bVar);
                        }
                        throw new IllegalArgumentException();
                    }
                    return c.a.ON_PAUSE;
                }
                return c.a.ON_STOP;
            }
            return c.a.ON_DESTROY;
        }
        throw new IllegalArgumentException();
    }

    public static c.b h(c.a aVar) {
        switch (a.a[aVar.ordinal()]) {
            case 1:
            case 2:
                return c.b.CREATED;
            case 3:
            case 4:
                return c.b.STARTED;
            case 5:
                return c.b.RESUMED;
            case 6:
                return c.b.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + aVar);
        }
    }

    public static c.b l(c.b bVar, c.b bVar2) {
        if (bVar2 != null && bVar2.compareTo(bVar) < 0) {
            return bVar2;
        }
        return bVar;
    }

    public static c.a r(c.b bVar) {
        int i = a.b[bVar.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            throw new IllegalArgumentException("Unexpected state value " + bVar);
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    return c.a.ON_RESUME;
                }
            } else {
                return c.a.ON_START;
            }
        }
        return c.a.ON_CREATE;
    }

    @Override // androidx.lifecycle.c
    public void a(e eVar) {
        f fVar;
        boolean z;
        c.b bVar = this.c;
        c.b bVar2 = c.b.DESTROYED;
        if (bVar != bVar2) {
            bVar2 = c.b.INITIALIZED;
        }
        b bVar3 = new b(eVar, bVar2);
        if (((b) this.b.f(eVar, bVar3)) != null || (fVar = (f) this.d.get()) == null) {
            return;
        }
        if (this.e == 0 && !this.f) {
            z = false;
        } else {
            z = true;
        }
        c.b e = e(eVar);
        this.e++;
        while (bVar3.a.compareTo(e) < 0 && this.b.contains(eVar)) {
            o(bVar3.a);
            bVar3.a(fVar, r(bVar3.a));
            n();
            e = e(eVar);
        }
        if (!z) {
            q();
        }
        this.e--;
    }

    @Override // androidx.lifecycle.c
    public c.b b() {
        return this.c;
    }

    @Override // androidx.lifecycle.c
    public void c(e eVar) {
        this.b.g(eVar);
    }

    public final void d(f fVar) {
        Iterator descendingIterator = this.b.descendingIterator();
        while (descendingIterator.hasNext() && !this.g) {
            Map.Entry entry = (Map.Entry) descendingIterator.next();
            b bVar = (b) entry.getValue();
            while (bVar.a.compareTo(this.c) > 0 && !this.g && this.b.contains(entry.getKey())) {
                c.a f = f(bVar.a);
                o(h(f));
                bVar.a(fVar, f);
                n();
            }
        }
    }

    public final c.b e(e eVar) {
        c.b bVar;
        ArrayList arrayList;
        Map.Entry h = this.b.h(eVar);
        c.b bVar2 = null;
        if (h != null) {
            bVar = ((b) h.getValue()).a;
        } else {
            bVar = null;
        }
        if (!this.h.isEmpty()) {
            bVar2 = (c.b) this.h.get(arrayList.size() - 1);
        }
        return l(l(this.c, bVar), bVar2);
    }

    public final void g(f fVar) {
        b.d c = this.b.c();
        while (c.hasNext() && !this.g) {
            Map.Entry entry = (Map.Entry) c.next();
            b bVar = (b) entry.getValue();
            while (bVar.a.compareTo(this.c) < 0 && !this.g && this.b.contains(entry.getKey())) {
                o(bVar.a);
                bVar.a(fVar, r(bVar.a));
                n();
            }
        }
    }

    public void i(c.a aVar) {
        m(h(aVar));
    }

    public final boolean j() {
        if (this.b.size() == 0) {
            return true;
        }
        c.b bVar = ((b) this.b.a().getValue()).a;
        c.b bVar2 = ((b) this.b.d().getValue()).a;
        if (bVar == bVar2 && this.c == bVar2) {
            return true;
        }
        return false;
    }

    public void k(c.b bVar) {
        p(bVar);
    }

    public final void m(c.b bVar) {
        if (this.c == bVar) {
            return;
        }
        this.c = bVar;
        if (!this.f && this.e == 0) {
            this.f = true;
            q();
            this.f = false;
            return;
        }
        this.g = true;
    }

    public final void n() {
        ArrayList arrayList = this.h;
        arrayList.remove(arrayList.size() - 1);
    }

    public final void o(c.b bVar) {
        this.h.add(bVar);
    }

    public void p(c.b bVar) {
        m(bVar);
    }

    public final void q() {
        f fVar = (f) this.d.get();
        if (fVar != null) {
            while (!j()) {
                this.g = false;
                if (this.c.compareTo(((b) this.b.a().getValue()).a) < 0) {
                    d(fVar);
                }
                Map.Entry d = this.b.d();
                if (!this.g && d != null && this.c.compareTo(((b) d.getValue()).a) > 0) {
                    g(fVar);
                }
            }
            this.g = false;
            return;
        }
        throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
    }
}
