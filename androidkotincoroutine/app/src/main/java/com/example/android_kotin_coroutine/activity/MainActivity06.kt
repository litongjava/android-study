package com.example.android_kotin_coroutine.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotin_coroutine.R
import com.example.android_kotin_coroutine.api.userServiceApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


@Suppress("DEPRECATION", "StaticFieldLeak")
class MainActivity06 : AppCompatActivity(),CoroutineScope by MainScope() {
  private var textViewName:TextView? = null;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    textViewName = findViewById<TextView>(R.id.textViewName)
    textViewName?.text="Ping"

    var btnSubmit = findViewById<Button>(R.id.btnSubmit)
    btnSubmit.setOnClickListener{
      Log.d("xxx","click")
      launch {
        var user = userServiceApi.getUser("ping")
        textViewName?.text="address ${user.address}"
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    //和Activity生命周期相关联,取消所有协程
    cancel()
  }
}


