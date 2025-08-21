package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import v.d1;
/* loaded from: classes.dex */
public abstract class n implements Cloneable {
    static final boolean DBG = false;
    private static final String LOG_TAG = "Transition";
    private static final int MATCH_FIRST = 1;
    public static final int MATCH_ID = 3;
    private static final String MATCH_ID_STR = "id";
    public static final int MATCH_INSTANCE = 1;
    private static final String MATCH_INSTANCE_STR = "instance";
    public static final int MATCH_ITEM_ID = 4;
    private static final String MATCH_ITEM_ID_STR = "itemId";
    private static final int MATCH_LAST = 4;
    public static final int MATCH_NAME = 2;
    private static final String MATCH_NAME_STR = "name";
    private ArrayList<u> mEndValuesList;
    private f mEpicenterCallback;
    private androidx.collection.a mNameOverrides;
    q mPropagation;
    private ArrayList<u> mStartValuesList;
    private static final int[] DEFAULT_MATCH_ORDER = {2, 1, 3, 4};
    private static final h STRAIGHT_PATH_MOTION = new a();
    private static ThreadLocal<androidx.collection.a> sRunningAnimators = new ThreadLocal<>();
    private String mName = getClass().getName();
    private long mStartDelay = -1;
    long mDuration = -1;
    private TimeInterpolator mInterpolator = null;
    ArrayList<Integer> mTargetIds = new ArrayList<>();
    ArrayList<View> mTargets = new ArrayList<>();
    private ArrayList<String> mTargetNames = null;
    private ArrayList<Class<?>> mTargetTypes = null;
    private ArrayList<Integer> mTargetIdExcludes = null;
    private ArrayList<View> mTargetExcludes = null;
    private ArrayList<Class<?>> mTargetTypeExcludes = null;
    private ArrayList<String> mTargetNameExcludes = null;
    private ArrayList<Integer> mTargetIdChildExcludes = null;
    private ArrayList<View> mTargetChildExcludes = null;
    private ArrayList<Class<?>> mTargetTypeChildExcludes = null;
    private v mStartValues = new v();
    private v mEndValues = new v();
    r mParent = null;
    private int[] mMatchOrder = DEFAULT_MATCH_ORDER;
    private ViewGroup mSceneRoot = null;
    boolean mCanRemoveViews = DBG;
    ArrayList<Animator> mCurrentAnimators = new ArrayList<>();
    private int mNumInstances = 0;
    private boolean mPaused = DBG;
    private boolean mEnded = DBG;
    private ArrayList<g> mListeners = null;
    private ArrayList<Animator> mAnimators = new ArrayList<>();
    private h mPathMotion = STRAIGHT_PATH_MOTION;

    /* loaded from: classes.dex */
    public static class a extends h {
        @Override // androidx.transition.h
        public Path a(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    }

    /* loaded from: classes.dex */
    public class b extends AnimatorListenerAdapter {
        public final /* synthetic */ androidx.collection.a a;

        public b(androidx.collection.a aVar) {
            this.a = aVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.remove(animator);
            n.this.mCurrentAnimators.remove(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            n.this.mCurrentAnimators.add(animator);
        }
    }

    /* loaded from: classes.dex */
    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            n.this.end();
            animator.removeListener(this);
        }
    }

    /* loaded from: classes.dex */
    public static class d {
        public View a;
        public String b;
        public u c;
        public r0 d;
        public n e;

        public d(View view, String str, n nVar, r0 r0Var, u uVar) {
            this.a = view;
            this.b = str;
            this.c = uVar;
            this.d = r0Var;
            this.e = nVar;
        }
    }

    /* loaded from: classes.dex */
    public static class e {
        public static ArrayList a(ArrayList arrayList, Object obj) {
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            if (!arrayList.contains(obj)) {
                arrayList.add(obj);
            }
            return arrayList;
        }

