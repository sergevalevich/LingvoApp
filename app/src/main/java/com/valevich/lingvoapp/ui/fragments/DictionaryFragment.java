package com.valevich.lingvoapp.ui.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.ui.recyclerview.adapters.DictionaryAdapter;
import com.valevich.lingvoapp.utils.ListItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_dictionary)
public class DictionaryFragment extends Fragment {

    @ViewById(R.id.words_list)
    RecyclerView mRecyclerView;

    @Bean
    DictionaryAdapter mDictionaryAdapter;

    @AfterViews
    void setUpViews() {
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new ListItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL));

        // TODO: 18.08.2016 loader here
        mDictionaryAdapter.initAdapter();
        mRecyclerView.setAdapter(mDictionaryAdapter);
    }

}
