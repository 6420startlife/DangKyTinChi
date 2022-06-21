package com.ptithcm.dangkytinchi.presenter;

import static com.ptithcm.dangkytinchi.utils.Credentials.MA_SV;

import androidx.lifecycle.Observer;

import com.ptithcm.dangkytinchi.fragments.MarksFragment;
import com.ptithcm.dangkytinchi.interfaces.MarksInterface;
import com.ptithcm.dangkytinchi.repositories.MarksRepository;
import com.ptithcm.dangkytinchi.response.ResponseMarks;

import java.util.List;

public class MarksPresenter {
    private final MarksInterface mMarksInterface;
    private final MarksRepository mMarksRepository;
    private final MarksFragment context;

    public MarksPresenter(MarksInterface marksInterface, MarksFragment context) {
        this.mMarksInterface = marksInterface;
        this.context = context;
        this.mMarksRepository = MarksRepository.getInstance();
        mMarksRepository.getIsUpdating().observe(context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    mMarksInterface.turnOnLoading();
                }else {
                    mMarksInterface.turnOffLoading();
                }
            }
        });
        mMarksRepository.getLiveDataMarks().observe(context, new Observer<List<ResponseMarks>>() {
            @Override
            public void onChanged(List<ResponseMarks> responseMarksList) {
                mMarksInterface.loadMarks(responseMarksList);
            }
        });
    }
    public void loadData() {
        mMarksRepository.loadData(MA_SV);
    }
}
