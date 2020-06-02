package com.zcz1024.withyou.Adapter.ActiveAdapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zcz1024.withyou.Fragments.BaseFragment;

import java.util.List;

public class ActiveTabAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    private String[] tabTitles;
    private Context context;

    public ActiveTabAdapter(FragmentManager fm, List fragments, String[] tabTitles) {
        super(fm);
        this.fragments = fragments;
        this.tabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position<fragments.size()){
            fragment = fragments.get(position);
        }else {
            fragment = fragments.get(0);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (tabTitles != null && tabTitles.length > 0)
            return tabTitles[position];
        return null;
    }
}
