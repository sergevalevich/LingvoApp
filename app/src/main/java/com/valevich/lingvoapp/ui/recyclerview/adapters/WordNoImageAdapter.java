package com.valevich.lingvoapp.ui.recyclerview.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.ui.recyclerview.ViewWrapper;
import com.valevich.lingvoapp.ui.recyclerview.views.WordMediaItemView_;
import com.valevich.lingvoapp.ui.recyclerview.views.WordNoImageItemView;
import com.valevich.lingvoapp.ui.recyclerview.views.WordNoImageItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class WordNoImageAdapter extends RecyclerViewAdapterBase<Word,WordNoImageItemView> {

    public void init(String categoryName) {
        Word.getAllByCategory(categoryName);
    }

    @RootContext
    Context mContext;

    @Override
    protected WordNoImageItemView onCreateItemView(ViewGroup parent, int viewType) {
        return WordNoImageItemView_.build(mContext);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<WordNoImageItemView> holder, int position) {
        WordNoImageItemView itemView = holder.getView();
        itemView.bindData(mItems.get(position));
    }
}
