package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class c {
    public final Context a;
    public Map b;
    public Map c;

    public c(Context context) {
        this.a = context;
    }

    public final MenuItem c(MenuItem menuItem) {
        if (menuItem instanceof o.b) {
            o.b bVar = (o.b) menuItem;
            if (this.b == null) {
                this.b = new androidx.collection.a();
            }
            MenuItem menuItem2 = (MenuItem) this.b.get(menuItem);
            if (menuItem2 == null) {
                j jVar = new j(this.a, bVar);
                this.b.put(bVar, jVar);
                return jVar;
            }
            return menuItem2;
        }
        return menuItem;
    }

    public final SubMenu d(SubMenu subMenu) {
        return subMenu;
    }

    public final void e() {
        Map map = this.b;
        if (map != null) {
            map.clear();
        }
        Map map2 = this.c;
        if (map2 != null) {
            map2.clear();
        }
    }

    public final void f(int i) {
        Map map = this.b;
        if (map == null) {
            return;
        }
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i == ((MenuItem) it.next()).getGroupId()) {
                it.remove();
            }
        }
    }

    public final void g(int i) {
        Map map = this.b;
        if (map == null) {
            return;
        }
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i == ((MenuItem) it.next()).getItemId()) {
                it.remove();
                return;
            }
        }
    }
}
