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
import com.valevich.lingvoapp.utils.KeyBoardHelper;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@EActivity(R.layout.activity_register)
public class RegistrationActivity extends AppCompatActivity {

    @ViewById(R.id.root)
    LinearLayout mRootView;

    @ViewById(R.id.name)
    EditText mNameField;

    @ViewById(R.id.surname)
    EditText mSurnameField;

    @ViewById(R.id.login)
    EditText mLoginField;

    @ViewById(R.id.email)
    EditText mEmailField;

    @ViewById(R.id.password)
    EditText mPasswordField;

    @ViewById(R.id.password_confirm)
    EditText mConfirmedPasswordField;

    @ViewById(R.id.register_button)
    Button mRegisterButton;

    @StringRes(R.string.auth_dialog_message)
    String mAuthMessage;

    @StringRes(R.string.auth_dialog_content)
    String mAuthDialogContent;

    @StringRes(R.string.progress_dialog_tag)
    String mProgressDialogTag;

    @StringRes(R.string.short_login_msg)
    String mShortLoginMessage;

    @StringRes(R.string.short_pass_msg)
    String mShortPasswordMessage;

    @StringRes(R.string.invalid_email_msg)
    String mInvalidEmailMessage;

    @StringRes(R.string.password_confirm_failed_msg)
    String mPasswordsNotMatchMessage;

    @StringRes(R.string.empty_fields_msg)
    String mEmptyFieldsMessage;

    @Bean
    InputFieldValidator mInputFieldValidator;

    @Bean
    KeyBoardHelper mKeyBoardHelper;

    private AuthProgressDialogFragment mProgressDialog;

    @Click(R.id.register_button)
    void onRegisterClicked() {
        blockButtons();
        hideKeyBoard();

        String login = mLoginField.getText().toString();
        String password = mPasswordField.getText().toString();
        String confirmedPassword = mConfirmedPasswordField.getText().toString();
        String email = mEmailField.getText().toString();
        String name = mNameField.getText().toString();
        String surname = mSurnameField.getText().toString();

        if (isInputValid(login, password,confirmedPassword,email,name,surname)) {
            //showProgressDialog();
            //register
            navigateToMain();
        } else {
            unblockButtons();
        }
    }

    private boolean isInputValid(String login,
                                 String password,
                                 String confirmedPass,
                                 String email,
                                 String name,
                                 String surname) {

        if(!mInputFieldValidator.isNameValid(name) || !mInputFieldValidator.isSurnameValid(surname)) {
            notifyUserWithSnackBar(mEmptyFieldsMessage);
            return false;
        }

        if (!mInputFieldValidator.isLoginValid(login)) {
            notifyUserWithSnackBar(mShortLoginMessage);
            return false;
        }

        if (!mInputFieldValidator.isEmailValid(email)) {
            notifyUserWithSnackBar(mInvalidEmailMessage);
            return false;
        }

        if (!mInputFieldValidator.isPasswordValid(password)) {
            notifyUserWithSnackBar(mShortPasswordMessage);
            return false;
        }

        if (!mInputFieldValidator.isPasswordConfirmed(password,confirmedPass)) {
            notifyUserWithSnackBar(mPasswordsNotMatchMessage);
            return false;
        }
        return true;
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
        mRegisterButton.setClickable(false);
    }

    private void unblockButtons() {
        mRegisterButton.setClickable(true);
    }

    private void hideKeyBoard() {
        mKeyBoardHelper.hideKeyBoard();
    }

    private void notifyUserWithSnackBar(String message) {
        Snackbar.make(mRootView,message,Snackbar.LENGTH_LONG).show();
    }

    private void navigateToMain() {
        Intent intent = MainActivity_.intent(this).get();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
