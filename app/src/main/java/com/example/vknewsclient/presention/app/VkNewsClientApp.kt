package com.example.vknewsclient.presention.app

import android.app.Application
import com.example.vknewsclient.di.DaggerAppComponent
import com.vk.id.VKID

class VkNewsClientApp : Application() {

    val component = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()
        VKID.init(this)
    }
}