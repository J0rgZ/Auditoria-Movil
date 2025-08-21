package org.cocos2dx.lib;

import android.content.Context;
import android.media.SoundPool;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Semaphore;
/* loaded from: classes.dex */
public class Cocos2dxSound {
    private static final int INVALID_SOUND_ID = -1;
    private static final int INVALID_STREAM_ID = -1;
    public static final int MAX_SIMULTANEOUS_STREAMS_DEFAULT = 5;
    public static final int MAX_SIMULTANEOUS_STREAMS_I9100 = 3;
    private static final int SOUND_PRIORITY = 1;
    private static final int SOUND_QUALITY = 5;
    private static final float SOUND_RATE = 1.0f;
    private static final String TAG = "Cocos2dxSound";
    private final Context mContext;
    private float mLeftVolume;
    private float mRightVolume;
    private Semaphore mSemaphore;
    private SoundPool mSoundPool;
    private int mStreamIdSyn;
    private int simultaneousStreams;
    private final HashMap<String, ArrayList<Integer>> mPathStreamIDsMap = new HashMap<>();
    private final HashMap<String, Integer> mPathSoundIDMap = new HashMap<>();
    private final ArrayList<SoundInfoForLoadedCompleted> mEffecToPlayWhenLoadedArray = new ArrayList<>();

    /* loaded from: classes.dex */
    public class OnLoadCompletedListener implements SoundPool.OnLoadCompleteListener {
        public OnLoadCompletedListener() {
        }

