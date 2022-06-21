package com.ptithcm.dangkytinchi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.ptithcm.dangkytinchi.R;
import com.ptithcm.dangkytinchi.activities.LoginActivity;
import com.ptithcm.dangkytinchi.interfaces.UserInterface;
import com.ptithcm.dangkytinchi.presenter.UserPresenter;
import com.ptithcm.dangkytinchi.response.ResponseUser;

public class UserFragment extends Fragment implements UserInterface {
    private TextView tvMaSinhVien, tvHoVaTen, tvNgaySinh, tvLop, tvDiaChi;
    private ProgressBar pbLoadUser;
    private AppCompatButton btnLogout;

    private UserPresenter mUserPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        setControl(view);
        initProgressBar();
        initPresenter();
        initData();
        setEvent();
        return view;
    }

    private void initData() {
        mUserPresenter.loadUser();
    }

    private void initPresenter() {
        mUserPresenter = new UserPresenter(this, this);
    }

    private void initProgressBar() {
        pbLoadUser.setIndeterminate(false);
    }

    private void setEvent() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickToLogout();
            }
        });
    }

    private void onClickToLogout() {
        btnLogout.setEnabled(false);
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
        getActivity().finishAffinity();
    }

    private void setControl(View view) {
        tvMaSinhVien = view.findViewById(R.id.tvMaSinhVien);
        tvHoVaTen = view.findViewById(R.id.tvHoVaTen);
        tvNgaySinh = view.findViewById(R.id.tvNgaySinh);
        tvLop = view.findViewById(R.id.tvLop);
        tvDiaChi = view.findViewById(R.id.tvDiaChi);
        pbLoadUser = view.findViewById(R.id.pbLoadUser);
        btnLogout = view.findViewById(R.id.btnLogout);
    }

    @Override
    public void turnOnLoading() {
        pbLoadUser.setVisibility(View.VISIBLE);
    }

    @Override
    public void turnOffLoading() {
        pbLoadUser.setVisibility(View.GONE);
    }

    @Override
    public void loadUser(ResponseUser responseUser) {
        tvMaSinhVien.setText(responseUser.getMasv());
        tvHoVaTen.setText(responseUser.getHoVaTen());
        tvLop.setText(responseUser.getMalop());
        tvNgaySinh.setText(responseUser.getNgaysinh());
        tvDiaChi.setText(responseUser.getDiachi());
    }
}