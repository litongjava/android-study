package com.example.androidxbasestudy;

import com.example.androidxbasestudy.model.WanAndroidResponse;
import com.example.androidxbasestudy.services.WanAndroidService;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/23
 */
public class RetrofitRxJavaAdapterUnitTest {

  //保存cookie信息
  private Map<String, List<Cookie>> cookieMap = new ConcurrentHashMap<>();
  //private Logger log=LoggerFactory.getLogger(this.getClass());
  String baseUrl = "https://www.wanandroid.com/";

  //实例化retorfit,指定httpClient
  Retrofit retorfit = new Retrofit.Builder().baseUrl(baseUrl)
    //指定httpClient
    .callFactory(new OkHttpClient.Builder().cookieJar(new CookieJar() {
      @Override
      public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
        String host = httpUrl.host();
        cookieMap.put(host, list);
      }

      @NotNull
      @Override
      public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
        List<Cookie> cookies = cookieMap.get(httpUrl.host());
        if (cookies != null) {
          return cookies;
        }
        return new ArrayList<>();
      }
    }).build())
    //添加转换器
    .addConverterFactory(GsonConverterFactory.create())
    //添加适配器
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .build();
  WanAndroidService wanAndroidService = retorfit.create(WanAndroidService.class);

  @Test
  public void getCollectTest() {

    //执行登录请求
    wanAndroidService.loginReturnFlowable("PingELee", "Litong@2516")
      //执行获取收藏列表的请求
      .flatMap(new Function<WanAndroidResponse, Publisher<ResponseBody>>() {
        @Override
        public Publisher<ResponseBody> apply(WanAndroidResponse response) throws Throwable {
          return wanAndroidService.getCollect(0);
        }
      })
      //切换到子线程
      .observeOn(Schedulers.io())
      //添加观察回调,Android中使用AndroidSchedulers.mainThread(),java中使用Schedulers.newThread()
      .subscribeOn(Schedulers.newThread())
      //添加回调
      .subscribe(new Consumer<ResponseBody>() {
        @Override
        public void accept(ResponseBody responseBody) throws Throwable {
          System.out.println(responseBody.string());
        }
      });
    while (true) {

    }
  }
}
