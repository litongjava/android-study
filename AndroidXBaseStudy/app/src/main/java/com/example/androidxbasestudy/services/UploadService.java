package com.example.androidxbasestudy.services;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/23
 */
public interface UploadService {

  @POST("post")
  @Multipart
  Call<ResponseBody> upload(@Part MultipartBody.Part file);


  @POST("post")
  @Multipart
  Call<ResponseBody> uploadPartMap(@PartMap MultipartBody.Part file);
}
