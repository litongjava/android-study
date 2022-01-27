package com.litongjava.androidbasestudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.androidbasestudy.R;

@FindViewByIdLayout(R.layout.activity_frame_layout)
public class FrameLayoutActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }
}