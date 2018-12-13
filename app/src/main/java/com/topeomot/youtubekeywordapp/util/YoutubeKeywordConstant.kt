package com.topeomot.youtubekeywordapp.util

class YoutubeKeywordConstant {
    companion object {
        @JvmStatic lateinit var instance: YoutubeKeywordConstant
        const val SHARED_PREFERENCE_NAME = "youTvdegmsdmv2443"
        const val SUBJECT_MATTER = "subjectMatter"
        const val SEARCH_URL = "https://www.googleapis.com/youtube/v3/search/"
        const val NUM_PAGE = 25
        const val YOUTUBE_KEY = "AIzaSyAzDCp4mTtTyaa5WAyITRLLnb3wrdQNwEA"
        const val LANDSCAPE = "landscape"
        const val PORTRAIT = "portrait"
    }

    init {
        instance = this
    }
}