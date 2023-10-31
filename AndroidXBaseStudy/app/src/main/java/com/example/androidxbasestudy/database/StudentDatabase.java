package com.example.androidxbasestudy.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.androidxbasestudy.dao.StudentDao;
import com.example.androidxbasestudy.entity.Student;

/**
 * 尽量将Database类定义成单例模式
 */
@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

  private static String databaseNmae = "student_database";
  private static StudentDatabase instance;

  public static synchronized StudentDatabase getInstance(Context context) {
    if (instance == null) {
      Builder<StudentDatabase> builder = Room.databaseBuilder(context, StudentDatabase.class, databaseNmae);
      //默认访问数据库是在异步线程中,可以强制开启允许在主线程中范围数据库
      //builder.allowMainThreadQueries();
      instance = builder.build();
    }
    return instance;
  }

  /**
   * 提供dao对象的获取
   */
  public abstract StudentDao getDao();

  @NonNull
  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
    return null;
  }

  @NonNull
  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return null;
  }

  @Override
  public void clearAllTables() {

  }


}
