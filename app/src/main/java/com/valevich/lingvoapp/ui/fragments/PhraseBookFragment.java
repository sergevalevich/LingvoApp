package com.valevich.lingvoapp.ui.fragments;

import android.support.v4.app.Fragment;
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

    @ViewById(R.id.phrase_category_list)
    RecyclerView mRecyclerView;

    @Bean
    PhraseBookAdapter mPhraseBookAdapter;

    @AfterViews
    void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new ListItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL ));

        mPhraseBookAdapter.initAdapter();
        mRecyclerView.setAdapter(mPhraseBookAdapter);
    }

}
