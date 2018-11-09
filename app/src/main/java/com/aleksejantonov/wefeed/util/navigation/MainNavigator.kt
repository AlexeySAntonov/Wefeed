package com.aleksejantonov.wefeed.util.navigation

import android.support.v4.app.Fragment
import com.aleksejantonov.wefeed.R
import com.aleksejantonov.wefeed.ui.Screens
import com.aleksejantonov.wefeed.ui.Screens.AUTH_SCREEN
import com.aleksejantonov.wefeed.ui.Screens.FEED_SCREEN
import com.aleksejantonov.wefeed.ui.auth.AuthFragment
import com.aleksejantonov.wefeed.ui.feed.FeedFragment
import com.aleksejantonov.wefeed.ui.main.MainActivity
import com.aleksejantonov.wefeed.util.navigation.MainNavigator.Commands.FORWARD
import com.aleksejantonov.wefeed.util.navigation.MainNavigator.Commands.REPLACE

class MainNavigator(activity: MainActivity) {
  private val fragmentManager by lazy { activity.supportFragmentManager }

  fun openMain() {
    replace(FEED_SCREEN)
  }

  fun replace(screen: Screens) {
    applyCommand(screen, REPLACE)
  }

  fun forward(screen: Screens) {
    applyCommand(screen, FORWARD)
  }

  fun back() {
    fragmentManager.popBackStackImmediate()
  }

  private fun applyCommand(screen: Screens, command: Commands, animate: Boolean = true) {
    fragmentManager
        .beginTransaction()
        .apply { if (animate) setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right) }
        .replace(R.id.fragmentContainer, getFragment(screen))
        .apply { if (command == FORWARD) addToBackStack(null) }
        .commitAllowingStateLoss()
  }

  private fun getFragment(screen: Screens): Fragment {
    return when (screen) {
      AUTH_SCREEN -> AuthFragment.newInstance()
      FEED_SCREEN -> FeedFragment.newInstance()
    }
  }

  enum class Commands {
    FORWARD,
    BACK,
    REPLACE
  }
}