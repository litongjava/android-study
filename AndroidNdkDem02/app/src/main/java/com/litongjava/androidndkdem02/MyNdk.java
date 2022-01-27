package com.litongjava.androidndkdem02;

public class MyNdk {
  static {
    System.loadLibrary("MyLibrary");
  }

  //自己定义的方法 正常应该是c/c++对外开放的方法名称
  public native String getString();

  //自己定义的方法 正常应该是c/c++对外开放的方法名称
  public native String getMyString();
}