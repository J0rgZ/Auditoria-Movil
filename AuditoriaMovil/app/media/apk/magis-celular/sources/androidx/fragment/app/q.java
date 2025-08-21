package androidx.fragment.app;

import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.p;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import v.d0;
import v.d1;
/* loaded from: classes.dex */
public abstract class q {
    public static final int[] a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};
    public static final x b;
    public static final x c;

    /* loaded from: classes.dex */
    public static class a implements Runnable {
        public final /* synthetic */ ArrayList a;

        public a(ArrayList arrayList) {
            this.a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            q.A(this.a, 4);
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Runnable {
        public final /* synthetic */ Object a;
        public final /* synthetic */ x b;
        public final /* synthetic */ View c;
        public final /* synthetic */ Fragment d;
        public final /* synthetic */ ArrayList e;
        public final /* synthetic */ ArrayList f;
        public final /* synthetic */ ArrayList g;
        public final /* synthetic */ Object h;

        public b(Object obj, x xVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.a = obj;
            this.b = xVar;
            this.c = view;
            this.d = fragment;
            this.e = arrayList;
            this.f = arrayList2;
            this.g = arrayList3;
            this.h = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.a;
            if (obj != null) {
                this.b.p(obj, this.c);
                this.f.addAll(q.k(this.b, this.a, this.d, this.e, this.c));
            }
            if (this.g != null) {
                if (this.h != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(this.c);
                    this.b.q(this.h, this.g, arrayList);
                }
                this.g.clear();
                this.g.add(this.c);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c implements Runnable {
        public final /* synthetic */ Fragment a;
        public final /* synthetic */ Fragment b;
        public final /* synthetic */ boolean c;
        public final /* synthetic */ androidx.collection.a d;
        public final /* synthetic */ View e;
        public final /* synthetic */ x f;
        public final /* synthetic */ Rect g;

        public c(Fragment fragment, Fragment fragment2, boolean z, androidx.collection.a aVar, View view, x xVar, Rect rect) {
            this.a = fragment;
            this.b = fragment2;
            this.c = z;
            this.d = aVar;
            this.e = view;
            this.f = xVar;
            this.g = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            q.f(this.a, this.b, this.c, this.d, false);
            View view = this.e;
            if (view != null) {
                this.f.k(view, this.g);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class d implements Runnable {
        public final /* synthetic */ x a;
        public final /* synthetic */ androidx.collection.a b;
        public final /* synthetic */ Object c;
        public final /* synthetic */ e d;
        public final /* synthetic */ ArrayList e;
        public final /* synthetic */ View f;
        public final /* synthetic */ Fragment g;
        public final /* synthetic */ Fragment h;
        public final /* synthetic */ boolean i;
        public final /* synthetic */ ArrayList j;
        public final /* synthetic */ Object k;
        public final /* synthetic */ Rect l;

        public d(x xVar, androidx.collection.a aVar, Object obj, e eVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.a = xVar;
            this.b = aVar;
            this.c = obj;
            this.d = eVar;
            this.e = arrayList;
            this.f = view;
            this.g = fragment;
            this.h = fragment2;
            this.i = z;
            this.j = arrayList2;
            this.k = obj2;
            this.l = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            androidx.collection.a h = q.h(this.a, this.b, this.c, this.d);
            if (h != null) {
                this.e.addAll(h.values());
                this.e.add(this.f);
            }
            q.f(this.g, this.h, this.i, h, false);
            Object obj = this.c;
            if (obj != null) {
                this.a.z(obj, this.j, this.e);
                View s2 = q.s(h, this.d, this.k, this.i);
                if (s2 != null) {
                    this.a.k(s2, this.l);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class e {
        public Fragment a;
        public boolean b;
        public androidx.fragment.app.a c;
        public Fragment d;
        public boolean e;
        public androidx.fragment.app.a f;
    }

    static {
        w wVar;
        if (Build.VERSION.SDK_INT >= 21) {
            wVar = new w();
        } else {
            wVar = null;
        }
        b = wVar;
        c = w();
    }

    public static void A(ArrayList arrayList, int i) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((View) arrayList.get(size)).setVisibility(i);
        }
    }

    public static void B(j jVar, ArrayList arrayList, ArrayList arrayList2, int i, int i2, boolean z) {
        if (jVar.p < 1) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        for (int i3 = i; i3 < i2; i3++) {
            androidx.fragment.app.a aVar = (androidx.fragment.app.a) arrayList.get(i3);
            if (((Boolean) arrayList2.get(i3)).booleanValue()) {
                e(aVar, sparseArray, z);
            } else {
                c(aVar, sparseArray, z);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(jVar.q.e());
            int size = sparseArray.size();
            for (int i4 = 0; i4 < size; i4++) {
                int keyAt = sparseArray.keyAt(i4);
                androidx.collection.a d2 = d(keyAt, arrayList, arrayList2, i, i2);
                e eVar = (e) sparseArray.valueAt(i4);
                if (z) {
                    o(jVar, keyAt, eVar, view, d2);
                } else {
                    n(jVar, keyAt, eVar, view, d2);
                }
            }
        }
    }

    public static void a(ArrayList arrayList, androidx.collection.a aVar, Collection collection) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View view = (View) aVar.valueAt(size);
            if (collection.contains(d1.I(view))) {
                arrayList.add(view);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0041, code lost:
        if (r10.mAdded != false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0076, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0092, code lost:
        if (r10.mHidden == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0094, code lost:
        r1 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00b0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00d5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00e7 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(androidx.fragment.app.a r16, androidx.fragment.app.p.a r17, android.util.SparseArray r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.q.b(androidx.fragment.app.a, androidx.fragment.app.p$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(androidx.fragment.app.a aVar, SparseArray sparseArray, boolean z) {
        int size = aVar.a.size();
        for (int i = 0; i < size; i++) {
            b(aVar, (p.a) aVar.a.get(i), sparseArray, false, z);
        }
    }

    public static androidx.collection.a d(int i, ArrayList arrayList, ArrayList arrayList2, int i2, int i3) {
        ArrayList arrayList3;
        ArrayList arrayList4;
        androidx.collection.a aVar = new androidx.collection.a();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            androidx.fragment.app.a aVar2 = (androidx.fragment.app.a) arrayList.get(i4);
            if (aVar2.B(i)) {
                boolean booleanValue = ((Boolean) arrayList2.get(i4)).booleanValue();
                ArrayList arrayList5 = aVar2.o;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (booleanValue) {
                        arrayList3 = aVar2.o;
                        arrayList4 = aVar2.p;
                    } else {
                        ArrayList arrayList6 = aVar2.o;
                        arrayList3 = aVar2.p;
                        arrayList4 = arrayList6;
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        String str = (String) arrayList4.get(i5);
                        String str2 = (String) arrayList3.get(i5);
                        String str3 = (String) aVar.remove(str2);
                        if (str3 != null) {
                            aVar.put(str, str3);
                        } else {
                            aVar.put(str, str2);
                        }
                    }
                }
            }
        }
        return aVar;
    }

    public static void e(androidx.fragment.app.a aVar, SparseArray sparseArray, boolean z) {
        if (!aVar.f19s.r.c()) {
            return;
        }
        for (int size = aVar.a.size() - 1; size >= 0; size--) {
            b(aVar, (p.a) aVar.a.get(size), sparseArray, true, z);
        }
    }

    public static void f(Fragment fragment, Fragment fragment2, boolean z, androidx.collection.a aVar, boolean z2) {
        if (z) {
            fragment2.getEnterTransitionCallback();
        } else {
            fragment.getEnterTransitionCallback();
        }
    }

    public static boolean g(x xVar, List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!xVar.e(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static androidx.collection.a h(x xVar, androidx.collection.a aVar, Object obj, e eVar) {
        ArrayList arrayList;
        Fragment fragment = eVar.a;
        View view = fragment.getView();
        if (!aVar.isEmpty() && obj != null && view != null) {
            androidx.collection.a aVar2 = new androidx.collection.a();
            xVar.j(aVar2, view);
            androidx.fragment.app.a aVar3 = eVar.c;
            if (eVar.b) {
                fragment.getExitTransitionCallback();
                arrayList = aVar3.o;
            } else {
                fragment.getEnterTransitionCallback();
                arrayList = aVar3.p;
            }
            if (arrayList != null) {
                aVar2.retainAll(arrayList);
                aVar2.retainAll(aVar.values());
            }
            x(aVar, aVar2);
            return aVar2;
        }
        aVar.clear();
        return null;
    }

    public static androidx.collection.a i(x xVar, androidx.collection.a aVar, Object obj, e eVar) {
        ArrayList arrayList;
        if (!aVar.isEmpty() && obj != null) {
            Fragment fragment = eVar.d;
            androidx.collection.a aVar2 = new androidx.collection.a();
            xVar.j(aVar2, fragment.requireView());
            androidx.fragment.app.a aVar3 = eVar.f;
            if (eVar.e) {
                fragment.getEnterTransitionCallback();
                arrayList = aVar3.p;
            } else {
                fragment.getExitTransitionCallback();
                arrayList = aVar3.o;
            }
            aVar2.retainAll(arrayList);
            aVar.retainAll(aVar2.keySet());
            return aVar2;
        }
        aVar.clear();
        return null;
    }

    public static x j(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            Object enterTransition = fragment2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        x xVar = b;
        if (xVar != null && g(xVar, arrayList)) {
            return xVar;
        }
        x xVar2 = c;
        if (xVar2 != null && g(xVar2, arrayList)) {
            return xVar2;
        }
        if (xVar == null && xVar2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    public static ArrayList k(x xVar, Object obj, Fragment fragment, ArrayList arrayList, View view) {
        if (obj != null) {
            ArrayList arrayList2 = new ArrayList();
            View view2 = fragment.getView();
            if (view2 != null) {
                xVar.f(arrayList2, view2);
            }
            if (arrayList != null) {
                arrayList2.removeAll(arrayList);
            }
            if (!arrayList2.isEmpty()) {
                arrayList2.add(view);
                xVar.b(obj, arrayList2);
                return arrayList2;
            }
            return arrayList2;
        }
        return null;
    }

    public static Object l(x xVar, ViewGroup viewGroup, View view, androidx.collection.a aVar, e eVar, ArrayList arrayList, ArrayList arrayList2, Object obj, Object obj2) {
        Object t;
        androidx.collection.a aVar2;
        Object obj3;
        Rect rect;
        Fragment fragment = eVar.a;
        Fragment fragment2 = eVar.d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.b;
        if (aVar.isEmpty()) {
            aVar2 = aVar;
            t = null;
        } else {
            t = t(xVar, fragment, fragment2, z);
            aVar2 = aVar;
        }
        androidx.collection.a i = i(xVar, aVar2, t, eVar);
        if (aVar.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(i.values());
            obj3 = t;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, i, true);
        if (obj3 != null) {
            rect = new Rect();
            xVar.y(obj3, view, arrayList);
            z(xVar, obj3, obj2, i, eVar.e, eVar.f);
            if (obj != null) {
                xVar.u(obj, rect);
            }
        } else {
            rect = null;
        }
        d0.a(viewGroup, new d(xVar, aVar, obj3, eVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    public static Object m(x xVar, ViewGroup viewGroup, View view, androidx.collection.a aVar, e eVar, ArrayList arrayList, ArrayList arrayList2, Object obj, Object obj2) {
        Object t;
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = eVar.a;
        Fragment fragment2 = eVar.d;
        if (fragment != null) {
            fragment.requireView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.b;
        if (aVar.isEmpty()) {
            t = null;
        } else {
            t = t(xVar, fragment, fragment2, z);
        }
        androidx.collection.a i = i(xVar, aVar, t, eVar);
        androidx.collection.a h = h(xVar, aVar, t, eVar);
        if (aVar.isEmpty()) {
            if (i != null) {
                i.clear();
            }
            if (h != null) {
                h.clear();
            }
            obj3 = null;
        } else {
            a(arrayList, i, aVar.keySet());
            a(arrayList2, h, aVar.values());
            obj3 = t;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, i, true);
        if (obj3 != null) {
            arrayList2.add(view);
            xVar.y(obj3, view, arrayList);
            z(xVar, obj3, obj2, i, eVar.e, eVar.f);
            Rect rect2 = new Rect();
            View s2 = s(h, eVar, obj, z);
            if (s2 != null) {
                xVar.u(obj, rect2);
            }
            rect = rect2;
            view2 = s2;
        } else {
            view2 = null;
            rect = null;
        }
        d0.a(viewGroup, new c(fragment, fragment2, z, h, view2, xVar, rect));
        return obj3;
    }

    public static void n(j jVar, int i, e eVar, View view, androidx.collection.a aVar) {
        ViewGroup viewGroup;
        Fragment fragment;
        Fragment fragment2;
        x j;
        Object obj;
        Object obj2;
        if (jVar.r.c()) {
            viewGroup = (ViewGroup) jVar.r.b(i);
        } else {
            viewGroup = null;
        }
        if (viewGroup == null || (j = j((fragment2 = eVar.d), (fragment = eVar.a))) == null) {
            return;
        }
        boolean z = eVar.b;
        boolean z2 = eVar.e;
        Object q = q(j, fragment, z);
        Object r = r(j, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Object l = l(j, viewGroup, view, aVar, eVar, arrayList, arrayList2, q, r);
        if (q == null && l == null) {
            obj = r;
            if (obj == null) {
                return;
            }
        } else {
            obj = r;
        }
        ArrayList k = k(j, obj, fragment2, arrayList, view);
        if (k != null && !k.isEmpty()) {
            obj2 = obj;
        } else {
            obj2 = null;
        }
        j.a(q, view);
        Object u = u(j, q, obj2, l, fragment, eVar.b);
        if (u != null) {
            ArrayList arrayList3 = new ArrayList();
            j.t(u, q, arrayList3, obj2, k, l, arrayList2);
            y(j, viewGroup, fragment, view, arrayList2, q, arrayList3, obj2, k);
            j.w(viewGroup, arrayList2, aVar);
            j.c(viewGroup, u);
            j.s(viewGroup, arrayList2, aVar);
        }
    }

    public static void o(j jVar, int i, e eVar, View view, androidx.collection.a aVar) {
        ViewGroup viewGroup;
        Fragment fragment;
        Fragment fragment2;
        x j;
        Object obj;
        if (jVar.r.c()) {
            viewGroup = (ViewGroup) jVar.r.b(i);
        } else {
            viewGroup = null;
        }
        ViewGroup viewGroup2 = viewGroup;
        if (viewGroup2 == null || (j = j((fragment2 = eVar.d), (fragment = eVar.a))) == null) {
            return;
        }
        boolean z = eVar.b;
        boolean z2 = eVar.e;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Object q = q(j, fragment, z);
        Object r = r(j, fragment2, z2);
        Object m = m(j, viewGroup2, view, aVar, eVar, arrayList2, arrayList, q, r);
        if (q == null && m == null) {
            obj = r;
            if (obj == null) {
                return;
            }
        } else {
            obj = r;
        }
        ArrayList k = k(j, obj, fragment2, arrayList2, view);
        ArrayList k2 = k(j, q, fragment, arrayList, view);
        A(k2, 4);
        Object u = u(j, q, obj, m, fragment, z);
        if (u != null) {
            v(j, obj, fragment2, k);
            ArrayList o = j.o(arrayList);
            j.t(u, q, k2, obj, k, m, arrayList);
            j.c(viewGroup2, u);
            j.x(viewGroup2, arrayList2, arrayList, o, aVar);
            A(k2, 0);
            j.z(m, arrayList2, arrayList);
        }
    }

    public static e p(e eVar, SparseArray sparseArray, int i) {
        if (eVar == null) {
            e eVar2 = new e();
            sparseArray.put(i, eVar2);
            return eVar2;
        }
        return eVar;
    }

    public static Object q(x xVar, Fragment fragment, boolean z) {
        Object enterTransition;
        if (fragment == null) {
            return null;
        }
        if (z) {
            enterTransition = fragment.getReenterTransition();
        } else {
            enterTransition = fragment.getEnterTransition();
        }
        return xVar.g(enterTransition);
    }

    public static Object r(x xVar, Fragment fragment, boolean z) {
        Object exitTransition;
        if (fragment == null) {
            return null;
        }
        if (z) {
            exitTransition = fragment.getReturnTransition();
        } else {
            exitTransition = fragment.getExitTransition();
        }
        return xVar.g(exitTransition);
    }

    public static View s(androidx.collection.a aVar, e eVar, Object obj, boolean z) {
        ArrayList arrayList;
        String str;
        androidx.fragment.app.a aVar2 = eVar.c;
        if (obj != null && aVar != null && (arrayList = aVar2.o) != null && !arrayList.isEmpty()) {
            if (z) {
                str = (String) aVar2.o.get(0);
            } else {
                str = (String) aVar2.p.get(0);
            }
            return (View) aVar.get(str);
        }
        return null;
    }

    public static Object t(x xVar, Fragment fragment, Fragment fragment2, boolean z) {
        Object sharedElementEnterTransition;
        if (fragment != null && fragment2 != null) {
            if (z) {
                sharedElementEnterTransition = fragment2.getSharedElementReturnTransition();
            } else {
                sharedElementEnterTransition = fragment.getSharedElementEnterTransition();
            }
            return xVar.A(xVar.g(sharedElementEnterTransition));
        }
        return null;
    }

    public static Object u(x xVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        boolean z2;
        if (obj != null && obj2 != null && fragment != null) {
            if (z) {
                z2 = fragment.getAllowReturnTransitionOverlap();
            } else {
                z2 = fragment.getAllowEnterTransitionOverlap();
            }
        } else {
            z2 = true;
        }
        if (z2) {
            return xVar.n(obj2, obj, obj3);
        }
        return xVar.m(obj2, obj, obj3);
    }

    public static void v(x xVar, Object obj, Fragment fragment, ArrayList arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            xVar.r(obj, fragment.getView(), arrayList);
            d0.a(fragment.mContainer, new a(arrayList));
        }
    }

    public static x w() {
        try {
            return (x) androidx.transition.e.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void x(androidx.collection.a aVar, androidx.collection.a aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey((String) aVar.valueAt(size))) {
                aVar.removeAt(size);
            }
        }
    }

    public static void y(x xVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList arrayList, Object obj, ArrayList arrayList2, Object obj2, ArrayList arrayList3) {
        d0.a(viewGroup, new b(obj, xVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    public static void z(x xVar, Object obj, Object obj2, androidx.collection.a aVar, boolean z, androidx.fragment.app.a aVar2) {
        String str;
        ArrayList arrayList = aVar2.o;
        if (arrayList != null && !arrayList.isEmpty()) {
            if (z) {
                str = (String) aVar2.p.get(0);
            } else {
                str = (String) aVar2.o.get(0);
            }
            View view = (View) aVar.get(str);
            xVar.v(obj, view);
            if (obj2 != null) {
                xVar.v(obj2, view);
            }
        }
    }
}
