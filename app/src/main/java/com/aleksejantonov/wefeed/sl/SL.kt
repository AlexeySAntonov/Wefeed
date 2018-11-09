package com.aleksejantonov.wefeed.sl

import android.content.Context

object SL {
  private lateinit var componentManager: ComponentManager

  fun init(context: Context) {
    componentManager = ComponentManager(context)
  }

  fun componentManager() = componentManager
}