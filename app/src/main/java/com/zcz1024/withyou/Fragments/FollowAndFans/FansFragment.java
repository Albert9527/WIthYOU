package com.zcz1024.withyou.Fragments.FollowAndFans;

import android.content.Context;
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

import com.zcz1024.withyou.Activity.LoginActivity;
import com.zcz1024.withyou.Adapter.FollowFansAdapter;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.SearchDataService;
import com.zcz1024.withyou.Presenter.Service.UserDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.AcountInfo;
import com.zcz1024.withyou.pojoVO.MyFriendVo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FansFragment extends BaseFragment {
    private List<MyFriendVo> myFriendVos;
    private OnItemClickListener listener;
    private RecyclerView recyclerView;
    private FollowFansAdapter followFansAdapter;
    private String userid;
    private EditText et_search;
    private ImageView img_search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fans, container, false);

        userid = AcountInfo.geteditInfo(getContext(),"userid");

        recyclerView = view.findViewById(R.id.rec_myfans);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        followFansAdapter = new FollowFansAdapter(null);

        /**
         * 自定义item的监听
         */
        listener = new OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {

            }
        };
        et_search = view.findViewById(R.id.et_search_fans);
        img_search = view.findViewById(R.id.img_search_fans);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchData();
            }
        });

        //设置下划线
        DividerItemDecoration itemDecoration = new DividerItemDecoration
                (getContext(),DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider,null));
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setAdapter(followFansAdapter);

        getFollowMe();

        return view;
    }

  /*  private void initData() {
        myFriendVos = new ArrayList<>();
        for (int i=0;i<8;i++){
            MyFriendVo myFriendVo = new MyFriendVo();
            myFriendVos.add(myFriendVo);
        }
        followFansAdapter.setData(myFriendVos);
    }*/

    private void getFollowMe(){
        if (userid!=null){
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(UserDataService.class)
                        .getFollowMe(userid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData<MyFriendVo>>() {
                            @Override
                            public void accept(ResultData<MyFriendVo> data) throws Exception {
                                if (data.isSuccess()){
                                    followFansAdapter.setData(data.getDatalist());
                                }else {
                                    Toast.makeText(getContext(),data.getMsg(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        })
        );
    }else
        startActivity(new Intent(getContext(), LoginActivity.class));
    }

    private void searchData(){
        String keyword = et_search.getText().toString();
        if (userid!=null){
            addDisposable(
                    RetrofitFactory.getRetrofit()
                            .create(SearchDataService.class)
                            .searchFollow(1,keyword,userid,"followme")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ResultData<MyFriendVo>>() {
                                @Override
                                public void accept(ResultData<MyFriendVo> data) throws Exception {
                                    if (data.isSuccess()){
                                        followFansAdapter.setData(data.getDatalist());
                                    }else {
                                        Toast.makeText(getContext(),data.getMsg(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Toast.makeText(getContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            })
            );
        }else
            startActivity(new Intent(getContext(), LoginActivity.class));
    }
}
