package s.h.e.l.l;

import android.app.Application;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* loaded from: classes.dex */
public final class S extends Application {
    public static String p = null;
    public static Application n = null;
    public static boolean m = true;
    public static String f = null;
    public static boolean l = false;
    public static long sT = 0;
    public static long eT = 0;
    public static long tT = 0;
    public static boolean la = false;

    static {
        Object obj;
        Object obj2;
        Object obj3 = null;
        try {
            obj2 = new Object();
        } catch (Exception e) {
            obj2 = null;
        } catch (Throwable th) {
            th = th;
            obj = null;
        }
        try {
            Object obj4 = new Object();
            if (obj2 != null) {
                try {
                    obj2.hashCode();
                } catch (Exception e2) {
                }
            }
            if (obj4 != null) {
                try {
                    obj4.hashCode();
                } catch (Exception e3) {
                }
            }
        } catch (Exception e4) {
            if (obj2 != null) {
                try {
                    obj2.hashCode();
                } catch (Exception e5) {
                }
            }
            if (0 != 0) {
                try {
                    obj3.hashCode();
                } catch (Exception e6) {
                }
            }
        } catch (Throwable th2) {
            obj = obj2;
            th = th2;
            if (obj != null) {
                try {
                    obj.hashCode();
                } catch (Exception e7) {
                }
            }
            if (0 != 0) {
                try {
                    obj3.hashCode();
                } catch (Exception e8) {
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002b A[Catch: Exception -> 0x0058, TryCatch #5 {Exception -> 0x0058, blocks: (B:10:0x0018, B:12:0x002b), top: B:58:0x0018 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a() {
        /*
            r1 = 0
            r3 = 0
            java.lang.Object r0 = new java.lang.Object     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L4b
            r0.<init>()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L4b
            java.lang.Object r2 = new java.lang.Object     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8f
            r2.<init>()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8f
            if (r0 == 0) goto L11
            r0.hashCode()     // Catch: java.lang.Exception -> L86
        L11:
            if (r2 == 0) goto L16
            r2.hashCode()     // Catch: java.lang.Exception -> L88
        L16:
            java.lang.String r1 = "armeabi"
            java.lang.Class<android.os.Build> r0 = android.os.Build.class
            java.lang.String r2 = "SUPPORTED_ABIS"
            java.lang.reflect.Field r0 = r0.getDeclaredField(r2)     // Catch: java.lang.Exception -> L58
            r2 = 1
            r0.setAccessible(r2)     // Catch: java.lang.Exception -> L58
            r2 = 0
            java.lang.Object r0 = r0.get(r2)     // Catch: java.lang.Exception -> L58
            if (r0 == 0) goto L91
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch: java.lang.Exception -> L58
            java.lang.String r0 = java.util.Arrays.toString(r0)     // Catch: java.lang.Exception -> L58
            java.lang.String r2 = "x86"
            boolean r0 = r0.contains(r2)     // Catch: java.lang.Exception -> L58
            if (r0 == 0) goto L91
            java.lang.String r0 = "x86"
        L3b:
            return r0
        L3c:
            r0 = move-exception
            r0 = r1
        L3e:
            if (r0 == 0) goto L43
            r0.hashCode()     // Catch: java.lang.Exception -> L80
        L43:
            if (r1 == 0) goto L16
            r3.hashCode()     // Catch: java.lang.Exception -> L49
            goto L16
        L49:
            r0 = move-exception
            goto L16
        L4b:
            r0 = move-exception
            r2 = r1
        L4d:
            if (r2 == 0) goto L52
            r2.hashCode()     // Catch: java.lang.Exception -> L82
        L52:
            if (r1 == 0) goto L57
            r3.hashCode()     // Catch: java.lang.Exception -> L84
        L57:
            throw r0
        L58:
            r0 = move-exception
            java.lang.Class<android.os.Build> r0 = android.os.Build.class
            java.lang.String r2 = "getString"
            r3 = 1
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch: java.lang.Exception -> L7d
            r4 = 0
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r3[r4] = r5     // Catch: java.lang.Exception -> L7d
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r2, r3)     // Catch: java.lang.Exception -> L7d
            r2 = 1
            r0.setAccessible(r2)     // Catch: java.lang.Exception -> L7d
            r2 = 0
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L7d
            r4 = 0
            java.lang.String r5 = "ro.product.cpu.abi"
            r3[r4] = r5     // Catch: java.lang.Exception -> L7d
            java.lang.Object r0 = r0.invoke(r2, r3)     // Catch: java.lang.Exception -> L7d
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L7d
            goto L3b
        L7d:
            r0 = move-exception
            r0 = r1
            goto L3b
        L80:
            r0 = move-exception
            goto L43
        L82:
            r2 = move-exception
            goto L52
        L84:
            r1 = move-exception
            goto L57
        L86:
            r0 = move-exception
            goto L11
        L88:
            r0 = move-exception
            goto L16
        L8a:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L4d
        L8f:
            r2 = move-exception
            goto L3e
        L91:
            r0 = r1
            goto L3b
        */
        throw new UnsupportedOperationException("Method not decompiled: s.h.e.l.l.S.a():java.lang.String");
    }

    public static void c(String str) throws IOException {
        Object obj;
        Object obj2;
        File file;
        File file2;
        ZipFile zipFile;
        ZipEntry entry;
        ZipEntry entry2;
        Object obj3 = null;
        try {
            obj2 = new Object();
        } catch (Exception e) {
            obj2 = null;
        } catch (Throwable th) {
            th = th;
            obj = null;
        }
        try {
            Object obj4 = new Object();
            if (obj2 != null) {
                try {
                    obj2.hashCode();
                } catch (Exception e2) {
                }
            }
            if (obj4 != null) {
                try {
                    obj4.hashCode();
                } catch (Exception e3) {
                }
            }
        } catch (Exception e4) {
            if (obj2 != null) {
                try {
                    obj2.hashCode();
                } catch (Exception e5) {
                }
            }
            if (0 != 0) {
                try {
                    obj3.hashCode();
                } catch (Exception e6) {
                }
            }
            String format = String.format("assets/ijm_lib/%s/libexec.so", str);
            String format2 = String.format("assets/ijm_lib/%s/libexecmain.so", str);
            file = new File(p, "libexec.so");
            file2 = new File(p, "libexecmain.so");
            zipFile = new ZipFile(f);
            entry = zipFile.getEntry(format);
            if (entry != null) {
                c(zipFile, entry, file);
            }
            if (m) {
                c(zipFile, entry2, file2);
            }
            zipFile.close();
        } catch (Throwable th2) {
            obj = obj2;
            th = th2;
            if (obj != null) {
                try {
                    obj.hashCode();
                } catch (Exception e7) {
                }
            }
            if (0 != 0) {
                try {
                    obj3.hashCode();
                } catch (Exception e8) {
                }
            }
            throw th;
        }
        String format3 = String.format("assets/ijm_lib/%s/libexec.so", str);
        String format22 = String.format("assets/ijm_lib/%s/libexecmain.so", str);
        file = new File(p, "libexec.so");
        file2 = new File(p, "libexecmain.so");
        zipFile = new ZipFile(f);
        entry = zipFile.getEntry(format3);
        if (entry != null && entry.getCrc() != g(file)) {
            c(zipFile, entry, file);
        }
        if (m && (entry2 = zipFile.getEntry(format22)) != null && entry2.getCrc() != g(file2)) {
            c(zipFile, entry2, file2);
        }
        zipFile.close();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0020 A[Catch: IOException -> 0x0063, TryCatch #4 {IOException -> 0x0063, blocks: (B:9:0x0016, B:11:0x0020, B:12:0x0027, B:13:0x0034, B:15:0x003b, B:34:0x005f), top: B:58:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x003b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(java.util.zip.ZipFile r6, java.util.zip.ZipEntry r7, java.io.File r8) {
        /*
            r1 = 0
            r3 = 0
            java.lang.Object r0 = new java.lang.Object     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L51
            r0.<init>()     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L51
            java.lang.Object r2 = new java.lang.Object     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L74
            r2.<init>()     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L74
            if (r0 == 0) goto L11
            r0.hashCode()     // Catch: java.lang.Exception -> L6b
        L11:
            if (r2 == 0) goto L16
            r2.hashCode()     // Catch: java.lang.Exception -> L6d
        L16:
            java.io.File r0 = r8.getParentFile()     // Catch: java.io.IOException -> L63
            boolean r0 = r0.exists()     // Catch: java.io.IOException -> L63
            if (r0 != 0) goto L27
            java.io.File r0 = r8.getParentFile()     // Catch: java.io.IOException -> L63
            r0.mkdirs()     // Catch: java.io.IOException -> L63
        L27:
            java.io.InputStream r0 = r6.getInputStream(r7)     // Catch: java.io.IOException -> L63
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.io.IOException -> L63
            r1.<init>(r8)     // Catch: java.io.IOException -> L63
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.io.IOException -> L63
        L34:
            int r3 = r0.read(r2)     // Catch: java.io.IOException -> L63
            r4 = -1
            if (r3 != r4) goto L5e
            r0.close()     // Catch: java.io.IOException -> L63
            r1.close()     // Catch: java.io.IOException -> L63
        L41:
            return
        L42:
            r0 = move-exception
            r0 = r1
        L44:
            if (r0 == 0) goto L49
            r0.hashCode()     // Catch: java.lang.Exception -> L65
        L49:
            if (r1 == 0) goto L16
            r3.hashCode()     // Catch: java.lang.Exception -> L4f
            goto L16
        L4f:
            r0 = move-exception
            goto L16
        L51:
            r0 = move-exception
            r2 = r1
        L53:
            if (r2 == 0) goto L58
            r2.hashCode()     // Catch: java.lang.Exception -> L67
        L58:
            if (r1 == 0) goto L5d
            r3.hashCode()     // Catch: java.lang.Exception -> L69
        L5d:
            throw r0
        L5e:
            r4 = 0
            r1.write(r2, r4, r3)     // Catch: java.io.IOException -> L63
            goto L34
        L63:
            r0 = move-exception
            goto L41
        L65:
            r0 = move-exception
            goto L49
        L67:
            r2 = move-exception
            goto L58
        L69:
            r1 = move-exception
            goto L5d
        L6b:
            r0 = move-exception
            goto L11
        L6d:
            r0 = move-exception
            goto L16
        L6f:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L53
        L74:
            r2 = move-exception
            goto L44
        */
        throw new UnsupportedOperationException("Method not decompiled: s.h.e.l.l.S.c(java.util.zip.ZipFile, java.util.zip.ZipEntry, java.io.File):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long g(java.io.File r8) {
        /*
            r0 = 0
            r3 = 0
            r4 = 0
            java.lang.Object r2 = new java.lang.Object     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L2e
            r2.<init>()     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L2e
            java.lang.Object r5 = new java.lang.Object     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            r5.<init>()     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            if (r2 == 0) goto L13
            r2.hashCode()     // Catch: java.lang.Exception -> L88
        L13:
            if (r5 == 0) goto L18
            r5.hashCode()     // Catch: java.lang.Exception -> L8a
        L18:
            boolean r2 = r8.exists()
            if (r2 != 0) goto L3b
        L1e:
            return r0
        L1f:
            r2 = move-exception
            r2 = r3
        L21:
            if (r2 == 0) goto L26
            r2.hashCode()     // Catch: java.lang.Exception -> L82
        L26:
            if (r3 == 0) goto L18
            r4.hashCode()     // Catch: java.lang.Exception -> L2c
            goto L18
        L2c:
            r2 = move-exception
            goto L18
        L2e:
            r0 = move-exception
            r2 = r3
        L30:
            if (r2 == 0) goto L35
            r2.hashCode()     // Catch: java.lang.Exception -> L84
        L35:
            if (r3 == 0) goto L3a
            r4.hashCode()     // Catch: java.lang.Exception -> L86
        L3a:
            throw r0
        L3b:
            java.util.zip.CRC32 r5 = new java.util.zip.CRC32
            r5.<init>()
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L66 java.lang.Throwable -> L75
            r4.<init>(r8)     // Catch: java.lang.Exception -> L66 java.lang.Throwable -> L75
            java.util.zip.CheckedInputStream r2 = new java.util.zip.CheckedInputStream     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L99
            r2.<init>(r4, r5)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L99
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L9c
        L4e:
            int r6 = r2.read(r3)     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L9c
            r7 = -1
            if (r6 != r7) goto L4e
            long r0 = r5.getValue()     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L9c
            if (r4 == 0) goto L5e
            r4.close()     // Catch: java.io.IOException -> L92
        L5e:
            if (r2 == 0) goto L1e
            r2.close()     // Catch: java.io.IOException -> L64
            goto L1e
        L64:
            r2 = move-exception
            goto L1e
        L66:
            r2 = move-exception
            r2 = r3
        L68:
            if (r2 == 0) goto L6d
            r2.close()     // Catch: java.io.IOException -> L8c
        L6d:
            if (r3 == 0) goto L1e
            r3.close()     // Catch: java.io.IOException -> L73
            goto L1e
        L73:
            r2 = move-exception
            goto L1e
        L75:
            r0 = move-exception
            r4 = r3
        L77:
            if (r4 == 0) goto L7c
            r4.close()     // Catch: java.io.IOException -> L8e
        L7c:
            if (r3 == 0) goto L81
            r3.close()     // Catch: java.io.IOException -> L90
        L81:
            throw r0
        L82:
            r2 = move-exception
            goto L26
        L84:
            r1 = move-exception
            goto L35
        L86:
            r1 = move-exception
            goto L3a
        L88:
            r2 = move-exception
            goto L13
        L8a:
            r2 = move-exception
            goto L18
        L8c:
            r2 = move-exception
            goto L6d
        L8e:
            r1 = move-exception
            goto L7c
        L90:
            r1 = move-exception
            goto L81
        L92:
            r3 = move-exception
            goto L5e
        L94:
            r0 = move-exception
            goto L77
        L96:
            r0 = move-exception
            r3 = r2
            goto L77
        L99:
            r2 = move-exception
            r2 = r4
            goto L68
        L9c:
            r3 = move-exception
            r3 = r2
            r2 = r4
            goto L68
        La0:
            r0 = move-exception
            goto L30
        La2:
            r5 = move-exception
            goto L21
        */
        throw new UnsupportedOperationException("Method not decompiled: s.h.e.l.l.S.g(java.io.File):long");
    }

    public static void gET() {
        eT = System.currentTimeMillis();
        tT = eT - sT;
    }

    public static void gST() {
        sT = System.currentTimeMillis();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:1|2|3|(4:5|6|(2:56|57)|(2:52|53))|9|10|11|(3:12|13|(2:15|(1:1)(1:19))(1:32))|21|22|(2:26|27)|24|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0073, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0074, code lost:
        r2 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x002e A[EDGE_INSN: B:100:0x002e->B:13:0x002e ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0050 A[Catch: all -> 0x0089, Exception -> 0x008b, TRY_ENTER, TryCatch #16 {Exception -> 0x008b, all -> 0x0089, blocks: (B:11:0x0028, B:32:0x0050, B:34:0x0058, B:36:0x0060), top: B:92:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0030 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean il() {
        /*
            r1 = 0
            r3 = 0
            java.lang.Object r0 = new java.lang.Object     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L43
            r0.<init>()     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L43
            java.lang.Object r2 = new java.lang.Object     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L93
            r2.<init>()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L93
            if (r0 == 0) goto L11
            r0.hashCode()     // Catch: java.lang.Exception -> L81
        L11:
            if (r2 == 0) goto L16
            r2.hashCode()     // Catch: java.lang.Exception -> L83
        L16:
            r0 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L73
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L73
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L73
            java.lang.String r5 = "/proc/self/maps"
            r4.<init>(r5)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L73
            r3.<init>(r4)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L73
            r2.<init>(r3)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L73
        L28:
            java.lang.String r1 = r2.readLine()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            if (r1 != 0) goto L50
        L2e:
            if (r2 == 0) goto L33
            r2.close()     // Catch: java.io.IOException -> L87
        L33:
            return r0
        L34:
            r0 = move-exception
            r0 = r1
        L36:
            if (r0 == 0) goto L3b
            r0.hashCode()     // Catch: java.lang.Exception -> L7b
        L3b:
            if (r1 == 0) goto L16
            r3.hashCode()     // Catch: java.lang.Exception -> L41
            goto L16
        L41:
            r0 = move-exception
            goto L16
        L43:
            r0 = move-exception
            r2 = r1
        L45:
            if (r2 == 0) goto L4a
            r2.hashCode()     // Catch: java.lang.Exception -> L7d
        L4a:
            if (r1 == 0) goto L4f
            r3.hashCode()     // Catch: java.lang.Exception -> L7f
        L4f:
            throw r0
        L50:
            java.lang.String r3 = "/lib64/libart.so"
            boolean r3 = r1.contains(r3)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            if (r3 != 0) goto L68
            java.lang.String r3 = "/lib64/libaoc.so"
            boolean r3 = r1.contains(r3)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            if (r3 != 0) goto L68
            java.lang.String r3 = "/bin/linker64"
            boolean r1 = r1.contains(r3)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            if (r1 == 0) goto L28
        L68:
            r0 = 1
            goto L2e
        L6a:
            r2 = move-exception
        L6b:
            if (r1 == 0) goto L33
            r1.close()     // Catch: java.io.IOException -> L71
            goto L33
        L71:
            r1 = move-exception
            goto L33
        L73:
            r0 = move-exception
            r2 = r1
        L75:
            if (r2 == 0) goto L7a
            r2.close()     // Catch: java.io.IOException -> L85
        L7a:
            throw r0
        L7b:
            r0 = move-exception
            goto L3b
        L7d:
            r2 = move-exception
            goto L4a
        L7f:
            r1 = move-exception
            goto L4f
        L81:
            r0 = move-exception
            goto L11
        L83:
            r0 = move-exception
            goto L16
        L85:
            r1 = move-exception
            goto L7a
        L87:
            r1 = move-exception
            goto L33
        L89:
            r0 = move-exception
            goto L75
        L8b:
            r1 = move-exception
            r1 = r2
            goto L6b
        L8e:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L45
        L93:
            r2 = move-exception
            goto L36
        */
        throw new UnsupportedOperationException("Method not decompiled: s.h.e.l.l.S.il():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001a A[Catch: Exception -> 0x0076, TryCatch #4 {Exception -> 0x0076, blocks: (B:9:0x0016, B:11:0x001a, B:12:0x0024, B:14:0x0028, B:15:0x002e, B:18:0x0040, B:20:0x0048, B:22:0x004e, B:40:0x0070, B:47:0x007f, B:49:0x0085, B:50:0x008b), top: B:73:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0028 A[Catch: Exception -> 0x0076, TryCatch #4 {Exception -> 0x0076, blocks: (B:9:0x0016, B:11:0x001a, B:12:0x0024, B:14:0x0028, B:15:0x002e, B:18:0x0040, B:20:0x0048, B:22:0x004e, B:40:0x0070, B:47:0x007f, B:49:0x0085, B:50:0x008b), top: B:73:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0085 A[Catch: Exception -> 0x0076, TryCatch #4 {Exception -> 0x0076, blocks: (B:9:0x0016, B:11:0x001a, B:12:0x0024, B:14:0x0028, B:15:0x002e, B:18:0x0040, B:20:0x0048, B:22:0x004e, B:40:0x0070, B:47:0x007f, B:49:0x0085, B:50:0x008b), top: B:73:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008b A[Catch: Exception -> 0x0076, TRY_LEAVE, TryCatch #4 {Exception -> 0x0076, blocks: (B:9:0x0016, B:11:0x001a, B:12:0x0024, B:14:0x0028, B:15:0x002e, B:18:0x0040, B:20:0x0048, B:22:0x004e, B:40:0x0070, B:47:0x007f, B:49:0x0085, B:50:0x008b), top: B:73:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void l(android.content.Context r5) {
        /*
            r1 = 0
            r3 = 0
            java.lang.Object r0 = new java.lang.Object     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L63
            r0.<init>()     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L63
            java.lang.Object r2 = new java.lang.Object     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La2
            r2.<init>()     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La2
            if (r0 == 0) goto L11
            r0.hashCode()     // Catch: java.lang.Exception -> L97
        L11:
            if (r2 == 0) goto L16
            r2.hashCode()     // Catch: java.lang.Exception -> L9a
        L16:
            java.lang.String r0 = s.h.e.l.l.S.p     // Catch: java.lang.Exception -> L76
            if (r0 != 0) goto L24
            java.io.File r0 = r5.getFilesDir()     // Catch: java.lang.Exception -> L76
            java.lang.String r0 = r0.getAbsolutePath()     // Catch: java.lang.Exception -> L76
            s.h.e.l.l.S.p = r0     // Catch: java.lang.Exception -> L76
        L24:
            java.lang.String r0 = s.h.e.l.l.S.f     // Catch: java.lang.Exception -> L76
            if (r0 != 0) goto L2e
            java.lang.String r0 = r5.getPackageCodePath()     // Catch: java.lang.Exception -> L76
            s.h.e.l.l.S.f = r0     // Catch: java.lang.Exception -> L76
        L2e:
            java.lang.String r1 = a()     // Catch: java.lang.Exception -> L76
            java.lang.String r0 = ld(r5)     // Catch: java.lang.Exception -> L76
            java.lang.String r2 = ""
            boolean r2 = r0.equals(r2)     // Catch: java.lang.Exception -> L76
            if (r2 != 0) goto La4
        L3e:
            if (r0 == 0) goto L7f
            java.lang.String r1 = "x86"
            boolean r0 = r0.contains(r1)     // Catch: java.lang.Exception -> L76
            if (r0 == 0) goto L7f
            boolean r0 = il()     // Catch: java.lang.Exception -> L76
            if (r0 == 0) goto L70
            java.lang.String r0 = "x86_64"
            c(r0)     // Catch: java.lang.Exception -> L76
        L53:
            return
        L54:
            r0 = move-exception
            r0 = r1
        L56:
            if (r0 == 0) goto L5b
            r0.hashCode()     // Catch: java.lang.Exception -> L91
        L5b:
            if (r1 == 0) goto L16
            r3.hashCode()     // Catch: java.lang.Exception -> L61
            goto L16
        L61:
            r0 = move-exception
            goto L16
        L63:
            r0 = move-exception
            r2 = r1
        L65:
            if (r2 == 0) goto L6a
            r2.hashCode()     // Catch: java.lang.Exception -> L93
        L6a:
            if (r1 == 0) goto L6f
            r3.hashCode()     // Catch: java.lang.Exception -> L95
        L6f:
            throw r0
        L70:
            java.lang.String r0 = "x86"
            c(r0)     // Catch: java.lang.Exception -> L76
            goto L53
        L76:
            r0 = move-exception
            java.lang.String r0 = "armeabi"
            c(r0)     // Catch: java.lang.Exception -> L7d
            goto L53
        L7d:
            r0 = move-exception
            goto L53
        L7f:
            boolean r0 = il()     // Catch: java.lang.Exception -> L76
            if (r0 == 0) goto L8b
            java.lang.String r0 = "arm64-v8a"
            c(r0)     // Catch: java.lang.Exception -> L76
            goto L53
        L8b:
            java.lang.String r0 = "armeabi"
            c(r0)     // Catch: java.lang.Exception -> L76
            goto L53
        L91:
            r0 = move-exception
            goto L5b
        L93:
            r2 = move-exception
            goto L6a
        L95:
            r1 = move-exception
            goto L6f
        L97:
            r0 = move-exception
            goto L11
        L9a:
            r0 = move-exception
            goto L16
        L9d:
            r2 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
            goto L65
        La2:
            r2 = move-exception
            goto L56
        La4:
            r0 = r1
            goto L3e
        */
        throw new UnsupportedOperationException("Method not decompiled: s.h.e.l.l.S.l(android.content.Context):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001a A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0037 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String ld(android.content.Context r6) {
        /*
            r1 = 0
            r3 = 0
            java.lang.Object r0 = new java.lang.Object     // Catch: java.lang.Exception -> L1b java.lang.Throwable -> L2a
            r0.<init>()     // Catch: java.lang.Exception -> L1b java.lang.Throwable -> L2a
            java.lang.Object r2 = new java.lang.Object     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L7d
            r2.<init>()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L7d
            if (r0 == 0) goto L11
            r0.hashCode()     // Catch: java.lang.Exception -> L72
        L11:
            if (r2 == 0) goto L16
            r2.hashCode()     // Catch: java.lang.Exception -> L74
        L16:
            java.lang.String r0 = ""
            if (r6 != 0) goto L37
        L1a:
            return r0
        L1b:
            r0 = move-exception
            r0 = r1
        L1d:
            if (r0 == 0) goto L22
            r0.hashCode()     // Catch: java.lang.Exception -> L6c
        L22:
            if (r1 == 0) goto L16
            r3.hashCode()     // Catch: java.lang.Exception -> L28
            goto L16
        L28:
            r0 = move-exception
            goto L16
        L2a:
            r0 = move-exception
            r2 = r1
        L2c:
            if (r2 == 0) goto L31
            r2.hashCode()     // Catch: java.lang.Exception -> L6e
        L31:
            if (r1 == 0) goto L36
            r3.hashCode()     // Catch: java.lang.Exception -> L70
        L36:
            throw r0
        L37:
            android.content.res.AssetManager r1 = r6.getAssets()     // Catch: java.lang.Exception -> L76
            java.lang.String r2 = "images/icon_max_data_encrypted_xxxxx.png"
            java.io.InputStream r1 = r1.open(r2)     // Catch: java.lang.Exception -> L76
            if (r1 == 0) goto L1a
            r1.close()     // Catch: java.lang.Exception -> L76
            r1 = 20
            byte[] r1 = new byte[r1]     // Catch: java.lang.Exception -> L76
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L76
            java.io.File r3 = new java.io.File     // Catch: java.lang.Exception -> L76
            java.lang.String r4 = "/system/bin/linker"
            r3.<init>(r4)     // Catch: java.lang.Exception -> L76
            r2.<init>(r3)     // Catch: java.lang.Exception -> L76
            if (r2 == 0) goto L1a
            r2.read(r1)     // Catch: java.lang.Exception -> L76
            r2.close()     // Catch: java.lang.Exception -> L76
            r2 = 18
            r1 = r1[r2]     // Catch: java.lang.Exception -> L76
            switch(r1) {
                case 3: goto L66;
                case 40: goto L69;
                default: goto L65;
            }     // Catch: java.lang.Exception -> L76
        L65:
            goto L1a
        L66:
            java.lang.String r0 = "x86"
            goto L1a
        L69:
            java.lang.String r0 = "armeabi"
            goto L1a
        L6c:
            r0 = move-exception
            goto L22
        L6e:
            r2 = move-exception
            goto L31
        L70:
            r1 = move-exception
            goto L36
        L72:
            r0 = move-exception
            goto L11
        L74:
            r0 = move-exception
            goto L16
        L76:
            r1 = move-exception
            goto L1a
        L78:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L2c
        L7d:
            r2 = move-exception
            goto L1d
        */
        throw new UnsupportedOperationException("Method not decompiled: s.h.e.l.l.S.ld(android.content.Context):java.lang.String");
    }

    public static void sp() {
        Object obj;
        Object obj2;
        Object obj3 = null;
        try {
            obj2 = new Object();
        } catch (Exception e) {
            obj2 = null;
        } catch (Throwable th) {
            th = th;
            obj = null;
        }
        try {
            try {
                Object obj4 = new Object();
                if (obj2 != null) {
                    try {
                        obj2.hashCode();
                    } catch (Exception e2) {
                    }
                }
                if (obj4 != null) {
                    try {
                        obj4.hashCode();
                    } catch (Exception e3) {
                    }
                }
            } catch (Exception e4) {
                if (obj2 != null) {
                    try {
                        obj2.hashCode();
                    } catch (Exception e5) {
                    }
                }
                if (0 != 0) {
                    try {
                        obj3.hashCode();
                    } catch (Exception e6) {
                    }
                }
                Method declaredMethod = Class.forName("com.ijm.dataencryption.DETool").getDeclaredMethod("loadDEso", String.class, String.class, String.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(null, f, p, "com.msandroid.mobile");
            } catch (Throwable th2) {
                obj = obj2;
                th = th2;
                if (obj != null) {
                    try {
                        obj.hashCode();
                    } catch (Exception e7) {
                    }
                }
                if (0 != 0) {
                    try {
                        obj3.hashCode();
                    } catch (Exception e8) {
                    }
                }
                throw th;
            }
            Method declaredMethod2 = Class.forName("com.ijm.dataencryption.DETool").getDeclaredMethod("loadDEso", String.class, String.class, String.class);
            declaredMethod2.setAccessible(true);
            declaredMethod2.invoke(null, f, p, "com.msandroid.mobile");
        } catch (Exception e9) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0020  */
    @Override // android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void attachBaseContext(android.content.Context r6) {
        /*
            r5 = this;
            r1 = 0
            gST()
            super.attachBaseContext(r6)
            r3 = 0
            java.lang.Object r0 = new java.lang.Object     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L40
            r0.<init>()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L40
            java.lang.Object r2 = new java.lang.Object     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5c
            r2.<init>()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5c
            if (r0 == 0) goto L17
            r0.hashCode()     // Catch: java.lang.Exception -> L53
        L17:
            if (r2 == 0) goto L1c
            r2.hashCode()     // Catch: java.lang.Exception -> L55
        L1c:
            boolean r0 = s.h.e.l.l.S.la
            if (r0 == 0) goto L23
            l(r6)
        L23:
            java.lang.String r0 = "com.msandroid.mobile"
            s.h.e.l.l.N.l(r5, r0)
            java.lang.String r0 = "com.mobile.brasiltv.app.App"
            s.h.e.l.l.N.r(r5, r0)
            gET()
            return
        L31:
            r0 = move-exception
            r0 = r1
        L33:
            if (r0 == 0) goto L38
            r0.hashCode()     // Catch: java.lang.Exception -> L4d
        L38:
            if (r1 == 0) goto L1c
            r3.hashCode()     // Catch: java.lang.Exception -> L3e
            goto L1c
        L3e:
            r0 = move-exception
            goto L1c
        L40:
            r0 = move-exception
            r2 = r1
        L42:
            if (r2 == 0) goto L47
            r2.hashCode()     // Catch: java.lang.Exception -> L4f
        L47:
            if (r1 == 0) goto L4c
            r3.hashCode()     // Catch: java.lang.Exception -> L51
        L4c:
            throw r0
        L4d:
            r0 = move-exception
            goto L38
        L4f:
            r2 = move-exception
            goto L47
        L51:
            r1 = move-exception
            goto L4c
        L53:
            r0 = move-exception
            goto L17
        L55:
            r0 = move-exception
            goto L1c
        L57:
            r2 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
            goto L42
        L5c:
            r2 = move-exception
            goto L33
        */
        throw new UnsupportedOperationException("Method not decompiled: s.h.e.l.l.S.attachBaseContext(android.content.Context):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f  */
    @Override // android.app.Application
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onCreate() {
        /*
            r5 = this;
            r1 = 0
            r3 = 0
            java.lang.Object r0 = new java.lang.Object     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L37
            r0.<init>()     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L37
            java.lang.Object r2 = new java.lang.Object     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L53
            r2.<init>()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L53
            if (r0 == 0) goto L11
            r0.hashCode()     // Catch: java.lang.Exception -> L4a
        L11:
            if (r2 == 0) goto L16
            r2.hashCode()     // Catch: java.lang.Exception -> L4c
        L16:
            java.lang.String r0 = "com.mobile.brasiltv.app.App"
            s.h.e.l.l.N.ra(r5, r0)
            android.app.Application r0 = s.h.e.l.l.S.n
            if (r0 == 0) goto L24
            android.app.Application r0 = s.h.e.l.l.S.n
            r0.onCreate()
        L24:
            super.onCreate()
            return
        L28:
            r0 = move-exception
            r0 = r1
        L2a:
            if (r0 == 0) goto L2f
            r0.hashCode()     // Catch: java.lang.Exception -> L44
        L2f:
            if (r1 == 0) goto L16
            r3.hashCode()     // Catch: java.lang.Exception -> L35
            goto L16
        L35:
            r0 = move-exception
            goto L16
        L37:
            r0 = move-exception
            r2 = r1
        L39:
            if (r2 == 0) goto L3e
            r2.hashCode()     // Catch: java.lang.Exception -> L46
        L3e:
            if (r1 == 0) goto L43
            r3.hashCode()     // Catch: java.lang.Exception -> L48
        L43:
            throw r0
        L44:
            r0 = move-exception
            goto L2f
        L46:
            r2 = move-exception
            goto L3e
        L48:
            r1 = move-exception
            goto L43
        L4a:
            r0 = move-exception
            goto L11
        L4c:
            r0 = move-exception
            goto L16
        L4e:
            r2 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
            goto L39
        L53:
            r2 = move-exception
            goto L2a
        */
        throw new UnsupportedOperationException("Method not decompiled: s.h.e.l.l.S.onCreate():void");
    }
}
