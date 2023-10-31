package com.example.androidxbasestudy.services;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/23
 */
public class DownloadServiceTest {

  //baseUrl
  String baseUrl = "https://httpbin.org/";
  //实例化retorfit
  Retrofit retorfit = new Retrofit.Builder().baseUrl(baseUrl)
    //添加适配器
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    //执行构建方法
    .build();
  //实例化uploadService
  DownloadService downloadService = retorfit.create(DownloadService.class);
  String url = "http://download.uairobot.com/x264/x264.tar.gz";

  @Test
  public void downloadTest() {
    try {
      Response<ResponseBody> response = downloadService.download(url).execute();
      if (response.isSuccessful()) {
        //保存到文件
        InputStream inputStream = response.body().byteStream();
        FileUtils.copyInputStreamToFile(inputStream, new File("x264.tar.gz"));
      } else {
        System.out.println("下载失败");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void downloadRxJavaTest() {
//    downloadService.downloadRxJava(url)
//    .subscribe(new Consumer<ResponseBody>() {
//      @Override
//      public void accept(ResponseBody responseBody) throws Throwable {
//        System.out.println("下载文件");
//        InputStream inputStream = responseBody.byteStream();
//        FileUtils.copyInputStreamToFile(inputStream, new File("x264.tar.gz"));
//      }
//    });
    //测试失败
    downloadService.downloadRxJava(url)
      //将ResponseBody转为Map
      .map(new Function<ResponseBody, File>() {
        @Override
        public File apply(ResponseBody responseBody) throws Throwable {
          System.out.println("执行");
          InputStream inputStream = responseBody.byteStream();
          File detFile = new File("x264.tar.gz");
          FileUtils.copyInputStreamToFile(inputStream, detFile);
          return detFile;
        }
      })
      //保存文件
      .subscribe(new Consumer<File>() {
        @Override
        public void accept(File file) throws Throwable {
          System.out.println(file.getAbsolutePath());
        }
      });
  }

}