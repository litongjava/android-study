package com.example.androidxbasestudy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.androidxbasestudy.R;
import com.example.androidxbasestudy.helper.MySQLiteOpenHelper;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@FindViewByIdLayout(R.layout.activity_sqlite_curd)
public class SqliteCurdActivity extends AppCompatActivity {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
  }


  @OnClick(R.id.btnSqlLiteCreateDb)
  public void btnSqlLiteCreateDbOnClick(View view) {
    MySQLiteOpenHelper instance = MySQLiteOpenHelper.getInstance(this.getBaseContext());
    SQLiteDatabase sqLiteDatabase = instance.getWritableDatabase();
  }

  @OnClick(R.id.btnSqlLiteSelect)
  public void btnSqlLiteSelect(View view) {
    MySQLiteOpenHelper helper = MySQLiteOpenHelper.getInstance(getBaseContext());
    SQLiteDatabase database = helper.getReadableDatabase();
    if (!database.isOpen()) {
      log.info("数据库打开失败");
      return;
    }
    //执行查询语句,返回游标
    Cursor cursor = database.rawQuery("select * from android_metadata", null);
    //向下移动
    while (cursor.moveToNext()) {
      String string = cursor.getString(0);
      //string:en_US
      log.info("string:{}", string);
      //获取列名的类索引
      int localeIndex = cursor.getColumnIndex("locale");
      String locale = cursor.getString(localeIndex);
      log.info("locale:{}", locale);
    }
    //查询Person数据库
    cursor = database.rawQuery("select * from person", null);
    while (cursor.moveToNext()) {
      int columnCount = cursor.getColumnCount();
      for (int i = 0; i < columnCount; i++) {
        String string = cursor.getString(i);
        log.info("index:{},value:{}", i, string);
      }
    }

    //关闭游标,减少性能消耗
    cursor.close();
    //关闭数据库
    database.close();
  }

  @OnClick(R.id.btnSqlLiteInsert)
  public void btnSqlLiteInsertOnClick(View view) {
    MySQLiteOpenHelper helper = MySQLiteOpenHelper.getInstance(getBaseContext());
    SQLiteDatabase database = helper.getWritableDatabase();
    if (!database.isOpen()) {
      log.info("数据打开失败");
      return;
    }
    String sql = "insert into person(name) values('ping')";
    database.execSQL(sql);
    database.close();
  }

  @OnClick(R.id.btnSqlLiteUpdate)
  public void btnSqlLiteUpdateOnClick(View view) {
    MySQLiteOpenHelper helper = MySQLiteOpenHelper.getInstance(getBaseContext());
    SQLiteDatabase database = helper.getWritableDatabase();
    if (!database.isOpen()) {
      log.info("数据打开失败");
      return;
    }
    String sql = "update person set name=?  where _id=?";
    database.execSQL(sql, new Object[]{"Ping E Lee", 1});
    database.close();
  }

  @OnClick(R.id.btnSqlLiteDelete)
  public void btnSqlLiteDeleteOnClick(View view) {
    MySQLiteOpenHelper helper = MySQLiteOpenHelper.getInstance(getBaseContext());
    SQLiteDatabase database = helper.getWritableDatabase();
    if (!database.isOpen()) {
      log.info("数据库打开失败");
      return;
    }
    String sql = "delete from person where _id=?";
    Object[] bindArgs = {1};
    log.info("删除数据:{}", Arrays.toString(bindArgs));
    database.execSQL(sql, bindArgs);
    database.close();
  }
}