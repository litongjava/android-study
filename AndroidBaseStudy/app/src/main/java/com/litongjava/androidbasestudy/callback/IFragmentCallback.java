package com.litongjava.androidbasestudy.callback;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/24
 */
public interface IFragmentCallback {
  void sendMsgToActivity(String msg);

  String getMsgFromActivity(String msg);
}
