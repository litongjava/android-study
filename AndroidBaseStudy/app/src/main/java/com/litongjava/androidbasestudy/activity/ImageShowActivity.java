package com.litongjava.androidbasestudy.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;

/**
 * create by Ping on 2022/1/15 15:33
 */
@FindViewByIdLayout(R.layout.activity_image_show)
public class ImageShowActivity extends AppCompatActivity {

  @FindViewById(R.id.imageView_show)
  private ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this,this);

    initView();
  }

  private void initView() {
    //设置ImageView的显示图片
    Resources resources = getResources();
    Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.ping_avatar);
    imageView.setImageBitmap(bitmap);
  }
}