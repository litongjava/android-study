package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.androidxbasestudy.R;
import com.example.androidxbasestudy.view.MyImageView;
import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FindViewByIdLayout(R.layout.activity_on_global_layout_listener)
public class OnGlobalLayoutListenerActivity extends AppCompatActivity{
  private Logger log = LoggerFactory.getLogger(this.getClass());
  @FindViewById(R.id.myImageview)
  private MyImageView myImageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_on_global_layout_listener);
    ViewInjectUtils.injectActivity(this, this);
    log.info("height:{}", myImageView.getHeight());
    //measureWidth(myImageView);
  }

  private void measureWidth(View view) {
    ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();

    //对控件layout结束事件进行监听（也不知道到底是先布局子空间还是父控件）
    ViewTreeObserver.OnGlobalLayoutListener listener = new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {// 当layout执行结束后回调
        //view.getViewTreeObserver().removeGlobalOnLayoutListener(this);//废弃了
        int width = view.getMeasuredWidth();
        //和上面的值是一样的
        int width2 = view.getWidth();
        log.info("width:{},{}", width, width2);
        //使用完必须撤销监听（只测量一次），否则，会一直不停的不定时的测量，这比较耗性能
        viewTreeObserver.removeOnGlobalLayoutListener(this);//Added in API level 16
      }
    };
    viewTreeObserver.addOnGlobalLayoutListener(listener);
  }
}