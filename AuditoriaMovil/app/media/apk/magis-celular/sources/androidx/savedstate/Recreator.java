package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.m;
import androidx.lifecycle.c;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import androidx.savedstate.SavedStateRegistry;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"RestrictedApi"})
/* loaded from: classes.dex */
public final class Recreator implements d {
    public final b a;

    public Recreator(b bVar) {
        this.a = bVar;
    }

    @Override // androidx.lifecycle.d
    public void a(f fVar, c.a aVar) {
        if (aVar == c.a.ON_CREATE) {
            fVar.getLifecycle().c(this);
            Bundle a = this.a.getSavedStateRegistry().a("androidx.savedstate.Restarter");
            if (a == null) {
                return;
            }
            ArrayList<String> stringArrayList = a.getStringArrayList("classes_to_restore");
            if (stringArrayList != null) {
                Iterator<String> it = stringArrayList.iterator();
                while (it.hasNext()) {
                    b(it.next());
                }
                return;
            }
            throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        }
        throw new AssertionError("Next event must be ON_CREATE");
    }

    public final void b(String str) {
        Class cls;
        try {
            try {
                Constructor declaredConstructor = Class.forName(str, false, Recreator.class.getClassLoader()).asSubclass(SavedStateRegistry.a.class).getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                try {
                    m.a(declaredConstructor.newInstance(new Object[0]));
                    throw null;
                } catch (Exception e) {
                    throw new RuntimeException("Failed to instantiate " + str, e);
                }
            } catch (NoSuchMethodException e2) {
                throw new IllegalStateException("Class" + cls.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
            }
        } catch (ClassNotFoundException e3) {
            throw new RuntimeException("Class " + str + " wasn't found", e3);
        }
    }
}
