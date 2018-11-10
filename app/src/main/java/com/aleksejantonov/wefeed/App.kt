package com.aleksejantonov.wefeed

import android.app.Application
import com.aleksejantonov.wefeed.sl.SL
import com.facebook.stetho.Stetho
import com.vk.sdk.VKSdk
import timber.log.Timber

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    SL.init(this)
    initVK()
    initStetho()
    initTimber()
  }

  private fun initVK() {
    VKSdk.initialize(this)
  }

  private fun initStetho() {
    if (BuildConfig.DEBUG) {
      Stetho.initializeWithDefaults(this)
    }
  }

  private fun initTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}