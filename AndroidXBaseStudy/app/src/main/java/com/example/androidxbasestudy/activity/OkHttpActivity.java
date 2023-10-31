package com.example.androidxbasestudy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidxbasestudy.BuildConfig;
import com.example.androidxbasestudy.R;
import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@FindViewByIdLayout(R.layout.activity_ok_http)
public class OkHttpActivity extends AppCompatActivity {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  private Map<String, List<Cookie>> cookieMap = new ConcurrentHashMap<>();

  @FindViewById(R.id.textView02)
  private TextView textView02;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
  }

  @OnClick(R.id.btnGetSyncRequest)
  public void btnRequestSyncOnClick(View view) {
    //在Android项目中,请求工程必须在线程中执行
    new Thread(() -> {
      OkHttpClient okHttpClient = new OkHttpClient();
      //构建请求对象
      String url = "http://httpbin.org/get?a=1&b=2";
      Request request = new Request.Builder().url(url).build();
      //新建Call对象
      Call call = okHttpClient.newCall(request);
      try {
        //执行请求,获取Response
        Response response = call.execute();
        //输出响应数据
        log.info(response.body().string());
        //textView02.setText(response.body().string());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();
  }

  @OnClick(R.id.btnGetAsyncRequest)
  public void btnRequestAsyncOnClick(View view) {
    OkHttpClient okHttpClient = new OkHttpClient();
    //构建请求对象
    String url = "http://httpbin.org/get?a=1&b=2";
    Request request = new Request.Builder().url(url).build();
    //新建Call对象
    Call call = okHttpClient.newCall(request);
    Callback responseCallback = new Callback() {

      @Override
      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.isSuccessful()) {
          log.info(response.body().string());
        }

      }

      @Override
      public void onFailure(@NotNull Call call, @NotNull IOException e) {

      }
    };
    call.enqueue(responseCallback);
  }

  @OnClick(R.id.btnPostSyncRequest)
  public void btnRequestSyncPostOnClick(View view) {
    new Thread(() -> {
      FormBody.Builder builder = new FormBody.Builder();
      builder.add("a", "1").add("b", "2");
      FormBody formBody = builder.build();
      String url = "http://httpbin.org/post";
      Request request = new Request.Builder().url(url).post(formBody).build();
      OkHttpClient okHttpClient = new OkHttpClient();
      Call call = okHttpClient.newCall(request);
      try {
        Response response = call.execute();
        log.info(response.body().string());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();
  }

  @OnClick(R.id.btnPostAsyncRequest)
  public void btnRequestAsyncPostOnClick(View view) {
    FormBody.Builder builder = new FormBody.Builder();
    builder.add("a", "1").add("b", "2");
    FormBody formBody = builder.build();
    String url = "http://httpbin.org/post";
    Request request = new Request.Builder().url(url).post(formBody).build();
    OkHttpClient okHttpClient = new OkHttpClient();
    Call call = okHttpClient.newCall(request);

    call.enqueue(new Callback() {
      @Override
      public void onFailure(@NotNull Call call, @NotNull IOException e) {

      }

      @Override
      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.isSuccessful()) {
          log.info(response.body().string());
        }
      }
    });
  }

  @OnClick(R.id.btnInterceptor)
  public void btnInterceptorOnClick(View view) {
    Interceptor interceptor = new Interceptor() {

      @NotNull
      @Override
      public Response intercept(@NotNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("os", "android");
        builder.addHeader("version", "1.0");

        Request request = builder.build();
        Response response = chain.proceed(request);
        return response;
      }
    };

    OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
    httpBuilder.addInterceptor(interceptor);

    OkHttpClient okHttpClient = httpBuilder.build();

    FormBody.Builder builder = new FormBody.Builder();
    builder.add("a", "1").add("b", "2");
    FormBody formBody = builder.build();
    String url = "http://httpbin.org/post";
    Request request = new Request.Builder().url(url).post(formBody).build();

    Call call = okHttpClient.newCall(request);

    call.enqueue(new Callback() {
      @Override
      public void onFailure(@NotNull Call call, @NotNull IOException e) {

      }

      @Override
      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.isSuccessful()) {
          log.info(response.body().string());
        }
      }
    });
  }

  @OnClick(R.id.btnCacheAndCookie)
  public void btnCacheAndCookieOnClick(View view) {
    OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
    httpBuilder.cookieJar(new CookieJar() {
      @Override
      public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
        //获取host,eg:www.wanandroid.com
        String host = httpUrl.host();
        log.info("host:{}", host);
        log.info("保存cookie,host:{},cookie:{}", host, list);
        cookieMap.put(host, list);
      }

      @NotNull
      @Override
      public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
        List<Cookie> cookies = cookieMap.get(httpUrl.host());
        if (cookies != null) {
          log.info("恢复cookie,httpUrl:{}", httpUrl);
          return cookies;
        }

        return new ArrayList<Cookie>();
      }
    });
    OkHttpClient okHttpClient = httpBuilder.build();
    //请求登录接口
    String loginUrl = "https://www.wanandroid.com/user/login";
    //请求收藏文章列表接口
    String collectListUrl = "https://www.wanandroid.com/lg/collect/list/0/json";

    //开启新的线程执行请求
    new Thread(() -> {
      Response loginResponse = login(okHttpClient, loginUrl);
      try {
        log.info("loginResponse:{}", loginResponse.body().string());
      } catch (IOException e) {
        e.printStackTrace();
      }
      collectList(okHttpClient, collectListUrl);
    }).start();

  }

  /**
   * 收藏
   *
   * @param okHttpClient
   * @param collectListUrl
   */
  private void collectList(OkHttpClient okHttpClient, String collectListUrl) {
    Request.Builder requestBuilder = new Request.Builder();
    requestBuilder.url(collectListUrl);
    Request request = requestBuilder.build();

    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(@NotNull Call call, @NotNull IOException e) {

      }

      @Override
      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.isSuccessful()) {
          log.info(response.body().string());
        }
      }
    });
  }

  /**
   * 登录
   *
   * @param okHttpClient
   * @param loginUrl
   */
  private Response login(OkHttpClient okHttpClient, String loginUrl) {

    FormBody.Builder formBuilder = new FormBody.Builder();
    formBuilder.add("username", "PingELee").add("password", "Litong@2516");
    FormBody formBody = formBuilder.build();

    Request.Builder requestBuilder = new Request.Builder();
    requestBuilder.url(loginUrl).post(formBody);
    Request request = requestBuilder.build();

    Call call = okHttpClient.newCall(request);
    try {
      return call.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}