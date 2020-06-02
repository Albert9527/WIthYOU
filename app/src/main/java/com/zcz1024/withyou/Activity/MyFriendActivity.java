package com.zcz1024.withyou.Activity;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.zcz1024.withyou.Adapter.MyFriendAdapter;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.Fragments.FollowAndFans.FansFragment;
import com.zcz1024.withyou.Fragments.FollowAndFans.FollowFragment;
import com.zcz1024.withyou.R;

import java.util.ArrayList;
import java.util.List;

public class MyFriendActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friend);

        initView();
    }

    private void initView() {
        tabLayout = findViewById(R.id.tab_myfriend);
        viewPager = findViewById(R.id.tab_viewpager);

        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new FollowFragment());
        fragments.add(new FansFragment());
        FragmentPagerAdapter adapter = new MyFriendAdapter(
                getSupportFragmentManager(), fragments, new String[]{"我的关注", "我的粉丝"}
        );
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        if (getIntent() != null) {
            Intent intent = getIntent();

            Bundle bundle = intent.getBundleExtra("FragmentCode");
            int code = bundle.getInt("code");

            if (code == 1) {
                tabLayout.getTabAt(1);
            } else {
                tabLayout.getTabAt(0);
            }
        } else
            tabLayout.getTabAt(0);
    }

    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
