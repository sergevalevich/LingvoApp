package com.valevich.lingvoapp.ui.recyclerview.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.valevich.lingvoapp.eventbus.EventBus;
import com.valevich.lingvoapp.eventbus.events.PhraseCategorySelectedEvent;
import com.valevich.lingvoapp.stubmodel.PhraseCategory;
import com.valevich.lingvoapp.ui.recyclerview.ViewWrapper;
import com.valevich.lingvoapp.ui.recyclerview.views.PhraseBookItemView;
import com.valevich.lingvoapp.ui.recyclerview.views.PhraseBookItemView_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class PhraseBookAdapter extends RecyclerViewAdapterBase<PhraseCategory, PhraseBookItemView> {

    @RootContext
    Context mContext;

    @Bean
    EventBus mEventBus;

    public void init() {
        mItems = PhraseCategory.getAll();
    }

    @Override
    protected PhraseBookItemView onCreateItemView(ViewGroup parent, int viewType) {
        return PhraseBookItemView_.build(mContext);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<PhraseBookItemView> holder, int position) {
        PhraseBookItemView itemView = holder.getView();
        itemView.bindData(mItems.get(position));

        itemView.setOnClickListener(view -> notifyPhraseCategorySelected(mItems
                .get(position)
                .getName()));
    }

    private void notifyPhraseCategorySelected(String name) {
        mEventBus.post(new PhraseCategorySelectedEvent(name));
    }
}
