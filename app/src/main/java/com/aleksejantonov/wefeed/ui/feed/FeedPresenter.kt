package com.aleksejantonov.wefeed.ui.feed

import com.aleksejantonov.wefeed.model.network.entity.dislike.DislikeResponseContainer
import com.aleksejantonov.wefeed.model.network.entity.feed.FeedResponseContainer
import com.aleksejantonov.wefeed.model.network.entity.group.GroupResponseContainer
import com.aleksejantonov.wefeed.model.network.entity.like.LikeResponseContainer
import com.aleksejantonov.wefeed.sl.SL
import com.aleksejantonov.wefeed.ui.feed.viewModel.PostVM
import com.aleksejantonov.wefeed.util.notNullNotEmpty
import com.aleksejantonov.wefeed.util.toVM
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class FeedPresenter : MvpPresenter {
  private val api by lazy { SL.componentManager().appComponent().api }
  private val preferencesManager by lazy { SL.componentManager().appComponent().preferencesManager }
  private var view: MvpView? = null
  private val postVMs = mutableListOf<PostVM>()
  private val bufferVMs = mutableListOf<PostVM>()
  private var nextChunk = "0"

  override fun onAttach(view: MvpView) {
    this.view = view
    loadData()
  }

  override fun detach() {
    view = null
  }

  override fun loadData(initial: Boolean) {
    if (initial) view?.showLoading()
    api.newsFeed(chunk = nextChunk, token = preferencesManager.getToken()).enqueue(object : retrofit2.Callback<FeedResponseContainer> {
      override fun onResponse(call: Call<FeedResponseContainer>, response: Response<FeedResponseContainer>) {
        if (response.isSuccessful) {
          response.body()?.let {
            val posts = it.response.items
            it.response.next_from?.let { nextChunk = it } ?: feelNextChunk()
            if (posts.notNullNotEmpty()) {
              bufferVMs.clear()
              posts.map { post -> post.toVM() }.let {
                postVMs.addAll(it)
                bufferVMs.addAll(it)
              }
              loadAdditionalInfo(posts.map { post -> Math.abs(post.source_id) })
            } else {
              loadData(false)
            }
          }
        }
      }

      override fun onFailure(call: Call<FeedResponseContainer>, t: Throwable) = Timber.e(t)
    })
  }

  private fun loadAdditionalInfo(ids: List<Long>) {
    api.groupsInfo(ids = ids.joinToString(separator = ","), token = preferencesManager.getToken())
        .enqueue(object : retrofit2.Callback<GroupResponseContainer> {
          override fun onResponse(call: Call<GroupResponseContainer>, response: Response<GroupResponseContainer>) {
            if (response.isSuccessful) {
              response.body()?.let {
                val groups = it.response
                if (groups.notNullNotEmpty()) {
                  for (i in 0 until groups.size) {
                    bufferVMs[i] = bufferVMs[i].copy(name = groups[i].name, avatarUrl = groups[i].photo_50)
                  }
                }
                view?.hideLoading()
                view?.showItems(bufferVMs)
              }
            }
          }

          override fun onFailure(call: Call<GroupResponseContainer>, t: Throwable) = Timber.e(t)
        })
  }

  override fun sendLike(position: Int) {
    if (position < postVMs.size) {
      api.addLike(type = "post", ownerId = postVMs[position].ownerId, itemId = postVMs[position].postId, token = preferencesManager.getToken())
          .enqueue(object : retrofit2.Callback<LikeResponseContainer> {
            override fun onResponse(call: Call<LikeResponseContainer>, response: Response<LikeResponseContainer>) {
              if (response.isSuccessful) {
//              response.body()?.let { view?.showLikesCount(it.response.likes) }
              }
            }

            override fun onFailure(call: Call<LikeResponseContainer>, t: Throwable) = Timber.e(t)
          })
    } else {
      giveMeMore()
    }
  }

  override fun dislike(position: Int) {
    if (position < postVMs.size) {
      api.dislike(type = "wall", ownerId = postVMs[position].ownerId, itemId = postVMs[position].postId, token = preferencesManager.getToken())
          .enqueue(object : retrofit2.Callback<DislikeResponseContainer> {
            override fun onResponse(call: Call<DislikeResponseContainer>, response: Response<DislikeResponseContainer>) {
              if (response.isSuccessful) {
//              view?.showMessage(R.string.post_ignore_message)
              }
            }

            override fun onFailure(call: Call<DislikeResponseContainer>, t: Throwable) = Timber.e(t)
          })
    } else {
      giveMeMore()
    }
  }

  override fun giveMeMore() {
    view?.showLoading()
    loadData(false)
  }

  private fun feelNextChunk() {
    val subChunks = nextChunk.split("/")
    val page = subChunks[subChunks.size - 2]
    val major = nextChunk.subSequence(0, nextChunk.length - 1 - page.length).toString()
    nextChunk = major.plus(((page.toInt() + 1)).toString()).plus("/")
  }
}