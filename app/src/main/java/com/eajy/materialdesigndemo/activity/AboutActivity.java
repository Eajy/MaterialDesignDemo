package com.eajy.materialdesigndemo.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.eajy.materialdesigndemo.Constant;
import com.eajy.materialdesigndemo.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_about);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        initView();
    }

    public void initView() {
        LinearLayout ll_card_about_2_shop = (LinearLayout) findViewById(R.id.ll_card_about_2_shop);
        LinearLayout ll_card_about_2_email = (LinearLayout) findViewById(R.id.ll_card_about_2_email);
        LinearLayout ll_card_about_2_git_hub = (LinearLayout) findViewById(R.id.ll_card_about_2_git_hub);
        ScrollView scroll_about = (ScrollView) findViewById(R.id.scroll_about);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        scroll_about.startAnimation(animation);

        ll_card_about_2_shop.setOnClickListener(this);
        ll_card_about_2_email.setOnClickListener(this);
        ll_card_about_2_git_hub.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_about_share);
        fab.setOnClickListener(this);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setStartOffset(600);

        TextView tv_about_version = (TextView) findViewById(R.id.tv_about_version);
        tv_about_version.setText(getVersionName());
        tv_about_version.startAnimation(alphaAnimation);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.ll_card_about_2_shop:
                intent.setData(Uri.parse(Constant.APP_URL));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;

            case R.id.ll_card_about_2_email:
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(Constant.EMAIL));
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.about_email_intent));
                //intent.putExtra(Intent.EXTRA_TEXT, "Hi,");
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(AboutActivity.this, getString(R.string.about_not_found_email), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.ll_card_about_2_git_hub:
                intent.setData(Uri.parse(Constant.GIT_HUB));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;

            case R.id.fab_about_share:
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT);
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, getString(R.string.share_with)));
                break;
        }
    }

    public String getVersionName() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return getString(R.string.about_version) + " " + version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
