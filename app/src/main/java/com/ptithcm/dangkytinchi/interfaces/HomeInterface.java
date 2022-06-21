package com.ptithcm.dangkytinchi.interfaces;

import com.ptithcm.dangkytinchi.response.ResponseHome;

import java.util.List;

public interface HomeInterface {
    void turnOnLoading();
    void turnOffLoading();
    void loadHome(List<ResponseHome> responseHomeList);
    void enableButtonDangKy();
    void disableButtonDangKy();
}
