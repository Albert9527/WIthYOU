package com.zcz1024.withyou.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.zcz1024.withyou.Adapter.ActiveAdapter.ActiveTabAdapter;
import com.zcz1024.withyou.Adapter.TabAdapter;
import com.zcz1024.withyou.Fragments.ActiveFragments.AllActiveFragment;
import com.zcz1024.withyou.Fragments.ActiveFragments.MyActMenberFragment;
import com.zcz1024.withyou.Fragments.ActiveFragments.MyActiveFragment;
import com.zcz1024.withyou.R;


import java.util.ArrayList;
import java.util.List;

public class ActiveFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_active, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        tabLayout = view.findViewById(R.id.tab_active);
        viewPager = view.findViewById(R.id.tab_active_viewpager);

        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new AllActiveFragment());
        fragments.add(new MyActiveFragment());
        fragments.add(new MyActMenberFragment());
        FragmentPagerAdapter adapter = new TabAdapter(
                getChildFragmentManager(), fragments, new String[]{"活动列表", "我创建的","我参与的"}
        );
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

}
