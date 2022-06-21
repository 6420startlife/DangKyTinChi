package com.ptithcm.dangkytinchi.presenter;

import static com.ptithcm.dangkytinchi.utils.Credentials.MA_SV;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ptithcm.dangkytinchi.adapters.HomeAdapter;
import com.ptithcm.dangkytinchi.fragments.HomeFragment;
import com.ptithcm.dangkytinchi.interfaces.HomeInterface;
import com.ptithcm.dangkytinchi.repositories.HomeRepository;
import com.ptithcm.dangkytinchi.request.RequestHome;
import com.ptithcm.dangkytinchi.response.ResponseHome;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter {
    private HomeInterface mHomeInterface;
    private HomeFragment context;
    private HomeRepository mHomeRepository;
    private HomeAdapter mHomeAdapter;
    private MutableLiveData<List<ResponseHome>> mResponseHomeList;

    public HomeAdapter getmHomeAdapter() {
        return mHomeAdapter;
    }

    public MutableLiveData<List<ResponseHome>> getmResponseHomeList() {
        return mResponseHomeList;
    }

    public HomePresenter(HomeInterface mHomeInterface, HomeFragment context) {
        this.mHomeInterface = mHomeInterface;
        this.context = context;
        this.mResponseHomeList = new MutableLiveData<>();
        mHomeRepository = new HomeRepository();
        mHomeAdapter = new HomeAdapter(getmResponseHomeList().getValue());
        getmResponseHomeList().observe(context, new Observer<List<ResponseHome>>() {
            @Override
            public void onChanged(List<ResponseHome> responseHomeList) {
                if(mHomeAdapter == null) {
                    mHomeAdapter = new HomeAdapter(responseHomeList);
                    mHomeAdapter.notifyDataSetChanged();
                }else{
                    mHomeAdapter.setResponseHomeList(responseHomeList);
                    mHomeAdapter.notifyDataSetChanged();
                }
            }
        });
        mHomeRepository.getIsUpdating().observe(context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) mHomeInterface.turnOnLoading();
                else mHomeInterface.turnOffLoading();
            }
        });
        mHomeRepository.getLiveDataHome().observe(context, new Observer<List<ResponseHome>>() {
            @Override
            public void onChanged(List<ResponseHome> responseHomeList) {
                mResponseHomeList.postValue(responseHomeList);
            }
        });
        mHomeAdapter.getCountSelected().observe(context, new Observer<Integer>() {
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
        mHomeRepository.loadHome(MA_SV);
        mHomeAdapter.getCountSelected().postValue(0);
    }

    public void dangKy(){
        List<RequestHome> requestHomeList = new ArrayList<>();
        for (ResponseHome responseHome : mResponseHomeList.getValue()) {
            if(responseHome.isCheck()){
                RequestHome requestHome = new RequestHome(responseHome.getMaLTC(), MA_SV);
                requestHomeList.add(requestHome);
            }
        }
        mHomeRepository.dangKyTinChi(requestHomeList,MA_SV);
    }
}
