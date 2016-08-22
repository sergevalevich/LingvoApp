package com.valevich.lingvoapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.ui.recyclerview.adapters.WordSlidePagerAdapter;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.List;


@EFragment(R.layout.details_wrapper)
public class DetailsWrapper extends Fragment {


    private static final String KEY_CURRENT_PAGE = "PAGE";
    private static final int WORDS_LOADER_ID = 0;

    @ViewById(R.id.pager)
    ViewPager mPager;

    @FragmentArg
    int wordNumber;

    @FragmentArg
    String categoryName;

    @Override
    public void onResume() {
        super.onResume();
        loadWords();
    }

    private void setupViewPager(List<Word> words) {
        PagerAdapter pagerAdapter = new WordSlidePagerAdapter(getFragmentManager(), words);
        mPager.setAdapter(pagerAdapter);
        mPager.setCurrentItem(wordNumber);
    }


    private void loadWords() {
        getLoaderManager().restartLoader(WORDS_LOADER_ID,
                null,
                new LoaderManager.LoaderCallbacks<List<Word>>() {
                    @Override
                    public Loader<List<Word>> onCreateLoader(int i, Bundle bundle) {
                        final AsyncTaskLoader<List<Word>> loader = new AsyncTaskLoader<List<Word>>(getContext()) {
                            @Override
                            public List<Word> loadInBackground() {
                                return Word.getAllByCategory(categoryName);
                            }
                        };
                        loader.forceLoad();
                        return loader;
                    }

                    @Override
                    public void onLoadFinished(Loader<List<Word>> loader, List<Word> words) {
                        setupViewPager(words);
                    }

                    @Override
                    public void onLoaderReset(Loader<List<Word>> loader) {

                    }
                });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_PAGE, mPager.getCurrentItem());
    }
}
