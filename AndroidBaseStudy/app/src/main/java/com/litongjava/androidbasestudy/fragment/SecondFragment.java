package com.litongjava.androidbasestudy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.litongjava.androidbasestudy.R;

public class SecondFragment extends Fragment {

  private View root;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    if (root == null) {
      root = inflater.inflate(R.layout.fragment_first, container, false);
    }
    TextView textView04 = root.findViewById(R.id.textView04);
    Button btn03 = root.findViewById(R.id.btn03);

    btn03.setOnClickListener((v)->{
      textView04.setText("I am fine,Thank you,And you");
    });
    return root;
  }
}