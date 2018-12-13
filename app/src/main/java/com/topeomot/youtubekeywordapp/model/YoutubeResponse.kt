package com.topeomot.youtubekeywordapp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
data class YoutubeResponse (
    var kind: String = "",
    var etag: String = "",
    var nextPageToken: String  = "",
    var prevPageToken: String  = "",
    var regionCode: String  = "",
    var pageInfo: PageInfo = PageInfo(),
    var items: ArrayList<Snippet> = ArrayList()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class PageInfo (var totalResults: String  = "", var resultsPerPage: String  = "")