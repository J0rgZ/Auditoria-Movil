package androidx.work;

import androidx.work.b;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import u0.h;
/* loaded from: classes.dex */
public final class OverwritingInputMerger extends h {
    public b b(List list) {
        b.a aVar = new b.a();
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            hashMap.putAll(((b) it.next()).h());
        }
        aVar.d(hashMap);
        return aVar.a();
    }
}
