package com.example.androidxbasestudy;

import com.example.androidxbasestudy.services.HttpbinService;

import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/21
 */
public class HttpbinServiceTest {

  String url = "https://www.httpbin.org/";
  Retrofit retrofit = new Retrofit.Builder().baseUrl(url).build();
  HttpbinService httpbinService = retrofit.create(HttpbinService.class);

  @Test
  public void henaderTest(){
    Call<ResponseBody> call = httpbinService.postWithHeader();
    try {
      Response<ResponseBody> response = call.execute();
      System.out.println(response.body().string());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void postUrlTest(){
    String url = "https://www.httpbin.org/post";
    Call<ResponseBody> call = httpbinService.postUrl(url);

    try {
      Response<ResponseBody> response = call.execute();
      System.out.println(response.body().string());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
