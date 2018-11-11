package com.aleksejantonov.wefeed.util

import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment.LinkAttachment
import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment.PhotoAttachment
import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment.VideoAttachment
import com.aleksejantonov.wefeed.model.network.entity.feed.Post
import com.aleksejantonov.wefeed.ui.feed.viewModel.PostVM

fun Post.toVM(): PostVM {
  return PostVM(
      ownerId = source_id,
      postId = post_id,
      type = when (attachments[0]) {
        is PhotoAttachment -> attachments[0]?.let { (it as PhotoAttachment).type } ?: ""
        is VideoAttachment -> attachments[0]?.let { (it as VideoAttachment).type } ?: ""
        is LinkAttachment  -> attachments[0]?.let { (it as LinkAttachment).type } ?: ""
      },
      text = text,
      date = date.toReadableDate(),
      imageUrls =
      attachments.map {
        when (it) {
          is PhotoAttachment -> it.photo.photo_604
          is VideoAttachment -> it.video.photo_320
          is LinkAttachment  -> it.link.photo.photo_604
        }
      },
      linkButtonTitle = if (attachments[0].type == "link") (attachments[0] as LinkAttachment).link.button?.let { it.title } ?: "Читать" else "",
      linkButtonLink = if (attachments[0].type == "link") (attachments[0] as LinkAttachment).link.button?.let { it.url } ?: "" else ""
  )
}