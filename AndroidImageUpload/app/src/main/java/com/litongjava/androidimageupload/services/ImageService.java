package com.litongjava.androidimageupload.services;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.widget.ImageView;

import com.litongjava.androidimageupload.utils.HttpUploadUtils;
import com.litongjava.androidimageupload.utils.ToastUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImageService {
  private class UploadAsyncTask extends AsyncTask<String,Void,String>{

    private InputStream inputStream;
    public UploadAsyncTask(InputStream inputStream) {
      this.inputStream=inputStream;
    }

    @Override
    protected String doInBackground(String... strings) {
      try {
        return HttpUploadUtils.upload(strings[0],strings[1],strings[2],inputStream);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }

    @Override
    protected void onPostExecute(String s) {
      log.info("responseString:{}",s);
    }
  }
  public void upload(ImageView imageViewShow) {
    log.info("上传图片");
    Bitmap bitmap =((BitmapDrawable) ((ImageView) imageViewShow).getDrawable()).getBitmap();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
    InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    UploadAsyncTask uploadAsyncTask = new UploadAsyncTask(inputStream);
    //上传地址
    String requestUrl="http://192.168.104.101:10041/opencv-engine/image/upload";
    uploadAsyncTask.execute(requestUrl,"file",System.currentTimeMillis()+".jpg");
  }
}
