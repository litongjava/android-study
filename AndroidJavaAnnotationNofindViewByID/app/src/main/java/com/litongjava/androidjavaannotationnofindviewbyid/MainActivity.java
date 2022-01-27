package com.litongjava.androidjavaannotationnofindviewbyid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.litongjava.androidjavaannotationnofindviewbyid.annotation.FindViewById;
import com.litongjava.androidjavaannotationnofindviewbyid.annotation.FindViewByIdLayout;
import com.litongjava.androidjavaannotationnofindviewbyid.annotation.OnClick;
import com.litongjava.androidjavaannotationnofindviewbyid.utils.ViewUtils;

@FindViewByIdLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

  @FindViewById(R.id.tv)
  private TextView mText;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_main);
    ViewUtils.inject(this);
    mText.setText("Annotation");
  }

  @OnClick(R.id.tv)
  public void onClick(View view) {
    Toast.makeText(this, "on click", Toast.LENGTH_LONG).show();
  }
}
