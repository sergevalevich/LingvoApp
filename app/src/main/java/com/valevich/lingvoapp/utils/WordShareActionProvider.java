package com.valevich.lingvoapp.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.res.StringRes;

@EBean
public class WordShareActionProvider {

    @RootContext
    Context mContext;

    @StringRes(R.string.share_dialog_msg)
    String mShareDialogMessage;

    public void share(Word word) {
        Intent shareIntent = createShareIntent(word.getNativeText());
        mContext.startActivity(Intent.createChooser(shareIntent,mShareDialogMessage));
    }

    private Intent createShareIntent(String message) {
        Intent myShareIntent = new Intent(Intent.ACTION_SEND);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            myShareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        } else {
            myShareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        }
        myShareIntent.setType("text/plain");
        myShareIntent.putExtra(Intent.EXTRA_TEXT,message);
        return myShareIntent;
    }
}
