package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.example.androidxbasestudy.R;
import com.example.androidxbasestudy.receiver.MySecondCustomRecevier;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FindViewByIdLayout(R.layout.activity_send_broadcast)
public class SendBroadcastActivity extends AppCompatActivity {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  private String actionFlag="com.example.androidxbasestudy.receiver.Flag2";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);

    //注册接收者
    MySecondCustomRecevier recevier = new MySecondCustomRecevier();
    IntentFilter filter = new IntentFilter();
    filter.addAction(actionFlag);
    super.registerReceiver(recevier,filter);
  }

  @OnClick(R.id.btnSendBroadcastToStatic)
  public void btnSendBroadcast_OnClick(View view) {
    log.info("发送广播");
    Intent intent = new Intent();
    intent.setAction("com.example.androidxbasestudy.receiver.Flag");
    super.sendBroadcast(intent);
  }

  @OnClick(R.id.btnSendBroadcastToDynamic)
  public void btnSendBroadcastToDynamic_OnClick(View view){
    log.info("发送广播");
    Intent intent = new Intent();
    intent.setAction(actionFlag);
    super.sendBroadcast(intent);
  }
}