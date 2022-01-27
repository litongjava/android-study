package com.example.androidxbasestudy.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidxbasestudy.R;
import com.example.androidxbasestudy.services.MyFirstService;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FindViewByIdLayout(R.layout.activity_start_service)
public class StartServiceActivity extends AppCompatActivity {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
  }

  @OnClick(R.id.btnStartService)
  public void btnStartService_OnClick(View view) {
    log.info("启动服务");
    Intent intent = new Intent(this, MyFirstService.class);
    super.startService(intent);
  }

  @OnClick(R.id.btnStopService)
  public void btnStopService_OnClick(View view) {
    log.info("停止服务");
    Intent intent = new Intent(this, MyFirstService.class);
    super.stopService(intent);
  }

  private ServiceConnection connection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
  };

  @OnClick(R.id.btnBindService)
  public void btnBindService_OnClick(View view) {
    log.info("绑定服务");
    Intent intent = new Intent(this, MyFirstService.class);
    super.bindService(intent, connection, Context.BIND_AUTO_CREATE);

  }

  @OnClick(R.id.btnUnBindService)
  public void btnUnBindService_OnClick(View view) {
    log.info("解绑服务");
    super.unbindService(connection);
  }

  /**
   * 当Activity销毁时,解绑服务
   */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    super.unbindService(connection);
  }
}