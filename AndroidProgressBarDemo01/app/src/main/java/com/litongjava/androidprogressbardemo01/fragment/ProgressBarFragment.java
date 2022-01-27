package com.litongjava.androidprogressbardemo01.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidprogressbardemo01.R;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProgressBarFragment extends Fragment {

  @FindViewById(R.id.btn01)
  private Button btn1;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_progress_bar, container, false);
    initView(view);
    ViewInjectUtils.injectView(view, this);
    ViewInjectUtils.injectOnClick(view, this);
    return view;

  }

  @OnClick(R.id.btn01)
  public void btn01OnClick() {
    log.info("click");
    DialogProgressFramgent dialogProgressFramgent = new DialogProgressFramgent();
    dialogProgressFramgent.show(getFragmentManager(), "progress");
  }

  private void initView(View view) {

  }
}