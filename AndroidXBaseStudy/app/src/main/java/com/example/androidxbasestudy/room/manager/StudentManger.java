package com.example.androidxbasestudy.room.manager;

import com.example.androidxbasestudy.dao.StudentDao;
import com.example.androidxbasestudy.database.StudentDatabase;
import com.example.androidxbasestudy.entity.Student;
import com.litongjava.android.utils.thread.BackgroundTask;
import com.litongjava.android.utils.thread.ThreadIOUtils;
import com.litongjava.jfinal.aop.Aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StudentManger {
  private StudentDao studentDao = Aop.get(StudentDatabase.class).getDao();

  public void insertStudent(Student[] students) {
    studentDao.insert(students);
  }

  public void deleteStudent(Student student) {
    studentDao.delete(student);
  }

  public void deleteAllStudent() {
    studentDao.deleteAll();
  }

  public void updateStudent(Student student) {
    studentDao.update(student);
  }

  public List<Student> selectAllStudent() {
    return studentDao.selectAll();
  }

  public void insertStudentByIo(Student[] students) {
    ThreadIOUtils.executeByIo(() -> {
      insertStudent(students);
      return null;
    });

  }

  public void deleteStudentByIo(Student student) {
    ThreadIOUtils.executeByIo(() -> {
      deleteStudentByIo(student);
      return null;
    });
  }

  public void deleteAllStudentByIo() {
    ThreadIOUtils.executeByIo(() -> {
      deleteAllStudent();
      return null;
    });
  }

  public void updateStudentByIo(Student student) {
    ThreadIOUtils.executeByIo(() -> {
      updateStudent(student);
      return null;
    });
  }

  public void selectAllStudentByIo() {
    ThreadIOUtils.executeByIo(new BackgroundTask<List<Student>>() {

      @Override
      public List<Student> doInBackground() throws Throwable {
        List<Student> students = selectAllStudent();
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("size:{}",students.size());
        return students;
      }
    });
  }
}
