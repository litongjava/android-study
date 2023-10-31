package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.example.androidxbasestudy.R;
import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@FindViewByIdLayout(R.layout.activity_android_path)
public class AndroidPathActivity extends AppCompatActivity {
  private Logger log= LoggerFactory.getLogger(this.getClass());

  @FindViewById(R.id.textViewLog)
  private TextView textViewLog;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this,this);
  }
  @OnClick(R.id.btnShowPath)
  public void btnShowPath_OnClick(View view){
    Context applicationContext = this.getApplicationContext();
    File filesDir = applicationContext.getFilesDir();
    File cacheDir = applicationContext.getCacheDir();
    File externalStorageDirectory = Environment.getExternalStorageDirectory();
    File logs = applicationContext.getExternalFilesDir("logs");
    File externalCacheDir = applicationContext.getExternalCacheDir();

    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("filesDir:"+filesDir+"\r\n");
    stringBuffer.append("cacheDir:"+cacheDir+"\r\n");
    stringBuffer.append("externalStorageDirectory:"+externalStorageDirectory+"\r\n");
    stringBuffer.append("logs:"+logs+"\r\n");
    stringBuffer.append("externalCacheDir:"+externalCacheDir+"\r\n");

    log.info(stringBuffer.toString());

    textViewLog.setText(stringBuffer.toString());
  }
}
