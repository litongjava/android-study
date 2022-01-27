package com.litongjava.androidbasestudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.litongjava.androidbasestudy.R;
import com.litongjava.androidbasestudy.model.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/16
 */
public class MyAdapter extends BaseAdapter {

  private Logger log = LoggerFactory.getLogger(this.getClass());
  private List<Bean> data;
  private Context context;

  public MyAdapter(List<Bean> data, Context context) {
    this.data = data;
    this.context = context;

  }

  @Override
  public int getCount() {
    return data.size();
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder viewHolder = null;
    if (convertView == null) {//防止convertView不断创建
      LayoutInflater layoutInflater = LayoutInflater.from(context);
      convertView = layoutInflater.inflate(R.layout.list_item, parent, false);

      viewHolder=new ViewHolder();
      viewHolder.textView = convertView.findViewById(R.id.textView01);

      convertView.setTag(viewHolder);
    }else{
      viewHolder= (ViewHolder) convertView.getTag();
    }

    log.info("position:{}", position);
    viewHolder.textView.setText(data.get(position).getName());
    return convertView;
  }

  private final class ViewHolder {
    //子控件的数量取决于Item中的控件数量
    TextView textView;
  }
}
