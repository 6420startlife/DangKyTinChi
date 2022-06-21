package com.ptithcm.dangkytinchi.presenter;

import static com.ptithcm.dangkytinchi.utils.Credentials.MA_SV;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ptithcm.dangkytinchi.adapters.HomeAdapter;
import com.ptithcm.dangkytinchi.fragments.RegisteredFragment;
import com.ptithcm.dangkytinchi.interfaces.HomeInterface;
import com.ptithcm.dangkytinchi.repositories.RegisteredRepository;
import com.ptithcm.dangkytinchi.request.RequestHome;
import com.ptithcm.dangkytinchi.response.ResponseHome;

import java.util.ArrayList;
import java.util.List;

public class RegisteredPresenter {
    private HomeInterface mHomeInterface;
    private RegisteredFragment context;
    private RegisteredRepository mRegisteredRepository;
    private HomeAdapter mRegisteredAdapter;
    private MutableLiveData<List<ResponseHome>> mResponseHomeList;

    public HomeAdapter getmRegisteredAdapter() {
        return mRegisteredAdapter;
    }

    public MutableLiveData<List<ResponseHome>> getmResponseHomeList() {
        return mResponseHomeList;
    }

    public RegisteredPresenter(HomeInterface mHomeInterface, RegisteredFragment context) {
        this.mHomeInterface = mHomeInterface;
        this.context = context;
        this.mResponseHomeList = new MutableLiveData<>();
        mRegisteredRepository = new RegisteredRepository();
        mRegisteredAdapter = new HomeAdapter(getmResponseHomeList().getValue());
        getmResponseHomeList().observe(context, new Observer<List<ResponseHome>>() {
            @Override
            public void onChanged(List<ResponseHome> responseHomeList) {
                if(mRegisteredAdapter == null) {
                    mRegisteredAdapter = new HomeAdapter(responseHomeList);
                    mRegisteredAdapter.notifyDataSetChanged();
                }else{
                    mRegisteredAdapter.setResponseHomeList(responseHomeList);
                    mRegisteredAdapter.notifyDataSetChanged();
                }
            }
        });
        mRegisteredRepository.getIsUpdating().observe(context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) mHomeInterface.turnOnLoading();
                else mHomeInterface.turnOffLoading();
            }
        });
        mRegisteredRepository.getLiveDataRegítered().observe(context, new Observer<List<ResponseHome>>() {
            @Override
            public void onChanged(List<ResponseHome> responseHomeList) {
                mResponseHomeList.postValue(responseHomeList);
            }
        });
        mRegisteredAdapter.getCountSelected().observe(context, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer > 0) {
                    mHomeInterface.enableButtonDangKy();
                }else {
                    mHomeInterface.disableButtonDangKy();
                }
            }
        });
    }

    public void loadData() {
        mRegisteredRepository.loadRegistered(MA_SV);
        mRegisteredAdapter.getCountSelected().postValue(0);
    }

    public void huyDangKy(){
        List<RequestHome> requestHomeList = new ArrayList<>();
        for (ResponseHome responseHome : mResponseHomeList.getValue()) {
            if(responseHome.isCheck()){
                RequestHome requestHome = new RequestHome(responseHome.getMaLTC(), MA_SV);
                requestHomeList.add(requestHome);
                Log.e("ErrorApi", requestHome.getMaLTC());
            }
        }

        mRegisteredRepository.huyDangKyTinChi(requestHomeList,MA_SV);
    }
}
