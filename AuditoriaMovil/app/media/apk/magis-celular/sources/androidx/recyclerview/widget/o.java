package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.Map;
import java.util.WeakHashMap;
import v.d1;
import w.g0;
import w.j0;
/* loaded from: classes.dex */
public class o extends v.a {
    private final a mItemDelegate;
    final RecyclerView mRecyclerView;

    /* loaded from: classes.dex */
    public static class a extends v.a {
        public final o a;
        public Map b = new WeakHashMap();

        public a(o oVar) {
            this.a = oVar;
        }

        public v.a c(View view) {
            return (v.a) this.b.remove(view);
        }

        public void d(View view) {
            v.a k = d1.k(view);
            if (k != null && k != this) {
                this.b.put(view, k);
            }
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            v.a aVar = (v.a) this.b.get(view);
            if (aVar != null) {
                return aVar.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            return super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        public j0 getAccessibilityNodeProvider(View view) {
            v.a aVar = (v.a) this.b.get(view);
            if (aVar != null) {
                return aVar.getAccessibilityNodeProvider(view);
            }
            return super.getAccessibilityNodeProvider(view);
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            v.a aVar = (v.a) this.b.get(view);
            if (aVar != null) {
                aVar.onInitializeAccessibilityEvent(view, accessibilityEvent);
            } else {
                super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View view, g0 g0Var) {
            if (!this.a.shouldIgnore() && this.a.mRecyclerView.getLayoutManager() != null) {
                this.a.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfoForItem(view, g0Var);
                v.a aVar = (v.a) this.b.get(view);
                if (aVar != null) {
                    aVar.onInitializeAccessibilityNodeInfo(view, g0Var);
                    return;
                } else {
                    super.onInitializeAccessibilityNodeInfo(view, g0Var);
                    return;
                }
            }
            super.onInitializeAccessibilityNodeInfo(view, g0Var);
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            v.a aVar = (v.a) this.b.get(view);
            if (aVar != null) {
                aVar.onPopulateAccessibilityEvent(view, accessibilityEvent);
            } else {
                super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            }
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            v.a aVar = (v.a) this.b.get(viewGroup);
            if (aVar != null) {
                return aVar.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            }
            return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (!this.a.shouldIgnore() && this.a.mRecyclerView.getLayoutManager() != null) {
                v.a aVar = (v.a) this.b.get(view);
                if (aVar != null) {
                    if (aVar.performAccessibilityAction(view, i, bundle)) {
                        return true;
                    }
                } else if (super.performAccessibilityAction(view, i, bundle)) {
                    return true;
                }
                return this.a.mRecyclerView.getLayoutManager().performAccessibilityActionForItem(view, i, bundle);
            }
            return super.performAccessibilityAction(view, i, bundle);
        }

        public void sendAccessibilityEvent(View view, int i) {
            v.a aVar = (v.a) this.b.get(view);
            if (aVar != null) {
                aVar.sendAccessibilityEvent(view, i);
            } else {
                super.sendAccessibilityEvent(view, i);
            }
        }

        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            v.a aVar = (v.a) this.b.get(view);
            if (aVar != null) {
                aVar.sendAccessibilityEventUnchecked(view, accessibilityEvent);
            } else {
                super.sendAccessibilityEventUnchecked(view, accessibilityEvent);
            }
        }
    }

    public o(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        v.a itemDelegate = getItemDelegate();
        if (itemDelegate != null && (itemDelegate instanceof a)) {
            this.mItemDelegate = (a) itemDelegate;
        } else {
            this.mItemDelegate = new a(this);
        }
    }

    public v.a getItemDelegate() {
        return this.mItemDelegate;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        if ((view instanceof RecyclerView) && !shouldIgnore()) {
            RecyclerView recyclerView = (RecyclerView) view;
            if (recyclerView.getLayoutManager() != null) {
                recyclerView.getLayoutManager().onInitializeAccessibilityEvent(accessibilityEvent);
            }
        }
    }

    public void onInitializeAccessibilityNodeInfo(View view, g0 g0Var) {
        super.onInitializeAccessibilityNodeInfo(view, g0Var);
        if (!shouldIgnore() && this.mRecyclerView.getLayoutManager() != null) {
            this.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfo(g0Var);
        }
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (super.performAccessibilityAction(view, i, bundle)) {
            return true;
        }
        if (!shouldIgnore() && this.mRecyclerView.getLayoutManager() != null) {
            return this.mRecyclerView.getLayoutManager().performAccessibilityAction(i, bundle);
        }
        return false;
    }

    public boolean shouldIgnore() {
        return this.mRecyclerView.hasPendingAdapterUpdates();
    }
}
