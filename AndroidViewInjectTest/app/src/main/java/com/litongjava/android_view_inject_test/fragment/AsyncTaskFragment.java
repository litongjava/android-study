package com.litongjava.android_view_inject_test.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.android_view_inject_test.R;



public class AsyncTaskFragment extends Fragment{

  @FindViewById(R.id.btn1)
  private Button button1;


  public static Fragment newInstance() {
    return new AsyncTaskFragment();
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_async, container, false);
    ViewInjectUtils.injectViewAndOnClick(view,this);
    return view;
  }


  @OnClick(R.id.btn1)
  public void button1OnClick(){
    Toast.makeText(this.getActivity(),"button 1 click",Toast.LENGTH_LONG).show();
  }
}