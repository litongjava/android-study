package com.litongjava.androidaynctaskdemo01.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.litongjava.androidaynctaskdemo01.listener.OnDataFinishedListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImageView1Service {
  /**
   * 获取图片并设置
   *
   * @param im1
   * @param progressBar
   */

  public void getBitMapAndSet(final Context context, final ImageView im1, ProgressBar progressBar) {
    //new ProgressBar();
    String requestUrl="http://192.168.104.3/photos/0.jpg";
    OnDataFinishedListener listener = new OnDataFinishedListener() {
      @Override
      public void onDataSuccessfully(Object data) {
        Toast.makeText(context, "回传数据成功", Toast.LENGTH_SHORT).show();
        try {
          im1.setImageBitmap((Bitmap) data);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onDataFailed() {
        Toast.makeText(context, "加载失败！", Toast.LENGTH_SHORT).show();
      }
    };
    ImageViewAsyncTask task = new ImageViewAsyncTask(requestUrl,progressBar);
    task.setOnDataFinishedListener(listener);

    task.execute();
  }

  private class ImageViewAsyncTask extends AsyncTask<String, Integer, Bitmap> {

    private String requestUrl;
    private ProgressBar progressBar;
    private OnDataFinishedListener onDataFinishedListener;

    public ImageViewAsyncTask(String requestUrl) {
      this.requestUrl = requestUrl;
    }

    public ImageViewAsyncTask(String requestUrl, ProgressBar progressBar) {
      this.requestUrl = requestUrl;
      this.progressBar=progressBar;
    }


    public void setOnDataFinishedListener(OnDataFinishedListener onDataFinishedListener) {
      this.onDataFinishedListener = onDataFinishedListener;
    }


    @Override
    protected Bitmap doInBackground(String... params) {
      InputStream ins = null;
      Bitmap bitmap = null;
      try {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        log.info("responseCode:{}",responseCode);
        if (responseCode== HttpURLConnection.HTTP_OK) {
          ins = connection.getInputStream();
          bitmap = BitmapFactory.decodeStream(ins);
          return bitmap;
        }
      } catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (ins != null) {
          try {
            ins.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
      return null;
    }

    @Override
    protected void onPreExecute() {
      //progressBar.setVisibility(View.VISIBLE);
      super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap result) {
      //progressBar.setVisibility(View.GONE);
      if(onDataFinishedListener!=null){
        if (result != null) {
          onDataFinishedListener.onDataSuccessfully(result);
        } else {
          onDataFinishedListener.onDataFailed();
        }
      }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
      progressBar.setProgress(values[0]);
    }

  }
}
