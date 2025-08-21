package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
/* loaded from: classes.dex */
public abstract class e {
    public Fragment a(Context context, String str, Bundle bundle) {
        return Fragment.instantiate(context, str, bundle);
    }

    public abstract View b(int i);

    public abstract boolean c();
}
