package com.valevich.lingvoapp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.valevich.lingvoapp.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

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

    @AfterViews
    void setUpViews() {

    }

    @Click(R.id.login_button)
    void onLoginClicked() {
        // TODO: 17.08.2016 Field Validation
        MainActivity_.intent(this).start();
        finish();
    }

    @Click(R.id.forgot_pass)
    void onForgotPassClicked() {
        RecoverActivity_.intent(this).start();
    }

    @Click(R.id.register)
    void onRegisterClicked() {
        RegistrationActivity_.intent(this).start();
    }

}
