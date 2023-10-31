package com.example.androidxbasestudy.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidxbasestudy.R;
import com.example.androidxbasestudy.engine.CustomObserver;
import com.example.androidxbasestudy.engine.LoginEngine;
import com.example.androidxbasestudy.model.ResponseResult;
import com.example.androidxbasestudy.model.SuccessBean;
import com.example.androidxbasestudy.utils.BitmapUtils;
import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@FindViewByIdLayout(R.layout.activity_rx_java)
public class RxJavaActivity extends AppCompatActivity {

  //日志类
  private Logger log = LoggerFactory.getLogger(this.getClass());
  //图片下载地址
  private String imageUrl = "https://unmc.cdn.bcebos.com/1641259241082_381275693.jpg";
  //加载框
  private ProgressDialog progressDialog;

  @FindViewById(R.id.imageView02)
  private ImageView imageView02;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
  }

  @OnClick(R.id.btnDownloadImage)
  public void btnDownloadImageOnClick() {

  }

  @OnClick(R.id.btnDownloadImageByRxJava)
  public void btnDownloadImageByRxJavaOnClick(View view) {
    Observable.just(imageUrl)
      .map(new Function<String, Bitmap>() {
        @NonNull
        @Override
        public Bitmap apply(@NonNull String s) throws Exception {
          Thread.sleep(2000);
          URL url = new URL(s);
          HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
          //请求连接时长为5s
          httpURLConnection.setConnectTimeout(5000);
          //获取响应状态吗
          int responseCode = httpURLConnection.getResponseCode();
          if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = httpURLConnection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
          }

          return null;
        }
      })
      //增加水印
      .map(new Function<Bitmap, Bitmap>() {
        @NonNull
        @Override
        public Bitmap apply(@NonNull Bitmap bitmap) throws Exception {
          //初始化画笔
          Paint paint = new Paint();
          paint.setColor(Color.RED);
          paint.setTextSize(100);
          return BitmapUtils.drawTextToBitmap(bitmap, paint, "大家好,我是Ping E Lee", 80, 80);
        }
      })
      //分配异步线程操作
      .subscribeOn(Schedulers.io())
      //终点分配为Android主线程
      .observeOn(AndroidSchedulers.mainThread())
      //关联:观察者模式,观察起点和终点==订阅,
      .subscribe(new Observer<Bitmap>() {
        /**
         * 订阅成功
         */
        @Override
        public void onSubscribe(Disposable d) {
          progressDialog = new ProgressDialog(RxJavaActivity.this);
          progressDialog.setTitle("加载中");
          progressDialog.show();
        }

        /**
         * 上一层给我的响应
         */
        @Override
        public void onNext(Bitmap bitmap) {
          imageView02.setImageBitmap(bitmap);
        }

        /**
         * 发生错误
         */
        @Override
        public void onError(Throwable e) {

        }

        /**
         * 整个链条全部结束
         */
        @Override
        public void onComplete() {
          if (progressDialog != null) {
            progressDialog.dismiss();
          }
        }
      });
  }

  @OnClick(R.id.btnPrintLog)
  public void btnPrintLogOnClick(View view) {
    String[] msg = {"AAA", "BBB"};
    Observable.fromArray(msg)
      //打印日志
      .subscribe(new Consumer<String>() {
        @Override
        public void accept(@NonNull String s) throws Exception {
          log.info("s:{}", s);
        }
      });
  }

  @OnClick(R.id.btnRxJavaLogin)
  public void btnRxJavaLoginOnClick(View view) {
    Observable<ResponseResult> observable = LoginEngine.login("PingELee", "00000000");
    observable.subscribe(new CustomObserver() {
      @Override
      public void success(SuccessBean successBean) {
        log.info("SuccessBean:{}",successBean);
      }

      @Override
      public void error(String message) {
        log.info("message:{}",message);
      }
    });
  }
}