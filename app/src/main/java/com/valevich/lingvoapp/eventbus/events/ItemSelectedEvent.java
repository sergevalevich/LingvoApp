package com.valevich.lingvoapp.eventbus.events;

import android.os.Parcelable;

import com.valevich.lingvoapp.stubmodel.Starable;
import com.valevich.lingvoapp.stubmodel.Translatable;
import com.valevich.lingvoapp.stubmodel.Viewable;

public class ItemSelectedEvent<T extends Parcelable & Translatable & Viewable & Starable> {
    private int mPosition;

    private T[] mItems;

    public ItemSelectedEvent(T[] items, int position) {
        mPosition = position;
        mItems = items;
    }

    public int getPosition() {
        return mPosition;
    }

    public T[] getItems() {
        return mItems;
    }
}
