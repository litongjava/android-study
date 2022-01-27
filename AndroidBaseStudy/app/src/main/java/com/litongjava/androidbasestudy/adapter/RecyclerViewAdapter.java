package com.litongjava.androidbasestudy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.litongjava.androidbasestudy.R;
import com.litongjava.androidbasestudy.listener.OnRecyclerItemClickListener;
import com.litongjava.androidbasestudy.model.Bean;

import java.util.List;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/16
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewViewHolder> {
  private Context context;
  private List<Bean> data;
  private OnRecyclerItemClickListener listener;

  public RecyclerViewAdapter(Context context, List<Bean> data) {
    this.context = context;
    this.data = data;
  }

  public RecyclerViewAdapter(Context context, List<Bean> data, OnRecyclerItemClickListener listener) {
    this.context = context;
    this.data = data;
    this.listener = listener;
  }

  @NonNull
  @Override
  public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = View.inflate(context, R.layout.recycler_view_item, null);
    return new RecyclerViewViewHolder(view,listener);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int i) {
    holder.getTextView().setText(data.get(i).getName());

  }

  @Override
  public int getItemCount() {
    return data == null ? 0 : data.size();
  }
}
