package com.valevich.lingvoapp.ui.recyclerview.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.ui.fragments.WordSlideFragment;


import java.util.List;

public class WordSlidePagerAdapter extends FragmentStatePagerAdapter {

    private List<Word> mWords;

    public WordSlidePagerAdapter(FragmentManager fm, List<Word> words) {
        super(fm);
        mWords = words;
    }

    @Override
    public Fragment getItem(int position) {
        return WordSlideFragment.newInstance(mWords.get(position).getId());
    }

    @Override
    public int getCount() {
        return mWords.size();
    }

}

