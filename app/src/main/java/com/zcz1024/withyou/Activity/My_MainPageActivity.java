package com.zcz1024.withyou.Activity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.Adapter.DynamicAdapter;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.DynamicDataService;
import com.zcz1024.withyou.Presenter.Service.UserDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.AcountInfo;
import com.zcz1024.withyou.pojoVO.DynamicVo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class My_MainPageActivity extends BaseActivity {
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<DynamicVo> dynamicVos;
    private DynamicAdapter adapter;
    private String userid;

    private BaseFragment.OnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_main_page);

        initView();
    }

    private void initView() {
        userid = AcountInfo.geteditInfo(this,"userid");
        refreshLayout = findViewById(R.id.refresh_mydynmic);
        recyclerView = findViewById(R.id.recyclerview_mydynmic);
        adapter = new DynamicAdapter(null);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider));

        recyclerView.addItemDecoration(itemDecoration);

        listener = new BaseFragment.OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {

            }
        };
        adapter.setListener(listener);

        recyclerView.setAdapter(adapter);

        getMyDynamic();
    }

/*    private void initData() {
        dynamicVos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            DynamicVo dynamicVo = new DynamicVo();
            dynamicVos.add(dynamicVo);
        }
        adapter.setDynamicVosData(dynamicVos);
    }*/

    private void getMyDynamic() {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(DynamicDataService.class)
                        .getMyDynamic(userid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData<DynamicVo>>() {
                            @Override
                            public void accept(ResultData<DynamicVo> resultData) throws Exception {
                                if (resultData.isSuccess()) {
                                    dynamicVos = resultData.getDatalist();
                                    adapter.setDynamicVosData(dynamicVos);
                                }
                                    Toast.makeText(getBaseContext(),resultData.getMsg(),Toast.LENGTH_SHORT).show();
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
