package com.valevich.lingvoapp.ui.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.ui.fragments.dialogs.AuthProgressDialogFragment;
import com.valevich.lingvoapp.ui.fragments.dialogs.AuthProgressDialogFragment_;
import com.valevich.lingvoapp.utils.InputFieldValidator;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@EActivity(R.layout.activity_recover)
public class RecoverActivity extends AppCompatActivity {

    @ViewById(R.id.root)
    LinearLayout mRootView;

    @ViewById(R.id.login)
    EditText mLoginField;

    @ViewById(R.id.recover_button)
    Button mRecoverButton;

    @StringRes(R.string.invalid_credentials_msg)
    String mInvalidCredentialsMessage;

    @StringRes(R.string.recover_dialog_message)
    String mRecoveringMessage;

    @StringRes(R.string.auth_dialog_content)
    String mDialogContent;

    @StringRes(R.string.progress_dialog_tag)
    String mProgressDialogTag;

    @Bean
    InputFieldValidator mInputFieldValidator;

    private AuthProgressDialogFragment mProgressDialog;

    @Click(R.id.recover_button)
    void onRecoverClicked() {
        blockButtons();

        String login = mLoginField.getText().toString();

        if (isInputValid(login)) {
            //showProgressDialog();
            //recover
            navigateToLogin();
        } else {
            unblockButtons();
        }
    }

    private void showProgressDialog() {
        mProgressDialog = AuthProgressDialogFragment_.builder()
                .message(mRecoveringMessage)
                .content(mDialogContent)
                .build();
        mProgressDialog.show(getSupportFragmentManager(), mProgressDialogTag);
        mProgressDialog.setCancelable(false);
    }

    private void closeProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    private void blockButtons() {
        mRecoverButton.setClickable(false);
    }

    private void unblockButtons() {
        mRecoverButton.setClickable(true);
    }

    private boolean isInputValid(String login) {
        if (!mInputFieldValidator.isLoginValid(login)) {
            notifyUserWithSnackBar(mInvalidCredentialsMessage);
            return false;
        }
        return true;
    }

    private void notifyUserWithSnackBar(String message) {
        Snackbar.make(mRootView,message,Snackbar.LENGTH_LONG).show();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity_.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
