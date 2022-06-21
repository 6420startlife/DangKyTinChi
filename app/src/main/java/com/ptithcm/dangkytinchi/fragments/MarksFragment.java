package com.ptithcm.dangkytinchi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ptithcm.dangkytinchi.R;
import com.ptithcm.dangkytinchi.adapters.MarksAdapter;
import com.ptithcm.dangkytinchi.interfaces.MarksInterface;
import com.ptithcm.dangkytinchi.presenter.MarksPresenter;
import com.ptithcm.dangkytinchi.response.ResponseMarks;

import java.util.ArrayList;
import java.util.List;

public class MarksFragment extends Fragment implements MarksInterface {
    private SearchView svMarks;
    private RecyclerView rvMarks;
    private ProgressBar pbLoadMarks;
    private SwipeRefreshLayout srlMarks;

    private MarksAdapter mMarksAdapter;
    private MarksPresenter mMarksPresenter;
    private List<ResponseMarks> mResponseMarksList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marks, container, false);
        setControl(view);
        initProgressBar();
        initPresenter();
        initRecyclerView();
        initData();
        setEvent();
        return view;
    }

    private void initRecyclerView() {
        mResponseMarksList = new ArrayList<>();
        mMarksAdapter = new MarksAdapter(mResponseMarksList);
        rvMarks.setAdapter(mMarksAdapter);
        rvMarks.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initData() {
        mMarksPresenter.loadData();
    }

    private void reloadData() {
        mMarksPresenter.loadData();
    }

    private void initProgressBar() {
        pbLoadMarks.setIndeterminate(true);
    }

    private void initPresenter() {
        mMarksPresenter = new MarksPresenter(this, this);
    }

    private void setEvent() {
        srlMarks.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
            }
        });
    }

    private void setControl(View view) {
        svMarks = view.findViewById(R.id.svMarks);
        rvMarks = view.findViewById(R.id.rvMarks);
        pbLoadMarks = view.findViewById(R.id.pbLoadMarks);
        srlMarks = view.findViewById(R.id.srlMarks);
    }

    @Override
    public void turnOnLoading() {
        pbLoadMarks.setVisibility(View.VISIBLE);
    }

    @Override
    public void turnOffLoading() {
        pbLoadMarks.setVisibility(View.GONE);
        srlMarks.setRefreshing(false);
    }

    @Override
    public void loadMarks(List<ResponseMarks> responseMarksList) {
        mResponseMarksList.clear();
        mResponseMarksList.addAll(responseMarksList);
        mMarksAdapter.notifyDataSetChanged();
    }

}