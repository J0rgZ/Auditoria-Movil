package androidx.fragment.app;

import androidx.lifecycle.r;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class l extends androidx.lifecycle.q {
    public static final r.a i = new a();
    public final boolean f;
    public final HashSet c = new HashSet();
    public final HashMap d = new HashMap();
    public final HashMap e = new HashMap();
    public boolean g = false;
    public boolean h = false;

    /* loaded from: classes.dex */
    public static class a implements r.a {
        @Override // androidx.lifecycle.r.a
        public androidx.lifecycle.q a(Class cls) {
            return new l(true);
        }
    }

    public l(boolean z) {
        this.f = z;
    }

    public static l g(androidx.lifecycle.s sVar) {
        return (l) new androidx.lifecycle.r(sVar, i).a(l.class);
    }

    @Override // androidx.lifecycle.q
    public void c() {
        if (j.H) {
            StringBuilder sb = new StringBuilder();
            sb.append("onCleared called for ");
            sb.append(this);
        }
        this.g = true;
    }

    public boolean d(Fragment fragment) {
        return this.c.add(fragment);
    }

    public void e(Fragment fragment) {
        if (j.H) {
            StringBuilder sb = new StringBuilder();
            sb.append("Clearing non-config state for ");
            sb.append(fragment);
        }
        l lVar = (l) this.d.get(fragment.mWho);
        if (lVar != null) {
            lVar.c();
            this.d.remove(fragment.mWho);
        }
        androidx.lifecycle.s sVar = (androidx.lifecycle.s) this.e.get(fragment.mWho);
        if (sVar != null) {
            sVar.a();
            this.e.remove(fragment.mWho);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        l lVar = (l) obj;
        if (this.c.equals(lVar.c) && this.d.equals(lVar.d) && this.e.equals(lVar.e)) {
            return true;
        }
        return false;
    }

    public l f(Fragment fragment) {
        l lVar = (l) this.d.get(fragment.mWho);
        if (lVar == null) {
            l lVar2 = new l(this.f);
            this.d.put(fragment.mWho, lVar2);
            return lVar2;
        }
        return lVar;
    }

    public Collection h() {
        return this.c;
    }

    public int hashCode() {
        return (((this.c.hashCode() * 31) + this.d.hashCode()) * 31) + this.e.hashCode();
    }

    public androidx.lifecycle.s i(Fragment fragment) {
        androidx.lifecycle.s sVar = (androidx.lifecycle.s) this.e.get(fragment.mWho);
        if (sVar == null) {
            androidx.lifecycle.s sVar2 = new androidx.lifecycle.s();
            this.e.put(fragment.mWho, sVar2);
            return sVar2;
        }
        return sVar;
    }

    public boolean j() {
        return this.g;
    }

    public boolean k(Fragment fragment) {
        return this.c.remove(fragment);
    }

    public boolean l(Fragment fragment) {
        if (!this.c.contains(fragment)) {
            return true;
        }
        if (this.f) {
            return this.g;
        }
        return !this.h;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator it2 = this.d.keySet().iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator it3 = this.e.keySet().iterator();
        while (it3.hasNext()) {
            sb.append((String) it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
