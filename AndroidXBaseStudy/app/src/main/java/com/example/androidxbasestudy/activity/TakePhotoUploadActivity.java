package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidxbasestudy.R;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

@FindViewByIdLayout(R.layout.activity_take_photo_upload)
public class TakePhotoUploadActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this,this);
  }
}