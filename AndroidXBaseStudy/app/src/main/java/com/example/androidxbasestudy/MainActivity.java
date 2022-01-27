package com.example.androidxbasestudy;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidxbasestudy.data.ActivityModel;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //setContentView(R.layout.activity_main);
    //获取viewGroup
    LayoutInflater layoutInflater = LayoutInflater.from(this);
    View view = layoutInflater.inflate(R.layout.activity_main, null);
    ViewGroup viewGroup = (ViewGroup) view;

    //初始化视图
    List<View> childVews = initView();
    //initView(viewGroup);
    //添加button到ViewGroup
    for (View childVew : childVews) {
      viewGroup.addView(childVew);
    }
    //绑定ViewGroup到Activity
    setContentView(viewGroup);

  }

  private List<View> initView() {
    List<String> activityNameList = ActivityModel.getActivityNames();
    String packageName = getPackageName();
    log.info("packageName:{}", packageName);

    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    List<View> childVews = new ArrayList<>();
    //创建Button,并添加到Activity
    for (final String s : activityNameList) {
      log.info("generate button:{}", s);
      //初始化Button
      Button button = new Button(this);
      button.setText(s);
      button.setLayoutParams(params);
      childVews.add(button);
      //添加启动Activity的代码
      button.setOnClickListener((v) -> {
        //启动启动对应的Activity
        //String packageName="com.litongjava.androidbasestudy";
        String className = packageName + ".activity." + s;
        log.info("start Activity:{}", className);
        ComponentName cn = new ComponentName(packageName, className);
        Intent intent = new Intent();
        intent.setComponent(cn);
        startActivity(intent);
      });
    }
    return childVews;
  }


}