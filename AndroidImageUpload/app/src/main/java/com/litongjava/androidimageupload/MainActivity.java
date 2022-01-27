package com.litongjava.androidimageupload;

import android.support.v4.app.Fragment;

import com.litongjava.androidimageupload.activity.SingleFragmentActivity;
import com.litongjava.androidimageupload.fragment.ImageFragment;

public class MainActivity extends SingleFragmentActivity {

  @Override
  protected Fragment createFragment() {
    return ImageFragment.newInstance();
  }
}
