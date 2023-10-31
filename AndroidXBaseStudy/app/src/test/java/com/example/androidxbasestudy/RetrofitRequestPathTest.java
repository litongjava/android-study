package com.example.androidxbasestudy;

import com.example.androidxbasestudy.services.HttpbinService;

import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitRequestPathTest {

  @Test
  public void pathTest() {
    String url = "https://www.httpbin.org/";
    Retrofit retrofit = new Retrofit.Builder().baseUrl(url).build();
    HttpbinService httpbinService = retrofit.create(HttpbinService.class);

//    try {
//      String id = "post";
//      Response<ResponseBody> response = httpbinService.postInPath(id,"ping-e-lee","0000000").execute();
//      System.out.println(response.body().string());
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    try {
      String id = "post";
      String os = "android";
      Response<ResponseBody> response = httpbinService.postHeader(os, id, "ping-e-lee", "0000000").execute();
      System.out.println(response.body().string());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
