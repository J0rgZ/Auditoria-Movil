package androidx.mediarouter.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.mediarouter.R$dimen;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$integer;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.R$string;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import h0.p0;
import h0.s0;
import h0.t0;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class h extends androidx.appcompat.app.j {
    public static final boolean O = Log.isLoggable("MediaRouteCtrlDialog", 3);
    public View A;
    public ImageView B;
    public TextView C;
    public TextView D;
    public String E;
    public MediaControllerCompat F;
    public e G;
    public MediaDescriptionCompat H;
    public d I;
    public Bitmap J;
    public Uri K;
    public boolean L;
    public Bitmap M;
    public int N;
    public final t0 a;
    public final g b;
    public s0 c;
    public t0.i d;
    public final List e;
    public final List f;
    public final List g;
    public final List h;
    public Context i;
    public boolean j;
    public boolean k;
    public long l;
    public final Handler m;
    public RecyclerView n;
    public C0025h o;
    public j p;
    public Map q;
    public t0.i r;

    /* renamed from: s  reason: collision with root package name */
    public Map f24s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public ImageButton x;
    public Button y;
    public ImageView z;

    /* loaded from: classes.dex */
    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    h hVar = h.this;
                    if (hVar.r != null) {
                        hVar.r = null;
                        hVar.p();
                        return;
                    }
                    return;
                }
                return;
            }
            h.this.o();
        }
    }

    /* loaded from: classes.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            h.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (h.this.d.C()) {
                h.this.a.x(2);
            }
            h.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public class d extends AsyncTask {
        public final Bitmap a;
        public final Uri b;
        public int c;

        public d() {
            Bitmap b;
            MediaDescriptionCompat mediaDescriptionCompat = h.this.H;
            if (mediaDescriptionCompat == null) {
                b = null;
            } else {
                b = mediaDescriptionCompat.b();
            }
            this.a = h.d(b) ? null : b;
            MediaDescriptionCompat mediaDescriptionCompat2 = h.this.H;
            this.b = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.c() : null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00ba  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x00c8  */
        /* JADX WARN: Type inference failed for: r3v0 */
        /* JADX WARN: Type inference failed for: r3v2 */
        /* JADX WARN: Type inference failed for: r3v3, types: [java.io.InputStream] */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public android.graphics.Bitmap doInBackground(java.lang.Void... r8) {
            /*
                Method dump skipped, instructions count: 253
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.h.d.doInBackground(java.lang.Void[]):android.graphics.Bitmap");
        }

        public Bitmap b() {
            return this.a;
        }

        public Uri c() {
            return this.b;
        }

        @Override // android.os.AsyncTask
        /* renamed from: d */
        public void onPostExecute(Bitmap bitmap) {
            h hVar = h.this;
            hVar.I = null;
            if (!u.d.a(hVar.J, this.a) || !u.d.a(h.this.K, this.b)) {
                h hVar2 = h.this;
                hVar2.J = this.a;
                hVar2.M = bitmap;
                hVar2.K = this.b;
                hVar2.N = this.c;
                hVar2.L = true;
                hVar2.m();
            }
        }

        public final InputStream e(Uri uri) {
            InputStream openInputStream;
            String lowerCase = uri.getScheme().toLowerCase();
            if (!"android.resource".equals(lowerCase) && !"content".equals(lowerCase) && !"file".equals(lowerCase)) {
                URLConnection openConnection = new URL(uri.toString()).openConnection();
                openConnection.setConnectTimeout(30000);
                openConnection.setReadTimeout(30000);
                openInputStream = openConnection.getInputStream();
            } else {
                openInputStream = h.this.i.getContentResolver().openInputStream(uri);
            }
            if (openInputStream == null) {
                return null;
            }
            return new BufferedInputStream(openInputStream);
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            h.this.b();
        }
    }

    /* loaded from: classes.dex */
    public final class e extends MediaControllerCompat.a {
        public e() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            MediaDescriptionCompat e;
            h hVar = h.this;
            if (mediaMetadataCompat == null) {
                e = null;
            } else {
                e = mediaMetadataCompat.e();
            }
            hVar.H = e;
            h.this.g();
            h.this.m();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void i() {
            h hVar = h.this;
            MediaControllerCompat mediaControllerCompat = hVar.F;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.g(hVar.G);
                h.this.F = null;
            }
        }
    }

    /* loaded from: classes.dex */
    public abstract class f extends RecyclerView.d0 {
        public t0.i a;
        public final ImageButton b;
        public final MediaRouteVolumeSlider c;

        /* loaded from: classes.dex */
        public class a implements View.OnClickListener {
            public a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int c;
                h hVar = h.this;
                if (hVar.r != null) {
                    hVar.m.removeMessages(2);
                }
                f fVar = f.this;
                h.this.r = fVar.a;
                boolean z = !view.isActivated();
                if (z) {
                    c = 0;
                } else {
                    c = f.this.c();
                }
                f.this.d(z);
                f.this.c.setProgress(c);
                f.this.a.G(c);
                h.this.m.sendEmptyMessageDelayed(2, 500L);
            }
        }

        public f(View view, ImageButton imageButton, MediaRouteVolumeSlider mediaRouteVolumeSlider) {
            super(view);
            this.b = imageButton;
            this.c = mediaRouteVolumeSlider;
            imageButton.setImageDrawable(androidx.mediarouter.app.i.k(h.this.i));
            androidx.mediarouter.app.i.v(h.this.i, mediaRouteVolumeSlider);
        }

        public void b(t0.i iVar) {
            boolean z;
            this.a = iVar;
            int s2 = iVar.s();
            if (s2 == 0) {
                z = true;
            } else {
                z = false;
            }
            this.b.setActivated(z);
            this.b.setOnClickListener(new a());
            this.c.setTag(this.a);
            this.c.setMax(iVar.u());
            this.c.setProgress(s2);
            this.c.setOnSeekBarChangeListener(h.this.p);
        }

        public int c() {
            Integer num = (Integer) h.this.f24s.get(this.a.k());
            if (num == null) {
                return 1;
            }
            return Math.max(1, num.intValue());
        }

        public void d(boolean z) {
            if (this.b.isActivated() == z) {
                return;
            }
            this.b.setActivated(z);
            if (z) {
                h.this.f24s.put(this.a.k(), Integer.valueOf(this.c.getProgress()));
            } else {
                h.this.f24s.remove(this.a.k());
            }
        }

        public void e() {
            boolean z;
            int s2 = this.a.s();
            if (s2 == 0) {
                z = true;
            } else {
                z = false;
            }
            d(z);
            this.c.setProgress(s2);
        }
    }

    /* loaded from: classes.dex */
    public final class g extends t0.b {
        public g() {
        }

        public void onRouteAdded(t0 t0Var, t0.i iVar) {
            h.this.o();
        }

        public void onRouteChanged(t0 t0Var, t0.i iVar) {
            boolean z;
            t0.i.a h;
            if (iVar == h.this.d && iVar.g() != null) {
                for (t0.i iVar2 : iVar.q().f()) {
                    if (!h.this.d.l().contains(iVar2) && (h = h.this.d.h(iVar2)) != null && h.b() && !h.this.f.contains(iVar2)) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            if (z) {
                h.this.p();
                h.this.n();
                return;
            }
            h.this.o();
        }

        public void onRouteRemoved(t0 t0Var, t0.i iVar) {
            h.this.o();
        }

        public void onRouteSelected(t0 t0Var, t0.i iVar) {
            h hVar = h.this;
            hVar.d = iVar;
            hVar.t = false;
            hVar.p();
            h.this.n();
        }

        public void onRouteUnselected(t0 t0Var, t0.i iVar) {
            h.this.o();
        }

        public void onRouteVolumeChanged(t0 t0Var, t0.i iVar) {
            f fVar;
            int s2 = iVar.s();
            if (h.O) {
                StringBuilder sb = new StringBuilder();
                sb.append("onRouteVolumeChanged(), route.getVolume:");
                sb.append(s2);
            }
            h hVar = h.this;
            if (hVar.r != iVar && (fVar = (f) hVar.q.get(iVar.k())) != null) {
                fVar.e();
            }
        }
    }

    /* renamed from: androidx.mediarouter.app.h$h  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public final class C0025h extends RecyclerView.g {
        public final LayoutInflater b;
        public final Drawable c;
        public final Drawable d;
        public final Drawable e;
        public final Drawable f;
        public f g;
        public final int h;
        public final ArrayList a = new ArrayList();
        public final Interpolator i = new AccelerateDecelerateInterpolator();

        /* renamed from: androidx.mediarouter.app.h$h$a */
        /* loaded from: classes.dex */
        public class a extends Animation {
            public final /* synthetic */ int a;
            public final /* synthetic */ int b;
            public final /* synthetic */ View c;

            public a(int i, int i2, View view) {
                this.a = i;
                this.b = i2;
                this.c = view;
            }

            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                int i = this.a;
                int i2 = this.b;
                h.h(this.c, i2 + ((int) ((i - i2) * f)));
            }
        }

        /* renamed from: androidx.mediarouter.app.h$h$b */
        /* loaded from: classes.dex */
        public class b implements Animation.AnimationListener {
            public b() {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                h hVar = h.this;
                hVar.u = false;
                hVar.p();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                h.this.u = true;
            }
        }

        /* renamed from: androidx.mediarouter.app.h$h$c */
        /* loaded from: classes.dex */
        public class c extends RecyclerView.d0 {
            public final View a;
            public final ImageView b;
            public final ProgressBar c;
            public final TextView d;
            public final float e;
            public t0.i f;

            /* renamed from: androidx.mediarouter.app.h$h$c$a */
            /* loaded from: classes.dex */
            public class a implements View.OnClickListener {
                public a() {
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    c cVar = c.this;
                    h.this.a.w(cVar.f);
                    c.this.b.setVisibility(4);
                    c.this.c.setVisibility(0);
                }
            }

            public c(View view) {
                super(view);
                this.a = view;
                this.b = (ImageView) view.findViewById(R$id.mr_cast_group_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R$id.mr_cast_group_progress_bar);
                this.c = progressBar;
                this.d = (TextView) view.findViewById(R$id.mr_cast_group_name);
                this.e = androidx.mediarouter.app.i.h(h.this.i);
                androidx.mediarouter.app.i.t(h.this.i, progressBar);
            }

            public void b(f fVar) {
                float f;
                t0.i iVar = (t0.i) fVar.a();
                this.f = iVar;
                this.b.setVisibility(0);
                this.c.setVisibility(4);
                boolean c = c(iVar);
                View view = this.a;
                if (c) {
                    f = 1.0f;
                } else {
                    f = this.e;
                }
                view.setAlpha(f);
                this.a.setOnClickListener(new a());
                this.b.setImageDrawable(C0025h.this.c(iVar));
                this.d.setText(iVar.m());
            }

            public final boolean c(t0.i iVar) {
                List l = h.this.d.l();
                if (l.size() != 1 || l.get(0) != iVar) {
                    return true;
                }
                return false;
            }
        }

        /* renamed from: androidx.mediarouter.app.h$h$d */
        /* loaded from: classes.dex */
        public class d extends f {
            public final TextView e;
            public final int f;

            public d(View view) {
                super(view, (ImageButton) view.findViewById(R$id.mr_cast_mute_button), (MediaRouteVolumeSlider) view.findViewById(R$id.mr_cast_volume_slider));
                this.e = (TextView) view.findViewById(R$id.mr_group_volume_route_name);
                Resources resources = h.this.i.getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                TypedValue typedValue = new TypedValue();
                resources.getValue(R$dimen.mr_dynamic_volume_group_list_item_height, typedValue, true);
                this.f = (int) typedValue.getDimension(displayMetrics);
            }

            public void f(f fVar) {
                int i;
                View view = this.itemView;
                if (C0025h.this.e()) {
                    i = this.f;
                } else {
                    i = 0;
                }
                h.h(view, i);
                t0.i iVar = (t0.i) fVar.a();
                super.b(iVar);
                this.e.setText(iVar.m());
            }

            public int g() {
                return this.f;
            }
        }

        /* renamed from: androidx.mediarouter.app.h$h$e */
        /* loaded from: classes.dex */
        public class e extends RecyclerView.d0 {
            public final TextView a;

            public e(View view) {
                super(view);
                this.a = (TextView) view.findViewById(R$id.mr_cast_header_name);
            }

            public void b(f fVar) {
                this.a.setText(fVar.a().toString());
            }
        }

        /* renamed from: androidx.mediarouter.app.h$h$f */
        /* loaded from: classes.dex */
        public class f {
            public final Object a;
            public final int b;

            public f(Object obj, int i) {
                this.a = obj;
                this.b = i;
            }

            public Object a() {
                return this.a;
            }

            public int b() {
                return this.b;
            }
        }

        /* renamed from: androidx.mediarouter.app.h$h$g */
        /* loaded from: classes.dex */
        public class g extends f {
            public final View e;
            public final ImageView f;
            public final ProgressBar g;
            public final TextView h;
            public final RelativeLayout i;
            public final CheckBox j;
            public final float k;
            public final int l;
            public final int m;
            public final View.OnClickListener n;

            /* renamed from: androidx.mediarouter.app.h$h$g$a */
            /* loaded from: classes.dex */
            public class a implements View.OnClickListener {
                public a() {
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    g gVar = g.this;
                    boolean z = !gVar.h(gVar.a);
                    boolean y = g.this.a.y();
                    if (z) {
                        g gVar2 = g.this;
                        h.this.a.c(gVar2.a);
                    } else {
                        g gVar3 = g.this;
                        h.this.a.r(gVar3.a);
                    }
                    g.this.i(z, !y);
                    if (y) {
                        List l = h.this.d.l();
                        for (t0.i iVar : g.this.a.l()) {
                            if (l.contains(iVar) != z) {
                                f fVar = (f) h.this.q.get(iVar.k());
                                if (fVar instanceof g) {
                                    ((g) fVar).i(z, true);
                                }
                            }
                        }
                    }
                    g gVar4 = g.this;
                    C0025h.this.f(gVar4.a, z);
                }
            }

            public g(View view) {
                super(view, (ImageButton) view.findViewById(R$id.mr_cast_mute_button), (MediaRouteVolumeSlider) view.findViewById(R$id.mr_cast_volume_slider));
                this.n = new a();
                this.e = view;
                this.f = (ImageView) view.findViewById(R$id.mr_cast_route_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R$id.mr_cast_route_progress_bar);
                this.g = progressBar;
                this.h = (TextView) view.findViewById(R$id.mr_cast_route_name);
                this.i = (RelativeLayout) view.findViewById(R$id.mr_cast_volume_layout);
                CheckBox checkBox = (CheckBox) view.findViewById(R$id.mr_cast_checkbox);
                this.j = checkBox;
                checkBox.setButtonDrawable(androidx.mediarouter.app.i.e(h.this.i));
                androidx.mediarouter.app.i.t(h.this.i, progressBar);
                this.k = androidx.mediarouter.app.i.h(h.this.i);
                Resources resources = h.this.i.getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                TypedValue typedValue = new TypedValue();
                resources.getValue(R$dimen.mr_dynamic_dialog_row_height, typedValue, true);
                this.l = (int) typedValue.getDimension(displayMetrics);
                this.m = 0;
            }

            public void f(f fVar) {
                boolean z;
                int i;
                float f;
                t0.i iVar = (t0.i) fVar.a();
                if (iVar == h.this.d && iVar.l().size() > 0) {
                    Iterator it = iVar.l().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        t0.i iVar2 = (t0.i) it.next();
                        if (!h.this.f.contains(iVar2)) {
                            iVar = iVar2;
                            break;
                        }
                    }
                }
                b(iVar);
                this.f.setImageDrawable(C0025h.this.c(iVar));
                this.h.setText(iVar.m());
                boolean z2 = false;
                this.j.setVisibility(0);
                boolean h = h(iVar);
                boolean g = g(iVar);
                this.j.setChecked(h);
                this.g.setVisibility(4);
                this.f.setVisibility(0);
                this.e.setEnabled(g);
                this.j.setEnabled(g);
                ImageButton imageButton = this.b;
                if (!g && !h) {
                    z = false;
                } else {
                    z = true;
                }
                imageButton.setEnabled(z);
                this.c.setEnabled((g || h) ? true : true);
                this.e.setOnClickListener(this.n);
                this.j.setOnClickListener(this.n);
                RelativeLayout relativeLayout = this.i;
                if (h && !this.a.y()) {
                    i = this.l;
                } else {
                    i = this.m;
                }
                h.h(relativeLayout, i);
                View view = this.e;
                float f2 = 1.0f;
                if (!g && !h) {
                    f = this.k;
                } else {
                    f = 1.0f;
                }
                view.setAlpha(f);
                CheckBox checkBox = this.j;
                if (!g && h) {
                    f2 = this.k;
                }
                checkBox.setAlpha(f2);
            }

            public final boolean g(t0.i iVar) {
                if (h.this.h.contains(iVar)) {
                    return false;
                }
                if (h(iVar) && h.this.d.l().size() < 2) {
                    return false;
                }
                if (!h(iVar)) {
                    return true;
                }
                t0.i.a h = h.this.d.h(iVar);
                if (h == null || !h.d()) {
                    return false;
                }
                return true;
            }

            public boolean h(t0.i iVar) {
                if (iVar.C()) {
                    return true;
                }
                t0.i.a h = h.this.d.h(iVar);
                if (h != null && h.a() == 3) {
                    return true;
                }
                return false;
            }

            public void i(boolean z, boolean z2) {
                int i;
                this.j.setEnabled(false);
                this.e.setEnabled(false);
                this.j.setChecked(z);
                if (z) {
                    this.f.setVisibility(4);
                    this.g.setVisibility(0);
                }
                if (z2) {
                    C0025h c0025h = C0025h.this;
                    RelativeLayout relativeLayout = this.i;
                    if (z) {
                        i = this.l;
                    } else {
                        i = this.m;
                    }
                    c0025h.a(relativeLayout, i);
                }
            }
        }

        public C0025h() {
            this.b = LayoutInflater.from(h.this.i);
            this.c = androidx.mediarouter.app.i.g(h.this.i);
            this.d = androidx.mediarouter.app.i.q(h.this.i);
            this.e = androidx.mediarouter.app.i.m(h.this.i);
            this.f = androidx.mediarouter.app.i.n(h.this.i);
            this.h = h.this.i.getResources().getInteger(R$integer.mr_cast_volume_slider_layout_animation_duration_ms);
            h();
        }

        public void a(View view, int i) {
            a aVar = new a(i, view.getLayoutParams().height, view);
            aVar.setAnimationListener(new b());
            aVar.setDuration(this.h);
            aVar.setInterpolator(this.i);
            view.startAnimation(aVar);
        }

        public final Drawable b(t0.i iVar) {
            int f2 = iVar.f();
            if (f2 != 1) {
                if (f2 != 2) {
                    if (iVar.y()) {
                        return this.f;
                    }
                    return this.c;
                }
                return this.e;
            }
            return this.d;
        }

        public Drawable c(t0.i iVar) {
            Uri j = iVar.j();
            if (j != null) {
                try {
                    Drawable createFromStream = Drawable.createFromStream(h.this.i.getContentResolver().openInputStream(j), null);
                    if (createFromStream != null) {
                        return createFromStream;
                    }
                } catch (IOException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to load ");
                    sb.append(j);
                }
            }
            return b(iVar);
        }

        public f d(int i) {
            if (i == 0) {
                return this.g;
            }
            return (f) this.a.get(i - 1);
        }

        public boolean e() {
            if (h.this.d.l().size() > 1) {
                return true;
            }
            return false;
        }

        public void f(t0.i iVar, boolean z) {
            int i;
            List l = h.this.d.l();
            boolean z2 = true;
            int max = Math.max(1, l.size());
            int i2 = -1;
            if (iVar.y()) {
                for (t0.i iVar2 : iVar.l()) {
                    if (l.contains(iVar2) != z) {
                        if (z) {
                            i = 1;
                        } else {
                            i = -1;
                        }
                        max += i;
                    }
                }
            } else {
                if (z) {
                    i2 = 1;
                }
                max += i2;
            }
            boolean e2 = e();
            int i3 = 0;
            if (max < 2) {
                z2 = false;
            }
            if (e2 != z2) {
                RecyclerView.d0 findViewHolderForAdapterPosition = h.this.n.findViewHolderForAdapterPosition(0);
                if (findViewHolderForAdapterPosition instanceof d) {
                    d dVar = (d) findViewHolderForAdapterPosition;
                    View view = dVar.itemView;
                    if (z2) {
                        i3 = dVar.g();
                    }
                    a(view, i3);
                }
            }
        }

        public void g() {
            h.this.h.clear();
            h hVar = h.this;
            hVar.h.addAll(androidx.mediarouter.app.f.g(hVar.f, hVar.c()));
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            return this.a.size() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemViewType(int i) {
            return d(i).b();
        }

        public void h() {
            String str;
            String str2;
            this.a.clear();
            this.g = new f(h.this.d, 1);
            if (!h.this.e.isEmpty()) {
                for (t0.i iVar : h.this.e) {
                    this.a.add(new f(iVar, 3));
                }
            } else {
                this.a.add(new f(h.this.d, 3));
            }
            boolean z = false;
            if (!h.this.f.isEmpty()) {
                boolean z2 = false;
                for (t0.i iVar2 : h.this.f) {
                    if (!h.this.e.contains(iVar2)) {
                        if (!z2) {
                            p0.b g2 = h.this.d.g();
                            if (g2 != null) {
                                str2 = g2.j();
                            } else {
                                str2 = null;
                            }
                            if (TextUtils.isEmpty(str2)) {
                                str2 = h.this.i.getString(R$string.mr_dialog_groupable_header);
                            }
                            this.a.add(new f(str2, 2));
                            z2 = true;
                        }
                        this.a.add(new f(iVar2, 3));
                    }
                }
            }
            if (!h.this.g.isEmpty()) {
                for (t0.i iVar3 : h.this.g) {
                    t0.i iVar4 = h.this.d;
                    if (iVar4 != iVar3) {
                        if (!z) {
                            p0.b g3 = iVar4.g();
                            if (g3 != null) {
                                str = g3.k();
                            } else {
                                str = null;
                            }
                            if (TextUtils.isEmpty(str)) {
                                str = h.this.i.getString(R$string.mr_dialog_transferable_header);
                            }
                            this.a.add(new f(str, 2));
                            z = true;
                        }
                        this.a.add(new f(iVar3, 4));
                    }
                }
            }
            g();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void onBindViewHolder(RecyclerView.d0 d0Var, int i) {
            int itemViewType = getItemViewType(i);
            f d2 = d(i);
            if (itemViewType != 1) {
                if (itemViewType != 2) {
                    if (itemViewType != 3) {
                        if (itemViewType == 4) {
                            ((c) d0Var).b(d2);
                            return;
                        }
                        return;
                    }
                    h.this.q.put(((t0.i) d2.a()).k(), (f) d0Var);
                    ((g) d0Var).f(d2);
                    return;
                }
                ((e) d0Var).b(d2);
                return;
            }
            h.this.q.put(((t0.i) d2.a()).k(), (f) d0Var);
            ((d) d0Var).f(d2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public RecyclerView.d0 onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return null;
                        }
                        return new c(this.b.inflate(R$layout.mr_cast_group_item, viewGroup, false));
                    }
                    return new g(this.b.inflate(R$layout.mr_cast_route_item, viewGroup, false));
                }
                return new e(this.b.inflate(R$layout.mr_cast_header_item, viewGroup, false));
            }
            return new d(this.b.inflate(R$layout.mr_cast_group_volume_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void onViewRecycled(RecyclerView.d0 d0Var) {
            super.onViewRecycled(d0Var);
            h.this.q.values().remove(d0Var);
        }
    }

    /* loaded from: classes.dex */
    public static final class i implements Comparator {
        public static final i a = new i();

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(t0.i iVar, t0.i iVar2) {
            return iVar.m().compareToIgnoreCase(iVar2.m());
        }
    }

    /* loaded from: classes.dex */
    public class j implements SeekBar.OnSeekBarChangeListener {
        public j() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            boolean z2;
            if (z) {
                t0.i iVar = (t0.i) seekBar.getTag();
                f fVar = (f) h.this.q.get(iVar.k());
                if (fVar != null) {
                    if (i == 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    fVar.d(z2);
                }
                iVar.G(i);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            h hVar = h.this;
            if (hVar.r != null) {
                hVar.m.removeMessages(2);
            }
            h.this.r = (t0.i) seekBar.getTag();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            h.this.m.sendEmptyMessageDelayed(2, 500L);
        }
    }

    public h(Context context) {
        this(context, 0);
    }

    public static Bitmap a(Bitmap bitmap, float f2, Context context) {
        RenderScript create = RenderScript.create(context);
        Allocation createFromBitmap = Allocation.createFromBitmap(create, bitmap);
        Allocation createTyped = Allocation.createTyped(create, createFromBitmap.getType());
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        create2.setRadius(f2);
        create2.setInput(createFromBitmap);
        create2.forEach(createTyped);
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        createTyped.copyTo(copy);
        createFromBitmap.destroy();
        createTyped.destroy();
        create2.destroy();
        create.destroy();
        return copy;
    }

    public static boolean d(Bitmap bitmap) {
        if (bitmap != null && bitmap.isRecycled()) {
            return true;
        }
        return false;
    }

    public static void h(View view, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i2;
        view.setLayoutParams(layoutParams);
    }

    public void b() {
        this.L = false;
        this.M = null;
        this.N = 0;
    }

    public List c() {
        ArrayList arrayList = new ArrayList();
        for (t0.i iVar : this.d.q().f()) {
            t0.i.a h = this.d.h(iVar);
            if (h != null && h.b()) {
                arrayList.add(iVar);
            }
        }
        return arrayList;
    }

    public boolean e(t0.i iVar) {
        if (!iVar.w() && iVar.x() && iVar.E(this.c) && this.d != iVar) {
            return true;
        }
        return false;
    }

    public void f(List list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (!e((t0.i) list.get(size))) {
                list.remove(size);
            }
        }
    }

    public void g() {
        Bitmap b2;
        Bitmap b3;
        Uri c2;
        MediaDescriptionCompat mediaDescriptionCompat = this.H;
        Uri uri = null;
        if (mediaDescriptionCompat == null) {
            b2 = null;
        } else {
            b2 = mediaDescriptionCompat.b();
        }
        MediaDescriptionCompat mediaDescriptionCompat2 = this.H;
        if (mediaDescriptionCompat2 != null) {
            uri = mediaDescriptionCompat2.c();
        }
        d dVar = this.I;
        if (dVar == null) {
            b3 = this.J;
        } else {
            b3 = dVar.b();
        }
        d dVar2 = this.I;
        if (dVar2 == null) {
            c2 = this.K;
        } else {
            c2 = dVar2.c();
        }
        if (b3 == b2 && (b3 != null || u.d.a(c2, uri))) {
            return;
        }
        d dVar3 = this.I;
        if (dVar3 != null) {
            dVar3.cancel(true);
        }
        d dVar4 = new d();
        this.I = dVar4;
        dVar4.execute(new Void[0]);
    }

    public final void i(MediaSessionCompat.Token token) {
        MediaControllerCompat mediaControllerCompat = this.F;
        MediaDescriptionCompat mediaDescriptionCompat = null;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.g(this.G);
            this.F = null;
        }
        if (token == null || !this.k) {
            return;
        }
        MediaControllerCompat mediaControllerCompat2 = new MediaControllerCompat(this.i, token);
        this.F = mediaControllerCompat2;
        mediaControllerCompat2.e(this.G);
        MediaMetadataCompat a2 = this.F.a();
        if (a2 != null) {
            mediaDescriptionCompat = a2.e();
        }
        this.H = mediaDescriptionCompat;
        g();
        m();
    }

    public void j(s0 s0Var) {
        if (s0Var != null) {
            if (!this.c.equals(s0Var)) {
                this.c = s0Var;
                if (this.k) {
                    this.a.q(this.b);
                    this.a.b(s0Var, this.b, 1);
                    n();
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("selector must not be null");
    }

    public final boolean k() {
        if (this.r != null || this.t || this.u) {
            return true;
        }
        return !this.j;
    }

    public void l() {
        getWindow().setLayout(androidx.mediarouter.app.f.c(this.i), androidx.mediarouter.app.f.a(this.i));
        this.J = null;
        this.K = null;
        g();
        m();
        o();
    }

    public void m() {
        CharSequence f2;
        if (k()) {
            this.w = true;
            return;
        }
        this.w = false;
        if (!this.d.C() || this.d.w()) {
            dismiss();
        }
        CharSequence charSequence = null;
        if (this.L && !d(this.M) && this.M != null) {
            this.B.setVisibility(0);
            this.B.setImageBitmap(this.M);
            this.B.setBackgroundColor(this.N);
            this.A.setVisibility(0);
            this.z.setImageBitmap(a(this.M, 10.0f, this.i));
        } else {
            if (d(this.M)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Can't set artwork image with recycled bitmap: ");
                sb.append(this.M);
            }
            this.B.setVisibility(8);
            this.A.setVisibility(8);
            this.z.setImageBitmap(null);
        }
        b();
        MediaDescriptionCompat mediaDescriptionCompat = this.H;
        if (mediaDescriptionCompat == null) {
            f2 = null;
        } else {
            f2 = mediaDescriptionCompat.f();
        }
        boolean z = !TextUtils.isEmpty(f2);
        MediaDescriptionCompat mediaDescriptionCompat2 = this.H;
        if (mediaDescriptionCompat2 != null) {
            charSequence = mediaDescriptionCompat2.e();
        }
        boolean isEmpty = true ^ TextUtils.isEmpty(charSequence);
        if (z) {
            this.C.setText(f2);
        } else {
            this.C.setText(this.E);
        }
        if (isEmpty) {
            this.D.setText(charSequence);
            this.D.setVisibility(0);
            return;
        }
        this.D.setVisibility(8);
    }

    public void n() {
        this.e.clear();
        this.f.clear();
        this.g.clear();
        this.e.addAll(this.d.l());
        for (t0.i iVar : this.d.q().f()) {
            t0.i.a h = this.d.h(iVar);
            if (h != null) {
                if (h.b()) {
                    this.f.add(iVar);
                }
                if (h.c()) {
                    this.g.add(iVar);
                }
            }
        }
        f(this.f);
        f(this.g);
        List list = this.e;
        i iVar2 = i.a;
        Collections.sort(list, iVar2);
        Collections.sort(this.f, iVar2);
        Collections.sort(this.g, iVar2);
        this.o.h();
    }

    public void o() {
        if (this.k) {
            if (SystemClock.uptimeMillis() - this.l >= 300) {
                if (k()) {
                    this.v = true;
                    return;
                }
                this.v = false;
                if (!this.d.C() || this.d.w()) {
                    dismiss();
                }
                this.l = SystemClock.uptimeMillis();
                this.o.g();
                return;
            }
            this.m.removeMessages(1);
            this.m.sendEmptyMessageAtTime(1, this.l + 300);
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.k = true;
        this.a.b(this.c, this.b, 1);
        n();
        i(this.a.j());
    }

    @Override // androidx.appcompat.app.j, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.mr_cast_dialog);
        androidx.mediarouter.app.i.s(this.i, this);
        ImageButton imageButton = (ImageButton) findViewById(R$id.mr_cast_close_button);
        this.x = imageButton;
        imageButton.setColorFilter(-1);
        this.x.setOnClickListener(new b());
        Button button = (Button) findViewById(R$id.mr_cast_stop_button);
        this.y = button;
        button.setTextColor(-1);
        this.y.setOnClickListener(new c());
        this.o = new C0025h();
        RecyclerView recyclerView = (RecyclerView) findViewById(R$id.mr_cast_list);
        this.n = recyclerView;
        recyclerView.setAdapter(this.o);
        this.n.setLayoutManager(new LinearLayoutManager(this.i));
        this.p = new j();
        this.q = new HashMap();
        this.f24s = new HashMap();
        this.z = (ImageView) findViewById(R$id.mr_cast_meta_background);
        this.A = findViewById(R$id.mr_cast_meta_black_scrim);
        this.B = (ImageView) findViewById(R$id.mr_cast_meta_art);
        TextView textView = (TextView) findViewById(R$id.mr_cast_meta_title);
        this.C = textView;
        textView.setTextColor(-1);
        TextView textView2 = (TextView) findViewById(R$id.mr_cast_meta_subtitle);
        this.D = textView2;
        textView2.setTextColor(-1);
        this.E = this.i.getResources().getString(R$string.mr_cast_dialog_title_view_placeholder);
        this.j = true;
        l();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.k = false;
        this.a.q(this.b);
        this.m.removeCallbacksAndMessages(null);
        i(null);
    }

    public void p() {
        if (this.v) {
            o();
        }
        if (this.w) {
            m();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public h(android.content.Context r2, int r3) {
        /*
            r1 = this;
            r0 = 0
            android.content.Context r2 = androidx.mediarouter.app.i.b(r2, r3, r0)
            int r3 = androidx.mediarouter.app.i.c(r2)
            r1.<init>(r2, r3)
            h0.s0 r2 = h0.s0.c
            r1.c = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.e = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.f = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.g = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.h = r2
            androidx.mediarouter.app.h$a r2 = new androidx.mediarouter.app.h$a
            r2.<init>()
            r1.m = r2
            android.content.Context r2 = r1.getContext()
            r1.i = r2
            h0.t0 r2 = h0.t0.i(r2)
            r1.a = r2
            androidx.mediarouter.app.h$g r3 = new androidx.mediarouter.app.h$g
            r3.<init>()
            r1.b = r3
            h0.t0$i r3 = r2.m()
            r1.d = r3
            androidx.mediarouter.app.h$e r3 = new androidx.mediarouter.app.h$e
            r3.<init>()
            r1.G = r3
            android.support.v4.media.session.MediaSessionCompat$Token r2 = r2.j()
            r1.i(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.h.<init>(android.content.Context, int):void");
    }
}
