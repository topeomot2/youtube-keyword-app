package com.topeomot.youtubekeywordapp

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.topeomot.youtubekeywordapp.di.component.ApplicationComponent
import com.topeomot.youtubekeywordapp.di.component.DaggerApplicationComponent
import com.topeomot.youtubekeywordapp.di.module.ApplicationModule
import com.topeomot.youtubekeywordapp.di.module.NetworkModule
import net.danlew.android.joda.JodaTimeAndroid

class YoutubeKeywordApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build()
    }

    companion object Factory {
        fun get(context: Context): YoutubeKeywordApplication
                = context.applicationContext as YoutubeKeywordApplication
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        JodaTimeAndroid.init(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}