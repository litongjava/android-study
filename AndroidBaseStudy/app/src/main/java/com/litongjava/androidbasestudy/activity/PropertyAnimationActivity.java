package com.litongjava.androidbasestudy.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FindViewByIdLayout(R.layout.activity_property_animation)
public class PropertyAnimationActivity extends AppCompatActivity {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @FindViewById(R.id.textView03)
  public TextView textView03;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
  }

  @OnClick(R.id.btnValueAnimator)
  public void btnValueAnimatorOnClick() {
    ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
    //设置周期,单位ms
    valueAnimator.setDuration(2000);

    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        float animatedValue = (float) animation.getAnimatedValue();
        log.info("animatedValue:{}", animatedValue);
      }
    });
    //启动动画
    valueAnimator.start();
  }

  @OnClick(R.id.btnObjectAnimator)
  public void btnObjectAnimatorOnClick() {
    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView03, "alpha", 0f, 1f);
    objectAnimator.setDuration(4000);
    objectAnimator.start();
    float alpha = textView03.getAlpha();
    log.info("textView03 alpha:{}",alpha);

    objectAnimator.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        //动画开始时调用
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        //动画结束时调用
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        //动画取消时调用该方法
      }

      @Override
      public void onAnimationRepeat(Animator animation) {
        //动画重复时,调用该方法
      }
    });
    //使用适配器,重写其中的一个方法
    objectAnimator.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationCancel(Animator animation) {
        super.onAnimationCancel(animation);
      }
    });
  }
}