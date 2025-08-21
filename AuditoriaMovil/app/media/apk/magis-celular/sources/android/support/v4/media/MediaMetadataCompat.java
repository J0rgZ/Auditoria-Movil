package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR;
    public static final androidx.collection.a d;
    public static final String[] e;
    public static final String[] f;
    public static final String[] g;
    public final Bundle a;
    public MediaMetadata b;
    public MediaDescriptionCompat c;

    /* loaded from: classes.dex */
    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public MediaMetadataCompat createFromParcel(Parcel parcel) {
            return new MediaMetadataCompat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public MediaMetadataCompat[] newArray(int i) {
            return new MediaMetadataCompat[i];
        }
    }

    static {
        androidx.collection.a aVar = new androidx.collection.a();
        d = aVar;
        aVar.put("android.media.metadata.TITLE", 1);
        aVar.put("android.media.metadata.ARTIST", 1);
        aVar.put("android.media.metadata.DURATION", 0);
        aVar.put("android.media.metadata.ALBUM", 1);
        aVar.put("android.media.metadata.AUTHOR", 1);
        aVar.put("android.media.metadata.WRITER", 1);
        aVar.put("android.media.metadata.COMPOSER", 1);
        aVar.put("android.media.metadata.COMPILATION", 1);
        aVar.put("android.media.metadata.DATE", 1);
        aVar.put("android.media.metadata.YEAR", 0);
        aVar.put("android.media.metadata.GENRE", 1);
        aVar.put("android.media.metadata.TRACK_NUMBER", 0);
        aVar.put("android.media.metadata.NUM_TRACKS", 0);
        aVar.put("android.media.metadata.DISC_NUMBER", 0);
        aVar.put("android.media.metadata.ALBUM_ARTIST", 1);
        aVar.put("android.media.metadata.ART", 2);
        aVar.put("android.media.metadata.ART_URI", 1);
        aVar.put("android.media.metadata.ALBUM_ART", 2);
        aVar.put("android.media.metadata.ALBUM_ART_URI", 1);
        aVar.put("android.media.metadata.USER_RATING", 3);
        aVar.put("android.media.metadata.RATING", 3);
        aVar.put("android.media.metadata.DISPLAY_TITLE", 1);
        aVar.put("android.media.metadata.DISPLAY_SUBTITLE", 1);
        aVar.put("android.media.metadata.DISPLAY_DESCRIPTION", 1);
        aVar.put("android.media.metadata.DISPLAY_ICON", 2);
        aVar.put("android.media.metadata.DISPLAY_ICON_URI", 1);
        aVar.put("android.media.metadata.MEDIA_ID", 1);
        aVar.put("android.media.metadata.BT_FOLDER_TYPE", 0);
        aVar.put("android.media.metadata.MEDIA_URI", 1);
        aVar.put("android.media.metadata.ADVERTISEMENT", 0);
        aVar.put("android.media.metadata.DOWNLOAD_STATUS", 0);
        e = new String[]{"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
        f = new String[]{"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
        g = new String[]{"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
        CREATOR = new a();
    }

    public MediaMetadataCompat(Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        this.a = bundle2;
        MediaSessionCompat.c(bundle2);
    }

    public static MediaMetadataCompat b(Object obj) {
        if (obj != null && Build.VERSION.SDK_INT >= 21) {
            Parcel obtain = Parcel.obtain();
            u.a(obj).writeToParcel(obtain, 0);
            obtain.setDataPosition(0);
            MediaMetadataCompat createFromParcel = CREATOR.createFromParcel(obtain);
            obtain.recycle();
            createFromParcel.b = u.a(obj);
            return createFromParcel;
        }
        return null;
    }

    public boolean a(String str) {
        return this.a.containsKey(str);
    }

    public Bitmap c(String str) {
        try {
            return (Bitmap) this.a.getParcelable(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public Bundle d() {
        return new Bundle(this.a);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MediaDescriptionCompat e() {
        Uri uri;
        Bitmap bitmap;
        Uri uri2;
        MediaDescriptionCompat mediaDescriptionCompat = this.c;
        if (mediaDescriptionCompat != null) {
            return mediaDescriptionCompat;
        }
        String h = h("android.media.metadata.MEDIA_ID");
        CharSequence[] charSequenceArr = new CharSequence[3];
        CharSequence i = i("android.media.metadata.DISPLAY_TITLE");
        if (!TextUtils.isEmpty(i)) {
            charSequenceArr[0] = i;
            charSequenceArr[1] = i("android.media.metadata.DISPLAY_SUBTITLE");
            charSequenceArr[2] = i("android.media.metadata.DISPLAY_DESCRIPTION");
        } else {
            int i2 = 0;
            int i3 = 0;
            while (i2 < 3) {
                String[] strArr = e;
                if (i3 >= strArr.length) {
                    break;
                }
                int i4 = i3 + 1;
                CharSequence i5 = i(strArr[i3]);
                if (!TextUtils.isEmpty(i5)) {
                    charSequenceArr[i2] = i5;
                    i2++;
                }
                i3 = i4;
            }
        }
        int i6 = 0;
        while (true) {
            String[] strArr2 = f;
            uri = null;
            if (i6 < strArr2.length) {
                bitmap = c(strArr2[i6]);
                if (bitmap != null) {
                    break;
                }
                i6++;
            } else {
                bitmap = null;
                break;
            }
        }
        int i7 = 0;
        while (true) {
            String[] strArr3 = g;
            if (i7 < strArr3.length) {
                String h2 = h(strArr3[i7]);
                if (!TextUtils.isEmpty(h2)) {
                    uri2 = Uri.parse(h2);
                    break;
                }
                i7++;
            } else {
                uri2 = null;
                break;
            }
        }
        String h3 = h("android.media.metadata.MEDIA_URI");
        if (!TextUtils.isEmpty(h3)) {
            uri = Uri.parse(h3);
        }
        MediaDescriptionCompat.b bVar = new MediaDescriptionCompat.b();
        bVar.f(h);
        bVar.i(charSequenceArr[0]);
        bVar.h(charSequenceArr[1]);
        bVar.b(charSequenceArr[2]);
        bVar.d(bitmap);
        bVar.e(uri2);
        bVar.g(uri);
        Bundle bundle = new Bundle();
        if (this.a.containsKey("android.media.metadata.BT_FOLDER_TYPE")) {
            bundle.putLong("android.media.extra.BT_FOLDER_TYPE", f("android.media.metadata.BT_FOLDER_TYPE"));
        }
        if (this.a.containsKey("android.media.metadata.DOWNLOAD_STATUS")) {
            bundle.putLong("android.media.extra.DOWNLOAD_STATUS", f("android.media.metadata.DOWNLOAD_STATUS"));
        }
        if (!bundle.isEmpty()) {
            bVar.c(bundle);
        }
        MediaDescriptionCompat a2 = bVar.a();
        this.c = a2;
        return a2;
    }

    public long f(String str) {
        return this.a.getLong(str, 0L);
    }

    public Object g() {
        Parcelable.Creator creator;
        if (this.b == null && Build.VERSION.SDK_INT >= 21) {
            Parcel obtain = Parcel.obtain();
            writeToParcel(obtain, 0);
            obtain.setDataPosition(0);
            creator = MediaMetadata.CREATOR;
            this.b = u.a(creator.createFromParcel(obtain));
            obtain.recycle();
        }
        return this.b;
    }

    public String h(String str) {
        CharSequence charSequence = this.a.getCharSequence(str);
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public CharSequence i(String str) {
        return this.a.getCharSequence(str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.a);
    }

    /* loaded from: classes.dex */
    public static final class b {
        public final Bundle a;

        public b() {
            this.a = new Bundle();
        }

        public MediaMetadataCompat a() {
            return new MediaMetadataCompat(this.a);
        }

        public b b(String str, Bitmap bitmap) {
            androidx.collection.a aVar = MediaMetadataCompat.d;
            if (aVar.containsKey(str) && ((Integer) aVar.get(str)).intValue() != 2) {
                throw new IllegalArgumentException("The " + str + " key cannot be used to put a Bitmap");
            }
            this.a.putParcelable(str, bitmap);
            return this;
        }

        public b c(String str, long j) {
            androidx.collection.a aVar = MediaMetadataCompat.d;
            if (aVar.containsKey(str) && ((Integer) aVar.get(str)).intValue() != 0) {
                throw new IllegalArgumentException("The " + str + " key cannot be used to put a long");
            }
            this.a.putLong(str, j);
            return this;
        }

        public b d(String str, String str2) {
            androidx.collection.a aVar = MediaMetadataCompat.d;
            if (aVar.containsKey(str) && ((Integer) aVar.get(str)).intValue() != 1) {
                throw new IllegalArgumentException("The " + str + " key cannot be used to put a String");
            }
            this.a.putCharSequence(str, str2);
            return this;
        }

        public final Bitmap e(Bitmap bitmap, int i) {
            float f = i;
            float min = Math.min(f / bitmap.getWidth(), f / bitmap.getHeight());
            return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * min), (int) (bitmap.getHeight() * min), true);
        }

        public b(MediaMetadataCompat mediaMetadataCompat) {
            Bundle bundle = new Bundle(mediaMetadataCompat.a);
            this.a = bundle;
            MediaSessionCompat.c(bundle);
        }

        public b(MediaMetadataCompat mediaMetadataCompat, int i) {
            this(mediaMetadataCompat);
            for (String str : this.a.keySet()) {
                Object obj = this.a.get(str);
                if (obj instanceof Bitmap) {
                    Bitmap bitmap = (Bitmap) obj;
                    if (bitmap.getHeight() > i || bitmap.getWidth() > i) {
                        b(str, e(bitmap, i));
                    }
                }
            }
        }
    }

    public MediaMetadataCompat(Parcel parcel) {
        this.a = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
    }
}
