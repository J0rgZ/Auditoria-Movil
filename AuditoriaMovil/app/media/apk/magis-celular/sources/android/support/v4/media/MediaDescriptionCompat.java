package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new a();
    public final String a;
    public final CharSequence b;
    public final CharSequence c;
    public final CharSequence d;
    public final Bitmap e;
    public final Uri f;
    public final Bundle g;
    public final Uri h;
    public MediaDescription i;

    /* loaded from: classes.dex */
    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            Parcelable.Creator creator;
            if (Build.VERSION.SDK_INT >= 21) {
                creator = MediaDescription.CREATOR;
                return MediaDescriptionCompat.a(creator.createFromParcel(parcel));
            }
            return new MediaDescriptionCompat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public MediaDescriptionCompat[] newArray(int i) {
            return new MediaDescriptionCompat[i];
        }
    }

    /* loaded from: classes.dex */
    public static final class b {
        public String a;
        public CharSequence b;
        public CharSequence c;
        public CharSequence d;
        public Bitmap e;
        public Uri f;
        public Bundle g;
        public Uri h;

        public MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }

        public b b(CharSequence charSequence) {
            this.d = charSequence;
            return this;
        }

        public b c(Bundle bundle) {
            this.g = bundle;
            return this;
        }

        public b d(Bitmap bitmap) {
            this.e = bitmap;
            return this;
        }

        public b e(Uri uri) {
            this.f = uri;
            return this;
        }

        public b f(String str) {
            this.a = str;
            return this;
        }

        public b g(Uri uri) {
            this.h = uri;
            return this;
        }

        public b h(CharSequence charSequence) {
            this.c = charSequence;
            return this;
        }

        public b i(CharSequence charSequence) {
            this.b = charSequence;
            return this;
        }
    }

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.a = str;
        this.b = charSequence;
        this.c = charSequence2;
        this.d = charSequence3;
        this.e = bitmap;
        this.f = uri;
        this.g = bundle;
        this.h = uri2;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.support.v4.media.MediaDescriptionCompat a(java.lang.Object r9) {
        /*
            r0 = 0
            if (r9 == 0) goto L85
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 21
            if (r1 < r2) goto L85
            android.support.v4.media.MediaDescriptionCompat$b r2 = new android.support.v4.media.MediaDescriptionCompat$b
            r2.<init>()
            android.media.MediaDescription r9 = android.support.v4.media.a.a(r9)
            java.lang.String r3 = android.support.v4.media.c.a(r9)
            r2.f(r3)
            java.lang.CharSequence r3 = android.support.v4.media.d.a(r9)
            r2.i(r3)
            java.lang.CharSequence r3 = android.support.v4.media.e.a(r9)
            r2.h(r3)
            java.lang.CharSequence r3 = android.support.v4.media.f.a(r9)
            r2.b(r3)
            android.graphics.Bitmap r3 = android.support.v4.media.g.a(r9)
            r2.d(r3)
            android.net.Uri r3 = android.support.v4.media.h.a(r9)
            r2.e(r3)
            android.os.Bundle r3 = android.support.v4.media.i.a(r9)
            if (r3 == 0) goto L46
            android.os.Bundle r3 = android.support.v4.media.session.MediaSessionCompat.s(r3)
        L46:
            java.lang.String r4 = "android.support.v4.media.description.MEDIA_URI"
            if (r3 == 0) goto L51
            android.os.Parcelable r5 = r3.getParcelable(r4)
            android.net.Uri r5 = (android.net.Uri) r5
            goto L52
        L51:
            r5 = r0
        L52:
            if (r5 == 0) goto L6a
            java.lang.String r6 = "android.support.v4.media.description.NULL_BUNDLE_FLAG"
            boolean r7 = r3.containsKey(r6)
            if (r7 == 0) goto L64
            int r7 = r3.size()
            r8 = 2
            if (r7 != r8) goto L64
            goto L6b
        L64:
            r3.remove(r4)
            r3.remove(r6)
        L6a:
            r0 = r3
        L6b:
            r2.c(r0)
            if (r5 == 0) goto L74
            r2.g(r5)
            goto L7f
        L74:
            r0 = 23
            if (r1 < r0) goto L7f
            android.net.Uri r0 = android.support.v4.media.j.a(r9)
            r2.g(r0)
        L7f:
            android.support.v4.media.MediaDescriptionCompat r0 = r2.a()
            r0.i = r9
        L85:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaDescriptionCompat.a(java.lang.Object):android.support.v4.media.MediaDescriptionCompat");
    }

    public Bitmap b() {
        return this.e;
    }

    public Uri c() {
        return this.f;
    }

    public Object d() {
        int i;
        MediaDescription build;
        MediaDescription mediaDescription = this.i;
        if (mediaDescription == null && (i = Build.VERSION.SDK_INT) >= 21) {
            MediaDescription.Builder builder = new MediaDescription.Builder();
            builder.setMediaId(this.a);
            builder.setTitle(this.b);
            builder.setSubtitle(this.c);
            builder.setDescription(this.d);
            builder.setIconBitmap(this.e);
            builder.setIconUri(this.f);
            Bundle bundle = this.g;
            if (i < 23 && this.h != null) {
                if (bundle == null) {
                    bundle = new Bundle();
                    bundle.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
                }
                bundle.putParcelable("android.support.v4.media.description.MEDIA_URI", this.h);
            }
            builder.setExtras(bundle);
            if (i >= 23) {
                builder.setMediaUri(this.h);
            }
            build = builder.build();
            this.i = build;
            return build;
        }
        return mediaDescription;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CharSequence e() {
        return this.c;
    }

    public CharSequence f() {
        return this.b;
    }

    public String toString() {
        return ((Object) this.b) + ", " + ((Object) this.c) + ", " + ((Object) this.d);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            android.support.v4.media.a.a(d()).writeToParcel(parcel, i);
            return;
        }
        parcel.writeString(this.a);
        TextUtils.writeToParcel(this.b, parcel, i);
        TextUtils.writeToParcel(this.c, parcel, i);
        TextUtils.writeToParcel(this.d, parcel, i);
        parcel.writeParcelable(this.e, i);
        parcel.writeParcelable(this.f, i);
        parcel.writeBundle(this.g);
        parcel.writeParcelable(this.h, i);
    }

    public MediaDescriptionCompat(Parcel parcel) {
        this.a = parcel.readString();
        this.b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        ClassLoader classLoader = MediaDescriptionCompat.class.getClassLoader();
        this.e = (Bitmap) parcel.readParcelable(classLoader);
        this.f = (Uri) parcel.readParcelable(classLoader);
        this.g = parcel.readBundle(classLoader);
        this.h = (Uri) parcel.readParcelable(classLoader);
    }
}
