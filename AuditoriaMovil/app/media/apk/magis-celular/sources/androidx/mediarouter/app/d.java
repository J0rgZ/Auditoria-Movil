package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import h0.s0;
/* loaded from: classes.dex */
public class d extends androidx.fragment.app.c {
    public boolean a = false;
    public Dialog b;
    public s0 c;

    public d() {
        setCancelable(true);
    }

    public final void E2() {
        if (this.c == null) {
            Bundle arguments = getArguments();
            if (arguments != null) {
                this.c = s0.d(arguments.getBundle("selector"));
            }
            if (this.c == null) {
                this.c = s0.c;
            }
        }
    }

    public c F2(Context context, Bundle bundle) {
        return new c(context);
    }

    public h G2(Context context) {
        return new h(context);
    }

    public void H2(s0 s0Var) {
        if (s0Var != null) {
            E2();
            if (!this.c.equals(s0Var)) {
                this.c = s0Var;
                Bundle arguments = getArguments();
                if (arguments == null) {
                    arguments = new Bundle();
                }
                arguments.putBundle("selector", s0Var.a());
                setArguments(arguments);
                Dialog dialog = this.b;
                if (dialog != null && this.a) {
                    ((h) dialog).j(s0Var);
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("selector must not be null");
    }

    public void I2(boolean z) {
        if (this.b == null) {
            this.a = z;
            return;
        }
        throw new IllegalStateException("This must be called before creating dialog");
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Dialog dialog = this.b;
        if (dialog != null) {
            if (this.a) {
                ((h) dialog).l();
            } else {
                ((c) dialog).A();
            }
        }
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.a) {
            h G2 = G2(getContext());
            this.b = G2;
            G2.j(this.c);
        } else {
            this.b = F2(getContext(), bundle);
        }
        return this.b;
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Dialog dialog = this.b;
        if (dialog != null && !this.a) {
            ((c) dialog).e(false);
        }
    }
}
