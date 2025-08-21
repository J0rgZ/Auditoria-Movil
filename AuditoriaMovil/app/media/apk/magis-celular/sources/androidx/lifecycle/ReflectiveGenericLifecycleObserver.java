package androidx.lifecycle;

import androidx.lifecycle.a;
import androidx.lifecycle.c;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ReflectiveGenericLifecycleObserver implements d {
    public final Object a;
    public final a.C0018a b;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.a = obj;
        this.b = a.c.c(obj.getClass());
    }

    @Override // androidx.lifecycle.d
    public void a(f fVar, c.a aVar) {
        this.b.a(fVar, aVar, this.a);
    }
}
