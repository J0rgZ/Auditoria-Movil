package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class i {
    public static Map a = new HashMap();
    public static Map b = new HashMap();

    public static b a(Constructor constructor, Object obj) {
        try {
            androidx.appcompat.app.m.a(constructor.newInstance(obj));
            return null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Constructor b(Class cls) {
        String str;
        try {
            Package r0 = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            if (r0 != null) {
                str = r0.getName();
            } else {
                str = "";
            }
            if (!str.isEmpty()) {
                canonicalName = canonicalName.substring(str.length() + 1);
            }
            String c = c(canonicalName);
            if (!str.isEmpty()) {
                c = str + "." + c;
            }
            Constructor declaredConstructor = Class.forName(c).getDeclaredConstructor(cls);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static String c(String str) {
        return str.replace(".", "_") + "_LifecycleAdapter";
    }

    public static int d(Class cls) {
        Integer num = (Integer) a.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int g = g(cls);
        a.put(cls, Integer.valueOf(g));
        return g;
    }

    public static boolean e(Class cls) {
        if (cls != null && e.class.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    public static d f(Object obj) {
        if (obj instanceof d) {
            return (d) obj;
        }
        Class<?> cls = obj.getClass();
        if (d(cls) == 2) {
            List list = (List) b.get(cls);
            if (list.size() == 1) {
                a((Constructor) list.get(0), obj);
                return new SingleGeneratedAdapterObserver(null);
            }
            b[] bVarArr = new b[list.size()];
            for (int i = 0; i < list.size(); i++) {
                a((Constructor) list.get(i), obj);
                bVarArr[i] = null;
            }
            return new CompositeGeneratedAdaptersObserver(bVarArr);
        }
        return new ReflectiveGenericLifecycleObserver(obj);
    }

    public static int g(Class cls) {
        ArrayList arrayList;
        Class<?>[] interfaces;
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor b2 = b(cls);
        if (b2 != null) {
            b.put(cls, Collections.singletonList(b2));
            return 2;
        } else if (a.c.d(cls)) {
            return 1;
        } else {
            Class superclass = cls.getSuperclass();
            if (e(superclass)) {
                if (d(superclass) == 1) {
                    return 1;
                }
                arrayList = new ArrayList((Collection) b.get(superclass));
            } else {
                arrayList = null;
            }
            for (Class<?> cls2 : cls.getInterfaces()) {
                if (e(cls2)) {
                    if (d(cls2) == 1) {
                        return 1;
                    }
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.addAll((Collection) b.get(cls2));
                }
            }
            if (arrayList == null) {
                return 1;
            }
            b.put(cls, arrayList);
            return 2;
        }
    }
}
