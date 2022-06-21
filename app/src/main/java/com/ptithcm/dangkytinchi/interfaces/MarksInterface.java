package com.ptithcm.dangkytinchi.interfaces;

import com.ptithcm.dangkytinchi.response.ResponseMarks;

import java.util.List;

public interface MarksInterface {
    void turnOnLoading();
    void turnOffLoading();
    void loadMarks(List<ResponseMarks> responseMarksList);
}
