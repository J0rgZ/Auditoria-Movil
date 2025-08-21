package androidx.mediarouter.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.j;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$layout;
import h0.s0;
import h0.t0;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* loaded from: classes.dex */
public class a extends j {
    public final t0 a;
    public final b b;
    public TextView c;
    public s0 d;
    public ArrayList e;
    public c f;
    public ListView g;
    public boolean h;
    public long i;
    public final Handler j;

    /* renamed from: androidx.mediarouter.app.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class HandlerC0023a extends Handler {
        public HandlerC0023a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                a.this.f((List) message.obj);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class b extends t0.b {
        public b() {
        }

        public void onRouteAdded(t0 t0Var, t0.i iVar) {
            a.this.c();
        }

        public void onRouteChanged(t0 t0Var, t0.i iVar) {
            a.this.c();
        }

        public void onRouteRemoved(t0 t0Var, t0.i iVar) {
            a.this.c();
        }

        public void onRouteSelected(t0 t0Var, t0.i iVar) {
            a.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public static final class c extends ArrayAdapter implements AdapterView.OnItemClickListener {
        public final LayoutInflater a;
        public final Drawable b;
        public final Drawable c;
        public final Drawable d;
        public final Drawable e;

        public c(Context context, List list) {
            super(context, 0, list);
            this.a = LayoutInflater.from(context);
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{R$attr.mediaRouteDefaultIconDrawable, R$attr.mediaRouteTvIconDrawable, R$attr.mediaRouteSpeakerIconDrawable, R$attr.mediaRouteSpeakerGroupIconDrawable});
            this.b = obtainStyledAttributes.getDrawable(0);
            this.c = obtainStyledAttributes.getDrawable(1);
            this.d = obtainStyledAttributes.getDrawable(2);
            this.e = obtainStyledAttributes.getDrawable(3);
            obtainStyledAttributes.recycle();
        }

        public final Drawable a(t0.i iVar) {
            int f = iVar.f();
            if (f != 1) {
                if (f != 2) {
                    if (iVar.y()) {
                        return this.e;
                    }
                    return this.b;
                }
                return this.d;
            }
            return this.c;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        public final Drawable b(t0.i iVar) {
            Uri j = iVar.j();
            if (j != null) {
                try {
                    Drawable createFromStream = Drawable.createFromStream(getContext().getContentResolver().openInputStream(j), null);
                    if (createFromStream != null) {
                        return createFromStream;
                    }
                } catch (IOException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to load ");
                    sb.append(j);
                }
            }
            return a(iVar);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.a.inflate(R$layout.mr_chooser_list_item, viewGroup, false);
            }
            t0.i iVar = (t0.i) getItem(i);
            TextView textView = (TextView) view.findViewById(R$id.mr_chooser_route_name);
            TextView textView2 = (TextView) view.findViewById(R$id.mr_chooser_route_desc);
            textView.setText(iVar.m());
            String d = iVar.d();
            boolean z = true;
            if (iVar.c() != 2 && iVar.c() != 1) {
                z = false;
            }
            if (z && !TextUtils.isEmpty(d)) {
                textView.setGravity(80);
                textView2.setVisibility(0);
                textView2.setText(d);
            } else {
                textView.setGravity(16);
                textView2.setVisibility(8);
                textView2.setText("");
            }
            view.setEnabled(iVar.x());
            ImageView imageView = (ImageView) view.findViewById(R$id.mr_chooser_route_icon);
            if (imageView != null) {
                imageView.setImageDrawable(b(iVar));
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            return ((t0.i) getItem(i)).x();
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i, long j) {
            t0.i iVar = (t0.i) getItem(i);
            if (iVar.x()) {
                ((ImageView) view.findViewById(R$id.mr_chooser_route_icon)).setVisibility(8);
                ((ProgressBar) view.findViewById(R$id.mr_chooser_route_progress_bar)).setVisibility(0);
                iVar.I();
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class d implements Comparator {
        public static final d a = new d();

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(t0.i iVar, t0.i iVar2) {
            return iVar.m().compareToIgnoreCase(iVar2.m());
        }
    }

    public a(Context context) {
        this(context, 0);
    }

    public boolean a(t0.i iVar) {
        if (!iVar.w() && iVar.x() && iVar.E(this.d)) {
            return true;
        }
        return false;
    }

    public void b(List list) {
        int size = list.size();
        while (true) {
            int i = size - 1;
            if (size > 0) {
                if (!a((t0.i) list.get(i))) {
                    list.remove(i);
                }
                size = i;
            } else {
                return;
            }
        }
    }

    public void c() {
        if (this.h) {
            ArrayList arrayList = new ArrayList(this.a.l());
            b(arrayList);
            Collections.sort(arrayList, d.a);
            if (SystemClock.uptimeMillis() - this.i >= 300) {
                f(arrayList);
                return;
            }
            this.j.removeMessages(1);
            Handler handler = this.j;
            handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.i + 300);
        }
    }

    public void d(s0 s0Var) {
        if (s0Var != null) {
            if (!this.d.equals(s0Var)) {
                this.d = s0Var;
                if (this.h) {
                    this.a.q(this.b);
                    this.a.b(s0Var, this.b, 1);
                }
                c();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("selector must not be null");
    }

    public void e() {
        getWindow().setLayout(f.b(getContext()), -2);
    }

    public void f(List list) {
        this.i = SystemClock.uptimeMillis();
        this.e.clear();
        this.e.addAll(list);
        this.f.notifyDataSetChanged();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.h = true;
        this.a.b(this.d, this.b, 1);
        c();
    }

    @Override // androidx.appcompat.app.j, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.mr_chooser_dialog);
        this.e = new ArrayList();
        this.f = new c(getContext(), this.e);
        ListView listView = (ListView) findViewById(R$id.mr_chooser_list);
        this.g = listView;
        listView.setAdapter((ListAdapter) this.f);
        this.g.setOnItemClickListener(this.f);
        this.g.setEmptyView(findViewById(16908292));
        this.c = (TextView) findViewById(R$id.mr_chooser_title);
        e();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.h = false;
        this.a.q(this.b);
        this.j.removeMessages(1);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.app.j, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        this.c.setText(charSequence);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public a(android.content.Context r2, int r3) {
        /*
            r1 = this;
            r0 = 0
            android.content.Context r2 = androidx.mediarouter.app.i.b(r2, r3, r0)
            int r3 = androidx.mediarouter.app.i.c(r2)
            r1.<init>(r2, r3)
            h0.s0 r2 = h0.s0.c
            r1.d = r2
            androidx.mediarouter.app.a$a r2 = new androidx.mediarouter.app.a$a
            r2.<init>()
            r1.j = r2
            android.content.Context r2 = r1.getContext()
            h0.t0 r2 = h0.t0.i(r2)
            r1.a = r2
            androidx.mediarouter.app.a$b r2 = new androidx.mediarouter.app.a$b
            r2.<init>()
            r1.b = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.a.<init>(android.content.Context, int):void");
    }

    @Override // androidx.appcompat.app.j, android.app.Dialog
    public void setTitle(int i) {
        this.c.setText(i);
    }
}
