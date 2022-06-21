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
import com.ptithcm.dangkytinchi.presenter.HomePresenter;
import com.ptithcm.dangkytinchi.response.ResponseHome;

import java.util.List;

public class HomeFragment extends Fragment implements HomeInterface {
    private SearchView svHome;
    private RecyclerView rvHome;
    private ProgressBar pbLoadHome;
    private SwipeRefreshLayout srlHome;
    private Button btnDangKy;

    private HomePresenter mHomePresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setControl(view);
        initProgressBar();
        initPresenter();
        initRecyclerView();
        initData();
        setEvent();
        return view;
    }

    private void setEvent() {
        srlHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomePresenter.dangKy();
            }
        });
    }

    private void setControl(View view) {
        svHome = view.findViewById(R.id.svHome);
        rvHome = view.findViewById(R.id.rvHome);
        pbLoadHome = view.findViewById(R.id.pbLoadHome);
        srlHome = view.findViewById(R.id.srlHome);
        btnDangKy = view.findViewById(R.id.btnDangKy);
    }

    private void initRecyclerView() {
        rvHome.setAdapter(mHomePresenter.getmHomeAdapter());
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initData() {
        mHomePresenter.loadData();
    }

    private void reloadData() {
        mHomePresenter.loadData();
    }

    private void initProgressBar() {
        pbLoadHome.setIndeterminate(true);
    }

    private void initPresenter() {
        mHomePresenter = new HomePresenter(this, this);
    }

    @Override
    public void turnOnLoading() {
        pbLoadHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void turnOffLoading() {
        pbLoadHome.setVisibility(View.GONE);
        srlHome.setRefreshing(false);
    }

    @Override
    public void loadHome(List<ResponseHome> responseHomeList) {
        mHomePresenter.getmHomeAdapter().notifyDataSetChanged();
    }

    @Override
    public void enableButtonDangKy() {
        btnDangKy.setVisibility(View.VISIBLE);
    }

    @Override
    public void disableButtonDangKy() {
        btnDangKy.setVisibility(View.GONE);
    }

}