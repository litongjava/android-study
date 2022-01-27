package com.litongjava.androidbasestudy;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private Logger log= LoggerFactory.getLogger(this.getClass());
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //setContentView(R.layout.activity_main);
    //获取viewGroup
    LayoutInflater layoutInflater = LayoutInflater.from(this);
    View view = layoutInflater.inflate(R.layout.activity_main, null);
    ViewGroup viewGroup = (ViewGroup) view;

    ViewGroup linearLayout01 = (ViewGroup)view.findViewById(R.id.linearLayout01);

    //初始化视图
    List<View> childVews = initView();
    //initView(viewGroup);
    //添加button到ViewGroup
    for (View childVew : childVews) {
      linearLayout01.addView(childVew);
    }
    //绑定ViewGroup到Activity
    setContentView(viewGroup);

  }

  /**
   * 手动初始化组件
   *
   * @param viewGroup
   */
  private void initView(ViewGroup viewGroup) {

    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    //初始化Button
    Button button01 = new Button(this);
    button01.setText("ImageViewActivity");
    button01.setLayoutParams(params);
    viewGroup.addView(button01);

    //初始化Button
    Button button02 = new Button(this);
    button02.setText("NotificationActivity");
    button02.setLayoutParams(params);
    viewGroup.addView(button02);
  }

  private List<View> initView() {
    List<String> activityNameList = getActivityNames();
    String packageName = getPackageName();
    log.info("packageName:{}", packageName);

    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    List<View> childVews = new ArrayList<>();
    //创建Button,并添加到Activity
    for (final String s : activityNameList) {
      log.info("generate button:{}",s);
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

  @NonNull
  private List<String> getActivityNames() {
    List<String> activityNameList = new ArrayList<>();
    activityNameList.add("ImageViewActivity");
    activityNameList.add("NotificationActivity");
    activityNameList.add("ImageShowActivity");
    activityNameList.add("ToolbarActivity");
    activityNameList.add("AlertDialogActivity");
    activityNameList.add("PopupWindowActivity");
    activityNameList.add("LinearLayoutActivity");
    activityNameList.add("ConstraintLayoutActivity");
    activityNameList.add("ListViewActivity");
    activityNameList.add("RecyclerViewActivity");
    activityNameList.add("FrameByFrameAnimationActivity");
    activityNameList.add("TweenedAnimationActivity");
    activityNameList.add("PropertyAnimationActivity");
    activityNameList.add("LayoutParamsActivity");
    activityNameList.add("ViewPagerActivity");
    activityNameList.add("FirstFragmentActivity");
    activityNameList.add("DynamicAddtionFragmentActvity");
    activityNameList.add("LifecycleFragmentActvity");
    return activityNameList;
  }

}
