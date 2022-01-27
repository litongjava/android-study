package com.litongjava.criminal.intent.criminalintentjava.activity;

import android.support.v4.app.Fragment;

import com.litongjava.criminal.intent.criminalintentjava.fragment.CrimeListFragment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CrimeListActivity extends SingleFragmentAcitvity {
  @Override
  protected Fragment createFragment() {
    log.info("create fragment");
    return new CrimeListFragment();
  }
}
