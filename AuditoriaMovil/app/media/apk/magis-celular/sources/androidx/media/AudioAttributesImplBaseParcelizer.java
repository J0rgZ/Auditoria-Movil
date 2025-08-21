package androidx.media;

import s0.b;
/* loaded from: classes.dex */
public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(b bVar) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.a = bVar.p(audioAttributesImplBase.a, 1);
        audioAttributesImplBase.b = bVar.p(audioAttributesImplBase.b, 2);
        audioAttributesImplBase.c = bVar.p(audioAttributesImplBase.c, 3);
        audioAttributesImplBase.d = bVar.p(audioAttributesImplBase.d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, b bVar) {
        bVar.x(false, false);
        bVar.F(audioAttributesImplBase.a, 1);
        bVar.F(audioAttributesImplBase.b, 2);
        bVar.F(audioAttributesImplBase.c, 3);
        bVar.F(audioAttributesImplBase.d, 4);
    }
}
