package androidx.fragment.app;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import v.d0;
import v.d1;
import v.s1;
/* loaded from: classes.dex */
public abstract class x {

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ int a;
        public final /* synthetic */ ArrayList b;
        public final /* synthetic */ ArrayList c;
        public final /* synthetic */ ArrayList d;
        public final /* synthetic */ ArrayList e;

        public a(int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
            this.a = i;
            this.b = arrayList;
            this.c = arrayList2;
            this.d = arrayList3;
            this.e = arrayList4;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (int i = 0; i < this.a; i++) {
                d1.J0((View) this.b.get(i), (String) this.c.get(i));
                d1.J0((View) this.d.get(i), (String) this.e.get(i));
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ ArrayList a;
        public final /* synthetic */ Map b;

        public b(ArrayList arrayList, Map map) {
            this.a = arrayList;
            this.b = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                View view = (View) this.a.get(i);
                String I = d1.I(view);
                if (I != null) {
                    d1.J0(view, x.i(this.b, I));
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public final /* synthetic */ ArrayList a;
        public final /* synthetic */ Map b;

        public c(ArrayList arrayList, Map map) {
            this.a = arrayList;
            this.b = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                View view = (View) this.a.get(i);
                d1.J0(view, (String) this.b.get(d1.I(view)));
            }
        }
    }

    public static void d(List list, View view) {
        int size = list.size();
        if (h(list, view, size)) {
            return;
        }
        list.add(view);
        for (int i = size; i < list.size(); i++) {
            View view2 = (View) list.get(i);
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = viewGroup.getChildAt(i2);
                    if (!h(list, childAt, size)) {
                        list.add(childAt);
                    }
                }
            }
        }
    }

    public static boolean h(List list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }

    public static String i(Map map, String str) {
        for (Map.Entry entry : map.entrySet()) {
            if (str.equals(entry.getValue())) {
                return (String) entry.getKey();
            }
        }
        return null;
    }

    public static boolean l(List list) {
        if (list != null && !list.isEmpty()) {
            return false;
        }
        return true;
    }

    public abstract Object A(Object obj);

    public abstract void a(Object obj, View view);

    public abstract void b(Object obj, ArrayList arrayList);

    public abstract void c(ViewGroup viewGroup, Object obj);

    public abstract boolean e(Object obj);

    public void f(ArrayList arrayList, View view) {
        if (view.getVisibility() == 0) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (s1.a(viewGroup)) {
                    arrayList.add(viewGroup);
                    return;
                }
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    f(arrayList, viewGroup.getChildAt(i));
                }
                return;
            }
            arrayList.add(view);
        }
    }

    public abstract Object g(Object obj);

    public void j(Map map, View view) {
        if (view.getVisibility() == 0) {
            String I = d1.I(view);
            if (I != null) {
                map.put(I, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    j(map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    public void k(View view, Rect rect) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        rect.set(i, iArr[1], view.getWidth() + i, iArr[1] + view.getHeight());
    }

    public abstract Object m(Object obj, Object obj2, Object obj3);

    public abstract Object n(Object obj, Object obj2, Object obj3);

    public ArrayList o(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view = (View) arrayList.get(i);
            arrayList2.add(d1.I(view));
            d1.J0(view, (String) null);
        }
        return arrayList2;
    }

    public abstract void p(Object obj, View view);

    public abstract void q(Object obj, ArrayList arrayList, ArrayList arrayList2);

    public abstract void r(Object obj, View view, ArrayList arrayList);

    public void s(ViewGroup viewGroup, ArrayList arrayList, Map map) {
        d0.a(viewGroup, new c(arrayList, map));
    }

    public abstract void t(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3);

    public abstract void u(Object obj, Rect rect);

    public abstract void v(Object obj, View view);

    public void w(View view, ArrayList arrayList, Map map) {
        d0.a(view, new b(arrayList, map));
    }

    public void x(View view, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Map map) {
        int size = arrayList2.size();
        ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < size; i++) {
            View view2 = (View) arrayList.get(i);
            String I = d1.I(view2);
            arrayList4.add(I);
            if (I != null) {
                d1.J0(view2, (String) null);
                String str = (String) map.get(I);
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    } else if (str.equals(arrayList3.get(i2))) {
                        d1.J0((View) arrayList2.get(i2), I);
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        }
        d0.a(view, new a(size, arrayList2, arrayList3, arrayList, arrayList4));
    }

    public abstract void y(Object obj, View view, ArrayList arrayList);

    public abstract void z(Object obj, ArrayList arrayList, ArrayList arrayList2);
}
