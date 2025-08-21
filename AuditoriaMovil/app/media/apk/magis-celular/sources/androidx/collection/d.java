package androidx.collection;
/* loaded from: classes.dex */
public class d implements Cloneable {
    public static final Object e = new Object();
    public boolean a;
    public long[] b;
    public Object[] c;
    public int d;

    public d() {
        this(10);
    }

    public void a(long j, Object obj) {
        int i = this.d;
        if (i != 0 && j <= this.b[i - 1]) {
            j(j, obj);
            return;
        }
        if (this.a && i >= this.b.length) {
            e();
        }
        int i2 = this.d;
        if (i2 >= this.b.length) {
            int f = c.f(i2 + 1);
            long[] jArr = new long[f];
            Object[] objArr = new Object[f];
            long[] jArr2 = this.b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.b = jArr;
            this.c = objArr;
        }
        this.b[i2] = j;
        this.c[i2] = obj;
        this.d = i2 + 1;
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
    public d clone() {
        try {
            d dVar = (d) super.clone();
            dVar.b = (long[]) this.b.clone();
            dVar.c = (Object[]) this.c.clone();
            return dVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public void d(long j) {
        k(j);
    }

    public final void e() {
        int i = this.d;
        long[] jArr = this.b;
        Object[] objArr = this.c;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != e) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.a = false;
        this.d = i2;
    }

    public Object f(long j) {
        return g(j, null);
    }

    public Object g(long j, Object obj) {
        Object obj2;
        int b = c.b(this.b, this.d, j);
        if (b >= 0 && (obj2 = this.c[b]) != e) {
            return obj2;
        }
        return obj;
    }

    public int h(long j) {
        if (this.a) {
            e();
        }
        return c.b(this.b, this.d, j);
    }

    public long i(int i) {
        if (this.a) {
            e();
        }
        return this.b[i];
    }

    public void j(long j, Object obj) {
        int b = c.b(this.b, this.d, j);
        if (b >= 0) {
            this.c[b] = obj;
            return;
        }
        int i = b ^ (-1);
        int i2 = this.d;
        if (i < i2) {
            Object[] objArr = this.c;
            if (objArr[i] == e) {
                this.b[i] = j;
                objArr[i] = obj;
                return;
            }
        }
        if (this.a && i2 >= this.b.length) {
            e();
            i = c.b(this.b, this.d, j) ^ (-1);
        }
        int i3 = this.d;
        if (i3 >= this.b.length) {
            int f = c.f(i3 + 1);
            long[] jArr = new long[f];
            Object[] objArr2 = new Object[f];
            long[] jArr2 = this.b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.b = jArr;
            this.c = objArr2;
        }
        int i4 = this.d;
        if (i4 - i != 0) {
            long[] jArr3 = this.b;
            int i5 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i5, i4 - i);
            Object[] objArr4 = this.c;
            System.arraycopy(objArr4, i, objArr4, i5, this.d - i);
        }
        this.b[i] = j;
        this.c[i] = obj;
        this.d++;
    }

    public void k(long j) {
        int b = c.b(this.b, this.d, j);
        if (b >= 0) {
            Object[] objArr = this.c;
            Object obj = objArr[b];
            Object obj2 = e;
            if (obj != obj2) {
                objArr[b] = obj2;
                this.a = true;
            }
        }
    }

    public void l(int i) {
        Object[] objArr = this.c;
        Object obj = objArr[i];
        Object obj2 = e;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.a = true;
        }
    }

    public int m() {
        if (this.a) {
            e();
        }
        return this.d;
    }

    public Object n(int i) {
        if (this.a) {
            e();
        }
        return this.c[i];
    }

    public String toString() {
        if (m() <= 0) {
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
            Object n = n(i);
            if (n != this) {
                sb.append(n);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public d(int i) {
        this.a = false;
        if (i == 0) {
            this.b = c.b;
            this.c = c.c;
            return;
        }
        int f = c.f(i);
        this.b = new long[f];
        this.c = new Object[f];
    }
}
