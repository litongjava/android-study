package com.example.android_kotin_coroutine.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotin_coroutine.R
import com.example.android_kotin_coroutine.api.userServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Suppress("DEPRECATION", "StaticFieldLeak")
class MainActivity02 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    var textViewName = findViewById<TextView>(R.id.textViewName)
    textViewName.text = "ping"

    var btnSumit = findViewById<Button>(R.id.btnSubmit)
    btnSumit.setOnClickListener {
      Log.d("activity", "监测到单击")

      GlobalScope.launch(Dispatchers.Main) {
        val user = withContext(Dispatchers.IO) {
          userServiceApi.getUser("coroutine")
        }
        textViewName.text = "address ${user?.address}"
      }
    }
  }
}