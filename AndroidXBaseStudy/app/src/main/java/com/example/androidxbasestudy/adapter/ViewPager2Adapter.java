package com.example.androidxbasestudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidxbasestudy.R;
import com.example.androidxbasestudy.viewholder.ViewPage2ViewHolder;

import java.util.List;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/25
 */
public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPage2ViewHolder> {
  private List<String> titles;

  public ViewPager2Adapter(List<String> titles) {
    this.titles = titles;
  }

  @NonNull
  @Override
  public ViewPage2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    View view = layoutInflater.inflate(R.layout.layout_view_pager2_item, parent, false);
    return new ViewPage2ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewPage2ViewHolder holder, int position) {
    holder.setTitle(titles.get(position));

  }

  @Override
  public int getItemCount() {
    return titles.size();
  }
}