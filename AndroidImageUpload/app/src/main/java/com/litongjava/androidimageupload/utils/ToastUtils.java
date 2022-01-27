package com.litongjava.androidimageupload.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
  public static void defaultToast(Context context, String message) {
    Toast.makeText(context,message,Toast.LENGTH_LONG).show();
  }
}
