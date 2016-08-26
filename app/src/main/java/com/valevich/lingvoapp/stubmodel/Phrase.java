package com.valevich.lingvoapp.stubmodel;

public class Phrase {

    private String mNativeText;

    private String mTranslation;

    public Phrase(String nativeText, String translation) {
        mNativeText = nativeText;
        mTranslation = translation;
    }

    public String getNativeText() {
        return mNativeText;
    }

    public String getTranslation() {
        return mTranslation;
    }

    public static Phrase getRandom() {
        return new Phrase("СКОЛЬКО ТЕБЕ ЛЕТ ?","HOW OLD ARE YOU ?");
    }

}
