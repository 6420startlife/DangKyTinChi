package com.ptithcm.dangkytinchi.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ptithcm.dangkytinchi.fragments.HomeFragment;
import com.ptithcm.dangkytinchi.fragments.MarksFragment;
import com.ptithcm.dangkytinchi.fragments.RegisteredFragment;
import com.ptithcm.dangkytinchi.fragments.UserFragment;


public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1: return new RegisteredFragment();
            case 2: return new MarksFragment();
            case 3: return new UserFragment();
            case 0:
            default: return  new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
