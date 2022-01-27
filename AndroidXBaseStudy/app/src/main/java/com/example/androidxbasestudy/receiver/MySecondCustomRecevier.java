package com.example.androidxbasestudy.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/26
 */
public class MySecondCustomRecevier extends BroadcastReceiver {
  private Logger log= LoggerFactory.getLogger(this.getClass());

  @Override
  public void onReceive(Context context, Intent intent) {
    log.info("context:{},intent:{}",context,intent);
  }
}
