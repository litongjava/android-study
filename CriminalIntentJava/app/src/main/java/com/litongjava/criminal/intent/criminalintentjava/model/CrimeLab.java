package com.litongjava.criminal.intent.criminalintentjava.model;

import android.content.ContentValues;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
  public static CrimeLab crimeLab;
  private static List<Crime> crimes;
  private Context mContext;

  private CrimeLab(Context context) {
    this.mContext=context;
    crimes = new ArrayList<>();
    //add 100 crime
    for (int i = 0; i < 100; i++) {
      crimes.add(new Crime("Crime#" + i, i % 2 == 0));
    }
  }

  public static CrimeLab get(Context context) {
    if (crimeLab == null) {
      crimeLab = new CrimeLab(context);
    }
    return crimeLab;
  }


  public void addCrime(Crime c){
    crimes.add(c);
  }
  public List<Crime> getCrimes() {
    return crimes;
  }

  public Crime getCrime(UUID id) {
    for (Crime crime : crimes) {
      if (crime.getId().equals(id)) {
        return crime;
      }
    }
    return null;
  }

  public File getPhotoFile(Crime crime) {
    File externalStorageDirectory = Environment.getExternalStorageDirectory();
    //File externalFilesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    if (externalStorageDirectory == null) {
      return null;
    }
    File folder = new File(externalStorageDirectory, "app");
    if(!folder.exists()){
      folder.mkdirs();
    }
    return new File(folder,crime.getPhotoFilename());
  }
  private static ContentValues getContentValues(Crime crime) {
    ContentValues values = new ContentValues();
    values.put(CrimeDbSchema.CrimeTable.Cols.UUID, crime.getId().toString());
    values.put(CrimeDbSchema.CrimeTable.Cols.TITLE, crime.getTitle());
    values.put(CrimeDbSchema.CrimeTable.Cols.DATE, crime.getDate().getTime());
    values.put(CrimeDbSchema.CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
    values.put(CrimeDbSchema.CrimeTable.Cols.SUSPECT, crime.getSuspect());
    return values;
  }
}
