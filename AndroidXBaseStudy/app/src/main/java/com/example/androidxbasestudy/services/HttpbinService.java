package com.example.androidxbasestudy.services;

import com.example.androidxbasestudy.model.JavaBean;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/21
 */
public interface HttpbinService {

  @GET("get")
  Call<ResponseBody> get(@Query("username") String usnm, @Query("password") String pswd);

  @POST("post")
  @FormUrlEncoded
  Call<ResponseBody> postForm(@Field("username") String usnm, @Query("password") String pswd);

  @POST("post")
  Call<ResponseBody> postBody(@Body RequestBody body);

  @POST("{id}")
  @FormUrlEncoded
  Call<ResponseBody> postInPath(@Path("id") String id, @Field("username") String usnm, @Query("password") String pswd);

  @POST("{id}")
  @FormUrlEncoded
  Call<ResponseBody> postHeader(@Header("os") String os, @Path("id") String id, @Field("username") String usnm, @Query("password") String pswd);

  @Headers({"os:android","version:1.0"})
  @POST("post")
  Call<ResponseBody> postWithHeader();


  @POST
  Call<ResponseBody> postUrl(@Url String url);

  @POST
  @FormUrlEncoded
  Call<JavaBean> post(@Field("username") String usnm, @Field("password") String pswd);
}
