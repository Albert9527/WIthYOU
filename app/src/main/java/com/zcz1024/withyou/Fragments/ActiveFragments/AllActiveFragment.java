package com.zcz1024.withyou.Fragments.ActiveFragments;


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
import com.zcz1024.withyou.Adapter.ActiveAdapter.ActiveAdapter;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.ActiveDataService;
import com.zcz1024.withyou.Presenter.Service.SearchDataService;
import com.zcz1024.withyou.Presenter.Service.UserDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.CreatDialogUtil;
import com.zcz1024.withyou.pojoVO.ActiveVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AllActiveFragment extends BaseFragment {
    private List<ActiveVo> activeVos;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private ActiveAdapter activeAdapter;
    private CreatDialogUtil dialogUtil;
    private OnItemClickListener listener;

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
        recyclerView = view.findViewById(R.id.recyclerview_active);
        refreshLayout = view.findViewById(R.id.refresh_active);

        activeAdapter = new ActiveAdapter(null);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                view.getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider, null));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getActiveData();
            }
        });
        recyclerView.addItemDecoration(itemDecoration);

        getActiveData();

        listener = new OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {
                switch (view.getId()) {
                    case R.id.btn_active_apply_now:
                        showEditDialog(activeVos.get(position));
                        break;
                    case R.id.btn_active_details:
                        Toast.makeText(getContext(),view.getTextDirection()+"被点击",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        et_search = getParentFragment().getView().findViewById(R.id.et_search_act);
        img_search = getParentFragment().getView().findViewById(R.id.img_search_act);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAcitve();
            }
        });


        activeAdapter.setListener(listener);
        recyclerView.setAdapter(activeAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        getActiveData();
    }

   /* private void initData() {
        ArrayList<String> texts = new ArrayList<>();
        for (int i = 0; i <6; i++) {
            String text = "btn" + i;
            texts.add(text);
        }
        activeVos = new ArrayList<>();
        for (int i =0;i<8;i++){
            ActiveVo activeVo = new ActiveVo(texts);
            activeVos.add(activeVo);
        }

        activeAdapter.setActiveVoData(activeVos);
    }*/

    private void getActiveData() {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(ActiveDataService.class)
                        .getAllActive(1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData<ActiveVo>>() {
                            @Override
                            public void accept(ResultData<ActiveVo> activeData) throws Exception {
                                if (activeData.isSuccess()) {
                                    activeVos = activeData.getDatalist();
                                    activeAdapter.setActiveVoData(activeVos);
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

    }

    private void searchAcitve() {
        String keyword = et_search.getText().toString();
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(SearchDataService.class)
                        .searchActive(1,keyword,"allact",null)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData<ActiveVo>>() {
                            @Override
                            public void accept(ResultData<ActiveVo> activeData) throws Exception {
                                if (activeData.isSuccess()) {
                                    activeVos = activeData.getDatalist();
                                    activeAdapter.setActiveVoData(activeVos);
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

    }

    private void ApplyActive(Map map) {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(UserDataService.class)
                        .ApplyAct(map)
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData>() {
                            @Override
                            public void accept(ResultData resultData) throws Exception {
                                if (resultData.isSuccess()) {
                                    Toast.makeText(getContext(), resultData.getMsg(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), resultData.getMsg(), Toast.LENGTH_SHORT).show();
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


    public void showEditDialog(final ActiveVo activeVo) {
        View.OnClickListener clicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = dialogUtil.getApplymap(activeVo.getActId());
                ApplyActive(map);
            }
        };
        dialogUtil = new CreatDialogUtil(this.getActivity(), 0, clicklistener, activeVo.getActTitle());
        dialogUtil.show();
    }

}
