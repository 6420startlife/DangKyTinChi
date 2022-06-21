package com.ptithcm.dangkytinchi.interfaces;

public interface LoginInterface {
    void invalidUsername();
    void invalidPassword();
    void loginSuccess();
    void loginFailed();
    void turnOnLoading();
    void turnOffLoading();
}
