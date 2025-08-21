package androidx.fragment.app;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.c;
import androidx.savedstate.SavedStateRegistry;
import i.j1;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public abstract class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, androidx.lifecycle.f, androidx.lifecycle.t, androidx.savedstate.b {
    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int RESUMED = 4;
    static final int STARTED = 3;
    static final Object USE_DEFAULT_TRANSITION = new Object();
    boolean mAdded;
    d mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    private boolean mCalled;
    ViewGroup mContainer;
    int mContainerId;
    private int mContentLayoutId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    j mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    h mHost;
    boolean mInLayout;
    View mInnerView;
    boolean mIsCreated;
    boolean mIsNewlyAdded;
    LayoutInflater mLayoutInflater;
    androidx.lifecycle.g mLifecycleRegistry;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    float mPostponedAlpha;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetainInstanceChangedWhileDetached;
    Bundle mSavedFragmentState;
    androidx.savedstate.a mSavedStateRegistryController;
    Boolean mSavedUserVisibleHint;
    SparseArray<Parcelable> mSavedViewState;
    String mTag;
    Fragment mTarget;
    int mTargetRequestCode;
    View mView;
    y mViewLifecycleOwner;
    int mState = 0;
    String mWho = UUID.randomUUID().toString();
    String mTargetWho = null;
    private Boolean mIsPrimaryNavigationFragment = null;
    j mChildFragmentManager = new j();
    boolean mMenuVisible = true;
    boolean mUserVisibleHint = true;
    Runnable mPostponedDurationRunnable = new a();
    c.b mMaxState = c.b.RESUMED;
    androidx.lifecycle.k mViewLifecycleOwnerLiveData = new androidx.lifecycle.k();

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.startPostponedEnterTransition();
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.callStartTransitionListener();
        }
    }

    /* loaded from: classes.dex */
    public class c extends androidx.fragment.app.e {
        public c() {
        }

        @Override // androidx.fragment.app.e
        public View b(int i) {
            View view = Fragment.this.mView;
            if (view != null) {
                return view.findViewById(i);
            }
            throw new IllegalStateException("Fragment " + this + " does not have a view");
        }

        @Override // androidx.fragment.app.e
        public boolean c() {
            if (Fragment.this.mView != null) {
                return true;
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class d {
        public View a;
        public Animator b;
        public int c;
        public int d;
        public int e;
        public int f;
        public Object g = null;
        public Object h;
        public Object i;
        public Object j;
        public Object k;
        public Object l;
        public Boolean m;
        public Boolean n;
        public boolean o;
        public f p;
        public boolean q;

        public d() {
            Object obj = Fragment.USE_DEFAULT_TRANSITION;
            this.h = obj;
            this.i = null;
            this.j = obj;
            this.k = null;
            this.l = obj;
        }
    }

    /* loaded from: classes.dex */
    public static class e extends RuntimeException {
        public e(String str, Exception exc) {
            super(str, exc);
        }
    }

    /* loaded from: classes.dex */
    public interface f {
        void a();

        void b();
    }

    public Fragment() {
        D2();
    }

    @Deprecated
    public static Fragment instantiate(Context context, String str) {
        return instantiate(context, str, null);
    }

    public final d C2() {
        if (this.mAnimationInfo == null) {
            this.mAnimationInfo = new d();
        }
        return this.mAnimationInfo;
    }

    public final void D2() {
        this.mLifecycleRegistry = new androidx.lifecycle.g(this);
        this.mSavedStateRegistryController = androidx.savedstate.a.a(this);
        this.mLifecycleRegistry.a(new androidx.lifecycle.d() { // from class: androidx.fragment.app.Fragment.2
            @Override // androidx.lifecycle.d
            public void a(androidx.lifecycle.f fVar, c.a aVar) {
                View view;
                if (aVar == c.a.ON_STOP && (view = Fragment.this.mView) != null) {
                    view.cancelPendingInputEvents();
                }
            }
        });
    }

    public void callStartTransitionListener() {
        d dVar = this.mAnimationInfo;
        f fVar = null;
        if (dVar != null) {
            dVar.o = false;
            f fVar2 = dVar.p;
            dVar.p = null;
            fVar = fVar2;
        }
        if (fVar != null) {
            fVar.b();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.mFragmentId));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.mContainerId));
        printWriter.print(" mTag=");
        printWriter.println(this.mTag);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.mState);
        printWriter.print(" mWho=");
        printWriter.print(this.mWho);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.mBackStackNesting);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoving=");
        printWriter.print(this.mRemoving);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.mFromLayout);
        printWriter.print(" mInLayout=");
        printWriter.println(this.mInLayout);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.mHidden);
        printWriter.print(" mDetached=");
        printWriter.print(this.mDetached);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.mMenuVisible);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.mHasMenu);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.mRetainInstance);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.mSavedViewState);
        }
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(targetFragment);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.mTargetRequestCode);
        }
        if (getNextAnim() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(getNextAnim());
        }
        if (this.mContainer != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.mContainer);
        }
        if (this.mView != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.mView);
        }
        if (this.mInnerView != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(getAnimatingAway());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(getStateAfterAnimating());
        }
        if (getContext() != null) {
            d0.a.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.mChildFragmentManager + ":");
        j jVar = this.mChildFragmentManager;
        jVar.b(str + "  ", fileDescriptor, printWriter, strArr);
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Fragment findFragmentByWho(String str) {
        if (str.equals(this.mWho)) {
            return this;
        }
        return this.mChildFragmentManager.t0(str);
    }

    public final androidx.fragment.app.d getActivity() {
        h hVar = this.mHost;
        if (hVar == null) {
            return null;
        }
        return (androidx.fragment.app.d) hVar.d();
    }

    public boolean getAllowEnterTransitionOverlap() {
        Boolean bool;
        d dVar = this.mAnimationInfo;
        if (dVar != null && (bool = dVar.n) != null) {
            return bool.booleanValue();
        }
        return true;
    }

    public boolean getAllowReturnTransitionOverlap() {
        Boolean bool;
        d dVar = this.mAnimationInfo;
        if (dVar != null && (bool = dVar.m) != null) {
            return bool.booleanValue();
        }
        return true;
    }

    public View getAnimatingAway() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return null;
        }
        return dVar.a;
    }

    public Animator getAnimator() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return null;
        }
        return dVar.b;
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final i getChildFragmentManager() {
        if (this.mHost != null) {
            return this.mChildFragmentManager;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public Context getContext() {
        h hVar = this.mHost;
        if (hVar == null) {
            return null;
        }
        return hVar.e();
    }

    public Object getEnterTransition() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return null;
        }
        return dVar.g;
    }

    public j1 getEnterTransitionCallback() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return null;
        }
        dVar.getClass();
        return null;
    }

    public Object getExitTransition() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return null;
        }
        return dVar.i;
    }

    public j1 getExitTransitionCallback() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return null;
        }
        dVar.getClass();
        return null;
    }

    public final i getFragmentManager() {
        return this.mFragmentManager;
    }

    public final Object getHost() {
        h hVar = this.mHost;
        if (hVar == null) {
            return null;
        }
        return hVar.i();
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        LayoutInflater layoutInflater = this.mLayoutInflater;
        return layoutInflater == null ? performGetLayoutInflater(null) : layoutInflater;
    }

    @Override // androidx.lifecycle.f
    public androidx.lifecycle.c getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Deprecated
    public d0.a getLoaderManager() {
        return d0.a.b(this);
    }

    public int getNextAnim() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return 0;
        }
        return dVar.d;
    }

    public int getNextTransition() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return 0;
        }
        return dVar.e;
    }

    public int getNextTransitionStyle() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return 0;
        }
        return dVar.f;
    }

    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }

    public Object getReenterTransition() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.j;
        if (obj == USE_DEFAULT_TRANSITION) {
            return getExitTransition();
        }
        return obj;
    }

    public final Resources getResources() {
        return requireContext().getResources();
    }

    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }

    public Object getReturnTransition() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.h;
        if (obj == USE_DEFAULT_TRANSITION) {
            return getEnterTransition();
        }
        return obj;
    }

    @Override // androidx.savedstate.b
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.b();
    }

    public Object getSharedElementEnterTransition() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return null;
        }
        return dVar.k;
    }

    public Object getSharedElementReturnTransition() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.l;
        if (obj == USE_DEFAULT_TRANSITION) {
            return getSharedElementEnterTransition();
        }
        return obj;
    }

    public int getStateAfterAnimating() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return 0;
        }
        return dVar.c;
    }

    public final String getString(int i) {
        return getResources().getString(i);
    }

    public final String getTag() {
        return this.mTag;
    }

    public final Fragment getTargetFragment() {
        String str;
        Fragment fragment = this.mTarget;
        if (fragment != null) {
            return fragment;
        }
        j jVar = this.mFragmentManager;
        if (jVar != null && (str = this.mTargetWho) != null) {
            return (Fragment) jVar.g.get(str);
        }
        return null;
    }

    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }

    public final CharSequence getText(int i) {
        return getResources().getText(i);
    }

    @Deprecated
    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    public View getView() {
        return this.mView;
    }

    public androidx.lifecycle.f getViewLifecycleOwner() {
        y yVar = this.mViewLifecycleOwner;
        if (yVar != null) {
            return yVar;
        }
        throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
    }

    public LiveData getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    @Override // androidx.lifecycle.t
    public androidx.lifecycle.s getViewModelStore() {
        j jVar = this.mFragmentManager;
        if (jVar != null) {
            return jVar.C0(this);
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public void initState() {
        D2();
        this.mWho = UUID.randomUUID().toString();
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = new j();
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
    }

    public final boolean isAdded() {
        if (this.mHost != null && this.mAdded) {
            return true;
        }
        return false;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    public boolean isHideReplaced() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return false;
        }
        return dVar.q;
    }

    public final boolean isInBackStack() {
        if (this.mBackStackNesting > 0) {
            return true;
        }
        return false;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    public final boolean isMenuVisible() {
        return this.mMenuVisible;
    }

    public boolean isPostponed() {
        d dVar = this.mAnimationInfo;
        if (dVar == null) {
            return false;
        }
        return dVar.o;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isResumed() {
        if (this.mState >= 4) {
            return true;
        }
        return false;
    }

    public final boolean isStateSaved() {
        j jVar = this.mFragmentManager;
        if (jVar == null) {
            return false;
        }
        return jVar.J0();
    }

    public final boolean isVisible() {
        View view;
        if (isAdded() && !isHidden() && (view = this.mView) != null && view.getWindowToken() != null && this.mView.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    public void noteStateNotSaved() {
        this.mChildFragmentManager.U0();
    }

    public void onActivityCreated(Bundle bundle) {
        this.mCalled = true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onAttach(Context context) {
        this.mCalled = true;
        h hVar = this.mHost;
        Activity d2 = hVar == null ? null : hVar.d();
        if (d2 != null) {
            this.mCalled = false;
            onAttach(d2);
        }
    }

    public void onAttachFragment(Fragment fragment) {
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        this.mCalled = true;
        restoreChildFragmentState(bundle);
        if (!this.mChildFragmentManager.I0(1)) {
            this.mChildFragmentManager.D();
        }
    }

    public Animation onCreateAnimation(int i, boolean z, int i2) {
        return null;
    }

    public Animator onCreateAnimator(int i, boolean z, int i2) {
        return null;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        requireActivity().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i = this.mContentLayoutId;
        if (i != 0) {
            return layoutInflater.inflate(i, viewGroup, false);
        }
        return null;
    }

    public void onDestroy() {
        this.mCalled = true;
    }

    public void onDestroyOptionsMenu() {
    }

    public void onDestroyView() {
        this.mCalled = true;
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        return getLayoutInflater(bundle);
    }

    public void onHiddenChanged(boolean z) {
    }

    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
        h hVar = this.mHost;
        Activity d2 = hVar == null ? null : hVar.d();
        if (d2 != null) {
            this.mCalled = false;
            onInflate(d2, attributeSet, bundle);
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean z) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu) {
    }

    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean z) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
    }

    public void onPrimaryNavigationFragmentChanged(boolean z) {
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onStart() {
        this.mCalled = true;
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(View view, Bundle bundle) {
    }

    public void onViewStateRestored(Bundle bundle) {
        this.mCalled = true;
    }

    public void performActivityCreated(Bundle bundle) {
        this.mChildFragmentManager.U0();
        this.mState = 2;
        this.mCalled = false;
        onActivityCreated(bundle);
        if (this.mCalled) {
            this.mChildFragmentManager.A();
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onActivityCreated()");
    }

    public void performAttach() {
        this.mChildFragmentManager.r(this.mHost, new c(), this);
        this.mCalled = false;
        onAttach(this.mHost.e());
        if (this.mCalled) {
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onAttach()");
    }

    public void performConfigurationChanged(Configuration configuration) {
        onConfigurationChanged(configuration);
        this.mChildFragmentManager.B(configuration);
    }

    public boolean performContextItemSelected(MenuItem menuItem) {
        if (!this.mHidden) {
            if (onContextItemSelected(menuItem) || this.mChildFragmentManager.C(menuItem)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void performCreate(Bundle bundle) {
        this.mChildFragmentManager.U0();
        this.mState = 1;
        this.mCalled = false;
        this.mSavedStateRegistryController.c(bundle);
        onCreate(bundle);
        this.mIsCreated = true;
        if (this.mCalled) {
            this.mLifecycleRegistry.i(c.a.ON_CREATE);
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onCreate()");
    }

    public boolean performCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onCreateOptionsMenu(menu, menuInflater);
            z = true;
        }
        return z | this.mChildFragmentManager.E(menu, menuInflater);
    }

    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mChildFragmentManager.U0();
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new y();
        View onCreateView = onCreateView(layoutInflater, viewGroup, bundle);
        this.mView = onCreateView;
        if (onCreateView != null) {
            this.mViewLifecycleOwner.b();
            this.mViewLifecycleOwnerLiveData.l(this.mViewLifecycleOwner);
        } else if (!this.mViewLifecycleOwner.c()) {
            this.mViewLifecycleOwner = null;
        } else {
            throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
        }
    }

    public void performDestroy() {
        this.mChildFragmentManager.F();
        this.mLifecycleRegistry.i(c.a.ON_DESTROY);
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (this.mCalled) {
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onDestroy()");
    }

    public void performDestroyView() {
        this.mChildFragmentManager.G();
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(c.a.ON_DESTROY);
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (this.mCalled) {
            d0.a.b(this).d();
            this.mPerformedCreateView = false;
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onDestroyView()");
    }

    public void performDetach() {
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (this.mCalled) {
            if (!this.mChildFragmentManager.F0()) {
                this.mChildFragmentManager.F();
                this.mChildFragmentManager = new j();
                return;
            }
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onDetach()");
    }

    public LayoutInflater performGetLayoutInflater(Bundle bundle) {
        LayoutInflater onGetLayoutInflater = onGetLayoutInflater(bundle);
        this.mLayoutInflater = onGetLayoutInflater;
        return onGetLayoutInflater;
    }

    public void performLowMemory() {
        onLowMemory();
        this.mChildFragmentManager.H();
    }

    public void performMultiWindowModeChanged(boolean z) {
        onMultiWindowModeChanged(z);
        this.mChildFragmentManager.I(z);
    }

    public boolean performOptionsItemSelected(MenuItem menuItem) {
        if (!this.mHidden) {
            if ((this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(menuItem)) || this.mChildFragmentManager.X(menuItem)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void performOptionsMenuClosed(Menu menu) {
        if (!this.mHidden) {
            if (this.mHasMenu && this.mMenuVisible) {
                onOptionsMenuClosed(menu);
            }
            this.mChildFragmentManager.Y(menu);
        }
    }

    public void performPause() {
        this.mChildFragmentManager.a0();
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(c.a.ON_PAUSE);
        }
        this.mLifecycleRegistry.i(c.a.ON_PAUSE);
        this.mState = 3;
        this.mCalled = false;
        onPause();
        if (this.mCalled) {
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onPause()");
    }

    public void performPictureInPictureModeChanged(boolean z) {
        onPictureInPictureModeChanged(z);
        this.mChildFragmentManager.b0(z);
    }

    public boolean performPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onPrepareOptionsMenu(menu);
            z = true;
        }
        return z | this.mChildFragmentManager.c0(menu);
    }

    public void performPrimaryNavigationFragmentChanged() {
        boolean H0 = this.mFragmentManager.H0(this);
        Boolean bool = this.mIsPrimaryNavigationFragment;
        if (bool == null || bool.booleanValue() != H0) {
            this.mIsPrimaryNavigationFragment = Boolean.valueOf(H0);
            onPrimaryNavigationFragmentChanged(H0);
            this.mChildFragmentManager.d0();
        }
    }

    public void performResume() {
        this.mChildFragmentManager.U0();
        this.mChildFragmentManager.n0();
        this.mState = 4;
        this.mCalled = false;
        onResume();
        if (this.mCalled) {
            androidx.lifecycle.g gVar = this.mLifecycleRegistry;
            c.a aVar = c.a.ON_RESUME;
            gVar.i(aVar);
            if (this.mView != null) {
                this.mViewLifecycleOwner.a(aVar);
            }
            this.mChildFragmentManager.e0();
            this.mChildFragmentManager.n0();
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onResume()");
    }

    public void performSaveInstanceState(Bundle bundle) {
        onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.d(bundle);
        Parcelable f1 = this.mChildFragmentManager.f1();
        if (f1 != null) {
            bundle.putParcelable("android:support:fragments", f1);
        }
    }

    public void performStart() {
        this.mChildFragmentManager.U0();
        this.mChildFragmentManager.n0();
        this.mState = 3;
        this.mCalled = false;
        onStart();
        if (this.mCalled) {
            androidx.lifecycle.g gVar = this.mLifecycleRegistry;
            c.a aVar = c.a.ON_START;
            gVar.i(aVar);
            if (this.mView != null) {
                this.mViewLifecycleOwner.a(aVar);
            }
            this.mChildFragmentManager.f0();
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onStart()");
    }

    public void performStop() {
        this.mChildFragmentManager.h0();
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(c.a.ON_STOP);
        }
        this.mLifecycleRegistry.i(c.a.ON_STOP);
        this.mState = 2;
        this.mCalled = false;
        onStop();
        if (this.mCalled) {
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onStop()");
    }

    public void postponeEnterTransition() {
        C2().o = true;
    }

    public void registerForContextMenu(View view) {
        view.setOnCreateContextMenuListener(this);
    }

    public final void requestPermissions(String[] strArr, int i) {
        h hVar = this.mHost;
        if (hVar != null) {
            hVar.m(this, strArr, i);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public final androidx.fragment.app.d requireActivity() {
        androidx.fragment.app.d activity = getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    public final Bundle requireArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments;
        }
        throw new IllegalStateException("Fragment " + this + " does not have any arguments.");
    }

    public final Context requireContext() {
        Context context = getContext();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    public final i requireFragmentManager() {
        i fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            return fragmentManager;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    public final Object requireHost() {
        Object host = getHost();
        if (host != null) {
            return host;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a host.");
    }

    public final Fragment requireParentFragment() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment == null) {
            if (getContext() == null) {
                throw new IllegalStateException("Fragment " + this + " is not attached to any Fragment or host");
            }
            throw new IllegalStateException("Fragment " + this + " is not a child Fragment, it is directly attached to " + getContext());
        }
        return parentFragment;
    }

    public final View requireView() {
        View view = getView();
        if (view != null) {
            return view;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    public void restoreChildFragmentState(Bundle bundle) {
        Parcelable parcelable;
        if (bundle != null && (parcelable = bundle.getParcelable("android:support:fragments")) != null) {
            this.mChildFragmentManager.d1(parcelable);
            this.mChildFragmentManager.D();
        }
    }

    public final void restoreViewState(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.mSavedViewState;
        if (sparseArray != null) {
            this.mInnerView.restoreHierarchyState(sparseArray);
            this.mSavedViewState = null;
        }
        this.mCalled = false;
        onViewStateRestored(bundle);
        if (this.mCalled) {
            if (this.mView != null) {
                this.mViewLifecycleOwner.a(c.a.ON_CREATE);
                return;
            }
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onViewStateRestored()");
    }

    public void setAllowEnterTransitionOverlap(boolean z) {
        C2().n = Boolean.valueOf(z);
    }

    public void setAllowReturnTransitionOverlap(boolean z) {
        C2().m = Boolean.valueOf(z);
    }

    public void setAnimatingAway(View view) {
        C2().a = view;
    }

    public void setAnimator(Animator animator) {
        C2().b = animator;
    }

    public void setArguments(Bundle bundle) {
        if (this.mFragmentManager != null && isStateSaved()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.mArguments = bundle;
    }

    public void setEnterSharedElementCallback(j1 j1Var) {
        C2().getClass();
    }

    public void setEnterTransition(Object obj) {
        C2().g = obj;
    }

    public void setExitSharedElementCallback(j1 j1Var) {
        C2().getClass();
    }

    public void setExitTransition(Object obj) {
        C2().i = obj;
    }

    public void setHasOptionsMenu(boolean z) {
        if (this.mHasMenu != z) {
            this.mHasMenu = z;
            if (isAdded() && !isHidden()) {
                this.mHost.r();
            }
        }
    }

    public void setHideReplaced(boolean z) {
        C2().q = z;
    }

    public void setInitialSavedState(g gVar) {
        if (this.mFragmentManager == null) {
            this.mSavedFragmentState = (gVar == null || (r2 = gVar.a) == null) ? null : null;
            return;
        }
        throw new IllegalStateException("Fragment already added");
    }

    public void setMenuVisibility(boolean z) {
        if (this.mMenuVisible != z) {
            this.mMenuVisible = z;
            if (this.mHasMenu && isAdded() && !isHidden()) {
                this.mHost.r();
            }
        }
    }

    public void setNextAnim(int i) {
        if (this.mAnimationInfo == null && i == 0) {
            return;
        }
        C2().d = i;
    }

    public void setNextTransition(int i, int i2) {
        if (this.mAnimationInfo == null && i == 0 && i2 == 0) {
            return;
        }
        C2();
        d dVar = this.mAnimationInfo;
        dVar.e = i;
        dVar.f = i2;
    }

    public void setOnStartEnterTransitionListener(f fVar) {
        C2();
        d dVar = this.mAnimationInfo;
        f fVar2 = dVar.p;
        if (fVar == fVar2) {
            return;
        }
        if (fVar != null && fVar2 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        if (dVar.o) {
            dVar.p = fVar;
        }
        if (fVar != null) {
            fVar.a();
        }
    }

    public void setReenterTransition(Object obj) {
        C2().j = obj;
    }

    public void setRetainInstance(boolean z) {
        this.mRetainInstance = z;
        j jVar = this.mFragmentManager;
        if (jVar != null) {
            if (z) {
                jVar.o(this);
                return;
            } else {
                jVar.b1(this);
                return;
            }
        }
        this.mRetainInstanceChangedWhileDetached = true;
    }

    public void setReturnTransition(Object obj) {
        C2().h = obj;
    }

    public void setSharedElementEnterTransition(Object obj) {
        C2().k = obj;
    }

    public void setSharedElementReturnTransition(Object obj) {
        C2().l = obj;
    }

    public void setStateAfterAnimating(int i) {
        C2().c = i;
    }

    public void setTargetFragment(Fragment fragment, int i) {
        i iVar;
        i fragmentManager = getFragmentManager();
        if (fragment != null) {
            iVar = fragment.getFragmentManager();
        } else {
            iVar = null;
        }
        if (fragmentManager != null && iVar != null && fragmentManager != iVar) {
            throw new IllegalArgumentException("Fragment " + fragment + " must share the same FragmentManager to be set as a target fragment");
        }
        for (Fragment fragment2 = fragment; fragment2 != null; fragment2 = fragment2.getTargetFragment()) {
            if (fragment2 == this) {
                throw new IllegalArgumentException("Setting " + fragment + " as the target of " + this + " would create a target cycle");
            }
        }
        if (fragment == null) {
            this.mTargetWho = null;
            this.mTarget = null;
        } else if (this.mFragmentManager != null && fragment.mFragmentManager != null) {
            this.mTargetWho = fragment.mWho;
            this.mTarget = null;
        } else {
            this.mTargetWho = null;
            this.mTarget = fragment;
        }
        this.mTargetRequestCode = i;
    }

    @Deprecated
    public void setUserVisibleHint(boolean z) {
        boolean z2;
        if (!this.mUserVisibleHint && z && this.mState < 3 && this.mFragmentManager != null && isAdded() && this.mIsCreated) {
            this.mFragmentManager.V0(this);
        }
        this.mUserVisibleHint = z;
        if (this.mState < 3 && !z) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.mDeferStart = z2;
        if (this.mSavedFragmentState != null) {
            this.mSavedUserVisibleHint = Boolean.valueOf(z);
        }
    }

    public boolean shouldShowRequestPermissionRationale(String str) {
        h hVar = this.mHost;
        if (hVar != null) {
            return hVar.o(str);
        }
        return false;
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent intent) {
        startActivity(intent, null);
    }

    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        startActivityForResult(intent, i, null);
    }

    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        h hVar = this.mHost;
        if (hVar != null) {
            hVar.q(this, intentSender, i, intent, i2, i3, i4, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public void startPostponedEnterTransition() {
        j jVar = this.mFragmentManager;
        if (jVar != null && jVar.q != null) {
            if (Looper.myLooper() != this.mFragmentManager.q.f().getLooper()) {
                this.mFragmentManager.q.f().postAtFrontOfQueue(new b());
                return;
            } else {
                callStartTransitionListener();
                return;
            }
        }
        C2().o = false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        u.b.a(this, sb);
        sb.append(" (");
        sb.append(this.mWho);
        sb.append(")");
        if (this.mFragmentId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            sb.append(" ");
            sb.append(this.mTag);
        }
        sb.append('}');
        return sb.toString();
    }

    public void unregisterForContextMenu(View view) {
        view.setOnCreateContextMenuListener(null);
    }

    /* loaded from: classes.dex */
    public static class g implements Parcelable {
        public static final Parcelable.Creator<g> CREATOR = new a();
        public final Bundle a;

        /* loaded from: classes.dex */
        public static class a implements Parcelable.ClassLoaderCreator {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public g createFromParcel(Parcel parcel) {
                return new g(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b */
            public g createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new g(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c */
            public g[] newArray(int i) {
                return new g[i];
            }
        }

        public g(Bundle bundle) {
            this.a = bundle;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBundle(this.a);
        }

        public g(Parcel parcel, ClassLoader classLoader) {
            Bundle readBundle = parcel.readBundle();
            this.a = readBundle;
            if (classLoader == null || readBundle == null) {
                return;
            }
            readBundle.setClassLoader(classLoader);
        }
    }

    @Deprecated
    public static Fragment instantiate(Context context, String str, Bundle bundle) {
        try {
            Fragment fragment = (Fragment) androidx.fragment.app.g.d(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.setArguments(bundle);
            }
            return fragment;
        } catch (IllegalAccessException e2) {
            throw new e("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (InstantiationException e3) {
            throw new e("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        } catch (NoSuchMethodException e4) {
            throw new e("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e4);
        } catch (InvocationTargetException e5) {
            throw new e("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e5);
        }
    }

    public final String getString(int i, Object... objArr) {
        return getResources().getString(i, objArr);
    }

    public final void postponeEnterTransition(long j, TimeUnit timeUnit) {
        Handler handler;
        C2().o = true;
        j jVar = this.mFragmentManager;
        if (jVar != null) {
            handler = jVar.q.f();
        } else {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.removeCallbacks(this.mPostponedDurationRunnable);
        handler.postDelayed(this.mPostponedDurationRunnable, timeUnit.toMillis(j));
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent intent, Bundle bundle) {
        h hVar = this.mHost;
        if (hVar != null) {
            hVar.p(this, intent, -1, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        h hVar = this.mHost;
        if (hVar != null) {
            hVar.p(this, intent, i, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    @Deprecated
    public LayoutInflater getLayoutInflater(Bundle bundle) {
        h hVar = this.mHost;
        if (hVar != null) {
            LayoutInflater j = hVar.j();
            v.l.b(j, this.mChildFragmentManager.A0());
            return j;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    @Deprecated
    public void onAttach(Activity activity) {
        this.mCalled = true;
    }

    @Deprecated
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
    }
}
