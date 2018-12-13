package com.topeomot.youtubekeywordapp.ui.player

import android.content.Intent
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.topeomot.youtubekeywordapp.R
import com.topeomot.youtubekeywordapp.util.YoutubeKeywordConstant

abstract class YouTubeFailureRecoveryActivity : YouTubeBaseActivity(),
    YouTubePlayer.OnInitializedListener{

    private val RECOVERY_DIALOG_REQUEST = 1

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?,
                                         errorReason: YouTubeInitializationResult?) {
        if (errorReason!!.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
        } else {
            val errorMessage = String.format(getString(R.string.error_player), errorReason.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(YoutubeKeywordConstant.YOUTUBE_KEY, this);
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    protected abstract fun getYouTubePlayerProvider(): YouTubePlayer.Provider
}