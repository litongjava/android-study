package com.litongjava.androidbasestudy.activity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FindViewByIdLayout(R.layout.activity_frame_by_frame_animation)
public class FrameByFrameAnimationActivity extends AppCompatActivity {

  private Logger log = LoggerFactory.getLogger(this.getClass());
  private boolean flag = false;
  @FindViewById(R.id.rl04)
  private RelativeLayout rl04;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
    initView();
  }

  private void initView() {
    rl04.setOnClickListener((v) -> {
      Drawable background = rl04.getBackground();
      AnimationDrawable animationDrawable = (AnimationDrawable) background;
      if (flag) {
        animationDrawable.stop();
        flag = false;
      } else {
        animationDrawable.start();
        flag = true;
      }
    });
  }
}