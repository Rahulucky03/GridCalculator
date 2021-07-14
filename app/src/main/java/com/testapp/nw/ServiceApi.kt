package com.testapp.nw

import com.testapp.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ServiceApi {
    //https://reqres.in/api/users?page=1

    @GET("users")
    fun getsuperHeroes(@Query("page") pageNo: Int): Call<UserResponse?>?
}