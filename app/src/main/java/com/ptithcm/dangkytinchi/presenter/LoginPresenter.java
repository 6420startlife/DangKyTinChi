package com.ptithcm.dangkytinchi.presenter;


import com.ptithcm.dangkytinchi.activities.LoginActivity;
import com.ptithcm.dangkytinchi.interfaces.LoginInterface;
import com.ptithcm.dangkytinchi.models.User;
import com.ptithcm.dangkytinchi.repositories.LoginRepository;

public class LoginPresenter{
    private final LoginInterface mLoginInterface;
    private final LoginRepository mLoginRepository;
    private final LoginActivity context;
    private boolean isClicking = false;

    public LoginRepository getmLoginRepository() {
        return mLoginRepository;
    }

    public LoginPresenter(LoginInterface mLoginInterface, LoginActivity context) {
        this.mLoginInterface = mLoginInterface;
        this.context = context;
        mLoginRepository = LoginRepository.getInstance();
        mLoginRepository.getIsUpdating().observe(context, aBoolean -> {
            if(aBoolean) {
                mLoginInterface.turnOnLoading();
            }else {
                mLoginInterface.turnOffLoading();
            }
        });
        mLoginRepository.getIsSuccess().observe(context, aBoolean -> {
            if(!isClicking) {
                return;
            }
            if(aBoolean) {
                mLoginInterface.loginSuccess();
                isClicking = false;
            }else {
                mLoginInterface.loginFailed();
                isClicking = false;
            }
        });
    }

    public void login(User user){
        isClicking = true;
        if(user.isValidUsername() && user.isValidPassword()){
            checkLogin(user);
            return;
        }
        if(!user.isValidUsername()) {
            mLoginInterface.invalidUsername();
        }
        if(!user.isValidPassword()) {
            mLoginInterface.invalidPassword();
        }
    }

    private void checkLogin(User user) {
        mLoginRepository.login(user);
    }

    public void returnDefault() {
        mLoginRepository.renewLiveData();
    }
}
