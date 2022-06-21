package com.ptithcm.dangkytinchi.presenter;

import static com.ptithcm.dangkytinchi.utils.Credentials.MA_SV;

import androidx.lifecycle.Observer;

import com.ptithcm.dangkytinchi.fragments.UserFragment;
import com.ptithcm.dangkytinchi.interfaces.UserInterface;
import com.ptithcm.dangkytinchi.repositories.UserRepository;
import com.ptithcm.dangkytinchi.response.ResponseUser;

public class UserPresenter {
    private final UserInterface mUserInterface;
    private final UserRepository mUserRepository;
    private final UserFragment context;

    public UserPresenter(UserInterface mUserInterface, UserFragment context) {
        this.mUserInterface = mUserInterface;
        this.mUserRepository = new UserRepository();
        this.context = context;
        mUserRepository.getIsUpdating().observe(context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    mUserInterface.turnOnLoading();
                }else {
                    mUserInterface.turnOffLoading();
                }
            }
        });
        mUserRepository.getLiveDataUser().observe(context, new Observer<ResponseUser>() {
            @Override
            public void onChanged(ResponseUser responseUser) {
                mUserInterface.loadUser(responseUser);
            }
        });
    }
    public void loadUser() {
        mUserRepository.loadData(MA_SV);
    }
}
