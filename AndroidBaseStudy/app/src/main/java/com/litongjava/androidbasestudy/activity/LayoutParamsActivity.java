package com.litongjava.androidbasestudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LayoutParamsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LinearLayout linearLayout = new LinearLayout(this);

    int matchParent = ViewGroup.LayoutParams.MATCH_PARENT;
    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(matchParent, matchParent);
    linearLayout.setLayoutParams(layoutParams);

    TextView textView=createTextView();
    linearLayout.addView(textView);

    setContentView(linearLayout);
  }

  private TextView createTextView() {
    TextView textView = new TextView(this);
    textView.setText("我是文本");
    textView.setBackgroundColor(0xffff0000);
    //默认单位是px
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300,300);
    textView.setLayoutParams(layoutParams);
    return textView;
  }
}