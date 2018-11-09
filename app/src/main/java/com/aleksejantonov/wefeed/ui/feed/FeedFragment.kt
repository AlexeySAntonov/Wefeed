package com.aleksejantonov.wefeed.ui.feed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleksejantonov.wefeed.R
import com.aleksejantonov.wefeed.sl.SL
import kotlinx.android.synthetic.main.fragment_feed.mock

class FeedFragment : Fragment(), MvpView {
  companion object {
    fun newInstance() = FeedFragment()
  }

  private val presenter by lazy { SL.componentManager().feedComponent().presenter }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_feed, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.onAttach(this)
  }

  override fun onDestroyView() {
    presenter.detach()
    super.onDestroyView()
  }

  override fun onDestroy() {
    super.onDestroy()
    activity?.let { if (it.isFinishing) SL.componentManager().releaseFeedComponent() }
  }

  override fun showItems() {
    mock.visibility = View.VISIBLE
  }
}