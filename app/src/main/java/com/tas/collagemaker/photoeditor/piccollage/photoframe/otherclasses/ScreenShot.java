package com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses;

import android.graphics.Bitmap;
import android.view.View;

public class ScreenShot {

    public  static Bitmap takeScreenShot(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap  b=Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return  b;

    }

    public static  Bitmap takeScreenShotofRootView(View v){
        return  takeScreenShot(v.getRootView());
    }
}
