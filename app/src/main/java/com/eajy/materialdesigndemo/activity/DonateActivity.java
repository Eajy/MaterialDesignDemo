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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class DonateActivity extends AppCompatActivity implements IabBroadcastReceiver.IabBroadcastListener {

    private TextView tv_donate_title;
    private Button btn_donate_low, btn_donate_high;
    private AdView ad_view;
    private CardView card_ad;

    boolean mIsDonateLow = false;
    boolean mIsDonateHigh = false;
    static final String SKU_DONATE_LOW = "material_design.donate_low";
    static final String SKU_DONATE_HIGH = "material_design.donate_high";
    static final int RC_REQUEST = 10001;
    IabHelper mHelper;
    IabBroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        initView();

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo/+Z/XVhBmpp9eh8z/hj7D9J2AFo/0FAxWEL60yGbpk75jPTXMqG08N6Kl3SbOhrAJGNj3bxfd3SGrOaGo0cxdR4Drkgqo8jiH0iQq2E0V/lfjMDlrqRVAFqhHLd08QoZDI78H7n8jsjh2r+/zP1E4UMphDOsUPBswZFQeXR0bOZshHEyI0Ual0YY9R4i57wznG/Wz42DHQaUjmV3nbKWw8qJ+QDrbm/doVEodOPAJL5XY+yNh6FL4NdljVhsHy59CoMQ0eZf399oMXVM5tUnaNDP9mk5DOjgQ7sGvwboFUWV2ApOiX5sT4aW1yklLNKVXH0Nr6D2lkXWmzAV52lrQIDAQAB";

        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.enableDebugLogging(false);

        // Start setup. This is asynchronous and the specified listener will be called once setup completes.
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    showAd();
                    return;
                }

                if (mHelper == null) return;

                mBroadcastReceiver = new IabBroadcastReceiver(DonateActivity.this);
                IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
                registerReceiver(mBroadcastReceiver, broadcastFilter);

                // IAB is fully set up. Now, let's get an inventory of stuff we own.
                try {
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                } catch (IabHelper.IabAsyncInProgressException e) {
                }
            }
        });
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar_donate);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tv_donate_title = findViewById(R.id.tv_donate_title);
        btn_donate_low = findViewById(R.id.btn_donate_low);
        btn_donate_high = findViewById(R.id.btn_donate_high);

        btn_donate_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDonateLowButton();
            }
        });
        btn_donate_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDonateHighButton();
            }
        });

        card_ad = findViewById(R.id.card_ad);
        ad_view = findViewById(R.id.ad_view);
    }

    public void clickDonateLowButton() {
        // TODO: for security, generate your payload here for verification. See the comments on verifyDeveloperPayload() for more info.
        // Since this is a SAMPLE, we just use an empty string, but on a production app you should carefully generate this. */
        String payloadDonateLow = "";

        try {
            mHelper.launchPurchaseFlow(this, SKU_DONATE_LOW, RC_REQUEST, mPurchaseFinishedListener, payloadDonateLow);
        } catch (Exception e) {
            Snackbar.make(btn_donate_low, R.string.donate_exception, Snackbar.LENGTH_SHORT).show();
        }
    }

    public void clickDonateHighButton() {
        // TODO: for security, generate your payload here for verification. See the comments on verifyDeveloperPayload() for more info.
        // Since this is a SAMPLE, we just use an empty string, but on a production app you should carefully generate this. */
        String payloadDonateHigh = "";

        try {
            mHelper.launchPurchaseFlow(this, SKU_DONATE_HIGH, RC_REQUEST, mPurchaseFinishedListener, payloadDonateHigh);
        } catch (Exception e) {
            Snackbar.make(btn_donate_low, R.string.donate_exception, Snackbar.LENGTH_SHORT).show();
        }
    }

    // Listener that's called when we finish querying the items and subscriptions we own
    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            if (mHelper == null) return;

            if (result.isFailure()) {
                return;
            }

            // Check for items we own. Notice that for each purchase, we check the developer payload
            // to see if it's correct! See verifyDeveloperPayload().

            Purchase donateLowPurchase = inventory.getPurchase(SKU_DONATE_LOW);
            mIsDonateLow = (donateLowPurchase != null && verifyDeveloperPayload(donateLowPurchase));

            Purchase donateHighPurchase = inventory.getPurchase(SKU_DONATE_HIGH);
            mIsDonateHigh = (donateHighPurchase != null && verifyDeveloperPayload(donateHighPurchase));

            updateUi();
        }
    };

    @Override
    public void receivedBroadcast() {
        // Received a broadcast notification that the inventory of items has changed
        try {
            mHelper.queryInventoryAsync(mGotInventoryListener);
        } catch (IabHelper.IabAsyncInProgressException e) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mHelper == null) return;

        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            // not handled, so handle it ourselves (here's where you'd perform any handling of activity results
            // not related to in-app billing...
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    // Callback for when a purchase is finished
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if (mHelper == null) return;

            if (result.isFailure()) {
                return;
            }

            if (!verifyDeveloperPayload(purchase)) {
                return;
            }

            if (purchase.getSku().equals(SKU_DONATE_LOW)) {
                mIsDonateLow = true;
                updateUi();

            } else if (purchase.getSku().equals(SKU_DONATE_HIGH)) {
                mIsDonateHigh = true;
                updateUi();
            }
        }
    };

    boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();
        /*
         * TODO: verify that the developer payload of the purchase is correct. It will be
         * the same one that you sent when initiating the purchase.
         * WARNING: Locally generating a random string when starting a purchase and verifying it here
         * might seem like a good approach, but this will fail in the case where the user purchases an item
         * on one device and then uses your app on a different device, because on the other device
         * you will not have access to the random string you originally generated.
         * So a good developer payload has these characteristics:
         * 1. If two different users purchase an item, the payload is different between them, so that
         *    one user's purchase can't be replayed to another user.
         * 2. The payload must be such that you can verify it even when the app wasn't the one who initiated the purchase flow
         *   (so that items purchased by the user on one device work on other devices owned by the user).
         * Using your own server to store and verify developer payloads across app installations is recommended.
         */
        return true;
    }

    public void updateUi() {
        if (mIsDonateLow) {
            btn_donate_low.setText(R.string.donated);
            btn_donate_low.setEnabled(false);
        }
        if (mIsDonateHigh) {
            btn_donate_high.setText(R.string.donated);
            btn_donate_high.setEnabled(false);
        }
        if (mIsDonateLow || mIsDonateHigh) {
            Animation animation = new AlphaAnimation(0.0f, 1.0f);
            animation.setDuration(500);
            tv_donate_title.setText(R.string.donate_title_after);
            tv_donate_title.startAnimation(animation);

            card_ad.setVisibility(View.GONE);

            updateSharedPreferences();
        } else {
            showAd();
        }
    }

    public void showAd() {
        AdRequest adRequest = new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build();
        ad_view.loadAd(adRequest);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(500);
        card_ad.setVisibility(View.VISIBLE);
        card_ad.startAnimation(animation);
    }

    public void updateSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("app", MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("isDonated", false)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isDonated", true);
            editor.apply();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mBroadcastReceiver != null) {
            unregisterReceiver(mBroadcastReceiver);
        }

        if (mHelper != null) {
            try {
                mHelper.disposeWhenFinished();
            } catch (Exception e) {
            }
            mHelper = null;
        }
    }

}
