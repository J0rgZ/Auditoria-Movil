package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.lifecycle.c;
import i.h;
import i.j1;
import java.io.FileDescriptor;
import java.io.PrintWriter;
/* loaded from: classes.dex */
public abstract class d extends ComponentActivity implements h.c, h.e {
    static final String ALLOCATED_REQUEST_INDICIES_TAG = "android:support:request_indicies";
    static final String FRAGMENTS_TAG = "android:support:fragments";
    static final int MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS = 65534;
    static final String NEXT_CANDIDATE_REQUEST_INDEX_TAG = "android:support:next_request_index";
    static final String REQUEST_FRAGMENT_WHO_TAG = "android:support:request_fragment_who";
    private static final String TAG = "FragmentActivity";
    boolean mCreated;
    int mNextCandidateRequestIndex;
    androidx.collection.h mPendingFragmentActivityResults;
    boolean mRequestedPermissionsFromFragment;
    boolean mResumed;
    boolean mStartedActivityFromFragment;
    boolean mStartedIntentSenderFromFragment;
    final f mFragments = f.b(new a());
    final androidx.lifecycle.g mFragmentLifecycleRegistry = new androidx.lifecycle.g(this);
    boolean mStopped = true;

    /* loaded from: classes.dex */
    public class a extends h implements androidx.lifecycle.t, androidx.activity.c {
        public a() {
            super(d.this);
        }

        @Override // androidx.fragment.app.e
        public View b(int i) {
            return d.this.findViewById(i);
        }

        @Override // androidx.fragment.app.e
        public boolean c() {
            Window window = d.this.getWindow();
            if (window != null && window.peekDecorView() != null) {
                return true;
            }
            return false;
        }

        @Override // androidx.fragment.app.h
        public void g(Fragment fragment) {
            d.this.onAttachFragment(fragment);
        }

        @Override // androidx.lifecycle.f
        public androidx.lifecycle.c getLifecycle() {
            return d.this.mFragmentLifecycleRegistry;
        }

        @Override // androidx.activity.c
        public OnBackPressedDispatcher getOnBackPressedDispatcher() {
            return d.this.getOnBackPressedDispatcher();
        }

        @Override // androidx.lifecycle.t
        public androidx.lifecycle.s getViewModelStore() {
            return d.this.getViewModelStore();
        }

        @Override // androidx.fragment.app.h
        public void h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            d.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // androidx.fragment.app.h
        public LayoutInflater j() {
            return d.this.getLayoutInflater().cloneInContext(d.this);
        }

        @Override // androidx.fragment.app.h
        public int k() {
            Window window = d.this.getWindow();
            if (window == null) {
                return 0;
            }
            return window.getAttributes().windowAnimations;
        }

        @Override // androidx.fragment.app.h
        public boolean l() {
            if (d.this.getWindow() != null) {
                return true;
            }
            return false;
        }

        @Override // androidx.fragment.app.h
        public void m(Fragment fragment, String[] strArr, int i) {
            d.this.requestPermissionsFromFragment(fragment, strArr, i);
        }

        @Override // androidx.fragment.app.h
        public boolean n(Fragment fragment) {
            return !d.this.isFinishing();
        }

        @Override // androidx.fragment.app.h
        public boolean o(String str) {
            return i.h.j(d.this, str);
        }

        @Override // androidx.fragment.app.h
        public void p(Fragment fragment, Intent intent, int i, Bundle bundle) {
            d.this.startActivityFromFragment(fragment, intent, i, bundle);
        }

        @Override // androidx.fragment.app.h
        public void q(Fragment fragment, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
            d.this.startIntentSenderFromFragment(fragment, intentSender, i, intent, i2, i3, i4, bundle);
        }

        @Override // androidx.fragment.app.h
        public void r() {
            d.this.supportInvalidateOptionsMenu();
        }

        @Override // androidx.fragment.app.h
        /* renamed from: s */
        public d i() {
            return d.this;
        }
    }

    public static boolean L1(i iVar, c.b bVar) {
        boolean z = false;
        for (Fragment fragment : iVar.f()) {
            if (fragment != null) {
                if (fragment.getLifecycle().b().a(c.b.STARTED)) {
                    fragment.mLifecycleRegistry.p(bVar);
                    z = true;
                }
                if (fragment.getHost() != null) {
                    z |= L1(fragment.getChildFragmentManager(), bVar);
                }
            }
        }
        return z;
    }

    public static void checkForValidRequestCode(int i) {
        if ((i & (-65536)) == 0) {
            return;
        }
        throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
    }

    public final int G1(Fragment fragment) {
        if (this.mPendingFragmentActivityResults.l() < MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS) {
            while (this.mPendingFragmentActivityResults.g(this.mNextCandidateRequestIndex) >= 0) {
                this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS;
            }
            int i = this.mNextCandidateRequestIndex;
            this.mPendingFragmentActivityResults.j(i, fragment.mWho);
            this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS;
            return i;
        }
        throw new IllegalStateException("Too many pending Fragment activity results.");
    }

