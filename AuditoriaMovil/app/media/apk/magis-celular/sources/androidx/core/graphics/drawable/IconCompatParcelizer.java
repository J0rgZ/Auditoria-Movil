package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import s0.b;
/* loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(b bVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.a = bVar.p(iconCompat.a, 1);
        iconCompat.c = bVar.j(iconCompat.c, 2);
        iconCompat.d = bVar.r(iconCompat.d, 3);
        iconCompat.e = bVar.p(iconCompat.e, 4);
        iconCompat.f = bVar.p(iconCompat.f, 5);
        iconCompat.g = (ColorStateList) bVar.r(iconCompat.g, 6);
        iconCompat.i = bVar.t(iconCompat.i, 7);
        iconCompat.j = bVar.t(iconCompat.j, 8);
        iconCompat.s();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, b bVar) {
        bVar.x(true, true);
        iconCompat.t(bVar.f());
        int i = iconCompat.a;
        if (-1 != i) {
            bVar.F(i, 1);
        }
        byte[] bArr = iconCompat.c;
        if (bArr != null) {
            bVar.B(bArr, 2);
        }
        Parcelable parcelable = iconCompat.d;
        if (parcelable != null) {
            bVar.H(parcelable, 3);
        }
        int i2 = iconCompat.e;
        if (i2 != 0) {
            bVar.F(i2, 4);
        }
        int i3 = iconCompat.f;
        if (i3 != 0) {
            bVar.F(i3, 5);
        }
        ColorStateList colorStateList = iconCompat.g;
        if (colorStateList != null) {
            bVar.H(colorStateList, 6);
        }
        String str = iconCompat.i;
        if (str != null) {
            bVar.J(str, 7);
        }
        String str2 = iconCompat.j;
        if (str2 != null) {
            bVar.J(str2, 8);
        }
    }
}
