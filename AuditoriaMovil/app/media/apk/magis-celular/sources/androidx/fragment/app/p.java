package androidx.fragment.app;

import androidx.lifecycle.c;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class p {
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public boolean h;
    public String j;
    public int k;
    public CharSequence l;
    public int m;
    public CharSequence n;
    public ArrayList o;
    public ArrayList p;
    public ArrayList r;
    public ArrayList a = new ArrayList();
    public boolean i = true;
    public boolean q = false;

    /* loaded from: classes.dex */
    public static final class a {
        public int a;
        public Fragment b;
        public int c;
        public int d;
        public int e;
        public int f;
        public c.b g;
        public c.b h;

        public a() {
        }

        public a(int i, Fragment fragment) {
            this.a = i;
            this.b = fragment;
            c.b bVar = c.b.RESUMED;
            this.g = bVar;
            this.h = bVar;
        }

        public a(int i, Fragment fragment, c.b bVar) {
            this.a = i;
            this.b = fragment;
            this.g = fragment.mMaxState;
            this.h = bVar;
        }
    }

    public p b(int i, Fragment fragment) {
        m(i, fragment, null, 1);
        return this;
    }

    public p c(int i, Fragment fragment, String str) {
        m(i, fragment, str, 1);
        return this;
    }

    public p d(Fragment fragment, String str) {
        m(0, fragment, str, 1);
        return this;
    }

    public void e(a aVar) {
        this.a.add(aVar);
        aVar.c = this.b;
        aVar.d = this.c;
        aVar.e = this.d;
        aVar.f = this.e;
    }

    public p f(Fragment fragment) {
        e(new a(7, fragment));
        return this;
    }

    public abstract int g();

    public abstract int h();

    public abstract void i();

    public abstract void j();

    public p k(Fragment fragment) {
        e(new a(6, fragment));
        return this;
    }

    public p l() {
        if (!this.h) {
            this.i = false;
            return this;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    public void m(int i, Fragment fragment, String str, int i2) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (!cls.isAnonymousClass() && Modifier.isPublic(modifiers) && (!cls.isMemberClass() || Modifier.isStatic(modifiers))) {
            if (str != null) {
                String str2 = fragment.mTag;
                if (str2 != null && !str.equals(str2)) {
                    throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
                }
                fragment.mTag = str;
            }
            if (i != 0) {
                if (i != -1) {
                    int i3 = fragment.mFragmentId;
                    if (i3 != 0 && i3 != i) {
                        throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i);
                    }
                    fragment.mFragmentId = i;
                    fragment.mContainerId = i;
                } else {
                    throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
                }
            }
            e(new a(i2, fragment));
            return;
        }
        throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
    }

    public p n(Fragment fragment) {
        e(new a(4, fragment));
        return this;
    }

    public p o(Fragment fragment) {
        e(new a(3, fragment));
        return this;
    }

    public p p(int i, Fragment fragment) {
        return q(i, fragment, null);
    }

    public p q(int i, Fragment fragment, String str) {
        if (i != 0) {
            m(i, fragment, str, 2);
            return this;
        }
        throw new IllegalArgumentException("Must use non-zero containerViewId");
    }

    public p r(Fragment fragment, c.b bVar) {
        e(new a(10, fragment, bVar));
        return this;
    }

    public p s(Fragment fragment) {
        e(new a(5, fragment));
        return this;
    }
}
