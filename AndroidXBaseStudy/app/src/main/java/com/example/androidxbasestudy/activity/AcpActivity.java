package com.example.androidxbasestudy.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidxbasestudy.R;
import com.litongjava.android.utils.toast.ToastUtils;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.util.List;

@FindViewByIdLayout(R.layout.activity_acp)
public class AcpActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
  }

  @OnClick(R.id.btnGetPermission)
  public void btnGetPermissionOnClick(View view) {
    //创建Builder
    AcpOptions.Builder builder = new AcpOptions.Builder();

    //创建acpOptions
    AcpOptions acpOptions = builder.setPermissions(
      //写入外部设备权限
      Manifest.permission.WRITE_EXTERNAL_STORAGE,
      //读取联系人权限
      Manifest.permission.READ_CONTACTS)
      .setDeniedMessage("DeniedMessage")
      .setDeniedCloseBtn("DeniedCloseBtn")
      .setDeniedSettingBtn("DeniedSettingBtn")
      .setRationalMessage("RationalMessage")
      .setRationalBtn("RationalBtn")
      .build();

    //创建acpListener
    AcpListener acpListener = new AcpListener() {
      @Override
      public void onGranted() {
        ToastUtils.defaultToast(getApplicationContext(), "获取权限成功");
      }

      @Override
      public void onDenied(List<String> permissions) {
        ToastUtils.defaultToast(getApplicationContext(), permissions.toString() + "权限拒绝");
      }
    };
    Acp acp = Acp.getInstance(this);
    acp.request(acpOptions, acpListener);
  }
}