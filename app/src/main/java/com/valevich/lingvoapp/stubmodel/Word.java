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

    private int mId;

    public Word(String translation) {
        mTranslation = translation;
    }

    public Word(String original, boolean isFavorite, String imageUrl) {
        mOriginal = original;
        mIsFavorite = isFavorite;
        mImageUrl = imageUrl;
    }

    public static List<Word> getAll(String filter) {
        return new ArrayList<>();
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

    public static Word get(int wordId) {
        return null;
    }

    public int getId() {
        return mId;
    }
}
