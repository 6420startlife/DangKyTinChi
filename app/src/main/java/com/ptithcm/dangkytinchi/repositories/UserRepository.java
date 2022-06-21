package com.ptithcm.dangkytinchi.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ptithcm.dangkytinchi.response.ResponseUser;
import com.ptithcm.dangkytinchi.services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private MutableLiveData<Boolean> isUpdating;
    private MutableLiveData<ResponseUser> liveDataUser;

    public UserRepository() {
        this.liveDataUser = new MutableLiveData<>();
        this.isUpdating = new MutableLiveData<>();
        isUpdating.postValue(false);
    }

    public MutableLiveData<ResponseUser> getLiveDataUser() {
        return liveDataUser;
    }

    public MutableLiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }

    public void loadData(String maSV){
        isUpdating.postValue(true);
        ApiService.getApi().loadUser(maSV).enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if(!response.isSuccessful()) {
                    isUpdating.postValue(false);
                    return;
                }
                liveDataUser.postValue(response.body());
                isUpdating.postValue(false);
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                Log.e("ErrorApi", t.getMessage());
                isUpdating.postValue(false);
            }
        });
    }
}
