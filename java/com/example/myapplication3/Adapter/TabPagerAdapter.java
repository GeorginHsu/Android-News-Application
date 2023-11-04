package com.example.myapplication3.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication3.Fragment.TabFirstFragment;
import com.example.myapplication3.Fragment.TabSecondFragment;
import com.example.myapplication3.Fragment.TabThirdFragment;
import com.example.myapplication3.DataDealer.GetURLString;

public class TabPagerAdapter extends FragmentPagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            String url = GetURLString.GetString("","","","");
            return new TabFirstFragment(url);

        } else if (position == 1) {
            return new TabSecondFragment();
        } else if (position == 2) {
            return new TabThirdFragment();
        } else {
            return null;
        }


    }
    @Override
    public int getCount () {
        return 3;
    }
}
