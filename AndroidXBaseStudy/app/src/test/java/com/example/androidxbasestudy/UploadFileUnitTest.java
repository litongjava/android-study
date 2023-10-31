package com.example.androidxbasestudy;

import android.view.textclassifier.TextLinks;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/7
 */
public class UploadFileUnitTest {

  @Test
  public void uploadFileTest() {

    String url = "http://httpbin.org/post";
    MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();

    File file1 = new File("E:\\data\\opencv-engine.txt");
    File file2 = new File("E:\\data\\video-to-test.txt");

    RequestBody requestBody1 = RequestBody.create(file1, MediaType.get("text/plain"));
    RequestBody requestBody2 = RequestBody.create(file2, MediaType.get("text/plain"));

    bodyBuilder.addFormDataPart("file1", file1.getName(), requestBody1);
    bodyBuilder.addFormDataPart("file2", file1.getName(), requestBody2);

    MultipartBody multipartBody = bodyBuilder.build();

    Request.Builder builder = new Request.Builder();
    builder.url(url);
    builder.post(multipartBody);
    Request request = builder.build();

    OkHttpClient okHttpClient = new OkHttpClient();
    Call call = okHttpClient.newCall(request);
    try {
      Response response = call.execute();
      System.out.println(response.body().string());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
