package com.zcz1024.withyou.Presenter.Service;

import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.entity.QsTestBank.UserTestRecord;
import com.zcz1024.withyou.pojoVO.QsAnalysisVo;
import com.zcz1024.withyou.pojoVO.QsBankVo;
import com.zcz1024.withyou.pojoVO.QsDetailsVo;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface QsBnakDataService {

    @GET("qsBank/getAllQs")
    Flowable<ResultData<QsBankVo>> getAllQsBank(@Query("current") Integer current);

    @GET("qsBank/getQsDetails")
    Flowable<ResultData<QsDetailsVo>> getQsDetails(@Query("qsId") String qsId);

    @POST("qsBank/DoAnalysis")
    Flowable<ResultData<QsAnalysisVo>> getAnalysis(@Body UserTestRecord utrd);
}
