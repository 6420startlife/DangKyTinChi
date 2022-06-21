package com.ptithcm.dangkytinchi.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ptithcm.dangkytinchi.R;
import com.ptithcm.dangkytinchi.interfaces.LoginInterface;
import com.ptithcm.dangkytinchi.models.User;
import com.ptithcm.dangkytinchi.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginInterface {
    private EditText etMaSinhVien, etMatKhau;
    private Button btnLogin;
    private ProgressDialog progressDialog;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setControl();
        initPresenter();
        initProgressDialog();
        setEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initPresenter() {
        mLoginPresenter = new LoginPresenter(this,LoginActivity.this);
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ... ");
    }

    private void setEvent() {
        btnLogin.setOnClickListener(v -> onClickLogin());
    }

    private void onClickLogin() {
        String username = etMaSinhVien.getText().toString().trim();
        String password = etMatKhau.getText().toString().trim();
        User user = new User(username, password);
        mLoginPresenter.login(user);
    }

    private void setControl() {
        etMaSinhVien = findViewById(R.id.etMaSinhVien);
        etMatKhau = findViewById(R.id.etMatKhau);
        btnLogin = findViewById(R.id.btnLogin);
    }

    @Override
    public void invalidUsername() {
        etMaSinhVien.setError("Mã sinh viên không hợp lệ");
    }

    @Override
    public void invalidPassword() {
        etMatKhau.setError("Mật khẩu không hợp lệ");
    }

    @Override
    public void loginSuccess() {
        Log.e("ErrorActivity", "Login Thành công");
        btnLogin.setEnabled(false);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        mLoginPresenter.returnDefault();
        finish();
    }

    @Override
    public void loginFailed() {
        Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void turnOnLoading() {
        progressDialog.show();
    }

    @Override
    public void turnOffLoading() {
        progressDialog.dismiss();
    }
}