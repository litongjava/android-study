package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.androidxbasestudy.R;
import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

@FindViewByIdLayout(R.layout.activity_radio_button)
public class RadioButtonActivity extends AppCompatActivity {

  @FindViewById(R.id.radioGroup)
  private RadioGroup radioGroup;
  @FindViewById(R.id.tvSex)
  private TextView tvSex;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);

    initListener();
  }

  private void initListener() {
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        //获取变更后的选中项的ID
        int radioButtonId = group.getCheckedRadioButtonId();
        //根据ID获取RadioButton的实例
        RadioButton rb = (RadioButton) RadioButtonActivity.this.findViewById(radioButtonId);
        //更新文本内容，以符合选中项
        tvSex.setText("您的性别是：" + rb.getText());
      }
    });
  }
}