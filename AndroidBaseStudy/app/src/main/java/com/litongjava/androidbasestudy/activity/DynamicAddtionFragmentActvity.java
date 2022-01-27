package com.litongjava.androidbasestudy.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;
import com.litongjava.androidbasestudy.callback.IFragmentCallback;
import com.litongjava.androidbasestudy.fragment.BlankFragment01;
import com.litongjava.androidbasestudy.fragment.BlankFragment02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 动态添加Fragment
 */
@FindViewByIdLayout(R.layout.activity_dynamic_addtion_fragment_actvity)
public class DynamicAddtionFragmentActvity extends AppCompatActivity {

  private Logger log = LoggerFactory.getLogger(this.getClass());
  @FindViewById(R.id.btn04)
  private Button btn04;

  @FindViewById(R.id.btn05)
  private Button btn05;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
  }


  @OnClick(R.id.btn04)
  public void btn04OnClick() {
    log.info("btn04");
    Bundle bundle = new Bundle();
    bundle.putString("message", "I am ping");
    BlankFragment01 blankFragment01 = new BlankFragment01();
    blankFragment01.setArguments(bundle);
    replaceFragemnt(blankFragment01);
  }

  @OnClick(R.id.btn05)
  public void btn05OnClick() {
    log.info("btn05");
    IFragmentCallback iCallback = new IFragmentCallback() {

      @Override
      public void sendMsgToActivity(String msg) {
        log.info("msg:{}", msg);
        Toast.makeText(DynamicAddtionFragmentActvity.this, msg, Toast.LENGTH_SHORT).show();
      }

      @Override
      public String getMsgFromActivity(String msg) {
        return "hello I'am from activity";
      }
    };
    BlankFragment02 fragment = new BlankFragment02();
    fragment.setFragmentCallback(iCallback);
    replaceFragemnt(fragment);
  }

  /**
   * 动态切换fragment
   *
   * @param fragment
   */
  private void replaceFragemnt(Fragment fragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.fragment03, fragment);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }
}