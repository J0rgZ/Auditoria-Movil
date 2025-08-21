package androidx.appcompat.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.R$string;
import androidx.appcompat.view.menu.n;
import v.b;
/* loaded from: classes.dex */
public final class i implements o.b {
    public View A;
    public v.b B;
    public MenuItem.OnActionExpandListener C;
    public ContextMenu.ContextMenuInfo E;
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
    public g n;
    public r o;
    public Runnable p;
    public MenuItem.OnMenuItemClickListener q;
    public CharSequence r;

    /* renamed from: s  reason: collision with root package name */
    public CharSequence f9s;
    public int z;
    public int i = 4096;
    public int k = 4096;
    public int m = 0;
    public ColorStateList t = null;
    public PorterDuff.Mode u = null;
    public boolean v = false;
    public boolean w = false;
    public boolean x = false;
    public int y = 16;
    public boolean D = false;

    /* loaded from: classes.dex */
    public class a implements b.b {
        public a() {
        }

        public void onActionProviderVisibilityChanged(boolean z) {
            i iVar = i.this;
            iVar.n.onItemVisibleChanged(iVar);
        }
    }

    public i(g gVar, int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        this.n = gVar;
        this.a = i2;
        this.b = i;
        this.c = i3;
        this.d = i4;
        this.e = charSequence;
        this.z = i5;
    }

    public static void d(StringBuilder sb, int i, int i2, String str) {
        if ((i & i2) == i2) {
            sb.append(str);
        }
    }

    public boolean A() {
        if (this.n.isShortcutsVisible() && g() != 0) {
            return true;
        }
        return false;
    }

    public boolean B() {
        if ((this.z & 4) == 4) {
            return true;
        }
        return false;
    }

    public o.b a(v.b bVar) {
        v.b bVar2 = this.B;
        if (bVar2 != null) {
            bVar2.j();
        }
        this.A = null;
        this.B = bVar;
        this.n.onItemsChanged(true);
        v.b bVar3 = this.B;
        if (bVar3 != null) {
            bVar3.l(new a());
        }
        return this;
    }

    public v.b b() {
        return this.B;
    }

