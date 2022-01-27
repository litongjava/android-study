package com.litongjava.androidbasestudy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;
import com.litongjava.androidbasestudy.callback.IFragmentCallback;

public class BlankFragment02 extends Fragment {

  private IFragmentCallback iCallback;

  public void setFragmentCallback(IFragmentCallback iCallback) {
    this.iCallback = iCallback;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {

    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = null;
    if (rootView == null) {
      rootView = inflater.inflate(R.layout.fragment_blank02, container, false);
      ViewInjectUtils.injectViewAndOnClick(rootView, this);
    }
    return rootView;
  }

  @OnClick(R.id.btn06)
  public void btn06OnClick() {
    //发送信息到Activity
    iCallback.sendMsgToActivity("Hello,this is BlankFragment02");
    //从Activity接收信息
    String message = iCallback.getMsgFromActivity(null);
    Toast.makeText(this.getActivity(), message, Toast.LENGTH_SHORT).show();
  }
}