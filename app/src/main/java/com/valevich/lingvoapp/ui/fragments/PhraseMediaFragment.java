package com.valevich.lingvoapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.ui.recyclerview.adapters.PhraseMediaAdapter;
import com.valevich.lingvoapp.ui.recyclerview.adapters.WordMediaAdapter;
import com.valevich.lingvoapp.utils.ListItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_phrase_media)
public class PhraseMediaFragment extends Fragment {

    private static final int PHRASE_LOADER_ID = 0;

    @ViewById(R.id.phrase_list)
    RecyclerView mRecyclerView;

    @Bean
    PhraseMediaAdapter mAdapter;

    @FragmentArg
    String categoryName;

    @AfterViews
    void setUpViews() {
        setUpRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadWords();
    }

    private void loadWords() {
        getLoaderManager().restartLoader(PHRASE_LOADER_ID,
                null,
                new LoaderManager.LoaderCallbacks() {
                    @Override
                    public Loader onCreateLoader(int id, Bundle args) {
                        final AsyncTaskLoader loader = new AsyncTaskLoader(getActivity()) {
                            @Override
                            public Object loadInBackground() {
                                mAdapter.init(categoryName);
                                return null;
                            }
                        };
                        loader.forceLoad();
                        return loader;
                    }

                    @Override
                    public void onLoadFinished(Loader loader, Object data) {
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onLoaderReset(Loader loader) {

                    }
                });
    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing_wide);
        mRecyclerView.addItemDecoration(new ListItemDecoration(spacingInPixels));
    }
}
