package com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.yourworks.YourWork;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

import static com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.AppPurchase.*;

public class InAppPurchase extends AppCompatActivity implements BillingProcessor.IBillingHandler{


    private static final String TAG = "mytag";
    private InterstitialAd interstitialAd;
    private Animation bottomAnim;
    private ImageView cardView;
    private RadioGroup radioGroup;
    private BillingProcessor bp;
    private TextView  txt;
    private ImageView  subscribe;
    private String value;
    private Typeface face2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_app_purchase);
        getSupportActionBar().hide();

        /*if (!AppPurchase.checkPurchases()) {
            callinterstitial();

        }*/

        init();

        face2 = Typeface.createFromAsset(getAssets(),
                "Montserrat-Bold.otf");
        txt.setTypeface(face2);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (interstitialAd != null && interstitialAd.isAdLoaded()) {

                    interstitialAd.show();
                } else*/ {
                    Log.e(TAG, "Interstitial ad dismissed.");
                    startActivity(new Intent(getApplicationContext(), YourWork.class));
                    finish();
                }

            }
        });

        bp=new BillingProcessor(this, LICENSE_KEY, MERCHANT_ID,this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.week_1:
                     //   bp.subscribe(InAppPurchase.this, PRODUCT_IDW);
                       value =PRODUCT_IDW;
                        break;
                    case R.id.month_2:
                       // bp.subscribe(InAppPurchase.this, PRODUCT_ID2M);
                        value =PRODUCT_ID2M;
                        break;
                    case R.id.month_3:
                        //bp.subscribe(InAppPurchase.this, PRODUCT_ID3M);
                        value =PRODUCT_ID3M;
                        break;
                    case R.id.lifetime:
                       // bp.purchase(InAppPurchase.this, PRODUCT_IDL);
                        value =PRODUCT_IDL;
                        break;
                       default:
                        break;
                }
            }
        });

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mytag",value);
                bp.subscribe(InAppPurchase.this, value);
            }
        });
    }

    private void init() {
        subscribe=findViewById(R.id.sub);
        txt=findViewById(R.id.textfont);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        cardView = findViewById(R.id.continueads);
        cardView.setAnimation(bottomAnim);
        radioGroup=findViewById(R.id.radioGroup);
    }


    void callinterstitial() {
        //ads
        AudienceNetworkAds.initialize(this);
        //interstitialAd = new InterstitialAd(this, "#YOUR_PLACEMENT_ID");
        interstitialAd = new InterstitialAd(this, getResources().getString(R.string.FbInterstitialAd));
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
                startActivity(new Intent(getApplicationContext(), YourWork.class));

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");

            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());
    }

    @Override
    protected void onDestroy() {
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        finish();
        super.onDestroy();
    }

    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        Toast.makeText(this, "initialize ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {

    }

    @Override
    public void onBillingInitialized() {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}