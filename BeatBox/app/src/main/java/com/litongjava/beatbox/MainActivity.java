package com.litongjava.beatbox;


import android.support.v4.app.Fragment;

import com.litongjava.beatbox.activity.SingleFragmentActivity;
import com.litongjava.beatbox.fragment.BeatBoxFragment;


public class MainActivity extends SingleFragmentActivity {
  @Override
  protected Fragment createFragment() {
    return BeatBoxFragment.newInstance();
  }
}
