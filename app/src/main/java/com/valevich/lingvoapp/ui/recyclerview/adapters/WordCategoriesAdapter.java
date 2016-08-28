package com.valevich.lingvoapp.ui.recyclerview.adapters;


import android.content.Context;
import android.view.ViewGroup;

import com.valevich.lingvoapp.eventbus.EventBus;
import com.valevich.lingvoapp.eventbus.events.WordCategorySelectedEvent;
import com.valevich.lingvoapp.stubmodel.WordCategory;
import com.valevich.lingvoapp.ui.recyclerview.ViewWrapper;
import com.valevich.lingvoapp.ui.recyclerview.views.WordCategoryItemView;
import com.valevich.lingvoapp.ui.recyclerview.views.WordCategoryItemView_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class WordCategoriesAdapter extends RecyclerViewAdapterBase<WordCategory, WordCategoryItemView>{

    @RootContext
    Context mContext;

    @Bean
    EventBus mEventBus;

    public void init() {
         mItems = WordCategory.getAll();
    }

    @Override
    protected WordCategoryItemView onCreateItemView(ViewGroup parent, int viewType) {
        return WordCategoryItemView_.build(mContext);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<WordCategoryItemView> holder, int position) {
        WordCategoryItemView itemView = holder.getView();
        itemView.bindData(mItems.get(position));

        itemView.setOnClickListener(view -> notifyWordCategorySelected(mItems
                .get(position)
                .getName()));
    }

    private void notifyWordCategorySelected(String categoryName) {
        mEventBus.post(new WordCategorySelectedEvent(categoryName));
    }
}
