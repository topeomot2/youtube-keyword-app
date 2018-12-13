package com.topeomot.youtubekeywordapp.ui.player

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.topeomot.youtubekeywordapp.R
import com.topeomot.youtubekeywordapp.util.YoutubeKeywordConstant

class PlayerActivity : YouTubeFailureRecoveryActivity() {

    var videoId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_activity)

        val youTubePlayerFragment: YouTubePlayerFragment = fragmentManager
            .findFragmentById(R.id.youtube_fragment) as YouTubePlayerFragment
        youTubePlayerFragment.initialize(YoutubeKeywordConstant.YOUTUBE_KEY, this)

        videoId = intent.getStringExtra("videoId")

    }


    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        if (!p2) {
            if(videoId != null) {
                p1?.cueVideo(videoId)
            }
        }
    }

    override fun getYouTubePlayerProvider(): YouTubePlayer.Provider {
        return fragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerFragment
    }


}