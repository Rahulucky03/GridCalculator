package com.testapp

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.testapp.binding.BindingComponent

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(BindingComponent())
    }

}