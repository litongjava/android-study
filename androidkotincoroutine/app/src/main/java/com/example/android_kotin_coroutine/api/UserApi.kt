package com.example.android_kotin_coroutine.api

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class User(val name:String,val address:String)

val userServiceApi:UserServiceApi by lazy {
  //val baseUrl="http://127.0.0.1:10060/json/"
  //don't ue 127.0.0.1
  val baseUrl="http://192.168.0.12/json/"
  val retrofit=retrofit2.Retrofit.Builder().
    client(OkHttpClient.Builder().addInterceptor{
      it.proceed(it.request()).apply {
        Log.d("xxx","request:${code()}")
        Log.d("xxx","body ${body()?.toString()}")
      }
    }.build())
    .baseUrl(baseUrl)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
  retrofit.create(UserServiceApi::class.java)
}

interface UserServiceApi {
  //http://xxx/json/loadUser?name=ping
  @GET("loadUser")
  fun loadUser(@Query("name") name:String): Call<User>

  @GET("loadUser")
  suspend fun getUser(@Query("name") name:String): User
}