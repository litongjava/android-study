package com.litongjava.androidbasestudy.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FindViewByIdLayout(R.layout.activity_alert_dialog)
public class AlertDialogActivity extends AppCompatActivity {

  private Logger log= LoggerFactory.getLogger(this.getClass());
  @FindViewById(R.id.btnDialogShow)
  private Button btnDialogShow;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView();
    ViewInjectUtils.injectActivity(this, this);
  }

  @OnClick(R.id.btnDialogShow)
  public void btnDialogShowOnClick() {
    View dialogView=getLayoutInflater().inflate(R.layout.dialog_view,null);
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setIcon(R.mipmap.title_icon)
      .setTitle("提示")
      .setMessage("消息")
      .setView(dialogView)
      .setPositiveButton("确认", (dialog, w) -> {
        log.info("确认");
      })
      .setNegativeButton("取消", (dialog, w) -> {
        log.info("取消");
      })
      .setNeutralButton("中间", (dialog, w) -> {
        log.info("中间");
      });

    AlertDialog alertDialog = builder.create();
    alertDialog.show();
  }
}