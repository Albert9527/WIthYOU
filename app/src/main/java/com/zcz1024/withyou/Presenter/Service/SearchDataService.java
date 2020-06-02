package com.zcz1024.withyou.Presenter.Service;

import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.pojoVO.ActiveApplyVo;
import com.zcz1024.withyou.pojoVO.ActiveVo;
import com.zcz1024.withyou.pojoVO.BookTjVo;
import com.zcz1024.withyou.pojoVO.CurePlanVo;
import com.zcz1024.withyou.pojoVO.DynamicVo;
import com.zcz1024.withyou.pojoVO.MenberVo;
import com.zcz1024.withyou.pojoVO.MusicVo;
import com.zcz1024.withyou.pojoVO.MyFriendVo;
import com.zcz1024.withyou.pojoVO.QsBankVo;
import com.zcz1024.withyou.pojoVO.VideoVo;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchDataService {
    @GET("search/rcm")
    Flowable<ResultData<BookTjVo>> searchrcmbook(@Query("current") Integer current,
                                                 @Query("keyWord") String keyword,
                                                 @Query("ctgy") String ctgy);

    @GET("search/rcm")
    Flowable<ResultData<MusicVo>> searchrcmmusic(@Query("current") Integer current,
                                                 @Query("keyWord") String keyword,
                                                 @Query("ctgy") String ctgy);

    @GET("search/rcm")
    Flowable<ResultData<VideoVo>> searchrcmvideos(@Query("current") Integer current,
                                                  @Query("keyWord") String keyword,
                                                  @Query("ctgy") String ctgy);

    @GET("search/rcm")
    Flowable<ResultData<CurePlanVo>> searchrcmcp(@Query("current") Integer current,
                                                 @Query("keyWord") String keyword,
                                                 @Query("ctgy") String ctgy);

    @GET("search/act")
    Flowable<ResultData<ActiveVo>> searchActive(@Query("current") Integer current,
                                                @Query("keyWord") String keyword,
                                                @Query("ctgy") String ctgy,
                                                @Query("userid") String userid);
    @GET("search/act")
    Flowable<ResultData<ActiveApplyVo>> searchActApply(@Query("current") Integer current,
                                                     @Query("keyWord") String keyword,
                                                     @Query("ctgy") String ctgy);
    @GET("search/act")
    Flowable<ResultData<MenberVo>> searchActMenber(@Query("current") Integer current,
                                                @Query("keyWord") String keyword,
                                                @Query("ctgy") String ctgy);

    @GET("search/dynamic")
    Flowable<ResultData<DynamicVo>> searchDynamic(@Query("current") Integer current,
                                                  @Query("keyWord") String keyword,
                                                  @Query("userid") String userid);

    @GET("search/qsbank")
    Flowable<ResultData<QsBankVo>> searchQsbank(@Query("current") Integer current,
                                                @Query("keyWord") String keyword);

    @GET("search/friend")
    Flowable<ResultData<MyFriendVo>> searchFollow(@Query("current") Integer current,
                                                    @Query("keyWord") String keyword,
                                                    @Query("userid") String userid,
                                                    @Query("ctgy") String ctgy);


}
