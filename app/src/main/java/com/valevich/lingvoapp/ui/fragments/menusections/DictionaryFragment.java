package com.valevich.lingvoapp.ui.fragments.menusections;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.ui.recyclerview.adapters.DictionaryAdapter;
import com.valevich.lingvoapp.utils.ListItemDecoration;
import com.viethoa.RecyclerViewFastScroller;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

@EFragment(R.layout.fragment_dictionary)
public class DictionaryFragment extends Fragment {

    private static final int WORDS_LOADER_ID = 0;
    private static final String SEARCH_ID = "SEARCH_ID";

    @ViewById(R.id.words_list)
    RecyclerView mRecyclerView;

    @ViewById(R.id.fast_scroller)
    RecyclerViewFastScroller mScroller;

    @ViewById(R.id.search_input)
    EditText mSearchBar;

    @Bean
    DictionaryAdapter mDictionaryAdapter;

    @AfterViews
    void setUpViews() {
        setUpRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadWords("");
    }

    @TextChange(R.id.search_input)
    void setUpSearch(CharSequence charSequence) {
        String filter = charSequence.toString();
        BackgroundExecutor.cancelAll(SEARCH_ID, true);
        queryWords(filter);
    }

    @Background(delay = 700, id = SEARCH_ID)
    void queryWords(String filter) {
        loadWords(filter);
    }

    private void loadWords(String filter) {
        getLoaderManager().restartLoader(WORDS_LOADER_ID,
                null,
                new LoaderManager.LoaderCallbacks() {
                    @Override
                    public Loader onCreateLoader(int id, Bundle args) {
                        final AsyncTaskLoader loader = new AsyncTaskLoader(getActivity()) {
                            @Override
                            public Object loadInBackground() {
                                mDictionaryAdapter.init(filter);
                                return null;
                            }
                        };
                        loader.forceLoad();
                        return loader;
                    }

                    @Override
                    public void onLoadFinished(Loader loader, Object data) {
                        mRecyclerView.setAdapter(mDictionaryAdapter);
                        mScroller.setRecyclerView(mRecyclerView);
                        mScroller.setUpAlphabet(mDictionaryAdapter.getAlphabetItems());
                    }

                    @Override
                    public void onLoaderReset(Loader loader) {

                    }
                });
    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new ListItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL));
    }

}
