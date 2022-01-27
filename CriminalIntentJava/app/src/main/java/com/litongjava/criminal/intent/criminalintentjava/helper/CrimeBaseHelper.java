package com.litongjava.criminal.intent.criminalintentjava.helper;

import android.database.sqlite.SQLiteDatabase;

import com.litongjava.criminal.intent.criminalintentjava.model.CrimeDbSchema;

public class CrimeBaseHelper {

  public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " + CrimeDbSchema.CrimeTable.NAME + "(" +
      " _id integer primary key autoincrement, " +
      CrimeDbSchema.CrimeTable.Cols.UUID + ", " +
      CrimeDbSchema.CrimeTable.Cols.TITLE + ", " +
      CrimeDbSchema.CrimeTable.Cols.DATE + ", " +
      CrimeDbSchema.CrimeTable.Cols.SOLVED + ", " +
      CrimeDbSchema.CrimeTable.Cols.SUSPECT +
      ")"
    );
  }
}
