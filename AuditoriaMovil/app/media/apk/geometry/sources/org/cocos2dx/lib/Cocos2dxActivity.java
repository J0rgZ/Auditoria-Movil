package org.cocos2dx.lib;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.customRobTop.BaseRobTopActivity;
import org.cocos2dx.lib.Cocos2dxHandler;
import org.cocos2dx.lib.Cocos2dxHelper;
import org.cocos2dx.lib.services.Event;
/* loaded from: classes.dex */
public abstract class Cocos2dxActivity extends BaseRobTopActivity implements Cocos2dxHelper.Cocos2dxHelperListener {
    private static final String TAG = "Cocos2dxActivity";
    private static Context sContext;
    private Cocos2dxGLSurfaceView mGLSurfaceView;
    private Cocos2dxHandler mHandler;
    private boolean hasWindowFocus_ = false;
    private boolean mIsRunning = false;
    private boolean mIsOnPause = false;

    public static Context getContext() {
        return sContext;
    }

    private static final boolean isAndroidEmulator() {
        String str = Build.MODEL;
        String str2 = TAG;
        Log.d(str2, "model=" + str);
        String str3 = Build.PRODUCT;
        Log.d(str2, "product=" + str3);
        boolean z = false;
        if (str3 != null && (str3.equals("sdk") || str3.contains("_sdk") || str3.contains("sdk_"))) {
            z = true;
        }
        Log.d(str2, "isEmulator=" + z);
        return z;
    }

    private void pauseGame() {
        this.mIsRunning = false;
        Cocos2dxHelper.onPause();
        this.mGLSurfaceView.onPause();
    }

    private void resumeGame() {
        this.mIsRunning = true;
        Cocos2dxHelper.onResume();
        this.mGLSurfaceView.onResume();
    }

    public void init() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, -2);
        Cocos2dxEditText cocos2dxEditText = new Cocos2dxEditText(this);
        cocos2dxEditText.setLayoutParams(layoutParams2);
        frameLayout.addView(cocos2dxEditText);
        Cocos2dxGLSurfaceView onCreateView = onCreateView();
        this.mGLSurfaceView = onCreateView;
        frameLayout.addView(onCreateView);
        if (isAndroidEmulator()) {
            this.mGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        }
        this.mGLSurfaceView.setCocos2dxRenderer(new Cocos2dxRenderer());
        cocos2dxEditText.setInputType(145);
        this.mGLSurfaceView.setCocos2dxEditText(cocos2dxEditText);
        setContentView(frameLayout);
    }

    public void injectEvent() {
        new Event(this).runOn();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.customRobTop.BaseRobTopActivity, com.customRobTop.DefaultRobTopActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        injectEvent();
        super.onCreate(bundle);
        sContext = this;
        this.mHandler = new Cocos2dxHandler(this);
        init();
        Cocos2dxHelper.init(this, this);
    }

    public Cocos2dxGLSurfaceView onCreateView() {
        return new Cocos2dxGLSurfaceView(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.customRobTop.BaseRobTopActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mIsOnPause = true;
        if (this.mIsRunning) {
            pauseGame();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.customRobTop.BaseRobTopActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mIsOnPause = false;
        if (!this.hasWindowFocus_ || this.mIsRunning) {
            return;
        }
        resumeGame();
    }

    @Override // com.customRobTop.BaseRobTopActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.hasWindowFocus_ = z;
        if (!z || this.mIsOnPause) {
            return;
        }
        resumeGame();
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void runOnGLThread(Runnable runnable) {
        this.mGLSurfaceView.queueEvent(runnable);
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void showDialog(String str, String str2) {
        Message message = new Message();
        message.what = 1;
        message.obj = new Cocos2dxHandler.DialogMessage(str, str2);
        this.mHandler.sendMessage(message);
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void showEditTextDialog(String str, String str2, int i, int i2, int i3, int i4) {
        Message message = new Message();
        message.what = 2;
        message.obj = new Cocos2dxHandler.EditBoxMessage(str, str2, i, i2, i3, i4);
        this.mHandler.sendMessage(message);
    }
}
