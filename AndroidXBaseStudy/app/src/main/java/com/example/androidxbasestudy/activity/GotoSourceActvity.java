package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidxbasestudy.R;
import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

@FindViewByIdLayout(R.layout.activity_goto_source_actvity)
public class GotoSourceActvity extends AppCompatActivity {

  @FindViewById(R.id.btnToActivity)
  private Button btnToActivity;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this,this);
  }


  @OnClick(R.id.btnToActivity)
  public void btnToActivity_OnClick(View view){
    Intent intent = new Intent(this, GotoTargetActvity.class);
    super.startActivity(intent);
  }
}