package com.example.androidxbasestudy;

import android.app.Application;

import com.example.androidxbasestudy.database.StudentDatabase;
import com.litongjava.jfinal.aop.AopManager;

public class AndroidXApp extends Application {
  @Override
  public void onCreate() {
    super.onCreate();

    StudentDatabase studentDatabase = StudentDatabase.getInstance(getBaseContext());
    AopManager.me().addMapping(StudentDatabase.class, studentDatabase.getClass());
    AopManager.me().addSingletonObject(studentDatabase);
  }
}
