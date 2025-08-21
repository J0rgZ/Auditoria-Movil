package androidx.transition;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;
/* loaded from: classes.dex */
public class i extends Property {
    public final Property a;
    public final PathMeasure b;
    public final float c;
    public final float[] d;
    public final PointF e;
    public float f;

    public i(Property property, Path path) {
        super(Float.class, property.getName());
        this.d = new float[2];
        this.e = new PointF();
        this.a = property;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        this.b = pathMeasure;
        this.c = pathMeasure.getLength();
    }

    @Override // android.util.Property
    /* renamed from: a */
    public Float get(Object obj) {
        return Float.valueOf(this.f);
    }

    @Override // android.util.Property
    /* renamed from: b */
    public void set(Object obj, Float f) {
        this.f = f.floatValue();
        this.b.getPosTan(this.c * f.floatValue(), this.d, null);
        PointF pointF = this.e;
        float[] fArr = this.d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.a.set(obj, pointF);
    }
}
