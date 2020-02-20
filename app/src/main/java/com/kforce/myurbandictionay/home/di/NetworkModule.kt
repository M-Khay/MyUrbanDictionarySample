package com.kforce.myurbandictionay.home.di

import com.kforce.myurbandictionay.home.repository.DictionaryApi
import com.kforce.myurbandictionay.home.utils.Constants
import com.kforce.myurbandictionay.home.utils.Constants.INSTANCE.API_KEY
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideDictionaryApi(retrofit: Retrofit) = retrofit.create(DictionaryApi::class.java)

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideOkHttpClient():OkHttpClient{

        val cacheSize = (1 * 1024 * 1024).toLong()
        val definitionCache = Cache(createTempDir("definitionCacheDir"),cacheSize)
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .cache(definitionCache)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("x-rapidapi-key", API_KEY).build()
                chain.proceed(request)
            }
            .build()
    }
}