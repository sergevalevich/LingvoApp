package com.valevich.lingvoapp.ui.activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.ui.fragments.dialogs.AuthProgressDialogFragment;
import com.valevich.lingvoapp.ui.fragments.dialogs.AuthProgressDialogFragment_;
import com.valevich.lingvoapp.utils.InputFieldValidator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    @ViewById(R.id.root)
    LinearLayout mRootView;

    @ViewById(R.id.login)
    EditText mLoginField;

    @ViewById(R.id.password)
    EditText mPasswordField;

    @ViewById(R.id.forgot_pass)
    TextView mForgotPassButton;

    @ViewById(R.id.register)
    TextView mRegisterButton;

    @ViewById(R.id.login_button)
    Button mLoginButton;

    @StringRes(R.string.auth_dialog_message)
    String mAuthMessage;

    @StringRes(R.string.auth_dialog_content)
    String mAuthDialogContent;

    @StringRes(R.string.progress_dialog_tag)
    String mProgressDialogTag;

    @StringRes(R.string.invalid_credentials_msg)
    String mInvalidCredentialsMessage;

    @Bean
    InputFieldValidator mInputFieldValidator;

    private AuthProgressDialogFragment mProgressDialog;

    @AfterViews
    void setUpViews() {

    }

    @Click(R.id.login_button)
    void onLoginClicked() {
        blockButtons();

        String login = mLoginField.getText().toString();
        String password = mPasswordField.getText().toString();

        if (isInputValid(login, password)) {
            //showProgressDialog();
            //login
            navigateToMain();
        } else {
            unblockButtons();
        }
    }

    @Click(R.id.forgot_pass)
    void onForgotPassClicked() {
        RecoverActivity_.intent(this).start();
    }

    @Click(R.id.register)
    void onRegisterClicked() {
        RegistrationActivity_.intent(this).start();
    }

    private void showProgressDialog() {
        mProgressDialog = AuthProgressDialogFragment_.builder()
                .message(mAuthMessage)
                .content(mAuthDialogContent)
                .build();
        mProgressDialog.show(getSupportFragmentManager(), mProgressDialogTag);
        mProgressDialog.setCancelable(false);
    }

    private void closeProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    private void blockButtons() {
        mLoginButton.setClickable(false);
        mForgotPassButton.setClickable(false);
        mRegisterButton.setClickable(false);
    }

    private void unblockButtons() {
        mLoginButton.setClickable(true);
        mForgotPassButton.setClickable(true);
        mRegisterButton.setClickable(true);
    }

    private boolean isInputValid(String login, String password) {
        if (!mInputFieldValidator.isLoginValid(login)
                || !mInputFieldValidator.isPasswordValid(password)) {

            notifyUserWithSnackBar(mInvalidCredentialsMessage);
            return false;
        }
        return true;
    }

    private void notifyUserWithSnackBar(String message) {
        Snackbar.make(mRootView,message,Snackbar.LENGTH_LONG).show();
    }

    private void navigateToMain() {
        MainActivity_.intent(this).start();
        finish();
    }

}
