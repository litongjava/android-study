package com.litongjava.androidaynctaskdemo01.listener;

/**
 * 回调接口
 */
public interface OnDataFinishedListener {
  public void onDataSuccessfully(Object data);
  public void onDataFailed();
}