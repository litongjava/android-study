package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.androidxbasestudy.R;
import com.example.androidxbasestudy.constants.SpConstants;
import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.ping.toast.ToastUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FindViewByIdLayout(R.layout.activity_auto_login)
public class AutoLoginActivity extends AppCompatActivity {

  private Logger log= LoggerFactory.getLogger(this.getClass());
  //保存用户信息类
  private SharedPreferences sharedPreferences;

  @FindViewById(R.id.et_name)
  private EditText et_name;
  @FindViewById(R.id.et_pswd)
  private EditText et_pswd;

  @FindViewById(R.id.cb_remeberPswd)
  private CheckBox cb_remeberPswd;
  @FindViewById(R.id.cb_autoLogin)
  private CheckBox cb_autoLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
    sharedPreferences = super.getSharedPreferences("config", Context.MODE_PRIVATE);
    initData();
  }

  private void initData() {
    //回响数据
    boolean isRemeberPswd = sharedPreferences.getBoolean(SpConstants.remeberPswd, false);
    boolean isAautoLogin = sharedPreferences.getBoolean(SpConstants.autoLogin, false);
    if(isRemeberPswd){
       et_name.setText(sharedPreferences.getString(SpConstants.username,"请输入用户名"));
       et_pswd.setText(sharedPreferences.getString(SpConstants.password,"请输入密码"));
       cb_remeberPswd.setChecked(true);
    }

    if(isAautoLogin){
      ToastUtils.defaultToast(getApplicationContext(),"检测到自动登录,正在调整Activity");
      cb_autoLogin.setChecked(true);
    }
  }

  @OnClick(R.id.bt_login)
  public void bt_login_OnClick(View view) {
    log.info("bt_login_OnClick");
    //1.获取用户名和密码
    String username = et_name.getText().toString().trim();
    String password = et_pswd.getText().toString().trim();
    if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
      ToastUtils.defaultToast(getApplicationContext(), "用户名和密码不能为空");
      return;
    }
    //2.判断用户是否记住密码
    if (cb_remeberPswd.isChecked()) {
      //保存用户名,密码,记住密码的状态
      SharedPreferences.Editor edit = sharedPreferences.edit();

      edit.putString(SpConstants.username, username);

      edit.putString(SpConstants.password, password);
      edit.putBoolean(SpConstants.remeberPswd, true);
      edit.apply();
    }
    if (cb_autoLogin.isChecked()) {
      SharedPreferences.Editor edit = sharedPreferences.edit();
      edit.putBoolean(SpConstants.autoLogin, true);
      edit.apply();
    }
    ToastUtils.defaultToast(getApplicationContext(),"登录成功");

  }
}