package com.valevich.lingvoapp.eventbus.events;

public class WordSelectedEvent {
    private int mPosition;

    private String mCategoryName;

    public WordSelectedEvent(int position, String categoryName) {
        mPosition = position;
        mCategoryName = categoryName;
    }

    public int getPosition() {
        return mPosition;
    }

    public String getCategoryName() {
        return mCategoryName;
    }
}
