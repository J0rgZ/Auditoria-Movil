package androidx.collection;
/* loaded from: classes.dex */
public class h implements Cloneable {
    public static final Object e = new Object();
    public boolean a;
    public int[] b;
    public Object[] c;
    public int d;

    public h() {
        this(10);
    }

    public void a(int i, Object obj) {
        int i2 = this.d;
        if (i2 != 0 && i <= this.b[i2 - 1]) {
            j(i, obj);
            return;
        }
        if (this.a && i2 >= this.b.length) {
            d();
        }
        int i3 = this.d;
        if (i3 >= this.b.length) {
            int e2 = c.e(i3 + 1);
            int[] iArr = new int[e2];
            Object[] objArr = new Object[e2];
            int[] iArr2 = this.b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.b = iArr;
            this.c = objArr;
        }
        this.b[i3] = i;
        this.c[i3] = obj;
        this.d = i3 + 1;
    }

    public void b() {
        int i = this.d;
        Object[] objArr = this.c;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.d = 0;
        this.a = false;
    }

    /* renamed from: c */
    public h clone() {
        try {
            h hVar = (h) super.clone();
            hVar.b = (int[]) this.b.clone();
            hVar.c = (Object[]) this.c.clone();
            return hVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public final void d() {
        int i = this.d;
        int[] iArr = this.b;
        Object[] objArr = this.c;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != e) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.a = false;
        this.d = i2;
    }

    public Object e(int i) {
        return f(i, null);
    }

    public Object f(int i, Object obj) {
        Object obj2;
        int a = c.a(this.b, this.d, i);
        if (a >= 0 && (obj2 = this.c[a]) != e) {
            return obj2;
        }
        return obj;
    }

    public int g(int i) {
        if (this.a) {
            d();
        }
        return c.a(this.b, this.d, i);
    }

    public int h(Object obj) {
        if (this.a) {
            d();
        }
        for (int i = 0; i < this.d; i++) {
            if (this.c[i] == obj) {
                return i;
            }
        }
        return -1;
    }

    public int i(int i) {
        if (this.a) {
            d();
        }
        return this.b[i];
    }

    public void j(int i, Object obj) {
        int a = c.a(this.b, this.d, i);
        if (a >= 0) {
            this.c[a] = obj;
            return;
        }
        int i2 = a ^ (-1);
        int i3 = this.d;
        if (i2 < i3) {
            Object[] objArr = this.c;
            if (objArr[i2] == e) {
                this.b[i2] = i;
                objArr[i2] = obj;
                return;
            }
        }
        if (this.a && i3 >= this.b.length) {
            d();
            i2 = c.a(this.b, this.d, i) ^ (-1);
        }
        int i4 = this.d;
        if (i4 >= this.b.length) {
            int e2 = c.e(i4 + 1);
            int[] iArr = new int[e2];
            Object[] objArr2 = new Object[e2];
            int[] iArr2 = this.b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.b = iArr;
            this.c = objArr2;
        }
        int i5 = this.d;
        if (i5 - i2 != 0) {
            int[] iArr3 = this.b;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            Object[] objArr4 = this.c;
            System.arraycopy(objArr4, i2, objArr4, i6, this.d - i2);
        }
        this.b[i2] = i;
        this.c[i2] = obj;
        this.d++;
    }

    public void k(int i) {
        int a = c.a(this.b, this.d, i);
        if (a >= 0) {
            Object[] objArr = this.c;
            Object obj = objArr[a];
            Object obj2 = e;
            if (obj != obj2) {
                objArr[a] = obj2;
                this.a = true;
            }
        }
    }

    public int l() {
        if (this.a) {
            d();
        }
        return this.d;
    }

    public Object m(int i) {
        if (this.a) {
            d();
        }
        return this.c[i];
    }

    public String toString() {
        if (l() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.d * 28);
        sb.append('{');
        for (int i = 0; i < this.d; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(i(i));
            sb.append('=');
            Object m = m(i);
            if (m != this) {
                sb.append(m);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public h(int i) {
        this.a = false;
        if (i == 0) {
            this.b = c.a;
            this.c = c.c;
            return;
        }
        int e2 = c.e(i);
        this.b = new int[e2];
        this.c = new Object[e2];
    }
}
