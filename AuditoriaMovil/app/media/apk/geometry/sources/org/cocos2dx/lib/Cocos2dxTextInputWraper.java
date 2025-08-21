package org.cocos2dx.lib;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
/* loaded from: classes.dex */
public class Cocos2dxTextInputWraper implements TextWatcher, TextView.OnEditorActionListener {
    private static final String TAG = "Cocos2dxTextInputWraper";
    private final Cocos2dxGLSurfaceView mCocos2dxGLSurfaceView;
    private String mOriginText;
    private String mText;

    public Cocos2dxTextInputWraper(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.mCocos2dxGLSurfaceView = cocos2dxGLSurfaceView;
    }

    private boolean isFullScreenEdit() {
        return ((InputMethodManager) this.mCocos2dxGLSurfaceView.getCocos2dxEditText().getContext().getSystemService("input_method")).isFullscreenMode();
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (isFullScreenEdit()) {
            return;
        }
        int length = editable.length() - this.mText.length();
        if (length > 0) {
            this.mCocos2dxGLSurfaceView.insertText(editable.subSequence(this.mText.length(), editable.length()).toString());
        } else {
            while (length < 0) {
                this.mCocos2dxGLSurfaceView.deleteBackward();
                length++;
            }
        }
        this.mText = editable.toString();
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.mText = charSequence.toString();
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (this.mCocos2dxGLSurfaceView.getCocos2dxEditText() == textView && isFullScreenEdit()) {
            for (int length = this.mOriginText.length(); length > 0; length--) {
                this.mCocos2dxGLSurfaceView.deleteBackward();
            }
            String charSequence = textView.getText().toString();
            if (charSequence.compareTo("") == 0) {
                charSequence = "\n";
            }
            if ('\n' != charSequence.charAt(charSequence.length() - 1)) {
                charSequence = charSequence + '\n';
            }
            this.mCocos2dxGLSurfaceView.insertText(charSequence);
        }
        if (i == 6) {
            this.mCocos2dxGLSurfaceView.requestFocus();
            Cocos2dxGLSurfaceView.closeIMEKeyboard();
            return false;
        }
        return false;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void setOriginText(String str) {
        this.mOriginText = str;
    }
}
