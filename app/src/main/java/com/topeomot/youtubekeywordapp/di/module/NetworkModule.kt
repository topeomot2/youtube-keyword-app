package com.topeomot.youtubekeywordapp.di.module

import android.content.Context
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.topeomot.youtubekeywordapp.data.DataRepository
import com.topeomot.youtubekeywordapp.data.YoutubeRepository
import com.topeomot.youtubekeywordapp.data.db.DbHelper
import com.topeomot.youtubekeywordapp.data.db.YoutubeDbHelper
import com.topeomot.youtubekeywordapp.data.network.ApiHelper
import com.topeomot.youtubekeywordapp.data.network.YoutubeApiHelper
import com.topeomot.youtubekeywordapp.di.ApplicationScope
import com.topeomot.youtubekeywordapp.util.YoutubeKeywordConstant
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @ApplicationScope
    fun provideCache(applicationContext: Context): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(applicationContext.cacheDir, cacheSize.toLong())
    }

    @Provides
    @ApplicationScope
    fun provideOKHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .cache(cache)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    fun provideJacksonMapper(): ObjectMapper {
        val objectMapper =ObjectMapper().registerModule(KotlinModule())
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true)
        return objectMapper
    }


    @Provides
    @ApplicationScope
    fun provideApiRetrofit(client: OkHttpClient, mapper: ObjectMapper): Retrofit {
        return Retrofit.Builder()
            .baseUrl(YoutubeKeywordConstant.SEARCH_URL)
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideApiHelper(youtubeApiHelper: YoutubeApiHelper): ApiHelper = youtubeApiHelper

    @Provides
    fun provideDbHelper(youtubeDbHelper: YoutubeDbHelper): DbHelper = youtubeDbHelper

    @Provides
    fun provideDataRepository(youtubeRepository: YoutubeRepository): DataRepository =
            youtubeRepository


}