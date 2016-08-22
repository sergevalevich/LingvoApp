package com.valevich.lingvoapp.ui.recyclerview.adapters;


import android.content.Context;
import android.view.ViewGroup;

import com.valevich.lingvoapp.stubmodel.CardCategory;
import com.valevich.lingvoapp.ui.recyclerview.ViewWrapper;
import com.valevich.lingvoapp.ui.recyclerview.views.CardCategoryItemView;
import com.valevich.lingvoapp.ui.recyclerview.views.CardCategoryItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class CardCategoriesAdapter extends RecyclerViewAdapterBase<CardCategory, CardCategoryItemView>{

    @RootContext
    Context mContext;

    public void init() {
         mItems = CardCategory.getAll();
    }

    @Override
    protected CardCategoryItemView onCreateItemView(ViewGroup parent, int viewType) {
        return CardCategoryItemView_.build(mContext);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<CardCategoryItemView> holder, int position) {
        CardCategoryItemView itemView = holder.getView();
        itemView.bindData(mItems.get(position));
    }
}
