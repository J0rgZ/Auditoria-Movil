package androidx.media;

import e0.a;
import s0.b;
/* loaded from: classes.dex */
public class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(b bVar) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        audioAttributesImplApi21.a = a.a(bVar.r(audioAttributesImplApi21.a, 1));
        audioAttributesImplApi21.b = bVar.p(audioAttributesImplApi21.b, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, b bVar) {
        bVar.x(false, false);
        bVar.H(audioAttributesImplApi21.a, 1);
        bVar.F(audioAttributesImplApi21.b, 2);
    }
}
