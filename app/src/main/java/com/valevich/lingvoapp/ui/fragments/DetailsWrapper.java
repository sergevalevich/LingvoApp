package com.valevich.lingvoapp.ui.fragments;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.squareup.otto.Subscribe;
import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.eventbus.EventBus;
import com.valevich.lingvoapp.eventbus.events.ArrowClickedEvent;
import com.valevich.lingvoapp.stubmodel.Starable;
import com.valevich.lingvoapp.stubmodel.Translatable;
import com.valevich.lingvoapp.stubmodel.Viewable;
import com.valevich.lingvoapp.ui.recyclerview.adapters.SlidePagerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
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

    @Bean
    EventBus mEventBus;

    @AfterViews
    void setUpViews() {
        if(items != null) setupViewPager();
    }

    @Override
    public void onStart() {
        super.onStart();
        mEventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mEventBus.unregister(this);
    }

    @Subscribe
    public void onArrowClicked(ArrowClickedEvent event)  {
        int currentItem = mPager.getCurrentItem();
        boolean isLeftArrow = event.isLeftArrowClicked();

        if(isLeftArrow) {
            if(!isItemFirst(currentItem)) mPager.setCurrentItem(--currentItem);
        } else {
            if(!isItemLast(currentItem)) mPager.setCurrentItem(++currentItem);
        }

    }

    private boolean isItemFirst(int itemPosition) {
        return itemPosition == 0;
    }

    private boolean isItemLast(int itemPosition) {
        return itemPosition == mPager.getAdapter().getCount() - 1;
    }

    private void setupViewPager() {
        PagerAdapter pagerAdapter = new SlidePagerAdapter<>(getFragmentManager(),
                Arrays.asList((T[]) items));//cast because if the AA bug
        mPager.setAdapter(pagerAdapter);
        mPager.setCurrentItem(position);
    }

}
