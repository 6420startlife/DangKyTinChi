package com.ptithcm.dangkytinchi.interfaces;

import com.ptithcm.dangkytinchi.response.ResponseUser;

public interface UserInterface {
    void turnOnLoading();
    void turnOffLoading();
    void loadUser(ResponseUser responseUser);
}
