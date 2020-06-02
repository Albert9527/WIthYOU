package com.zcz1024.withyou.Activity;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.liji.circleimageview.CircleImageView;
import com.zcz1024.withyou.Activity.active.ActiveAddActitvity;
import com.zcz1024.withyou.Activity.active.ActiveApplyCheckActivity;
import com.zcz1024.withyou.Fragments.ActiveFragment;
import com.zcz1024.withyou.Fragments.DynamicFragment;
import com.zcz1024.withyou.Fragments.RecommendFrgments.HomePageFragment;
import com.zcz1024.withyou.Fragments.ToolsFragment;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.UserDataService;
import com.zcz1024.withyou.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.android.material.navigation.NavigationView;
import com.zcz1024.withyou.Util.AcountInfo;
import com.zcz1024.withyou.Util.DataCleanManager;
import com.zcz1024.withyou.pojoVO.UserVo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class BasePageActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActiveFragment activeFragment;
    private HomePageFragment homePageFragment;
    private DynamicFragment dynamicFragment;
    private ToolsFragment toolsFragment;

    private List<Fragment> fragments;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CircleImageView imgUserTx, navHeadpt;
    private TextView textViewtitle, navNickname, navIntro;

    private ImageView icon_addactiv, icon_adddynamic, icon_msg,img_check_apply;
    private BottomNavigationView bottomNavigationView;
    private int lastSelectedPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_page);
        bottomNavigationView = this.findViewById(R.id.navigation);
        initFragments();
        initView();
        getUserInfo();
    }

    public void initView() {
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav);
        imgUserTx = findViewById(R.id.img_usertx);

        icon_msg = findViewById(R.id.img_homepage_msg);
        icon_addactiv = findViewById(R.id.img_homepage_add_active);
        icon_adddynamic = findViewById(R.id.img_homepage_add_dynmic);
        img_check_apply = findViewById(R.id.img_homepage_check_apply);

        textViewtitle = findViewById(R.id.tv_homepage_title);


        /**
         * 侧滑栏菜单弹出事件
         */

        LinearLayout linearLayout = findViewById(R.id.snavbar);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView);
                } else {
                    drawerLayout.openDrawer(navigationView);
                }
            }
        });

        /**
         * 侧滑栏菜单响应事件
         */
        navigationView.setNavigationItemSelectedListener(navViewclicklisener());
    }

    private NavigationView.OnNavigationItemSelectedListener navViewclicklisener() {

        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.set_Up:
                        startActivity(new Intent(getBaseContext(), SetUpActivity.class));
                        break;
                    case R.id.nav_menu_myapply:
                        Intent intent = new Intent(getBaseContext(),ActiveApplyCheckActivity.class);
                        intent.putExtra("requestcode","myApply");
                        startActivity(intent);
                }
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        };
    }

    private void getUserInfo() {
        String userid = AcountInfo.geteditInfo(this, "userid");
        if (userid != null) {
            addDisposable(
                    RetrofitFactory.getRetrofit()
                            .create(UserDataService.class)
                            .getUserInfo(userid)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ResultData<UserVo>>() {
                                @Override
                                public void accept(ResultData<UserVo> resultData) throws Exception {
                                    if (resultData.isSuccess())
                                        setUserData(resultData.getData());
                                    else
                                        Toast.makeText(getBaseContext(), resultData.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                }
                            })
            );
        }

    }

    private void setUserData(UserVo userVo) {
        View headerView = navigationView.getHeaderView(0);
        navHeadpt = headerView.findViewById(R.id.nav_headpt);
        navNickname = headerView.findViewById(R.id.nav_nickname);
        navIntro = headerView.findViewById(R.id.nav_userintro);
        if (userVo.getIntro() != null)
            navIntro.setText(userVo.getIntro());
        if (userVo.getNickname() != null)
            navNickname.setText(userVo.getNickname());
       /* if (userVo.getAvatar()!=null){
            Glide.with(getBaseContext())
                    .load("http://59.110.221.100:8080/img/"+userVo.getAvatar())
                    .into(navHeadpt);
        }*/
    }

    /**
     * 侧滑栏头像点击事件
     * 如果用户未登录，跳转到登陆界面
     * 如果用户已登录，跳转到用户主页
     * <p>
     * 顶部图标点击事件
     *
     * @param view
     */
    public void TopNavbar(View view) {
        switch (view.getId()) {
            case R.id.nav_headpt:
                break;
        }
    }

    public void IconListener(View view) {
        switch (view.getId()) {
            case R.id.img_homepage_msg:
                startActivity(new Intent(getBaseContext(), NewsActivity.class));
                break;
            case R.id.img_homepage_add_active:
                startActivity(new Intent(getBaseContext(), ActiveAddActitvity.class));
                break;
            case R.id.img_homepage_add_dynmic:
                startActivity(new Intent(getBaseContext(), PublishDynamicActivity.class));
                break;
            case R.id.img_homepage_check_apply:
                Intent intent = new Intent(getBaseContext(),ActiveApplyCheckActivity.class);
                intent.putExtra("requestcode","checkApply");
                startActivity(intent);
                break;
            case R.id.nav_headpt:
                if (AcountInfo.geteditInfo(getBaseContext(), "userid") != null)
                    startActivity(new Intent(getBaseContext(), My_MainPageActivity.class));
                else
                    startActivity(new Intent((getBaseContext()), LoginActivity.class));
                break;
        }
    }

    public void FollowFanslistener(View view) {
        Bundle bundle = new Bundle();
        Intent intent;
        switch (view.getId()) {
            case R.id.layout_follow:
                bundle.putSerializable("code", 0);
                intent = new Intent(getBaseContext(), MyFriendActivity.class);
                intent.putExtra("FragmentCode", bundle);
                startActivity(intent);
                break;
            case R.id.layout_fans:
                bundle.putSerializable("code", 1);
                intent = new Intent(getBaseContext(), MyFriendActivity.class);
                intent.putExtra("FragmentCode", bundle);
                startActivity(intent);
                break;
        }
    }

    private void initFragments() {
        cleanCache();

        //监听切换事件
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //平均布局
        setItemType(bottomNavigationView);


        homePageFragment = new HomePageFragment();
        activeFragment = new ActiveFragment();
        toolsFragment = new ToolsFragment();
        dynamicFragment = new DynamicFragment();

        fragments = new ArrayList<>();
        fragments.add(homePageFragment);
        fragments.add(activeFragment);
        fragments.add(toolsFragment);
        fragments.add(dynamicFragment);
        lastSelectedPosition = 0;

        //默认提交第一个
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment, homePageFragment)//添加
                .show(homePageFragment)//展示
                .commit();//提交
    }

    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    private void setDefaultFragment(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments.get(lastIndex));
        if (!fragments.get(index).isAdded()) {
            transaction.add(R.id.fragment, fragments.get(index));
        }
        //需要展示fragment下标的位置
        //commit：安排该事务的提交。这一承诺不会立即发生;它将被安排在主线程上，以便在线程准备好的时候完成。
        //commitAllowingStateLoss：与 commit类似，但允许在活动状态保存后执行提交。这是危险的，因为如果Activity需要从其状态恢复，
        // 那么提交就会丢失，因此，只有在用户可以意外地更改UI状态的情况下，才可以使用该提交
        transaction.show(fragments.get(index)).commit();
    }

    /**
     * 切换事件
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_homepage:
                if (0 != lastSelectedPosition) {
                    setDefaultFragment(lastSelectedPosition, 0);
                    lastSelectedPosition = 0;
                    setNavIcon(0);
                }
                return true;
            case R.id.navigation_active:
                if (1 != lastSelectedPosition) {
                    setDefaultFragment(lastSelectedPosition, 1);
                    lastSelectedPosition = 1;
                    setNavIcon(1);
                }
                return true;
            case R.id.navigation_tools:
                if (2 != lastSelectedPosition) {
                    setDefaultFragment(lastSelectedPosition, 2);
                    lastSelectedPosition = 2;
                    setNavIcon(2);
                }
                return true;
            case R.id.navigation_dynamic:
                if (3 != lastSelectedPosition) {
                    setDefaultFragment(lastSelectedPosition, 3);
                    lastSelectedPosition = 3;
                    setNavIcon(3);
                }
                return true;
        }
        return false;
    }

    /**
     * 防止超过3个fragment布局不平分
     */
    @SuppressLint("RestrictedApi")
    private void setItemType(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShifting(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    private void setNavIcon(int code) {
        icon_msg.setVisibility(View.GONE);
        icon_addactiv.setVisibility(View.GONE);
        icon_adddynamic.setVisibility(View.GONE);
        img_check_apply.setVisibility(View.GONE);
        switch (code) {
            case 0:
                icon_msg.setVisibility(View.VISIBLE);
                break;
            case 1:
                icon_addactiv.setVisibility(View.VISIBLE);
                img_check_apply.setVisibility(View.VISIBLE);
                break;

            case 3:
                icon_adddynamic.setVisibility(View.VISIBLE);
                break;
            default:
                icon_msg.setVisibility(View.GONE);
                icon_addactiv.setVisibility(View.GONE);
                icon_adddynamic.setVisibility(View.GONE);
                break;
        }
    }

    private void cleanCache() {
        DataCleanManager.clearAllCache(this);
    }
}
