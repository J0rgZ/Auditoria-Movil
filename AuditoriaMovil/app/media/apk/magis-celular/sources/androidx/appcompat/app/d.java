package androidx.appcompat.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.x2;
import e.b;
import i.k1;
/* loaded from: classes.dex */
public abstract class d extends androidx.fragment.app.d implements e, k1.a {
    private f mDelegate;
    private Resources mResources;

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean Q1(int i, KeyEvent keyEvent) {
        Window window;
        if (Build.VERSION.SDK_INT < 26 && !keyEvent.isCtrlPressed() && !KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) && keyEvent.getRepeatCount() == 0 && !KeyEvent.isModifierKey(keyEvent.getKeyCode()) && (window = getWindow()) != null && window.getDecorView() != null && window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) {
            return true;
        }
        return false;
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().a(view, layoutParams);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void attachBaseContext(Context context) {
        super/*android.app.Activity*/.attachBaseContext(context);
        getDelegate().b(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void closeOptionsMenu() {
        a supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.g()) {
                super/*android.app.Activity*/.closeOptionsMenu();
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        a supportActionBar = getSupportActionBar();
        if (keyCode == 82 && supportActionBar != null && supportActionBar.p(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public <T extends View> T findViewById(int i) {
        return (T) getDelegate().e(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public f getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = f.c(this, this);
        }
        return this.mDelegate;
    }

    public b getDrawerToggleDelegate() {
        return getDelegate().g();
    }

    public MenuInflater getMenuInflater() {
        return getDelegate().i();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Resources getResources() {
        if (this.mResources == null && x2.b()) {
            this.mResources = new x2(this, super/*android.app.Activity*/.getResources());
        }
        Resources resources = this.mResources;
        if (resources == null) {
            return super/*android.app.Activity*/.getResources();
        }
        return resources;
    }

    public a getSupportActionBar() {
        return getDelegate().j();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Intent getSupportParentActivityIntent() {
        return i.p.a(this);
    }

    public void invalidateOptionsMenu() {
        getDelegate().l();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.d
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mResources != null) {
            this.mResources.updateConfiguration(configuration, super/*android.app.Activity*/.getResources().getDisplayMetrics());
        }
        getDelegate().o(configuration);
    }

    public void onContentChanged() {
        onSupportContentChanged();
    }

    @Override // androidx.fragment.app.d, androidx.activity.ComponentActivity
    public void onCreate(Bundle bundle) {
        f delegate = getDelegate();
        delegate.k();
        delegate.p(bundle);
        super.onCreate(bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onCreateSupportNavigateUpTaskStack(k1 k1Var) {
        k1Var.b(this);
    }

    @Override // androidx.fragment.app.d
    public void onDestroy() {
        super.onDestroy();
        getDelegate().q();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (Q1(i, keyEvent)) {
            return true;
        }
        return super/*android.app.Activity*/.onKeyDown(i, keyEvent);
    }

    @Override // androidx.fragment.app.d
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        a supportActionBar = getSupportActionBar();
        if (menuItem.getItemId() == 16908332 && supportActionBar != null && (supportActionBar.j() & 4) != 0) {
            return onSupportNavigateUp();
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onMenuOpened(int i, Menu menu) {
        return super/*android.app.Activity*/.onMenuOpened(i, menu);
    }

    public void onNightModeChanged(int i) {
    }

    @Override // androidx.fragment.app.d
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onPostCreate(Bundle bundle) {
        super/*android.app.Activity*/.onPostCreate(bundle);
        getDelegate().r(bundle);
    }

    @Override // androidx.fragment.app.d
    public void onPostResume() {
        super.onPostResume();
        getDelegate().s();
    }

    public void onPrepareSupportNavigateUpTaskStack(k1 k1Var) {
    }

    @Override // androidx.fragment.app.d, androidx.activity.ComponentActivity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getDelegate().t(bundle);
    }

    @Override // androidx.fragment.app.d
    public void onStart() {
        super.onStart();
        getDelegate().u();
    }

    @Override // androidx.fragment.app.d
    public void onStop() {
        super.onStop();
        getDelegate().v();
    }

    @Override // androidx.appcompat.app.e
    public void onSupportActionModeFinished(e.b bVar) {
    }

    @Override // androidx.appcompat.app.e
    public void onSupportActionModeStarted(e.b bVar) {
    }

    @Deprecated
    public void onSupportContentChanged() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onSupportNavigateUp() {
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent != null) {
            if (supportShouldUpRecreateTask(supportParentActivityIntent)) {
                k1 d = k1.d(this);
                onCreateSupportNavigateUpTaskStack(d);
                onPrepareSupportNavigateUpTaskStack(d);
                d.g();
                try {
                    i.h.b(this);
                    return true;
                } catch (IllegalStateException unused) {
                    finish();
                    return true;
                }
            }
            supportNavigateUpTo(supportParentActivityIntent);
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onTitleChanged(CharSequence charSequence, int i) {
        super/*android.app.Activity*/.onTitleChanged(charSequence, i);
        getDelegate().D(charSequence);
    }

    @Override // androidx.appcompat.app.e
    public e.b onWindowStartingSupportActionMode(b.a aVar) {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void openOptionsMenu() {
        a supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.q()) {
                super/*android.app.Activity*/.openOptionsMenu();
            }
        }
    }

    public void setContentView(int i) {
        getDelegate().y(i);
    }

    public void setSupportActionBar(Toolbar toolbar) {
        getDelegate().B(toolbar);
    }

    @Deprecated
    public void setSupportProgress(int i) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminate(boolean z) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminateVisibility(boolean z) {
    }

    @Deprecated
    public void setSupportProgressBarVisibility(boolean z) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setTheme(int i) {
        super/*android.app.Activity*/.setTheme(i);
        getDelegate().C(i);
    }

    public e.b startSupportActionMode(b.a aVar) {
        return getDelegate().E(aVar);
    }

    @Override // androidx.fragment.app.d
    public void supportInvalidateOptionsMenu() {
        getDelegate().l();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void supportNavigateUpTo(Intent intent) {
        i.p.e(this, intent);
    }

    public boolean supportRequestWindowFeature(int i) {
        return getDelegate().x(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean supportShouldUpRecreateTask(Intent intent) {
        return i.p.f(this, intent);
    }

    public void setContentView(View view) {
        getDelegate().z(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().A(view, layoutParams);
    }
}
