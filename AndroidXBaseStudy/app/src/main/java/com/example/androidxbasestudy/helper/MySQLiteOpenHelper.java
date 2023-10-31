package com.example.androidxbasestudy.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.androidxbasestudy.R;
import com.litongjava.android.view.inject.annotation.OnClick;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022-04-18
 * 单例模式
 * 1.构造函数私有化
 * 2.对外提供获取实例的函数
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
  private Logger log = LoggerFactory.getLogger(this.getClass());
  private static MySQLiteOpenHelper instance;

  public static synchronized MySQLiteOpenHelper getInstance(Context context) {
    if (instance == null) {
      instance = new MySQLiteOpenHelper(context, "ping.db", null, 1);
    }
    return instance;
  }

  /**
   * @param context
   * @param name    名字
   * @param factory
   * @param version 版本号,必须从1开始
   */
  private MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory,
                             int version) {
    super(context, name, factory, version);
    log.info("name:{},version:{}", name, version);
  }

  /**
   * 当数据库创建时执行
   *
   * @param db
   */
  @Override
  public void onCreate(SQLiteDatabase db) {
    log.info("db:{}", db);
    //创建表,注解有两个要求 1.列名推荐是_id,2.类型推荐是integer
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("create table person(");
    stringBuffer.append("_id integer primary key autoincrement,");
    stringBuffer.append("name text");
    stringBuffer.append(")");

    db.execSQL(stringBuffer.toString());

  }

  /**
   * 当数据升级时
   *
   * @param db
   * @param oldVersion
   * @param newVersion
   */
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    log.info("db:{},oldVersion:{},newVersion:{}", db, oldVersion, newVersion);
  }
}
