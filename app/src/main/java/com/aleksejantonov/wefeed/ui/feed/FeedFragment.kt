package com.aleksejantonov.wefeed.ui.feed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleksejantonov.wefeed.R
import com.aleksejantonov.wefeed.sl.SL
import com.aleksejantonov.wefeed.ui.feed.adapter.CardsAdapter
import com.aleksejantonov.wefeed.ui.feed.viewModel.PostVM
import com.aleksejantonov.wefeed.util.visible
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import kotlinx.android.synthetic.main.fragment_feed.progressOverlay
import kotlinx.android.synthetic.main.fragment_feed.recycler

class FeedFragment : Fragment(), MvpView {
  companion object {
    fun newInstance() = FeedFragment()
  }

  private val presenter by lazy { SL.componentManager().feedComponent().presenter }
  private val adapter by lazy { CardsAdapter() }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_feed, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecycler()
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

  override fun showItems(items: List<PostVM>) {
    adapter.updateItems(items)
  }

  override fun showLoading() {
    progressOverlay.visible = true
  }

  override fun hideLoading() {
    progressOverlay.visible = false
  }

  private fun setupRecycler() {
    with(recycler) {
      layoutManager = CardStackLayoutManager(context)
      adapter = this@FeedFragment.adapter
    }
  }
}