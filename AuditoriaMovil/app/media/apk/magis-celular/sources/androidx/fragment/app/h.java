package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import java.io.FileDescriptor;
import java.io.PrintWriter;
/* loaded from: classes.dex */
public abstract class h extends e {
    public final Activity a;
    public final Context b;
    public final Handler c;
    public final int d;
    public final j e;

    /* JADX WARN: Multi-variable type inference failed */
    public h(d dVar) {
        this(dVar, dVar, new Handler(), 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Activity d() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Context e() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Handler f() {
        return this.c;
    }

    public abstract void g(Fragment fragment);

    public abstract void h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract Object i();

    public abstract LayoutInflater j();

    public abstract int k();

    public abstract boolean l();

    public abstract void m(Fragment fragment, String[] strArr, int i);

    public abstract boolean n(Fragment fragment);

    public abstract boolean o(String str);

    public abstract void p(Fragment fragment, Intent intent, int i, Bundle bundle);

    public abstract void q(Fragment fragment, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle);

    public abstract void r();

    public h(Activity activity, Context context, Handler handler, int i) {
        this.e = new j();
        this.a = activity;
        this.b = (Context) u.i.e(context, "context == null");
        this.c = (Handler) u.i.e(handler, "handler == null");
        this.d = i;
    }
}
