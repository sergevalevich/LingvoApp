package com.valevich.lingvoapp.ui.recyclerview.adapters;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.valevich.lingvoapp.stubmodel.Starable;
import com.valevich.lingvoapp.stubmodel.Translatable;
import com.valevich.lingvoapp.stubmodel.Viewable;
import com.valevich.lingvoapp.ui.fragments.SlideFragment_;


import java.util.List;

public class SlidePagerAdapter<T extends Parcelable & Translatable & Viewable & Starable> extends FragmentStatePagerAdapter {

    private List<T> mItems;

    public SlidePagerAdapter(FragmentManager fm, List<T> items) {
        super(fm);
        mItems = items;
    }

    @Override
    public Fragment getItem(int position) {
        return SlideFragment_.builder().item(mItems.get(position)).build();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

}

