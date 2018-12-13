package com.topeomot.youtubekeywordapp.data.network

import com.topeomot.youtubekeywordapp.model.YoutubeResponse
import com.topeomot.youtubekeywordapp.data.network.retrointerface.Api
import com.topeomot.youtubekeywordapp.di.ApplicationScope
import com.topeomot.youtubekeywordapp.util.YoutubeKeywordConstant
import io.reactivex.Flowable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@ApplicationScope
class YoutubeApiHelper @Inject constructor(val retrofit: Retrofit) : ApiHelper {

    private val apiInterface: Api = retrofit.create(Api::class.java)

    override fun getSearchResult(searchTerm: String, pageToken: String?): Flowable<YoutubeResponse> =
        apiInterface.getSearchResult(searchTerm = searchTerm,
            token = pageToken,
            numPage = YoutubeKeywordConstant.NUM_PAGE,
            apiKey = YoutubeKeywordConstant.YOUTUBE_KEY
            )

}