package com.litongjava.androidimageupload.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author litong
 * @date 2019年4月30日_下午4:51:30
 * @version 1.0
 */
public class IOUtils {

  /**
   * 关闭流
   * 
   * @param stream
   */
  public static void closeQuietly(Closeable stream) {
    if (stream != null) {
      try {
        stream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
