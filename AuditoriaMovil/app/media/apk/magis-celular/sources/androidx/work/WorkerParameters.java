package androidx.work;

import android.net.Network;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import u0.f;
import u0.p;
import u0.v;
/* loaded from: classes.dex */
public final class WorkerParameters {
    public UUID a;
    public b b;
    public Set c;
    public a d;
    public int e;
    public Executor f;
    public g1.a g;
    public v h;
    public p i;
    public f j;

    /* loaded from: classes.dex */
    public static class a {
        public List a = Collections.emptyList();
        public List b = Collections.emptyList();
        public Network c;
    }

    public WorkerParameters(UUID uuid, b bVar, Collection collection, a aVar, int i, Executor executor, g1.a aVar2, v vVar, p pVar, f fVar) {
        this.a = uuid;
        this.b = bVar;
        this.c = new HashSet(collection);
        this.d = aVar;
        this.e = i;
        this.f = executor;
        this.g = aVar2;
        this.h = vVar;
        this.i = pVar;
        this.j = fVar;
    }

    public Executor a() {
        return this.f;
    }

    public f b() {
        return this.j;
    }

    public UUID c() {
        return this.a;
    }

    public b d() {
        return this.b;
    }

    public g1.a e() {
        return this.g;
    }

    public v f() {
        return this.h;
    }
}
