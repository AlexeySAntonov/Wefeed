package com.aleksejantonov.wefeed.util

import com.aleksejantonov.wefeed.model.network.entity.feed.Post
import com.aleksejantonov.wefeed.ui.feed.viewModel.PostVM

fun Post.toVM(): PostVM {
  return PostVM(text = text)
}