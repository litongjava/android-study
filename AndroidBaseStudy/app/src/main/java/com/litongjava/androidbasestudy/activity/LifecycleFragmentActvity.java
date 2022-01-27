package com.litongjava.androidbasestudy.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;
import com.litongjava.androidbasestudy.fragment.BlankFragment03;

@FindViewByIdLayout(R.layout.activity_lifecycle_fragment_actvity)
public class LifecycleFragmentActvity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this,this);
  }

  @OnClick(R.id.btn07)
  public void btn06OnClick(){
    //启动Fragment
    BlankFragment03 blankFragment03 = new BlankFragment03();
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.frameLayout02,blankFragment03);
    fragmentTransaction.commit();
  }
}