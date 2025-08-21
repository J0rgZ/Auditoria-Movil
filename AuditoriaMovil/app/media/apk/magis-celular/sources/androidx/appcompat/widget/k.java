package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$drawable;
import androidx.appcompat.widget.e2;
/* loaded from: classes.dex */
public final class k {
    public static final PorterDuff.Mode b = PorterDuff.Mode.SRC_IN;
    public static k c;
    public e2 a;

    /* loaded from: classes.dex */
    public static class a implements e2.e {
        public final int[] a = {R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha, R$drawable.abc_ab_share_pack_mtrl_alpha};
        public final int[] b = {R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_seekbar_tick_mark_material, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha};
        public final int[] c = {R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha, R$drawable.abc_text_cursor_material, R$drawable.abc_text_select_handle_left_mtrl_dark, R$drawable.abc_text_select_handle_middle_mtrl_dark, R$drawable.abc_text_select_handle_right_mtrl_dark, R$drawable.abc_text_select_handle_left_mtrl_light, R$drawable.abc_text_select_handle_middle_mtrl_light, R$drawable.abc_text_select_handle_right_mtrl_light};
        public final int[] d = {R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult};
        public final int[] e = {R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material};
        public final int[] f = {R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material, R$drawable.abc_btn_check_material_anim, R$drawable.abc_btn_radio_material_anim};

