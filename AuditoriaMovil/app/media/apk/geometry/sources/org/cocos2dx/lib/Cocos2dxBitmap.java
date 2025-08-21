package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;
/* loaded from: classes.dex */
public class Cocos2dxBitmap {
    private static final int HORIZONTALALIGN_CENTER = 3;
    private static final int HORIZONTALALIGN_LEFT = 1;
    private static final int HORIZONTALALIGN_RIGHT = 2;
    private static final int VERTICALALIGN_BOTTOM = 2;
    private static final int VERTICALALIGN_CENTER = 3;
    private static final int VERTICALALIGN_TOP = 1;
    private static Context sContext;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class TextProperty {
        private final int mHeightPerLine;
        private final String[] mLines;
        private final int mMaxWidth;
        private final int mTotalHeight;

        TextProperty(int i, int i2, String[] strArr) {
            this.mMaxWidth = i;
            this.mHeightPerLine = i2;
            this.mTotalHeight = i2 * strArr.length;
            this.mLines = strArr;
        }
    }

    private static TextProperty computeTextProperty(String str, int i, int i2, Paint paint) {
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int ceil = (int) Math.ceil(fontMetricsInt.bottom - fontMetricsInt.top);
        String[] splitString = splitString(str, i, i2, paint);
        if (i == 0) {
            int i3 = 0;
            for (String str2 : splitString) {
                int ceil2 = (int) Math.ceil(paint.measureText(str2, 0, str2.length()));
                if (ceil2 > i3) {
                    i3 = ceil2;
                }
            }
            i = i3;
        }
        return new TextProperty(i, ceil, splitString);
    }

    private static int computeX(String str, int i, int i2) {
        if (i2 != 2) {
            if (i2 != 3) {
                return 0;
            }
            return i / 2;
        }
        return i;
    }

    private static int computeY(Paint.FontMetricsInt fontMetricsInt, int i, int i2, int i3) {
        int i4;
        int i5;
        int i6 = -fontMetricsInt.top;
        if (i > i2) {
            if (i3 != 1) {
                if (i3 == 2) {
                    i4 = -fontMetricsInt.top;
                    i5 = i - i2;
                } else if (i3 != 3) {
                    return i6;
                } else {
                    i4 = -fontMetricsInt.top;
                    i5 = (i - i2) / 2;
                }
                return i4 + i5;
            }
            return -fontMetricsInt.top;
        }
        return i6;
    }

