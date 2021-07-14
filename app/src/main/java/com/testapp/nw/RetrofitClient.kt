package com.testapp.nw

import com.testapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var myApi: ServiceApi? = null

    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        myApi = retrofit.create(ServiceApi::class.java)
    }

    fun getMyApi(): ServiceApi? {
        return myApi
    }


}