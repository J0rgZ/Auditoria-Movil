package androidx.core.widget;

import android.widget.ListView;
/* loaded from: classes.dex */
public abstract class p {
    public static boolean a(ListView listView, int i) {
        return listView.canScrollList(i);
    }

    public static void b(ListView listView, int i) {
        listView.scrollListBy(i);
    }
}
