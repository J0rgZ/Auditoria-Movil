package androidx.savedstate;

import android.os.Bundle;
import androidx.appcompat.app.m;
import androidx.lifecycle.c;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import g.b;
import java.util.Map;
/* loaded from: classes.dex */
public final class SavedStateRegistry {
    public Bundle b;
    public boolean c;
    public g.b a = new g.b();
    public boolean d = true;

    /* loaded from: classes.dex */
    public interface a {
    }

    public Bundle a(String str) {
        if (this.c) {
            Bundle bundle = this.b;
            if (bundle == null) {
                return null;
            }
            Bundle bundle2 = bundle.getBundle(str);
            this.b.remove(str);
            if (this.b.isEmpty()) {
                this.b = null;
            }
            return bundle2;
        }
        throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
    }

    public void b(c cVar, Bundle bundle) {
        if (!this.c) {
            if (bundle != null) {
                this.b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
            }
            cVar.a(new d() { // from class: androidx.savedstate.SavedStateRegistry.1
                @Override // androidx.lifecycle.d
                public void a(f fVar, c.a aVar) {
                    if (aVar == c.a.ON_START) {
                        SavedStateRegistry.this.d = true;
                    } else if (aVar == c.a.ON_STOP) {
                        SavedStateRegistry.this.d = false;
                    }
                }
            });
            this.c = true;
            return;
        }
        throw new IllegalStateException("SavedStateRegistry was already restored.");
    }

    public void c(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        b.d c = this.a.c();
        if (!c.hasNext()) {
            bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
            return;
        }
        Map.Entry entry = (Map.Entry) c.next();
        String str = (String) entry.getKey();
        m.a(entry.getValue());
        throw null;
    }
}
