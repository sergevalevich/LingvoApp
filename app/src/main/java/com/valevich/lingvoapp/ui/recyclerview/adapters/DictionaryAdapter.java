package com.valevich.lingvoapp.ui.recyclerview.adapters;


import android.content.Context;
import android.view.ViewGroup;

import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.ui.recyclerview.ViewWrapper;
import com.valevich.lingvoapp.ui.recyclerview.views.DictionaryItemView;
import com.valevich.lingvoapp.ui.recyclerview.views.DictionaryItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.Arrays;

@EBean
public class DictionaryAdapter extends RecyclerViewAdapterBase<Word,DictionaryItemView> {

    @RootContext
    Context mContext;

    public void initAdapter() {
        mItems = new ArrayList<>(Arrays.asList(
                new Word(),new Word(),new Word(),new Word(),new Word()
        )); // TODO: 18.08.2016 DbImplementation here
    }

    @Override
    protected DictionaryItemView onCreateItemView(ViewGroup parent, int viewType) {
        return DictionaryItemView_.build(mContext);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<DictionaryItemView> holder, int position) {
        DictionaryItemView itemView = holder.getView();
        itemView.bindData(mItems.get(position));
    }
}
