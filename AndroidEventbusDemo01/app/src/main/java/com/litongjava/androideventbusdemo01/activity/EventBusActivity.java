package com.litongjava.androideventbusdemo01.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androideventbusdemo01.R;

@FindViewByIdLayout(R.layout.activity_event_bus)
public class EventBusActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this,this);
  }
}
