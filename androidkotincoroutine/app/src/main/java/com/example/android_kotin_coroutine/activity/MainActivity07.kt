package com.example.android_kotin_coroutine.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android_kotin_coroutine.R
import com.example.android_kotin_coroutine.databinding.ActivityMainBinding
import com.example.android_kotin_coroutine.viewmodel.MainViewModel


@Suppress("DEPRECATION", "StaticFieldLeak")
class MainActivity07 : AppCompatActivity() {
  private val mainViewModel:MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //setContentView(R.layout.activity_main)
    var binding =
      DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    binding.viewModel=mainViewModel
    binding.lifecycleOwner=this

    binding.btnSubmit.setOnClickListener{
      Log.d("activity", "监测到单击")
      mainViewModel.getUser("ping")
    }
  }
}
