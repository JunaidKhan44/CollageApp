package com.tas.collagemaker.photoeditor.piccollage.photoframe.pageadapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tas.collagemaker.photoeditor.piccollage.photoframe.colorfragment.ColorFragment;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.gradientfragment.GradientFragment;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.imagefragment.ImageFragment;

public class PageAdapter extends FragmentPagerAdapter {


    private int numOfTabs;

    public PageAdapter(@NonNull FragmentManager fm, int behavior, int numOfTabs) {
        super(fm, behavior);
        this.numOfTabs = numOfTabs;
    }

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ColorFragment();
            case 1:
                return new GradientFragment();
            case 2:
                return new ImageFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}