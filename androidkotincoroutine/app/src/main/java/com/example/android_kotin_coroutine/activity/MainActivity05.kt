package com.example.android_kotin_coroutine.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotin_coroutine.R
import kotlin.coroutines.*


@Suppress("DEPRECATION", "StaticFieldLeak")
class MainActivity05 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    //协程体
    var continuation = suspend {
      5
    }.createCoroutine(object : Continuation<Int> {
      //空的协程上下文
      override val context: CoroutineContext = EmptyCoroutineContext

      //resumeWith是一个回调函数,实际的深层的代码背后还是有回调函数的
      override fun resumeWith(result: Result<Int>) {
        Log.d("xxx", "Coroutine End $result")
      }

    })
    //启动协程
    Log.d("xxx","启动协程")
    //continuation保存有协程的挂起点,continuation中有一个协程上下文
    continuation.resume(Unit)

  }
}


