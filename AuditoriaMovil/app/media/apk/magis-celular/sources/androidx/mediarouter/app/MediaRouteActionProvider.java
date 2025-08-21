package androidx.mediarouter.app;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import h0.s0;
import h0.t0;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class MediaRouteActionProvider extends v.b {
    public final t0 d;
    public final a e;
    public s0 f;
    public e g;
    public MediaRouteButton h;
    public boolean i;

    /* loaded from: classes.dex */
    public static final class a extends t0.b {
        public final WeakReference a;

        public a(MediaRouteActionProvider mediaRouteActionProvider) {
            this.a = new WeakReference(mediaRouteActionProvider);
        }

        public final void a(t0 t0Var) {
            MediaRouteActionProvider mediaRouteActionProvider = (MediaRouteActionProvider) this.a.get();
            if (mediaRouteActionProvider != null) {
                mediaRouteActionProvider.n();
            } else {
                t0Var.q(this);
            }
        }

        public void onProviderAdded(t0 t0Var, t0.h hVar) {
            a(t0Var);
        }

        public void onProviderChanged(t0 t0Var, t0.h hVar) {
            a(t0Var);
        }

        public void onProviderRemoved(t0 t0Var, t0.h hVar) {
            a(t0Var);
        }

        public void onRouteAdded(t0 t0Var, t0.i iVar) {
            a(t0Var);
        }

        public void onRouteChanged(t0 t0Var, t0.i iVar) {
            a(t0Var);
        }

        public void onRouteRemoved(t0 t0Var, t0.i iVar) {
            a(t0Var);
        }
    }

    public MediaRouteActionProvider(Context context) {
        super(context);
        this.f = s0.c;
        this.g = e.a();
        this.d = t0.i(context);
        this.e = new a(this);
    }

    public boolean c() {
        if (this.i || this.d.o(this.f, 1)) {
            return true;
        }
        return false;
    }

    public View d() {
        if (this.h != null) {
            Log.e("MRActionProvider", "onCreateActionView: this ActionProvider is already associated with a menu item. Don't reuse MediaRouteActionProvider instances! Abandoning the old menu item...");
        }
        MediaRouteButton m = m();
        this.h = m;
        m.setCheatSheetEnabled(true);
        this.h.setRouteSelector(this.f);
        this.h.setAlwaysVisible(this.i);
        this.h.setDialogFactory(this.g);
        this.h.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
        return this.h;
    }

    public boolean f() {
        MediaRouteButton mediaRouteButton = this.h;
        if (mediaRouteButton != null) {
            return mediaRouteButton.d();
        }
        return false;
    }

    public boolean h() {
        return true;
    }

    public MediaRouteButton m() {
        return new MediaRouteButton(a());
    }

    public void n() {
        i();
    }

    public void o(s0 s0Var) {
        if (s0Var != null) {
            if (!this.f.equals(s0Var)) {
                if (!this.f.f()) {
                    this.d.q(this.e);
                }
                if (!s0Var.f()) {
                    this.d.a(s0Var, this.e);
                }
                this.f = s0Var;
                n();
                MediaRouteButton mediaRouteButton = this.h;
                if (mediaRouteButton != null) {
                    mediaRouteButton.setRouteSelector(s0Var);
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("selector must not be null");
    }
}
