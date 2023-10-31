//package com.example.androidxbasestudy.room.manager;
//
//import android.content.Context;
//import android.os.AsyncTask;
//
//import com.example.androidxbasestudy.dao.StudentDao;
//import com.example.androidxbasestudy.database.StudentDatabase;
//import com.example.androidxbasestudy.entity.Student;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
//public class DBEngine {
//  private static Logger log = LoggerFactory.getLogger(DBEngine.class);
//  private StudentDao studentDao;
//
//  public DBEngine(Context context) {
//    StudentDatabase studentDatabase = StudentDatabase.getInstance(context);
//    studentDao = studentDatabase.getDao();
//  }
//
//  public void insertStudent(Student... students) {
//    //noinspection deprecation
//    new InsertAsyncTask(studentDao).execute(students);
//  }
//
//
//  public void deleteStudent(Student... students) {
//    //noinspection deprecation
//    new DeleteAsyncTask(studentDao).execute(students);
//  }
//
//  public void deleteAllStudent(){
//    //noinspection deprecation
//    new DeleteAsyncTask(studentDao).execute();
//  }
//
//  public void updateStudent(Student... students) {
//    //noinspection deprecation
//    new UpdateAsyncTask(studentDao).execute(students);
//  }
//
//  public void selectAllStudent() {
//    //noinspection deprecation
//    new SelectAsyncTask(studentDao).execute();
//  }
//
//  @SuppressWarnings("deprecation")
//  static class InsertAsyncTask extends AsyncTask<Student, Void, Void> {
//    private StudentDao dao;
//
//    public InsertAsyncTask(StudentDao dao) {
//      this.dao = dao;
//    }
//
//    @Override
//    protected Void doInBackground(Student... students) {
//      dao.insert(students);
//      return null;
//    }
//  }
//
//  @SuppressWarnings("deprecation")
//  static class DeleteAsyncTask extends AsyncTask<Student, Void, Void> {
//    private StudentDao dao;
//
//    public DeleteAsyncTask(StudentDao dao) {
//      this.dao = dao;
//    }
//
//    @Override
//    protected Void doInBackground(Student... students) {
//      if (students.length>0) {
//        dao.delete(students);
//      } else {
//        dao.deleteAll();
//      }
//      return null;
//    }
//  }
//
//  @SuppressWarnings("deprecation")
//  static class SelectAsyncTask extends AsyncTask<Student, Void, Void> {
//
//    private StudentDao dao;
//
//    public SelectAsyncTask(StudentDao dao) {
//      this.dao = dao;
//    }
//
//    @Override
//    protected Void doInBackground(Student... students) {
//      List<Student> selectAll = dao.selectAll();
//      log.info("size:{}",selectAll.size());
//      for (Student student : selectAll) {
//        log.info(student.toString());
//      }
//
//      return null;
//    }
//  }
//
//  @SuppressWarnings("deprecation")
//  static class UpdateAsyncTask extends AsyncTask<Student, Void, Void> {
//
//    private StudentDao dao;
//
//    public UpdateAsyncTask(StudentDao dao) {
//      this.dao = dao;
//    }
//
//    @Override
//    protected Void doInBackground(Student... students) {
//      dao.update(students);
//      return null;
//    }
//  }
//}
