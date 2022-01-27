package com.example.android_kotin_coroutine.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotin_coroutine.R
import com.example.android_kotin_coroutine.api.User
import com.example.android_kotin_coroutine.api.userServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Suppress("DEPRECATION","StaticFieldLeak")
class MainActivity03 : AppCompatActivity() {

  var textViewName: TextView? =null;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    textViewName = findViewById<TextView>(R.id.textViewName)
    textViewName?.text="ping"

    var btnSumit = findViewById<Button>(R.id.btnSubmit).also {
      it.setOnClickListener{
        Log.d("activity","监测到单击")
        GlobalScope.launch(Dispatchers.Main){
          getUser()
        }


      }
    }
  }
  private suspend fun getUser(){
    var user=get()
    show(user)
  }

  //如果要调用挂起函数,外部函数也要声明成挂起函数
  private suspend fun get() =withContext(Dispatchers.IO){
    userServiceApi.getUser("coroutine")
  }
  private fun show(user: User) {
    textViewName?.text = "address ${user?.address}"
  }
}