package com.aleksejantonov.wefeed.sl.component

import com.aleksejantonov.wefeed.ui.feed.FeedPresenter
import com.aleksejantonov.wefeed.ui.feed.MvpPresenter

class FeedComponent {
  val presenter: MvpPresenter by lazy { FeedPresenter() }
}