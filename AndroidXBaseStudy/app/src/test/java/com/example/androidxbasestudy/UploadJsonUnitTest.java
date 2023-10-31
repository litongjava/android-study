package com.example.androidxbasestudy;

import org.junit.Test;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/7
 */
public class UploadJsonUnitTest {

  @Test
  public void uploadJsonTest() {
    String url = "http://httpbin.org/post";
    String jsonString = "{\"a\":\"1\",\"b\":\"2\"}";
    RequestBody requestBody = RequestBody.create(jsonString, MediaType.parse("application/json"));

    Request request = new Request.Builder().url(url).post(requestBody).build();
    OkHttpClient okHttpClient = new OkHttpClient();
    try {
      Response response = okHttpClient.newCall(request).execute();
      System.out.println(response.body().string());
    } catch (IOException e) {
      e.printStackTrace();
    }

    OkHttpClient build = new OkHttpClient.Builder().build();

  }
}
