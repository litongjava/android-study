package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.androidxbasestudy.R;
import com.example.androidxbasestudy.services.HttpbinService;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@FindViewByIdLayout(R.layout.activity_retrofit)
public class RetrofitActivity extends AppCompatActivity {

  private Logger log= LoggerFactory.getLogger(this.getClass());
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this,this);
  }

  @OnClick(R.id.btnRetrofitRequest)
  public void btnRetrofitRequestOnClick(View view){
    String url="https://www.httpbin.org/";
    Retrofit retrofit = new Retrofit.Builder().baseUrl(url).build();
    HttpbinService httpbinService = retrofit.create(HttpbinService.class);

    Call<ResponseBody> call = httpbinService.get("litong", "00000000");
    //同步请求
//    try {
//      call.execute();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
    //异步请求
    call.enqueue(new Callback<ResponseBody>() {
      @Override
      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
          log.info("response:{}",response.body().string());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onFailure(Call<ResponseBody> call, Throwable t) {

      }
    });
  }

}