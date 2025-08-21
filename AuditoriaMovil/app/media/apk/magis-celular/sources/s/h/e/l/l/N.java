package s.h.e.l.l;

import android.app.Application;
import android.content.pm.ApplicationInfo;
/* loaded from: classes.dex */
public final class N {
    static boolean la;

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f A[Catch: Throwable -> 0x0085, TryCatch #8 {Throwable -> 0x0085, blocks: (B:9:0x001b, B:11:0x001f, B:13:0x003b, B:31:0x0070, B:33:0x0076, B:35:0x007f, B:39:0x0087, B:41:0x0090), top: B:70:0x001b }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0070 A[Catch: Throwable -> 0x0085, TRY_ENTER, TryCatch #8 {Throwable -> 0x0085, blocks: (B:9:0x001b, B:11:0x001f, B:13:0x003b, B:31:0x0070, B:33:0x0076, B:35:0x007f, B:39:0x0087, B:41:0x0090), top: B:70:0x001b }] */
    static {
        /*
            r1 = 0
            r0 = 1
            s.h.e.l.l.N.la = r0
            s.h.e.l.l.N.la = r1
            r3 = 0
            java.lang.Object r0 = new java.lang.Object     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L63
            r0.<init>()     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L63
            java.lang.Object r2 = new java.lang.Object     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb7
            r2.<init>()     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb7
            if (r0 == 0) goto L16
            r0.hashCode()     // Catch: java.lang.Exception -> Lac
        L16:
            if (r2 == 0) goto L1b
            r2.hashCode()     // Catch: java.lang.Exception -> Laf
        L1b:
            boolean r0 = s.h.e.l.l.N.la     // Catch: java.lang.Throwable -> L85
            if (r0 == 0) goto L70
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L85
            java.lang.String r1 = s.h.e.l.l.S.p     // Catch: java.lang.Throwable -> L85
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L85
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L85
            java.lang.String r1 = "/libexec.so"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> L85
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L85
            java.lang.System.load(r0)     // Catch: java.lang.Throwable -> L85
            boolean r0 = s.h.e.l.l.S.m     // Catch: java.lang.Throwable -> L85
            if (r0 == 0) goto L53
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L85
            java.lang.String r1 = s.h.e.l.l.S.p     // Catch: java.lang.Throwable -> L85
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L85
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L85
            java.lang.String r1 = "/libexecmain.so"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> L85
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L85
            java.lang.System.load(r0)     // Catch: java.lang.Throwable -> L85
        L53:
            return
        L54:
            r0 = move-exception
            r0 = r1
        L56:
            if (r0 == 0) goto L5b
            r0.hashCode()     // Catch: java.lang.Exception -> La6
        L5b:
            if (r1 == 0) goto L1b
            r3.hashCode()     // Catch: java.lang.Exception -> L61
            goto L1b
        L61:
            r0 = move-exception
            goto L1b
        L63:
            r0 = move-exception
            r2 = r1
        L65:
            if (r2 == 0) goto L6a
            r2.hashCode()     // Catch: java.lang.Exception -> La8
        L6a:
            if (r1 == 0) goto L6f
            r3.hashCode()     // Catch: java.lang.Exception -> Laa
        L6f:
            throw r0
        L70:
            boolean r0 = x()     // Catch: java.lang.Throwable -> L85
            if (r0 != 0) goto L87
            java.lang.String r0 = "exec"
            java.lang.System.loadLibrary(r0)     // Catch: java.lang.Throwable -> L85
            boolean r0 = s.h.e.l.l.S.m     // Catch: java.lang.Throwable -> L85
            if (r0 == 0) goto L53
            java.lang.String r0 = "execmain"
            java.lang.System.loadLibrary(r0)     // Catch: java.lang.Throwable -> L85
            goto L53
        L85:
            r0 = move-exception
            goto L53
        L87:
            java.lang.String r0 = "exec_x86"
            java.lang.System.loadLibrary(r0)     // Catch: java.lang.Throwable -> L96
            boolean r0 = s.h.e.l.l.S.m     // Catch: java.lang.Throwable -> L96
            if (r0 == 0) goto L53
            java.lang.String r0 = "execmain_x86"
            java.lang.System.loadLibrary(r0)     // Catch: java.lang.Throwable -> L96
            goto L53
        L96:
            r0 = move-exception
            java.lang.String r0 = "exec"
            java.lang.System.loadLibrary(r0)     // Catch: java.lang.Throwable -> L85
            boolean r0 = s.h.e.l.l.S.m     // Catch: java.lang.Throwable -> L85
            if (r0 == 0) goto L53
            java.lang.String r0 = "execmain"
            java.lang.System.loadLibrary(r0)     // Catch: java.lang.Throwable -> L85
            goto L53
        La6:
            r0 = move-exception
            goto L5b
        La8:
            r2 = move-exception
            goto L6a
        Laa:
            r1 = move-exception
            goto L6f
        Lac:
            r0 = move-exception
            goto L16
        Laf:
            r0 = move-exception
            goto L1b
        Lb2:
            r2 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
            goto L65
        Lb7:
            r2 = move-exception
            goto L56
        */
        throw new UnsupportedOperationException("Method not decompiled: s.h.e.l.l.N.<clinit>():void");
    }

    public static native ClassLoader al(ClassLoader classLoader, ApplicationInfo applicationInfo, String str, String str2);

    public static native byte[] b2b(byte[] bArr, int i);

    public static native boolean l(Application application, String str);

    public static native void m(String str, int i);

    public static native boolean r(Application application, String str);

    public static native boolean ra(Application application, String str);

    public static native void sa(String str, String str2);

    /* JADX WARN: Can't wrap try/catch for region: R(12:1|(2:2|3)|(4:5|6|(2:33|34)|(2:29|30))|9|10|11|12|13|(2:15|16)|(2:21|(1:23))|26|(1:(0))) */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002c A[Catch: Exception -> 0x0074, TRY_LEAVE, TryCatch #9 {Exception -> 0x0074, blocks: (B:11:0x001c, B:13:0x002c), top: B:67:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003b A[Catch: Exception -> 0x0067, TRY_ENTER, TRY_LEAVE, TryCatch #7 {Exception -> 0x0067, blocks: (B:9:0x0016, B:16:0x003b), top: B:65:0x0016 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean x() {
        /*
            r1 = 0
            r3 = 0
            java.lang.Object r0 = new java.lang.Object     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L54
            r0.<init>()     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L54
            java.lang.Object r2 = new java.lang.Object     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            r2.<init>()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            if (r0 == 0) goto L11
            r0.hashCode()     // Catch: java.lang.Exception -> L70
        L11:
            if (r2 == 0) goto L16
            r2.hashCode()     // Catch: java.lang.Exception -> L72
        L16:
            java.lang.String r0 = s.h.e.l.l.S.a()     // Catch: java.lang.Exception -> L67
            r1 = 20
            byte[] r1 = new byte[r1]     // Catch: java.lang.Exception -> L74
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L74
            java.io.File r3 = new java.io.File     // Catch: java.lang.Exception -> L74
            java.lang.String r4 = "/system/bin/linker"
            r3.<init>(r4)     // Catch: java.lang.Exception -> L74
            r2.<init>(r3)     // Catch: java.lang.Exception -> L74
            if (r2 == 0) goto L39
            r2.read(r1)     // Catch: java.lang.Exception -> L74
            r2.close()     // Catch: java.lang.Exception -> L74
            r2 = 18
            r1 = r1[r2]     // Catch: java.lang.Exception -> L74
            switch(r1) {
                case 3: goto L61;
                case 40: goto L64;
                default: goto L39;
            }
        L39:
            if (r0 == 0) goto L68
            java.lang.String r1 = "x86"
            boolean r0 = r0.contains(r1)     // Catch: java.lang.Exception -> L67
            if (r0 == 0) goto L68
            r0 = 1
        L44:
            return r0
        L45:
            r0 = move-exception
            r0 = r1
        L47:
            if (r0 == 0) goto L4c
            r0.hashCode()     // Catch: java.lang.Exception -> L6a
        L4c:
            if (r1 == 0) goto L16
            r3.hashCode()     // Catch: java.lang.Exception -> L52
            goto L16
        L52:
            r0 = move-exception
            goto L16
        L54:
            r0 = move-exception
            r2 = r1
        L56:
            if (r2 == 0) goto L5b
            r2.hashCode()     // Catch: java.lang.Exception -> L6c
        L5b:
            if (r1 == 0) goto L60
            r3.hashCode()     // Catch: java.lang.Exception -> L6e
        L60:
            throw r0
        L61:
            java.lang.String r0 = "x86"
            goto L39
        L64:
            java.lang.String r0 = "armeabi"
            goto L39
        L67:
            r0 = move-exception
        L68:
            r0 = 0
            goto L44
        L6a:
            r0 = move-exception
            goto L4c
        L6c:
            r2 = move-exception
            goto L5b
        L6e:
            r1 = move-exception
            goto L60
        L70:
            r0 = move-exception
            goto L11
        L72:
            r0 = move-exception
            goto L16
        L74:
            r1 = move-exception
            goto L39
        L76:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L56
        L7b:
            r2 = move-exception
            goto L47
        */
        throw new UnsupportedOperationException("Method not decompiled: s.h.e.l.l.N.x():boolean");
    }
}
