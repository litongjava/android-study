package com.litongjava.androidbasestudy.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/21
 */
public class ViewPagerAdapter extends PagerAdapter {
  private List<View> listView;

  public ViewPagerAdapter(List<View> listView) {
    this.listView = listView;
  }

  @Override
  public int getCount() {
    return listView.size();
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
    return view==o;
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    container.addView(listView.get(position),0);
    return listView.get(position);
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView(listView.get(position));
  }
}
