package com.example.androidxbasestudy.services;

import io.reactivex.rxjava3.core.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/23
 */
public interface DownloadService {

  @Streaming
  @GET
  Call<ResponseBody> download(@Url String url);

  @GET
  Flowable<ResponseBody> downloadRxJava(@Url String url);

}
