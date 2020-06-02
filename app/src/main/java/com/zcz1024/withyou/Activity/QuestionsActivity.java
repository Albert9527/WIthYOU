package com.zcz1024.withyou.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zcz1024.withyou.Adapter.QuestionsAdapter;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.QsBnakDataService;
import com.zcz1024.withyou.Presenter.Service.SearchDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.QsBankVo;
import com.zcz1024.withyou.pojoVO.QuestionsVo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class QuestionsActivity extends BaseActivity {
    private List<QsBankVo> qsBankVos;
    private QuestionsAdapter questionsAdapter;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private OnItemClickListener listener;

    private EditText et_search;
    private ImageView img_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview_questions);
        refreshLayout = findViewById(R.id.refresh_questions);
        questionsAdapter = new QuestionsAdapter(null);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider));

        recyclerView.addItemDecoration(itemDecoration);

        setListener();

        questionsAdapter.setListener(listener);
        recyclerView.setAdapter(questionsAdapter);

        et_search = findViewById(R.id.et_search_qsbank);
        img_search = findViewById(R.id.img_search_qsbank);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchQSdata();
            }
        });

        loadQsData();


    }

    private void setListener() {
        listener = new OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {
                switch (view.getId()) {
                    case R.id.btn_question_details:
                        Intent intent = new Intent(getBaseContext(), AnswerQsActivity.class);
                        intent.putExtra("qsId",qsBankVos.get(position).getQsId());
                        startActivity(intent);
                }
            }
        };

    }

    private void loadQsData(){
        addDisposable(
                RetrofitFactory.getRetrofit()
                .create(QsBnakDataService.class)
                .getAllQsBank(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultData<QsBankVo>>() {
                    @Override
                    public void accept(ResultData<QsBankVo> qsBankVoResultData) throws Exception {
                        if (qsBankVoResultData.isSuccess()){
                            qsBankVos = qsBankVoResultData.getDatalist();
                            questionsAdapter.setQuestionsVos(qsBankVos);
                        }else
                            Toast.makeText(getBaseContext(),qsBankVoResultData.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getBaseContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }

    private void searchQSdata(){
        String keyword = et_search.getText().toString();
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(SearchDataService.class)
                        .searchQsbank(1,keyword)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData<QsBankVo>>() {
                            @Override
                            public void accept(ResultData<QsBankVo> qsBankVoResultData) throws Exception {
                                if (qsBankVoResultData.isSuccess()){
                                    qsBankVos = qsBankVoResultData.getDatalist();
                                    questionsAdapter.setQuestionsVos(qsBankVos);
                                }else
                                    Toast.makeText(getBaseContext(),qsBankVoResultData.getMsg(),Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getBaseContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        })
        );
    }

/*
    private void initData() {
        q = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            QuestionsVo qss = new QuestionsVo();
            questionsVos.add(qss);
        }
        questionsAdapter.setQuestionsVos(questionsVos);
    }
*/

    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
