package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Parcelable;
/* loaded from: classes.dex */
public interface m {

    /* loaded from: classes.dex */
    public interface a {
        boolean a(g gVar);

        void onCloseMenu(g gVar, boolean z);
    }

    boolean collapseItemActionView(g gVar, i iVar);

    boolean expandItemActionView(g gVar, i iVar);

    boolean flagActionItems();

    int getId();

    void initForMenu(Context context, g gVar);

    void onCloseMenu(g gVar, boolean z);

    void onRestoreInstanceState(Parcelable parcelable);

    Parcelable onSaveInstanceState();

    boolean onSubMenuSelected(r rVar);

    void setCallback(a aVar);

    void updateMenuView(boolean z);
}
