package androidx.mediarouter.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.mediarouter.R$dimen;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$integer;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.R$string;
import androidx.mediarouter.app.OverlayListView;
import h0.s0;
import h0.t0;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class c extends androidx.appcompat.app.c {
    public static final boolean r0 = Log.isLoggable("MediaRouteCtrlDialog", 3);
    public static final int s0 = (int) TimeUnit.SECONDS.toMillis(30);
    public OverlayListView A;
    public r B;
    public List C;
    public Set D;
    public Set E;
    public Set F;
    public SeekBar G;
    public q H;
    public t0.i I;
    public int J;
    public int K;
    public int L;
    public final int M;
    public Map N;
    public MediaControllerCompat O;
    public o Q;
    public PlaybackStateCompat S;
    public MediaDescriptionCompat V;
    public n W;
    public Bitmap X;
    public Uri Y;
    public boolean Z;
    public final t0 a;
    public final p b;
    public Bitmap b0;
    public final t0.i c;
    public int c0;
    public Context d;
    public boolean d0;
    public boolean e;
    public boolean e0;
    public boolean f;
    public boolean f0;
    public int g;
    public boolean g0;
    public View h;
    public boolean h0;
    public Button i;
    public int i0;
    public Button j;
    public int j0;
    public ImageButton k;
    public int k0;
    public ImageButton l;
    public Interpolator l0;
    public MediaRouteExpandCollapseButton m;
    public Interpolator m0;
    public FrameLayout n;
    public Interpolator n0;
    public LinearLayout o;
    public Interpolator o0;
    public FrameLayout p;
    public final AccessibilityManager p0;
    public FrameLayout q;
    public Runnable q0;
    public ImageView r;

    /* renamed from: s  reason: collision with root package name */
    public TextView f23s;
    public TextView t;
    public TextView u;
    public boolean v;
    public LinearLayout w;
    public RelativeLayout x;
    public LinearLayout y;
    public View z;

    /* loaded from: classes.dex */
    public class a implements OverlayListView.a.InterfaceC0022a {
        public final /* synthetic */ t0.i a;

        public a(t0.i iVar) {
            this.a = iVar;
        }

        @Override // androidx.mediarouter.app.OverlayListView.a.InterfaceC0022a
        public void onAnimationEnd() {
            c.this.F.remove(this.a);
            c.this.B.notifyDataSetChanged();
        }
    }

    /* loaded from: classes.dex */
    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            c.this.A.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            c.this.x();
        }
    }

    /* renamed from: androidx.mediarouter.app.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class animation.Animation$AnimationListenerC0024c implements Animation.AnimationListener {
        public animation.Animation$AnimationListenerC0024c() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            c.this.h(true);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.w();
        }
    }

    /* loaded from: classes.dex */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    /* loaded from: classes.dex */
    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PendingIntent c;
            MediaControllerCompat mediaControllerCompat = c.this.O;
            if (mediaControllerCompat != null && (c = mediaControllerCompat.c()) != null) {
                try {
                    c.send();
                    c.this.dismiss();
                } catch (PendingIntent.CanceledException unused) {
                    Log.e("MediaRouteCtrlDialog", c + " was not sent, it had been canceled.");
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c cVar = c.this;
            boolean z = !cVar.f0;
            cVar.f0 = z;
            if (z) {
                cVar.A.setVisibility(0);
            }
            c.this.r();
            c.this.B(true);
        }
    }

    /* loaded from: classes.dex */
    public class i implements ViewTreeObserver.OnGlobalLayoutListener {
        public final /* synthetic */ boolean a;

        public i(boolean z) {
            this.a = z;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            c.this.p.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            c cVar = c.this;
            if (cVar.g0) {
                cVar.h0 = true;
            } else {
                cVar.C(this.a);
            }
        }
    }

    /* loaded from: classes.dex */
    public class j extends Animation {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;
        public final /* synthetic */ View c;

        public j(int i, int i2, View view) {
            this.a = i;
            this.b = i2;
            this.c = view;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            int i = this.a;
            c.u(this.c, i - ((int) ((i - this.b) * f)));
        }
    }

    /* loaded from: classes.dex */
    public class k implements ViewTreeObserver.OnGlobalLayoutListener {
        public final /* synthetic */ Map a;
        public final /* synthetic */ Map b;

        public k(Map map, Map map2) {
            this.a = map;
            this.b = map2;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            c.this.A.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            c.this.b(this.a, this.b);
        }
    }

    /* loaded from: classes.dex */
    public class l implements Animation.AnimationListener {
        public l() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            c.this.A.b();
            c cVar = c.this;
            cVar.A.postDelayed(cVar.q0, cVar.i0);
        }
    }

    /* loaded from: classes.dex */
    public final class m implements View.OnClickListener {
        public m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PlaybackStateCompat playbackStateCompat;
            int id = view.getId();
            int i = 1;
            if (id != 16908313 && id != 16908314) {
                if (id == R$id.mr_control_playback_ctrl) {
                    c cVar = c.this;
                    if (cVar.O != null && (playbackStateCompat = cVar.S) != null) {
                        int i2 = 0;
                        if (playbackStateCompat.g() != 3) {
                            i = 0;
                        }
                        if (i != 0 && c.this.n()) {
                            c.this.O.d().a();
                            i2 = R$string.mr_controller_pause;
                        } else if (i != 0 && c.this.p()) {
                            c.this.O.d().c();
                            i2 = R$string.mr_controller_stop;
                        } else if (i == 0 && c.this.o()) {
                            c.this.O.d().b();
                            i2 = R$string.mr_controller_play;
                        }
                        AccessibilityManager accessibilityManager = c.this.p0;
                        if (accessibilityManager != null && accessibilityManager.isEnabled() && i2 != 0) {
                            AccessibilityEvent obtain = AccessibilityEvent.obtain(16384);
                            obtain.setPackageName(c.this.d.getPackageName());
                            obtain.setClassName(m.class.getName());
                            obtain.getText().add(c.this.d.getString(i2));
                            c.this.p0.sendAccessibilityEvent(obtain);
                            return;
                        }
                        return;
                    }
                    return;
                } else if (id == R$id.mr_close) {
                    c.this.dismiss();
                    return;
                } else {
                    return;
                }
            }
            if (c.this.c.C()) {
                t0 t0Var = c.this.a;
                if (id == 16908313) {
                    i = 2;
                }
                t0Var.x(i);
            }
            c.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public class n extends AsyncTask {
        public final Bitmap a;
        public final Uri b;
        public int c;
        public long d;

        public n() {
            Bitmap b;
            MediaDescriptionCompat mediaDescriptionCompat = c.this.V;
            if (mediaDescriptionCompat == null) {
                b = null;
            } else {
                b = mediaDescriptionCompat.b();
            }
            this.a = c.l(b) ? null : b;
            MediaDescriptionCompat mediaDescriptionCompat2 = c.this.V;
            this.b = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.c() : null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00b6  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x00c4  */
        /* JADX WARN: Type inference failed for: r3v0 */
        /* JADX WARN: Type inference failed for: r3v2 */
        /* JADX WARN: Type inference failed for: r3v3, types: [java.io.InputStream] */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public android.graphics.Bitmap doInBackground(java.lang.Void... r9) {
            /*
                Method dump skipped, instructions count: 249
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.c.n.doInBackground(java.lang.Void[]):android.graphics.Bitmap");
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
            c cVar = c.this;
            cVar.W = null;
            if (!u.d.a(cVar.X, this.a) || !u.d.a(c.this.Y, this.b)) {
                c cVar2 = c.this;
                cVar2.X = this.a;
                cVar2.b0 = bitmap;
                cVar2.Y = this.b;
                cVar2.c0 = this.c;
                boolean z = true;
                cVar2.Z = true;
                long uptimeMillis = SystemClock.uptimeMillis() - this.d;
                c cVar3 = c.this;
                if (uptimeMillis <= 120) {
                    z = false;
                }
                cVar3.y(z);
            }
        }

        public final InputStream e(Uri uri) {
            InputStream openInputStream;
            String lowerCase = uri.getScheme().toLowerCase();
            if (!"android.resource".equals(lowerCase) && !"content".equals(lowerCase) && !"file".equals(lowerCase)) {
                URLConnection openConnection = new URL(uri.toString()).openConnection();
                int i = c.s0;
                openConnection.setConnectTimeout(i);
                openConnection.setReadTimeout(i);
                openInputStream = openConnection.getInputStream();
            } else {
                openInputStream = c.this.d.getContentResolver().openInputStream(uri);
            }
            if (openInputStream == null) {
                return null;
            }
            return new BufferedInputStream(openInputStream);
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            this.d = SystemClock.uptimeMillis();
            c.this.f();
        }
    }

    /* loaded from: classes.dex */
    public final class o extends MediaControllerCompat.a {
        public o() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            MediaDescriptionCompat e;
            c cVar = c.this;
            if (mediaMetadataCompat == null) {
                e = null;
            } else {
                e = mediaMetadataCompat.e();
            }
            cVar.V = e;
            c.this.z();
            c.this.y(false);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            c cVar = c.this;
            cVar.S = playbackStateCompat;
            cVar.y(false);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void i() {
            c cVar = c.this;
            MediaControllerCompat mediaControllerCompat = cVar.O;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.g(cVar.Q);
                c.this.O = null;
            }
        }
    }

    /* loaded from: classes.dex */
    public final class p extends t0.b {
        public p() {
        }

        public void onRouteChanged(t0 t0Var, t0.i iVar) {
            c.this.y(true);
        }

        public void onRouteUnselected(t0 t0Var, t0.i iVar) {
            c.this.y(false);
        }

        public void onRouteVolumeChanged(t0 t0Var, t0.i iVar) {
            SeekBar seekBar = (SeekBar) c.this.N.get(iVar);
            int s2 = iVar.s();
            if (c.r0) {
                StringBuilder sb = new StringBuilder();
                sb.append("onRouteVolumeChanged(), route.getVolume:");
                sb.append(s2);
            }
            if (seekBar != null && c.this.I != iVar) {
                seekBar.setProgress(s2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class q implements SeekBar.OnSeekBarChangeListener {
        public final Runnable a = new a();

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c cVar = c.this;
                if (cVar.I != null) {
                    cVar.I = null;
                    if (cVar.d0) {
                        cVar.y(cVar.e0);
                    }
                }
            }
        }

        public q() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                t0.i iVar = (t0.i) seekBar.getTag();
                if (c.r0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("onProgressChanged(): calling MediaRouter.RouteInfo.requestSetVolume(");
                    sb.append(i);
                    sb.append(")");
                }
                iVar.G(i);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            c cVar = c.this;
            if (cVar.I != null) {
                cVar.G.removeCallbacks(this.a);
            }
            c.this.I = (t0.i) seekBar.getTag();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            c.this.G.postDelayed(this.a, 500L);
        }
    }

    /* loaded from: classes.dex */
    public class r extends ArrayAdapter {
        public final float a;

        public r(Context context, List list) {
            super(context, 0, list);
            this.a = androidx.mediarouter.app.i.h(context);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            int i2;
            int i3 = 0;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mr_controller_volume_item, viewGroup, false);
            } else {
                c.this.G(view);
            }
            t0.i iVar = (t0.i) getItem(i);
            if (iVar != null) {
                boolean x = iVar.x();
                TextView textView = (TextView) view.findViewById(R$id.mr_name);
                textView.setEnabled(x);
                textView.setText(iVar.m());
                MediaRouteVolumeSlider mediaRouteVolumeSlider = (MediaRouteVolumeSlider) view.findViewById(R$id.mr_volume_slider);
                androidx.mediarouter.app.i.w(viewGroup.getContext(), mediaRouteVolumeSlider, c.this.A);
                mediaRouteVolumeSlider.setTag(iVar);
                c.this.N.put(iVar, mediaRouteVolumeSlider);
                mediaRouteVolumeSlider.c(!x);
                mediaRouteVolumeSlider.setEnabled(x);
                if (x) {
                    if (c.this.q(iVar)) {
                        mediaRouteVolumeSlider.setMax(iVar.u());
                        mediaRouteVolumeSlider.setProgress(iVar.s());
                        mediaRouteVolumeSlider.setOnSeekBarChangeListener(c.this.H);
                    } else {
                        mediaRouteVolumeSlider.setMax(100);
                        mediaRouteVolumeSlider.setProgress(100);
                        mediaRouteVolumeSlider.setEnabled(false);
                    }
                }
                ImageView imageView = (ImageView) view.findViewById(R$id.mr_volume_item_icon);
                if (x) {
                    i2 = 255;
                } else {
                    i2 = (int) (this.a * 255.0f);
                }
                imageView.setAlpha(i2);
                LinearLayout linearLayout = (LinearLayout) view.findViewById(R$id.volume_item_container);
                if (c.this.F.contains(iVar)) {
                    i3 = 4;
                }
                linearLayout.setVisibility(i3);
                Set set = c.this.D;
                if (set != null && set.contains(iVar)) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
                    alphaAnimation.setDuration(0L);
                    alphaAnimation.setFillEnabled(true);
                    alphaAnimation.setFillAfter(true);
                    view.clearAnimation();
                    view.startAnimation(alphaAnimation);
                }
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            return false;
        }
    }

    public c(Context context) {
        this(context, 0);
    }

    public static boolean H(Uri uri, Uri uri2) {
        if (uri != null && uri.equals(uri2)) {
            return true;
        }
        if (uri == null && uri2 == null) {
            return true;
        }
        return false;
    }

    public static int j(View view) {
        return view.getLayoutParams().height;
    }

    public static boolean l(Bitmap bitmap) {
        if (bitmap != null && bitmap.isRecycled()) {
            return true;
        }
        return false;
    }

    public static void u(View view, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i2;
        view.setLayoutParams(layoutParams);
    }

    public void A() {
        int b2 = androidx.mediarouter.app.f.b(this.d);
        getWindow().setLayout(b2, -2);
        View decorView = getWindow().getDecorView();
        this.g = (b2 - decorView.getPaddingLeft()) - decorView.getPaddingRight();
        Resources resources = this.d.getResources();
        this.J = resources.getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_item_icon_size);
        this.K = resources.getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_item_height);
        this.L = resources.getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_max_height);
        this.X = null;
        this.Y = null;
        z();
        y(false);
    }

    public void B(boolean z) {
        this.p.requestLayout();
        this.p.getViewTreeObserver().addOnGlobalLayoutListener(new i(z));
    }

    public void C(boolean z) {
        int i2;
        int i3;
        boolean z2;
        Bitmap bitmap;
        ImageView.ScaleType scaleType;
        int j2 = j(this.w);
        u(this.w, -1);
        D(d());
        View decorView = getWindow().getDecorView();
        boolean z3 = false;
        decorView.measure(View.MeasureSpec.makeMeasureSpec(getWindow().getAttributes().width, 1073741824), 0);
        u(this.w, j2);
        if (this.h == null && (this.r.getDrawable() instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) this.r.getDrawable()).getBitmap()) != null) {
            i2 = i(bitmap.getWidth(), bitmap.getHeight());
            ImageView imageView = this.r;
            if (bitmap.getWidth() >= bitmap.getHeight()) {
                scaleType = ImageView.ScaleType.FIT_XY;
            } else {
                scaleType = ImageView.ScaleType.FIT_CENTER;
            }
            imageView.setScaleType(scaleType);
        } else {
            i2 = 0;
        }
        int k2 = k(d());
        int size = this.C.size();
        if (this.c.y()) {
            i3 = this.K * this.c.l().size();
        } else {
            i3 = 0;
        }
        if (size > 0) {
            i3 += this.M;
        }
        int min = Math.min(i3, this.L);
        if (!this.f0) {
            min = 0;
        }
        int max = Math.max(i2, min) + k2;
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int height = rect.height() - (this.o.getMeasuredHeight() - this.p.getMeasuredHeight());
        if (this.h == null && i2 > 0 && max <= height) {
            this.r.setVisibility(0);
            u(this.r, i2);
        } else {
            if (j(this.A) + this.w.getMeasuredHeight() >= this.p.getMeasuredHeight()) {
                this.r.setVisibility(8);
            }
            max = min + k2;
            i2 = 0;
        }
        if (d() && max <= height) {
            this.x.setVisibility(0);
        } else {
            this.x.setVisibility(8);
        }
        if (this.x.getVisibility() == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        D(z2);
        if (this.x.getVisibility() == 0) {
            z3 = true;
        }
        int k3 = k(z3);
        int max2 = Math.max(i2, min) + k3;
        if (max2 > height) {
            min -= max2 - height;
        } else {
            height = max2;
        }
        this.w.clearAnimation();
        this.A.clearAnimation();
        this.p.clearAnimation();
        if (z) {
            c(this.w, k3);
            c(this.A, min);
            c(this.p, height);
        } else {
            u(this.w, k3);
            u(this.A, min);
            u(this.p, height);
        }
        u(this.n, rect.height());
        t(z);
    }

    public final void D(boolean z) {
        int i2;
        View view = this.z;
        int i3 = 0;
        if (this.y.getVisibility() == 0 && z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        view.setVisibility(i2);
        LinearLayout linearLayout = this.w;
        if (this.y.getVisibility() == 8 && !z) {
            i3 = 8;
        }
        linearLayout.setVisibility(i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void E() {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.c.E():void");
    }

    public final void F() {
        int i2 = 8;
        if (q(this.c)) {
            if (this.y.getVisibility() == 8) {
                this.y.setVisibility(0);
                this.G.setMax(this.c.u());
                this.G.setProgress(this.c.s());
                MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = this.m;
                if (this.c.y()) {
                    i2 = 0;
                }
                mediaRouteExpandCollapseButton.setVisibility(i2);
                return;
            }
            return;
        }
        this.y.setVisibility(8);
    }

    public void G(View view) {
        u((LinearLayout) view.findViewById(R$id.volume_item_container), this.K);
        View findViewById = view.findViewById(R$id.mr_volume_item_icon);
        ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
        int i2 = this.J;
        layoutParams.width = i2;
        layoutParams.height = i2;
        findViewById.setLayoutParams(layoutParams);
    }

    public final void a(Map map, Map map2) {
        this.A.setEnabled(false);
        this.A.requestLayout();
        this.g0 = true;
        this.A.getViewTreeObserver().addOnGlobalLayoutListener(new k(map, map2));
    }

    public void b(Map map, Map map2) {
        OverlayListView.a d2;
        int i2;
        Set set = this.D;
        if (set != null && this.E != null) {
            int size = set.size() - this.E.size();
            l lVar = new l();
            int firstVisiblePosition = this.A.getFirstVisiblePosition();
            boolean z = false;
            for (int i3 = 0; i3 < this.A.getChildCount(); i3++) {
                View childAt = this.A.getChildAt(i3);
                Object obj = (t0.i) this.B.getItem(firstVisiblePosition + i3);
                Rect rect = (Rect) map.get(obj);
                int top = childAt.getTop();
                if (rect != null) {
                    i2 = rect.top;
                } else {
                    i2 = (this.K * size) + top;
                }
                AnimationSet animationSet = new AnimationSet(true);
                Set set2 = this.D;
                if (set2 != null && set2.contains(obj)) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
                    alphaAnimation.setDuration(this.j0);
                    animationSet.addAnimation(alphaAnimation);
                    i2 = top;
                }
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, i2 - top, 0.0f);
                translateAnimation.setDuration(this.i0);
                animationSet.addAnimation(translateAnimation);
                animationSet.setFillAfter(true);
                animationSet.setFillEnabled(true);
                animationSet.setInterpolator(this.l0);
                if (!z) {
                    animationSet.setAnimationListener(lVar);
                    z = true;
                }
                childAt.clearAnimation();
                childAt.startAnimation(animationSet);
                map.remove(obj);
                map2.remove(obj);
            }
            for (Map.Entry entry : map2.entrySet()) {
                t0.i iVar = (t0.i) entry.getKey();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) entry.getValue();
                Rect rect2 = (Rect) map.get(iVar);
                if (this.E.contains(iVar)) {
                    d2 = new OverlayListView.a(bitmapDrawable, rect2).c(1.0f, 0.0f).e(this.k0).f(this.l0);
                } else {
                    d2 = new OverlayListView.a(bitmapDrawable, rect2).g(this.K * size).e(this.i0).f(this.l0).d(new a(iVar));
                    this.F.add(iVar);
                }
                this.A.a(d2);
            }
        }
    }

    public final void c(View view, int i2) {
        j jVar = new j(j(view), i2, view);
        jVar.setDuration(this.i0);
        if (Build.VERSION.SDK_INT >= 21) {
            jVar.setInterpolator(this.l0);
        }
        view.startAnimation(jVar);
    }

    public final boolean d() {
        if (this.h == null && (this.V != null || this.S != null)) {
            return true;
        }
        return false;
    }

    public void e(boolean z) {
        Set set;
        int firstVisiblePosition = this.A.getFirstVisiblePosition();
        for (int i2 = 0; i2 < this.A.getChildCount(); i2++) {
            View childAt = this.A.getChildAt(i2);
            t0.i iVar = (t0.i) this.B.getItem(firstVisiblePosition + i2);
            if (!z || (set = this.D) == null || !set.contains(iVar)) {
                ((LinearLayout) childAt.findViewById(R$id.volume_item_container)).setVisibility(0);
                AnimationSet animationSet = new AnimationSet(true);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 1.0f);
                alphaAnimation.setDuration(0L);
                animationSet.addAnimation(alphaAnimation);
                new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f).setDuration(0L);
                animationSet.setFillAfter(true);
                animationSet.setFillEnabled(true);
                childAt.clearAnimation();
                childAt.startAnimation(animationSet);
            }
        }
        this.A.c();
        if (!z) {
            h(false);
        }
    }

    public void f() {
        this.Z = false;
        this.b0 = null;
        this.c0 = 0;
    }

    public final void g() {
        animation.Animation$AnimationListenerC0024c animation_animation_animationlistenerc0024c = new animation.Animation$AnimationListenerC0024c();
        int firstVisiblePosition = this.A.getFirstVisiblePosition();
        boolean z = false;
        for (int i2 = 0; i2 < this.A.getChildCount(); i2++) {
            View childAt = this.A.getChildAt(i2);
            r rVar = this.B;
            if (this.D.contains((t0.i) rVar.getItem(firstVisiblePosition + i2))) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(this.j0);
                alphaAnimation.setFillEnabled(true);
                alphaAnimation.setFillAfter(true);
                if (!z) {
                    alphaAnimation.setAnimationListener(animation_animation_animationlistenerc0024c);
                    z = true;
                }
                childAt.clearAnimation();
                childAt.startAnimation(alphaAnimation);
            }
        }
    }

    public void h(boolean z) {
        this.D = null;
        this.E = null;
        this.g0 = false;
        if (this.h0) {
            this.h0 = false;
            B(z);
        }
        this.A.setEnabled(true);
    }

    public int i(int i2, int i3) {
        if (i2 >= i3) {
            return (int) (((this.g * i3) / i2) + 0.5f);
        }
        return (int) (((this.g * 9.0f) / 16.0f) + 0.5f);
    }

    public final int k(boolean z) {
        if (!z && this.y.getVisibility() != 0) {
            return 0;
        }
        int paddingTop = 0 + this.w.getPaddingTop() + this.w.getPaddingBottom();
        if (z) {
            paddingTop += this.x.getMeasuredHeight();
        }
        if (this.y.getVisibility() == 0) {
            paddingTop += this.y.getMeasuredHeight();
        }
        if (z && this.y.getVisibility() == 0) {
            return paddingTop + this.z.getMeasuredHeight();
        }
        return paddingTop;
    }

    public final boolean m() {
        Bitmap b2;
        Bitmap b3;
        Uri c;
        MediaDescriptionCompat mediaDescriptionCompat = this.V;
        Uri uri = null;
        if (mediaDescriptionCompat == null) {
            b2 = null;
        } else {
            b2 = mediaDescriptionCompat.b();
        }
        MediaDescriptionCompat mediaDescriptionCompat2 = this.V;
        if (mediaDescriptionCompat2 != null) {
            uri = mediaDescriptionCompat2.c();
        }
        n nVar = this.W;
        if (nVar == null) {
            b3 = this.X;
        } else {
            b3 = nVar.b();
        }
        n nVar2 = this.W;
        if (nVar2 == null) {
            c = this.Y;
        } else {
            c = nVar2.c();
        }
        if (b3 != b2) {
            return true;
        }
        if (b3 == null && !H(c, uri)) {
            return true;
        }
        return false;
    }

    public boolean n() {
        if ((this.S.b() & 514) != 0) {
            return true;
        }
        return false;
    }

    public boolean o() {
        if ((this.S.b() & 516) != 0) {
            return true;
        }
        return false;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f = true;
        this.a.b(s0.c, this.b, 2);
        v(this.a.j());
    }

    @Override // androidx.appcompat.app.c, androidx.appcompat.app.j, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(17170445);
        setContentView(R$layout.mr_controller_material_dialog_b);
        findViewById(16908315).setVisibility(8);
        m mVar = new m();
        FrameLayout frameLayout = (FrameLayout) findViewById(R$id.mr_expandable_area);
        this.n = frameLayout;
        frameLayout.setOnClickListener(new e());
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.mr_dialog_area);
        this.o = linearLayout;
        linearLayout.setOnClickListener(new f());
        int d2 = androidx.mediarouter.app.i.d(this.d);
        Button button = (Button) findViewById(16908314);
        this.i = button;
        button.setText(R$string.mr_controller_disconnect);
        this.i.setTextColor(d2);
        this.i.setOnClickListener(mVar);
        Button button2 = (Button) findViewById(16908313);
        this.j = button2;
        button2.setText(R$string.mr_controller_stop_casting);
        this.j.setTextColor(d2);
        this.j.setOnClickListener(mVar);
        this.u = (TextView) findViewById(R$id.mr_name);
        ImageButton imageButton = (ImageButton) findViewById(R$id.mr_close);
        this.l = imageButton;
        imageButton.setOnClickListener(mVar);
        this.q = (FrameLayout) findViewById(R$id.mr_custom_control);
        this.p = (FrameLayout) findViewById(R$id.mr_default_control);
        g gVar = new g();
        ImageView imageView = (ImageView) findViewById(R$id.mr_art);
        this.r = imageView;
        imageView.setOnClickListener(gVar);
        findViewById(R$id.mr_control_title_container).setOnClickListener(gVar);
        this.w = (LinearLayout) findViewById(R$id.mr_media_main_control);
        this.z = findViewById(R$id.mr_control_divider);
        this.x = (RelativeLayout) findViewById(R$id.mr_playback_control);
        this.f23s = (TextView) findViewById(R$id.mr_control_title);
        this.t = (TextView) findViewById(R$id.mr_control_subtitle);
        ImageButton imageButton2 = (ImageButton) findViewById(R$id.mr_control_playback_ctrl);
        this.k = imageButton2;
        imageButton2.setOnClickListener(mVar);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R$id.mr_volume_control);
        this.y = linearLayout2;
        linearLayout2.setVisibility(8);
        SeekBar seekBar = (SeekBar) findViewById(R$id.mr_volume_slider);
        this.G = seekBar;
        seekBar.setTag(this.c);
        q qVar = new q();
        this.H = qVar;
        this.G.setOnSeekBarChangeListener(qVar);
        this.A = (OverlayListView) findViewById(R$id.mr_volume_group_list);
        this.C = new ArrayList();
        r rVar = new r(this.A.getContext(), this.C);
        this.B = rVar;
        this.A.setAdapter((ListAdapter) rVar);
        this.F = new HashSet();
        androidx.mediarouter.app.i.u(this.d, this.w, this.A, this.c.y());
        androidx.mediarouter.app.i.w(this.d, (MediaRouteVolumeSlider) this.G, this.w);
        HashMap hashMap = new HashMap();
        this.N = hashMap;
        hashMap.put(this.c, this.G);
        MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = (MediaRouteExpandCollapseButton) findViewById(R$id.mr_group_expand_collapse);
        this.m = mediaRouteExpandCollapseButton;
        mediaRouteExpandCollapseButton.setOnClickListener(new h());
        r();
        this.i0 = this.d.getResources().getInteger(R$integer.mr_controller_volume_group_list_animation_duration_ms);
        this.j0 = this.d.getResources().getInteger(R$integer.mr_controller_volume_group_list_fade_in_duration_ms);
        this.k0 = this.d.getResources().getInteger(R$integer.mr_controller_volume_group_list_fade_out_duration_ms);
        View s2 = s(bundle);
        this.h = s2;
        if (s2 != null) {
            this.q.addView(s2);
            this.q.setVisibility(0);
        }
        this.e = true;
        A();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.a.q(this.b);
        v(null);
        this.f = false;
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.app.c, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        int i3;
        if (i2 != 25 && i2 != 24) {
            return super.onKeyDown(i2, keyEvent);
        }
        t0.i iVar = this.c;
        if (i2 == 25) {
            i3 = -1;
        } else {
            i3 = 1;
        }
        iVar.H(i3);
        return true;
    }

    @Override // androidx.appcompat.app.c, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 25 && i2 != 24) {
            return super.onKeyUp(i2, keyEvent);
        }
        return true;
    }

    public boolean p() {
        if ((this.S.b() & 1) != 0) {
            return true;
        }
        return false;
    }

    public boolean q(t0.i iVar) {
        if (this.v && iVar.t() == 1) {
            return true;
        }
        return false;
    }

    public void r() {
        Interpolator interpolator;
        if (Build.VERSION.SDK_INT >= 21) {
            if (this.f0) {
                interpolator = this.m0;
            } else {
                interpolator = this.n0;
            }
            this.l0 = interpolator;
            return;
        }
        this.l0 = this.o0;
    }

    public View s(Bundle bundle) {
        return null;
    }

    public final void t(boolean z) {
        HashMap hashMap;
        HashMap hashMap2;
        List l2 = this.c.l();
        if (l2.isEmpty()) {
            this.C.clear();
            this.B.notifyDataSetChanged();
        } else if (androidx.mediarouter.app.f.i(this.C, l2)) {
            this.B.notifyDataSetChanged();
        } else {
            if (z) {
                hashMap = androidx.mediarouter.app.f.e(this.A, this.B);
            } else {
                hashMap = null;
            }
            if (z) {
                hashMap2 = androidx.mediarouter.app.f.d(this.d, this.A, this.B);
            } else {
                hashMap2 = null;
            }
            this.D = androidx.mediarouter.app.f.f(this.C, l2);
            this.E = androidx.mediarouter.app.f.g(this.C, l2);
            this.C.addAll(0, this.D);
            this.C.removeAll(this.E);
            this.B.notifyDataSetChanged();
            if (z && this.f0 && this.D.size() + this.E.size() > 0) {
                a(hashMap, hashMap2);
                return;
            }
            this.D = null;
            this.E = null;
        }
    }

    public final void v(MediaSessionCompat.Token token) {
        MediaControllerCompat mediaControllerCompat = this.O;
        MediaDescriptionCompat mediaDescriptionCompat = null;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.g(this.Q);
            this.O = null;
        }
        if (token == null || !this.f) {
            return;
        }
        MediaControllerCompat mediaControllerCompat2 = new MediaControllerCompat(this.d, token);
        this.O = mediaControllerCompat2;
        mediaControllerCompat2.e(this.Q);
        MediaMetadataCompat a2 = this.O.a();
        if (a2 != null) {
            mediaDescriptionCompat = a2.e();
        }
        this.V = mediaDescriptionCompat;
        this.S = this.O.b();
        z();
        y(false);
    }

    public void w() {
        e(true);
        this.A.requestLayout();
        this.A.getViewTreeObserver().addOnGlobalLayoutListener(new b());
    }

    public void x() {
        Set set = this.D;
        if (set != null && set.size() != 0) {
            g();
        } else {
            h(true);
        }
    }

    public void y(boolean z) {
        if (this.I != null) {
            this.d0 = true;
            this.e0 = z | this.e0;
            return;
        }
        int i2 = 0;
        this.d0 = false;
        this.e0 = false;
        if (this.c.C() && !this.c.w()) {
            if (!this.e) {
                return;
            }
            this.u.setText(this.c.m());
            Button button = this.i;
            if (!this.c.a()) {
                i2 = 8;
            }
            button.setVisibility(i2);
            if (this.h == null && this.Z) {
                if (l(this.b0)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Can't set artwork image with recycled bitmap: ");
                    sb.append(this.b0);
                } else {
                    this.r.setImageBitmap(this.b0);
                    this.r.setBackgroundColor(this.c0);
                }
                f();
            }
            F();
            E();
            B(z);
            return;
        }
        dismiss();
    }

    public void z() {
        if (this.h == null && m()) {
            n nVar = this.W;
            if (nVar != null) {
                nVar.cancel(true);
            }
            n nVar2 = new n();
            this.W = nVar2;
            nVar2.execute(new Void[0]);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public c(android.content.Context r2, int r3) {
        /*
            r1 = this;
            r0 = 1
            android.content.Context r2 = androidx.mediarouter.app.i.b(r2, r3, r0)
            int r3 = androidx.mediarouter.app.i.c(r2)
            r1.<init>(r2, r3)
            r1.v = r0
            androidx.mediarouter.app.c$d r3 = new androidx.mediarouter.app.c$d
            r3.<init>()
            r1.q0 = r3
            android.content.Context r3 = r1.getContext()
            r1.d = r3
            androidx.mediarouter.app.c$o r3 = new androidx.mediarouter.app.c$o
            r3.<init>()
            r1.Q = r3
            android.content.Context r3 = r1.d
            h0.t0 r3 = h0.t0.i(r3)
            r1.a = r3
            androidx.mediarouter.app.c$p r0 = new androidx.mediarouter.app.c$p
            r0.<init>()
            r1.b = r0
            h0.t0$i r0 = r3.m()
            r1.c = r0
            android.support.v4.media.session.MediaSessionCompat$Token r3 = r3.j()
            r1.v(r3)
            android.content.Context r3 = r1.d
            android.content.res.Resources r3 = r3.getResources()
            int r0 = androidx.mediarouter.R$dimen.mr_controller_volume_group_list_padding_top
            int r3 = r3.getDimensionPixelSize(r0)
            r1.M = r3
            android.content.Context r3 = r1.d
            java.lang.String r0 = "accessibility"
            java.lang.Object r3 = r3.getSystemService(r0)
            android.view.accessibility.AccessibilityManager r3 = (android.view.accessibility.AccessibilityManager) r3
            r1.p0 = r3
            int r3 = android.os.Build.VERSION.SDK_INT
            r0 = 21
            if (r3 < r0) goto L6e
            int r3 = androidx.mediarouter.R$interpolator.mr_linear_out_slow_in
            android.view.animation.Interpolator r3 = android.view.animation.AnimationUtils.loadInterpolator(r2, r3)
            r1.m0 = r3
            int r3 = androidx.mediarouter.R$interpolator.mr_fast_out_slow_in
            android.view.animation.Interpolator r2 = android.view.animation.AnimationUtils.loadInterpolator(r2, r3)
            r1.n0 = r2
        L6e:
            android.view.animation.AccelerateDecelerateInterpolator r2 = new android.view.animation.AccelerateDecelerateInterpolator
            r2.<init>()
            r1.o0 = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.c.<init>(android.content.Context, int):void");
    }
}
