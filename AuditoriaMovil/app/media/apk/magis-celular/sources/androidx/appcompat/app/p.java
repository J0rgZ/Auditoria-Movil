package androidx.appcompat.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import java.util.Calendar;
/* loaded from: classes.dex */
public class p {
    public static p d;
    public final Context a;
    public final LocationManager b;
    public final a c = new a();

    /* loaded from: classes.dex */
    public static class a {
        public boolean a;
        public long b;
        public long c;
        public long d;
        public long e;
        public long f;
    }

    public p(Context context, LocationManager locationManager) {
        this.a = context;
        this.b = locationManager;
    }

    public static p a(Context context) {
        if (d == null) {
            Context applicationContext = context.getApplicationContext();
            d = new p(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return d;
    }

    public final Location b() {
        Location location;
        Location location2 = null;
        if (j.m.b(this.a, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            location = c("network");
        } else {
            location = null;
        }
        if (j.m.b(this.a, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            location2 = c("gps");
        }
        if (location2 != null && location != null) {
            if (location2.getTime() > location.getTime()) {
                return location2;
            }
            return location;
        } else if (location2 != null) {
            return location2;
        } else {
            return location;
        }
    }

    public final Location c(String str) {
        try {
            if (this.b.isProviderEnabled(str)) {
                return this.b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean d() {
        a aVar = this.c;
        if (e()) {
            return aVar.a;
        }
        Location b = b();
        if (b != null) {
            f(b);
            return aVar.a;
        }
        int i = Calendar.getInstance().get(11);
        if (i >= 6 && i < 22) {
            return false;
        }
        return true;
    }

    public final boolean e() {
        if (this.c.f > System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    public final void f(Location location) {
        boolean z;
        long j;
        long j2;
        a aVar = this.c;
        long currentTimeMillis = System.currentTimeMillis();
        o b = o.b();
        b.a(currentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j3 = b.a;
        b.a(currentTimeMillis, location.getLatitude(), location.getLongitude());
        if (b.c == 1) {
            z = true;
        } else {
            z = false;
        }
        long j4 = b.b;
        long j5 = b.a;
        boolean z2 = z;
        b.a(86400000 + currentTimeMillis, location.getLatitude(), location.getLongitude());
        long j6 = b.b;
        if (j4 != -1 && j5 != -1) {
            if (currentTimeMillis > j5) {
                j2 = 0 + j6;
            } else if (currentTimeMillis > j4) {
                j2 = 0 + j5;
            } else {
                j2 = 0 + j4;
            }
            j = j2 + 60000;
        } else {
            j = 43200000 + currentTimeMillis;
        }
        aVar.a = z2;
        aVar.b = j3;
        aVar.c = j4;
        aVar.d = j5;
        aVar.e = j6;
        aVar.f = j;
    }
}
