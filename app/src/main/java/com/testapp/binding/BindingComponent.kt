package com.testapp.binding

import androidx.databinding.DataBindingComponent

class BindingComponent : DataBindingComponent {
    override fun getBindingAdapters(): BindingAdapters {
        return BindingAdapters()
    }
}