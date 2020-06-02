package com.zcz1024.withyou.Adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zcz1024.withyou.Fragments.BaseFragment;

import java.util.List;

public class MyFriendAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;
    private String[] titles;
    private Context context;

    public MyFriendAdapter(FragmentManager fm, List fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fm = null;
        if (position < fragments.size()) {
            fm = fragments.get(position);
        }else {
            fm = fragments.get(0);
        }
        return fm;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.length > 0)
            return titles[position];
        return null;
    }
}
