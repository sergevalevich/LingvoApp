package com.valevich.lingvoapp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.valevich.lingvoapp.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_recover)
public class RecoverActivity extends AppCompatActivity{

    @ViewById(R.id.login)
    EditText mLoginField;

    @ViewById(R.id.recover_button)
    Button mRecoverButton;

    @Click(R.id.recover_button)
    void onRecoverClicked() {
        navigateToLogIn();
    }

    private void navigateToLogIn() {
        Intent intent = new Intent(this, LoginActivity_.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
