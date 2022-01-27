package com.litongjava.androidbasestudy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.litongjava.androidbasestudy.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlankFragment01 extends Fragment {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle = getArguments();
    if (bundle != null) {
      String message = bundle.getString("message");
      log.info("message:{}", message);
      Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = null;
    if(rootView!=null){
      rootView = inflater.inflate(R.layout.fragment_blank01, container, false);
    }
    return rootView;
  }
}