package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.n;
/* loaded from: classes.dex */
public abstract class p0 extends n {
    public static final String[] b = {"android:visibility:visibility", "android:visibility:parent"};
    public int a = 3;

    /* loaded from: classes.dex */
    public class a extends o {
        public final /* synthetic */ ViewGroup a;
        public final /* synthetic */ View b;
        public final /* synthetic */ View c;

        public a(ViewGroup viewGroup, View view, View view2) {
            this.a = viewGroup;
            this.b = view;
            this.c = view2;
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void b(n nVar) {
            z.a(this.a).d(this.b);
        }

        @Override // androidx.transition.n.g
        public void c(n nVar) {
            this.c.setTag(R$id.save_overlay_view, null);
            z.a(this.a).d(this.b);
            nVar.removeListener(this);
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void e(n nVar) {
            if (this.b.getParent() == null) {
                z.a(this.a).c(this.b);
            } else {
                p0.this.cancel();
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b extends AnimatorListenerAdapter implements n.g {
        public final View a;
        public final int b;
        public final ViewGroup c;
        public final boolean d;
        public boolean e;
        public boolean f = false;

        public b(View view, int i, boolean z) {
            this.a = view;
            this.b = i;
            this.c = (ViewGroup) view.getParent();
            this.d = z;
            g(true);
        }

        @Override // androidx.transition.n.g
        public void a(n nVar) {
        }

        @Override // androidx.transition.n.g
        public void b(n nVar) {
            g(false);
        }

        @Override // androidx.transition.n.g
        public void c(n nVar) {
            f();
            nVar.removeListener(this);
        }

        @Override // androidx.transition.n.g
        public void d(n nVar) {
        }

        @Override // androidx.transition.n.g
        public void e(n nVar) {
            g(true);
        }

        public final void f() {
            if (!this.f) {
                c0.h(this.a, this.b);
                ViewGroup viewGroup = this.c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        public final void g(boolean z) {
            ViewGroup viewGroup;
            if (this.d && this.e != z && (viewGroup = this.c) != null) {
                this.e = z;
                z.c(viewGroup, z);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) {
            if (!this.f) {
                c0.h(this.a, this.b);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            if (!this.f) {
                c0.h(this.a, 0);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* loaded from: classes.dex */
    public static class c {
        public boolean a;
        public boolean b;
        public int c;
        public int d;
        public ViewGroup e;
        public ViewGroup f;
    }

    @Override // androidx.transition.n
    public void captureEndValues(u uVar) {
        captureValues(uVar);
    }

    @Override // androidx.transition.n
    public void captureStartValues(u uVar) {
        captureValues(uVar);
    }

    public final void captureValues(u uVar) {
        uVar.a.put("android:visibility:visibility", Integer.valueOf(uVar.b.getVisibility()));
        uVar.a.put("android:visibility:parent", uVar.b.getParent());
        int[] iArr = new int[2];
        uVar.b.getLocationOnScreen(iArr);
        uVar.a.put("android:visibility:screenLocation", iArr);
    }

    @Override // androidx.transition.n
    public Animator createAnimator(ViewGroup viewGroup, u uVar, u uVar2) {
        c r = r(uVar, uVar2);
        if (r.a) {
            if (r.e != null || r.f != null) {
                if (r.b) {
                    return t(viewGroup, uVar, r.c, uVar2, r.d);
                }
                return v(viewGroup, uVar, r.c, uVar2, r.d);
            }
            return null;
        }
        return null;
    }

    @Override // androidx.transition.n
    public String[] getTransitionProperties() {
        return b;
    }

    @Override // androidx.transition.n
    public boolean isTransitionRequired(u uVar, u uVar2) {
        if (uVar == null && uVar2 == null) {
            return false;
        }
        if (uVar != null && uVar2 != null && uVar2.a.containsKey("android:visibility:visibility") != uVar.a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c r = r(uVar, uVar2);
        if (!r.a) {
            return false;
        }
        if (r.c != 0 && r.d != 0) {
            return false;
        }
        return true;
    }

    public final c r(u uVar, u uVar2) {
        c cVar = new c();
        cVar.a = false;
        cVar.b = false;
        if (uVar != null && uVar.a.containsKey("android:visibility:visibility")) {
            cVar.c = ((Integer) uVar.a.get("android:visibility:visibility")).intValue();
            cVar.e = (ViewGroup) uVar.a.get("android:visibility:parent");
        } else {
            cVar.c = -1;
            cVar.e = null;
        }
        if (uVar2 != null && uVar2.a.containsKey("android:visibility:visibility")) {
            cVar.d = ((Integer) uVar2.a.get("android:visibility:visibility")).intValue();
            cVar.f = (ViewGroup) uVar2.a.get("android:visibility:parent");
        } else {
            cVar.d = -1;
            cVar.f = null;
        }
        if (uVar != null && uVar2 != null) {
            int i = cVar.c;
            int i2 = cVar.d;
            if (i == i2 && cVar.e == cVar.f) {
                return cVar;
            }
            if (i != i2) {
                if (i == 0) {
                    cVar.b = false;
                    cVar.a = true;
                } else if (i2 == 0) {
                    cVar.b = true;
                    cVar.a = true;
                }
            } else if (cVar.f == null) {
                cVar.b = false;
                cVar.a = true;
            } else if (cVar.e == null) {
                cVar.b = true;
                cVar.a = true;
            }
        } else if (uVar == null && cVar.d == 0) {
            cVar.b = true;
            cVar.a = true;
        } else if (uVar2 == null && cVar.c == 0) {
            cVar.b = false;
            cVar.a = true;
        }
        return cVar;
    }

    public abstract Animator s(ViewGroup viewGroup, View view, u uVar, u uVar2);

    public Animator t(ViewGroup viewGroup, u uVar, int i, u uVar2, int i2) {
        if ((this.a & 1) != 1 || uVar2 == null) {
            return null;
        }
        if (uVar == null) {
            View view = (View) uVar2.b.getParent();
            if (r(getMatchedTransitionValues(view, false), getTransitionValues(view, false)).a) {
                return null;
            }
        }
        return s(viewGroup, uVar2.b, uVar, uVar2);
    }

    public abstract Animator u(ViewGroup viewGroup, View view, u uVar, u uVar2);

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0089, code lost:
        if (r17.mCanRemoveViews != false) goto L52;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.animation.Animator v(android.view.ViewGroup r18, androidx.transition.u r19, int r20, androidx.transition.u r21, int r22) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.p0.v(android.view.ViewGroup, androidx.transition.u, int, androidx.transition.u, int):android.animation.Animator");
    }

    public void w(int i) {
        if ((i & (-4)) == 0) {
            this.a = i;
            return;
        }
        throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
    }
}
