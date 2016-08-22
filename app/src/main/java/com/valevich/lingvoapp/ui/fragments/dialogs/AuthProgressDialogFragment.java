package com.valevich.lingvoapp.ui.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.valevich.lingvoapp.R;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

@EFragment
public class AuthProgressDialogFragment extends DialogFragment {

    @FragmentArg
    String message;

    @FragmentArg
    String content;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (message != null && content != null) {
            return new MaterialDialog.Builder(getContext())
                    .title(message)
                    .content(content)
                    .progress(true, 0)
                    .theme(Theme.LIGHT)
                    .contentColorRes(R.color.colorTextGray)
                    .titleColorRes(R.color.colorTextGray)
                    .build();
        }
        return super.onCreateDialog(savedInstanceState);
    }
}
