package com.valevich.lingvoapp.ui.fragments;

import android.support.v4.app.Fragment;
import android.text.method.KeyListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.valevich.lingvoapp.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_profile_details)
@OptionsMenu(R.menu.menu_profile_details)
public class ProfileDetailsFragment extends Fragment{

    @ViewById(R.id.name)
    EditText mNameField;

    @ViewById(R.id.surname)
    EditText mSurnameField;

    @ViewById(R.id.age)
    EditText mAgeField;

    @ViewById(R.id.email)
    EditText mEmailField;

    @ViewById(R.id.password)
    EditText mPasswordField;

    @ViewById(R.id.password_old)
    EditText mOldPasswordField;

    @ViewById(R.id.password_confirm)
    EditText mConfirmPasswordField;

    @ViewById(R.id.profile_image)
    ImageView mProfileImage;

    @ViewById(R.id.plus)
    ImageView mProfileImageEdit;

    @ViewById(R.id.save)
    Button mSaveButton;

    @OptionsMenuItem(R.id.action_edit)
    MenuItem mEditMenuIcon;

    @OptionsItem(R.id.action_edit)
    void onEditClicked() {
        mProfileImageEdit.setVisibility(View.VISIBLE);
        mSaveButton.setVisibility(View.VISIBLE);
        mConfirmPasswordField.setVisibility(View.VISIBLE);
        unBlockInput();
    }

    @AfterViews
    void setUpViews() {
        blockInput();
    }

    private void blockInput() {
        mNameField.setTag(mNameField.getKeyListener());
        mNameField.setKeyListener(null);

        mSurnameField.setTag(mSurnameField.getKeyListener());
        mSurnameField.setKeyListener(null);

        mEmailField.setTag(mEmailField.getKeyListener());
        mEmailField.setKeyListener(null);

        mAgeField.setTag(mAgeField.getKeyListener());
        mAgeField.setKeyListener(null);

        mPasswordField.setTag(mPasswordField.getKeyListener());
        mPasswordField.setKeyListener(null);

        mOldPasswordField.setTag(mOldPasswordField.getKeyListener());
        mOldPasswordField.setKeyListener(null);
    }

    private void unBlockInput() {
        mNameField.setKeyListener((KeyListener) mNameField.getTag());
        mSurnameField.setKeyListener((KeyListener) mSurnameField.getTag());
        mEmailField.setKeyListener((KeyListener) mEmailField.getTag());
        mAgeField.setKeyListener((KeyListener) mAgeField.getTag());
        mPasswordField.setKeyListener((KeyListener) mPasswordField.getTag());
        mOldPasswordField.setKeyListener((KeyListener) mOldPasswordField.getTag());
    }

}
