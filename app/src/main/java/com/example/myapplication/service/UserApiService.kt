package com.example.myapplication.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

public interface UserApi {
    @Headers(
        "Accept: application/json"
    )
    @GET("photos")
    abstract fun getUsers(): Call<List<UserModel>?>?

    @GET("photos/{id}")
    abstract fun getUserById(@Path("id") id: String): Call<UserModel?>?
    companion object {
        fun getInstance(): UserApi {
            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserApi::class.java)
        }
    }
}

