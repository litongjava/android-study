package com.example.android_kotin_coroutine.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotin_coroutine.R
import com.example.android_kotin_coroutine.api.User
import com.example.android_kotin_coroutine.api.userServiceApi
import kotlinx.coroutines.*


@Suppress("DEPRECATION","StaticFieldLeak")
class MainActivity04 : AppCompatActivity() {

  var textViewName: TextView? =null;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    textViewName = findViewById<TextView>(R.id.textViewName)
    textViewName?.text="ping"

    var btnSumit = findViewById<Button>(R.id.btnSubmit).also {
      it.setOnClickListener{
        Log.d("xxx","监测到单击")
        GlobalScope.launch(Dispatchers.Main){
          //挂起
//          delay(12000)
//          Log.d("xxx","${Thread.currentThread().name}:after delay")
          //测试结果,点击按钮之后立即弹上来,启动了多个协程,没有协程都在12秒后打印
          //阻塞
          Thread.sleep(12000)
          Log.d("xxx","${Thread.currentThread().name}: after sleep")
          //测试结果,点击按钮之后没有立即弹上来,启动了多个协程,没有协程都在12秒后打印
        }
      }
    }
  }
}