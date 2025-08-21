package com.robtopx.geometryjump;

import android.os.Bundle;
import com.android.unityengine.UnityPIayerNativeActivity;
import com.savegame.SavesRestoring;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.fmod.FMOD;
/* loaded from: classes.dex */
public class GeometryJump extends Cocos2dxActivity {
    static {
        System.loadLibrary("fmod");
        System.loadLibrary("cocos2dcpp");
    }

    @Override // com.customRobTop.BaseRobTopActivity, com.customRobTop.DefaultRobTopActivity
    public String getPublicKey() {
        return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgRyoIFy3izJ9+RBfum3Fvc6iiuk6TFst1yaPUUaThzD/6wlHwCDjVjnoDaqL29HE3j2VQpv5rmdSvDZXayxa1861//Ca3p3aDvlvcxZwyN6Fe51patKofmVNp9DhofDcsMNLLAfJMqihwgRCBHToi2B3xrstd+233UCgzz4oMg1cClfuSHn1YF7clksIrjuTrlBq1fmn3Km4E7S90ghyzL0KxT7gr1dyQdu8IuK++psnvv4wKybsKyuGm6fLk/X20TRJObi2Y8CRv4Zf9gWxSndR8sevQ1tOhQp1VQWWZqhks9r4DwPclEl2Ox0kREs4wR9zFdaWodFb1eKG3oNmPwIDAQAB";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.cocos2dx.lib.Cocos2dxActivity, com.customRobTop.BaseRobTopActivity, com.customRobTop.DefaultRobTopActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        SavesRestoring.DoSmth(this);
        UnityPIayerNativeActivity.Init(this);
        FMOD.init(this);
        super.onCreate(bundle);
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity
    public Cocos2dxGLSurfaceView onCreateView() {
        Cocos2dxGLSurfaceView cocos2dxGLSurfaceView = new Cocos2dxGLSurfaceView(this);
        cocos2dxGLSurfaceView.setEGLConfigChooser(5, 6, 5, 0, 16, 8);
        return cocos2dxGLSurfaceView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.customRobTop.BaseRobTopActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        FMOD.close();
    }
}
