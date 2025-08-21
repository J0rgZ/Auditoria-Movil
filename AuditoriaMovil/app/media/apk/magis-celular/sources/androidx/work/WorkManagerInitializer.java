package androidx.work;

import android.content.Context;
import androidx.work.a;
import java.util.Collections;
import java.util.List;
import u0.k;
import u0.t;
/* loaded from: classes.dex */
public final class WorkManagerInitializer implements p0.b {
    public static final String a = k.f("WrkMgrInitializer");

    /* renamed from: b */
    public t a(Context context) {
        k.c().a(a, "Initializing WorkManager with default configuration.", new Throwable[0]);
        t.d(context, new a.b().a());
        return t.c(context);
    }

    public List dependencies() {
        return Collections.emptyList();
    }
}
