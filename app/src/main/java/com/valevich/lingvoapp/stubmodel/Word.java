package com.valevich.lingvoapp.stubmodel;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Word implements Serializable {

    private String mOriginal;

    private String mTranslation;

    private boolean mIsFavorite;

    private String mImageUrl;

    public Word() {}

    public Word(String original, boolean isFavorite, String imageUrl) {
        mOriginal = original;
        mIsFavorite = isFavorite;
        mImageUrl = imageUrl;
    }

    public String getOriginal() {
        return mOriginal;
    }

    public boolean isFavorite() {
        return mIsFavorite;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setFavorite(boolean isFavorite) {
        mIsFavorite = isFavorite;
    }

    public String getTranslation() {
        return mTranslation;
    }

    public static List<Word> getAllByCategory(String categoryName) {
        return new ArrayList<>();
    }
}
