
package com.tas.collagemaker.photoeditor.piccollage.photoframe.yourworks;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.ExitActivity;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.FreeStyle;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.MainCanvas;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.vlk.multimager.activities.GalleryActivity;
import com.vlk.multimager.utils.Constants;
import com.vlk.multimager.utils.Image;
import com.vlk.multimager.utils.Params;


import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class YourWork extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener{



    //one signal  test
   // private static final String ONESIGNAL_APP_ID = "37303aab-2251-4063-b591-b327973cda11";
    //one signal original
    private static final String ONESIGNAL_APP_ID = "0e5d6760-416d-4f0b-b672-3d8cfd41c0cd";
    private static final int UPDATE_REQUEST_CODE = 12;


    //firebase 
    private FirebaseAnalytics mFirebaseAnalytics;
   
    //ads
    private InterstitialAd interstitialAd;
    private InterstitialAd interstitialAdMain;
    private InterstitialAd interstitialAdFree;
    private NativeBannerAd mNativeBannerAd;

    //tag
    private final String TAG = YourWork.class.getSimpleName();
    
    //views
    public RecyclerView recyclerView;
    TextView loadText;
    TextView txtNoYet;
    ImageView addImages, freeStyleAdd;

    
    //adapters
    private YourWorkAdapter recyclerViewAdapter;
    
    
    
    //flags
    boolean flagFreeStyle = false, flagAddImage = false;
   
   //list
    public static ArrayList<Image> imagesList;
    public static ArrayList<Image> imagesListfree;
    public static ArrayList<YourWorkModel> listOfGallery=new ArrayList<YourWorkModel>();
    
    //files
    private File[] listFile;
    File file,folder;

    //layouts
    SwipeRefreshLayout swipeRefreshLayout;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourwork);
        getSupportActionBar().hide();


/*
        if (!AppPurchase.checkPurchases()) {
            callinterstitial();
            callinterstitialMain();
            callinterstitialFree();
            bannerNative();
        }
*/

