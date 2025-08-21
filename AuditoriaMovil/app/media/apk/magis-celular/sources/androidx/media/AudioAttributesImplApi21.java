package androidx.media;

import android.media.AudioAttributes;
import androidx.media.AudioAttributesImpl;
import e0.b;
import e0.c;
/* loaded from: classes.dex */
public class AudioAttributesImplApi21 implements AudioAttributesImpl {
    public AudioAttributes a;
    public int b;

    /* loaded from: classes.dex */
    public static class a implements AudioAttributesImpl.a {
        public final AudioAttributes.Builder a = new AudioAttributes.Builder();

        @Override // androidx.media.AudioAttributesImpl.a
        /* renamed from: b */
        public a a(int i) {
            this.a.setLegacyStreamType(i);
            return this;
        }

        @Override // androidx.media.AudioAttributesImpl.a
        public AudioAttributesImpl build() {
            AudioAttributes build;
            build = this.a.build();
            return new AudioAttributesImplApi21(build);
        }
    }

    public AudioAttributesImplApi21() {
        this.b = -1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplApi21)) {
            return false;
        }
        return b.a(this.a, ((AudioAttributesImplApi21) obj).a);
    }

    public int hashCode() {
        return c.a(this.a);
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.a;
    }

    public AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    public AudioAttributesImplApi21(AudioAttributes audioAttributes, int i) {
        this.a = audioAttributes;
        this.b = i;
    }
}
