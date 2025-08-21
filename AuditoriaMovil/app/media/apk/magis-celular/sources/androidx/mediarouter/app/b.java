package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import h0.s0;
/* loaded from: classes.dex */
public class b extends androidx.fragment.app.c {
    public boolean a = false;
    public Dialog b;
    public s0 c;

    public b() {
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

    public s0 F2() {
        E2();
        return this.c;
    }

    public a G2(Context context, Bundle bundle) {
        return new a(context);
    }

    public g H2(Context context) {
        return new g(context);
    }

    public void I2(s0 s0Var) {
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
                if (dialog != null) {
                    if (this.a) {
                        ((g) dialog).d(s0Var);
                        return;
                    } else {
                        ((a) dialog).d(s0Var);
                        return;
                    }
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("selector must not be null");
    }

    public void J2(boolean z) {
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
        if (dialog == null) {
            return;
        }
        if (this.a) {
            ((g) dialog).e();
        } else {
            ((a) dialog).e();
        }
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.a) {
            g H2 = H2(getContext());
            this.b = H2;
            H2.d(F2());
        } else {
            a G2 = G2(getContext(), bundle);
            this.b = G2;
            G2.d(F2());
        }
        return this.b;
    }
}
