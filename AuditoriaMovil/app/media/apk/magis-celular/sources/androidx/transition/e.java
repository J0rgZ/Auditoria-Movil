package androidx.transition;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.n;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class e extends androidx.fragment.app.x {

    /* loaded from: classes.dex */
    public class a extends n.f {
        public final /* synthetic */ Rect a;

        public a(Rect rect) {
            this.a = rect;
        }

        @Override // androidx.transition.n.f
        public Rect a(n nVar) {
            return this.a;
        }
    }

    /* loaded from: classes.dex */
    public class b implements n.g {
        public final /* synthetic */ View a;
        public final /* synthetic */ ArrayList b;

        public b(View view, ArrayList arrayList) {
            this.a = view;
            this.b = arrayList;
        }

        @Override // androidx.transition.n.g
        public void a(n nVar) {
        }

        @Override // androidx.transition.n.g
        public void b(n nVar) {
        }

        @Override // androidx.transition.n.g
        public void c(n nVar) {
            nVar.removeListener(this);
            this.a.setVisibility(8);
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                ((View) this.b.get(i)).setVisibility(0);
            }
        }

        @Override // androidx.transition.n.g
        public void d(n nVar) {
        }

        @Override // androidx.transition.n.g
        public void e(n nVar) {
        }
    }

    /* loaded from: classes.dex */
    public class c extends o {
        public final /* synthetic */ Object a;
        public final /* synthetic */ ArrayList b;
        public final /* synthetic */ Object c;
        public final /* synthetic */ ArrayList d;
        public final /* synthetic */ Object e;
        public final /* synthetic */ ArrayList f;

        public c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.a = obj;
            this.b = arrayList;
            this.c = obj2;
            this.d = arrayList2;
            this.e = obj3;
            this.f = arrayList3;
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void a(n nVar) {
            Object obj = this.a;
            if (obj != null) {
                e.this.q(obj, this.b, null);
            }
            Object obj2 = this.c;
            if (obj2 != null) {
                e.this.q(obj2, this.d, null);
            }
            Object obj3 = this.e;
            if (obj3 != null) {
                e.this.q(obj3, this.f, null);
            }
        }

        @Override // androidx.transition.n.g
        public void c(n nVar) {
            nVar.removeListener(this);
        }
    }

    /* loaded from: classes.dex */
    public class d extends n.f {
        public final /* synthetic */ Rect a;

        public d(Rect rect) {
            this.a = rect;
        }

        @Override // androidx.transition.n.f
        public Rect a(n nVar) {
            Rect rect = this.a;
            if (rect != null && !rect.isEmpty()) {
                return this.a;
            }
            return null;
        }
    }

    public static boolean B(n nVar) {
        if (androidx.fragment.app.x.l(nVar.getTargetIds()) && androidx.fragment.app.x.l(nVar.getTargetNames()) && androidx.fragment.app.x.l(nVar.getTargetTypes())) {
            return false;
        }
        return true;
    }

    @Override // androidx.fragment.app.x
    public Object A(Object obj) {
        if (obj == null) {
            return null;
        }
        r rVar = new r();
        rVar.w((n) obj);
        return rVar;
    }

    @Override // androidx.fragment.app.x
    public void a(Object obj, View view) {
        if (obj != null) {
            ((n) obj).addTarget(view);
        }
    }

    @Override // androidx.fragment.app.x
    public void b(Object obj, ArrayList arrayList) {
        n nVar = (n) obj;
        if (nVar == null) {
            return;
        }
        int i = 0;
        if (nVar instanceof r) {
            r rVar = (r) nVar;
            int z = rVar.z();
            while (i < z) {
                b(rVar.y(i), arrayList);
                i++;
            }
        } else if (!B(nVar) && androidx.fragment.app.x.l(nVar.getTargets())) {
            int size = arrayList.size();
            while (i < size) {
                nVar.addTarget((View) arrayList.get(i));
                i++;
            }
        }
    }

    @Override // androidx.fragment.app.x
    public void c(ViewGroup viewGroup, Object obj) {
        p.a(viewGroup, (n) obj);
    }

    @Override // androidx.fragment.app.x
    public boolean e(Object obj) {
        return obj instanceof n;
    }

    @Override // androidx.fragment.app.x
    public Object g(Object obj) {
        if (obj != null) {
            return ((n) obj).mo4clone();
        }
        return null;
    }

    @Override // androidx.fragment.app.x
    public Object m(Object obj, Object obj2, Object obj3) {
        n nVar = (n) obj;
        n nVar2 = (n) obj2;
        n nVar3 = (n) obj3;
        if (nVar != null && nVar2 != null) {
            nVar = new r().w(nVar).w(nVar2).H(1);
        } else if (nVar == null) {
            if (nVar2 != null) {
                nVar = nVar2;
            } else {
                nVar = null;
            }
        }
        if (nVar3 != null) {
            r rVar = new r();
            if (nVar != null) {
                rVar.w(nVar);
            }
            rVar.w(nVar3);
            return rVar;
        }
        return nVar;
    }

    @Override // androidx.fragment.app.x
    public Object n(Object obj, Object obj2, Object obj3) {
        r rVar = new r();
        if (obj != null) {
            rVar.w((n) obj);
        }
        if (obj2 != null) {
            rVar.w((n) obj2);
        }
        if (obj3 != null) {
            rVar.w((n) obj3);
        }
        return rVar;
    }

    @Override // androidx.fragment.app.x
    public void p(Object obj, View view) {
        if (obj != null) {
            ((n) obj).removeTarget(view);
        }
    }

    @Override // androidx.fragment.app.x
    public void q(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        int size;
        n nVar = (n) obj;
        int i = 0;
        if (nVar instanceof r) {
            r rVar = (r) nVar;
            int z = rVar.z();
            while (i < z) {
                q(rVar.y(i), arrayList, arrayList2);
                i++;
            }
        } else if (!B(nVar)) {
            List<View> targets = nVar.getTargets();
            if (targets.size() == arrayList.size() && targets.containsAll(arrayList)) {
                if (arrayList2 == null) {
                    size = 0;
                } else {
                    size = arrayList2.size();
                }
                while (i < size) {
                    nVar.addTarget((View) arrayList2.get(i));
                    i++;
                }
                for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                    nVar.removeTarget((View) arrayList.get(size2));
                }
            }
        }
    }

    @Override // androidx.fragment.app.x
    public void r(Object obj, View view, ArrayList arrayList) {
        ((n) obj).addListener(new b(view, arrayList));
    }

    @Override // androidx.fragment.app.x
    public void t(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3) {
        ((n) obj).addListener(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // androidx.fragment.app.x
    public void u(Object obj, Rect rect) {
        if (obj != null) {
            ((n) obj).setEpicenterCallback(new d(rect));
        }
    }

    @Override // androidx.fragment.app.x
    public void v(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            k(view, rect);
            ((n) obj).setEpicenterCallback(new a(rect));
        }
    }

    @Override // androidx.fragment.app.x
    public void y(Object obj, View view, ArrayList arrayList) {
        r rVar = (r) obj;
        List<View> targets = rVar.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            androidx.fragment.app.x.d(targets, (View) arrayList.get(i));
        }
        targets.add(view);
        arrayList.add(view);
        b(rVar, arrayList);
    }

    @Override // androidx.fragment.app.x
    public void z(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        r rVar = (r) obj;
        if (rVar != null) {
            rVar.getTargets().clear();
            rVar.getTargets().addAll(arrayList2);
            q(rVar, arrayList, arrayList2);
        }
    }
}
