package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;
import v.d1;
/* loaded from: classes.dex */
public class c extends n {
    public static final String[] d = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    public static final Property e = new b(PointF.class, "boundsOrigin");
    public static final Property f = new C0040c(PointF.class, "topLeft");
    public static final Property g = new d(PointF.class, "bottomRight");
    public static final Property h = new e(PointF.class, "bottomRight");
    public static final Property i = new f(PointF.class, "topLeft");
    public static final Property j = new g(PointF.class, "position");
    public static l k = new l();
    public int[] a = new int[2];
    public boolean b = false;
    public boolean c = false;

    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ ViewGroup a;
        public final /* synthetic */ BitmapDrawable b;
        public final /* synthetic */ View c;
        public final /* synthetic */ float d;

        public a(ViewGroup viewGroup, BitmapDrawable bitmapDrawable, View view, float f) {
            this.a = viewGroup;
            this.b = bitmapDrawable;
            this.c = view;
            this.d = f;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            c0.b(this.a).b(this.b);
            c0.g(this.c, this.d);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends Property {
        public Rect a;

        public b(Class cls, String str) {
            super(cls, str);
            this.a = new Rect();
        }

        @Override // android.util.Property
        /* renamed from: a */
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.a);
            Rect rect = this.a;
            return new PointF(rect.left, rect.top);
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.a);
            this.a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.a);
        }
    }

    /* renamed from: androidx.transition.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0040c extends Property {
        public C0040c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(k kVar, PointF pointF) {
            kVar.c(pointF);
        }
    }

    /* loaded from: classes.dex */
    public static class d extends Property {
        public d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(k kVar, PointF pointF) {
            kVar.a(pointF);
        }
    }

    /* loaded from: classes.dex */
    public static class e extends Property {
        public e(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(View view, PointF pointF) {
            c0.f(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
        }
    }

    /* loaded from: classes.dex */
    public static class f extends Property {
        public f(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(View view, PointF pointF) {
            c0.f(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
        }
    }

    /* loaded from: classes.dex */
    public static class g extends Property {
        public g(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(View view, PointF pointF) {
            int round = Math.round(pointF.x);
            int round2 = Math.round(pointF.y);
            c0.f(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
        }
    }

    /* loaded from: classes.dex */
    public class h extends AnimatorListenerAdapter {
        public final /* synthetic */ k a;
        private k mViewBounds;

        public h(k kVar) {
            this.a = kVar;
            this.mViewBounds = kVar;
        }
    }

    /* loaded from: classes.dex */
    public class i extends AnimatorListenerAdapter {
        public boolean a;
        public final /* synthetic */ View b;
        public final /* synthetic */ Rect c;
        public final /* synthetic */ int d;
        public final /* synthetic */ int e;
        public final /* synthetic */ int f;
        public final /* synthetic */ int g;

        public i(View view, Rect rect, int i, int i2, int i3, int i4) {
            this.b = view;
            this.c = rect;
            this.d = i;
            this.e = i2;
            this.f = i3;
            this.g = i4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.a) {
                d1.r0(this.b, this.c);
                c0.f(this.b, this.d, this.e, this.f, this.g);
            }
        }
    }

    /* loaded from: classes.dex */
    public class j extends o {
        public boolean a = false;
        public final /* synthetic */ ViewGroup b;

        public j(ViewGroup viewGroup) {
            this.b = viewGroup;
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void b(n nVar) {
            z.c(this.b, false);
        }

        @Override // androidx.transition.n.g
        public void c(n nVar) {
            if (!this.a) {
                z.c(this.b, false);
            }
            nVar.removeListener(this);
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void d(n nVar) {
            z.c(this.b, false);
            this.a = true;
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void e(n nVar) {
            z.c(this.b, true);
        }
    }

    /* loaded from: classes.dex */
    public static class k {
        public int a;
        public int b;
        public int c;
        public int d;
        public View e;
        public int f;
        public int g;

        public k(View view) {
            this.e = view;
        }

        public void a(PointF pointF) {
            this.c = Math.round(pointF.x);
            this.d = Math.round(pointF.y);
            int i = this.g + 1;
            this.g = i;
            if (this.f == i) {
                b();
            }
        }

        public final void b() {
            c0.f(this.e, this.a, this.b, this.c, this.d);
            this.f = 0;
            this.g = 0;
        }

        public void c(PointF pointF) {
            this.a = Math.round(pointF.x);
            this.b = Math.round(pointF.y);
            int i = this.f + 1;
            this.f = i;
            if (i == this.g) {
                b();
            }
        }
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
        View view = uVar.b;
        if (d1.Q(view) || view.getWidth() != 0 || view.getHeight() != 0) {
            uVar.a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            uVar.a.put("android:changeBounds:parent", uVar.b.getParent());
            if (this.c) {
                uVar.b.getLocationInWindow(this.a);
                uVar.a.put("android:changeBounds:windowX", Integer.valueOf(this.a[0]));
                uVar.a.put("android:changeBounds:windowY", Integer.valueOf(this.a[1]));
            }
            if (this.b) {
                uVar.a.put("android:changeBounds:clip", d1.s(view));
            }
        }
    }

    @Override // androidx.transition.n
    public Animator createAnimator(ViewGroup viewGroup, u uVar, u uVar2) {
        int i2;
        View view;
        ObjectAnimator a2;
        int i3;
        Rect rect;
        Rect rect2;
        ObjectAnimator objectAnimator;
        Animator c;
        if (uVar != null && uVar2 != null) {
            Map map = uVar.a;
            Map map2 = uVar2.a;
            ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
            ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
            if (viewGroup2 != null && viewGroup3 != null) {
                View view2 = uVar2.b;
                if (r(viewGroup2, viewGroup3)) {
                    Rect rect3 = (Rect) uVar.a.get("android:changeBounds:bounds");
                    Rect rect4 = (Rect) uVar2.a.get("android:changeBounds:bounds");
                    int i4 = rect3.left;
                    int i5 = rect4.left;
                    int i6 = rect3.top;
                    int i7 = rect4.top;
                    int i8 = rect3.right;
                    int i9 = rect4.right;
                    int i10 = rect3.bottom;
                    int i11 = rect4.bottom;
                    int i12 = i8 - i4;
                    int i13 = i10 - i6;
                    int i14 = i9 - i5;
                    int i15 = i11 - i7;
                    Rect rect5 = (Rect) uVar.a.get("android:changeBounds:clip");
                    Rect rect6 = (Rect) uVar2.a.get("android:changeBounds:clip");
                    if ((i12 != 0 && i13 != 0) || (i14 != 0 && i15 != 0)) {
                        if (i4 == i5 && i6 == i7) {
                            i2 = 0;
                        } else {
                            i2 = 1;
                        }
                        if (i8 != i9 || i10 != i11) {
                            i2++;
                        }
                    } else {
                        i2 = 0;
                    }
                    if ((rect5 != null && !rect5.equals(rect6)) || (rect5 == null && rect6 != null)) {
                        i2++;
                    }
                    if (i2 > 0) {
                        if (!this.b) {
                            view = view2;
                            c0.f(view, i4, i6, i8, i10);
                            if (i2 == 2) {
                                if (i12 == i14 && i13 == i15) {
                                    c = androidx.transition.g.a(view, j, getPathMotion().a(i4, i6, i5, i7));
                                } else {
                                    k kVar = new k(view);
                                    ObjectAnimator a3 = androidx.transition.g.a(kVar, f, getPathMotion().a(i4, i6, i5, i7));
                                    ObjectAnimator a4 = androidx.transition.g.a(kVar, g, getPathMotion().a(i8, i10, i9, i11));
                                    AnimatorSet animatorSet = new AnimatorSet();
                                    animatorSet.playTogether(a3, a4);
                                    animatorSet.addListener(new h(kVar));
                                    c = animatorSet;
                                }
                            } else if (i4 == i5 && i6 == i7) {
                                c = androidx.transition.g.a(view, h, getPathMotion().a(i8, i10, i9, i11));
                            } else {
                                c = androidx.transition.g.a(view, i, getPathMotion().a(i4, i6, i5, i7));
                            }
                        } else {
                            view = view2;
                            c0.f(view, i4, i6, Math.max(i12, i14) + i4, Math.max(i13, i15) + i6);
                            if (i4 == i5 && i6 == i7) {
                                a2 = null;
                            } else {
                                a2 = androidx.transition.g.a(view, j, getPathMotion().a(i4, i6, i5, i7));
                            }
                            if (rect5 == null) {
                                i3 = 0;
                                rect = new Rect(0, 0, i12, i13);
                            } else {
                                i3 = 0;
                                rect = rect5;
                            }
                            if (rect6 == null) {
                                rect2 = new Rect(i3, i3, i14, i15);
                            } else {
                                rect2 = rect6;
                            }
                            if (!rect.equals(rect2)) {
                                d1.r0(view, rect);
                                l lVar = k;
                                Object[] objArr = new Object[2];
                                objArr[i3] = rect;
                                objArr[1] = rect2;
                                ObjectAnimator ofObject = ObjectAnimator.ofObject(view, "clipBounds", lVar, objArr);
                                ofObject.addListener(new i(view, rect6, i5, i7, i9, i11));
                                objectAnimator = ofObject;
                            } else {
                                objectAnimator = null;
                            }
                            c = t.c(a2, objectAnimator);
                        }
                        if (view.getParent() instanceof ViewGroup) {
                            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
                            z.c(viewGroup4, true);
                            addListener(new j(viewGroup4));
                        }
                        return c;
                    }
                    return null;
                }
                int intValue = ((Integer) uVar.a.get("android:changeBounds:windowX")).intValue();
                int intValue2 = ((Integer) uVar.a.get("android:changeBounds:windowY")).intValue();
                int intValue3 = ((Integer) uVar2.a.get("android:changeBounds:windowX")).intValue();
                int intValue4 = ((Integer) uVar2.a.get("android:changeBounds:windowY")).intValue();
                if (intValue == intValue3 && intValue2 == intValue4) {
                    return null;
                }
                viewGroup.getLocationInWindow(this.a);
                Bitmap createBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
                view2.draw(new Canvas(createBitmap));
                BitmapDrawable bitmapDrawable = new BitmapDrawable(createBitmap);
                float c2 = c0.c(view2);
                c0.g(view2, 0.0f);
                c0.b(viewGroup).a(bitmapDrawable);
                androidx.transition.h pathMotion = getPathMotion();
                int[] iArr = this.a;
                int i16 = iArr[0];
                int i17 = iArr[1];
                ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, androidx.transition.k.a(e, pathMotion.a(intValue - i16, intValue2 - i17, intValue3 - i16, intValue4 - i17)));
                ofPropertyValuesHolder.addListener(new a(viewGroup, bitmapDrawable, view2, c2));
                return ofPropertyValuesHolder;
            }
            return null;
        }
        return null;
    }

    @Override // androidx.transition.n
    public String[] getTransitionProperties() {
        return d;
    }

    public final boolean r(View view, View view2) {
        if (!this.c) {
            return true;
        }
        u matchedTransitionValues = getMatchedTransitionValues(view, true);
        if (matchedTransitionValues == null) {
            if (view == view2) {
                return true;
            }
        } else if (view2 == matchedTransitionValues.b) {
            return true;
        }
        return false;
    }
}
