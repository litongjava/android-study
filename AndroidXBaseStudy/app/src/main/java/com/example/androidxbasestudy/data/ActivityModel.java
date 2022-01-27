package com.example.androidxbasestudy.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/26
 */
public class ActivityModel {
  public static List<String> getActivityNames() {
    List<String> activityNameList = new ArrayList<>();
    activityNameList.add("ViewPager2Actvity");
    activityNameList.add("ViewPager2FragmentActvity");
    activityNameList.add("ViewPager2WechatActvity");
    activityNameList.add("GotoSourceActvity");
    activityNameList.add("StartServiceActivity");
    activityNameList.add("SendBroadcastActivity");
    return activityNameList;
  }
}
