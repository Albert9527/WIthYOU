package com.zcz1024.withyou.Presenter.Service;

import android.text.Editable;

import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.entity.ActApply;
import com.zcz1024.withyou.entity.Menber;
import com.zcz1024.withyou.pojoVO.ActiveApplyVo;
import com.zcz1024.withyou.pojoVO.ActiveVo;
import com.zcz1024.withyou.pojoVO.MenberVo;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ActiveDataService {

    @GET("activity/getAll")
    Flowable<ResultData<ActiveVo>> getAllActive(@Query("current") Integer current);

    @GET("activity/getActInfo")
    Flowable<ResultData<ActiveVo>> getActInfo(@Query("actId") String actId);

    @Multipart
    @POST("activity/add")
    Flowable<ResultData> addActive(
            @PartMap Map<String,Object> activity,
            @Part MultipartBody.Part pic
    );

    @GET("activity/delete")
    Flowable<ResultData> deleteActivity(@Query("actId") String actId);

    @GET("activity/getMyActAdmin")
    Flowable<ResultData<ActiveVo>> getMyActive(@Query("userid") String userid);

    @GET("activity/getMyAct")
    Flowable<ResultData<ActiveVo>> getMenberAct(@Query("userid") String userid);

    @GET("activity/getApply")
    Flowable<ResultData<ActiveApplyVo>> getApply(@Query("current") Integer current, @Query("userId") String userId);

    @POST("activity/checkApply")
    Flowable<ResultData> checkApply(@Body ActiveApplyVo actApply);

    @GET("activity/getMenber")
    Flowable<ResultData<MenberVo>> getMenber(@Query("actId") String actId);
}
