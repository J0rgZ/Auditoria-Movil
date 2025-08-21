package androidx.fragment.app;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.c;
/* loaded from: classes.dex */
public abstract class m extends androidx.viewpager.widget.a {
    public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;
    @Deprecated
    public static final int BEHAVIOR_SET_USER_VISIBLE_HINT = 0;
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentPagerAdapter";
    private final int mBehavior;
    private p mCurTransaction;
    private Fragment mCurrentPrimaryItem;
    private final i mFragmentManager;

    public m(i iVar) {
        this(iVar, 0);
    }

    public static String a(int i, long j) {
        return "android:switcher:" + i + ":" + j;
    }

    @Override // androidx.viewpager.widget.a
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.a();
        }
        this.mCurTransaction.k(fragment);
        if (fragment == this.mCurrentPrimaryItem) {
            this.mCurrentPrimaryItem = null;
        }
    }

    @Override // androidx.viewpager.widget.a
    public void finishUpdate(ViewGroup viewGroup) {
        p pVar = this.mCurTransaction;
        if (pVar != null) {
            pVar.j();
            this.mCurTransaction = null;
        }
    }

    public abstract Fragment getItem(int i);

    public long getItemId(int i) {
        return i;
    }

    @Override // androidx.viewpager.widget.a
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.a();
        }
        long itemId = getItemId(i);
        Fragment c = this.mFragmentManager.c(a(viewGroup.getId(), itemId));
        if (c != null) {
            this.mCurTransaction.f(c);
        } else {
            c = getItem(i);
            this.mCurTransaction.c(viewGroup.getId(), c, a(viewGroup.getId(), itemId));
        }
        if (c != this.mCurrentPrimaryItem) {
            c.setMenuVisibility(DEBUG);
            if (this.mBehavior == 1) {
                this.mCurTransaction.r(c, c.b.STARTED);
            } else {
                c.setUserVisibleHint(DEBUG);
            }
        }
        return c;
    }

    @Override // androidx.viewpager.widget.a
    public boolean isViewFromObject(View view, Object obj) {
        if (((Fragment) obj).getView() == view) {
            return true;
        }
        return DEBUG;
    }

    @Override // androidx.viewpager.widget.a
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    @Override // androidx.viewpager.widget.a
    public Parcelable saveState() {
        return null;
    }

    @Override // androidx.viewpager.widget.a
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.mCurrentPrimaryItem;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(DEBUG);
                if (this.mBehavior == 1) {
                    if (this.mCurTransaction == null) {
                        this.mCurTransaction = this.mFragmentManager.a();
                    }
                    this.mCurTransaction.r(this.mCurrentPrimaryItem, c.b.STARTED);
                } else {
                    this.mCurrentPrimaryItem.setUserVisibleHint(DEBUG);
                }
            }
            fragment.setMenuVisibility(true);
            if (this.mBehavior == 1) {
                if (this.mCurTransaction == null) {
                    this.mCurTransaction = this.mFragmentManager.a();
                }
                this.mCurTransaction.r(fragment, c.b.RESUMED);
            } else {
                fragment.setUserVisibleHint(true);
            }
            this.mCurrentPrimaryItem = fragment;
        }
    }

    @Override // androidx.viewpager.widget.a
    public void startUpdate(ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }

    public m(i iVar, int i) {
        this.mCurTransaction = null;
        this.mCurrentPrimaryItem = null;
        this.mFragmentManager = iVar;
        this.mBehavior = i;
    }
}
