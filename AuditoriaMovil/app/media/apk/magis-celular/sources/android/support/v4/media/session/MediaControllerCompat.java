package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.a;
import android.support.v4.media.session.b;
import android.util.Log;
import androidx.media.AudioAttributesCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public final class MediaControllerCompat {
    public final b a;
    public final MediaSessionCompat.Token b;
    public final ConcurrentHashMap c = new ConcurrentHashMap();

    /* loaded from: classes.dex */
    public static class MediaControllerImplApi21 implements b {
        public final MediaController a;
        public final Object b = new Object();
        public final List c = new ArrayList();
        public HashMap d = new HashMap();
        public final MediaSessionCompat.Token e;

        /* loaded from: classes.dex */
        public static class ExtraBinderRequestResultReceiver extends ResultReceiver {
            public WeakReference a;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21) {
                super(null);
                this.a = new WeakReference(mediaControllerImplApi21);
            }

            @Override // android.os.ResultReceiver
            public void onReceiveResult(int i, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = (MediaControllerImplApi21) this.a.get();
                if (mediaControllerImplApi21 != null && bundle != null) {
                    synchronized (mediaControllerImplApi21.b) {
                        mediaControllerImplApi21.e.d(b.a.i0(i.n.a(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                        mediaControllerImplApi21.e.e(s0.a.b(bundle, "android.support.v4.media.session.SESSION_TOKEN2"));
                        mediaControllerImplApi21.f();
                    }
                }
            }
        }

        /* loaded from: classes.dex */
        public static class a extends a.c {
            public a(a aVar) {
                super(aVar);
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void H(CharSequence charSequence) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void K() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void M(MediaMetadataCompat mediaMetadataCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void h0(ParcelableVolumeInfo parcelableVolumeInfo) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void t(Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void u(List list) {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) {
            this.e = token;
            this.a = new MediaController(context, n.a(token.c()));
            if (token.a() == null) {
                g();
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public final void a(a aVar) {
            this.a.unregisterCallback(aVar.a);
            synchronized (this.b) {
                if (this.e.a() != null) {
                    try {
                        a aVar2 = (a) this.d.remove(aVar);
                        if (aVar2 != null) {
                            aVar.c = null;
                            this.e.a().y(aVar2);
                        }
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e);
                    }
                } else {
                    this.c.remove(aVar);
                }
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PendingIntent b() {
            PendingIntent sessionActivity;
            sessionActivity = this.a.getSessionActivity();
            return sessionActivity;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public e c() {
            MediaController.TransportControls transportControls;
            transportControls = this.a.getTransportControls();
            return new f(transportControls);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public final void d(a aVar, Handler handler) {
            this.a.registerCallback(aVar.a, handler);
            synchronized (this.b) {
                if (this.e.a() != null) {
                    a aVar2 = new a(aVar);
                    this.d.put(aVar, aVar2);
                    aVar.c = aVar2;
                    try {
                        this.e.a().g(aVar2);
                        aVar.m(13, null, null);
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                    }
                } else {
                    aVar.c = null;
                    this.c.add(aVar);
                }
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PlaybackStateCompat e() {
            PlaybackState playbackState;
            if (this.e.a() != null) {
                try {
                    return this.e.a().e();
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
                }
            }
            playbackState = this.a.getPlaybackState();
            if (playbackState != null) {
                return PlaybackStateCompat.a(playbackState);
            }
            return null;
        }

        public void f() {
            if (this.e.a() == null) {
                return;
            }
            for (a aVar : this.c) {
                a aVar2 = new a(aVar);
                this.d.put(aVar, aVar2);
                aVar.c = aVar2;
                try {
                    this.e.a().g(aVar2);
                    aVar.m(13, null, null);
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                }
            }
            this.c.clear();
        }

        public final void g() {
            h("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public MediaMetadataCompat getMetadata() {
            MediaMetadata metadata;
            metadata = this.a.getMetadata();
            if (metadata != null) {
                return MediaMetadataCompat.b(metadata);
            }
            return null;
        }

        public void h(String str, Bundle bundle, ResultReceiver resultReceiver) {
            this.a.sendCommand(str, bundle, resultReceiver);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class a implements IBinder.DeathRecipient {
        public final MediaController.Callback a;
        public b b;
        public android.support.v4.media.session.a c;

        /* renamed from: android.support.v4.media.session.MediaControllerCompat$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0000a extends MediaController.Callback {
            public final WeakReference a;

            public C0000a(a aVar) {
                this.a = new WeakReference(aVar);
            }

            @Override // android.media.session.MediaController.Callback
            public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
                int playbackType;
                AudioAttributes audioAttributes;
                int volumeControl;
                int maxVolume;
                int currentVolume;
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    playbackType = playbackInfo.getPlaybackType();
                    audioAttributes = playbackInfo.getAudioAttributes();
                    AudioAttributesCompat c = AudioAttributesCompat.c(audioAttributes);
                    volumeControl = playbackInfo.getVolumeControl();
                    maxVolume = playbackInfo.getMaxVolume();
                    currentVolume = playbackInfo.getCurrentVolume();
                    aVar.a(new d(playbackType, c, volumeControl, maxVolume, currentVolume));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onExtrasChanged(Bundle bundle) {
                MediaSessionCompat.c(bundle);
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.c(bundle);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onMetadataChanged(MediaMetadata mediaMetadata) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.d(MediaMetadataCompat.b(mediaMetadata));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                a aVar = (a) this.a.get();
                if (aVar != null && aVar.c == null) {
                    aVar.e(PlaybackStateCompat.a(playbackState));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueChanged(List list) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.f(MediaSessionCompat.QueueItem.b(list));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueTitleChanged(CharSequence charSequence) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.g(charSequence);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionDestroyed() {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.i();
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionEvent(String str, Bundle bundle) {
                MediaSessionCompat.c(bundle);
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    if (aVar.c == null || Build.VERSION.SDK_INT >= 23) {
                        aVar.j(str, bundle);
                    }
                }
            }
        }

        /* loaded from: classes.dex */
        public class b extends Handler {
            public boolean a;

            public b(Looper looper) {
                super(looper);
                this.a = false;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (!this.a) {
                    return;
                }
                switch (message.what) {
                    case 1:
                        Bundle data = message.getData();
                        MediaSessionCompat.c(data);
                        a.this.j((String) message.obj, data);
                        return;
                    case 2:
                        a.this.e((PlaybackStateCompat) message.obj);
                        return;
                    case 3:
                        a.this.d((MediaMetadataCompat) message.obj);
                        return;
                    case 4:
                        a.this.a((d) message.obj);
                        return;
                    case 5:
                        a.this.f((List) message.obj);
                        return;
                    case 6:
                        a.this.g((CharSequence) message.obj);
                        return;
                    case 7:
                        Bundle bundle = (Bundle) message.obj;
                        MediaSessionCompat.c(bundle);
                        a.this.c(bundle);
                        return;
                    case 8:
                        a.this.i();
                        return;
                    case 9:
                        a.this.h(((Integer) message.obj).intValue());
                        return;
                    case 10:
                    default:
                        return;
                    case 11:
                        a.this.b(((Boolean) message.obj).booleanValue());
                        return;
                    case 12:
                        a.this.l(((Integer) message.obj).intValue());
                        return;
                    case 13:
                        a.this.k();
                        return;
                }
            }
        }

        /* loaded from: classes.dex */
        public static class c extends a.AbstractBinderC0002a {
            public final WeakReference a;

            public c(a aVar) {
                this.a = new WeakReference(aVar);
            }

            @Override // android.support.v4.media.session.a
            public void D(boolean z) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(11, Boolean.valueOf(z), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void G(boolean z) {
            }

            public void H(CharSequence charSequence) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(6, charSequence, null);
                }
            }

            public void K() {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(8, null, null);
                }
            }

            public void M(MediaMetadataCompat mediaMetadataCompat) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(3, mediaMetadataCompat, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void O(int i) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(9, Integer.valueOf(i), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void S(int i) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(12, Integer.valueOf(i), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void f0(PlaybackStateCompat playbackStateCompat) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(2, playbackStateCompat, null);
                }
            }

            public void h0(ParcelableVolumeInfo parcelableVolumeInfo) {
                d dVar;
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    if (parcelableVolumeInfo != null) {
                        dVar = new d(parcelableVolumeInfo.a, parcelableVolumeInfo.b, parcelableVolumeInfo.c, parcelableVolumeInfo.d, parcelableVolumeInfo.e);
                    } else {
                        dVar = null;
                    }
                    aVar.m(4, dVar, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void onEvent(String str, Bundle bundle) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(1, str, bundle);
                }
            }

            @Override // android.support.v4.media.session.a
            public void s() {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(13, null, null);
                }
            }

            public void t(Bundle bundle) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(7, bundle, null);
                }
            }

            public void u(List list) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(5, list, null);
                }
            }
        }

        public a() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.a = new C0000a(this);
                return;
            }
            this.a = null;
            this.c = new c(this);
        }

        public void a(d dVar) {
        }

        public void b(boolean z) {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            m(8, null, null);
        }

        public void c(Bundle bundle) {
        }

        public abstract void d(MediaMetadataCompat mediaMetadataCompat);

        public void e(PlaybackStateCompat playbackStateCompat) {
        }

        public void f(List list) {
        }

        public void g(CharSequence charSequence) {
        }

        public void h(int i) {
        }

        public abstract void i();

        public void j(String str, Bundle bundle) {
        }

        public void k() {
        }

        public void l(int i) {
        }

        public void m(int i, Object obj, Bundle bundle) {
            b bVar = this.b;
            if (bVar != null) {
                Message obtainMessage = bVar.obtainMessage(i, obj);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            }
        }

        public void n(Handler handler) {
            if (handler == null) {
                b bVar = this.b;
                if (bVar != null) {
                    bVar.a = false;
                    bVar.removeCallbacksAndMessages(null);
                    this.b = null;
                    return;
                }
                return;
            }
            b bVar2 = new b(handler.getLooper());
            this.b = bVar2;
            bVar2.a = true;
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(a aVar);

        PendingIntent b();

        e c();

        void d(a aVar, Handler handler);

        PlaybackStateCompat e();

        MediaMetadataCompat getMetadata();
    }

    /* loaded from: classes.dex */
    public static class c implements b {
        public android.support.v4.media.session.b a;
        public e b;

        public c(MediaSessionCompat.Token token) {
            this.a = b.a.i0((IBinder) token.c());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public void a(a aVar) {
            if (aVar != null) {
                try {
                    this.a.y(aVar.c);
                    this.a.asBinder().unlinkToDeath(aVar, 0);
                    return;
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e);
                    return;
                }
            }
            throw new IllegalArgumentException("callback may not be null.");
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PendingIntent b() {
            try {
                return this.a.o();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getSessionActivity.", e);
                return null;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public e c() {
            if (this.b == null) {
                this.b = new g(this.a);
            }
            return this.b;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public void d(a aVar, Handler handler) {
            if (aVar != null) {
                try {
                    this.a.asBinder().linkToDeath(aVar, 0);
                    this.a.g(aVar.c);
                    aVar.m(13, null, null);
                    return;
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                    aVar.m(8, null, null);
                    return;
                }
            }
            throw new IllegalArgumentException("callback may not be null.");
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PlaybackStateCompat e() {
            try {
                return this.a.e();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
                return null;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public MediaMetadataCompat getMetadata() {
            try {
                return this.a.getMetadata();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getMetadata.", e);
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class d {
        public final int a;
        public final AudioAttributesCompat b;
        public final int c;
        public final int d;
        public final int e;

        public d(int i, int i2, int i3, int i4, int i5) {
            this(i, new AudioAttributesCompat.a().b(i2).a(), i3, i4, i5);
        }

        public d(int i, AudioAttributesCompat audioAttributesCompat, int i2, int i3, int i4) {
            this.a = i;
            this.b = audioAttributesCompat;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class e {
        public abstract void a();

        public abstract void b();

        public abstract void c();
    }

    /* loaded from: classes.dex */
    public static class f extends e {
        public final MediaController.TransportControls a;

        public f(MediaController.TransportControls transportControls) {
            this.a = transportControls;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void a() {
            this.a.pause();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void b() {
            this.a.play();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void c() {
            this.a.stop();
        }
    }

    /* loaded from: classes.dex */
    public static class g extends e {
        public android.support.v4.media.session.b a;

        public g(android.support.v4.media.session.b bVar) {
            this.a = bVar;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void a() {
            try {
                this.a.pause();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in pause.", e);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void b() {
            try {
                this.a.T();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in play.", e);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void c() {
            try {
                this.a.stop();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in stop.", e);
            }
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat mediaSessionCompat) {
        if (mediaSessionCompat != null) {
            MediaSessionCompat.Token f2 = mediaSessionCompat.f();
            this.b = f2;
            if (Build.VERSION.SDK_INT >= 21) {
                this.a = new MediaControllerImplApi21(context, f2);
                return;
            } else {
                this.a = new c(f2);
                return;
            }
        }
        throw new IllegalArgumentException("session must not be null");
    }

    public MediaMetadataCompat a() {
        return this.a.getMetadata();
    }

    public PlaybackStateCompat b() {
        return this.a.e();
    }

    public PendingIntent c() {
        return this.a.b();
    }

    public e d() {
        return this.a.c();
    }

    public void e(a aVar) {
        f(aVar, null);
    }

    public void f(a aVar, Handler handler) {
        if (aVar != null) {
            if (this.c.putIfAbsent(aVar, Boolean.TRUE) != null) {
                return;
            }
            if (handler == null) {
                handler = new Handler();
            }
            aVar.n(handler);
            this.a.d(aVar, handler);
            return;
        }
        throw new IllegalArgumentException("callback must not be null");
    }

    public void g(a aVar) {
        if (aVar != null) {
            if (this.c.remove(aVar) == null) {
                return;
            }
            try {
                this.a.a(aVar);
                return;
            } finally {
                aVar.n(null);
            }
        }
        throw new IllegalArgumentException("callback must not be null");
    }

    public MediaControllerCompat(Context context, MediaSessionCompat.Token token) {
        if (token != null) {
            this.b = token;
            if (Build.VERSION.SDK_INT >= 21) {
                this.a = new MediaControllerImplApi21(context, token);
                return;
            } else {
                this.a = new c(token);
                return;
            }
        }
        throw new IllegalArgumentException("sessionToken must not be null");
    }
}