        public static ArrayList b(ArrayList arrayList, Object obj) {
            if (arrayList != null) {
                arrayList.remove(obj);
                if (arrayList.isEmpty()) {
                    return null;
                }
                return arrayList;
            }
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class f {
        public abstract Rect a(n nVar);
    }

    /* loaded from: classes.dex */
    public interface g {
        void a(n nVar);

        void b(n nVar);

        void c(n nVar);

        void d(n nVar);

        void e(n nVar);
    }

    public static void b(v vVar, View view, u uVar) {
        vVar.a.put(view, uVar);
        int id = view.getId();
        if (id >= 0) {
            if (vVar.b.indexOfKey(id) >= 0) {
                vVar.b.put(id, null);
            } else {
                vVar.b.put(id, view);
            }
        }
        String I = d1.I(view);
        if (I != null) {
            if (vVar.d.containsKey(I)) {
                vVar.d.put(I, null);
            } else {
                vVar.d.put(I, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (vVar.c.h(itemIdAtPosition) >= 0) {
                    View view2 = (View) vVar.c.f(itemIdAtPosition);
                    if (view2 != null) {
                        d1.u0(view2, (boolean) DBG);
                        vVar.c.j(itemIdAtPosition, null);
                        return;
                    }
                    return;
                }
                d1.u0(view, true);
                vVar.c.j(itemIdAtPosition, view);
            }
        }
    }

    public static boolean c(int[] iArr, int i) {
        int i2 = iArr[i];
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return true;
            }
        }
        return DBG;
    }

    public static ArrayList f(ArrayList arrayList, Object obj, boolean z) {
        if (obj != null) {
            if (z) {
                return e.a(arrayList, obj);
            }
            return e.b(arrayList, obj);
        }
        return arrayList;
    }

    public static androidx.collection.a i() {
        androidx.collection.a aVar = sRunningAnimators.get();
        if (aVar == null) {
            androidx.collection.a aVar2 = new androidx.collection.a();
            sRunningAnimators.set(aVar2);
            return aVar2;
        }
        return aVar;
    }

    public static boolean j(int i) {
        if (i < 1 || i > 4) {
            return DBG;
        }
        return true;
    }

    public static boolean k(u uVar, u uVar2, String str) {
        Object obj = uVar.a.get(str);
        Object obj2 = uVar2.a.get(str);
        if (obj == null && obj2 == null) {
            return DBG;
        }
        if (obj != null && obj2 != null) {
            return !obj.equals(obj2);
        }
        return true;
    }

    public final void a(androidx.collection.a aVar, androidx.collection.a aVar2) {
        for (int i = 0; i < aVar.size(); i++) {
            u uVar = (u) aVar.valueAt(i);
            if (isValidTarget(uVar.b)) {
                this.mStartValuesList.add(uVar);
                this.mEndValuesList.add(null);
            }
        }
        for (int i2 = 0; i2 < aVar2.size(); i2++) {
            u uVar2 = (u) aVar2.valueAt(i2);
            if (isValidTarget(uVar2.b)) {
                this.mEndValuesList.add(uVar2);
                this.mStartValuesList.add(null);
            }
        }
    }

    public n addListener(g gVar) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        this.mListeners.add(gVar);
        return this;
    }

    public n addTarget(View view) {
        this.mTargets.add(view);
        return this;
    }

    public void animate(Animator animator) {
        if (animator == null) {
            end();
            return;
        }
        if (getDuration() >= 0) {
            animator.setDuration(getDuration());
        }
        if (getStartDelay() >= 0) {
            animator.setStartDelay(getStartDelay() + animator.getStartDelay());
        }
        if (getInterpolator() != null) {
            animator.setInterpolator(getInterpolator());
        }
        animator.addListener(new c());
        animator.start();
    }

