package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.j;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.R$string;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import h0.s0;
import h0.t0;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* loaded from: classes.dex */
public class g extends j {
    public final t0 a;
    public final c b;
    public Context c;
    public s0 d;
    public List e;
    public ImageButton f;
    public d g;
    public RecyclerView h;
    public boolean i;
    public t0.i j;
    public long k;
    public long l;
    public final Handler m;

    /* loaded from: classes.dex */
    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                g.this.f((List) message.obj);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public final class c extends t0.b {
        public c() {
        }

        public void onRouteAdded(t0 t0Var, t0.i iVar) {
            g.this.c();
        }

        public void onRouteChanged(t0 t0Var, t0.i iVar) {
            g.this.c();
        }

        public void onRouteRemoved(t0 t0Var, t0.i iVar) {
            g.this.c();
        }

        public void onRouteSelected(t0 t0Var, t0.i iVar) {
            g.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public final class d extends RecyclerView.g {
        public final ArrayList a = new ArrayList();
        public final LayoutInflater b;
        public final Drawable c;
        public final Drawable d;
        public final Drawable e;
        public final Drawable f;

        /* loaded from: classes.dex */
        public class a extends RecyclerView.d0 {
            public TextView a;

            public a(View view) {
                super(view);
                this.a = (TextView) view.findViewById(R$id.mr_picker_header_name);
            }

            public void b(b bVar) {
                this.a.setText(bVar.a().toString());
            }
        }

        /* loaded from: classes.dex */
        public class b {
            public final Object a;
            public final int b;

            public b(Object obj) {
                this.a = obj;
                if (obj instanceof String) {
                    this.b = 1;
                } else if (obj instanceof t0.i) {
                    this.b = 2;
                } else {
                    this.b = 0;
                }
            }

            public Object a() {
                return this.a;
            }

            public int b() {
                return this.b;
            }
        }

        /* loaded from: classes.dex */
        public class c extends RecyclerView.d0 {
            public final View a;
            public final ImageView b;
            public final ProgressBar c;
            public final TextView d;

            /* loaded from: classes.dex */
            public class a implements View.OnClickListener {
                public final /* synthetic */ t0.i a;

                public a(t0.i iVar) {
                    this.a = iVar;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    g gVar = g.this;
                    t0.i iVar = this.a;
                    gVar.j = iVar;
                    iVar.I();
                    c.this.b.setVisibility(4);
                    c.this.c.setVisibility(0);
                }
            }

            public c(View view) {
                super(view);
                this.a = view;
                this.b = (ImageView) view.findViewById(R$id.mr_picker_route_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R$id.mr_picker_route_progress_bar);
                this.c = progressBar;
                this.d = (TextView) view.findViewById(R$id.mr_picker_route_name);
                i.t(g.this.c, progressBar);
            }

            public void b(b bVar) {
                t0.i iVar = (t0.i) bVar.a();
                this.a.setVisibility(0);
                this.c.setVisibility(4);
                this.a.setOnClickListener(new a(iVar));
                this.d.setText(iVar.m());
                this.b.setImageDrawable(d.this.b(iVar));
            }
        }

        public d() {
            this.b = LayoutInflater.from(g.this.c);
            this.c = i.g(g.this.c);
            this.d = i.q(g.this.c);
            this.e = i.m(g.this.c);
            this.f = i.n(g.this.c);
            d();
        }

        public final Drawable a(t0.i iVar) {
            int f = iVar.f();
            if (f != 1) {
                if (f != 2) {
                    if (iVar.y()) {
                        return this.f;
                    }
                    return this.c;
                }
                return this.e;
            }
            return this.d;
        }

        public Drawable b(t0.i iVar) {
            Uri j = iVar.j();
            if (j != null) {
                try {
                    Drawable createFromStream = Drawable.createFromStream(g.this.c.getContentResolver().openInputStream(j), null);
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

        public b c(int i) {
            return (b) this.a.get(i);
        }

        public void d() {
            this.a.clear();
            this.a.add(new b(g.this.c.getString(R$string.mr_chooser_title)));
            for (t0.i iVar : g.this.e) {
                this.a.add(new b(iVar));
            }
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            return this.a.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemViewType(int i) {
            return ((b) this.a.get(i)).b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void onBindViewHolder(RecyclerView.d0 d0Var, int i) {
            int itemViewType = getItemViewType(i);
            b c2 = c(i);
            if (itemViewType != 1) {
                if (itemViewType == 2) {
                    ((c) d0Var).b(c2);
                    return;
                }
                return;
            }
            ((a) d0Var).b(c2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public RecyclerView.d0 onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i != 1) {
                if (i != 2) {
                    return null;
                }
                return new c(this.b.inflate(R$layout.mr_picker_route_item, viewGroup, false));
            }
            return new a(this.b.inflate(R$layout.mr_picker_header_item, viewGroup, false));
        }
    }

    /* loaded from: classes.dex */
    public static final class e implements Comparator {
        public static final e a = new e();

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(t0.i iVar, t0.i iVar2) {
            return iVar.m().compareToIgnoreCase(iVar2.m());
        }
    }

    public g(Context context) {
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
        if (this.j == null && this.i) {
            ArrayList arrayList = new ArrayList(this.a.l());
            b(arrayList);
            Collections.sort(arrayList, e.a);
            if (SystemClock.uptimeMillis() - this.l >= this.k) {
                f(arrayList);
                return;
            }
            this.m.removeMessages(1);
            Handler handler = this.m;
            handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.l + this.k);
        }
    }

    public void d(s0 s0Var) {
        if (s0Var != null) {
            if (!this.d.equals(s0Var)) {
                this.d = s0Var;
                if (this.i) {
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
        getWindow().setLayout(f.c(this.c), f.a(this.c));
    }

    public void f(List list) {
        this.l = SystemClock.uptimeMillis();
        this.e.clear();
        this.e.addAll(list);
        this.g.d();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.i = true;
        this.a.b(this.d, this.b, 1);
        c();
    }

    @Override // androidx.appcompat.app.j, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.mr_picker_dialog);
        i.s(this.c, this);
        this.e = new ArrayList();
        ImageButton imageButton = (ImageButton) findViewById(R$id.mr_picker_close_button);
        this.f = imageButton;
        imageButton.setOnClickListener(new b());
        this.g = new d();
        RecyclerView recyclerView = (RecyclerView) findViewById(R$id.mr_picker_list);
        this.h = recyclerView;
        recyclerView.setAdapter(this.g);
        this.h.setLayoutManager(new LinearLayoutManager(this.c));
        e();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.i = false;
        this.a.q(this.b);
        this.m.removeMessages(1);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public g(android.content.Context r2, int r3) {
        /*
            r1 = this;
            r0 = 0
            android.content.Context r2 = androidx.mediarouter.app.i.b(r2, r3, r0)
            int r3 = androidx.mediarouter.app.i.c(r2)
            r1.<init>(r2, r3)
            h0.s0 r2 = h0.s0.c
            r1.d = r2
            androidx.mediarouter.app.g$a r2 = new androidx.mediarouter.app.g$a
            r2.<init>()
            r1.m = r2
            android.content.Context r2 = r1.getContext()
            h0.t0 r3 = h0.t0.i(r2)
            r1.a = r3
            androidx.mediarouter.app.g$c r3 = new androidx.mediarouter.app.g$c
            r3.<init>()
            r1.b = r3
            r1.c = r2
            android.content.res.Resources r2 = r2.getResources()
            int r3 = androidx.mediarouter.R$integer.mr_update_routes_delay_ms
            int r2 = r2.getInteger(r3)
            long r2 = (long) r2
            r1.k = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.g.<init>(android.content.Context, int):void");
    }
}
