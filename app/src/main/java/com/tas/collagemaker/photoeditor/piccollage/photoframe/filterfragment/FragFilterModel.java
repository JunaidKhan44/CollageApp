package com.tas.collagemaker.photoeditor.piccollage.photoframe.filterfragment;

public class FragFilterModel {

    public  int filterImg;
    public String name;

    public FragFilterModel(int filterImg, String name) {
        this.filterImg = filterImg;
        this.name = name;
    }

    public int getfilterImg() {
        return filterImg;
    }

    public void setfilterImg(int filterImg) {
        this.filterImg = filterImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
