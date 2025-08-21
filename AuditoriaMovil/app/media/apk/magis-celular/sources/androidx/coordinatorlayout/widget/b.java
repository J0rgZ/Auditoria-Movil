package androidx.coordinatorlayout.widget;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import u.f;
import u.g;
/* loaded from: classes.dex */
public final class b {
    public final f a = new g(10);
    public final androidx.collection.g b = new androidx.collection.g();
    public final ArrayList c = new ArrayList();
    public final HashSet d = new HashSet();

    public void a(Object obj, Object obj2) {
        if (this.b.containsKey(obj) && this.b.containsKey(obj2)) {
            ArrayList arrayList = (ArrayList) this.b.get(obj);
            if (arrayList == null) {
                arrayList = f();
                this.b.put(obj, arrayList);
            }
            arrayList.add(obj2);
            return;
        }
        throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
    }

    public void b(Object obj) {
        if (!this.b.containsKey(obj)) {
            this.b.put(obj, null);
        }
    }

    public void c() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            ArrayList arrayList = (ArrayList) this.b.valueAt(i);
            if (arrayList != null) {
                k(arrayList);
            }
        }
        this.b.clear();
    }

    public boolean d(Object obj) {
        return this.b.containsKey(obj);
    }

    public final void e(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (arrayList.contains(obj)) {
            return;
        }
        if (!hashSet.contains(obj)) {
            hashSet.add(obj);
            ArrayList arrayList2 = (ArrayList) this.b.get(obj);
            if (arrayList2 != null) {
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    e(arrayList2.get(i), arrayList, hashSet);
                }
            }
            hashSet.remove(obj);
            arrayList.add(obj);
            return;
        }
        throw new RuntimeException("This graph contains cyclic dependencies");
    }

    public final ArrayList f() {
        ArrayList arrayList = (ArrayList) this.a.acquire();
        if (arrayList == null) {
            return new ArrayList();
        }
        return arrayList;
    }

    public List g(Object obj) {
        return (List) this.b.get(obj);
    }

    public List h(Object obj) {
        int size = this.b.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            ArrayList arrayList2 = (ArrayList) this.b.valueAt(i);
            if (arrayList2 != null && arrayList2.contains(obj)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.b.keyAt(i));
            }
        }
        return arrayList;
    }

    public ArrayList i() {
        this.c.clear();
        this.d.clear();
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            e(this.b.keyAt(i), this.c, this.d);
        }
        return this.c;
    }

    public boolean j(Object obj) {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            ArrayList arrayList = (ArrayList) this.b.valueAt(i);
            if (arrayList != null && arrayList.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    public final void k(ArrayList arrayList) {
        arrayList.clear();
        this.a.release(arrayList);
    }
}
