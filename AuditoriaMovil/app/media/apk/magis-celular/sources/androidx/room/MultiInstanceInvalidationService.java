package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import androidx.room.b;
import java.util.HashMap;
/* loaded from: classes.dex */
public class MultiInstanceInvalidationService extends Service {
    public int a = 0;
    public final HashMap b = new HashMap();
    public final RemoteCallbackList c = new a();
    public final b.a d = new b();

    /* loaded from: classes.dex */
    public class a extends RemoteCallbackList {
        public a() {
        }

        @Override // android.os.RemoteCallbackList
        /* renamed from: a */
        public void onCallbackDied(androidx.room.a aVar, Object obj) {
            MultiInstanceInvalidationService.this.b.remove(Integer.valueOf(((Integer) obj).intValue()));
        }
    }

    /* loaded from: classes.dex */
    public class b extends b.a {
        public b() {
        }

        @Override // androidx.room.b
        public int b(androidx.room.a aVar, String str) {
            if (str == null) {
                return 0;
            }
            synchronized (MultiInstanceInvalidationService.this.c) {
                MultiInstanceInvalidationService multiInstanceInvalidationService = MultiInstanceInvalidationService.this;
                int i = multiInstanceInvalidationService.a + 1;
                multiInstanceInvalidationService.a = i;
                if (multiInstanceInvalidationService.c.register(aVar, Integer.valueOf(i))) {
                    MultiInstanceInvalidationService.this.b.put(Integer.valueOf(i), str);
                    return i;
                }
                MultiInstanceInvalidationService multiInstanceInvalidationService2 = MultiInstanceInvalidationService.this;
                multiInstanceInvalidationService2.a--;
                return 0;
            }
        }

        @Override // androidx.room.b
        public void c(int i, String[] strArr) {
            synchronized (MultiInstanceInvalidationService.this.c) {
                String str = (String) MultiInstanceInvalidationService.this.b.get(Integer.valueOf(i));
                if (str == null) {
                    return;
                }
                int beginBroadcast = MultiInstanceInvalidationService.this.c.beginBroadcast();
                for (int i2 = 0; i2 < beginBroadcast; i2++) {
                    int intValue = ((Integer) MultiInstanceInvalidationService.this.c.getBroadcastCookie(i2)).intValue();
                    String str2 = (String) MultiInstanceInvalidationService.this.b.get(Integer.valueOf(intValue));
                    if (i != intValue && str.equals(str2)) {
                        try {
                            ((androidx.room.a) MultiInstanceInvalidationService.this.c.getBroadcastItem(i2)).a(strArr);
                        } catch (RemoteException unused) {
                        }
                    }
                }
                MultiInstanceInvalidationService.this.c.finishBroadcast();
            }
        }

        @Override // androidx.room.b
        public void d(androidx.room.a aVar, int i) {
            synchronized (MultiInstanceInvalidationService.this.c) {
                MultiInstanceInvalidationService.this.c.unregister(aVar);
                MultiInstanceInvalidationService.this.b.remove(Integer.valueOf(i));
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.d;
    }
}
