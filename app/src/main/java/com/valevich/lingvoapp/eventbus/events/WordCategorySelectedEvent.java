package com.valevich.lingvoapp.eventbus.events;

public class WordCategorySelectedEvent {
    private String mCategoryName;

    public WordCategorySelectedEvent(String categoryName) {
        mCategoryName = categoryName;
    }

    public String getCategoryName() {
        return mCategoryName;
    }
}
