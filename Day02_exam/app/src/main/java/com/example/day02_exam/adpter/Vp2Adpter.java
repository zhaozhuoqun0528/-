package com.example.day02_exam.adpter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.day02_exam.bean.TabBean;

import java.util.ArrayList;

public class Vp2Adpter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private ArrayList<TabBean.DataBean> list;

    public Vp2Adpter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<TabBean.DataBean> list) {
        super(fm);
        this.fragments = fragments;
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }
}
