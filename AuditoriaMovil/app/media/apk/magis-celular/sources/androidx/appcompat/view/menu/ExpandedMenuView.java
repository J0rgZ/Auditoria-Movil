package androidx.appcompat.view.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.widget.r2;
/* loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements g.b, n, AdapterView.OnItemClickListener {
    public static final int[] c = {16842964, 16843049};
    public g a;
    public int b;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.g.b
    public boolean a(i iVar) {
        return this.a.performItemAction(iVar, 0);
    }

    public int getWindowAnimations() {
        return this.b;
    }

    @Override // androidx.appcompat.view.menu.n
    public void initialize(g gVar) {
        this.a = gVar;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        a((i) getAdapter().getItem(i));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        r2 u = r2.u(context, attributeSet, c, i, 0);
        if (u.r(0)) {
            setBackgroundDrawable(u.g(0));
        }
        if (u.r(1)) {
            setDivider(u.g(1));
        }
        u.v();
    }
}
