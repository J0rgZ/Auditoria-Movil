package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new a();
    public final int a;
    public final long b;
    public final long c;
    public final float d;
    public final long e;
    public final int f;
    public final CharSequence g;
    public final long h;
    public List i;
    public final long j;
    public final Bundle k;
    public PlaybackState l;

    /* loaded from: classes.dex */
    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public PlaybackStateCompat createFromParcel(Parcel parcel) {
            return new PlaybackStateCompat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public PlaybackStateCompat[] newArray(int i) {
            return new PlaybackStateCompat[i];
        }
    }

    public PlaybackStateCompat(int i, long j, long j2, float f, long j3, int i2, CharSequence charSequence, long j4, List list, long j5, Bundle bundle) {
        this.a = i;
        this.b = j;
        this.c = j2;
        this.d = f;
        this.e = j3;
        this.f = i2;
        this.g = charSequence;
        this.h = j4;
        this.i = new ArrayList(list);
        this.j = j5;
        this.k = bundle;
    }

    public static PlaybackStateCompat a(Object obj) {
        List<Object> customActions;
        ArrayList arrayList;
        int state;
        long position;
        long bufferedPosition;
        float playbackSpeed;
        long actions;
        CharSequence errorMessage;
        long lastPositionUpdateTime;
        long activeQueueItemId;
        Bundle bundle = null;
        if (obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        PlaybackState a2 = u.a(obj);
        customActions = a2.getCustomActions();
        if (customActions != null) {
            ArrayList arrayList2 = new ArrayList(customActions.size());
            for (Object obj2 : customActions) {
                arrayList2.add(CustomAction.a(obj2));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        if (Build.VERSION.SDK_INT >= 22) {
            bundle = a2.getExtras();
            MediaSessionCompat.c(bundle);
        }
        state = a2.getState();
        position = a2.getPosition();
        bufferedPosition = a2.getBufferedPosition();
        playbackSpeed = a2.getPlaybackSpeed();
        actions = a2.getActions();
        errorMessage = a2.getErrorMessage();
        lastPositionUpdateTime = a2.getLastPositionUpdateTime();
        activeQueueItemId = a2.getActiveQueueItemId();
        PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(state, position, bufferedPosition, playbackSpeed, actions, 0, errorMessage, lastPositionUpdateTime, arrayList, activeQueueItemId, bundle);
        playbackStateCompat.l = a2;
        return playbackStateCompat;
    }

    public long b() {
        return this.e;
    }

    public long c() {
        return this.h;
    }

    public float d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Object e() {
        PlaybackState build;
        if (this.l == null && Build.VERSION.SDK_INT >= 21) {
            PlaybackState.Builder builder = new PlaybackState.Builder();
            builder.setState(this.a, this.b, this.d, this.h);
            builder.setBufferedPosition(this.c);
            builder.setActions(this.e);
            builder.setErrorMessage(this.g);
            for (CustomAction customAction : this.i) {
                builder.addCustomAction(s0.a(customAction.b()));
            }
            builder.setActiveQueueItemId(this.j);
            if (Build.VERSION.SDK_INT >= 22) {
                builder.setExtras(this.k);
            }
            build = builder.build();
            this.l = build;
        }
        return this.l;
    }

    public long f() {
        return this.b;
    }

    public int g() {
        return this.a;
    }

    public String toString() {
        return "PlaybackState {state=" + this.a + ", position=" + this.b + ", buffered position=" + this.c + ", speed=" + this.d + ", updated=" + this.h + ", actions=" + this.e + ", error code=" + this.f + ", error message=" + this.g + ", custom actions=" + this.i + ", active item id=" + this.j + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeLong(this.b);
        parcel.writeFloat(this.d);
        parcel.writeLong(this.h);
        parcel.writeLong(this.c);
        parcel.writeLong(this.e);
        TextUtils.writeToParcel(this.g, parcel, i);
        parcel.writeTypedList(this.i);
        parcel.writeLong(this.j);
        parcel.writeBundle(this.k);
        parcel.writeInt(this.f);
    }

    /* loaded from: classes.dex */
    public static final class b {
        public final List a;
        public int b;
        public long c;
        public long d;
        public float e;
        public long f;
        public int g;
        public CharSequence h;
        public long i;
        public long j;
        public Bundle k;

        public b() {
            this.a = new ArrayList();
            this.j = -1L;
        }

        public PlaybackStateCompat a() {
            return new PlaybackStateCompat(this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.a, this.j, this.k);
        }

        public b b(long j) {
            this.f = j;
            return this;
        }

        public b c(int i, long j, float f) {
            return d(i, j, f, SystemClock.elapsedRealtime());
        }

        public b d(int i, long j, float f, long j2) {
            this.b = i;
            this.c = j;
            this.i = j2;
            this.e = f;
            return this;
        }

        public b(PlaybackStateCompat playbackStateCompat) {
            ArrayList arrayList = new ArrayList();
            this.a = arrayList;
            this.j = -1L;
            this.b = playbackStateCompat.a;
            this.c = playbackStateCompat.b;
            this.e = playbackStateCompat.d;
            this.i = playbackStateCompat.h;
            this.d = playbackStateCompat.c;
            this.f = playbackStateCompat.e;
            this.g = playbackStateCompat.f;
            this.h = playbackStateCompat.g;
            List list = playbackStateCompat.i;
            if (list != null) {
                arrayList.addAll(list);
            }
            this.j = playbackStateCompat.j;
            this.k = playbackStateCompat.k;
        }
    }

    /* loaded from: classes.dex */
    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new a();
        public final String a;
        public final CharSequence b;
        public final int c;
        public final Bundle d;
        public PlaybackState.CustomAction e;

        /* loaded from: classes.dex */
        public class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public CustomAction createFromParcel(Parcel parcel) {
                return new CustomAction(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public CustomAction[] newArray(int i) {
                return new CustomAction[i];
            }
        }

        public CustomAction(String str, CharSequence charSequence, int i, Bundle bundle) {
            this.a = str;
            this.b = charSequence;
            this.c = i;
            this.d = bundle;
        }

        public static CustomAction a(Object obj) {
            Bundle extras;
            String action;
            CharSequence name;
            int icon;
            if (obj != null && Build.VERSION.SDK_INT >= 21) {
                PlaybackState.CustomAction a2 = s0.a(obj);
                extras = a2.getExtras();
                MediaSessionCompat.c(extras);
                action = a2.getAction();
                name = a2.getName();
                icon = a2.getIcon();
                CustomAction customAction = new CustomAction(action, name, icon, extras);
                customAction.e = a2;
                return customAction;
            }
            return null;
        }

        public Object b() {
            PlaybackState.CustomAction build;
            PlaybackState.CustomAction customAction = this.e;
            if (customAction == null && Build.VERSION.SDK_INT >= 21) {
                PlaybackState.CustomAction.Builder builder = new PlaybackState.CustomAction.Builder(this.a, this.b, this.c);
                builder.setExtras(this.d);
                build = builder.build();
                return build;
            }
            return customAction;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "Action:mName='" + ((Object) this.b) + ", mIcon=" + this.c + ", mExtras=" + this.d;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            TextUtils.writeToParcel(this.b, parcel, i);
            parcel.writeInt(this.c);
            parcel.writeBundle(this.d);
        }

        public CustomAction(Parcel parcel) {
            this.a = parcel.readString();
            this.b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.c = parcel.readInt();
            this.d = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        }
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readLong();
        this.d = parcel.readFloat();
        this.h = parcel.readLong();
        this.c = parcel.readLong();
        this.e = parcel.readLong();
        this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.i = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.j = parcel.readLong();
        this.k = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        this.f = parcel.readInt();
    }
}
