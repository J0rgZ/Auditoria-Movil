package androidx.mediarouter.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.mediarouter.R$bool;
import androidx.mediarouter.R$dimen;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class f {
    public static int a(Context context) {
        if (!context.getResources().getBoolean(R$bool.is_tablet)) {
            return -1;
        }
        return -2;
    }

    public static int b(Context context) {
        boolean z;
        int i;
        float fraction;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            z = true;
        } else {
            z = false;
        }
        TypedValue typedValue = new TypedValue();
        Resources resources = context.getResources();
        if (z) {
            i = R$dimen.mr_dialog_fixed_width_minor;
        } else {
            i = R$dimen.mr_dialog_fixed_width_major;
        }
        resources.getValue(i, typedValue, true);
        int i2 = typedValue.type;
        if (i2 == 5) {
            fraction = typedValue.getDimension(displayMetrics);
        } else if (i2 == 6) {
            int i3 = displayMetrics.widthPixels;
            fraction = typedValue.getFraction(i3, i3);
        } else {
            return -2;
        }
        return (int) fraction;
    }

    public static int c(Context context) {
        if (!context.getResources().getBoolean(R$bool.is_tablet)) {
            return -1;
        }
        return b(context);
    }

    public static HashMap d(Context context, ListView listView, ArrayAdapter arrayAdapter) {
        HashMap hashMap = new HashMap();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        for (int i = 0; i < listView.getChildCount(); i++) {
            hashMap.put(arrayAdapter.getItem(firstVisiblePosition + i), h(context, listView.getChildAt(i)));
        }
        return hashMap;
    }

    public static HashMap e(ListView listView, ArrayAdapter arrayAdapter) {
        HashMap hashMap = new HashMap();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        for (int i = 0; i < listView.getChildCount(); i++) {
            Object item = arrayAdapter.getItem(firstVisiblePosition + i);
            View childAt = listView.getChildAt(i);
            hashMap.put(item, new Rect(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom()));
        }
        return hashMap;
    }

    public static Set f(List list, List list2) {
        HashSet hashSet = new HashSet(list2);
        hashSet.removeAll(list);
        return hashSet;
    }

    public static Set g(List list, List list2) {
        HashSet hashSet = new HashSet(list);
        hashSet.removeAll(list2);
        return hashSet;
    }

    public static BitmapDrawable h(Context context, View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return new BitmapDrawable(context.getResources(), createBitmap);
    }

    public static boolean i(List list, List list2) {
        return new HashSet(list).equals(new HashSet(list2));
    }
}
