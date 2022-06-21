package com.ptithcm.dangkytinchi.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ptithcm.dangkytinchi.response.ResponseMarks;
import com.ptithcm.dangkytinchi.services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarksRepository {
    public static MarksRepository instance;
    private MutableLiveData<Boolean> isUpdating;
    private MutableLiveData<List<ResponseMarks>> liveDataMarks;

    public static MarksRepository getInstance() {
        if(instance == null) {
            instance = new MarksRepository();
        }
        return instance;
    }

    public MutableLiveData<List<ResponseMarks>> getLiveDataMarks() {
        return liveDataMarks;
    }

    public MutableLiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }

    public MarksRepository() {
        isUpdating = new MutableLiveData<>();
        liveDataMarks = new MutableLiveData<>();
        isUpdating.postValue(false);
    }

    public void loadData(String maSV) {
        isUpdating.postValue(true);
        ApiService.getApi().loadMarks(maSV).enqueue(new Callback<List<ResponseMarks>>() {
            @Override
            public void onResponse(Call<List<ResponseMarks>> call, Response<List<ResponseMarks>> response) {
                if(!response.isSuccessful()) {
                    isUpdating.postValue(false);
                    return;
                }
                liveDataMarks.postValue(response.body());
                isUpdating.postValue(false);
            }

            @Override
            public void onFailure(Call<List<ResponseMarks>> call, Throwable t) {
                isUpdating.postValue(false);
                Log.e("ErrorApi", t.getMessage());
            }
        });
    }
}
