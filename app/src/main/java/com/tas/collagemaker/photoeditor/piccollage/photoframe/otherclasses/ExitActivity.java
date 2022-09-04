package com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.yourworks.YourWork;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeAdViewAttributes;


import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ExitActivity extends AppCompatActivity {

    ImageView yes,no;
    private NativeAd nativeAd;
    TextView loading;
    ProgressBar progressBar;
    RatingBar ratingBar;
    ReviewManager manager;

    private InterstitialAd interstitialAd;
    public static final String TAG="mytag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        getSupportActionBar().hide();

/*
       if(!AppPurchase.checkPurchases()){
        nativeAds();
       }
*/
        manager = ReviewManagerFactory.create(this);

       initializeView();


        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
              if(rating<3.0f) {
              openGmail();
              }else{
                  review();
              }
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), YourWork.class));
                finish();
            }
        });

    }

    private void initializeView() {
        progressBar=findViewById(R.id.prograssbar);
        loading=findViewById(R.id.loading);
        ratingBar = (RatingBar) findViewById(R.id.simpleRatingBar);
        yes=findViewById(R.id.yes);
        no=findViewById(R.id.no);
    }

    private void openGmail() {
        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
        Uri data =
        Uri.parse("mailto:?subject=" + "Feedback" + "&body=" + "Suggest us what went wrong and we'll work on it." + "&to="
                + getString(R.string.email));
        mailIntent.setData(data);
        startActivity(Intent.createChooser(mailIntent, "Send mail..."));
    }
    private void review() {
        manager.requestReviewFlow().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ReviewInfo reviewInfo = task.getResult();
                manager.launchReviewFlow(this, reviewInfo
                ).addOnFailureListener(e -> {
                    Toast.makeText(this, "Rating Failed", Toast.LENGTH_SHORT).show();
                    Log.d("TAGreview", "review1: " + e.toString());
                }).addOnCompleteListener(task1 ->
                        Toast.makeText(this, "Thanks for the feedback!", Toast.LENGTH_SHORT).show()
                );
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Rating Failed", Toast.LENGTH_SHORT).show();
            Log.d("TAGreview", "review3: " + e.toString());
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), YourWork.class));
        finish();

    }
    public void nativeAds(){

        AudienceNetworkAds.initialize(this);
        nativeAd = new NativeAd(this, getResources().getString(R.string.FbNativeAd));
        //nativeAd = new NativeAd(this, "#YOUR_PLACEMENT_ID");
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Set the Native Ad attributes
                NativeAdViewAttributes viewAttributes = new NativeAdViewAttributes()
                        .setBackgroundColor(getResources().getColor(R.color.colorpurpleshade))
                        .setTitleTextColor(Color.WHITE)
                        .setDescriptionTextColor(Color.LTGRAY)
                        .setButtonColor(getResources().getColor(R.color.colorredlight))
                        .setButtonTextColor(Color.WHITE);

                View adView = NativeAdView.render(ExitActivity.this, nativeAd,viewAttributes);
                LinearLayout nativeAdContainer = (LinearLayout) findViewById(R.id.native_ad_container);
                nativeAdContainer.addView(adView, new LinearLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT));
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