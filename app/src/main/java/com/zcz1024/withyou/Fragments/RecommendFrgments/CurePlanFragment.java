package com.zcz1024.withyou.Fragments.RecommendFrgments;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zcz1024.withyou.Adapter.RecommendAdapter.BookAdapter;
import com.zcz1024.withyou.Adapter.RecommendAdapter.CurePlanAdapter;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.RecommendDataService;
import com.zcz1024.withyou.Presenter.Service.SearchDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.entity.Recommend.CurePlan;
import com.zcz1024.withyou.pojoVO.BookTjVo;
import com.zcz1024.withyou.pojoVO.CurePlanVo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CurePlanFragment extends BaseFragment{
    private RecyclerView recyclerView;
    private CurePlanAdapter curePlanAdapter;
    private List<CurePlanVo> curePlanVos;
    private OnItemClickListener listener;

    private EditText et_search;
    private ImageView img_search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tj_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_tj);

        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),2));

        //设置适配器
        curePlanAdapter = new CurePlanAdapter(null);
        listener = new OnItemClickListener(){

            @Override
            public void itemClick(int position, View view) {

            }
        };

        et_search = view.findViewById(R.id.et_search_tj);
        img_search = view.findViewById(R.id.img_search_tj);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchData();
            }
        });

        curePlanAdapter.setListener(listener);

        //设置下划线
        DividerItemDecoration itemDecoration = new DividerItemDecoration
                (getContext(),DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider));
        recyclerView.addItemDecoration(itemDecoration);

        /*//全屏水滴刷新
        RefreshUtil.refresh(getContext(), view, R.id.refresh_allteam,
                new OnRefreshListener() {
                    @Override
                    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                        loadData();
                        refreshLayout.finishRefresh(1000);
                    }
                });*/

        recyclerView.setAdapter(curePlanAdapter);

        searchData();
        return view;
    }

       /* private void  initData(){
            bookTjVos = new ArrayList<>();
            for (int i=0;i<8;i++){
                BookTjVo book = new BookTjVo();
                bookTjVos.add(book);
            }
            initData(bookTjVos);
    }*/

    private void getBookData(){
        addDisposable(
                RetrofitFactory.getRetrofit()
                .create(RecommendDataService.class)
                .getCurePlan(1,"cureplan")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultData<CurePlanVo>>() {
                    @Override
                    public void accept(ResultData<CurePlanVo> resultData) throws Exception {
                        if (resultData.isSuccess()){
                            curePlanVos = resultData.getDatalist();
                            initData(curePlanVos);
                        }
                        Toast.makeText(getContext(),resultData.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getContext(),"数据获取失败,错误原因："+throwable.getMessage(),Toast.LENGTH_LONG).show();
                    }
                })

        );
    }

    private void searchData(){
        String keyword = et_search.getText().toString();
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(SearchDataService.class)
                        .searchrcmcp(1,keyword,"cureplan")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData<CurePlanVo>>() {
                            @Override
                            public void accept(ResultData<CurePlanVo> resultData) throws Exception {
                                if (resultData.isSuccess()){
                                    curePlanVos = resultData.getDatalist();
                                    initData(curePlanVos);
                                }
                                Toast.makeText(getContext(),resultData.getMsg(),Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        })

        );
    }

    private void initData(List curePlanVos){
        curePlanAdapter.setCurePlanVos(curePlanVos);
    }
}
