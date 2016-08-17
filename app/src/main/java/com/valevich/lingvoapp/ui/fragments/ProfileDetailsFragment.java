package com.valevich.lingvoapp.ui.fragments;

import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.valevich.lingvoapp.R;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_profile_details)
public class ProfileDetailsFragment extends Fragment{

//    @ViewById(R.id.name)
//    EditText mNameField;
//
//    @ViewById(R.id.name)
//    EditText mNameField;
//
//    @ViewById(R.id.name)
//    EditText mNameField;
//
//    @ViewById(R.id.name)
//    EditText mNameField;
//
//    @ViewById(R.id.name)
//    EditText mNameField;
//
//    @ViewById(R.id.name)
//    EditText mNameField;

    @ViewById(R.id.name)
    EditText mNameField;

    @ViewById(R.id.profile_image)
    ImageView mProfileImage;

    @ViewById(R.id.plus)
    ImageView mProfileImageEdit;

    @ViewById(R.id.save)
    Button mSaveButton;
}
