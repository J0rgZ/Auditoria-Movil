package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaDescription;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.media.session.b;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import e0.l;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class MediaSessionCompat {
    public static int d;
    public final c a;
    public final MediaControllerCompat b;
    public final ArrayList c;

    @SuppressLint({"BanParcelableUsage"})
    /* loaded from: classes.dex */
    public static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new a();
        public ResultReceiver a;

        /* loaded from: classes.dex */
        public class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public ResultReceiverWrapper[] newArray(int i) {
                return new ResultReceiverWrapper[i];
            }
        }

        public ResultReceiverWrapper(Parcel parcel) {
            this.a = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            this.a.writeToParcel(parcel, i);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    /* loaded from: classes.dex */
    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = new a();
        public final Object a;
        public final Object b;
        public android.support.v4.media.session.b c;
        public s0.d d;

        /* loaded from: classes.dex */
        public class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public Token createFromParcel(Parcel parcel) {
                Object readStrongBinder;
                if (Build.VERSION.SDK_INT >= 21) {
                    readStrongBinder = parcel.readParcelable(null);
                } else {
                    readStrongBinder = parcel.readStrongBinder();
                }
                return new Token(readStrongBinder);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public Token[] newArray(int i) {
                return new Token[i];
            }
        }

        public Token(Object obj) {
            this(obj, null, null);
        }

        public android.support.v4.media.session.b a() {
            android.support.v4.media.session.b bVar;
            synchronized (this.a) {
                bVar = this.c;
            }
            return bVar;
        }

        public s0.d b() {
            s0.d dVar;
            synchronized (this.a) {
                dVar = this.d;
            }
            return dVar;
        }

        public Object c() {
            return this.b;
        }

        public void d(android.support.v4.media.session.b bVar) {
            synchronized (this.a) {
                this.c = bVar;
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public void e(s0.d dVar) {
            synchronized (this.a) {
                this.d = dVar;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            Object obj2 = this.b;
            if (obj2 == null) {
                if (token.b == null) {
                    return true;
                }
                return false;
            }
            Object obj3 = token.b;
            if (obj3 == null) {
                return false;
            }
            return obj2.equals(obj3);
        }

        public int hashCode() {
            Object obj = this.b;
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (Build.VERSION.SDK_INT >= 21) {
                parcel.writeParcelable((Parcelable) this.b, i);
            } else {
                parcel.writeStrongBinder((IBinder) this.b);
            }
        }

        public Token(Object obj, android.support.v4.media.session.b bVar, s0.d dVar) {
            this.a = new Object();
            this.b = obj;
            this.c = bVar;
            this.d = dVar;
        }
    }

    /* loaded from: classes.dex */
    public class a extends b {
        public a() {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class b {
        final MediaSession.Callback mCallbackFwk;
        a mCallbackHandler;
        final Object mLock = new Object();
        private boolean mMediaPlayPausePendingOnHandler;
        WeakReference<c> mSessionImpl;

        /* loaded from: classes.dex */
        public class a extends Handler {
            public a(Looper looper) {
                super(looper);
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                c cVar;
                b bVar;
                a aVar;
                if (message.what == 1) {
                    synchronized (b.this.mLock) {
                        cVar = b.this.mSessionImpl.get();
                        bVar = b.this;
                        aVar = bVar.mCallbackHandler;
                    }
                    if (cVar != null && bVar == cVar.i() && aVar != null) {
                        cVar.p((e0.d) message.obj);
                        b.this.handleMediaPlayPauseIfPendingOnHandler(cVar, aVar);
                        cVar.p(null);
                    }
                }
            }
        }

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0001b extends MediaSession.Callback {
            public C0001b() {
            }

            public final void a(c cVar) {
                cVar.p(null);
            }

            public final f b() {
                f fVar;
                synchronized (b.this.mLock) {
                    fVar = (f) b.this.mSessionImpl.get();
                }
                if (b.this != fVar.i()) {
                    return null;
                }
                return fVar;
            }

            public final void c(c cVar) {
                if (Build.VERSION.SDK_INT >= 28) {
                    return;
                }
                String c = cVar.c();
                if (TextUtils.isEmpty(c)) {
                    c = "android.media.session.MediaController";
                }
                cVar.p(new e0.d(c, -1, -1));
            }

            @Override // android.media.session.MediaSession.Callback
            public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
                f b = b();
                if (b == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(b);
                try {
                    QueueItem queueItem = null;
                    IBinder asBinder = null;
                    queueItem = null;
                    if (str.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER")) {
                        Bundle bundle2 = new Bundle();
                        Token b2 = b.b();
                        android.support.v4.media.session.b a = b2.a();
                        if (a != null) {
                            asBinder = a.asBinder();
                        }
                        i.n.b(bundle2, "android.support.v4.media.session.EXTRA_BINDER", asBinder);
                        s0.a.c(bundle2, "android.support.v4.media.session.SESSION_TOKEN2", b2.b());
                        resultReceiver.send(0, bundle2);
                    } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM")) {
                        b.this.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                    } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT")) {
                        b.this.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
                    } else if (str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM")) {
                        b.this.onRemoveQueueItem((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                    } else if (str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT")) {
                        if (b.h != null) {
                            int i = bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);
                            if (i >= 0 && i < b.h.size()) {
                                queueItem = (QueueItem) b.h.get(i);
                            }
                            if (queueItem != null) {
                                b.this.onRemoveQueueItem(queueItem.c());
                            }
                        }
                    } else {
                        b.this.onCommand(str, bundle, resultReceiver);
                    }
                } catch (BadParcelableException unused) {
                    Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
                }
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onCustomAction(String str, Bundle bundle) {
                f b = b();
                if (b == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(b);
                try {
                    if (str.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
                        Bundle bundle2 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.c(bundle2);
                        b.this.onPlayFromUri((Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI"), bundle2);
                    } else if (str.equals("android.support.v4.media.session.action.PREPARE")) {
                        b.this.onPrepare();
                    } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
                        String string = bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
                        Bundle bundle3 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.c(bundle3);
                        b.this.onPrepareFromMediaId(string, bundle3);
                    } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
                        String string2 = bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
                        Bundle bundle4 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.c(bundle4);
                        b.this.onPrepareFromSearch(string2, bundle4);
                    } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
                        Bundle bundle5 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.c(bundle5);
                        b.this.onPrepareFromUri((Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI"), bundle5);
                    } else if (str.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED")) {
                        b.this.onSetCaptioningEnabled(bundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED"));
                    } else if (str.equals("android.support.v4.media.session.action.SET_REPEAT_MODE")) {
                        b.this.onSetRepeatMode(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE"));
                    } else if (str.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE")) {
                        b.this.onSetShuffleMode(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE"));
                    } else if (str.equals("android.support.v4.media.session.action.SET_RATING")) {
                        Bundle bundle6 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.c(bundle6);
                        b.this.onSetRating((RatingCompat) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING"), bundle6);
                    } else if (str.equals("android.support.v4.media.session.action.SET_PLAYBACK_SPEED")) {
                        b.this.onSetPlaybackSpeed(bundle.getFloat("android.support.v4.media.session.action.ARGUMENT_PLAYBACK_SPEED", 1.0f));
                    } else {
                        b.this.onCustomAction(str, bundle);
                    }
                } catch (BadParcelableException unused) {
                    Log.e("MediaSessionCompat", "Could not unparcel the data.");
                }
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onFastForward() {
                f b = b();
                if (b == null) {
                    return;
                }
                c(b);
                b.this.onFastForward();
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public boolean onMediaButtonEvent(Intent intent) {
                f b = b();
                if (b == null) {
                    return false;
                }
                c(b);
                boolean onMediaButtonEvent = b.this.onMediaButtonEvent(intent);
                a(b);
                if (!onMediaButtonEvent && !super.onMediaButtonEvent(intent)) {
                    return false;
                }
                return true;
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPause() {
                f b = b();
                if (b == null) {
                    return;
                }
                c(b);
                b.this.onPause();
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlay() {
                f b = b();
                if (b == null) {
                    return;
                }
                c(b);
                b.this.onPlay();
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromMediaId(String str, Bundle bundle) {
                f b = b();
                if (b == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(b);
                b.this.onPlayFromMediaId(str, bundle);
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromSearch(String str, Bundle bundle) {
                f b = b();
                if (b == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(b);
                b.this.onPlayFromSearch(str, bundle);
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromUri(Uri uri, Bundle bundle) {
                f b = b();
                if (b == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(b);
                b.this.onPlayFromUri(uri, bundle);
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepare() {
                f b = b();
                if (b == null) {
                    return;
                }
                c(b);
                b.this.onPrepare();
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromMediaId(String str, Bundle bundle) {
                f b = b();
                if (b == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(b);
                b.this.onPrepareFromMediaId(str, bundle);
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromSearch(String str, Bundle bundle) {
                f b = b();
                if (b == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(b);
                b.this.onPrepareFromSearch(str, bundle);
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromUri(Uri uri, Bundle bundle) {
                f b = b();
                if (b == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(b);
                b.this.onPrepareFromUri(uri, bundle);
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onRewind() {
                f b = b();
                if (b == null) {
                    return;
                }
                c(b);
                b.this.onRewind();
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSeekTo(long j) {
                f b = b();
                if (b == null) {
                    return;
                }
                c(b);
                b.this.onSeekTo(j);
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSetPlaybackSpeed(float f) {
                f b = b();
                if (b == null) {
                    return;
                }
                c(b);
                b.this.onSetPlaybackSpeed(f);
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSetRating(Rating rating) {
                f b = b();
                if (b == null) {
                    return;
                }
                c(b);
                b.this.onSetRating(RatingCompat.a(rating));
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToNext() {
                f b = b();
                if (b == null) {
                    return;
                }
                c(b);
                b.this.onSkipToNext();
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToPrevious() {
                f b = b();
                if (b == null) {
                    return;
                }
                c(b);
                b.this.onSkipToPrevious();
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToQueueItem(long j) {
                f b = b();
                if (b == null) {
                    return;
                }
                c(b);
                b.this.onSkipToQueueItem(j);
                a(b);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onStop() {
                f b = b();
                if (b == null) {
                    return;
                }
                c(b);
                b.this.onStop();
                a(b);
            }
        }

        public b() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.mCallbackFwk = new C0001b();
            } else {
                this.mCallbackFwk = null;
            }
            this.mSessionImpl = new WeakReference<>(null);
        }

        public void handleMediaPlayPauseIfPendingOnHandler(c cVar, Handler handler) {
            long b;
            boolean z;
            boolean z2;
            if (!this.mMediaPlayPausePendingOnHandler) {
                return;
            }
            boolean z3 = false;
            this.mMediaPlayPausePendingOnHandler = false;
            handler.removeMessages(1);
            PlaybackStateCompat e = cVar.e();
            if (e == null) {
                b = 0;
            } else {
                b = e.b();
            }
            if (e != null && e.g() == 3) {
                z = true;
            } else {
                z = false;
            }
            if ((516 & b) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if ((b & 514) != 0) {
                z3 = true;
            }
            if (z && z3) {
                onPause();
            } else if (!z && z2) {
                onPlay();
            }
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        }

        public void onCustomAction(String str, Bundle bundle) {
        }

        public void onFastForward() {
        }

        public boolean onMediaButtonEvent(Intent intent) {
            c cVar;
            a aVar;
            KeyEvent keyEvent;
            long b;
            if (Build.VERSION.SDK_INT >= 27) {
                return false;
            }
            synchronized (this.mLock) {
                cVar = this.mSessionImpl.get();
                aVar = this.mCallbackHandler;
            }
            if (cVar == null || aVar == null || (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) == null || keyEvent.getAction() != 0) {
                return false;
            }
            e0.d o = cVar.o();
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 79 && keyCode != 85) {
                handleMediaPlayPauseIfPendingOnHandler(cVar, aVar);
                return false;
            }
            if (keyEvent.getRepeatCount() == 0) {
                if (this.mMediaPlayPausePendingOnHandler) {
                    aVar.removeMessages(1);
                    this.mMediaPlayPausePendingOnHandler = false;
                    PlaybackStateCompat e = cVar.e();
                    if (e == null) {
                        b = 0;
                    } else {
                        b = e.b();
                    }
                    if ((b & 32) != 0) {
                        onSkipToNext();
                    }
                } else {
                    this.mMediaPlayPausePendingOnHandler = true;
                    aVar.sendMessageDelayed(aVar.obtainMessage(1, o), ViewConfiguration.getDoubleTapTimeout());
                }
            } else {
                handleMediaPlayPauseIfPendingOnHandler(cVar, aVar);
            }
            return true;
        }

        public void onPause() {
        }

        public void onPlay() {
        }

        public void onPlayFromMediaId(String str, Bundle bundle) {
        }

        public void onPlayFromSearch(String str, Bundle bundle) {
        }

        public void onPlayFromUri(Uri uri, Bundle bundle) {
        }

        public void onPrepare() {
        }

        public void onPrepareFromMediaId(String str, Bundle bundle) {
        }

        public void onPrepareFromSearch(String str, Bundle bundle) {
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle) {
        }

        public void onRemoveQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        @Deprecated
        public void onRemoveQueueItemAt(int i) {
        }

        public void onRewind() {
        }

        public void onSeekTo(long j) {
        }

        public void onSetCaptioningEnabled(boolean z) {
        }

        public void onSetPlaybackSpeed(float f) {
        }

        public void onSetRating(RatingCompat ratingCompat) {
        }

        public void onSetRepeatMode(int i) {
        }

        public void onSetShuffleMode(int i) {
        }

        public void onSkipToNext() {
        }

        public void onSkipToPrevious() {
        }

        public void onSkipToQueueItem(long j) {
        }

        public void onStop() {
        }

        public void setSessionImpl(c cVar, Handler handler) {
            synchronized (this.mLock) {
                this.mSessionImpl = new WeakReference<>(cVar);
                a aVar = this.mCallbackHandler;
                a aVar2 = null;
                if (aVar != null) {
                    aVar.removeCallbacksAndMessages(null);
                }
                if (cVar != null && handler != null) {
                    aVar2 = new a(handler.getLooper());
                }
                this.mCallbackHandler = aVar2;
            }
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        }

        public void onSetRating(RatingCompat ratingCompat, Bundle bundle) {
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        boolean a();

        Token b();

        String c();

        void d(PendingIntent pendingIntent);

        PlaybackStateCompat e();

        void f(b bVar, Handler handler);

        void g(e0.l lVar);

        void h(int i);

        b i();

        void j(MediaMetadataCompat mediaMetadataCompat);

        void k(PendingIntent pendingIntent);

        Object l();

        void m(boolean z);

        void n(PlaybackStateCompat playbackStateCompat);

        e0.d o();

        void p(e0.d dVar);

        void release();
    }

    /* loaded from: classes.dex */
    public static class d extends i {
        public static boolean G = true;

        /* loaded from: classes.dex */
        public class a implements RemoteControlClient.OnPlaybackPositionUpdateListener {
            public a() {
            }

            @Override // android.media.RemoteControlClient.OnPlaybackPositionUpdateListener
            public void onPlaybackPositionUpdate(long j) {
                d.this.v(18, -1, -1, Long.valueOf(j), null);
            }
        }

        public d(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, s0.d dVar, Bundle bundle) {
            super(context, str, componentName, pendingIntent, dVar, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        public void B(PlaybackStateCompat playbackStateCompat) {
            long f = playbackStateCompat.f();
            float d = playbackStateCompat.d();
            long c = playbackStateCompat.c();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (playbackStateCompat.g() == 3) {
                long j = 0;
                if (f > 0) {
                    if (c > 0) {
                        j = elapsedRealtime - c;
                        if (d > 0.0f && d != 1.0f) {
                            j = ((float) j) * d;
                        }
                    }
                    f += j;
                }
            }
            this.j.setPlaybackState(t(playbackStateCompat.g()), f, d);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        public void D(PendingIntent pendingIntent, ComponentName componentName) {
            if (G) {
                this.i.unregisterMediaButtonEventReceiver(pendingIntent);
            } else {
                super.D(pendingIntent, componentName);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i, android.support.v4.media.session.MediaSessionCompat.c
        public void f(b bVar, Handler handler) {
            super.f(bVar, handler);
            if (bVar == null) {
                this.j.setPlaybackPositionUpdateListener(null);
                return;
            }
            this.j.setPlaybackPositionUpdateListener(new a());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        public int u(long j) {
            int u = super.u(j);
            if ((j & 256) != 0) {
                return u | 256;
            }
            return u;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        public void w(PendingIntent pendingIntent, ComponentName componentName) {
            if (G) {
                try {
                    this.i.registerMediaButtonEventReceiver(pendingIntent);
                } catch (NullPointerException unused) {
                    G = false;
                }
            }
            if (!G) {
                super.w(pendingIntent, componentName);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class e extends d {

        /* loaded from: classes.dex */
        public class a implements RemoteControlClient.OnMetadataUpdateListener {
            public a() {
            }

            @Override // android.media.RemoteControlClient.OnMetadataUpdateListener
            public void onMetadataUpdate(int i, Object obj) {
                if (i == 268435457 && (obj instanceof Rating)) {
                    e.this.v(19, -1, -1, RatingCompat.a(obj), null);
                }
            }
        }

        public e(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, s0.d dVar, Bundle bundle) {
            super(context, str, componentName, pendingIntent, dVar, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d, android.support.v4.media.session.MediaSessionCompat.i, android.support.v4.media.session.MediaSessionCompat.c
        public void f(b bVar, Handler handler) {
            super.f(bVar, handler);
            if (bVar == null) {
                this.j.setMetadataUpdateListener(null);
                return;
            }
            this.j.setMetadataUpdateListener(new a());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        public RemoteControlClient.MetadataEditor r(Bundle bundle) {
            long b;
            RemoteControlClient.MetadataEditor r = super.r(bundle);
            PlaybackStateCompat playbackStateCompat = this.t;
            if (playbackStateCompat == null) {
                b = 0;
            } else {
                b = playbackStateCompat.b();
            }
            if ((b & 128) != 0) {
                r.addEditableKey(268435457);
            }
            if (bundle == null) {
                return r;
            }
            if (bundle.containsKey("android.media.metadata.YEAR")) {
                r.putLong(8, bundle.getLong("android.media.metadata.YEAR"));
            }
            if (bundle.containsKey("android.media.metadata.RATING")) {
                r.putObject(101, (Object) bundle.getParcelable("android.media.metadata.RATING"));
            }
            if (bundle.containsKey("android.media.metadata.USER_RATING")) {
                r.putObject(268435457, (Object) bundle.getParcelable("android.media.metadata.USER_RATING"));
            }
            return r;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d, android.support.v4.media.session.MediaSessionCompat.i
        public int u(long j) {
            int u = super.u(j);
            if ((j & 128) != 0) {
                return u | 512;
            }
            return u;
        }
    }

    /* loaded from: classes.dex */
    public static class f implements c {
        public final MediaSession a;
        public final Token b;
        public Bundle d;
        public PlaybackStateCompat g;
        public List h;
        public MediaMetadataCompat i;
        public int j;
        public boolean k;
        public int l;
        public int m;
        public b n;
        public e0.d o;
        public final Object c = new Object();
        public boolean e = false;
        public final RemoteCallbackList f = new RemoteCallbackList();

        /* loaded from: classes.dex */
        public class a extends b.a {
            public a() {
            }

            @Override // android.support.v4.media.session.b
            public void A(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void B() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void C(Uri uri, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void E(float f) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean F(KeyEvent keyEvent) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void I(int i, int i2, String str) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void J(RatingCompat ratingCompat, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void L(MediaDescriptionCompat mediaDescriptionCompat, int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void N(boolean z) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public int P() {
                return f.this.m;
            }

            @Override // android.support.v4.media.session.b
            public void Q(int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean R() {
                return f.this.k;
            }

            @Override // android.support.v4.media.session.b
            public void T() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void U(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public List V() {
                return null;
            }

            @Override // android.support.v4.media.session.b
            public void W(int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void X() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public long Y() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public int Z() {
                return f.this.l;
            }

            @Override // android.support.v4.media.session.b
            public void a0(long j) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void b0(boolean z) {
            }

            @Override // android.support.v4.media.session.b
            public ParcelableVolumeInfo c0() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void d0(int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public PlaybackStateCompat e() {
                f fVar = f.this;
                return MediaSessionCompat.g(fVar.g, fVar.i);
            }

            @Override // android.support.v4.media.session.b
            public String e0() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void f(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void g(android.support.v4.media.session.a aVar) {
                if (!f.this.e) {
                    f.this.f.register(aVar, new e0.d("android.media.session.MediaController", Binder.getCallingPid(), Binder.getCallingUid()));
                }
            }

            @Override // android.support.v4.media.session.b
            public Bundle getExtras() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public MediaMetadataCompat getMetadata() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public String getTag() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean h() {
                return false;
            }

            @Override // android.support.v4.media.session.b
            public void i(RatingCompat ratingCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void j(int i, int i2, String str) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void k(Uri uri, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void l(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean m() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void n(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void next() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public PendingIntent o() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public int p() {
                return f.this.j;
            }

            @Override // android.support.v4.media.session.b
            public void pause() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void previous() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void q(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void r() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void seekTo(long j) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void stop() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public CharSequence v() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void w(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public Bundle x() {
                if (f.this.d == null) {
                    return null;
                }
                return new Bundle(f.this.d);
            }

            @Override // android.support.v4.media.session.b
            public void y(android.support.v4.media.session.a aVar) {
                f.this.f.unregister(aVar);
            }

            @Override // android.support.v4.media.session.b
            public void z(String str, Bundle bundle) {
                throw new AssertionError();
            }
        }

        public f(MediaSession mediaSession, s0.d dVar, Bundle bundle) {
            MediaSession.Token sessionToken;
            this.a = mediaSession;
            sessionToken = mediaSession.getSessionToken();
            this.b = new Token(sessionToken, new a(), dVar);
            this.d = bundle;
            q(3);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public boolean a() {
            boolean isActive;
            isActive = this.a.isActive();
            return isActive;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public Token b() {
            return this.b;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public String c() {
            if (Build.VERSION.SDK_INT < 24) {
                return null;
            }
            try {
                return (String) this.a.getClass().getMethod("getCallingPackage", new Class[0]).invoke(this.a, new Object[0]);
            } catch (Exception e) {
                Log.e("MediaSessionCompat", "Cannot execute MediaSession.getCallingPackage()", e);
                return null;
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void d(PendingIntent pendingIntent) {
            this.a.setSessionActivity(pendingIntent);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public PlaybackStateCompat e() {
            return this.g;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void f(b bVar, Handler handler) {
            MediaSession.Callback callback;
            synchronized (this.c) {
                this.n = bVar;
                MediaSession mediaSession = this.a;
                if (bVar == null) {
                    callback = null;
                } else {
                    callback = bVar.mCallbackFwk;
                }
                mediaSession.setCallback(callback, handler);
                if (bVar != null) {
                    bVar.setSessionImpl(this, handler);
                }
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void g(e0.l lVar) {
            this.a.setPlaybackToRemote(a0.a(lVar.d()));
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void h(int i) {
            AudioAttributes build;
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            builder.setLegacyStreamType(i);
            MediaSession mediaSession = this.a;
            build = builder.build();
            mediaSession.setPlaybackToLocal(build);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public b i() {
            b bVar;
            synchronized (this.c) {
                bVar = this.n;
            }
            return bVar;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void j(MediaMetadataCompat mediaMetadataCompat) {
            MediaMetadata a2;
            this.i = mediaMetadataCompat;
            MediaSession mediaSession = this.a;
            if (mediaMetadataCompat == null) {
                a2 = null;
            } else {
                a2 = android.support.v4.media.u.a(mediaMetadataCompat.g());
            }
            mediaSession.setMetadata(a2);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void k(PendingIntent pendingIntent) {
            this.a.setMediaButtonReceiver(pendingIntent);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public Object l() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void m(boolean z) {
            this.a.setActive(z);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void n(PlaybackStateCompat playbackStateCompat) {
            PlaybackState a2;
            this.g = playbackStateCompat;
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((android.support.v4.media.session.a) this.f.getBroadcastItem(beginBroadcast)).f0(playbackStateCompat);
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
            MediaSession mediaSession = this.a;
            if (playbackStateCompat == null) {
                a2 = null;
            } else {
                a2 = u.a(playbackStateCompat.e());
            }
            mediaSession.setPlaybackState(a2);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public e0.d o() {
            e0.d dVar;
            synchronized (this.c) {
                dVar = this.o;
            }
            return dVar;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void p(e0.d dVar) {
            synchronized (this.c) {
                this.o = dVar;
            }
        }

        public void q(int i) {
            this.a.setFlags(i | 1 | 2);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void release() {
            this.e = true;
            this.f.kill();
            this.a.setCallback(null);
            this.a.release();
        }
    }

    /* loaded from: classes.dex */
    public static class g extends f {
        public g(MediaSession mediaSession, s0.d dVar, Bundle bundle) {
            super(mediaSession, dVar, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.f, android.support.v4.media.session.MediaSessionCompat.c
        public final e0.d o() {
            MediaSessionManager.RemoteUserInfo currentControllerInfo;
            currentControllerInfo = this.a.getCurrentControllerInfo();
            return new e0.d(currentControllerInfo);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.f, android.support.v4.media.session.MediaSessionCompat.c
        public void p(e0.d dVar) {
        }
    }

    /* loaded from: classes.dex */
    public static class h extends g {
        public h(MediaSession mediaSession, s0.d dVar, Bundle bundle) {
            super(mediaSession, dVar, bundle);
        }
    }

    /* loaded from: classes.dex */
    public static class i implements c {
        public int A;
        public Bundle B;
        public int C;
        public int D;
        public e0.l E;
        public final Context a;
        public final ComponentName b;
        public final PendingIntent c;
        public final c d;
        public final Token e;
        public final String f;
        public final Bundle g;
        public final String h;
        public final AudioManager i;
        public final RemoteControlClient j;
        public d m;
        public volatile b p;
        public e0.d q;

        /* renamed from: s  reason: collision with root package name */
        public MediaMetadataCompat f0s;
        public PlaybackStateCompat t;
        public PendingIntent u;
        public List v;
        public CharSequence w;
        public int x;
        public boolean y;
        public int z;
        public final Object k = new Object();
        public final RemoteCallbackList l = new RemoteCallbackList();
        public boolean n = false;
        public boolean o = false;
        public int r = 3;
        public l.c F = new a();

        /* loaded from: classes.dex */
        public class a extends l.c {
            public a() {
            }

            public void a(e0.l lVar) {
                if (i.this.E != lVar) {
                    return;
                }
                i iVar = i.this;
                i.this.A(new ParcelableVolumeInfo(iVar.C, iVar.D, lVar.c(), lVar.b(), lVar.a()));
            }
        }

        /* loaded from: classes.dex */
        public static final class b {
            public final String a;
            public final Bundle b;
            public final ResultReceiver c;

            public b(String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.a = str;
                this.b = bundle;
                this.c = resultReceiver;
            }
        }

        /* loaded from: classes.dex */
        public class c extends b.a {
            public c() {
            }

            @Override // android.support.v4.media.session.b
            public void A(String str, Bundle bundle) {
                o0(9, str, bundle);
            }

            @Override // android.support.v4.media.session.b
            public void B() {
                k0(16);
            }

            @Override // android.support.v4.media.session.b
            public void C(Uri uri, Bundle bundle) {
                o0(10, uri, bundle);
            }

            @Override // android.support.v4.media.session.b
            public void E(float f) {
                m0(32, Float.valueOf(f));
            }

            @Override // android.support.v4.media.session.b
            public boolean F(KeyEvent keyEvent) {
                m0(21, keyEvent);
                return true;
            }

            @Override // android.support.v4.media.session.b
            public void I(int i, int i2, String str) {
                i.this.q(i, i2);
            }

            @Override // android.support.v4.media.session.b
            public void J(RatingCompat ratingCompat, Bundle bundle) {
                o0(31, ratingCompat, bundle);
            }

            @Override // android.support.v4.media.session.b
            public void L(MediaDescriptionCompat mediaDescriptionCompat, int i) {
                n0(26, mediaDescriptionCompat, i);
            }

            @Override // android.support.v4.media.session.b
            public void N(boolean z) {
                m0(29, Boolean.valueOf(z));
            }

            @Override // android.support.v4.media.session.b
            public int P() {
                return i.this.A;
            }

            @Override // android.support.v4.media.session.b
            public void Q(int i) {
                l0(28, i);
            }

            @Override // android.support.v4.media.session.b
            public boolean R() {
                return i.this.y;
            }

            @Override // android.support.v4.media.session.b
            public void T() {
                k0(7);
            }

            @Override // android.support.v4.media.session.b
            public void U(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                ResultReceiver resultReceiver;
                if (resultReceiverWrapper == null) {
                    resultReceiver = null;
                } else {
                    resultReceiver = resultReceiverWrapper.a;
                }
                m0(1, new b(str, bundle, resultReceiver));
            }

            @Override // android.support.v4.media.session.b
            public List V() {
                List list;
                synchronized (i.this.k) {
                    list = i.this.v;
                }
                return list;
            }

            @Override // android.support.v4.media.session.b
            public void W(int i) {
                l0(23, i);
            }

            @Override // android.support.v4.media.session.b
            public void X() {
                k0(17);
            }

            @Override // android.support.v4.media.session.b
            public long Y() {
                long j;
                synchronized (i.this.k) {
                    j = i.this.r;
                }
                return j;
            }

            @Override // android.support.v4.media.session.b
            public int Z() {
                return i.this.z;
            }

            @Override // android.support.v4.media.session.b
            public void a0(long j) {
                m0(11, Long.valueOf(j));
            }

            @Override // android.support.v4.media.session.b
            public void b0(boolean z) {
            }

            @Override // android.support.v4.media.session.b
            public ParcelableVolumeInfo c0() {
                int i;
                int i2;
                int i3;
                int streamMaxVolume;
                int streamVolume;
                synchronized (i.this.k) {
                    i iVar = i.this;
                    i = iVar.C;
                    i2 = iVar.D;
                    e0.l lVar = iVar.E;
                    i3 = 2;
                    if (i == 2) {
                        int c = lVar.c();
                        int b = lVar.b();
                        streamVolume = lVar.a();
                        streamMaxVolume = b;
                        i3 = c;
                    } else {
                        streamMaxVolume = iVar.i.getStreamMaxVolume(i2);
                        streamVolume = i.this.i.getStreamVolume(i2);
                    }
                }
                return new ParcelableVolumeInfo(i, i2, i3, streamMaxVolume, streamVolume);
            }

            @Override // android.support.v4.media.session.b
            public void d0(int i) {
                l0(30, i);
            }

            @Override // android.support.v4.media.session.b
            public PlaybackStateCompat e() {
                PlaybackStateCompat playbackStateCompat;
                MediaMetadataCompat mediaMetadataCompat;
                synchronized (i.this.k) {
                    i iVar = i.this;
                    playbackStateCompat = iVar.t;
                    mediaMetadataCompat = iVar.f0s;
                }
                return MediaSessionCompat.g(playbackStateCompat, mediaMetadataCompat);
            }

            @Override // android.support.v4.media.session.b
            public String e0() {
                return i.this.f;
            }

            @Override // android.support.v4.media.session.b
            public void f(String str, Bundle bundle) {
                o0(20, str, bundle);
            }

            @Override // android.support.v4.media.session.b
            public void g(android.support.v4.media.session.a aVar) {
                if (i.this.n) {
                    try {
                        aVar.K();
                        return;
                    } catch (Exception unused) {
                        return;
                    }
                }
                i.this.l.register(aVar, new e0.d(i.this.s(Binder.getCallingUid()), Binder.getCallingPid(), Binder.getCallingUid()));
            }

            @Override // android.support.v4.media.session.b
            public Bundle getExtras() {
                Bundle bundle;
                synchronized (i.this.k) {
                    bundle = i.this.B;
                }
                return bundle;
            }

            @Override // android.support.v4.media.session.b
            public MediaMetadataCompat getMetadata() {
                return i.this.f0s;
            }

            @Override // android.support.v4.media.session.b
            public String getTag() {
                return i.this.h;
            }

            @Override // android.support.v4.media.session.b
            public boolean h() {
                return false;
            }

            @Override // android.support.v4.media.session.b
            public void i(RatingCompat ratingCompat) {
                m0(19, ratingCompat);
            }

            @Override // android.support.v4.media.session.b
            public void j(int i, int i2, String str) {
                i.this.C(i, i2);
            }

            @Override // android.support.v4.media.session.b
            public void k(Uri uri, Bundle bundle) {
                o0(6, uri, bundle);
            }

            public void k0(int i) {
                i.this.v(i, 0, 0, null, null);
            }

            @Override // android.support.v4.media.session.b
            public void l(MediaDescriptionCompat mediaDescriptionCompat) {
                m0(27, mediaDescriptionCompat);
            }

            public void l0(int i, int i2) {
                i.this.v(i, i2, 0, null, null);
            }

            @Override // android.support.v4.media.session.b
            public boolean m() {
                return true;
            }

            public void m0(int i, Object obj) {
                i.this.v(i, 0, 0, obj, null);
            }

            @Override // android.support.v4.media.session.b
            public void n(MediaDescriptionCompat mediaDescriptionCompat) {
                m0(25, mediaDescriptionCompat);
            }

            public void n0(int i, Object obj, int i2) {
                i.this.v(i, i2, 0, obj, null);
            }

            @Override // android.support.v4.media.session.b
            public void next() {
                k0(14);
            }

            @Override // android.support.v4.media.session.b
            public PendingIntent o() {
                PendingIntent pendingIntent;
                synchronized (i.this.k) {
                    pendingIntent = i.this.u;
                }
                return pendingIntent;
            }

            public void o0(int i, Object obj, Bundle bundle) {
                i.this.v(i, 0, 0, obj, bundle);
            }

            @Override // android.support.v4.media.session.b
            public int p() {
                return i.this.x;
            }

            @Override // android.support.v4.media.session.b
            public void pause() {
                k0(12);
            }

            @Override // android.support.v4.media.session.b
            public void previous() {
                k0(15);
            }

            @Override // android.support.v4.media.session.b
            public void q(String str, Bundle bundle) {
                o0(5, str, bundle);
            }

            @Override // android.support.v4.media.session.b
            public void r() {
                k0(3);
            }

            @Override // android.support.v4.media.session.b
            public void seekTo(long j) {
                m0(18, Long.valueOf(j));
            }

            @Override // android.support.v4.media.session.b
            public void stop() {
                k0(13);
            }

            @Override // android.support.v4.media.session.b
            public CharSequence v() {
                return i.this.w;
            }

            @Override // android.support.v4.media.session.b
            public void w(String str, Bundle bundle) {
                o0(4, str, bundle);
            }

            @Override // android.support.v4.media.session.b
            public Bundle x() {
                if (i.this.g == null) {
                    return null;
                }
                return new Bundle(i.this.g);
            }

            @Override // android.support.v4.media.session.b
            public void y(android.support.v4.media.session.a aVar) {
                i.this.l.unregister(aVar);
            }

            @Override // android.support.v4.media.session.b
            public void z(String str, Bundle bundle) {
                o0(8, str, bundle);
            }
        }

        /* loaded from: classes.dex */
        public class d extends Handler {
            public d(Looper looper) {
                super(looper);
            }

            public final void a(KeyEvent keyEvent, b bVar) {
                long b;
                if (keyEvent != null && keyEvent.getAction() == 0) {
                    PlaybackStateCompat playbackStateCompat = i.this.t;
                    if (playbackStateCompat == null) {
                        b = 0;
                    } else {
                        b = playbackStateCompat.b();
                    }
                    int keyCode = keyEvent.getKeyCode();
                    if (keyCode != 126) {
                        if (keyCode != 127) {
                            switch (keyCode) {
                                case 86:
                                    if ((b & 1) != 0) {
                                        bVar.onStop();
                                        return;
                                    }
                                    return;
                                case 87:
                                    if ((b & 32) != 0) {
                                        bVar.onSkipToNext();
                                        return;
                                    }
                                    return;
                                case 88:
                                    if ((b & 16) != 0) {
                                        bVar.onSkipToPrevious();
                                        return;
                                    }
                                    return;
                                case 89:
                                    if ((b & 8) != 0) {
                                        bVar.onRewind();
                                        return;
                                    }
                                    return;
                                case 90:
                                    if ((b & 64) != 0) {
                                        bVar.onFastForward();
                                        return;
                                    }
                                    return;
                                default:
                                    return;
                            }
                        } else if ((b & 2) != 0) {
                            bVar.onPause();
                        }
                    } else if ((b & 4) != 0) {
                        bVar.onPlay();
                    }
                }
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                QueueItem queueItem;
                b bVar = i.this.p;
                if (bVar == null) {
                    return;
                }
                Bundle data = message.getData();
                MediaSessionCompat.c(data);
                i.this.p(new e0.d(data.getString("data_calling_pkg"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid")));
                Bundle bundle = data.getBundle("data_extras");
                MediaSessionCompat.c(bundle);
                try {
                    switch (message.what) {
                        case 1:
                            b bVar2 = (b) message.obj;
                            bVar.onCommand(bVar2.a, bVar2.b, bVar2.c);
                            break;
                        case 2:
                            i.this.q(message.arg1, 0);
                            break;
                        case 3:
                            bVar.onPrepare();
                            break;
                        case 4:
                            bVar.onPrepareFromMediaId((String) message.obj, bundle);
                            break;
                        case 5:
                            bVar.onPrepareFromSearch((String) message.obj, bundle);
                            break;
                        case 6:
                            bVar.onPrepareFromUri((Uri) message.obj, bundle);
                            break;
                        case 7:
                            bVar.onPlay();
                            break;
                        case 8:
                            bVar.onPlayFromMediaId((String) message.obj, bundle);
                            break;
                        case 9:
                            bVar.onPlayFromSearch((String) message.obj, bundle);
                            break;
                        case 10:
                            bVar.onPlayFromUri((Uri) message.obj, bundle);
                            break;
                        case 11:
                            bVar.onSkipToQueueItem(((Long) message.obj).longValue());
                            break;
                        case 12:
                            bVar.onPause();
                            break;
                        case 13:
                            bVar.onStop();
                            break;
                        case 14:
                            bVar.onSkipToNext();
                            break;
                        case 15:
                            bVar.onSkipToPrevious();
                            break;
                        case 16:
                            bVar.onFastForward();
                            break;
                        case 17:
                            bVar.onRewind();
                            break;
                        case 18:
                            bVar.onSeekTo(((Long) message.obj).longValue());
                            break;
                        case 19:
                            bVar.onSetRating((RatingCompat) message.obj);
                            break;
                        case 20:
                            bVar.onCustomAction((String) message.obj, bundle);
                            break;
                        case 21:
                            KeyEvent keyEvent = (KeyEvent) message.obj;
                            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                            intent.putExtra("android.intent.extra.KEY_EVENT", keyEvent);
                            if (!bVar.onMediaButtonEvent(intent)) {
                                a(keyEvent, bVar);
                                break;
                            }
                            break;
                        case 22:
                            i.this.C(message.arg1, 0);
                            break;
                        case 23:
                            bVar.onSetRepeatMode(message.arg1);
                            break;
                        case 25:
                            bVar.onAddQueueItem((MediaDescriptionCompat) message.obj);
                            break;
                        case 26:
                            bVar.onAddQueueItem((MediaDescriptionCompat) message.obj, message.arg1);
                            break;
                        case 27:
                            bVar.onRemoveQueueItem((MediaDescriptionCompat) message.obj);
                            break;
                        case 28:
                            List list = i.this.v;
                            if (list != null) {
                                int i = message.arg1;
                                if (i >= 0 && i < list.size()) {
                                    queueItem = (QueueItem) i.this.v.get(message.arg1);
                                } else {
                                    queueItem = null;
                                }
                                if (queueItem != null) {
                                    bVar.onRemoveQueueItem(queueItem.c());
                                    break;
                                }
                            }
                            break;
                        case 29:
                            bVar.onSetCaptioningEnabled(((Boolean) message.obj).booleanValue());
                            break;
                        case 30:
                            bVar.onSetShuffleMode(message.arg1);
                            break;
                        case 31:
                            bVar.onSetRating((RatingCompat) message.obj, bundle);
                            break;
                        case 32:
                            bVar.onSetPlaybackSpeed(((Float) message.obj).floatValue());
                            break;
                    }
                } finally {
                    i.this.p(null);
                }
            }
        }

        public i(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, s0.d dVar, Bundle bundle) {
            if (componentName != null) {
                this.a = context;
                this.f = context.getPackageName();
                this.g = bundle;
                this.i = (AudioManager) context.getSystemService("audio");
                this.h = str;
                this.b = componentName;
                this.c = pendingIntent;
                c cVar = new c();
                this.d = cVar;
                this.e = new Token(cVar, null, dVar);
                this.x = 0;
                this.C = 1;
                this.D = 3;
                this.j = new RemoteControlClient(pendingIntent);
                return;
            }
            throw new IllegalArgumentException("MediaButtonReceiver component may not be null");
        }

        public void A(ParcelableVolumeInfo parcelableVolumeInfo) {
            for (int beginBroadcast = this.l.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((android.support.v4.media.session.a) this.l.getBroadcastItem(beginBroadcast)).h0(parcelableVolumeInfo);
                } catch (RemoteException unused) {
                }
            }
            this.l.finishBroadcast();
        }

        public abstract void B(PlaybackStateCompat playbackStateCompat);

        public void C(int i, int i2) {
            if (this.C == 2) {
                e0.l lVar = this.E;
                if (lVar != null) {
                    lVar.f(i);
                    return;
                }
                return;
            }
            this.i.setStreamVolume(this.D, i, i2);
        }

        public void D(PendingIntent pendingIntent, ComponentName componentName) {
            this.i.unregisterMediaButtonEventReceiver(componentName);
        }

        public void E() {
            if (this.o) {
                w(this.c, this.b);
                this.i.registerRemoteControlClient(this.j);
                j(this.f0s);
                n(this.t);
                return;
            }
            D(this.c, this.b);
            this.j.setPlaybackState(0);
            this.i.unregisterRemoteControlClient(this.j);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public boolean a() {
            return this.o;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public Token b() {
            return this.e;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public String c() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void d(PendingIntent pendingIntent) {
            synchronized (this.k) {
                this.u = pendingIntent;
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public PlaybackStateCompat e() {
            PlaybackStateCompat playbackStateCompat;
            synchronized (this.k) {
                playbackStateCompat = this.t;
            }
            return playbackStateCompat;
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0030 A[Catch: all -> 0x0037, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0008, B:10:0x0010, B:12:0x001b, B:14:0x0021, B:16:0x0025, B:17:0x002a, B:19:0x0030, B:20:0x0035), top: B:25:0x0003 }] */
        @Override // android.support.v4.media.session.MediaSessionCompat.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void f(android.support.v4.media.session.MediaSessionCompat.b r5, android.os.Handler r6) {
            /*
                r4 = this;
                java.lang.Object r0 = r4.k
                monitor-enter(r0)
                android.support.v4.media.session.MediaSessionCompat$i$d r1 = r4.m     // Catch: java.lang.Throwable -> L37
                r2 = 0
                if (r1 == 0) goto Lb
                r1.removeCallbacksAndMessages(r2)     // Catch: java.lang.Throwable -> L37
            Lb:
                if (r5 == 0) goto L1a
                if (r6 != 0) goto L10
                goto L1a
            L10:
                android.support.v4.media.session.MediaSessionCompat$i$d r1 = new android.support.v4.media.session.MediaSessionCompat$i$d     // Catch: java.lang.Throwable -> L37
                android.os.Looper r3 = r6.getLooper()     // Catch: java.lang.Throwable -> L37
                r1.<init>(r3)     // Catch: java.lang.Throwable -> L37
                goto L1b
            L1a:
                r1 = r2
            L1b:
                r4.m = r1     // Catch: java.lang.Throwable -> L37
                android.support.v4.media.session.MediaSessionCompat$b r1 = r4.p     // Catch: java.lang.Throwable -> L37
                if (r1 == r5) goto L2a
                android.support.v4.media.session.MediaSessionCompat$b r1 = r4.p     // Catch: java.lang.Throwable -> L37
                if (r1 == 0) goto L2a
                android.support.v4.media.session.MediaSessionCompat$b r1 = r4.p     // Catch: java.lang.Throwable -> L37
                r1.setSessionImpl(r2, r2)     // Catch: java.lang.Throwable -> L37
            L2a:
                r4.p = r5     // Catch: java.lang.Throwable -> L37
                android.support.v4.media.session.MediaSessionCompat$b r5 = r4.p     // Catch: java.lang.Throwable -> L37
                if (r5 == 0) goto L35
                android.support.v4.media.session.MediaSessionCompat$b r5 = r4.p     // Catch: java.lang.Throwable -> L37
                r5.setSessionImpl(r4, r6)     // Catch: java.lang.Throwable -> L37
            L35:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L37
                return
            L37:
                r5 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L37
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.MediaSessionCompat.i.f(android.support.v4.media.session.MediaSessionCompat$b, android.os.Handler):void");
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void g(e0.l lVar) {
            if (lVar != null) {
                e0.l lVar2 = this.E;
                if (lVar2 != null) {
                    lVar2.g((l.c) null);
                }
                this.C = 2;
                this.E = lVar;
                A(new ParcelableVolumeInfo(this.C, this.D, this.E.c(), this.E.b(), this.E.a()));
                lVar.g(this.F);
                return;
            }
            throw new IllegalArgumentException("volumeProvider may not be null");
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void h(int i) {
            e0.l lVar = this.E;
            if (lVar != null) {
                lVar.g((l.c) null);
            }
            this.D = i;
            this.C = 1;
            int i2 = this.C;
            int i3 = this.D;
            A(new ParcelableVolumeInfo(i2, i3, 2, this.i.getStreamMaxVolume(i3), this.i.getStreamVolume(this.D)));
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public b i() {
            b bVar;
            synchronized (this.k) {
                bVar = this.p;
            }
            return bVar;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void j(MediaMetadataCompat mediaMetadataCompat) {
            Bundle d2;
            if (mediaMetadataCompat != null) {
                mediaMetadataCompat = new MediaMetadataCompat.b(mediaMetadataCompat, MediaSessionCompat.d).a();
            }
            synchronized (this.k) {
                this.f0s = mediaMetadataCompat;
            }
            x(mediaMetadataCompat);
            if (!this.o) {
                return;
            }
            if (mediaMetadataCompat == null) {
                d2 = null;
            } else {
                d2 = mediaMetadataCompat.d();
            }
            r(d2).apply();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void k(PendingIntent pendingIntent) {
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public Object l() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void m(boolean z) {
            if (z == this.o) {
                return;
            }
            this.o = z;
            E();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void n(PlaybackStateCompat playbackStateCompat) {
            synchronized (this.k) {
                this.t = playbackStateCompat;
            }
            z(playbackStateCompat);
            if (!this.o) {
                return;
            }
            if (playbackStateCompat == null) {
                this.j.setPlaybackState(0);
                this.j.setTransportControlFlags(0);
                return;
            }
            B(playbackStateCompat);
            this.j.setTransportControlFlags(u(playbackStateCompat.b()));
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public e0.d o() {
            e0.d dVar;
            synchronized (this.k) {
                dVar = this.q;
            }
            return dVar;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void p(e0.d dVar) {
            synchronized (this.k) {
                this.q = dVar;
            }
        }

        public void q(int i, int i2) {
            if (this.C == 2) {
                e0.l lVar = this.E;
                if (lVar != null) {
                    lVar.e(i);
                    return;
                }
                return;
            }
            this.i.adjustStreamVolume(this.D, i, i2);
        }

        public RemoteControlClient.MetadataEditor r(Bundle bundle) {
            RemoteControlClient.MetadataEditor editMetadata = this.j.editMetadata(true);
            if (bundle == null) {
                return editMetadata;
            }
            if (bundle.containsKey("android.media.metadata.ART")) {
                Bitmap bitmap = (Bitmap) bundle.getParcelable("android.media.metadata.ART");
                if (bitmap != null) {
                    bitmap = bitmap.copy(bitmap.getConfig(), false);
                }
                editMetadata.putBitmap(100, bitmap);
            } else if (bundle.containsKey("android.media.metadata.ALBUM_ART")) {
                Bitmap bitmap2 = (Bitmap) bundle.getParcelable("android.media.metadata.ALBUM_ART");
                if (bitmap2 != null) {
                    bitmap2 = bitmap2.copy(bitmap2.getConfig(), false);
                }
                editMetadata.putBitmap(100, bitmap2);
            }
            if (bundle.containsKey("android.media.metadata.ALBUM")) {
                editMetadata.putString(1, bundle.getString("android.media.metadata.ALBUM"));
            }
            if (bundle.containsKey("android.media.metadata.ALBUM_ARTIST")) {
                editMetadata.putString(13, bundle.getString("android.media.metadata.ALBUM_ARTIST"));
            }
            if (bundle.containsKey("android.media.metadata.ARTIST")) {
                editMetadata.putString(2, bundle.getString("android.media.metadata.ARTIST"));
            }
            if (bundle.containsKey("android.media.metadata.AUTHOR")) {
                editMetadata.putString(3, bundle.getString("android.media.metadata.AUTHOR"));
            }
            if (bundle.containsKey("android.media.metadata.COMPILATION")) {
                editMetadata.putString(15, bundle.getString("android.media.metadata.COMPILATION"));
            }
            if (bundle.containsKey("android.media.metadata.COMPOSER")) {
                editMetadata.putString(4, bundle.getString("android.media.metadata.COMPOSER"));
            }
            if (bundle.containsKey("android.media.metadata.DATE")) {
                editMetadata.putString(5, bundle.getString("android.media.metadata.DATE"));
            }
            if (bundle.containsKey("android.media.metadata.DISC_NUMBER")) {
                editMetadata.putLong(14, bundle.getLong("android.media.metadata.DISC_NUMBER"));
            }
            if (bundle.containsKey("android.media.metadata.DURATION")) {
                editMetadata.putLong(9, bundle.getLong("android.media.metadata.DURATION"));
            }
            if (bundle.containsKey("android.media.metadata.GENRE")) {
                editMetadata.putString(6, bundle.getString("android.media.metadata.GENRE"));
            }
            if (bundle.containsKey("android.media.metadata.TITLE")) {
                editMetadata.putString(7, bundle.getString("android.media.metadata.TITLE"));
            }
            if (bundle.containsKey("android.media.metadata.TRACK_NUMBER")) {
                editMetadata.putLong(0, bundle.getLong("android.media.metadata.TRACK_NUMBER"));
            }
            if (bundle.containsKey("android.media.metadata.WRITER")) {
                editMetadata.putString(11, bundle.getString("android.media.metadata.WRITER"));
            }
            return editMetadata;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void release() {
            this.o = false;
            this.n = true;
            E();
            y();
            f(null, null);
        }

        public String s(int i) {
            String nameForUid = this.a.getPackageManager().getNameForUid(i);
            if (TextUtils.isEmpty(nameForUid)) {
                return "android.media.session.MediaController";
            }
            return nameForUid;
        }

        public int t(int i) {
            switch (i) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
                case 6:
                case 8:
                    return 8;
                case 7:
                    return 9;
                case 9:
                    return 7;
                case 10:
                case 11:
                    return 6;
                default:
                    return -1;
            }
        }

        public int u(long j) {
            int i = (1 & j) != 0 ? 32 : 0;
            if ((2 & j) != 0) {
                i |= 16;
            }
            if ((4 & j) != 0) {
                i |= 4;
            }
            if ((8 & j) != 0) {
                i |= 2;
            }
            if ((16 & j) != 0) {
                i |= 1;
            }
            if ((32 & j) != 0) {
                i |= 128;
            }
            if ((64 & j) != 0) {
                i |= 64;
            }
            return (j & 512) != 0 ? i | 8 : i;
        }

        public void v(int i, int i2, int i3, Object obj, Bundle bundle) {
            synchronized (this.k) {
                d dVar = this.m;
                if (dVar != null) {
                    Message obtainMessage = dVar.obtainMessage(i, i2, i3, obj);
                    Bundle bundle2 = new Bundle();
                    int callingUid = Binder.getCallingUid();
                    bundle2.putInt("data_calling_uid", callingUid);
                    bundle2.putString("data_calling_pkg", s(callingUid));
                    int callingPid = Binder.getCallingPid();
                    if (callingPid > 0) {
                        bundle2.putInt("data_calling_pid", callingPid);
                    } else {
                        bundle2.putInt("data_calling_pid", -1);
                    }
                    if (bundle != null) {
                        bundle2.putBundle("data_extras", bundle);
                    }
                    obtainMessage.setData(bundle2);
                    obtainMessage.sendToTarget();
                }
            }
        }

        public void w(PendingIntent pendingIntent, ComponentName componentName) {
            this.i.registerMediaButtonEventReceiver(componentName);
        }

        public final void x(MediaMetadataCompat mediaMetadataCompat) {
            for (int beginBroadcast = this.l.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((android.support.v4.media.session.a) this.l.getBroadcastItem(beginBroadcast)).M(mediaMetadataCompat);
                } catch (RemoteException unused) {
                }
            }
            this.l.finishBroadcast();
        }

        public final void y() {
            for (int beginBroadcast = this.l.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((android.support.v4.media.session.a) this.l.getBroadcastItem(beginBroadcast)).K();
                } catch (RemoteException unused) {
                }
            }
            this.l.finishBroadcast();
            this.l.kill();
        }

        public final void z(PlaybackStateCompat playbackStateCompat) {
            for (int beginBroadcast = this.l.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((android.support.v4.media.session.a) this.l.getBroadcastItem(beginBroadcast)).f0(playbackStateCompat);
                } catch (RemoteException unused) {
                }
            }
            this.l.finishBroadcast();
        }
    }

    /* loaded from: classes.dex */
    public interface j {
        void a();
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
        this(context, str, componentName, pendingIntent, null);
    }

    public static void c(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
        }
    }

    public static PlaybackStateCompat g(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat) {
        long c2;
        long j2;
        if (playbackStateCompat != null) {
            long j3 = -1;
            if (playbackStateCompat.f() != -1) {
                if (playbackStateCompat.g() == 3 || playbackStateCompat.g() == 4 || playbackStateCompat.g() == 5) {
                    if (playbackStateCompat.c() > 0) {
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        long d2 = (playbackStateCompat.d() * ((float) (elapsedRealtime - c2))) + playbackStateCompat.f();
                        if (mediaMetadataCompat != null && mediaMetadataCompat.a("android.media.metadata.DURATION")) {
                            j3 = mediaMetadataCompat.f("android.media.metadata.DURATION");
                        }
                        if (j3 >= 0 && d2 > j3) {
                            j2 = j3;
                        } else if (d2 < 0) {
                            j2 = 0;
                        } else {
                            j2 = d2;
                        }
                        return new PlaybackStateCompat.b(playbackStateCompat).d(playbackStateCompat.g(), j2, playbackStateCompat.d(), elapsedRealtime).a();
                    }
                    return playbackStateCompat;
                }
                return playbackStateCompat;
            }
            return playbackStateCompat;
        }
        return playbackStateCompat;
    }

    public static Bundle s(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        c(bundle);
        try {
            bundle.isEmpty();
            return bundle;
        } catch (BadParcelableException unused) {
            Log.e("MediaSessionCompat", "Could not unparcel the data.");
            return null;
        }
    }

    public void a(j jVar) {
        if (jVar != null) {
            this.c.add(jVar);
            return;
        }
        throw new IllegalArgumentException("Listener may not be null");
    }

    public final MediaSession b(Context context, String str, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 29) {
            return new MediaSession(context, str, bundle);
        }
        return new MediaSession(context, str);
    }

    public MediaControllerCompat d() {
        return this.b;
    }

    public Object e() {
        return this.a.l();
    }

    public Token f() {
        return this.a.b();
    }

    public boolean h() {
        return this.a.a();
    }

    public void i() {
        this.a.release();
    }

    public void j(j jVar) {
        if (jVar != null) {
            this.c.remove(jVar);
            return;
        }
        throw new IllegalArgumentException("Listener may not be null");
    }

    public void k(boolean z) {
        this.a.m(z);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((j) it.next()).a();
        }
    }

    public void l(b bVar) {
        m(bVar, null);
    }

    public void m(b bVar, Handler handler) {
        if (bVar == null) {
            this.a.f(null, null);
            return;
        }
        c cVar = this.a;
        if (handler == null) {
            handler = new Handler();
        }
        cVar.f(bVar, handler);
    }

    public void n(MediaMetadataCompat mediaMetadataCompat) {
        this.a.j(mediaMetadataCompat);
    }

    public void o(PlaybackStateCompat playbackStateCompat) {
        this.a.n(playbackStateCompat);
    }

    public void p(int i2) {
        this.a.h(i2);
    }

    public void q(e0.l lVar) {
        if (lVar != null) {
            this.a.g(lVar);
            return;
        }
        throw new IllegalArgumentException("volumeProvider may not be null!");
    }

    public void r(PendingIntent pendingIntent) {
        this.a.d(pendingIntent);
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, Bundle bundle) {
        this(context, str, componentName, pendingIntent, bundle, null);
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, Bundle bundle, s0.d dVar) {
        this.c = new ArrayList();
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                ComponentName a2 = componentName == null ? g0.a.a(context) : componentName;
                if (a2 != null && pendingIntent == null) {
                    Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                    intent.setComponent(a2);
                    pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                }
                PendingIntent pendingIntent2 = pendingIntent;
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 21) {
                    MediaSession b2 = b(context, str, bundle);
                    if (i2 >= 29) {
                        this.a = new h(b2, dVar, bundle);
                    } else if (i2 >= 28) {
                        this.a = new g(b2, dVar, bundle);
                    } else {
                        this.a = new f(b2, dVar, bundle);
                    }
                    m(new a(), new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()));
                    this.a.k(pendingIntent2);
                } else {
                    this.a = new e(context, str, a2, pendingIntent2, dVar, bundle);
                }
                this.b = new MediaControllerCompat(context, this);
                if (d == 0) {
                    d = (int) (TypedValue.applyDimension(1, 320.0f, context.getResources().getDisplayMetrics()) + 0.5f);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("tag must not be null or empty");
        }
        throw new IllegalArgumentException("context must not be null");
    }

    @SuppressLint({"BanParcelableUsage"})
    /* loaded from: classes.dex */
    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = new a();
        public final MediaDescriptionCompat a;
        public final long b;
        public MediaSession.QueueItem c;

        /* loaded from: classes.dex */
        public class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public QueueItem[] newArray(int i) {
                return new QueueItem[i];
            }
        }

        public QueueItem(MediaSession.QueueItem queueItem, MediaDescriptionCompat mediaDescriptionCompat, long j) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null");
            }
            if (j != -1) {
                this.a = mediaDescriptionCompat;
                this.b = j;
                this.c = queueItem;
                return;
            }
            throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
        }

        public static QueueItem a(Object obj) {
            MediaDescription description;
            long queueId;
            if (obj != null && Build.VERSION.SDK_INT >= 21) {
                MediaSession.QueueItem a2 = k0.a(obj);
                description = a2.getDescription();
                MediaDescriptionCompat a3 = MediaDescriptionCompat.a(description);
                queueId = a2.getQueueId();
                return new QueueItem(a2, a3, queueId);
            }
            return null;
        }

        public static List b(List list) {
            if (list != null && Build.VERSION.SDK_INT >= 21) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    arrayList.add(a(obj));
                }
                return arrayList;
            }
            return null;
        }

        public MediaDescriptionCompat c() {
            return this.a;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.a + ", Id=" + this.b + " }";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            this.a.writeToParcel(parcel, i);
            parcel.writeLong(this.b);
        }

        public QueueItem(Parcel parcel) {
            this.a = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.b = parcel.readLong();
        }
    }
}
