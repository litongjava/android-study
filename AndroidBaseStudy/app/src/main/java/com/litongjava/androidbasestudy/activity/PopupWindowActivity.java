package com.litongjava.androidbasestudy.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;

@FindViewByIdLayout(R.layout.activity_popup_window)
public class PopupWindowActivity extends AppCompatActivity {

  @FindViewById(R.id.btnShowPopupWindow)
  private Button btnShowPopupWindow;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this,this);
  }

  @OnClick(R.id.btnShowPopupWindow)
  public void btnShowPopupWindowOnClick(){
    //获取布局
    View popupView=getLayoutInflater().inflate(R.layout.popup_view,null);
    //设置自定义布局
    PopupWindow popupWindow=new PopupWindow(popupView,
      ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);

    //设置背景色
    Drawable drawable = getResources().getDrawable(R.drawable.ping_avatar);
    popupWindow.setBackgroundDrawable(drawable);
    //显示在按钮下方
    popupWindow.showAsDropDown(btnShowPopupWindow);

    View btn01 = popupView.findViewById(R.id.btn01);
    btn01.setOnClickListener((v ->{
      popupWindow.dismiss();
    }));
  }

}