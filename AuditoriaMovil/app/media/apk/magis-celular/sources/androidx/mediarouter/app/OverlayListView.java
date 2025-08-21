package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
final class OverlayListView extends ListView {
    public final List a;

    /* loaded from: classes.dex */
    public static class a {
        public BitmapDrawable a;
        public Rect c;
        public Interpolator d;
        public long e;
        public Rect f;
        public int g;
        public long j;
        public boolean k;
        public boolean l;
        public InterfaceC0022a m;
        public float b = 1.0f;
        public float h = 1.0f;
        public float i = 1.0f;

        /* renamed from: androidx.mediarouter.app.OverlayListView$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public interface InterfaceC0022a {
            void onAnimationEnd();
        }

        public a(BitmapDrawable bitmapDrawable, Rect rect) {
            this.a = bitmapDrawable;
            this.f = rect;
            this.c = new Rect(rect);
            BitmapDrawable bitmapDrawable2 = this.a;
            if (bitmapDrawable2 != null) {
                bitmapDrawable2.setAlpha((int) (this.b * 255.0f));
                this.a.setBounds(this.c);
            }
        }

        public BitmapDrawable a() {
            return this.a;
        }

        public boolean b() {
            return this.k;
        }

        public a c(float f, float f2) {
            this.h = f;
            this.i = f2;
            return this;
        }

        public a d(InterfaceC0022a interfaceC0022a) {
            this.m = interfaceC0022a;
            return this;
        }

        public a e(long j) {
            this.e = j;
            return this;
        }

        public a f(Interpolator interpolator) {
            this.d = interpolator;
            return this;
        }

        public a g(int i) {
            this.g = i;
            return this;
        }

        public void h(long j) {
            this.j = j;
            this.k = true;
        }

        public void i() {
            this.k = true;
            this.l = true;
            InterfaceC0022a interfaceC0022a = this.m;
            if (interfaceC0022a != null) {
                interfaceC0022a.onAnimationEnd();
            }
        }

        public boolean j(long j) {
            float interpolation;
            if (this.l) {
                return false;
            }
            float f = 0.0f;
            float max = Math.max(0.0f, Math.min(1.0f, ((float) (j - this.j)) / ((float) this.e)));
            if (this.k) {
                f = max;
            }
            Interpolator interpolator = this.d;
            if (interpolator == null) {
                interpolation = f;
            } else {
                interpolation = interpolator.getInterpolation(f);
            }
            int i = (int) (this.g * interpolation);
            Rect rect = this.c;
            Rect rect2 = this.f;
            rect.top = rect2.top + i;
            rect.bottom = rect2.bottom + i;
            float f2 = this.h;
            float f3 = f2 + ((this.i - f2) * interpolation);
            this.b = f3;
            BitmapDrawable bitmapDrawable = this.a;
            if (bitmapDrawable != null && rect != null) {
                bitmapDrawable.setAlpha((int) (f3 * 255.0f));
                this.a.setBounds(this.c);
            }
            if (this.k && f >= 1.0f) {
                this.l = true;
                InterfaceC0022a interfaceC0022a = this.m;
                if (interfaceC0022a != null) {
                    interfaceC0022a.onAnimationEnd();
                }
            }
            return !this.l;
        }
    }

    public OverlayListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new ArrayList();
    }

    public void a(a aVar) {
        this.a.add(aVar);
    }

    public void b() {
        for (a aVar : this.a) {
            if (!aVar.b()) {
                aVar.h(getDrawingTime());
            }
        }
    }

    public void c() {
        for (a aVar : this.a) {
            aVar.i();
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.a.size() > 0) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                BitmapDrawable a2 = aVar.a();
                if (a2 != null) {
                    a2.draw(canvas);
                }
                if (!aVar.j(getDrawingTime())) {
                    it.remove();
                }
            }
        }
    }
}
