package androidx.customview.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.collection.h;
import androidx.customview.widget.b;
import java.util.ArrayList;
import java.util.List;
import v.a2;
import v.d1;
import w.g0;
import w.j0;
import w.k0;
/* loaded from: classes.dex */
public abstract class a extends v.a {
    private static final String DEFAULT_CLASS_NAME = "android.view.View";
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = Integer.MIN_VALUE;
    private static final Rect INVALID_PARENT_BOUNDS = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final b.a NODE_ADAPTER = new C0014a();
    private static final b.InterfaceC0015b SPARSE_VALUES_ADAPTER = new b();
    private final View mHost;
    private final AccessibilityManager mManager;
    private c mNodeProvider;
    private final Rect mTempScreenRect = new Rect();
    private final Rect mTempParentRect = new Rect();
    private final Rect mTempVisibleRect = new Rect();
    private final int[] mTempGlobalRect = new int[2];
    int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
    int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
    private int mHoveredVirtualViewId = Integer.MIN_VALUE;

    /* renamed from: androidx.customview.widget.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0014a implements b.a {
        @Override // androidx.customview.widget.b.a
        /* renamed from: b */
        public void a(g0 g0Var, Rect rect) {
            g0Var.l(rect);
        }
    }

    /* loaded from: classes.dex */
    public static class b implements b.InterfaceC0015b {
        @Override // androidx.customview.widget.b.InterfaceC0015b
        /* renamed from: c */
        public g0 a(h hVar, int i) {
            return (g0) hVar.m(i);
        }

        @Override // androidx.customview.widget.b.InterfaceC0015b
        /* renamed from: d */
        public int b(h hVar) {
            return hVar.l();
        }
    }

    /* loaded from: classes.dex */
    public class c extends j0 {
        public c() {
        }

        public g0 b(int i) {
            return g0.M(a.this.obtainAccessibilityNodeInfo(i));
        }

        public g0 d(int i) {
            int i2;
            if (i == 2) {
                i2 = a.this.mAccessibilityFocusedVirtualViewId;
            } else {
                i2 = a.this.mKeyboardFocusedVirtualViewId;
            }
            if (i2 == Integer.MIN_VALUE) {
                return null;
            }
            return b(i2);
        }

        public boolean f(int i, int i2, Bundle bundle) {
            return a.this.performAction(i, i2, bundle);
        }
    }

    public a(View view) {
        if (view != null) {
            this.mHost = view;
            this.mManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
            view.setFocusable(true);
            if (d1.x(view) == 0) {
                d1.v0(view, 1);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("View may not be null");
    }

    public static Rect l(View view, int i, Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i != 17) {
            if (i != 33) {
                if (i != 66) {
                    if (i == 130) {
                        rect.set(0, -1, width, -1);
                    } else {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                } else {
                    rect.set(-1, 0, -1, height);
                }
            } else {
                rect.set(0, height, width, height);
            }
        } else {
            rect.set(width, 0, width, height);
        }
        return rect;
    }

    public static int n(int i) {
        if (i != 19) {
            if (i != 21) {
                return i != 22 ? 130 : 66;
            }
            return 17;
        }
        return 33;
    }

    public final boolean c(int i) {
        if (this.mAccessibilityFocusedVirtualViewId == i) {
            this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
            this.mHost.invalidate();
            sendEventForVirtualView(i, 65536);
            return true;
        }
        return false;
    }

    public final boolean clearKeyboardFocusForVirtualView(int i) {
        if (this.mKeyboardFocusedVirtualViewId != i) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        onVirtualViewKeyboardFocusChanged(i, false);
        sendEventForVirtualView(i, 8);
        return true;
    }

    public final boolean d() {
        int i = this.mKeyboardFocusedVirtualViewId;
        if (i != Integer.MIN_VALUE && onPerformActionForVirtualView(i, 16, null)) {
            return true;
        }
        return false;
    }

    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (!this.mManager.isEnabled() || !this.mManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7 && action != 9) {
            if (action != 10 || this.mHoveredVirtualViewId == Integer.MIN_VALUE) {
                return false;
            }
            s(Integer.MIN_VALUE);
            return true;
        }
        int virtualViewAt = getVirtualViewAt(motionEvent.getX(), motionEvent.getY());
        s(virtualViewAt);
        if (virtualViewAt == Integer.MIN_VALUE) {
            return false;
        }
        return true;
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int i = 0;
        if (keyEvent.getAction() == 1) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 61) {
            if (keyCode != 66) {
                switch (keyCode) {
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                        if (!keyEvent.hasNoModifiers()) {
                            return false;
                        }
                        int n = n(keyCode);
                        int repeatCount = keyEvent.getRepeatCount() + 1;
                        boolean z = false;
                        while (i < repeatCount && o(n, null)) {
                            i++;
                            z = true;
                        }
                        return z;
                    case 23:
                        break;
                    default:
                        return false;
                }
            }
            if (!keyEvent.hasNoModifiers() || keyEvent.getRepeatCount() != 0) {
                return false;
            }
            d();
            return true;
        } else if (keyEvent.hasNoModifiers()) {
            return o(2, null);
        } else {
            if (!keyEvent.hasModifiers(1)) {
                return false;
            }
            return o(1, null);
        }
    }

    public final AccessibilityEvent e(int i, int i2) {
        if (i != -1) {
            return f(i, i2);
        }
        return g(i2);
    }

    public final AccessibilityEvent f(int i, int i2) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
        g0 obtainAccessibilityNodeInfo = obtainAccessibilityNodeInfo(i);
        obtain.getText().add(obtainAccessibilityNodeInfo.v());
        obtain.setContentDescription(obtainAccessibilityNodeInfo.q());
        obtain.setScrollable(obtainAccessibilityNodeInfo.H());
        obtain.setPassword(obtainAccessibilityNodeInfo.G());
        obtain.setEnabled(obtainAccessibilityNodeInfo.C());
        obtain.setChecked(obtainAccessibilityNodeInfo.A());
        onPopulateEventForVirtualView(i, obtain);
        if (obtain.getText().isEmpty() && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        obtain.setClassName(obtainAccessibilityNodeInfo.o());
        k0.g(obtain, this.mHost, i);
        obtain.setPackageName(this.mHost.getContext().getPackageName());
        return obtain;
    }

    public final AccessibilityEvent g(int i) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i);
        this.mHost.onInitializeAccessibilityEvent(obtain);
        return obtain;
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.mAccessibilityFocusedVirtualViewId;
    }

    public j0 getAccessibilityNodeProvider(View view) {
        if (this.mNodeProvider == null) {
            this.mNodeProvider = new c();
        }
        return this.mNodeProvider;
    }

    @Deprecated
    public int getFocusedVirtualView() {
        return getAccessibilityFocusedVirtualViewId();
    }

    public final int getKeyboardFocusedVirtualViewId() {
        return this.mKeyboardFocusedVirtualViewId;
    }

    public abstract int getVirtualViewAt(float f, float f2);

    public abstract void getVisibleVirtualViews(List list);

    public final g0 h(int i) {
        boolean z;
        g0 K = g0.K();
        K.d0(true);
        K.f0(true);
        K.W(DEFAULT_CLASS_NAME);
        Rect rect = INVALID_PARENT_BOUNDS;
        K.S(rect);
        K.T(rect);
        K.l0(this.mHost);
        onPopulateNodeForVirtualView(i, K);
        if (K.v() == null && K.q() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        K.l(this.mTempParentRect);
        if (!this.mTempParentRect.equals(rect)) {
            int j = K.j();
            if ((j & 64) == 0) {
                if ((j & 128) == 0) {
                    K.j0(this.mHost.getContext().getPackageName());
                    K.q0(this.mHost, i);
                    if (this.mAccessibilityFocusedVirtualViewId == i) {
                        K.Q(true);
                        K.a(128);
                    } else {
                        K.Q(false);
                        K.a(64);
                    }
                    if (this.mKeyboardFocusedVirtualViewId == i) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        K.a(2);
                    } else if (K.D()) {
                        K.a(1);
                    }
                    K.g0(z);
                    this.mHost.getLocationOnScreen(this.mTempGlobalRect);
                    K.m(this.mTempScreenRect);
                    if (this.mTempScreenRect.equals(rect)) {
                        K.l(this.mTempScreenRect);
                        if (K.b != -1) {
                            g0 K2 = g0.K();
                            for (int i2 = K.b; i2 != -1; i2 = K2.b) {
                                K2.m0(this.mHost, -1);
                                K2.S(INVALID_PARENT_BOUNDS);
                                onPopulateNodeForVirtualView(i2, K2);
                                K2.l(this.mTempParentRect);
                                Rect rect2 = this.mTempScreenRect;
                                Rect rect3 = this.mTempParentRect;
                                rect2.offset(rect3.left, rect3.top);
                            }
                            K2.O();
                        }
                        this.mTempScreenRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
                    }
                    if (this.mHost.getLocalVisibleRect(this.mTempVisibleRect)) {
                        this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
                        if (this.mTempScreenRect.intersect(this.mTempVisibleRect)) {
                            K.T(this.mTempScreenRect);
                            if (m(this.mTempScreenRect)) {
                                K.t0(true);
                            }
                        }
                    }
                    return K;
                }
                throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            }
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
    }

    public final g0 i() {
        g0 L = g0.L(this.mHost);
        d1.Y(this.mHost, L);
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        if (L.n() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            L.c(this.mHost, ((Integer) arrayList.get(i)).intValue());
        }
        return L;
    }

    public final void invalidateRoot() {
        invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int i) {
        invalidateVirtualView(i, 0);
    }

    public final h j() {
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        h hVar = new h();
        for (int i = 0; i < arrayList.size(); i++) {
            hVar.j(i, h(i));
        }
        return hVar;
    }

    public final void k(int i, Rect rect) {
        obtainAccessibilityNodeInfo(i).l(rect);
    }

    public final boolean m(Rect rect) {
        if (rect == null || rect.isEmpty() || this.mHost.getWindowVisibility() != 0) {
            return false;
        }
        ViewParent parent = this.mHost.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        if (parent == null) {
            return false;
        }
        return true;
    }

    public final boolean o(int i, Rect rect) {
        g0 g0Var;
        boolean z;
        g0 g0Var2;
        h j = j();
        int i2 = this.mKeyboardFocusedVirtualViewId;
        int i3 = Integer.MIN_VALUE;
        if (i2 == Integer.MIN_VALUE) {
            g0Var = null;
        } else {
            g0Var = (g0) j.e(i2);
        }
        g0 g0Var3 = g0Var;
        if (i != 1 && i != 2) {
            if (i != 17 && i != 33 && i != 66 && i != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            Rect rect2 = new Rect();
            int i4 = this.mKeyboardFocusedVirtualViewId;
            if (i4 != Integer.MIN_VALUE) {
                k(i4, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                l(this.mHost, i, rect2);
            }
            g0Var2 = (g0) androidx.customview.widget.b.c(j, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, g0Var3, rect2, i);
        } else {
            if (d1.z(this.mHost) == 1) {
                z = true;
            } else {
                z = false;
            }
            g0Var2 = (g0) androidx.customview.widget.b.d(j, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, g0Var3, i, z, false);
        }
        if (g0Var2 != null) {
            i3 = j.i(j.h(g0Var2));
        }
        return requestKeyboardFocusForVirtualView(i3);
    }

    public g0 obtainAccessibilityNodeInfo(int i) {
        if (i == -1) {
            return i();
        }
        return h(i);
    }

    public final void onFocusChanged(boolean z, int i, Rect rect) {
        int i2 = this.mKeyboardFocusedVirtualViewId;
        if (i2 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i2);
        }
        if (z) {
            o(i, rect);
        }
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        onPopulateEventForHost(accessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, g0 g0Var) {
        super.onInitializeAccessibilityNodeInfo(view, g0Var);
        onPopulateNodeForHost(g0Var);
    }

    public abstract boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle);

    public void onPopulateEventForHost(AccessibilityEvent accessibilityEvent) {
    }

    public void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityEvent) {
    }

    public abstract void onPopulateNodeForHost(g0 g0Var);

    public abstract void onPopulateNodeForVirtualView(int i, g0 g0Var);

    public abstract void onVirtualViewKeyboardFocusChanged(int i, boolean z);

    public final boolean p(int i, int i2, Bundle bundle) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 64) {
                    if (i2 != 128) {
                        return onPerformActionForVirtualView(i, i2, bundle);
                    }
                    return c(i);
                }
                return r(i);
            }
            return clearKeyboardFocusForVirtualView(i);
        }
        return requestKeyboardFocusForVirtualView(i);
    }

    public boolean performAction(int i, int i2, Bundle bundle) {
        if (i != -1) {
            return p(i, i2, bundle);
        }
        return q(i2, bundle);
    }

    public final boolean q(int i, Bundle bundle) {
        return d1.a0(this.mHost, i, bundle);
    }

    public final boolean r(int i) {
        int i2;
        if (!this.mManager.isEnabled() || !this.mManager.isTouchExplorationEnabled() || (i2 = this.mAccessibilityFocusedVirtualViewId) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            c(i2);
        }
        this.mAccessibilityFocusedVirtualViewId = i;
        this.mHost.invalidate();
        sendEventForVirtualView(i, 32768);
        return true;
    }

    public final boolean requestKeyboardFocusForVirtualView(int i) {
        int i2;
        if ((!this.mHost.isFocused() && !this.mHost.requestFocus()) || (i2 = this.mKeyboardFocusedVirtualViewId) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i2);
        }
        this.mKeyboardFocusedVirtualViewId = i;
        onVirtualViewKeyboardFocusChanged(i, true);
        sendEventForVirtualView(i, 8);
        return true;
    }

    public final void s(int i) {
        int i2 = this.mHoveredVirtualViewId;
        if (i2 == i) {
            return;
        }
        this.mHoveredVirtualViewId = i;
        sendEventForVirtualView(i, 128);
        sendEventForVirtualView(i2, 256);
    }

    public final boolean sendEventForVirtualView(int i, int i2) {
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.mManager.isEnabled() || (parent = this.mHost.getParent()) == null) {
            return false;
        }
        return a2.h(parent, this.mHost, e(i, i2));
    }

    public final void invalidateVirtualView(int i, int i2) {
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.mManager.isEnabled() || (parent = this.mHost.getParent()) == null) {
            return;
        }
        AccessibilityEvent e = e(i, 2048);
        w.b.b(e, i2);
        a2.h(parent, this.mHost, e);
    }
}
