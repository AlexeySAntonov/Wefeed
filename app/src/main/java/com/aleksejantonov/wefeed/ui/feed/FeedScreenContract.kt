package com.aleksejantonov.wefeed.ui.feed

import com.aleksejantonov.wefeed.ui.feed.viewModel.PostVM

interface MvpView {
  fun showItems(items: List<PostVM>)
  fun showLoading()
  fun hideLoading()
}

interface MvpPresenter {
  fun onAttach(view: MvpView)
  fun detach()
}