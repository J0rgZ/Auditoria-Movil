package androidx.customview.widget;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/* loaded from: classes.dex */
public abstract class b {

    /* loaded from: classes.dex */
    public interface a {
        void a(Object obj, Rect rect);
    }

    /* renamed from: androidx.customview.widget.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0015b {
        Object a(Object obj, int i);

        int b(Object obj);
    }

    /* loaded from: classes.dex */
    public static class c implements Comparator {
        public final Rect a = new Rect();
        public final Rect b = new Rect();
        public final boolean c;
        public final a d;

        public c(boolean z, a aVar) {
            this.c = z;
            this.d = aVar;
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            Rect rect = this.a;
            Rect rect2 = this.b;
            this.d.a(obj, rect);
            this.d.a(obj2, rect2);
            int i = rect.top;
            int i2 = rect2.top;
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            int i3 = rect.left;
            int i4 = rect2.left;
            if (i3 < i4) {
                if (!this.c) {
                    return -1;
                }
                return 1;
            } else if (i3 > i4) {
                if (this.c) {
                    return -1;
                }
                return 1;
            } else {
                int i5 = rect.bottom;
                int i6 = rect2.bottom;
                if (i5 < i6) {
                    return -1;
                }
                if (i5 > i6) {
                    return 1;
                }
                int i7 = rect.right;
                int i8 = rect2.right;
                if (i7 < i8) {
                    if (!this.c) {
                        return -1;
                    }
                    return 1;
                } else if (i7 > i8) {
                    if (this.c) {
                        return -1;
                    }
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    public static boolean a(int i, Rect rect, Rect rect2, Rect rect3) {
        boolean b = b(i, rect, rect2);
        if (b(i, rect, rect3) || !b) {
            return false;
        }
        if (j(i, rect, rect3) && i != 17 && i != 66 && k(i, rect, rect2) >= m(i, rect, rect3)) {
            return false;
        }
        return true;
    }

    public static boolean b(int i, Rect rect, Rect rect2) {
        if (i != 17) {
            if (i != 33) {
                if (i != 66) {
                    if (i != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            if (rect2.right >= rect.left && rect2.left <= rect.right) {
                return true;
            }
            return false;
        }
        if (rect2.bottom >= rect.top && rect2.top <= rect.bottom) {
            return true;
        }
        return false;
    }

    public static Object c(Object obj, InterfaceC0015b interfaceC0015b, a aVar, Object obj2, Rect rect, int i) {
        Rect rect2 = new Rect(rect);
        if (i != 17) {
            if (i != 33) {
                if (i != 66) {
                    if (i == 130) {
                        rect2.offset(0, -(rect.height() + 1));
                    } else {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                } else {
                    rect2.offset(-(rect.width() + 1), 0);
                }
            } else {
                rect2.offset(0, rect.height() + 1);
            }
        } else {
            rect2.offset(rect.width() + 1, 0);
        }
        int b = interfaceC0015b.b(obj);
        Rect rect3 = new Rect();
        Object obj3 = null;
        for (int i2 = 0; i2 < b; i2++) {
            Object a2 = interfaceC0015b.a(obj, i2);
            if (a2 != obj2) {
                aVar.a(a2, rect3);
                if (h(i, rect, rect3, rect2)) {
                    rect2.set(rect3);
                    obj3 = a2;
                }
            }
        }
        return obj3;
    }

    public static Object d(Object obj, InterfaceC0015b interfaceC0015b, a aVar, Object obj2, int i, boolean z, boolean z2) {
        int b = interfaceC0015b.b(obj);
        ArrayList arrayList = new ArrayList(b);
        for (int i2 = 0; i2 < b; i2++) {
            arrayList.add(interfaceC0015b.a(obj, i2));
        }
        Collections.sort(arrayList, new c(z, aVar));
        if (i != 1) {
            if (i == 2) {
                return e(obj2, arrayList, z2);
            }
            throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
        }
        return f(obj2, arrayList, z2);
    }

    public static Object e(Object obj, ArrayList arrayList, boolean z) {
        int lastIndexOf;
        int size = arrayList.size();
        if (obj == null) {
            lastIndexOf = -1;
        } else {
            lastIndexOf = arrayList.lastIndexOf(obj);
        }
        int i = lastIndexOf + 1;
        if (i < size) {
            return arrayList.get(i);
        }
        if (z && size > 0) {
            return arrayList.get(0);
        }
        return null;
    }

    public static Object f(Object obj, ArrayList arrayList, boolean z) {
        int indexOf;
        int size = arrayList.size();
        if (obj == null) {
            indexOf = size;
        } else {
            indexOf = arrayList.indexOf(obj);
        }
        int i = indexOf - 1;
        if (i >= 0) {
            return arrayList.get(i);
        }
        if (z && size > 0) {
            return arrayList.get(size - 1);
        }
        return null;
    }

    public static int g(int i, int i2) {
        return (i * 13 * i) + (i2 * i2);
    }

    public static boolean h(int i, Rect rect, Rect rect2, Rect rect3) {
        if (!i(rect, rect2, i)) {
            return false;
        }
        if (!i(rect, rect3, i) || a(i, rect, rect2, rect3)) {
            return true;
        }
        if (a(i, rect, rect3, rect2) || g(k(i, rect, rect2), o(i, rect, rect2)) >= g(k(i, rect, rect3), o(i, rect, rect3))) {
            return false;
        }
        return true;
    }

    public static boolean i(Rect rect, Rect rect2, int i) {
        if (i != 17) {
            if (i != 33) {
                if (i != 66) {
                    if (i == 130) {
                        int i2 = rect.top;
                        int i3 = rect2.top;
                        if ((i2 < i3 || rect.bottom <= i3) && rect.bottom < rect2.bottom) {
                            return true;
                        }
                        return false;
                    }
                    throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                }
                int i4 = rect.left;
                int i5 = rect2.left;
                if ((i4 < i5 || rect.right <= i5) && rect.right < rect2.right) {
                    return true;
                }
                return false;
            }
            int i6 = rect.bottom;
            int i7 = rect2.bottom;
            if ((i6 > i7 || rect.top >= i7) && rect.top > rect2.top) {
                return true;
            }
            return false;
        }
        int i8 = rect.right;
        int i9 = rect2.right;
        if ((i8 > i9 || rect.left >= i9) && rect.left > rect2.left) {
            return true;
        }
        return false;
    }

    public static boolean j(int i, Rect rect, Rect rect2) {
        if (i != 17) {
            if (i != 33) {
                if (i != 66) {
                    if (i == 130) {
                        if (rect.bottom <= rect2.top) {
                            return true;
                        }
                        return false;
                    }
                    throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                } else if (rect.right <= rect2.left) {
                    return true;
                } else {
                    return false;
                }
            } else if (rect.top >= rect2.bottom) {
                return true;
            } else {
                return false;
            }
        } else if (rect.left >= rect2.right) {
            return true;
        } else {
            return false;
        }
    }

    public static int k(int i, Rect rect, Rect rect2) {
        return Math.max(0, l(i, rect, rect2));
    }

    public static int l(int i, Rect rect, Rect rect2) {
        int i2;
        int i3;
        if (i != 17) {
            if (i != 33) {
                if (i != 66) {
                    if (i == 130) {
                        i2 = rect2.top;
                        i3 = rect.bottom;
                    } else {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                } else {
                    i2 = rect2.left;
                    i3 = rect.right;
                }
            } else {
                i2 = rect.top;
                i3 = rect2.bottom;
            }
        } else {
            i2 = rect.left;
            i3 = rect2.right;
        }
        return i2 - i3;
    }

    public static int m(int i, Rect rect, Rect rect2) {
        return Math.max(1, n(i, rect, rect2));
    }

    public static int n(int i, Rect rect, Rect rect2) {
        int i2;
        int i3;
        if (i != 17) {
            if (i != 33) {
                if (i != 66) {
                    if (i == 130) {
                        i2 = rect2.bottom;
                        i3 = rect.bottom;
                    } else {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                } else {
                    i2 = rect2.right;
                    i3 = rect.right;
                }
            } else {
                i2 = rect.top;
                i3 = rect2.top;
            }
        } else {
            i2 = rect.left;
            i3 = rect2.left;
        }
        return i2 - i3;
    }

    public static int o(int i, Rect rect, Rect rect2) {
        if (i != 17) {
            if (i != 33) {
                if (i != 66) {
                    if (i != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs((rect.left + (rect.width() / 2)) - (rect2.left + (rect2.width() / 2)));
        }
        return Math.abs((rect.top + (rect.height() / 2)) - (rect2.top + (rect2.height() / 2)));
    }
}
