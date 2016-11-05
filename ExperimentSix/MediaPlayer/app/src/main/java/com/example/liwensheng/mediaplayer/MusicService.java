package com.example.liwensheng.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Button;

/**
 * Created by liWensheng on 2016/11/3.
 */

public class MusicService extends Service {
    public MediaPlayer mediaPlayer = new MediaPlayer();
    private final IBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        load();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
    public void load() {
        mediaPlayer = new MediaPlayer();
        try {
//            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory() +"/music/K.Will-Melt.mp3");
            mediaPlayer.setDataSource("/data/K.Will-Melt.mp3");
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
            mediaPlayer.start();
    }
    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare();
                load();
                mediaPlayer.seekTo(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
