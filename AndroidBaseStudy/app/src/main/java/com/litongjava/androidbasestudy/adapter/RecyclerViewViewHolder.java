package com.litongjava.androidbasestudy.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.litongjava.androidbasestudy.R;
import com.litongjava.androidbasestudy.listener.OnRecyclerItemClickListener;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/16
 */
public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
  private TextView textView;
  private OnRecyclerItemClickListener listener;

  public RecyclerViewViewHolder(@NonNull View itemView) {
    super(itemView);
    initView();
  }

  public RecyclerViewViewHolder(View itemView, OnRecyclerItemClickListener listener) {
    super(itemView);
    this.listener = listener;
    initView();
  }

  public TextView getTextView() {
    return textView;
  }

  private void initView() {
    textView = itemView.findViewById(R.id.textView02);
    itemView.setOnClickListener((v) -> {
      if (listener != null) {
        listener.onRecyclerItemClick(getAdapterPosition());
      }
    });
  }
}
