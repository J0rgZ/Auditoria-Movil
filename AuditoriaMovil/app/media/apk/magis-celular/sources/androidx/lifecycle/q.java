package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class q {
    public final Map a = new HashMap();
    public volatile boolean b = false;

    public static void b(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public final void a() {
        this.b = true;
        Map map = this.a;
        if (map != null) {
            synchronized (map) {
                for (Object obj : this.a.values()) {
                    b(obj);
                }
            }
        }
        c();
    }

    public void c() {
    }
}
