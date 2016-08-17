package com.valevich.lingvoapp.ui.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.valevich.lingvoapp.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_register)
public class RegistrationActivity extends AppCompatActivity {

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

    @ViewById(R.id.register_button)
    Button mRegisterButton;

    @Click(R.id.register_button)
    void onRegisterClicked() {
        // TODO: 17.08.2016 validation
        Intent intent = MainActivity_.intent(this).get();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
