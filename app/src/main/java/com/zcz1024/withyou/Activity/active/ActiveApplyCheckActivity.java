package com.zcz1024.withyou.Activity.active;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.Adapter.ActiveApplyCheckAdapter;
import com.zcz1024.withyou.Adapter.MyApplyAdapter;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.ActiveDataService;
import com.zcz1024.withyou.Presenter.Service.UserDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.AcountInfo;
import com.zcz1024.withyou.entity.ActApply;
import com.zcz1024.withyou.pojoVO.ActiveApplyVo;
import com.zcz1024.withyou.pojoVO.MyapplyVo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ActiveApplyCheckActivity extends BaseActivity {
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<ActiveApplyVo> applyVos;
    private ActiveApplyCheckAdapter applyCheckAdapter;
    private OnItemClickListener listener;
    private String userid;
    private List<MyapplyVo> myapplyVos;
    private MyApplyAdapter myApplyAdapter;
    private TextView tv_navtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_apply_check);
        initView();
    }

    private void initView() {
        userid = AcountInfo.geteditInfo(getBaseContext(), "userid");
        refreshLayout = findViewById(R.id.refresh_active_apply_check);
        recyclerView = findViewById(R.id.recyclerview_active_apply_check);
        tv_navtitle = findViewById(R.id.tv_nav_apply_title);

        applyCheckAdapter = new ActiveApplyCheckAdapter(null);
        myApplyAdapter = new MyApplyAdapter(null);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider, null));

        recyclerView.addItemDecoration(itemDecoration);


        listener = new OnItemClickListener() {

            @Override
            public void itemClick(int position, View view) {
                ActiveApplyVo actApply = applyVos.get(position);
                switch (view.getId()) {
                    case R.id.btn_active_apply_check_agree:
                        actApply.setActAuditState(1);
                        checkApply(actApply);
                        break;
                    case R.id.btn_active_apply_check_refuse:
                        actApply.setActAuditState(0);
                        checkApply(actApply);
                        break;
                }
            }
        };

        String requestCode = this.getIntent().getStringExtra("requestcode");

        if ("checkApply".equals(requestCode)) {
            tv_navtitle.setText("申请审核");
            applyCheckAdapter.setListener(listener);

            recyclerView.setAdapter(applyCheckAdapter);
            initData();
            //LoadData();
        } else if ("myApply".equals(requestCode)) {
            tv_navtitle.setText("我的申请");
            recyclerView.setAdapter(myApplyAdapter);
            getMyApply();
        } else {
            return;
        }
    }

    private void getMyApply() {
        addDisposable(RetrofitFactory.getRetrofit()
                .create(UserDataService.class)
                .getMypply(userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultData<MyapplyVo>>() {
                    @Override
                    public void accept(ResultData<MyapplyVo> resultData) throws Exception {
                        if (resultData.isSuccess()) {
                            myapplyVos = resultData.getDatalist();
                            myApplyAdapter.setMyapplyVos(myapplyVos);
                        }
                        Toast.makeText(getBaseContext(), resultData.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }


    private void initData() {
        applyVos = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ActiveApplyVo applyVo = new ActiveApplyVo();
            applyVo.setUserId(i+"");
            applyVo.setActAuditState(i%2);
            applyVo.setActApplyReason("测试数据"+i);
            applyVo.setActApplyId("2");
            applyVo.setActId("actid"+i);
            applyVo.setActApplyTime("2020-02-"+i);
            applyVos.add(applyVo);
        }
        applyCheckAdapter.setApplyVos(applyVos);
    }

    private void LoadData() {
        if (userid != null) {
            addDisposable(
                    RetrofitFactory.getRetrofit()
                            .create(ActiveDataService.class)
                            .getApply(1, userid)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ResultData<ActiveApplyVo>>() {
                                @Override
                                public void accept(ResultData<ActiveApplyVo> resultData) throws Exception {
                                    if (resultData.isSuccess()) {
                                        applyVos = resultData.getDatalist();
                                        applyCheckAdapter.setApplyVos(applyVos);
                                    } else {
                                        Toast.makeText(getBaseContext(), resultData.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Toast.makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            })
            );
        }
    }

    private void checkApply(ActiveApplyVo apply) {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(ActiveDataService.class)
                        .checkApply(apply)
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData>() {
                            @Override
                            public void accept(ResultData resultData) throws Exception {
                                if (resultData.isSuccess()) {
                                    Toast.makeText(getBaseContext(), resultData.getMsg(), Toast.LENGTH_SHORT).show();
                                    LoadData();
                                } else {
                                    Toast.makeText(getBaseContext(), resultData.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
        );
    }

    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
