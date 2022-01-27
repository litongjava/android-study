package com.example.androidxbasestudy.viewholder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidxbasestudy.R;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/25
 */
public class ViewPage2ViewHolder extends RecyclerView.ViewHolder {
  private TextView textViewTitle;
  private RelativeLayout container;

  public ViewPage2ViewHolder(@NonNull View itemView) {
    super(itemView);
    container=itemView.findViewById(R.id.viewPager2Container);
    textViewTitle=itemView.findViewById(R.id.textViewTitle);
  }

  public void setTitle(String s) {
    textViewTitle.setText(s);
  }
}