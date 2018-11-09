package com.aleksejantonov.wefeed.ui.feed

class FeedPresenter : MvpPresenter {
  private var view: MvpView? = null
  override fun onAttach(view: MvpView) {
    this.view = view
    view.showItems()
  }

  override fun detach() {
    view = null
  }
}