package com.valevich.lingvoapp.ui.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.ui.recyclerview.adapters.TrainingsAdapter;
import com.valevich.lingvoapp.utils.ListItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_trainings)
public class TrainingsFragment extends Fragment {

    @ViewById(R.id.training_list)
    RecyclerView mRecyclerView;

    @Bean
    TrainingsAdapter mTrainingsAdapter;

    @AfterViews
    void setUpViews() {
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        mRecyclerView.addItemDecoration(new ListItemDecoration(spacingInPixels));

        mTrainingsAdapter.initAdapter();
        mRecyclerView.setAdapter(mTrainingsAdapter);
    }
}
