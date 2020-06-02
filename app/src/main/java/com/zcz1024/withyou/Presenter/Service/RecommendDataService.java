package com.zcz1024.withyou.Presenter.Service;

import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.entity.Follow;
import com.zcz1024.withyou.pojoVO.BookTjVo;
import com.zcz1024.withyou.pojoVO.CurePlanVo;
import com.zcz1024.withyou.pojoVO.MusicVo;
import com.zcz1024.withyou.pojoVO.VideoVo;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecommendDataService {

    @GET("recmd/getAllByCtgy")
    Flowable<ResultData<BookTjVo>> getBook(@Query("current") Integer current, @Query("ctgy") String ctgy);

    @GET("recmd/getAllByCtgy")
    Flowable<ResultData<MusicVo>> getMusic(@Query("current") Integer current, @Query("ctgy") String ctgy);

    @GET("recmd/getAllByCtgy")
    Flowable<ResultData<VideoVo>> getVideo(@Query("current") Integer current, @Query("ctgy") String ctgy);

    @GET("recmd/getAllByCtgy")
    Flowable<ResultData<CurePlanVo>> getCurePlan(@Query("current") Integer current, @Query("ctgy") String ctgy);

}
