package androidx.appcompat.app;

import android.app.Dialog;
/* loaded from: classes.dex */
public abstract class k extends androidx.fragment.app.c {
    @Override // androidx.fragment.app.c
    public void setupDialog(Dialog dialog, int i) {
        if (dialog instanceof j) {
            j jVar = (j) dialog;
            if (i != 1 && i != 2) {
                if (i == 3) {
                    dialog.getWindow().addFlags(24);
                } else {
                    return;
                }
            }
            jVar.supportRequestWindowFeature(1);
            return;
        }
        super.setupDialog(dialog, i);
    }
}
