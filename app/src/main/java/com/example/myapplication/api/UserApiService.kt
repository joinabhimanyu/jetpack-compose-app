package com.example.myapplication.api

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
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
}

fun getUsers(
    users: MutableLiveData<List<UserModel>>,
    isLoading: MutableLiveData<Boolean>,
    isError: MutableLiveData<Boolean>,
    error: MutableLiveData<String>
) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(UserApi::class.java)

    val call: Call<List<UserModel>?>? = api.getUsers();

    isLoading.value = true;
    call!!.enqueue(object : Callback<List<UserModel>?> {
        override fun onResponse(
            call: Call<List<UserModel>?>,
            response: Response<List<UserModel>?>
        ) {
            if (response.isSuccessful) {
                Log.d("Main", "success!" + response.body().toString())
                isLoading.value = false;
                isError.value = false;
                error.value = null;
                users.value = response.body();
            }
        }

        override fun onFailure(call: Call<List<UserModel>?>, t: Throwable) {
            Log.e("Main", "Failed mate " + t.message.toString())
            isLoading.value = false;
            isError.value = true;
            error.value = t.message.toString();
            users.value = null;
        }
    })
}