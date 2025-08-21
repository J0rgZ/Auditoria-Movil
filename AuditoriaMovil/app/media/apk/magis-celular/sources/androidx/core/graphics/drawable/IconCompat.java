package androidx.core.graphics.drawable;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import m.h;
import m.i;
import m.j;
import m.k;
import m.l;
import m.m;
import m.n;
import m.o;
import m.p;
import m.q;
import m.r;
import m.s;
import m.t;
import m.u;
import m.v;
/* loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {
    public static final PorterDuff.Mode k = PorterDuff.Mode.SRC_IN;
    public int a;
    public Object b;
    public byte[] c;
    public Parcelable d;
    public int e;
    public int f;
    public ColorStateList g;
    public PorterDuff.Mode h;
    public String i;
    public String j;

    public IconCompat() {
        this.a = -1;
        this.c = null;
        this.d = null;
        this.e = 0;
        this.f = 0;
        this.g = null;
        this.h = k;
        this.i = null;
    }

    public static Bitmap b(Bitmap bitmap, boolean z) {
        int min = (int) (Math.min(bitmap.getWidth(), bitmap.getHeight()) * 0.6666667f);
        Bitmap createBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(3);
        float f = min;
        float f2 = 0.5f * f;
        float f3 = 0.9166667f * f2;
        if (z) {
            float f4 = 0.010416667f * f;
            paint.setColor(0);
            paint.setShadowLayer(f4, 0.0f, f * 0.020833334f, 1023410176);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.setShadowLayer(f4, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(-16777216);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Matrix matrix = new Matrix();
        matrix.setTranslate((-(bitmap.getWidth() - min)) / 2, (-(bitmap.getHeight() - min)) / 2);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f2, f2, f3, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }

    public static IconCompat c(Bitmap bitmap) {
        if (bitmap != null) {
            IconCompat iconCompat = new IconCompat(1);
            iconCompat.b = bitmap;
            return iconCompat;
        }
        throw new IllegalArgumentException("Bitmap must not be null.");
    }

    public static IconCompat d(Context context, int i) {
        if (context != null) {
            return e(context.getResources(), context.getPackageName(), i);
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    public static IconCompat e(Resources resources, String str, int i) {
        if (str != null) {
            if (i != 0) {
                IconCompat iconCompat = new IconCompat(2);
                iconCompat.e = i;
                if (resources != null) {
                    try {
                        iconCompat.b = resources.getResourceName(i);
                    } catch (Resources.NotFoundException unused) {
                        throw new IllegalArgumentException("Icon resource cannot be found");
                    }
                } else {
                    iconCompat.b = str;
                }
                iconCompat.j = str;
                return iconCompat;
            }
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
        throw new IllegalArgumentException("Package must not be null.");
    }

    public static int h(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return o.a(icon);
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            Log.e("IconCompat", "Unable to get icon resource", e);
            return 0;
        } catch (NoSuchMethodException e2) {
            Log.e("IconCompat", "Unable to get icon resource", e2);
            return 0;
        } catch (InvocationTargetException e3) {
            Log.e("IconCompat", "Unable to get icon resource", e3);
            return 0;
        }
    }

    public static String j(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return l.a(icon);
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e("IconCompat", "Unable to get icon package", e);
            return null;
        } catch (NoSuchMethodException e2) {
            Log.e("IconCompat", "Unable to get icon package", e2);
            return null;
        } catch (InvocationTargetException e3) {
            Log.e("IconCompat", "Unable to get icon package", e3);
            return null;
        }
    }

    public static Resources k(Context context, String str) {
        if ("android".equals(str)) {
            return Resources.getSystem();
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 8192);
            if (applicationInfo == null) {
                return null;
            }
            return packageManager.getResourcesForApplication(applicationInfo);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("IconCompat", String.format("Unable to find pkg=%s for icon", str), e);
            return null;
        }
    }

    public static int m(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return n.a(icon);
        }
        try {
            return ((Integer) icon.getClass().getMethod("getType", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            Log.e("IconCompat", "Unable to get icon type " + icon, e);
            return -1;
        } catch (NoSuchMethodException e2) {
            Log.e("IconCompat", "Unable to get icon type " + icon, e2);
            return -1;
        } catch (InvocationTargetException e3) {
            Log.e("IconCompat", "Unable to get icon type " + icon, e3);
            return -1;
        }
    }

    public static Uri o(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return m.a(icon);
        }
        try {
            return (Uri) icon.getClass().getMethod("getUri", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e("IconCompat", "Unable to get icon uri", e);
            return null;
        } catch (NoSuchMethodException e2) {
            Log.e("IconCompat", "Unable to get icon uri", e2);
            return null;
        } catch (InvocationTargetException e3) {
            Log.e("IconCompat", "Unable to get icon uri", e3);
            return null;
        }
    }

    public static String w(int i) {
        switch (i) {
            case 1:
                return "BITMAP";
            case 2:
                return "RESOURCE";
            case 3:
                return "DATA";
            case 4:
                return "URI";
            case 5:
                return "BITMAP_MASKABLE";
            case 6:
                return "URI_MASKABLE";
            default:
                return "UNKNOWN";
        }
    }

    public void a(Context context) {
        Object obj;
        if (this.a == 2 && (obj = this.b) != null) {
            String str = (String) obj;
            if (!str.contains(":")) {
                return;
            }
            String str2 = str.split(":", -1)[1];
            String str3 = str2.split("/", -1)[0];
            String str4 = str2.split("/", -1)[1];
            String str5 = str.split(":", -1)[0];
            if ("0_resource_name_obfuscated".equals(str4)) {
                return;
            }
            String i = i();
            int identifier = k(context, i).getIdentifier(str4, str3, str5);
            if (this.e != identifier) {
                StringBuilder sb = new StringBuilder();
                sb.append("Id has changed for ");
                sb.append(i);
                sb.append(" ");
                sb.append(str);
                this.e = identifier;
            }
        }
    }

    public Bitmap f() {
        int i = this.a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            Object obj = this.b;
            if (obj instanceof Bitmap) {
                return (Bitmap) obj;
            }
            return null;
        } else if (i == 1) {
            return (Bitmap) this.b;
        } else {
            if (i == 5) {
                return b((Bitmap) this.b, true);
            }
            throw new IllegalStateException("called getBitmap() on " + this);
        }
    }

    public int g() {
        int i = this.a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return h(i.a(this.b));
        }
        if (i == 2) {
            return this.e;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public String i() {
        int i = this.a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return j(i.a(this.b));
        }
        if (i == 2) {
            if (TextUtils.isEmpty(this.j)) {
                return ((String) this.b).split(":", -1)[0];
            }
            return this.j;
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public int l() {
        int i = this.a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return m(i.a(this.b));
        }
        return i;
    }

    public Uri n() {
        int i = this.a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return o(i.a(this.b));
        }
        if (i != 4 && i != 6) {
            throw new IllegalStateException("called getUri() on " + this);
        }
        return Uri.parse((String) this.b);
    }

    public InputStream p(Context context) {
        Uri n = n();
        String scheme = n.getScheme();
        if (!"content".equals(scheme) && !"file".equals(scheme)) {
            try {
                return new FileInputStream(new File((String) this.b));
            } catch (FileNotFoundException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to load image from path: ");
                sb.append(n);
                return null;
            }
        }
        try {
            return context.getContentResolver().openInputStream(n);
        } catch (Exception unused2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to load image from URI: ");
            sb2.append(n);
            return null;
        }
    }

    public Drawable q(Context context) {
        a(context);
        if (Build.VERSION.SDK_INT >= 23) {
            return k.a(v(context), context);
        }
        Drawable r = r(context);
        if (r != null && (this.g != null || this.h != k)) {
            r.mutate();
            h.o(r, this.g);
            h.p(r, this.h);
        }
        return r;
    }

    public final Drawable r(Context context) {
        switch (this.a) {
            case 1:
                return new BitmapDrawable(context.getResources(), (Bitmap) this.b);
            case 2:
                String i = i();
                if (TextUtils.isEmpty(i)) {
                    i = context.getPackageName();
                }
                try {
                    return k.h.d(k(context, i), this.e, context.getTheme());
                } catch (RuntimeException e) {
                    Log.e("IconCompat", String.format("Unable to load resource 0x%08x from pkg=%s", Integer.valueOf(this.e), this.b), e);
                    break;
                }
            case 3:
                return new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray((byte[]) this.b, this.e, this.f));
            case 4:
                InputStream p = p(context);
                if (p != null) {
                    return new BitmapDrawable(context.getResources(), BitmapFactory.decodeStream(p));
                }
                break;
            case 5:
                return new BitmapDrawable(context.getResources(), b((Bitmap) this.b, false));
            case 6:
                InputStream p2 = p(context);
                if (p2 != null) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        return new AdaptiveIconDrawable(null, new BitmapDrawable(context.getResources(), BitmapFactory.decodeStream(p2)));
                    }
                    return new BitmapDrawable(context.getResources(), b(BitmapFactory.decodeStream(p2), false));
                }
                break;
        }
        return null;
    }

    public void s() {
        this.h = PorterDuff.Mode.valueOf(this.i);
        switch (this.a) {
            case -1:
                Parcelable parcelable = this.d;
                if (parcelable != null) {
                    this.b = parcelable;
                    return;
                }
                throw new IllegalArgumentException("Invalid icon");
            case 0:
            default:
                return;
            case 1:
            case 5:
                Parcelable parcelable2 = this.d;
                if (parcelable2 != null) {
                    this.b = parcelable2;
                    return;
                }
                byte[] bArr = this.c;
                this.b = bArr;
                this.a = 3;
                this.e = 0;
                this.f = bArr.length;
                return;
            case 2:
            case 4:
            case 6:
                String str = new String(this.c, Charset.forName("UTF-16"));
                this.b = str;
                if (this.a == 2 && this.j == null) {
                    this.j = str.split(":", -1)[0];
                    return;
                }
                return;
            case 3:
                this.b = this.c;
                return;
        }
    }

    public void t(boolean z) {
        this.i = this.h.name();
        switch (this.a) {
            case -1:
                if (!z) {
                    this.d = (Parcelable) this.b;
                    return;
                }
                throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
            case 0:
            default:
                return;
            case 1:
            case 5:
                if (z) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ((Bitmap) this.b).compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                    this.c = byteArrayOutputStream.toByteArray();
                    return;
                }
                this.d = (Parcelable) this.b;
                return;
            case 2:
                this.c = ((String) this.b).getBytes(Charset.forName("UTF-16"));
                return;
            case 3:
                this.c = (byte[]) this.b;
                return;
            case 4:
            case 6:
                this.c = this.b.toString().getBytes(Charset.forName("UTF-16"));
                return;
        }
    }

    public String toString() {
        if (this.a == -1) {
            return String.valueOf(this.b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(w(this.a));
        switch (this.a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.b).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.b).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(this.j);
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(g())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.e);
                if (this.f != 0) {
                    sb.append(" off=");
                    sb.append(this.f);
                    break;
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.b);
                break;
        }
        if (this.g != null) {
            sb.append(" tint=");
            sb.append(this.g);
        }
        if (this.h != k) {
            sb.append(" mode=");
            sb.append(this.h);
        }
        sb.append(")");
        return sb.toString();
    }

    public Icon u() {
        return v(null);
    }

    public Icon v(Context context) {
        Icon a;
        switch (this.a) {
            case -1:
                return i.a(this.b);
            case 0:
            default:
                throw new IllegalArgumentException("Unknown type");
            case 1:
                a = r.a((Bitmap) this.b);
                break;
            case 2:
                a = t.a(i(), this.e);
                break;
            case 3:
                a = u.a((byte[]) this.b, this.e, this.f);
                break;
            case 4:
                a = v.a((String) this.b);
                break;
            case 5:
                if (Build.VERSION.SDK_INT >= 26) {
                    a = s.a((Bitmap) this.b);
                    break;
                } else {
                    a = r.a(b((Bitmap) this.b, false));
                    break;
                }
            case 6:
                int i = Build.VERSION.SDK_INT;
                if (i >= 30) {
                    a = j.a(n());
                    break;
                } else if (context != null) {
                    InputStream p = p(context);
                    if (p != null) {
                        if (i >= 26) {
                            a = s.a(BitmapFactory.decodeStream(p));
                            break;
                        } else {
                            a = r.a(b(BitmapFactory.decodeStream(p), false));
                            break;
                        }
                    } else {
                        throw new IllegalStateException("Cannot load adaptive icon from uri: " + n());
                    }
                } else {
                    throw new IllegalArgumentException("Context is required to resolve the file uri of the icon: " + n());
                }
        }
        ColorStateList colorStateList = this.g;
        if (colorStateList != null) {
            p.a(a, colorStateList);
        }
        PorterDuff.Mode mode = this.h;
        if (mode != k) {
            q.a(a, mode);
        }
        return a;
    }

    public IconCompat(int i) {
        this.c = null;
        this.d = null;
        this.e = 0;
        this.f = 0;
        this.g = null;
        this.h = k;
        this.i = null;
        this.a = i;
    }
}
