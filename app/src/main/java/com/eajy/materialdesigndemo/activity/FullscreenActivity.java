package com.eajy.materialdesigndemo.activity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.eajy.materialdesigndemo.R;

public class FullscreenActivity extends AppCompatActivity {

    private VideoView video_fullscreen;
    private RelativeLayout relative_fullscreen;
    private ProgressBar progress_fullscreen;
    private boolean isShowBar;

    private final int MESSAGE_HIDE_BARS = 0x001;
    private final int MESSAGE_VIDEO_PREPARED = 0x002;

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_HIDE_BARS:
                    hideBars();
                    break;
                case MESSAGE_VIDEO_PREPARED:
                    Animation animation = new AlphaAnimation(1.0f, 0.0f);
                    animation.setDuration(500);
                    relative_fullscreen.startAnimation(animation);
                    relative_fullscreen.setVisibility(View.GONE);
                    break;
            }
        }
    };

    /*private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hideBars();
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.hide();
        }

        progress_fullscreen = findViewById(R.id.progress_fullscreen);
        relative_fullscreen = findViewById(R.id.relative_fullscreen);
        video_fullscreen = findViewById(R.id.video_fullscreen);
    }

    private void playVideo() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.full_screen_google);
        video_fullscreen.setVideoURI(uri);
        video_fullscreen.start();

        video_fullscreen.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mHandler.sendEmptyMessageDelayed(MESSAGE_VIDEO_PREPARED, 500);
            }
        });

        video_fullscreen.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                video_fullscreen.start();
            }
        });

        video_fullscreen.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                progress_fullscreen.setVisibility(View.VISIBLE);
                return true;
            }
        });

        video_fullscreen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    if (!isShowBar) {
                        showBars();
                    } else {
                        hideBars();
                    }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideBars();
        playVideo();
    }

    @Override
    protected void onStop() {
        super.onStop();
        relative_fullscreen.setVisibility(View.VISIBLE);
    }

    private void showBars() {
        isShowBar = true;
        video_fullscreen.setSystemUiVisibility(View.VISIBLE);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.GRAY);
        window.setNavigationBarColor(Color.GRAY);

        mHandler.removeMessages(MESSAGE_HIDE_BARS);
        mHandler.sendEmptyMessageDelayed(MESSAGE_HIDE_BARS, 2000);

        /*mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, 2000);*/
    }

    private void hideBars() {
        isShowBar = false;
        video_fullscreen.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}

