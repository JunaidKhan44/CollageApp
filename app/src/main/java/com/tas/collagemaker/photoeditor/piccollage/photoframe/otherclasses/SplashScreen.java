package com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;

public class SplashScreen extends AppCompatActivity {

    private TextView textView;
    private Animation topAnim, bottomAnim;
    private ImageView imgSplash;
    private InterstitialAd interstitialAd;
    private final String TAG = SplashScreen.class.getSimpleName();
    private Handler  handler1;
    private CardView  cardView;
    private Typeface face;
    SharedPreferences prefs;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        try {
            init();

            //AppPurchase.runfirst(SplashScreen.this);


            /*if (!AppPurchase.checkpurchases()) {
               callinterstitial();
            }*/


            face = Typeface.createFromAsset(getAssets(),
                    "Montserrat-Light.otf");
            textView.setTypeface(face);
            imgSplash.setAnimation(topAnim);
            textView.setAnimation(bottomAnim);


            handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {

                    progressBar.setVisibility(View.INVISIBLE);
                    cardView.setVisibility(View.VISIBLE);

                }
            },6000);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  /*  if (interstitialAd != null && interstitialAd.isAdLoaded()) {
                        interstitialAd.show();
                    } else*/ {
                        Log.e(TAG, "Interstitial ad dismissed.");

                        if(prefs.getBoolean("isAgree", false)){
                            startActivity(new Intent(getApplicationContext(), InAppPurchase.class));
                            finish();
                        }else{
                        startActivity(new Intent(getApplicationContext(), PrivacyPolicy.class));
                        finish();
                        }
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public  void callinterstitial(){

        //ads
        AudienceNetworkAds.initialize(this);
        // interstitialAd = new InterstitialAd(this, "#YOUR_PLACEMENT_ID");
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
                Intent it = new Intent(SplashScreen.this, PrivacyPolicy.class);
                startActivity(it);
                finish();

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
                // Show the ad
                //  interstitialAd.show();
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
        super.onDestroy();
    }

    public void init(){
        cardView=findViewById(R.id.card);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        imgSplash = findViewById(R.id.splash);
        textView = findViewById(R.id.appnametxt);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        progressBar=findViewById(R.id.prograssbar);

    }

}