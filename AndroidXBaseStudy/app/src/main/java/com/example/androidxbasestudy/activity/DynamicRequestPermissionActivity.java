package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.androidxbasestudy.R;
import com.example.androidxbasestudy.utils.AlertDialogUtils;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.ping.permission.PermissionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FindViewByIdLayout(R.layout.activity_dynamic_request_permission)
public class DynamicRequestPermissionActivity extends AppCompatActivity {
  private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10024;
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
  }

  @OnClick(R.id.btnRequestPermisson)
  public void btnRequestPermissonOnClick(View view) {
    String permission = Manifest.permission.READ_CONTACTS;
    PermissionUtils.requestPermission(this,permission, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
    switch (requestCode) {
      case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          log.info("onRequestPermissionsResult granted");
        } else {
          log.info("onRequestPermissionsResult denied");
          String message = "请前往设置->应用->PermissionDemo->权限中打开相关权限，否则功能无法正常运行！";
          AlertDialogUtils.showWaringDialog(this, message);
        }
        return;
      }
    }
  }
}