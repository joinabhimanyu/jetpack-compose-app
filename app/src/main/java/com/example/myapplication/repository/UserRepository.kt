package com.example.myapplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.UserModel
import com.example.myapplication.service.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val userApi: UserApi) {
    fun getUsers(
        users: MutableLiveData<List<UserModel>>,
        isLoading: MutableLiveData<Boolean>,
        isError: MutableLiveData<Boolean>,
        error: MutableLiveData<String>
    ) {
        val call: Call<List<UserModel>?>? = userApi.getUsers()

        isLoading.value = true
        call!!.enqueue(object : Callback<List<UserModel>?> {
            override fun onResponse(
                call: Call<List<UserModel>?>,
                response: Response<List<UserModel>?>
            ) {
                if (response.isSuccessful) {
                    Log.d("Main", "success!" + response.body().toString())
                    isLoading.value = false
                    isError.value = false
                    error.value = null
                    users.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<UserModel>?>, t: Throwable) {
                Log.e("Main", "Failed mate " + t.message.toString())
                isLoading.value = false
                isError.value = true
                error.value = t.message.toString()
                users.value = null
            }
        })
    }
}