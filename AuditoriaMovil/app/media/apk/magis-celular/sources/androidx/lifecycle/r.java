package androidx.lifecycle;
/* loaded from: classes.dex */
public class r {
    public final a a;
    public final s b;

    /* loaded from: classes.dex */
    public interface a {
        q a(Class cls);
    }

    public r(s sVar, a aVar) {
        this.a = aVar;
        this.b = sVar;
    }

    public q a(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return b("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public q b(String str, Class cls) {
        q b = this.b.b(str);
        if (cls.isInstance(b)) {
            return b;
        }
        q a2 = this.a.a(cls);
        this.b.c(str, a2);
        return a2;
    }
}
