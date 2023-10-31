package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.androidxbasestudy.R;
import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FindViewByIdLayout(R.layout.activity_shared_preference)
public class SharedPreferenceActivity extends AppCompatActivity {

  private Logger log= LoggerFactory.getLogger(this.getClass());


  @FindViewById(R.id.textView03)
  private TextView textView03;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this,this);
  }

  @OnClick(R.id.btnSaveSp)
  public void btnSaveSp_OnClick(View view){
    log.info("btnSaveSp_OnClick");
    /**
     * String name: key值
     * int mode : 保存模式 追加,(常规)覆盖=Context.MODE_PRIVATE,
     */
    SharedPreferences sharedPreferences = super.getSharedPreferences("watermark", Context.MODE_PRIVATE);
    //获取sp编辑器
    SharedPreferences.Editor edit = sharedPreferences.edit();
    //添加key,value
    edit.putString("watermark","数媒201李通2030681876");
    //写入到配置文件
    edit.apply();
  }

  @OnClick(R.id.btnReadSp)
  public void btnReadSp_OnClick(View view){
    log.info("btnReadSp_OnClick");
    SharedPreferences sharedPreferences = super.getSharedPreferences("watermark", Context.MODE_PRIVATE);
    String string = sharedPreferences.getString("watermark", "请输入水印信息");
    textView03.setText(string);
  }
}