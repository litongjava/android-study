package com.litongjava.androidbasestudy.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;
import com.litongjava.androidbasestudy.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

@FindViewByIdLayout(R.layout.activity_view_pager)
public class ViewPagerActivity extends AppCompatActivity {

  @FindViewById(R.id.viewPager01)
  private ViewPager viewPager01;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this,this);
    //创建补间
    LayoutInflater inflater = getLayoutInflater().from(this);
    View page01 = inflater.inflate(R.layout.layout_view_page_1, null);
    View page02 = inflater.inflate(R.layout.layout_view_page_2, null);
    View page03 = inflater.inflate(R.layout.layout_view_page_3, null);

    List<View> viewList=new ArrayList<>();
    viewList.add(page01);
    viewList.add(page02);
    viewList.add(page03);

    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(viewList);
    viewPager01.setAdapter(viewPagerAdapter);
  }


}