package com.aleksejantonov.wefeed

import android.app.Application
import com.aleksejantonov.wefeed.sl.SL
import com.vk.sdk.VKSdk

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    SL.init(this)
    initVK()
  }

  private fun initVK() {
    VKSdk.initialize(this)
  }
}