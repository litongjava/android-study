package com.example.android_kotin_coroutine.activity

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotin_coroutine.R
import com.example.android_kotin_coroutine.api.User
import com.example.android_kotin_coroutine.api.userServiceApi


@Suppress("DEPRECATION", "StaticFieldLeak")
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    var textViewName = findViewById<TextView>(R.id.textViewName)
    textViewName.text = "ping"

    var btnSumit = findViewById<Button>(R.id.btnSubmit)
    btnSumit.setOnClickListener {
      Log.d("activity", "检测到单击")
      //创建一个异步因任务并执行
      object : AsyncTask<Void, Void, User>() {
        //在子线程中发起网络请求
        override fun doInBackground(vararg params: Void?): User? {
          Log.d("async", "request server")
          return userServiceApi.loadUser("ping").execute().body()
        }
        //请求成功,回调主线程,更新UI
        override fun onPostExecute(result: User?) {
          Log.d("xxx", result.toString())
          textViewName.text = "address ${result?.address}"
        }
      }.execute()
    }
  }
}