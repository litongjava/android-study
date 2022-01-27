package com.example.android_kotin_coroutine.repository

import com.example.android_kotin_coroutine.api.User
import com.example.android_kotin_coroutine.api.userServiceApi

/**
 * 请求数据
 */
class UserRepository {
  suspend fun getUser(name:String): User {
    return userServiceApi.getUser(name)
  }
}