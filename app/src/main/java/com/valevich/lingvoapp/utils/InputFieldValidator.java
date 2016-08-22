package com.valevich.lingvoapp.utils;

import org.androidannotations.annotations.EBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EBean
public class InputFieldValidator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    //Login validation
    public boolean isLoginValid(String username) {
        return trim(username).length() > 2;
    }

    public boolean isPasswordValid(String password) {
        return trim(password).length() > 5;
    }

    public boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean isPasswordConfirmed(String password, String confirmedPassword) {
        return trim(password).equals(trim(confirmedPassword));
    }

    public boolean isNameValid(String name) {
        return !trim(name).isEmpty();
    }


    public boolean isSurnameValid(String surname) {
        return !trim(surname).isEmpty();
    }

    private String trim(String s) {
        return s.replace(" ","");
    }
}
