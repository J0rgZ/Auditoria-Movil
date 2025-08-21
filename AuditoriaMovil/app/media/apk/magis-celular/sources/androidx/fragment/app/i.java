package androidx.fragment.app;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;
/* loaded from: classes.dex */
public abstract class i {
    public static final g b = new g();
    public g a = null;

    public abstract p a();

    public abstract void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract Fragment c(String str);

    public abstract Fragment d(Bundle bundle, String str);

    public g e() {
        if (this.a == null) {
            this.a = b;
        }
        return this.a;
    }

    public abstract List f();

    public abstract void g(int i, int i2);

    public abstract boolean h();

    public abstract void i(Bundle bundle, String str, Fragment fragment);

    public abstract Fragment.g j(Fragment fragment);

    public void k(g gVar) {
        this.a = gVar;
    }
}
