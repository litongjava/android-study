package com.litongjava.androidbasestudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FindViewByIdLayout(R.layout.activity_toolbar)
public class ToolbarActivity extends AppCompatActivity {
  private Logger log = LoggerFactory.getLogger(this.getClass());
  @FindViewById(R.id.toolBar01)
  private Toolbar toolBar01;

  @FindViewById(R.id.toolBar02)
  private Toolbar toolBar02;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
    initView();
  }

  private void initView() {
    toolBar01.setNavigationOnClickListener((v) -> {
      log.info("点击");
    });

    toolBar02.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
    toolBar02.setTitle("标题");
    toolBar02.setNavigationOnClickListener((v) -> {
      log.info("点击");
    });
  }
}