package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.AppCompatSeekBar;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class MediaRouteVolumeSlider extends AppCompatSeekBar {
    public final float a;
    public boolean b;
    public Drawable c;
    public int d;
    public int e;

    public MediaRouteVolumeSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.seekBarStyle);
    }

    public void a(int i) {
        b(i, i);
    }

    public void b(int i, int i2) {
        if (this.d != i) {
            if (Color.alpha(i) != 255) {
                Log.e("MediaRouteVolumeSlider", "Volume slider progress and thumb color cannot be translucent: #" + Integer.toHexString(i));
            }
            this.d = i;
        }
        if (this.e != i2) {
            if (Color.alpha(i2) != 255) {
                Log.e("MediaRouteVolumeSlider", "Volume slider background color cannot be translucent: #" + Integer.toHexString(i2));
            }
            this.e = i2;
        }
    }

    public void c(boolean z) {
        Drawable drawable;
        if (this.b == z) {
            return;
        }
        this.b = z;
        if (z) {
            drawable = null;
        } else {
            drawable = this.c;
        }
        super.setThumb(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void drawableStateChanged() {
        int i;
        super.drawableStateChanged();
        if (isEnabled()) {
            i = 255;
        } else {
            i = (int) (this.a * 255.0f);
        }
        this.c.setColorFilter(this.d, PorterDuff.Mode.SRC_IN);
        this.c.setAlpha(i);
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) getProgressDrawable();
            Drawable findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908301);
            layerDrawable.findDrawableByLayerId(16908288).setColorFilter(this.e, PorterDuff.Mode.SRC_IN);
            progressDrawable = findDrawableByLayerId;
        }
        progressDrawable.setColorFilter(this.d, PorterDuff.Mode.SRC_IN);
        progressDrawable.setAlpha(i);
    }

    @Override // android.widget.AbsSeekBar
    public void setThumb(Drawable drawable) {
        this.c = drawable;
        if (this.b) {
            drawable = null;
        }
        super.setThumb(drawable);
    }

    public MediaRouteVolumeSlider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = i.h(context);
    }
}
