package org.cocos2dx.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
/* loaded from: classes.dex */
public class Cocos2dxEditText extends EditText {
    private Cocos2dxGLSurfaceView mCocos2dxGLSurfaceView;

    public Cocos2dxEditText(Context context) {
        super(context);
    }

    public Cocos2dxEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Cocos2dxEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        super.onKeyDown(i, keyEvent);
        if (i == 4) {
            this.mCocos2dxGLSurfaceView.requestFocus();
            return true;
        }
        return true;
    }

    public void setCocos2dxGLSurfaceView(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.mCocos2dxGLSurfaceView = cocos2dxGLSurfaceView;
    }
}
