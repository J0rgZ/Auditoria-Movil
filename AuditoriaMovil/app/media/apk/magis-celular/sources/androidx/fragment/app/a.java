package androidx.fragment.app;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.j;
import androidx.fragment.app.p;
import androidx.lifecycle.c;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
/* loaded from: classes.dex */
public final class a extends p implements j.InterfaceC0017j {

    /* renamed from: s  reason: collision with root package name */
    public final j f19s;
    public boolean t;
    public int u = -1;

    public a(j jVar) {
        this.f19s = jVar;
    }

    public static boolean D(p.a aVar) {
        Fragment fragment = aVar.b;
        if (fragment != null && fragment.mAdded && fragment.mView != null && !fragment.mDetached && !fragment.mHidden && fragment.isPostponed()) {
            return true;
        }
        return false;
    }

    public String A() {
        return this.j;
    }

    public boolean B(int i) {
        int i2;
        int size = this.a.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = ((p.a) this.a.get(i3)).b;
            if (fragment != null) {
                i2 = fragment.mContainerId;
            } else {
                i2 = 0;
            }
            if (i2 != 0 && i2 == i) {
                return true;
            }
        }
        return false;
    }

    public boolean C(ArrayList arrayList, int i, int i2) {
        int i3;
        int i4;
        if (i2 == i) {
            return false;
        }
        int size = this.a.size();
        int i5 = -1;
        for (int i6 = 0; i6 < size; i6++) {
            Fragment fragment = ((p.a) this.a.get(i6)).b;
            if (fragment != null) {
                i3 = fragment.mContainerId;
            } else {
                i3 = 0;
            }
            if (i3 != 0 && i3 != i5) {
                for (int i7 = i; i7 < i2; i7++) {
                    a aVar = (a) arrayList.get(i7);
                    int size2 = aVar.a.size();
                    for (int i8 = 0; i8 < size2; i8++) {
                        Fragment fragment2 = ((p.a) aVar.a.get(i8)).b;
                        if (fragment2 != null) {
                            i4 = fragment2.mContainerId;
                        } else {
                            i4 = 0;
                        }
                        if (i4 == i3) {
                            return true;
                        }
                    }
                }
                i5 = i3;
            }
        }
        return false;
    }

    public boolean E() {
        for (int i = 0; i < this.a.size(); i++) {
            if (D((p.a) this.a.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void F() {
        if (this.r != null) {
            for (int i = 0; i < this.r.size(); i++) {
                ((Runnable) this.r.get(i)).run();
            }
            this.r = null;
        }
    }

    public void G(Fragment.f fVar) {
        for (int i = 0; i < this.a.size(); i++) {
            p.a aVar = (p.a) this.a.get(i);
            if (D(aVar)) {
                aVar.b.setOnStartEnterTransitionListener(fVar);
            }
        }
    }

    public Fragment H(ArrayList arrayList, Fragment fragment) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            p.a aVar = (p.a) this.a.get(size);
            int i = aVar.a;
            if (i != 1) {
                if (i != 3) {
                    switch (i) {
                        case 8:
                            fragment = null;
                            break;
                        case 9:
                            fragment = aVar.b;
                            break;
                        case 10:
                            aVar.h = aVar.g;
                            break;
                    }
                }
                arrayList.add(aVar.b);
            }
            arrayList.remove(aVar.b);
        }
        return fragment;
    }

    @Override // androidx.fragment.app.j.InterfaceC0017j
    public boolean a(ArrayList arrayList, ArrayList arrayList2) {
        if (j.H) {
            StringBuilder sb = new StringBuilder();
            sb.append("Run: ");
            sb.append(this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (this.h) {
            this.f19s.m(this);
            return true;
        }
        return true;
    }

    @Override // androidx.fragment.app.p
    public int g() {
        return u(false);
    }

    @Override // androidx.fragment.app.p
    public int h() {
        return u(true);
    }

    @Override // androidx.fragment.app.p
    public void i() {
        l();
        this.f19s.o0(this, false);
    }

    @Override // androidx.fragment.app.p
    public void j() {
        l();
        this.f19s.o0(this, true);
    }

    @Override // androidx.fragment.app.p
    public p k(Fragment fragment) {
        j jVar = fragment.mFragmentManager;
        if (jVar != null && jVar != this.f19s) {
            throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
        }
        return super.k(fragment);
    }

    @Override // androidx.fragment.app.p
    public void m(int i, Fragment fragment, String str, int i2) {
        super.m(i, fragment, str, i2);
        fragment.mFragmentManager = this.f19s;
    }

    @Override // androidx.fragment.app.p
    public p n(Fragment fragment) {
        j jVar = fragment.mFragmentManager;
        if (jVar != null && jVar != this.f19s) {
            throw new IllegalStateException("Cannot hide Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
        }
        return super.n(fragment);
    }

    @Override // androidx.fragment.app.p
    public p o(Fragment fragment) {
        j jVar = fragment.mFragmentManager;
        if (jVar != null && jVar != this.f19s) {
            throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
        }
        return super.o(fragment);
    }

    @Override // androidx.fragment.app.p
    public p r(Fragment fragment, c.b bVar) {
        if (fragment.mFragmentManager == this.f19s) {
            c.b bVar2 = c.b.CREATED;
            if (bVar.a(bVar2)) {
                return super.r(fragment, bVar);
            }
            throw new IllegalArgumentException("Cannot set maximum Lifecycle below " + bVar2);
        }
        throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + this.f19s);
    }

    @Override // androidx.fragment.app.p
    public p s(Fragment fragment) {
        j jVar = fragment.mFragmentManager;
        if (jVar != null && jVar != this.f19s) {
            throw new IllegalStateException("Cannot show Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
        }
        return super.s(fragment);
    }

    public void t(int i) {
        if (!this.h) {
            return;
        }
        if (j.H) {
            StringBuilder sb = new StringBuilder();
            sb.append("Bump nesting in ");
            sb.append(this);
            sb.append(" by ");
            sb.append(i);
        }
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            p.a aVar = (p.a) this.a.get(i2);
            Fragment fragment = aVar.b;
            if (fragment != null) {
                fragment.mBackStackNesting += i;
                if (j.H) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Bump nesting of ");
                    sb2.append(aVar.b);
                    sb2.append(" to ");
                    sb2.append(aVar.b.mBackStackNesting);
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.u >= 0) {
            sb.append(" #");
            sb.append(this.u);
        }
        if (this.j != null) {
            sb.append(" ");
            sb.append(this.j);
        }
        sb.append("}");
        return sb.toString();
    }

    public int u(boolean z) {
        if (!this.t) {
            if (j.H) {
                StringBuilder sb = new StringBuilder();
                sb.append("Commit: ");
                sb.append(this);
                PrintWriter printWriter = new PrintWriter((Writer) new u.c("FragmentManager"));
                v("  ", printWriter);
                printWriter.close();
            }
            this.t = true;
            if (this.h) {
                this.u = this.f19s.p(this);
            } else {
                this.u = -1;
            }
            this.f19s.k0(this, z);
            return this.u;
        }
        throw new IllegalStateException("commit already called");
    }

    public void v(String str, PrintWriter printWriter) {
        w(str, printWriter, true);
    }

    public void w(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.j);
            printWriter.print(" mIndex=");
            printWriter.print(this.u);
            printWriter.print(" mCommitted=");
            printWriter.println(this.t);
            if (this.f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.g));
            }
            if (this.b != 0 || this.c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.c));
            }
            if (this.d != 0 || this.e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.e));
            }
            if (this.k != 0 || this.l != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.k));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.l);
            }
            if (this.m != 0 || this.n != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.m));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.n);
            }
        }
        if (!this.a.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Operations:");
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                p.a aVar = (p.a) this.a.get(i);
                switch (aVar.a) {
                    case 0:
                        str2 = "NULL";
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case 2:
                        str2 = "REPLACE";
                        break;
                    case 3:
                        str2 = "REMOVE";
                        break;
                    case 4:
                        str2 = "HIDE";
                        break;
                    case 5:
                        str2 = "SHOW";
                        break;
                    case 6:
                        str2 = "DETACH";
                        break;
                    case 7:
                        str2 = "ATTACH";
                        break;
                    case 8:
                        str2 = "SET_PRIMARY_NAV";
                        break;
                    case 9:
                        str2 = "UNSET_PRIMARY_NAV";
                        break;
                    case 10:
                        str2 = "OP_SET_MAX_LIFECYCLE";
                        break;
                    default:
                        str2 = "cmd=" + aVar.a;
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(str2);
                printWriter.print(" ");
                printWriter.println(aVar.b);
                if (z) {
                    if (aVar.c != 0 || aVar.d != 0) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(aVar.c));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(aVar.d));
                    }
                    if (aVar.e != 0 || aVar.f != 0) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(aVar.e));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(aVar.f));
                    }
                }
            }
        }
    }

    public void x() {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            p.a aVar = (p.a) this.a.get(i);
            Fragment fragment = aVar.b;
            if (fragment != null) {
                fragment.setNextTransition(this.f, this.g);
            }
            switch (aVar.a) {
                case 1:
                    fragment.setNextAnim(aVar.c);
                    this.f19s.n(fragment, false);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.a);
                case 3:
                    fragment.setNextAnim(aVar.d);
                    this.f19s.Z0(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.d);
                    this.f19s.E0(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.c);
                    this.f19s.m1(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.d);
                    this.f19s.z(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.c);
                    this.f19s.s(fragment);
                    break;
                case 8:
                    this.f19s.l1(fragment);
                    break;
                case 9:
                    this.f19s.l1(null);
                    break;
                case 10:
                    this.f19s.k1(fragment, aVar.h);
                    break;
            }
            if (!this.q && aVar.a != 1 && fragment != null) {
                this.f19s.Q0(fragment);
            }
        }
        if (!this.q) {
            j jVar = this.f19s;
            jVar.R0(jVar.p, true);
        }
    }

    public void y(boolean z) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            p.a aVar = (p.a) this.a.get(size);
            Fragment fragment = aVar.b;
            if (fragment != null) {
                fragment.setNextTransition(j.e1(this.f), this.g);
            }
            switch (aVar.a) {
                case 1:
                    fragment.setNextAnim(aVar.f);
                    this.f19s.Z0(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.a);
                case 3:
                    fragment.setNextAnim(aVar.e);
                    this.f19s.n(fragment, false);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.e);
                    this.f19s.m1(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f);
                    this.f19s.E0(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.e);
                    this.f19s.s(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f);
                    this.f19s.z(fragment);
                    break;
                case 8:
                    this.f19s.l1(null);
                    break;
                case 9:
                    this.f19s.l1(fragment);
                    break;
                case 10:
                    this.f19s.k1(fragment, aVar.g);
                    break;
            }
            if (!this.q && aVar.a != 3 && fragment != null) {
                this.f19s.Q0(fragment);
            }
        }
        if (!this.q && z) {
            j jVar = this.f19s;
            jVar.R0(jVar.p, true);
        }
    }

    public Fragment z(ArrayList arrayList, Fragment fragment) {
        Fragment fragment2 = fragment;
        int i = 0;
        while (i < this.a.size()) {
            p.a aVar = (p.a) this.a.get(i);
            int i2 = aVar.a;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3 && i2 != 6) {
                        if (i2 != 7) {
                            if (i2 == 8) {
                                this.a.add(i, new p.a(9, fragment2));
                                i++;
                                fragment2 = aVar.b;
                            }
                        }
                    } else {
                        arrayList.remove(aVar.b);
                        Fragment fragment3 = aVar.b;
                        if (fragment3 == fragment2) {
                            this.a.add(i, new p.a(9, fragment3));
                            i++;
                            fragment2 = null;
                        }
                    }
                } else {
                    Fragment fragment4 = aVar.b;
                    int i3 = fragment4.mContainerId;
                    boolean z = false;
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        Fragment fragment5 = (Fragment) arrayList.get(size);
                        if (fragment5.mContainerId == i3) {
                            if (fragment5 == fragment4) {
                                z = true;
                            } else {
                                if (fragment5 == fragment2) {
                                    this.a.add(i, new p.a(9, fragment5));
                                    i++;
                                    fragment2 = null;
                                }
                                p.a aVar2 = new p.a(3, fragment5);
                                aVar2.c = aVar.c;
                                aVar2.e = aVar.e;
                                aVar2.d = aVar.d;
                                aVar2.f = aVar.f;
                                this.a.add(i, aVar2);
                                arrayList.remove(fragment5);
                                i++;
                            }
                        }
                    }
                    if (z) {
                        this.a.remove(i);
                        i--;
                    } else {
                        aVar.a = 1;
                        arrayList.add(fragment4);
                    }
                }
                i++;
            }
            arrayList.add(aVar.b);
            i++;
        }
        return fragment2;
    }
}
