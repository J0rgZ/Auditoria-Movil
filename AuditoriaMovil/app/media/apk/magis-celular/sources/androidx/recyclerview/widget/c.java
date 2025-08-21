package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import v.d1;
/* loaded from: classes.dex */
public class c extends q {

    /* renamed from: s  reason: collision with root package name */
    public static TimeInterpolator f26s;
    public ArrayList h = new ArrayList();
    public ArrayList i = new ArrayList();
    public ArrayList j = new ArrayList();
    public ArrayList k = new ArrayList();
    public ArrayList l = new ArrayList();
    public ArrayList m = new ArrayList();
    public ArrayList n = new ArrayList();
    public ArrayList o = new ArrayList();
    public ArrayList p = new ArrayList();
    public ArrayList q = new ArrayList();
    public ArrayList r = new ArrayList();

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ ArrayList a;

        public a(ArrayList arrayList) {
            this.a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                j jVar = (j) it.next();
                c.this.S(jVar.a, jVar.b, jVar.c, jVar.d, jVar.e);
            }
            this.a.clear();
            c.this.m.remove(this.a);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ ArrayList a;

        public b(ArrayList arrayList) {
            this.a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                c.this.R((i) it.next());
            }
            this.a.clear();
            c.this.n.remove(this.a);
        }
    }

    /* renamed from: androidx.recyclerview.widget.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0029c implements Runnable {
        public final /* synthetic */ ArrayList a;

        public RunnableC0029c(ArrayList arrayList) {
            this.a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                c.this.Q((RecyclerView.d0) it.next());
            }
            this.a.clear();
            c.this.l.remove(this.a);
        }
    }

    /* loaded from: classes.dex */
    public class d extends AnimatorListenerAdapter {
        public final /* synthetic */ RecyclerView.d0 a;
        public final /* synthetic */ ViewPropertyAnimator b;
        public final /* synthetic */ View c;

        public d(RecyclerView.d0 d0Var, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.a = d0Var;
            this.b = viewPropertyAnimator;
            this.c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.b.setListener(null);
            this.c.setAlpha(1.0f);
            c.this.G(this.a);
            c.this.q.remove(this.a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.H(this.a);
        }
    }

    /* loaded from: classes.dex */
    public class e extends AnimatorListenerAdapter {
        public final /* synthetic */ RecyclerView.d0 a;
        public final /* synthetic */ View b;
        public final /* synthetic */ ViewPropertyAnimator c;

        public e(RecyclerView.d0 d0Var, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.a = d0Var;
            this.b = view;
            this.c = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.b.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.c.setListener(null);
            c.this.A(this.a);
            c.this.o.remove(this.a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.B(this.a);
        }
    }

    /* loaded from: classes.dex */
    public class f extends AnimatorListenerAdapter {
        public final /* synthetic */ RecyclerView.d0 a;
        public final /* synthetic */ int b;
        public final /* synthetic */ View c;
        public final /* synthetic */ int d;
        public final /* synthetic */ ViewPropertyAnimator e;

        public f(RecyclerView.d0 d0Var, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
            this.a = d0Var;
            this.b = i;
            this.c = view;
            this.d = i2;
            this.e = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.b != 0) {
                this.c.setTranslationX(0.0f);
            }
            if (this.d != 0) {
                this.c.setTranslationY(0.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.e.setListener(null);
            c.this.E(this.a);
            c.this.p.remove(this.a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.F(this.a);
        }
    }

    /* loaded from: classes.dex */
    public class g extends AnimatorListenerAdapter {
        public final /* synthetic */ i a;
        public final /* synthetic */ ViewPropertyAnimator b;
        public final /* synthetic */ View c;

        public g(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.a = iVar;
            this.b = viewPropertyAnimator;
            this.c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.b.setListener(null);
            this.c.setAlpha(1.0f);
            this.c.setTranslationX(0.0f);
            this.c.setTranslationY(0.0f);
            c.this.C(this.a.a, true);
            c.this.r.remove(this.a.a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.D(this.a.a, true);
        }
    }

    /* loaded from: classes.dex */
    public class h extends AnimatorListenerAdapter {
        public final /* synthetic */ i a;
        public final /* synthetic */ ViewPropertyAnimator b;
        public final /* synthetic */ View c;

        public h(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.a = iVar;
            this.b = viewPropertyAnimator;
            this.c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.b.setListener(null);
            this.c.setAlpha(1.0f);
            this.c.setTranslationX(0.0f);
            this.c.setTranslationY(0.0f);
            c.this.C(this.a.b, false);
            c.this.r.remove(this.a.b);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.D(this.a.b, false);
        }
    }

    /* loaded from: classes.dex */
    public static class j {
        public RecyclerView.d0 a;
        public int b;
        public int c;
        public int d;
        public int e;

        public j(RecyclerView.d0 d0Var, int i, int i2, int i3, int i4) {
            this.a = d0Var;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    public void Q(RecyclerView.d0 d0Var) {
        View view = d0Var.itemView;
        ViewPropertyAnimator animate = view.animate();
        this.o.add(d0Var);
        animate.alpha(1.0f).setDuration(l()).setListener(new e(d0Var, view, animate)).start();
    }

    public void R(i iVar) {
        View view;
        RecyclerView.d0 d0Var = iVar.a;
        View view2 = null;
        if (d0Var == null) {
            view = null;
        } else {
            view = d0Var.itemView;
        }
        RecyclerView.d0 d0Var2 = iVar.b;
        if (d0Var2 != null) {
            view2 = d0Var2.itemView;
        }
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(m());
            this.r.add(iVar.a);
            duration.translationX(iVar.e - iVar.c);
            duration.translationY(iVar.f - iVar.d);
            duration.alpha(0.0f).setListener(new g(iVar, duration, view)).start();
        }
        if (view2 != null) {
            ViewPropertyAnimator animate = view2.animate();
            this.r.add(iVar.b);
            animate.translationX(0.0f).translationY(0.0f).setDuration(m()).alpha(1.0f).setListener(new h(iVar, animate, view2)).start();
        }
    }

    public void S(RecyclerView.d0 d0Var, int i2, int i3, int i4, int i5) {
        View view = d0Var.itemView;
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        if (i6 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i7 != 0) {
            view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator animate = view.animate();
        this.p.add(d0Var);
        animate.setDuration(n()).setListener(new f(d0Var, i6, view, i7, animate)).start();
    }

    public final void T(RecyclerView.d0 d0Var) {
        View view = d0Var.itemView;
        ViewPropertyAnimator animate = view.animate();
        this.q.add(d0Var);
        animate.setDuration(o()).alpha(0.0f).setListener(new d(d0Var, animate, view)).start();
    }

    public void U(List list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ((RecyclerView.d0) list.get(size)).itemView.animate().cancel();
        }
    }

    public void V() {
        if (!p()) {
            i();
        }
    }

    public final void W(List list, RecyclerView.d0 d0Var) {
        for (int size = list.size() - 1; size >= 0; size--) {
            i iVar = (i) list.get(size);
            if (Y(iVar, d0Var) && iVar.a == null && iVar.b == null) {
                list.remove(iVar);
            }
        }
    }

    public final void X(i iVar) {
        RecyclerView.d0 d0Var = iVar.a;
        if (d0Var != null) {
            Y(iVar, d0Var);
        }
        RecyclerView.d0 d0Var2 = iVar.b;
        if (d0Var2 != null) {
            Y(iVar, d0Var2);
        }
    }

    public final boolean Y(i iVar, RecyclerView.d0 d0Var) {
        boolean z = false;
        if (iVar.b == d0Var) {
            iVar.b = null;
        } else if (iVar.a != d0Var) {
            return false;
        } else {
            iVar.a = null;
            z = true;
        }
        d0Var.itemView.setAlpha(1.0f);
        d0Var.itemView.setTranslationX(0.0f);
        d0Var.itemView.setTranslationY(0.0f);
        C(d0Var, z);
        return true;
    }

    public final void Z(RecyclerView.d0 d0Var) {
        if (f26s == null) {
            f26s = new ValueAnimator().getInterpolator();
        }
        d0Var.itemView.animate().setInterpolator(f26s);
        j(d0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean g(RecyclerView.d0 d0Var, List list) {
        if (list.isEmpty() && !super.g(d0Var, list)) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void j(RecyclerView.d0 d0Var) {
        View view = d0Var.itemView;
        view.animate().cancel();
        int size = this.j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else if (((j) this.j.get(size)).a == d0Var) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                E(d0Var);
                this.j.remove(size);
            }
        }
        W(this.k, d0Var);
        if (this.h.remove(d0Var)) {
            view.setAlpha(1.0f);
            G(d0Var);
        }
        if (this.i.remove(d0Var)) {
            view.setAlpha(1.0f);
            A(d0Var);
        }
        for (int size2 = this.n.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.n.get(size2);
            W(arrayList, d0Var);
            if (arrayList.isEmpty()) {
                this.n.remove(size2);
            }
        }
        for (int size3 = this.m.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList2 = (ArrayList) this.m.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                } else if (((j) arrayList2.get(size4)).a == d0Var) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    E(d0Var);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.m.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.l.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList3 = (ArrayList) this.l.get(size5);
            if (arrayList3.remove(d0Var)) {
                view.setAlpha(1.0f);
                A(d0Var);
                if (arrayList3.isEmpty()) {
                    this.l.remove(size5);
                }
            }
        }
        this.q.remove(d0Var);
        this.o.remove(d0Var);
        this.r.remove(d0Var);
        this.p.remove(d0Var);
        V();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void k() {
        int size = this.j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            j jVar = (j) this.j.get(size);
            View view = jVar.a.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            E(jVar.a);
            this.j.remove(size);
        }
        for (int size2 = this.h.size() - 1; size2 >= 0; size2--) {
            G((RecyclerView.d0) this.h.get(size2));
            this.h.remove(size2);
        }
        int size3 = this.i.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.d0 d0Var = (RecyclerView.d0) this.i.get(size3);
            d0Var.itemView.setAlpha(1.0f);
            A(d0Var);
            this.i.remove(size3);
        }
        for (int size4 = this.k.size() - 1; size4 >= 0; size4--) {
            X((i) this.k.get(size4));
        }
        this.k.clear();
        if (!p()) {
            return;
        }
        for (int size5 = this.m.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList = (ArrayList) this.m.get(size5);
            for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                j jVar2 = (j) arrayList.get(size6);
                View view2 = jVar2.a.itemView;
                view2.setTranslationY(0.0f);
                view2.setTranslationX(0.0f);
                E(jVar2.a);
                arrayList.remove(size6);
                if (arrayList.isEmpty()) {
                    this.m.remove(arrayList);
                }
            }
        }
        for (int size7 = this.l.size() - 1; size7 >= 0; size7--) {
            ArrayList arrayList2 = (ArrayList) this.l.get(size7);
            for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                RecyclerView.d0 d0Var2 = (RecyclerView.d0) arrayList2.get(size8);
                d0Var2.itemView.setAlpha(1.0f);
                A(d0Var2);
                arrayList2.remove(size8);
                if (arrayList2.isEmpty()) {
                    this.l.remove(arrayList2);
                }
            }
        }
        for (int size9 = this.n.size() - 1; size9 >= 0; size9--) {
            ArrayList arrayList3 = (ArrayList) this.n.get(size9);
            for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                X((i) arrayList3.get(size10));
                if (arrayList3.isEmpty()) {
                    this.n.remove(arrayList3);
                }
            }
        }
        U(this.q);
        U(this.p);
        U(this.o);
        U(this.r);
        i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean p() {
        if (this.i.isEmpty() && this.k.isEmpty() && this.j.isEmpty() && this.h.isEmpty() && this.p.isEmpty() && this.q.isEmpty() && this.o.isEmpty() && this.r.isEmpty() && this.m.isEmpty() && this.l.isEmpty() && this.n.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void u() {
        long j2;
        long j3;
        boolean z = !this.h.isEmpty();
        boolean z2 = !this.j.isEmpty();
        boolean z3 = !this.k.isEmpty();
        boolean z4 = !this.i.isEmpty();
        if (!z && !z2 && !z4 && !z3) {
            return;
        }
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            T((RecyclerView.d0) it.next());
        }
        this.h.clear();
        if (z2) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.j);
            this.m.add(arrayList);
            this.j.clear();
            a aVar = new a(arrayList);
            if (z) {
                d1.d0(((j) arrayList.get(0)).a.itemView, aVar, o());
            } else {
                aVar.run();
            }
        }
        if (z3) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(this.k);
            this.n.add(arrayList2);
            this.k.clear();
            b bVar = new b(arrayList2);
            if (z) {
                d1.d0(((i) arrayList2.get(0)).a.itemView, bVar, o());
            } else {
                bVar.run();
            }
        }
        if (z4) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(this.i);
            this.l.add(arrayList3);
            this.i.clear();
            RunnableC0029c runnableC0029c = new RunnableC0029c(arrayList3);
            if (!z && !z2 && !z3) {
                runnableC0029c.run();
                return;
            }
            long j4 = 0;
            if (z) {
                j2 = o();
            } else {
                j2 = 0;
            }
            if (z2) {
                j3 = n();
            } else {
                j3 = 0;
            }
            if (z3) {
                j4 = m();
            }
            d1.d0(((RecyclerView.d0) arrayList3.get(0)).itemView, runnableC0029c, j2 + Math.max(j3, j4));
        }
    }

    @Override // androidx.recyclerview.widget.q
    public boolean w(RecyclerView.d0 d0Var) {
        Z(d0Var);
        d0Var.itemView.setAlpha(0.0f);
        this.i.add(d0Var);
        return true;
    }

    @Override // androidx.recyclerview.widget.q
    public boolean x(RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2, int i2, int i3, int i4, int i5) {
        if (d0Var == d0Var2) {
            return y(d0Var, i2, i3, i4, i5);
        }
        float translationX = d0Var.itemView.getTranslationX();
        float translationY = d0Var.itemView.getTranslationY();
        float alpha = d0Var.itemView.getAlpha();
        Z(d0Var);
        int i6 = (int) ((i4 - i2) - translationX);
        int i7 = (int) ((i5 - i3) - translationY);
        d0Var.itemView.setTranslationX(translationX);
        d0Var.itemView.setTranslationY(translationY);
        d0Var.itemView.setAlpha(alpha);
        if (d0Var2 != null) {
            Z(d0Var2);
            d0Var2.itemView.setTranslationX(-i6);
            d0Var2.itemView.setTranslationY(-i7);
            d0Var2.itemView.setAlpha(0.0f);
        }
        this.k.add(new i(d0Var, d0Var2, i2, i3, i4, i5));
        return true;
    }

    @Override // androidx.recyclerview.widget.q
    public boolean y(RecyclerView.d0 d0Var, int i2, int i3, int i4, int i5) {
        View view = d0Var.itemView;
        int translationX = i2 + ((int) view.getTranslationX());
        int translationY = i3 + ((int) d0Var.itemView.getTranslationY());
        Z(d0Var);
        int i6 = i4 - translationX;
        int i7 = i5 - translationY;
        if (i6 == 0 && i7 == 0) {
            E(d0Var);
            return false;
        }
        if (i6 != 0) {
            view.setTranslationX(-i6);
        }
        if (i7 != 0) {
            view.setTranslationY(-i7);
        }
        this.j.add(new j(d0Var, translationX, translationY, i4, i5));
        return true;
    }

    @Override // androidx.recyclerview.widget.q
    public boolean z(RecyclerView.d0 d0Var) {
        Z(d0Var);
        this.h.add(d0Var);
        return true;
    }

    /* loaded from: classes.dex */
    public static class i {
        public RecyclerView.d0 a;
        public RecyclerView.d0 b;
        public int c;
        public int d;
        public int e;
        public int f;

        public i(RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2) {
            this.a = d0Var;
            this.b = d0Var2;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.a + ", newHolder=" + this.b + ", fromX=" + this.c + ", fromY=" + this.d + ", toX=" + this.e + ", toY=" + this.f + '}';
        }

        public i(RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2, int i, int i2, int i3, int i4) {
            this(d0Var, d0Var2);
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
        }
    }
}
