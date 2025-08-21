package androidx.lifecycle;

import java.util.HashMap;
/* loaded from: classes.dex */
public class s {
    public final HashMap a = new HashMap();

    public final void a() {
        for (q qVar : this.a.values()) {
            qVar.a();
        }
        this.a.clear();
    }

    public final q b(String str) {
        return (q) this.a.get(str);
    }

    public final void c(String str, q qVar) {
        q qVar2 = (q) this.a.put(str, qVar);
        if (qVar2 != null) {
            qVar2.c();
        }
    }
}
