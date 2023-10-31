package com.example.androidxbasestudy.services;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/23
 */
public class UploadServiceTest {
  //baseUrl
  String baseUrl = "https://httpbin.org/";
  //实例化retorfit
  Retrofit retorfit = new Retrofit.Builder().baseUrl(baseUrl).build();
  //实例化uploadService
  UploadService uploadService = retorfit.create(UploadService.class);

  @Test
  public void uoloadTest() {
    //文件名
    File file = new File("C:\\Users\\Administrator\\Desktop\\1.docx");
    //requestBody
    RequestBody requestBody = RequestBody.create(file, MediaType.parse("text/plain"));
    //part
    MultipartBody.Part part = MultipartBody.Part.createFormData("file1", "1.txt", requestBody);
    //upload
    Call<ResponseBody> call = uploadService.upload(part);
    try {
      Response<ResponseBody> response = call.execute();
      if (response.isSuccessful()) {
        System.out.println("upload success");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}