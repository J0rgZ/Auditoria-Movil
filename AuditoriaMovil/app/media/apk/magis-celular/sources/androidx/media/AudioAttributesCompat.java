package androidx.media;

import android.os.Build;
import android.util.SparseIntArray;
import androidx.media.AudioAttributesImpl;
import androidx.media.AudioAttributesImplApi21;
import androidx.media.AudioAttributesImplApi26;
import androidx.media.AudioAttributesImplBase;
import s0.d;
/* loaded from: classes.dex */
public class AudioAttributesCompat implements d {
    public static final SparseIntArray b;
    public static boolean c;
    public static final int[] d;
    public AudioAttributesImpl a;

    /* loaded from: classes.dex */
    public static class a {
        public final AudioAttributesImpl.a a;

        public a() {
            if (AudioAttributesCompat.c) {
                this.a = new AudioAttributesImplBase.a();
                return;
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 26) {
                this.a = new AudioAttributesImplApi26.a();
            } else if (i >= 21) {
                this.a = new AudioAttributesImplApi21.a();
            } else {
                this.a = new AudioAttributesImplBase.a();
            }
        }

        public AudioAttributesCompat a() {
            return new AudioAttributesCompat(this.a.build());
        }

        public a b(int i) {
            this.a.a(i);
            return this;
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        b = sparseIntArray;
        sparseIntArray.put(5, 1);
        sparseIntArray.put(6, 2);
        sparseIntArray.put(7, 2);
        sparseIntArray.put(8, 1);
        sparseIntArray.put(9, 1);
        sparseIntArray.put(10, 1);
        d = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16};
    }

    public AudioAttributesCompat() {
    }

    public static int a(boolean z, int i, int i2) {
        if ((i & 1) == 1) {
            if (z) {
                return 1;
            }
            return 7;
        } else if ((i & 4) == 4) {
            if (z) {
                return 0;
            }
            return 6;
        } else {
            switch (i2) {
                case 0:
                case 1:
                case 12:
                case 14:
                case 16:
                    return 3;
                case 2:
                    return 0;
                case 3:
                    if (z) {
                        return 0;
                    }
                    return 8;
                case 4:
                    return 4;
                case 5:
                case 7:
                case 8:
                case 9:
                case 10:
                    return 5;
                case 6:
                    return 2;
                case 11:
                    return 10;
                case 13:
                    return 1;
                case 15:
                default:
                    if (!z) {
                        return 3;
                    }
                    throw new IllegalArgumentException("Unknown usage value " + i2 + " in audio attributes");
            }
        }
    }

    public static String b(int i) {
        switch (i) {
            case 0:
                return "USAGE_UNKNOWN";
            case 1:
                return "USAGE_MEDIA";
            case 2:
                return "USAGE_VOICE_COMMUNICATION";
            case 3:
                return "USAGE_VOICE_COMMUNICATION_SIGNALLING";
            case 4:
                return "USAGE_ALARM";
            case 5:
                return "USAGE_NOTIFICATION";
            case 6:
                return "USAGE_NOTIFICATION_RINGTONE";
            case 7:
                return "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
            case 8:
                return "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
            case 9:
                return "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
            case 10:
                return "USAGE_NOTIFICATION_EVENT";
            case 11:
                return "USAGE_ASSISTANCE_ACCESSIBILITY";
            case 12:
                return "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
            case 13:
                return "USAGE_ASSISTANCE_SONIFICATION";
            case 14:
                return "USAGE_GAME";
            case 15:
            default:
                return "unknown usage " + i;
            case 16:
                return "USAGE_ASSISTANT";
        }
    }

    public static AudioAttributesCompat c(Object obj) {
        if (c) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            return new AudioAttributesCompat(new AudioAttributesImplApi26(e0.a.a(obj)));
        }
        if (i < 21) {
            return null;
        }
        return new AudioAttributesCompat(new AudioAttributesImplApi21(e0.a.a(obj)));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesCompat)) {
            return false;
        }
        AudioAttributesCompat audioAttributesCompat = (AudioAttributesCompat) obj;
        AudioAttributesImpl audioAttributesImpl = this.a;
        if (audioAttributesImpl == null) {
            if (audioAttributesCompat.a != null) {
                return false;
            }
            return true;
        }
        return audioAttributesImpl.equals(audioAttributesCompat.a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }

    public AudioAttributesCompat(AudioAttributesImpl audioAttributesImpl) {
        this.a = audioAttributesImpl;
    }
}
