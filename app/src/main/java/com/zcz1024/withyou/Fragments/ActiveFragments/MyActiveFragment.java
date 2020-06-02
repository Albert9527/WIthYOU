package com.zcz1024.withyou.Fragments.ActiveFragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zcz1024.withyou.Activity.LoginActivity;
import com.zcz1024.withyou.Adapter.ActiveAdapter.ActiveAdapter;
import com.zcz1024.withyou.Adapter.ActiveAdapter.MyActiveAdapter;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.ActiveDataService;
import com.zcz1024.withyou.Presenter.Service.SearchDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.AcountInfo;
import com.zcz1024.withyou.pojoVO.ActiveVo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyActiveFragment extends BaseFragment {
    private List<ActiveVo> activeVos;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private MyActiveAdapter myActiveAdapter;
    private String userid;

    private EditText et_search;
    private ImageView img_search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active_item, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        userid = AcountInfo.geteditInfo(getActivity().getBaseContext(), "userid");

        if (userid == null) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        }
        recyclerView = view.findViewById(R.id.recyclerview_active);
        refreshLayout = view.findViewById(R.id.refresh_active);

        myActiveAdapter = new MyActiveAdapter(null);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                view.getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider, null));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getMyActive();
            }
        });

        et_search = getParentFragment().getView().findViewById(R.id.et_search_act);
        img_search = getParentFragment().getView().findViewById(R.id.img_search_act);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMyAcitve();
            }
        });

        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setAdapter(myActiveAdapter);

        getMyActive();
    }

    @Override
    public void onResume() {
        super.onResume();
        getMyActive();
    }

/*
    private void initData() {
        ArrayList<String> texts = new ArrayList<>();
        for (int i = 0; i <4; i++) {
            String text = "btn" + i;
            texts.add(text);
        }
        activeVos = new ArrayList<>();

        myActiveAdapter.setActiveVoData(activeVos);
    }
*/

    private void getMyActive() {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(ActiveDataService.class)
                        .getMyActive(userid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData<ActiveVo>>() {
                            @Override
                            public void accept(ResultData<ActiveVo> activeVoResultData) throws Exception {
                                if (activeVoResultData.isSuccess()) {
                                    activeVos = activeVoResultData.getDatalist();
                                    myActiveAdapter.setActiveVoData(activeVos);
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
        );
    }

    private void searchMyAcitve() {
        if (userid != null) {
            String keyword = et_search.getText().toString();
            addDisposable(
                    RetrofitFactory.getRetrofit()
                            .create(SearchDataService.class)
                            .searchActive(1, keyword, "optionact", userid)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ResultData<ActiveVo>>() {
                                @Override
                                public void accept(ResultData<ActiveVo> activeData) throws Exception {
                                    if (activeData.isSuccess()) {
                                        activeVos = activeData.getDatalist();
                                        myActiveAdapter.setActiveVoData(activeVos);
                                    } else {
                                        Toast.makeText(getContext(), activeData.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Toast.makeText(getContext(), "网络请求错误" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            })
            );
        }else {
             Toast.makeText(getContext(),"请先登录！",Toast.LENGTH_SHORT).show();
        }
    }
}
