package com.example.androidxbasestudy.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidxbasestudy.entity.Student;

import java.util.List;

@Dao
public interface StudentDao {

  @Insert
  void insert(Student... students);

  @Query("delete from student")
  void deleteAll();

  @Delete
  void delete(Student... students);

  @Update
  void update(Student... students);

  @Query("select * from student order by _id desc")
  List<Student> selectAll();
}
