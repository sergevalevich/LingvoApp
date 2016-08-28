package com.valevich.lingvoapp.stubmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.valevich.lingvoapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Phrase implements Parcelable,Translatable,Viewable,Starable {

    private String mNativeText;

    private String mTranslation;

    private int mImageResId;

    private boolean mIsFavorite;

    public Phrase(String nativeText, String translation, int imageResId) {
        mNativeText = nativeText;
        mTranslation = translation;
        mImageResId = imageResId;
    }

    protected Phrase(Parcel in) {
        mNativeText = in.readString();
        mTranslation = in.readString();
        mImageResId = in.readInt();
        mIsFavorite = in.readByte() != 0;
    }

    @Override
    public String getNativeText() {
        return mNativeText;
    }

    @Override
    public String getTranslation() {
        return mTranslation;
    }

    @Override
    public int getImageResId() {
        return mImageResId;
    }

    @Override
    public boolean isFavorite() {
        return mIsFavorite;
    }

    @Override
    public void setFavorite(boolean favorite) {
        mIsFavorite = favorite;
    }

    public static Phrase getRandom() {
        return new Phrase("СКОЛЬКО ТЕБЕ ЛЕТ ?","HOW OLD ARE YOU ?",-1);
    }

    public static List<Phrase> getAllByCategory(String categoryName) {
        return new ArrayList<>(Arrays.asList(
                new Phrase("Доброе утро","Good morning", R.drawable.baby_big),
                new Phrase("Добрый день","Good afternoon", R.drawable.sunny),
                new Phrase("Добрый вечер","Good evening", R.drawable.gentelmen),
                new Phrase("Рад тебя видеть","I'm glad to see you", R.drawable.hugs)
        ));
    }

    public PhraseCategory getCategory() {
        return new PhraseCategory("ПОВСЕДНЕВНЫЕ ФРАЗЫ","приветствие,комплименты", R.drawable.hello);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Phrase> CREATOR = new Creator<Phrase>() {
        @Override
        public Phrase createFromParcel(Parcel in) {
            return new Phrase(in);
        }

        @Override
        public Phrase[] newArray(int size) {
            return new Phrase[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mNativeText);
        parcel.writeString(mTranslation);
        parcel.writeInt(mImageResId);
        parcel.writeByte((byte) (mIsFavorite ? 1 : 0));
    }

    public static Phrase get(int id) {
        return new Phrase("Доброе утро","Good morning", R.drawable.baby_big);
    }
}
