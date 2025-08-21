package androidx.transition;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.n;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class r extends n {
    public int c;
    public ArrayList a = new ArrayList();
    public boolean b = true;
    public boolean d = false;
    public int e = 0;

    /* loaded from: classes.dex */
    public class a extends o {
        public final /* synthetic */ n a;

        public a(n nVar) {
            this.a = nVar;
        }

        @Override // androidx.transition.n.g
        public void c(n nVar) {
            this.a.runAnimators();
            nVar.removeListener(this);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends o {
        public r a;

        public b(r rVar) {
            this.a = rVar;
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void a(n nVar) {
            r rVar = this.a;
            if (!rVar.d) {
                rVar.start();
                this.a.d = true;
            }
        }

        @Override // androidx.transition.n.g
        public void c(n nVar) {
            r rVar = this.a;
            int i = rVar.c - 1;
            rVar.c = i;
            if (i == 0) {
                rVar.d = false;
                rVar.end();
            }
            nVar.removeListener(this);
        }
    }

    @Override // androidx.transition.n
    /* renamed from: A */
    public r removeListener(n.g gVar) {
        return (r) super.removeListener(gVar);
    }

    @Override // androidx.transition.n
    /* renamed from: B */
    public r removeTarget(int i) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            ((n) this.a.get(i2)).removeTarget(i);
        }
        return (r) super.removeTarget(i);
    }

    @Override // androidx.transition.n
    /* renamed from: C */
    public r removeTarget(View view) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).removeTarget(view);
        }
        return (r) super.removeTarget(view);
    }

    @Override // androidx.transition.n
    /* renamed from: D */
    public r removeTarget(Class cls) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).removeTarget(cls);
        }
        return (r) super.removeTarget(cls);
    }

    @Override // androidx.transition.n
    /* renamed from: E */
    public r removeTarget(String str) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).removeTarget(str);
        }
        return (r) super.removeTarget(str);
    }

    @Override // androidx.transition.n
    /* renamed from: F */
    public r setDuration(long j) {
        ArrayList arrayList;
        super.setDuration(j);
        if (this.mDuration >= 0 && (arrayList = this.a) != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((n) this.a.get(i)).setDuration(j);
            }
        }
        return this;
    }

    @Override // androidx.transition.n
    /* renamed from: G */
    public r setInterpolator(TimeInterpolator timeInterpolator) {
        this.e |= 1;
        ArrayList arrayList = this.a;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((n) this.a.get(i)).setInterpolator(timeInterpolator);
            }
        }
        return (r) super.setInterpolator(timeInterpolator);
    }

    public r H(int i) {
        if (i != 0) {
            if (i == 1) {
                this.b = false;
            } else {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i);
            }
        } else {
            this.b = true;
        }
        return this;
    }

    @Override // androidx.transition.n
    /* renamed from: I */
    public r setSceneRoot(ViewGroup viewGroup) {
        super.setSceneRoot(viewGroup);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).setSceneRoot(viewGroup);
        }
        return this;
    }

    @Override // androidx.transition.n
    /* renamed from: J */
    public r setStartDelay(long j) {
        return (r) super.setStartDelay(j);
    }

    public final void K() {
        b bVar = new b(this);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((n) it.next()).addListener(bVar);
        }
        this.c = this.a.size();
    }

    @Override // androidx.transition.n
    public void cancel() {
        super.cancel();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).cancel();
        }
    }

    @Override // androidx.transition.n
    public void captureEndValues(u uVar) {
        if (isValidTarget(uVar.b)) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                n nVar = (n) it.next();
                if (nVar.isValidTarget(uVar.b)) {
                    nVar.captureEndValues(uVar);
                    uVar.c.add(nVar);
                }
            }
        }
    }

    @Override // androidx.transition.n
    public void capturePropagationValues(u uVar) {
        super.capturePropagationValues(uVar);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).capturePropagationValues(uVar);
        }
    }

    @Override // androidx.transition.n
    public void captureStartValues(u uVar) {
        if (isValidTarget(uVar.b)) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                n nVar = (n) it.next();
                if (nVar.isValidTarget(uVar.b)) {
                    nVar.captureStartValues(uVar);
                    uVar.c.add(nVar);
                }
            }
        }
    }

    @Override // androidx.transition.n
    public void createAnimators(ViewGroup viewGroup, v vVar, v vVar2, ArrayList arrayList, ArrayList arrayList2) {
        long startDelay = getStartDelay();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            n nVar = (n) this.a.get(i);
            if (startDelay > 0 && (this.b || i == 0)) {
                long startDelay2 = nVar.getStartDelay();
                if (startDelay2 > 0) {
                    nVar.setStartDelay(startDelay2 + startDelay);
                } else {
                    nVar.setStartDelay(startDelay);
                }
            }
            nVar.createAnimators(viewGroup, vVar, vVar2, arrayList, arrayList2);
        }
    }

    @Override // androidx.transition.n
    public n excludeTarget(View view, boolean z) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).excludeTarget(view, z);
        }
        return super.excludeTarget(view, z);
    }

    @Override // androidx.transition.n
    public void forceToEnd(ViewGroup viewGroup) {
        super.forceToEnd(viewGroup);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).forceToEnd(viewGroup);
        }
    }

    @Override // androidx.transition.n
    public void pause(View view) {
        super.pause(view);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).pause(view);
        }
    }

    @Override // androidx.transition.n
    /* renamed from: r */
    public r addListener(n.g gVar) {
        return (r) super.addListener(gVar);
    }

    @Override // androidx.transition.n
    public void resume(View view) {
        super.resume(view);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).resume(view);
        }
    }

    @Override // androidx.transition.n
    public void runAnimators() {
        if (this.a.isEmpty()) {
            start();
            end();
            return;
        }
        K();
        if (!this.b) {
            for (int i = 1; i < this.a.size(); i++) {
                ((n) this.a.get(i - 1)).addListener(new a((n) this.a.get(i)));
            }
            n nVar = (n) this.a.get(0);
            if (nVar != null) {
                nVar.runAnimators();
                return;
            }
            return;
        }
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((n) it.next()).runAnimators();
        }
    }

    @Override // androidx.transition.n
    /* renamed from: s */
    public r addTarget(int i) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            ((n) this.a.get(i2)).addTarget(i);
        }
        return (r) super.addTarget(i);
    }

    @Override // androidx.transition.n
    public void setCanRemoveViews(boolean z) {
        super.setCanRemoveViews(z);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).setCanRemoveViews(z);
        }
    }

    @Override // androidx.transition.n
    public void setEpicenterCallback(n.f fVar) {
        super.setEpicenterCallback(fVar);
        this.e |= 8;
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).setEpicenterCallback(fVar);
        }
    }

    @Override // androidx.transition.n
    public void setPathMotion(h hVar) {
        super.setPathMotion(hVar);
        this.e |= 4;
        if (this.a != null) {
            for (int i = 0; i < this.a.size(); i++) {
                ((n) this.a.get(i)).setPathMotion(hVar);
            }
        }
    }

    @Override // androidx.transition.n
    public void setPropagation(q qVar) {
        super.setPropagation(qVar);
        this.e |= 2;
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).setPropagation(qVar);
        }
    }

    @Override // androidx.transition.n
    /* renamed from: t */
    public r addTarget(View view) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).addTarget(view);
        }
        return (r) super.addTarget(view);
    }

    @Override // androidx.transition.n
    public String toString(String str) {
        String nVar = super.toString(str);
        for (int i = 0; i < this.a.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(nVar);
            sb.append("\n");
            sb.append(((n) this.a.get(i)).toString(str + "  "));
            nVar = sb.toString();
        }
        return nVar;
    }

    @Override // androidx.transition.n
    /* renamed from: u */
    public r addTarget(Class cls) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).addTarget(cls);
        }
        return (r) super.addTarget(cls);
    }

    @Override // androidx.transition.n
    /* renamed from: v */
    public r addTarget(String str) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).addTarget(str);
        }
        return (r) super.addTarget(str);
    }

    public r w(n nVar) {
        x(nVar);
        long j = this.mDuration;
        if (j >= 0) {
            nVar.setDuration(j);
        }
        if ((this.e & 1) != 0) {
            nVar.setInterpolator(getInterpolator());
        }
        if ((this.e & 2) != 0) {
            getPropagation();
            nVar.setPropagation(null);
        }
        if ((this.e & 4) != 0) {
            nVar.setPathMotion(getPathMotion());
        }
        if ((this.e & 8) != 0) {
            nVar.setEpicenterCallback(getEpicenterCallback());
        }
        return this;
    }

    public final void x(n nVar) {
        this.a.add(nVar);
        nVar.mParent = this;
    }

    public n y(int i) {
        if (i >= 0 && i < this.a.size()) {
            return (n) this.a.get(i);
        }
        return null;
    }

    public int z() {
        return this.a.size();
    }

    @Override // androidx.transition.n
    /* renamed from: clone */
    public n mo4clone() {
        r rVar = (r) super.mo4clone();
        rVar.a = new ArrayList();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            rVar.x(((n) this.a.get(i)).mo4clone());
        }
        return rVar;
    }

    @Override // androidx.transition.n
    public n excludeTarget(String str, boolean z) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).excludeTarget(str, z);
        }
        return super.excludeTarget(str, z);
    }

    @Override // androidx.transition.n
    public n excludeTarget(int i, boolean z) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            ((n) this.a.get(i2)).excludeTarget(i, z);
        }
        return super.excludeTarget(i, z);
    }

    @Override // androidx.transition.n
    public n excludeTarget(Class cls, boolean z) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).excludeTarget(cls, z);
        }
        return super.excludeTarget(cls, z);
    }
}
