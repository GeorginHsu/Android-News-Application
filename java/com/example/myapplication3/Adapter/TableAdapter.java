package com.example.myapplication3.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication3.Fragment.FatherFragment;

import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends FragmentStateAdapter {
    public List<FatherFragment> fragments = new ArrayList<>();

    public TableAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,List<FatherFragment> fragments) {
        super(fragmentManager, lifecycle);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

}