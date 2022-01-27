package com.example.androidxbasestudy.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.util.ContextSelectorStaticBinder;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/26
 */
public class MyFirstService extends Service {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public void onCreate() {
    log.info("onCreate");
    super.onCreate();
  }

  @Override
  public void onStart(Intent intent, int startId) {
    log.info("onStart");
    super.onStart(intent, startId);
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    log.info("onStartCommand");
    return super.onStartCommand(intent, flags, startId);
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    log.info("onBind");
    return null;
  }

  @Override
  public boolean onUnbind(Intent intent) {
    log.info("onUnbind");
    return super.onUnbind(intent);
  }

  @Override
  public void onDestroy() {
    log.info("onDestroy");
    super.onDestroy();
  }
}
