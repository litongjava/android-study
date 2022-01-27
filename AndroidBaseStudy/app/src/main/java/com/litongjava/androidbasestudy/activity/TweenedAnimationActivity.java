package com.litongjava.androidbasestudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FindViewByIdLayout(R.layout.activity_tweened_animation)
public class TweenedAnimationActivity extends AppCompatActivity {
  private Logger log = LoggerFactory.getLogger(this.getClass());
  @FindViewById(R.id.imageView02)
  private ImageView imageView02;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
  }

  @OnClick(R.id.btnAlpha)
  public void btnAlphaOnClick() {
    log.info("btnAlpha");
    startAnimation(R.anim.alpha);
  }

  @OnClick(R.id.btnRotate)
  public void btnRotateOnClick() {
    log.info("rotate");
    //通过加载XML动画配置文件创建一个Animation对象
    startAnimation(R.anim.rotate);
  }

  @OnClick(R.id.btnScale)
  public void btnSacleOnClick() {
    log.info("btnSacle");
    startAnimation(R.anim.scale);
  }

  @OnClick(R.id.btnTranslate)
  public void btnTranslateOnClick() {
    log.info("btnTranslate");
    startAnimation(R.anim.translate);
  }

  private void startAnimation(int id) {
    //通过加载XML动画配置文件创建一个Animation对象
    Animation animation = AnimationUtils.loadAnimation(this, id);
    //启动动画
    imageView02.startAnimation(animation);
  }
}