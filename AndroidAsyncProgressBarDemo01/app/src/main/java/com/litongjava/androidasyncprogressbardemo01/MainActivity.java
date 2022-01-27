package com.litongjava.androidasyncprogressbardemo01;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@FindViewByIdLayout(R.layout.activity_main)
public class MainActivity extends Activity {

  @FindViewById(R.id.btnStart)
  private Button btnStart;
  @FindViewById(R.id.btnStop)
  private Button btnStop;
  @FindViewById(R.id.progressBar)
  private ProgressBar progressBar;

  private MyTask myTask;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.inject(this, this);
    init();
  }

  private void init() {
  }

  @OnClick(R.id.btnStart)
  public void btnStartOnClick() {
    myTask = new MyTask();
    myTask.execute();
    //显示,清空进度条
    progressBar.setVisibility(View.VISIBLE);
    progressBar.setProgress(0);
  }

  @OnClick(R.id.btnStop)
  public void btnStopOnClick() {
    if (myTask != null) {
      myTask.cancel(true);
    }
    //隐藏
    progressBar.setVisibility(View.GONE);
  }


  // 写一个类继承AsyncTask
  class MyTask extends AsyncTask<String, Integer, String> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      Log.i("qing", "onPreExecute");
    }

    @Override
    protected String doInBackground(String... arg0) {

      /*
       * 这里假设有循环，一定要写在try里面，同一时候一定要写休眠sleep，
       * 不然AsyncTask的cancel()方法运行时此AsyncTask无法停止，
       * 由于这整个AsyncTask停止时由于它在休眠时Thread.sleep()，
       * 点击了运行cancel()会报异常，于是退出。不然就会一直循环，
       * 尽管会运行cancel()方法。但这个AsyncTask不会停止。
       **/
      try {
        for (int i = 0; i <= 100; i++) {
          Thread.sleep(100);
          publishProgress(i);
        }
      } catch (InterruptedException e) {
        log.info("线程被被中断");
      }
      return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
      progressBar.setProgress(values[0]);
      log.info("progress:{}",values[0]);
    }

    @Override
    protected void onPostExecute(String result) {
      Toast.makeText(getApplication(), "进度结束", Toast.LENGTH_SHORT).show();
      log.info("reslt:{}",result);
    }

    @Override
    protected void onCancelled() {
      super.onCancelled();
      log.info("cancelled");
    }
  }
}