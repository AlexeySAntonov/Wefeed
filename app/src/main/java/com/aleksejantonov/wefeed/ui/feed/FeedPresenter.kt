package com.aleksejantonov.wefeed.ui.feed

import android.util.Log
import com.aleksejantonov.wefeed.model.network.entity.feed.ResponseContainer
import com.aleksejantonov.wefeed.sl.SL
import retrofit2.Call
import retrofit2.Response

class FeedPresenter : MvpPresenter {
  private val api by lazy { SL.componentManager().appComponent().api }
  private val preferencesManager by lazy { SL.componentManager().appComponent().preferencesManager }
  private var view: MvpView? = null

  override fun onAttach(view: MvpView) {
    this.view = view
    loadData()
  }

  override fun detach() {
    view = null
  }

  private fun loadData() {
    api.newsFeed(token = preferencesManager.getToken()).enqueue(object : retrofit2.Callback<ResponseContainer> {
      override fun onResponse(call: Call<ResponseContainer>, response: Response<ResponseContainer>) {
        Log.d("", "")
      }

      override fun onFailure(call: Call<ResponseContainer>, t: Throwable) {
        Log.d("Response", "Failed")
      }
    })
  }
}