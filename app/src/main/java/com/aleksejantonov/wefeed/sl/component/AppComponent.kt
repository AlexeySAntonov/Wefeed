package com.aleksejantonov.wefeed.sl.component

import android.content.Context
import com.aleksejantonov.wefeed.model.preferences.PreferencesManager
import com.aleksejantonov.wefeed.util.navigation.AppRouter

class AppComponent(private val context: Context) {
  val appRouter by lazy { AppRouter() }
  val preferencesManager by lazy { PreferencesManager(context) }
}