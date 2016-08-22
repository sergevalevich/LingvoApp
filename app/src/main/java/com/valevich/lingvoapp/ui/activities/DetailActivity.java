package com.valevich.lingvoapp.ui.activities;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.ui.recyclerview.adapters.WordSlidePagerAdapter;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;


@EActivity(R.layout.activity_details)
public class DetailActivity extends AppCompatActivity {


    private static final String KEY_CURRENT_PAGE = "PAGE";
    private static final String KEY_INTENT_WORD_NUMBER = "WORD_NUMBER";
    private static final String KEY_INTENT_CATEGORY_NAME = "CATEGORY_NAME";
    private static final int WORDS_LOADER_ID = 0;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.pager)
    ViewPager mPager;

    @Extra(value = KEY_INTENT_WORD_NUMBER)
    int mWordNumber;

    @Extra(value = KEY_INTENT_CATEGORY_NAME)
    String mCategoryName;

    @AfterViews
    void setupViews() {
        setupActionBar();
    }

    @AfterExtras
    void setupPage() {
        loadWords();
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private void setupActionBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            setTitle("");
        }
    }

    private void setupViewPager(List<Word> words) {
        PagerAdapter pagerAdapter = new WordSlidePagerAdapter(getSupportFragmentManager(), words);
        mPager.setAdapter(pagerAdapter);
        mPager.setCurrentItem(mWordNumber);
    }


    private void loadWords() {
        getLoaderManager().restartLoader(WORDS_LOADER_ID,
                null,
                new LoaderManager.LoaderCallbacks<List<Word>>() {
                    @Override
                    public Loader<List<Word>> onCreateLoader(int i, Bundle bundle) {
                        final AsyncTaskLoader<List<Word>> loader = new AsyncTaskLoader<List<Word>>(DetailActivity.this) {
                            @Override
                            public List<Word> loadInBackground() {
                                return Word.getAllByCategory(mCategoryName);
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

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mWordNumber = savedInstanceState.getInt(KEY_CURRENT_PAGE, 0);
        mPager.setCurrentItem(mWordNumber);
    }
}
