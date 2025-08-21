package androidx.viewpager.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import v.b0;
import v.d1;
import v.g2;
import w.g0;
/* loaded from: classes.dex */
public class ViewPager extends ViewGroup {
    private static final int CLOSE_ENOUGH = 2;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private int mActivePointerId;
    androidx.viewpager.widget.a mAdapter;
    private List<i> mAdapterChangeListeners;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private j mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsScrollStarted;
    private boolean mIsUnableToDrag;
    private final ArrayList<f> mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private EdgeEffect mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets;
    private l mObserver;
    private int mOffscreenPageLimit;
    private j mOnPageChangeListener;
    private List<j> mOnPageChangeListeners;
    private int mPageMargin;
    private k mPageTransformer;
    private int mPageTransformerLayerType;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffect mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final f mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    static final int[] LAYOUT_ATTRS = {16842931};
    private static final Comparator<f> COMPARATOR = new a();
    private static final Interpolator sInterpolator = new b();
    private static final o sPositionComparator = new o();

    /* loaded from: classes.dex */
    public static class a implements Comparator {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(f fVar, f fVar2) {
            return fVar.b - fVar2.b;
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewPager.this.setScrollState(0);
            ViewPager.this.populate();
        }
    }

    /* loaded from: classes.dex */
    public class d implements b0 {
        public final Rect a = new Rect();

        public d() {
        }

