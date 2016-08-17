package com.valevich.lingvoapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.ui.recyclerview.adapters.CardCategoriesAdapter;
import com.valevich.lingvoapp.utils.SpacesItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_card_categories)
public class CardCategoriesFragment extends Fragment {

    private static final int CATEGORIES_LOADER_ID = 0;

    @ViewById(R.id.grid)
    RecyclerView mRecyclerView;

    @Bean
    CardCategoriesAdapter mCardCategoriesAdapter;

    @AfterViews
    void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCategories();
    }

    private void loadCategories() {
        getLoaderManager().restartLoader(CATEGORIES_LOADER_ID,
                null,
                new LoaderManager.LoaderCallbacks() {
                    @Override
                    public Loader onCreateLoader(int id, Bundle args) {
                        final AsyncTaskLoader loader = new AsyncTaskLoader(getActivity()) {
                            @Override
                            public Object loadInBackground() {
                                mCardCategoriesAdapter.initAdapter();
                                return null;
                            }
                        };
                        loader.forceLoad();
                        return loader;
                    }

                    @Override
                    public void onLoadFinished(Loader loader, Object data) {
                        mRecyclerView.setAdapter(mCardCategoriesAdapter);
                    }

                    @Override
                    public void onLoaderReset(Loader loader) {

                    }
                });
    }
}
