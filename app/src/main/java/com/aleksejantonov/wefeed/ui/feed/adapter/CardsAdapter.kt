package com.aleksejantonov.wefeed.ui.feed.adapter

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleksejantonov.wefeed.R
import com.aleksejantonov.wefeed.ui.feed.adapter.CardsAdapter.CardViewHolder
import com.aleksejantonov.wefeed.ui.feed.viewModel.PostVM
import com.aleksejantonov.wefeed.util.visible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_post.view.dateView
import kotlinx.android.synthetic.main.item_post.view.linkButton
import kotlinx.android.synthetic.main.item_post.view.mainImage
import kotlinx.android.synthetic.main.item_post.view.mainText
import kotlinx.android.synthetic.main.item_post.view.postUserAvatar
import kotlinx.android.synthetic.main.item_post.view.tabLayout
import kotlinx.android.synthetic.main.item_post.view.userName
import kotlinx.android.synthetic.main.item_post.view.viewPager

class CardsAdapter(
    private val linkButtonListener: (String) -> Unit
) : RecyclerView.Adapter<CardViewHolder>() {

  private var items: MutableList<PostVM> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
      CardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
    holder.bind(items[position])
  }

  fun updateItems(items: List<PostVM>) {
    with(this.items) {
      addAll(items)
      notifyDataSetChanged()
    }
  }

  inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(post: PostVM) {
      itemView.apply {
        userName.text = post.name
        dateView.text = post.date
        mainText.text = post.text
        linkButton.apply {
          visible = post.type == "link"
          text = post.linkButtonTitle
          setOnClickListener { linkButtonListener.invoke(post.linkButtonLink ?: "") }
        }
        val imgRequestManager = Glide.with(context)
        imgRequestManager
            .load(post.avatarUrl)
            .apply(
                RequestOptions
                    .circleCropTransform()
            )
            .into(postUserAvatar)
        if (post.imageUrls.isNotEmpty() && post.imageUrls[0].isNotBlank()) {
          imgRequestManager
              .load(post.imageUrls[0])
              .into(mainImage)
        }
        if (post.imageUrls.size > 1) {
          setupTabLayout(tabLayout, viewPager, post.imageUrls)
        }
      }
    }

    private fun setupTabLayout(tabLayout: TabLayout, viewPager: ViewPager, imageUrls: List<String>) {
      for (url in imageUrls) {
        tabLayout.addTab(tabLayout.newTab())
      }
      tabLayout.setupWithViewPager(viewPager)
    }
  }
}