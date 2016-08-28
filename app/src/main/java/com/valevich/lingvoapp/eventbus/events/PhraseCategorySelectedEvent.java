package com.valevich.lingvoapp.eventbus.events;

public class PhraseCategorySelectedEvent {
    private String mCategoryName;

    public PhraseCategorySelectedEvent(String categoryName) {
        mCategoryName = categoryName;
    }

    public String getCategoryName() {
        return mCategoryName;
    }
}
