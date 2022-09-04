package com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.divyanshu.colorseekbar.ColorSeekBar;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.pageadapters.PageAdapter;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.pageadapters.PageAdapterFilterFree;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.pageadapters.PageAdapterSticker;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.pageadapters.PageAdapterlayout;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.pageadapters.PageAdapterForFreeStyle;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.BitmapStickerIcon;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.DeleteIconEvent;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.FlipHorizontallyEvent;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.HelloIconEvent;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.Sticker;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.StickerView;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.TextSticker;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.ZoomIconEvent;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.yourworks.YourWork;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class FreeStyle extends AppCompatActivity implements  View.OnClickListener{

    //flags
    public static boolean checkEmptyTxtStickerFree=false;
    public static boolean flagForTextSticker = false;
    private boolean doubleBackToExitPressedOnce=false;

    //static
    private static final int SELECT_PICTURE =1 ;
    private static final int PERMISSION_CODE = 2;
    private static final int IMAGE_PICK_CODE =3 ;
    private static final String TAG = "mytag";

    //views
    public static StickerView stickerViewFree;
    public static ImageView freeBackGroundChange;
    RelativeLayout relativeLayout;

    //context
    public static Context context;


    //tabview
    TabLayout tabLayout, tabLayoutForLayout, tabLayoutSticker, tabLayoutText, tabLayoutfilterFree;

    //viewpager
    ViewPager viewPager, viewPagerForLayout, viewPagerSticker, viewPagerText, viewPagerfilterFree;


    //adapters
    PageAdapter pageAdapter;   //create class of page adapter
    PageAdapterlayout pageAdapterLayout;   //create class of page adapter
    PageAdapterSticker pageAdapterSticker;   //create class of page adapter
    PageAdapterForFreeStyle pageAdapterText;   //create class of page adapter
    PageAdapterFilterFree pageAdapterfilterFree;   //create class of page adapter

    //tabitems
    TabItem tabColor,tabGradient,tabImage,layouts,stickersTab,textStickerTab,filterFree;

    //views
    View viewColor, viewBackground, viewSticker, viewText, viewfilterFree;
    HorizontalScrollView scrollView;
    ImageView imgDone, imgCancel, doneLayout, cancelLayout, doneSticker, cancelSticker, doneText, cancelText, doneFilterFree, cancelFilterFree;


    public static TextSticker stickerForTextFree;

    public static int localcolor;
    public static int mDefaultColor;

    //bitmap
    private Bitmap b;


    //moreImg
    public  ImageView moreImg;

    private int iol=0;
    private int l2=0;

    //firebase
    private FirebaseAnalytics mFirebaseAnalytics;
    private InterstitialAd interstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_style);
        getSupportActionBar().hide();
        flagForTextSticker = true;
    /*    if (!AppPurchase.checkPurchases()) {
            callinterstitial();
        }
    */
        //firebase analytics
        mFirebaseAnalyticsStart();
        //intializeViews
        intializeViews();
        //icons
        initializeSpecialIcon();
        loadStickerFreeStyle();
        setAdapters();


        stickerViewFree.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@androidx.annotation.NonNull Sticker sticker) {
                Log.d(TAG, "onStickerAdded");
            }

            @Override
            public void onStickerClicked(@androidx.annotation.NonNull Sticker sticker) {
                //stickerView.removeAllSticker();
                if (sticker instanceof TextSticker) {
                    ((TextSticker) sticker).setTextColor(Color.RED);
                    stickerViewFree.replace(sticker);
                    stickerViewFree.invalidate();

                }
                Log.d(TAG, "onStickerClicked");
            }

            @Override
            public void onStickerDeleted(@androidx.annotation.NonNull Sticker sticker) {
                Log.d(TAG, "onStickerDeleted");
            }

            @Override
            public void onStickerDragFinished(@androidx.annotation.NonNull Sticker sticker) {
                Log.d(TAG, "onStickerDragFinished");


            }

            @Override
            public void onStickerTouchedDown(@androidx.annotation.NonNull Sticker sticker) {
                Log.d(TAG, "onStickerTouchedDown");


            }

            @Override
            public void onStickerZoomFinished(@androidx.annotation.NonNull Sticker sticker) {
                Log.d(TAG, "onStickerZoomFinished");
            }

            @Override
            public void onStickerFlipped(@androidx.annotation.NonNull Sticker sticker) {
                Log.d(TAG, "onStickerFlipped");
            }

            @Override
            public void onStickerDoubleTapped(@NonNull Sticker sticker) {
                Log.d(TAG, "onDoubleTapped: double tap will be with two click");
            }
        });


        //on page change listeners
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPagerForLayout.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutForLayout));
        viewPagerSticker.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutSticker));
        viewPagerText.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutText));
        viewPagerfilterFree.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutfilterFree));

        //on tab selected listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                    moreImg.setVisibility(View.GONE);

                } else if (tab.getPosition() == 2) {
                    moreImg.setVisibility(View.VISIBLE);
                } else {
                    moreImg.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
        tabLayoutForLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {

                } else if (tab.getPosition() == 2) {

                } else {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPagerForLayout.setCurrentItem(tab.getPosition());
            }
        });
        tabLayoutSticker.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {

                } else if (tab.getPosition() == 2) {

                } else {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPagerSticker.setCurrentItem(tab.getPosition());
            }
        });
        tabLayoutText.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {

                } else if (tab.getPosition() == 2) {

                } else {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPagerText.setCurrentItem(tab.getPosition());
            }
        });
        tabLayoutfilterFree.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {

                } else if (tab.getPosition() == 2) {

                } else {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPagerfilterFree.setCurrentItem(tab.getPosition());
            }
        });



    }

    private void setAdapters() {
        //page adapters
        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        pageAdapterLayout = new PageAdapterlayout(getSupportFragmentManager(), tabLayoutForLayout.getTabCount());
        pageAdapterSticker = new PageAdapterSticker(getSupportFragmentManager(), tabLayoutSticker.getTabCount());
        pageAdapterText = new PageAdapterForFreeStyle(getSupportFragmentManager(), tabLayoutText.getTabCount());
        pageAdapterfilterFree = new PageAdapterFilterFree(getSupportFragmentManager(), tabLayoutfilterFree.getTabCount());


        //view pagers
        viewPager.setAdapter(pageAdapter);
        viewPagerForLayout.setAdapter(pageAdapterLayout);
        viewPagerSticker.setAdapter(pageAdapterSticker);
        viewPagerText.setAdapter(pageAdapterText);
        viewPagerfilterFree.setAdapter(pageAdapterfilterFree);

    }

    private void intializeViews() {
        moreImg=findViewById(R.id.addmore);
        freeBackGroundChange = findViewById(R.id.freebackgrounder);



        context = getApplicationContext();
        stickerViewFree = (StickerView) findViewById(R.id.sticker_viewfree);

        //tab layouts
        tabLayout = findViewById(R.id.tablayout);
        tabLayoutForLayout = findViewById(R.id.tablayoutforlayout);
        tabLayoutSticker = findViewById(R.id.tablayoutforsticker);
        tabLayoutText = findViewById(R.id.tablayoutfortext);
        tabLayoutfilterFree = findViewById(R.id.tablayoutforfilterfree);

        //tab items
        tabColor = findViewById(R.id.tabcolor);
        tabGradient = findViewById(R.id.tabgradient);
        tabImage = findViewById(R.id.tabimage);
        layouts = findViewById(R.id.tablayoutforgrid);
        stickersTab = findViewById(R.id.tabsticker);
        textStickerTab = findViewById(R.id.tabtext);
        filterFree = findViewById(R.id.tabfilterfree);


        //view pagers
        viewPager = findViewById(R.id.viewPager);
        viewPagerForLayout = findViewById(R.id.viewPagerforlayout);
        viewPagerSticker = findViewById(R.id.viewPagerforsticker);
        viewPagerText = findViewById(R.id.viewPagerfortext);
        viewPagerfilterFree = findViewById(R.id.viewPagerforfilterfree);


        //view for fragments
        viewColor = findViewById(R.id.forcolor);
        viewBackground = findViewById(R.id.forcollage);
        viewSticker = findViewById(R.id.forsticker);
        viewText = findViewById(R.id.fortextsticker);
        viewfilterFree = findViewById(R.id.forfilterapplyfree);

        scrollView = findViewById(R.id.scrollView);

        // done and cancel button for each view
        imgDone = findViewById(R.id.done);
        imgCancel = findViewById(R.id.cancel);
        doneLayout = findViewById(R.id.donelayout);
        cancelLayout = findViewById(R.id.cancellayout);
        doneSticker = findViewById(R.id.donesticker);
        cancelSticker = findViewById(R.id.cancelsticker);
        doneText = findViewById(R.id.donesticker);
        cancelText = findViewById(R.id.cancelsticker);
        doneFilterFree = findViewById(R.id.donefilterfree);
        cancelFilterFree = findViewById(R.id.cancelfilterfree);



        //

        imgDone.setOnClickListener(this);
        imgCancel.setOnClickListener(this);
        doneLayout.setOnClickListener(this);
        cancelLayout.setOnClickListener(this);
        doneSticker.setOnClickListener(this);
        cancelSticker.setOnClickListener(this);
        doneText.setOnClickListener(this);
        cancelText.setOnClickListener(this);
        doneFilterFree.setOnClickListener(this);
        cancelFilterFree.setOnClickListener(this);
        moreImg.setOnClickListener(this);

        //relative layout for visibility
        relativeLayout = findViewById(R.id.bottom);

    }

    private void initializeSpecialIcon() {
        BitmapStickerIcon deleteIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                R.drawable.sticker_ic_close_white_18dp),
                BitmapStickerIcon.LEFT_TOP);
        deleteIcon.setIconEvent(new DeleteIconEvent());

        BitmapStickerIcon zoomIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                R.drawable.sticker_ic_scale_white_18dp),
                BitmapStickerIcon.RIGHT_BOTOM);
        zoomIcon.setIconEvent(new ZoomIconEvent());

        BitmapStickerIcon flipIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                R.drawable.sticker_ic_flip_white_18dp),
                BitmapStickerIcon.RIGHT_TOP);
        flipIcon.setIconEvent(new FlipHorizontallyEvent());

        BitmapStickerIcon heartIcon =
                new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp),
                        BitmapStickerIcon.LEFT_BOTTOM);
        heartIcon.setIconEvent(new HelloIconEvent());


        stickerViewFree.setIcons(Arrays.asList(deleteIcon, zoomIcon, flipIcon, heartIcon));
        stickerViewFree.setLocked(false);
        stickerViewFree.setConstrained(true);

    }

    private void mFirebaseAnalyticsStart() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "3");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "FreeStyle");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        //firebase analytics
    }

    public void loadStickerFreeStyle() {
        for (int i = 0; i < YourWork.imagesListfree.size(); i++) {

            Drawable yourDrawable;
            try {
                InputStream inputStream = getContentResolver().openInputStream(YourWork.imagesListfree.get(i).uri);
                yourDrawable = Drawable.createFromStream(inputStream, YourWork.imagesListfree.get(i).uri.toString());

                stickerViewFree.addSticker(
                        new TextSticker(context)
                                .setDrawable(yourDrawable)
                                .setText("\n")
                                .setMaxTextSize(14)
                                .resizeText()
                        , Sticker.Position.TOP);
            } catch (FileNotFoundException e) {
                yourDrawable = getResources().getDrawable(R.drawable.haizewang_23);
            }
        }
    }
    public void colorFun(View view) {

        viewColor.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);
    }


    public void textForStickerText(View view) {

        showDialog();
    }
    public void textForFun(View view) {

        viewText.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);
    }
    public void stickerFun(View view) {

        viewSticker.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);
    }
    public void filterForFun(View view) {

        viewfilterFree.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);

    }
    public void showDialog() {

        final int[] colorPosition = new int[1];

        final String[] styles = new String[]{
                "Montserrat-Light.otf",
                "Montserrat-Bold.otf",
                "Etalasi.otf",
                "Oi-Regular.ttf",
                "OswaldHeavy.ttf",
                "MaskedHero.ttf",
                "RodanoItalic.otf"
        };
        LayoutInflater inflater;
        View view;
        inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.textdialog, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        Button yes = view.findViewById(R.id.done);
        Button no = view.findViewById(R.id.cancel);
        Button font = view.findViewById(R.id.font);
        Button btnColors = view.findViewById(R.id.colorbutton);
        EditText etxt = view.findViewById(R.id.edittext);
        ColorSeekBar colorSeekBar = view.findViewById(R.id.colorseekbar);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        stickerForTextFree = new TextSticker(getApplicationContext());

        stickerForTextFree.setTextColor(mDefaultColor);
        font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                final String[] style = new String[]{
                        "Montserrat-Light",
                        "Montserrat-Bold",
                        "Etalasi",
                        "Oi-Regular",
                        "OswaldHeavy",
                        "MaskedHero",
                        "RodanoItalic"

                };
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(FreeStyle.this,
                        android.R.layout.simple_spinner_item, style);
                spinner.setAdapter(adapter);

            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                stickerForTextFree.setText(etxt.getText().toString());
                if (etxt.getText().toString().isEmpty()) {
                    etxt.setError("Please provide text...");
                    etxt.requestFocus();
                } else {
                    stickerForTextFree.setTextColor(localcolor);
                    stickerForTextFree.setTextAlign(Layout.Alignment.ALIGN_CENTER);
                    stickerForTextFree.resizeText();
                    stickerViewFree.addSticker(stickerForTextFree);
                    alertDialog.dismiss();
                    checkEmptyTxtStickerFree=true;
                    DeleteIconEvent.flagdeletefree=false;
                }

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mytag", etxt.getText().toString());
                alertDialog.dismiss();
            }
        });
        btnColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorSeekBar.setVisibility(View.VISIBLE);
            }
        });
        alertDialog.show();
        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int i) {
                localcolor = i;
                etxt.setTextColor(i);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.white));
                colorPosition[0] = i;
                etxt.setTypeface(Typeface.createFromAsset(FreeStyle.this.getAssets(), styles[colorPosition[0]]));
                stickerForTextFree.setTypeface(Typeface.createFromAsset(FreeStyle.this.getAssets(), styles[colorPosition[0]]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    //downlaod after editing
    public void downloadImage(View view) {
        b = ScreenShot.takeScreenShot(stickerViewFree);
        saveToGallery(b);
    }
    public void saveToGallery(Bitmap b) {
        FileOutputStream fileOutputStream = null;
        File sdcard = Environment.getExternalStorageDirectory();
        File directory = new File(sdcard.getAbsolutePath() + "/CollageApp");
        directory.mkdir();

        String filename = String.format("%d.jpg", System.currentTimeMillis());
        File outfile = new File(directory, filename);

        Toast.makeText(this, "Image Saved successfully..", Toast.LENGTH_SHORT).show();

        try {
            fileOutputStream = new FileOutputStream(outfile);
            b.compress(Bitmap.CompressFormat.JPEG
                    , 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(Uri.fromFile(outfile));
            sendBroadcast(intent);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void backToYourWork(View view) {

        if (interstitialAd != null && interstitialAd.isAdLoaded()) {
            interstitialAd.show();
        } else {
            startActivity(new Intent(getApplicationContext(), YourWork.class));
        }
    }
    public static void loadSticker2(Drawable img) {
        stickerViewFree.addSticker(
                new TextSticker(context)
                        .setDrawable(img)
                        .setText("\n")
                        .setMaxTextSize(14)
                        .resizeText()
                , Sticker.Position.TOP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
                Uri selectedImageUri = data.getData();

                if(iol==1){

                    Drawable yourDrawable;
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                        yourDrawable = Drawable.createFromStream(inputStream, selectedImageUri.toString());
                        stickerViewFree.addSticker(
                                new TextSticker(context)
                                        .setDrawable(yourDrawable)
                                        .setText("\n")
                                        .setMaxTextSize(14)
                                        .resizeText()
                                , Sticker.Position.TOP);
                    } catch (FileNotFoundException e) {
                        yourDrawable = getResources().getDrawable(R.drawable.haizewang_23);
                    }
                    iol=0;
                }
                if(l2==1) {
                Drawable yourDrawable;
                try {
                    InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                    yourDrawable = Drawable.createFromStream(inputStream, selectedImageUri.toString() );
                } catch (FileNotFoundException e) {
                    yourDrawable = getResources().getDrawable(R.drawable.gradient_0);
                }
                stickerViewFree.setBackground(yourDrawable);
                l2=0;
            }
        }
        }

    //choose img and pick gallery fun
    private void chooseImage() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_CODE);
            } else {
                pickImageFromGallery();
            }
        } else {

            pickImageFromGallery();
        }


    }
    private void pickImageFromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
        Log.d("mytag", "call gallery");
    }

    public void onclickselectimagefromgallery(View view) {

        iol=1;
        chooseImage();

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(context, "Press back again to exit..", Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
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
    @Override
    protected void onDestroy() {
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.done:
                viewColor.setVisibility(View.GONE);
                break;
            case R.id.cancel:
                viewColor.setVisibility(View.GONE);
                break;
            case R.id.donelayout:
                viewBackground.setVisibility(View.GONE);
                break;
            case R.id.cancellayout:
                viewBackground.setVisibility(View.GONE);
                break;
            case R.id.donesticker:
                viewText.setVisibility(View.GONE);
                break;
            case R.id.cancelsticker:
                viewText.setVisibility(View.GONE);
                break;
            case R.id.donefilterfree:
                viewfilterFree.setVisibility(View.GONE);
                break;
            case R.id.cancelfilterfree:
                viewfilterFree.setVisibility(View.GONE);

                break;
            case R.id.addmore:
                chooseImage();
                l2=1;
                break;

        }
        relativeLayout.setVisibility(View.VISIBLE);

    }


}


