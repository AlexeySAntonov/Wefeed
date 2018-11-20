package com.aleksejantonov.wefeed.util

import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment.AudioAttachment
import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment.LinkAttachment
import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment.PhotoAttachment
import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment.VideoAttachment
import com.aleksejantonov.wefeed.model.network.entity.feed.Post
import com.aleksejantonov.wefeed.ui.feed.viewModel.PostVM

fun Post.toVM(): PostVM {
  return PostVM(
      ownerId = source_id,
      postId = post_id,
      type = if (attachments.notNullNotEmpty()) {
        when (attachments[0]) {
          is PhotoAttachment -> attachments[0]?.let { (it as PhotoAttachment).type } ?: ""
          is VideoAttachment -> attachments[0]?.let { (it as VideoAttachment).type } ?: ""
          is LinkAttachment  -> attachments[0]?.let { (it as LinkAttachment).type } ?: ""
          is AudioAttachment -> attachments[0]?.let { (it as AudioAttachment).type } ?: ""
          else               -> ""
        }
      } else "",
      text = text,
      date = date.toReadableDate(),
      imageUrls = if (attachments.notNullNotEmpty()) {
        attachments.map {
          when (it) {
            is PhotoAttachment -> it.photo.photo_604
            is VideoAttachment -> it.video.photo_320
            is LinkAttachment  -> it.link.photo?.photo_604 ?: ""
            else               -> ""
          }
        }
      } else emptyList(),
      linkButtonTitle = if (attachments.notNullNotEmpty() && attachments[0] != null && attachments[0].type == "link") {
        (attachments[0] as LinkAttachment).link.button?.title ?: "читать"
      } else "",
      linkButtonLink = if (attachments.notNullNotEmpty() && attachments[0] != null && attachments[0].type == "link") {
        (attachments[0] as LinkAttachment).link.url
      } else ""
  )
}