package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
/* loaded from: classes.dex */
public class a implements o.b {
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public CharSequence e;
    public CharSequence f;
    public Intent g;
    public char h;
    public char j;
    public Drawable l;
    public Context n;
    public MenuItem.OnMenuItemClickListener o;
    public CharSequence p;
    public CharSequence q;
    public int i = 4096;
    public int k = 4096;
    public int m = 0;
    public ColorStateList r = null;

    /* renamed from: s  reason: collision with root package name */
    public PorterDuff.Mode f7s = null;
    public boolean t = false;
    public boolean u = false;
    public int v = 16;

    public a(Context context, int i, int i2, int i3, int i4, CharSequence charSequence) {
        this.n = context;
        this.a = i2;
        this.b = i;
        this.c = i3;
        this.d = i4;
        this.e = charSequence;
    }

    public o.b a(v.b bVar) {
        throw new UnsupportedOperationException();
    }

    public v.b b() {
        return null;
    }

    public final void c() {
        Drawable drawable = this.l;
        if (drawable != null) {
            if (this.t || this.u) {
                Drawable r = m.h.r(drawable);
                this.l = r;
                Drawable mutate = r.mutate();
                this.l = mutate;
                if (this.t) {
                    m.h.o(mutate, this.r);
                }
                if (this.u) {
                    m.h.p(this.l, this.f7s);
                }
            }
        }
    }

    public boolean collapseActionView() {
        return false;
    }

    /* renamed from: d */
    public o.b setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: e */
    public o.b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    public boolean expandActionView() {
        return false;
    }

    /* renamed from: f */
    public o.b setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public View getActionView() {
        return null;
    }

    public int getAlphabeticModifiers() {
        return this.k;
    }

    public char getAlphabeticShortcut() {
        return this.j;
    }

    public CharSequence getContentDescription() {
        return this.p;
    }

    public int getGroupId() {
        return this.b;
    }

    public Drawable getIcon() {
        return this.l;
    }

    public ColorStateList getIconTintList() {
        return this.r;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.f7s;
    }

    public Intent getIntent() {
        return this.g;
    }

    public int getItemId() {
        return this.a;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    public int getNumericModifiers() {
        return this.i;
    }

    public char getNumericShortcut() {
        return this.h;
    }

    public int getOrder() {
        return this.d;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public CharSequence getTitle() {
        return this.e;
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f;
        if (charSequence == null) {
            return this.e;
        }
        return charSequence;
    }

    public CharSequence getTooltipText() {
        return this.q;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isActionViewExpanded() {
        return false;
    }

    public boolean isCheckable() {
        if ((this.v & 1) != 0) {
            return true;
        }
        return false;
    }

    public boolean isChecked() {
        if ((this.v & 2) != 0) {
            return true;
        }
        return false;
    }

    public boolean isEnabled() {
        if ((this.v & 16) != 0) {
            return true;
        }
        return false;
    }

    public boolean isVisible() {
        if ((this.v & 8) == 0) {
            return true;
        }
        return false;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setAlphabeticShortcut(char c) {
        this.j = Character.toLowerCase(c);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setCheckable(boolean z) {
        this.v = (z ? 1 : 0) | (this.v & (-2));
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setChecked(boolean z) {
        int i;
        int i2 = this.v & (-3);
        if (z) {
            i = 2;
        } else {
            i = 0;
        }
        this.v = i | i2;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setEnabled(boolean z) {
        int i;
        int i2 = this.v & (-17);
        if (z) {
            i = 16;
        } else {
            i = 0;
        }
        this.v = i | i2;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIcon(Drawable drawable) {
        this.l = drawable;
        this.m = 0;
        c();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.r = colorStateList;
        this.t = true;
        c();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f7s = mode;
        this.u = true;
        c();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIntent(Intent intent) {
        this.g = intent;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setNumericShortcut(char c) {
        this.h = c;
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.o = onMenuItemClickListener;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setShortcut(char c, char c2) {
        this.h = c;
        this.j = Character.toLowerCase(c2);
        return this;
    }

    public void setShowAsAction(int i) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setTitle(CharSequence charSequence) {
        this.e = charSequence;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f = charSequence;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setVisible(boolean z) {
        int i = 8;
        int i2 = this.v & 8;
        if (z) {
            i = 0;
        }
        this.v = i2 | i;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setAlphabeticShortcut(char c, int i) {
        this.j = Character.toLowerCase(c);
        this.k = KeyEvent.normalizeMetaState(i);
        return this;
    }

    public o.b setContentDescription(CharSequence charSequence) {
        this.p = charSequence;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setNumericShortcut(char c, int i) {
        this.h = c;
        this.i = KeyEvent.normalizeMetaState(i);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setTitle(int i) {
        this.e = this.n.getResources().getString(i);
        return this;
    }

    public o.b setTooltipText(CharSequence charSequence) {
        this.q = charSequence;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.h = c;
        this.i = KeyEvent.normalizeMetaState(i);
        this.j = Character.toLowerCase(c2);
        this.k = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIcon(int i) {
        this.m = i;
        this.l = j.a.getDrawable(this.n, i);
        c();
        return this;
    }
}
