package com.aleksejantonov.wefeed.ui.feed

interface MvpView {
  fun showItems()
}

interface MvpPresenter {
  fun onAttach(view: MvpView)
  fun detach()
}