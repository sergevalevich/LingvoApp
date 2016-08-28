package com.valevich.lingvoapp.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.SystemService;

@EBean
public class KeyBoardHelper {

    @RootContext
    AppCompatActivity mActivity;

    @SystemService
    InputMethodManager mInputMethodManager;

    public void hideKeyBoard() {
        View view = mActivity.getCurrentFocus();
        if (view != null) {
            mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
