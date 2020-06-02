package com.zcz1024.withyou.Activity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zcz1024.withyou.Adapter.AnswerQsAdapter;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.QsBnakDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.AcountInfo;
import com.zcz1024.withyou.entity.QsTestBank.QsAnalysis;
import com.zcz1024.withyou.entity.QsTestBank.UserTestRecord;
import com.zcz1024.withyou.pojoVO.QsAnalysisVo;
import com.zcz1024.withyou.pojoVO.QsDetailsVo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AnswerQsActivity extends BaseActivity {
    private List<QsDetailsVo> qsDetailsVos;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private AnswerQsAdapter answerQsAdapter;
    private String qsId;
    private OnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_qs);

        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview_answer_qs);
        refreshLayout = findViewById(R.id.refresh_answer_qs);
        answerQsAdapter = new AnswerQsAdapter(null);
        final Button btn_sureTest = findViewById(R.id.btn_answer_suretest);
        btn_sureTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sureTest();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider));

        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(answerQsAdapter);

        qsId = this.getIntent().getStringExtra("qsId");

        getQsDetails();
    }

    private UserTestRecord getUtrd() {
        UserTestRecord utrd = new UserTestRecord();
        utrd.setQsId(qsId);
        utrd.setTrAnswer(answerQsAdapter.getAswers().toString());
        utrd.setUserId(AcountInfo.geteditInfo(getBaseContext(), "userid"));
        int count = 0;
        for (int score : answerQsAdapter.getSocre()) {
            count += score;
        }
        utrd.setTrScore(count);

        return utrd;
    }

   /* private void initData() {
        qsDetailsVos = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            QsDetailsVo qss = new QsDetailsVo();
            qsDetailsVos.add(qss);
        }
        answerQsAdapter.setQuestionsVos(qsDetailsVos);
    }*/

    private void getQsDetails() {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(QsBnakDataService.class)
                        .getQsDetails(qsId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData<QsDetailsVo>>() {
                            @Override
                            public void accept(ResultData<QsDetailsVo> resultData) throws Exception {
                                if (resultData.isSuccess()) {
                                    qsDetailsVos = resultData.getDatalist();
                                    answerQsAdapter.setQuestionsVos(qsDetailsVos);
                                } else
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




    private void sureTest() {
        int aswernum = qsDetailsVos.size() - answerQsAdapter.getAswers().size();
        if (aswernum != 0) {
            Toast.makeText(this, "还有" + aswernum + "题未作答", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("zd1024", getUtrd().toString());
                Bundle bundle = new Bundle();
                bundle.putSerializable("testResult", getUtrd());
                Intent intent = new Intent(getBaseContext(), TestResultActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
        }
    }

    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }

}
