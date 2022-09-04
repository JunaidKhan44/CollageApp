package com.tas.collagemaker.photoeditor.piccollage.photoframe.pageadapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tas.collagemaker.photoeditor.piccollage.photoframe.freestyletextsticker.TextStickerFreeStyleFragment;

public class PageAdapterForFreeStyle extends FragmentPagerAdapter {


    private int numOfTabs;

    public PageAdapterForFreeStyle(@androidx.annotation.NonNull FragmentManager fm, int behavior, int numOfTabs) {
        super(fm, behavior);
        this.numOfTabs = numOfTabs;
    }

    public PageAdapterForFreeStyle(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TextStickerFreeStyleFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}


