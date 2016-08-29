package com.valevich.lingvoapp.stubmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.valevich.lingvoapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Word implements Parcelable,Translatable,Viewable,Starable,Randomized {

    private String mNativeText;

    private String mTranslation;

    private boolean mIsFavorite;

    private String mImageUrl;

    private int mImageResId;

    private int mId;

    public Word(String nativeText, String translation) {
        mTranslation = translation;
        mNativeText = nativeText;
    }

    public Word() {}

    public Word(String nativeText, String translation, int imageResId) {
        mNativeText = nativeText;
        mTranslation = translation;
        mImageResId = imageResId;
    }

    protected Word(Parcel in) {
        mNativeText = in.readString();
        mTranslation = in.readString();
        mIsFavorite = in.readByte() != 0;
        mImageUrl = in.readString();
        mImageResId = in.readInt();
        mId = in.readInt();
    }

    public static List<Word> getAll(String filter) {
        List<Word> words = new ArrayList<>(Arrays.asList(
                new Word("","a", R.drawable.a),
                new Word("","abandon",R.drawable.abandon),
                new Word("","ability",R.drawable.ability),
                new Word("","able",R.drawable.able),
                new Word("","aboard", R.drawable.aboard),
                new Word("","above",R.drawable.above),
                new Word("","abruptly",R.drawable.abruptly),
                new Word("","absolutely",R.drawable.absolut),
                new Word("","accent", R.drawable.accent),
                new Word("","accept",R.drawable.accept),
                new Word("","access",R.drawable.access),
                new Word("","b", R.drawable.a),
                new Word("","babandon",R.drawable.abandon),
                new Word("","bability",R.drawable.ability),
                new Word("","bable",R.drawable.able),
                new Word("","baboard", R.drawable.aboard),
                new Word("","babove",R.drawable.above),
                new Word("","babruptly",R.drawable.abruptly),
                new Word("","babsolutely",R.drawable.absolut),
                new Word("","baccent", R.drawable.accent),
                new Word("","baccept",R.drawable.accept),
                new Word("","baccess",R.drawable.access),
                new Word("","c", R.drawable.a),
                new Word("","cabandon",R.drawable.abandon),
                new Word("","cability",R.drawable.ability),
                new Word("","cable",R.drawable.able),
                new Word("","caboard", R.drawable.aboard),
                new Word("","cabove",R.drawable.above),
                new Word("","cabruptly",R.drawable.abruptly),
                new Word("","cabsolutely",R.drawable.absolut),
                new Word("","caccent", R.drawable.accent),
                new Word("","caccept",R.drawable.accept),
                new Word("","caccess",R.drawable.access),
                new Word("","d", R.drawable.a),
                new Word("","dabandon",R.drawable.abandon),
                new Word("","dability",R.drawable.ability),
                new Word("","dable",R.drawable.able),
                new Word("","daboard", R.drawable.aboard),
                new Word("","dabove",R.drawable.above),
                new Word("","dabruptly",R.drawable.abruptly),
                new Word("","dabsolutely",R.drawable.absolut),
                new Word("","daccent", R.drawable.accent),
                new Word("","daccept",R.drawable.accept),
                new Word("","daccess",R.drawable.access)
        ));

        List<Word> result = new ArrayList<>(words.size());

        for(Word word : words) {
            if(word.getTranslation().contains(filter.toUpperCase())) {
                result.add(word);
            }
        }

        return result;
    }

    @Override
    public String getNativeText() {
        return mNativeText.toUpperCase();
    }

    @Override
    public boolean isFavorite() {
        return mIsFavorite;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    @Override
    public int getImageResId() {
        return mImageResId;
    }

    @Override
    public void setFavorite(boolean isFavorite) {
        mIsFavorite = isFavorite;
    }

    @Override
    public String getTranslation() {
        return mTranslation.toUpperCase();
    }

    public static List<Word> getAllByCategory(String categoryName) {
        return new ArrayList<>(Arrays.asList(
                new Word("Акула","shark", R.drawable.shark),
                new Word("Енот","raccoon",R.drawable.raccoon),
                new Word("Медведь","bear",R.drawable.bear),
                new Word("Панда","panda",R.drawable.panda)
        ));
    }

    public static Word get(int wordId) {
        return new Word("Енот","Raccoon",R.drawable.raccoon);
    }

    public int getId() {
        return mId;
    }

    public static List<Word> getBunch() {
        return new ArrayList<>(Arrays.asList(
                new Word("Лимон","Lemon", R.drawable.lemon),
                new Word("Яблоко","Apple",R.drawable.apple),
                new Word("Арбуз","Watermelon",R.drawable.arbuz),
                new Word("Апельсин","Orange",R.drawable.apelsin)
        ));
    }

    @Override
    public Word getRandom() {
        return new Word("Апельсин","Orange");
    }

    public WordCategory getCategory() {
        return new WordCategory(0,"Животные",R.drawable.panda);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mNativeText);
        parcel.writeString(mTranslation);
        parcel.writeByte((byte) (mIsFavorite ? 1 : 0));
        parcel.writeString(mImageUrl);
        parcel.writeInt(mImageResId);
        parcel.writeInt(mId);
    }
}
