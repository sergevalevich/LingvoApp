package com.valevich.lingvoapp.ui.recyclerview.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.valevich.lingvoapp.eventbus.EventBus;
import com.valevich.lingvoapp.eventbus.events.ItemSelectedEvent;
import com.valevich.lingvoapp.stubmodel.Phrase;
import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.ui.recyclerview.ViewWrapper;
import com.valevich.lingvoapp.ui.recyclerview.views.PhraseMediaItemView;
import com.valevich.lingvoapp.ui.recyclerview.views.PhraseMediaItemView_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class PhraseMediaAdapter extends RecyclerViewAdapterBase<Phrase,PhraseMediaItemView>{

    public void init(String categoryName) {
        mItems = Phrase.getAllByCategory(categoryName);
    }

    @RootContext
    Context mContext;

    @Bean
    EventBus mEventBus;

    @Override
    protected PhraseMediaItemView onCreateItemView(ViewGroup parent, int viewType) {
        return PhraseMediaItemView_.build(mContext);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<PhraseMediaItemView> holder, int position) {
        PhraseMediaItemView itemView = holder.getView();
        itemView.bindData(mItems.get(position));

        itemView.setOnClickListener(view -> notifyPhraseSelected(position));
    }

    private void notifyPhraseSelected(int position) {
        Phrase [] items = mItems.toArray(new Phrase[mItems.size()]);
        mEventBus.post(new ItemSelectedEvent<>(items,position));
    }
}
