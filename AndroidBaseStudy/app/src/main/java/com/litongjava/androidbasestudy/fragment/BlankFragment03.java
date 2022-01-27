package com.litongjava.androidbasestudy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.litongjava.androidbasestudy.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlankFragment03 extends Fragment {

  private Logger log= LoggerFactory.getLogger(this.getClass());

  @Override
  public void onAttach(Context context) {
    log.info("onAttach");
    super.onAttach(context);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    log.info("onCreate");
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    log.info("onCreateView");
    return inflater.inflate(R.layout.fragment_blank03, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    log.info("onActivityCreated");
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public void onStart() {
    log.info("onStart");
    super.onStart();
  }

  @Override
  public void onResume() {
    log.info("onResume");
    super.onResume();
  }

  @Override
  public void onPause() {
    log.info("onPause");
    super.onPause();
  }

  @Override
  public void onStop() {
    log.info("onStop");
    super.onStop();
  }

  @Override
  public void onDestroyView() {
    log.info("onDestroyView");
    super.onDestroyView();
  }

  @Override
  public void onDestroy() {
    log.info("onDestroy");
    super.onDestroy();
  }

  @Override
  public void onDetach() {
    log.info("onDetach");
    super.onDetach();
  }
}