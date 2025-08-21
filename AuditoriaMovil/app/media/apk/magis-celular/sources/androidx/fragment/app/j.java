package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.c;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import v.d0;
/* loaded from: classes.dex */
public final class j extends androidx.fragment.app.i implements LayoutInflater.Factory2 {
    public static boolean H;
    public static final Interpolator I = new DecelerateInterpolator(2.5f);
    public static final Interpolator J = new DecelerateInterpolator(1.5f);
    public ArrayList A;
    public ArrayList B;
    public ArrayList E;
    public androidx.fragment.app.l F;
    public ArrayList c;
    public boolean d;
    public ArrayList h;
    public ArrayList i;
    public OnBackPressedDispatcher j;
    public ArrayList l;
    public ArrayList m;
    public ArrayList n;
    public androidx.fragment.app.h q;
    public androidx.fragment.app.e r;

    /* renamed from: s  reason: collision with root package name */
    public Fragment f20s;
    public Fragment t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public ArrayList z;
    public int e = 0;
    public final ArrayList f = new ArrayList();
    public final HashMap g = new HashMap();
    public final androidx.activity.b k = new a(false);
    public final CopyOnWriteArrayList o = new CopyOnWriteArrayList();
    public int p = 0;
    public Bundle C = null;
    public SparseArray D = null;
    public Runnable G = new b();

    /* loaded from: classes.dex */
    public class a extends androidx.activity.b {
        public a(boolean z) {
            super(z);
        }

        @Override // androidx.activity.b
        public void b() {
            j.this.D0();
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            j.this.n0();
        }
    }

    /* loaded from: classes.dex */
    public class c implements Animation.AnimationListener {
        public final /* synthetic */ ViewGroup a;
        public final /* synthetic */ Fragment b;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (c.this.b.getAnimatingAway() != null) {
                    c.this.b.setAnimatingAway(null);
                    c cVar = c.this;
                    j jVar = j.this;
                    Fragment fragment = cVar.b;
                    jVar.T0(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                }
            }
        }

        public c(ViewGroup viewGroup, Fragment fragment) {
            this.a = viewGroup;
            this.b = fragment;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* loaded from: classes.dex */
    public class d extends AnimatorListenerAdapter {
        public final /* synthetic */ ViewGroup a;
        public final /* synthetic */ View b;
        public final /* synthetic */ Fragment c;

        public d(ViewGroup viewGroup, View view, Fragment fragment) {
            this.a = viewGroup;
            this.b = view;
            this.c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.endViewTransition(this.b);
            Animator animator2 = this.c.getAnimator();
            this.c.setAnimator(null);
            if (animator2 != null && this.a.indexOfChild(this.b) < 0) {
                j jVar = j.this;
                Fragment fragment = this.c;
                jVar.T0(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
            }
        }
    }

    /* loaded from: classes.dex */
    public class e extends AnimatorListenerAdapter {
        public final /* synthetic */ ViewGroup a;
        public final /* synthetic */ View b;
        public final /* synthetic */ Fragment c;

        public e(ViewGroup viewGroup, View view, Fragment fragment) {
            this.a = viewGroup;
            this.b = view;
            this.c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.endViewTransition(this.b);
            animator.removeListener(this);
            Fragment fragment = this.c;
            View view = fragment.mView;
            if (view != null && fragment.mHidden) {
                view.setVisibility(8);
            }
        }
    }

    /* loaded from: classes.dex */
    public class f extends androidx.fragment.app.g {
        public f() {
        }

        @Override // androidx.fragment.app.g
        public Fragment a(ClassLoader classLoader, String str) {
            androidx.fragment.app.h hVar = j.this.q;
            return hVar.a(hVar.e(), str, null);
        }
    }

    /* loaded from: classes.dex */
    public static class i {
        public static final int[] a = {16842755, 16842960, 16842961};
    }

    /* renamed from: androidx.fragment.app.j$j  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0017j {
        boolean a(ArrayList arrayList, ArrayList arrayList2);
    }

    /* loaded from: classes.dex */
    public class k implements InterfaceC0017j {
        public final String a;
        public final int b;
        public final int c;

        public k(String str, int i, int i2) {
            this.a = str;
            this.b = i;
            this.c = i2;
        }

        @Override // androidx.fragment.app.j.InterfaceC0017j
        public boolean a(ArrayList arrayList, ArrayList arrayList2) {
            Fragment fragment = j.this.t;
            if (fragment != null && this.b < 0 && this.a == null && fragment.getChildFragmentManager().h()) {
                return false;
            }
            return j.this.X0(arrayList, arrayList2, this.a, this.b, this.c);
        }
    }

    /* loaded from: classes.dex */
    public static class l implements Fragment.f {
        public final boolean a;
        public final androidx.fragment.app.a b;
        public int c;

        public l(androidx.fragment.app.a aVar, boolean z) {
            this.a = z;
            this.b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.f
        public void a() {
            this.c++;
        }

        @Override // androidx.fragment.app.Fragment.f
        public void b() {
            int i = this.c - 1;
            this.c = i;
            if (i != 0) {
                return;
            }
            this.b.f19s.i1();
        }

        public void c() {
            androidx.fragment.app.a aVar = this.b;
            aVar.f19s.x(aVar, this.a, false, false);
        }

        public void d() {
            boolean z;
            if (this.c > 0) {
                z = true;
            } else {
                z = false;
            }
            j jVar = this.b.f19s;
            int size = jVar.f.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = (Fragment) jVar.f.get(i);
                fragment.setOnStartEnterTransitionListener(null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            androidx.fragment.app.a aVar = this.b;
            aVar.f19s.x(aVar, this.a, !z, true);
        }

        public boolean e() {
            if (this.c == 0) {
                return true;
            }
            return false;
        }
    }

    public static g M0(float f2, float f3) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f3);
        alphaAnimation.setInterpolator(J);
        alphaAnimation.setDuration(220L);
        return new g(alphaAnimation);
    }

    public static g O0(float f2, float f3, float f4, float f5) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(I);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f4, f5);
        alphaAnimation.setInterpolator(J);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return new g(animationSet);
    }

    public static int e1(int i2) {
        if (i2 != 4097) {
            if (i2 != 4099) {
                return i2 != 8194 ? 0 : 4097;
            }
            return 4099;
        }
        return 8194;
    }

    public static void p0(ArrayList arrayList, ArrayList arrayList2, int i2, int i3) {
        while (i2 < i3) {
            androidx.fragment.app.a aVar = (androidx.fragment.app.a) arrayList.get(i2);
            boolean z = true;
            if (((Boolean) arrayList2.get(i2)).booleanValue()) {
                aVar.t(-1);
                if (i2 != i3 - 1) {
                    z = false;
                }
                aVar.y(z);
            } else {
                aVar.t(1);
                aVar.x();
            }
            i2++;
        }
    }

    public static int p1(int i2, boolean z) {
        if (i2 == 4097) {
            return z ? 1 : 2;
        } else if (i2 == 4099) {
            return z ? 5 : 6;
        } else if (i2 != 8194) {
            return -1;
        } else {
            return z ? 3 : 4;
        }
    }

