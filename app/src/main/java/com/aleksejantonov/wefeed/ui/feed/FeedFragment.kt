package com.aleksejantonov.wefeed.ui.feed

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import com.aleksejantonov.wefeed.R
import com.aleksejantonov.wefeed.sl.SL
import com.aleksejantonov.wefeed.ui.feed.adapter.CardsAdapter
import com.aleksejantonov.wefeed.ui.feed.viewModel.PostVM
import com.aleksejantonov.wefeed.util.mainActivity
import com.aleksejantonov.wefeed.util.toast
import com.aleksejantonov.wefeed.util.visible
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Direction.Left
import com.yuyakaido.android.cardstackview.Direction.Right
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import kotlinx.android.synthetic.main.fragment_feed.likeOverlay
import kotlinx.android.synthetic.main.fragment_feed.progressOverlay
import kotlinx.android.synthetic.main.fragment_feed.recycler
import kotlinx.android.synthetic.main.fragment_feed.skipOverlay

class FeedFragment : Fragment(), MvpView {
  companion object {
    fun newInstance() = FeedFragment()
  }

  private val presenter by lazy { SL.componentManager().feedComponent().presenter }
  private val adapter by lazy { CardsAdapter(::onLinkButtonClick) }
  private lateinit var layoutManager: CardStackLayoutManager

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_feed, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecycler()
    presenter.onAttach(this)
    skipOverlay.setOnClickListener { onSkipClick() }
    likeOverlay.setOnClickListener { onLikeClick() }
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

  override fun showLikesCount(count: Long) {
    mainActivity?.toast(getString(R.string.likes_toast, count))
  }

  override fun showMessage(@StringRes textRes: Int) {
    mainActivity?.toast(textRes)
  }

  private fun setupRecycler() {
    with(recycler) {
      this@FeedFragment.layoutManager = CardStackLayoutManager(context)
      layoutManager = this@FeedFragment.layoutManager
      adapter = this@FeedFragment.adapter
    }
  }

  private fun onSkipClick() {
    layoutManager.setSwipeAnimationSetting(animationSettings(Left))
    presenter.dislike(layoutManager.topPosition)
    recycler.swipe()
  }

  private fun onLikeClick() {
    layoutManager.setSwipeAnimationSetting(animationSettings(Right))
    presenter.sendLike(layoutManager.topPosition)
    recycler.swipe()
  }

  private fun animationSettings(direction: Direction): SwipeAnimationSetting {
    return SwipeAnimationSetting.Builder()
        .setDirection(direction)
        .setDuration(300)
        .setInterpolator(AccelerateInterpolator())
        .build()
  }

  private fun onLinkButtonClick(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
  }
}