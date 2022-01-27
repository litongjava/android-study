package com.litongjava.androidbasestudy.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.litongjava.androidbasestudy.BitmapUtils;
import com.litongjava.androidbasestudy.R;

import java.io.IOException;
import java.io.InputStream;

public class ImageViewActivity extends AppCompatActivity {

  private ImageView imageView01;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_view);

    imageView01=findViewById(R.id.imageView01);

    initView();
  }
  /**
   * 初始化组件
   */
  @TargetApi(Build.VERSION_CODES.KITKAT)
  private void initView() {
    String imagePath01="images/01.jpg";
    String imagePath02="images/02.jpg";
    Bitmap bitmap01 =null;
    //打开图片为流
    try(InputStream imageStream = getAssets().open(imagePath01)){
      //解析流为bitmap
      bitmap01= BitmapFactory.decodeStream(imageStream);
      //设置imageView
      //imageView01.setImageBitmap(bitmap);
    } catch (IOException e) {
      e.printStackTrace();
    }

    Bitmap bitmap02 =null;
    try(InputStream imageStream = getAssets().open(imagePath02)){
      bitmap02=BitmapFactory.decodeStream(imageStream);
    } catch (IOException e) {
      e.printStackTrace();
    }

    Bitmap bitmap03= BitmapUtils.merge(bitmap01,bitmap02,"数媒201李通2030681876");
    imageView01.setImageBitmap(bitmap03);
  }
}
