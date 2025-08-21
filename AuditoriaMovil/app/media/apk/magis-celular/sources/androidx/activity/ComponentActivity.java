package androidx.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.lifecycle.c;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import androidx.lifecycle.g;
import androidx.lifecycle.s;
import androidx.lifecycle.t;
import androidx.savedstate.SavedStateRegistry;
import i.o;
/* loaded from: classes.dex */
public abstract class ComponentActivity extends o implements t, androidx.savedstate.b, c {
    private int mContentLayoutId;
    private s mViewModelStore;
    private final g mLifecycleRegistry = new g(this);
    private final androidx.savedstate.a mSavedStateRegistryController = androidx.savedstate.a.a(this);
    private final OnBackPressedDispatcher mOnBackPressedDispatcher = new OnBackPressedDispatcher(new a());

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            super/*android.app.Activity*/.onBackPressed();
        }
    }

    /* loaded from: classes.dex */
    public static final class b {
        public Object a;
        public s b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ComponentActivity() {
        if (getLifecycle() != null) {
            int i = Build.VERSION.SDK_INT;
            getLifecycle().a(new d() { // from class: androidx.activity.ComponentActivity.2
                @Override // androidx.lifecycle.d
                public void a(f fVar, c.a aVar) {
                    View view;
                    if (aVar == c.a.ON_STOP) {
                        Window window = ComponentActivity.this.getWindow();
                        if (window != null) {
                            view = window.peekDecorView();
                        } else {
                            view = null;
                        }
                        if (view != null) {
                            view.cancelPendingInputEvents();
                        }
                    }
                }
            });
            getLifecycle().a(new d() { // from class: androidx.activity.ComponentActivity.3
                @Override // androidx.lifecycle.d
                public void a(f fVar, c.a aVar) {
                    if (aVar == c.a.ON_DESTROY && !ComponentActivity.this.isChangingConfigurations()) {
                        ComponentActivity.this.getViewModelStore().a();
                    }
                }
            });
            if (i <= 23) {
                getLifecycle().a(new ImmLeaksCleaner(this));
                return;
            }
            return;
        }
        throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        b bVar = (b) getLastNonConfigurationInstance();
        if (bVar != null) {
            return bVar.a;
        }
        return null;
    }

    @Override // androidx.lifecycle.f
    public androidx.lifecycle.c getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // androidx.activity.c
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    @Override // androidx.savedstate.b
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.lifecycle.t
    public s getViewModelStore() {
        if (getApplication() != null) {
            if (this.mViewModelStore == null) {
                b bVar = (b) getLastNonConfigurationInstance();
                if (bVar != null) {
                    this.mViewModelStore = bVar.b;
                }
                if (this.mViewModelStore == null) {
                    this.mViewModelStore = new s();
                }
            }
            return this.mViewModelStore;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    public void onBackPressed() {
        this.mOnBackPressedDispatcher.c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSavedStateRegistryController.c(bundle);
        androidx.lifecycle.o.f(this);
        int i = this.mContentLayoutId;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object onRetainNonConfigurationInstance() {
        b bVar;
        Object onRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        s sVar = this.mViewModelStore;
        if (sVar == null && (bVar = (b) getLastNonConfigurationInstance()) != null) {
            sVar = bVar.b;
        }
        if (sVar == null && onRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        b bVar2 = new b();
        bVar2.a = onRetainCustomNonConfigurationInstance;
        bVar2.b = sVar;
        return bVar2;
    }

    public void onSaveInstanceState(Bundle bundle) {
        androidx.lifecycle.c lifecycle = getLifecycle();
        if (lifecycle instanceof g) {
            ((g) lifecycle).p(c.b.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.d(bundle);
    }
}
