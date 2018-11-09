package com.aleksejantonov.wefeed.util.navigation

import com.aleksejantonov.wefeed.ui.Screens
import com.aleksejantonov.wefeed.ui.main.MainActivity

class AppRouter {
  private lateinit var navigator: MainNavigator

  fun createNavigator(activity: MainActivity) {
    navigator = MainNavigator(activity)
  }

  fun openMain() = navigator.openMain()
  fun replace(screen: Screens) = navigator.replace(screen)
  fun forward(screen: Screens) = navigator.forward(screen)
  fun back() = navigator.back()
}