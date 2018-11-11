package com.aleksejantonov.wefeed.ui.feed

import com.aleksejantonov.wefeed.ui.feed.viewModel.PostVM

interface MvpView {
  fun showItems(items: List<PostVM>)
  fun showLoading()
  fun hideLoading()
  fun showLikesCount(count: Long)
}

interface MvpPresenter {
  fun onAttach(view: MvpView)
  fun detach()
  fun sendLike(position: Int)
}