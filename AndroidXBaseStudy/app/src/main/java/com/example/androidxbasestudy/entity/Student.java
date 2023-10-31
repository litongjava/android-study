package com.example.androidxbasestudy.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 添加@Entity注解之后只能拥有一个构造方法
 */
@Entity
public class Student {
  @PrimaryKey(autoGenerate = true)
  private int _id;
  private String name;
  private int age;

  public Student(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public void set_id(int _id) {
    this._id = _id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int get_id() {
    return _id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "Student{" +
      "_id=" + _id +
      ", name='" + name + '\'' +
      ", age=" + age +
      '}';
  }
}