    public void cancel() {
        for (int size = this.mCurrentAnimators.size() - 1; size >= 0; size--) {
            this.mCurrentAnimators.get(size).cancel();
        }
        ArrayList<g> arrayList = this.mListeners;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
            int size2 = arrayList2.size();
            for (int i = 0; i < size2; i++) {
                ((g) arrayList2.get(i)).d(this);
            }
        }
    }

    public abstract void captureEndValues(u uVar);

    public void capturePropagationValues(u uVar) {
    }

    public abstract void captureStartValues(u uVar);

    public void captureValues(ViewGroup viewGroup, boolean z) {
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        androidx.collection.a aVar;
        clearValues(z);
        if ((this.mTargetIds.size() <= 0 && this.mTargets.size() <= 0) || (((arrayList = this.mTargetNames) != null && !arrayList.isEmpty()) || ((arrayList2 = this.mTargetTypes) != null && !arrayList2.isEmpty()))) {
            d(viewGroup, z);
        } else {
            for (int i = 0; i < this.mTargetIds.size(); i++) {
                View findViewById = viewGroup.findViewById(this.mTargetIds.get(i).intValue());
                if (findViewById != null) {
                    u uVar = new u(findViewById);
                    if (z) {
                        captureStartValues(uVar);
                    } else {
                        captureEndValues(uVar);
                    }
                    uVar.c.add(this);
                    capturePropagationValues(uVar);
                    if (z) {
                        b(this.mStartValues, findViewById, uVar);
                    } else {
                        b(this.mEndValues, findViewById, uVar);
                    }
                }
            }
            for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                View view = this.mTargets.get(i2);
                u uVar2 = new u(view);
                if (z) {
                    captureStartValues(uVar2);
                } else {
                    captureEndValues(uVar2);
                }
                uVar2.c.add(this);
                capturePropagationValues(uVar2);
                if (z) {
                    b(this.mStartValues, view, uVar2);
                } else {
                    b(this.mEndValues, view, uVar2);
                }
            }
        }
        if (!z && (aVar = this.mNameOverrides) != null) {
            int size = aVar.size();
            ArrayList arrayList3 = new ArrayList(size);
            for (int i3 = 0; i3 < size; i3++) {
                arrayList3.add(this.mStartValues.d.remove((String) this.mNameOverrides.keyAt(i3)));
            }
            for (int i4 = 0; i4 < size; i4++) {
                View view2 = (View) arrayList3.get(i4);
                if (view2 != null) {
                    this.mStartValues.d.put((String) this.mNameOverrides.valueAt(i4), view2);
                }
            }
        }
    }

    public void clearValues(boolean z) {
        if (z) {
            this.mStartValues.a.clear();
            this.mStartValues.b.clear();
            this.mStartValues.c.b();
            return;
        }
        this.mEndValues.a.clear();
        this.mEndValues.b.clear();
        this.mEndValues.c.b();
    }

    public Animator createAnimator(ViewGroup viewGroup, u uVar, u uVar2) {
        return null;
    }

    public void createAnimators(ViewGroup viewGroup, v vVar, v vVar2, ArrayList<u> arrayList, ArrayList<u> arrayList2) {
        boolean z;
        View view;
        Animator animator;
        u uVar;
        int i;
        Animator animator2;
        u uVar2;
        androidx.collection.a i2 = i();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        int i3 = 0;
        while (i3 < size) {
            u uVar3 = arrayList.get(i3);
            u uVar4 = arrayList2.get(i3);
            if (uVar3 != null && !uVar3.c.contains(this)) {
                uVar3 = null;
            }
            if (uVar4 != null && !uVar4.c.contains(this)) {
                uVar4 = null;
            }
            if (uVar3 != null || uVar4 != null) {
                if (uVar3 != null && uVar4 != null && !isTransitionRequired(uVar3, uVar4)) {
                    z = DBG;
                } else {
                    z = true;
                }
                if (z) {
                    Animator createAnimator = createAnimator(viewGroup, uVar3, uVar4);
                    if (createAnimator != null) {
                        if (uVar4 != null) {
                            View view2 = uVar4.b;
                            String[] transitionProperties = getTransitionProperties();
                            if (transitionProperties != null && transitionProperties.length > 0) {
                                uVar2 = new u(view2);
                                u uVar5 = (u) vVar2.a.get(view2);
                                if (uVar5 != null) {
                                    int i4 = 0;
                                    while (i4 < transitionProperties.length) {
                                        Map map = uVar2.a;
                                        Animator animator3 = createAnimator;
                                        String str = transitionProperties[i4];
                                        map.put(str, uVar5.a.get(str));
                                        i4++;
                                        createAnimator = animator3;
                                        transitionProperties = transitionProperties;
                                    }
                                }
                                Animator animator4 = createAnimator;
                                int size2 = i2.size();
                                int i5 = 0;
                                while (true) {
                                    if (i5 < size2) {
                                        d dVar = (d) i2.get((Animator) i2.keyAt(i5));
                                        if (dVar.c != null && dVar.a == view2 && dVar.b.equals(getName()) && dVar.c.equals(uVar2)) {
                                            animator2 = null;
                                            break;
                                        }
                                        i5++;
                                    } else {
                                        animator2 = animator4;
                                        break;
                                    }
                                }
                            } else {
                                animator2 = createAnimator;
                                uVar2 = null;
                            }
                            view = view2;
                            animator = animator2;
                            uVar = uVar2;
                        } else {
                            view = uVar3.b;
                            animator = createAnimator;
                            uVar = null;
                        }
                        if (animator != null) {
                            i = size;
                            i2.put(animator, new d(view, getName(), this, c0.d(viewGroup), uVar));
                            this.mAnimators.add(animator);
                            i3++;
                            size = i;
                        }
                        i = size;
                        i3++;
                        size = i;
                    }
                    i = size;
                    i3++;
                    size = i;
                }
            }
            i = size;
            i3++;
            size = i;
        }
        if (sparseIntArray.size() != 0) {
            for (int i6 = 0; i6 < sparseIntArray.size(); i6++) {
                Animator animator5 = this.mAnimators.get(sparseIntArray.keyAt(i6));
                animator5.setStartDelay((sparseIntArray.valueAt(i6) - Long.MAX_VALUE) + animator5.getStartDelay());
            }
        }
    }

    public final void d(View view, boolean z) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        ArrayList<Integer> arrayList = this.mTargetIdExcludes;
        if (arrayList != null && arrayList.contains(Integer.valueOf(id))) {
            return;
        }
        ArrayList<View> arrayList2 = this.mTargetExcludes;
        if (arrayList2 != null && arrayList2.contains(view)) {
            return;
        }
        ArrayList<Class<?>> arrayList3 = this.mTargetTypeExcludes;
        if (arrayList3 != null) {
            int size = arrayList3.size();
            for (int i = 0; i < size; i++) {
                if (this.mTargetTypeExcludes.get(i).isInstance(view)) {
                    return;
                }
            }
        }
        if (view.getParent() instanceof ViewGroup) {
            u uVar = new u(view);
            if (z) {
                captureStartValues(uVar);
            } else {
                captureEndValues(uVar);
            }
            uVar.c.add(this);
            capturePropagationValues(uVar);
            if (z) {
                b(this.mStartValues, view, uVar);
            } else {
                b(this.mEndValues, view, uVar);
            }
        }
        if (view instanceof ViewGroup) {
            ArrayList<Integer> arrayList4 = this.mTargetIdChildExcludes;
            if (arrayList4 != null && arrayList4.contains(Integer.valueOf(id))) {
                return;
            }
            ArrayList<View> arrayList5 = this.mTargetChildExcludes;
            if (arrayList5 != null && arrayList5.contains(view)) {
                return;
            }
            ArrayList<Class<?>> arrayList6 = this.mTargetTypeChildExcludes;
            if (arrayList6 != null) {
                int size2 = arrayList6.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    if (this.mTargetTypeChildExcludes.get(i2).isInstance(view)) {
                        return;
                    }
                }
            }
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                d(viewGroup.getChildAt(i3), z);
            }
        }
    }

    public final ArrayList e(ArrayList arrayList, int i, boolean z) {
        if (i > 0) {
            if (z) {
                return e.a(arrayList, Integer.valueOf(i));
            }
            return e.b(arrayList, Integer.valueOf(i));
        }
        return arrayList;
    }

    public void end() {
        int i = this.mNumInstances - 1;
        this.mNumInstances = i;
        if (i == 0) {
            ArrayList<g> arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((g) arrayList2.get(i2)).c(this);
                }
            }
            for (int i3 = 0; i3 < this.mStartValues.c.m(); i3++) {
                View view = (View) this.mStartValues.c.n(i3);
                if (view != null) {
                    d1.u0(view, (boolean) DBG);
                }
            }
            for (int i4 = 0; i4 < this.mEndValues.c.m(); i4++) {
                View view2 = (View) this.mEndValues.c.n(i4);
                if (view2 != null) {
                    d1.u0(view2, (boolean) DBG);
                }
            }
            this.mEnded = true;
        }
    }

    public n excludeChildren(View view, boolean z) {
        this.mTargetChildExcludes = h(this.mTargetChildExcludes, view, z);
        return this;
    }

    public n excludeTarget(View view, boolean z) {
        this.mTargetExcludes = h(this.mTargetExcludes, view, z);
        return this;
    }

    public void forceToEnd(ViewGroup viewGroup) {
        androidx.collection.a i = i();
        int size = i.size();
        if (viewGroup != null && size != 0) {
            r0 d2 = c0.d(viewGroup);
            androidx.collection.a aVar = new androidx.collection.a(i);
            i.clear();
            for (int i2 = size - 1; i2 >= 0; i2--) {
                d dVar = (d) aVar.valueAt(i2);
                if (dVar.a != null && d2 != null && d2.equals(dVar.d)) {
                    ((Animator) aVar.keyAt(i2)).end();
                }
            }
        }
    }

    public final ArrayList g(ArrayList arrayList, Class cls, boolean z) {
        if (cls != null) {
            if (z) {
                return e.a(arrayList, cls);
            }
            return e.b(arrayList, cls);
        }
        return arrayList;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public Rect getEpicenter() {
        f fVar = this.mEpicenterCallback;
        if (fVar == null) {
            return null;
        }
        return fVar.a(this);
    }

    public f getEpicenterCallback() {
        return this.mEpicenterCallback;
    }

    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    public u getMatchedTransitionValues(View view, boolean z) {
        ArrayList<u> arrayList;
        ArrayList<u> arrayList2;
        r rVar = this.mParent;
        if (rVar != null) {
            return rVar.getMatchedTransitionValues(view, z);
        }
        if (z) {
            arrayList = this.mStartValuesList;
        } else {
            arrayList = this.mEndValuesList;
        }
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i < size) {
                u uVar = arrayList.get(i);
                if (uVar == null) {
                    return null;
                }
                if (uVar.b == view) {
                    break;
                }
                i++;
            } else {
                i = -1;
                break;
            }
        }
        if (i < 0) {
            return null;
        }
        if (z) {
            arrayList2 = this.mEndValuesList;
        } else {
            arrayList2 = this.mStartValuesList;
        }
        return arrayList2.get(i);
    }

    public String getName() {
        return this.mName;
    }

    public h getPathMotion() {
        return this.mPathMotion;
    }

    public q getPropagation() {
        return null;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public List<Integer> getTargetIds() {
        return this.mTargetIds;
    }

    public List<String> getTargetNames() {
        return this.mTargetNames;
    }

    public List<Class<?>> getTargetTypes() {
        return this.mTargetTypes;
    }

    public List<View> getTargets() {
        return this.mTargets;
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public u getTransitionValues(View view, boolean z) {
        v vVar;
        r rVar = this.mParent;
        if (rVar != null) {
            return rVar.getTransitionValues(view, z);
        }
        if (z) {
            vVar = this.mStartValues;
        } else {
            vVar = this.mEndValues;
        }
        return (u) vVar.a.get(view);
    }

    public final ArrayList h(ArrayList arrayList, View view, boolean z) {
        if (view != null) {
            if (z) {
                return e.a(arrayList, view);
            }
            return e.b(arrayList, view);
        }
        return arrayList;
    }

    public boolean isTransitionRequired(u uVar, u uVar2) {
        if (uVar == null || uVar2 == null) {
            return DBG;
        }
        String[] transitionProperties = getTransitionProperties();
        if (transitionProperties != null) {
            for (String str : transitionProperties) {
                if (!k(uVar, uVar2, str)) {
                }
            }
            return DBG;
        }
        for (String str2 : uVar.a.keySet()) {
            if (k(uVar, uVar2, str2)) {
            }
        }
        return DBG;
        return true;
    }

    public boolean isValidTarget(View view) {
        ArrayList<Class<?>> arrayList;
        ArrayList<String> arrayList2;
        int id = view.getId();
        ArrayList<Integer> arrayList3 = this.mTargetIdExcludes;
        if (arrayList3 != null && arrayList3.contains(Integer.valueOf(id))) {
            return DBG;
        }
        ArrayList<View> arrayList4 = this.mTargetExcludes;
        if (arrayList4 != null && arrayList4.contains(view)) {
            return DBG;
        }
        ArrayList<Class<?>> arrayList5 = this.mTargetTypeExcludes;
        if (arrayList5 != null) {
            int size = arrayList5.size();
            for (int i = 0; i < size; i++) {
                if (this.mTargetTypeExcludes.get(i).isInstance(view)) {
                    return DBG;
                }
            }
        }
        if (this.mTargetNameExcludes != null && d1.I(view) != null && this.mTargetNameExcludes.contains(d1.I(view))) {
            return DBG;
        }
        if ((this.mTargetIds.size() == 0 && this.mTargets.size() == 0 && (((arrayList = this.mTargetTypes) == null || arrayList.isEmpty()) && ((arrayList2 = this.mTargetNames) == null || arrayList2.isEmpty()))) || this.mTargetIds.contains(Integer.valueOf(id)) || this.mTargets.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.mTargetNames;
        if (arrayList6 != null && arrayList6.contains(d1.I(view))) {
            return true;
        }
        if (this.mTargetTypes != null) {
            for (int i2 = 0; i2 < this.mTargetTypes.size(); i2++) {
                if (this.mTargetTypes.get(i2).isInstance(view)) {
                    return true;
                }
            }
        }
        return DBG;
    }

    public final void l(androidx.collection.a aVar, androidx.collection.a aVar2, SparseArray sparseArray, SparseArray sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            View view2 = (View) sparseArray.valueAt(i);
            if (view2 != null && isValidTarget(view2) && (view = (View) sparseArray2.get(sparseArray.keyAt(i))) != null && isValidTarget(view)) {
                u uVar = (u) aVar.get(view2);
                u uVar2 = (u) aVar2.get(view);
                if (uVar != null && uVar2 != null) {
                    this.mStartValuesList.add(uVar);
                    this.mEndValuesList.add(uVar2);
                    aVar.remove(view2);
                    aVar2.remove(view);
                }
            }
        }
    }

    public final void m(androidx.collection.a aVar, androidx.collection.a aVar2) {
        u uVar;
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View view = (View) aVar.keyAt(size);
            if (view != null && isValidTarget(view) && (uVar = (u) aVar2.remove(view)) != null && isValidTarget(uVar.b)) {
                this.mStartValuesList.add((u) aVar.removeAt(size));
                this.mEndValuesList.add(uVar);
            }
        }
    }

    public final void n(androidx.collection.a aVar, androidx.collection.a aVar2, androidx.collection.d dVar, androidx.collection.d dVar2) {
        View view;
        int m = dVar.m();
        for (int i = 0; i < m; i++) {
            View view2 = (View) dVar.n(i);
            if (view2 != null && isValidTarget(view2) && (view = (View) dVar2.f(dVar.i(i))) != null && isValidTarget(view)) {
                u uVar = (u) aVar.get(view2);
                u uVar2 = (u) aVar2.get(view);
                if (uVar != null && uVar2 != null) {
                    this.mStartValuesList.add(uVar);
                    this.mEndValuesList.add(uVar2);
                    aVar.remove(view2);
                    aVar2.remove(view);
                }
            }
        }
    }

    public final void o(androidx.collection.a aVar, androidx.collection.a aVar2, androidx.collection.a aVar3, androidx.collection.a aVar4) {
        View view;
        int size = aVar3.size();
        for (int i = 0; i < size; i++) {
            View view2 = (View) aVar3.valueAt(i);
            if (view2 != null && isValidTarget(view2) && (view = (View) aVar4.get(aVar3.keyAt(i))) != null && isValidTarget(view)) {
                u uVar = (u) aVar.get(view2);
                u uVar2 = (u) aVar2.get(view);
                if (uVar != null && uVar2 != null) {
                    this.mStartValuesList.add(uVar);
                    this.mEndValuesList.add(uVar2);
                    aVar.remove(view2);
                    aVar2.remove(view);
                }
            }
        }
    }

    public final void p(v vVar, v vVar2) {
        androidx.collection.a aVar = new androidx.collection.a(vVar.a);
        androidx.collection.a aVar2 = new androidx.collection.a(vVar2.a);
        int i = 0;
        while (true) {
            int[] iArr = this.mMatchOrder;
            if (i < iArr.length) {
                int i2 = iArr[i];
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 == 4) {
                                n(aVar, aVar2, vVar.c, vVar2.c);
                            }
                        } else {
                            l(aVar, aVar2, vVar.b, vVar2.b);
                        }
                    } else {
                        o(aVar, aVar2, vVar.d, vVar2.d);
                    }
                } else {
                    m(aVar, aVar2);
                }
                i++;
            } else {
                a(aVar, aVar2);
                return;
            }
        }
    }

    public void pause(View view) {
        if (!this.mEnded) {
            androidx.collection.a i = i();
            int size = i.size();
            r0 d2 = c0.d(view);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                d dVar = (d) i.valueAt(i2);
                if (dVar.a != null && d2.equals(dVar.d)) {
                    androidx.transition.a.b((Animator) i.keyAt(i2));
                }
            }
            ArrayList<g> arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size2 = arrayList2.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    ((g) arrayList2.get(i3)).b(this);
                }
            }
            this.mPaused = true;
        }
    }

    public void playTransition(ViewGroup viewGroup) {
        d dVar;
        boolean z;
        this.mStartValuesList = new ArrayList<>();
        this.mEndValuesList = new ArrayList<>();
        p(this.mStartValues, this.mEndValues);
        androidx.collection.a i = i();
        int size = i.size();
        r0 d2 = c0.d(viewGroup);
        for (int i2 = size - 1; i2 >= 0; i2--) {
            Animator animator = (Animator) i.keyAt(i2);
            if (animator != null && (dVar = (d) i.get(animator)) != null && dVar.a != null && d2.equals(dVar.d)) {
                u uVar = dVar.c;
                View view = dVar.a;
                u transitionValues = getTransitionValues(view, true);
                u matchedTransitionValues = getMatchedTransitionValues(view, true);
                if (transitionValues == null && matchedTransitionValues == null) {
                    matchedTransitionValues = (u) this.mEndValues.a.get(view);
                }
                if ((transitionValues != null || matchedTransitionValues != null) && dVar.e.isTransitionRequired(uVar, matchedTransitionValues)) {
                    z = true;
                } else {
                    z = DBG;
                }
                if (z) {
                    if (!animator.isRunning() && !animator.isStarted()) {
                        i.remove(animator);
                    } else {
                        animator.cancel();
                    }
                }
            }
        }
        createAnimators(viewGroup, this.mStartValues, this.mEndValues, this.mStartValuesList, this.mEndValuesList);
        runAnimators();
    }

    public final void q(Animator animator, androidx.collection.a aVar) {
        if (animator != null) {
            animator.addListener(new b(aVar));
            animate(animator);
        }
    }

    public n removeListener(g gVar) {
        ArrayList<g> arrayList = this.mListeners;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(gVar);
        if (this.mListeners.size() == 0) {
            this.mListeners = null;
        }
        return this;
    }

    public n removeTarget(View view) {
        this.mTargets.remove(view);
        return this;
    }

    public void resume(View view) {
        if (this.mPaused) {
            if (!this.mEnded) {
                androidx.collection.a i = i();
                int size = i.size();
                r0 d2 = c0.d(view);
                for (int i2 = size - 1; i2 >= 0; i2--) {
                    d dVar = (d) i.valueAt(i2);
                    if (dVar.a != null && d2.equals(dVar.d)) {
                        androidx.transition.a.c((Animator) i.keyAt(i2));
                    }
                }
                ArrayList<g> arrayList = this.mListeners;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                    int size2 = arrayList2.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        ((g) arrayList2.get(i3)).e(this);
                    }
                }
            }
            this.mPaused = DBG;
        }
    }

    public void runAnimators() {
        start();
        androidx.collection.a i = i();
        Iterator<Animator> it = this.mAnimators.iterator();
        while (it.hasNext()) {
            Animator next = it.next();
            if (i.containsKey(next)) {
                start();
                q(next, i);
            }
        }
        this.mAnimators.clear();
        end();
    }

    public void setCanRemoveViews(boolean z) {
        this.mCanRemoveViews = z;
    }

    public n setDuration(long j) {
        this.mDuration = j;
        return this;
    }

    public void setEpicenterCallback(f fVar) {
        this.mEpicenterCallback = fVar;
    }

    public n setInterpolator(TimeInterpolator timeInterpolator) {
        this.mInterpolator = timeInterpolator;
        return this;
    }

    public void setMatchOrder(int... iArr) {
        if (iArr != null && iArr.length != 0) {
            for (int i = 0; i < iArr.length; i++) {
                if (j(iArr[i])) {
                    if (c(iArr, i)) {
                        throw new IllegalArgumentException("matches contains a duplicate value");
                    }
                } else {
                    throw new IllegalArgumentException("matches contains invalid value");
                }
            }
            this.mMatchOrder = (int[]) iArr.clone();
            return;
        }
        this.mMatchOrder = DEFAULT_MATCH_ORDER;
    }

    public void setPathMotion(h hVar) {
        if (hVar == null) {
            this.mPathMotion = STRAIGHT_PATH_MOTION;
        } else {
            this.mPathMotion = hVar;
        }
    }

    public void setPropagation(q qVar) {
    }

    public n setSceneRoot(ViewGroup viewGroup) {
        this.mSceneRoot = viewGroup;
        return this;
    }

    public n setStartDelay(long j) {
        this.mStartDelay = j;
        return this;
    }

    public void start() {
        if (this.mNumInstances == 0) {
            ArrayList<g> arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((g) arrayList2.get(i)).a(this);
                }
            }
            this.mEnded = DBG;
        }
        this.mNumInstances++;
    }

    public String toString() {
        return toString("");
    }

    public n addTarget(int i) {
        if (i != 0) {
            this.mTargetIds.add(Integer.valueOf(i));
        }
        return this;
    }

    @Override // 
    /* renamed from: clone */
    public n mo4clone() {
        try {
            n nVar = (n) super.clone();
            nVar.mAnimators = new ArrayList<>();
            nVar.mStartValues = new v();
            nVar.mEndValues = new v();
            nVar.mStartValuesList = null;
            nVar.mEndValuesList = null;
            return nVar;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public n excludeChildren(int i, boolean z) {
        this.mTargetIdChildExcludes = e(this.mTargetIdChildExcludes, i, z);
        return this;
    }

    public n excludeTarget(int i, boolean z) {
        this.mTargetIdExcludes = e(this.mTargetIdExcludes, i, z);
        return this;
    }

    public n removeTarget(int i) {
        if (i != 0) {
            this.mTargetIds.remove(Integer.valueOf(i));
        }
        return this;
    }

    public String toString(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.mDuration != -1) {
            str2 = str2 + "dur(" + this.mDuration + ") ";
        }
        if (this.mStartDelay != -1) {
            str2 = str2 + "dly(" + this.mStartDelay + ") ";
        }
        if (this.mInterpolator != null) {
            str2 = str2 + "interp(" + this.mInterpolator + ") ";
        }
        if (this.mTargetIds.size() > 0 || this.mTargets.size() > 0) {
            String str3 = str2 + "tgts(";
            if (this.mTargetIds.size() > 0) {
                for (int i = 0; i < this.mTargetIds.size(); i++) {
                    if (i > 0) {
                        str3 = str3 + ", ";
                    }
                    str3 = str3 + this.mTargetIds.get(i);
                }
            }
            if (this.mTargets.size() > 0) {
                for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                    if (i2 > 0) {
                        str3 = str3 + ", ";
                    }
                    str3 = str3 + this.mTargets.get(i2);
                }
            }
            return str3 + ")";
        }
        return str2;
    }

    public n addTarget(String str) {
        if (this.mTargetNames == null) {
            this.mTargetNames = new ArrayList<>();
        }
        this.mTargetNames.add(str);
        return this;
    }

    public n excludeChildren(Class<?> cls, boolean z) {
        this.mTargetTypeChildExcludes = g(this.mTargetTypeChildExcludes, cls, z);
        return this;
    }

    public n excludeTarget(String str, boolean z) {
        this.mTargetNameExcludes = f(this.mTargetNameExcludes, str, z);
        return this;
    }

    public n removeTarget(String str) {
        ArrayList<String> arrayList = this.mTargetNames;
        if (arrayList != null) {
            arrayList.remove(str);
        }
        return this;
    }

    public n excludeTarget(Class<?> cls, boolean z) {
        this.mTargetTypeExcludes = g(this.mTargetTypeExcludes, cls, z);
        return this;
    }

    public n removeTarget(Class<?> cls) {
        ArrayList<Class<?>> arrayList = this.mTargetTypes;
        if (arrayList != null) {
            arrayList.remove(cls);
        }
        return this;
    }

    public n addTarget(Class<?> cls) {
        if (this.mTargetTypes == null) {
            this.mTargetTypes = new ArrayList<>();
        }
        this.mTargetTypes.add(cls);
        return this;
    }
}
