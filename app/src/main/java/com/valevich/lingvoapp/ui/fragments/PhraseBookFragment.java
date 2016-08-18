package com.valevich.lingvoapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.ui.recyclerview.adapters.PhraseBookAdapter;
import com.valevich.lingvoapp.utils.ListItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_phrasebook)
public class PhraseBookFragment extends Fragment {
    private static final int PHRASE_BOOK_LOADER_ID = 1;

    @ViewById(R.id.phrase_category_list)
    RecyclerView mRecyclerView;

    @Bean
    PhraseBookAdapter mPhraseBookAdapter;

    @AfterViews
    void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new ListItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL ));
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPhraseBook();
    }

    private void loadPhraseBook() {
        getLoaderManager().restartLoader(PHRASE_BOOK_LOADER_ID,
                null,
                new LoaderManager.LoaderCallbacks() {
                    @Override
                    public Loader onCreateLoader(int id, Bundle args) {
                        final AsyncTaskLoader loader = new AsyncTaskLoader(getActivity()) {
                            @Override
                            public Object loadInBackground() {
                                mPhraseBookAdapter.initAdapter();
                                return null;
                            }
                        };
                        loader.forceLoad();
                        return loader;
                    }

                    @Override
                    public void onLoadFinished(Loader loader, Object data) {
                        mRecyclerView.setAdapter(mPhraseBookAdapter);
                    }

                    @Override
                    public void onLoaderReset(Loader loader) {

                    }
                });
    }
}
