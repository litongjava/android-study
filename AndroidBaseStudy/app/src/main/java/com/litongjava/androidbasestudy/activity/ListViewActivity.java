package com.litongjava.androidbasestudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;
import com.litongjava.androidbasestudy.adapter.MyAdapter;
import com.litongjava.androidbasestudy.model.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@FindViewByIdLayout(R.layout.activity_list_view)
public class ListViewActivity extends AppCompatActivity {

  private Logger log= LoggerFactory.getLogger(this.getClass());

  private List<Bean> data = new ArrayList<>();
  @FindViewById(R.id.listView01)
  private ListView listView01;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
    for (int i = 0; i < 100; i++) {
      Bean bean = new Bean();
      bean.setName("Item_" + i);
      data.add(bean);
    }
    listView01.setAdapter(new MyAdapter(data,this));

    //添加单击事件
    listView01.setOnItemClickListener((parent, view, position, id)->{
      log.info("positon:{}",position);

    });
  }
}