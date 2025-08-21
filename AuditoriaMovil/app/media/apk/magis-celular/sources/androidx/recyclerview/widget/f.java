package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.recyclerview.R$dimen;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import v.d1;
/* loaded from: classes.dex */
public abstract class f extends RecyclerView.n implements RecyclerView.q {

    /* loaded from: classes.dex */
    public static abstract class a {
        private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
        private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000;
        static final int RELATIVE_DIR_FLAGS = 3158064;
        private static final Interpolator sDragScrollInterpolator = new animation.InterpolatorC0031a();
        private static final Interpolator sDragViewScrollCapInterpolator = new b();
        private int mCachedMaxScrollSpeed = -1;

        /* renamed from: androidx.recyclerview.widget.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class animation.InterpolatorC0031a implements Interpolator {
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                return f * f * f * f * f;
            }
        }

        /* loaded from: classes.dex */
        public static class b implements Interpolator {
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return (f2 * f2 * f2 * f2 * f2) + 1.0f;
            }
        }

        public static int convertToRelativeDirection(int i, int i2) {
            int i3;
            int i4 = i & ABS_HORIZONTAL_DIR_FLAGS;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (i4 ^ (-1));
            if (i2 == 0) {
                i3 = i4 << 2;
            } else {
                int i6 = i4 << 1;
                i5 |= (-789517) & i6;
                i3 = (i6 & ABS_HORIZONTAL_DIR_FLAGS) << 2;
            }
            return i5 | i3;
        }

        public static g getDefaultUIUtil() {
            return h.a;
        }

        public static int makeFlag(int i, int i2) {
            return i2 << (i * 8);
        }

        public static int makeMovementFlags(int i, int i2) {
            int makeFlag = makeFlag(0, i2 | i);
            return makeFlag(2, i) | makeFlag(1, i2) | makeFlag;
        }

