package com.litongjava.androidbasestudy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/12
 */
public class BitmapUtils {
  /**
   * 合并两个bitmap为一个bitmap
   *
   * @param bitmap01
   * @param bitmap02
   * @return
   */
  public static Bitmap merge(Bitmap bitmap01, Bitmap bitmap02, String text) {
    //1.计算宽高
    int srcWidth = bitmap01.getWidth();
    int srcHeight = bitmap01.getHeight();

    int dstWidth = srcWidth * 2;
    int dstHeight = srcHeight * 2;

    //2.创建空白的bitmap
    Bitmap dstBitmap = Bitmap.createBitmap(srcWidth, srcHeight, bitmap01.getConfig());

    //3.初始化华画布
    Canvas canvas = new Canvas(dstBitmap);
    //4.绘制bitMap
    Paint paint = new Paint();
    //绘制第一张
    canvas.drawBitmap(bitmap01, 0, 0, paint);
    //绘制第二张
    canvas.drawBitmap(bitmap02, 0, srcWidth, paint);
    //绘制字体
    paint.setTextSize(100);
    paint.setColor(Color.RED);
    canvas.drawText(text, srcWidth / 2, srcHeight / 2, paint);
    return dstBitmap;
  }
}
