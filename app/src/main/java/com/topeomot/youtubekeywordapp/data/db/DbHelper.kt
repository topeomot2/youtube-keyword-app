package com.topeomot.youtubekeywordapp.data.db

import com.topeomot.youtubekeywordapp.data.db.entity.Cache
import com.topeomot.youtubekeywordapp.model.YoutubeResponse
import io.reactivex.Maybe

interface DbHelper{
    fun isValid(cache: Cache): Boolean
    fun getSearchResult(searchTerm: String, pageToken: String = ""): Maybe<Cache>
    fun addSearchResult(searchTerm: String, pageToken: String = "", youtubeResponse: YoutubeResponse): Cache
}