package androidx.appcompat.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;
import e.b;
import v.k;
/* loaded from: classes.dex */
public abstract class j extends Dialog implements e {
    private f mDelegate;
    private final k.a mKeyDispatcher;

    /* loaded from: classes.dex */
    public class a implements k.a {
        public a() {
        }

        public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
            return j.this.superDispatchKeyEvent(keyEvent);
        }
    }

    public j(Context context, int i) {
        super(context, getThemeResId(context, i));
        this.mKeyDispatcher = new a();
        f delegate = getDelegate();
        delegate.C(getThemeResId(context, i));
        delegate.p(null);
    }

    public static int getThemeResId(Context context, int i) {
        if (i == 0) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R$attr.dialogTheme, typedValue, true);
            return typedValue.resourceId;
        }
        return i;
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().a(view, layoutParams);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return v.k.e(this.mKeyDispatcher, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i) {
        return (T) getDelegate().e(i);
    }

    public f getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = f.d(this, this);
        }
        return this.mDelegate;
    }

    public androidx.appcompat.app.a getSupportActionBar() {
        return getDelegate().j();
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        getDelegate().l();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        getDelegate().k();
        super.onCreate(bundle);
        getDelegate().p(bundle);
    }

    @Override // android.app.Dialog
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

    @Override // androidx.appcompat.app.e
    public e.b onWindowStartingSupportActionMode(b.a aVar) {
        return null;
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        getDelegate().y(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        getDelegate().D(charSequence);
    }

    boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean supportRequestWindowFeature(int i) {
        return getDelegate().x(i);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        getDelegate().z(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().A(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        getDelegate().D(getContext().getString(i));
    }

    public j(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.mKeyDispatcher = new a();
    }
}
