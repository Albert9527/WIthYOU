package com.zcz1024.withyou.Fragments.RecommendFrgments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.zcz1024.withyou.Adapter.TabAdapter;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.R;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        tabLayout = view.findViewById(R.id.tab_homepage);
        viewPager = view.findViewById(R.id.viewpager_homepage);

        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new BookFragment());
        fragments.add(new VideoFragment());
        fragments.add(new MusicFragment());
        fragments.add(new CurePlanFragment());

        FragmentPagerAdapter adapter = new TabAdapter(
                getChildFragmentManager(), fragments, new String[]{
                "书籍", "影视", "音乐", "自疗方案"
        }
        );
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0);
    }

}
