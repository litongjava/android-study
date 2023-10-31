package com.example.androidxbasestudy;

import com.example.androidxbasestudy.model.WanAndroidResponse;
import com.example.androidxbasestudy.services.WanAndroidService;
import com.google.gson.Gson;

import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/23
 */
public class WanAndroidUnitTest {

  String baseUrl = "https://www.wanandroid.com";
  Retrofit retorfit = new Retrofit.Builder().baseUrl(baseUrl)
    //添加
    .addConverterFactory(GsonConverterFactory.create()).build();
  WanAndroidService wanAndroidService = retorfit.create(WanAndroidService.class);

//  @Test
//  public void loginTest() {
//    Call<ResponseBody> call = wanAndroidService.login("PingELee", "Litong@2516");
//    Response<ResponseBody> response = null;
//    try {
//      response = call.execute();
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    if(response.isSuccessful()){
//      try {
//        String string = response.body().string();
//        WanAndroidResponse wanAndroidResponse = new Gson().fromJson(string, WanAndroidResponse.class);
//        System.out.println(wanAndroidResponse.toString());
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//  }

  @Test
  public void loginTest(){
    Call<WanAndroidResponse> call = wanAndroidService.login("PingELee", "Litong@2516");
    Response<WanAndroidResponse> response = null;
    try {
      response = call.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    WanAndroidResponse javaBean = response.body();
    System.out.println(javaBean.toString());

  }
}
