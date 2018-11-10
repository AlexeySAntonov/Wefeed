package com.aleksejantonov.wefeed.ui.feed.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleksejantonov.wefeed.R
import com.aleksejantonov.wefeed.ui.feed.adapter.CardsAdapter.CardViewHolder
import com.aleksejantonov.wefeed.ui.feed.viewModel.PostVM
import kotlinx.android.synthetic.main.item_post.view.text

class CardsAdapter : RecyclerView.Adapter<CardViewHolder>() {

  private var items: MutableList<PostVM> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
      CardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
    holder.bind(items[position])
  }

  fun updateItems(items: List<PostVM>) {
    with(this.items) {
      clear()
      addAll(items)
      notifyDataSetChanged()
    }
  }

  inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(post: PostVM) {
      itemView.apply {
        text.text = post.mock
      }
    }
  }
}