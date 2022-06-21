package com.ptithcm.dangkytinchi.presenter;

import android.view.MenuItem;

import com.ptithcm.dangkytinchi.R;
import com.ptithcm.dangkytinchi.interfaces.ViewPagerInterface;

public class ViewPagerPresenter {
    private final ViewPagerInterface viewPagerInterface;
    public ViewPagerPresenter(ViewPagerInterface viewPagerInterface) {
        this.viewPagerInterface = viewPagerInterface;
    }

    public void changeViewPager(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.menu_item_home) {
            viewPagerInterface.switchPageSelected(0);
        }else if(id == R.id.menu_item_registered) {
            viewPagerInterface.switchPageSelected(1);
        }else if(id == R.id.menu_item_marks) {
            viewPagerInterface.switchPageSelected(2);
        }else {
            viewPagerInterface.switchPageSelected(3);
        }
    }
    public void changeViewPagerOnCallBack(int position) {
        switch (position) {
            case 1: viewPagerInterface.changePageOnCallback(R.id.menu_item_registered);
                break;
            case 2: viewPagerInterface.changePageOnCallback(R.id.menu_item_marks);
                break;
            case 3: viewPagerInterface.changePageOnCallback(R.id.menu_item_user);
                break;
            case 0:
            default: viewPagerInterface.changePageOnCallback(R.id.menu_item_home);
                break;
        }
    }
}