        /* JADX WARN: Removed duplicated region for block: B:22:0x0052  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x006d A[RETURN] */
        @Override // androidx.appcompat.widget.e2.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean a(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
            /*
                r6 = this;
                android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.k.a()
                int[] r1 = r6.a
                boolean r1 = r6.f(r1, r8)
                r2 = 1
                r3 = 0
                r4 = -1
                if (r1 == 0) goto L15
                int r8 = androidx.appcompat.R$attr.colorControlNormal
            L11:
                r1 = r0
            L12:
                r0 = -1
                r5 = 1
                goto L50
            L15:
                int[] r1 = r6.c
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L20
                int r8 = androidx.appcompat.R$attr.colorControlActivated
                goto L11
            L20:
                int[] r1 = r6.d
                boolean r1 = r6.f(r1, r8)
                r5 = 16842801(0x1010031, float:2.3693695E-38)
                if (r1 == 0) goto L32
                android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
            L2d:
                r1 = r0
                r8 = 16842801(0x1010031, float:2.3693695E-38)
                goto L12
            L32:
                int r1 = androidx.appcompat.R$drawable.abc_list_divider_mtrl_alpha
                if (r8 != r1) goto L47
                r8 = 1109603123(0x42233333, float:40.8)
                int r8 = java.lang.Math.round(r8)
                r1 = 16842800(0x1010030, float:2.3693693E-38)
                r1 = r0
                r5 = 1
                r0 = r8
                r8 = 16842800(0x1010030, float:2.3693693E-38)
                goto L50
            L47:
                int r1 = androidx.appcompat.R$drawable.abc_dialog_material_background
                if (r8 != r1) goto L4c
                goto L2d
            L4c:
                r1 = r0
                r8 = 0
                r0 = -1
                r5 = 0
            L50:
                if (r5 == 0) goto L6d
                boolean r3 = androidx.appcompat.widget.o1.a(r9)
                if (r3 == 0) goto L5c
                android.graphics.drawable.Drawable r9 = r9.mutate()
            L5c:
                int r7 = androidx.appcompat.widget.n2.b(r7, r8)
                android.graphics.PorterDuffColorFilter r7 = androidx.appcompat.widget.k.e(r7, r1)
                r9.setColorFilter(r7)
                if (r0 == r4) goto L6c
                r9.setAlpha(r0)
            L6c:
                return r2
            L6d:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.k.a.a(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // androidx.appcompat.widget.e2.e
        public PorterDuff.Mode b(int i) {
            if (i == R$drawable.abc_switch_thumb_material) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }

        @Override // androidx.appcompat.widget.e2.e
        public Drawable c(e2 e2Var, Context context, int i) {
            if (i == R$drawable.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{e2Var.j(context, R$drawable.abc_cab_background_internal_bg), e2Var.j(context, R$drawable.abc_cab_background_top_mtrl_alpha)});
            }
            return null;
        }

        @Override // androidx.appcompat.widget.e2.e
        public ColorStateList d(Context context, int i) {
            if (i == R$drawable.abc_edit_text_material) {
                return b.b.c(context, R$color.abc_tint_edittext);
            }
            if (i == R$drawable.abc_switch_track_mtrl_alpha) {
                return b.b.c(context, R$color.abc_tint_switch_track);
            }
            if (i == R$drawable.abc_switch_thumb_material) {
                return k(context);
            }
            if (i == R$drawable.abc_btn_default_mtrl_shape) {
                return j(context);
            }
            if (i == R$drawable.abc_btn_borderless_material) {
                return g(context);
            }
            if (i == R$drawable.abc_btn_colored_material) {
                return i(context);
            }
            if (i != R$drawable.abc_spinner_mtrl_am_alpha && i != R$drawable.abc_spinner_textfield_background_material) {
                if (f(this.b, i)) {
                    return n2.d(context, R$attr.colorControlNormal);
                }
                if (f(this.e, i)) {
                    return b.b.c(context, R$color.abc_tint_default);
                }
                if (f(this.f, i)) {
                    return b.b.c(context, R$color.abc_tint_btn_checkable);
                }
                if (i == R$drawable.abc_seekbar_thumb_material) {
                    return b.b.c(context, R$color.abc_tint_seek_thumb);
                }
                return null;
            }
            return b.b.c(context, R$color.abc_tint_spinner);
        }

        @Override // androidx.appcompat.widget.e2.e
        public boolean e(Context context, int i, Drawable drawable) {
            if (i == R$drawable.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908288);
                int i2 = R$attr.colorControlNormal;
                l(findDrawableByLayerId, n2.b(context, i2), k.b);
                l(layerDrawable.findDrawableByLayerId(16908303), n2.b(context, i2), k.b);
                l(layerDrawable.findDrawableByLayerId(16908301), n2.b(context, R$attr.colorControlActivated), k.b);
                return true;
            } else if (i != R$drawable.abc_ratingbar_material && i != R$drawable.abc_ratingbar_indicator_material && i != R$drawable.abc_ratingbar_small_material) {
                return false;
            } else {
                LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
                l(layerDrawable2.findDrawableByLayerId(16908288), n2.a(context, R$attr.colorControlNormal), k.b);
                Drawable findDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(16908303);
                int i3 = R$attr.colorControlActivated;
                l(findDrawableByLayerId2, n2.b(context, i3), k.b);
                l(layerDrawable2.findDrawableByLayerId(16908301), n2.b(context, i3), k.b);
                return true;
            }
        }

        public final boolean f(int[] iArr, int i) {
            for (int i2 : iArr) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }

        public final ColorStateList g(Context context) {
            return h(context, 0);
        }

        public final ColorStateList h(Context context, int i) {
            int b = n2.b(context, R$attr.colorControlHighlight);
            return new ColorStateList(new int[][]{n2.b, n2.e, n2.c, n2.i}, new int[]{n2.a(context, R$attr.colorButtonNormal), l.a.i(b, i), l.a.i(b, i), i});
        }

        public final ColorStateList i(Context context) {
            return h(context, n2.b(context, R$attr.colorAccent));
        }

        public final ColorStateList j(Context context) {
            return h(context, n2.b(context, R$attr.colorButtonNormal));
        }

        public final ColorStateList k(Context context) {
            int[][] iArr = new int[3];
            int[] iArr2 = new int[3];
            int i = R$attr.colorSwitchThumbNormal;
            ColorStateList d = n2.d(context, i);
            if (d != null && d.isStateful()) {
                int[] iArr3 = n2.b;
                iArr[0] = iArr3;
                iArr2[0] = d.getColorForState(iArr3, 0);
                iArr[1] = n2.f;
                iArr2[1] = n2.b(context, R$attr.colorControlActivated);
                iArr[2] = n2.i;
                iArr2[2] = d.getDefaultColor();
            } else {
                iArr[0] = n2.b;
                iArr2[0] = n2.a(context, i);
                iArr[1] = n2.f;
                iArr2[1] = n2.b(context, R$attr.colorControlActivated);
                iArr[2] = n2.i;
                iArr2[2] = n2.b(context, i);
            }
            return new ColorStateList(iArr, iArr2);
        }

        public final void l(Drawable drawable, int i, PorterDuff.Mode mode) {
            if (o1.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = k.b;
            }
            drawable.setColorFilter(k.e(i, mode));
        }
    }

    public static synchronized k b() {
        k kVar;
        synchronized (k.class) {
            if (c == null) {
                h();
            }
            kVar = c;
        }
        return kVar;
    }

    public static synchronized PorterDuffColorFilter e(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter l;
        synchronized (k.class) {
            l = e2.l(i, mode);
        }
        return l;
    }

    public static synchronized void h() {
        synchronized (k.class) {
            if (c == null) {
                k kVar = new k();
                c = kVar;
                kVar.a = e2.h();
                c.a.u(new a());
            }
        }
    }

    public static void i(Drawable drawable, p2 p2Var, int[] iArr) {
        e2.w(drawable, p2Var, iArr);
    }

    public synchronized Drawable c(Context context, int i) {
        return this.a.j(context, i);
    }

    public synchronized Drawable d(Context context, int i, boolean z) {
        return this.a.k(context, i, z);
    }

    public synchronized ColorStateList f(Context context, int i) {
        return this.a.m(context, i);
    }

    public synchronized void g(Context context) {
        this.a.s(context);
    }
}
