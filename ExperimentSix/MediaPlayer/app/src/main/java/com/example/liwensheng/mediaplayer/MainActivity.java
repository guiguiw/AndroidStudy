package com.example.liwensheng.mediaplayer;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by liWensheng on 2016/11/3.
 */

public class MainActivity extends AppCompatActivity {


    //服务端service
    private MusicService musicService = new MusicService();
    //button
    private Button playButton, stopButton, quitButton;
    //TextView
    private TextView playState, playingTime;
    private ImageView imageView;
    //标志，表示是否在播放
    private boolean flag = false;
    private SeekBar seekBar;
    //用来处理ui
    //处理图片旋转
    private ObjectAnimator objectAnimator;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //连接绑定服务端
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        bindService(intent, sc, BIND_AUTO_CREATE);

        //获取界面对应的组件
        playButton = (Button) findViewById(R.id.playButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        quitButton = (Button) findViewById(R.id.quitButton);
        playState = (TextView) findViewById(R.id.musicState);
        playingTime = (TextView) findViewById(R.id.playingTime);
        seekBar = (SeekBar) findViewById(R.id.musicProgress);
        imageView = (ImageView) findViewById(R.id.musicPic);


        rotate();


        //绑定点击按钮事件
        clickListener();

        handler.post(runnable);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //更新播放时间
                playingTime.setText(getPlayingTime(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                musicService.mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        unbindService(sc);
        handler.removeCallbacks(runnable);
    }

    //点击按钮事件
    private void clickListener() {
        //click play button
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
        //click stop button
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
        //click quit button
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quit();
            }
        });
    }

    private void play() {
        if (!musicService.mediaPlayer.isPlaying()) {
            musicService.play();
            playButton.setText("PAUSE");
            playState.setText("Playing");
            if (!objectAnimator.isRunning())
                objectAnimator.start();
            else
                objectAnimator.resume();
            flag = !flag;
        }
        else {
            musicService.pause();
            playButton.setText("PLAY");
            playState.setText("Paused");
            objectAnimator.pause();
            flag = !flag;
        }
    }

    private void stop() {
        playButton.setText("PLAY");
        playState.setText("Stopped");
        musicService.stop();
        objectAnimator.end();
        flag = false;
    }

    private void quit() {
        unbindService(sc);
        handler.removeCallbacks(runnable);
        try {
            MainActivity.this.finish();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rotate() {
        //anim.setDuration(1000);

        objectAnimator = ObjectAnimator .ofFloat(imageView, "rotation", 0f, 360f);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(5000);
        objectAnimator.setRepeatCount(-1);
    }

    //播放时间变化
    private String getPlayingTime(int progress) {
        int second = progress/1000;
        int min = second / 60;
        second %= 60;
        if (second < 10)
            return "0"+min+" : 0"+ second;
        return"0"+min+" : "+ second;
    }

    private void setDuration() {
        TextView duration = (TextView) findViewById(R.id.endTime);
        duration.setText(getPlayingTime(musicService.mediaPlayer.getDuration()));
    }

    //与服务建立连接
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService = ((MusicService.MyBinder)service).getService();
            setDuration();
            if (musicService.mediaPlayer.isPlaying()) {
                playButton.setText("PAUSE");
                playState.setText("Playing");
                objectAnimator.resume();
                objectAnimator.start();
            }

        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //处理进度条
            SeekBar sb = (SeekBar) findViewById( R.id.musicProgress);
            sb.setProgress(musicService.mediaPlayer.getCurrentPosition());
            sb.setMax(musicService.mediaPlayer.getDuration());
            handler.postDelayed(this, 1000);
        }
    };
}
