package com.valevich.lingvoapp.ui.fragments;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Starable;
import com.valevich.lingvoapp.stubmodel.Translatable;
import com.valevich.lingvoapp.stubmodel.Viewable;
import com.valevich.lingvoapp.ui.recyclerview.adapters.SlidePagerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.Arrays;


@EFragment(R.layout.details_wrapper)
public class DetailsWrapper<T extends Parcelable & Translatable & Viewable & Starable> extends Fragment {

    @ViewById(R.id.pager)
    ViewPager mPager;

    @FragmentArg
    int position;

    @FragmentArg
    Parcelable[] items;

    @AfterViews
    void setUpViews() {
        if(items != null) setupViewPager();
    }

    private void setupViewPager() {
        PagerAdapter pagerAdapter = new SlidePagerAdapter<>(getFragmentManager(),
                Arrays.asList((T[]) items));//cast because if the AA bug
        mPager.setAdapter(pagerAdapter);
        mPager.setCurrentItem(position);
    }

}
