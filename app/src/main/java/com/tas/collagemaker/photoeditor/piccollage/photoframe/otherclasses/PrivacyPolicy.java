package com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeAdViewAttributes;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class PrivacyPolicy extends AppCompatActivity {


    private  TextView txt1, txt3, txt5;
    private RelativeLayout agree;
    private NativeAd nativeAd;
    private TextView loading;
    private  ProgressBar progressBar;
    private Typeface face,face2;
    private SpannableString spannableString;
    SharedPreferences prefs;
    private String url="https://thinktechappstudios.wordpress.com/";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        getSupportActionBar().hide();

        init();

        /*if (!AppPurchase.checkPurchases()) {
            nativeAdFun();
        }*/

        face = Typeface.createFromAsset(getAssets(),
                "Montserrat-Light.otf");
        face2 = Typeface.createFromAsset(getAssets(),
                "Montserrat-Bold.otf");

        txt3.setTypeface(face);
        txt5.setTypeface(face);
        txt1.setTypeface(face2);



        spannableString = new SpannableString("terms and conditions");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@androidx.annotation.NonNull View widget) {

                try {

                    try {
                        Uri uri = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorredlight));
            }
        };
        spannableString.setSpan(clickableSpan, 0, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt5.setText(spannableString);
        txt5.setMovementMethod(LinkMovementMethod.getInstance());

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prefs = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                prefs.edit().putBoolean("isAgree", true).apply();
                startActivity(new Intent(getApplicationContext(), InAppPurchase.class));
                finish();
            }
        });

    }

    private void init() {
        progressBar = findViewById(R.id.prograssbar);
        loading = findViewById(R.id.loading);
        txt1 = findViewById(R.id.txt1);
        txt3 = findViewById(R.id.txt3);
        txt5 = findViewById(R.id.txt5);
        agree = findViewById(R.id.pp4);
    }

    public void nativeAdFun() {

        AudienceNetworkAds.initialize(this);
        nativeAd = new NativeAd(this, getResources().getString(R.string.FbNativeAd));
        //nativeAd = new NativeAd(this, "#YOUR_PLACEMENT_ID");
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {

                NativeAdViewAttributes viewAttributes = new NativeAdViewAttributes()
                        .setBackgroundColor(getResources().getColor(R.color.colorpurpleshade))
                        .setTitleTextColor(Color.WHITE)
                        .setDescriptionTextColor(Color.LTGRAY)
                        .setButtonColor(getResources().getColor(R.color.colorredlight))
                        .setButtonTextColor(Color.WHITE);


                View adView = NativeAdView.render(PrivacyPolicy.this, nativeAd, viewAttributes);
                LinearLayout nativeAdContainer = (LinearLayout) findViewById(R.id.native_ad_container);
                nativeAdContainer.addView(adView, new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
                loading.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

            @Override
            public void onMediaDownloaded(Ad ad) {

            }
        };
        nativeAd.loadAd(
                nativeAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .withMediaCacheFlag(NativeAdBase.MediaCacheFlag.ALL)
                        .build());

    }
}