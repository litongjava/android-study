package com.example.androidxbasestudy.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/12
 */
public class BitmapUtils {
  private static Logger log = LoggerFactory.getLogger(BitmapUtils.class);

  /**
   * 将多张图片合并为一张图片
   *
   * @param text
   * @param list
   * @return
   */

  public static Bitmap merge(String text, List<Bitmap> list) {

    int size = list.size();
    Bitmap bitmap01 = list.get(0);
    //1.计算宽高
    int srcWidth = bitmap01.getWidth();
    int srcHeight = bitmap01.getHeight();

    int dstWidth = srcWidth * size;
    int dstHeight = srcHeight;

    //2.创建空白的bitmap
    Bitmap dstBitmap = Bitmap.createBitmap(dstWidth, dstHeight, bitmap01.getConfig());

    //3.初始化华画布
    Canvas canvas = new Canvas(dstBitmap);
    //4.绘制bitMap
    Paint paint = new Paint();
    //绘制图片
    for (int i = 0; i < size; i++) {
      canvas.drawBitmap(list.get(i), srcWidth * i, 0, paint);
    }
    //绘制字体
    paint.setTextSize(100);
    paint.setColor(Color.RED);

    //计算x的坐标值
    int x=dstWidth / (size * 2) * (size - 1);
    try {
      canvas.drawText(text,x, dstHeight / 2, paint);
    } catch (Exception e) {
      log.info("添加文字异常,请联系管理员解决");
      e.printStackTrace();
    }
    return dstBitmap;
  }

  /**
   *
   * @param src
   * @param paint 画笔
   * @param text
   * @param paddingLeft
   * @param paddingTop
   * @return
   */
  public static Bitmap drawTextToBitmap(Bitmap src,Paint paint,String text,int paddingLeft,int paddingTop){
    //获取Bitmap配置
    Bitmap.Config config = src.getConfig();
    //获取清晰的图像采样
    paint.setDither(true);
    //过滤bitmap
    paint.setFilterBitmap(true);
    if(config!=null){
      config=Bitmap.Config.ARGB_8888;
    }
    //复制一份bitmap
    Bitmap dst = src.copy(config, true);
    //初始化Canvas,画布
    Canvas canvas = new Canvas(dst);
    //绘制文字
    canvas.drawText(text,paddingLeft,paddingTop,paint);
    //返回
    return dst;
  }
}