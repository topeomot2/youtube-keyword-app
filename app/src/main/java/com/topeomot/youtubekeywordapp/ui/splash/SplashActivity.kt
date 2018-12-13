package com.topeomot.youtubekeywordapp.ui.splash


import android.support.v4.content.ContextCompat
import com.daimajia.androidanimations.library.Techniques
import com.topeomot.youtubekeywordapp.R
import com.topeomot.youtubekeywordapp.ui.main.MainActivity
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor

class SplashActivity : AwesomeSplash() {

    override fun initSplash(configSplash: ConfigSplash?) {
        configSplash!!.backgroundColor = R.color.colorPrimaryDark; //any color you want form colors.xml
        configSplash.animCircularRevealDuration = 2000; //int ms
        configSplash.revealFlagX = Flags.REVEAL_RIGHT;  //or Flags.REVEAL_LEFT
        configSplash.revealFlagY = Flags.REVEAL_BOTTOM;

        configSplash.logoSplash = R.mipmap.ic_launcher; //or any other drawable
        configSplash.animLogoSplashDuration = 2000; //int ms
        configSplash.animLogoSplashTechnique = Techniques.Bounce

        configSplash.titleSplash = getString(R.string.app_name)
        configSplash.titleTextSize = 25f
        configSplash.titleTextColor = R.color.colorAccent
        configSplash.animTitleDuration = 3000
        configSplash.animTitleTechnique = Techniques.FadeIn
        configSplash.titleFont = "fonts/Righteous-Regular.ttf";

    }

    override fun animationsFinished() {
        startActivity(intentFor<MainActivity>().clearTop())
        finish()
    }
}
