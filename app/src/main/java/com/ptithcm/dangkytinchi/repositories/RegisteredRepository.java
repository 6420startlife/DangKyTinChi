package com.ptithcm.dangkytinchi.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ptithcm.dangkytinchi.request.RequestHome;
import com.ptithcm.dangkytinchi.response.ResponseHome;
import com.ptithcm.dangkytinchi.services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisteredRepository {
    public static RegisteredRepository instance;
    private MutableLiveData<Boolean> isUpdating;
    private MutableLiveData<List<ResponseHome>> liveDataRegítered;

    public static RegisteredRepository getInstance() {
        if(instance == null) {
            instance = new RegisteredRepository();
        }
        return instance;
    }

    public MutableLiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }

    public MutableLiveData<List<ResponseHome>> getLiveDataRegítered() {
        return liveDataRegítered;
    }

    public RegisteredRepository() {
        isUpdating = new MutableLiveData<>();
        liveDataRegítered = new MutableLiveData<>();
        isUpdating.postValue(false);
    }

    public void loadRegistered(String MaSV){
        isUpdating.postValue(true);
        ApiService.getApi().loadRegistered(MaSV).enqueue(new Callback<List<ResponseHome>>() {
            @Override
            public void onResponse(Call<List<ResponseHome>> call, Response<List<ResponseHome>> response) {
                if(!response.isSuccessful()){
                    isUpdating.postValue(false);
                    return;
                }
                liveDataRegítered.postValue(response.body());
                isUpdating.postValue(false);
            }

            @Override
            public void onFailure(Call<List<ResponseHome>> call, Throwable t) {
                Log.e("ErrorApi", t.getMessage());
                isUpdating.postValue(false);
            }
        });
    }

    public void huyDangKyTinChi(List<RequestHome> requestHomeList, String maSV){
        isUpdating.postValue(true);
        ApiService.getApi().huyDangKy(requestHomeList).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    isUpdating.postValue(false);
                    return;
                }
                loadRegistered(maSV);
                isUpdating.postValue(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("ErrorApi", t.getMessage());
                isUpdating.postValue(false);
            }
        });
    }
}
