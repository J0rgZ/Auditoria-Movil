package androidx.media;

import android.media.AudioAttributes;
import androidx.media.AudioAttributesImplApi21;
/* loaded from: classes.dex */
public class AudioAttributesImplApi26 extends AudioAttributesImplApi21 {

    /* loaded from: classes.dex */
    public static class a extends AudioAttributesImplApi21.a {
        @Override // androidx.media.AudioAttributesImplApi21.a, androidx.media.AudioAttributesImpl.a
        public AudioAttributesImpl build() {
            AudioAttributes build;
            build = this.a.build();
            return new AudioAttributesImplApi26(build);
        }
    }

    public AudioAttributesImplApi26() {
    }

    public AudioAttributesImplApi26(AudioAttributes audioAttributes) {
        super(audioAttributes, -1);
    }
}
