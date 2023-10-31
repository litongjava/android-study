package com.example.androidxbasestudy;

import com.example.androidxbasestudy.services.HttpbinService;

import org.junit.Test;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/21
 */
public class RetrofitRequestBody {

  @Test
  public void requestBodyTest() {
    String url = "https://www.httpbin.org/";
    Retrofit retrofit = new Retrofit.Builder().baseUrl(url).build();
    HttpbinService httpbinService = retrofit.create(HttpbinService.class);

    FormBody formBody = new FormBody.Builder().add("a", "1").add("b", "2").build();
    try {
      Response<ResponseBody> response = httpbinService.postBody(formBody).execute();
      boolean successful = response.isSuccessful();
      if(successful){
        System.out.println(response.toString());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
