package com.valevich.lingvoapp.ui.fragments.menusections;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.ui.recyclerview.adapters.CardCategoriesAdapter;
import com.valevich.lingvoapp.utils.ListItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_card_categories)
public class CardCategoriesFragment extends Fragment {

    @ViewById(R.id.grid)
    RecyclerView mRecyclerView;

    @Bean
    CardCategoriesAdapter mCardCategoriesAdapter;

    @AfterViews
    void setUpViews() {
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing_wide);
        mRecyclerView.addItemDecoration(new ListItemDecoration(spacingInPixels));

        mCardCategoriesAdapter.init();
        mRecyclerView.setAdapter(mCardCategoriesAdapter);
    }

}