    public static void createTextBitmap(String str, String str2, int i, int i2, int i3, int i4) {
        createTextBitmapShadowStroke(str, str2, i, 1.0f, 1.0f, 1.0f, i2, i3, i4, false, 0.0f, 0.0f, 0.0f, false, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void createTextBitmapShadowStroke(String str, String str2, int i, float f, float f2, float f3, int i2, int i3, int i4, boolean z, float f4, float f5, float f6, boolean z2, float f7, float f8, float f9, float f10) {
        float f11;
        float f12;
        float f13;
        String[] strArr;
        String str3;
        int i5 = i2 & 15;
        int i6 = (i2 >> 4) & 15;
        String refactorString = refactorString(str);
        Paint newPaint = newPaint(str2, i, i5);
        newPaint.setARGB(255, (int) (f * 255.0d), (int) (f2 * 255.0d), (int) (f3 * 255.0d));
        TextProperty computeTextProperty = computeTextProperty(refactorString, i3, i4, newPaint);
        int i7 = i4 == 0 ? computeTextProperty.mTotalHeight : i4;
        float f14 = 0.0f;
        if (z) {
            newPaint.setShadowLayer(f6, f4, f5, -8553091);
            float abs = Math.abs(f4);
            f13 = Math.abs(f5);
            f11 = ((double) f4) < 0.0d ? abs : 0.0f;
            f14 = abs;
            f12 = ((double) f5) < 0.0d ? f13 : 0.0f;
        } else {
            f11 = 0.0f;
            f12 = 0.0f;
            f13 = 0.0f;
        }
        Bitmap createBitmap = Bitmap.createBitmap(computeTextProperty.mMaxWidth + ((int) f14), i7 + ((int) f13), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint.FontMetricsInt fontMetricsInt = newPaint.getFontMetricsInt();
        int computeY = computeY(fontMetricsInt, i4, computeTextProperty.mTotalHeight, i6);
        String[] strArr2 = computeTextProperty.mLines;
        int length = strArr2.length;
        int i8 = 0;
        while (i8 < length) {
            int i9 = length;
            canvas.drawText(strArr2[i8], computeX(str3, computeTextProperty.mMaxWidth, i5) + f11, computeY + f12, newPaint);
            computeY += computeTextProperty.mHeightPerLine;
            i8++;
            strArr2 = strArr2;
            createBitmap = createBitmap;
            length = i9;
        }
        Bitmap bitmap = createBitmap;
        if (z2) {
            Paint newPaint2 = newPaint(str2, i, i5);
            newPaint2.setStyle(Paint.Style.STROKE);
            newPaint2.setStrokeWidth(0.5f * f10);
            newPaint2.setARGB(255, ((int) f7) * 255, ((int) f8) * 255, ((int) f9) * 255);
            int computeY2 = computeY(fontMetricsInt, i4, computeTextProperty.mTotalHeight, i6);
            for (String str4 : computeTextProperty.mLines) {
                canvas.drawText(str4, computeX(str4, computeTextProperty.mMaxWidth, i5) + f11, computeY2 + f12, newPaint2);
                computeY2 += computeTextProperty.mHeightPerLine;
            }
        }
        initNativeObject(bitmap);
    }

    private static LinkedList<String> divideStringWithMaxWidth(String str, int i, Paint paint) {
        int length = str.length();
        LinkedList<String> linkedList = new LinkedList<>();
        int i2 = 1;
        int i3 = 0;
        while (i2 <= length) {
            int ceil = (int) Math.ceil(paint.measureText(str, i3, i2));
            if (ceil >= i) {
                int lastIndexOf = str.substring(0, i2).lastIndexOf(" ");
                if (lastIndexOf != -1 && lastIndexOf > i3) {
                    linkedList.add(str.substring(i3, lastIndexOf));
                    i2 = lastIndexOf + 1;
                } else if (ceil > i) {
                    linkedList.add(str.substring(i3, i2 - 1));
                    i2--;
                } else {
                    linkedList.add(str.substring(i3, i2));
                }
                while (i2 < length && str.charAt(i2) == ' ') {
                    i2++;
                }
                i3 = i2;
            }
            i2++;
        }
        if (i3 < length) {
            linkedList.add(str.substring(i3));
        }
        return linkedList;
    }

    private static int getFontSizeAccordingHeight(int i) {
        Paint paint = new Paint();
        Rect rect = new Rect();
        paint.setTypeface(Typeface.DEFAULT);
        boolean z = false;
        int i2 = 1;
        while (!z) {
            paint.setTextSize(i2);
            paint.getTextBounds("SghMNy", 0, 6, rect);
            i2++;
            if (i - rect.height() <= 2) {
                z = true;
            }
            Log.d("font size", "incr size:" + i2);
        }
        return i2;
    }

    private static byte[] getPixels(Bitmap bitmap) {
        if (bitmap != null) {
            byte[] bArr = new byte[bitmap.getWidth() * bitmap.getHeight() * 4];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.order(ByteOrder.nativeOrder());
            bitmap.copyPixelsToBuffer(wrap);
            return bArr;
        }
        return null;
    }

    private static String getStringWithEllipsis(String str, float f, float f2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setTextSize(f2);
        return TextUtils.ellipsize(str, textPaint, f, TextUtils.TruncateAt.END).toString();
    }

    private static void initNativeObject(Bitmap bitmap) {
        byte[] pixels = getPixels(bitmap);
        if (pixels == null) {
            return;
        }
        nativeInitBitmapDC(bitmap.getWidth(), bitmap.getHeight(), pixels);
    }

    private static native void nativeInitBitmapDC(int i, int i2, byte[] bArr);

    private static Paint newPaint(String str, int i, int i2) {
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setTextSize(i);
        paint.setAntiAlias(true);
        if (str.endsWith(".ttf")) {
            try {
                paint.setTypeface(Cocos2dxTypefaces.get(sContext, str));
            } catch (Exception unused) {
                Log.e("Cocos2dxBitmap", "error to create ttf type face: " + str);
                paint.setTypeface(Typeface.create(str, 0));
            }
        } else {
            paint.setTypeface(Typeface.create(str, 0));
        }
        if (i2 == 2) {
            paint.setTextAlign(Paint.Align.RIGHT);
        } else if (i2 != 3) {
            paint.setTextAlign(Paint.Align.LEFT);
        } else {
            paint.setTextAlign(Paint.Align.CENTER);
        }
        return paint;
    }

    private static String refactorString(String str) {
        if (str.compareTo("") == 0) {
            return " ";
        }
        StringBuilder sb = new StringBuilder(str);
        int i = 0;
        for (int indexOf = sb.indexOf("\n"); indexOf != -1; indexOf = sb.indexOf("\n", i)) {
            if (indexOf == 0 || sb.charAt(indexOf - 1) == '\n') {
                sb.insert(i, " ");
                i = indexOf + 2;
            } else {
                i = indexOf + 1;
            }
            if (i > sb.length() || indexOf == sb.length()) {
                break;
            }
        }
        return sb.toString();
    }

    public static void setContext(Context context) {
        sContext = context;
    }

    private static String[] splitString(String str, int i, int i2, Paint paint) {
        String[] split = str.split("\\n");
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int ceil = i2 / ((int) Math.ceil(fontMetricsInt.bottom - fontMetricsInt.top));
        int i3 = 0;
        if (i == 0) {
            if (i2 == 0 || split.length <= ceil) {
                return split;
            }
            LinkedList linkedList = new LinkedList();
            while (i3 < ceil) {
                linkedList.add(split[i3]);
                i3++;
            }
            String[] strArr = new String[linkedList.size()];
            linkedList.toArray(strArr);
            return strArr;
        }
        LinkedList linkedList2 = new LinkedList();
        int length = split.length;
        while (i3 < length) {
            String str2 = split[i3];
            if (((int) Math.ceil(paint.measureText(str2))) > i) {
                linkedList2.addAll(divideStringWithMaxWidth(str2, i, paint));
            } else {
                linkedList2.add(str2);
            }
            if (ceil > 0 && linkedList2.size() >= ceil) {
                break;
            }
            i3++;
        }
        if (ceil > 0 && linkedList2.size() > ceil) {
            while (linkedList2.size() > ceil) {
                linkedList2.removeLast();
            }
        }
        String[] strArr2 = new String[linkedList2.size()];
        linkedList2.toArray(strArr2);
        return strArr2;
    }
}
