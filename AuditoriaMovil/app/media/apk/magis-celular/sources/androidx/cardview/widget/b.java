package androidx.cardview.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.cardview.widget.i;
/* loaded from: classes.dex */
public class b extends d {

    /* loaded from: classes.dex */
    public class a implements i.a {
        public a() {
        }

        @Override // androidx.cardview.widget.i.a
        public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
            canvas.drawRoundRect(rectF, f, f, paint);
        }
    }

    @Override // androidx.cardview.widget.f
    public void j() {
        i.r = new a();
    }
}
