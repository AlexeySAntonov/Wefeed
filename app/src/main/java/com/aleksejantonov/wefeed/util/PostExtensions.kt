package com.aleksejantonov.wefeed.util

import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment.LinkAttachment
import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment.PhotoAttachment
import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment.VideoAttachment
import com.aleksejantonov.wefeed.model.network.entity.feed.Post
import com.aleksejantonov.wefeed.ui.feed.viewModel.PostVM

fun Post.toVM(): PostVM {
  return PostVM(
      type = when (attachments[0]) {
        is PhotoAttachment -> attachments[0]?.let { (it as PhotoAttachment).type } ?: ""
        is VideoAttachment -> attachments[0]?.let { (it as VideoAttachment).type } ?: ""
        is LinkAttachment  -> attachments[0]?.let { (it as LinkAttachment).type } ?: ""
      },
      text = text,
      date = date.toReadableDate(),
      imageUrls = when (attachments[0]) {
        is PhotoAttachment -> attachments.map { if (it != null) (it as PhotoAttachment).photo.photo_604 else "" }
        is VideoAttachment -> attachments.map { if (it != null) (it as VideoAttachment).video.photo_320 else "" }
        is LinkAttachment  -> attachments.map { if (it != null) (it as LinkAttachment).link.photo.photo_604 else "" }
      },
      linkButtonTitle = if (attachments[0].type == "link") (attachments[0] as LinkAttachment).link.button.title else "",
      linkButtonLink = if (attachments[0].type == "link") (attachments[0] as LinkAttachment).link.button.url else ""
  )
}