package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.c;
import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class o extends androidx.viewpager.widget.a {
    public final i a;
    public final int b;
    public p c;
    public ArrayList d;
    public ArrayList e;
    public Fragment f;

    public o(i iVar) {
        this(iVar, 0);
    }

    @Override // androidx.viewpager.widget.a
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment.g gVar;
        Fragment fragment = (Fragment) obj;
        if (this.c == null) {
            this.c = this.a.a();
        }
        while (this.d.size() <= i) {
            this.d.add(null);
        }
        ArrayList arrayList = this.d;
        if (fragment.isAdded()) {
            gVar = this.a.j(fragment);
        } else {
            gVar = null;
        }
        arrayList.set(i, gVar);
        this.e.set(i, null);
        this.c.o(fragment);
        if (fragment == this.f) {
            this.f = null;
        }
    }

    @Override // androidx.viewpager.widget.a
    public void finishUpdate(ViewGroup viewGroup) {
        p pVar = this.c;
        if (pVar != null) {
            pVar.j();
            this.c = null;
        }
    }

    public abstract Fragment getItem(int i);

    @Override // androidx.viewpager.widget.a
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Fragment.g gVar;
        Fragment fragment;
        if (this.e.size() > i && (fragment = (Fragment) this.e.get(i)) != null) {
            return fragment;
        }
        if (this.c == null) {
            this.c = this.a.a();
        }
        Fragment item = getItem(i);
        if (this.d.size() > i && (gVar = (Fragment.g) this.d.get(i)) != null) {
            item.setInitialSavedState(gVar);
        }
        while (this.e.size() <= i) {
            this.e.add(null);
        }
        item.setMenuVisibility(false);
        if (this.b == 0) {
            item.setUserVisibleHint(false);
        }
        this.e.set(i, item);
        this.c.b(viewGroup.getId(), item);
        if (this.b == 1) {
            this.c.r(item, c.b.STARTED);
        }
        return item;
    }

    @Override // androidx.viewpager.widget.a
    public boolean isViewFromObject(View view, Object obj) {
        if (((Fragment) obj).getView() == view) {
            return true;
        }
        return false;
    }

    @Override // androidx.viewpager.widget.a
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(classLoader);
            Parcelable[] parcelableArray = bundle.getParcelableArray("states");
            this.d.clear();
            this.e.clear();
            if (parcelableArray != null) {
                for (Parcelable parcelable2 : parcelableArray) {
                    this.d.add((Fragment.g) parcelable2);
                }
            }
            for (String str : bundle.keySet()) {
                if (str.startsWith("f")) {
                    int parseInt = Integer.parseInt(str.substring(1));
                    Fragment d = this.a.d(bundle, str);
                    if (d != null) {
                        while (this.e.size() <= parseInt) {
                            this.e.add(null);
                        }
                        d.setMenuVisibility(false);
                        this.e.set(parseInt, d);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Bad fragment at key ");
                        sb.append(str);
                    }
                }
            }
        }
    }

    @Override // androidx.viewpager.widget.a
    public Parcelable saveState() {
        Bundle bundle;
        if (this.d.size() > 0) {
            bundle = new Bundle();
            Fragment.g[] gVarArr = new Fragment.g[this.d.size()];
            this.d.toArray(gVarArr);
            bundle.putParcelableArray("states", gVarArr);
        } else {
            bundle = null;
        }
        for (int i = 0; i < this.e.size(); i++) {
            Fragment fragment = (Fragment) this.e.get(i);
            if (fragment != null && fragment.isAdded()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                this.a.i(bundle, "f" + i, fragment);
            }
        }
        return bundle;
    }

    @Override // androidx.viewpager.widget.a
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.f;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(false);
                if (this.b == 1) {
                    if (this.c == null) {
                        this.c = this.a.a();
                    }
                    this.c.r(this.f, c.b.STARTED);
                } else {
                    this.f.setUserVisibleHint(false);
                }
            }
            fragment.setMenuVisibility(true);
            if (this.b == 1) {
                if (this.c == null) {
                    this.c = this.a.a();
                }
                this.c.r(fragment, c.b.RESUMED);
            } else {
                fragment.setUserVisibleHint(true);
            }
            this.f = fragment;
        }
    }

    @Override // androidx.viewpager.widget.a
    public void startUpdate(ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }

    public o(i iVar, int i) {
        this.c = null;
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = null;
        this.a = iVar;
        this.b = i;
    }
}