    public final void K1() {
        do {
        } while (L1(getSupportFragmentManager(), c.b.CREATED));
    }

    public final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mFragments.w(view, str, context, attributeSet);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super/*android.app.Activity*/.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print(" mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        if (getApplication() != null) {
            d0.a.b(this).a(str2, fileDescriptor, printWriter, strArr);
        }
        this.mFragments.u().b(str, fileDescriptor, printWriter, strArr);
    }

    public i getSupportFragmentManager() {
        return this.mFragments.u();
    }

    @Deprecated
    public d0.a getSupportLoaderManager() {
        return d0.a.b(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onActivityResult(int i, int i2, Intent intent) {
        this.mFragments.v();
        int i3 = i >> 16;
        if (i3 != 0) {
            int i4 = i3 - 1;
            String str = (String) this.mPendingFragmentActivityResults.e(i4);
            this.mPendingFragmentActivityResults.k(i4);
            if (str == null) {
                return;
            }
            Fragment t = this.mFragments.t(str);
            if (t == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Activity result no fragment exists for who: ");
                sb.append(str);
                return;
            }
            t.onActivityResult(i & 65535, i2, intent);
            return;
        }
        i.h.d();
        super/*android.app.Activity*/.onActivityResult(i, i2, intent);
    }

    public void onAttachFragment(Fragment fragment) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        super/*android.app.Activity*/.onConfigurationChanged(configuration);
        this.mFragments.v();
        this.mFragments.d(configuration);
    }

    @Override // androidx.activity.ComponentActivity
    public void onCreate(Bundle bundle) {
        this.mFragments.a(null);
        if (bundle != null) {
            this.mFragments.x(bundle.getParcelable(FRAGMENTS_TAG));
            if (bundle.containsKey(NEXT_CANDIDATE_REQUEST_INDEX_TAG)) {
                this.mNextCandidateRequestIndex = bundle.getInt(NEXT_CANDIDATE_REQUEST_INDEX_TAG);
                int[] intArray = bundle.getIntArray(ALLOCATED_REQUEST_INDICIES_TAG);
                String[] stringArray = bundle.getStringArray(REQUEST_FRAGMENT_WHO_TAG);
                if (intArray != null && stringArray != null && intArray.length == stringArray.length) {
                    this.mPendingFragmentActivityResults = new androidx.collection.h(intArray.length);
                    for (int i = 0; i < intArray.length; i++) {
                        this.mPendingFragmentActivityResults.j(intArray[i], stringArray[i]);
                    }
                }
            }
        }
        if (this.mPendingFragmentActivityResults == null) {
            this.mPendingFragmentActivityResults = new androidx.collection.h();
            this.mNextCandidateRequestIndex = 0;
        }
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.i(c.a.ON_CREATE);
        this.mFragments.f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i == 0) {
            return super/*android.app.Activity*/.onCreatePanelMenu(i, menu) | this.mFragments.g(menu, getMenuInflater());
        }
        return super/*android.app.Activity*/.onCreatePanelMenu(i, menu);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(view, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super/*android.app.Activity*/.onCreateView(view, str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onDestroy() {
        super/*android.app.Activity*/.onDestroy();
        this.mFragments.h();
        this.mFragmentLifecycleRegistry.i(c.a.ON_DESTROY);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onLowMemory() {
        super/*android.app.Activity*/.onLowMemory();
        this.mFragments.i();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super/*android.app.Activity*/.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i != 0) {
            if (i != 6) {
                return false;
            }
            return this.mFragments.e(menuItem);
        }
        return this.mFragments.k(menuItem);
    }

    public void onMultiWindowModeChanged(boolean z) {
        this.mFragments.j(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onNewIntent(@SuppressLint({"UnknownNullness"}) Intent intent) {
        super/*android.app.Activity*/.onNewIntent(intent);
        this.mFragments.v();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onPanelClosed(int i, Menu menu) {
        if (i == 0) {
            this.mFragments.l(menu);
        }
        super/*android.app.Activity*/.onPanelClosed(i, menu);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onPause() {
        super/*android.app.Activity*/.onPause();
        this.mResumed = false;
        this.mFragments.m();
        this.mFragmentLifecycleRegistry.i(c.a.ON_PAUSE);
    }

    public void onPictureInPictureModeChanged(boolean z) {
        this.mFragments.n(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onPostResume() {
        super/*android.app.Activity*/.onPostResume();
        onResumeFragments();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public boolean onPrepareOptionsPanel(View view, Menu menu) {
        return super/*android.app.Activity*/.onPreparePanel(0, view, menu);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i == 0) {
            return onPrepareOptionsPanel(view, menu) | this.mFragments.o(menu);
        }
        return super/*android.app.Activity*/.onPreparePanel(i, view, menu);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.mFragments.v();
        int i2 = (i >> 16) & 65535;
        if (i2 != 0) {
            int i3 = i2 - 1;
            String str = (String) this.mPendingFragmentActivityResults.e(i3);
            this.mPendingFragmentActivityResults.k(i3);
            if (str == null) {
                return;
            }
            Fragment t = this.mFragments.t(str);
            if (t == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Activity result no fragment exists for who: ");
                sb.append(str);
                return;
            }
            t.onRequestPermissionsResult(i & 65535, strArr, iArr);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onResume() {
        super/*android.app.Activity*/.onResume();
        this.mResumed = true;
        this.mFragments.v();
        this.mFragments.s();
    }

    public void onResumeFragments() {
        this.mFragmentLifecycleRegistry.i(c.a.ON_RESUME);
        this.mFragments.p();
    }

    @Override // androidx.activity.ComponentActivity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        K1();
        this.mFragmentLifecycleRegistry.i(c.a.ON_STOP);
        Parcelable y = this.mFragments.y();
        if (y != null) {
            bundle.putParcelable(FRAGMENTS_TAG, y);
        }
        if (this.mPendingFragmentActivityResults.l() > 0) {
            bundle.putInt(NEXT_CANDIDATE_REQUEST_INDEX_TAG, this.mNextCandidateRequestIndex);
            int[] iArr = new int[this.mPendingFragmentActivityResults.l()];
            String[] strArr = new String[this.mPendingFragmentActivityResults.l()];
            for (int i = 0; i < this.mPendingFragmentActivityResults.l(); i++) {
                iArr[i] = this.mPendingFragmentActivityResults.i(i);
                strArr[i] = (String) this.mPendingFragmentActivityResults.m(i);
            }
            bundle.putIntArray(ALLOCATED_REQUEST_INDICIES_TAG, iArr);
            bundle.putStringArray(REQUEST_FRAGMENT_WHO_TAG, strArr);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onStart() {
        super/*android.app.Activity*/.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.c();
        }
        this.mFragments.v();
        this.mFragments.s();
        this.mFragmentLifecycleRegistry.i(c.a.ON_START);
        this.mFragments.q();
    }

    public void onStateNotSaved() {
        this.mFragments.v();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onStop() {
        super/*android.app.Activity*/.onStop();
        this.mStopped = true;
        K1();
        this.mFragments.r();
        this.mFragmentLifecycleRegistry.i(c.a.ON_STOP);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void requestPermissionsFromFragment(Fragment fragment, String[] strArr, int i) {
        if (i == -1) {
            i.h.g(this, strArr, i);
            return;
        }
        checkForValidRequestCode(i);
        try {
            this.mRequestedPermissionsFromFragment = true;
            i.h.g(this, strArr, ((G1(fragment) + 1) << 16) + (i & 65535));
        } finally {
            this.mRequestedPermissionsFromFragment = false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setEnterSharedElementCallback(j1 j1Var) {
        i.h.h(this, j1Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setExitSharedElementCallback(j1 j1Var) {
        i.h.i(this, j1Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        if (!this.mStartedActivityFromFragment && i != -1) {
            checkForValidRequestCode(i);
        }
        super/*android.app.Activity*/.startActivityForResult(intent, i);
    }

    public void startActivityFromFragment(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        startActivityFromFragment(fragment, intent, i, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) {
        if (!this.mStartedIntentSenderFromFragment && i != -1) {
            checkForValidRequestCode(i);
        }
        super/*android.app.Activity*/.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startIntentSenderFromFragment(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        this.mStartedIntentSenderFromFragment = true;
        try {
            if (i == -1) {
                i.h.l(this, intentSender, i, intent, i2, i3, i4, bundle);
                return;
            }
            checkForValidRequestCode(i);
            i.h.l(this, intentSender, ((G1(fragment) + 1) << 16) + (i & 65535), intent, i2, i3, i4, bundle);
        } finally {
            this.mStartedIntentSenderFromFragment = false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void supportFinishAfterTransition() {
        i.h.c(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public void supportInvalidateOptionsMenu() {
        invalidateOptionsMenu();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void supportPostponeEnterTransition() {
        i.h.e(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void supportStartPostponedEnterTransition() {
        i.h.m(this);
    }

    public final void validateRequestPermissionsRequestCode(int i) {
        if (!this.mRequestedPermissionsFromFragment && i != -1) {
            checkForValidRequestCode(i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startActivityFromFragment(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        this.mStartedActivityFromFragment = true;
        try {
            if (i == -1) {
                i.h.k(this, intent, -1, bundle);
                return;
            }
            checkForValidRequestCode(i);
            i.h.k(this, intent, ((G1(fragment) + 1) << 16) + (i & 65535), bundle);
        } finally {
            this.mStartedActivityFromFragment = false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(null, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super/*android.app.Activity*/.onCreateView(str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        if (!this.mStartedActivityFromFragment && i != -1) {
            checkForValidRequestCode(i);
        }
        super/*android.app.Activity*/.startActivityForResult(intent, i, bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        if (!this.mStartedIntentSenderFromFragment && i != -1) {
            checkForValidRequestCode(i);
        }
        super/*android.app.Activity*/.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }
}
