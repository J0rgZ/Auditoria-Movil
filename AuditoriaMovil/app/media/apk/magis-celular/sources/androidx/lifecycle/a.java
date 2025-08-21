package androidx.lifecycle;

import androidx.lifecycle.c;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class a {
    public static a c = new a();
    public final Map a = new HashMap();
    public final Map b = new HashMap();

    /* renamed from: androidx.lifecycle.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0018a {
        public final Map a = new HashMap();
        public final Map b;

        public C0018a(Map map) {
            this.b = map;
            for (Map.Entry entry : map.entrySet()) {
                c.a aVar = (c.a) entry.getValue();
                List list = (List) this.a.get(aVar);
                if (list == null) {
                    list = new ArrayList();
                    this.a.put(aVar, list);
                }
                list.add(entry.getKey());
            }
        }

        public static void b(List list, f fVar, c.a aVar, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    ((b) list.get(size)).a(fVar, aVar, obj);
                }
            }
        }

        public void a(f fVar, c.a aVar, Object obj) {
            b((List) this.a.get(aVar), fVar, aVar, obj);
            b((List) this.a.get(c.a.ON_ANY), fVar, aVar, obj);
        }
    }

    /* loaded from: classes.dex */
    public static final class b {
        public final int a;
        public final Method b;

        public b(int i, Method method) {
            this.a = i;
            this.b = method;
            method.setAccessible(true);
        }

        public void a(f fVar, c.a aVar, Object obj) {
            try {
                int i = this.a;
                if (i != 0) {
                    if (i != 1) {
                        if (i == 2) {
                            this.b.invoke(obj, fVar, aVar);
                            return;
                        }
                        return;
                    }
                    this.b.invoke(obj, fVar);
                    return;
                }
                this.b.invoke(obj, new Object[0]);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException("Failed to call observer method", e2.getCause());
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            if (this.a == bVar.a && this.b.getName().equals(bVar.b.getName())) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.a * 31) + this.b.getName().hashCode();
        }
    }

    public final C0018a a(Class cls, Method[] methodArr) {
        int i;
        C0018a c2;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (superclass != null && (c2 = c(superclass)) != null) {
            hashMap.putAll(c2.b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry entry : c(cls2).b.entrySet()) {
                e(hashMap, (b) entry.getKey(), (c.a) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = b(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            m mVar = (m) method.getAnnotation(m.class);
            if (mVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length > 0) {
                    if (parameterTypes[0].isAssignableFrom(f.class)) {
                        i = 1;
                    } else {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                } else {
                    i = 0;
                }
                c.a value = mVar.value();
                if (parameterTypes.length > 1) {
                    if (parameterTypes[1].isAssignableFrom(c.a.class)) {
                        if (value == c.a.ON_ANY) {
                            i = 2;
                        } else {
                            throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                        }
                    } else {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                }
                if (parameterTypes.length <= 2) {
                    e(hashMap, new b(i, method), value, cls);
                    z = true;
                } else {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
            }
        }
        C0018a c0018a = new C0018a(hashMap);
        this.a.put(cls, c0018a);
        this.b.put(cls, Boolean.valueOf(z));
        return c0018a;
    }

    public final Method[] b(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
        }
    }

    public C0018a c(Class cls) {
        C0018a c0018a = (C0018a) this.a.get(cls);
        if (c0018a != null) {
            return c0018a;
        }
        return a(cls, null);
    }

    public boolean d(Class cls) {
        Boolean bool = (Boolean) this.b.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        Method[] b2 = b(cls);
        for (Method method : b2) {
            if (((m) method.getAnnotation(m.class)) != null) {
                a(cls, b2);
                return true;
            }
        }
        this.b.put(cls, Boolean.FALSE);
        return false;
    }

    public final void e(Map map, b bVar, c.a aVar, Class cls) {
        c.a aVar2 = (c.a) map.get(bVar);
        if (aVar2 != null && aVar != aVar2) {
            Method method = bVar.b;
            throw new IllegalArgumentException("Method " + method.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + aVar2 + ", new value " + aVar);
        } else if (aVar2 == null) {
            map.put(bVar, aVar);
        }
    }
}
