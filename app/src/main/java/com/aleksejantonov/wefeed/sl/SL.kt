package com.aleksejantonov.wefeed.sl

import android.annotation.SuppressLint
import android.content.Context

object SL {
  // No leak, app context
  @SuppressLint("StaticFieldLeak")
  private lateinit var componentManager: ComponentManager

  fun init(context: Context) {
    componentManager = ComponentManager(context)
  }

  fun componentManager() = componentManager
}