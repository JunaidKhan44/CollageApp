package com.tas.collagemaker.photoeditor.piccollage.photoframe.yourworks;

import android.graphics.Bitmap;

public class YourWorkModel {

    private Bitmap imgWork;
    String ImageName,Path;

    public YourWorkModel(Bitmap imgWork, String imageName, String path) {
        this.imgWork = imgWork;
        this.ImageName = imageName;
        this.Path = path;
    }

    public Bitmap getimgWork() {
        return imgWork;
    }

    public void setimgWork(Bitmap imgWork) {
        this.imgWork = imgWork;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }
}
