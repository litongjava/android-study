package com.example.androidxbasestudy.services;

import com.example.androidxbasestudy.model.WanAndroidResponse;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/23
 */
public interface WanAndroidService {

  @POST("user/login")
  @FormUrlEncoded
  Call<WanAndroidResponse> login(@Field("username") String username, @Field("password") String password);

  @POST("user/login")
  @FormUrlEncoded
  Flowable<WanAndroidResponse> loginReturnFlowable(@Field("username") String username,
                                                   @Field("password") String password);

  @GET("lg/collect/list/{pageNum}/json")
  Flowable<ResponseBody> getCollect(@Path("pageNum") int pageNum);


}
