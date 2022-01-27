package com.example.android_kotin_coroutine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_kotin_coroutine.api.User
import com.example.android_kotin_coroutine.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
  val userLiveData= MutableLiveData<User>()

  private val userRepository = UserRepository()

  fun getUser(name:String){
    viewModelScope.launch {
      userLiveData.value=userRepository.getUser(name)
    }
  }
}
