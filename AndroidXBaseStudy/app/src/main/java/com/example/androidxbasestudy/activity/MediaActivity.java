package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidxbasestudy.R;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

@FindViewByIdLayout(R.layout.activity_media)
public class MediaActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_media);
    ViewInjectUtils.injectActivity(this,this);
  }

  @OnClick(R.id.btnToMediaRecorderActivity)
  public void btnToMediaRecorderActivityOnClick(View view){
    Intent intent = new Intent(this,MediaRecorderActivity.class);
    startActivity(intent);
  }
}