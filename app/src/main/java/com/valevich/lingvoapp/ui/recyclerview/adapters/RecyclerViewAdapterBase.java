package com.valevich.lingvoapp.ui.recyclerview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.valevich.lingvoapp.ui.recyclerview.ViewWrapper;
import com.valevich.lingvoapp.ui.recyclerview.utils.ViewBinder;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewAdapterBase<T, V extends View & ViewBinder<T>>
        extends RecyclerView.Adapter<ViewWrapper<V>> {

    protected List<T> mItems = new ArrayList<>();

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public final ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<>(onCreateItemView(parent, viewType));
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

}
