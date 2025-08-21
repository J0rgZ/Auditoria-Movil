package androidx.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class a extends g implements Map {
    f mCollections;

    /* renamed from: androidx.collection.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0012a extends f {
        public C0012a() {
        }

        @Override // androidx.collection.f
        public void a() {
            a.this.clear();
        }

        @Override // androidx.collection.f
        public Object b(int i, int i2) {
            return a.this.mArray[(i << 1) + i2];
        }

        @Override // androidx.collection.f
        public Map c() {
            return a.this;
        }

        @Override // androidx.collection.f
        public int d() {
            return a.this.mSize;
        }

        @Override // androidx.collection.f
        public int e(Object obj) {
            return a.this.indexOfKey(obj);
        }

        @Override // androidx.collection.f
        public int f(Object obj) {
            return a.this.indexOfValue(obj);
        }

        @Override // androidx.collection.f
        public void g(Object obj, Object obj2) {
            a.this.put(obj, obj2);
        }

        @Override // androidx.collection.f
        public void h(int i) {
            a.this.removeAt(i);
        }

        @Override // androidx.collection.f
        public Object i(int i, Object obj) {
            return a.this.setValueAt(i, obj);
        }
    }

    public a() {
    }

    public boolean containsAll(Collection<?> collection) {
        return f.j(this, collection);
    }

    public final f d() {
        if (this.mCollections == null) {
            this.mCollections = new C0012a();
        }
        return this.mCollections;
    }

    @Override // java.util.Map
    public Set<Map.Entry<Object, Object>> entrySet() {
        return d().l();
    }

    @Override // java.util.Map
    public Set<Object> keySet() {
        return d().m();
    }

    @Override // java.util.Map
    public void putAll(Map<Object, Object> map) {
        ensureCapacity(this.mSize + map.size());
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public boolean removeAll(Collection<?> collection) {
        return f.o(this, collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return f.p(this, collection);
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return d().n();
    }

    public a(int i) {
        super(i);
    }

    public a(g gVar) {
        super(gVar);
    }
}
