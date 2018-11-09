package com.aleksejantonov.wefeed

import android.app.Application
import com.vk.sdk.VKSdk

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    initVK()
  }

  fun initVK() {
    VKSdk.initialize(this)
  }
}