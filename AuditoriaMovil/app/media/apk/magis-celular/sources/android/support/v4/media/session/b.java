package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.a;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.List;
/* loaded from: classes.dex */
public interface b extends IInterface {

    /* loaded from: classes.dex */
    public static abstract class a extends Binder implements b {

        /* renamed from: android.support.v4.media.session.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0004a implements b {
            public static b b;
            public IBinder a;

            public C0004a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // android.support.v4.media.session.b
            public void T() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.a.transact(13, obtain, obtain2, 0) && a.j0() != null) {
                        a.j0().T();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }

            @Override // android.support.v4.media.session.b
            public PlaybackStateCompat e() {
                PlaybackStateCompat playbackStateCompat;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.a.transact(28, obtain, obtain2, 0) && a.j0() != null) {
                        return a.j0().e();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        playbackStateCompat = PlaybackStateCompat.CREATOR.createFromParcel(obtain2);
                    } else {
                        playbackStateCompat = null;
                    }
                    return playbackStateCompat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.b
            public void g(android.support.v4.media.session.a aVar) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (aVar != null) {
                        iBinder = aVar.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (!this.a.transact(3, obtain, obtain2, 0) && a.j0() != null) {
                        a.j0().g(aVar);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.b
            public MediaMetadataCompat getMetadata() {
                MediaMetadataCompat mediaMetadataCompat;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.a.transact(27, obtain, obtain2, 0) && a.j0() != null) {
                        return a.j0().getMetadata();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        mediaMetadataCompat = MediaMetadataCompat.CREATOR.createFromParcel(obtain2);
                    } else {
                        mediaMetadataCompat = null;
                    }
                    return mediaMetadataCompat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.b
            public PendingIntent o() {
                PendingIntent pendingIntent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.a.transact(8, obtain, obtain2, 0) && a.j0() != null) {
                        return a.j0().o();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(obtain2);
                    } else {
                        pendingIntent = null;
                    }
                    return pendingIntent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.b
            public void pause() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.a.transact(18, obtain, obtain2, 0) && a.j0() != null) {
                        a.j0().pause();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.b
            public void stop() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.a.transact(19, obtain, obtain2, 0) && a.j0() != null) {
                        a.j0().stop();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.b
            public void y(android.support.v4.media.session.a aVar) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (aVar != null) {
                        iBinder = aVar.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (!this.a.transact(4, obtain, obtain2, 0) && a.j0() != null) {
                        a.j0().y(aVar);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "android.support.v4.media.session.IMediaSession");
        }

        public static b i0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            if (queryLocalInterface != null && (queryLocalInterface instanceof b)) {
                return (b) queryLocalInterface;
            }
            return new C0004a(iBinder);
        }

        public static b j0() {
            return C0004a.b;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            Bundle bundle;
            Uri uri;
            Uri uri2;
            RatingCompat ratingCompat;
            if (i != 1598968902) {
                boolean z = false;
                MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper = null;
                Bundle bundle2 = null;
                MediaDescriptionCompat mediaDescriptionCompat = null;
                MediaDescriptionCompat mediaDescriptionCompat2 = null;
                MediaDescriptionCompat mediaDescriptionCompat3 = null;
                Bundle bundle3 = null;
                Bundle bundle4 = null;
                Bundle bundle5 = null;
                Bundle bundle6 = null;
                RatingCompat ratingCompat2 = null;
                Bundle bundle7 = null;
                Bundle bundle8 = null;
                Bundle bundle9 = null;
                KeyEvent keyEvent = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        String readString = parcel.readString();
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        } else {
                            bundle = null;
                        }
                        if (parcel.readInt() != 0) {
                            resultReceiverWrapper = MediaSessionCompat.ResultReceiverWrapper.CREATOR.createFromParcel(parcel);
                        }
                        U(readString, bundle, resultReceiverWrapper);
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        if (parcel.readInt() != 0) {
                            keyEvent = (KeyEvent) KeyEvent.CREATOR.createFromParcel(parcel);
                        }
                        boolean F = F(keyEvent);
                        parcel2.writeNoException();
                        parcel2.writeInt(F ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        g(a.AbstractBinderC0002a.i0(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        y(a.AbstractBinderC0002a.i0(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        boolean m = m();
                        parcel2.writeNoException();
                        parcel2.writeInt(m ? 1 : 0);
                        return true;
                    case 6:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        String e0 = e0();
                        parcel2.writeNoException();
                        parcel2.writeString(e0);
                        return true;
                    case 7:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        String tag = getTag();
                        parcel2.writeNoException();
                        parcel2.writeString(tag);
                        return true;
                    case 8:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        PendingIntent o = o();
                        parcel2.writeNoException();
                        if (o != null) {
                            parcel2.writeInt(1);
                            o.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 9:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        long Y = Y();
                        parcel2.writeNoException();
                        parcel2.writeLong(Y);
                        return true;
                    case 10:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        ParcelableVolumeInfo c0 = c0();
                        parcel2.writeNoException();
                        if (c0 != null) {
                            parcel2.writeInt(1);
                            c0.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 11:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        I(parcel.readInt(), parcel.readInt(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 12:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        j(parcel.readInt(), parcel.readInt(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 13:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        T();
                        parcel2.writeNoException();
                        return true;
                    case 14:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        String readString2 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            bundle9 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        z(readString2, bundle9);
                        parcel2.writeNoException();
                        return true;
                    case 15:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        String readString3 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            bundle8 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        A(readString3, bundle8);
                        parcel2.writeNoException();
                        return true;
                    case 16:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        if (parcel.readInt() != 0) {
                            uri = (Uri) Uri.CREATOR.createFromParcel(parcel);
                        } else {
                            uri = null;
                        }
                        if (parcel.readInt() != 0) {
                            bundle7 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        C(uri, bundle7);
                        parcel2.writeNoException();
                        return true;
                    case 17:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        a0(parcel.readLong());
                        parcel2.writeNoException();
                        return true;
                    case 18:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        pause();
                        parcel2.writeNoException();
                        return true;
                    case 19:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        stop();
                        parcel2.writeNoException();
                        return true;
                    case 20:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        next();
                        parcel2.writeNoException();
                        return true;
                    case 21:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        previous();
                        parcel2.writeNoException();
                        return true;
                    case 22:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        B();
                        parcel2.writeNoException();
                        return true;
                    case 23:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        X();
                        parcel2.writeNoException();
                        return true;
                    case 24:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        seekTo(parcel.readLong());
                        parcel2.writeNoException();
                        return true;
                    case 25:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        if (parcel.readInt() != 0) {
                            ratingCompat2 = RatingCompat.CREATOR.createFromParcel(parcel);
                        }
                        i(ratingCompat2);
                        parcel2.writeNoException();
                        return true;
                    case 26:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        String readString4 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            bundle6 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        f(readString4, bundle6);
                        parcel2.writeNoException();
                        return true;
                    case 27:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        MediaMetadataCompat metadata = getMetadata();
                        parcel2.writeNoException();
                        if (metadata != null) {
                            parcel2.writeInt(1);
                            metadata.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 28:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        PlaybackStateCompat e = e();
                        parcel2.writeNoException();
                        if (e != null) {
                            parcel2.writeInt(1);
                            e.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 29:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        List V = V();
                        parcel2.writeNoException();
                        parcel2.writeTypedList(V);
                        return true;
                    case 30:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        CharSequence v = v();
                        parcel2.writeNoException();
                        if (v != null) {
                            parcel2.writeInt(1);
                            TextUtils.writeToParcel(v, parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 31:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        Bundle extras = getExtras();
                        parcel2.writeNoException();
                        if (extras != null) {
                            parcel2.writeInt(1);
                            extras.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 32:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        int p = p();
                        parcel2.writeNoException();
                        parcel2.writeInt(p);
                        return true;
                    case 33:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        r();
                        parcel2.writeNoException();
                        return true;
                    case 34:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        String readString5 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            bundle5 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        w(readString5, bundle5);
                        parcel2.writeNoException();
                        return true;
                    case 35:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        String readString6 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            bundle4 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        q(readString6, bundle4);
                        parcel2.writeNoException();
                        return true;
                    case 36:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        if (parcel.readInt() != 0) {
                            uri2 = (Uri) Uri.CREATOR.createFromParcel(parcel);
                        } else {
                            uri2 = null;
                        }
                        if (parcel.readInt() != 0) {
                            bundle3 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        k(uri2, bundle3);
                        parcel2.writeNoException();
                        return true;
                    case 37:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        int Z = Z();
                        parcel2.writeNoException();
                        parcel2.writeInt(Z);
                        return true;
                    case 38:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        boolean h = h();
                        parcel2.writeNoException();
                        parcel2.writeInt(h ? 1 : 0);
                        return true;
                    case 39:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        W(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 40:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        b0(z);
                        parcel2.writeNoException();
                        return true;
                    case 41:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        if (parcel.readInt() != 0) {
                            mediaDescriptionCompat3 = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
                        }
                        n(mediaDescriptionCompat3);
                        parcel2.writeNoException();
                        return true;
                    case 42:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        if (parcel.readInt() != 0) {
                            mediaDescriptionCompat2 = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
                        }
                        L(mediaDescriptionCompat2, parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 43:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        if (parcel.readInt() != 0) {
                            mediaDescriptionCompat = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
                        }
                        l(mediaDescriptionCompat);
                        parcel2.writeNoException();
                        return true;
                    case 44:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        Q(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 45:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        boolean R = R();
                        parcel2.writeNoException();
                        parcel2.writeInt(R ? 1 : 0);
                        return true;
                    case 46:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        N(z);
                        parcel2.writeNoException();
                        return true;
                    case 47:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        int P = P();
                        parcel2.writeNoException();
                        parcel2.writeInt(P);
                        return true;
                    case 48:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        d0(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 49:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        E(parcel.readFloat());
                        parcel2.writeNoException();
                        return true;
                    case 50:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        Bundle x = x();
                        parcel2.writeNoException();
                        if (x != null) {
                            parcel2.writeInt(1);
                            x.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 51:
                        parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                        if (parcel.readInt() != 0) {
                            ratingCompat = RatingCompat.CREATOR.createFromParcel(parcel);
                        } else {
                            ratingCompat = null;
                        }
                        if (parcel.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        J(ratingCompat, bundle2);
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString("android.support.v4.media.session.IMediaSession");
            return true;
        }
    }

    void A(String str, Bundle bundle);

    void B();

    void C(Uri uri, Bundle bundle);

    void E(float f);

    boolean F(KeyEvent keyEvent);

    void I(int i, int i2, String str);

    void J(RatingCompat ratingCompat, Bundle bundle);

    void L(MediaDescriptionCompat mediaDescriptionCompat, int i);

    void N(boolean z);

    int P();

    void Q(int i);

    boolean R();

    void T();

    void U(String str, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper);

    List V();

    void W(int i);

    void X();

    long Y();

    int Z();

    void a0(long j);

    void b0(boolean z);

    ParcelableVolumeInfo c0();

    void d0(int i);

    PlaybackStateCompat e();

    String e0();

    void f(String str, Bundle bundle);

    void g(android.support.v4.media.session.a aVar);

    Bundle getExtras();

    MediaMetadataCompat getMetadata();

    String getTag();

    boolean h();

    void i(RatingCompat ratingCompat);

    void j(int i, int i2, String str);

    void k(Uri uri, Bundle bundle);

    void l(MediaDescriptionCompat mediaDescriptionCompat);

    boolean m();

    void n(MediaDescriptionCompat mediaDescriptionCompat);

    void next();

    PendingIntent o();

    int p();

    void pause();

    void previous();

    void q(String str, Bundle bundle);

    void r();

    void seekTo(long j);

    void stop();

    CharSequence v();

    void w(String str, Bundle bundle);

    Bundle x();

    void y(android.support.v4.media.session.a aVar);

    void z(String str, Bundle bundle);
}
