package com.tas.collagemaker.photoeditor.piccollage.photoframe.filtersfreestyle;

public class FilterFrag_FreestyleModel {

    public  int img;
    public String text;

    public FilterFrag_FreestyleModel(int img, String text) {
        this.img = img;
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
