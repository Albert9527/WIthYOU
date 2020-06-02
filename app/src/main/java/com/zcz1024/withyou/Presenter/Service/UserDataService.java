package com.zcz1024.withyou.Presenter.Service;

import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.entity.ActApply;
import com.zcz1024.withyou.pojoVO.ActiveApplyVo;
import com.zcz1024.withyou.pojoVO.MyFriendVo;
import com.zcz1024.withyou.pojoVO.MyapplyVo;
import com.zcz1024.withyou.pojoVO.UserVo;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface UserDataService {


    /**
     * 注册接口请求，返回一个map，包含一个success（true和false）和error(状态码)
     * @param acountInfo
     * @return
     */
    @FormUrlEncoded
    @POST("Acount/API/signup")
    Flowable<ResultData> singUp(@FieldMap Map<String,String> acountInfo);


    /**
     * 登陆接口请求。返回状态码
     * @param acount 用户账户{username，password}
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Flowable<ResultData<UserVo>> LoginTest(@FieldMap Map<String,String> acount);



    /**
     * 根据用户id获取用户基本信息
     * @param userid
     * @return
     */
    @GET("user/getinfo")
    Flowable<ResultData<UserVo>> getUserInfo(@Query("userid") String userid);

    @FormUrlEncoded
    @POST("user/updatepswd")
    Flowable<ResultData> updatePswd(@FieldMap Map<String,String> newInfo);;

    @GET("user/MyFollow")
    Flowable<ResultData<MyFriendVo>> getMyFollow(@Query("userid") String userid);

    @GET("user/FollowMe")
    Flowable<ResultData<MyFriendVo>> getFollowMe(@Query("userId") String userId);

    @GET("user/addFollow")
    Flowable<ResultData> addFollow(@Query("userId") String userId,@Query("targetId") String targetId);

    @GET("user/cancelFollow")
    Flowable<ResultData> cancelFollow(@Query("userId") String userId,@Query("targetId") String targetId);

    @FormUrlEncoded
    @POST("user/applyAct")
    Flowable<ResultData> ApplyAct(@FieldMap Map<String,String> map);

    @GET("user/MyApply")
    Flowable<ResultData<MyapplyVo>> getMypply(@Query("userId") String userId);

    @POST("user/update/info")
    Flowable<ResultData> updateInfo(String userId, String clumName, String info);

    @Multipart
    @POST("user/update/avatar")
    Flowable<ResultData> updatevatar(@Query("userid") String userid, @Part() MultipartBody.Part avatar);

    @GET("/nickname")
    Flowable<String> getNickname(@Query("userid") String userid);
}
