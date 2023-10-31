package com.example.androidxbasestudy.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidxbasestudy.R;
import com.example.androidxbasestudy.entity.Student;
import com.example.androidxbasestudy.room.manager.StudentManger;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.jfinal.aop.Aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@FindViewByIdLayout(R.layout.activity_room)
public class RoomActivity extends AppCompatActivity {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_room);
    ViewInjectUtils.injectActivity(this, this);
  }

  @OnClick(R.id.btnRoomInsert)
  public void btnRoomInsertOnClick(View view) {
    log.info("插入数据");

    List<Student> studentList = new ArrayList<>();
    studentList.add(new Student("Ping", 18));
    studentList.add(new Student("Tom", 19));
    studentList.add(new Student("Jery", 20));

    Student[] students = studentList.toArray(new Student[0]);
    //dbEngine.insertStudent(students);
    Aop.get(StudentManger.class).insertStudentByIo(students);

  }

  @OnClick(R.id.btnRoomDelete)
  public void btnRoomDeleteOnClick(View view) {
    log.info("删除数据");
    Student student = new Student(null,0);
    student.set_id(3);
    Aop.get(StudentManger.class).deleteStudentByIo(student);
  }

  @OnClick(R.id.btnRoomDeleteAll)
  public void btnRoomDeleteAllOnClick(View view) {
    log.info("删除所有数据");
    Aop.get(StudentManger.class).deleteAllStudentByIo();

  }

  @OnClick(R.id.btnRoomUpdate)
  public void btnRoomUpdateAllOnClick(View view) {
    log.info("更新数据");
    Student student = new Student("王五",33);
    student.set_id(9);
    Aop.get(StudentManger.class).updateStudentByIo(student);
  }

  @OnClick(R.id.btnRoomSelect)
  public void btnRoomSelectOnClick(View view) {
    log.info("查询数据");
    Aop.get(StudentManger.class).selectAllStudentByIo();
  }
}