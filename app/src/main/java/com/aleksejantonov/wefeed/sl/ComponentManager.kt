package com.aleksejantonov.wefeed.sl

import android.content.Context
import com.aleksejantonov.wefeed.sl.component.AppComponent
import com.aleksejantonov.wefeed.sl.component.FeedComponent

class ComponentManager(private val context: Context) {
  private val appComponent: AppComponent by lazy { AppComponent(context) }
  private var feedComponent: FeedComponent? = null

  fun appComponent() = appComponent
  fun feedComponent(): FeedComponent {
    if (feedComponent == null) feedComponent = FeedComponent()
    return requireNotNull(feedComponent)
  }

  fun releaseFeedComponent() {
    feedComponent = null
  }
}