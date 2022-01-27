package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.androidxbasestudy.R;
import com.example.androidxbasestudy.adapter.ViewPager2Adapter;
import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import java.util.ArrayList;
import java.util.List;

@FindViewByIdLayout(R.layout.activity_view_pager2_actvity)
public class ViewPager2Actvity extends AppCompatActivity {

  @FindViewById(R.id.viewPager02)
  private ViewPager2 viewPager2;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this,this);

    List<String> titles=new ArrayList<>();
    titles.add("one");
    titles.add("two");
    titles.add("three");
    RecyclerView.Adapter viewPagerAdapter=new ViewPager2Adapter(titles);
    viewPager2.setAdapter(viewPagerAdapter);
  }
}