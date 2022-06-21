package com.ptithcm.dangkytinchi.repositories;

import static com.ptithcm.dangkytinchi.utils.Credentials.MA_SV;
import static com.ptithcm.dangkytinchi.utils.Credentials.ROLE_SV;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ptithcm.dangkytinchi.models.User;
import com.ptithcm.dangkytinchi.services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private MutableLiveData<Boolean> isSuccess;
    private MutableLiveData<Boolean> isUpdating;

    public static LoginRepository instance;

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    public MutableLiveData<Boolean> getIsSuccess() {
        return isSuccess;
    }

    public MutableLiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }

    public LoginRepository() {
        isSuccess = new MutableLiveData<>();
        isUpdating = new MutableLiveData<>();
    }

    public void login(User user) {
        isUpdating.postValue(true);
        ApiService.getApi().login(user.getUsername(),user.getPassword()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    isSuccess.postValue(false);
                    isUpdating.postValue(false);
                    return;
                }
                if(!response.body().toString().equalsIgnoreCase(ROLE_SV)){
                    isSuccess.postValue(false);
                    isUpdating.postValue(false);
                    return;
                }
                    MA_SV = user.getUsername();
                    isSuccess.postValue(true);
                    isUpdating.postValue(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                isSuccess.postValue(false);
                isUpdating.postValue(false);
                Log.e("ErrorApi", t.getMessage());
            }
        });
    }

    public void renewLiveData() {
        isSuccess.postValue(false);
    }
}
