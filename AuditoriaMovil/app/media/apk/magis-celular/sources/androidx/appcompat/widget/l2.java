package androidx.appcompat.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public class l2 extends y.c implements View.OnClickListener {
    public final SearchManager l;
    public final SearchView m;
    public final SearchableInfo n;
    public final Context o;
    public final WeakHashMap p;
    public final int q;
    public boolean r;

    /* renamed from: s  reason: collision with root package name */
    public int f15s;
    public ColorStateList t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;

    /* loaded from: classes.dex */
    public static final class a {
        public final TextView a;
        public final TextView b;
        public final ImageView c;
        public final ImageView d;
        public final ImageView e;

        public a(View view) {
            this.a = (TextView) view.findViewById(16908308);
            this.b = (TextView) view.findViewById(16908309);
            this.c = (ImageView) view.findViewById(16908295);
            this.d = (ImageView) view.findViewById(16908296);
            this.e = (ImageView) view.findViewById(R$id.edit_query);
        }
    }

    public l2(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), (Cursor) null, true);
        this.r = false;
        this.f15s = 1;
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = -1;
        this.l = (SearchManager) ((y.a) this).d.getSystemService("search");
        this.m = searchView;
        this.n = searchableInfo;
        this.q = searchView.getSuggestionCommitIconResId();
        this.o = context;
        this.p = weakHashMap;
    }

    public static String o(Cursor cursor, String str) {
        return w(cursor, cursor.getColumnIndex(str));
    }

    public static String w(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            return null;
        }
    }

    public final void A(String str, Drawable drawable) {
        if (drawable != null) {
            this.p.put(str, drawable.getConstantState());
        }
    }

    public final void B(Cursor cursor) {
        Bundle bundle;
        if (cursor != null) {
            bundle = cursor.getExtras();
        } else {
            bundle = null;
        }
        if (bundle != null) {
            bundle.getBoolean("in_progress");
        }
    }

    public void a(Cursor cursor) {
        if (this.r) {
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super/*y.a*/.a(cursor);
            if (cursor != null) {
                this.u = cursor.getColumnIndex("suggest_text_1");
                this.v = cursor.getColumnIndex("suggest_text_2");
                this.w = cursor.getColumnIndex("suggest_text_2_url");
                this.x = cursor.getColumnIndex("suggest_icon_1");
                this.y = cursor.getColumnIndex("suggest_icon_2");
                this.z = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e);
        }
    }

    public CharSequence c(Cursor cursor) {
        String o;
        String o2;
        if (cursor == null) {
            return null;
        }
        String o3 = o(cursor, "suggest_intent_query");
        if (o3 != null) {
            return o3;
        }
        if (this.n.shouldRewriteQueryFromData() && (o2 = o(cursor, "suggest_intent_data")) != null) {
            return o2;
        }
        if (!this.n.shouldRewriteQueryFromText() || (o = o(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return o;
    }

    public Cursor d(CharSequence charSequence) {
        String charSequence2;
        if (charSequence == null) {
            charSequence2 = "";
        } else {
            charSequence2 = charSequence.toString();
        }
        if (this.m.getVisibility() == 0 && this.m.getWindowVisibility() == 0) {
            try {
                Cursor v = v(this.n, charSequence2, 50);
                if (v != null) {
                    v.getCount();
                    return v;
                }
            } catch (RuntimeException unused) {
            }
        }
        return null;
    }

    public void e(View view, Context context, Cursor cursor) {
        int i;
        CharSequence w;
        a aVar = (a) view.getTag();
        int i2 = this.z;
        if (i2 != -1) {
            i = cursor.getInt(i2);
        } else {
            i = 0;
        }
        if (aVar.a != null) {
            z(aVar.a, w(cursor, this.u));
        }
        if (aVar.b != null) {
            String w2 = w(cursor, this.w);
            if (w2 != null) {
                w = l(w2);
            } else {
                w = w(cursor, this.v);
            }
            if (TextUtils.isEmpty(w)) {
                TextView textView = aVar.a;
                if (textView != null) {
                    textView.setSingleLine(false);
                    aVar.a.setMaxLines(2);
                }
            } else {
                TextView textView2 = aVar.a;
                if (textView2 != null) {
                    textView2.setSingleLine(true);
                    aVar.a.setMaxLines(1);
                }
            }
            z(aVar.b, w);
        }
        ImageView imageView = aVar.c;
        if (imageView != null) {
            y(imageView, t(cursor), 4);
        }
        ImageView imageView2 = aVar.d;
        if (imageView2 != null) {
            y(imageView2, u(cursor), 8);
        }
        int i3 = this.f15s;
        if (i3 != 2 && (i3 != 1 || (i & 1) == 0)) {
            aVar.e.setVisibility(8);
            return;
        }
        aVar.e.setVisibility(0);
        aVar.e.setTag(aVar.a.getText());
        aVar.e.setOnClickListener(this);
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        try {
            return super/*y.a*/.getDropDownView(i, view, viewGroup);
        } catch (RuntimeException e) {
            View g = g(((y.a) this).d, ((y.a) this).c, viewGroup);
            if (g != null) {
                ((a) g.getTag()).a.setText(e.toString());
            }
            return g;
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super/*y.a*/.getView(i, view, viewGroup);
        } catch (RuntimeException e) {
            View h = h(((y.a) this).d, ((y.a) this).c, viewGroup);
            if (h != null) {
                ((a) h.getTag()).a.setText(e.toString());
            }
            return h;
        }
    }

    public View h(Context context, Cursor cursor, ViewGroup viewGroup) {
        View h = super.h(context, cursor, viewGroup);
        h.setTag(new a(h));
        ((ImageView) h.findViewById(R$id.edit_query)).setImageResource(this.q);
        return h;
    }

    public boolean hasStableIds() {
        return false;
    }

    public final Drawable k(String str) {
        Drawable.ConstantState constantState = (Drawable.ConstantState) this.p.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    public final CharSequence l(CharSequence charSequence) {
        if (this.t == null) {
            TypedValue typedValue = new TypedValue();
            ((y.a) this).d.getTheme().resolveAttribute(R$attr.textColorSearchUrl, typedValue, true);
            this.t = ((y.a) this).d.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.t, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    public final Drawable m(ComponentName componentName) {
        PackageManager packageManager = ((y.a) this).d.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid icon resource ");
                sb.append(iconResource);
                sb.append(" for ");
                sb.append(componentName.flattenToShortString());
                return null;
            }
            return drawable;
        } catch (PackageManager.NameNotFoundException e) {
            e.toString();
            return null;
        }
    }

    public final Drawable n(ComponentName componentName) {
        String flattenToShortString = componentName.flattenToShortString();
        Drawable.ConstantState constantState = null;
        if (this.p.containsKey(flattenToShortString)) {
            Drawable.ConstantState constantState2 = (Drawable.ConstantState) this.p.get(flattenToShortString);
            if (constantState2 == null) {
                return null;
            }
            return constantState2.newDrawable(this.o.getResources());
        }
        Drawable m = m(componentName);
        if (m != null) {
            constantState = m.getConstantState();
        }
        this.p.put(flattenToShortString, constantState);
        return m;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void notifyDataSetChanged() {
        super/*android.widget.BaseAdapter*/.notifyDataSetChanged();
        B(b());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void notifyDataSetInvalidated() {
        super/*android.widget.BaseAdapter*/.notifyDataSetInvalidated();
        B(b());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.m.z((CharSequence) tag);
        }
    }

    public final Drawable p(Cursor cursor) {
        Drawable n = n(this.n.getSearchActivity());
        if (n != null) {
            return n;
        }
        return ((y.a) this).d.getPackageManager().getDefaultActivityIcon();
    }

    public final Drawable q(Uri uri) {
        try {
            if ("android.resource".equals(uri.getScheme())) {
                try {
                    return r(uri);
                } catch (Resources.NotFoundException unused) {
                    throw new FileNotFoundException("Resource does not exist: " + uri);
                }
            }
            InputStream openInputStream = this.o.getContentResolver().openInputStream(uri);
            if (openInputStream != null) {
                Drawable createFromStream = Drawable.createFromStream(openInputStream, null);
                try {
                    openInputStream.close();
                } catch (IOException e) {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e);
                }
                return createFromStream;
            }
            throw new FileNotFoundException("Failed to open " + uri);
        } catch (FileNotFoundException e2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Icon not found: ");
            sb.append(uri);
            sb.append(", ");
            sb.append(e2.getMessage());
            return null;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Icon not found: ");
        sb2.append(uri);
        sb2.append(", ");
        sb2.append(e2.getMessage());
        return null;
    }

    public Drawable r(Uri uri) {
        int parseInt;
        String authority = uri.getAuthority();
        if (!TextUtils.isEmpty(authority)) {
            try {
                Resources resourcesForApplication = ((y.a) this).d.getPackageManager().getResourcesForApplication(authority);
                List<String> pathSegments = uri.getPathSegments();
                if (pathSegments != null) {
                    int size = pathSegments.size();
                    if (size == 1) {
                        try {
                            parseInt = Integer.parseInt(pathSegments.get(0));
                        } catch (NumberFormatException unused) {
                            throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                        }
                    } else if (size == 2) {
                        parseInt = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
                    } else {
                        throw new FileNotFoundException("More than two path segments: " + uri);
                    }
                    if (parseInt != 0) {
                        return resourcesForApplication.getDrawable(parseInt);
                    }
                    throw new FileNotFoundException("No resource found for: " + uri);
                }
                throw new FileNotFoundException("No path: " + uri);
            } catch (PackageManager.NameNotFoundException unused2) {
                throw new FileNotFoundException("No package found for authority: " + uri);
            }
        }
        throw new FileNotFoundException("No authority: " + uri);
    }

    public final Drawable s(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(str);
            String str2 = "android.resource://" + this.o.getPackageName() + "/" + parseInt;
            Drawable k = k(str2);
            if (k != null) {
                return k;
            }
            Drawable drawable = j.a.getDrawable(this.o, parseInt);
            A(str2, drawable);
            return drawable;
        } catch (Resources.NotFoundException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Icon resource not found: ");
            sb.append(str);
            return null;
        } catch (NumberFormatException unused2) {
            Drawable k2 = k(str);
            if (k2 != null) {
                return k2;
            }
            Drawable q = q(Uri.parse(str));
            A(str, q);
            return q;
        }
    }

    public final Drawable t(Cursor cursor) {
        int i = this.x;
        if (i == -1) {
            return null;
        }
        Drawable s2 = s(cursor.getString(i));
        if (s2 != null) {
            return s2;
        }
        return p(cursor);
    }

    public final Drawable u(Cursor cursor) {
        int i = this.y;
        if (i == -1) {
            return null;
        }
        return s(cursor.getString(i));
    }

    public Cursor v(SearchableInfo searchableInfo, String str, int i) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder fragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        if (i > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i));
        }
        return ((y.a) this).d.getContentResolver().query(fragment.build(), null, suggestSelection, strArr2, null);
    }

    public void x(int i) {
        this.f15s = i;
    }

    public final void y(ImageView imageView, Drawable drawable, int i) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    public final void z(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }
}
