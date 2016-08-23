package com.valevich.lingvoapp.stubmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Word implements Serializable {

    private String mNativeText;

    private String mTranslation;

    private boolean mIsFavorite;

    private String mImageUrl;

    private int mId;

    public Word(String translation) {
        mTranslation = translation;
    }

    public Word(String nativeText, boolean isFavorite, String imageUrl) {
        mNativeText = nativeText;
        mIsFavorite = isFavorite;
        mImageUrl = imageUrl;
    }

    public static List<Word> getAll(String filter) {
        return new ArrayList<>();
    }

    public String getNativeText() {
        return mNativeText;
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

    public static List<Word> getBunch() {
        return new ArrayList<>(4);
    }
}
