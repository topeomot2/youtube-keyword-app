package com.topeomot.youtubekeywordapp.data.db.entity

import android.arch.persistence.room.Entity
import com.topeomot.youtubekeywordapp.util.YoutubeKeywordConstant
import org.joda.time.DateTime

@Entity(primaryKeys= ["searchTerm", "size", "token"])
data class Cache (
    var searchTerm: String = "",
    var size: Int = YoutubeKeywordConstant.NUM_PAGE,
    var token: String = "",
    var type: String = "all",
    var result: String = "",
    var updateTime: DateTime = DateTime()
)