        public g2 onApplyWindowInsets(View view, g2 g2Var) {
            g2 X = d1.X(view, g2Var);
            if (X.m()) {
                return X;
            }
            Rect rect = this.a;
            rect.left = X.i();
            rect.top = X.k();
            rect.right = X.j();
            rect.bottom = X.h();
            int childCount = ViewPager.this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                g2 g = d1.g(ViewPager.this.getChildAt(i), X);
                rect.left = Math.min(g.i(), rect.left);
                rect.top = Math.min(g.k(), rect.top);
                rect.right = Math.min(g.j(), rect.right);
                rect.bottom = Math.min(g.h(), rect.bottom);
            }
            return X.n(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes.dex */
    public @interface e {
    }

    /* loaded from: classes.dex */
    public static class f {
        public Object a;
        public int b;
        public boolean c;
        public float d;
        public float e;
    }

    /* loaded from: classes.dex */
    public class h extends v.a {
        public h() {
        }

        public final boolean canScroll() {
            androidx.viewpager.widget.a aVar = ViewPager.this.mAdapter;
            if (aVar != null && aVar.getCount() > 1) {
                return true;
            }
            return ViewPager.DEBUG;
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            androidx.viewpager.widget.a aVar;
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            accessibilityEvent.setScrollable(canScroll());
            if (accessibilityEvent.getEventType() == 4096 && (aVar = ViewPager.this.mAdapter) != null) {
                accessibilityEvent.setItemCount(aVar.getCount());
                accessibilityEvent.setFromIndex(ViewPager.this.mCurItem);
                accessibilityEvent.setToIndex(ViewPager.this.mCurItem);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View view, g0 g0Var) {
            super.onInitializeAccessibilityNodeInfo(view, g0Var);
            g0Var.W(ViewPager.class.getName());
            g0Var.o0(canScroll());
            if (ViewPager.this.canScrollHorizontally(1)) {
                g0Var.a(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                g0Var.a(8192);
            }
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (super.performAccessibilityAction(view, i, bundle)) {
                return true;
            }
            if (i != 4096) {
                if (i != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                    return ViewPager.DEBUG;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.mCurItem - 1);
                return true;
            } else if (!ViewPager.this.canScrollHorizontally(1)) {
                return ViewPager.DEBUG;
            } else {
                ViewPager viewPager2 = ViewPager.this;
                viewPager2.setCurrentItem(viewPager2.mCurItem + 1);
                return true;
            }
        }
    }

    /* loaded from: classes.dex */
    public interface i {
        void onAdapterChanged(ViewPager viewPager, androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2);
    }

    /* loaded from: classes.dex */
    public interface j {
        void onPageScrollStateChanged(int i);

        void onPageScrolled(int i, float f, int i2);

        void onPageSelected(int i);
    }

    /* loaded from: classes.dex */
    public interface k {
        void transformPage(View view, float f);
    }

    /* loaded from: classes.dex */
    public class l extends DataSetObserver {
        public l() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            ViewPager.this.dataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ViewPager.this.dataSetChanged();
        }
    }

    /* loaded from: classes.dex */
    public static class m extends z.a {
        public static final Parcelable.Creator<m> CREATOR = new a();
        public int a;
        public Parcelable b;
        public ClassLoader c;

        /* loaded from: classes.dex */
        public static class a implements Parcelable.ClassLoaderCreator {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public m createFromParcel(Parcel parcel) {
                return new m(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b */
            public m createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new m(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c */
            public m[] newArray(int i) {
                return new m[i];
            }
        }

        public m(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.a + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeParcelable(this.b, i);
        }

        public m(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.c = classLoader;
        }
    }

    /* loaded from: classes.dex */
    public static class n implements j {
        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageScrolled(int i, float f, int i2) {
        }
    }

    /* loaded from: classes.dex */
    public static class o implements Comparator {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(View view, View view2) {
            g gVar = (g) view.getLayoutParams();
            g gVar2 = (g) view2.getLayoutParams();
            boolean z = gVar.a;
            if (z != gVar2.a) {
                if (z) {
                    return 1;
                }
                return -1;
            }
            return gVar.e - gVar2.e;
        }
    }

    public ViewPager(Context context) {
        super(context);
        this.mItems = new ArrayList<>();
        this.mTempItem = new f();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = DEBUG;
        this.mEndScrollRunnable = new c();
        this.mScrollState = 0;
        initViewPager();
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public static boolean k(View view) {
        if (view.getClass().getAnnotation(e.class) != null) {
            return true;
        }
        return DEBUG;
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.mScrollingCacheEnabled != z) {
            this.mScrollingCacheEnabled = z;
        }
    }

    public final void a(f fVar, int i2, f fVar2) {
        float f2;
        float f3;
        float f4;
        int i3;
        int i4;
        f fVar3;
        f fVar4;
        int count = this.mAdapter.getCount();
        int clientWidth = getClientWidth();
        if (clientWidth > 0) {
            f2 = this.mPageMargin / clientWidth;
        } else {
            f2 = 0.0f;
        }
        if (fVar2 != null) {
            int i5 = fVar2.b;
            int i6 = fVar.b;
            if (i5 < i6) {
                float f5 = fVar2.e + fVar2.d + f2;
                int i7 = i5 + 1;
                int i8 = 0;
                while (i7 <= fVar.b && i8 < this.mItems.size()) {
                    f fVar5 = this.mItems.get(i8);
                    while (true) {
                        fVar4 = fVar5;
                        if (i7 <= fVar4.b || i8 >= this.mItems.size() - 1) {
                            break;
                        }
                        i8++;
                        fVar5 = this.mItems.get(i8);
                    }
                    while (i7 < fVar4.b) {
                        f5 += this.mAdapter.getPageWidth(i7) + f2;
                        i7++;
                    }
                    fVar4.e = f5;
                    f5 += fVar4.d + f2;
                    i7++;
                }
            } else if (i5 > i6) {
                int size = this.mItems.size() - 1;
                float f6 = fVar2.e;
                while (true) {
                    i5--;
                    if (i5 < fVar.b || size < 0) {
                        break;
                    }
                    f fVar6 = this.mItems.get(size);
                    while (true) {
                        fVar3 = fVar6;
                        if (i5 >= fVar3.b || size <= 0) {
                            break;
                        }
                        size--;
                        fVar6 = this.mItems.get(size);
                    }
                    while (i5 > fVar3.b) {
                        f6 -= this.mAdapter.getPageWidth(i5) + f2;
                        i5--;
                    }
                    f6 -= fVar3.d + f2;
                    fVar3.e = f6;
                }
            }
        }
        int size2 = this.mItems.size();
        float f7 = fVar.e;
        int i9 = fVar.b;
        int i10 = i9 - 1;
        if (i9 == 0) {
            f3 = f7;
        } else {
            f3 = -3.4028235E38f;
        }
        this.mFirstOffset = f3;
        int i11 = count - 1;
        if (i9 == i11) {
            f4 = (fVar.d + f7) - 1.0f;
        } else {
            f4 = Float.MAX_VALUE;
        }
        this.mLastOffset = f4;
        int i12 = i2 - 1;
        while (i12 >= 0) {
            f fVar7 = this.mItems.get(i12);
            while (true) {
                i4 = fVar7.b;
                if (i10 <= i4) {
                    break;
                }
                f7 -= this.mAdapter.getPageWidth(i10) + f2;
                i10--;
            }
            f7 -= fVar7.d + f2;
            fVar7.e = f7;
            if (i4 == 0) {
                this.mFirstOffset = f7;
            }
            i12--;
            i10--;
        }
        float f8 = fVar.e + fVar.d + f2;
        int i13 = fVar.b + 1;
        int i14 = i2 + 1;
        while (i14 < size2) {
            f fVar8 = this.mItems.get(i14);
            while (true) {
                i3 = fVar8.b;
                if (i13 >= i3) {
                    break;
                }
                f8 += this.mAdapter.getPageWidth(i13) + f2;
                i13++;
            }
            if (i3 == i11) {
                this.mLastOffset = (fVar8.d + f8) - 1.0f;
            }
            fVar8.e = f8;
            f8 += fVar8.d + f2;
            i14++;
            i13++;
        }
        this.mNeedCalculatePageOffsets = DEBUG;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        f infoForChild;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.mCurItem) {
                    childAt.addFocusables(arrayList, i2, i3);
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if ((i3 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
            return;
        }
        arrayList.add(this);
    }

    public f addNewItem(int i2, int i3) {
        f fVar = new f();
        fVar.b = i2;
        fVar.a = this.mAdapter.instantiateItem((ViewGroup) this, i2);
        fVar.d = this.mAdapter.getPageWidth(i2);
        if (i3 >= 0 && i3 < this.mItems.size()) {
            this.mItems.add(i3, fVar);
        } else {
            this.mItems.add(fVar);
        }
        return fVar;
    }

    public void addOnAdapterChangeListener(i iVar) {
        if (this.mAdapterChangeListeners == null) {
            this.mAdapterChangeListeners = new ArrayList();
        }
        this.mAdapterChangeListeners.add(iVar);
    }

    public void addOnPageChangeListener(j jVar) {
        if (this.mOnPageChangeListeners == null) {
            this.mOnPageChangeListeners = new ArrayList();
        }
        this.mOnPageChangeListeners.add(jVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        f infoForChild;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.mCurItem) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        g gVar = (g) layoutParams;
        boolean k2 = gVar.a | k(view);
        gVar.a = k2;
        if (this.mInLayout) {
            if (!k2) {
                gVar.d = true;
                addViewInLayout(view, i2, layoutParams);
                return;
            }
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
        super.addView(view, i2, layoutParams);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean arrowScroll(int r7) {
        /*
            r6 = this;
            android.view.View r0 = r6.findFocus()
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 != r6) goto Lb
        L9:
            r0 = r3
            goto L69
        Lb:
            if (r0 == 0) goto L69
            android.view.ViewParent r4 = r0.getParent()
        L11:
            boolean r5 = r4 instanceof android.view.ViewGroup
            if (r5 == 0) goto L1e
            if (r4 != r6) goto L19
            r4 = 1
            goto L1f
        L19:
            android.view.ViewParent r4 = r4.getParent()
            goto L11
        L1e:
            r4 = 0
        L1f:
            if (r4 != 0) goto L69
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.Class r5 = r0.getClass()
            java.lang.String r5 = r5.getSimpleName()
            r4.append(r5)
            android.view.ViewParent r0 = r0.getParent()
        L35:
            boolean r5 = r0 instanceof android.view.ViewGroup
            if (r5 == 0) goto L4e
            java.lang.String r5 = " => "
            r4.append(r5)
            java.lang.Class r5 = r0.getClass()
            java.lang.String r5 = r5.getSimpleName()
            r4.append(r5)
            android.view.ViewParent r0 = r0.getParent()
            goto L35
        L4e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "arrowScroll tried to find focus based on non-child current focused view "
            r0.append(r5)
            java.lang.String r4 = r4.toString()
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.String r4 = "ViewPager"
            android.util.Log.e(r4, r0)
            goto L9
        L69:
            android.view.FocusFinder r3 = android.view.FocusFinder.getInstance()
            android.view.View r3 = r3.findNextFocus(r6, r0, r7)
            r4 = 66
            r5 = 17
            if (r3 == 0) goto Lba
            if (r3 == r0) goto Lba
            if (r7 != r5) goto L9a
            android.graphics.Rect r1 = r6.mTempRect
            android.graphics.Rect r1 = r6.i(r1, r3)
            int r1 = r1.left
            android.graphics.Rect r2 = r6.mTempRect
            android.graphics.Rect r2 = r6.i(r2, r0)
            int r2 = r2.left
            if (r0 == 0) goto L94
            if (r1 < r2) goto L94
            boolean r0 = r6.pageLeft()
            goto L98
        L94:
            boolean r0 = r3.requestFocus()
        L98:
            r2 = r0
            goto Lcd
        L9a:
            if (r7 != r4) goto Lcd
            android.graphics.Rect r1 = r6.mTempRect
            android.graphics.Rect r1 = r6.i(r1, r3)
            int r1 = r1.left
            android.graphics.Rect r2 = r6.mTempRect
            android.graphics.Rect r2 = r6.i(r2, r0)
            int r2 = r2.left
            if (r0 == 0) goto Lb5
            if (r1 > r2) goto Lb5
            boolean r0 = r6.pageRight()
            goto L98
        Lb5:
            boolean r0 = r3.requestFocus()
            goto L98
        Lba:
            if (r7 == r5) goto Lc9
            if (r7 != r1) goto Lbf
            goto Lc9
        Lbf:
            if (r7 == r4) goto Lc4
            r0 = 2
            if (r7 != r0) goto Lcd
        Lc4:
            boolean r2 = r6.pageRight()
            goto Lcd
        Lc9:
            boolean r2 = r6.pageLeft()
        Lcd:
            if (r2 == 0) goto Ld6
            int r7 = android.view.SoundEffectConstants.getContantForFocusDirection(r7)
            r6.playSoundEffect(r7)
        Ld6:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.arrowScroll(int):boolean");
    }

    public final void b(boolean z) {
        boolean z2;
        if (this.mScrollState == 2) {
            z2 = true;
        } else {
            z2 = DEBUG;
        }
        if (z2) {
            setScrollingCacheEnabled(DEBUG);
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        n(currX);
                    }
                }
            }
        }
        this.mPopulatePending = DEBUG;
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            f fVar = this.mItems.get(i2);
            if (fVar.c) {
                fVar.c = DEBUG;
                z2 = true;
            }
        }
        if (z2) {
            if (z) {
                d1.c0(this, this.mEndScrollRunnable);
            } else {
                this.mEndScrollRunnable.run();
            }
        }
    }

    public boolean beginFakeDrag() {
        if (this.mIsBeingDragged) {
            return DEBUG;
        }
        this.mFakeDragging = true;
        setScrollState(1);
        this.mLastMotionX = 0.0f;
        this.mInitialMotionX = 0.0f;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
        this.mVelocityTracker.addMovement(obtain);
        obtain.recycle();
        this.mFakeDragBeginTime = uptimeMillis;
        return true;
    }

    public final int c(int i2, float f2, int i3, int i4) {
        float f3;
        if (Math.abs(i4) > this.mFlingDistance && Math.abs(i3) > this.mMinimumVelocity) {
            if (i3 <= 0) {
                i2++;
            }
        } else {
            if (i2 >= this.mCurItem) {
                f3 = 0.4f;
            } else {
                f3 = 0.6f;
            }
            i2 += (int) (f2 + f3);
        }
        if (this.mItems.size() > 0) {
            ArrayList<f> arrayList = this.mItems;
            return Math.max(this.mItems.get(0).b, Math.min(i2, arrayList.get(arrayList.size() - 1).b));
        }
        return i2;
    }

    public boolean canScroll(View view, boolean z, int i2, int i3, int i4) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom() && canScroll(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                    return true;
                }
            }
        }
        if (z && view.canScrollHorizontally(-i2)) {
            return true;
        }
        return DEBUG;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        if (this.mAdapter == null) {
            return DEBUG;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        if (i2 < 0) {
            if (scrollX <= ((int) (clientWidth * this.mFirstOffset))) {
                return DEBUG;
            }
            return true;
        } else if (i2 <= 0 || scrollX >= ((int) (clientWidth * this.mLastOffset))) {
            return DEBUG;
        } else {
            return true;
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if ((layoutParams instanceof g) && super.checkLayoutParams(layoutParams)) {
            return true;
        }
        return DEBUG;
    }

    public void clearOnPageChangeListeners() {
        List<j> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        this.mIsScrollStarted = true;
        if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            if (scrollX != currX || scrollY != currY) {
                scrollTo(currX, currY);
                if (!n(currX)) {
                    this.mScroller.abortAnimation();
                    scrollTo(0, currY);
                }
            }
            d1.b0(this);
            return;
        }
        b(true);
    }

    public final void d(int i2, float f2, int i3) {
        j jVar = this.mOnPageChangeListener;
        if (jVar != null) {
            jVar.onPageScrolled(i2, f2, i3);
        }
        List<j> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                j jVar2 = this.mOnPageChangeListeners.get(i4);
                if (jVar2 != null) {
                    jVar2.onPageScrolled(i2, f2, i3);
                }
            }
        }
        j jVar3 = this.mInternalPageChangeListener;
        if (jVar3 != null) {
            jVar3.onPageScrolled(i2, f2, i3);
        }
    }

    public void dataSetChanged() {
        boolean z;
        int count = this.mAdapter.getCount();
        this.mExpectedAdapterCount = count;
        if (this.mItems.size() < (this.mOffscreenPageLimit * 2) + 1 && this.mItems.size() < count) {
            z = true;
        } else {
            z = DEBUG;
        }
        int i2 = this.mCurItem;
        int i3 = 0;
        boolean z2 = DEBUG;
        while (i3 < this.mItems.size()) {
            f fVar = this.mItems.get(i3);
            int itemPosition = this.mAdapter.getItemPosition(fVar.a);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.mItems.remove(i3);
                    i3--;
                    if (!z2) {
                        this.mAdapter.startUpdate((ViewGroup) this);
                        z2 = true;
                    }
                    this.mAdapter.destroyItem((ViewGroup) this, fVar.b, fVar.a);
                    int i4 = this.mCurItem;
                    if (i4 == fVar.b) {
                        i2 = Math.max(0, Math.min(i4, count - 1));
                    }
                } else {
                    int i5 = fVar.b;
                    if (i5 != itemPosition) {
                        if (i5 == this.mCurItem) {
                            i2 = itemPosition;
                        }
                        fVar.b = itemPosition;
                    }
                }
                z = true;
            }
            i3++;
        }
        if (z2) {
            this.mAdapter.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (z) {
            int childCount = getChildCount();
            for (int i6 = 0; i6 < childCount; i6++) {
                g gVar = (g) getChildAt(i6).getLayoutParams();
                if (!gVar.a) {
                    gVar.c = 0.0f;
                }
            }
            setCurrentItemInternal(i2, DEBUG, true);
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!super.dispatchKeyEvent(keyEvent) && !executeKeyEvent(keyEvent)) {
            return DEBUG;
        }
        return true;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        f infoForChild;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.mCurItem && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return DEBUG;
    }

    public float distanceInfluenceForSnapDuration(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        androidx.viewpager.widget.a aVar;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean z = DEBUG;
        if (overScrollMode != 0 && (overScrollMode != 1 || (aVar = this.mAdapter) == null || aVar.getCount() <= 1)) {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
        } else {
            if (!this.mLeftEdge.isFinished()) {
                int save = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.mFirstOffset * width);
                this.mLeftEdge.setSize(height, width);
                z = false | this.mLeftEdge.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.mRightEdge.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.mLastOffset + 1.0f)) * width2);
                this.mRightEdge.setSize(height2, width2);
                z |= this.mRightEdge.draw(canvas);
                canvas.restoreToCount(save2);
            }
        }
        if (z) {
            d1.b0(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mMarginDrawable;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    public final void e(int i2) {
        j jVar = this.mOnPageChangeListener;
        if (jVar != null) {
            jVar.onPageSelected(i2);
        }
        List<j> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar2 = this.mOnPageChangeListeners.get(i3);
                if (jVar2 != null) {
                    jVar2.onPageSelected(i2);
                }
            }
        }
        j jVar3 = this.mInternalPageChangeListener;
        if (jVar3 != null) {
            jVar3.onPageSelected(i2);
        }
    }

    public void endFakeDrag() {
        if (this.mFakeDragging) {
            if (this.mAdapter != null) {
                VelocityTracker velocityTracker = this.mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
                int xVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
                this.mPopulatePending = true;
                int clientWidth = getClientWidth();
                int scrollX = getScrollX();
                f j2 = j();
                setCurrentItemInternal(c(j2.b, ((scrollX / clientWidth) - j2.e) / j2.d, xVelocity, (int) (this.mLastMotionX - this.mInitialMotionX)), true, true, xVelocity);
            }
            h();
            this.mFakeDragging = DEBUG;
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 21) {
                if (keyCode != 22) {
                    if (keyCode == 61) {
                        if (keyEvent.hasNoModifiers()) {
                            return arrowScroll(2);
                        }
                        if (keyEvent.hasModifiers(1)) {
                            return arrowScroll(1);
                        }
                    }
                } else if (keyEvent.hasModifiers(2)) {
                    return pageRight();
                } else {
                    return arrowScroll(66);
                }
            } else if (keyEvent.hasModifiers(2)) {
                return pageLeft();
            } else {
                return arrowScroll(17);
            }
        }
        return DEBUG;
    }

    public final void f(int i2) {
        j jVar = this.mOnPageChangeListener;
        if (jVar != null) {
            jVar.onPageScrollStateChanged(i2);
        }
        List<j> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar2 = this.mOnPageChangeListeners.get(i3);
                if (jVar2 != null) {
                    jVar2.onPageScrollStateChanged(i2);
                }
            }
        }
        j jVar3 = this.mInternalPageChangeListener;
        if (jVar3 != null) {
            jVar3.onPageScrollStateChanged(i2);
        }
    }

    public void fakeDragBy(float f2) {
        ArrayList<f> arrayList;
        if (this.mFakeDragging) {
            if (this.mAdapter == null) {
                return;
            }
            this.mLastMotionX += f2;
            float scrollX = getScrollX() - f2;
            float clientWidth = getClientWidth();
            float f3 = this.mFirstOffset * clientWidth;
            float f4 = this.mLastOffset * clientWidth;
            f fVar = this.mItems.get(0);
            f fVar2 = this.mItems.get(arrayList.size() - 1);
            if (fVar.b != 0) {
                f3 = fVar.e * clientWidth;
            }
            if (fVar2.b != this.mAdapter.getCount() - 1) {
                f4 = fVar2.e * clientWidth;
            }
            if (scrollX < f3) {
                scrollX = f3;
            } else if (scrollX > f4) {
                scrollX = f4;
            }
            int i2 = (int) scrollX;
            this.mLastMotionX += scrollX - i2;
            scrollTo(i2, getScrollY());
            n(i2);
            MotionEvent obtain = MotionEvent.obtain(this.mFakeDragBeginTime, SystemClock.uptimeMillis(), 2, this.mLastMotionX, 0.0f, 0);
            this.mVelocityTracker.addMovement(obtain);
            obtain.recycle();
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public final void g(boolean z) {
        int i2;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            if (z) {
                i2 = this.mPageTransformerLayerType;
            } else {
                i2 = 0;
            }
            getChildAt(i3).setLayerType(i2, null);
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new g();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public androidx.viewpager.widget.a getAdapter() {
        return this.mAdapter;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i2, int i3) {
        if (this.mDrawingOrder == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((g) this.mDrawingOrderedChildren.get(i3).getLayoutParams()).f;
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    public final void h() {
        this.mIsBeingDragged = DEBUG;
        this.mIsUnableToDrag = DEBUG;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public final Rect i(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    public f infoForAnyChild(View view) {
        while (true) {
            ViewParent parent = view.getParent();
            if (parent != this) {
                if (parent != null && (parent instanceof View)) {
                    view = (View) parent;
                } else {
                    return null;
                }
            } else {
                return infoForChild(view);
            }
        }
    }

    public f infoForChild(View view) {
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            f fVar = this.mItems.get(i2);
            if (this.mAdapter.isViewFromObject(view, fVar.a)) {
                return fVar;
            }
        }
        return null;
    }

    public f infoForPosition(int i2) {
        for (int i3 = 0; i3 < this.mItems.size(); i3++) {
            f fVar = this.mItems.get(i3);
            if (fVar.b == i2) {
                return fVar;
            }
        }
        return null;
    }

    public void initViewPager() {
        setWillNotDraw(DEBUG);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mMinimumVelocity = (int) (400.0f * f2);
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffect(context);
        this.mRightEdge = new EdgeEffect(context);
        this.mFlingDistance = (int) (25.0f * f2);
        this.mCloseEnough = (int) (2.0f * f2);
        this.mDefaultGutterSize = (int) (f2 * 16.0f);
        d1.k0(this, new h());
        if (d1.x(this) == 0) {
            d1.v0(this, 1);
        }
        d1.y0(this, new d());
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    public final f j() {
        float f2;
        float f3;
        int i2;
        int clientWidth = getClientWidth();
        float f4 = 0.0f;
        if (clientWidth > 0) {
            f2 = getScrollX() / clientWidth;
        } else {
            f2 = 0.0f;
        }
        if (clientWidth > 0) {
            f3 = this.mPageMargin / clientWidth;
        } else {
            f3 = 0.0f;
        }
        f fVar = null;
        float f5 = 0.0f;
        int i3 = -1;
        int i4 = 0;
        boolean z = true;
        while (i4 < this.mItems.size()) {
            f fVar2 = this.mItems.get(i4);
            if (!z && fVar2.b != (i2 = i3 + 1)) {
                fVar2 = this.mTempItem;
                fVar2.e = f4 + f5 + f3;
                fVar2.b = i2;
                fVar2.d = this.mAdapter.getPageWidth(i2);
                i4--;
            }
            f fVar3 = fVar2;
            f4 = fVar3.e;
            float f6 = fVar3.d + f4 + f3;
            if (!z && f2 < f4) {
                return fVar;
            }
            if (f2 >= f6 && i4 != this.mItems.size() - 1) {
                int i5 = fVar3.b;
                float f7 = fVar3.d;
                i4++;
                z = DEBUG;
                i3 = i5;
                f5 = f7;
                fVar = fVar3;
            } else {
                return fVar3;
            }
        }
        return fVar;
    }

    public final boolean l(float f2, float f3) {
        if ((f2 < this.mGutterSize && f3 > 0.0f) || (f2 > getWidth() - this.mGutterSize && f3 < 0.0f)) {
            return true;
        }
        return DEBUG;
    }

    public final void m(MotionEvent motionEvent) {
        int i2;
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            if (actionIndex == 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            this.mLastMotionX = motionEvent.getX(i2);
            this.mActivePointerId = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public final boolean n(int i2) {
        if (this.mItems.size() == 0) {
            if (this.mFirstLayout) {
                return DEBUG;
            }
            this.mCalledSuper = DEBUG;
            onPageScrolled(0, 0.0f, 0);
            if (this.mCalledSuper) {
                return DEBUG;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        f j2 = j();
        int clientWidth = getClientWidth();
        int i3 = this.mPageMargin;
        int i4 = clientWidth + i3;
        float f2 = clientWidth;
        int i5 = j2.b;
        float f3 = ((i2 / f2) - j2.e) / (j2.d + (i3 / f2));
        this.mCalledSuper = DEBUG;
        onPageScrolled(i5, f3, (int) (i4 * f3));
        if (this.mCalledSuper) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    public final boolean o(float f2) {
        boolean z;
        boolean z2;
        float f3 = this.mLastMotionX - f2;
        this.mLastMotionX = f2;
        float scrollX = getScrollX() + f3;
        float clientWidth = getClientWidth();
        float f4 = this.mFirstOffset * clientWidth;
        float f5 = this.mLastOffset * clientWidth;
        ArrayList<f> arrayList = this.mItems;
        boolean z3 = DEBUG;
        f fVar = arrayList.get(0);
        ArrayList<f> arrayList2 = this.mItems;
        f fVar2 = arrayList2.get(arrayList2.size() - 1);
        if (fVar.b != 0) {
            f4 = fVar.e * clientWidth;
            z = DEBUG;
        } else {
            z = true;
        }
        if (fVar2.b != this.mAdapter.getCount() - 1) {
            f5 = fVar2.e * clientWidth;
            z2 = DEBUG;
        } else {
            z2 = true;
        }
        if (scrollX < f4) {
            if (z) {
                this.mLeftEdge.onPull(Math.abs(f4 - scrollX) / clientWidth);
                z3 = true;
            }
            scrollX = f4;
        } else if (scrollX > f5) {
            if (z2) {
                this.mRightEdge.onPull(Math.abs(scrollX - f5) / clientWidth);
                z3 = true;
            }
            scrollX = f5;
        }
        int i2 = (int) scrollX;
        this.mLastMotionX += scrollX - i2;
        scrollTo(i2, getScrollY());
        n(i2);
        return z3;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        Scroller scroller = this.mScroller;
        if (scroller != null && !scroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int width;
        int i2;
        float f2;
        float f3;
        super.onDraw(canvas);
        if (this.mPageMargin > 0 && this.mMarginDrawable != null && this.mItems.size() > 0 && this.mAdapter != null) {
            int scrollX = getScrollX();
            float width2 = getWidth();
            float f4 = this.mPageMargin / width2;
            int i3 = 0;
            f fVar = this.mItems.get(0);
            float f5 = fVar.e;
            int size = this.mItems.size();
            int i4 = fVar.b;
            int i5 = this.mItems.get(size - 1).b;
            while (i4 < i5) {
                while (true) {
                    i2 = fVar.b;
                    if (i4 <= i2 || i3 >= size) {
                        break;
                    }
                    i3++;
                    fVar = this.mItems.get(i3);
                }
                if (i4 == i2) {
                    float f6 = fVar.e;
                    float f7 = fVar.d;
                    f2 = (f6 + f7) * width2;
                    f5 = f6 + f7 + f4;
                } else {
                    float pageWidth = this.mAdapter.getPageWidth(i4);
                    f2 = (f5 + pageWidth) * width2;
                    f5 += pageWidth + f4;
                }
                if (this.mPageMargin + f2 > scrollX) {
                    f3 = f4;
                    this.mMarginDrawable.setBounds(Math.round(f2), this.mTopPageBounds, Math.round(this.mPageMargin + f2), this.mBottomPageBounds);
                    this.mMarginDrawable.draw(canvas);
                } else {
                    f3 = f4;
                }
                if (f2 <= scrollX + width) {
                    i4++;
                    f4 = f3;
                } else {
                    return;
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float f2;
        int action = motionEvent.getAction() & 255;
        if (action != 3 && action != 1) {
            if (action != 0) {
                if (this.mIsBeingDragged) {
                    return true;
                }
                if (this.mIsUnableToDrag) {
                    return DEBUG;
                }
            }
            if (action != 0) {
                if (action != 2) {
                    if (action == 6) {
                        m(motionEvent);
                    }
                } else {
                    int i2 = this.mActivePointerId;
                    if (i2 != -1) {
                        int findPointerIndex = motionEvent.findPointerIndex(i2);
                        float x = motionEvent.getX(findPointerIndex);
                        float f3 = x - this.mLastMotionX;
                        float abs = Math.abs(f3);
                        float y = motionEvent.getY(findPointerIndex);
                        float abs2 = Math.abs(y - this.mInitialMotionY);
                        if (f3 != 0.0f && !l(this.mLastMotionX, f3) && canScroll(this, DEBUG, (int) f3, (int) x, (int) y)) {
                            this.mLastMotionX = x;
                            this.mLastMotionY = y;
                            this.mIsUnableToDrag = true;
                            return DEBUG;
                        }
                        int i3 = this.mTouchSlop;
                        if (abs > i3 && abs * 0.5f > abs2) {
                            this.mIsBeingDragged = true;
                            r(true);
                            setScrollState(1);
                            if (f3 > 0.0f) {
                                f2 = this.mInitialMotionX + this.mTouchSlop;
                            } else {
                                f2 = this.mInitialMotionX - this.mTouchSlop;
                            }
                            this.mLastMotionX = f2;
                            this.mLastMotionY = y;
                            setScrollingCacheEnabled(true);
                        } else if (abs2 > i3) {
                            this.mIsUnableToDrag = true;
                        }
                        if (this.mIsBeingDragged && o(x)) {
                            d1.b0(this);
                        }
                    }
                }
            } else {
                float x2 = motionEvent.getX();
                this.mInitialMotionX = x2;
                this.mLastMotionX = x2;
                float y2 = motionEvent.getY();
                this.mInitialMotionY = y2;
                this.mLastMotionY = y2;
                this.mActivePointerId = motionEvent.getPointerId(0);
                this.mIsUnableToDrag = DEBUG;
                this.mIsScrollStarted = true;
                this.mScroller.computeScrollOffset();
                if (this.mScrollState == 2 && Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.mCloseEnough) {
                    this.mScroller.abortAnimation();
                    this.mPopulatePending = DEBUG;
                    populate();
                    this.mIsBeingDragged = true;
                    r(true);
                    setScrollState(1);
                } else {
                    b(DEBUG);
                    this.mIsBeingDragged = DEBUG;
                }
            }
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent);
            return this.mIsBeingDragged;
        }
        s();
        return DEBUG;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008e  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a8  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r14, int r15) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onMeasure(int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onPageScrolled(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.mDecorChildCount
            r1 = 0
            r2 = 1
            if (r0 <= 0) goto L6b
            int r0 = r12.getScrollX()
            int r3 = r12.getPaddingLeft()
            int r4 = r12.getPaddingRight()
            int r5 = r12.getWidth()
            int r6 = r12.getChildCount()
            r7 = 0
        L1b:
            if (r7 >= r6) goto L6b
            android.view.View r8 = r12.getChildAt(r7)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            androidx.viewpager.widget.ViewPager$g r9 = (androidx.viewpager.widget.ViewPager.g) r9
            boolean r10 = r9.a
            if (r10 != 0) goto L2c
            goto L68
        L2c:
            int r9 = r9.b
            r9 = r9 & 7
            if (r9 == r2) goto L4d
            r10 = 3
            if (r9 == r10) goto L47
            r10 = 5
            if (r9 == r10) goto L3a
            r9 = r3
            goto L5c
        L3a:
            int r9 = r5 - r4
            int r10 = r8.getMeasuredWidth()
            int r9 = r9 - r10
            int r10 = r8.getMeasuredWidth()
            int r4 = r4 + r10
            goto L59
        L47:
            int r9 = r8.getWidth()
            int r9 = r9 + r3
            goto L5c
        L4d:
            int r9 = r8.getMeasuredWidth()
            int r9 = r5 - r9
            int r9 = r9 / 2
            int r9 = java.lang.Math.max(r9, r3)
        L59:
            r11 = r9
            r9 = r3
            r3 = r11
        L5c:
            int r3 = r3 + r0
            int r10 = r8.getLeft()
            int r3 = r3 - r10
            if (r3 == 0) goto L67
            r8.offsetLeftAndRight(r3)
        L67:
            r3 = r9
        L68:
            int r7 = r7 + 1
            goto L1b
        L6b:
            r12.d(r13, r14, r15)
            androidx.viewpager.widget.ViewPager$k r13 = r12.mPageTransformer
            if (r13 == 0) goto L9f
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L7a:
            if (r1 >= r14) goto L9f
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            androidx.viewpager.widget.ViewPager$g r0 = (androidx.viewpager.widget.ViewPager.g) r0
            boolean r0 = r0.a
            if (r0 == 0) goto L8b
            goto L9c
        L8b:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            androidx.viewpager.widget.ViewPager$k r3 = r12.mPageTransformer
            r3.transformPage(r15, r0)
        L9c:
            int r1 = r1 + 1
            goto L7a
        L9f:
            r12.mCalledSuper = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onPageScrolled(int, float, int):void");
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        int i5;
        f infoForChild;
        int childCount = getChildCount();
        if ((i2 & 2) != 0) {
            i4 = childCount;
            i3 = 0;
            i5 = 1;
        } else {
            i3 = childCount - 1;
            i4 = -1;
            i5 = -1;
        }
        while (i3 != i4) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.mCurItem && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i3 += i5;
        }
        return DEBUG;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof m)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        m mVar = (m) parcelable;
        super.onRestoreInstanceState(mVar.getSuperState());
        androidx.viewpager.widget.a aVar = this.mAdapter;
        if (aVar != null) {
            aVar.restoreState(mVar.b, mVar.c);
            setCurrentItemInternal(mVar.a, DEBUG, true);
            return;
        }
        this.mRestoredCurItem = mVar.a;
        this.mRestoredAdapterState = mVar.b;
        this.mRestoredClassLoader = mVar.c;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [android.os.Parcelable, androidx.viewpager.widget.ViewPager$m] */
    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        ?? mVar = new m(super.onSaveInstanceState());
        mVar.a = this.mCurItem;
        androidx.viewpager.widget.a aVar = this.mAdapter;
        if (aVar != null) {
            mVar.b = aVar.saveState();
        }
        return mVar;
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            int i6 = this.mPageMargin;
            p(i2, i4, i6, i6);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        androidx.viewpager.widget.a aVar;
        float f2;
        if (this.mFakeDragging) {
            return true;
        }
        int action = motionEvent.getAction();
        boolean z = DEBUG;
        if ((action == 0 && motionEvent.getEdgeFlags() != 0) || (aVar = this.mAdapter) == null || aVar.getCount() == 0) {
            return DEBUG;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int action2 = motionEvent.getAction() & 255;
        if (action2 != 0) {
            if (action2 != 1) {
                if (action2 != 2) {
                    if (action2 != 3) {
                        if (action2 != 5) {
                            if (action2 == 6) {
                                m(motionEvent);
                                this.mLastMotionX = motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId));
                            }
                        } else {
                            int actionIndex = motionEvent.getActionIndex();
                            this.mLastMotionX = motionEvent.getX(actionIndex);
                            this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                        }
                    } else if (this.mIsBeingDragged) {
                        t(this.mCurItem, true, 0, DEBUG);
                        z = s();
                    }
                } else {
                    if (!this.mIsBeingDragged) {
                        int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                        if (findPointerIndex == -1) {
                            z = s();
                        } else {
                            float x = motionEvent.getX(findPointerIndex);
                            float abs = Math.abs(x - this.mLastMotionX);
                            float y = motionEvent.getY(findPointerIndex);
                            float abs2 = Math.abs(y - this.mLastMotionY);
                            if (abs > this.mTouchSlop && abs > abs2) {
                                this.mIsBeingDragged = true;
                                r(true);
                                float f3 = this.mInitialMotionX;
                                if (x - f3 > 0.0f) {
                                    f2 = f3 + this.mTouchSlop;
                                } else {
                                    f2 = f3 - this.mTouchSlop;
                                }
                                this.mLastMotionX = f2;
                                this.mLastMotionY = y;
                                setScrollState(1);
                                setScrollingCacheEnabled(true);
                                ViewParent parent = getParent();
                                if (parent != null) {
                                    parent.requestDisallowInterceptTouchEvent(true);
                                }
                            }
                        }
                    }
                    if (this.mIsBeingDragged) {
                        z = false | o(motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId)));
                    }
                }
            } else if (this.mIsBeingDragged) {
                VelocityTracker velocityTracker = this.mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
                int xVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
                this.mPopulatePending = true;
                int clientWidth = getClientWidth();
                int scrollX = getScrollX();
                f j2 = j();
                float f4 = clientWidth;
                setCurrentItemInternal(c(j2.b, ((scrollX / f4) - j2.e) / (j2.d + (this.mPageMargin / f4)), xVelocity, (int) (motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId)) - this.mInitialMotionX)), true, true, xVelocity);
                z = s();
            }
        } else {
            this.mScroller.abortAnimation();
            this.mPopulatePending = DEBUG;
            populate();
            float x2 = motionEvent.getX();
            this.mInitialMotionX = x2;
            this.mLastMotionX = x2;
            float y2 = motionEvent.getY();
            this.mInitialMotionY = y2;
            this.mLastMotionY = y2;
            this.mActivePointerId = motionEvent.getPointerId(0);
        }
        if (z) {
            d1.b0(this);
        }
        return true;
    }

    public final void p(int i2, int i3, int i4, int i5) {
        float f2;
        if (i3 > 0 && !this.mItems.isEmpty()) {
            if (!this.mScroller.isFinished()) {
                this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
                return;
            }
            scrollTo((int) ((getScrollX() / (((i3 - getPaddingLeft()) - getPaddingRight()) + i5)) * (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)), getScrollY());
            return;
        }
        f infoForPosition = infoForPosition(this.mCurItem);
        if (infoForPosition != null) {
            f2 = Math.min(infoForPosition.e, this.mLastOffset);
        } else {
            f2 = 0.0f;
        }
        int paddingLeft = (int) (f2 * ((i2 - getPaddingLeft()) - getPaddingRight()));
        if (paddingLeft != getScrollX()) {
            b(DEBUG);
            scrollTo(paddingLeft, getScrollY());
        }
    }

    public boolean pageLeft() {
        int i2 = this.mCurItem;
        if (i2 > 0) {
            setCurrentItem(i2 - 1, true);
            return true;
        }
        return DEBUG;
    }

    public boolean pageRight() {
        androidx.viewpager.widget.a aVar = this.mAdapter;
        if (aVar != null && this.mCurItem < aVar.getCount() - 1) {
            setCurrentItem(this.mCurItem + 1, true);
            return true;
        }
        return DEBUG;
    }

    public void populate() {
        populate(this.mCurItem);
    }

    public final void q() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((g) getChildAt(i2).getLayoutParams()).a) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    public final void r(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    public void removeOnAdapterChangeListener(i iVar) {
        List<i> list = this.mAdapterChangeListeners;
        if (list != null) {
            list.remove(iVar);
        }
    }

    public void removeOnPageChangeListener(j jVar) {
        List<j> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.remove(jVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public final boolean s() {
        this.mActivePointerId = -1;
        h();
        this.mLeftEdge.onRelease();
        this.mRightEdge.onRelease();
        if (!this.mLeftEdge.isFinished() && !this.mRightEdge.isFinished()) {
            return DEBUG;
        }
        return true;
    }

    public void setAdapter(androidx.viewpager.widget.a aVar) {
        androidx.viewpager.widget.a aVar2 = this.mAdapter;
        if (aVar2 != null) {
            aVar2.setViewPagerObserver(null);
            this.mAdapter.startUpdate((ViewGroup) this);
            for (int i2 = 0; i2 < this.mItems.size(); i2++) {
                f fVar = this.mItems.get(i2);
                this.mAdapter.destroyItem((ViewGroup) this, fVar.b, fVar.a);
            }
            this.mAdapter.finishUpdate((ViewGroup) this);
            this.mItems.clear();
            q();
            this.mCurItem = 0;
            scrollTo(0, 0);
        }
        androidx.viewpager.widget.a aVar3 = this.mAdapter;
        this.mAdapter = aVar;
        this.mExpectedAdapterCount = 0;
        if (aVar != null) {
            if (this.mObserver == null) {
                this.mObserver = new l();
            }
            this.mAdapter.setViewPagerObserver(this.mObserver);
            this.mPopulatePending = DEBUG;
            boolean z = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                setCurrentItemInternal(this.mRestoredCurItem, DEBUG, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            } else if (!z) {
                populate();
            } else {
                requestLayout();
            }
        }
        List<i> list = this.mAdapterChangeListeners;
        if (list != null && !list.isEmpty()) {
            int size = this.mAdapterChangeListeners.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.mAdapterChangeListeners.get(i3).onAdapterChanged(this, aVar3, aVar);
            }
        }
    }

    public void setCurrentItem(int i2) {
        this.mPopulatePending = DEBUG;
        setCurrentItemInternal(i2, !this.mFirstLayout, DEBUG);
    }

    public void setCurrentItemInternal(int i2, boolean z, boolean z2) {
        setCurrentItemInternal(i2, z, z2, 0);
    }

    public j setInternalPageChangeListener(j jVar) {
        j jVar2 = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = jVar;
        return jVar2;
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Requested offscreen page limit ");
            sb.append(i2);
            sb.append(" too small; defaulting to ");
            sb.append(1);
            i2 = 1;
        }
        if (i2 != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = i2;
            populate();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(j jVar) {
        this.mOnPageChangeListener = jVar;
    }

    public void setPageMargin(int i2) {
        int i3 = this.mPageMargin;
        this.mPageMargin = i2;
        int width = getWidth();
        p(width, width, i2, i3);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.mMarginDrawable = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null ? true : DEBUG);
        invalidate();
    }

    public void setPageTransformer(boolean z, k kVar) {
        setPageTransformer(z, kVar, 2);
    }

    public void setScrollState(int i2) {
        boolean z;
        if (this.mScrollState == i2) {
            return;
        }
        this.mScrollState = i2;
        if (this.mPageTransformer != null) {
            if (i2 != 0) {
                z = true;
            } else {
                z = DEBUG;
            }
            g(z);
        }
        f(i2);
    }

    public void smoothScrollTo(int i2, int i3) {
        smoothScrollTo(i2, i3, 0);
    }

    public final void t(int i2, boolean z, int i3, boolean z2) {
        int i4;
        f infoForPosition = infoForPosition(i2);
        if (infoForPosition != null) {
            i4 = (int) (getClientWidth() * Math.max(this.mFirstOffset, Math.min(infoForPosition.e, this.mLastOffset)));
        } else {
            i4 = 0;
        }
        if (z) {
            smoothScrollTo(i4, 0, i3);
            if (z2) {
                e(i2);
                return;
            }
            return;
        }
        if (z2) {
            e(i2);
        }
        b(DEBUG);
        scrollTo(i4, 0);
        n(i4);
    }

    public final void u() {
        if (this.mDrawingOrder != 0) {
            ArrayList<View> arrayList = this.mDrawingOrderedChildren;
            if (arrayList == null) {
                this.mDrawingOrderedChildren = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.mDrawingOrderedChildren.add(getChildAt(i2));
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        if (!super.verifyDrawable(drawable) && drawable != this.mMarginDrawable) {
            return DEBUG;
        }
        return true;
    }

    /* loaded from: classes.dex */
    public static class g extends ViewGroup.LayoutParams {
        public boolean a;
        public int b;
        public float c;
        public boolean d;
        public int e;
        public int f;

        public g() {
            super(-1, -1);
            this.c = 0.0f;
        }

        public g(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.c = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.LAYOUT_ATTRS);
            this.b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0060, code lost:
        if (r9 == r10) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0066, code lost:
        r8 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void populate(int r18) {
        /*
            Method dump skipped, instructions count: 616
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.populate(int):void");
    }

    public void setCurrentItemInternal(int i2, boolean z, boolean z2, int i3) {
        androidx.viewpager.widget.a aVar = this.mAdapter;
        boolean z3 = DEBUG;
        if (aVar != null && aVar.getCount() > 0) {
            if (!z2 && this.mCurItem == i2 && this.mItems.size() != 0) {
                setScrollingCacheEnabled(DEBUG);
                return;
            }
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 >= this.mAdapter.getCount()) {
                i2 = this.mAdapter.getCount() - 1;
            }
            int i4 = this.mOffscreenPageLimit;
            int i5 = this.mCurItem;
            if (i2 > i5 + i4 || i2 < i5 - i4) {
                for (int i6 = 0; i6 < this.mItems.size(); i6++) {
                    this.mItems.get(i6).c = true;
                }
            }
            if (this.mCurItem != i2) {
                z3 = true;
            }
            if (this.mFirstLayout) {
                this.mCurItem = i2;
                if (z3) {
                    e(i2);
                }
                requestLayout();
                return;
            }
            populate(i2);
            t(i2, z, i3, z3);
            return;
        }
        setScrollingCacheEnabled(DEBUG);
    }

    public void setPageTransformer(boolean z, k kVar, int i2) {
        boolean z2 = kVar != null ? true : DEBUG;
        boolean z3 = z2 != (this.mPageTransformer != null ? true : DEBUG) ? true : DEBUG;
        this.mPageTransformer = kVar;
        setChildrenDrawingOrderEnabled(z2);
        if (z2) {
            this.mDrawingOrder = z ? 2 : 1;
            this.mPageTransformerLayerType = i2;
        } else {
            this.mDrawingOrder = 0;
        }
        if (z3) {
            populate();
        }
    }

    public void smoothScrollTo(int i2, int i3, int i4) {
        int scrollX;
        int abs;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(DEBUG);
            return;
        }
        Scroller scroller = this.mScroller;
        if ((scroller == null || scroller.isFinished()) ? DEBUG : true) {
            scrollX = this.mIsScrollStarted ? this.mScroller.getCurrX() : this.mScroller.getStartX();
            this.mScroller.abortAnimation();
            setScrollingCacheEnabled(DEBUG);
        } else {
            scrollX = getScrollX();
        }
        int i5 = scrollX;
        int scrollY = getScrollY();
        int i6 = i2 - i5;
        int i7 = i3 - scrollY;
        if (i6 == 0 && i7 == 0) {
            b(DEBUG);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i8 = clientWidth / 2;
        float f2 = clientWidth;
        float f3 = i8;
        float distanceInfluenceForSnapDuration = f3 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (Math.abs(i6) * 1.0f) / f2)) * f3);
        int abs2 = Math.abs(i4);
        if (abs2 > 0) {
            abs = Math.round(Math.abs(distanceInfluenceForSnapDuration / abs2) * 1000.0f) * 4;
        } else {
            abs = (int) (((Math.abs(i6) / ((f2 * this.mAdapter.getPageWidth(this.mCurItem)) + this.mPageMargin)) + 1.0f) * 100.0f);
        }
        int min = Math.min(abs, (int) MAX_SETTLE_DURATION);
        this.mIsScrollStarted = DEBUG;
        this.mScroller.startScroll(i5, scrollY, i6, i7, min);
        d1.b0(this);
    }

    public void setCurrentItem(int i2, boolean z) {
        this.mPopulatePending = DEBUG;
        setCurrentItemInternal(i2, z, DEBUG);
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(j.a.getDrawable(getContext(), i2));
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mItems = new ArrayList<>();
        this.mTempItem = new f();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = DEBUG;
        this.mEndScrollRunnable = new c();
        this.mScrollState = 0;
        initViewPager();
    }
}
