package com.aleksejantonov.wefeed.util.converter

import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment
import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment.LinkAttachment
import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment.PhotoAttachment
import com.aleksejantonov.wefeed.model.network.entity.feed.Attachment.VideoAttachment
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class AttachmentConverter : JsonDeserializer<Attachment> {
  private companion object {
    const val VIDEO = "video"
    const val PHOTO = "photo"
    const val LINK = "link"
  }

  override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Attachment? {
    val jsonElement = (json as JsonObject).get("type")
    if (jsonElement != null) {
      when (jsonElement.asString) {
        VIDEO -> return context.deserialize(json, VideoAttachment::class.java)
        PHOTO -> return context.deserialize(json, PhotoAttachment::class.java)
        LINK  -> return context.deserialize(json, LinkAttachment::class.java)
      }
    }
    return null
  }
}