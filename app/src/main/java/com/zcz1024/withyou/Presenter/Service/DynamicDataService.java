package com.zcz1024.withyou.Presenter.Service;

import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.entity.Dynamic;
import com.zcz1024.withyou.pojoVO.DynamicVo;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface DynamicDataService {

    @GET("dynamic/")
    Flowable<ResultData<DynamicVo>> getDynamicData(@Query("userid") String userid);

    @GET("dynamic/MyDynamic")
    Flowable<ResultData<DynamicVo>> getMyDynamic(@Query("userid") String userid);

    @Multipart
    @POST("dynamic/Add")
    Flowable<ResultData> addDynamic(@Query("userid") String userid,
                                    @Query("content") String content,
                                    @Part() MultipartBody.Part pic);

    @GET("dynamic/Delete")
    Flowable<ResultData> deleteDynamic(@Query("dynamicId") String dynamicId);

    @FormUrlEncoded
    @POST("dynamic/pubNopic")
    Flowable<ResultData> pubNopic(@Query("userid") String userid,
                                    @Query("content") String content);
}
