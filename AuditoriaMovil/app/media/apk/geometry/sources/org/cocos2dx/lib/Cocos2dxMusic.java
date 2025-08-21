package org.cocos2dx.lib;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;
import java.io.FileInputStream;
/* loaded from: classes.dex */
public class Cocos2dxMusic {
    private static final String TAG = "Cocos2dxMusic";
    private MediaPlayer mBackgroundMediaPlayer;
    private final Context mContext;
    private String mCurrentPath;
    private float mLeftVolume;
    private boolean mPaused;
    private float mRightVolume;

    public Cocos2dxMusic(Context context) {
        this.mContext = context;
        initData();
    }

    private MediaPlayer createMediaplayer(String str) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            if (str.startsWith("/")) {
                FileInputStream fileInputStream = new FileInputStream(str);
                mediaPlayer.setDataSource(fileInputStream.getFD());
                fileInputStream.close();
            } else {
                AssetFileDescriptor openFd = this.mContext.getAssets().openFd(str);
                mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            }
            mediaPlayer.prepare();
            mediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
            return mediaPlayer;
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "error: " + e.getMessage(), e);
            return null;
        }
    }

    private void initData() {
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        this.mBackgroundMediaPlayer = null;
        this.mPaused = false;
        this.mCurrentPath = null;
    }

    public void end() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        initData();
    }

    public float getBackgroundMusicTime() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition() / 1000.0f;
        }
        return 0.0f;
    }

    public float getBackgroundVolume() {
        if (this.mBackgroundMediaPlayer != null) {
            return (this.mLeftVolume + this.mRightVolume) / 2.0f;
        }
        return 0.0f;
    }

    public boolean isBackgroundMusicPlaying() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer == null) {
            return false;
        }
        return mediaPlayer.isPlaying();
    }

    public void pauseBackgroundMusic() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        this.mBackgroundMediaPlayer.pause();
        this.mPaused = true;
    }

    public void playBackgroundMusic(String str, boolean z) {
        String str2 = this.mCurrentPath;
        if (str2 == null) {
            this.mBackgroundMediaPlayer = createMediaplayer(str);
            this.mCurrentPath = str;
        } else if (!str2.equals(str)) {
            MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            this.mBackgroundMediaPlayer = createMediaplayer(str);
            this.mCurrentPath = str;
        }
        MediaPlayer mediaPlayer2 = this.mBackgroundMediaPlayer;
        if (mediaPlayer2 == null) {
            Log.e(TAG, "playBackgroundMusic: background media player is null");
            return;
        }
        mediaPlayer2.stop();
        this.mBackgroundMediaPlayer.setLooping(z);
        try {
            this.mBackgroundMediaPlayer.prepare();
            this.mBackgroundMediaPlayer.seekTo(0);
            this.mBackgroundMediaPlayer.start();
            this.mPaused = false;
        } catch (Exception unused) {
            Log.e(TAG, "playBackgroundMusic: error state");
        }
    }

    public void preloadBackgroundMusic(String str) {
        String str2 = this.mCurrentPath;
        if (str2 == null || !str2.equals(str)) {
            MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            this.mBackgroundMediaPlayer = createMediaplayer(str);
            this.mCurrentPath = str;
        }
    }

    public void resumeBackgroundMusic() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer == null || !this.mPaused) {
            return;
        }
        mediaPlayer.start();
        this.mPaused = false;
    }

    public void rewindBackgroundMusic() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                this.mBackgroundMediaPlayer.prepare();
                this.mBackgroundMediaPlayer.seekTo(0);
                this.mBackgroundMediaPlayer.start();
                this.mPaused = false;
            } catch (Exception unused) {
                Log.e(TAG, "rewindBackgroundMusic: error state");
            }
        }
    }

    public void setBackgroundMusicTime(float f) {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                this.mBackgroundMediaPlayer.prepare();
                this.mBackgroundMediaPlayer.seekTo((int) (f * 1000.0f));
                this.mBackgroundMediaPlayer.start();
                this.mPaused = false;
            } catch (Exception unused) {
                Log.e(TAG, "rewindBackgroundMusic: error state");
            }
        }
    }

    public void setBackgroundVolume(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        this.mRightVolume = f;
        this.mLeftVolume = f;
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(f, f);
        }
    }

    public void stopBackgroundMusic() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mPaused = false;
        }
    }
}
