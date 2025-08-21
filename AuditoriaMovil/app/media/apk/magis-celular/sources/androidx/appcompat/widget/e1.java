package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public class e1 {
    public static final RectF k = new RectF();
    public static ConcurrentHashMap l = new ConcurrentHashMap();
    public static ConcurrentHashMap m = new ConcurrentHashMap();
    public int a = 0;
    public boolean b = false;
    public float c = -1.0f;
    public float d = -1.0f;
    public float e = -1.0f;
    public int[] f = new int[0];
    public boolean g = false;
    public TextPaint h;
    public final TextView i;
    public final Context j;

    public e1(TextView textView) {
        this.i = textView;
        this.j = textView.getContext();
    }

    public static Method m(String str) {
        try {
            Method method = (Method) l.get(str);
            if (method == null && (method = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                method.setAccessible(true);
                l.put(str, method);
            }
            return method;
        } catch (Exception unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to retrieve TextView#");
            sb.append(str);
            sb.append("() method");
            return null;
        }
    }

    public static Object o(Object obj, String str, Object obj2) {
        try {
            return m(str).invoke(obj, new Object[0]);
        } catch (Exception unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to invoke TextView#");
            sb.append(str);
            sb.append("() method");
            return obj2;
        }
    }

    public final boolean A() {
        return !(this.i instanceof l);
    }

    public final void B(float f, float f2, float f3) {
        if (f > 0.0f) {
            if (f2 > f) {
                if (f3 > 0.0f) {
                    this.a = 1;
                    this.d = f;
                    this.e = f2;
                    this.c = f3;
                    this.g = false;
                    return;
                }
                throw new IllegalArgumentException("The auto-size step granularity (" + f3 + "px) is less or equal to (0px)");
            }
            throw new IllegalArgumentException("Maximum auto-size text size (" + f2 + "px) is less or equal to minimum auto-size text size (" + f + "px)");
        }
        throw new IllegalArgumentException("Minimum auto-size text size (" + f + "px) is less or equal to (0px)");
    }

    public void a() {
        boolean booleanValue;
        int measuredWidth;
        if (!p()) {
            return;
        }
        if (this.b) {
            if (this.i.getMeasuredHeight() > 0 && this.i.getMeasuredWidth() > 0) {
                if (Build.VERSION.SDK_INT >= 29) {
                    booleanValue = this.i.isHorizontallyScrollable();
                } else {
                    booleanValue = ((Boolean) o(this.i, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue();
                }
                if (booleanValue) {
                    measuredWidth = 1048576;
                } else {
                    measuredWidth = (this.i.getMeasuredWidth() - this.i.getTotalPaddingLeft()) - this.i.getTotalPaddingRight();
                }
                int height = (this.i.getHeight() - this.i.getCompoundPaddingBottom()) - this.i.getCompoundPaddingTop();
                if (measuredWidth > 0 && height > 0) {
                    RectF rectF = k;
                    synchronized (rectF) {
                        rectF.setEmpty();
                        rectF.right = measuredWidth;
                        rectF.bottom = height;
                        float g = g(rectF);
                        if (g != this.i.getTextSize()) {
                            v(0, g);
                        }
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        this.b = true;
    }

    public final int[] b(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (i > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i)) < 0) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    public final void c() {
        this.a = 0;
        this.d = -1.0f;
        this.e = -1.0f;
        this.c = -1.0f;
        this.f = new int[0];
        this.b = false;
    }

    public StaticLayout d(CharSequence charSequence, Layout.Alignment alignment, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return e(charSequence, alignment, i, i2);
        }
        return f(charSequence, alignment, i);
    }

    public final StaticLayout e(CharSequence charSequence, Layout.Alignment alignment, int i, int i2) {
        StaticLayout.Builder obtain;
        StaticLayout.Builder alignment2;
        StaticLayout.Builder lineSpacing;
        StaticLayout.Builder includePad;
        int breakStrategy;
        StaticLayout.Builder breakStrategy2;
        int hyphenationFrequency;
        StaticLayout.Builder hyphenationFrequency2;
        StaticLayout build;
        TextDirectionHeuristic textDirectionHeuristic;
        obtain = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), this.h, i);
        alignment2 = obtain.setAlignment(alignment);
        lineSpacing = alignment2.setLineSpacing(this.i.getLineSpacingExtra(), this.i.getLineSpacingMultiplier());
        includePad = lineSpacing.setIncludePad(this.i.getIncludeFontPadding());
        breakStrategy = this.i.getBreakStrategy();
        breakStrategy2 = includePad.setBreakStrategy(breakStrategy);
        hyphenationFrequency = this.i.getHyphenationFrequency();
        hyphenationFrequency2 = breakStrategy2.setHyphenationFrequency(hyphenationFrequency);
        if (i2 == -1) {
            i2 = Integer.MAX_VALUE;
        }
        hyphenationFrequency2.setMaxLines(i2);
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                textDirectionHeuristic = this.i.getTextDirectionHeuristic();
            } else {
                textDirectionHeuristic = (TextDirectionHeuristic) o(this.i, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
            }
            obtain.setTextDirection(textDirectionHeuristic);
        } catch (ClassCastException unused) {
        }
        build = obtain.build();
        return build;
    }

    public final StaticLayout f(CharSequence charSequence, Layout.Alignment alignment, int i) {
        return new StaticLayout(charSequence, this.h, i, alignment, this.i.getLineSpacingMultiplier(), this.i.getLineSpacingExtra(), this.i.getIncludeFontPadding());
    }

    public final int g(RectF rectF) {
        int length = this.f.length;
        if (length != 0) {
            int i = 1;
            int i2 = length - 1;
            int i3 = 0;
            while (i <= i2) {
                int i4 = (i + i2) / 2;
                if (z(this.f[i4], rectF)) {
                    int i5 = i4 + 1;
                    i3 = i;
                    i = i5;
                } else {
                    i3 = i4 - 1;
                    i2 = i3;
                }
            }
            return this.f[i3];
        }
        throw new IllegalStateException("No available text sizes to choose from.");
    }

    public int h() {
        return Math.round(this.e);
    }

    public int i() {
        return Math.round(this.d);
    }

    public int j() {
        return Math.round(this.c);
    }

    public int[] k() {
        return this.f;
    }

    public int l() {
        return this.a;
    }

    public void n(int i) {
        TextPaint textPaint = this.h;
        if (textPaint == null) {
            this.h = new TextPaint();
        } else {
            textPaint.reset();
        }
        this.h.set(this.i.getPaint());
        this.h.setTextSize(i);
    }

    public boolean p() {
        if (A() && this.a != 0) {
            return true;
        }
        return false;
    }

    public void q(AttributeSet attributeSet, int i) {
        float f;
        float f2;
        float f3;
        int resourceId;
        TypedArray obtainStyledAttributes = this.j.obtainStyledAttributes(attributeSet, R$styleable.n, i, 0);
        int i2 = R$styleable.AppCompatTextView_autoSizeTextType;
        if (obtainStyledAttributes.hasValue(i2)) {
            this.a = obtainStyledAttributes.getInt(i2, 0);
        }
        int i3 = R$styleable.AppCompatTextView_autoSizeStepGranularity;
        if (obtainStyledAttributes.hasValue(i3)) {
            f = obtainStyledAttributes.getDimension(i3, -1.0f);
        } else {
            f = -1.0f;
        }
        int i4 = R$styleable.AppCompatTextView_autoSizeMinTextSize;
        if (obtainStyledAttributes.hasValue(i4)) {
            f2 = obtainStyledAttributes.getDimension(i4, -1.0f);
        } else {
            f2 = -1.0f;
        }
        int i5 = R$styleable.AppCompatTextView_autoSizeMaxTextSize;
        if (obtainStyledAttributes.hasValue(i5)) {
            f3 = obtainStyledAttributes.getDimension(i5, -1.0f);
        } else {
            f3 = -1.0f;
        }
        int i6 = R$styleable.AppCompatTextView_autoSizePresetSizes;
        if (obtainStyledAttributes.hasValue(i6) && (resourceId = obtainStyledAttributes.getResourceId(i6, 0)) > 0) {
            TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            x(obtainTypedArray);
            obtainTypedArray.recycle();
        }
        obtainStyledAttributes.recycle();
        if (A()) {
            if (this.a == 1) {
                if (!this.g) {
                    DisplayMetrics displayMetrics = this.j.getResources().getDisplayMetrics();
                    if (f2 == -1.0f) {
                        f2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                    }
                    if (f3 == -1.0f) {
                        f3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                    }
                    if (f == -1.0f) {
                        f = 1.0f;
                    }
                    B(f2, f3, f);
                }
                w();
                return;
            }
            return;
        }
        this.a = 0;
    }

    public void r(int i, int i2, int i3, int i4) {
        if (A()) {
            DisplayMetrics displayMetrics = this.j.getResources().getDisplayMetrics();
            B(TypedValue.applyDimension(i4, i, displayMetrics), TypedValue.applyDimension(i4, i2, displayMetrics), TypedValue.applyDimension(i4, i3, displayMetrics));
            if (w()) {
                a();
            }
        }
    }

    public void s(int[] iArr, int i) {
        if (A()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.j.getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < length; i2++) {
                        iArr2[i2] = Math.round(TypedValue.applyDimension(i, iArr[i2], displayMetrics));
                    }
                }
                this.f = b(iArr2);
                if (!y()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.g = false;
            }
            if (w()) {
                a();
            }
        }
    }

    public void t(int i) {
        if (A()) {
            if (i != 0) {
                if (i == 1) {
                    DisplayMetrics displayMetrics = this.j.getResources().getDisplayMetrics();
                    B(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
                    if (w()) {
                        a();
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException("Unknown auto-size text type: " + i);
            }
            c();
        }
    }

    public final void u(float f) {
        if (f != this.i.getPaint().getTextSize()) {
            this.i.getPaint().setTextSize(f);
            boolean isInLayout = this.i.isInLayout();
            if (this.i.getLayout() != null) {
                this.b = false;
                try {
                    Method m2 = m("nullLayouts");
                    if (m2 != null) {
                        m2.invoke(this.i, new Object[0]);
                    }
                } catch (Exception unused) {
                }
                if (!isInLayout) {
                    this.i.requestLayout();
                } else {
                    this.i.forceLayout();
                }
                this.i.invalidate();
            }
        }
    }

    public void v(int i, float f) {
        Resources resources;
        Context context = this.j;
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        u(TypedValue.applyDimension(i, f, resources.getDisplayMetrics()));
    }

    public final boolean w() {
        if (A() && this.a == 1) {
            if (!this.g || this.f.length == 0) {
                int floor = ((int) Math.floor((this.e - this.d) / this.c)) + 1;
                int[] iArr = new int[floor];
                for (int i = 0; i < floor; i++) {
                    iArr[i] = Math.round(this.d + (i * this.c));
                }
                this.f = b(iArr);
            }
            this.b = true;
        } else {
            this.b = false;
        }
        return this.b;
    }

    public final void x(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                iArr[i] = typedArray.getDimensionPixelSize(i, -1);
            }
            this.f = b(iArr);
            y();
        }
    }

    public final boolean y() {
        boolean z;
        int[] iArr = this.f;
        int length = iArr.length;
        if (length > 0) {
            z = true;
        } else {
            z = false;
        }
        this.g = z;
        if (z) {
            this.a = 1;
            this.d = iArr[0];
            this.e = iArr[length - 1];
            this.c = -1.0f;
        }
        return z;
    }

    public final boolean z(int i, RectF rectF) {
        CharSequence transformation;
        CharSequence text = this.i.getText();
        TransformationMethod transformationMethod = this.i.getTransformationMethod();
        if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, this.i)) != null) {
            text = transformation;
        }
        int maxLines = this.i.getMaxLines();
        n(i);
        StaticLayout d = d(text, (Layout.Alignment) o(this.i, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL), Math.round(rectF.right), maxLines);
        if ((maxLines != -1 && (d.getLineCount() > maxLines || d.getLineEnd(d.getLineCount() - 1) != text.length())) || d.getHeight() > rectF.bottom) {
            return false;
        }
        return true;
    }
}
