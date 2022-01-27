package com.litongjava.nerdlauncher;

import android.support.v4.app.Fragment;

import com.litongjava.nerdlauncher.activity.SingleFragmentActivity;
import com.litongjava.nerdlauncher.fragment.NerdLauncherFragment;

public class MainActivity extends SingleFragmentActivity {

  @Override
  protected Fragment createFragment() {
    return NerdLauncherFragment.newInstance();
  }
}
