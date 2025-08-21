package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ProgressBar;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$color;
import androidx.mediarouter.R$drawable;
import androidx.mediarouter.R$style;
/* loaded from: classes.dex */
public abstract class i {
    public static final int a = R$color.mr_dynamic_dialog_icon_light;

    public static Context a(Context context) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, l(context));
        int p = p(contextThemeWrapper, R$attr.mediaRouteTheme);
        if (p != 0) {
            return new ContextThemeWrapper(contextThemeWrapper, p);
        }
        return contextThemeWrapper;
    }

    public static Context b(Context context, int i, boolean z) {
        int i2;
        if (i == 0) {
            if (!z) {
                i2 = androidx.appcompat.R$attr.dialogTheme;
            } else {
                i2 = androidx.appcompat.R$attr.alertDialogTheme;
            }
            i = p(context, i2);
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
        if (p(contextThemeWrapper, R$attr.mediaRouteTheme) != 0) {
            return new ContextThemeWrapper(contextThemeWrapper, l(contextThemeWrapper));
        }
        return contextThemeWrapper;
    }

    public static int c(Context context) {
        int p = p(context, R$attr.mediaRouteTheme);
        if (p == 0) {
            return l(context);
        }
        return p;
    }

    public static int d(Context context) {
        int o = o(context, 0, androidx.appcompat.R$attr.colorPrimary);
        if (l.a.c(o, o(context, 0, 16842801)) < 3.0d) {
            return o(context, 0, androidx.appcompat.R$attr.colorAccent);
        }
        return o;
    }

    public static Drawable e(Context context) {
        return j(context, R$drawable.mr_cast_checkbox);
    }

    public static int f(Context context, int i) {
        if (l.a.c(-1, o(context, i, androidx.appcompat.R$attr.colorPrimary)) >= 3.0d) {
            return -1;
        }
        return -570425344;
    }

    public static Drawable g(Context context) {
        return i(context, R$attr.mediaRouteDefaultIconDrawable);
    }

    public static float h(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(16842803, typedValue, true)) {
            return typedValue.getFloat();
        }
        return 0.5f;
    }

    public static Drawable i(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        Drawable r = m.h.r(obtainStyledAttributes.getDrawable(0));
        if (r(context)) {
            m.h.n(r, j.a.getColor(context, a));
        }
        obtainStyledAttributes.recycle();
        return r;
    }

    public static Drawable j(Context context, int i) {
        Drawable r = m.h.r(j.a.getDrawable(context, i));
        if (r(context)) {
            m.h.n(r, j.a.getColor(context, a));
        }
        return r;
    }

    public static Drawable k(Context context) {
        return j(context, R$drawable.mr_cast_mute_button);
    }

    public static int l(Context context) {
        if (r(context)) {
            if (f(context, 0) == -570425344) {
                return R$style.Theme_MediaRouter_Light;
            }
            return R$style.Theme_MediaRouter_Light_DarkControlPanel;
        } else if (f(context, 0) == -570425344) {
            return R$style.Theme_MediaRouter_LightControlPanel;
        } else {
            return R$style.Theme_MediaRouter;
        }
    }

    public static Drawable m(Context context) {
        return i(context, R$attr.mediaRouteSpeakerIconDrawable);
    }

    public static Drawable n(Context context) {
        return i(context, R$attr.mediaRouteSpeakerGroupIconDrawable);
    }

    public static int o(Context context, int i, int i2) {
        if (i != 0) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, new int[]{i2});
            int color = obtainStyledAttributes.getColor(0, 0);
            obtainStyledAttributes.recycle();
            if (color != 0) {
                return color;
            }
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i2, typedValue, true);
        if (typedValue.resourceId != 0) {
            return context.getResources().getColor(typedValue.resourceId);
        }
        return typedValue.data;
    }

    public static int p(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i, typedValue, true)) {
            return typedValue.resourceId;
        }
        return 0;
    }

    public static Drawable q(Context context) {
        return i(context, R$attr.mediaRouteTvIconDrawable);
    }

    public static boolean r(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(androidx.appcompat.R$attr.isLightTheme, typedValue, true) && typedValue.data != 0) {
            return true;
        }
        return false;
    }

    public static void s(Context context, Dialog dialog) {
        int i;
        View decorView = dialog.getWindow().getDecorView();
        if (r(context)) {
            i = R$color.mr_dynamic_dialog_background_light;
        } else {
            i = R$color.mr_dynamic_dialog_background_dark;
        }
        decorView.setBackgroundColor(j.a.getColor(context, i));
    }

    public static void t(Context context, ProgressBar progressBar) {
        int i;
        if (!progressBar.isIndeterminate()) {
            return;
        }
        if (r(context)) {
            i = R$color.mr_cast_progressbar_progress_and_thumb_light;
        } else {
            i = R$color.mr_cast_progressbar_progress_and_thumb_dark;
        }
        progressBar.getIndeterminateDrawable().setColorFilter(j.a.getColor(context, i), PorterDuff.Mode.SRC_IN);
    }

    public static void u(Context context, View view, View view2, boolean z) {
        int o = o(context, 0, androidx.appcompat.R$attr.colorPrimary);
        int o2 = o(context, 0, androidx.appcompat.R$attr.colorPrimaryDark);
        if (z && f(context, 0) == -570425344) {
            o2 = o;
            o = -1;
        }
        view.setBackgroundColor(o);
        view2.setBackgroundColor(o2);
        view.setTag(Integer.valueOf(o));
        view2.setTag(Integer.valueOf(o2));
    }

    public static void v(Context context, MediaRouteVolumeSlider mediaRouteVolumeSlider) {
        int color;
        int color2;
        if (r(context)) {
            color = j.a.getColor(context, R$color.mr_cast_progressbar_progress_and_thumb_light);
            color2 = j.a.getColor(context, R$color.mr_cast_progressbar_background_light);
        } else {
            color = j.a.getColor(context, R$color.mr_cast_progressbar_progress_and_thumb_dark);
            color2 = j.a.getColor(context, R$color.mr_cast_progressbar_background_dark);
        }
        mediaRouteVolumeSlider.b(color, color2);
    }

    public static void w(Context context, MediaRouteVolumeSlider mediaRouteVolumeSlider, View view) {
        int f = f(context, 0);
        if (Color.alpha(f) != 255) {
            f = l.a.i(f, ((Integer) view.getTag()).intValue());
        }
        mediaRouteVolumeSlider.a(f);
    }
}
