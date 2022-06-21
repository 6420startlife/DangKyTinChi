package com.ptithcm.dangkytinchi.models;

import static com.ptithcm.dangkytinchi.utils.Regex.regexMaSV;

import android.text.TextUtils;

import java.util.regex.Pattern;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidUsername(){
        return !TextUtils.isEmpty(username) && Pattern.compile(regexMaSV).matcher(username).find();
    }

    public boolean isValidPassword(){
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }
}