/*
        callInAppUpdate();
*/

        //one signal
        // Enable verbose OneSignal logging to debug issues if needed.
        // OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        // OneSignal Initialization
        //OneSignal.initWithContext(this);
        //OneSignal.setAppId(ONESIGNAL_APP_ID);
        //one signal


        initializeViews();

        mFirebaseAnalyticsStart();

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {


                if (task.isSuccessful()) {
                    String token = task.getResult().getToken();
                    //  Toast.makeText(Yourwork.this, "jjj"+token, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "on Complete Token:" + token);

                } else {
                    Log.d("TAG", task.getException().toString());
                    //Toast.makeText(YourWork.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //firebase analytics and fcm


        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "Error! No SDCARD Found!",
                    Toast.LENGTH_LONG).show();
        } else {
            // Locate the image folder in your SD Card
            file = new File(Environment.getExternalStorageDirectory()
                    .getPath() + "/CollageMaker");

            folder = new File(Environment.getExternalStorageDirectory().toString() + "/CollageMaker/");
            Log.d("check", "directory found");
        }

        if (folder.exists())
        {
            listFile = folder.listFiles();
            Log.d("check","folder is === "+listFile.length);

        }

        if (file.isDirectory()) {

            listFile = file.listFiles();
            Log.d("check","listfile is === "+listFile.length);

            if (listFile == null) {

            } else {


                for (int i = 0; i < listFile.length; i++) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(listFile[i].getAbsolutePath());
                    listOfGallery.add(new YourWorkModel(myBitmap, listFile[i].getName(), file.listFiles()[i].getAbsolutePath()));
                }
            }
        }

        setUpRecyclerView();

    }

    private void initializeViews() {
        swipeRefreshLayout = findViewById(R.id.swipelayout);
        loadText = findViewById(R.id.loadtxt);
        addImages = findViewById(R.id.addimage);
        freeStyleAdd = findViewById(R.id.addfreestyle);
        txtNoYet = findViewById(R.id.nowork);
        recyclerView = (RecyclerView) findViewById(R.id.gridrecyclerview);


        addImages.setOnClickListener(this);
        freeStyleAdd.setOnClickListener(this);

    }

    private void mFirebaseAnalyticsStart() {
        //firebase analytics and fcm
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "yourwork");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

    }

    private void setUpRecyclerView() {
        if (listOfGallery.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            txtNoYet.setVisibility(View.VISIBLE);
            Log.d("check",listOfGallery.size()+"  size");
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            txtNoYet.setVisibility(View.GONE);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
            recyclerViewAdapter = new YourWorkAdapter(getApplicationContext(), listOfGallery);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }
   //overide
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case Constants.TYPE_MULTI_CAPTURE:
                imagesList = intent.getParcelableArrayListExtra(Constants.KEY_BUNDLE_LIST);
                imagesListfree = intent.getParcelableArrayListExtra(Constants.KEY_BUNDLE_LIST);
                break;
            case Constants.TYPE_MULTI_PICKER:
                imagesList = intent.getParcelableArrayListExtra(Constants.KEY_BUNDLE_LIST);
                imagesListfree = intent.getParcelableArrayListExtra(Constants.KEY_BUNDLE_LIST);
                break;
        }

        if (flagAddImage) {
            flagAddImage = false;

            if (interstitialAdMain != null && interstitialAdMain.isAdLoaded()) {
                interstitialAdMain.show();
            } else {

                startActivity(new Intent(getApplicationContext(), MainCanvas.class));
            }

        } else if (flagFreeStyle) {
            flagFreeStyle = false;
            if (interstitialAdFree != null && interstitialAdFree.isAdLoaded()) {
                interstitialAdFree.show();
            } else {

                startActivity(new Intent(getApplicationContext(), FreeStyle.class));
            }

        }

    }
    @Override
    public void onBackPressed() {
        if (interstitialAd != null && interstitialAd.isAdLoaded()) {
            interstitialAd.show();
        } else {
            startActivity(new Intent(getApplicationContext(), ExitActivity.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    void callinterstitial() {
        //ads
        AudienceNetworkAds.initialize(this);
        //interstitialAd = new InterstitialAd(this, "#YOUR_PLACEMENT_ID");
         interstitialAd = new InterstitialAd(this, getResources().getString(R.string.FbInterstitialAd));
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener1 = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
                startActivity(new Intent(getApplicationContext(), ExitActivity.class));


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
                        .withAdListener(interstitialAdListener1)
                        .build());


        //

    }
    void callinterstitialMain() {
        //ads
        AudienceNetworkAds.initialize(this);
        //interstitialAd = new InterstitialAd(this, "#YOUR_PLACEMENT_ID");
        interstitialAdMain = new InterstitialAd(this, getResources().getString(R.string.FbInterstitialAd));
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener2 = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
                startActivity(new Intent(getApplicationContext(),MainCanvas.class));


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
        interstitialAdMain.loadAd(
                interstitialAdMain.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener2)
                        .build());

    }
    void callinterstitialFree() {
        //ads
        AudienceNetworkAds.initialize(this);
        //interstitialAd = new InterstitialAd(this, "#YOUR_PLACEMENT_ID");
        interstitialAdFree = new InterstitialAd(this, getResources().getString(R.string.FbInterstitialAd));
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener3 = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
                startActivity(new Intent(getApplicationContext(),FreeStyle.class));


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
        interstitialAdFree.loadAd(
                interstitialAdFree.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener3)
                        .build());

    }
    //for swipelayout
    private void setUpRecyclerViewswipelayout() {
        listOfGallery.clear();
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "Error! No SDCARD Found!",
                    Toast.LENGTH_LONG).show();
        } else {
            // Locate the image folder in your SD Card
            file = new File(Environment.getExternalStorageDirectory()
                    .getPath() + "/CollageApp");
        }
        if (file.isDirectory()) {
            listFile = file.listFiles();
            if (listFile == null) {
            } else {
                for (int i = 0; i < listFile.length; i++) {

                    Bitmap myBitmap = BitmapFactory.decodeFile(listFile[i].getAbsolutePath());
//                    listOfGallery.add(new YourworkModel(myBitmap));
                    listOfGallery.add(new YourWorkModel(myBitmap, listFile[i].getName(), file.listFiles()[i].getAbsolutePath()));


                }
            }
        }

        if (listOfGallery.size() == 0) {

            recyclerView.setVisibility(View.GONE);
            txtNoYet.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            txtNoYet.setVisibility(View.GONE);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
            recyclerViewAdapter = new YourWorkAdapter(getApplicationContext(), listOfGallery);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }
    @Override
    protected void onDestroy() {
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        if (interstitialAdMain != null) {
            interstitialAdMain.destroy();
        }
        if (interstitialAdFree != null) {
            interstitialAdFree.destroy();
        }

        super.onDestroy();
    }
    public void callInAppUpdate() {
        try {
            AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(YourWork.this);
            com.google.android.play.core.tasks.Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
            appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    try {
                        appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, YourWork.this, UPDATE_REQUEST_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception r) {
        }
    }
    public  void bannerNative(){
        mNativeBannerAd = new NativeBannerAd(this, getResources().getString(R.string.FbBannerAd));
        //mNativeBannerAd = new NativeBannerAd(this, "#YOUR_PLACEMENT_ID");
        NativeAdListener nativeAdListener = new NativeAdListener() {

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {

                NativeAdViewAttributes viewAttributes = new NativeAdViewAttributes()
                        .setBackgroundColor(getResources().getColor(R.color.colorbrownshade))
                        .setTitleTextColor(Color.WHITE)
                        .setDescriptionTextColor(Color.LTGRAY)
                        .setButtonColor(getResources().getColor(R.color.colorredlight))
                        .setButtonTextColor(Color.WHITE);

                View adView = NativeBannerAdView.render(YourWork.this, mNativeBannerAd, NativeBannerAdView.Type.HEIGHT_50, viewAttributes);
                LinearLayout nativeBannerAdContainer = (LinearLayout) findViewById(R.id.banner_container);
                nativeBannerAdContainer.addView(adView);
                loadText.setVisibility(View.GONE);

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
        mNativeBannerAd.loadAd(
                mNativeBannerAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());



    }


    @Override
    public void onRefresh() {
        try {
            swipeRefreshLayout.setRefreshing(true);
            setUpRecyclerViewswipelayout();
            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getApplicationContext(), "Refreshed!", Toast.LENGTH_SHORT).show();
                }
            }, 2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.addimage:
                Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                Params params = new Params();

                params.setCaptureLimit(8);
                params.setPickerLimit(8);
                params.setToolbarColor(getColor(R.color.colorbrownshade));
                params.setActionButtonColor(getColor(R.color.colorbrownshade));

                intent.putExtra(Constants.KEY_PARAMS, params);
                startActivityForResult(intent, Constants.TYPE_MULTI_PICKER);

                flagAddImage = true;

                break;
            case R.id.addfreestyle:
                Intent intentTwo = new Intent(getApplicationContext(), GalleryActivity.class);
                Params paramsTwo = new Params();
                paramsTwo.setCaptureLimit(8);
                paramsTwo.setPickerLimit(8);

                paramsTwo.setToolbarColor(getColor(R.color.colorbrownshade));
                paramsTwo.setActionButtonColor(getColor(R.color.colorbrownshade));

                intentTwo.putExtra(Constants.KEY_PARAMS, paramsTwo);
                startActivityForResult(intentTwo, Constants.TYPE_MULTI_PICKER);
                flagFreeStyle = true;

                break;

        }
    }
}