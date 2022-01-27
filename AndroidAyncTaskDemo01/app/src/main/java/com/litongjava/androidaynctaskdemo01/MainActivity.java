package com.litongjava.androidaynctaskdemo01;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidaynctaskdemo01.service.ImageView1Service;
import com.litongjava.androidaynctaskdemo01.service.ImageView2Service;

@FindViewByIdLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

  private ImageView1Service imageView1Service = new ImageView1Service();
  private ImageView2Service imageView2Service = new ImageView2Service();
  @FindViewById(R.id.im1)
  private ImageView im1;
  @FindViewById(R.id.im2)
  private ImageView im2;
  @FindViewById(R.id.progressBar)
  private ProgressBar progressBar;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.inject(this,this);
    initListener();
  }

  private void initListener() {

    im1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        imageView1Service.getBitMapAndSet(MainActivity.this,im1,progressBar);
      }
    });

    im2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        imageView2Service.getBitMapAndSet(im1);
      }
    });

  }
}
