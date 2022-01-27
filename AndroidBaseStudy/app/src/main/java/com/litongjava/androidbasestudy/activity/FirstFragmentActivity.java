package com.litongjava.androidbasestudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.litongjava.androidbasestudy.R;

public class FirstFragmentActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_fragment);
  }
}