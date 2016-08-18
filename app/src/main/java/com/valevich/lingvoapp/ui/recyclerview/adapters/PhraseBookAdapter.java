package com.valevich.lingvoapp.ui.recyclerview.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.valevich.lingvoapp.stubmodel.PhraseCategory;
import com.valevich.lingvoapp.ui.recyclerview.ViewWrapper;
import com.valevich.lingvoapp.ui.recyclerview.views.PhraseBookItemView;
import com.valevich.lingvoapp.ui.recyclerview.views.PhraseBookItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.Arrays;

@EBean
public class PhraseBookAdapter extends RecyclerViewAdapterBase<PhraseCategory, PhraseBookItemView> {
    @RootContext
    Context mContext;

    public void initAdapter() {
        mItems = new ArrayList<>(Arrays.asList(new PhraseCategory(),new PhraseCategory(),new PhraseCategory()));
    }

    @Override
    protected PhraseBookItemView onCreateItemView(ViewGroup parent, int viewType) {
        return PhraseBookItemView_.build(mContext);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<PhraseBookItemView> holder, int position) {
        PhraseBookItemView itemView = holder.getView();
        itemView.bindData(mItems.get(position));
    }
}