        @Override // android.media.SoundPool.OnLoadCompleteListener
        public void onLoadComplete(SoundPool soundPool, int i, int i2) {
            if (i2 == 0) {
                Iterator it = Cocos2dxSound.this.mEffecToPlayWhenLoadedArray.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    SoundInfoForLoadedCompleted soundInfoForLoadedCompleted = (SoundInfoForLoadedCompleted) it.next();
                    if (i == soundInfoForLoadedCompleted.soundID) {
                        Cocos2dxSound cocos2dxSound = Cocos2dxSound.this;
                        cocos2dxSound.mStreamIdSyn = cocos2dxSound.doPlayEffect(soundInfoForLoadedCompleted.path, soundInfoForLoadedCompleted.soundID, soundInfoForLoadedCompleted.isLoop, soundInfoForLoadedCompleted.pitch, soundInfoForLoadedCompleted.pan, soundInfoForLoadedCompleted.gain);
                        Cocos2dxSound.this.mEffecToPlayWhenLoadedArray.remove(soundInfoForLoadedCompleted);
                        break;
                    }
                }
            } else {
                Cocos2dxSound.this.mStreamIdSyn = -1;
            }
            Cocos2dxSound.this.mSemaphore.release();
        }
    }

    /* loaded from: classes.dex */
    public class SoundInfoForLoadedCompleted {
        public float gain;
        public boolean isLoop;
        public float pan;
        public String path;
        public float pitch;
        public int soundID;

        public SoundInfoForLoadedCompleted(String str, int i, boolean z, float f, float f2, float f3) {
            this.path = str;
            this.soundID = i;
            this.isLoop = z;
            this.pitch = f;
            this.pan = f2;
            this.gain = f3;
        }
    }

    public Cocos2dxSound(Context context, int i) {
        this.mContext = context;
        initData(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int doPlayEffect(String str, int i, boolean z, float f, float f2, float f3) {
        float max = Math.max(0.0f, Math.min((float) SOUND_RATE, this.mLeftVolume * f3 * Math.max(0.0f, Math.min((float) SOUND_RATE, SOUND_RATE - f2))));
        float max2 = Math.max(0.0f, Math.min((float) SOUND_RATE, this.mRightVolume * f3 * Math.max(0.0f, Math.min((float) SOUND_RATE, f2 + SOUND_RATE))));
        float max3 = Math.max(0.5f, Math.min(2.0f, SOUND_RATE * f));
        int play = this.mSoundPool.play(i, max, max2, z ? 2 : 1, z ? -1 : 0, max3);
        ArrayList<Integer> arrayList = this.mPathStreamIDsMap.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mPathStreamIDsMap.put(str, arrayList);
        }
        arrayList.add(Integer.valueOf(play));
        return play;
    }

    private void initData(int i) {
        SoundPool soundPool = new SoundPool(i, 3, 5);
        this.mSoundPool = soundPool;
        soundPool.setOnLoadCompleteListener(new OnLoadCompletedListener());
        this.mLeftVolume = 0.5f;
        this.simultaneousStreams = i;
        this.mRightVolume = 0.5f;
        this.mSemaphore = new Semaphore(0, true);
    }

    public int createSoundIDFromAsset(String str) {
        int i;
        try {
            i = str.startsWith("/") ? this.mSoundPool.load(str, 0) : this.mSoundPool.load(this.mContext.getAssets().openFd(str), 0);
        } catch (Exception e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
            i = -1;
        }
        if (i == 0) {
            return -1;
        }
        return i;
    }

    public void end() {
        this.mSoundPool.release();
        this.mPathStreamIDsMap.clear();
        this.mPathSoundIDMap.clear();
        this.mEffecToPlayWhenLoadedArray.clear();
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        initData(this.simultaneousStreams);
    }

    public float getEffectsVolume() {
        return (this.mLeftVolume + this.mRightVolume) / 2.0f;
    }

    public void pauseAllEffects() {
        this.mSoundPool.autoPause();
    }

    public void pauseEffect(int i) {
        this.mSoundPool.pause(i);
    }

    public int playEffect(String str, boolean z, float f, float f2, float f3) {
        int i;
        Integer num = this.mPathSoundIDMap.get(str);
        if (num != null) {
            return doPlayEffect(str, num.intValue(), z, f, f2, f3);
        }
        int preloadEffect = preloadEffect(str);
        Integer valueOf = Integer.valueOf(preloadEffect);
        valueOf.getClass();
        if (preloadEffect == -1) {
            return -1;
        }
        synchronized (this.mSoundPool) {
            ArrayList<SoundInfoForLoadedCompleted> arrayList = this.mEffecToPlayWhenLoadedArray;
            valueOf.getClass();
            arrayList.add(new SoundInfoForLoadedCompleted(str, preloadEffect, z, f, f2, f3));
            try {
                this.mSemaphore.acquire();
                i = this.mStreamIdSyn;
            } catch (Exception unused) {
                return -1;
            }
        }
        return i;
    }

    public int preloadEffect(String str) {
        Integer num = this.mPathSoundIDMap.get(str);
        if (num == null) {
            int createSoundIDFromAsset = createSoundIDFromAsset(str);
            Integer valueOf = Integer.valueOf(createSoundIDFromAsset);
            valueOf.getClass();
            if (createSoundIDFromAsset != -1) {
                this.mPathSoundIDMap.put(str, valueOf);
            }
            num = valueOf;
        }
        return num.intValue();
    }

    public void resumeAllEffects() {
        if (this.mPathStreamIDsMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, ArrayList<Integer>> entry : this.mPathStreamIDsMap.entrySet()) {
            Iterator<Integer> it = entry.getValue().iterator();
            while (it.hasNext()) {
                this.mSoundPool.resume(it.next().intValue());
            }
        }
    }

    public void resumeEffect(int i) {
        this.mSoundPool.resume(i);
    }

    public void setEffectsVolume(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > SOUND_RATE) {
            f = SOUND_RATE;
        }
        this.mRightVolume = f;
        this.mLeftVolume = f;
        if (this.mPathStreamIDsMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, ArrayList<Integer>> entry : this.mPathStreamIDsMap.entrySet()) {
            Iterator<Integer> it = entry.getValue().iterator();
            while (it.hasNext()) {
                this.mSoundPool.setVolume(it.next().intValue(), this.mLeftVolume, this.mRightVolume);
            }
        }
    }

    public void stopAllEffects() {
        if (!this.mPathStreamIDsMap.isEmpty()) {
            for (Map.Entry<String, ArrayList<Integer>> entry : this.mPathStreamIDsMap.entrySet()) {
                Iterator<Integer> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    this.mSoundPool.stop(it.next().intValue());
                }
            }
        }
        this.mPathStreamIDsMap.clear();
    }

    public void stopEffect(int i) {
        this.mSoundPool.stop(i);
        for (String str : this.mPathStreamIDsMap.keySet()) {
            if (this.mPathStreamIDsMap.get(str).contains(Integer.valueOf(i))) {
                this.mPathStreamIDsMap.get(str).remove(this.mPathStreamIDsMap.get(str).indexOf(Integer.valueOf(i)));
                return;
            }
        }
    }

    public void unloadEffect(String str) {
        ArrayList<Integer> arrayList = this.mPathStreamIDsMap.get(str);
        if (arrayList != null) {
            Iterator<Integer> it = arrayList.iterator();
            while (it.hasNext()) {
                this.mSoundPool.stop(it.next().intValue());
            }
        }
        this.mPathStreamIDsMap.remove(str);
        Integer num = this.mPathSoundIDMap.get(str);
        if (num != null) {
            this.mSoundPool.unload(num.intValue());
            this.mPathSoundIDMap.remove(str);
        }
    }
}