        public final int a(RecyclerView recyclerView) {
            if (this.mCachedMaxScrollSpeed == -1) {
                this.mCachedMaxScrollSpeed = recyclerView.getResources().getDimensionPixelSize(R$dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.mCachedMaxScrollSpeed;
        }

        public boolean canDropOver(RecyclerView recyclerView, RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2) {
            return true;
        }

        public RecyclerView.d0 chooseDropTarget(RecyclerView.d0 d0Var, List<RecyclerView.d0> list, int i, int i2) {
            int bottom;
            int abs;
            int top;
            int abs2;
            int left;
            int abs3;
            int right;
            int abs4;
            int width = i + d0Var.itemView.getWidth();
            int height = i2 + d0Var.itemView.getHeight();
            int left2 = i - d0Var.itemView.getLeft();
            int top2 = i2 - d0Var.itemView.getTop();
            int size = list.size();
            RecyclerView.d0 d0Var2 = null;
            int i3 = -1;
            for (int i4 = 0; i4 < size; i4++) {
                RecyclerView.d0 d0Var3 = list.get(i4);
                if (left2 > 0 && (right = d0Var3.itemView.getRight() - width) < 0 && d0Var3.itemView.getRight() > d0Var.itemView.getRight() && (abs4 = Math.abs(right)) > i3) {
                    d0Var2 = d0Var3;
                    i3 = abs4;
                }
                if (left2 < 0 && (left = d0Var3.itemView.getLeft() - i) > 0 && d0Var3.itemView.getLeft() < d0Var.itemView.getLeft() && (abs3 = Math.abs(left)) > i3) {
                    d0Var2 = d0Var3;
                    i3 = abs3;
                }
                if (top2 < 0 && (top = d0Var3.itemView.getTop() - i2) > 0 && d0Var3.itemView.getTop() < d0Var.itemView.getTop() && (abs2 = Math.abs(top)) > i3) {
                    d0Var2 = d0Var3;
                    i3 = abs2;
                }
                if (top2 > 0 && (bottom = d0Var3.itemView.getBottom() - height) < 0 && d0Var3.itemView.getBottom() > d0Var.itemView.getBottom() && (abs = Math.abs(bottom)) > i3) {
                    d0Var2 = d0Var3;
                    i3 = abs;
                }
            }
            return d0Var2;
        }

        public void clearView(RecyclerView recyclerView, RecyclerView.d0 d0Var) {
            h.a.a(d0Var.itemView);
        }

        public int convertToAbsoluteDirection(int i, int i2) {
            int i3;
            int i4 = i & RELATIVE_DIR_FLAGS;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (i4 ^ (-1));
            if (i2 == 0) {
                i3 = i4 >> 2;
            } else {
                int i6 = i4 >> 1;
                i5 |= (-3158065) & i6;
                i3 = (i6 & RELATIVE_DIR_FLAGS) >> 2;
            }
            return i5 | i3;
        }

        public final int getAbsoluteMovementFlags(RecyclerView recyclerView, RecyclerView.d0 d0Var) {
            return convertToAbsoluteDirection(getMovementFlags(recyclerView, d0Var), d1.z(recyclerView));
        }

        public long getAnimationDuration(RecyclerView recyclerView, int i, float f, float f2) {
            RecyclerView.l itemAnimator = recyclerView.getItemAnimator();
            if (itemAnimator == null) {
                if (i == 8) {
                    return 200L;
                }
                return 250L;
            } else if (i == 8) {
                return itemAnimator.n();
            } else {
                return itemAnimator.o();
            }
        }

        public int getBoundingBoxMargin() {
            return 0;
        }

        public abstract int getMovementFlags(RecyclerView recyclerView, RecyclerView.d0 d0Var);

        public float getSwipeEscapeVelocity(float f) {
            return f;
        }

        public float getSwipeVelocityThreshold(float f) {
            return f;
        }

        public boolean hasDragFlag(RecyclerView recyclerView, RecyclerView.d0 d0Var) {
            if ((getAbsoluteMovementFlags(recyclerView, d0Var) & 16711680) != 0) {
                return true;
            }
            return false;
        }

        public boolean hasSwipeFlag(RecyclerView recyclerView, RecyclerView.d0 d0Var) {
            if ((getAbsoluteMovementFlags(recyclerView, d0Var) & 65280) != 0) {
                return true;
            }
            return false;
        }

        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i, int i2, int i3, long j) {
            float f = 1.0f;
            int signum = (int) (((int) Math.signum(i2)) * a(recyclerView) * sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, (Math.abs(i2) * 1.0f) / i)));
            if (j <= DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS) {
                f = ((float) j) / 2000.0f;
            }
            int interpolation = (int) (signum * sDragScrollInterpolator.getInterpolation(f));
            if (interpolation == 0) {
                if (i2 > 0) {
                    return 1;
                }
                return -1;
            }
            return interpolation;
        }

        public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.d0 d0Var, float f, float f2, int i, boolean z) {
            h.a.d(canvas, recyclerView, d0Var.itemView, f, f2, i, z);
        }

        public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.d0 d0Var, float f, float f2, int i, boolean z) {
            h.a.c(canvas, recyclerView, d0Var.itemView, f, f2, i, z);
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.d0 d0Var, List<Object> list, int i, float f, float f2) {
            if (list.size() <= 0) {
                if (d0Var != null) {
                    int save = canvas.save();
                    onChildDraw(canvas, recyclerView, d0Var, f, f2, i, true);
                    canvas.restoreToCount(save);
                    return;
                }
                return;
            }
            androidx.appcompat.app.m.a(list.get(0));
            throw null;
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.d0 d0Var, List<Object> list, int i, float f, float f2) {
            int size = list.size();
            if (size <= 0) {
                if (d0Var != null) {
                    int save = canvas.save();
                    onChildDrawOver(canvas, recyclerView, d0Var, f, f2, i, true);
                    canvas.restoreToCount(save);
                }
                int i2 = size - 1;
                if (i2 < 0) {
                    return;
                }
                androidx.appcompat.app.m.a(list.get(i2));
                throw null;
            }
            androidx.appcompat.app.m.a(list.get(0));
            canvas.save();
            throw null;
        }

        public void onMoved(RecyclerView recyclerView, RecyclerView.d0 d0Var, int i, RecyclerView.d0 d0Var2, int i2, int i3, int i4) {
            RecyclerView.o layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof b) {
                ((b) layoutManager).prepareForDrop(d0Var.itemView, d0Var2.itemView, i3, i4);
                return;
            }
            if (layoutManager.canScrollHorizontally()) {
                if (layoutManager.getDecoratedLeft(d0Var2.itemView) <= recyclerView.getPaddingLeft()) {
                    recyclerView.scrollToPosition(i2);
                }
                if (layoutManager.getDecoratedRight(d0Var2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.scrollToPosition(i2);
                }
            }
            if (layoutManager.canScrollVertically()) {
                if (layoutManager.getDecoratedTop(d0Var2.itemView) <= recyclerView.getPaddingTop()) {
                    recyclerView.scrollToPosition(i2);
                }
                if (layoutManager.getDecoratedBottom(d0Var2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.scrollToPosition(i2);
                }
            }
        }

        public void onSelectedChanged(RecyclerView.d0 d0Var, int i) {
            if (d0Var != null) {
                h.a.b(d0Var.itemView);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void prepareForDrop(View view, View view2, int i, int i2);
    }
}
