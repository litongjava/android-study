package com.litongjava.androidbasestudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;
import com.litongjava.androidbasestudy.adapter.RecyclerViewAdapter;
import com.litongjava.androidbasestudy.listener.OnRecyclerItemClickListener;
import com.litongjava.androidbasestudy.model.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@FindViewByIdLayout(R.layout.activity_recycler_view)
public class RecyclerViewActivity extends AppCompatActivity {

  private Logger log = LoggerFactory.getLogger(this.getClass());
  @FindViewById(R.id.recyclerView01)
  private RecyclerView recyclerView01;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_recycler_view);
    ViewInjectUtils.injectActivity(this, this);

    List<Bean> data = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      Bean bean = new Bean();
      bean.setName("Item_" + i);
      data.add(bean);
    }
    //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    //recyclerView01.setLayoutManager(linearLayoutManager);
//    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
//    recyclerView01.setLayoutManager(gridLayoutManager);
    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL);
    recyclerView01.setLayoutManager(staggeredGridLayoutManager);

    OnRecyclerItemClickListener listener=(position) -> {
      log.info("position:{}",position);
    };

    RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, data, listener);
    recyclerView01.setAdapter(adapter);
  }
}