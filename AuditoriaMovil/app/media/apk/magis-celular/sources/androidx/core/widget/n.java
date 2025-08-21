package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;
/* loaded from: classes.dex */
public abstract class n {
    public static ColorStateList a(ImageView imageView) {
        ColorStateList imageTintList;
        if (Build.VERSION.SDK_INT >= 21) {
            imageTintList = imageView.getImageTintList();
            return imageTintList;
        } else if (imageView instanceof h0) {
            return ((h0) imageView).getSupportImageTintList();
        } else {
            return null;
        }
    }

    public static PorterDuff.Mode b(ImageView imageView) {
        PorterDuff.Mode imageTintMode;
        if (Build.VERSION.SDK_INT >= 21) {
            imageTintMode = imageView.getImageTintMode();
            return imageTintMode;
        } else if (imageView instanceof h0) {
            return ((h0) imageView).getSupportImageTintMode();
        } else {
            return null;
        }
    }

    public static void c(ImageView imageView, ColorStateList colorStateList) {
        Drawable drawable;
        ColorStateList imageTintList;
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            imageView.setImageTintList(colorStateList);
            if (i == 21 && (drawable = imageView.getDrawable()) != null) {
                imageTintList = imageView.getImageTintList();
                if (imageTintList != null) {
                    if (drawable.isStateful()) {
                        drawable.setState(imageView.getDrawableState());
                    }
                    imageView.setImageDrawable(drawable);
                }
            }
        } else if (imageView instanceof h0) {
            ((h0) imageView).setSupportImageTintList(colorStateList);
        }
    }

    public static void d(ImageView imageView, PorterDuff.Mode mode) {
        Drawable drawable;
        ColorStateList imageTintList;
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            imageView.setImageTintMode(mode);
            if (i == 21 && (drawable = imageView.getDrawable()) != null) {
                imageTintList = imageView.getImageTintList();
                if (imageTintList != null) {
                    if (drawable.isStateful()) {
                        drawable.setState(imageView.getDrawableState());
                    }
                    imageView.setImageDrawable(drawable);
                }
            }
        } else if (imageView instanceof h0) {
            ((h0) imageView).setSupportImageTintMode(mode);
        }
    }
}
