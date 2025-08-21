package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.n;
import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class b implements m {
    public Context a;
    public Context b;
    public g c;
    public LayoutInflater d;
    public LayoutInflater e;
    public m.a f;
    public int g;
    public int h;
    public n i;
    public int j;

    public b(Context context, int i, int i2) {
        this.a = context;
        this.d = LayoutInflater.from(context);
        this.g = i;
        this.h = i2;
    }

    public void a(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.i).addView(view, i);
    }

    public abstract void b(i iVar, n.a aVar);

    public n.a c(ViewGroup viewGroup) {
        return (n.a) this.d.inflate(this.h, viewGroup, false);
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean collapseItemActionView(g gVar, i iVar) {
        return false;
    }

    public boolean d(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    public m.a e() {
        return this.f;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean expandItemActionView(g gVar, i iVar) {
        return false;
    }

    public View f(i iVar, View view, ViewGroup viewGroup) {
        n.a c;
        if (view instanceof n.a) {
            c = (n.a) view;
        } else {
            c = c(viewGroup);
        }
        b(iVar, c);
        return (View) c;
    }

    public n g(ViewGroup viewGroup) {
        if (this.i == null) {
            n nVar = (n) this.d.inflate(this.g, viewGroup, false);
            this.i = nVar;
            nVar.initialize(this.c);
            updateMenuView(true);
        }
        return this.i;
    }

    @Override // androidx.appcompat.view.menu.m
    public int getId() {
        return this.j;
    }

    public void h(int i) {
        this.j = i;
    }

    public abstract boolean i(int i, i iVar);

    @Override // androidx.appcompat.view.menu.m
    public void initForMenu(Context context, g gVar) {
        this.b = context;
        this.e = LayoutInflater.from(context);
        this.c = gVar;
    }

    @Override // androidx.appcompat.view.menu.m
    public void onCloseMenu(g gVar, boolean z) {
        m.a aVar = this.f;
        if (aVar != null) {
            aVar.onCloseMenu(gVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean onSubMenuSelected(r rVar) {
        m.a aVar = this.f;
        if (aVar != null) {
            return aVar.a(rVar);
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void setCallback(m.a aVar) {
        this.f = aVar;
    }

    @Override // androidx.appcompat.view.menu.m
    public void updateMenuView(boolean z) {
        i iVar;
        ViewGroup viewGroup = (ViewGroup) this.i;
        if (viewGroup == null) {
            return;
        }
        g gVar = this.c;
        int i = 0;
        if (gVar != null) {
            gVar.flagActionItems();
            ArrayList<i> visibleItems = this.c.getVisibleItems();
            int size = visibleItems.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                i iVar2 = visibleItems.get(i3);
                if (i(i2, iVar2)) {
                    View childAt = viewGroup.getChildAt(i2);
                    if (childAt instanceof n.a) {
                        iVar = ((n.a) childAt).getItemData();
                    } else {
                        iVar = null;
                    }
                    View f = f(iVar2, childAt, viewGroup);
                    if (iVar2 != iVar) {
                        f.setPressed(false);
                        f.jumpDrawablesToCurrentState();
                    }
                    if (f != childAt) {
                        a(f, i2);
                    }
                    i2++;
                }
            }
            i = i2;
        }
        while (i < viewGroup.getChildCount()) {
            if (!d(viewGroup, i)) {
                i++;
            }
        }
    }
}
