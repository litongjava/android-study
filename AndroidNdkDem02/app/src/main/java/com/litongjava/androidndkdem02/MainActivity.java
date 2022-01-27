package com.litongjava.androidndkdem02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    MyNdk myNdk = new MyNdk();
    TextView tv = (TextView) findViewById(R.id.tv);
    tv.setText( myNdk.getMyString());
  }
}
