package com.ptithcm.dangkytinchi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ptithcm.dangkytinchi.R;
import com.ptithcm.dangkytinchi.interfaces.HomeInterface;
import com.ptithcm.dangkytinchi.presenter.RegisteredPresenter;
import com.ptithcm.dangkytinchi.response.ResponseHome;

import java.util.List;

public class RegisteredFragment extends Fragment implements HomeInterface {
    private SearchView svRegistered;
    private RecyclerView rvRegistered;
    private ProgressBar pbLoadRegistered;
    private SwipeRefreshLayout srlRegistered;
    private Button btnHuyDangKy;

    private RegisteredPresenter mRegisteredPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registered, container, false);
        setControl(view);
        initProgressBar();
        initPresenter();
        initRecyclerView();
        initData();
        setEvent();
        return view;
    }

    private void setEvent() {
        srlRegistered.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
            }
        });
        btnHuyDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegisteredPresenter.huyDangKy();
            }
        });
    }

    private void setControl(View view) {
        svRegistered = view.findViewById(R.id.svRegistered);
        rvRegistered = view.findViewById(R.id.rvRegistered);
        pbLoadRegistered = view.findViewById(R.id.pbLoadRegistered);
        srlRegistered = view.findViewById(R.id.srlRegistered);
        btnHuyDangKy = view.findViewById(R.id.btnHuyDangKy);
    }

    private void initRecyclerView() {
        rvRegistered.setAdapter(mRegisteredPresenter.getmHomeAdapter());
        rvRegistered.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initData() {
        mRegisteredPresenter.loadData();
    }

    private void reloadData() {
        mRegisteredPresenter.loadData();
    }

    private void initProgressBar() {
        pbLoadRegistered.setIndeterminate(true);
    }

    private void initPresenter() {
        mRegisteredPresenter = new RegisteredPresenter(this, this);
    }

    @Override
    public void turnOnLoading() {
        pbLoadRegistered.setVisibility(View.VISIBLE);
    }

    @Override
    public void turnOffLoading() {
        pbLoadRegistered.setVisibility(View.GONE);
        srlRegistered.setRefreshing(false);
    }

    @Override
    public void loadHome(List<ResponseHome> responseHomeList) {
        mRegisteredPresenter.getmHomeAdapter().notifyDataSetChanged();
    }

    @Override
    public void enableButtonDangKy() {
        btnHuyDangKy.setVisibility(View.VISIBLE);
    }

    @Override
    public void disableButtonDangKy() {
        btnHuyDangKy.setVisibility(View.GONE);
    }
}