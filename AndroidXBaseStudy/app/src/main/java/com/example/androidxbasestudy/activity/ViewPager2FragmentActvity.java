package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.androidxbasestudy.R;
import com.example.androidxbasestudy.adapter.FragmentViewPager2Adapter;
import com.example.androidxbasestudy.fragment.BlankFragment04;
import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import java.util.ArrayList;
import java.util.List;

@FindViewByIdLayout(R.layout.activity_view_pager2_fragment_actvity)
public class ViewPager2FragmentActvity extends AppCompatActivity {

  @FindViewById(R.id.viewPager03)
  private ViewPager2 viewPager03;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this,this);

    initPageer();
  }

  private void initPageer() {
    FragmentManager fragmentManager = getSupportFragmentManager();
    Lifecycle lifecycle = getLifecycle();
    List<Fragment> fragments=getFragments();
    FragmentViewPager2Adapter adapter = new FragmentViewPager2Adapter(fragmentManager,lifecycle,fragments);
    viewPager03.setAdapter(adapter);

  }

  private List<Fragment> getFragments() {
    BlankFragment04 fragment01 = BlankFragment04.newInstance("微信聊天");
    BlankFragment04 fragment02 = BlankFragment04.newInstance("通讯录");
    BlankFragment04 fragment03 = BlankFragment04.newInstance("发现");
    BlankFragment04 fragment04 = BlankFragment04.newInstance("我的");

    List<Fragment> fragments=new ArrayList<>(4);
    fragments.add(fragment01);
    fragments.add(fragment02);
    fragments.add(fragment03);
    fragments.add(fragment04);
    return fragments;
  }
}