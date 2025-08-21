package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
/* loaded from: classes.dex */
public interface h0 {
    ColorStateList getSupportImageTintList();

    PorterDuff.Mode getSupportImageTintMode();

    void setSupportImageTintList(ColorStateList colorStateList);

    void setSupportImageTintMode(PorterDuff.Mode mode);
}
