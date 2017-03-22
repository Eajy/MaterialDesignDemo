package com.eajy.materialdesigndemo.activity;

import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eajy.materialdesigndemo.R;

public class ShareViewActivity extends AppCompatActivity {

    private TextView tv_share_view_tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_share_view);
        toolbar.setSubtitle("Shared Element Transitions");
        toolbar.setNavigationIcon(R.mipmap.ic_close_white_24dp);
        setSupportActionBar(toolbar);

        //左上角返回按钮处理成与返回键的逻辑一样，否则会没有过渡动画
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        initView();
    }

    public void initView() {
        CardView card_share_view = (CardView) findViewById(R.id.card_share_view);
        RelativeLayout rela_round_big = (RelativeLayout) findViewById(R.id.rela_round_big);
        tv_share_view_tip = (TextView) findViewById(R.id.tv_share_view_tip);

        if (getIntent() != null) {
            int color = getIntent().getIntExtra("color", 0);
            if (color == 1) {
                rela_round_big.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.google_blue)));
            } else if (color == 2) {
                rela_round_big.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.google_green)));
            } else if (color == 3) {
                rela_round_big.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.google_yellow)));
            } else if (color == 4) {
                rela_round_big.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.google_red)));
            } else {
                rela_round_big.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
            }
        }

        card_share_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ObjectAnimator upAnim = ObjectAnimator.ofFloat(view, "translationZ", 0);
                        upAnim.setDuration(200);
                        upAnim.setInterpolator(new DecelerateInterpolator());
                        upAnim.start();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        ObjectAnimator downAnim = ObjectAnimator.ofFloat(view, "translationZ", 20);
                        downAnim.setDuration(200);
                        downAnim.setInterpolator(new AccelerateInterpolator());
                        downAnim.start();
                        break;
                }
                return false;
            }
        });

        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(1500);
        alphaAnimation.setStartOffset(1000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tv_share_view_tip.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tv_share_view_tip.startAnimation(alphaAnimation);
    }

}
