package androidx.transition;

import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.os.Build;
import android.util.Property;
/* loaded from: classes.dex */
public abstract class k {
    public static PropertyValuesHolder a(Property property, Path path) {
        PropertyValuesHolder ofObject;
        if (Build.VERSION.SDK_INT >= 21) {
            ofObject = PropertyValuesHolder.ofObject(property, (TypeConverter) null, path);
            return ofObject;
        }
        return PropertyValuesHolder.ofFloat(new i(property, path), 0.0f, 1.0f);
    }
}
