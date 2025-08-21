package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public class t {
    public final androidx.collection.g a = new androidx.collection.g();
    public final androidx.collection.d b = new androidx.collection.d();

    /* loaded from: classes.dex */
    public static class a {
        public static u.f d = new u.g(20);
        public int a;
        public RecyclerView.l.b b;
        public RecyclerView.l.b c;

        public static void a() {
            do {
            } while (d.acquire() != null);
        }

        public static a b() {
            a aVar = (a) d.acquire();
            if (aVar == null) {
                return new a();
            }
            return aVar;
        }

        public static void c(a aVar) {
            aVar.a = 0;
            aVar.b = null;
            aVar.c = null;
            d.release(aVar);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(RecyclerView.d0 d0Var);

        void b(RecyclerView.d0 d0Var, RecyclerView.l.b bVar, RecyclerView.l.b bVar2);

        void c(RecyclerView.d0 d0Var, RecyclerView.l.b bVar, RecyclerView.l.b bVar2);

        void d(RecyclerView.d0 d0Var, RecyclerView.l.b bVar, RecyclerView.l.b bVar2);
    }

    public void a(RecyclerView.d0 d0Var, RecyclerView.l.b bVar) {
        a aVar = (a) this.a.get(d0Var);
        if (aVar == null) {
            aVar = a.b();
            this.a.put(d0Var, aVar);
        }
        aVar.a |= 2;
        aVar.b = bVar;
    }

    public void b(RecyclerView.d0 d0Var) {
        a aVar = (a) this.a.get(d0Var);
        if (aVar == null) {
            aVar = a.b();
            this.a.put(d0Var, aVar);
        }
        aVar.a |= 1;
    }

    public void c(long j, RecyclerView.d0 d0Var) {
        this.b.j(j, d0Var);
    }

    public void d(RecyclerView.d0 d0Var, RecyclerView.l.b bVar) {
        a aVar = (a) this.a.get(d0Var);
        if (aVar == null) {
            aVar = a.b();
            this.a.put(d0Var, aVar);
        }
        aVar.c = bVar;
        aVar.a |= 8;
    }

    public void e(RecyclerView.d0 d0Var, RecyclerView.l.b bVar) {
        a aVar = (a) this.a.get(d0Var);
        if (aVar == null) {
            aVar = a.b();
            this.a.put(d0Var, aVar);
        }
        aVar.b = bVar;
        aVar.a |= 4;
    }

    public void f() {
        this.a.clear();
        this.b.b();
    }

    public RecyclerView.d0 g(long j) {
        return (RecyclerView.d0) this.b.f(j);
    }

    public boolean h(RecyclerView.d0 d0Var) {
        a aVar = (a) this.a.get(d0Var);
        if (aVar != null && (aVar.a & 1) != 0) {
            return true;
        }
        return false;
    }

    public boolean i(RecyclerView.d0 d0Var) {
        a aVar = (a) this.a.get(d0Var);
        if (aVar != null && (aVar.a & 4) != 0) {
            return true;
        }
        return false;
    }

    public void j() {
        a.a();
    }

    public void k(RecyclerView.d0 d0Var) {
        p(d0Var);
    }

    public final RecyclerView.l.b l(RecyclerView.d0 d0Var, int i) {
        a aVar;
        RecyclerView.l.b bVar;
        int indexOfKey = this.a.indexOfKey(d0Var);
        if (indexOfKey >= 0 && (aVar = (a) this.a.valueAt(indexOfKey)) != null) {
            int i2 = aVar.a;
            if ((i2 & i) != 0) {
                int i3 = (i ^ (-1)) & i2;
                aVar.a = i3;
                if (i == 4) {
                    bVar = aVar.b;
                } else if (i == 8) {
                    bVar = aVar.c;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((i3 & 12) == 0) {
                    this.a.removeAt(indexOfKey);
                    a.c(aVar);
                }
                return bVar;
            }
        }
        return null;
    }

    public RecyclerView.l.b m(RecyclerView.d0 d0Var) {
        return l(d0Var, 8);
    }

    public RecyclerView.l.b n(RecyclerView.d0 d0Var) {
        return l(d0Var, 4);
    }

    public void o(b bVar) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            RecyclerView.d0 d0Var = (RecyclerView.d0) this.a.keyAt(size);
            a aVar = (a) this.a.removeAt(size);
            int i = aVar.a;
            if ((i & 3) == 3) {
                bVar.a(d0Var);
            } else if ((i & 1) != 0) {
                RecyclerView.l.b bVar2 = aVar.b;
                if (bVar2 == null) {
                    bVar.a(d0Var);
                } else {
                    bVar.c(d0Var, bVar2, aVar.c);
                }
            } else if ((i & 14) == 14) {
                bVar.b(d0Var, aVar.b, aVar.c);
            } else if ((i & 12) == 12) {
                bVar.d(d0Var, aVar.b, aVar.c);
            } else if ((i & 4) != 0) {
                bVar.c(d0Var, aVar.b, null);
            } else if ((i & 8) != 0) {
                bVar.b(d0Var, aVar.b, aVar.c);
            }
            a.c(aVar);
        }
    }

    public void p(RecyclerView.d0 d0Var) {
        a aVar = (a) this.a.get(d0Var);
        if (aVar == null) {
            return;
        }
        aVar.a &= -2;
    }

    public void q(RecyclerView.d0 d0Var) {
        int m = this.b.m() - 1;
        while (true) {
            if (m < 0) {
                break;
            } else if (d0Var == this.b.n(m)) {
                this.b.l(m);
                break;
            } else {
                m--;
            }
        }
        a aVar = (a) this.a.remove(d0Var);
        if (aVar != null) {
            a.c(aVar);
        }
    }
}
