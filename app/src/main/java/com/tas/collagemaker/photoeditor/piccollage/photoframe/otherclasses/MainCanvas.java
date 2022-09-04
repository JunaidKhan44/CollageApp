package com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.tas.collagemaker.photoeditor.piccollage.photoframe.pageadapters.PageAdapterFilter;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.pageadapters.PageAdapterSticker;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.pageadapters.PageAdapterText;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.pageadapters.PageAdapterlayout;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.BitmapStickerIcon;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.DeleteIconEvent;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.DrawableSticker;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.FlipHorizontallyEvent;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.HelloIconEvent;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.Sticker;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.StickerView;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.TextSticker;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.ZoomIconEvent;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.yourworks.YourWork;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.vlk.multimager.utils.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MainCanvas extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    
    //flags
    public boolean doubleBackToExitPressedOnce = false;
    
    //static section
    public static boolean checkEmptyTxtSticker = false;
    public static boolean flagTextSticker = false;
    public static boolean flagForBack = false;
    public static TextSticker stickerForText;
    public static ImageView imageBackGrounder;
    public static int localColor;
    private static int mDefaultColor;
    private static final String TAG = MainCanvas.class.getSimpleName();
    
    
    //Context
    private static Context context;

    //Constant
    private static final int IMAGE_PICK_CODE = 2;
    private static final int PERMISSION_CODE = 1;

    
    //list
    public static ArrayList<Image> forChangeImgList = new ArrayList<Image>();
    
    //tablayout
    TabLayout tabLayout, tabLayoutForLayout, tabLayoutSticker, tabLayoutText, tabLayoutFilter;
    //Viewpager
    ViewPager viewPager, viewPagerForLayout, viewPagerSticker, viewPagerText, viewPagerFilter;
    //Adapters
    PageAdapter pageAdapter;   //create class of page adapter
    PageAdapterlayout pageAdapterLayout;   //create class of page adapter
    PageAdapterSticker pageAdapterSticker;   //create class of page adapter
    PageAdapterText pageAdapterText;   //create class of page adapter
    PageAdapterFilter pageAdapterFilter;   //create class of page adapter
    
    //tabitems
    TabItem tabColor,tabImage,layouts,stickers,textSticker,filter,tabGradient;
    
    //Views
    View viewColor, viewBackGround, viewSticker, viewText, viewfilter;
    HorizontalScrollView scrollView;
    ImageView imgDone, imgCancel, doneLayout, cancelLayout, doneSticker, cancelSticker, doneText, cancelText, doneFilter, cancelFilter;
    public static StickerView stickerView;
    public ImageView imgAddMore;
    private TextSticker sticker;
    
    
    //layouts
    RelativeLayout relativeLayout;
    
    // uri
    private Uri uri;
    
    //others
    public int i, i1, i2, i21, i22, i23, i24, i41, i42, i43, i61, i62, i63, i64, i65, i66;
    public int i51, i52, i53, i54, i55;
    public int i71, i72, i73, i74, i75, i76, i77;
    public int i81, i82, i83, i84, i85, i86, i87, i88;
    private int pickFrag = 0;

    //other views
    public static View v1, v2, v3, v4, v5, v6, v7, v8;
    
    //all images declare
    public static PhotoView zimg1, zimg2;
    public static PhotoView zimg21, zimg22, zimg23, zimg24;
    public static PhotoView img61, img62, img63, img64, img65, img66, img31, img32, img33;
    public static PhotoView img51, img52, img53, img54, img55;
    public static PhotoView singleimg;
    public static PhotoView img71, img72, img73, img74, img75, img76, img77;
    public static PhotoView img81, img82, img83, img84, img85, img86, img87, img88;
    
    //bitmaps
    private Bitmap b;
    
    //prograssbar
    public ProgressBar progressBar;

    //Special icon
    BitmapStickerIcon deleteIcon;
    BitmapStickerIcon zoomIcon;
    BitmapStickerIcon flipIcon;
    BitmapStickerIcon heartIcon;

    //
    public Animation animation;
    Sprite doubleBounce = new CubeGrid();

    //firebase
    private FirebaseAnalytics mFirebaseAnalytics;
    private InterstitialAd interstitialAd;

    @SuppressLint("WrongViewCast")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_canvas);
        getSupportActionBar().hide();

        /*if (!AppPurchase.checkPurchases()) {
            callInterstitial();
        }*/

        mFirebaseAnalyticsFun();

        initializeViews();

        flagForBack = true;

        progressBar.setIndeterminateDrawable(doubleBounce);

        gettingImg();

        initializeStickerSetting();

        //page adapters
        inflatePageAdapters();
        //sticker listener
        


        //on page change listeners
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPagerForLayout.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutForLayout));
        viewPagerSticker.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutSticker));
        viewPagerText.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutText));
        viewPagerFilter.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutFilter));

        //on tab selected listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {

                    imgAddMore.setVisibility(View.GONE);

                } else if (tab.getPosition() == 2) {

                    imgAddMore.setVisibility(View.VISIBLE);
                } else {
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
        tabLayoutFilter.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
                viewPagerFilter.setCurrentItem(tab.getPosition());
            }
        });

    }

    private void inflatePageAdapters() {
        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        pageAdapterLayout = new PageAdapterlayout(getSupportFragmentManager(), tabLayoutForLayout.getTabCount());
        pageAdapterSticker = new PageAdapterSticker(getSupportFragmentManager(), tabLayoutSticker.getTabCount());
        pageAdapterText = new PageAdapterText(getSupportFragmentManager(), tabLayoutText.getTabCount());
        pageAdapterFilter = new PageAdapterFilter(getSupportFragmentManager(), tabLayoutFilter.getTabCount());


        //view pagers
        viewPager.setAdapter(pageAdapter);
        viewPagerForLayout.setAdapter(pageAdapterLayout);
        viewPagerSticker.setAdapter(pageAdapterSticker);
        viewPagerText.setAdapter(pageAdapterText);
        viewPagerFilter.setAdapter(pageAdapterFilter);

    }

    private void initializeStickerSetting() {

        //initialize of sticker view icons and some sticker view properties

        deleteIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                R.drawable.sticker_ic_close_white_18dp),
                BitmapStickerIcon.LEFT_TOP);
        deleteIcon.setIconEvent(new DeleteIconEvent());


        zoomIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                R.drawable.sticker_ic_scale_white_18dp),
                BitmapStickerIcon.RIGHT_BOTOM);
        zoomIcon.setIconEvent(new ZoomIconEvent());


        flipIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                R.drawable.sticker_ic_flip_white_18dp),
                BitmapStickerIcon.RIGHT_TOP);
        flipIcon.setIconEvent(new FlipHorizontallyEvent());


        heartIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp),
                BitmapStickerIcon.LEFT_BOTTOM);
        heartIcon.setIconEvent(new HelloIconEvent());
        stickerView.setIcons(Arrays.asList(deleteIcon, zoomIcon, flipIcon, heartIcon));
        stickerView.setBackgroundColor(Color.WHITE);
        stickerView.setLocked(false);
        stickerView.setConstrained(true);
        sticker = new TextSticker(this);
        sticker.setDrawable(ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.sticker_transparent_background));
        sticker.setText("Hello, world!");
        sticker.setTextColor(Color.BLACK);
        sticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        sticker.resizeText();

    }

    private void initializeViews() {

        context = getApplicationContext();

        singleimg = findViewById(R.id.singleimg1);
        zimg1 = findViewById(R.id.zimage1);
        zimg2 = findViewById(R.id.zimage2);
        zimg21 = findViewById(R.id.zimage21);
        zimg22 = findViewById(R.id.zimage22);
        zimg23 = findViewById(R.id.zimage23);
        zimg24 = findViewById(R.id.zimage24);
        img31 = findViewById(R.id.image41);
        img32 = findViewById(R.id.image42);
        img33 = findViewById(R.id.image43);
        img51 = findViewById(R.id.five1);
        img52 = findViewById(R.id.five2);
        img53 = findViewById(R.id.five3);
        img54 = findViewById(R.id.five4);
        img55 = findViewById(R.id.five5);
        img61 = findViewById(R.id.first);
        img62 = findViewById(R.id.second);
        img63 = findViewById(R.id.third);
        img64 = findViewById(R.id.fourth);
        img65 = findViewById(R.id.fith);
        img66 = findViewById(R.id.sixth);
        img71 = findViewById(R.id.seventh1);
        img72 = findViewById(R.id.seventh2);
        img73 = findViewById(R.id.seventh3);
        img74 = findViewById(R.id.seventh4);
        img75 = findViewById(R.id.seventh5);
        img76 = findViewById(R.id.seventh6);
        img77 = findViewById(R.id.seventh7);
        img81 = findViewById(R.id.eight1);
        img82 = findViewById(R.id.eight2);
        img83 = findViewById(R.id.eight3);
        img84 = findViewById(R.id.eight4);
        img85 = findViewById(R.id.eight5);
        img86 = findViewById(R.id.eight6);
        img87 = findViewById(R.id.eight7);
        img88 = findViewById(R.id.eight8);

        /*set listener*/
        singleimg.setOnLongClickListener(this);
        zimg1.setOnLongClickListener(this);
        zimg2.setOnLongClickListener(this);
        zimg21.setOnLongClickListener(this);
        zimg22.setOnLongClickListener(this);
        zimg23.setOnLongClickListener(this);
        zimg24.setOnLongClickListener(this);
        img31.setOnLongClickListener(this);
        img32.setOnLongClickListener(this);
        img33.setOnLongClickListener(this);
        img51.setOnLongClickListener(this);
        img52.setOnLongClickListener(this);
        img53.setOnLongClickListener(this);
        img54.setOnLongClickListener(this);
        img55.setOnLongClickListener(this);
        img61.setOnLongClickListener(this);
        img62.setOnLongClickListener(this);
        img63.setOnLongClickListener(this);
        img64.setOnLongClickListener(this);
        img65.setOnLongClickListener(this);
        img66.setOnLongClickListener(this);
        img71.setOnLongClickListener(this);
        img72.setOnLongClickListener(this);
        img73.setOnLongClickListener(this);
        img74.setOnLongClickListener(this);
        img75.setOnLongClickListener(this);
        img76.setOnLongClickListener(this);
        img77.setOnLongClickListener(this);
        img81.setOnLongClickListener(this);
        img82.setOnLongClickListener(this);
        img83.setOnLongClickListener(this);
        img84.setOnLongClickListener(this);
        img85.setOnLongClickListener(this);
        img86.setOnLongClickListener(this);
        img87.setOnLongClickListener(this);
        img88.setOnLongClickListener(this);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_effect);
        mDefaultColor = ContextCompat.getColor(MainCanvas.this, R.color.Aqua);
        imageBackGrounder = findViewById(R.id.backgrounder);
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
       
        v1 = (View) findViewById(R.id.include1);
        v2 = (View) findViewById(R.id.include2);
        v3 = (View) findViewById(R.id.include3);
        v4 = (View) findViewById(R.id.include4);
        v5 = (View) findViewById(R.id.include5);
        v6 = (View) findViewById(R.id.include6);
        v7 = (View) findViewById(R.id.include7);
        v8 = (View) findViewById(R.id.include8);
       
        stickerView = (StickerView) findViewById(R.id.sticker_view);

        //tab layouts
        tabLayout = findViewById(R.id.tablayout);
        tabLayoutForLayout = findViewById(R.id.tablayoutforlayout);
        tabLayoutSticker = findViewById(R.id.tablayoutforsticker);
        tabLayoutText = findViewById(R.id.tablayoutfortext);
        tabLayoutFilter = findViewById(R.id.tablayoutforfilter);

        //tab items
        tabColor = findViewById(R.id.tabcolor);
        tabGradient = findViewById(R.id.tabgradient);
        tabImage = findViewById(R.id.tabimage);
        layouts = findViewById(R.id.tablayoutforgrid);
        stickers = findViewById(R.id.tabsticker);
        textSticker = findViewById(R.id.tabtext);
        filter = findViewById(R.id.tabfilter);


        //view pagers
        viewPager = findViewById(R.id.viewPager);
        viewPagerForLayout = findViewById(R.id.viewPagerforlayout);
        viewPagerSticker = findViewById(R.id.viewPagerforsticker);
        viewPagerText = findViewById(R.id.viewPagerfortext);
        viewPagerFilter = findViewById(R.id.viewPagerforfilter);


        //view for fragments
        viewColor = findViewById(R.id.forcolor);
        viewBackGround = findViewById(R.id.forcollage);
        viewSticker = findViewById(R.id.forsticker);
        viewText = findViewById(R.id.fortextsticker);
        viewfilter = findViewById(R.id.forfilterapply);

        scrollView = findViewById(R.id.scrollView);

        // done and cancel button for each view
        imgAddMore = findViewById(R.id.addmore);
        imgDone = findViewById(R.id.done);
        imgCancel = findViewById(R.id.cancel);
        doneLayout = findViewById(R.id.donelayout);
        cancelLayout = findViewById(R.id.cancellayout);
        doneSticker = findViewById(R.id.donesticker);
        cancelSticker = findViewById(R.id.cancelsticker);
        doneText = findViewById(R.id.donetext);
        cancelText = findViewById(R.id.canceltext);
        doneFilter = findViewById(R.id.donefilter);
        cancelFilter = findViewById(R.id.cancelfilter);

        //relative layout for visibility
        relativeLayout = findViewById(R.id.bottom);

        /*other*/
        imgAddMore.setOnClickListener(this);
        imgDone.setOnClickListener(this);
        imgCancel.setOnClickListener(this);
        doneLayout.setOnClickListener(this);
        cancelLayout.setOnClickListener(this);
        doneSticker.setOnClickListener(this);
        cancelSticker.setOnClickListener(this);
        doneText.setOnClickListener(this);
        cancelText.setOnClickListener(this);
        doneFilter.setOnClickListener(this);
        cancelFilter.setOnClickListener(this);


    }

    private void mFirebaseAnalyticsFun() {
        //firebase analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "2");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "MainCanvas");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }

    public void colorfun(View view) {

        viewColor.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);


    }

    public void backgroundfun(View view) {

        viewBackGround.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);
    }

    public void stickerfun(View view) {

        viewSticker.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);
    }

    public void textforfun(View view) {

        viewText.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);
        //  showDialog();
    }

    public void filterforfun(View view) {

        viewfilter.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);

    }

    //  getimage function
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void gettingImg() {

        if (!(YourWork.imagesList.size() == 0)) {    //homescreen replace with YourWork
            int size = YourWork.imagesList.size();
            if (size == 1) {
                Log.d("mytag", "one image called");
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                v4.setVisibility(View.GONE);
                v5.setVisibility(View.GONE);
                v6.setVisibility(View.GONE);
                v7.setVisibility(View.GONE);
                singleimg.setImageURI(YourWork.imagesList.get(0).uri);
                forChangeImgList.add(YourWork.imagesList.get(0));


            } else if (size == 2) {
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.VISIBLE);
                v3.setVisibility(View.GONE);
                v4.setVisibility(View.GONE);
                v5.setVisibility(View.GONE);
                v6.setVisibility(View.GONE);
                v7.setVisibility(View.GONE);
                zimg1.setImageURI(YourWork.imagesList.get(0).uri);
                zimg2.setImageURI(YourWork.imagesList.get(1).uri);
                forChangeImgList.add(YourWork.imagesList.get(0));
                forChangeImgList.add(YourWork.imagesList.get(1));
            } else if (size == 3) {
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.VISIBLE);
                v4.setVisibility(View.GONE);
                v5.setVisibility(View.GONE);
                v6.setVisibility(View.GONE);
                v7.setVisibility(View.GONE);
                img31.setImageURI(YourWork.imagesList.get(0).uri);
                img32.setImageURI(YourWork.imagesList.get(1).uri);
                img33.setImageURI(YourWork.imagesList.get(2).uri);
                forChangeImgList.add(YourWork.imagesList.get(0));
                forChangeImgList.add(YourWork.imagesList.get(1));
                forChangeImgList.add(YourWork.imagesList.get(2));


            } else if (size == 4) {
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                v4.setVisibility(View.VISIBLE);
                v5.setVisibility(View.GONE);
                v6.setVisibility(View.GONE);
                v7.setVisibility(View.GONE);
                zimg21.setImageURI(YourWork.imagesList.get(0).uri);
                zimg22.setImageURI(YourWork.imagesList.get(1).uri);
                zimg23.setImageURI(YourWork.imagesList.get(2).uri);
                zimg24.setImageURI(YourWork.imagesList.get(3).uri);

                forChangeImgList.add(YourWork.imagesList.get(0));
                forChangeImgList.add(YourWork.imagesList.get(1));
                forChangeImgList.add(YourWork.imagesList.get(2));
                forChangeImgList.add(YourWork.imagesList.get(3));
            } else if (size == 5) {
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                v4.setVisibility(View.GONE);
                v5.setVisibility(View.VISIBLE);
                v6.setVisibility(View.GONE);
                v7.setVisibility(View.GONE);
                img51.setImageURI(YourWork.imagesList.get(0).uri);
                img52.setImageURI(YourWork.imagesList.get(1).uri);
                img53.setImageURI(YourWork.imagesList.get(2).uri);
                img54.setImageURI(YourWork.imagesList.get(3).uri);
                img55.setImageURI(YourWork.imagesList.get(4).uri);


                forChangeImgList.add(YourWork.imagesList.get(0));
                forChangeImgList.add(YourWork.imagesList.get(1));
                forChangeImgList.add(YourWork.imagesList.get(2));
                forChangeImgList.add(YourWork.imagesList.get(3));
                forChangeImgList.add(YourWork.imagesList.get(4));
            } else if (size == 6) {
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                v4.setVisibility(View.GONE);
                v5.setVisibility(View.GONE);
                v6.setVisibility(View.VISIBLE);
                v7.setVisibility(View.GONE);
                img61.setImageURI(YourWork.imagesList.get(0).uri);
                img62.setImageURI(YourWork.imagesList.get(1).uri);
                img63.setImageURI(YourWork.imagesList.get(2).uri);
                img64.setImageURI(YourWork.imagesList.get(3).uri);
                img65.setImageURI(YourWork.imagesList.get(4).uri);
                img66.setImageURI(YourWork.imagesList.get(5).uri);


                forChangeImgList.add(YourWork.imagesList.get(0));
                forChangeImgList.add(YourWork.imagesList.get(1));
                forChangeImgList.add(YourWork.imagesList.get(2));
                forChangeImgList.add(YourWork.imagesList.get(3));
                forChangeImgList.add(YourWork.imagesList.get(4));
                forChangeImgList.add(YourWork.imagesList.get(5));

            } else if (size == 7) {
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                v4.setVisibility(View.GONE);
                v5.setVisibility(View.GONE);
                v6.setVisibility(View.GONE);
                v7.setVisibility(View.VISIBLE);
                img71.setImageURI(YourWork.imagesList.get(0).uri);
                img72.setImageURI(YourWork.imagesList.get(1).uri);
                img73.setImageURI(YourWork.imagesList.get(2).uri);
                img74.setImageURI(YourWork.imagesList.get(3).uri);
                img75.setImageURI(YourWork.imagesList.get(4).uri);
                img76.setImageURI(YourWork.imagesList.get(5).uri);
                img77.setImageURI(YourWork.imagesList.get(6).uri);


                forChangeImgList.add(YourWork.imagesList.get(0));
                forChangeImgList.add(YourWork.imagesList.get(1));
                forChangeImgList.add(YourWork.imagesList.get(2));
                forChangeImgList.add(YourWork.imagesList.get(3));
                forChangeImgList.add(YourWork.imagesList.get(4));
                forChangeImgList.add(YourWork.imagesList.get(5));
                forChangeImgList.add(YourWork.imagesList.get(6));

            } else if (size == 8) {

                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                v4.setVisibility(View.GONE);
                v5.setVisibility(View.GONE);
                v6.setVisibility(View.GONE);
                v7.setVisibility(View.GONE);
                v8.setVisibility(View.VISIBLE);
                img81.setImageURI(YourWork.imagesList.get(0).uri);
                img82.setImageURI(YourWork.imagesList.get(1).uri);
                img83.setImageURI(YourWork.imagesList.get(2).uri);
                img84.setImageURI(YourWork.imagesList.get(3).uri);
                img85.setImageURI(YourWork.imagesList.get(4).uri);
                img86.setImageURI(YourWork.imagesList.get(5).uri);
                img87.setImageURI(YourWork.imagesList.get(6).uri);
                img88.setImageURI(YourWork.imagesList.get(7).uri);


                forChangeImgList.add(YourWork.imagesList.get(0));
                forChangeImgList.add(YourWork.imagesList.get(1));
                forChangeImgList.add(YourWork.imagesList.get(2));
                forChangeImgList.add(YourWork.imagesList.get(3));
                forChangeImgList.add(YourWork.imagesList.get(4));
                forChangeImgList.add(YourWork.imagesList.get(5));
                forChangeImgList.add(YourWork.imagesList.get(6));
                forChangeImgList.add(YourWork.imagesList.get(7));

            }
        }
    }

    //on activity result
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri uri = result.getUri();
                if (i1 == 1) {
                    zimg1.setImageURI(uri);
                    i1 = 0;
                } else if (i == 1) {
                    singleimg.setImageURI(uri);
                    i = 0;
                } else if (i2 == 1) {
                    zimg2.setImageURI(uri);
                    i2 = 0;
                } else if (i21 == 1) {
                    zimg21.setImageURI(uri);
                    i21 = 0;
                } else if (i22 == 1) {
                    zimg22.setImageURI(uri);
                    i22 = 0;
                } else if (i23 == 1) {
                    zimg23.setImageURI(uri);
                    i23 = 0;
                } else if (i24 == 1) {
                    zimg24.setImageURI(uri);
                    i24 = 0;
                } else if (i41 == 1) {
                    img31.setImageURI(uri);
                    i41 = 0;
                } else if (i42 == 1) {
                    img32.setImageURI(uri);
                    i42 = 0;
                } else if (i43 == 1) {
                    img33.setImageURI(uri);
                    i43 = 0;
                } else if (i61 == 1) {
                    img61.setImageURI(uri);
                    i61 = 0;
                } else if (i62 == 1) {
                    img62.setImageURI(uri);
                    i62 = 0;
                } else if (i63 == 1) {
                    img63.setImageURI(uri);
                    i63 = 0;
                } else if (i64 == 1) {
                    img64.setImageURI(uri);
                    i64 = 0;
                } else if (i65 == 1) {
                    img65.setImageURI(uri);
                    i65 = 0;
                } else if (i66 == 1) {
                    img66.setImageURI(uri);
                    i66 = 0;
                } else if (i51 == 1) {
                    img51.setImageURI(uri);
                    i51 = 0;
                } else if (i52 == 1) {
                    img52.setImageURI(uri);
                    i52 = 0;
                } else if (i53 == 1) {
                    img53.setImageURI(uri);
                    i53 = 0;
                } else if (i54 == 1) {
                    img54.setImageURI(uri);
                    i54 = 0;
                } else if (i55 == 1) {
                    img55.setImageURI(uri);
                    i55 = 0;
                } else if (i71 == 1) {
                    img71.setImageURI(uri);
                    i71 = 0;
                } else if (i72 == 1) {
                    img72.setImageURI(uri);
                    i72 = 0;
                } else if (i73 == 1) {
                    img73.setImageURI(uri);
                    i73 = 0;
                } else if (i74 == 1) {
                    img74.setImageURI(uri);
                    i74 = 0;
                } else if (i75 == 1) {
                    img75.setImageURI(uri);
                    i75 = 0;
                } else if (i76 == 1) {
                    img76.setImageURI(uri);
                    i76 = 0;
                } else if (i77 == 1) {
                    img77.setImageURI(uri);
                    i77 = 0;
                } else if (i81 == 1) {
                    img81.setImageURI(uri);
                    i81 = 0;
                } else if (i82 == 1) {
                    img82.setImageURI(uri);
                    i82 = 0;
                } else if (i83 == 1) {
                    img83.setImageURI(uri);
                    i83 = 0;
                } else if (i84 == 1) {
                    img84.setImageURI(uri);
                    i84 = 0;
                } else if (i85 == 1) {
                    img85.setImageURI(uri);
                    i85 = 0;
                } else if (i86 == 1) {
                    img86.setImageURI(uri);
                    i86 = 0;
                } else if (i87 == 1) {
                    img87.setImageURI(uri);
                    i87 = 0;
                } else if (i88 == 1) {
                    img88.setImageURI(uri);
                    i88 = 0;
//                } else if (pickFrag == 1) {
//
//                    Drawable yourDrawable;
//                    try {
//                        InputStream inputStream = getContentResolver().openInputStream(uri);
//                        yourDrawable = Drawable.createFromStream(inputStream, uri.toString());
//                    } catch (FileNotFoundException e) {
//                        yourDrawable = getResources().getDrawable(R.drawable.gradient_0);
//                    }
//                    stickerView.setBackground(yourDrawable);
//                    pickFrag = 0;
//
//                }
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            }
        }
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {

            uri = data.getData();
            if (pickFrag == 1) {

                Drawable yourDrawable;
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    yourDrawable = Drawable.createFromStream(inputStream, uri.toString());
                } catch (FileNotFoundException e) {
                    yourDrawable = getResources().getDrawable(R.drawable.gradient_0);
                }
                stickerView.setBackground(yourDrawable);
                pickFrag = 0;

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
//                pickImageFromGallery();
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
            }
        } else {

            //      pickImageFromGallery();
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }


    }

    private void pickImageFromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
        Log.d("mytag", "call gallery");
    }


    //downlaod after editing
    public void downloadImage(View view) {
        b = ScreenShot.takeScreenShot(stickerView);
        saveToGallery(b);
    }

    public void saveToGallery(Bitmap b) {
        FileOutputStream fileOutputStream = null;
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/CollageApp");
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


    private void loadSticker() {
        Drawable drawable =
                ContextCompat.getDrawable(this, R.drawable.haizewang_215);
        Drawable drawable1 =
                ContextCompat.getDrawable(this, R.drawable.haizewang_23);
        stickerView.addSticker(new DrawableSticker(drawable));
        stickerView.addSticker(new DrawableSticker(drawable1), Sticker.Position.BOTTOM | Sticker.Position.RIGHT);

        Drawable bubble = ContextCompat.getDrawable(this, R.drawable.sticker1);
        stickerView.addSticker(
                new TextSticker(getApplicationContext())
                        .setDrawable(bubble)
                        .setText("Sticker\n")
                        .setMaxTextSize(14)
                        .resizeText()
                , Sticker.Position.TOP);
    }

    public static void loadSticker2(Drawable img) {
        stickerView.addSticker(
                new TextSticker(context)
                        .setDrawable(img)
                        .setText("\n")
                        .setMaxTextSize(14)
                        .resizeText()
                , Sticker.Position.TOP);
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
        stickerForText = new TextSticker(getApplicationContext());

        stickerForText.setTextColor(mDefaultColor);
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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainCanvas.this,
                        android.R.layout.simple_spinner_item, style);
                spinner.setAdapter(adapter);

            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                if (etxt.getText().toString().isEmpty()) {
                    etxt.setError("Please provide text...");
                    etxt.requestFocus();
                } else {
                    stickerForText.setText(etxt.getText().toString());
                    stickerForText.setTextColor(localColor);
                    stickerForText.setTextAlign(Layout.Alignment.ALIGN_CENTER);
                    stickerForText.resizeText();
                    stickerView.addSticker(stickerForText);
                    alertDialog.dismiss();
                    checkEmptyTxtSticker = true;
                    DeleteIconEvent.flagdelete = false;
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
                localColor = i;
                etxt.setTextColor(i);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.white));
                colorPosition[0] = i;
                etxt.setTypeface(Typeface.createFromAsset(MainCanvas.this.getAssets(), styles[colorPosition[0]]));
                stickerForText.setTypeface(Typeface.createFromAsset(MainCanvas.this.getAssets(), styles[colorPosition[0]]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    public void textForStickerText(View view) {
        showDialog();
        flagTextSticker = true;

    }

    public void backToYourWork(View view) {

        this.finish();  //may call problem
        if (interstitialAd != null && interstitialAd.isAdLoaded()) {
            interstitialAd.show();
        } else {

            startActivity(new Intent(getApplicationContext(), YourWork.class));

        }
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
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    void callInterstitial() {
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
    public boolean onLongClick(View v) {

        switch (v.getId()) {
            case R.id.singleimg1:
                singleimg.startAnimation(animation);
                i = 1;
                chooseImage();
                break;
            case R.id.zimage1:
                zimg1.startAnimation(animation);
                i1 = 1;
                chooseImage();
                break;
            case R.id.zimage2:
                zimg2.startAnimation(animation);
                i2 = 1;
                chooseImage();
                break;
            case R.id.zimage21:
                zimg21.startAnimation(animation);
                i21 = 1;
                chooseImage();
                break;
            case R.id.zimage22:

                zimg22.startAnimation(animation);
                i22 = 1;
                chooseImage();
                break;
            case R.id.zimage23:
                zimg23.startAnimation(animation);
                i23 = 1;
                chooseImage();
                break;
            case R.id.zimage24:
                zimg24.startAnimation(animation);
                i24 = 1;
                chooseImage();
                break;
            case R.id.image41:
                img31.startAnimation(animation);
                i41 = 1;
                chooseImage();
                break;
            case R.id.image42:
                img32.startAnimation(animation);
                i42 = 1;
                chooseImage();
                break;
            case R.id.image43:
                img33.startAnimation(animation);
                i43 = 1;
                chooseImage();
                break;
            case R.id.five1:
                img51.startAnimation(animation);
                i51 = 1;
                chooseImage();
                break;
            case R.id.five2:
                img52.startAnimation(animation);
                i52 = 1;
                chooseImage();
                break;
            case R.id.five3:
                img53.startAnimation(animation);
                i53 = 1;
                chooseImage();
                break;
            case R.id.five4:
                img54.startAnimation(animation);
                i54 = 1;
                chooseImage();
                break;
            case R.id.five5:
                img55.startAnimation(animation);
                i55 = 1;
                chooseImage();
                break;
            case R.id.first:
                img61.startAnimation(animation);
                i61 = 1;
                chooseImage();
                break;
            case R.id.second:
                img62.startAnimation(animation);
                i62 = 1;
                chooseImage();
                break;
            case R.id.third:
                img63.startAnimation(animation);
                i63 = 1;
                chooseImage();
                break;
            case R.id.fourth:
                img64.startAnimation(animation);
                i64 = 1;
                chooseImage();
                break;
            case R.id.fith:
                img65.startAnimation(animation);
                i65 = 1;
                chooseImage();
                break;
            case R.id.sixth:
                img66.startAnimation(animation);
                i66 = 1;
                chooseImage();
                break;
            case R.id.seventh1:
                img71.startAnimation(animation);
                i71 = 1;
                chooseImage();
                break;
            case R.id.seventh2:
                img72.startAnimation(animation);
                i72 = 1;
                chooseImage();
                break;
            case R.id.seventh3:
                img73.startAnimation(animation);
                i73 = 1;
                chooseImage();
                break;
            case R.id.seventh4:
                img74.startAnimation(animation);
                i74 = 1;
                chooseImage();
                break;
            case R.id.seventh5:
                img75.startAnimation(animation);
                i75 = 1;
                chooseImage();
                break;
            case R.id.seventh6:
                img76.startAnimation(animation);
                i76 = 1;
                chooseImage();
                break;
            case R.id.seventh7:
                img77.startAnimation(animation);
                i77 = 1;
                chooseImage();
                break;
            case R.id.eight1:
                img81.startAnimation(animation);
                i81 = 1;
                chooseImage();
                break;
            case R.id.eight2:
                img82.startAnimation(animation);
                i82 = 1;
                chooseImage();
                break;
            case R.id.eight3:
                img83.startAnimation(animation);
                i83 = 1;
                chooseImage();
                break;
            case R.id.eight4:
                img84.startAnimation(animation);
                i84 = 1;
                chooseImage();
                break;
            case R.id.eight5:
                img85.startAnimation(animation);
                i85 = 1;
                chooseImage();
                break;
            case R.id.eight6:
                img86.startAnimation(animation);
                i86 = 1;
                chooseImage();
                break;
            case R.id.eight7:
                img87.startAnimation(animation);
                i87 = 1;
                chooseImage();
                break;
            case R.id.eight8:
                img88.startAnimation(animation);
                i88 = 1;
                chooseImage();
                break;

            default:
                break;

        }

        return false;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.addmore:
                pickImageFromGallery();
                pickFrag = 1;
                break;
            case R.id.done:
                viewColor.setVisibility(View.GONE);
                break;
            case R.id.cancel:
                viewColor.setVisibility(View.GONE);
                break;
            case R.id.donelayout:
                viewBackGround.setVisibility(View.GONE);
                break;
            case R.id.cancellayout:
                viewBackGround.setVisibility(View.GONE);
                break;
            case R.id.donesticker:
                viewSticker.setVisibility(View.GONE);
                break;
            case R.id.cancelsticker:
                viewSticker.setVisibility(View.GONE);
                break;
            case R.id.donetext:
                viewText.setVisibility(View.GONE);
                break;
            case R.id.canceltext:
                viewText.setVisibility(View.GONE);
                break;
            case R.id.donefilter:
                viewfilter.setVisibility(View.GONE);
                break;
            case R.id.cancelfilter:
                viewfilter.setVisibility(View.GONE);
                break;
        }

        relativeLayout.setVisibility(View.VISIBLE);

    }
}
