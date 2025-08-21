package org.cocos2dx.lib;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import com.customRobTop.BaseRobTopActivity;
/* loaded from: classes.dex */
public class Cocos2dxGLSurfaceView extends GLSurfaceView {
    private static final int HANDLER_CLOSE_IME_KEYBOARD = 3;
    private static final int HANDLER_OPEN_IME_KEYBOARD = 2;
    private static final String TAG = "Cocos2dxGLSurfaceView";
    private static Cocos2dxGLSurfaceView mCocos2dxGLSurfaceView;
    private static Cocos2dxTextInputWraper sCocos2dxTextInputWraper;
    private static Handler sHandler;
    private Cocos2dxEditText mCocos2dxEditText;
    private Cocos2dxRenderer mCocos2dxRenderer;

    public Cocos2dxGLSurfaceView(Context context) {
        super(context);
        initView();
    }

    public Cocos2dxGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public static void closeIMEKeyboard() {
        Message message = new Message();
        message.what = 3;
        sHandler.sendMessage(message);
    }

    private static void dumpMotionEvent(MotionEvent motionEvent) {
        StringBuilder sb = new StringBuilder("event ACTION_");
        int action = motionEvent.getAction();
        int i = action & 255;
        sb.append(new String[]{"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"}[i]);
        if (i == 5 || i == 6) {
            sb.append("(pid ");
            sb.append(action >> 8);
            sb.append(")");
        }
        sb.append("[");
        int i2 = 0;
        while (i2 < motionEvent.getPointerCount()) {
            sb.append("#");
            sb.append(i2);
            sb.append("(pid ");
            sb.append(motionEvent.getPointerId(i2));
            sb.append(")=");
            sb.append((int) motionEvent.getX(i2));
            sb.append(",");
            sb.append((int) motionEvent.getY(i2));
            i2++;
            if (i2 < motionEvent.getPointerCount()) {
                sb.append(";");
            }
        }
        sb.append("]");
        Log.d(TAG, sb.toString());
    }

    private String getContentText() {
        return this.mCocos2dxRenderer.getContentText();
    }

    public static Cocos2dxGLSurfaceView getInstance() {
        return mCocos2dxGLSurfaceView;
    }

    public static void openIMEKeyboard() {
        Message message = new Message();
        message.what = 2;
        message.obj = mCocos2dxGLSurfaceView.getContentText();
        sHandler.sendMessage(message);
    }