    public void A() {
        this.v = false;
        this.w = false;
        g0(2);
    }

    public LayoutInflater.Factory2 A0() {
        return this;
    }

    public void B(Configuration configuration) {
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = (Fragment) this.f.get(i2);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public Fragment B0() {
        return this.t;
    }

    public boolean C(MenuItem menuItem) {
        if (this.p < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = (Fragment) this.f.get(i2);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public androidx.lifecycle.s C0(Fragment fragment) {
        return this.F.i(fragment);
    }

    public void D() {
        this.v = false;
        this.w = false;
        g0(1);
    }

    public void D0() {
        n0();
        if (this.k.c()) {
            h();
        } else {
            this.j.c();
        }
    }

    public boolean E(Menu menu, MenuInflater menuInflater) {
        if (this.p < 1) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z = false;
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = (Fragment) this.f.get(i2);
            if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.i != null) {
            for (int i3 = 0; i3 < this.i.size(); i3++) {
                Fragment fragment2 = (Fragment) this.i.get(i3);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.i = arrayList;
        return z;
    }

    public void E0(Fragment fragment) {
        if (H) {
            StringBuilder sb = new StringBuilder();
            sb.append("hide: ");
            sb.append(fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
        }
    }

    public void F() {
        this.x = true;
        n0();
        g0(0);
        this.q = null;
        this.r = null;
        this.f20s = null;
        if (this.j != null) {
            this.k.d();
            this.j = null;
        }
    }

    public boolean F0() {
        return this.x;
    }

    public void G() {
        g0(1);
    }

    public final boolean G0(Fragment fragment) {
        if ((fragment.mHasMenu && fragment.mMenuVisible) || fragment.mChildFragmentManager.u()) {
            return true;
        }
        return false;
    }

    public void H() {
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = (Fragment) this.f.get(i2);
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public boolean H0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        j jVar = fragment.mFragmentManager;
        if (fragment == jVar.B0() && H0(jVar.f20s)) {
            return true;
        }
        return false;
    }

    public void I(boolean z) {
        for (int size = this.f.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) this.f.get(size);
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z);
            }
        }
    }

    public boolean I0(int i2) {
        if (this.p >= i2) {
            return true;
        }
        return false;
    }

    public void J(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).J(fragment, bundle, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public boolean J0() {
        if (!this.v && !this.w) {
            return false;
        }
        return true;
    }

    public void K(Fragment fragment, Context context, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).K(fragment, context, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public g K0(Fragment fragment, int i2, boolean z, int i3) {
        int p1;
        int nextAnim = fragment.getNextAnim();
        boolean z2 = false;
        fragment.setNextAnim(0);
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        Animation onCreateAnimation = fragment.onCreateAnimation(i2, z, nextAnim);
        if (onCreateAnimation != null) {
            return new g(onCreateAnimation);
        }
        Animator onCreateAnimator = fragment.onCreateAnimator(i2, z, nextAnim);
        if (onCreateAnimator != null) {
            return new g(onCreateAnimator);
        }
        if (nextAnim != 0) {
            boolean equals = "anim".equals(this.q.e().getResources().getResourceTypeName(nextAnim));
            if (equals) {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(this.q.e(), nextAnim);
                    if (loadAnimation != null) {
                        return new g(loadAnimation);
                    }
                    z2 = true;
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                }
            }
            if (!z2) {
                try {
                    Animator loadAnimator = AnimatorInflater.loadAnimator(this.q.e(), nextAnim);
                    if (loadAnimator != null) {
                        return new g(loadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (!equals) {
                        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.q.e(), nextAnim);
                        if (loadAnimation2 != null) {
                            return new g(loadAnimation2);
                        }
                    } else {
                        throw e3;
                    }
                }
            }
        }
        if (i2 == 0 || (p1 = p1(i2, z)) < 0) {
            return null;
        }
        switch (p1) {
            case 1:
                return O0(1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return O0(1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return O0(0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return O0(1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return M0(0.0f, 1.0f);
            case 6:
                return M0(1.0f, 0.0f);
            default:
                if (i3 == 0 && this.q.l()) {
                    this.q.k();
                }
                return null;
        }
    }

    public void L(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).L(fragment, bundle, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public void L0(Fragment fragment) {
        if (this.g.get(fragment.mWho) != null) {
            return;
        }
        this.g.put(fragment.mWho, fragment);
        if (fragment.mRetainInstanceChangedWhileDetached) {
            if (fragment.mRetainInstance) {
                o(fragment);
            } else {
                b1(fragment);
            }
            fragment.mRetainInstanceChangedWhileDetached = false;
        }
        if (H) {
            StringBuilder sb = new StringBuilder();
            sb.append("Added fragment to active set ");
            sb.append(fragment);
        }
    }

    public void M(Fragment fragment, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).M(fragment, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public void N(Fragment fragment, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).N(fragment, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public void N0(Fragment fragment) {
        if (this.g.get(fragment.mWho) == null) {
            return;
        }
        if (H) {
            StringBuilder sb = new StringBuilder();
            sb.append("Removed fragment from active set ");
            sb.append(fragment);
        }
        for (Fragment fragment2 : this.g.values()) {
            if (fragment2 != null && fragment.mWho.equals(fragment2.mTargetWho)) {
                fragment2.mTarget = fragment;
                fragment2.mTargetWho = null;
            }
        }
        this.g.put(fragment.mWho, null);
        b1(fragment);
        String str = fragment.mTargetWho;
        if (str != null) {
            fragment.mTarget = (Fragment) this.g.get(str);
        }
        fragment.initState();
    }

    public void O(Fragment fragment, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).O(fragment, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public void P(Fragment fragment, Context context, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).P(fragment, context, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final void P0(androidx.collection.b bVar) {
        int size = bVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = (Fragment) bVar.h(i2);
            if (!fragment.mAdded) {
                View requireView = fragment.requireView();
                fragment.mPostponedAlpha = requireView.getAlpha();
                requireView.setAlpha(0.0f);
            }
        }
    }

    public void Q(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).Q(fragment, bundle, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public void Q0(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        if (!this.g.containsKey(fragment.mWho)) {
            if (H) {
                StringBuilder sb = new StringBuilder();
                sb.append("Ignoring moving ");
                sb.append(fragment);
                sb.append(" to state ");
                sb.append(this.p);
                sb.append("since it is not added to ");
                sb.append(this);
                return;
            }
            return;
        }
        int i2 = this.p;
        if (fragment.mRemoving) {
            if (fragment.isInBackStack()) {
                i2 = Math.min(i2, 1);
            } else {
                i2 = Math.min(i2, 0);
            }
        }
        T0(fragment, i2, fragment.getNextTransition(), fragment.getNextTransitionStyle(), false);
        if (fragment.mView != null) {
            Fragment u0 = u0(fragment);
            if (u0 != null) {
                View view = u0.mView;
                ViewGroup viewGroup = fragment.mContainer;
                int indexOfChild = viewGroup.indexOfChild(view);
                int indexOfChild2 = viewGroup.indexOfChild(fragment.mView);
                if (indexOfChild2 < indexOfChild) {
                    viewGroup.removeViewAt(indexOfChild2);
                    viewGroup.addView(fragment.mView, indexOfChild);
                }
            }
            if (fragment.mIsNewlyAdded && fragment.mContainer != null) {
                float f2 = fragment.mPostponedAlpha;
                if (f2 > 0.0f) {
                    fragment.mView.setAlpha(f2);
                }
                fragment.mPostponedAlpha = 0.0f;
                fragment.mIsNewlyAdded = false;
                g K0 = K0(fragment, fragment.getNextTransition(), true, fragment.getNextTransitionStyle());
                if (K0 != null) {
                    Animation animation = K0.a;
                    if (animation != null) {
                        fragment.mView.startAnimation(animation);
                    } else {
                        K0.b.setTarget(fragment.mView);
                        K0.b.start();
                    }
                }
            }
        }
        if (fragment.mHiddenChanged) {
            y(fragment);
        }
    }

    public void R(Fragment fragment, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).R(fragment, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public void R0(int i2, boolean z) {
        androidx.fragment.app.h hVar;
        if (this.q == null && i2 != 0) {
            throw new IllegalStateException("No activity");
        }
        if (!z && i2 == this.p) {
            return;
        }
        this.p = i2;
        int size = this.f.size();
        for (int i3 = 0; i3 < size; i3++) {
            Q0((Fragment) this.f.get(i3));
        }
        for (Fragment fragment : this.g.values()) {
            if (fragment != null && (fragment.mRemoving || fragment.mDetached)) {
                if (!fragment.mIsNewlyAdded) {
                    Q0(fragment);
                }
            }
        }
        n1();
        if (this.u && (hVar = this.q) != null && this.p == 4) {
            hVar.r();
            this.u = false;
        }
    }

    public void S(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).S(fragment, bundle, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public void S0(Fragment fragment) {
        T0(fragment, this.p, 0, 0, false);
    }

    public void T(Fragment fragment, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).T(fragment, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0082, code lost:
        if (r0 != 3) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0491  */
    /* JADX WARN: Removed duplicated region for block: B:269:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void T0(androidx.fragment.app.Fragment r18, int r19, int r20, int r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 1203
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.j.T0(androidx.fragment.app.Fragment, int, int, int, boolean):void");
    }

    public void U(Fragment fragment, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).U(fragment, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public void U0() {
        this.v = false;
        this.w = false;
        int size = this.f.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = (Fragment) this.f.get(i2);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public void V(Fragment fragment, View view, Bundle bundle, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).V(fragment, view, bundle, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public void V0(Fragment fragment) {
        if (fragment.mDeferStart) {
            if (this.d) {
                this.y = true;
                return;
            }
            fragment.mDeferStart = false;
            T0(fragment, this.p, 0, 0, false);
        }
    }

    public void W(Fragment fragment, boolean z) {
        Fragment fragment2 = this.f20s;
        if (fragment2 != null) {
            androidx.fragment.app.i fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).W(fragment, true);
            }
        }
        Iterator it = this.o.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.m.a(it.next());
            if (z) {
                throw null;
            }
            throw null;
        }
    }

    public final boolean W0(String str, int i2, int i3) {
        n0();
        l0(true);
        Fragment fragment = this.t;
        if (fragment != null && i2 < 0 && str == null && fragment.getChildFragmentManager().h()) {
            return true;
        }
        boolean X0 = X0(this.z, this.A, str, i2, i3);
        if (X0) {
            this.d = true;
            try {
                a1(this.z, this.A);
            } finally {
                w();
            }
        }
        q1();
        i0();
        t();
        return X0;
    }

    public boolean X(MenuItem menuItem) {
        if (this.p < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = (Fragment) this.f.get(i2);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean X0(ArrayList arrayList, ArrayList arrayList2, String str, int i2, int i3) {
        int i4;
        ArrayList arrayList3 = this.h;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.h.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str == null && i2 < 0) {
                i4 = -1;
            } else {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    androidx.fragment.app.a aVar = (androidx.fragment.app.a) this.h.get(size2);
                    if ((str != null && str.equals(aVar.A())) || (i2 >= 0 && i2 == aVar.u)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        androidx.fragment.app.a aVar2 = (androidx.fragment.app.a) this.h.get(size2);
                        if (str == null || !str.equals(aVar2.A())) {
                            if (i2 < 0 || i2 != aVar2.u) {
                                break;
                            }
                        }
                    }
                }
                i4 = size2;
            }
            if (i4 == this.h.size() - 1) {
                return false;
            }
            for (int size3 = this.h.size() - 1; size3 > i4; size3--) {
                arrayList.add(this.h.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    public void Y(Menu menu) {
        if (this.p < 1) {
            return;
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = (Fragment) this.f.get(i2);
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
        }
    }

    public final int Y0(ArrayList arrayList, ArrayList arrayList2, int i2, int i3, androidx.collection.b bVar) {
        boolean z;
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            androidx.fragment.app.a aVar = (androidx.fragment.app.a) arrayList.get(i5);
            boolean booleanValue = ((Boolean) arrayList2.get(i5)).booleanValue();
            if (aVar.E() && !aVar.C(arrayList, i5 + 1, i3)) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                if (this.E == null) {
                    this.E = new ArrayList();
                }
                l lVar = new l(aVar, booleanValue);
                this.E.add(lVar);
                aVar.G(lVar);
                if (booleanValue) {
                    aVar.x();
                } else {
                    aVar.y(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, aVar);
                }
                l(bVar);
            }
        }
        return i4;
    }

    public final void Z(Fragment fragment) {
        if (fragment != null && this.g.get(fragment.mWho) == fragment) {
            fragment.performPrimaryNavigationFragmentChanged();
        }
    }

    public void Z0(Fragment fragment) {
        if (H) {
            StringBuilder sb = new StringBuilder();
            sb.append("remove: ");
            sb.append(fragment);
            sb.append(" nesting=");
            sb.append(fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            synchronized (this.f) {
                this.f.remove(fragment);
            }
            if (G0(fragment)) {
                this.u = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    @Override // androidx.fragment.app.i
    public p a() {
        return new androidx.fragment.app.a(this);
    }

    public void a0() {
        g0(3);
    }

    public final void a1(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList != null && !arrayList.isEmpty()) {
            if (arrayList2 != null && arrayList.size() == arrayList2.size()) {
                r0(arrayList, arrayList2);
                int size = arrayList.size();
                int i2 = 0;
                int i3 = 0;
                while (i2 < size) {
                    if (!((androidx.fragment.app.a) arrayList.get(i2)).q) {
                        if (i3 != i2) {
                            q0(arrayList, arrayList2, i3, i2);
                        }
                        i3 = i2 + 1;
                        if (((Boolean) arrayList2.get(i2)).booleanValue()) {
                            while (i3 < size && ((Boolean) arrayList2.get(i3)).booleanValue() && !((androidx.fragment.app.a) arrayList.get(i3)).q) {
                                i3++;
                            }
                        }
                        q0(arrayList, arrayList2, i2, i3);
                        i2 = i3 - 1;
                    }
                    i2++;
                }
                if (i3 != size) {
                    q0(arrayList, arrayList2, i3, size);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Internal error with the back stack records");
        }
    }

    @Override // androidx.fragment.app.i
    public void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        String str2 = str + "    ";
        if (!this.g.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (Fragment fragment : this.g.values()) {
                printWriter.print(str);
                printWriter.println(fragment);
                if (fragment != null) {
                    fragment.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size5 = this.f.size();
        if (size5 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size5; i2++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(((Fragment) this.f.get(i2)).toString());
            }
        }
        ArrayList arrayList = this.i;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i3 = 0; i3 < size4; i3++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(((Fragment) this.i.get(i3)).toString());
            }
        }
        ArrayList arrayList2 = this.h;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i4 = 0; i4 < size3; i4++) {
                androidx.fragment.app.a aVar = (androidx.fragment.app.a) this.h.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.v(str2, printWriter);
            }
        }
        synchronized (this) {
            ArrayList arrayList3 = this.l;
            if (arrayList3 != null && (size2 = arrayList3.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i5 = 0; i5 < size2; i5++) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i5);
                    printWriter.print(": ");
                    printWriter.println((androidx.fragment.app.a) this.l.get(i5));
                }
            }
            ArrayList arrayList4 = this.m;
            if (arrayList4 != null && arrayList4.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.m.toArray()));
            }
        }
        ArrayList arrayList5 = this.c;
        if (arrayList5 != null && (size = arrayList5.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i6 = 0; i6 < size; i6++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i6);
                printWriter.print(": ");
                printWriter.println((InterfaceC0017j) this.c.get(i6));
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.q);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.r);
        if (this.f20s != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f20s);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.p);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.v);
        printWriter.print(" mStopped=");
        printWriter.print(this.w);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.x);
        if (this.u) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.u);
        }
    }

    public void b0(boolean z) {
        for (int size = this.f.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) this.f.get(size);
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z);
            }
        }
    }

    public void b1(Fragment fragment) {
        if (!J0() && this.F.k(fragment) && H) {
            StringBuilder sb = new StringBuilder();
            sb.append("Updating retained Fragments: Removed ");
            sb.append(fragment);
        }
    }

    @Override // androidx.fragment.app.i
    public Fragment c(String str) {
        if (str != null) {
            for (int size = this.f.size() - 1; size >= 0; size--) {
                Fragment fragment = (Fragment) this.f.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (str != null) {
            for (Fragment fragment2 : this.g.values()) {
                if (fragment2 != null && str.equals(fragment2.mTag)) {
                    return fragment2;
                }
            }
            return null;
        }
        return null;
    }

    public boolean c0(Menu menu) {
        if (this.p < 1) {
            return false;
        }
        boolean z = false;
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = (Fragment) this.f.get(i2);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    public void c1() {
        ArrayList arrayList = this.n;
        if (arrayList != null && arrayList.size() > 0) {
            androidx.appcompat.app.m.a(this.n.get(0));
            throw null;
        }
    }

    @Override // androidx.fragment.app.i
    public Fragment d(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment fragment = (Fragment) this.g.get(string);
        if (fragment == null) {
            o1(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        }
        return fragment;
    }

    public void d0() {
        q1();
        Z(this.t);
    }

    public void d1(Parcelable parcelable) {
        n nVar;
        String str;
        if (parcelable == null) {
            return;
        }
        androidx.fragment.app.k kVar = (androidx.fragment.app.k) parcelable;
        if (kVar.a == null) {
            return;
        }
        for (Fragment fragment : this.F.h()) {
            if (H) {
                StringBuilder sb = new StringBuilder();
                sb.append("restoreSaveState: re-attaching retained ");
                sb.append(fragment);
            }
            Iterator it = kVar.a.iterator();
            while (true) {
                if (it.hasNext()) {
                    nVar = (n) it.next();
                    if (nVar.b.equals(fragment.mWho)) {
                        break;
                    }
                } else {
                    nVar = null;
                    break;
                }
            }
            if (nVar == null) {
                if (H) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Discarding retained Fragment ");
                    sb2.append(fragment);
                    sb2.append(" that was not found in the set of active Fragments ");
                    sb2.append(kVar.a);
                }
                T0(fragment, 1, 0, 0, false);
                fragment.mRemoving = true;
                T0(fragment, 0, 0, 0, false);
            } else {
                nVar.n = fragment;
                fragment.mSavedViewState = null;
                fragment.mBackStackNesting = 0;
                fragment.mInLayout = false;
                fragment.mAdded = false;
                Fragment fragment2 = fragment.mTarget;
                if (fragment2 != null) {
                    str = fragment2.mWho;
                } else {
                    str = null;
                }
                fragment.mTargetWho = str;
                fragment.mTarget = null;
                Bundle bundle = nVar.m;
                if (bundle != null) {
                    bundle.setClassLoader(this.q.e().getClassLoader());
                    fragment.mSavedViewState = nVar.m.getSparseParcelableArray("android:view_state");
                    fragment.mSavedFragmentState = nVar.m;
                }
            }
        }
        this.g.clear();
        Iterator it2 = kVar.a.iterator();
        while (it2.hasNext()) {
            n nVar2 = (n) it2.next();
            if (nVar2 != null) {
                Fragment a2 = nVar2.a(this.q.e().getClassLoader(), e());
                a2.mFragmentManager = this;
                if (H) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("restoreSaveState: active (");
                    sb3.append(a2.mWho);
                    sb3.append("): ");
                    sb3.append(a2);
                }
                this.g.put(a2.mWho, a2);
                nVar2.n = null;
            }
        }
        this.f.clear();
        ArrayList arrayList = kVar.b;
        if (arrayList != null) {
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                String str2 = (String) it3.next();
                Fragment fragment3 = (Fragment) this.g.get(str2);
                if (fragment3 == null) {
                    o1(new IllegalStateException("No instantiated fragment for (" + str2 + ")"));
                }
                fragment3.mAdded = true;
                if (H) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("restoreSaveState: added (");
                    sb4.append(str2);
                    sb4.append("): ");
                    sb4.append(fragment3);
                }
                if (!this.f.contains(fragment3)) {
                    synchronized (this.f) {
                        this.f.add(fragment3);
                    }
                } else {
                    throw new IllegalStateException("Already added " + fragment3);
                }
            }
        }
        if (kVar.c != null) {
            this.h = new ArrayList(kVar.c.length);
            int i2 = 0;
            while (true) {
                androidx.fragment.app.b[] bVarArr = kVar.c;
                if (i2 >= bVarArr.length) {
                    break;
                }
                androidx.fragment.app.a a3 = bVarArr[i2].a(this);
                if (H) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("restoreAllState: back stack #");
                    sb5.append(i2);
                    sb5.append(" (index ");
                    sb5.append(a3.u);
                    sb5.append("): ");
                    sb5.append(a3);
                    PrintWriter printWriter = new PrintWriter((Writer) new u.c("FragmentManager"));
                    a3.w("  ", printWriter, false);
                    printWriter.close();
                }
                this.h.add(a3);
                int i3 = a3.u;
                if (i3 >= 0) {
                    j1(i3, a3);
                }
                i2++;
            }
        } else {
            this.h = null;
        }
        String str3 = kVar.d;
        if (str3 != null) {
            Fragment fragment4 = (Fragment) this.g.get(str3);
            this.t = fragment4;
            Z(fragment4);
        }
        this.e = kVar.e;
    }

    @Override // androidx.fragment.app.i
    public androidx.fragment.app.g e() {
        if (super.e() == androidx.fragment.app.i.b) {
            Fragment fragment = this.f20s;
            if (fragment != null) {
                return fragment.mFragmentManager.e();
            }
            k(new f());
        }
        return super.e();
    }

    public void e0() {
        this.v = false;
        this.w = false;
        g0(4);
    }

    @Override // androidx.fragment.app.i
    public List f() {
        List list;
        if (this.f.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f) {
            list = (List) this.f.clone();
        }
        return list;
    }

    public void f0() {
        this.v = false;
        this.w = false;
        g0(3);
    }

    public Parcelable f1() {
        ArrayList arrayList;
        int size;
        v0();
        j0();
        n0();
        this.v = true;
        androidx.fragment.app.b[] bVarArr = null;
        if (this.g.isEmpty()) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList(this.g.size());
        boolean z = false;
        for (Fragment fragment : this.g.values()) {
            if (fragment != null) {
                if (fragment.mFragmentManager != this) {
                    o1(new IllegalStateException("Failure saving state: active " + fragment + " was removed from the FragmentManager"));
                }
                n nVar = new n(fragment);
                arrayList2.add(nVar);
                if (fragment.mState > 0 && nVar.m == null) {
                    nVar.m = g1(fragment);
                    String str = fragment.mTargetWho;
                    if (str != null) {
                        Fragment fragment2 = (Fragment) this.g.get(str);
                        if (fragment2 == null) {
                            o1(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.mTargetWho));
                        }
                        if (nVar.m == null) {
                            nVar.m = new Bundle();
                        }
                        i(nVar.m, "android:target_state", fragment2);
                        int i2 = fragment.mTargetRequestCode;
                        if (i2 != 0) {
                            nVar.m.putInt("android:target_req_state", i2);
                        }
                    }
                } else {
                    nVar.m = fragment.mSavedFragmentState;
                }
                if (H) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Saved state of ");
                    sb.append(fragment);
                    sb.append(": ");
                    sb.append(nVar.m);
                }
                z = true;
            }
        }
        if (!z) {
            return null;
        }
        int size2 = this.f.size();
        if (size2 > 0) {
            arrayList = new ArrayList(size2);
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                Fragment fragment3 = (Fragment) it.next();
                arrayList.add(fragment3.mWho);
                if (fragment3.mFragmentManager != this) {
                    o1(new IllegalStateException("Failure saving state: active " + fragment3 + " was removed from the FragmentManager"));
                }
                if (H) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("saveAllState: adding fragment (");
                    sb2.append(fragment3.mWho);
                    sb2.append("): ");
                    sb2.append(fragment3);
                }
            }
        } else {
            arrayList = null;
        }
        ArrayList arrayList3 = this.h;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            bVarArr = new androidx.fragment.app.b[size];
            for (int i3 = 0; i3 < size; i3++) {
                bVarArr[i3] = new androidx.fragment.app.b((androidx.fragment.app.a) this.h.get(i3));
                if (H) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("saveAllState: adding back stack #");
                    sb3.append(i3);
                    sb3.append(": ");
                    sb3.append(this.h.get(i3));
                }
            }
        }
        androidx.fragment.app.k kVar = new androidx.fragment.app.k();
        kVar.a = arrayList2;
        kVar.b = arrayList;
        kVar.c = bVarArr;
        Fragment fragment4 = this.t;
        if (fragment4 != null) {
            kVar.d = fragment4.mWho;
        }
        kVar.e = this.e;
        return kVar;
    }

    @Override // androidx.fragment.app.i
    public void g(int i2, int i3) {
        if (i2 >= 0) {
            k0(new k(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    public final void g0(int i2) {
        try {
            this.d = true;
            R0(i2, false);
            this.d = false;
            n0();
        } catch (Throwable th) {
            this.d = false;
            throw th;
        }
    }

    public Bundle g1(Fragment fragment) {
        if (this.C == null) {
            this.C = new Bundle();
        }
        fragment.performSaveInstanceState(this.C);
        S(fragment, this.C, false);
        Bundle bundle = null;
        if (!this.C.isEmpty()) {
            Bundle bundle2 = this.C;
            this.C = null;
            bundle = bundle2;
        }
        if (fragment.mView != null) {
            h1(fragment);
        }
        if (fragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        if (!fragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle;
    }

    @Override // androidx.fragment.app.i
    public boolean h() {
        v();
        return W0(null, -1, 0);
    }

    public void h0() {
        this.w = true;
        g0(2);
    }

    public void h1(Fragment fragment) {
        if (fragment.mInnerView == null) {
            return;
        }
        SparseArray sparseArray = this.D;
        if (sparseArray == null) {
            this.D = new SparseArray();
        } else {
            sparseArray.clear();
        }
        fragment.mInnerView.saveHierarchyState(this.D);
        if (this.D.size() > 0) {
            fragment.mSavedViewState = this.D;
            this.D = null;
        }
    }

    @Override // androidx.fragment.app.i
    public void i(Bundle bundle, String str, Fragment fragment) {
        if (fragment.mFragmentManager != this) {
            o1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(str, fragment.mWho);
    }

    public void i0() {
        if (this.y) {
            this.y = false;
            n1();
        }
    }

    public void i1() {
        boolean z;
        synchronized (this) {
            ArrayList arrayList = this.E;
            boolean z2 = false;
            if (arrayList != null && !arrayList.isEmpty()) {
                z = true;
            } else {
                z = false;
            }
            ArrayList arrayList2 = this.c;
            if (arrayList2 != null && arrayList2.size() == 1) {
                z2 = true;
            }
            if (z || z2) {
                this.q.f().removeCallbacks(this.G);
                this.q.f().post(this.G);
                q1();
            }
        }
    }

    @Override // androidx.fragment.app.i
    public Fragment.g j(Fragment fragment) {
        Bundle g1;
        if (fragment.mFragmentManager != this) {
            o1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        if (fragment.mState <= 0 || (g1 = g1(fragment)) == null) {
            return null;
        }
        return new Fragment.g(g1);
    }

    public final void j0() {
        for (Fragment fragment : this.g.values()) {
            if (fragment != null) {
                if (fragment.getAnimatingAway() != null) {
                    int stateAfterAnimating = fragment.getStateAfterAnimating();
                    View animatingAway = fragment.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    fragment.setAnimatingAway(null);
                    T0(fragment, stateAfterAnimating, 0, 0, false);
                } else if (fragment.getAnimator() != null) {
                    fragment.getAnimator().end();
                }
            }
        }
    }

    public void j1(int i2, androidx.fragment.app.a aVar) {
        synchronized (this) {
            if (this.l == null) {
                this.l = new ArrayList();
            }
            int size = this.l.size();
            if (i2 < size) {
                if (H) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Setting back stack index ");
                    sb.append(i2);
                    sb.append(" to ");
                    sb.append(aVar);
                }
                this.l.set(i2, aVar);
            } else {
                while (size < i2) {
                    this.l.add(null);
                    if (this.m == null) {
                        this.m = new ArrayList();
                    }
                    if (H) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Adding available back stack index ");
                        sb2.append(size);
                    }
                    this.m.add(Integer.valueOf(size));
                    size++;
                }
                if (H) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Adding back stack index ");
                    sb3.append(i2);
                    sb3.append(" with ");
                    sb3.append(aVar);
                }
                this.l.add(aVar);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0027, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void k0(androidx.fragment.app.j.InterfaceC0017j r2, boolean r3) {
        /*
            r1 = this;
            if (r3 != 0) goto L5
            r1.v()
        L5:
            monitor-enter(r1)
            boolean r0 = r1.x     // Catch: java.lang.Throwable -> L30
            if (r0 != 0) goto L24
            androidx.fragment.app.h r0 = r1.q     // Catch: java.lang.Throwable -> L30
            if (r0 != 0) goto Lf
            goto L24
        Lf:
            java.util.ArrayList r3 = r1.c     // Catch: java.lang.Throwable -> L30
            if (r3 != 0) goto L1a
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L30
            r3.<init>()     // Catch: java.lang.Throwable -> L30
            r1.c = r3     // Catch: java.lang.Throwable -> L30
        L1a:
            java.util.ArrayList r3 = r1.c     // Catch: java.lang.Throwable -> L30
            r3.add(r2)     // Catch: java.lang.Throwable -> L30
            r1.i1()     // Catch: java.lang.Throwable -> L30
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L30
            return
        L24:
            if (r3 == 0) goto L28
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L30
            return
        L28:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L30
            java.lang.String r3 = "Activity has been destroyed"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L30
            throw r2     // Catch: java.lang.Throwable -> L30
        L30:
            r2 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L30
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.j.k0(androidx.fragment.app.j$j, boolean):void");
    }

    public void k1(Fragment fragment, c.b bVar) {
        if (this.g.get(fragment.mWho) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this)) {
            fragment.mMaxState = bVar;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public final void l(androidx.collection.b bVar) {
        int i2 = this.p;
        if (i2 < 1) {
            return;
        }
        int min = Math.min(i2, 3);
        int size = this.f.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = (Fragment) this.f.get(i3);
            if (fragment.mState < min) {
                T0(fragment, min, fragment.getNextAnim(), fragment.getNextTransition(), false);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    bVar.add(fragment);
                }
            }
        }
    }

    public final void l0(boolean z) {
        if (!this.d) {
            if (this.q != null) {
                if (Looper.myLooper() == this.q.f().getLooper()) {
                    if (!z) {
                        v();
                    }
                    if (this.z == null) {
                        this.z = new ArrayList();
                        this.A = new ArrayList();
                    }
                    this.d = true;
                    try {
                        r0(null, null);
                        return;
                    } finally {
                        this.d = false;
                    }
                }
                throw new IllegalStateException("Must be called from main thread of fragment host");
            }
            throw new IllegalStateException("Fragment host has been destroyed");
        }
        throw new IllegalStateException("FragmentManager is already executing transactions");
    }

    public void l1(Fragment fragment) {
        if (fragment != null && (this.g.get(fragment.mWho) != fragment || (fragment.mHost != null && fragment.getFragmentManager() != this))) {
            throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
        }
        Fragment fragment2 = this.t;
        this.t = fragment;
        Z(fragment2);
        Z(this.t);
    }

    public void m(androidx.fragment.app.a aVar) {
        if (this.h == null) {
            this.h = new ArrayList();
        }
        this.h.add(aVar);
    }

    public void m0(Fragment fragment) {
        if (fragment.mFromLayout && !fragment.mPerformedCreateView) {
            fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
            View view = fragment.mView;
            if (view != null) {
                fragment.mInnerView = view;
                view.setSaveFromParentEnabled(false);
                if (fragment.mHidden) {
                    fragment.mView.setVisibility(8);
                }
                fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                V(fragment, fragment.mView, fragment.mSavedFragmentState, false);
                return;
            }
            fragment.mInnerView = null;
        }
    }

    public void m1(Fragment fragment) {
        if (H) {
            StringBuilder sb = new StringBuilder();
            sb.append("show: ");
            sb.append(fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    public void n(Fragment fragment, boolean z) {
        if (H) {
            StringBuilder sb = new StringBuilder();
            sb.append("add: ");
            sb.append(fragment);
        }
        L0(fragment);
        if (!fragment.mDetached) {
            if (!this.f.contains(fragment)) {
                synchronized (this.f) {
                    this.f.add(fragment);
                }
                fragment.mAdded = true;
                fragment.mRemoving = false;
                if (fragment.mView == null) {
                    fragment.mHiddenChanged = false;
                }
                if (G0(fragment)) {
                    this.u = true;
                }
                if (z) {
                    S0(fragment);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
    }

    public boolean n0() {
        l0(true);
        boolean z = false;
        while (x0(this.z, this.A)) {
            this.d = true;
            try {
                a1(this.z, this.A);
                w();
                z = true;
            } catch (Throwable th) {
                w();
                throw th;
            }
        }
        q1();
        i0();
        t();
        return z;
    }

    public void n1() {
        for (Fragment fragment : this.g.values()) {
            if (fragment != null) {
                V0(fragment);
            }
        }
    }

    public void o(Fragment fragment) {
        if (!J0() && this.F.d(fragment) && H) {
            StringBuilder sb = new StringBuilder();
            sb.append("Updating retained Fragments: Added ");
            sb.append(fragment);
        }
    }

    public void o0(InterfaceC0017j interfaceC0017j, boolean z) {
        if (z && (this.q == null || this.x)) {
            return;
        }
        l0(z);
        if (interfaceC0017j.a(this.z, this.A)) {
            this.d = true;
            try {
                a1(this.z, this.A);
            } finally {
                w();
            }
        }
        q1();
        i0();
        t();
    }

    public final void o1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter((Writer) new u.c("FragmentManager"));
        androidx.fragment.app.h hVar = this.q;
        if (hVar != null) {
            try {
                hVar.h("  ", null, printWriter, new String[0]);
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
            }
        } else {
            try {
                b("  ", null, printWriter, new String[0]);
            } catch (Exception e3) {
                Log.e("FragmentManager", "Failed dumping state", e3);
            }
        }
        throw runtimeException;
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if ("fragment".equals(str)) {
            String attributeValue = attributeSet.getAttributeValue(null, "class");
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.a);
            if (attributeValue == null) {
                attributeValue = obtainStyledAttributes.getString(0);
            }
            String str2 = attributeValue;
            int resourceId = obtainStyledAttributes.getResourceId(1, -1);
            String string = obtainStyledAttributes.getString(2);
            obtainStyledAttributes.recycle();
            if (str2 == null || !androidx.fragment.app.g.b(context.getClassLoader(), str2)) {
                return null;
            }
            int id = view != null ? view.getId() : 0;
            if (id == -1 && resourceId == -1 && string == null) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
            }
            Fragment s0 = resourceId != -1 ? s0(resourceId) : null;
            if (s0 == null && string != null) {
                s0 = c(string);
            }
            if (s0 == null && id != -1) {
                s0 = s0(id);
            }
            if (H) {
                StringBuilder sb = new StringBuilder();
                sb.append("onCreateView: id=0x");
                sb.append(Integer.toHexString(resourceId));
                sb.append(" fname=");
                sb.append(str2);
                sb.append(" existing=");
                sb.append(s0);
            }
            if (s0 == null) {
                s0 = e().a(context.getClassLoader(), str2);
                s0.mFromLayout = true;
                s0.mFragmentId = resourceId != 0 ? resourceId : id;
                s0.mContainerId = id;
                s0.mTag = string;
                s0.mInLayout = true;
                s0.mFragmentManager = this;
                androidx.fragment.app.h hVar = this.q;
                s0.mHost = hVar;
                s0.onInflate(hVar.e(), attributeSet, s0.mSavedFragmentState);
                n(s0, true);
            } else if (!s0.mInLayout) {
                s0.mInLayout = true;
                androidx.fragment.app.h hVar2 = this.q;
                s0.mHost = hVar2;
                s0.onInflate(hVar2.e(), attributeSet, s0.mSavedFragmentState);
            } else {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            Fragment fragment = s0;
            if (this.p < 1 && fragment.mFromLayout) {
                T0(fragment, 1, 0, 0, false);
            } else {
                S0(fragment);
            }
            View view2 = fragment.mView;
            if (view2 != null) {
                if (resourceId != 0) {
                    view2.setId(resourceId);
                }
                if (fragment.mView.getTag() == null) {
                    fragment.mView.setTag(string);
                }
                return fragment.mView;
            }
            throw new IllegalStateException("Fragment " + str2 + " did not create a view.");
        }
        return null;
    }

    public int p(androidx.fragment.app.a aVar) {
        synchronized (this) {
            ArrayList arrayList = this.m;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = this.m;
                int intValue = ((Integer) arrayList2.remove(arrayList2.size() - 1)).intValue();
                if (H) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Adding back stack index ");
                    sb.append(intValue);
                    sb.append(" with ");
                    sb.append(aVar);
                }
                this.l.set(intValue, aVar);
                return intValue;
            }
            if (this.l == null) {
                this.l = new ArrayList();
            }
            int size = this.l.size();
            if (H) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Setting back stack index ");
                sb2.append(size);
                sb2.append(" to ");
                sb2.append(aVar);
            }
            this.l.add(aVar);
            return size;
        }
    }

    public final void q(Fragment fragment, g gVar, int i2) {
        View view = fragment.mView;
        ViewGroup viewGroup = fragment.mContainer;
        viewGroup.startViewTransition(view);
        fragment.setStateAfterAnimating(i2);
        if (gVar.a != null) {
            h hVar = new h(gVar.a, viewGroup, view);
            fragment.setAnimatingAway(fragment.mView);
            hVar.setAnimationListener(new c(viewGroup, fragment));
            fragment.mView.startAnimation(hVar);
            return;
        }
        Animator animator = gVar.b;
        fragment.setAnimator(animator);
        animator.addListener(new d(viewGroup, view, fragment));
        animator.setTarget(fragment.mView);
        animator.start();
    }

    public final void q0(ArrayList arrayList, ArrayList arrayList2, int i2, int i3) {
        int i4;
        int i5;
        int i6 = i2;
        boolean z = ((androidx.fragment.app.a) arrayList.get(i6)).q;
        ArrayList arrayList3 = this.B;
        if (arrayList3 == null) {
            this.B = new ArrayList();
        } else {
            arrayList3.clear();
        }
        this.B.addAll(this.f);
        Fragment B0 = B0();
        boolean z2 = false;
        for (int i7 = i6; i7 < i3; i7++) {
            androidx.fragment.app.a aVar = (androidx.fragment.app.a) arrayList.get(i7);
            if (!((Boolean) arrayList2.get(i7)).booleanValue()) {
                B0 = aVar.z(this.B, B0);
            } else {
                B0 = aVar.H(this.B, B0);
            }
            if (!z2 && !aVar.h) {
                z2 = false;
            } else {
                z2 = true;
            }
        }
        this.B.clear();
        if (!z) {
            q.B(this, arrayList, arrayList2, i2, i3, false);
        }
        p0(arrayList, arrayList2, i2, i3);
        if (z) {
            androidx.collection.b bVar = new androidx.collection.b();
            l(bVar);
            int Y0 = Y0(arrayList, arrayList2, i2, i3, bVar);
            P0(bVar);
            i4 = Y0;
        } else {
            i4 = i3;
        }
        if (i4 != i6 && z) {
            q.B(this, arrayList, arrayList2, i2, i4, true);
            R0(this.p, true);
        }
        while (i6 < i3) {
            androidx.fragment.app.a aVar2 = (androidx.fragment.app.a) arrayList.get(i6);
            if (((Boolean) arrayList2.get(i6)).booleanValue() && (i5 = aVar2.u) >= 0) {
                w0(i5);
                aVar2.u = -1;
            }
            aVar2.F();
            i6++;
        }
        if (z2) {
            c1();
        }
    }

    public final void q1() {
        ArrayList arrayList = this.c;
        boolean z = true;
        if (arrayList != null && !arrayList.isEmpty()) {
            this.k.f(true);
        } else {
            this.k.f((y0() <= 0 || !H0(this.f20s)) ? false : false);
        }
    }

    public void r(androidx.fragment.app.h hVar, androidx.fragment.app.e eVar, Fragment fragment) {
        if (this.q == null) {
            this.q = hVar;
            this.r = eVar;
            this.f20s = fragment;
            if (fragment != null) {
                q1();
            }
            if (hVar instanceof androidx.activity.c) {
                androidx.activity.c cVar = (androidx.activity.c) hVar;
                OnBackPressedDispatcher onBackPressedDispatcher = cVar.getOnBackPressedDispatcher();
                this.j = onBackPressedDispatcher;
                androidx.lifecycle.f fVar = cVar;
                if (fragment != null) {
                    fVar = fragment;
                }
                onBackPressedDispatcher.a(fVar, this.k);
            }
            if (fragment != null) {
                this.F = fragment.mFragmentManager.z0(fragment);
                return;
            } else if (hVar instanceof androidx.lifecycle.t) {
                this.F = androidx.fragment.app.l.g(((androidx.lifecycle.t) hVar).getViewModelStore());
                return;
            } else {
                this.F = new androidx.fragment.app.l(false);
                return;
            }
        }
        throw new IllegalStateException("Already attached");
    }

    public final void r0(ArrayList arrayList, ArrayList arrayList2) {
        int size;
        int indexOf;
        int indexOf2;
        ArrayList arrayList3 = this.E;
        if (arrayList3 == null) {
            size = 0;
        } else {
            size = arrayList3.size();
        }
        int i2 = 0;
        while (i2 < size) {
            l lVar = (l) this.E.get(i2);
            if (arrayList != null && !lVar.a && (indexOf2 = arrayList.indexOf(lVar.b)) != -1 && ((Boolean) arrayList2.get(indexOf2)).booleanValue()) {
                this.E.remove(i2);
                i2--;
                size--;
                lVar.c();
            } else if (lVar.e() || (arrayList != null && lVar.b.C(arrayList, 0, arrayList.size()))) {
                this.E.remove(i2);
                i2--;
                size--;
                if (arrayList != null && !lVar.a && (indexOf = arrayList.indexOf(lVar.b)) != -1 && ((Boolean) arrayList2.get(indexOf)).booleanValue()) {
                    lVar.c();
                } else {
                    lVar.d();
                }
            }
            i2++;
        }
    }

    public void s(Fragment fragment) {
        if (H) {
            StringBuilder sb = new StringBuilder();
            sb.append("attach: ");
            sb.append(fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (!fragment.mAdded) {
                if (!this.f.contains(fragment)) {
                    if (H) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("add from attach: ");
                        sb2.append(fragment);
                    }
                    synchronized (this.f) {
                        this.f.add(fragment);
                    }
                    fragment.mAdded = true;
                    if (G0(fragment)) {
                        this.u = true;
                        return;
                    }
                    return;
                }
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
        }
    }

    public Fragment s0(int i2) {
        for (int size = this.f.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) this.f.get(size);
            if (fragment != null && fragment.mFragmentId == i2) {
                return fragment;
            }
        }
        for (Fragment fragment2 : this.g.values()) {
            if (fragment2 != null && fragment2.mFragmentId == i2) {
                return fragment2;
            }
        }
        return null;
    }

    public final void t() {
        this.g.values().removeAll(Collections.singleton(null));
    }

    public Fragment t0(String str) {
        Fragment findFragmentByWho;
        for (Fragment fragment : this.g.values()) {
            if (fragment != null && (findFragmentByWho = fragment.findFragmentByWho(str)) != null) {
                return findFragmentByWho;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.f20s;
        if (fragment != null) {
            u.b.a(fragment, sb);
        } else {
            u.b.a(this.q, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    public boolean u() {
        boolean z = false;
        for (Fragment fragment : this.g.values()) {
            if (fragment != null) {
                z = G0(fragment);
                continue;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final Fragment u0(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        View view = fragment.mView;
        if (viewGroup != null && view != null) {
            for (int indexOf = this.f.indexOf(fragment) - 1; indexOf >= 0; indexOf--) {
                Fragment fragment2 = (Fragment) this.f.get(indexOf);
                if (fragment2.mContainer == viewGroup && fragment2.mView != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    public final void v() {
        if (!J0()) {
            return;
        }
        throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
    }

    public final void v0() {
        if (this.E != null) {
            while (!this.E.isEmpty()) {
                ((l) this.E.remove(0)).d();
            }
        }
    }

    public final void w() {
        this.d = false;
        this.A.clear();
        this.z.clear();
    }

    public void w0(int i2) {
        synchronized (this) {
            this.l.set(i2, null);
            if (this.m == null) {
                this.m = new ArrayList();
            }
            if (H) {
                StringBuilder sb = new StringBuilder();
                sb.append("Freeing back stack index ");
                sb.append(i2);
            }
            this.m.add(Integer.valueOf(i2));
        }
    }

    public void x(androidx.fragment.app.a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.y(z3);
        } else {
            aVar.x();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            q.B(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z3) {
            R0(this.p, true);
        }
        for (Fragment fragment : this.g.values()) {
            if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && aVar.B(fragment.mContainerId)) {
                float f2 = fragment.mPostponedAlpha;
                if (f2 > 0.0f) {
                    fragment.mView.setAlpha(f2);
                }
                if (z3) {
                    fragment.mPostponedAlpha = 0.0f;
                } else {
                    fragment.mPostponedAlpha = -1.0f;
                    fragment.mIsNewlyAdded = false;
                }
            }
        }
    }

    public final boolean x0(ArrayList arrayList, ArrayList arrayList2) {
        synchronized (this) {
            ArrayList arrayList3 = this.c;
            if (arrayList3 != null && arrayList3.size() != 0) {
                int size = this.c.size();
                boolean z = false;
                for (int i2 = 0; i2 < size; i2++) {
                    z |= ((InterfaceC0017j) this.c.get(i2)).a(arrayList, arrayList2);
                }
                this.c.clear();
                this.q.f().removeCallbacks(this.G);
                return z;
            }
            return false;
        }
    }

    public void y(Fragment fragment) {
        int i2;
        Animator animator;
        if (fragment.mView != null) {
            g K0 = K0(fragment, fragment.getNextTransition(), !fragment.mHidden, fragment.getNextTransitionStyle());
            if (K0 != null && (animator = K0.b) != null) {
                animator.setTarget(fragment.mView);
                if (fragment.mHidden) {
                    if (fragment.isHideReplaced()) {
                        fragment.setHideReplaced(false);
                    } else {
                        ViewGroup viewGroup = fragment.mContainer;
                        View view = fragment.mView;
                        viewGroup.startViewTransition(view);
                        K0.b.addListener(new e(viewGroup, view, fragment));
                    }
                } else {
                    fragment.mView.setVisibility(0);
                }
                K0.b.start();
            } else {
                if (K0 != null) {
                    fragment.mView.startAnimation(K0.a);
                    K0.a.start();
                }
                if (fragment.mHidden && !fragment.isHideReplaced()) {
                    i2 = 8;
                } else {
                    i2 = 0;
                }
                fragment.mView.setVisibility(i2);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            }
        }
        if (fragment.mAdded && G0(fragment)) {
            this.u = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    public int y0() {
        ArrayList arrayList = this.h;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public void z(Fragment fragment) {
        if (H) {
            StringBuilder sb = new StringBuilder();
            sb.append("detach: ");
            sb.append(fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (H) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("remove from detach: ");
                    sb2.append(fragment);
                }
                synchronized (this.f) {
                    this.f.remove(fragment);
                }
                if (G0(fragment)) {
                    this.u = true;
                }
                fragment.mAdded = false;
            }
        }
    }

    public androidx.fragment.app.l z0(Fragment fragment) {
        return this.F.f(fragment);
    }

    /* loaded from: classes.dex */
    public static class g {
        public final Animation a;
        public final Animator b;

        public g(Animation animation) {
            this.a = animation;
            this.b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        public g(Animator animator) {
            this.a = null;
            this.b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* loaded from: classes.dex */
    public static class h extends AnimationSet implements Runnable {
        public final ViewGroup a;
        public final View b;
        public boolean c;
        public boolean d;
        public boolean e;

        public h(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.e = true;
            this.a = viewGroup;
            this.b = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation) {
            this.e = true;
            if (this.c) {
                return !this.d;
            }
            if (!super.getTransformation(j, transformation)) {
                this.c = true;
                d0.a(this.a, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.c && this.e) {
                this.e = false;
                this.a.post(this);
                return;
            }
            this.a.endViewTransition(this.b);
            this.d = true;
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation, float f) {
            this.e = true;
            if (this.c) {
                return !this.d;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.c = true;
                d0.a(this.a, this);
            }
            return true;
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
