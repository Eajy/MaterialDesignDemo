package com.eajy.materialdesigndemo.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.eajy.materialdesigndemo.R;
import com.eajy.materialdesigndemo.billing.IabBroadcastReceiver;
import com.eajy.materialdesigndemo.billing.IabHelper;
import com.eajy.materialdesigndemo.billing.IabResult;
import com.eajy.materialdesigndemo.billing.Inventory;
import com.eajy.materialdesigndemo.billing.Purchase;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class DonateActivity extends AppCompatActivity implements IabBroadcastReceiver.IabBroadcastListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        initView();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_donate);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public void receivedBroadcast() {

    }
}

