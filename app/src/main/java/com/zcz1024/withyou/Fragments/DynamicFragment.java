package com.zcz1024.withyou.Fragments;

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
import com.zcz1024.withyou.Adapter.DynamicAdapter;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.DynamicDataService;
import com.zcz1024.withyou.Presenter.Service.SearchDataService;
import com.zcz1024.withyou.Presenter.Service.UserDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.AcountInfo;
import com.zcz1024.withyou.pojoVO.DynamicVo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DynamicFragment extends BaseFragment {
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<DynamicVo> dynamicVos;
    private DynamicAdapter adapter;

    private EditText et_search;
    private ImageView img_search;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerview_dynamic);
        refreshLayout = view.findViewById(R.id.refresh_dynmic);
        adapter = new DynamicAdapter(null);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider,null));

        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);

        et_search = view.findViewById(R.id.et_search_dy);
        img_search = view.findViewById(R.id.img_search_dy);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDynamic();
            }
        });


        searchDynamic();
    }

   /* private void initData() {
        dynamicVos = new ArrayList<>();
        for (int i=0;i<8;i++){
            DynamicVo dynamicVo = new DynamicVo();
            dynamicVos.add(dynamicVo);
        }
        adapter.setDynamicVosData(dynamicVos);
    }*/

    private void getDynamicData(){
        addDisposable(
                RetrofitFactory.getRetrofit()
                .create(DynamicDataService.class)
                .getDynamicData(AcountInfo.geteditInfo(getContext(),"userid"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultData<DynamicVo>>() {
                    @Override
                    public void accept(ResultData<DynamicVo> resultData) throws Exception {
                        if (resultData.isSuccess()){
                            dynamicVos=resultData.getDatalist();
                            Toast.makeText(getContext(),resultData.getMsg(),Toast.LENGTH_SHORT).show();
                            adapter.setDynamicVosData(dynamicVos);
                        }else{
                            Toast.makeText(getContext(),resultData.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }

    private void searchDynamic(){
        String userid = AcountInfo.geteditInfo(getContext(),"userid");
        String keyword = et_search.getText().toString();
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(SearchDataService.class)
                        .searchDynamic(1,keyword,userid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData<DynamicVo>>() {
                            @Override
                            public void accept(ResultData<DynamicVo> resultData) throws Exception {
                                if (resultData.isSuccess()){
                                    dynamicVos=resultData.getDatalist();
                                    Toast.makeText(getContext(),resultData.getMsg(),Toast.LENGTH_SHORT).show();
                                    adapter.setDynamicVosData(dynamicVos);
                                }else{
                                    Toast.makeText(getContext(),resultData.getMsg(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        })
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        getDynamicData();
    }
}
