package androidx.collection;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class b implements Collection, Set {
    public static final int[] e = new int[0];
    public static final Object[] f = new Object[0];
    public static Object[] g;
    public static int h;
    public static Object[] i;
    public static int j;
    public int[] a;
    public Object[] b;
    public int c;
    public f d;

    /* loaded from: classes.dex */
    public class a extends f {
        public a() {
        }

        @Override // androidx.collection.f
        public void a() {
            b.this.clear();
        }

        @Override // androidx.collection.f
        public Object b(int i, int i2) {
            return b.this.b[i];
        }

        @Override // androidx.collection.f
        public Map c() {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // androidx.collection.f
        public int d() {
            return b.this.c;
        }

        @Override // androidx.collection.f
        public int e(Object obj) {
            return b.this.indexOf(obj);
        }

        @Override // androidx.collection.f
        public int f(Object obj) {
            return b.this.indexOf(obj);
        }

        @Override // androidx.collection.f
        public void g(Object obj, Object obj2) {
            b.this.add(obj);
        }

        @Override // androidx.collection.f
        public void h(int i) {
            b.this.g(i);
        }

        @Override // androidx.collection.f
        public Object i(int i, Object obj) {
            throw new UnsupportedOperationException("not a map");
        }
    }

    public b() {
        this(0);
    }

    public static void c(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (b.class) {
                if (j < 10) {
                    objArr[0] = i;
                    objArr[1] = iArr;
                    for (int i3 = i2 - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    i = objArr;
                    j++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (b.class) {
                if (h < 10) {
                    objArr[0] = g;
                    objArr[1] = iArr;
                    for (int i4 = i2 - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    g = objArr;
                    h++;
                }
            }
        }
    }

    public final void a(int i2) {
        if (i2 == 8) {
            synchronized (b.class) {
                Object[] objArr = i;
                if (objArr != null) {
                    this.b = objArr;
                    i = (Object[]) objArr[0];
                    this.a = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    j--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (b.class) {
                Object[] objArr2 = g;
                if (objArr2 != null) {
                    this.b = objArr2;
                    g = (Object[]) objArr2[0];
                    this.a = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    h--;
                    return;
                }
            }
        }
        this.a = new int[i2];
        this.b = new Object[i2];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(Object obj) {
        int i2;
        int e2;
        if (obj == null) {
            e2 = f();
            i2 = 0;
        } else {
            int hashCode = obj.hashCode();
            i2 = hashCode;
            e2 = e(obj, hashCode);
        }
        if (e2 >= 0) {
            return false;
        }
        int i3 = e2 ^ (-1);
        int i4 = this.c;
        int[] iArr = this.a;
        if (i4 >= iArr.length) {
            int i5 = 8;
            if (i4 >= 8) {
                i5 = (i4 >> 1) + i4;
            } else if (i4 < 4) {
                i5 = 4;
            }
            Object[] objArr = this.b;
            a(i5);
            int[] iArr2 = this.a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.b, 0, objArr.length);
            }
            c(iArr, objArr, this.c);
        }
        int i6 = this.c;
        if (i3 < i6) {
            int[] iArr3 = this.a;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr2 = this.b;
            System.arraycopy(objArr2, i3, objArr2, i7, this.c - i3);
        }
        this.a[i3] = i2;
        this.b[i3] = obj;
        this.c++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection collection) {
        b(this.c + collection.size());
        boolean z = false;
        for (Object obj : collection) {
            z |= add(obj);
        }
        return z;
    }

    public void b(int i2) {
        int[] iArr = this.a;
        if (iArr.length < i2) {
            Object[] objArr = this.b;
            a(i2);
            int i3 = this.c;
            if (i3 > 0) {
                System.arraycopy(iArr, 0, this.a, 0, i3);
                System.arraycopy(objArr, 0, this.b, 0, this.c);
            }
            c(iArr, objArr, this.c);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i2 = this.c;
        if (i2 != 0) {
            c(this.a, this.b, i2);
            this.a = e;
            this.b = f;
            this.c = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        if (indexOf(obj) >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        for (Object obj : collection) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    public final f d() {
        if (this.d == null) {
            this.d = new a();
        }
        return this.d;
    }

    public final int e(Object obj, int i2) {
        int i3 = this.c;
        if (i3 == 0) {
            return -1;
        }
        int a2 = c.a(this.a, i3, i2);
        if (a2 < 0) {
            return a2;
        }
        if (obj.equals(this.b[a2])) {
            return a2;
        }
        int i4 = a2 + 1;
        while (i4 < i3 && this.a[i4] == i2) {
            if (obj.equals(this.b[i4])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = a2 - 1; i5 >= 0 && this.a[i5] == i2; i5--) {
            if (obj.equals(this.b[i5])) {
                return i5;
            }
        }
        return i4 ^ (-1);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.c; i2++) {
                try {
                    if (!set.contains(h(i2))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public final int f() {
        int i2 = this.c;
        if (i2 == 0) {
            return -1;
        }
        int a2 = c.a(this.a, i2, 0);
        if (a2 < 0) {
            return a2;
        }
        if (this.b[a2] == null) {
            return a2;
        }
        int i3 = a2 + 1;
        while (i3 < i2 && this.a[i3] == 0) {
            if (this.b[i3] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = a2 - 1; i4 >= 0 && this.a[i4] == 0; i4--) {
            if (this.b[i4] == null) {
                return i4;
            }
        }
        return i3 ^ (-1);
    }

    public Object g(int i2) {
        Object[] objArr = this.b;
        Object obj = objArr[i2];
        int i3 = this.c;
        if (i3 <= 1) {
            c(this.a, objArr, i3);
            this.a = e;
            this.b = f;
            this.c = 0;
        } else {
            int[] iArr = this.a;
            int i4 = 8;
            if (iArr.length > 8 && i3 < iArr.length / 3) {
                if (i3 > 8) {
                    i4 = i3 + (i3 >> 1);
                }
                a(i4);
                this.c--;
                if (i2 > 0) {
                    System.arraycopy(iArr, 0, this.a, 0, i2);
                    System.arraycopy(objArr, 0, this.b, 0, i2);
                }
                int i5 = this.c;
                if (i2 < i5) {
                    int i6 = i2 + 1;
                    System.arraycopy(iArr, i6, this.a, i2, i5 - i2);
                    System.arraycopy(objArr, i6, this.b, i2, this.c - i2);
                }
            } else {
                int i7 = i3 - 1;
                this.c = i7;
                if (i2 < i7) {
                    int i8 = i2 + 1;
                    System.arraycopy(iArr, i8, iArr, i2, i7 - i2);
                    Object[] objArr2 = this.b;
                    System.arraycopy(objArr2, i8, objArr2, i2, this.c - i2);
                }
                this.b[this.c] = null;
            }
        }
        return obj;
    }

    public Object h(int i2) {
        return this.b[i2];
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.a;
        int i2 = this.c;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += iArr[i4];
        }
        return i3;
    }

    public int indexOf(Object obj) {
        if (obj == null) {
            return f();
        }
        return e(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        if (this.c <= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator iterator() {
        return d().m().iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf >= 0) {
            g(indexOf);
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection collection) {
        boolean z = false;
        for (Object obj : collection) {
            z |= remove(obj);
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection collection) {
        boolean z = false;
        for (int i2 = this.c - 1; i2 >= 0; i2--) {
            if (!collection.contains(this.b[i2])) {
                g(i2);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.c;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int i2 = this.c;
        Object[] objArr = new Object[i2];
        System.arraycopy(this.b, 0, objArr, 0, i2);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.c * 14);
        sb.append('{');
        for (int i2 = 0; i2 < this.c; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object h2 = h(i2);
            if (h2 != this) {
                sb.append(h2);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public b(int i2) {
        if (i2 == 0) {
            this.a = e;
            this.b = f;
        } else {
            a(i2);
        }
        this.c = 0;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray(Object[] objArr) {
        if (objArr.length < this.c) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), this.c);
        }
        System.arraycopy(this.b, 0, objArr, 0, this.c);
        int length = objArr.length;
        int i2 = this.c;
        if (length > i2) {
            objArr[i2] = null;
        }
        return objArr;
    }
}
