package com.valevich.lingvoapp.ui.recyclerview.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.valevich.lingvoapp.eventbus.EventBus;
import com.valevich.lingvoapp.eventbus.events.WordSelectedEvent;
import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.ui.recyclerview.ViewWrapper;
import com.valevich.lingvoapp.ui.recyclerview.views.WordMediaItemView;
import com.valevich.lingvoapp.ui.recyclerview.views.WordMediaItemView_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class WordMediaAdapter extends RecyclerViewAdapterBase<Word,WordMediaItemView> {

    public void init(String categoryName) {
        mItems = Word.getAllByCategory(categoryName);
    }

    @RootContext
    Context mContext;

    @Bean
    EventBus mEventBus;

    @Override
    protected WordMediaItemView onCreateItemView(ViewGroup parent, int viewType) {
        return WordMediaItemView_.build(mContext);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<WordMediaItemView> holder, int position) {
        WordMediaItemView itemView = holder.getView();
        itemView.bindData(mItems.get(position));

        itemView.setOnClickListener(view -> notifyWordSelected(
                position,
                mItems.get(position).getCategory().getName()));
    }

    private void notifyWordSelected(int position, String wordCategory) {
        mEventBus.post(new WordSelectedEvent(position,wordCategory));
    }
}