    public void c() {
        this.n.onItemActionRequestChanged(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean collapseActionView() {
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener != null && !onActionExpandListener.onMenuItemActionCollapse(this)) {
            return false;
        }
        return this.n.collapseItemActionView(this);
    }

    public final Drawable e(Drawable drawable) {
        if (drawable != null && this.x && (this.v || this.w)) {
            drawable = m.h.r(drawable).mutate();
            if (this.v) {
                m.h.o(drawable, this.t);
            }
            if (this.w) {
                m.h.p(drawable, this.u);
            }
            this.x = false;
        }
        return drawable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean expandActionView() {
        if (!j()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener != null && !onActionExpandListener.onMenuItemActionExpand(this)) {
            return false;
        }
        return this.n.expandItemActionView(this);
    }

    public int f() {
        return this.d;
    }

    public char g() {
        if (this.n.isQwertyMode()) {
            return this.j;
        }
        return this.h;
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View getActionView() {
        View view = this.A;
        if (view != null) {
            return view;
        }
        v.b bVar = this.B;
        if (bVar != null) {
            View e = bVar.e(this);
            this.A = e;
            return e;
        }
        return null;
    }

    public int getAlphabeticModifiers() {
        return this.k;
    }

    public char getAlphabeticShortcut() {
        return this.j;
    }

    public CharSequence getContentDescription() {
        return this.r;
    }

    public int getGroupId() {
        return this.b;
    }

    public Drawable getIcon() {
        Drawable drawable = this.l;
        if (drawable != null) {
            return e(drawable);
        }
        if (this.m != 0) {
            Drawable d = b.b.d(this.n.getContext(), this.m);
            this.m = 0;
            this.l = d;
            return e(d);
        }
        return null;
    }

    public ColorStateList getIconTintList() {
        return this.t;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.u;
    }

    public Intent getIntent() {
        return this.g;
    }

    public int getItemId() {
        return this.a;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.E;
    }

    public int getNumericModifiers() {
        return this.i;
    }

    public char getNumericShortcut() {
        return this.h;
    }

    public int getOrder() {
        return this.c;
    }

    public SubMenu getSubMenu() {
        return this.o;
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
        return this.f9s;
    }

    public String h() {
        int i;
        char g = g();
        if (g == 0) {
            return "";
        }
        Resources resources = this.n.getContext().getResources();
        StringBuilder sb = new StringBuilder();
        if (ViewConfiguration.get(this.n.getContext()).hasPermanentMenuKey()) {
            sb.append(resources.getString(R$string.abc_prepend_shortcut_label));
        }
        if (this.n.isQwertyMode()) {
            i = this.k;
        } else {
            i = this.i;
        }
        d(sb, i, 65536, resources.getString(R$string.abc_menu_meta_shortcut_label));
        d(sb, i, 4096, resources.getString(R$string.abc_menu_ctrl_shortcut_label));
        d(sb, i, 2, resources.getString(R$string.abc_menu_alt_shortcut_label));
        d(sb, i, 1, resources.getString(R$string.abc_menu_shift_shortcut_label));
        d(sb, i, 4, resources.getString(R$string.abc_menu_sym_shortcut_label));
        d(sb, i, 8, resources.getString(R$string.abc_menu_function_shortcut_label));
        if (g != '\b') {
            if (g != '\n') {
                if (g != ' ') {
                    sb.append(g);
                } else {
                    sb.append(resources.getString(R$string.abc_menu_space_shortcut_label));
                }
            } else {
                sb.append(resources.getString(R$string.abc_menu_enter_shortcut_label));
            }
        } else {
            sb.append(resources.getString(R$string.abc_menu_delete_shortcut_label));
        }
        return sb.toString();
    }

    public boolean hasSubMenu() {
        if (this.o != null) {
            return true;
        }
        return false;
    }

    public CharSequence i(n.a aVar) {
        if (aVar != null && aVar.prefersCondensedTitle()) {
            return getTitleCondensed();
        }
        return getTitle();
    }

    public boolean isActionViewExpanded() {
        return this.D;
    }

    public boolean isCheckable() {
        if ((this.y & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean isChecked() {
        if ((this.y & 2) == 2) {
            return true;
        }
        return false;
    }

    public boolean isEnabled() {
        if ((this.y & 16) != 0) {
            return true;
        }
        return false;
    }

    public boolean isVisible() {
        v.b bVar = this.B;
        if (bVar != null && bVar.h()) {
            if ((this.y & 8) == 0 && this.B.c()) {
                return true;
            }
            return false;
        } else if ((this.y & 8) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean j() {
        v.b bVar;
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null && (bVar = this.B) != null) {
            this.A = bVar.e(this);
        }
        if (this.A == null) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean k() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.q;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        g gVar = this.n;
        if (gVar.dispatchMenuItemSelected(gVar, this)) {
            return true;
        }
        Runnable runnable = this.p;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (this.g != null) {
            try {
                this.n.getContext().startActivity(this.g);
                return true;
            } catch (ActivityNotFoundException e) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e);
            }
        }
        v.b bVar = this.B;
        if (bVar != null && bVar.f()) {
            return true;
        }
        return false;
    }

    public boolean l() {
        if ((this.y & 32) == 32) {
            return true;
        }
        return false;
    }

    public boolean m() {
        if ((this.y & 4) != 0) {
            return true;
        }
        return false;
    }

    public boolean n() {
        if ((this.z & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean o() {
        if ((this.z & 2) == 2) {
            return true;
        }
        return false;
    }

    /* renamed from: p */
    public o.b setActionView(int i) {
        Context context = this.n.getContext();
        setActionView(LayoutInflater.from(context).inflate(i, (ViewGroup) new LinearLayout(context), false));
        return this;
    }

    /* renamed from: q */
    public o.b setActionView(View view) {
        int i;
        this.A = view;
        this.B = null;
        if (view != null && view.getId() == -1 && (i = this.a) > 0) {
            view.setId(i);
        }
        this.n.onItemActionRequestChanged(this);
        return this;
    }

    public void r(boolean z) {
        this.D = z;
        this.n.onItemsChanged(false);
    }

    public void s(boolean z) {
        int i;
        int i2 = this.y;
        int i3 = i2 & (-3);
        if (z) {
            i = 2;
        } else {
            i = 0;
        }
        int i4 = i | i3;
        this.y = i4;
        if (i2 != i4) {
            this.n.onItemsChanged(false);
        }
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setAlphabeticShortcut(char c) {
        if (this.j == c) {
            return this;
        }
        this.j = Character.toLowerCase(c);
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setCheckable(boolean z) {
        int i = this.y;
        int i2 = (z ? 1 : 0) | (i & (-2));
        this.y = i2;
        if (i != i2) {
            this.n.onItemsChanged(false);
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setChecked(boolean z) {
        if ((this.y & 4) != 0) {
            this.n.setExclusiveItemChecked(this);
        } else {
            s(z);
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setEnabled(boolean z) {
        if (z) {
            this.y |= 16;
        } else {
            this.y &= -17;
        }
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIcon(Drawable drawable) {
        this.m = 0;
        this.l = drawable;
        this.x = true;
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.t = colorStateList;
        this.v = true;
        this.x = true;
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.u = mode;
        this.w = true;
        this.x = true;
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIntent(Intent intent) {
        this.g = intent;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setNumericShortcut(char c) {
        if (this.h == c) {
            return this;
        }
        this.h = c;
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.C = onActionExpandListener;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.q = onMenuItemClickListener;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setShortcut(char c, char c2) {
        this.h = c;
        this.j = Character.toLowerCase(c2);
        this.n.onItemsChanged(false);
        return this;
    }

    public void setShowAsAction(int i) {
        int i2 = i & 3;
        if (i2 != 0 && i2 != 1 && i2 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.z = i;
        this.n.onItemActionRequestChanged(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setTitle(CharSequence charSequence) {
        this.e = charSequence;
        this.n.onItemsChanged(false);
        r rVar = this.o;
        if (rVar != null) {
            rVar.setHeaderTitle(charSequence);
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f = charSequence;
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setVisible(boolean z) {
        if (y(z)) {
            this.n.onItemVisibleChanged(this);
        }
        return this;
    }

    public void t(boolean z) {
        int i;
        int i2 = this.y & (-5);
        if (z) {
            i = 4;
        } else {
            i = 0;
        }
        this.y = i | i2;
    }

    public String toString() {
        CharSequence charSequence = this.e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public void u(boolean z) {
        if (z) {
            this.y |= 32;
        } else {
            this.y &= -33;
        }
    }

    public void v(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.E = contextMenuInfo;
    }

    /* renamed from: w */
    public o.b setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    public void x(r rVar) {
        this.o = rVar;
        rVar.setHeaderTitle(getTitle());
    }

    public boolean y(boolean z) {
        int i;
        int i2 = this.y;
        int i3 = i2 & (-9);
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        int i4 = i | i3;
        this.y = i4;
        if (i2 == i4) {
            return false;
        }
        return true;
    }

    public boolean z() {
        return this.n.getOptionalIconsVisible();
    }

    public o.b setContentDescription(CharSequence charSequence) {
        this.r = charSequence;
        this.n.onItemsChanged(false);
        return this;
    }

    public o.b setTooltipText(CharSequence charSequence) {
        this.f9s = charSequence;
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setAlphabeticShortcut(char c, int i) {
        if (this.j == c && this.k == i) {
            return this;
        }
        this.j = Character.toLowerCase(c);
        this.k = KeyEvent.normalizeMetaState(i);
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setNumericShortcut(char c, int i) {
        if (this.h == c && this.i == i) {
            return this;
        }
        this.h = c;
        this.i = KeyEvent.normalizeMetaState(i);
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.h = c;
        this.i = KeyEvent.normalizeMetaState(i);
        this.j = Character.toLowerCase(c2);
        this.k = KeyEvent.normalizeMetaState(i2);
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIcon(int i) {
        this.l = null;
        this.m = i;
        this.x = true;
        this.n.onItemsChanged(false);
        return this;
    }

    public MenuItem setTitle(int i) {
        return setTitle(this.n.getContext().getString(i));
    }
}
