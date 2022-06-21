package com.ptithcm.dangkytinchi.activities;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ptithcm.dangkytinchi.R;
import com.ptithcm.dangkytinchi.adapters.ViewPagerAdapter;
import com.ptithcm.dangkytinchi.interfaces.ViewPagerInterface;
import com.ptithcm.dangkytinchi.presenter.ViewPagerPresenter;

public class MainActivity extends FragmentActivity implements ViewPagerInterface {
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPagerPresenter mViewPagerPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        initViewPagerAdapter();
        initPresenter();
        setEvent();
    }

    private void initPresenter() {
        mViewPagerPresenter = new ViewPagerPresenter(this);
    }

    private void initViewPagerAdapter() {
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
    }

    private void setEvent() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            mViewPagerPresenter.changeViewPager(item);
            return true;
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mViewPagerPresenter.changeViewPagerOnCallBack(position);
            }
        });
    }

    private void setControl() {
        viewPager2 = findViewById(R.id.viewPager2);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    @Override
    public void switchPageSelected(int position) {
        viewPager2.setCurrentItem(position);
    }

    @Override
    public void changePageOnCallback(int id) {
        bottomNavigationView.getMenu().findItem(id).setChecked(true);
    }
}