package com.litongjava.androidprogressbardemo01.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidprogressbardemo01.R;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AsyncProgressFragment extends Fragment {

  @FindViewById(R.id.btnUpload)
  private Button btnUpload;
  @FindViewById(R.id.asyprogss)
  private ProgressBar mProgressBar;

  private MyProgressAsyncTask mTask;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.async_progress, container, false);
    ViewInjectUtils.injectView(view, this);
    ViewInjectUtils.injectOnClick(view, this);
    return view;
  }

  @OnClick(R.id.btnUpload)
  private void btn01OnClick(){
    log.info("发送请求");
    mTask =new MyProgressAsyncTask();
    mTask.execute();
  }

  private class MyProgressAsyncTask extends AsyncTask<Void, Integer, Void> {


    @Override
    protected Void doInBackground(Void... params) {

      //模拟进度条更新
      for (int i = 0; i < 100; i++) {
        //判断是否为取消状态如果是则跳出循环同时通知onProgressUpdate（）也判断一下 是否为取消状态
        if(isCancelled()){
          break;
        }
        //调用publishProgress（）方法才能触发onProgressUpdate（）方法
        publishProgress(i);
        try {
          //睡眠时间延缓时间的发生
          Thread.sleep(300);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      return null;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

      if(isCancelled()){
        return;
      }
//            super.onProgressUpdate(values);
      mProgressBar.setProgress(values[0]);
      if (values[0]==99){
        Toast.makeText(getActivity(),"完成",Toast.LENGTH_LONG).show();
      }
    }
  }
}
