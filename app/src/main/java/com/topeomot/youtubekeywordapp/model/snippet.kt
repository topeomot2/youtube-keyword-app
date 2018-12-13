package com.topeomot.youtubekeywordapp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

data class Snippet (
    var kind: String,
    var etag: String,
    var id: Id,
    var snippet: Detail
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Id(
    var kind: String?,
    var videoId: String?,
    var channelId: String?,
    var playlistId: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Detail(
    var publishedAt: String = "",
    var channelId: String = "",
    var title: String = "",
    var description: String = "",
    var thumbnails: Thumbnails = Thumbnails(),
    var channelTitle: String = "",
    var liveBroadcastContent: String = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Thumbnails(
    var default: Pix = Pix(),
    var standard: Pix = Pix(),
    var medium: Pix = Pix(),
    var high: Pix = Pix(),
    var maxres: Pix = Pix()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Pix(
    var url: String = "",
    var width: Int = 0,
    var height: Int = 0
)