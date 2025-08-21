package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public class SearchView extends u1 implements e.c {
    public static final k d0 = new k();
    public boolean A;
    public CharSequence B;
    public boolean C;
    public boolean D;
    public int E;
    public boolean F;
    public CharSequence G;
    public CharSequence H;
    public boolean I;
    public int J;
    public SearchableInfo K;
    public Bundle L;
    public final Runnable M;
    public Runnable N;
    public final WeakHashMap O;
    public final View.OnClickListener Q;
    public View.OnKeyListener S;
    public final TextView.OnEditorActionListener V;
    public final AdapterView.OnItemClickListener W;
    public final SearchAutoComplete a;
    public final View b;
    public final AdapterView.OnItemSelectedListener b0;
    public final View c;
    public TextWatcher c0;
    public final View d;
    public final ImageView e;
    public final ImageView f;
    public final ImageView g;
    public final ImageView h;
    public final View i;
    public p j;
    public Rect k;
    public Rect l;
    public int[] m;
    public int[] n;
    public final ImageView o;
    public final Drawable p;
    public final int q;
    public final int r;

    /* renamed from: s  reason: collision with root package name */
    public final Intent f13s;
    public final Intent t;
    public final CharSequence u;
    public View.OnFocusChangeListener v;
    public View.OnClickListener w;
    public boolean x;
    public boolean y;
    public y.a z;

    /* loaded from: classes.dex */
    public static class SearchAutoComplete extends androidx.appcompat.widget.e {
        public int d;
        public SearchView e;
        public boolean f;
        public final Runnable g;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SearchAutoComplete.this.b();
            }
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i >= 960 && i2 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i < 600) {
                if (i < 640 || i2 < 480) {
                    return 160;
                }
                return 192;
            }
            return 192;
        }

        public boolean a() {
            if (TextUtils.getTrimmedLength(getText()) == 0) {
                return true;
            }
            return false;
        }

        public void b() {
            if (this.f) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.f = false;
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            if (this.d > 0 && !super.enoughToFilter()) {
                return false;
            }
            return true;
        }

        @Override // androidx.appcompat.widget.e, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f) {
                removeCallbacks(this.g);
                post(this.g);
            }
            return onCreateInputConnection;
        }

        @Override // android.view.View
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.e.E();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.e.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.e.hasFocus() && getVisibility() == 0) {
                this.f = true;
                if (SearchView.r(getContext())) {
                    SearchView.d0.c(this, true);
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public void replaceText(CharSequence charSequence) {
        }

        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.f = false;
                removeCallbacks(this.g);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this)) {
                this.f = false;
                removeCallbacks(this.g);
                inputMethodManager.showSoftInput(this, 0);
            } else {
                this.f = true;
            }
        }

        public void setSearchView(SearchView searchView) {
            this.e = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.d = i;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.g = new a();
            this.d = getThreshold();
        }
    }

    /* loaded from: classes.dex */
    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            SearchView.this.D(charSequence);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchView.this.K();
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            y.a aVar = SearchView.this.z;
            if (aVar instanceof l2) {
                aVar.a((Cursor) null);
            }
        }
    }

    /* loaded from: classes.dex */
    public class d implements View.OnFocusChangeListener {
        public d() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            SearchView searchView = SearchView.this;
            View.OnFocusChangeListener onFocusChangeListener = searchView.v;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(searchView, z);
            }
        }
    }

    /* loaded from: classes.dex */
    public class e implements View.OnLayoutChangeListener {
        public e() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            SearchView.this.g();
        }
    }

    /* loaded from: classes.dex */
    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchView searchView = SearchView.this;
            if (view == searchView.e) {
                searchView.A();
            } else if (view == searchView.g) {
                searchView.w();
            } else if (view == searchView.f) {
                searchView.B();
            } else if (view == searchView.h) {
                searchView.F();
            } else if (view == searchView.a) {
                searchView.m();
            }
        }
    }

    /* loaded from: classes.dex */
    public class g implements View.OnKeyListener {
        public g() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            SearchView searchView = SearchView.this;
            if (searchView.K == null) {
                return false;
            }
            if (searchView.a.isPopupShowing() && SearchView.this.a.getListSelection() != -1) {
                return SearchView.this.C(view, i, keyEvent);
            }
            if (SearchView.this.a.a() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i != 66) {
                return false;
            }
            view.cancelLongPress();
            SearchView searchView2 = SearchView.this;
            searchView2.u(0, null, searchView2.a.getText().toString());
            return true;
        }
    }

    /* loaded from: classes.dex */
    public class h implements TextView.OnEditorActionListener {
        public h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            SearchView.this.B();
            return true;
        }
    }

    /* loaded from: classes.dex */
    public class i implements AdapterView.OnItemClickListener {
        public i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i, long j) {
            SearchView.this.x(i, 0, null);
        }
    }

    /* loaded from: classes.dex */
    public class j implements AdapterView.OnItemSelectedListener {
        public j() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            SearchView.this.y(i);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    /* loaded from: classes.dex */
    public static class k {
        public Method a;
        public Method b;
        public Method c;

        public k() {
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.b = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.c = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        public void a(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        public void b(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        public void c(AutoCompleteTextView autoCompleteTextView, boolean z) {
            Method method = this.c;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, Boolean.valueOf(z));
                } catch (Exception unused) {
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public interface l {
    }

    /* loaded from: classes.dex */
    public interface m {
    }

    /* loaded from: classes.dex */
    public interface n {
    }

    /* loaded from: classes.dex */
    public static class o extends z.a {
        public static final Parcelable.Creator<o> CREATOR = new a();
        public boolean a;

        /* loaded from: classes.dex */
        public static class a implements Parcelable.ClassLoaderCreator {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public o createFromParcel(Parcel parcel) {
                return new o(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b */
            public o createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new o(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c */
            public o[] newArray(int i) {
                return new o[i];
            }
        }

        public o(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.a + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.a));
        }

        public o(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    /* loaded from: classes.dex */
    public static class p extends TouchDelegate {
        public final View a;
        public final Rect b;
        public final Rect c;
        public final Rect d;
        public final int e;
        public boolean f;

        public p(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.b = new Rect();
            this.d = new Rect();
            this.c = new Rect();
            a(rect, rect2);
            this.a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.b.set(rect);
            this.d.set(rect);
            Rect rect3 = this.d;
            int i = this.e;
            rect3.inset(-i, -i);
            this.c.set(rect2);
        }

        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z;
            boolean z2;
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z3 = true;
            if (action != 0) {
                if (action != 1 && action != 2) {
                    if (action == 3) {
                        z2 = this.f;
                        this.f = false;
                    }
                    z = true;
                    z3 = false;
                } else {
                    z2 = this.f;
                    if (z2 && !this.d.contains(x, y)) {
                        z3 = z2;
                        z = false;
                    }
                }
                z3 = z2;
                z = true;
            } else {
                if (this.b.contains(x, y)) {
                    this.f = true;
                    z = true;
                }
                z = true;
                z3 = false;
            }
            if (!z3) {
                return false;
            }
            if (z && !this.c.contains(x, y)) {
                motionEvent.setLocation(this.a.getWidth() / 2, this.a.getHeight() / 2);
            } else {
                Rect rect = this.c;
                motionEvent.setLocation(x - rect.left, y - rect.top);
            }
            return this.a.dispatchTouchEvent(motionEvent);
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_width);
    }

    public static boolean r(Context context) {
        if (context.getResources().getConfiguration().orientation == 2) {
            return true;
        }
        return false;
    }

    private void setQuery(CharSequence charSequence) {
        int length;
        this.a.setText(charSequence);
        SearchAutoComplete searchAutoComplete = this.a;
        if (TextUtils.isEmpty(charSequence)) {
            length = 0;
        } else {
            length = charSequence.length();
        }
        searchAutoComplete.setSelection(length);
    }

    public void A() {
        P(false);
        this.a.requestFocus();
        this.a.setImeVisibility(true);
        View.OnClickListener onClickListener = this.w;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void B() {
        Editable text = this.a.getText();
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            if (this.K != null) {
                u(0, null, text.toString());
            }
            this.a.setImeVisibility(false);
            l();
        }
    }

    public boolean C(View view, int i2, KeyEvent keyEvent) {
        int length;
        if (this.K != null && this.z != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i2 != 66 && i2 != 84 && i2 != 61) {
                if (i2 != 21 && i2 != 22) {
                    if (i2 == 19) {
                        this.a.getListSelection();
                        return false;
                    }
                } else {
                    if (i2 == 21) {
                        length = 0;
                    } else {
                        length = this.a.length();
                    }
                    this.a.setSelection(length);
                    this.a.setListSelection(0);
                    this.a.clearListSelection();
                    d0.c(this.a, true);
                    return true;
                }
            } else {
                return x(this.a.getListSelection(), 0, null);
            }
        }
        return false;
    }

    public void D(CharSequence charSequence) {
        Editable text = this.a.getText();
        this.H = text;
        boolean z = !TextUtils.isEmpty(text);
        O(z);
        Q(!z);
        J();
        N();
        this.G = charSequence.toString();
    }

    public void E() {
        P(q());
        G();
        if (this.a.hasFocus()) {
            m();
        }
    }

    public void F() {
        SearchableInfo searchableInfo = this.K;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                getContext().startActivity(k(this.f13s, searchableInfo));
            } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                getContext().startActivity(j(this.t, searchableInfo));
            }
        } catch (ActivityNotFoundException unused) {
        }
    }

    public final void G() {
        post(this.M);
    }

    public final void H(int i2) {
        Editable text = this.a.getText();
        Cursor b2 = this.z.b();
        if (b2 == null) {
            return;
        }
        if (b2.moveToPosition(i2)) {
            CharSequence c2 = this.z.c(b2);
            if (c2 != null) {
                setQuery(c2);
                return;
            } else {
                setQuery(text);
                return;
            }
        }
        setQuery(text);
    }

    public void I(CharSequence charSequence, boolean z) {
        this.a.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.a;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.H = charSequence;
        }
        if (z && !TextUtils.isEmpty(charSequence)) {
            B();
        }
    }

    public final void J() {
        int[] iArr;
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.a.getText());
        int i2 = 0;
        if (!z2 && (!this.x || this.I)) {
            z = false;
        }
        ImageView imageView = this.g;
        if (!z) {
            i2 = 8;
        }
        imageView.setVisibility(i2);
        Drawable drawable = this.g.getDrawable();
        if (drawable != null) {
            if (z2) {
                iArr = ViewGroup.ENABLED_STATE_SET;
            } else {
                iArr = ViewGroup.EMPTY_STATE_SET;
            }
            drawable.setState(iArr);
        }
    }

    public void K() {
        int[] iArr;
        if (this.a.hasFocus()) {
            iArr = ViewGroup.FOCUSED_STATE_SET;
        } else {
            iArr = ViewGroup.EMPTY_STATE_SET;
        }
        Drawable background = this.c.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.d.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public final void L() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.a;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(o(queryHint));
    }

    public final void M() {
        this.a.setThreshold(this.K.getSuggestThreshold());
        this.a.setImeOptions(this.K.getImeOptions());
        int inputType = this.K.getInputType();
        int i2 = 1;
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.K.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.a.setInputType(inputType);
        y.a aVar = this.z;
        if (aVar != null) {
            aVar.a((Cursor) null);
        }
        if (this.K.getSuggestAuthority() != null) {
            l2 l2Var = new l2(getContext(), this, this.K, this.O);
            this.z = l2Var;
            this.a.setAdapter(l2Var);
            l2 l2Var2 = this.z;
            if (this.C) {
                i2 = 2;
            }
            l2Var2.x(i2);
        }
    }

    public final void N() {
        int i2;
        if (s() && (this.f.getVisibility() == 0 || this.h.getVisibility() == 0)) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        this.d.setVisibility(i2);
    }

    public final void O(boolean z) {
        int i2;
        if (this.A && s() && hasFocus() && (z || !this.F)) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        this.f.setVisibility(i2);
    }

    public final void P(boolean z) {
        int i2;
        int i3;
        this.y = z;
        int i4 = 0;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        boolean z2 = !TextUtils.isEmpty(this.a.getText());
        this.e.setVisibility(i2);
        O(z2);
        View view = this.b;
        if (z) {
            i3 = 8;
        } else {
            i3 = 0;
        }
        view.setVisibility(i3);
        this.o.setVisibility((this.o.getDrawable() == null || this.x) ? 8 : 8);
        J();
        Q(!z2);
        N();
    }

    public final void Q(boolean z) {
        int i2 = 8;
        if (this.F && !q() && z) {
            this.f.setVisibility(8);
            i2 = 0;
        }
        this.h.setVisibility(i2);
    }

    public void b() {
        if (this.I) {
            return;
        }
        this.I = true;
        int imeOptions = this.a.getImeOptions();
        this.J = imeOptions;
        this.a.setImeOptions(imeOptions | 33554432);
        this.a.setText("");
        setIconified(false);
    }

    public void c() {
        I("", false);
        clearFocus();
        P(true);
        this.a.setImeOptions(this.J);
        this.I = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.D = true;
        super.clearFocus();
        this.a.clearFocus();
        this.a.setImeVisibility(false);
        this.D = false;
    }

    public void g() {
        int i2;
        int i3;
        if (this.i.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.c.getPaddingLeft();
            Rect rect = new Rect();
            boolean b2 = y2.b(this);
            if (this.x) {
                i2 = resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_text_padding_left);
            } else {
                i2 = 0;
            }
            this.a.getDropDownBackground().getPadding(rect);
            if (b2) {
                i3 = -rect.left;
            } else {
                i3 = paddingLeft - (rect.left + i2);
            }
            this.a.setDropDownHorizontalOffset(i3);
            this.a.setDropDownWidth((((this.i.getWidth() + rect.left) + rect.right) + i2) - paddingLeft);
        }
    }

    public int getImeOptions() {
        return this.a.getImeOptions();
    }

    public int getInputType() {
        return this.a.getInputType();
    }

    public int getMaxWidth() {
        return this.E;
    }

    public CharSequence getQuery() {
        return this.a.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.B;
        if (charSequence == null) {
            SearchableInfo searchableInfo = this.K;
            if (searchableInfo != null && searchableInfo.getHintId() != 0) {
                return getContext().getText(this.K.getHintId());
            }
            return this.u;
        }
        return charSequence;
    }

    public int getSuggestionCommitIconResId() {
        return this.r;
    }

    public int getSuggestionRowLayout() {
        return this.q;
    }

    public y.a getSuggestionsAdapter() {
        return this.z;
    }

    public final Intent h(String str, Uri uri, String str2, String str3, int i2, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.H);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.L;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i2 != 0) {
            intent.putExtra("action_key", i2);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.K.getSearchActivity());
        return intent;
    }

    public final Intent i(Cursor cursor, int i2, String str) {
        int i3;
        Uri parse;
        String o2;
        try {
            try {
                String o3 = l2.o(cursor, "suggest_intent_action");
                if (o3 == null) {
                    o3 = this.K.getSuggestIntentAction();
                }
                if (o3 == null) {
                    o3 = "android.intent.action.SEARCH";
                }
                String str2 = o3;
                String o4 = l2.o(cursor, "suggest_intent_data");
                if (o4 == null) {
                    o4 = this.K.getSuggestIntentData();
                }
                if (o4 != null && (o2 = l2.o(cursor, "suggest_intent_data_id")) != null) {
                    o4 = o4 + "/" + Uri.encode(o2);
                }
                if (o4 == null) {
                    parse = null;
                } else {
                    parse = Uri.parse(o4);
                }
                return h(str2, parse, l2.o(cursor, "suggest_intent_extra_data"), l2.o(cursor, "suggest_intent_query"), i2, str);
            } catch (RuntimeException unused) {
                i3 = -1;
                StringBuilder sb = new StringBuilder();
                sb.append("Search suggestions cursor at row ");
                sb.append(i3);
                sb.append(" returned exception.");
                return null;
            }
        } catch (RuntimeException unused2) {
            i3 = cursor.getPosition();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Search suggestions cursor at row ");
            sb2.append(i3);
            sb2.append(" returned exception.");
            return null;
        }
    }

    public final Intent j(Intent intent, SearchableInfo searchableInfo) {
        String str;
        String str2;
        String str3;
        int i2;
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.L;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        if (searchableInfo.getVoiceLanguageModeId() != 0) {
            str = resources.getString(searchableInfo.getVoiceLanguageModeId());
        } else {
            str = "free_form";
        }
        String str4 = null;
        if (searchableInfo.getVoicePromptTextId() != 0) {
            str2 = resources.getString(searchableInfo.getVoicePromptTextId());
        } else {
            str2 = null;
        }
        if (searchableInfo.getVoiceLanguageId() != 0) {
            str3 = resources.getString(searchableInfo.getVoiceLanguageId());
        } else {
            str3 = null;
        }
        if (searchableInfo.getVoiceMaxResults() != 0) {
            i2 = searchableInfo.getVoiceMaxResults();
        } else {
            i2 = 1;
        }
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", str);
        intent3.putExtra("android.speech.extra.PROMPT", str2);
        intent3.putExtra("android.speech.extra.LANGUAGE", str3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", i2);
        if (searchActivity != null) {
            str4 = searchActivity.flattenToShortString();
        }
        intent3.putExtra("calling_package", str4);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    public final Intent k(Intent intent, SearchableInfo searchableInfo) {
        String flattenToShortString;
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        if (searchActivity == null) {
            flattenToShortString = null;
        } else {
            flattenToShortString = searchActivity.flattenToShortString();
        }
        intent2.putExtra("calling_package", flattenToShortString);
        return intent2;
    }

    public final void l() {
        this.a.dismissDropDown();
    }

    public void m() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.a.refreshAutoCompleteResults();
            return;
        }
        k kVar = d0;
        kVar.b(this.a);
        kVar.a(this.a);
    }

    public final void n(View view, Rect rect) {
        view.getLocationInWindow(this.m);
        getLocationInWindow(this.n);
        int[] iArr = this.m;
        int i2 = iArr[1];
        int[] iArr2 = this.n;
        int i3 = i2 - iArr2[1];
        int i4 = iArr[0] - iArr2[0];
        rect.set(i4, i3, view.getWidth() + i4, view.getHeight() + i3);
    }

    public final CharSequence o(CharSequence charSequence) {
        if (this.x && this.p != null) {
            double textSize = this.a.getTextSize();
            Double.isNaN(textSize);
            int i2 = (int) (textSize * 1.25d);
            this.p.setBounds(0, 0, i2, i2);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
            spannableStringBuilder.setSpan(new ImageSpan(this.p), 1, 2, 33);
            spannableStringBuilder.append(charSequence);
            return spannableStringBuilder;
        }
        return charSequence;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.M);
        post(this.N);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.u1, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            n(this.a, this.k);
            Rect rect = this.l;
            Rect rect2 = this.k;
            rect.set(rect2.left, 0, rect2.right, i5 - i3);
            p pVar = this.j;
            if (pVar == null) {
                p pVar2 = new p(this.l, this.k, this.a);
                this.j = pVar2;
                setTouchDelegate(pVar2);
                return;
            }
            pVar.a(this.l, this.k);
        }
    }

    @Override // androidx.appcompat.widget.u1, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4;
        if (q()) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode != Integer.MIN_VALUE) {
            if (mode != 0) {
                if (mode == 1073741824 && (i4 = this.E) > 0) {
                    size = Math.min(i4, size);
                }
            } else {
                size = this.E;
                if (size <= 0) {
                    size = getPreferredWidth();
                }
            }
        } else {
            int i5 = this.E;
            size = i5 > 0 ? Math.min(i5, size) : Math.min(getPreferredWidth(), size);
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode2 != Integer.MIN_VALUE) {
            if (mode2 == 0) {
                size2 = getPreferredHeight();
            }
        } else {
            size2 = Math.min(getPreferredHeight(), size2);
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof o)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        o oVar = (o) parcelable;
        super.onRestoreInstanceState(oVar.getSuperState());
        P(oVar.a);
        requestLayout();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [android.os.Parcelable, androidx.appcompat.widget.SearchView$o] */
    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        ?? oVar = new o(super.onSaveInstanceState());
        oVar.a = q();
        return oVar;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        G();
    }

    public final boolean p() {
        Intent intent;
        SearchableInfo searchableInfo = this.K;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        if (this.K.getVoiceSearchLaunchWebSearch()) {
            intent = this.f13s;
        } else if (this.K.getVoiceSearchLaunchRecognizer()) {
            intent = this.t;
        } else {
            intent = null;
        }
        if (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) {
            return false;
        }
        return true;
    }

    public boolean q() {
        return this.y;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i2, Rect rect) {
        if (this.D || !isFocusable()) {
            return false;
        }
        if (!q()) {
            boolean requestFocus = this.a.requestFocus(i2, rect);
            if (requestFocus) {
                P(false);
            }
            return requestFocus;
        }
        return super.requestFocus(i2, rect);
    }

    public final boolean s() {
        if ((this.A || this.F) && !q()) {
            return true;
        }
        return false;
    }

    public void setAppSearchData(Bundle bundle) {
        this.L = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            w();
        } else {
            A();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.x == z) {
            return;
        }
        this.x = z;
        P(z);
        L();
    }

    public void setImeOptions(int i2) {
        this.a.setImeOptions(i2);
    }

    public void setInputType(int i2) {
        this.a.setInputType(i2);
    }

    public void setMaxWidth(int i2) {
        this.E = i2;
        requestLayout();
    }

    public void setOnCloseListener(l lVar) {
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.v = onFocusChangeListener;
    }

    public void setOnQueryTextListener(m mVar) {
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.w = onClickListener;
    }

    public void setOnSuggestionListener(n nVar) {
    }

    public void setQueryHint(CharSequence charSequence) {
        this.B = charSequence;
        L();
    }

    public void setQueryRefinementEnabled(boolean z) {
        int i2;
        this.C = z;
        l2 l2Var = this.z;
        if (l2Var instanceof l2) {
            l2 l2Var2 = l2Var;
            if (z) {
                i2 = 2;
            } else {
                i2 = 1;
            }
            l2Var2.x(i2);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.K = searchableInfo;
        if (searchableInfo != null) {
            M();
            L();
        }
        boolean p2 = p();
        this.F = p2;
        if (p2) {
            this.a.setPrivateImeOptions("nm");
        }
        P(q());
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.A = z;
        P(q());
    }

    public void setSuggestionsAdapter(y.a aVar) {
        this.z = aVar;
        this.a.setAdapter(aVar);
    }

    public final void t(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException e2) {
            Log.e("SearchView", "Failed launch activity: " + intent, e2);
        }
    }

    public void u(int i2, String str, String str2) {
        getContext().startActivity(h("android.intent.action.SEARCH", null, null, str2, i2, str));
    }

    public final boolean v(int i2, int i3, String str) {
        Cursor b2 = this.z.b();
        if (b2 != null && b2.moveToPosition(i2)) {
            t(i(b2, i3, str));
            return true;
        }
        return false;
    }

    public void w() {
        if (TextUtils.isEmpty(this.a.getText())) {
            if (this.x) {
                clearFocus();
                P(true);
                return;
            }
            return;
        }
        this.a.setText("");
        this.a.requestFocus();
        this.a.setImeVisibility(true);
    }

    public boolean x(int i2, int i3, String str) {
        v(i2, 0, null);
        this.a.setImeVisibility(false);
        l();
        return true;
    }

    public boolean y(int i2) {
        H(i2);
        return true;
    }

    public void z(CharSequence charSequence) {
        setQuery(charSequence);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.k = new Rect();
        this.l = new Rect();
        this.m = new int[2];
        this.n = new int[2];
        this.M = new b();
        this.N = new c();
        this.O = new WeakHashMap();
        f fVar = new f();
        this.Q = fVar;
        this.S = new g();
        h hVar = new h();
        this.V = hVar;
        i iVar = new i();
        this.W = iVar;
        j jVar = new j();
        this.b0 = jVar;
        this.c0 = new a();
        r2 u = r2.u(context, attributeSet, R$styleable.G, i2, 0);
        LayoutInflater.from(context).inflate(u.n(R$styleable.SearchView_layout, R$layout.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R$id.search_src_text);
        this.a = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.b = findViewById(R$id.search_edit_frame);
        View findViewById = findViewById(R$id.search_plate);
        this.c = findViewById;
        View findViewById2 = findViewById(R$id.submit_area);
        this.d = findViewById2;
        ImageView imageView = (ImageView) findViewById(R$id.search_button);
        this.e = imageView;
        ImageView imageView2 = (ImageView) findViewById(R$id.search_go_btn);
        this.f = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R$id.search_close_btn);
        this.g = imageView3;
        ImageView imageView4 = (ImageView) findViewById(R$id.search_voice_btn);
        this.h = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R$id.search_mag_icon);
        this.o = imageView5;
        v.d1.o0(findViewById, u.g(R$styleable.SearchView_queryBackground));
        v.d1.o0(findViewById2, u.g(R$styleable.SearchView_submitBackground));
        int i3 = R$styleable.SearchView_searchIcon;
        imageView.setImageDrawable(u.g(i3));
        imageView2.setImageDrawable(u.g(R$styleable.SearchView_goIcon));
        imageView3.setImageDrawable(u.g(R$styleable.SearchView_closeIcon));
        imageView4.setImageDrawable(u.g(R$styleable.SearchView_voiceIcon));
        imageView5.setImageDrawable(u.g(i3));
        this.p = u.g(R$styleable.SearchView_searchHintIcon);
        u2.a(imageView, getResources().getString(R$string.abc_searchview_description_search));
        this.q = u.n(R$styleable.SearchView_suggestionRowLayout, R$layout.abc_search_dropdown_item_icons_2line);
        this.r = u.n(R$styleable.SearchView_commitIcon, 0);
        imageView.setOnClickListener(fVar);
        imageView3.setOnClickListener(fVar);
        imageView2.setOnClickListener(fVar);
        imageView4.setOnClickListener(fVar);
        searchAutoComplete.setOnClickListener(fVar);
        searchAutoComplete.addTextChangedListener(this.c0);
        searchAutoComplete.setOnEditorActionListener(hVar);
        searchAutoComplete.setOnItemClickListener(iVar);
        searchAutoComplete.setOnItemSelectedListener(jVar);
        searchAutoComplete.setOnKeyListener(this.S);
        searchAutoComplete.setOnFocusChangeListener(new d());
        setIconifiedByDefault(u.a(R$styleable.SearchView_iconifiedByDefault, true));
        int f2 = u.f(R$styleable.SearchView_android_maxWidth, -1);
        if (f2 != -1) {
            setMaxWidth(f2);
        }
        this.u = u.p(R$styleable.SearchView_defaultQueryHint);
        this.B = u.p(R$styleable.SearchView_queryHint);
        int k2 = u.k(R$styleable.SearchView_android_imeOptions, -1);
        if (k2 != -1) {
            setImeOptions(k2);
        }
        int k3 = u.k(R$styleable.SearchView_android_inputType, -1);
        if (k3 != -1) {
            setInputType(k3);
        }
        setFocusable(u.a(R$styleable.SearchView_android_focusable, true));
        u.v();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.f13s = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.t = intent2;
        intent2.addFlags(268435456);
        View findViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.i = findViewById3;
        if (findViewById3 != null) {
            findViewById3.addOnLayoutChangeListener(new e());
        }
        P(this.x);
        L();
    }
}
