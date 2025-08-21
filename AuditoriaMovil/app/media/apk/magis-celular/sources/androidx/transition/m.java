package androidx.transition;

import android.view.ViewGroup;
/* loaded from: classes.dex */
public abstract class m {
    public static m a(ViewGroup viewGroup) {
        androidx.appcompat.app.m.a(viewGroup.getTag(R$id.transition_current_scene));
        return null;
    }

    public static void b(ViewGroup viewGroup, m mVar) {
        viewGroup.setTag(R$id.transition_current_scene, mVar);
    }
}
