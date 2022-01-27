package com.litongjava.androidprogressbardemo01;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.litongjava.androidprogressbardemo01.fragment.AsyncProgressFragment;
import com.litongjava.androidprogressbardemo01.fragment.ProgressBarFragment;
import com.litongjava.androidprogressbardemo01.fragment.SingleFragmentActivity;

public class MainActivity extends SingleFragmentActivity {

  @Override
  protected Fragment createFragment() {
    //return new ProgressBarFragment();
    return new AsyncProgressFragment();
  }
}
