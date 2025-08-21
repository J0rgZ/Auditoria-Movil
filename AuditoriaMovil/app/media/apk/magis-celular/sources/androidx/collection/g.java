package androidx.collection;

import java.util.ConcurrentModificationException;
import java.util.Map;
/* loaded from: classes.dex */
public class g {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
    private static final boolean DEBUG = false;
    private static final String TAG = "ArrayMap";
    static Object[] mBaseCache;
    static int mBaseCacheSize;
    static Object[] mTwiceBaseCache;
    static int mTwiceBaseCacheSize;
    Object[] mArray;
    int[] mHashes;
    int mSize;

    public g() {
        this.mHashes = c.a;
        this.mArray = c.c;
        this.mSize = 0;
    }

    private void a(int i) {
        if (i == 8) {
            synchronized (g.class) {
                Object[] objArr = mTwiceBaseCache;
                if (objArr != null) {
                    this.mArray = objArr;
                    mTwiceBaseCache = (Object[]) objArr[0];
                    this.mHashes = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    mTwiceBaseCacheSize--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (g.class) {
                Object[] objArr2 = mBaseCache;
                if (objArr2 != null) {
                    this.mArray = objArr2;
                    mBaseCache = (Object[]) objArr2[0];
                    this.mHashes = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    mBaseCacheSize--;
                    return;
                }
            }
        }
        this.mHashes = new int[i];
        this.mArray = new Object[i << 1];
    }

    public static int b(int[] iArr, int i, int i2) {
        try {
            return c.a(iArr, i, i2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static void c(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (g.class) {
                if (mTwiceBaseCacheSize < 10) {
                    objArr[0] = mTwiceBaseCache;
                    objArr[1] = iArr;
                    for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    mTwiceBaseCache = objArr;
                    mTwiceBaseCacheSize++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (g.class) {
                if (mBaseCacheSize < 10) {
                    objArr[0] = mBaseCache;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    mBaseCache = objArr;
                    mBaseCacheSize++;
                }
            }
        }
    }

    public void clear() {
        int i = this.mSize;
        if (i > 0) {
            int[] iArr = this.mHashes;
            Object[] objArr = this.mArray;
            this.mHashes = c.a;
            this.mArray = c.c;
            this.mSize = 0;
            c(iArr, objArr, i);
        }
        if (this.mSize <= 0) {
            return;
        }
        throw new ConcurrentModificationException();
    }

    public boolean containsKey(Object obj) {
        if (indexOfKey(obj) >= 0) {
            return CONCURRENT_MODIFICATION_EXCEPTIONS;
        }
        return DEBUG;
    }

    public boolean containsValue(Object obj) {
        if (indexOfValue(obj) >= 0) {
            return CONCURRENT_MODIFICATION_EXCEPTIONS;
        }
        return DEBUG;
    }

    public void ensureCapacity(int i) {
        int i2 = this.mSize;
        int[] iArr = this.mHashes;
        if (iArr.length < i) {
            Object[] objArr = this.mArray;
            a(i);
            if (this.mSize > 0) {
                System.arraycopy(iArr, 0, this.mHashes, 0, i2);
                System.arraycopy(objArr, 0, this.mArray, 0, i2 << 1);
            }
            c(iArr, objArr, i2);
        }
        if (this.mSize == i2) {
            return;
        }
        throw new ConcurrentModificationException();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return CONCURRENT_MODIFICATION_EXCEPTIONS;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (size() != gVar.size()) {
                return DEBUG;
            }
            for (int i = 0; i < this.mSize; i++) {
                try {
                    Object keyAt = keyAt(i);
                    Object valueAt = valueAt(i);
                    Object obj2 = gVar.get(keyAt);
                    if (valueAt == null) {
                        if (obj2 != null || !gVar.containsKey(keyAt)) {
                            return DEBUG;
                        }
                    } else if (!valueAt.equals(obj2)) {
                        return DEBUG;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return DEBUG;
                }
            }
            return CONCURRENT_MODIFICATION_EXCEPTIONS;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return DEBUG;
            }
            for (int i2 = 0; i2 < this.mSize; i2++) {
                try {
                    Object keyAt2 = keyAt(i2);
                    Object valueAt2 = valueAt(i2);
                    Object obj3 = map.get(keyAt2);
                    if (valueAt2 == null) {
                        if (obj3 != null || !map.containsKey(keyAt2)) {
                            return DEBUG;
                        }
                    } else if (!valueAt2.equals(obj3)) {
                        return DEBUG;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return CONCURRENT_MODIFICATION_EXCEPTIONS;
        }
        return DEBUG;
    }

    public Object get(Object obj) {
        return getOrDefault(obj, null);
    }

    public Object getOrDefault(Object obj, Object obj2) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return this.mArray[(indexOfKey << 1) + 1];
        }
        return obj2;
    }

    public int hashCode() {
        int hashCode;
        int[] iArr = this.mHashes;
        Object[] objArr = this.mArray;
        int i = this.mSize;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            int i5 = iArr[i3];
            if (obj == null) {
                hashCode = 0;
            } else {
                hashCode = obj.hashCode();
            }
            i4 += hashCode ^ i5;
            i3++;
            i2 += 2;
        }
        return i4;
    }

    int indexOf(Object obj, int i) {
        int i2 = this.mSize;
        if (i2 == 0) {
            return -1;
        }
        int b = b(this.mHashes, i2, i);
        if (b < 0) {
            return b;
        }
        if (obj.equals(this.mArray[b << 1])) {
            return b;
        }
        int i3 = b + 1;
        while (i3 < i2 && this.mHashes[i3] == i) {
            if (obj.equals(this.mArray[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = b - 1; i4 >= 0 && this.mHashes[i4] == i; i4--) {
            if (obj.equals(this.mArray[i4 << 1])) {
                return i4;
            }
        }
        return i3 ^ (-1);
    }

    public int indexOfKey(Object obj) {
        if (obj == null) {
            return indexOfNull();
        }
        return indexOf(obj, obj.hashCode());
    }

    public int indexOfNull() {
        int i = this.mSize;
        if (i == 0) {
            return -1;
        }
        int b = b(this.mHashes, i, 0);
        if (b < 0) {
            return b;
        }
        if (this.mArray[b << 1] == null) {
            return b;
        }
        int i2 = b + 1;
        while (i2 < i && this.mHashes[i2] == 0) {
            if (this.mArray[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = b - 1; i3 >= 0 && this.mHashes[i3] == 0; i3--) {
            if (this.mArray[i3 << 1] == null) {
                return i3;
            }
        }
        return i2 ^ (-1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int indexOfValue(Object obj) {
        int i = this.mSize * 2;
        Object[] objArr = this.mArray;
        if (obj == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (obj.equals(objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        if (this.mSize <= 0) {
            return CONCURRENT_MODIFICATION_EXCEPTIONS;
        }
        return DEBUG;
    }

    public Object keyAt(int i) {
        return this.mArray[i << 1];
    }

    public Object put(Object obj, Object obj2) {
        int i;
        int indexOf;
        int i2 = this.mSize;
        if (obj == null) {
            indexOf = indexOfNull();
            i = 0;
        } else {
            int hashCode = obj.hashCode();
            i = hashCode;
            indexOf = indexOf(obj, hashCode);
        }
        if (indexOf >= 0) {
            int i3 = (indexOf << 1) + 1;
            Object[] objArr = this.mArray;
            Object obj3 = objArr[i3];
            objArr[i3] = obj2;
            return obj3;
        }
        int i4 = indexOf ^ (-1);
        int[] iArr = this.mHashes;
        if (i2 >= iArr.length) {
            int i5 = 8;
            if (i2 >= 8) {
                i5 = (i2 >> 1) + i2;
            } else if (i2 < 4) {
                i5 = 4;
            }
            Object[] objArr2 = this.mArray;
            a(i5);
            if (i2 == this.mSize) {
                int[] iArr2 = this.mHashes;
                if (iArr2.length > 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    System.arraycopy(objArr2, 0, this.mArray, 0, objArr2.length);
                }
                c(iArr, objArr2, i2);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i4 < i2) {
            int[] iArr3 = this.mHashes;
            int i6 = i4 + 1;
            System.arraycopy(iArr3, i4, iArr3, i6, i2 - i4);
            Object[] objArr3 = this.mArray;
            System.arraycopy(objArr3, i4 << 1, objArr3, i6 << 1, (this.mSize - i4) << 1);
        }
        int i7 = this.mSize;
        if (i2 == i7) {
            int[] iArr4 = this.mHashes;
            if (i4 < iArr4.length) {
                iArr4[i4] = i;
                Object[] objArr4 = this.mArray;
                int i8 = i4 << 1;
                objArr4[i8] = obj;
                objArr4[i8 + 1] = obj2;
                this.mSize = i7 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public void putAll(g gVar) {
        int i = gVar.mSize;
        ensureCapacity(this.mSize + i);
        if (this.mSize == 0) {
            if (i > 0) {
                System.arraycopy(gVar.mHashes, 0, this.mHashes, 0, i);
                System.arraycopy(gVar.mArray, 0, this.mArray, 0, i << 1);
                this.mSize = i;
                return;
            }
            return;
        }
        for (int i2 = 0; i2 < i; i2++) {
            put(gVar.keyAt(i2), gVar.valueAt(i2));
        }
    }

    public Object putIfAbsent(Object obj, Object obj2) {
        Object obj3 = get(obj);
        if (obj3 == null) {
            return put(obj, obj2);
        }
        return obj3;
    }

    public Object remove(Object obj) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return removeAt(indexOfKey);
        }
        return null;
    }

    public Object removeAt(int i) {
        Object[] objArr = this.mArray;
        int i2 = i << 1;
        Object obj = objArr[i2 + 1];
        int i3 = this.mSize;
        int i4 = 0;
        if (i3 <= 1) {
            c(this.mHashes, objArr, i3);
            this.mHashes = c.a;
            this.mArray = c.c;
        } else {
            int i5 = i3 - 1;
            int[] iArr = this.mHashes;
            int i6 = 8;
            if (iArr.length > 8 && i3 < iArr.length / 3) {
                if (i3 > 8) {
                    i6 = i3 + (i3 >> 1);
                }
                a(i6);
                if (i3 == this.mSize) {
                    if (i > 0) {
                        System.arraycopy(iArr, 0, this.mHashes, 0, i);
                        System.arraycopy(objArr, 0, this.mArray, 0, i2);
                    }
                    if (i < i5) {
                        int i7 = i + 1;
                        int i8 = i5 - i;
                        System.arraycopy(iArr, i7, this.mHashes, i, i8);
                        System.arraycopy(objArr, i7 << 1, this.mArray, i2, i8 << 1);
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            } else {
                if (i < i5) {
                    int i9 = i + 1;
                    int i10 = i5 - i;
                    System.arraycopy(iArr, i9, iArr, i, i10);
                    Object[] objArr2 = this.mArray;
                    System.arraycopy(objArr2, i9 << 1, objArr2, i2, i10 << 1);
                }
                Object[] objArr3 = this.mArray;
                int i11 = i5 << 1;
                objArr3[i11] = null;
                objArr3[i11 + 1] = null;
            }
            i4 = i5;
        }
        if (i3 == this.mSize) {
            this.mSize = i4;
            return obj;
        }
        throw new ConcurrentModificationException();
    }

    public Object replace(Object obj, Object obj2) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return setValueAt(indexOfKey, obj2);
        }
        return null;
    }

    public Object setValueAt(int i, Object obj) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.mArray;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        return obj2;
    }

    public int size() {
        return this.mSize;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.mSize * 28);
        sb.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object keyAt = keyAt(i);
            if (keyAt != this) {
                sb.append(keyAt);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object valueAt = valueAt(i);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public Object valueAt(int i) {
        return this.mArray[(i << 1) + 1];
    }

    public boolean remove(Object obj, Object obj2) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            Object valueAt = valueAt(indexOfKey);
            if (obj2 == valueAt || (obj2 != null && obj2.equals(valueAt))) {
                removeAt(indexOfKey);
                return CONCURRENT_MODIFICATION_EXCEPTIONS;
            }
            return DEBUG;
        }
        return DEBUG;
    }

    public boolean replace(Object obj, Object obj2, Object obj3) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            Object valueAt = valueAt(indexOfKey);
            if (valueAt == obj2 || (obj2 != null && obj2.equals(valueAt))) {
                setValueAt(indexOfKey, obj3);
                return CONCURRENT_MODIFICATION_EXCEPTIONS;
            }
            return DEBUG;
        }
        return DEBUG;
    }

    public g(int i) {
        if (i == 0) {
            this.mHashes = c.a;
            this.mArray = c.c;
        } else {
            a(i);
        }
        this.mSize = 0;
    }

    public g(g gVar) {
        this();
        if (gVar != null) {
            putAll(gVar);
        }
    }
}
