package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
/* loaded from: classes.dex */
public final class k implements Parcelable {
    public static final Parcelable.Creator<k> CREATOR = new a();
    public ArrayList a;
    public ArrayList b;
    public b[] c;
    public String d;
    public int e;

    /* loaded from: classes.dex */
    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public k createFromParcel(Parcel parcel) {
            return new k(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public k[] newArray(int i) {
            return new k[i];
        }
    }

    public k() {
        this.d = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.a);
        parcel.writeStringList(this.b);
        parcel.writeTypedArray(this.c, i);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
    }

    public k(Parcel parcel) {
        this.d = null;
        this.a = parcel.createTypedArrayList(n.CREATOR);
        this.b = parcel.createStringArrayList();
        this.c = (b[]) parcel.createTypedArray(b.CREATOR);
        this.d = parcel.readString();
        this.e = parcel.readInt();
    }
}