    public static void queueAccelerometer(final float f, final float f2, final float f3, final long j) {
        mCocos2dxGLSurfaceView.queueEvent(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxGLSurfaceView.2
            @Override // java.lang.Runnable
            public void run() {
                Cocos2dxAccelerometer.onSensorChanged(f, f2, f3, j);
            }
        });
    }

    public void deleteBackward() {
        queueEvent(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxGLSurfaceView.13
            @Override // java.lang.Runnable
            public void run() {
                Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleDeleteBackward();
            }
        });
    }

    public Cocos2dxEditText getCocos2dxEditText() {
        return this.mCocos2dxEditText;
    }

    protected void initView() {
        setEGLContextClientVersion(2);
        setFocusableInTouchMode(true);
        setPreserveEGLContextOnPause(true);
        mCocos2dxGLSurfaceView = this;
        sCocos2dxTextInputWraper = new Cocos2dxTextInputWraper(this);
        sHandler = new Handler() { // from class: org.cocos2dx.lib.Cocos2dxGLSurfaceView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i != 2) {
                    if (i == 3 && Cocos2dxGLSurfaceView.this.mCocos2dxEditText != null) {
                        Cocos2dxGLSurfaceView.this.mCocos2dxEditText.removeTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
                        ((InputMethodManager) Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(Cocos2dxGLSurfaceView.this.mCocos2dxEditText.getWindowToken(), 0);
                        Cocos2dxGLSurfaceView.this.requestFocus();
                        Log.d("GLSurfaceView", "HideSoftInput");
                        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleTextClosed();
                    }
                } else if (Cocos2dxGLSurfaceView.this.mCocos2dxEditText == null || !Cocos2dxGLSurfaceView.this.mCocos2dxEditText.requestFocus()) {
                } else {
                    Cocos2dxGLSurfaceView.this.mCocos2dxEditText.removeTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
                    Cocos2dxGLSurfaceView.this.mCocos2dxEditText.setText("");
                    String str = (String) message.obj;
                    Cocos2dxGLSurfaceView.this.mCocos2dxEditText.append(str);
                    Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper.setOriginText(str);
                    Cocos2dxGLSurfaceView.this.mCocos2dxEditText.addTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
                    ((InputMethodManager) Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext().getSystemService("input_method")).showSoftInput(Cocos2dxGLSurfaceView.this.mCocos2dxEditText, 0);
                    Log.d("GLSurfaceView", "showSoftInput");
                }
            }
        };
    }

    public void insertText(final String str) {
        queueEvent(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxGLSurfaceView.12
            @Override // java.lang.Runnable
            public void run() {
                Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleInsertText(str);
            }
        });
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(final int i, KeyEvent keyEvent) {
        if (i == 4 || i == 82) {
            if (keyEvent.getRepeatCount() == 0 && !BaseRobTopActivity.blockBackButton_) {
                queueEvent(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxGLSurfaceView.11
                    @Override // java.lang.Runnable
                    public void run() {
                        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleKeyDown(i);
                    }
                });
                return true;
            }
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        queueEvent(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxGLSurfaceView.4
            @Override // java.lang.Runnable
            public void run() {
                Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleOnPause();
            }
        });
        setRenderMode(0);
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        super.onResume();
        setRenderMode(1);
        queueEvent(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxGLSurfaceView.3
            @Override // java.lang.Runnable
            public void run() {
                Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleOnResume();
            }
        });
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (isInEditMode()) {
            return;
        }
        this.mCocos2dxRenderer.setScreenWidthAndHeight(i, i2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        final int[] iArr = new int[pointerCount];
        final float[] fArr = new float[pointerCount];
        final float[] fArr2 = new float[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            iArr[i] = motionEvent.getPointerId(i);
            fArr[i] = motionEvent.getX(i);
            fArr2[i] = motionEvent.getY(i);
        }
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            final int pointerId = motionEvent.getPointerId(0);
            final float f = fArr[0];
            final float f2 = fArr2[0];
            queueEvent(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxGLSurfaceView.6
                @Override // java.lang.Runnable
                public void run() {
                    Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionDown(pointerId, f, f2);
                }
            });
        } else if (action == 1) {
            final int pointerId2 = motionEvent.getPointerId(0);
            final float f3 = fArr[0];
            final float f4 = fArr2[0];
            queueEvent(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxGLSurfaceView.9
                @Override // java.lang.Runnable
                public void run() {
                    Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionUp(pointerId2, f3, f4);
                }
            });
        } else if (action == 2) {
            queueEvent(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxGLSurfaceView.7
                @Override // java.lang.Runnable
                public void run() {
                    Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionMove(iArr, fArr, fArr2);
                }
            });
        } else if (action == 3) {
            queueEvent(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxGLSurfaceView.10
                @Override // java.lang.Runnable
                public void run() {
                    Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionCancel(iArr, fArr, fArr2);
                }
            });
        } else if (action == 5) {
            int action2 = motionEvent.getAction() >> 8;
            final int pointerId3 = motionEvent.getPointerId(action2);
            final float x = motionEvent.getX(action2);
            final float y = motionEvent.getY(action2);
            queueEvent(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxGLSurfaceView.5
                @Override // java.lang.Runnable
                public void run() {
                    Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionDown(pointerId3, x, y);
                }
            });
        } else if (action == 6) {
            int action3 = motionEvent.getAction() >> 8;
            final int pointerId4 = motionEvent.getPointerId(action3);
            final float x2 = motionEvent.getX(action3);
            final float y2 = motionEvent.getY(action3);
            queueEvent(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxGLSurfaceView.8
                @Override // java.lang.Runnable
                public void run() {
                    Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionUp(pointerId4, x2, y2);
                }
            });
        }
        return true;
    }

    public void setCocos2dxEditText(Cocos2dxEditText cocos2dxEditText) {
        Cocos2dxTextInputWraper cocos2dxTextInputWraper;
        this.mCocos2dxEditText = cocos2dxEditText;
        if (cocos2dxEditText == null || (cocos2dxTextInputWraper = sCocos2dxTextInputWraper) == null) {
            return;
        }
        cocos2dxEditText.setOnEditorActionListener(cocos2dxTextInputWraper);
        this.mCocos2dxEditText.setCocos2dxGLSurfaceView(this);
        requestFocus();
    }

    public void setCocos2dxRenderer(Cocos2dxRenderer cocos2dxRenderer) {
        this.mCocos2dxRenderer = cocos2dxRenderer;
        setRenderer(cocos2dxRenderer);
    }
}
