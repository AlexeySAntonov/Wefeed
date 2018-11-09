package com.aleksejantonov.wefeed.sl

import android.content.Context
import com.aleksejantonov.wefeed.sl.component.AppComponent

class ComponentManager(private val context: Context) {
  private val appComponent: AppComponent by lazy { AppComponent(context) }

  fun appComponent() = appComponent
}