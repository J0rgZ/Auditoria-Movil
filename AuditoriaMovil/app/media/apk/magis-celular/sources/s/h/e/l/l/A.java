package s.h.e.l.l;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityThread;
import android.app.AppComponentFactory;
import android.app.LoadedApk;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import java.io.File;
import java.lang.reflect.Field;
@SuppressLint({"Override"})
@TargetApi(28)
/* loaded from: classes.dex */
public final class A extends AppComponentFactory {
    private String packageName = "com.msandroid.mobile";
    private String orignAppName = "com.mobile.brasiltv.app.App";
    private String orignName = "androidx.core.app.CoreComponentFactory";
    private AppComponentFactory orignACF = null;
    private boolean supportInstantiateClassLoader = false;
    private AppComponentFactory acf = null;

    public static native ClassLoader al(ClassLoader classLoader, ApplicationInfo applicationInfo, String str, String str2);

    private ApplicationInfo ga() {
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
                ActivityThread currentActivityThread = ActivityThread.currentActivityThread();
                Field declaredField = currentActivityThread.getClass().getDeclaredField("mBoundApplication");
                declaredField.setAccessible(true);
                Object obj5 = declaredField.get(currentActivityThread);
                Field declaredField2 = obj5.getClass().getDeclaredField("info");
                declaredField2.setAccessible(true);
                return ((LoadedApk) declaredField2.get(obj5)).getApplicationInfo();
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
            ActivityThread currentActivityThread2 = ActivityThread.currentActivityThread();
            Field declaredField3 = currentActivityThread2.getClass().getDeclaredField("mBoundApplication");
            declaredField3.setAccessible(true);
            Object obj52 = declaredField3.get(currentActivityThread2);
            Field declaredField22 = obj52.getClass().getDeclaredField("info");
            declaredField22.setAccessible(true);
            return ((LoadedApk) declaredField22.get(obj52)).getApplicationInfo();
        } catch (Exception e9) {
            return null;
        }
    }

    private void ls(ApplicationInfo applicationInfo) {
        try {
            File file = new File(applicationInfo.dataDir, "files");
            if (!file.exists()) {
                file.mkdirs();
            }
            S.p = file.getAbsolutePath();
            S.f = applicationInfo.sourceDir;
            if (S.la) {
                S.l(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized AppComponentFactory getACF(ClassLoader classLoader) {
        if (this.acf == null && this.orignName != null && !this.orignName.equals("")) {
            try {
                this.acf = (AppComponentFactory) classLoader.loadClass(this.orignName).newInstance();
            } catch (Exception e) {
            }
        }
        return this.acf;
    }

    @Override // android.app.AppComponentFactory
    public Activity instantiateActivity(ClassLoader classLoader, String str, Intent intent) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (S.l) {
            AppComponentFactory acf = getACF(classLoader);
            this.acf = acf;
            if (acf != null) {
                return this.acf.instantiateActivity(classLoader, str, intent);
            }
        }
        return super.instantiateActivity(classLoader, str, intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0058  */
    @Override // android.app.AppComponentFactory
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.app.Application instantiateApplication(java.lang.ClassLoader r6, java.lang.String r7) throws java.lang.ClassNotFoundException, java.lang.IllegalAccessException, java.lang.InstantiationException {
        /*
            r5 = this;
            r1 = 0
            r3 = 0
            java.lang.Object r0 = new java.lang.Object     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L4b
            r0.<init>()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L4b
            java.lang.Object r2 = new java.lang.Object     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L7a
            r2.<init>()     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L7a
            if (r0 == 0) goto L11
            r0.hashCode()     // Catch: java.lang.Exception -> L71
        L11:
            if (r2 == 0) goto L16
            r2.hashCode()     // Catch: java.lang.Exception -> L73
        L16:
            java.lang.String r0 = "s.h.e.l.l.S"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L58
            boolean r0 = r5.supportInstantiateClassLoader
            if (r0 != 0) goto L35
            android.content.pm.ApplicationInfo r0 = r5.ga()
            r5.ls(r0)
            java.lang.String r1 = r5.packageName
            java.lang.String r2 = r5.orignAppName
            java.lang.ClassLoader r6 = s.h.e.l.l.N.al(r6, r0, r1, r2)
            java.lang.String r1 = r5.orignAppName
            r0.className = r1
        L35:
            java.lang.String r7 = r5.orignAppName
        L37:
            android.app.Application r0 = super.instantiateApplication(r6, r7)
        L3b:
            return r0
        L3c:
            r0 = move-exception
            r0 = r1
        L3e:
            if (r0 == 0) goto L43
            r0.hashCode()     // Catch: java.lang.Exception -> L6b
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
            r2.hashCode()     // Catch: java.lang.Exception -> L6d
        L52:
            if (r1 == 0) goto L57
            r3.hashCode()     // Catch: java.lang.Exception -> L6f
        L57:
            throw r0
        L58:
            boolean r0 = s.h.e.l.l.S.l
            if (r0 == 0) goto L37
            android.app.AppComponentFactory r0 = r5.getACF(r6)
            r5.acf = r0
            if (r0 == 0) goto L37
            android.app.AppComponentFactory r0 = r5.acf
            android.app.Application r0 = r0.instantiateApplication(r6, r7)
            goto L3b
        L6b:
            r0 = move-exception
            goto L43
        L6d:
            r2 = move-exception
            goto L52
        L6f:
            r1 = move-exception
            goto L57
        L71:
            r0 = move-exception
            goto L11
        L73:
            r0 = move-exception
            goto L16
        L75:
            r2 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
            goto L4d
        L7a:
            r2 = move-exception
            goto L3e
        */
        throw new UnsupportedOperationException("Method not decompiled: s.h.e.l.l.A.instantiateApplication(java.lang.ClassLoader, java.lang.String):android.app.Application");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001a A[Catch: Throwable -> 0x0063, TRY_LEAVE, TryCatch #7 {Throwable -> 0x0063, blocks: (B:9:0x0016, B:11:0x001a), top: B:63:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0036 A[Catch: Throwable -> 0x0074, TryCatch #9 {Throwable -> 0x0074, blocks: (B:12:0x0028, B:13:0x0032, B:15:0x0036, B:17:0x0040), top: B:65:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x007d  */
    @Override // android.app.AppComponentFactory
    @android.annotation.TargetApi(29)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.ClassLoader instantiateClassLoader(java.lang.ClassLoader r6, android.content.pm.ApplicationInfo r7) {
        /*
            r5 = this;
            r1 = 0
            r3 = 0
            java.lang.Object r0 = new java.lang.Object     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L56
            r0.<init>()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L56
            java.lang.Object r2 = new java.lang.Object     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            r2.<init>()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            if (r0 == 0) goto L11
            r0.hashCode()     // Catch: java.lang.Exception -> L70
        L11:
            if (r2 == 0) goto L16
            r2.hashCode()     // Catch: java.lang.Exception -> L72
        L16:
            boolean r0 = r5.supportInstantiateClassLoader     // Catch: java.lang.Throwable -> L63
            if (r0 != 0) goto L7d
            s.h.e.l.l.S.gST()     // Catch: java.lang.Throwable -> L63
            r5.ls(r7)     // Catch: java.lang.Throwable -> L63
            java.lang.String r0 = r5.packageName     // Catch: java.lang.Throwable -> L63
            java.lang.String r1 = r5.orignAppName     // Catch: java.lang.Throwable -> L63
            java.lang.ClassLoader r0 = s.h.e.l.l.N.al(r6, r7, r0, r1)     // Catch: java.lang.Throwable -> L63
            java.lang.String r1 = r5.orignAppName     // Catch: java.lang.Throwable -> L74
            r7.className = r1     // Catch: java.lang.Throwable -> L74
            r1 = 1
            r5.supportInstantiateClassLoader = r1     // Catch: java.lang.Throwable -> L74
            s.h.e.l.l.S.gET()     // Catch: java.lang.Throwable -> L74
        L32:
            boolean r1 = s.h.e.l.l.S.l     // Catch: java.lang.Throwable -> L74
            if (r1 == 0) goto L65
            android.app.AppComponentFactory r1 = r5.getACF(r0)     // Catch: java.lang.Throwable -> L74
            r5.acf = r1     // Catch: java.lang.Throwable -> L74
            android.app.AppComponentFactory r1 = r5.acf     // Catch: java.lang.Throwable -> L74
            if (r1 == 0) goto L65
            android.app.AppComponentFactory r1 = r5.acf     // Catch: java.lang.Throwable -> L74
            java.lang.ClassLoader r0 = r1.instantiateClassLoader(r0, r7)     // Catch: java.lang.Throwable -> L74
        L46:
            return r0
        L47:
            r0 = move-exception
            r0 = r1
        L49:
            if (r0 == 0) goto L4e
            r0.hashCode()     // Catch: java.lang.Exception -> L6a
        L4e:
            if (r1 == 0) goto L16
            r3.hashCode()     // Catch: java.lang.Exception -> L54
            goto L16
        L54:
            r0 = move-exception
            goto L16
        L56:
            r0 = move-exception
            r2 = r1
        L58:
            if (r2 == 0) goto L5d
            r2.hashCode()     // Catch: java.lang.Exception -> L6c
        L5d:
            if (r1 == 0) goto L62
            r3.hashCode()     // Catch: java.lang.Exception -> L6e
        L62:
            throw r0
        L63:
            r0 = move-exception
            r0 = r6
        L65:
            java.lang.ClassLoader r0 = super.instantiateClassLoader(r0, r7)
            goto L46
        L6a:
            r0 = move-exception
            goto L4e
        L6c:
            r2 = move-exception
            goto L5d
        L6e:
            r1 = move-exception
            goto L62
        L70:
            r0 = move-exception
            goto L11
        L72:
            r0 = move-exception
            goto L16
        L74:
            r1 = move-exception
            goto L65
        L76:
            r2 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
            goto L58
        L7b:
            r2 = move-exception
            goto L49
        L7d:
            r0 = r6
            goto L32
        */
        throw new UnsupportedOperationException("Method not decompiled: s.h.e.l.l.A.instantiateClassLoader(java.lang.ClassLoader, android.content.pm.ApplicationInfo):java.lang.ClassLoader");
    }

    @Override // android.app.AppComponentFactory
    public ContentProvider instantiateProvider(ClassLoader classLoader, String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (S.l) {
            AppComponentFactory acf = getACF(classLoader);
            this.acf = acf;
            if (acf != null) {
                return this.acf.instantiateProvider(classLoader, str);
            }
        }
        return super.instantiateProvider(classLoader, str);
    }

    @Override // android.app.AppComponentFactory
    public BroadcastReceiver instantiateReceiver(ClassLoader classLoader, String str, Intent intent) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (S.l) {
            AppComponentFactory acf = getACF(classLoader);
            this.acf = acf;
            if (acf != null) {
                return this.acf.instantiateReceiver(classLoader, str, intent);
            }
        }
        return super.instantiateReceiver(classLoader, str, intent);
    }

    @Override // android.app.AppComponentFactory
    public Service instantiateService(ClassLoader classLoader, String str, Intent intent) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (S.l) {
            AppComponentFactory acf = getACF(classLoader);
            this.acf = acf;
            if (acf != null) {
                return this.acf.instantiateService(classLoader, str, intent);
            }
        }
        return super.instantiateService(classLoader, str, intent);
    }
}
