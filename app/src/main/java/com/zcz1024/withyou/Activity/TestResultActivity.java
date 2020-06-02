package com.zcz1024.withyou.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.QsBnakDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.entity.QsTestBank.UserTestRecord;
import com.zcz1024.withyou.pojoVO.QsAnalysisVo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestResultActivity extends BaseActivity {
    private TextView tv_socre,tv_level,tv_advise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        initView();
    }

    private void initView(){
        tv_socre=findViewById(R.id.tv_testresult_socre);
        tv_level=findViewById(R.id.tv_testresult_level);
        tv_advise=findViewById(R.id.tv_testresult_advise);

        Bundle bundle = this.getIntent().getExtras();
        UserTestRecord utrd = (UserTestRecord) bundle.getSerializable("testResult");
        tv_socre.setText(utrd.getTrScore()+"");
        sendAnswer(utrd);
    }

    private void sendAnswer(UserTestRecord userTestRecord) {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(QsBnakDataService.class)
                        .getAnalysis(userTestRecord)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData<QsAnalysisVo>>() {
                            @Override
                            public void accept(ResultData<QsAnalysisVo> resultData) throws Exception {
                                if (resultData.isSuccess()) {
                                    setData(resultData.getData());
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

    private void setData(QsAnalysisVo qsAnalysisVo){
        tv_level.setText(qsAnalysisVo.getAlysContent());
        tv_advise.setText(qsAnalysisVo.getAlysProposal());
    }

    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
