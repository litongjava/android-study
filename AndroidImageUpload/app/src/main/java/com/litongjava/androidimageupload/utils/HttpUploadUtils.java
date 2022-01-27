package com.litongjava.androidimageupload.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpUploadUtils {

  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  public static String upload(String uploadRequestUrl, String localFilePath) throws IOException {

    File file = new File(localFilePath);
    if (!file.exists() || !file.isFile()) { // 如果文件不存在抛出异常
      throw new IOException("文件不存在");
    }

    try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
      String result = upload(uploadRequestUrl, "file", file.getName(), dataInputStream);
      System.out.println(result);
      return result;
    }
  }

  public static String upload(String uploadRequestUrl, String name, String filename, InputStream inputStream)
      throws MalformedURLException, IOException, ProtocolException, UnsupportedEncodingException {
    String contentDisposition = "Content-Disposition: form-data;name=\"%s\";filename=\"%s\"\r\n";
    contentDisposition = String.format(contentDisposition, name, filename);
    
    URL httpURL = new URL(uploadRequestUrl); // 转为URL类
    HttpURLConnection httpURLConnection = (HttpURLConnection) httpURL.openConnection(); // 打开连接

    httpURLConnection.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
    httpURLConnection.setDoInput(true); // 必须设置
    httpURLConnection.setDoOutput(true); // 必须设置
    httpURLConnection.setUseCaches(false); // post方式不能使用缓存
    // 设置请求头信息
    httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
    httpURLConnection.setRequestProperty("Charset", "UTF-8");

    // 设置边界
    String BOUNDARY = "----------" + System.currentTimeMillis();
    httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

    // 请求正文信息
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("--"); // 必须多两道线
    stringBuffer.append(BOUNDARY);
    stringBuffer.append("\r\n");

    System.out.println(contentDisposition);
    stringBuffer.append(contentDisposition);
    stringBuffer.append("Content-Type:application/octet-stream\r\n\r\n");

    byte[] head = stringBuffer.toString().getBytes("utf-8");

    // 获得输出流,将远程url输出的部分
    OutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
    // 输出表头
    dataOutputStream.write(head);

    // 文件正文部分
    // 把文件一流文件方式 推入到url中

    int length = 0;
    byte[] bufferOut = new byte[1024 * 1024 * 2]; // 2M
    while ((length = inputStream.read(bufferOut)) != -1) {
      dataOutputStream.write(bufferOut, 0, length);
    }
    inputStream.close();

    // 结尾部分
    byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
    dataOutputStream.write(foot);
    dataOutputStream.flush();
    dataOutputStream.close();

    stringBuffer = new StringBuffer();
    BufferedReader bufferedReader = null;
    try {
      // 定义BufferedReader输入流来读取URL的响应
      bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
      String line = null;
      while ((line = bufferedReader.readLine()) != null) {
        stringBuffer.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(bufferedReader);
    }
    return stringBuffer.toString();
  }
}
