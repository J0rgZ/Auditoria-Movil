package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.n;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class f extends BaseAdapter {
    public g a;
    public int b = -1;
    public boolean c;
    public final boolean d;
    public final LayoutInflater e;
    public final int f;

    public f(g gVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.d = z;
        this.e = layoutInflater;
        this.a = gVar;
        this.f = i;
        a();
    }

    public void a() {
        i expandedItem = this.a.getExpandedItem();
        if (expandedItem != null) {
            ArrayList<i> nonActionItems = this.a.getNonActionItems();
            int size = nonActionItems.size();
            for (int i = 0; i < size; i++) {
                if (nonActionItems.get(i) == expandedItem) {
                    this.b = i;
                    return;
                }
            }
        }
        this.b = -1;
    }

    public g b() {
        return this.a;
    }

    @Override // android.widget.Adapter
    /* renamed from: c */
    public i getItem(int i) {
        ArrayList<i> visibleItems;
        if (this.d) {
            visibleItems = this.a.getNonActionItems();
        } else {
            visibleItems = this.a.getVisibleItems();
        }
        int i2 = this.b;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return visibleItems.get(i);
    }

    public void d(boolean z) {
        this.c = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<i> visibleItems;
        if (this.d) {
            visibleItems = this.a.getNonActionItems();
        } else {
            visibleItems = this.a.getVisibleItems();
        }
        if (this.b < 0) {
            return visibleItems.size();
        }
        return visibleItems.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2;
        boolean z;
        if (view == null) {
            view = this.e.inflate(this.f, viewGroup, false);
        }
        int groupId = getItem(i).getGroupId();
        int i3 = i - 1;
        if (i3 >= 0) {
            i2 = getItem(i3).getGroupId();
        } else {
            i2 = groupId;
        }
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        if (this.a.isGroupDividerEnabled() && groupId != i2) {
            z = true;
        } else {
            z = false;
        }
        listMenuItemView.setGroupDividerEnabled(z);
        n.a aVar = (n.a) view;
        if (this.c) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.initialize(getItem(i), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
