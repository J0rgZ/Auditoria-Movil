package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.fragment.app.p;
import androidx.lifecycle.c;
import java.util.ArrayList;
/* loaded from: classes.dex */
public final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();
    public final int[] a;
    public final ArrayList b;
    public final int[] c;
    public final int[] d;
    public final int e;
    public final int f;
    public final String g;
    public final int h;
    public final int i;
    public final CharSequence j;
    public final int k;
    public final CharSequence l;
    public final ArrayList m;
    public final ArrayList n;
    public final boolean o;

    /* loaded from: classes.dex */
    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public b[] newArray(int i) {
            return new b[i];
        }
    }

    public b(androidx.fragment.app.a aVar) {
        int size = aVar.a.size();
        this.a = new int[size * 5];
        if (aVar.h) {
            this.b = new ArrayList(size);
            this.c = new int[size];
            this.d = new int[size];
            int i = 0;
            int i2 = 0;
            while (i < size) {
                p.a aVar2 = (p.a) aVar.a.get(i);
                int i3 = i2 + 1;
                this.a[i2] = aVar2.a;
                ArrayList arrayList = this.b;
                Fragment fragment = aVar2.b;
                arrayList.add(fragment != null ? fragment.mWho : null);
                int[] iArr = this.a;
                int i4 = i3 + 1;
                iArr[i3] = aVar2.c;
                int i5 = i4 + 1;
                iArr[i4] = aVar2.d;
                int i6 = i5 + 1;
                iArr[i5] = aVar2.e;
                iArr[i6] = aVar2.f;
                this.c[i] = aVar2.g.ordinal();
                this.d[i] = aVar2.h.ordinal();
                i++;
                i2 = i6 + 1;
            }
            this.e = aVar.f;
            this.f = aVar.g;
            this.g = aVar.j;
            this.h = aVar.u;
            this.i = aVar.k;
            this.j = aVar.l;
            this.k = aVar.m;
            this.l = aVar.n;
            this.m = aVar.o;
            this.n = aVar.p;
            this.o = aVar.q;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public androidx.fragment.app.a a(j jVar) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(jVar);
        int i = 0;
        int i2 = 0;
        while (i < this.a.length) {
            p.a aVar2 = new p.a();
            int i3 = i + 1;
            aVar2.a = this.a[i];
            if (j.H) {
                StringBuilder sb = new StringBuilder();
                sb.append("Instantiate ");
                sb.append(aVar);
                sb.append(" op #");
                sb.append(i2);
                sb.append(" base fragment #");
                sb.append(this.a[i3]);
            }
            String str = (String) this.b.get(i2);
            if (str != null) {
                aVar2.b = (Fragment) jVar.g.get(str);
            } else {
                aVar2.b = null;
            }
            aVar2.g = c.b.values()[this.c[i2]];
            aVar2.h = c.b.values()[this.d[i2]];
            int[] iArr = this.a;
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            aVar2.c = i5;
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            aVar2.d = i7;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            aVar2.e = i9;
            int i10 = iArr[i8];
            aVar2.f = i10;
            aVar.b = i5;
            aVar.c = i7;
            aVar.d = i9;
            aVar.e = i10;
            aVar.e(aVar2);
            i2++;
            i = i8 + 1;
        }
        aVar.f = this.e;
        aVar.g = this.f;
        aVar.j = this.g;
        aVar.u = this.h;
        aVar.h = true;
        aVar.k = this.i;
        aVar.l = this.j;
        aVar.m = this.k;
        aVar.n = this.l;
        aVar.o = this.m;
        aVar.p = this.n;
        aVar.q = this.o;
        aVar.t(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.a);
        parcel.writeStringList(this.b);
        parcel.writeIntArray(this.c);
        parcel.writeIntArray(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        TextUtils.writeToParcel(this.j, parcel, 0);
        parcel.writeInt(this.k);
        TextUtils.writeToParcel(this.l, parcel, 0);
        parcel.writeStringList(this.m);
        parcel.writeStringList(this.n);
        parcel.writeInt(this.o ? 1 : 0);
    }

    public b(Parcel parcel) {
        this.a = parcel.createIntArray();
        this.b = parcel.createStringArrayList();
        this.c = parcel.createIntArray();
        this.d = parcel.createIntArray();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readString();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.k = parcel.readInt();
        this.l = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.m = parcel.createStringArrayList();
        this.n = parcel.createStringArrayList();
        this.o = parcel.readInt() != 0;
    }
}
