package com.aleksejantonov.wefeed.ui.feed

import android.support.annotation.StringRes
import com.aleksejantonov.wefeed.ui.feed.viewModel.PostVM

interface MvpView {
  fun showItems(items: List<PostVM>)
  fun showLoading()
  fun hideLoading()
  fun showLikesCount(count: Long)
  fun showMessage(@StringRes textRes: Int)
}

interface MvpPresenter {
  fun onAttach(view: MvpView)
  fun detach()
  fun loadData(initial: Boolean = true)
  fun sendLike(position: Int)
  fun dislike(position: Int)
  fun giveMeMore()
}