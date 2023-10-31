package com.example.androidxbasestudy.acp;

import android.content.Context;

import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

public class AcpUtils {
  /**
   * 请求权限
   */
  public static void requestPermissions(Context context, String[] permissions, AcpListener acpListener) {
    //创建Builder
    AcpOptions.Builder builder = new AcpOptions.Builder();
    //创建设置permission
    builder.setPermissions(permissions);
    //build
    AcpOptions acpOptions = builder.build();
    Acp acp = Acp.getInstance(context);
    acp.request(acpOptions, acpListener);
  }
}
