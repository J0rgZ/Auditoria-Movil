package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import v.d1;
/* loaded from: classes.dex */
public abstract class p {
    public static n a = new b();
    public static ThreadLocal b = new ThreadLocal();
    public static ArrayList c = new ArrayList();

    /* loaded from: classes.dex */
    public static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        public n a;
        public ViewGroup b;

        /* renamed from: androidx.transition.p$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0041a extends o {
            public final /* synthetic */ androidx.collection.a a;

            public C0041a(androidx.collection.a aVar) {
                this.a = aVar;
            }

            @Override // androidx.transition.n.g
            public void c(n nVar) {
                ((ArrayList) this.a.get(a.this.b)).remove(nVar);
                nVar.removeListener(this);
            }
        }

        public a(n nVar, ViewGroup viewGroup) {
            this.a = nVar;
            this.b = viewGroup;
        }

        public final void a() {
            this.b.getViewTreeObserver().removeOnPreDrawListener(this);
            this.b.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!p.c.remove(this.b)) {
                return true;
            }
            androidx.collection.a b = p.b();
            ArrayList arrayList = (ArrayList) b.get(this.b);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList();
                b.put(this.b, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.a);
            this.a.addListener(new C0041a(b));
            this.a.captureValues(this.b, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((n) it.next()).resume(this.b);
                }
            }
            this.a.playTransition(this.b);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            p.c.remove(this.b);
            ArrayList arrayList = (ArrayList) p.b().get(this.b);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((n) it.next()).resume(this.b);
                }
            }
            this.a.clearValues(true);
        }
    }

    public static void a(ViewGroup viewGroup, n nVar) {
        if (!c.contains(viewGroup) && d1.Q(viewGroup)) {
            c.add(viewGroup);
            if (nVar == null) {
                nVar = a;
            }
            n mo4clone = nVar.mo4clone();
            d(viewGroup, mo4clone);
            m.b(viewGroup, null);
            c(viewGroup, mo4clone);
        }
    }

    public static androidx.collection.a b() {
        androidx.collection.a aVar;
        WeakReference weakReference = (WeakReference) b.get();
        if (weakReference != null && (aVar = (androidx.collection.a) weakReference.get()) != null) {
            return aVar;
        }
        androidx.collection.a aVar2 = new androidx.collection.a();
        b.set(new WeakReference(aVar2));
        return aVar2;
    }

    public static void c(ViewGroup viewGroup, n nVar) {
        if (nVar != null && viewGroup != null) {
            a aVar = new a(nVar, viewGroup);
            viewGroup.addOnAttachStateChangeListener(aVar);
            viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
        }
    }

    public static void d(ViewGroup viewGroup, n nVar) {
        ArrayList arrayList = (ArrayList) b().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((n) it.next()).pause(viewGroup);
            }
        }
        if (nVar != null) {
            nVar.captureValues(viewGroup, true);
        }
        m.a(viewGroup);
    }
}
